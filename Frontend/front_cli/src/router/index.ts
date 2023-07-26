import { createRouter, createWebHistory } from 'vue-router'
import MainView from '../views/MainView.vue'
import SearchPage from "../components/searchPage/SearchPage.vue"
import SearchKeyword from "../components/searchPage/SearchKeyword.vue"
import SingupView from  '@/views/SignupView.vue'
import LoginView from  '@/views/LoginView.vue'
import AboutView from '@/views/AboutView.vue'
import RecipeDetail from '@/components/recipePage/recipeDetail.vue'
import DisfavorView from '@/views/DisfavorView.vue'

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
    path: '/recipe/:recipe_id',
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
    path: '/disfavor',
    name: 'disfavor',
    component: DisfavorView
  },

]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
