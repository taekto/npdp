<template>
  <!-- 레시피 정보(재료, 영양소, 유사도 등) -->
  <div>
    <!-- 레시피 정보 윗줄(재료 & 그래프) -->
    <div class="graph_title" v-if="recipeDetail.memberName"><span class="similarity_title">{{recipeDetail.memberName}}</span>님 냉장고 속 재료와 <span class="similarity_title">{{(recipeDetail.similarity*100).toFixed(2)}}%</span> 일치합니다!</div>
    <div class="graph_title logout" v-else>로그인 후 유사도 확인이 가능합니다</div>
    <div class="InfomationLine">
      <!-- 유사도 그래프 -->
      <GraphInfomation :similarity='similarity' class="infoBox graph" />
    </div>
    <div class="InfomationLine">
    <!-- 레시피 정보 아랫줄(영양소 & 재료 관리 버튼) -->
    <div class="InfomationLine2">
      <!-- 영양소 정보 -->
      
      <!-- 재료 정보 -->
      <CalorieInformation class="infoBox" :serving='serving' />
    </div>
    <!-- 각종 버튼(인분 조절, 재료 입력, 양념 입력) -->
    <div class="infoBox input_button">
      <!-- 인분수 정보 -->
      <p class="dishText">기준 : {{recipeDetail.dish}}인분</p>
      
      <!-- 재료 입력 모달 버튼
      <div class="modal_container">
        <IngredientModal />
        양념 입력 모달 버튼 -->
        <!-- <SeasoningModal />
        </div> -->
        </div>
    </div>
  </div>
</template>

<script>

import GraphInfomation from './GraphInfomation.vue'
import CalorieInformation from './CalorieInfomation.vue'
// import IngredientModal from '../../modalPage/IngredientModal'
// import SeasoningModal from '../../modalPage/SeasoningModal'
import {mapGetters} from 'vuex'

export default {
    name : 'RecipeInfomation',
    components: {
      GraphInfomation,
      CalorieInformation,
      // IngredientModal,
      // SeasoningModal,
    },
    computed: {
      ...mapGetters(['recipeDetail']),
      
    },
    mounted() {
      this.chageServingOne()
    },
    data() {
      return {
        serving : 1,
        similarity: 0,
      }
    },
    methods: {
      chageServingOne() {
        this.serving = this.recipeDetail.dish * 1
        this.$emit('servingUpdate', this.serving)
      },
      chageServingTwo() {
        this.serving = this.recipeDetail.dish * 2
        this.$emit('servingUpdate', this.serving)
      },
      chageServingThree() {
        this.serving = this.recipeDetail.dish * 3
        this.$emit('servingUpdate', this.serving)
      },
      chageServingFour() {
        this.serving = this.recipeDetail.dish * 4
        this.$emit('servingUpdate', this.serving)
      },
      // goToIngredient() {
      //   console.log('ingredient')
      // }
    }
}
</script>

<style>
/* 각 레시피 정보가 들어갈 박스 */
.infoBox {
    width: 13rem;
    height: 13rem;
}
.percent {
  color: #FD7E14;
}
.graph_title {
  font-family: 'LINESeedKR-Bd';
  font-size: 1.7rem;
  margin-left: 3rem;
  margin-bottom: 1rem;
  margin: 0 auto;
}

.graph_title.logout {
  font-family: 'LINESeedKR-Rg';
  font-size: 1.1rem;
  color: #ff0000;
  border: 1px solid #f3f3f3;
  background-color: #f3f3f3;
  padding: .9rem;
  margin: 0 auto;
}

.infoBox.graph {
  /* margin-top: 25%; */
  margin-bottom: 3rem;
}

/* 재료 정보 박스 */
/* 각 줄 정보 */
.oneLine {
    display: flex;
    justify-content: space-around;
    height: 1.5rem;
    font-family: 'LINESeedKR-Bd';
}
.ingredientName {
    justify-content: center;
    width: 50%;
}
.ingredientAmount {
    display: flex;
    justify-content: center;
    width: 50%;
}
/* 상단 설명 */
.menu {
    font-size: 20px;
    font-weight: bold;
    margin-bottom: 1rem;
}
</style>

<style scoped>
/* 레시피 정보 */

/* 레시피 정보 각 줄 */
.InfomationLine {
    display: flex;
    justify-content: center;
}

.InfomationLine2 {
    display: flex;
    justify-content: flex-end;
}

.similarity_title {
  color: #FD7E14;
}

.dishText {
  font-size: 1.5rem;
  font-weight: bold;
  text-align: center;
  font-family: 'GangwonEdu_OTFBoldA';
}

/* 인분 조절 버튼 */
.servingButton {
    border: 1.7px solid #FD7E14;
    background-color: white;
    border-radius: .5rem;
    margin: 1rem;
    margin-top: 0rem;
    padding: 0.3rem;
    padding-left: 1rem;
    font-size: 1.25rem;
    font-weight: bold;
    text-align: end;
    font-family: 'GangwonEdu_OTFBoldA';
}

.modal_container {
  display: flex;
  justify-content: center;
  margin-top: 1rem;
  margin-bottom: 1rem;
}

.inputServing {
  font-family: 'GangwonEdu_OTFBoldA';
  font-size: 1.2rem;
    display: flex;
    margin-left: 1rem;
    margin-right: 1rem;
}

.inputServingNumber {
    width: 40%;
    height: 2rem;
    border: 1px solid rgb(174, 174, 174);
    border-radius: .25rem;
}

.dropdown-item {
  font-size: 1.2rem;
  font-family: 'GangwonEdu_OTFBoldA';
}

</style>