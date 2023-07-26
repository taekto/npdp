<template>
  <div class="tempBox">
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

<style scoped>
.menu {
  font-size: 17.5px;
  font-weight: bold;
  margin-bottom: 1rem;
}

.oneLine {
  display: flex;
  justify-content: space-around;
  justify-items: center;
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