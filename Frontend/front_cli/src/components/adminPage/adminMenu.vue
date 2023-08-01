<template>
  <div class="admin-view">
    <div class="left-area">
      <AdminMenu @menu-click="handleMenuClick" />
    </div>
    <div class="right-area">
      <component :is="selectedComponent" />
    </div>
  </div>
</template>

<script>
import { defineComponent, ref } from 'vue';
import AdminMenu from '../components/adminPage/adminMenu.vue';

export default defineComponent({
  components: {
    AdminMenu,
  },
  setup() {
    const selectedComponent = ref('UserPage'); // 기본적으로 UserPage를 출력

    function handleMenuClick(page) {
      selectedComponent.value = page;
    }

    return {
      selectedComponent,
      handleMenuClick,
    };
  },
});
</script>

<style>
/* 스타일링 코드는 필요에 따라 추가하시면 됩니다. */
</style>
