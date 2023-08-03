<template>
<!-- 로컬로그인 -->
<div class="login_container">
  <div class="login_card">
    <form class="login_form" id="login_form" @submit.prevent="memberLogin(credentials)">
      <h1 class="form_title">Log in to your account</h1>
        <label for="email" class="input_label">Email</label>
        <input
          type="email"
          id="email"
          placeholder="Enter your Email"
          class="input"
          name="email"
          v-model="credentiacditials.email"
          style="width: 100%; height: 33.6px"  
        />
        <label for="password" class="input_label">Password</label>
        <input
          type="password"
          id="password"
          placeholder="Password"
          class="input"
          name="password"
          v-model="credentials.password"
          style="width: 100%; height: 33.6px"
        />
        <div class="login_form_remember">
          <!-- <input
            type="checkbox"
            id="remember_me"
            v-model="rememberMe"
          /> -->
          <!-- <label for="remember_me">Remember me</label> -->
          <div class="forgot_password">
            <router-link to="/signup">Forgot password</router-link>
          </div>
        </div>
      <button class="login_btn" style="width: 100%;">Log In</button>
      
      <!-- 회원가입 페이지 router -->
      <div class="login_signup">
        <span>Don’t have an account? <router-link to="/signup">Sign Up</router-link></span>
      </div>
      
      <!-- 소셜 로그인 -->
      <div class="login_sns">
        <div class="login_sns_google">
          <a href="http://localhost:8080/signup" class="btn_sns btn-google btn-block">
          Google 계정으로 로그인</a>
        </div>
        <div class="login_sns_naver">
          <a href="http://localhost:8080/signup" class="btn_sns btn-naver btn-block"><i class="fab fab-naver-alt"></i> 네이버 계정으로 로그인</a>
        </div>
        <div class="login_sns_kakao">
          <a href="http://localhost:8080/signup" class="btn_sns btn-kakao btn-block">
          KaKao 계정으로 로그인</a>
        </div>
      </div>

    </form>
  </div>
  
</div>

</template>

<script lang="ts">

import { mapActions } from 'vuex';

export default {
  
  data() {
    return {
      credentials: {
        email: '',
        password: '',
      },
    }
  },

  methods: {
    ...mapActions(["memberLogin"]),
  },
  
  
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

/* 소셜 로그인 */
.btn_sns {
    font-weight: normal;
    text-align: center;
    white-space: nowrap;
    vertical-align: middle;
    cursor: pointer;
    background-image: none;
    border: 1px solid transparent;
    padding: 6px 12px;
    font-size: 14px;
    line-height: 1.42857143;
    border-radius: 4px;
    -webkit-user-select: none;
    -moz-user-select: none;
    -ms-user-select: none;
    user-select: none;
    text-decoration: none;
    margin: 25px 0px 25px 0px;
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
    min-width: 280px;
}

.btn-naver {
    background: #01c73c;
    border-color: #01c73c;
    color: #fff;
    font-weight: 500;
    font-family: 'Nexon Gothic', 'Malgun Gothic', Sans-Serif;
    background-image: url(../assets/naver_login2x.png);
    background-size: 17px;
    background-position: 17px 50%;
    background-repeat: no-repeat;
    display: block;
    position: relative;
    min-width: 280px;
}

.btn-kakao {
    color: #000000;
    background-color: #FEE500;
    border-color: #ccc;
    background-size: 20px;
    background-position: 15px 50%;
    background-repeat: no-repeat;
    display: block;
    position: relative;
    font-family: Sans-Serif !important;
    min-width: 280px;
    background-image: url(../assets/Kakao.png);
}

</style>

