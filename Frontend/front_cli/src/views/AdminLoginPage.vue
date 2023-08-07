<template>
<!-- 로그인 -->
<div class="login_container">
  <div class="login_card">
    <form class="login_form" id="login_form">
      <h1 class="form_title">Log in to Admin account</h1>
        <label for="email" class="input_label">Email</label>
        <input
          type="email"
          id="email"
          placeholder="Enter your Email"
          class="input"
          name="email"
          style="width: 100%; height: 33.6px"  
        />
        <label for="password" class="input_label">Password</label>
        <input
          type="password"
          id="password"
          placeholder="Password"
          class="input"
          name="password"
          style="width: 100%; height: 33.6px"
        />
      <button class="login_btn" style="width: 100%;">Sign In</button>
    </form>
  </div>
  
</div>

</template>

<script lang="ts">
import { Options, Vue } from 'vue-class-component';

@Options({
})
export default class LoginView extends Vue {
  
  credentials = {
    email: '',
    password: '',
  };
  
}
</script>

<style scoped>

/* 로그인 */
.login_card {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  margin: 15px 0;
  font-family: 'Inter', sans-serif;
}

.form_title {
  text-align: center;
  margin-bottom: 15px;
}

.input_label {
  display: flex;
  padding: 15px 0px 5px 0px;
}

.login_btn {
  margin-top: 25px;
  font-size: 14px;
  border: none; 
  background-color: #FD7E14; 
  color: #FFFFFF;
  padding: 7.5px;
  cursor: pointer; 
  border-radius: 4px; 
}
span > a {
  color: #FD7E14;
}

.login_signup {
  margin: 40px 0px 60px 0px;
  text-align: center;
}


</style>

