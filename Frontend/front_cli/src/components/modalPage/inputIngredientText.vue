<template>
  <!-- 재료 텍스트 입력 모달 -->
  <div class="modal fade modal-xl" id="exampleModalToggle" aria-hidden="true" aria-labelledby="exampleModalToggleLabel" tabindex="-1">
      <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
          <div class="modal-header">
            <h1 class="modal-title fs-5" id="exampleModalToggleLabel">재료 입력</h1>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>

          <!-- 입력된 재료 리스트 -->
          <div class="modal-body">
            <ul class="ListShow">
              <li class="ingredientList row" v-for="(ingredient, index) in ingredientList" :key="index">
                <p class="col-1">{{ingredient.name}}</p>
                <div class="amount col-2 row">
                  <button class="amountButton col-3" @click="plusAmount(ingredient)">+</button>
                  <p class="col-6">{{ingredient.amount}}{{ingredient.unit}}</p>
                  <button class="amountButton col-3" @click="minusAmount(ingredient)">-</button>
                </div>
                <p class="col-3">보관시작일 : {{ingredient.startDate}}</p>
                <p class="col-3">
                  유통기한 : {{ingredient.endDate}}
                </p>
                <p class="col-2">보관방식 : {{ingredient.storage}}</p>
                <button class="col-1" @click="deleteIngredient(ingredient)">제거</button>
              </li>
            </ul>
          </div>

          <!-- 재료 이름 검색 폼 -->
          <div class="modal-body inputComponent">
            <div>
              <form @submit.prevent="goToSearchwithKeyword">
                <div class="input-group">
                    <input id="searchForm ingredientText" class="form-control" type="text" v-model.trim="ingredientName">
                    <input id="submitButton" type="submit" value="검색">
                </div>
              </form>
            </div>

            <!-- 재료 입력 폼 -->
            <div>
              <div>
                <form @submit.prevent="goToSearchwithKeyword">
                  <div class="input-group">
                      <button class="amountButton" @click="addAmount">+</button>
                      <input id="searchForm" class="form-control" type="number" v-model.trim="amount">
                      <button class="amountButton" @click="reduceAmount">-</button>
                      <div class="dropdown">
                        <button class="dropdown-toggle servingButton" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                          {{unit}}
                        </button>
                        <ul class="dropdown-menu">
                          <li><button class="dropdown-item" type="button" @click="chageUnitGae">개</button></li>
                          <li><button class="dropdown-item" type="button" @click="chageUnitGram">g</button></li>
                          <li><button class="dropdown-item" type="button" @click="chageUnitMari">마리</button></li>
                          <li class="inputServing">직접기입 : <input class="dropdown-item" type="text" v-model="unit"></li>
                        </ul>
                      </div>
                  </div>
                </form>
              </div>
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
              <button class="soundButton" data-bs-target="#exampleModalToggle2" data-bs-toggle="modal">음성입력</button>
              <button class="soundButton" @click="appendList">추가하기</button>
            </div>
          </div>

          <!-- 재료 리스트 저장 -->
          <div class="modal-footer">
            <button class="soundButton" @click="pushIngredientData">저장하기</button>
          </div>
        </div>
      </div>
    </div>
</template>

<script>
export default {
    name: 'IngredientText',
    data() {
      return {
        ingredientList : [{name : "감자", amount: 2, unit: "개",  startDate : '2023-07-27', endDate: '', storage: '냉장'}, 
        {name : "김치", amount: 2, unit: "포기",  startDate : '2023-07-27', endDate: '', storage: '냉장'},
        {name : "계란", amount: 2, unit: "개",  startDate : '2023-07-27', endDate: '', storage: '냉장'},
        {name : "돼지고기", amount: 600, unit: "g",  startDate : '2023-07-27', endDate: '', storage: '냉장'},
        {name : "소고기", amount: 1200, unit: "g",  startDate : '2023-07-27', endDate: '', storage: '냉장'},],
        ingredientName: '',
        amount : 0,
        unit : '개',
        selectStorage : '냉장',
      }
  },
  methods: {
    chageUnitGae() {
      this.unit = '개'
    },
    chageUnitGram() {
      this.unit = 'g'
    },
    chageUnitMari() {
      this.unit = '마리'
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

      this.ingredientList.push({name: this.ingredientName, amount : this.amount, unit: this.unit, startDate : todayDate, endDate: '', storage: this.selectStorage})

      this.ingredientName = ''
      this.amount = 0
      this.unit = '개'
      this.selectStorage = '냉장'
    },
    pushIngredientData() {
      console.log(this.ingredientList)
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
      if(this.unit === 'g') {
        this.amount -= 10
      }
      else {
        this.amount --
      }

      
    },
    plusAmount(ingredient) {
      ingredient.amount ++
    },
    minusAmount(ingredient) {
      ingredient.amount --
      if (ingredient.amount <= 0) {
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
            return ele != value
          })
        }
        this.ingredientList = arrayRemove(this.ingredientList, ingredient)
      },

}
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
  width: 2rem;
  height: 60%;
  border-radius: .5rem;
  background-color: #f2f2f2;
  border: solid rgb(244, 244, 244);
}

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