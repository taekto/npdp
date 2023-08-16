from django.db import models

# Create your models here.
# 사용자 - 레시피 유사도
from django.db import models


class Ingredient(models.Model):
    ingredient_id = models.AutoField(primary_key=True)
    code = models.CharField(max_length=255)
    kor = models.CharField(max_length=255)
    level = models.IntegerField()
    upper_class = models.CharField(max_length=255)
    super_upper_class = models.CharField(max_length=255)
    
    class Meta:
        managed = False 
        db_table = 'ingredient'  




class Recipe(models.Model):
    recipe_id = models.AutoField(primary_key=True)
    name = models.CharField(max_length=255)
    
    class Meta:
        managed = False 
        db_table = 'recipe'  

class RecipeIngredient(models.Model):
    recipe_ingredient_id = models.AutoField(primary_key=True)
    Recipe = models.ForeignKey(Recipe, on_delete=models.CASCADE)
    ingredient = models.ForeignKey(Ingredient, on_delete=models.CASCADE, related_name='recipe_ingredients')
    type = models.IntegerField()

    class Meta:
        managed = False 
        db_table = 'recipe_ingredient'  

class RecipeRecommend(models.Model):
    recipe_recommend_id = models.AutoField(primary_key=True)
    recipe_own_id = models.IntegerField()
    recipe_slave_id = models.IntegerField()
    similarity = models.DecimalField(max_digits=5, decimal_places=2, default=0.0)

    class Meta:
        managed = False 
        db_table = 'recipe_recommend'  



class Member(models.Model):
    member_id = models.AutoField(primary_key=True)
    email = models.CharField(max_length=255)

    class Meta:
        managed = False
        db_table = 'member'

class Refrigerator(models.Model):
    refregirator_id = models.AutoField(primary_key=True)
    member_id = models.IntegerField()
    ingredient = models.ForeignKey(Ingredient, on_delete=models.CASCADE, db_column='ingredient_id')
    # storage = models.IntegerField()
    # amount = models.DecimalField(max_digits=10, decimal_places=4)
    # start_date = models.DateField()
    # expired_date = models.DateField()

    class Meta:
        managed = False 
        db_table = 'refregirator'  

class MemberRecommend(models.Model):
    member_recommend_id = models.AutoField(primary_key=True)
    member_id = models.IntegerField()
    # recipe_id = models.ForeignKey(Recipe, on_delete=models.CASCADE, db_column='recipe_id')
    recipe_id = models.IntegerField()
    similarity = models.DecimalField(max_digits=5, decimal_places=2)

    class Meta:
        managed = False 
        db_table = 'member_recommend'  

