<template>
  <!-- 로컬 회원가입 -->
  <div class="signup_container">
    <div class="signup_card">
      <!-- 회원정보입력 -->
      <form class="signup_form" id="signup_form" @submit.prevent="">
        <h1 class="form_title">회원가입</h1>
          <label for="nickname" class="input_label">닉네임</label>
          <input
            type="text"
            id="nickname"
            placeholder="닉네임을 입력해주세요"
            class="input"
            name="name"
            v-model="credentials.nickname" 
          />
          
          <label for="email" class="input_label" 
          :class="{ 'title-danger': emailHasError }">
          이메일
          </label>
          <div class="email_box">
          <input
            type="email"
            id="email"
            placeholder="이메일을 입력해주세요"
            class="input-item"
            :class="{ 'input-danger': emailHasError }"
            name="email" 
            v-model="credentials.email"
          />
          <button @click.prevent="emailCodeVerify" class="email_auth">
            인증
          </button>
        </div>
        <!-- 에러 메시지 표시 -->
          <p v-show="valid.email" class="input-error">
            이메일 주소가 올바르지 않습니다. 다시 확인해주세요!
          </p>
        <label class="input_label">이메일 인증</label>
        <div class="email_box">
          <input type="text" placeholder="인증번호를 입력해주세요" class="input-item" v-model="emailVerifyCode" />
          <button @click.prevent="checkEmailVerify" class="email_auth">
              확인
          </button>
        </div>
          
          <p v-show="emailVerify === 1" class="input-error">
            인증번호가 확인되었습니다.
          </p>
          <p v-show="emailVerify === 2" class="input-error">
            인증번호가 일치하지 않습니다.
          </p>
          <p v-if="countdown > 0" class="input-error">남은 시간: {{ formatCountdown() }}초</p>
          

          

          <label for="password" class="input_label">비밀번호</label>
          <input
            type="password"
            id="password"
            placeholder="비밀번호를 입력해주세요"
            class="input-item"
            :class="{ 'input-danger':passwordHasError }"
            name="password"
            v-model="credentials.password"
          />
          <p
            v-show="valid.password"
            class="input-error">
             8~16자리 숫자,영문자,특수문자 조합으로 입력해주세요
          </p>
          <label class="input_label">비밀번호 확인</label>
          <input
            type="password"
            id="passwordConfirm"
            placeholder="비밀번호를 확인해주세요"
            class="input-item"
            :class="{ 'input-danger':passwordConfirmHasError }"
            name="passwordConfirm"
            v-model="passwordConfirm" />
          <!-- 에러 메시지 표시 -->
          <p v-show="passwordConfirmHasError" class="input-error">
            비밀번호가 일치하지 않습니다.
          </p>
          

          <p>생일 선택</p>
          <VDatePicker 
          v-model="credentials.birth"
          :max-date="new Date()"
          name="birth"
          @dayclick="whatDate(credentials.birth)" />
          <div class="genderSelect">  
            <div class="editCategoryTitle">
              <p>성별 선택</p>
            </div>
            <div class="storageRadio btn_group">
              <label v-if="credentials.gender === 'none'" class="activeRadioButton">
                <input type="radio" name="noGender" value="none" v-model="credentials.gender" @click="changeClassification">선택 안함
              </label>
              <label v-else class="radioButton">
                <input type="radio" name="noGender" value="none" v-model="credentials.gender" @click="changeClassification">선택 안함
              </label>
              <label v-if="credentials.gender === 'male'" class="activeRadioButton">
                <input type="radio" name="male" value="male" v-model="credentials.gender" @click="changeClassification">남자
              </label>
              <label v-else class="radioButton">
                <input type="radio" name="male" value="male" v-model="credentials.gender" @click="changeClassification">남자
              </label>
              <label v-if="credentials.gender === 'female'" class="activeRadioButton">
                <input type="radio" name="female" value="female" v-model="credentials.gender" @click="changeClassification">여자
              </label>
              <label v-else class="radioButton">
                <input type="radio" name="female" value="female" v-model="credentials.gender" @click="changeClassification">여자
              </label>
            </div>
          </div>
      <button v-if="emailVerify === 1 && passwordConfirmHasError === false" class="signup_btn" style="width: 100%;" @click="localSignup(credentials)">회원가입</button>
      <button v-else class="signup_btn" id="disabled_btn" style="width: 100%;" @click="failSignup" disabled>회원가입</button>
      

      </form>
      <!-- 소셜 로그인 -->
      <!-- <div class="signup_sns">
          <a href="https://i9b202.p.ssafy.io/api/oauth/google-login?redirect_uri=http://i9B202.p.ssafy.io:8080/api/oauth/google" class="btn_sns btn-google btn-block" @click="socialLoginGoogle" style="width: 100%">
          Google 계정으로 시작</a>
          <a href="https://i9b202.p.ssafy.io/api/oauth/naver-login?redirect_uri=https://i9b202.p.ssafy.io/social" class="btn_sns btn-naver btn-block" @click="socialLoginNaver" style="width: 100%"><i class="fab fab-naver-alt"></i> 
          네이버 계정으로 시작</a>
          <a href="https://i9b202.p.ssafy.io/api/oauth/kakao-login?redirect_uri=https://i9b202.p.ssafy.io/social" class="btn_sns btn-kakao btn-block" @click="socialLoginKakao" style="width: 100%">
          KaKao 계정으로 시작</a>
      </div> -->
    </div>
  </div>
</template>

<script>

import { mapActions } from 'vuex';
// import axios from "axios"


export default {

  data() {
    return {
        socialType: '',
        credentials: {
        email: '',
        password: '',
        nickname: '',
        gender: 'none',
        birth: '',
        
      },
      isCounting: false,
      intervalId: null,
      remainingSeconds: 0,
      emailCode: '',
      emailVerifyCode: '',
      emailVerify: 0,
      // 이메일, 패스워드 검증
      valid: {
        email: false,
        password: false,
      },
      signupVerify: false,
      passwordConfirm: '',
      passwordConfirmHasError: false,
      emailHasError: false,
      passwordHasError: false,
      countdown: 0,
      countdownTimer: null,
      verificationCodeSent: false,
    }
  },
  
  watch: {
    'credentials.email': function() {
      this.checkEmail()
    },
    'credentials.password': function() {
      this.checkPassword()
    },
    'passwordConfirm': function() {
      this.checkPasswordConfirm()
    }
  },
  computed: {
    formattedTime() {
    if (this.remainingSeconds >= 0) {
      const minutes = Math.floor(this.remainingSeconds / 60);
      const seconds = this.remainingSeconds % 60;
      return `${this.formatDigits(minutes)}:${this.formatDigits(seconds)}`;
    } else {
      return '00:00';
    }
  }
  },

  // birthdate = new Date(); // birthdate 매개변수의 타입을 Date | null로 명시
  methods: {
    failSignup() {
      alert('회원가입에 실패하셨습니다.')
      this.$router.go(0)
    },
    emailCodeVerify() {
      this.EmailVerify(this.credentials.email)
      this.startCountdown()
      setTimeout(() => {
        const tempEmailCode = sessionStorage.getItem('emailVerify')
        const tempEmailCodeJson = JSON.parse(tempEmailCode)
        this.emailCode = tempEmailCodeJson
      }, 7500)
    },

    startCountdown() {
      this.countdown = 180; // 3분 (180초) 카운트 다운 시작
      this.countdownTimer = setInterval(() => {
        if (this.countdown > 0) {
          this.countdown--;
        } else {
          this.stopCountdown();
        }
      }, 1000);
    },

    stopCountdown() {
      clearInterval(this.countdownTimer);
      this.verificationCodeSent = false;
      this.countdown = 0;
    },

    formatCountdown() {
      const minutes = Math.floor(this.countdown / 60);
      const seconds = this.countdown % 60;
      return `${minutes}:${seconds < 10 ? '0' : ''}${seconds}`;
    },


    checkEmailVerify() {
      if(this.emailCode.value.code === this.emailVerifyCode) {
        this.emailVerify = 1
        this.countdown = 0
      }
      else {
        this.emailVerify = 2
      }
    },

    // 이메일 형식 검사
    checkEmail() {
      // 이메일 형식 검사
      const validateEmail = /^[A-Za-z0-9_\\.\\-]+@[A-Za-z0-9\\-]+\.[A-Za-z0-9\\-]+/

      if (!validateEmail.test(this.credentials.email) || !this.credentials.email) {
        this.valid.email = true
        this.emailHasError = true
        return
      } this.valid.email = false
        this.emailHasError = false
    },

     checkPassword() {
      // 비밀번호 형식 검사(영문, 숫자, 특수문자)
      const validatePassword = /^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{8,16}$/
      
      if (!validatePassword.test(this.credentials.password) || !this.credentials.password) {
        this.valid.password = true
        this.passwordHasError = true
        return
      } this.valid.password = false
        this.passwordHasError = false
     },

     checkPasswordConfirm() {
      if(this.credentials.password !== this.passwordConfirm) {
        this.passwordConfirmHasError = true
        return
      }
        this.passwordConfirmHasError = false
     },

    ...mapActions(["localSignup", "EmailVerify"]),
    
    whatDate(birthdate, delimiter = '-') {
    // if (!birthdate) return ''; // 날짜가 선택되지 않은 경우 빈 문자열 반환
      const year = birthdate.getFullYear();
      const month = birthdate.getMonth() + 1;
      const date = birthdate.getDate();

      if (month < 10) {
        if(date < 10) {
          this.birthdate = `${year}${delimiter}0${month}${delimiter}0${date}`;
        }
        else {
          this.birthdate = `${year}${delimiter}0${month}${delimiter}${date}`;
        }
        
      } else {
        if(date < 10) {
          this.birthdate = `${year}${delimiter}${month}${delimiter}0${date}`;
        }
        else {
          this.birthdate = `${year}${delimiter}${month}${delimiter}${date}`;
        }
      }
      this.credentials.birth = this.birthdate
    },

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
  beforeUnmount() {
    clearInterval(this.countdownTimer);
  },
}
</script>

<style scoped>

.signup_form {
  width: 30%;
  min-width: 280px;
}
.signup_form input {
  font-family: 'LINESeedKR-Rg';
}

.signup_form p {
  font-family: 'LINESeedKR-Bd';
  font-size: 19px;
  margin-top: 2rem;
}

.radioButton {
  font-family: 'LINESeedKR-Rg' !important;
  border: 1.6px solid #FD7E14;
  font-size: 1.1rem;
}
.activeRadioButton {
  background-color: #FD7E14;
  border: 1.6px solid #FD7E14;
  color: white;
  border-radius: .5rem;
  padding: .5rem;
  margin: .5rem;
  width: 6rem;
}

#disabled_btn {
  opacity: 60%;
  cursor: default;
}

.activeRadioButton input {
  border: 1px solid #FD7E14;
}

.signup_card {
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
}

.input_label {
  font-family: 'LINESeedKR-Bd';
  font-size: 18px;
  display: flex;
  padding: 15px 0px 5px 0px;
}

.email_box {
  display: flex;
}
.email_auth {
  font-family: 'LINESeedKR-Rg';
  width: 4rem;
  margin-left: .8rem;
  border-radius: 3px;
  border: none;
  background-color: #FD7E14;
  color: #FFFFFF;
}

.birthdate_container {
  text-align: left; 
  margin-top: 15px;
}

.birthdate_select_container {
  display: flex;
  padding: 5px 0px 0px 0px;
  gap: 2px;
}
.gender_container {
  text-align: left;
  
}
.gender {
  border: 1px solid #FD7E14;
  padding: 15px 0px 5px 0px;
}

.signup_btn {
  height: 3rem;
  font-family: 'LINESeedKR-Rg';
  margin-top: 50px;
  margin-bottom: 50px;
  font-size: 20px;
  border: none; 
  background-color: #FD7E14; 
  color: #FFFFFF;
  padding: .5rem;
  cursor: pointer; 
  border-radius: 4px; 
}

.storageRadio {
  font-family: 'LINESeedKR-Rg';
}

/* 소셜 로그인 */
.signup_sns {
  margin-top: 35px;
}

.btn_sns {
    font-weight: normal;
    text-align: center;
    white-space: nowrap;
    vertical-align: middle;
    cursor: pointer;
    background-image: none;
    border: 1px solid transparent;
    padding: 6px 16px;
    font-size: 14px;
    line-height: 1.42857143;
    border-radius: 4px;
    -webkit-user-select: none;
    -moz-user-select: none;
    -ms-user-select: none;
    user-select: none;
    text-decoration: none;
    margin: 10px 0px 10px 0px;
}

/* .btn-google {
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
} */

/* 이메일 패스워드 에러 */
.input-error {
  font-family: 'LINESeedKR-Rg' !important;
  display: flex; 
  line-height: 16px;
  font-size: 13px !important;
  margin-top: .5rem !important;
  color: red;
  align-content: left;
}

.title-danger {
  color: red;
}

.input-danger {
  border-bottom: 1px solid red !important;
}

.input, .input-item {
  /* style="width: 100%; height: 33.6px" */
  width: 100%;
  height: 3rem;
  border-radius: 3px;
  border: 1px solid black;
  padding: .5rem
}

.btn_group {
  display: flex;
  justify-content: center;
}
</style>
