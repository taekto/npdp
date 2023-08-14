<template>
    <!-- 좌측 메뉴 변경 컴포넌트 -->
    <div class="myPageComponent" id="myPageMenu">
        <div>
            <router-link to="/mypage/refrigerator" class="category">내 냉장고</router-link>
        </div>
        <div>
            <router-link to="/mypage/tool" class="category">내 조리도구</router-link>
        </div>
        <div>
            <router-link to="/mypage/like" class="category">좋아요한 레시피</router-link>
        </div>
        <div>
            <router-link to="/mypage/edit" class="category">내 정보 수정</router-link>
        </div>
    </div>
</template>

<script>
export default {
    name: 'CategoryComponent',
}
</script>

<style scoped>
.myPageComponent {
    margin-top: 5rem;
    /* margin-bottom: 3rem; */
    /* border: 1px solid black; */
    border-radius: .4rem;
    /* box-shadow: 1px 1px 1px 1px; */
    margin-left: 3rem;
    height: 17rem;
    min-width: 12rem;
}

.myPageComponent div {
    margin: 1rem auto 2rem;
    /* border-bottom: 1px solid #000000; */
    width: 90%;
}

.myPageComponent div a {
    text-decoration: none;
    color: #000000;
    font-weight: bold;
    font-size: 1.4rem;
    margin-bottom: 2rem;
}
.category {
    font-family: 'LINESeedKR-Rg';
}
.myPageComponent div a.router-link-exact-active {
  /* color: #42b983; */
}
</style>