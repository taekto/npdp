# 냉파대파 (냉장고 파먹기 대차게 파먹기)


## 프로젝트 개요

2023.07.04 ~ 2023.08.18

## 목차

1. [프로젝트 기획 배경](#프로젝트-기획-배경)
2. [프로젝트 목표](#프로젝트-목표)
3. [성과](#성과)
4. [주요기능](#주요기능)
5. [서비스 화면](#서비스-화면)
6. [기술차별점](#기술차별점)
7. [확장가능성](#확장가능성)
8. [홍보](#홍보)
9. [개발환경](#개발환경)
10. [프로젝트 구조](#프로젝트-구조)
11. [서비스 아키텍쳐](#서비스-아키텍쳐)
12. [협업환경](#협업-환경)
13. [팀원](#팀원)
14. [프로젝트 산출물](#프로젝트-산출물)
15. [프로젝트 발표자료](#프로젝트-발표자료)
16. [프로젝트 UCC](#프로젝트-UCC)

## 프로젝트 기획 배경




## 프로젝트 목표





## 성과



<br/>

## 주요기능

** **

- 
- 


** **

- 
- 


** **

- 
- 


** **
- 
- 


** **

- 
- 
- 

** **
- 

** **
- 

** **
- 

** **
- 

<br/>

## 서비스 화면



<br/>

### 개인 책상 


** **


<img src="img/.gif" height="700px">

- 
- 

** **

<img src="img/.gif" height="700px" >

- 
- 

** **

<img src="img/.gif" height="700px" >
<img src="img/.gif" height="700px" >

- 
- 
- 


** **

<img src="img/.gif" height="700px" >
<img src="img/.gif" height="700px" >

- 
- 
-  


** **

<img src="img/.gif" height="700px" >

- 

** **

<img src="img/.gif" height="700px" >

- 
- 

<br/>

### 

** **

<img src="img/.gif" height="700px" >

- 
- 

** **

<img src="img/.gif" height="700px" >

- 
- 

<br/>

### 

** **

<img src="img/.gif" height="700px" >

- 

** **

<img src="img/.gif" height="700px" >

- 

<br/>


## 기술차별점



1. 
2. 
3. 

### 

** **

- 
  <img src="img/.PNG" >

  - 
    - 
  - 
    - 
  - 
    - 

- 
  - 
    - 
  - 
    - 
  - 
    <p><img src="img/.PNG" ></p>
    - 
    - 


** **

- 


### 안전한 데이터 
- 암호화
  - 



### 필터링
- 
  - 
- 
  - 


## 

![](img/.PNG)

## 개발환경

- AWS
    - ubuntu - 20.04
    - nginx - 1.18.0
    - docker - 24.0.6
    - docker-compose - 2.21.0
    - Openjdk - 11.0.20.1
    - S3
- BackEnd
    - Java - 11
    - springboot - 2.7.17
    - JPA
- DB
    - MySQL
- FrontEnd
    - node - 18.13
    - nginx - 1.25.3
    - react
- IDE
    - VS code
    - intelliJ IDEA
- 외부 활용 기능
    - 카카오 API


<br/>

## 프로젝트 구조

### Frontend (React)

```
client
├── src
│   ├── apis
│   │   └── 
│   ├── common
│   │   └── Header
│   ├── fonts
│   ├── Modal
│   │   ├── 
│   │   ├── 
│   │   └── 
│   ├── pages
│   │   ├── 
│   │   ├── 
│   │   ├── 
│   │   ├── 
│   │   ├── 
│   │   ├── 
│   │   ├── 
│   │   └── 
│   ├── APP
│   ├── 
│   ├── reportWebVitals
│   └── RouteChangeTracker
├── env
├── package.json
```
<br/>

### Backend (Spring Boot)

```
user
├── domain
│   ├── 
│   ├── 
│   ├── 
│   ├── 
│   ├── 
│   └── 
├── 
│   └── model
│       └── repository
├── 
│   ├── controller
│   └── model
│       ├── dto
│       ├── repository
│       └── service
├── util
│   ├── AES256
│   ├── 
│   └── JwtUtil
```

<br/>

## 와이어프레임

![wireframe](./img/.png)
<br/>

## ERD

![erd](./img/.png)

<br/>

## 서비스 아키텍쳐

![architecture](./img/.png)

<br/>

## 협업 환경

### Git으로 협업하기


브랜치는 develop, develop-test, develop-be, develop-fe, feature를 사용했으며 전략은 다음과 같습니다.

- `develop`: 서비스가 배포될 수 있는 브랜치입니다. develop 브랜치에 올라온 기능들은 에러 없이 작동하는 상태입니다.

- `develop-be`, `develop-fe` : 기능 개발이 완료된 브랜치를 병합하여 각 fontend와 backend 환경에서 실제 기능이 정상적으로 수행되는지 테스트를 위한 브랜치 입니다.

- `feature`: 기능 단위 개발을 위한 브랜치로 develop에서 분기하여 개발이 끝나면 각각 베이스 브랜치로 병합됩니다.


<br/>

### Jira로 협업하기

매주 월요일 스프린트 회의를 통해 그 주의 목표를 세우고 목표 달성을 위한 구체적인 작업들을 정리했습니다.
팀 회의와 같은 공통적인 일정부터 파트별 회의, 개인 개발 작업까지 구체적으로 계획했습니다.

이를 위해 사용된 요소들은 다음과 같습니다.

`에픽`: 어떤 작업이 속하는 레벨로 공통(설계, 회의), 백엔드, 프론트, 인프라 총 4가지 에픽을 만들어 사용하였습니다.

`스토리`: 에픽에 속하는 작업의 단위입니다. 구체적인 작업 내용을 작성하고 스토리 포인트로 예상 소요 시간을 산정할 수 있습니다. 한 스토리 당 최대 4시간을 넘지 않게 하였고 개인별로 매주 40시간 이상 할당했습니다.

`번다운 차트`: 스프린트의 목표를 달성하기 위해 남은 시간과 남은 스토리 포인트를 확인해 프로젝트의 진척도를 파악할 수 있는 지표입니다.

<br/>

### Notion으로 협업하기

컨벤션, 개발 노트, 미팅, 자료함 등을 Notion을 통해 작성 및 관리하였습니다.


- `컨벤션`: 프로젝트의 컨벤션들을 문서화하여 모두가 공유 가능하도록 하였습니다. Git Branch 컨벤션, Commit 컨벤션이 있습니다.  
- `개발 노트`: 서버 환경 설정과 설치 과정, 개발하면서 만난 오류와 문제 상황을 정리하고 원인과 해결방법을 정리하였습니다.
- `미팅`: 아이디어 회의, 기획 회의, 개발 회의 등을 회의록으로 기록하였습니다.
- `자료함`: 요구사항 정의서, 기능명세서, API 명세서 등 공유 문서를 기록하여 모두가 동일한 목표를 가지고 개발 할 수 있도록 하였습니다.

<br/>

## 팀원


|                                                                    [김민식](https://github.com/)                                                                    |                        [박기택](https://github.com/)                         |                           [조해린](https://github.com/zosunny)                           |                           [천지호](https://github.com/wnstj7788/)                            |                        [최미은](https://github.com/)                         |                        
| :-----------------------------------------------------------------------------------------------------------------------------------------------------------------------: | :---------------------------------------------------------------------------------------: | :---------------------------------------------------------------------------------------: | :---------------------------------------------------------------------------------------: | :---------------------------------------------------------------------------------------: | 
| <img src="https://avatars.githubusercontent.com/u/64001133?v=4" width="100" height="100"> | <img src="https://avatars.githubusercontent.com/u/90086799?v=4" width="100" height="100"> | <img src="https://avatars.githubusercontent.com/u/104357560?v=4" width="100" height="100"> | <img src="https://avatars.githubusercontent.com/u/61938768?v=4" width="100" height="100"> | <img src="https://avatars.githubusercontent.com/u/58421346?v=4" width="100" height="100"> |
|                                                                               **Front-End**                                                                                |                                        **Back-End**                                        |                                        **Back-End**                                        |                                       **Front-End**                                        |                                      **Back-End**                                       |                                       

<br/>

## 프로젝트 산출물

### 요구사항명세서
![요구사항명세서](./img/.png)
<br/>

### API 명세서
![API_1](./img/.png)
![API_2](./img/.png)

## 프로젝트 발표자료

- [중간발표 Presentation](docs/중간 발표 자료.pdf)
- [최종발표 Presentation](docs/최종 발표 자료.pdf)


## 프로젝트 UCC

- [위시미 UCC 보러가기]()

