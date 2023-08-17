  <template>
    <!-- 레시피 상세 페이지 -->
    <div class="recipeDetail">
      <!-- 레시피 이름 -->
      <div class="recipeName">
        <!-- <h2 class="recipeTitle">레시피이름 : {{recipe_data.name}}</h2> -->
        <div class="recipeTitle">{{recipeDetail.name}}</div>
        <div @click="toggleLike" class="like_container">
          <button v-if="isRecipeLike" class="dislikeButton bi_heart" @click="memberLikeRecipe({type: 'unlike', memberId: this.memberId, recipeId: this.recipeDetail.recipeId })"><i class="bi bi-heart-fill"></i></button>
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

  <div v-if="recipeDetail.recipeUtensils && recipeDetail.recipeUtensils.length > 0">
    <div class="utensilLine menu">
        <div class="utensilName total">조리도구</div>
    </div>

    <div class="utensilContainer">
        <div class="utensilLine" v-for="item,idx in recipeDetail.recipeUtensils" :key="idx">
            <div class="utensilName">
              {{item.name}}
            </div>
        </div>
    </div>
  </div>
  <div v-else>
    <div></div>
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
  <hr/>    
    <!-- 레시피 유사도 재료 추천 -->
  <div class="slider-container">
    <h2 class="orderTitle similarity">유사한 레시피</h2>
    <Carousel :items-to-show="3" :wrap-around="true"
    :autoplay= "3500" :transition = "1000">
        <Slide v-for="item,idx in recipeSimilarity" :key="idx">
            <div class="recommendCard" @click="detailRecipe({recipeId: item.recipeId, memberId: memberId})">
              <img :src="item.imgBig" alt="">
              <p class="card_recipeName">{{ item.name }}</p>
            </div>
        </Slide>

        <!-- 슬라이드 이동 버튼 -->
        <template #addons>
            <Navigation class="arrowButton" />
        </template>
    </Carousel>
    <div class="slider-track">
      
    </div>
  </div>

    </div>
</template>

<script>
import RecipeInfomation from '../recipePage/recipeInfomation/recipeInfomation.vue'
import IngredientInfomation from './recipeInfomation/IngredientInfomation.vue'
import {mapGetters, mapActions} from 'vuex' 
import { Carousel, Navigation, Slide } from 'vue3-carousel'

import 'vue3-carousel/dist/carousel.css'

export default {
    name: 'RecipeDetail',
    components: {
      IngredientInfomation,
      RecipeInfomation,
      Carousel,
      Slide,
      Navigation,
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
      ...mapGetters(['recipeDetail','isRecipeLike','recipeSimilarity']),
      recipeItem() {
        const recipeItem = this.$route.query.recipeItem;
        if (recipeItem) {
          return JSON.parse(recipeItem);
        }
        return null;
      },
    },
    
    methods: {
      goToDetailRecipe(recipeId) {      
        this.$router.push({name: "recipe",  
          params: { 
            recipeId: recipeId
          },
        })
      },

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
    },
    displaySimilarity(similarity) {
      const multipliedSimilarity = similarity * 100;
      const roundedSimilarity = Math.floor(multipliedSimilarity);
      return roundedSimilarity
    },
    },
    created() {
      this.recipeId = parseInt(this.$route.params.recipeId);
      this.memberId = parseInt(sessionStorage.getItem('memberId'))
      this.detailRecipe({recipeId: this.recipeId, memberId: this.memberId})
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
    justify-content: center;
}

/* 레시피 이미지 */
.recipeImage {
    width: 45%;
    height: 32rem;
    border-radius: .5rem;
    border: solid rgb(205, 205, 205) 1.5px;
    margin-right: 5rem;
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

.similarity.similarity {
  margin-left: 1rem;
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
  color: #FD7E14;
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



/* 유사도 카드...수정 필요 */
.recommendCard {
    border-radius: .1rem;
    border: 1px solid rgb(207, 205, 205);
    /* box-shadow: 0 4px 4px -4px black; */
    box-shadow: 0 0 0 1px hsla(212,7%,43%,.32);
    /* border: 1px solid #857f7b; */
    height: 23rem;
    width: 18rem;
    cursor: pointer;
    margin: 1rem;
    font-weight: bold;
    transition: 0.3s;
}

.recommendCard:hover{
    transform: scale(1.05);
}

.recommendCard p {
    /* font-family: 'LINESeedKR-Bd';
    margin-top: 2rem 2rem 0;
    font-size: 2.5rem; */
    margin: auto;
    font-family: 'LINESeedKR-Bd';
    margin-top: 2rem;
    font-size: 2rem;
    word-break: keep-all;
}

img {
    width: 100%;
    height: 50%;
    /* margin-top: .7rem; */
    border-radius: 0.1rem;
}

.card_recipeName {
    margin: auto;
    /* word-break: keep-all; */
    overflow: hidden;
    margin: 3rem 2rem;
    font-size: 2rem;
    font-family: 'LINESeedKR-Bd';
}

.slider-container {
  width: 100%;
  overflow: hidden;
  margin-bottom: 5rem;
}

.slider-track {
  display: flex;
  justify-content: flex-start;
  transition: transform 0.3s ease-in-out;
}

.slide {
  flex: 0 0 300px; /* Adjust the card width as needed */
  margin: 0 10px;
}


/* 조리도구css */
.utensilContainer {
  display: flex;
  flex-wrap: wrap; /* 자식 요소가 한 줄에 모두 나타나지 않을 경우 줄바꿈 */
  /* justify-content: center; */
  /* align-items: center; */
  /* width: ; */
  border: 2px solid #FD7E14;
  border-radius: .7rem;
  margin-bottom: 2rem;
}
.utensilName {
  padding: 0 1rem;
}

.utensilName.total {
  position: absolute;
  font-size: 1.7rem;
  min-width: 60px;
  padding: 0 1rem;
  color: #FD7E14;
  background-color: #fff;
}
.utensilLine.menu {
  margin-bottom: 1.5rem;
}

.utensilLine {
  position: relative;
  margin: .7rem 0;
  min-width: 10rem;
  /* font-family: 'LINESeedKR-Bd'; */
  font-family: 'GangwonEdu_OTFBoldA';
  width: 15%;
  display: flex;
  margin-left: .5rem;
  border-width: 0 2px 0 0;
  border-color: #FD7E14;
  border-style: solid;
  /* margin-right: 1rem; */
  /* margin-bottom: 1rem; */
  /* border: solid rgb(160, 160, 160);
  border-radius: .5rem; */
  /* display: flex; */
}

.no_utensil {
  font-family: 'GangwonEdu_OTFBoldA';
  margin: 1rem auto;
}

</style>