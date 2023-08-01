import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import 'bootstrap'
import 'bootstrap/dist/css/bootstrap.min.css'
import VCalendar from 'v-calendar';
import 'v-calendar/style.css';

<<<<<<< HEAD

=======
>>>>>>> 6cb65edb133f3b348b3eb90d4adc9a944e3dbc18
createApp(App).use(router).use(store).use(router).use(VCalendar).mount('#app')
