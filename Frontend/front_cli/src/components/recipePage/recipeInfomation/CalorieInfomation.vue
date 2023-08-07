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
                <p>{{method.name}}</p>
            </div>
            <div class="ingredientAmount">
                <p>{{method.value}}</p>
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
export default {
    name: "CalorieInformation",
    props: {
        method: Text,
        calorie: Array,
        serving: Number,
    }
}
</script>
