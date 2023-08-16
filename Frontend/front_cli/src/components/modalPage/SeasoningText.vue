<template>
  <!-- 양념 텍스트 입력 모달 -->
  <div class="modal fade modal-xl" id="seasoningModalToggle" aria-hidden="true" aria-labelledby="seasoningModalToggleLabel" tabindex="-1">
      <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
          <div class="modal-header">
            <h1 class="modal-title fs-5" id="exampleModalToggleLabel">양념 입력</h1>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>

          <!-- 현재 입력된 양념 리스트 -->
          <div class="modal-body">
            <ul class="ListShow">
              <li class="ingredientList" v-for="(seasoning, index) in seasoningList" :key="index">
                <p class="ingredientName">{{seasoning.seasoningName}}</p>
                <div class="startDate">
                  <p>보관시작일 : </p>
                  <p>{{seasoning.startDate}}</p>
                </div>
                <!-- <div class="endDate">
                  <p>유통기한 :</p>
                  <p>{{seasoning.expiredDate}}</p>
                </div> -->
                
                <p class="storage">보관방식 : {{seasoning.storage}}</p>
                <button class="deleteButton" @click="deleteSeasoning(seasoning)">제거</button>
              </li>
            </ul>
          </div>

          <!-- 양념 이름 검색 -->
          <div class="modal-body inputComponent">
            <div>
              <form @submit.prevent="specificSearch({ type: 'seasoning', name: this.seasoningName })">
                <div class="input-group">
                    <input id="searchForm ingredientText" class="form-control" type="text" v-model.trim="seasoningName">
                    <input id="submitButton" type="submit" value="검색">
                </div>
              </form>
 <div v-if="seasoningSearchData.length > 0" class="search_results_container">
      <ul>
        <li v-for="result in seasoningSearchData" :key="result.id" @click="selectedItem(result)">
          {{ result.name }}
        </li>
      </ul>
    </div>
    <div v-else-if="isLoading">
      검색 중...
    </div>
    <div v-else>
      {{ name }} 데이터가 없습니다.
    </div>
            </div>

            <!-- 보관 방법 라디오 버튼 -->
            <div>
              <div>
                  <p v-if="seasoningName">선택한 양념: {{ seasoningName }}</p>
                  <div class="btn_group">
                    <label v-if="storage !== 0" class="radioButton">
                      <input type="radio" name="classification" value="냉장" @click="selectStorage(0)">냉장
                    </label>
                    <label v-else class="radioButton2">
                      <input type="radio" name="classification" value="냉장" @click="selectStorage(0)">냉장
                    </label>
                    <label v-if="storage !== 1" class="radioButton">
                      <input type="radio" name="classification" value="냉동" @click="selectStorage(1)">냉동
                    </label>
                    <label v-else class="radioButton2">
                      <input type="radio" name="classification" value="냉동" @click="selectStorage(1)">냉동
                    </label>
                    <label v-if="storage !== 2" class="radioButton">
                      <input type="radio" name="classification" value="실온" @click="selectStorage(2)">실온
                    </label>
                    <label v-else class="radioButton2">
                      <input type="radio" name="classification" value="실온" @click="selectStorage(2)">실온
                    </label>
                  </div>
              </div>
              <button v-show="none" class="soundButton" data-bs-target="#seasoningModalToggle2" data-bs-toggle="modal">음성입력</button>
              <button class="soundButton" @click="appendList">추가하기</button>
            </div>
          </div>

          <!-- 현재 입력된 리스트 저장 -->
          <div class="modal-footer">
            <button class="soundButton" @click="saveMaterial({ type: 'seasoning', memberId: memberId, sendData: throwList })">저장하기</button>
          </div>
        </div>
      </div>
    </div>
</template>

<script>
import { mapActions, mapGetters } from 'vuex';
export default {
    name: 'SeasoningText',
    data() {
      return {
        seasoningList: [],  
        throwList:[],
        seasoningId: null,
        storage: 0,
        startDate: '',
        expiredDate: '2023-08-08',
        seasoningName: '',
        memberId: null,
        selected:null,
        isLoading: false,
        printStorage : '',
      }
  },
  computed: {
    ...mapGetters(['seasoningSearchData'])
    
  },
  methods: {
    ...mapActions(['specificSearch','saveMaterial']),
    selectedItem(result) {
      console.log(result)
      this.seasoningName = result.name;
      this.seasoningId = result.id;
    },

    selectStorage(storage) {
      this.storage = storage
        switch (storage) {
          case 0:
          this.printStorage = "냉장";
            break;
          case 1:
            this.printStorage = "냉동";
            break;
          case 2:
          this.printStorage = "실온";
          break;
        }
    },

    appendList() {
      let today = new Date();   

      let year = today.getFullYear(); // 년도
      let month = today.getMonth() + 1;  // 월
      let date = today.getDate();  // 날짜
    
      let todayDate

      if(month < 10) {
        if(date < 10) {
          todayDate = `${year}-0${month}-0${date}`
        }
        else {
          todayDate = `${year}-0${month}-${date}`
        }
      }
      else {
        if(date < 10) {
          todayDate = `${year}-${month}-0${date}`
        }
        else {
          todayDate = `${year}-${month}-${date}`
        }
      }

      this.seasoningList.push({seasoningName:this.seasoningName, seasoningId: this.seasoningId, startDate : todayDate, expiredDate: this.expiredDate, storage: this.storage})
      this.throwList.push({seasoningId: this.seasoningId, storage: this.storage})
      console.log(this.throwList)
    },

    pushSeasoningData() {
      console.log(this.seasoningList)
      this.seasoningList = []
    },

    selectSeasoningStorage(storage) {
      this.storage = storage
    },
    
    deleteSeasoning(seasoning) {
      const arrayRemove = (arr, value) => {
        return arr.filter((ele) => {
            // 객체 또는 배열 비교를 위해 JSON.stringify 사용
          return JSON.stringify(ele) !== JSON.stringify(value);
        });
      };
      this.seasoningList = arrayRemove(this.seasoningList, seasoning);
      this.throwList = this.throwList.filter((ele) => {
        return ele.seasoningId !== seasoning.seasoningId;
      });
      },
    },

  created() {
    // 컴포넌트가 생성될 때 sessionStorage에서 memberId 값을 가져옵니다.
      this.memberId = sessionStorage.getItem('memberId');
  },
}
</script>

<style scoped>
/* 모달 창 */
.modalButton {
  border: solid #FD7E14;
  color: white;
  background-color: #FD7E14;
  border-radius: .5rem;
  margin: 1rem;
  padding: 0.5rem;
  font-size: 1.25rem;
  font-weight: bold;
}

.amountButton {
  /* width: 2rem; */
  padding-left: 1rem;
  padding-right: 1rem;
  justify-content: center;
  /* padding: 5rem; */
  /* height: 60%; */
  border-radius: .5rem;
  background-color: #f2f2f2;
  border: solid rgb(244, 244, 244);
}

.ListShow {
  border-bottom: solid #a7a7a7;
  width: 90%;
  margin: auto;
}

.ingredientList {
  /* display: flex;
  border-bottom: solid grey; */
  display: flex;
    border-bottom: solid grey;
    align-items: center;
    justify-content: space-between;
    width: 100%;
    /* padding: .5rem; */
    margin: auto;
    height: 10vh;
  
}

.ingredientName {
    font-weight: bold;
    width: 7.5%;
    margin-top: auto;
    margin-bottom: auto;
}

.modal-body {
  display: flex;
  margin: auto;
  width: 100%;
}


.amount {
  display: flex;
  border-radius: .5rem;
  /* margin: auto; */
  margin-left: 1rem;
  margin-right: 1rem;
  justify-content: center;
  align-items: center;
  width: 20%;
}

.amountAndUnit {
    margin-top: auto;
    margin-bottom: auto;
    margin-left: .5rem;
    margin-right: .5rem;
}

.amountButton {
    width: 2rem;
    height: 60%;
    border-radius: .5rem;
    background-color: #f2f2f2;
    border: solid rgb(244, 244, 244);
    display: flex;
    justify-content: center;
    align-items: center;
}

.startDate {
    margin-top: auto;
    margin-bottom: auto;
    margin-left: .5rem;
    margin-right: .5rem;
    flex: none;
    width: 10rem;
}

.startDate p {
    margin-top: 0;
    margin-bottom: 0;
}

.endDate {
    margin-top: auto;
    margin-bottom: auto;
    margin-left: .5rem;
    margin-right: .5rem;
    flex: none;
    width: 10rem;
}

.endDate p {
    margin-top: 0;
    margin-bottom: 0;
}

.storage {
    margin-top: auto;
    margin-bottom: auto;
    margin-left: .5rem;
    margin-right: .5rem;
}

.deleteButton {
    border-radius: .5rem;
    margin-left: .5rem;
    height: 2rem;
    width: 5rem;
    background-color: #FD7E14;
    color: white;
    border: none;
}

.inputComponent {
  justify-content: space-around;
}

.radioButton {
    border: solid #FD7E14;
    border-radius: .5rem;
    padding: .5rem;
    margin: .5rem;
    width: 6rem;
}

[type="radio"] {
    appearance: none;
}

.btn_group {
  display: flex;
}

#searchForm {
    border-radius: .5rem;
    margin-right: 1rem;
    margin-left: 1rem;
    width: 10rem;
}

#ingredientText {
  width: 10rem;
}

#submitButton {
    width: 5rem;
    border-radius: .5rem;
    background-color: #f2f2f2;
    border: solid rgb(244, 244, 244);
}

.input-group {
  display: flex;
  justify-content: space-around;
  /* margin: auto; */
}

.servingButton {
  border: solid #FD7E14;
  background-color: white;
  border-radius: .5rem;
  width: 4rem;
  padding: 0.25rem;
  font-size: 1rem;
  font-weight: bold;
  text-align: center;
}

.soundButton {
  border: solid #FD7E14;
  color: white;
  background-color: #FD7E14;
  border-radius: .5rem;
  margin: 1rem;
  padding: 0.5rem;
  font-size: 1.25rem;
  font-weight: bold;
}

/* 검색 결과 스타일 */
.search_results_container{
  text-align: left;
  max-width: 100%;
  max-height: 150px;
  overflow-y: auto; 
}

.search_results_container ul{
  margin-top: 0.5rem;
  margin-bottom: 0.2rem;
}

</style>