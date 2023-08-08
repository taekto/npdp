import { createApp } from 'vue';
import App from './App.vue';
import router from './router';
import store from './store';
import 'bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import VCalendar from 'v-calendar';
import 'v-calendar/style.css';
import axios from 'axios'
import "@/assets/font.css"

axios.defaults.withCredentials = true


createApp(App).use(router).use(store).use(router).use(VCalendar).mount('#app');
