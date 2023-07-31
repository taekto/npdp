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

const routes = [
  {
    path: '/',
    name: 'main',
    component: MainView
  },

  {
    path: '/about',
    name: 'about',
    component: AboutView
  },
  {
    path: '/search',
    name: 'search',
    component: SearchPage,
  },
  {
    path: '/search/:keyword',
    name: 'searchKeyword',
    component: SearchKeyword,
  },
  {
    // path: '/recipe/:recipe_id',
    path: '/recipe',
    name: 'recipe',
    component: RecipeDetail
  },
  {
    path: '/signup',
    name: 'signup',
    component: SingupView
  },

  {
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

]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
