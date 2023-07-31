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
import DislikeView from '../views/DislikeView.vue'

//adminPage
import AdminLoginPage from '../views/AdminLoginPage.vue'
import AdminView from '../views/AdminView.vue'
import AdminUserPage from '../components/adminPage/userPage.vue'
import AdminRecipePage from '../components/adminPage/recipePage.vue'
import AdminIngredientPage from '../components/adminPage/ingredientPage.vue'
import AdminSeasoningPage from '../components/adminPage/seasoningPage.vue'


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
    path: '/mypage',
    name: 'mypage',
    component: MyPage,
  },

  {
    path: '/mypage/refrigerator',
    name: 'refrigerator',
    component: RefrigeratorPage,
  },

  {
    path: '/mypage/tool',
    name: 'tool',
    component: ToolPage,
  },

  {
    path: '/mypage/like',
    name: 'like',
    component: LikeRecipe,
  },
  
  {
    path: '/mypage/edit',
    name: 'edit',
    component: EditPage,
  },

  {
    path: '/dislike',
    name: 'dislike',
    component: DislikeView
  },
  
  {
    path: '/admin-login',
    name: 'admin-login',
    component: AdminLoginPage
  },
  
  {
    path: '/admin',
    name: 'admin',
    component: AdminView,
    redirect: '/admin/user', // Default sub-route
    children: [
      {
        path: 'user',
        name: 'admin/user',
        component: AdminUserPage,
      },
      {
        path: 'recipe',
        name: 'admin/recipe',
        component: AdminRecipePage,
      },
      {
        path: 'ingredient',
        name: 'admin/ingredient',
        component: AdminIngredientPage,
      },
      {
        path: 'seasoning',
        name: 'admin/seasoning',
        component: AdminSeasoningPage,
      },
    ],
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
