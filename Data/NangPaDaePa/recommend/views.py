# from django.shortcuts import render
from rest_framework import status
from rest_framework.decorators import api_view
from rest_framework.response import Response
from django.core import serializers
from django.http import JsonResponse

from .models import RecipeIngredient, Ingredient, RecipeRecommend, Recipe, Refrigerator, Member, MemberRecommend

import pandas as pd
import pickle
from sklearn.metrics.pairwise import cosine_similarity

from django.conf import settings
import os


@api_view(['GET'])
def recipes(request, format=None):

    # 0. 이전 데이터 삭제
    RecipeRecommend.objects.all().delete()

    # 1. 필요한 데이터
    #       - recipe_ingredient(레시피포함재료)
    #       - ingredient(재료)
    # 를 db로부터 불러옴
    recipe_ingredient_queryset = RecipeIngredient.objects.all()
    ing_queryset = Ingredient.objects.all()
    
    # 2. QuerySet을 DataFrame으로 변환
    recipe_ingredient_df = pd.DataFrame.from_records(recipe_ingredient_queryset.values())
    ing_df = pd.DataFrame.from_records(ing_queryset.values())

    # 3. 중복값을 삭제
    unique_recipe_ing = recipe_ingredient_df[['Recipe_id', 'ingredient_id']].drop_duplicates()

    # 4. groupby
    grouped = unique_recipe_ing.groupby('Recipe_id')['ingredient_id'].apply(list).reset_index(name='ing_list')
    
    # 5. 주재료에 가중치
    grouped['ing_list'] = grouped.apply(double_main_ing, axis=1, additional_param=recipe_ingredient_df)
    
    # 6. 부모 추가
    grouped['upper_class_list'] = grouped.apply(add_upper_class, axis=1, additional_param=ing_df)

    # 7. Vectorizer에 사용할 문자열 열 생성
    grouped['total_ing'] = grouped['ing_list'].apply(lambda x: ' '.join(map(str, x))) 

    # 8. index 
    recipeId_to_index = dict(zip(grouped['Recipe_id'], grouped.index))
    index_to_recipeId = {v: k for k, v in recipeId_to_index.items()}

    # 9. CountVectorizer 적용
    vectorizer = CountVectorizer()
    X = vectorizer.fit_transform(grouped['total_ing'])
    
    # 10. similarity matrix
    similarity_matrix = cosine_similarity(X)

    print(similarity_matrix)

    save_similarity_between_recipes(recipeId_to_index, index_to_recipeId, similarity_matrix)

    return Response("200")

@api_view(['GET'])
def users(request, format=None):

    # 0. 이전 데이터 삭제
    # MemberRecommend.objects.all().delete()

    # 1. 모델 불러오기
    ## CountVectorizer 객체 불러오기
    BASE_DIR = os.path.join(settings.STATICFILES_DIRS[0], 'files/static/model/')

    try:
      with open(BASE_DIR+'vectorizer.pkl', 'rb') as f:
        vectorizer = pickle.load(f)
      
      ## recipe 행렬 불러오기
      with open(BASE_DIR+'similarityX.pkl', 'rb') as f:
        X = pickle.load(f)

      ## recipeId -> Index 객체 불러오기
      with open(BASE_DIR+'recipeId_to_index.pkl', 'rb') as f:
        recipeId_to_index = pickle.load(f)

      ## Index -> recipeId 객체 불러오기
      with open(BASE_DIR+'index_to_recipeId.pkl', 'rb') as f:
        index_to_recipeId = pickle.load(f)
    
      user_similarity_df = calcurate_user_similarity(vectorizer, X, index_to_recipeId, recipeId_to_index)
      print(user_similarity_df)
      save_similarity_for_user(user_similarity_df)
    except FileNotFoundError:
        return Response("404")

    return Response("200")

## CountVectorizer에서 주재료에 가중치 주기 위한 함수
def double_main_ing(row, additional_param):
  row['ing_list_befo'] = row['ing_list']
  ing_list_new = row['ing_list'].copy()

  for ing_id in row['ing_list']:
    ing_type =  additional_param[( additional_param['Recipe_id'] == row['Recipe_id']) & ( additional_param['ingredient_id'] == ing_id)].iloc[0]['type']
    if ing_type == 0 : 
      ing_list_new.append(ing_id)

  return ing_list_new

# 재료들의 조상들도 등록
def add_upper_class(row,  additional_param):
  upper_class_set = row['ing_list'].copy()

  for ing_id in row['ing_list']:
    cur_id = ing_id
    upper_class_code =  additional_param[ additional_param['ingredient_id'] == cur_id].iloc[0]['upper_class']
    upper_class =  additional_param[ additional_param['code'] == upper_class_code].iloc[0]
    upper_class_set.append(upper_class['ingredient_id'])
    while upper_class['level']!=1:
      upper_class_code = upper_class['upper_class']
      upper_class =  additional_param[ additional_param['code'] == upper_class_code].iloc[0]
      upper_class_set.append(upper_class['ingredient_id'])
  
  return upper_class_set

def save_similarity_between_recipes(recipeId_to_index, index_to_recipeId, similarity_matrix):
    recipe_ids = Recipe.objects.values_list('recipe_id', flat=True)

    for recipe_id in recipe_ids:

        recipe_index = recipeId_to_index[recipe_id]
        # 유사도 추출
        similarity_values = similarity_matrix[recipe_index]
        
        records_to_save = []

        # 다른 레시피 아이디 조회
        for other_recipe_index, similarity in enumerate(similarity_values):
            if other_recipe_index != recipe_index:  # 같은 레시피 아이디는 건너뛰기
                other_recipe_id = index_to_recipeId[other_recipe_index]
                
                # RecipeRecommend.objects.create(
                #     recipe_own_id=recipe_id,
                #     recipe_slave_id=other_recipe_id,
                #     similarity=similarity
                # )
                records_to_save.append({
                    'recipe_own_id': recipe_id,
                    'recipe_slave_id': other_recipe_id,
                    'similarity': similarity
                })
    # DataFrame 생성
    df_to_save = pd.DataFrame(records_to_save)
    
    # DataFrame을 데이터베이스에 저장
    RecipeRecommend.objects.bulk_create([
        RecipeRecommend(**row) for _, row in df_to_save.iterrows()
    ])


def calcurate_user_similarity(vectorizer, X, index_to_recipeId, recipeId_to_index):
  records_to_save = []

  refregirator_queryset = Refrigerator.objects.values('member_id', 'ingredient_id').distinct()
  refregirator_df = pd.DataFrame.from_records(refregirator_queryset)

  ing_queryset = Ingredient.objects.all()
  ing_df = pd.DataFrame.from_records(ing_queryset.values())

  grouped = refregirator_df.groupby('member_id')['ingredient_id'].apply(list).reset_index(name='ing_list')
  print(grouped['member_id'].values)
  grouped['upper_class_list'] = grouped.apply(add_upper_class, axis=1, additional_param=ing_df)
  grouped['total_ing'] = grouped['upper_class_list'].apply(lambda x: ' '.join(map(str, x)))

  print("grouped의 member_id")
  print(grouped['member_id'].values)
  print(grouped)

  for index, row in grouped.iterrows():
    new_X = vectorizer.transform([row['total_ing']])

    # 새로운 벡터와 기존 벡터 간의 유사도 계산
    new_similarity_values = cosine_similarity(new_X, X)

    # 다른 레시피 아이디 조회
    for recipe_index, similarity in enumerate(new_similarity_values[0]):
      # print("recipe_index: "+str(recipe_index))
      recipe_id = index_to_recipeId[recipe_index]
      
      records_to_save.append({
          # 'recipe_recommend_id': index+1,
          'member_id': row['member_id'],
          'recipe_id': recipe_id,
          'similarity': similarity
      })

  df_to_save = pd.DataFrame(records_to_save)
  df_to_save['member_recommend_id'] = df_to_save.index+1

  return df_to_save

def save_similarity_for_user(user_similarity_df):
  objects_to_insert = [MemberRecommend(**row) for _, row in user_similarity_df.iterrows()]
  MemberRecommend.objects.bulk_create(objects_to_insert)