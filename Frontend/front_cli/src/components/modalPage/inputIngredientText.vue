<template>
  <!-- 재료 텍스트 입력 모달 -->
  <div class="modal fade modal-xl" id="exampleModalToggle" aria-hidden="true" aria-labelledby="exampleModalToggleLabel" tabindex="-1">
      <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
          <div class="modal-header">
            <h1 class="modal-title fs-5 modal_title" id="exampleModalToggleLabel">재료 등록</h1>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>

          <!-- 입력된 재료 리스트 -->
          <div class="modal-body">
            <ul class="ListShow">
                <div v-if="ingredientList.length !== 0">
                <li class="ingredientList" v-for="(ingredient, index) in ingredientList" :key="index">
                  <div class="ingredientName">{{ingredient.ingredientName}}</div>
                  <div class="amount">
                    <button class="amountButton2" @click="plusAmount(ingredient)">+</button>
                    <p class="amountAndUnit">{{ingredient.amount}}{{ingredient.unit}}</p>
                    
                    <button class="amountButton" @click="minusAmount(ingredient)">-</button>
                  </div>
                  <!-- <div class="startDate"> -->
                    <!-- <p>보관시작일 : {{ingredient.startDate}}</p> -->
                  <!-- </div> -->
                  <!-- <div class="endDate">
                    <p>유통기한 : </p>
                    <p>{{ingredient.expiredDate}}</p>
                  </div> -->
                  <!-- <p class="storage">보관방식 : {{changeStorage(ingredient.storage)}}</p> -->
                  <button class="deleteButton" @click="deleteIngredient(ingredient)">제거</button>
                </li>
              </div>
              <div v-else>
              <div class="no_item">입력된 재료가 없습니다</div>
            </div>
              </ul>
          </div>

          <!-- 재료 이름 검색 폼 -->
          <div class="modal-body inputComponent">
            <div>
              <p class="inputTitle">재료 검색</p>
              <form @submit.prevent="specificSearch({ type: 'ingredient', name: this.ingredientName })">
                <div class="input-group">
                    <input id="searchForm ingredientText" class="form-control" type="text" v-model.trim="ingredientName">
                    <input id="submitButton" type="submit" value="검색">
                </div>
              </form>
            <div class="search_results_container">
              <ul>
                <li v-for="result in ingredientSearchData" :key="result.id" class="search_container" @click="selectedItem(result)">
                  <div v-if="ingredientId === result.id" style="font-weight: bold;">{{ result.name }}</div>
                  <div v-else>{{ result.name }}</div>
                </li>
              </ul>
            </div>
          </div>


            <!-- 재료 입력 폼 -->
            <div>
              <div>
                <form @submit.prevent="addIngredient">
                  <p class="inputTitle">재료 정보</p>
                  <p class="explainText">개수</p>
                  <div class="input-group">
                    <div class="amount">
                      <button class="amountButton2" @click="reduceAmount">-</button>
                      <input id="searchForm" class="form-control" type="number" v-model.trim="amount">
                      <button class="amountButton" @click="addAmount">+</button>
                    </div>
                    <div class="dropdown">
                      <button class="dropdown-toggle servingButton" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                        {{unit}}
                      </button>
                      <ul class="dropdown-menu">
                        <p class="inputServing">선택</p>
                        <li><button class="dropdown-item" type="button" @click="chageUnit('개')">개</button></li>
                        <li><button class="dropdown-item" type="button" @click="chageUnit('g')">g</button></li>
                        <li><button class="dropdown-item" type="button" @click="chageUnit('마리')">마리</button></li>
                        <li class="inputServing">직접기입 : <input class="dropdown-item" type="text" v-model="unit"></li>
                      </ul>
                    </div>
                  </div>
                </form>
              </div>
              <div class="explainText">보관방법</div>
              <div class="btn_group">
                <label v-if="storage !== 0" class="radioButton_1">
                  <input type="radio" name="classification"  @click="selectStorage(0)">냉장
                </label>
                <label v-else class="radioButton_2">
                  <input type="radio" name="classification"  @click="selectStorage(0)">냉장
                </label>
                <label v-if="storage !== 1" class="radioButton_1">
                  <input type="radio" name="classification"  @click="selectStorage(1)">냉동
                </label>
                <label v-else class="radioButton_2">
                  <input type="radio" name="classification"  @click="selectStorage(1)">냉동
                </label>
                <label v-if="storage !== 2" class="radioButton_1">
                  <input type="radio" name="classification"  @click="selectStorage(2)">실온
                </label>
                <label v-else class="radioButton_2">
                  <input type="radio" name="classification"  @click="selectStorage(2)">실온
                </label>
              </div>
              <button v-show="none" class="addButton" data-bs-target="#exampleModalToggle2" data-bs-toggle="modal">음성입력</button>
              <button class="addButton" @click="appendList">추가</button>
            


            </div>
          </div>

          <!-- 재료 리스트 저장 -->
          <div class="modal-footer">
            <button class="addButton" data-bs-dismiss="modal" @click="saveMaterial({type: 'ingredient', memberId: this.memberId, sendData: throwList })">저장</button>
          </div>
        </div>
      </div>
    </div>
</template>

<script>
import { mapActions, mapGetters } from 'vuex';

export default {
    name: 'IngredientText',
    data() {
      return {
        ingredientList: [],
        throwList:[],
        ingredientId: null,
        storage: 0,
        printStorage:'',
        amount: '',
        unit: '개',
        startDate: '',
        expiredDate: '',
        ingredientName: '',
        memberId: null,
      }
  },
  computed: {
    ...mapGetters(['ingredientSearchData'])
    
  },
  watch: {
    amount(newValue) {
      if(newValue < 0) {
        alert('0보다 작은 값을 입력할 수 없습니다.')
        this.amount = 0
      }
    }
  },
  methods: {
    ...mapActions(['specificSearch','saveMaterial']),


    selectedItem(result) {
      this.ingredientName = result.name;
      this.ingredientId = result.id;
    },

    changeStorage(value) {
      switch (value) {
          case 0:
            return "냉장";
          case 1:
            return "냉동";
          case 2:
            return "실온";
        }
    },

    selectStorage(number) {
      this.storage = number
        switch (number) {
          case 0:
          this.printStorage = "냉장";
            break
          case 1:
            this.printStorage = "냉동";
            break
          case 2:
          this.printStorage = "실온";
          break
        }
    },

    chageUnit(unit) {
      this.unit = unit
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
      this.ingredientList.push({ingredientName:this.ingredientName, ingredientId: this.ingredientId, amount: this.amount.toString(), unit: this.unit, startDate : todayDate, expiredDate: this.expiredDate, storage: this.storage})
      this.throwList.push({ingredientId: this.ingredientId, amount: this.amount, unit: this.unit, startDate : today, expiredDate: this.expiredDate, storage: this.storage})
      
    },
    pushIngredientData() {
      this.ingredientList = []
    },
    addAmount() {
      if(this.unit === 'g') {
        this.amount += 10
      }
      else {
        this.amount ++
      }
    },
    reduceAmount() {
      if(this.amount > 0) {
        if(this.unit === 'g') {
          this.amount -= 10
        }
        else {
          this.amount --
        }
      }
      

      
    },
    plusAmount(ingredient) {
      ingredient.amount ++
    },
    minusAmount(ingredient) {
      ingredient.amount --
      if (parseFloat(ingredient.amount) <= 0) {
        const arrayRemove = (arr, value) => {
          return arr.filter((ele) => {
              return ele != value
          })
        }
        this.ingredientList = arrayRemove(this.ingredientList, ingredient)
      }
    },
    deleteIngredient(ingredient) {
      const arrayRemove = (arr, value) => {
        return arr.filter((ele) => {
            // 객체 또는 배열 비교를 위해 JSON.stringify 사용
          return JSON.stringify(ele) !== JSON.stringify(value);
        });
      };

        // ingredientList에서 ingredient 제거
      this.ingredientList = arrayRemove(this.ingredientList, ingredient);

      // throwList에서 ingredientId가 일치하는 객체 제거
      this.throwList = this.throwList.filter((ele) => {
        return ele.ingredientId !== ingredient.ingredientId;
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
.no_item {
  font-family: 'LINESeedKR-Rg';
  color: #aeaeae;
  font-size: 1.5rem;
  text-align: center;
  vertical-align: center;
  align-items: center;
  margin-top: 1.5rem;
  margin-bottom: 1.5rem;
}
.modal_title {
  font-family: 'LINESeedKR-Bd';
}
.radioButton_1 {
    font-family: 'LINESeedKR-Rg';
    font-size: 1.2rem;
    margin-right: 1rem;
    cursor: pointer;
    border: .1rem solid #FD7E14;
    border-radius: .5rem;
    padding: .3rem;
  }
  .radioButton_2 {
    font-family: 'LINESeedKR-Rg';
    font-size: 1.2rem;
    color: #fff;
    background-color: #FD7E14;
    margin-right: 1rem;
    cursor: pointer;
    border: .1rem solid #FD7E14;
    border-radius: .5rem;
    padding: .3rem;
}
.radioButton_1:hover {
    color: #FD7E14;
}
.ListShow {
  border-bottom: .1rem solid #a7a7a7;
  width: 90%;
  margin: auto;
  min-height: 10vh;
}
.search_container {
  text-decoration: none;
}

.inputTitle {
  font-weight: bold;
  font-size: 1.5rem;
  font-family: 'LINESeedKR-Bd';
  text-align: start;
  /* margin-left: 1rem; */
}

.ingredientList {
  /* display: flex;
  border-bottom: solid grey; */
  display: flex;
    /* border-bottom: .1rem solid #aeaeae; */
    align-items: center;
    justify-content: space-between;
    width: 100%;
    /* padding: .5rem; */
    margin: auto;
    height: 5vh;
    margin-bottom: .5rem;
  
}

.ingredientName {
    font-weight: bold;
    width: 10rem;
    margin-top: auto;
    margin-bottom: auto;
}

.modal-body {
  display: flex;
  justify-content: center;
  margin: auto;
  width: 100%;
}


.amount {
  display: flex;
  border-radius: .5rem;
  /* margin: auto; */
  /* margin-left: 0.5rem; */
  margin-right: 0.5rem;
  justify-content: center;
  align-items: center;
  /* width: 20%; */
  /* border: solid black; */
}

.amountAndUnit {
  margin-bottom: 0;
  margin-top: 0;
  width: 5rem;
  height: 2rem;
  border-top: solid rgb(231, 231, 231) 0.1px;
  border-bottom: solid rgb(231, 231, 231) 0.1px;
  display: flex; /* 플렉스 박스 설정 */
  align-items: center; /* 세로 가운데 정렬 */
  justify-content: center;
}

#searchForm[data-v-0f963bc4] {
    border-radius: 0;
    margin-right: 0;
    margin-left: 0;
    width: 7rem;
}

#searchForm[data-v-652ac216] {
  border-radius: 0;
    margin-right: 0;
    margin-left: 0;
    width: 7rem;
}

.amountButton {
    width: 2rem;
    height: 100%;
    border-radius: 0 0.5rem 0.5rem 0;
    background-color: #FD7E14;
    border: solid #FD7E14;
    color: white;
    font-family: 'LINESeedKR-Rg';
    display: flex;
    justify-content: center;
    align-items: center;
}
.amountButton2 {
    width: 2rem;
    height: 100%;
    border-radius: 0.5rem 0 0 0.5rem;
    background-color: #FD7E14;
    border: solid #FD7E14;
    color: white;
    font-family: 'LINESeedKR-Rg';
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
    width: 15rem;
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
  font-family: 'LINESeedKR-Rg';
  border: none;
  color: white;
  background-color: #FD7E14;
  border-radius: 0.5rem;
  margin: 0.5rem;
  padding: .4rem .6rem;
  font-size: 1rem;
}

.inputComponent {
  font-family: 'LINESeedKR-Rg';
  justify-content: space-around;
  min-height: 25vh;
}

.radioButton {
    border: solid #FD7E14;
    border-radius: .5rem;
    padding: .5rem;
    margin: .5rem;
    margin-top: 0;
    width: 6rem;
}
.radioButton2 {
    border: solid #FD7E14;
    border-radius: .5rem;
    padding: .5rem;
    margin: .5rem;
    margin-top: 0;
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
    margin-left: .5rem;
    border-radius: .5rem;
    background-color: #f2f2f2;
    border: solid rgb(244, 244, 244);
}

.input-group {
  display: flex;
  justify-content: space-around;
  /* justify-content: start; */
  /* margin: auto; */
}

.servingButton {
  border: .1rem solid #FD7E14;
  background-color: white;
  border-radius: .5rem;
  width: 4rem;
  height: 3rem;
  padding: 0.25rem;
  font-size: 1rem;
  font-weight: bold;
  text-align: center;
}

.soundButton {
  border: .1rem solid #FD7E14;
  color: white;
  background-color: #FD7E14;
  border-radius: .5rem;
  margin: 1rem;
  padding: 0.5rem;
  font-size: 1.25rem;
  font-weight: bold;
}

.addButton {
  /* border: solid #FD7E14; */
  font-family: 'LINESeedKR-Rg';
  border: none;
  color: white;
  background-color: #FD7E14;
  border-radius: 0.5rem;
  margin: 0.5rem;
  padding: .4rem .6rem;
  font-size: 1rem;
  /* font-weight: bold; */
  float: right;
}

/* 검색 결과 스타일 */
.search_results_container{
  text-align: left;
  max-width: 100%;
  max-height: 150px;
  overflow-y: auto; 
  list-style-type: none;
}

.dropdown-item {
  padding-left: 1rem;
  font-family: 'LINESeedKR-Rg';
  /* margin-top: .25rem; */
}

.search_results_container ul{
  margin-top: 0.5rem;
  margin-bottom: 0.2rem;
}

.inputServing {
  font-family: 'LINESeedKR-Rg';
  font-weight: bold;
  margin-top: 0.5rem;
  padding-left: 0.5rem;
  margin-right: 0.5rem;
}

.inputServing .dropdown-item {
  padding-left: 0.5rem;
  
}

.explainText {
  margin-top: 1.5rem;
  font-family: 'LINESeedKR-Bd';
  font-size: 1.3rem;
  margin-bottom: 0.25rem;
  text-align: start;
  /* margin-left: 0.75rem; */
  font-weight: bold
}

</style>