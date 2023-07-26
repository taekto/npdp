<template>
    <div class="tempBox">
        <div class="oneLine menu">
            <div class="ingredientName">
            <p>재료명</p>
            </div>
            <div class="ingredientAmount">
            <p>양</p>
            <p>/단위</p>
            </div>
        </div>
        <div class="oneLine" v-for="(ingredient, index) in ingredients" :key="index">
            <div class="ingredientName">
            <p>{{ingredient.name}}</p>
            </div>
            <div class="ingredientAmount">
            <p>{{ingredient.amount * serving}}</p>
            <p>{{ingredient.unit}}</p>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: 'IngredientInfomation',
    props: {
        ingredients: Array,
        serving: Number,
    }
    // data() {
    //   return {
    //     ingredients : [{ name : '감자', amount : 2, unit : '개'}, { name : '김치', amount : 1/4, unit : '포기'}, 
    //     { name : '설탕', amount : 50, unit : 'g'}, { name : '멸치', amount : 2, unit : '개'}, { name : '밀가루', amount : 150, unit : 'g'}]
    //   }
    // }

}
</script>

<style scoped>
.menu {
  font-size: 17.5px;
  font-weight: bold;
  margin-bottom: 1rem;
}

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

</style>