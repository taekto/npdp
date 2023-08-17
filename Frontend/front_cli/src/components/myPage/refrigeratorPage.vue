<template>
    <!-- 마이페이지 냉장고(재료, 양념 관리) 페이지 -->
    <div class="myPage">
        <!-- 좌측 마이페이지 메뉴 컴포넌트 -->
        <CategoryComponent />

        <!-- 우측 냉장고 컴포넌트 -->
        <div id="myPageView">
            <!-- <p class="menuTitle">내 냉장고</p> -->
            <!-- 보관방법 변경 & 재료, 양념 입력 버튼 -->
            <div class="buttonGroup">
                <!-- 보관 방법 변경 버튼 -->
                <div class="storageRadio">
                    <label v-if="storage !== 0" class="radioButton_1">
                        <input type="radio" name="coldStorage" value="냉장" @click="selectStorage(0)">냉장
                    </label>
                    <label v-else class="radioButton_2">
                        <input type="radio" name="coldStorage" value="냉장" @click="selectStorage(0)">냉장
                    </label>
                    <label v-if="storage !== 1" class="radioButton_1">
                        <input type="radio" name="frozenStorage" value="냉동" @click="selectStorage(1)">냉동
                    </label>
                    <label v-else class="radioButton_2">
                        <input type="radio" name="frozenStorage" value="냉동" @click="selectStorage(1)">냉동
                    </label>
                    <label v-if="storage !== 2" class="radioButton_1">
                        <input type="radio" name="rtStorage" value="실온" @click="selectStorage(2)">실온
                    </label>            
                    <label v-else class="radioButton_2">
                        <input type="radio" name="rtStorage" value="실온" @click="selectStorage(2)">실온
                    </label>            
                </div>
                <!-- 재료, 양념 입력 버튼 -->
                <div class="modalButtons">
                    <IngredientModal />
                    <SeasoningModal />
                </div>
            </div>
            <!-- <div class="subtitle_line"></div> -->
            
            <!-- 현재 보관 중인 재료 & 양념 보여주는 컴포넌트(냉장, 냉동, 실온) -->
            <div>
                <!-- 재료 -->
                <div class="refrigeratorCategory">
                    <div style="display: flex; justify-content: space-between; width: 80%; margin: auto">
                        <div class="categoryTitle">내가 보관중인 재료</div>
                        <button class="saveButton" @click="saveMaterial({type: 'ingredient', memberId: this.memberId, sendData: displayedIngredientItems.tempIngredient })">
                            저장하기
                        </button>
                    </div>
                    
                    <ul class="ListShow">
                        <li v-for="(ingredientItem, index) in displayedIngredientItems.displayedItems" :key="index">
                            <div class="ingredientList">
                                <p class="ingredientName">{{ingredientItem.kor}}</p>
                                <div class="amount">
                                    <button class="amountButton" @click="minusAmount(ingredientItem)">-</button>
                                    <p class="amountAndUnit">{{changeAmount(ingredientItem.amount)}}{{ingredientItem.unit}}</p>
                                    <button class="amountButton" @click="plusAmount(ingredientItem)">+</button>
                                </div>
                                <div class="startDate">
                                    <p>보관시작일 : {{changeDate(ingredientItem.startDate)}}</p>
                                </div>
                                <!-- <div class="endDate">
                                    <p>유통기한 : </p>
                                    <p>{{changeDate(ingredientItem.expiredDate)}}</p>
                                </div> -->

                                <!-- <p class="startDate">보관시작일 : {{whatDate(ingredientItem.startDate)}}</p> -->
                                
                                <p class="storage">보관방식 : {{printStorage}}</p>
                                <button class="deleteButton" @click="updateMaterial({type: 'ingredientDelete', memberId: this.memberId, updateItem: ingredientItem })">제거</button>
                            </div>
                        </li>
                    </ul>
                    <div class="pagination">
                        <button @click="goToIngredientPage(ingredientPage - 1)" :disabled="ingredientPage === 1">이전</button>
                        <button v-for="pageNumber in ingredientTotalPages" :key="pageNumber" 
                        @click="goToIngredientPage(pageNumber)" 
                        :disabled="ingredientPage === pageNumber">
                            {{ pageNumber }}
                        </button>
                        <button @click="goToIngredientPage(ingredientPage + 1)" :disabled="ingredientPage === ingredientTotalPages">다음</button>
                    </div>
                </div>
                <!-- 양념 -->
                <div class="refrigeratorCategory">

                    <div style="display: flex; justify-content: space-between; width: 80%; margin: auto">
                        <div class="categoryTitle">내가 보관중인 양념</div>
                        <button class="saveButton" @click="saveMaterial({ type: 'seasoning', memberId: memberId, sendData: displayedIngredientItems.tempIngredient })">
                            저장
                        </button>
                    </div>


                    <ul class="ListShow">
                        <li v-for="seasoningItem in displayedSeasoningItems.displayedItems" :key="seasoningItem.memberSeasoningId">
                            <div class="ingredientList">
                                <p class="ingredientName2">{{seasoningItem.kor}}</p>
                                <div class="startDate">
                                    <p>보관시작일 : {{changeDate(seasoningItem.startDate)}}</p>
                                </div>
                                <!-- <div class="endDate">
                                    <p>유통기한 :</p>
                                    <p>{{changeDate(seasoningItem.expiredDate)}}</p>
                                </div> -->
                                <p class="storage">보관방식 : {{printStorage}}</p>
                                <button class="deleteButton" @click="updateMaterial({type: 'seasoningDelete', memberId: this.memberId, updateItem: seasoningItem })">제거</button>
                            </div>
                        </li>
                    </ul>
                    <div class="pagination">
                        <button @click="goToSeasoningPage(seasoningPage - 1)" :disabled="seasoningPage === 1">이전</button>
                        <button v-for="pageNumber in seasoningtTotalPages" :key="pageNumber" 
                        @click="goToSeasoningPage(pageNumber)"
                        :disabled="seasoningPage === pageNumber">
                            {{ pageNumber }}
                        </button>
                        <button @click="goToSeasoningPage(seasoningPage + 1)" :disabled="seasoningPage === seasoningtTotalPages">다음</button>
                    </div>
                </div>
            </div>
            
        </div>
    </div>
</template>

<script>
import CategoryComponent from './categoryComponent.vue'
import IngredientModal from '../modalPage/IngredientModal.vue'
import SeasoningModal from '../modalPage/SeasoningModal'

import {mapGetters, mapActions} from 'vuex'

export default {
    name: 'RefrigeratorPage',
    components: {
        CategoryComponent,
        IngredientModal,
        SeasoningModal,
    },
    computed: {
        ...mapGetters(['memberSeasoning', 'memberIngredient']),
        ingredientTotalPages() {
          let count = 0
          for (let ingredient of this.memberIngredient) {
            if(this.storage === ingredient.storage) {
              count ++ 
            }
          }
          return Math.ceil(count / this.itemsPerPage)
        },
        seasoningtTotalPages() {
          let count = 0
          for (let seasoning of this.memberSeasoning) {
              if(this.storage === seasoning.storage) {
                count ++ 
              }
          }
          return Math.ceil(count / this.itemsPerPage)
        },

        
        
        displayedIngredientItems() {
          const startIndex = (this.ingredientPage - 1) * this.itemsPerPage
          const endIndex = startIndex + this.itemsPerPage
          
          const tempIngredient = this.memberIngredient
            .filter(ingredient => ingredient.storage === this.storage)
            .map(ingredient => {
                return {
                    refregiratorId: ingredient.refregiratorId,
                    amount: ingredient.amount,
                    unit: ingredient.unit,
                    startDate: ingredient.startDate,
                    expiredDate: ingredient.expiredDate,
                    storage: ingredient.storage,
                    isdelete: ingredient.amount <= 0
                }
          })
          const displayedItems = this.memberIngredient.filter(ingredient => {
            return ingredient.storage === this.storage
          }).slice(startIndex, endIndex)

          const tmp = {
            displayedItems : displayedItems,
            tempIngredient : tempIngredient,
          }
          return tmp
        },
        displayedSeasoningItems() {
          const startIndex = (this.seasoningPage - 1) * this.itemsPerPage
          const endIndex = startIndex + this.itemsPerPage
          const displayedItems = this.memberSeasoning.filter(seasoning => {
            return seasoning.storage === this.storage
          }).slice(startIndex, endIndex)

          const tempSeasoning = this.memberSeasoning
            .filter(seasoning => seasoning.storage === this.storage)
            .map(seasoning => {
              return { 
                seasoningId: seasoning.memberSeasoningId,
                storage: seasoning.storage,
                startDate: seasoning.startDate,
                expiredDate: seasoning.expiredDate,
                isdelete: false,
              }
          })
           const tmp = {
            displayedItems : displayedItems,
            tempSeasoning : tempSeasoning,
          }
          return tmp
        },
    },
    // 임시 더미 데이터
    data() {
      return {
        storage : 0,
        memberId:null,
        printStorage:'냉장',
        delData:[],
        refregiratorId: null,
        amount: null,
        unit: '',
        startDate: null,
        expiredDate:  null,
        itemsPerPage:5,
        ingredientPage:1,
        seasoningPage:1,
      }
    },
    methods: {
        ...mapActions(['fetchMemberMaterial', 'updateMaterial','saveMaterial']),
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
        plusAmount(tmpingredient) {
          if(tmpingredient.unit === "g") {
            tmpingredient.amount += 10
          }
          else{
            tmpingredient.amount ++        
          }
          
        },
        minusAmount(tmpingredient) {
          if(tmpingredient.unit === "g") {
            tmpingredient.amount -= 10
          }
          else{
            tmpingredient.amount --
          }

          if (tmpingredient.amount <= 0) {
            this.updateMaterial({type: 'ingredient', memberId: this.memberId, updateItem: tmpingredient })
          }
        },
        goToIngredientPage(pageNumber) {
            if (pageNumber >= 1 && pageNumber <= this.ingredientTotalPages) {
                this.ingredientPage = pageNumber;
            }
        },
        goToSeasoningPage(pageNumber) {
            if (pageNumber >= 1 && pageNumber <= this.seasoningtTotalPages) {
                this.seasoningPage = pageNumber;
            }
        },
        changeDate(targetdate) {
            if (targetdate !== null) {
                let returnDate = ''

                returnDate = targetdate.slice(0, 4) + '년 ' + targetdate.slice(5, 7) + '월 ' + targetdate.slice(8, 10) + '일'


                return returnDate
            }
            else {
                return
            }
        },
        changeAmount(value) {
            if(!isNaN(parseFloat(value))) {
                let newValue = parseFloat(value).toFixed(0)
                return newValue
            }
            else {
                return value
            }
        },
        
    },
    created() {
        this.memberId = parseInt(sessionStorage.getItem('memberId'))
        this.fetchMemberMaterial({type:'all', memberId:this.memberId})
    },
}
</script>

<style scoped>
#myPageView {
    width: 75%;
}
.subtitle_line {
    position: absolute;
    margin-left: 5rem;
    width: 60%;
    border-width: .1rem 0 0;
    border-style: solid;
    border-color: #cecece;
}

/* 보관 방법 & 모달창 버튼 */
.buttonGroup {
    display: flex;
    justify-content: space-between;
    width: 80%;
    margin: auto;
    /* border-bottom: .1rem solid #cecece; */
}

.modalButtons {
    display: flex;
}

.ListShow {
    border: solid #a7a7a7;
    border-radius: .5rem;
    width: 80%;
    margin: auto;
    padding: 1rem;
    margin-bottom: 3rem;
    height: 60vh;
}

/* 레시피의 ingredientName과 다름 */
.ingredientName {
    font-weight: bold;
    width: 7.5%;
    margin-top: auto;
    margin-bottom: auto;
}

.ingredientName2 {
    font-weight: bold;
    width: 15%;
    margin-top: auto;
    margin-bottom: auto;
}

.ingredientList {
    display: flex;
    border-bottom: solid grey;
    align-items: center;
    justify-content: space-between;
    width: 95%;
    padding: .5rem;
    margin: auto;
    height: 10vh;
}

ul {
    list-style: none;
}

.amount {
    display: flex;
    /* border: solid rgb(207, 207, 207); */
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
    border: solid #FD7E14;
    background-color: #FD7E14;
    color: white;
}

.startDate {
    margin-top: auto;
    margin-bottom: auto;
    margin-left: .5rem;
    margin-right: .5rem;
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

.refrigeratorCategory {
  margin-top: 2rem;
  /* overflow-y: auto;  */
  /* max-height: 500px; */
}

.storageRadio {
    display: flex;
    align-items: end;
    /* margin: auto; */
    /* margin-top: 3rem; */
    /* margin-left: 7.5rem; */
}
.radioButton_1 {
    font-family: 'LINESeedKR-Bd';
    font-size: 1.4rem;
    margin-right: 1rem;
    cursor: pointer;
}
.radioButton_2 {
    font-family: 'LINESeedKR-Bd';
    color: #FD7E14; 
    font-size: 1.5rem;
    margin-right: 1rem;
    cursor: pointer;
    border-bottom: .1rem solid #FD7E14;
}
.radioButton_1:hover {
    color: #FD7E14;
}
.categoryTitle {
    font-family: 'LINESeedKR-Rg';
    text-align: start;
    margin-top: 3rem;
    margin-bottom: 1rem;
    /* margin-left: 1rem; */
    font-size: 1.5rem;
    font-weight: bold;
}

.pagination {
  margin-top: 10px;
  display: flex;
  justify-content: center;
  align-items: center;
  margin-bottom: 4rem;
}
.pagination button {
  font-family: 'LINESeedKR-Rg';
  margin: 0 5px;
  border-radius: .2rem;
  border: 1px solid #FD7E14;
  background-color: #fff;
  padding: .25rem .7rem;
}

.saveButton {
  /* border: solid #FD7E14; */
  color: white;
  background-color: #FD7E14;
  border-radius: .5rem;
  /* margin: .5rem; */
  padding: .1rem;
  margin-bottom: 1rem;
  /* margin-right: 1rem; */
  font-size: 1rem;
  /* font-weight: bold; */
  font-family: 'LINESeedKR-Rg';
  /* width: 5rem; */
}
</style>