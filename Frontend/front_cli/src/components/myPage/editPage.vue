<template>
<<<<<<< HEAD
  <div>
    <div>
        <router-link to="/mypage/refrigerator">내 냉장고</router-link>
        <router-link to="/mypage/tool">내 조리도구</router-link>
        <router-link to="/mypage/like">내가 좋아요한 레시피</router-link>
        <router-link to="/mypage/edit">내 정보 수정</router-link>
    </div>
    <h1>수정</h1>
=======
  <!-- 마이페이지 회원정보 수정 페이지 -->
  <div class="myPage">
    <!-- 좌측 마이페이지 메뉴 컴포넌트 -->
    <CategoryComponent />

    <!-- 우측 회원정보 컴포넌트 -->
    <div id="myPageView">
      <div class="editComponent">
        <div class="editTitle">
          <p>회원정보수정</p>
        </div>
        <div>
          <!-- 닉네임 변경 -->
          <div class="searchWindow">
            <div class="editCategoryTitle">
              <p>닉네임 변경</p>
            </div>
            <div>
              <form @submit.prevent="confirmPassword">
                <div class="input-group">
                  <input id="searchForm" class="form-control" type="text" v-model.trim="nickName">
                </div>
              </form>
            </div>
          </div>

          <!-- 이메일 변경 -->
          <div class="searchWindow">
            <div class="editCategoryTitle">
              <p>이메일 변경</p>
            </div>
            <div>
              <form @submit.prevent="confirmPassword">
                <div class="input-group">
                  <input id="searchForm" class="form-control" type="email" v-model.trim="email">
                </div>
              </form>
            </div>
          </div>

          <!-- 비밀번호 변경 -->
          <div class="searchWindow">
            <div class="editCategoryTitle">
              <p>비밀번호 변경</p>
            </div>
            <div>
              <form @submit.prevent="confirmPassword">
                <div class="input-group">
                    <input id="searchForm" class="form-control" type="password" v-model.trim="password">
                </div>
              </form>
            </div>
          </div>

          <!-- 생일 & 성별 변경 -->
          <div class="birthAndgender">

            <!-- 생일 변경 -->
            <div class="birthDate">
              <div class="editCategoryTitle">
                <p>생일 변경</p>
              </div>
              <VDatePicker v-model="birthDate" />
              <div>
                <input id="searchForm" class="birthInput" type="date" v-model.trim="birthDate">
              </div>
            </div>

            <!-- 성별 변경 -->
            <div class="genderSelect">
              <div class="editCategoryTitle">
                <p>성별 변경</p>
              </div>
              <div class="storageRadio">
                <label class="radioButton">
                  <input type="radio" name="male" value="남자" v-model="gender" @click="changeClassification">남자
                </label>
                <label class="radioButton">
                  <input type="radio" name="female" value="여자" v-model="gender" @click="changeClassification">여자
                </label>
                <label class="radioButton">
                  <input type="radio" name="noGender" value="미선택" v-model="gender" @click="changeClassification">선택 안함
                </label>            
              </div>
            </div>
          </div>
          <button class="saveButton" @click="changeUserData">저장</button>
        </div>
      </div>
      </div>
      
>>>>>>> 7ce6a7470900fd4b2fcfa8cef66603bb7489b144
  </div>
</template>

<script>
<<<<<<< HEAD
export default {
    name: "EditPage",
}
</script>

<style>
=======
import CategoryComponent from './categoryComponent.vue'

export default {
    name: "EditPage",
    components: {
        CategoryComponent,
    },
    // 임시 데이터
    data() {
      return {
        nickName : "임시 닉네임",
        email : "temp123@ssafy.com",
        password : "",
        birthDate : "1996-07-11",
        gender : "남자",
        user_data : {nickname : this.nickName, email : this.email, password : this.password, birthDate : this.birthDate, gender : this.gender}
      }
    },
    methods: {
      changeUserData() {
        this.user_data = {nickname : this.nickName, email : this.email, password : this.password, birthDate : this.birthDate, gender : this.gender}
        console.log(this.user_data)
        alert("저장되었습니다.")
      }
    }
}
</script>

<style scoped>
.editTitle {
    margin: auto;
    margin-top: 2rem;
    font-size: 2rem;
    font-weight: bold;
    text-align: start;
    width: 75%;
}

.editCategoryTitle {
    margin: auto;
    margin-top: 2rem;
    font-size: 1.2rem;
    font-weight: bold;
    text-align: start;
}

.editComponent {
    width: 80%;
    margin: auto;
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
  border-radius: .5rem;
  width: 100%;
  height: 2.5rem;
  text-align: center;
  margin-top: 1rem;
}

.birthDate{
  margin: auto;
}

.genderSelect {
  margin: auto;
}


#myPageView {
  border: solid grey;
  border-radius: .5rem;
  margin-left: 2rem;
  margin-right: 2rem;
  margin-bottom: 5rem;
}


>>>>>>> 7ce6a7470900fd4b2fcfa8cef66603bb7489b144

</style>