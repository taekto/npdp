<template>
  <!-- 마이페이지 회원정보 수정 페이지 -->
  <div class="myPage">
    <!-- 좌측 마이페이지 메뉴 컴포넌트 -->
    <CategoryComponent />

    <!-- 우측 회원정보 컴포넌트 -->
    <div id="myPageView">
      <form class="signup_form" id="signup_form" @submit.prevent="">
          <label for="nickname" class="input_label">닉네임</label>
          <input
            type="text"
            id="nickname"
            placeholder="닉네임을 입력해주세요"
            class="input"
            name="name"
            v-model="nickname" 
          />
          <label for="password" class="input_label">비밀번호</label>
          <input
            type="password"
            id="password"
            placeholder="비밀번호를 입력해주세요"
            class="input-item"
            :class="{ 'input-danger':passwordHasError }"
            name="password"
            v-model="password"
          />
          <p
            v-show="valid"
            class="input-error">
             8~16자리 숫자,영문자,특수문자 조합으로 입력해주세요
          </p>
          
          <p>생일 선택</p>
          <VDatePicker 
          v-model="birth"
          :max-date="new Date()"
          name="birth"
          @dayclick="whatDate(birth)" />
          <p>성별 선택</p>
            <div class="storageRadio btn_group">
              <label v-if="gender === 'none'" class="activeRadioButton">
                <input type="radio" name="noGender" value="none" v-model="gender" @click="changeClassification">선택 안함
              </label>
              <label v-else class="radioButton">
                <input type="radio" name="noGender" value="none" v-model="gender" @click="changeClassification">선택 안함
              </label>
              <label v-if="gender === 'male'" class="activeRadioButton">
                <input type="radio" name="male" value="male" v-model="gender" @click="changeClassification">남자
              </label>
              <label v-else class="radioButton">
                <input type="radio" name="male" value="male" v-model="gender" @click="changeClassification">남자
              </label>
              <label v-if="gender === 'female'" class="activeRadioButton">
                <input type="radio" name="female" value="female" v-model="gender" @click="changeClassification">여자
              </label>
              <label v-else class="radioButton">
                <input type="radio" name="female" value="female" v-model="gender" @click="changeClassification">여자
              </label>
            </div>

      </form>
      
    </div>
  </div>
</template>

<script>
import CategoryComponent from "./categoryComponent.vue";
import { mapActions, mapGetters } from "vuex";

export default {
  name: "UserInfoEditPage",
  components: {
    CategoryComponent,
  },
  // 임시 데이터
  data() {
    return {
      userData: {
        nickname: "",
        email: "",
        birthDate: "",
        password: "",
        gender: "",
        valid:true
      },
      memberId: null,
    };
  },
  computed: {
    ...mapGetters(["member"]),
  },

  methods: {
    ...mapActions(["fetchMember", "memberUpdate"]),

    setGender() {
      this.userData.gender = this.member.gender;
    },

    whatDate(birthDate, delimiter = "-") {
      const year = birthDate.getFullYear();
      const month = birthDate.getMonth() + 1;
      const date = birthDate.getDate();
      console.log(year + delimiter + month + delimiter + date);
      console.log(typeof month);
      if (month < 10) {
        this.birthDate = year + delimiter + "0" + month + delimiter + date;
      } else {
        this.birthDate = year + delimiter + month + delimiter + date;
      }
    },
  },
    watch: {
      'member': {
        handler(updatedMember) {
          // member 게터의 데이터 출력
          console.log('Updated member data');
          
          // 만약 updatedMember 안에 nickname 프로퍼티가 있다면 userData.nickname에 할당
          if (updatedMember && updatedMember.nickname) {
            this.userData.nickname = updatedMember.nickname;
          }
        },
        deep: true // 객체 내부의 프로퍼티까지 감시
      }
    },
    async created() {
      this.memberId = parseInt(sessionStorage.getItem('memberId'))
      this.fetchMember(this.memberId)
      this.setGender()
    },

}
</script>

<style scoped>
.editTitle {
  font-family: "LINESeedKR-Bd";
  margin: auto;
  margin-top: 2rem;
  font-size: 2rem;
  font-weight: bold;
  /* text-align: start; */
  width: 75%;
}

.storageRadio {
  display: flex;
}

.editCategoryTitle {
  margin: auto;
  margin-top: 2rem;
  margin-left: 0.75rem;
  font-size: 1.2rem;
  font-weight: bold;
  text-align: start;
}

.editComponent {
  width: 80%;
  margin: auto;
  /* border: solid grey; */
  border-radius: .5rem;
  /* margin-left: 2rem;
  margin-right: 2rem;
  margin-bottom: 5rem; */
}


.searchWindow {
  width: 75%;
  margin: auto;
}

.birthAndgender {
  display: flex;
  justify-content: space-around;
  margin: auto;
  margin-bottom: 3rem;
  width: 75%;
}

.birthInput {
  justify-content: center;
  border-radius: 0.5rem;
  width: 100%;
  height: 2.5rem;
  text-align: center;
  margin-top: 1rem;
}


.birthDate{
  width: 75%;
  /* display: flex; */
  margin: auto;
  font-family: 'LINESeedKR-Bd';
}

.genderSelect {
  margin: auto;
  /* // margin-left: 3rem; */
}


.btn_update {
  border-radius: 0.5rem;
  font-family: "LINESeedKR-Rg";
  border: 1.6px solid #fd7e14;
  background-color: #fd7e14;
  color: white;
  border-radius: 0.5rem;
  padding: 0.3rem 0.2rem;
  margin-right: 1rem;
  width: 4rem;
  font-size: 1.25rem;
}

.edit_form {
  display: flex;
}

.editBirthTitle {
  margin: auto;
  margin-top: 2rem;
  margin-bottom: 1rem;
  font-size: 1.5rem;
  font-weight: bold;
}

.saveButton {
  font-family: "LINESeedKR-Rg";
  border: 1.6px solid #fd7e14;
  background-color: #fd7e14;
  color: white;
  border-radius: 0.5rem;
  padding: 0.3rem 0.2rem;
  margin: 0.5rem;
  width: 6rem;
  font-size: 1.25rem;
  margin-bottom: 3rem;
  /* display: flex;
  align-items: center;
  justify-content: center; */
}


.signup_form {
  width: 50%;
  min-width: 280px;
  margin: auto;
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
  border: 1.6px solid #FD7E14;
}
.activeRadioButton {
  background-color: #FD7E14;
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
