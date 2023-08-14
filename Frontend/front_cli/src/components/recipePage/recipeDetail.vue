  <template>
    <!-- 레시피 상세 페이지 -->
    <div class="recipeDetail">
      <!-- 레시피 이름 -->
      <div class="recipeName">
        <!-- <h2 class="recipeTitle">레시피이름 : {{recipe_data.name}}</h2> -->
        <div class="recipeTitle">{{recipeDetail.name}}</div>
        <div @click="toggleLike" class="like_container">
          <button v-if="liked" class="dislikeButton bi_heart" @click="memberLikeRecipe({type: 'unlike', memberId: this.memberId, recipeId: this.recipeDetail.recipeId })"><i class="bi bi-heart-fill"></i></button>
          <button v-else class="likeButton bi_heart" @click="memberLikeRecipe({type: 'like', memberId: this.memberId, recipeId: this.recipeDetail.recipeId })"><i class="bi bi-heart"></i></button>
        </div>
      </div>

      <div class="recipeInfomation">
        <!-- 레시피 이미지 -->
        <div class="recipeImage">
          <img class="recipeImg" :src='recipeDetail.imgBig'>
        </div>
        
        <!-- 레시피 정보 -->
        <RecipeInfomation />
      </div>
  <div>
    <IngredientInfomation :serving='serving'/>
  </div>
      <!-- 레시피 순서 -->
  <div class="recipeOrder">
    <h2 class="orderTitle">레시피 순서</h2>
    <button @click="playAllDescriptions" class="all_replay"><i class="bi bi-volume-up-fill">click!</i></button>
    <div class="orderLine" v-for="(order, index) in recipeDetail.recipeSequences" :key="index">
      <p class="orderExplain">{{ order.description }}
        <!-- <button @click="playTTS(order.description)" class="replay_button"><i class="bi bi-volume-up-fill"></i></button> -->
      </p>
      <img class="orderImage" :src="order.img">
    </div>
  </div>
      
    </div>
</template>

<script>
import RecipeInfomation from '../recipePage/recipeInfomation/recipeInfomation.vue'
import IngredientInfomation from './recipeInfomation/IngredientInfomation.vue'
import {mapGetters, mapActions} from 'vuex' 

export default {
    name: 'RecipeDetail',
    components: {
      IngredientInfomation,
      RecipeInfomation,
    },
    data() {
      return {
        recipeId: null,
        liked : false,
        memberId: null,
        serving: 1,
      }
    },
    
    computed: {
      recipeItem() {
        console.log(this.$route.params.recipeItem)
        const recipeItem = this.$route.query.recipeItem;
        if (recipeItem) {
          return JSON.parse(recipeItem);
        }
        return null;
      },
      ...mapGetters(['recipeDetail'])
    },
    
    methods: {
      toggleLike(){
        this.liked = !this.liked;
      },

      servingUpdate(serving) {
        this.serving = serving
      },

      ...mapActions(['memberLikeRecipe','detailRecipe']),
      
          playTTS(text) {
      const apiKey = 'AIzaSyCN8qg_05_pSKpv6wRKwKyUfVfEAOC-uaA'; // Google Text-to-Speech API 키를 여기에 넣으세요.
      const url = `https://texttospeech.googleapis.com/v1/text:synthesize?key=${apiKey}`;
      const data = {
        input: {
          text: text,
        },
        voice: {
          languageCode: 'ko-KR',
          name: 'ko-KR-Neural2-c',
          ssmlGender: 'MALE',
        },
        audioConfig: {
          audioEncoding: 'MP3',
        },
      };
      const otherparam = {
        headers: {
          'content-type': 'application/json; charset=UTF-8',
        },
        body: JSON.stringify(data),
        method: 'POST',
      };

      let delayTime = 0; // 초기 지연 시간

      fetch(url, otherparam)
        .then((data) => {
          console.log(data);
          return data.json();
        })
        .then((res) => {
          if (res.audioContent) {
            const audioData = res.audioContent;
            const audioBlob = new Blob([new Uint8Array(atob(audioData).split('').map((c) => c.charCodeAt(0)))], {
              type: 'audio/mp3',
            });
            const audioUrl = URL.createObjectURL(audioBlob);
            const audio = new Audio(audioUrl);
            
            audio.play();

            if (text.includes('.')) {
              this.delayTime = 5000; // 특정 특수 기호(. - 마침표)가 나올 때 5초로 지연 설정
            }
          }
        })
        .then(() => {
          return this.delay(delayTime); // 특정 조건에 따른 지연 시간 적용
        })
        .catch((error) => {
          console.log(error);
        });
    },
    playAllDescriptions() {
      const allDescriptions = this.recipeDetail.recipeSequences.map(order => order.description);
      const combinedText = allDescriptions.join(' '); // 본문 전체 텍스트를 결합하여 재생

      this.playTTS(combinedText);
    },
    delay(ms) {
      return new Promise(resolve => setTimeout(resolve, ms));
    }

    },
    created() {
      this.recipeId = parseInt(this.$route.params.recipe_id);
      this.memberId = parseInt(sessionStorage.getItem('memberId'))
      this.detailRecipe(this.recipeId)
    },
}
</script>

<style scoped>
/* 레시피 상세 */
.recipeDetail {
    width: 75%;
    margin-top: 2rem;
    margin: auto;
}

/* 레시피 이름 */
.recipeName {
    display: flex;
    margin-top: 2rem;
    margin-left: 2rem;
    margin-bottom: 2rem;
    text-align: start;
    display: flex;
    font-family: 'LINESeedKR-Bd';
    /* justify-content: space-between; */
}

.recipeTitle {
    /* font-weight: bold; */
    margin-right: .3rem;
    font-size: 2.5rem;
}

/* 좋아요 버튼 구분 */
.like_container {
  position: relative;
}

.likeButton {
    top: .5rem;
    position: absolute;
    border-radius: .5rem;
    background-color: #fff;
    /* border: solid #FD7E14; */
    border: none;
    color: #FD7E14;
    padding-left: 1rem;
    padding-right: 1rem;
    /* height: 3rem; */
    /* width: 7rem; */
}

.dislikeButton {
    top: .5rem;
    position: absolute;
    border-radius: .5rem;
    /* color: #FD7E14; */
    /* border: solid #FD7E14; */
    color: #FD7E14;
    border: none;
    background-color: white;
    padding-left: 1rem;
    padding-right: 1rem;
    /* height: 3rem; */
    /* width: 6rem; */
}

.bi_heart {
  font-size: 2rem;
}

/* 레시피 이미지 & 레시피 정보 */
.recipeInfomation {
    display: flex;
    justify-content: space-around;
}

/* 레시피 이미지 */
.recipeImage {
    width: 45%;
    height: 30rem;
    border-radius: .5rem;
    border: solid rgb(205, 205, 205) 1.5px;
}

.recipeImg {
  width: 100%;
  height: 100%;
  border-radius: .5rem;
}


/* 레시피 순서 */
.recipeOrder {
  position: relative;
    margin-top: 3rem;
    font-family: 'LINESeedKR-Bd';
    font-size: 1.25rem;
    word-break: keep-all;
    margin-bottom: 10rem;
}
/* 레시피 순서 타이틀 */
.orderTitle {
    text-align: start;
    /* margin-left: 2rem; */
    margin-top: 5rem;
    margin-bottom: 3rem;
    font-weight: bold;
}

.all_replay {
  font-family: 'GangwonEdu_OTFBoldA';
  position: absolute;
  top: 0;
  left: 10rem;
  /* border: 1.7px solid #FD7E14; */
  border: none;
  border-radius: .8rem;
  padding: .3rem .6rem;
  background-color: #fff;
  /* color: #FD7E14; */
  font-size: 1.5rem;
  font-style: normal;
}

.all_replay:hover {
  color: #FD7E14;
}

.all_replay:focus {
  color: #FD7E14;
}


.replay_button {
  font-family: 'GangwonEdu_OTFBoldA';
  /* border: 1.7px solid #FD7E14; */
  border: none;
  background-color: #fff;
  color: #000000;
  margin-left: .5rem;
}

/* 레시피 순서 각 컴포넌트 */
.orderLine {
    display: flex;
    justify-content: space-around;
    margin-bottom: 3rem;
}

/* 순서 설명 */
.orderExplain {
    width: 45%;
    /* margin: 2rem; */
    margin: auto;
    margin-top: 0px;
    margin-bottom: 0px;
    padding: 2rem;
    /* border: solid rgb(205, 205, 205); */
    border-radius: .5rem;
    word-break: keep-all;
    font-size: 1.3rem;
    display: flex;
    align-items: center;
}

/* 순서 관련 이미지 */
.orderImage{
    width: 35%;
    min-width: 270px;
    min-height: 175px;
    border-radius: .5rem;
    border: solid rgb(205, 205, 205) 1.5px;
}

</style>