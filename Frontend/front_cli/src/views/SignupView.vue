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
          <label for="email" class="input_label">Email</label>
          <input
            type="email"
            id="email"
            placeholder="Enter your Email"
            class="input"
            name="email"
            style="width: 100%; height: 33.6px" 
            v-model="credentials.email"
          />
          <label for="password" class="input_label">Password</label>
          <input
            type="password"
            id="password"
            placeholder="Password"
            class="input"
            name="password"
            style="width: 100%; height: 33.6px" 
            v-model="credentials.password"
          />
          
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
              <label class="radioButton">
                <input type="radio" name="male" value="남자" v-model="credentials.gender" @click="changeClassification">남자
              </label>
              <label class="radioButton">
                <input type="radio" name="female" value="여자" v-model="credentials.gender" @click="changeClassification">여자
              </label>
              <label class="radioButton">
                <input type="radio" name="noGender" value="미선택" v-model="credentials.gender" @click="changeClassification">선택 안함
              </label>            
            </div>
            <p>{{credentials.gender}}</p>
          </div>
          <p>성별 : {{credentials.gender}}</p>
          <p>1234567987564165</p>
          <p>1234567987564165</p>
          <p>1234567987564165</p>
          <p>1234567987564165</p>
          <p>1234567987564165</p>
          <p>1234567987564165</p>
      <button class="signup_btn" style="width: 100%;" @click="signup">Get started</button>

      <!-- 소셜 로그인 -->
      <div class="signup_sns">
          <a href="http://localhost:8080/signup" class="btn_sns btn-google btn-block">
          Google 계정으로 시작</a>
          <a href="http://localhost:8080/signup" class="btn_sns btn-naver btn-block"><i class="fab fab-naver-alt"></i> 
          네이버 계정으로 시작</a>
          <a href="http://localhost:8080/signup" class="btn_sns btn-kakao btn-block">
          KaKao 계정으로 시작</a>
      </div>

      </form>
    </div>
  </div>
</template>

<script>

import { mapActions } from 'vuex';


export default {

  data() {
    return {
       credentials: {
        email: '',
        password: '',
        nickname: '',
        gender: '',
        birth: '',
      },
    }
  },

  // birthdate = new Date(); // birthdate 매개변수의 타입을 Date | null로 명시
  methods: {
    ...mapActions(["localSignup"]),
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


</style>
