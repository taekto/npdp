<template>
  <!-- 레시피 정보(재료, 영양소, 유사도 등) -->
  <div>
    <!-- 레시피 정보 윗줄(재료 & 그래프) -->
    <div class="InfomationLine">
      <!-- 재료 정보 -->
      <IngredientInfomation class="infoBox" :ingredients=ingredients :serving=serving />
      
      <!-- 유사도 그래프 -->
      <GraphInfomation class="infoBox" :similarity=similarity />
    </div>
    
    <!-- 레시피 정보 아랫줄(영양소 & 재료 관리 버튼) -->
    <div class="InfomationLine">
      <!-- 영양소 정보 -->
      <CalorieInformation class="infoBox" :method=method :calorie=calorie :serving=serving />
      
      <!-- 각종 버튼(인분 조절, 재료 입력, 양념 입력) -->
      <div class="infoBox">
        <!-- 인분 조절 -->
        <div class="dropdown mt-3">
          <button class="dropdown-toggle servingButton" type="button" data-bs-toggle="dropdown" aria-expanded="false">
            {{serving}}인분
          </button>
          <ul class="dropdown-menu">
            <li><button class="dropdown-item" type="button" @click="chageServingOne">1인분</button></li>
            <li><button class="dropdown-item" type="button" @click="chageServingTwo">2인분</button></li>
            <li><button class="dropdown-item" type="button" @click="chageServingThree">3인분</button></li>
            <li><button class="dropdown-item" type="button" @click="chageServingFour">4인분</button></li>
            <li class="inputServing">직접기입 : <input class="dropdown-item inputServingNumber" type="number" v-model="serving"></li>
          </ul>
        </div>

        <!-- 재료 입력 모달 버튼 -->
        <IngredientModal />

        <!-- 양념 입력 모달 버튼 -->
        <SeasoningModal />
      </div>
    </div>
  </div>
</template>

<script>
import IngredientInfomation from './IngredientInfomation.vue'
import GraphInfomation from './GraphInfomation.vue'
import CalorieInformation from './CalorieInfomation.vue'
import IngredientModal from '../../modalPage/IngredientModal'
import SeasoningModal from '../../modalPage/SeasoningModal'

export default {
    name : 'RecipeInfomation',
    components: {
      IngredientInfomation,
      GraphInfomation,
      CalorieInformation,
      IngredientModal,
      SeasoningModal,
    },
    props: {
      recipe: Object,
    },
    data() {
      return {
        ingredients: this.$props.recipe.ingredients,
        similarity : this.$props.recipe.similarity,
        method: this.$props.recipe.method,
        calorie : this.$props.recipe.calorie,
        serving : 1,
      }
    },
    methods: {
      chageServingOne() {
        this.serving = 1
      },
      chageServingTwo() {
        this.serving = 2
      },
      chageServingThree() {
        this.serving = 3
      },
      chageServingFour() {
        this.serving = 4
      },
      goToIngredient() {
        console.log('ingredient')
      }
    }
}
</script>

<style>
/* 각 레시피 정보가 들어갈 박스 */
.infoBox {
    width: 15rem;
    height: 15rem;
}
/* 재료 정보 박스 */
/* 각 줄 정보 */
.oneLine {
    display: flex;
    justify-content: space-around;
    height: 1.5rem;
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
    font-size: 17.5px;
    font-weight: bold;
    margin-bottom: 1rem;
}
</style>

<style scoped>
/* 레시피 정보 */

/* 레시피 정보 각 줄 */
.InfomationLine {
    display: flex;
}



/* 인분 조절 버튼 */
.servingButton {
    border: solid #FD7E14;
    background-color: white;
    border-radius: .5rem;
    margin: 1rem;
    margin-top: 0rem;
    padding: 0.7rem;
    padding-left: 1.5rem;
    font-size: 1.25rem;
    font-weight: bold;
    text-align: end;    
}

.inputServing {
    margin-left: 1rem;
    margin-right: 1rem;
}

.inputServingNumber {
    border: solid rgb(174, 174, 174);
    border-radius: .25rem;
}

</style>