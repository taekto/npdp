<template>
    <!-- 영양소 박스 -->
    <div>
        <div class="oneLine menu">
            <div class="ingredientName">
                <p>영양소명</p>
            </div>
            <div class="ingredientAmount">
            <p>Calorie</p>
            </div>
        </div>
        <div class="oneLine">
            <div class="ingredientName">
                <p>{{recipeDetail.way}}</p>
            </div>
            <div class="ingredientAmount">
                <p>{{recipeDetail.way}}</p>
            </div>
        </div>
        <div class="oneLine" v-for="(calorieInfo, index) in calorie" :key="index">
            <div class="ingredientName">
                <p>{{calorieInfo.name}}</p>
            </div>
            <div class="ingredientAmount">
                <p>{{calorieInfo.value*serving}}</p>
            </div>
        </div>
        </div>
</template>

<script>
import {mapGetters} from 'vuex'

export default {
    name: "CalorieInformation",
    props: {
        serving: Number,
    },
    data() {
        return {
            calorie : [{name: "calorie", value: this.recipeDetail.calorie},
            {name: 'carbohydrate'},
            ]
        }
    },
    computed: {
      ...mapGetters(['recipeDetail'])
    },
}
</script>
