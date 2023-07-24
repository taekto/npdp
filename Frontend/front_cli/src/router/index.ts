import { createRouter, createWebHistory } from 'vue-router'
import MainView from '../views/MainView.vue'
import SearchPage from "../components/searchPage/SearchPage.vue"
import SearchKeyword from "../components/searchPage/SearchKeyword.vue"
import SingupView from  '@/views/SignupView.vue'
import LoginView from  '@/views/LoginView..vue'

const routes = [
  {
    path: '/',
    name: 'home',
    component: MainView
  },

  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
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
    path: '/signup',
    name: 'signup',
    component: SingupView
  },

  {
    path: '/login',
    name: 'login',
    component: LoginView
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
