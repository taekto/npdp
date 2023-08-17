<template>
<!-- 로컬로그인 -->
<div class="login_container">
  <div class="login_card">
    <form class="login_form" id="login_form" @submit.prevent="localLogin(credentials)">
      <h1 class="form_title">로그인</h1>
        <label for="email" class="input_label">이메일</label>
        <input
          type="email"
          id="email"
          placeholder="이메일을 입력하세요"
          class="input"
          name="email"
          v-model="credentials.email" 
        />
        <label for="password" class="input_label">비밀번호</label>
        <input
          type="password"
          id="password"
          placeholder="비밀번호를 입력하세요"
          class="input"
          name="password"
          v-model="credentials.password"
        />
        <div class="login_form_remember">
          <!-- <input
            type="checkbox"
            id="remember_me"
            v-model="rememberMe"
          /> -->
          <!-- <label for="remember_me">Remember me</label> -->
        </div>
      <button class="login_btn" style="width: 100%;">로그인</button>
      <div class="login_btn_bottom">
        <div class="forgot_password">
          <router-link to="/find">비밀번호 찾기</router-link>
        </div>
      <!-- 회원가입 페이지 router -->
        <div class="login_signup">
          <router-link to="/signup">회원가입</router-link>
        </div>
      </div>

    </form>
    <!-- 소셜 로그인 -->
      <div class="login_sns" v-show="none">
          <button class="btn_sns btn-google btn-block" @click="socialLoginGoogle" style="width: 100%">
          Google 계정으로 시작</button>
          <button class="btn_sns btn-naver btn-block" @click="socialLoginNaver" style="width: 100%"><i class="fab fab-naver-alt"></i> 
          네이버 계정으로 시작</button>
          <button  class="btn_sns btn-kakao btn-block" @click="socialLoginKakao" style="width: 100%">
          KaKao 계정으로 시작</button>
      </div>
  </div>
</div>

</template>

<script>
import { mapActions } from 'vuex';

export default {
  
  data() {
    return {
      socialType: "",
      credentials: {
        email: '',
        password: '',
      },
    }
  },

  methods: {
    ...mapActions(["localLogin"]),

    socialLoginGoogle() {
      this.socialType = 'Google'
      
    },
    socialLoginNaver() {
      this.socialType = 'Naver'
      
    },
    socialLoginKakao() {
      this.socialType = 'Kakao'
      
    }
  },
  
  
}

</script>

<style scoped>

/* 로그인 */
.login_form {
  width: 25%;
}
.login_card {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  margin: 15px 0;
  font-family: 'Inter', sans-serif;
}

.form_title {
  font-family: 'KimjungchulGothic-Bold';
  text-align: center;
  margin-top: 70px;
  margin-bottom: 70px;
  min-width: 280px;
}

.input_label {
  font-family: 'LINESeedKR-Bd';
  font-size: 18px;
  display: flex;
  padding: 15px 0px 5px 0px;
  min-width: 280px;
}

.input_label ~ input {
  font-family: 'LINESeedKR-Rg';
  width: 100%;
  height: 3rem;
  border-radius: 3px;
  border: 1px solid black;
  padding: .5rem;
  min-width: 280px;
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

/* .forgot_password {
  margin-left: auto; 
} */

.forgot_password > a {
  font-family: 'LINESeedKR-Rg';
  color: #FD7E14;
  text-decoration: none;
}

.login_btn {
  /* margin-top: 25px;
  font-size: 14px;
  border: none; 
  background-color: #FD7E14; 
  color: #FFFFFF;
  padding: 7.5px;
  cursor: pointer; 
  border-radius: 4px;  */

  height: 3rem;
  font-family: 'LINESeedKR-Rg';
  margin-top: 30px;
  margin-bottom: 5px;
  font-size: 20px;
  border: none; 
  background-color: #FD7E14; 
  color: #FFFFFF;
  padding: .5rem;
  cursor: pointer; 
  min-width: 280px;
  border-radius: 4px;
}

.login_btn_bottom {
  min-width: 280px;
  width: 100%;
  display: flex;
  justify-content: space-between;
  margin-bottom: 4rem;
}

span > a {
  color: #FD7E14;
}

.login_signup > a{
  /* margin: 40px 0px 60px 0px; */
  /* text-align: center; */
  font-family: 'LINESeedKR-Rg';
  text-decoration-line: none;
  color: #FD7E14;
}

/* 소셜 로그인 */
.login_sns {
  margin-top: 2rem;
  width: 25%;
  margin-top: 3rem;
  margin-bottom: 3rem;
}
.btn_sns {
    font-weight: normal;
    text-align: center;
    white-space: nowrap;
    vertical-align: middle;
    cursor: pointer;
    background-image: none;
    border: 1px solid transparent;
    margin-left: auto;
    margin-right: auto;
    /* padding: 6px 12px; */
    padding: .5rem .5rem;
    font-size: 14px;
    /* line-height: 1.42857143; */
    border-radius: 4px;
    -webkit-user-select: none;
    -moz-user-select: none;
    -ms-user-select: none;
    user-select: none;
    text-decoration: none;
    /* margin: 1rem 0; */
    margin-bottom: 1rem;
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
    /* font-family: Sans-Serif !important; */
    font-family: 'LINESeedKR-Rg';
    font-size: .9rem;
    height: 3rem;
    width: 90%;
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
    font-family: 'LINESeedKR-Rg';
    font-size: .9rem;
    height: 3rem;
    width: 90%;
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
    /* font-family: Sans-Serif !important; */
    width: 90%;
    height: 3rem;
    min-width: 280px;
    font-family: 'LINESeedKR-Rg';
    font-size: .9rem;
    background-image: url(../assets/Kakao.png);
}

</style>

