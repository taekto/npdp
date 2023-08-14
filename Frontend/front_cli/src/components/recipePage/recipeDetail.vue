  <template>
    <!-- 레시피 상세 페이지 -->
    <div class="recipeDetail">
      <!-- 레시피 이름 -->
      <div class="recipeName">
        <!-- <h2 class="recipeTitle">레시피이름 : {{recipe_data.name}}</h2> -->
        <p class="recipeTitle">{{recipeDetail.name}}</p>
        <div>
          <button  class="likeButton" @click="memberLikeRecipe({type: 'like', memberId: this.memberId, recipeId: this.recipeDetail.recipeId })">Like</button>
          <button  class="dislikeButton" @click="memberLikeRecipe({type: 'unlike', memberId: this.memberId, recipeId: this.recipeDetail.recipeId })">Dislike</button>
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
    <IngredientInfomation/>
  </div>
      <!-- 레시피 순서 -->
  <div class="recipeOrder">
    <h2 class="orderTitle">레시피 순서</h2>
    <button @click="playAllDescriptions">모든 설명 재생</button>
    <div class="orderLine" v-for="(order, index) in recipeDetail.recipeSequences" :key="index">
      <p class="orderExplain">{{ order.description }}</p>
      <img class="orderImage" :src="order.img">
      <button @click="playTTS(order.description)">TTS 재생</button>
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
          }
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
    margin-top: 2rem;
    margin-left: 2rem;
    margin-bottom: 2rem;
    text-align: start;
    display: flex;
    font-family: 'LINESeedKR-Bd';
    justify-content: space-between;
}

.recipeTitle {
    /* font-weight: bold; */
    margin-right: 2rem;
    font-size: 2.5rem;
}

/* 좋아요 버튼 구분 */
.likeButton {
    border-radius: .5rem;
    background-color: #FD7E14;
    border: solid #FD7E14;
    color: white;
    padding-left: 1rem;
    padding-right: 1rem;
    height: 3rem;
    width: 6rem;
}

.dislikeButton {
    border-radius: .5rem;
    color: #FD7E14;
    border: solid #FD7E14;
    background-color: white;
    padding-left: 1rem;
    padding-right: 1rem;
    height: 3rem;
    width: 6rem;
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
    border-radius: .5rem;
    border: solid rgb(205, 205, 205) 1.5px;
}

</style>