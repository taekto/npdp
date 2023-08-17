<template>
    <!-- 좌측 메뉴 변경 컴포넌트 -->
    <div class="myPageComponent" id="myPageMenu">
        <div class="mypage_title">마이페이지</div>
        <div class="category_container">
            <div class="category_title">HOME</div>
            <div class="category_name">
                <router-link 
                v-if="nowPage === 'http://localhost:8080/mypage/edit' || nowPage === 'https://i9b202.p.ssafy.io/mypage/edit'" 
                to="/mypage/edit">{{link4Text}}</router-link>
                <router-link 
                v-else to="/mypage">{{link4Text}}</router-link>
            </div>
            <div class="category_title">주방 관리</div>
            <div class="category_name">
                <router-link to="/mypage/refrigerator">{{link1Text}}</router-link>
            </div>
            <div class="category_name">
                <router-link to="/mypage/tool">{{link2Text}}</router-link>
            </div>
            <!-- 비선호/ 알러지 -->
            <div class="category_name">
                <router-link to="/dislike">{{link5Text}}</router-link>
            </div>
            <div class="category_title">레시피 관리</div>
            <div class="category_name">
                <router-link to="/mypage/like">{{link3Text}}</router-link>
            </div>
            <div class="category_name">
                <router-link to="/allergy">{{link6Text}}</router-link>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: 'CategoryComponent',
    data() {
        return {
            isViewportSmall: false, // 뷰포트 크기가 작은지 여부
            nowPage : '',
        };
    },
    created() {
        this.checkViewportSize(); // 초기 값 설정
        this.nowPage = document.location.href

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
        link5Text() {
            return this.isViewportSmall ? "비선호재료" : "비선호재료";
        },
        link6Text() {
            return this.isViewportSmall ? "알러지" : "내 알러지";
        },

    }
}
</script>

<style scoped>
.myPageComponent {
    position: relative;
    margin-right: .5rem;
}
.mypage_title {
    font-family: 'LINESeedKR-Bd';
    font-size: 2rem;
    text-align: left;
    margin-top: 1.5rem;
}
.category_container {
    position: absolute;
    top: 6rem;
    left: .5rem;
}
.category_title {
    font-family: 'LINESeedKR-Bd';
    text-align: left;
    left: 1rem;
    color: #aeaeae;
    margin-bottom: 2rem;
    margin-top: 3rem;
}
.myPageComponent {
    margin-top: 1rem;
    /* margin-bottom: 3rem; */
    /* border: 1px solid black; */
    border-radius: .4rem;
    /* box-shadow: 1px 1px 1px 1px; */
    margin-left: 3rem;
    height: 17rem;
    min-width: 12rem;
}

.category_name {
    margin: 1rem;
    margin-left: 1.5rem;
    text-align: left;
    /* border-bottom: 1px solid #000000; */
    width: 90%;
}

.myPageComponent div a {
    text-decoration: none;
    color: #4e4e4e;
    font-weight: bold;
    font-size: 1.2rem;
    font-family: 'LINESeedKR-Rg';
}
.myPageComponent div a.router-link-exact-active {
    font-size: 1.5rem;
  color: #FD7E14;
}
</style>