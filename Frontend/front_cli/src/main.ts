import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import 'bootstrap'
import 'bootstrap/dist/css/bootstrap.min.css'
import VCalendar from 'v-calendar';
import 'v-calendar/style.css';

<<<<<<< HEAD
createApp(App).use(router).use(store).mount('#app')

=======
createApp(App).use(router).use(store).use(router).use(VCalendar).mount('#app')
>>>>>>> 7ce6a7470900fd4b2fcfa8cef66603bb7489b144
