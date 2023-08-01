<template>
  <div>
        <div class="InfomationLine">
          <IngredientInfomation class="tempBox" :ingredients=ingredients :serving=serving />
          <GraphInfomation class="tempBox" :similarity=similarity />
        </div>
        
        <div class="InfomationLine">
          <CalorieInformation class="tempBox" :method=method :calorie=calorie :serving=serving />
          <div class="tempBox">
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
            <IngredientModal />
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

<style scoped>

.InfomationLine {
  display: flex;
}

.tempBox {
  width: 15rem;
  height: 15rem;
}

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

.ingredientButton {
  border: solid #FD7E14;
  color: white;
  background-color: #FD7E14;
  border-radius: .5rem;
  margin: 1rem;
  margin-top: 5rem;
  padding: 0.7rem;
  font-size: 1.25rem;
  font-weight: bold;
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