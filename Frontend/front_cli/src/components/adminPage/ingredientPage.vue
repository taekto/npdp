<template>
  <div>
    <!-- 여기에 레시피 관리 페이지의 내용을 작성합니다. -->
    <h2>레시피 관리 페이지</h2>
    <div class="admin_ingredient_container">
      <template v-if="ingredient.length > 0">
        <div v-for="ingredientItem in ingredient" class="ingredient_list" :key="ingredientItem.ingredient_id">
          <span class="ingredient_id">{{ ingredientItem.ingredient_id }} </span>
          <span class="ingredient_title">{{ ingredientItem.title }} </span>
          <span class="ingredient_update col">수정</span>
          <span class="ingredient_delete col-2">삭제</span>
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
    ...mapGetters(['ingredient']), 
  },
}
</script>

<style>
.admin_ingredient_container {
  display: flex;
  flex-direction: column;
}

.ingredient_list {
  display: flex;
}

.ingredient_id {
  margin-right: 20px;
}


.ingredient_update {
  text-align: right;

}

.ingredient_delete {
  text-align: right;
  margin-right: 10px;
} 
</style>
