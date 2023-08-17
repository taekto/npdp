<template>
  <!-- 모달창 이동 하게하는 상위 컴퍼넌트 -->
  <div class="modal_container">
    <SeasoningText />
    <SeasoningSound />
    <button class="modalButton" data-bs-target="#seasoningModalToggle" data-bs-toggle="modal">양념등록</button>
  </div>
  
</template>

<script>
import SeasoningText from '../modalPage/SeasoningText'
import SeasoningSound from '../modalPage/SeasoningSound'

export default {
  name : "SeasoningModal",
  components: {
    SeasoningText,
    SeasoningSound,
  },

  
  
    
}
</script>

<style scoped>
/* 모달 버튼 */
.modalButton {
  border: .1rem solid #FD7E14;
  color: #FD7E14;
  background-color: #fff;
  border-radius: .5rem;
  margin: .5rem;
  padding: 0.3rem;
  font-size: 1rem;
  font-weight: bold;
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