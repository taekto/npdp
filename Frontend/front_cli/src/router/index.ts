import { createRouter, createWebHistory } from 'vue-router'


// 기능 페이지
import MainView from '../views/MainView.vue'
// import SearchPage from "../components/searchPage/SearchPage.vue"
import SearchKeyword from "../components/searchPage/SearchKeyword.vue"
import SingupView from  '../views/SignupView.vue'
import LoginView from  '../views/LoginView.vue'
import SocialSignup from '../views/SocialSignup.vue'
import AboutView from '../views/AboutView.vue'
import RecipeDetail from '../components/recipePage/recipeDetail.vue'
import MyPage from '../views/MyPage.vue'
import RefrigeratorPage from '../components/myPage/refrigeratorPage.vue'
import ToolPage from '../components/myPage/toolPage.vue'
import LikeRecipe from '../components/myPage/likeRecipe.vue'
import memberInfoEditPage from '../components/myPage/editPage.vue'
import DislikeView from '../views/DislikeView.vue'
import FindPassword from '../views/findPassword.vue'
import AllergyView from '../components/dislikePage/memberAllergy.vue'

//adminPage
import AdminLoginPage from '../views/AdminLoginPage.vue'
import AdminView from '../views/AdminView.vue'
import AdminMemberPage from '../components/adminPage/memberPage.vue'
import AdminRecipePage from '../components/adminPage/recipePage.vue'
import AdminIngredientPage from '../components/adminPage/ingredientPage.vue'
import AdminSeasoningPage from '../components/adminPage/seasoningPage.vue'


const router = createRouter({
  history: createWebHistory(),
  routes: [
  {
    // 메인페이지
    path: '/',
    name: 'main',
    component: MainView
  },
  {
    // 서비스 설명 페이지
    path: '/about',
    name: 'about',
    component: AboutView
  },
  // {
  //   // 검색 결과(키워드 없음) 페이지
  //   path: '/search',
  //   name: 'search',
  //   component: SearchPage,
  // },
  {
    // 검색 결과(키워드 있음) 페이지
    path: '/search/:keyword?',
    name: 'searchKeyword',
    component: SearchKeyword,
  },
  {
    // 상세 레시피 페이지
    // 후에 데이터 연결 후 recipe_id 연결해줘야 함
    path: '/recipe/:recipeId',
    name: 'recipe',
    component: RecipeDetail,
  },
  {
    // 회원가입 페이지
    path: '/signup',
    name: 'signup',
    component: SingupView
  },
  {
    // 로그인 페이지
    path: '/login',
    name: 'login',
    component: LoginView
  },
  {
    path: '/social',
    name: 'social',
    component: SocialSignup,
    props: true
  },
  {
    // 마이페이지(비밀번호 확인)
    path: '/mypage',
    name: 'mypage',
    component: MyPage,
  },
  {
    // 마이페이지(냉장고)
    path: '/mypage/refrigerator',
    name: 'refrigerator',
    component: RefrigeratorPage,
  },
  {
    // 마이페이지(조리도구)
    path: '/mypage/tool',
    name: 'tool',
    component: ToolPage,
  },
  {
    // 마이페이지(좋아요한 레시피)
    path: '/mypage/like',
    name: 'like',
    component: LikeRecipe,
  },
  {
    // 마이페이지(회원정보 수정)
    path: '/mypage/edit',
    name: 'edit',
    component: memberInfoEditPage,
  },
  // 비밀번호 찾기
  {
    path: '/find',
    name: 'find',
    component: FindPassword,
  },
  {
    // 비선호 재료
    path: '/dislike',
    name: 'dislike',
    component: DislikeView
  },
  {
    path: '/allergy',
    name:'allergy',
    component: AllergyView
  },
  {
    // 관리자 페이지(로그인)
    path: '/admin-login',
    name: 'admin-login',
    component: AdminLoginPage
  },
  {
    // 관리자 페이지(메인)
    path: '/admin',
    name: 'admin',
    component: AdminView,
    redirect: '/admin/member', // Default sub-route
    children: [
      {
        // 관리자 페이지(유저)
        path: 'member',
        name: 'admin/member',
        component: AdminMemberPage,
      },
      {
        // 관리자 페이지(레시피)
        path: 'recipe',
        name: 'admin/recipe',
        component: AdminRecipePage,
      },
      {
        // 관리자 페이지(재료)
        path: 'ingredient',
        name: 'admin/ingredient',
        component: AdminIngredientPage,
      },
      {
        // 관리자 페이지(양념)
        path: 'seasoning',
        name: 'admin/seasoning',
        component: AdminSeasoningPage,
      },
    ]
  }]
  })


  router.beforeEach((to, from, next) => {
    // 페이지 전환 이전에 스크롤을 맨 위로 설정
    window.scrollTo(0, 0);
    next();
  });

export default router
