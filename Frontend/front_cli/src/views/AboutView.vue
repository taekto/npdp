<template>
  <div class="about">
    <div class="Info">
      <h3>안녕하세요!</h3>
      <p>B202의 프로젝트 냉파대파는 "냉장고에서 잊혀져 버려지는 음식물들이 아깝다" 라는 생각에서 시작되었습니다.
        <br>
      </p>
      
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

<style>
* {
  list-style: none;
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



</style>
