<template>
  <!-- 로컬 회원가입 -->
  <div class="signup_container">
    <div class="signup_card">
      <!-- 회원정보입력 -->
      <form class="signup_form" id="signup_form" @submit.prevent="localSignup(credentials)">
        <h1 class="form_title">Craete an account</h1>
          <label for="nickname" class="input_label">NickName</label>
          <input
            type="text"
            id="nickname"
            placeholder="Enter your NickName"
            class="input"
            name="name"
            style="width: 100%; height: 33.6px"
            v-model="credentials.nickname"
          />
          
          <label for="email" class="input_label" 
          :class="{ 'title-danger': emailHasError }">
          Email
          </label>

          <input
            type="email"
            id="email"
            placeholder="Enter your Email"
            class="input-item"
            :class="{ 'input-danger': emailHasError }"
            name="email"
            style="width: 100%; height: 33.6px" 
            v-model="credentials.email"
            readonly
          />
          <!-- 에러 메시지 표시 -->
          <p v-show="valid.email" class="input-error">
            이메일 주소가 올바르지 않습니다. 다시 확인해주세요!
          </p>


          <label for="password" class="input_label">Password</label>
          <input
            type="password"
            id="password"
            placeholder="Password"
            class="input-item"
            :class="{ 'input-danger':passwordHasError }"
            name="password"
            style="width: 100%; height: 33.6px" 
            v-model="credentials.password"
            readonly
          />
          <!-- 에러 메시지 표시 -->
          <p v-show="valid.passord" class="input-error">
            "숫자+영문자+특수문자 조합으로 8자리 이상 입력해주세요!"
          </p>
          <p
            v-show="valid.password"
            class="input-error">
             "8~16자리 숫자+영문자+특수문자 조합으로 입력해주세요!"
          </p>


          <VDatePicker 
          v-model="credentials.birth"
          :max="new Date()"
          name="birth"
          @dayclick="whatDate(credentials.birth)" />
          <p>생일 : {{credentials.birth}}</p>
          <div class="genderSelect">  
            <div class="editCategoryTitle">
              <p>성별 변경</p>
            </div>
            <div class="storageRadio">
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
      <button class="signup_btn" style="width: 100%;" @click="signup">Get started</button>
      <!-- 소셜 로그인 -->
      <div class="signup_sns">
          <a href="https://i9b202.p.ssafy.io/api/oauth/google-login?redirect_uri=https://i9b202.p.ssafy.io/social" class="btn_sns btn-google btn-block" @click="socialLoginGoogle" style="width: 100%">
          Google 계정으로 시작</a>
          <a href="https://i9b202.p.ssafy.io/api/oauth/naver-login?redirect_uri=https://i9b202.p.ssafy.io/social" class="btn_sns btn-naver btn-block" @click="socialLoginNaver" style="width: 100%"><i class="fab fab-naver-alt"></i> 
          네이버 계정으로 시작</a>
          <a href="https://i9b202.p.ssafy.io/api/oauth/kakao-login?redirect_uri=https://i9b202.p.ssafy.io/social" class="btn_sns btn-kakao btn-block" @click="socialLoginKakao" style="width: 100%">
          KaKao 계정으로 시작</a>
      </div>

      </form>
    </div>
  </div>
</template>

<script>
import { mapGetters, mapActions } from 'vuex';

export default {
    name: "SocialLogin",
    props: {
      socialType : {
        type: String,
        default : ""
      }
    },
    computed: {
      ...mapGetters(['member'])
    },
    mounted() {
      this.fetchMember(parseInt(sessionStorage.getItem('memberId')))
      .then(this.setUserInfo())
      
    },
    data() {
      return {
        // socialLoginType: this.$route.query.socialType,
        credentials: {
          email: 'a184562@gmail.com',
          password: '',
          nickname: '',
          gender: '',
          birth: '',
        },
        // 이메일, 패스워드 검증
        valid: {
          email: false,
          password: false,
        },
        emailHasError: false,
        passwordHasError: false,
      }
  },
  methods: {
    setUserInfo() {
      this.email = this.member.email
      this.birth = this.member.birth
      this.gender = this.member.gender
    },
    changeSocialGoogle() {
      this.socialLoginType = 'Google'
      this.$router.push({name: 'social', query: {socialType: this.socialLoginType}})
    },
    changeSocialNaver() {
      this.socialLoginType = 'Naver'
      this.$router.push({name: 'social', query: {socialType: this.socialLoginType}})
    },
    changeSocialKakao() {
      this.socialLoginType = 'Kakao'
      this.$router.push({name: 'social', query: {socialType: this.socialLoginType}})
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

    ...mapActions(["localSignup", "fetchMember"]),
    
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
    }
  }
}
</script>

<style scoped>
.signup_card {
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
  padding: 15px 0px 5px 0px;
}

.signup_btn {
  margin-top: 25px;
  font-size: 14px;
  border: none; 
  background-color: #FD7E14; 
  color: #FFFFFF;
  padding: 7.5px;
  cursor: pointer; 
  border-radius: 4px; 
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

/* 이메일 패스워드 에러 */
.input-error {  
  display: flex; 
  line-height: 16px;
  font-size: 11px;
  color: red;
  align-content: left;
}

.title-danger {
  color: red;
}

.input-danger {
  border-bottom: 1px solid red !important;
}
</style>
