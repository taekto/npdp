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
                    <p class="categoryTitle">{{storageType}} 재료</p>
                    <ul class="ListShow">
                        <li class= "row" v-for="tmpingredient in ingredients" :key="tmpingredient.pk">
                            <div class="ingredientList" v-if="tmpingredient.storage === storageType">
                                <p class="col-1 ingredientName">{{tmpingredient.name}}</p>
                                <div class="amount col-2 row">
                                    <button class="amountButton col-3" @click="plusAmount(tmpingredient)">+</button>
                                    <p class="col-6">{{tmpingredient.amount}}{{tmpingredient.unit}}</p>
                                    <button class="amountButton col-3" @click="minusAmount(tmpingredient)">-</button>
                                </div>
                                <p class="col-3">보관시작일 : {{tmpingredient.startDate}}</p>
                                <p class="col-3">
                                유통기한 : {{tmpingredient.endDate}}
                                </p>
                                <p class="col-2">보관방식 : {{tmpingredient.storage}}</p>
                                <button class="col-1 deleteButton" @click="deleteIngredient(tmpingredient)">제거</button>
                            </div>
                        </li>
                    </ul>
                </div>

                <!-- 양념 -->
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

import {mapGetters, mapActions} from 'vuex'

export default {
    name: 'RefrigeratorPage',
    components: {
        CategoryComponent,
        IngredientModal,
        SeasoningModal,
    },
    computed: {
        ...mapGetters(['ingredients', 'seasonings']),
        ...mapActions(['plusAmount', 'minusAmount'])
    },
    // 임시 더미 데이터
    data() {
        return {
            storageType : '냉장',
        }
    },
    methods: {

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