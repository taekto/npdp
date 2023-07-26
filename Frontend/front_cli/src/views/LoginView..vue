<template>
<!-- 로그인 -->
<div class="login_container">
  <div class="login_card">
    <form class="login_form" id="login_form">
      <h1 class="form_title">Log in to your account</h1>
      <div class="form_title_message">
        <span>Welcome back please enter your details</span>
      </div>
        <br>
        <label for="email" class="input_label">Email</label>
        <input
          type="email"
          id="email"
          placeholder="Enter your Email"
          class="input"
          name="email"
          style="width: 97.5%; height: 26px"  
        />
        <label for="password" class="input_label">Password</label>
        <input
          type="password"
          id="password"
          placeholder="Password"
          class="input"
          name="password"
          style="width: 97.5%; height: 26px"
        />
        <div class="login_form_remember">
          <input
            type="checkbox"
            id="remember_me"
            v-model="rememberMe"
          />
          <label for="remember_me">Remember me</label>
          <div class="forgot_password">
            <router-link to="/signup">Forgot password</router-link>
          </div>
        </div>
      <button class="btn" style="width: 100%;">Sign In</button>
    </form>

<!-- 회원가입 -->

    <div class="login_signup">
      <span>Don’t have an account? <router-link to="/signup">Sign Up</router-link></span>
    </div>
    <!-- 소셜 로그인 -->
    <div class="login_sns">
      <div class="login_sns_google">
        <a href="https://accounts.google.com/o/oauth2/auth?approval_prompt=auto&amp;response_type=code&amp;scope=email+profile&amp;redirect_uri=https%3A%2F%2Fimweb.me%2Foauth%2F&amp;client_id=632560491025-lqk88mdp4sb1oh6g9evlrrb2b811fpeg.apps.googleusercontent.com&amp;state=%2Fjoin_complete%7Cservice%7Cgoogle&amp;svctype=0" class="btn btn-google btn-block">
            Google 계정으로 가입</a>
      </div>
      <div class="login_sns_naver">
        <a href="https://nid.naver.com/oauth2.0/authorize?response_type=code&amp;redirect_uri=https%3A%2F%2Fimweb.me%2Foauth%2F&amp;client_id=j3e_Hw3XnZ1u_vI5aZoK&amp;state=%2Fjoin_complete%7Cservice%7Cnaver&amp;svctype=0" class="btn btn-naver btn-block"><i class="fab fab-naver-alt"></i> 네이버 계정으로 가입</a>
      </div>
      
    </div>
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
}

.form_title_message {
  text-align: center;
}

.input_label {
  display: flex;
  padding: 15px 0px 5px 0px;
}

.login_form_remember {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-top: 15px;
}

.login_form_remember label {
  margin-left: 5px;
}

.forgot_password {
  margin-left: auto; 
}

.forgot_password > a {
  color: #FD7E14;
}

.btn {
  margin-top: 25px;
  border: none; 
  background-color: #FD7E14; 
  color: #FFFFFF;
  padding: 7.5px;
  cursor: pointer; 
  border-radius: 5px; 
}

/* 회원가입 */
.login_signup {
  margin-top: 35px; /* 로그인 폼과의 여백 */
}

span > a {
  color: #FD7E14;
}

/* 소셜 로그인 */
.btn {
    padding: 13px 12px;
    font-size: 16px;
    vertical-align: baseline;
}

.btn-google {
    color: #000;
    background-color: #ffffff;
    border-color: #ccc;
    background-image: url(../assets/google_icon.png);
    background-size: 20px;
    background-position: 15px 50%;
    background-repeat: no-repeat;
    display: block;
    position: relative;
    font-family: Sans-Serif !important;
}

.btn-naver {
    background: #01c73c;
    border-color: #01c73c;
    color: #fff;
    font-weight: 500;
    font-family: 'Nexon Gothic', 'Malgun Gothic', Sans-Serif;
}



</style>
