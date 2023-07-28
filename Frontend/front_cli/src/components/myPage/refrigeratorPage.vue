<template>
  <div class="myPage">
    <div class="myPageComponent" id="myPageMenu">
        <div>
            <router-link to="/mypage/refrigerator">내 냉장고</router-link>
        </div>
        <div>
            <router-link to="/mypage/tool">내 조리도구</router-link>
        </div>
        <div>
            <router-link to="/mypage/like">내가 좋아요한 레시피</router-link>
        </div>
        <div>
            <router-link to="/mypage/edit">내 정보 수정</router-link>
        </div>
    </div>
    <div class="myPageComponent" id="myPageView">
        <h1>냉장고</h1>
        <div>
            <label class="radioButton">
                <input type="radio" name="classification" value="냉장" v-model="storageType" @click="changeClassification">냉장
            </label>
            <label class="radioButton">
                <input type="radio" name="classification" value="냉동" v-model="storageType" @click="changeClassification">냉동
            </label>
            <label class="radioButton">
                <input type="radio" name="classification" value="실온" v-model="storageType" @click="changeClassification">실온
            </label>
                        
        </div>
        <div>
            <div>
                <p>재료</p>
                <ul class="ListShow">
                    <li class= "row" v-for="(ingredient, index) in ingredients" :key="index">
                        <div class="ingredientList" v-if="ingredient.storage === storageType">
                            <p class="col-1">{{ingredient.name}}</p>
                            <div class="amount col-2 row">
                                <button class="amountButton col-3">+</button>
                                <p class="col-6">{{ingredient.amount}}{{ingredient.unit}}</p>
                                <button class="amountButton col-3">-</button>
                            </div>
                            <p class="col-3">보관시작일 : {{ingredient.startDate}}</p>
                            <p class="col-3">
                            유통기한 : {{ingredient.endDate}}
                            </p>
                            <p class="col-2">보관방식 : {{ingredient.storage}}</p>
                            <button class="col-1">제거</button>
                        </div>
                    </li>
                </ul>
            </div>
            <div>
                <p>양념</p>
                <ul class="ListShow">
                    <li class= "row" v-for="(seasoning, index) in seasonings" :key="index">
                        <div class="ingredientList" v-if="seasoning.storage === storageType">
                            <p class="col-1">{{seasoning.name}}</p>
                            <div class="amount col-2 row">
                                <button class="amountButton col-3">+</button>
                                <p class="col-6">{{seasoning.amount}}{{seasoning.unit}}</p>
                                <button class="amountButton col-3">-</button>
                            </div>
                            <p class="col-3">보관시작일 : {{seasoning.startDate}}</p>
                            <p class="col-3">
                            유통기한 : {{seasoning.endDate}}
                            </p>
                            <p class="col-2">보관방식 : {{seasoning.storage}}</p>
                            <button class="col-1">제거</button>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    
  </div>
</template>

<script>
export default {
    name: 'RefrigeratorPage',
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
}

.myPageComponent {
    margin-top: 3rem;
    margin-bottom: 3rem;
    border-radius: .5rem;
    box-shadow: 1px 1px 1px 1px;
}
</style>

<style scoped>
.ListShow {
  border-bottom: solid #a7a7a7;
  width: 95%;
  margin: auto;
}

.ingredientList {
  display: flex;
  border-bottom: solid grey;
  
}

.amount {
  display: flex;
}

.amountButton {
  width: 2rem;
  height: 60%;
  border-radius: .5rem;
  background-color: #f2f2f2;
  border: solid rgb(244, 244, 244);
}
</style>