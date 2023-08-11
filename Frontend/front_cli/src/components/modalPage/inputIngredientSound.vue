<template>
  <!-- 재료 음성 입력 모달창 -->
  <div class="modal fade modal-xl" id="exampleModalToggle2" aria-hidden="true" aria-labelledby="exampleModalToggleLabel2" tabindex="-1">
      <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
          <div class="modal-header">
            <h1 class="modal-title fs-5" id="exampleModalToggleLabel2">음성 입력</h1>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>

          <!-- 입력된 재료 리스트 -->
          <div class="modal-body">
            <ul class="ListShow">
              <li class="ingredientList row" v-for="(ingredient, index) in ingredientList" :key="index">
                <p class="col-1">{{ingredient.name}}</p>
                <div class="amount col-2 row">
                  <button class="amountButton col-3" @click="plusAmount(ingredient)">+</button>
                  <p class="col-6">{{ingredient.amount}}{{ingredient.unit}}</p>
                  <button class="amountButton col-3" @click="minusAmount(ingredient)">-</button>
                </div>
                <p class="col-3">보관시작일 : {{ingredient.startDate}}</p>
                <p class="col-3">
                  유통기한 : {{ingredient.endDate}}
                </p>
                <p class="col-2">보관방식 : {{ingredient.storage}}</p>
                <button class="col-1" @click="deleteIngredient(ingredient)">제거</button>
              </li>
            </ul>
          </div>

          <!-- 음성입력을 텍스트로 바꿔주는 부분 -->
          <div class="modal-body">
            <div>
              <button @click="startSpeechRecognition">음성으로 재료 입력</button>
              <p class="output">인식된 재료: {{ recognizedIngredients }}</p>
            </div>
            
            <textarea class="soundToTextarea" name="soundToText" id="" rows="5" v-model="recognizedIngredients"></textarea>
          </div>
          <div class="buttonGroup">
            <button class="modalButton" data-bs-target="#exampleModalToggle" data-bs-toggle="modal">TEXT 입력</button>
            <button class="modalButton" @click="pushSoundInput">추가하기</button>
          </div>

          <!-- 입력된 재료 리스트 저장 -->
          <div class="modal-footer">
            <button class="soundButton" @click="pushIngredientData">저장하기</button>
          </div>
        </div>
      </div>
    </div>
</template>

<script>
export default {
    name: 'IngredientSound',
    data() {
      return {
        ingredientList : [{name : "감자", amount: 2, unit: "개",  startDate : '2023-07-27', endDate: '', storage: '냉장'}, 
        {name : "김치", amount: 2, unit: "포기",  startDate : '2023-07-27', endDate: '', storage: '냉장'},
        {name : "계란", amount: 2, unit: "개",  startDate : '2023-07-27', endDate: '', storage: '냉장'},
        {name : "돼지고기", amount: 600, unit: "g",  startDate : '2023-07-27', endDate: '', storage: '냉장'},
        {name : "소고기", amount: 1200, unit: "g",  startDate : '2023-07-27', endDate: '', storage: '냉장'},],
        soundInput: '',
        recognizedIngredients: '',
      }
    },
    methods: {
      pushSoundInput() {
        console.log(this.soundInput)
        this.soundInput = ''
      },
      pushIngredientData() {
        console.log(this.ingredientList)
        this.ingredientList = []
      },
      plusAmount(ingredient) {
        ingredient.amount ++
      },
      minusAmount(ingredient) {
        ingredient.amount --
        if (ingredient.amount <= 0) {
                const arrayRemove = (arr, value) => {
                    return arr.filter((ele) => {
                        return ele != value
                    })
                }
                this.ingredients = arrayRemove(this.ingredientList, ingredient)
            }
      },
      deleteIngredient(ingredient) {
        const arrayRemove = (arr, value) => {
          return arr.filter((ele) => {
            return ele != value
          })
        }
        this.ingredientList = arrayRemove(this.ingredientList, ingredient)
      },
      startSpeechRecognition() {
        const recognition = new window.webkitSpeechRecognition();
        recognition.lang = "ko-KR";
        recognition.interimResults = false;
        recognition.continuous = false;
        recognition.maxAlternatives = 1;

        recognition.onresult = (event) => {
          const speechResult = event.results[0][0].transcript.toLowerCase();
          console.log("Confidence: " + event.results[0][0].confidence);
          console.log("Speech Result: " + speechResult);
          this.recognizedIngredients = speechResult;
      };

      recognition.onend = () => {
        console.log("SpeechRecognition.onend");
      };

      recognition.start();
    },
    }

        

      
  
  }
  

</script>

<style scoped>
/* 모달 버튼 */
.buttonGroup {
  display: flex;
  justify-content: flex-end;
}

.soundToTextarea {
  width: 100%;
}

.modalButton {
  border: solid #FD7E14;
  color: white;
  background-color: #FD7E14;
  border-radius: .5rem;
  margin: 1rem;
  padding: 0.5rem;
  font-size: 1.25rem;
  font-weight: bold;
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
</style>