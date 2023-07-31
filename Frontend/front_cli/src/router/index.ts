import { createRouter, createWebHistory } from 'vue-router'
import MainView from '../views/MainView.vue'
import SearchPage from "../components/searchPage/SearchPage.vue"
import SearchKeyword from "../components/searchPage/SearchKeyword.vue"
import SingupView from  '../views/SignupView.vue'
import LoginView from  '../views/LoginView.vue'
import AboutView from '../views/AboutView.vue'
import RecipeDetail from '../components/recipePage/recipeDetail.vue'
import MyPage from '../views/MyPage.vue'
import RefrigeratorPage from '../components/myPage/refrigeratorPage.vue'
import ToolPage from '../components/myPage/toolPage.vue'
import LikeRecipe from '../components/myPage/likeRecipe.vue'
import EditPage from '../components/myPage/editPage.vue'

const routes = [
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
  {
    // 검색 결과(키워드 없음) 페이지
    path: '/search',
    name: 'search',
    component: SearchPage,
  },
  {
    // 검색 결과(키워드 있음) 페이지
    path: '/search/:keyword',
    name: 'searchKeyword',
    component: SearchKeyword,
  },
  {
    // 상세 레시피 페이지
    // 후에 데이터 연결 후 recipe_id 연결해줘야 함
    path: '/recipe',
    name: 'recipe',
    component: RecipeDetail
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
    // 마이페이지(비밀번호 확인) -> 입력해야 마이페이지 이용 가능
    path: '/mypage',
    name: 'mypage',
    component: MyPage,
  },
  {
    // 마이페이지 냉장고 관리 페이지
    path: '/mypage/refrigerator',
    name: 'refrigerator',
    component: RefrigeratorPage,
  },
  {
    // 마이페이지 조리도구 관리 페이지
    path: '/mypage/tool',
    name: 'tool',
    component: ToolPage,
  },
  {
    // 마이페이지 좋아요한 레시피 페이지
    path: '/mypage/like',
    name: 'like',
    component: LikeRecipe,
  },
  {
    // 마이페이지 회원정보 수정 페이지
    path: '/mypage/edit',
    name: 'edit',
    component: EditPage,
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
