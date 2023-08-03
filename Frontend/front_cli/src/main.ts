import { createApp } from 'vue';
import App from './App.vue';
import router from './router';
import store from './store';
import 'bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import VCalendar from 'v-calendar';
import 'v-calendar/style.css';
import axios from 'axios'; // axios import 추가

axios.defaults.withCredentials = true; // axios 설정 추가

createApp(App).use(router).use(store).use(router).use(VCalendar).mount('#app');
