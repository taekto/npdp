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
        <h5 class="date">2023.7.4</h5>
        <ol>
          <li style="font-size: 20px;">SSAFY 2학기 공통 프로젝트 시작</li>
          <li style="font-size: 13px; color: #8c8c8c;">팀명 싸피 아이(I)들으로 정함</li>
        </ol>
      </div>

      <div>
        <h5 class="date">2023.7.10</h5>
        <ol>
          <li style="font-size: 20px;">주제 선정 및 기획 시작</li>
        </ol>
      </div>

      <div>
        <h5 class="date">2023.07.17</h5>
        <ol>
          <li style="font-size: 20px;">산출물 생성</li>
          <li style="font-size: 12px; color: #8c8c8c;">요구사항 명세서</li>
          <li style="font-size: 12px; color: #8c8c8c;">화면 설계</li>
          <li style="font-size: 12px; color: #8c8c8c;">ERD 다이어그램</li>
          <li style="font-size: 12px; color: #8c8c8c;">인터페이스 명세서</li>
        </ol>
      </div>
      
      <div>
        <h5 class="date">2023.7.24</h5>
        <ol>
          <li style="font-size: 20px;">1000+개의 레시피 데이터 전처리</li>
          <li style="font-size: 12px; color: #8c8c8c;">레시피 주재료/부재료/요리순서 분리</li>
          <li style="font-size: 12px; color: #8c8c8c;">레시피 속 재료/양념 매핑</li>
          <li style="font-size: 12px; color: #8c8c8c;">재료 및 양념 구분</li>
          <li style="font-size: 12px; color: #8c8c8c;">알러지 데이터 수집 및 분류</li>
          <li style="font-size: 12px; color: #8c8c8c;">조리도구 데이터 수집 및 분류</li>
        </ol>
      </div>

      <div>
        <h5 class="date">2023.7.31</h5>
        <ol>
          <li style="font-size: 20px;">데이터 전처리 병행 및 개발 시작</li>
        </ol>
      </div>
      
      <div>
        <h5 class="date">2023.8.17</h5>
        <ol>
          <li style="font-size: 20px;">냉파대파 프로젝트 완성</li>
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
  /* font-family: 'LINESeedKR-Bd'; */
  font-family: 'LINESeedKR-Rg';
}
.info_text_title {
  position: absolute;
  top: 3rem;
  font-family: 'LINESeedKR-Bd';
}

.info_text_detail {
  position: absolute;
  top: 17rem;
  font-family: 'LINESeedKR-Rg';
}

.history{
  width: 50%;
  margin: auto;
  padding-top: 10rem;
  padding-bottom: 10rem;
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
  position: relative;
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
.date {
  color: #ff5a2b;
}

</style>


