<template>
    <!-- 마이페이지 냉장고(재료, 양념 관리) 페이지 -->
    <div class="myPage">
        <!-- 좌측 마이페이지 메뉴 컴포넌트 -->
        <CategoryComponent />

        <!-- 우측 냉장고 컴포넌트 -->
        <div id="myPageView">
            <!-- 보관방법 변경 & 재료, 양념 입력 버튼 -->
            <div class="buttonGroup">
                <!-- 보관 방법 변경 버튼 -->
                <div class="storageRadio">
                    <label class="storagae radioButton">
                        <input type="radio" name="coldStorage" value="냉장" @click="selectStorage(0)">냉장
                    </label>
                    <label class="storagae radioButton">
                        <input type="radio" name="frozenStorage" value="냉동" @click="selectStorage(1)">냉동
                    </label>
                    <label class="storagae radioButton">
                        <input type="radio" name="rtStorage" value="실온" @click="selectStorage(2)">실온
                    </label>            
                </div>

                <!-- 재료, 양념 입력 버튼 -->
                <div class="modalButtons">
                    <IngredientModal />
                    <SeasoningModal />
                </div>
            </div>
            
            <!-- 현재 보관 중인 재료 & 양념 보여주는 컴포넌트(냉장, 냉동, 실온) -->
            <div>
                <!-- 재료 -->
                <div class="refrigeratorCategory">
                    <div style="display: flex; justify-content: space-between; width: 80%; margin: auto">
                        <p class="categoryTitle">{{printStorage}} 재료</p>
                        <button class="saveButton">
                            저장하기
                        </button>
                    </div>
                    
                    <ul class="ListShow">
                        <li class= "row" v-for="(ingredientItem, index) in displayedIngredientItems" :key="index">
                            <div class="ingredientList">
                                <p class="col-1 ingredientName">{{ingredientItem.kor}}</p>
                                <div class="amount col-2 row">
                                    <button class="amountButton col-3" @click="plusAmount(ingredientItem)">+</button>
                                    <p class="col-6">{{ingredientItem.amount}}{{ingredientItem.unit}}</p>
                                    <button class="amountButton col-3" @click="minusAmount(ingredientItem)">-</button>
                                </div>
                                <p class="col-3">보관시작일 : {{ingredientItem.startDate}}</p>
                                <p class="col-3">
                                유통기한 : {{ingredientItem.expiredDate}}
                                </p>
                                <p class="col-2">보관방식 : {{printStorage}}</p>
                                <button class="col-1 deleteButton" @click="deleteMaterial({type: 'ingredient', memberId: this.memberId, deleteItem: ingredientItem })">제거</button>
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
                        <p class="categoryTitle">{{printStorage}} 양념</p>
                        <button class="saveButton">
                            저장하기
                        </button>
                    </div>


                    <ul class="ListShow">
                        <li class= "row" v-for="seasoningItem in displayedSeasoningItems" :key="seasoningItem.memberSeasoningId">
                            <div class="ingredientList">
                                <p class="col-2 ingredientName">{{seasoningItem.kor}}</p>
                                
                                <p class="col-4">보관시작일 : {{seasoningItem.startDate}}</p>
                                <p class="col-3">
                                유통기한 : {{seasoningItem.expiredDate}}
                                </p>
                                <p class="col-2">보관방식 : {{printStorage}}</p>
                                <button class="col-1 deleteButton" @click="deleteMaterial({type: 'seasoning', memberId: this.memberId, deleteItem: seasoningItem })">제거</button>
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
            const displayedItems = this.memberIngredient.filter(ingredient => {
                return ingredient.storage === this.storage
            }).slice(startIndex, endIndex)
            return displayedItems
        },
        displayedSeasoningItems() {
            const startIndex = (this.seasoningPage - 1) * this.itemsPerPage
            const endIndex = startIndex + this.itemsPerPage
            const displayedItems = this.memberSeasoning.filter(seasoning => {
                return seasoning.storage === this.storage
            }).slice(startIndex, endIndex)
            return displayedItems
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
          isdelete : false,
          itemsPerPage:5,
          ingredientPage:1,
          seasoningPage:1,
        }
    },
    methods: {
        ...mapActions(['plusAmount', 'minusAmount', 'fetchMemberMaterial', 'deleteMaterial']),
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
        minusAmount(tmpingredient) {
            if(tmpingredient.unit === "g") {
                tmpingredient.amount -= 10
            }
            else{
                tmpingredient.amount --
            }

            if (tmpingredient.amount <= 0) {
                const arrayRemove = (arr, value) => {
                    return arr.filter((ele) => {
                        return ele != value
                    })
                }
                this.ingredients = arrayRemove(this.ingredients, tmpingredient)
            }
        },
        deleteIngredient(tmpingredient) {
            const arrayRemove = (arr, value) => {
                return arr.filter((ele) => {
                    return ele != value
                })
            }
            this.ingredients = arrayRemove(this.ingredients, tmpingredient)
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
        
    },
    created() {
        this.memberId = parseInt(sessionStorage.getItem('memberId'))
        this.fetchMemberMaterial({type:'all', memberId:this.memberId})
    },
}
</script>

<style scoped>
/* 보관 방법 & 모달창 버튼 */
.buttonGroup {
    display: flex;
    justify-content: space-between;
    width: 90%;
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
    margin-bottom: 5rem;
    height: 60vh;
}

/* 레시피의 ingredientName과 다름 */
.ingredientName {
font-weight: bold;
}

.ingredientList {
    display: flex;
    border-bottom: solid grey;
    align-items: center;
    width: 95%;
    padding: .5rem;
    margin: auto;
    height: 10vh;
}

.amount {
    display: flex;
    border: solid rgb(207, 207, 207);
    border-radius: .5rem;
    margin: auto;
    justify-content: center;
    align-items: center;
}

.amountButton {
    width: 2rem;
    height: 60%;
    border-radius: .5rem;
    background-color: #f2f2f2;
    border: solid rgb(244, 244, 244);
}

.deleteButton {
    border-radius: .5rem;
    height: 2rem;
    background-color: #FD7E14;
    color: white;
    border: none
}

.refrigeratorCategory {
  margin-top: 2rem;
  /* overflow-y: auto;  */
  /* max-height: 500px; */
}

.storageRadio {
    display: flex;
    margin-top: 2rem;
    margin-left: 4rem;
}

.storagae.radioButton {
    font-family: 'LINESeedKR-Bd';
    font-size: 1.2rem;
    cursor: pointer;
}
.storagae.radioButton:hover {
    background-color: #FD7E14;
    color: #f2f2f2;
}

.categoryTitle {
    text-align: start;
    margin-top: 1rem;
    margin-left: 1rem;
    font-size: 2rem;
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
  border: solid #FD7E14;
  color: white;
  background-color: #FD7E14;
  border-radius: .5rem;
  margin: .5rem;
  padding: 0.5rem;
  margin-bottom: 3rem;
  margin-right: 2rem;
  font-size: 1.25rem;
  font-weight: bold;
  font-family: 'LINESeedKR-Bd';
}
</style>