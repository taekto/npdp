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
              <li class="ingredientList row" v-for="(seasoning, index) in seasoningList" :key="index">
                <p class="col-2">{{seasoning.name}}</p>
                <p class="col-4">보관시작일 : {{seasoning.startDate}}</p>
                <p class="col-3">
                  유통기한 : {{seasoning.endDate}}
                </p>
                <p class="col-2">보관방식 : {{seasoning.storage}}</p>
                <button class="col-1" @click="deleteSeasoning(seasoning)">제거</button>
              </li>
            </ul>
          </div>

          <!-- 양념 이름 검색 -->
          <div class="modal-body inputComponent">
            <div>
              <form @submit.prevent="goToSearchwithKeyword">
                <div class="input-group">
                    <input id="searchForm ingredientText" class="form-control" type="text" v-model.trim="ingredientName">
                    <input id="submitButton" type="submit" value="검색">
                </div>
              </form>
            </div>

            <!-- 보관 방법 라디오 버튼 -->
            <div>
              <div>
                <label class="radioButton">
                  <input type="radio" name="classification" value="냉장" v-model="selectStorage" @click="changeStorage">냉장
                </label>
                <label class="radioButton">
                  <input type="radio" name="classification" value="냉동" v-model="selectStorage" @click="changeStorage">냉동
                </label>
                <label class="radioButton">
                  <input type="radio" name="classification" value="실온" v-model="selectStorage" @click="changeStorage">실온
                </label>
              </div>
              <button class="soundButton" data-bs-target="#seasoningModalToggle2" data-bs-toggle="modal">음성입력</button>
              <button class="soundButton" @click="appendList">추가하기</button>
            </div>
          </div>

          <!-- 현재 입력된 리스트 저장 -->
          <div class="modal-footer">
            <button class="soundButton" @click="pushSeasoningData">저장하기</button>
          </div>
        </div>
      </div>
    </div>
</template>

<script>
export default {
    name: 'SeasoningText',
    data() {
      return {
        seasoningList : [{name : "고춧가루", startDate : '2023-07-27', endDate: '', storage: '냉장'}, 
        {name : "간장",  startDate : '2023-07-27', endDate: '', storage: '냉장'},
        {name : "고추장",  startDate : '2023-07-27', endDate: '', storage: '냉장'},
        {name : "된장",  startDate : '2023-07-27', endDate: '', storage: '냉장'},],
        seasoningName: '',
        selectStorage : '냉장',
      }
  },
  methods: {
    appendList() {
      let today = new Date();   

      let year = today.getFullYear(); // 년도
      let month = today.getMonth() + 1;  // 월
      let date = today.getDate();  // 날짜

      this.seasoningList.push({name: this.seasoningName, startDate : `${year}-${month}-${date}`, endDate: '', storage: this.selectStorage})

      this.seasoningName = ''
      this.selectStorage = '냉장'
    },
    pushSeasoningData() {
      console.log(this.seasoningList)
      this.seasoningList = []
    },
    deleteSeasoning(seasoning) {
            const arrayRemove = (arr, value) => {
                return arr.filter((ele) => {
                    return ele != value
                })
            }
            this.seasoningList = arrayRemove(this.seasoningList, seasoning)
        }

}
}
</script>

<style scoped>
/* 모달 버튼 */
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
  width: 2rem;
  height: 60%;
  border-radius: .5rem;
  background-color: #f2f2f2;
  border: solid rgb(244, 244, 244);
}

/* 양념 리스트 */
.ListShow {
  border-bottom: solid #a7a7a7;
  width: 95%;
  margin: auto;
}

.ingredientList {
  display: flex;
  border-bottom: solid grey;
  
}

.modal-body {
  display: flex;
  margin: auto;
  width: 100%;
}


.amount {
  display: flex;
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

#searchForm {
    border-radius: .5rem;
    margin-right: 1rem;
    margin-left: 1rem;
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
</style>