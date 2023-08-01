<template>
  <div class="admin-page">
    <div class="left-sidebar">
      <!-- 메뉴 영역 -->
      <ul>
        <li @click="navigateTo('/admin/member')">유저 관리</li>
        <li @click="navigateTo('/admin/recipe')">레시피 관리</li>
        <li @click="navigateTo('/admin/ingredient')">재료 관리</li>
        <li @click="navigateTo('/admin/seasoning')">양념 관리</li>
      </ul>
    </div>
    <div class="right-content">
      <!-- 선택 영역 -->
      <div class="selected-menu">
        <router-view></router-view>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  methods: {
    navigateTo(path) {
      this.$router.push(path);
    },
  },
};
</script>

<style>
.admin-page {
  display: flex;
}

.left-sidebar {
  flex: 1;
  background-color: #f2f2f2;
  padding: 20px;
  box-sizing: border-box;
}

.right-content {
  flex: 4;
  padding: 20px;
  box-sizing: border-box;
}

/* 메뉴 스타일링 */
.left-sidebar ul {
  list-style: none;
  padding: 0;
  margin: 0;
}

.left-sidebar li {
  cursor: pointer;
  padding: 10px;
  border-bottom: 1px solid #ccc;
}

.left-sidebar li:last-child {
  border-bottom: none;
}

/* 선택 영역 스타일링 */
.selected-menu {
  border: 1px solid #ccc;
  min-height: 300px;
  padding: 10px;
}


</style>
