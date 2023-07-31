<template>
  <div>
    <div>
        <router-link to="/mypage/refrigerator">내 냉장고</router-link>
        <router-link to="/mypage/tool">내 조리도구</router-link>
        <router-link to="/mypage/like">내가 좋아요한 레시피</router-link>
        <router-link to="/mypage/edit">내 정보 수정</router-link>
    </div>
    <h1>도구</h1>
  </div>
</template>

<script>
export default {
    name: 'ToolPage',
}
</script>

<style>

</style>