<template>
  <div class="modal fade modal-xl" id="seasoningModalToggle2" aria-hidden="true" aria-labelledby="exampleModalToggleLabel2" tabindex="-1">
      <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
          <div class="modal-header">
            <h1 class="modal-title fs-5" id="exampleModalToggleLabel2">음성 입력</h1>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
            <ul class="ListShow">
              <li class="ingredientList row" v-for="(seasoning, index) in seasoningList" :key="index">
                <p class="col-1">{{seasoning.name}}</p>
                <div class="amount col-2 row">
                  <button class="amountButton col-3">+</button>
                  <p class="col-6">{{seasoning.amount}}{{seasoning.unit}}</p>
                  <button class="amountButton col-3">-</button>
                </div>
                <p class="col-3">보관시작일 : {{seasoning.startDate}}</p>
                <p class="col-3">
                  유통기한 : {{seasoning.endDate}}
                </p>
                <p class="col-2">보관방식 : {{seasoning.storage}}</p>
                <button class="col-1" @click="deleteSeasoning(seasoning)">제거</button>
              </li>
            </ul>
          </div>
          <div class="modal-body">
            <textarea class="soundToTextarea" name="soundToText" id="" rows="5" v-model="soundInput"></textarea>
          </div>
          <div class="buttonGroup">
            <button class="modalButton" data-bs-target="#exampleModalToggle" data-bs-toggle="modal">TEXT 입력</button>
            <button class="modalButton" @click="pushSoundInput">추가하기</button>
          </div>
          <div class="modal-footer">
            <button class="soundButton" @click="pushSeasoningData">저장하기</button>
          </div>
        </div>
      </div>
    </div>
</template>

<script>
export default {
    name: 'SeasoningSound',
    data() {
      return {
        seasoningList : [{name : "고춧가루", startDate : '2023-07-27', endDate: '', storage: '냉장'}, 
        {name : "간장",  startDate : '2023-07-27', endDate: '', storage: '냉장'},
        {name : "고추장",  startDate : '2023-07-27', endDate: '', storage: '냉장'},
        {name : "된장",  startDate : '2023-07-27', endDate: '', storage: '냉장'},],
        soundInput : '',
      }
    },
    methods: {
      pushSoundInput() {
        console.log(this.soundInput)
        this.soundInput = ''
      },
      pushSeasoningData() {
        console.log(this.SeasoningList)
        this.SeasoningList = []
      },
      deleteSeasoning(seasoning) {
            const arrayRemove = (arr, value) => {
                return arr.filter((ele) => {
                    return ele != value
                })
            }
            this.seasoningList = arrayRemove(this.seasoningList, seasoning)
        }
    }

}
</script>

<style scoped>
.buttonGroup {
  display: flex;
  justify-content: end;
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