<template>
    <!-- 영양소 박스 -->
    <div class="calorie_container">
        <div class="oneLine menu">
            <div class="ingredientName">
                <p>영양소</p>
            </div>
            <div class="ingredientAmount">
            <p>Calorie</p>
            </div>
        </div>
        <div class="oneLine" v-for="(calorieInfo, index) in calorie" :key="index">
            <div class="ingredientName">
                <p>{{calorieInfo.name}}</p>
            </div>
            <div class="ingredientAmount">
                <p>{{calorieInfo.value}}</p>
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
            calorie : [],
            way : ''
        }
    },
    mounted() {
        this.setWay()
        this.setCalorie()
    },
    computed: {
      ...mapGetters(['recipeDetail', 'recipeWay'])
    },
    watch: {
        recipeDetail: {
            immediate: true, // 처음에도 즉시 호출
            handler() {
                this.setCalorie();
                this.setWay();
            },
        }
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
        },
        setWay() {
            for (let recipeWay_data of this.recipeWay) {
                if(recipeWay_data.recipe_way_id === this.recipeDetail.way) {
                    this.way = recipeWay_data.recipe_way_name
                }
            }
        }
    }
}
</script>

<style scoped>
.oneLine {
  font-family: 'LINESeedKR-Bd';
}
.oneLine.menu.way {
    margin-top: 2rem;
}
</style>