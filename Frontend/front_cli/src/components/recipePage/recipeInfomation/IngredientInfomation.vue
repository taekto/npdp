<template>
  <!-- 레시피 재료 정보 박스 -->
  <div class="ingredientAllContainer">
    <!-- 각 컬럼이 무엇을 뜻하는 지 설명 -->
    <div class="ingredientLine menu">
      <div class="ingredientName total">재료</div>
    </div>
    <!-- 재료 정보 -->
    <div class="ingredientContainer">
      <div class="ingredientLine" v-for="(ingredient, index) in recipeDetail.recipeIngredients" :key="index">
          <div class="ingredientName each">
            <div>{{ingredient.kor}}</div>
          </div>
          <div class="ingredientAmount each">
            <!-- <div>{{(ingredient.amount * serving).toFixed(1)}}</div> -->
            <div>{{ingredient.amount}}</div>
            <div>{{ingredient.unit}}</div>
          </div>
      </div>
    </div>
    
    <div class="seasoning_containter" v-if="recipeDetail.recipeSeasonings && recipeDetail.recipeSeasonings.length > 0">
      <div class="ingredientLine menu">
        <div class="ingredientName total">양념</div>
      </div>

      <div class="ingredientContainer">
        <div class="ingredientLine" v-for="(ingredient, index) in recipeDetail.recipeSeasonings" :key="index">
            <div class="ingredientName each">
              <div>{{ingredient.kor}}</div>
            </div>
            <div class="ingredientAmount each">
              <div>{{ingredient.amount}}</div>
              <div>{{ingredient.unit}}</div>
            </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import {mapGetters} from 'vuex'

export default {
    name: 'IngredientInfomation',
    props: {
      serving : Number
    },
    data() {
      return {
        calculatedValues: [],
      }
    },
    computed: {
      ...mapGetters(['recipeDetail'])
    },
    watch: {
      recipeDetail: {
        immediate: true, // 처음에도 즉시 호출
        handler() {
          this.recipeDetail
          // this.calculateValues();
        },
      },
      // 'ingredient.amount': 'calculateValue'
    },
    methods: {
    //   calculateValues() {
    //     this.calculatedValues = this.recipeDetail.recipeIngredients.map(ingredient => {
          
        
    //       const parsedValue = parseFloat(ingredient.amount);
    //       const calculatedValue = {
    //         kor: ingredient.kor,
    //         value: '',
    //         unit: ingredient.unit,
    //       }
    //       if (!isNaN(parsedValue)) {
    //         calculatedValue.value = (parsedValue * this.serving).toFixed(1);
    //       } else {
    //         calculatedValue.value = ingredient.amount;
    //       }
    //       return calculatedValue;
    //   })
    // },
    calculateValues() {
      if(this.recipeDetail.recipeIngredients) {
        for(let ingredient of this.recipeDetail.recipeIngredients) {
        const parsedValue = this.parseAmount(ingredient.amount);
        const calculatedValue = {
          kor: ingredient.kor,
          value: '',
          unit: ingredient.unit,
        };
        if (!isNaN(parsedValue)) {
          calculatedValue.value = (parsedValue * this.serving).toFixed(1)
        } else {
          calculatedValue.value = ingredient.amount
        }
        this.calculatedValues.push(calculatedValue)
      }
    //   this.calculatedValues = this.recipeDetail.recipeIngredients.map(ingredient => {
    //   const parsedValue = this.parseAmount(ingredient.amount);
    //   const calculatedValue = {
    //     kor: ingredient.kor,
    //     value: '',
    //     unit: ingredient.unit,
    //   };
    //   if (!isNaN(parsedValue)) {
    //     calculatedValue.value = (parsedValue * this.serving).toFixed(1);
    //   } else {
    //     calculatedValue.value = ingredient.amount;
    //   }
    //   return calculatedValue;
    // });
      }
      
  },

    // parseAmount(amount) {
    //   const fractionRegex = /^(\d+)\s?\/\s?(\d+)$/; // 분수 형식 정규식
    //   const match = amount.match(fractionRegex);
    //   if (match) {
    //     const numerator = parseFloat(match[1]);
    //     const denominator = parseFloat(match[2]);
    //     if (!isNaN(numerator) && !isNaN(denominator) && denominator !== 0) {
    //       return numerator / denominator; // 분수 계산
    //     }
    //   } else if (!isNaN(parseFloat(amount))) {
    //     return parseFloat(amount); // 숫자로 직접 변환
    //   }
    //   return amount; // 변환할 수 없는 경우 원래 문자열 반환
    // },


    },
}
</script>

<style scoped>
.ingredientContainer {
  display: flex;
  flex-wrap: wrap; /* 자식 요소가 한 줄에 모두 나타나지 않을 경우 줄바꿈 */
  /* justify-content: center; */
  /* align-items: center; */
  /* width: ; */
  border: 2px solid #FD7E14;
  border-radius: .7rem;
}

.ingredientName.total {
  position: absolute;
  font-size: 1.7rem;
  min-width: 60px;
  color: #FD7E14;
  background-color: #fff;
}
.ingredientLine.menu {
  margin-bottom: 1.5rem;
}

.ingredientLine {
  position: relative;
  margin: .7rem 0;
  min-width: 10rem;
  /* font-family: 'LINESeedKR-Bd'; */
  font-family: 'GangwonEdu_OTFBoldA';
  width: 15%;
  display: flex;
  margin-left: .5rem;
  border-width: 0 2px 0 0;
  border-color: #FD7E14;
  border-style: solid;
  /* margin-right: 1rem; */
  /* margin-bottom: 1rem; */
  /* border: solid rgb(160, 160, 160);
  border-radius: .5rem; */
  /* display: flex; */
}

</style>
