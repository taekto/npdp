<template>
  <div>
    <!-- 여기에 레시피 관리 페이지의 내용을 작성합니다. -->
    <h2>레시피 관리 페이지</h2>
    <div class="admin_seasoning_container">
      <template v-if="seasoning.length > 0">
        <div v-for="seasoningItem in seasoning" class="seasoning_list" :key="seasoningItem.seasoning_id">
          <span class="seasoning_id">{{ seasoningItem.seasoning_id }} </span>
          <span class="seasoning_name">{{ seasoningItem.name }} </span>
          <span class="seasoning_update col">수정</span>
          <span class="seasoning_delete col-2">삭제</span>
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
    ...mapGetters(['seasoning']), 
  },
   created() {
    console.log(this.seasoning);
  },
}
</script>

<style>
.admin_seasoning_container {
  display: flex;
  flex-direction: column;
}

.seasoning_list {
  display: flex;
}

.seasoning_id {
  margin-right: 20px;
}


.seasoning_update {
  text-align: right;

}

.seasoning_delete {
  text-align: right;
  margin-right: 10px;
} 
</style>
