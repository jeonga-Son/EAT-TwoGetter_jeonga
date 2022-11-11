# ideaBANK -  eatTwoGetter
<img src="https://user-images.githubusercontent.com/107593020/192379736-10117707-1169-463d-9475-1d2297bdb8c5.png" style="zoom:30"/>



**"공동 배달을 통해 배달요금을 분담하고 불필요한 지출을 줄인다."**

해당 repo는 "eatTwoGetter - 공동 배달을 위한 Java/Spring 기반 서비스 개발"에 대한 프로젝트 입니다. 

<br>

### 📣 개발목표

* 지도 기반의 게시판 웹사이트 구현
* 사용자들의 중간지점을 보여주는 서비스 도입
* 매칭이 된 사람끼리 채팅할 수 있는 서비스 도입

<br>

### 📣구현 기능 및 기술

* 마커 형식의 게시판 구현
* GPS 신호 수신
* 중간 거리 찾기 
* 거리순으로 우리동네이웃 사람들 정렬
* 실시간 채팅 기능

<br>

### 📣개발환경

* JAVA 17
* IntelliJ
* GitHub

<br>

### 📣사용기술

#### - 백엔드

###### Spring boot

* Spring MVC
* Spring Security
* JPA
* Spring Web
* Lombok



###### Buid tool

* Gradle



###### Database

* MySQL Driver



###### AWS

* EC2


#### - 프론트엔드

* Javascript
* Thymeleaf

<br>

### 📣주요 키워드

* REST API
* 시큐리티
* 페이징
* Git 버전관리
* AWS EC2배포
* Kakao Map API
* Google G-mail API

<br>

### 📣내부 디렉터리 구조

```
├─java
│  │  
│  └─com
│      │ 
│      └─ll
│          │ 
│          └─example
│              │
│              └─eatTwoGetter
│                  │  EatTwoGetterApplication.java
│                  │  Util.java
│                  │
│                  ├─Board
│                  │  ├─controller
│                  │  │      BoardController.java
│                  │  │
│                  │  ├─domain
│                  │  │  ├─entity
│                  │  │  │      Board.java
│                  │  │  │      BoardForm.java
│                  │  │  │
│                  │  │  └─repository
│                  │  │          BoardRepository.java
│                  │  │
│                  │  ├─dto
│                  │  │      BoardDistanceAsc.java
│                  │  │      BoardDto.java
│                  │  │      BoardDtoDistance.java
│                  │  │
│                  │  ├─model
│                  │  │      PageResult.java
│                  │  │
│                  │  └─service
│                  │          BoardService.java
│                  │
│                  ├─chat
│                  │  ├─controller
│                  │  │      ChatApiController.java
│                  │  │      ChatController.java
│                  │  │
│                  │  ├─dto
│                  │  │      ChatInfoDto.java
│                  │  │      ChatMessageDto.java
│                  │  │
│                  │  ├─model
│                  │  │      ChatInfo.java
│                  │  │      ChatMessage.java
│                  │  │
│                  │  ├─repository
│                  │  │      ChatInfoRepository.java
│                  │  │      ChatMessageRepository.java
│                  │  │
│                  │  └─service
│                  │          ChatInfoService.java
│                  │          ChatMessageService.java
│                  │
│                  ├─common
│                  │  └─constants
│                  │          BoardConstants.java
│                  │
│                  ├─exception
│                  │      DataNotFoundException.java
│                  │
│                  ├─login
│                  │  ├─config
│                  │  │      WebSecurityConfig.java
│                  │  │
│                  │  ├─controller
│                  │  │      AccountApiController.java
│                  │  │      AccountController.java
│                  │  │      HomeController.java
│                  │  │
│                  │  ├─model
│                  │  │      Role.java
│                  │  │      User.java
│                  │  │
│                  │  ├─Repository
│                  │  │      UserRepository.java
│                  │  │
│                  │  └─Service
│                  │          KakaoService.java
│                  │          MailService.java
│                  │          UserService.java
│                  │
│                  ├─middleMap
│                  │  └─Controller
│                  │          MiddlePlaceMapController.java
│                  │
│                  ├─Profile
│                  │      ProfileController.java
│                  │
│                  └─report
│                          reportController.java
│
└─resources
    │  application-prod.properties
    │  application.properties
    │
    ├─static
    │  │  .DS_Store
    │  │
    │  ├─css        
    │  │
    │  ├─images
    |  |
    │  ├─js
    │  │  │  board-list.js
    │  │  │  breakpoints.min.js
    │  │  │  browser.min.js
    │  │  │  category-marker.js
    │  │  │  jquery.min.js
    │  │  │  jquery.poptrox.min.js
    │  │  │  jquery.scrollex.min.js
    │  │  │  jquery.scrolly.min.js
    │  │  │  main-map.js
    │  │  │  main.js
    │  │  │  modal-board.js
    │  │  │  modal-category.js
    │  │  │  modal-chat.js
    │  │  │  modal-myProfile.js
    │  │  │  register.js
    │  │  │  util.js
    │  │  │
    │  │  └─middlemap
    │  │          middleMap.js
    │  │
    │  ├─sass
    │  │
    │  └─webfonts
    │
    └─templates
        │  index.html
        │
        ├─about
        │      about_us.html
        │
        ├─account
        │      find.html
        │      login.html
        │      register.html
        │
        ├─board
        │      edit_board.html
        │      list.html
        │      modal_board.html
        │
        ├─chat
        │      chatList.html
        │
        ├─fragment
        │      common.html
        │
        ├─mainMap
        │      map_wrap.html
        │      present_location.html
        │
        ├─middlePlaceMap
        │      middleMap.html
        │
        ├─profile
        │      myProfile.html
        │
        └─report
                userReport.html
```

<br>

### 📣시스템 구조

![image](https://user-images.githubusercontent.com/107593020/192380704-7e4f7060-2478-4b8e-9104-c0fb2ccfb6c1.png)

<br>

![image](https://user-images.githubusercontent.com/107593020/192380730-9abad018-37ce-4844-b256-750bcb3383a8.png)

<br>
<hr>

### 📣결과물

##### ETG 사용방법 페이지

![image](https://user-images.githubusercontent.com/107593020/192380773-ec1cb315-d129-4c84-b2a1-60ae2cf725fb.png)

* 처음 접하는 사용자를 위한 소개페이지 입니다.  마지막 페이지에 있는 버튼을 클릭하면 메인 페이지로 이동합니다.

<br>

##### 메인페이지

![image](https://user-images.githubusercontent.com/107593020/192380794-fbfcb0dd-2d1d-45d7-ba2e-487570c3b734.png)

* 고객들이 생성한 마커를 확인할 수 있습니다.
* 마커를 생성하려면 로그인을 해야합니다.
* 원하는 음식 카테고리별로 마커를 확인할 수 있습니다.

<br>

##### 메인페이지 - 게시물 생성 

![image](https://user-images.githubusercontent.com/107593020/192381074-67572a4c-6dca-4b44-b7b3-6abc8dc842d3.png)

* 게시물을 생성하면 지정한 핀 위치에 마커가 띄워집니다.



##### 메인페이지 - 게시글 정보

![image](https://user-images.githubusercontent.com/107593020/192381098-0036ca12-b7de-439a-9854-2233a66d427d.png)

* 마커를 클릭하면 게시글 정보를 확인할 수 있습니다.
* 본인이 생성한 마커이면 수정과 삭제를 할 수 있고, 본인이 아니라면 중간거리 확인과 채팅을 할 수 있습니다.

<br>

##### 로그인 페이지

![image](https://user-images.githubusercontent.com/107593020/192381202-f187443a-5748-40a8-bd17-da39e183abd9.png)

* 로그인은 페이지 입니다.

<br>

##### 회원가입 페이지

![image](https://user-images.githubusercontent.com/107593020/192381212-7cef791f-2a8e-440d-be18-6910b1ba5955.png)

* 회원가입 페이지에서는 닉네임 중복확인, 이메일 중복확인을 해야하도록 구현하였습니다.

<br>

##### 회원정보 수정 페이지

![image](https://user-images.githubusercontent.com/107593020/192381236-a7ecef77-a30a-4a60-aac4-64a525af247a.png)

* 회원정보 수정페이지에서는 닉네임과 비밀번호가 수정이 가능하도록 하였고, 회원탈퇴 기능이 있습니다.

<br>

##### 채팅방 페이지

![image](https://user-images.githubusercontent.com/107593020/192381256-8963c435-a462-400b-8e96-f7931c6bcee7.png)

* 채팅페이지는 누군가 메세지를 보내면 알림이 뜨도록 구현하였습니다.
* 알림은 대화를 끝내고 창을 닫으면 사라집니다.

<br>

##### 우리동네 사람들 페이지

![image](https://user-images.githubusercontent.com/107593020/192381278-226fc563-fd9d-4013-95e0-6b547dc8dfc9.png)

* 본인 위치를 기준으로 가까운 동네사람들 부터 제일 먼 사람까지 확인이 가능한 페이지 입니다.

<br>

### 📣 시연영상

https://www.youtube.com/watch?v=i3RMGNMNdJk&feature=youtu.be

<br>
<hr>

### 📣추후 발전 과제

* 서비스 고도화
  * 프로필 사진 파일업로드 가능하도록 구현

