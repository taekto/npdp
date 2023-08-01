<template>
    <!-- 좌측 메뉴 변경 컴포넌트 -->
    <div class="myPageComponent" id="myPageMenu">
        <div>
            <router-link to="/mypage/refrigerator">내 냉장고</router-link>
        </div>
        <div>
            <router-link to="/mypage/tool">내 조리도구</router-link>
        </div>
        <div>
            <router-link to="/mypage/like">좋아요한 레시피</router-link>
        </div>
        <div>
            <router-link to="/mypage/edit">내 정보 수정</router-link>
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
    margin-top: 3rem;
    margin-bottom: 3rem;
    border-radius: .5rem;
    box-shadow: 1px 1px 1px 1px;
    margin-left: 1rem;
    height: 15rem;
}

.myPageComponent div {
    margin: auto;
    margin-top: 1rem;
    border-bottom: 1px solid #bdbdbd;
    width: 90%;
}

.myPageComponent div a {
    text-decoration: none;
    color: #FD7E14;
    font-weight: bold;
    font-size: 1.5rem;
    
}

.myPageComponent div a.router-link-exact-active {
  color: #42b983;
}
</style>