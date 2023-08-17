<template>
  <div>
    <!-- 여기에 레시피 관리 페이지의 내용을 작성합니다. -->
    <h2>레시피 관리 페이지</h2>
    <div class="admin_recipe_container">
      <template v-if="recipe.length > 0">
        <div v-for="recipeItem in recipe" class="recipe_list" :key="recipeItem.recipeId">
          <span class="recipeId">{{ recipeItem.recipeId }} </span>
          <span class="recipe_name">{{ recipeItem.name }} </span>
          <span class="recipe_update col">수정</span>
          <span class="recipe_delete col-2">삭제</span>
        </div>
      </template>
      <template v-else>
        로딩 중...
      </template>
    </div>
  </div>
</template>


<script>
import { mapGetters } from 'vuex';

export default {
  computed: {
    ...mapGetters(['recipe']), 
  },
   created() {
    console.log(this.recipe);
  },
}
</script>

<style>
.admin_recipe_container {
  display: flex;
  flex-direction: column;
}

.recipe_list {
  display: flex;
}

.recipeId {
  margin-right: 20px;
}


.recipe_update {
  text-align: right;

}

.recipe_delete {
  text-align: right;
  margin-right: 10px;
} 
</style>
