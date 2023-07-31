<template>
  <div class="myPage">
    <CategoryComponent />
    <div id="myPageView">
        <div class="buttonGroup">
            <div class="storageRadio">
                <label class="radioButton">
                    <input type="radio" name="coldStorage" value="냉장" v-model="storageType" @click="changeClassification">냉장
                </label>
                <label class="radioButton">
                    <input type="radio" name="frozenStorage" value="냉동" v-model="storageType" @click="changeClassification">냉동
                </label>
                <label class="radioButton">
                    <input type="radio" name="rtStorage" value="실온" v-model="storageType" @click="changeClassification">실온
                </label>            
            </div>
            <div class="modalButtons">
                <IngredientModal />
                <SeasoningModal />
            </div>
        </div>
        
        <div>
            <div class="refrigeratorCategory">
                <p class="categoryTitle">{{storageType}} 재료</p>
                <ul class="ListShow">
                    <li class= "row" v-for="(ingredient, index) in ingredients" :key="index">
                        <div class="ingredientList" v-if="ingredient.storage === storageType">
                            <p class="col-1 ingredientName">{{ingredient.name}}</p>
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
                            <button class="col-1 deleteButton" @click="deleteIngredient(ingredient)">제거</button>
                        </div>
                    </li>
                </ul>
            </div>
            <div>
                <p class="categoryTitle">{{storageType}} 양념</p>
                <ul class="ListShow">
                    <li class= "row" v-for="(seasoning, index) in seasonings" :key="index">
                        <div class="ingredientList" v-if="seasoning.storage === storageType">
                            <p class="col-2 ingredientName">{{seasoning.name}}</p>
                            
                            <p class="col-4">보관시작일 : {{seasoning.startDate}}</p>
                            <p class="col-3">
                            유통기한 : {{seasoning.endDate}}
                            </p>
                            <p class="col-2">보관방식 : {{seasoning.storage}}</p>
                            <button class="col-1 deleteButton" @click="deleteSeasoning(seasoning)">제거</button>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
        
    </div>
  </div>
</template>

<script>
import CategoryComponent from './categoryComponent.vue'
import IngredientModal from '../modalPage/IngredientModal.vue'
import SeasoningModal from '../modalPage/SeasoningModal'

export default {
    name: 'RefrigeratorPage',
    components: {
        CategoryComponent,
        IngredientModal,
        SeasoningModal,
    },
    data() {
        return {
            ingredients : [{name : "감자", amount: 2, unit: "개",  startDate : '2023-07-27', endDate: '', storage: '냉장'}, 
                {name : "김치", amount: 2, unit: "포기",  startDate : '2023-07-27', endDate: '', storage: '냉장'},
                {name : "계란", amount: 2, unit: "개",  startDate : '2023-07-27', endDate: '', storage: '냉장'},
                {name : "돼지고기", amount: 600, unit: "g",  startDate : '2023-07-27', endDate: '', storage: '냉장'},
                {name : "소고기", amount: 1200, unit: "g",  startDate : '2023-07-27', endDate: '', storage: '냉장'},
                {name : "양파", amount: 2, unit: "개",  startDate : '2023-07-27', endDate: '', storage: '냉동'}, 
                {name : "닭", amount: 2, unit: "마리",  startDate : '2023-07-27', endDate: '', storage: '냉동'},
                {name : "대파", amount: 2, unit: "개",  startDate : '2023-07-27', endDate: '', storage: '냉동'},
                {name : "칡", amount: 600, unit: "g",  startDate : '2023-07-27', endDate: '', storage: '냉동'},
                {name : "마", amount: 1200, unit: "g",  startDate : '2023-07-27', endDate: '', storage: '냉동'},
                {name : "라면", amount: 2, unit: "개",  startDate : '2023-07-27', endDate: '', storage: '실온'}, 
                {name : "김", amount: 2, unit: "개",  startDate : '2023-07-27', endDate: '', storage: '실온'},],
            seasonings : [{name : "고춧가루", startDate : '2023-07-27', endDate: '', storage: '냉장'}, 
                {name : "진간장",  startDate : '2023-07-27', endDate: '', storage: '냉장'},
                {name : "고추장",  startDate : '2023-07-27', endDate: '', storage: '냉장'},
                {name : "된장",  startDate : '2023-07-27', endDate: '', storage: '냉장'},
                {name : "고춧가루", startDate : '2023-07-27', endDate: '', storage: '냉동'}, 
                {name : "다진마늘",  startDate : '2023-07-27', endDate: '', storage: '냉동'},
                {name : "다시마",  startDate : '2023-07-27', endDate: '', storage: '냉동'},
                {name : "멸치액젓", startDate : '2023-07-27', endDate: '', storage: '실온'}, 
                {name : "양조간장",  startDate : '2023-07-27', endDate: '', storage: '실온'},
                {name : "초고추장",  startDate : '2023-07-27', endDate: '', storage: '실온'},
                {name : "케쳡",  startDate : '2023-07-27', endDate: '', storage: '실온'},],
            storageType : '냉장',
        }
    },
    methods: {
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
                this.ingredients = arrayRemove(this.ingredients, ingredient)
            }
        },
        deleteIngredient(ingredient) {
            const arrayRemove = (arr, value) => {
                return arr.filter((ele) => {
                    return ele != value
                })
            }
            this.ingredients = arrayRemove(this.ingredients, ingredient)
        },
        deleteSeasoning(seasoning) {
            const arrayRemove = (arr, value) => {
                return arr.filter((ele) => {
                    return ele != value
                })
            }
            this.seasonings = arrayRemove(this.seasonings, seasoning)
        }
    }
}
</script>

<style>
.myPage {
    display: flex;
}

#myPageMenu {
    width: 15%;
}

#myPageView {
    width: 85%;
    margin-top: 3rem;
}


</style>

<style scoped>
.buttonGroup {
    display: flex;
    justify-content: space-between;
    width: 90%;
}

.modalButtons {
    display: flex;
    /* border: solid #FD7E14;
    border-radius: .5rem;
    padding: .5rem;
    margin: .5rem;
    width: 6rem; */
}

.ListShow {
  border: solid #a7a7a7;
  border-radius: .5rem;
  width: 80%;
  margin: auto;
  padding: 1rem;
  margin-bottom: 5rem;
}

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

.deleteButton {
    border-radius: .5rem;
    height: 2rem;
    background-color: #FD7E14;
    color: white;
    border: none
}

.refrigeratorCategory {
    margin-top: 2rem;
}

.storageRadio {
    display: flex;
    margin-top: 3rem;
    margin-left: 7.5rem;
}

.categoryTitle {
    text-align: start;
    margin-left: 9rem;
    font-size: 2rem;
    font-weight: bold;
}
</style>