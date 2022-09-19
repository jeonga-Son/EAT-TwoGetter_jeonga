# 5주차 회고

## **팀 구성원**

---

- 최태승(팀장)
- 이승훈
- 김동현2
- 박종수
- 손정아

## 5주차 **회고 내용 요약 (최소 500자 이상)**

---

### 🔻[Javascript] 로컬 스토리지 사용 방법

### 1. 로컬 스토리지란?

자바 스크립트 메서드나 변수를 사용할 때, 새로 고침을 해도 그 값을 계속 저장해놓고 사용하고 싶을 때가 있다.

예를 들면, 카카오맵을 웹에서 사용하면, 드래그를 하면서 지도를 이동시키는데, 한 번 이동했던 장소를 새로고침 하거나 다른 페이지를 갔다 와도 원래 보았던 위치를 계속 보고 싶을 때를 들 수 있겠다. 자바스크립트는 브라우저를 새로고침 할 때마다 계속 새롭게 불러와지고, 변수도 초기화될 수밖에 없다. 그렇기 때문에 어딘가에 변수를 저장하고 싶다면, 로컬 스토리지를 활용하면 된다.

![https://blog.kakaocdn.net/dn/dnjICX/btrLu9SFrNn/H4JmjhgJJO4IEzKhBhHG81/img.png](https://blog.kakaocdn.net/dn/dnjICX/btrLu9SFrNn/H4JmjhgJJO4IEzKhBhHG81/img.png)

나는 지금 현재 프로젝트에서 로컬 스토리지를 활용하여 경도, 위도 값을 로컬 스토리지에 저장하고, 창을 닫으면 그 값을 지우는 방법으로 브라우저에게 원래 있던 위치를 '기억'하게 만들었다.

이러한 로컬 스토리지를 어떻게 사용하는지 알아보고자 한다.

> <요약>
> 

### 2. 로컬 스토리지에 키-값 추가하기, 조회하기

setItem(key, value) : key-value 쌍의 데이터를 로컬스토리지에 저장한다.

getItem(key, value) : localStorage의 키의 값을 반환한다.

```jsx
// setItemwindow.localStorage.setItem('Lat', '37.51564324');
window.localStorage.setItem('Lng', '126.212121');

// getItemconst Lat = window.localStorage.getItem('Lat');
const Lng = window.localStorage.getItem('Lng');

// 결과 출력document.write(Lat);// 37.51564324document.write('<br/>');
document.write(Lng);// 126.212121
```

로컬 스토리지에는 문자열만 저장할 수 있다. 123.141244를 저장해도 문자열로 저장이 된다. 따라서 숫자로 활용하고 싶다면, 파싱을 해서 그 값을 활용하여야 한다.

### 3, localStorage에 객체, 배열 저장하기

```jsx
// localStorage에 저장할 객체const latLng = {
    lat : 38,
    lng : 123.1241
}

// localStorage에 저장할 배열const arr = [1, 2, 3];

// 객체, 배열을 JSON 문자열로 변환const LatLngString = JSON.stringify(LatLng);
const arrString = JSON.stringify(arr);

// setItemwindow.localStorage.setItem('LatLng', LatLngString);
window.localStorage.setItem('nums', arrString);

// getItemconst LatLngString = window.localStorage.getItem('LatLng');
const numsString = window.localStorage.getItem('nums');

// JSON 문자열을 객체, 배열로 변환const LatLngObj = JSON.parse(LatLngString);
const numsArr = JSON.parse(numsString);

// 결과 출력document.write(LatLngString);// {lat:38, lng:123.1241}document.write('<br/>');
document.write(LatLngObj);// [object Object]document.write('<br/>');

document.write(numsString);// [1,2,3]document.write('<br/>');
document.write(numsArr);// 1,2,3document.write('<br/>');
```

객체의 저장, 조회는 **'문자열화 ▶︎ set ▶︎ get ▶︎ json파싱 ▶︎ 출력'**의 순서를 거친다.

const LatLngString = JSON.stringify(LatLng);

const arrString = JSON.stringify(arr);

JSON.stringify() 메서드를 거치며 문자열화 되고, 이것을 저장한 뒤, 다시 불러내어 parse 하여 나타내는 것이다.

### 4. localStorage에 값 삭제하기

removeItem(key) : 특정 key의 value를 삭제한다.

```jsx
// setItemwindow.localStorage.setItem('Lat', '37.51564324');
window.localStorage.setItem('Lng', '126.212121');

// removeItemwindow.localStorage.removeItem('Lat');
window.localStorage.removeItem('Hat');

// getItemconst Lat = window.localStorage.getItem('Lat');
const Lng = window.localStorage.getItem('Lng');

// 결과 출력document.write(Lat);// nulldocument.write('<br/>');
document.write(Lng);// 126.212121
```

window.localStorage.removeItem('Lat');

키 Lat과 쌍인 value인 37.51564324가 삭제된다. 그런데 Hat은 로컬 스토리지에 없는 키이다. 존재하지 않는 키를 파라미터로 전달하면 아무 일도 일어나지 않는다.

### 5. localStorage에 값 전체 삭제하기

clear() : 로컬 스토리지 모든 키에 저장된 value를 삭제한다.

```jsx
// setItemwindow.localStorage.setItem('Lat', '37.51564324');
window.localStorage.setItem('Lng', '126.212121');

// clearwindow.localStorage.clear();

// getItemconst Lat = window.localStorage.getItem('Lat');
const Lng = window.localStorage.getItem('Lng');

// 결과 출력document.write(Lat);// nulldocument.write('<br/>');
document.write(Lng);// null
```

clear()의 경우, 현재 스토리지에 존재하는 key값과 쌍을 이루는 value가 모두 삭제된다. null이 출력되는 것을 볼 수 있다.

### 6. 로컬 스토리지의 키의 개수 구하기

length : 로컬 스토리지에 있는 키의 개수를 구한다.

```jsx
// 초기화window.localStorage.clear();

// setItemwindow.localStorage.setItem('Lat', '37.51564324');
window.localStorage.setItem('Lng', '126.212121');

// localStorage item 갯수const length = window.localStorage.length

// 결과 출력document.write(length);// 2
```

const length = window.localStorage.length;

localStorage에 저장된 키의 개수를 확인할 수 있다.

### 7. localStorage의 key의 인덱스 찾기

key(index) : n번 인덱스의 키값을 반환한다.

```jsx
// setItemwindow.localStorage.setItem('Lat', '37.51564324');
window.localStorage.setItem('Lng', '126.212121');

// keyconst key_1 = window.localStorage.key(0);
const key_2 = window.localStorage.key(1);

// 결과 출력document.write(key_1);// Latdocument.write('<br/>');
document.write(key_2);// Lng
```

## **회고 과정에서 나왔던 질문 (최소 200자 이상)**

---

### 🔻 yml 파일의 주용도는 무엇인지?

      구성 파일 생성이다. Json보다 가독성이 높고 사용자 친화적이다.

### 🔻 FTP의 특징은?

     빠른 파일 전송을 위한 단순하고 직관적인 동작방식
     FTP 서비스 제공 서버와 클아이언트 사이에 2개의 연결 생성
     데이터 전송을 제어하기 위한 신호 주고받음 (21번 포트)
     실제 데이터(파일) 전송에 사용 (20번 포트)

### 🔻CSRF 토큰이 무엇인지?

    서버에서 들어온 요청이 실제 서버에서 요청한 요청이 맞는지 확인을 위한 토큰 발행

### 🔻POSTMAN 사용법

1. Workspances 생성
2. [+} 버튼 클릭
3. GET or POST 요청
- Paramse
    - QueryString으로 요청 파라미터 설정
- Authrization
    - API 인증을 위해 OAuth 2.0/ API Key / Bearer Token 지원
- Headers
    - 요청 헤더에 정보를 설정할 수 있습니다.
- Body
    - form-data, json, text, xml 등의 데이터 타입 요청 파라미터로 설정할 수 있습니다.
- Pre-request sqript
    - 요청을 보내기 전에 특정 스크립트를 작성하여 미리 만들어 놓은 요청들을 순서대로 실행하여 시나리오 실행이 가능합니다.
