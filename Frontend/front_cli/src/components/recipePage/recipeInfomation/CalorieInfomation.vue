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
            calorie : []
        }
    },
    mounted() {
        this.setCalorie()
    },
    computed: {
      ...mapGetters(['recipeDetail'])
    },
    methods: {
        setCalorie() {
            this.calorie = [{name: "칼로리", value: this.recipeDetail.calorie},
            {name: '탄수화물', value: this.recipeDetail.carbohydrate},
            {name: '단백질', value: this.recipeDetail.protein},
            {name: '지방', value: this.recipeDetail.fat},
            {name: '염분', value: this.recipeDetail.salt},
            {name: '중량', value: this.recipeDetail.weight},
            ]
        }
    }
}
</script>
