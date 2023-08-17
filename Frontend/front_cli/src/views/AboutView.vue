<template>
  <div class="about">

    <div class="info">

      <div class="carousel_one">
        <div class="info_text">
          <h2 class="info_text_title">'오늘 뭐 먹지' 고민될 땐, <br> <b>냉파대파</b>를 이용해 보세요.</h2>
          <div class="info_text_detail">B202의 프로젝트 냉파대파는 <br>"냉장고에서 잊혀져 버려지는 재료들이 아깝다"는 생각에서 시작되었습니다.<br>
          사용자의 냉장고에 있는 재료들을 기록하고 관리하여 낭비되지 않게 하고, <br> 레시피를 추천함으로써 더욱 풍성한 요리 생활을 즐길 수 있게 합니다.
          </div>
        </div>
      </div>
        
    </div>

    <div class="history">
  
      <div>
        <h5>2023.7.4</h5>
        <ol>
          <li>SSAFY 2학기 공통 프로젝트 시작</li>
          <li>팀명 싸피 아이(I)들으로 정함</li>
        </ol>
      </div>

      <div>
        <h5>2023.7.10</h5>
        <ol>
          <li>주제 선정 시작</li>
        </ol>
      </div>

      <div>
        <h5>2023.07.17</h5>
        <ol>
          <li>기획 시작</li>
          <li>ERD, 인터페이스 설계서 등 산출물 생성</li>
        </ol>
      </div>
      
      <div>
        <h5>2023.7.31</h5>
        <ol>
          <li>개발 시작</li>
        </ol>
      </div>
      
      <div>
        <h5>2023.8.17</h5>
        <ol>
          <li>냉파대파 프로젝트 완성</li>
          
        </ol>
      </div>
      
    </div>

  </div>
</template>

<style scoped>
img {
  width: 100%;
  height: 45rem;
}

* {
  list-style: none;
}
.info_text_title {
  font-family: 'LINESeedKR-Bd';
}

.info_text_detail {
  font-family: 'LINESeedKR-Rg';
}

.history{
  width: 50%;
  margin: auto;
  padding-top: 50px;
  padding-bottom: 50px;
}

.history h5 {
  position: relative; 
  font-size: 25px; 
  color: #000; 
  line-height: 1;
}

.history h5:before {
  content:""; 
  position: absolute;
  left:-48px; 
  top:50%; 
  transform: translateY(-50%);
  width: 17px; 
  height: 17px; 
  border-radius: 100%;
  background:#fff; 
  border: 5px solid #ff5a2b;
  box-sizing: border-box;
}

.history div {
  position: relative; 
  padding: 0 0 0 48px;
}

.history div:before { 
  content:""; 
  position:absolute; 
  left:8px; 
  top:0;
  width: 2px; 
  height:100%; 
  background:#ddd;
}

.history div:first-child:before { 
  top:10px; 
  height:calc(100% - 10px);
}

.history ol { 
  padding: 20px 0 74px; 
  margin-bottom: 0px; 
}

.history li {
  font-size: 17px; 
  color:#000; 
  line-height: 29px;  
}

.carousel_one {
    display: block;
    flex-direction: column;
    width: 100vw;
    height: 70vh;
    background: url("@/assets/mainImage01.jpg") center;
    background-size: cover;
    /* text-align: center; */
    position: relative;
    /* justify-content: center; */
    /* align-items: center; */
    z-index: 1;
}


.carousel_one::after{
    content: "";
    width: 100%;
    height: 100%;
    background-color: black;
    position: absolute;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    z-index: -1;
    opacity: 0.3;
}

.info_text {
  color: white;
  padding-left: 10rem;
  text-align: left;
}

.info_text > h2 {
  font-size: 50px;
  padding-top: 5rem;
}

.info_text p {
  font-size: 20px;
  padding-top: 5rem;
  
}

</style>


