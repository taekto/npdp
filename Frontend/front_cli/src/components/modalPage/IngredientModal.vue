<template>
  <!-- 재료 입력 모달 상위 컴포넌트 -->
  <div class="modal_container">
    <IngredientText />
    <IngredientSound />
    <button class="modalButton" data-bs-target="#exampleModalToggle" data-bs-toggle="modal" data-bs-whatever="@ingredient">재료등록</button>
  </div>
  
</template>

<script>
import IngredientText from '../modalPage/inputIngredientText.vue'
import IngredientSound from '../modalPage/inputIngredientSound.vue'

export default {
  name : "IngredientModal",
  components: {
    IngredientText,
    IngredientSound,
  },

  
  
    
}
</script>

<style scoped>
/* 모달버튼 */
.modalButton {
  border: .1rem solid #FD7E14;
  color: #FD7E14;
  background-color: #fff;
  border-radius: .5rem;
  margin: .5rem;
  padding: 0.3rem;
  font-size: 1rem;
  font-weight: bold;
  /* min-width: 5rem; */
  /* font-family: 'LINESeedKR-Bd'; */
  font-family: 'LINESeedKR-Bd';
}

.amountButton {
  width: 2rem;
  height: 60%;
  border-radius: .5rem;
  background-color: #f2f2f2;
  border: solid rgb(244, 244, 244);
}

.ListShow {
  border-bottom: solid #a7a7a7;
  width: 95%;
  margin: auto;
}

.ingredientList {
  display: flex;
  border-bottom: solid grey;
  
}

.modal-body {
  display: flex;
  margin: auto;
  width: 100%;
}


.amount {
  display: flex;
}

.inputComponent {
  justify-content: space-around;
}

.radioButton {
    border: solid #FD7E14;
    border-radius: .5rem;
    padding: .5rem;
    margin: .5rem;
    width: 6rem;
}

[type="radio"] {
    appearance: none;
}

#searchForm {
    border-radius: .5rem;
    margin-right: 1rem;
    margin-left: 1rem;
}

#ingredientText {
  width: 10rem;
}

#submitButton {
    width: 5rem;
    border-radius: .5rem;
    background-color: #f2f2f2;
    border: solid rgb(244, 244, 244);
}

.servingButton {
  border: solid #FD7E14;
  background-color: white;
  border-radius: .5rem;
  width: 4rem;
  padding: 0.25rem;
  font-size: 1rem;
  font-weight: bold;
  text-align: center;
}

.soundButton {
  border: solid #FD7E14;
  color: white;
  background-color: #FD7E14;
  border-radius: .5rem;
  margin: 1rem;
  padding: 0.5rem;
  font-size: 1.25rem;
  font-weight: bold;
}


/* .modal {
  width: 75%;
} */
/* 
.modal-content {
  width: 75%;
} */

</style>