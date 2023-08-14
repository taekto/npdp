<template>
    <!-- 좌측 메뉴 변경 컴포넌트 -->
    <div class="myPageComponent" id="myPageMenu">
        <div>
            <router-link to="/mypage/refrigerator">{{link1Text}}</router-link>
        </div>
        <div>
            <router-link to="/mypage/tool">{{link2Text}}</router-link>
        </div>
        <div>
            <router-link to="/mypage/like">{{link3Text}}</router-link>
        </div>
        <div>
            <router-link to="/mypage/edit">{{link4Text}}</router-link>
        </div>
    </div>
</template>

<script>
export default {
    name: 'CategoryComponent',
    data() {
        return {
            isViewportSmall: false, // 뷰포트 크기가 작은지 여부
        };
    },
    created() {
        this.checkViewportSize(); // 초기 값 설정

        window.addEventListener('resize', this.checkViewportSize); // resize 이벤트 리스너 추가
    },
    beforeUnmount() {
        window.removeEventListener('resize', this.checkViewportSize); // resize 이벤트 리스너 제거
    },
    methods: {
        checkViewportSize() {
            this.isViewportSmall = window.innerWidth <= 1180;
        },
    },
    computed: {
        link1Text() {
            return this.isViewportSmall ? "냉장고" : "내 냉장고";
        },
        link2Text() {
            return this.isViewportSmall ? "조리도구" : "내 조리도구";
        },
        link3Text() {
            return this.isViewportSmall ? "좋아요" : "좋아요한 레시피";
        },
        link4Text() {
            return this.isViewportSmall ? "정보수정" : "내 정보수정";
        },
    }
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
    color: #757575;
    font-weight: bold;
    font-size: 1.5rem;
    font-family: 'LINESeedKR-Rg';
}

.myPageComponent div a.router-link-exact-active {
  color: #FD7E14;
}
</style>