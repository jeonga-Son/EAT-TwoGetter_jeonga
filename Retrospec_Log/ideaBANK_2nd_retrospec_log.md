## **팀 구성원**

---

- 최태승(팀장)
- 이승훈
- 김동현2
- 박종수
- 손정아

## 2주차 **회고 내용 요약 (최소 500자 이상)**

2주차 회고는 1명이 금요일 결석 예정이라 하루 일찍인 목요일(8.18)에 진행하였습니다.

---

## **🔻GIT**

### interactive rebase (`$git rebase -i`)

**✔️개념**

<aside>
💡 인터렉티브 리베이스가 의미하는 뜻은 `rebase`
 명령어를 사용할 때 `-i`
 옵션을 같이 사용한다는 것입니다.

</aside>

**✔️유용한 상황**

다수의 feature 브랜치가 merge 된 `epic branch`를 main 브랜치로 merge하는 상황

**✔️실습**

1. 목표 띄우기 

![image](https://user-images.githubusercontent.com/98330884/185565773-ad3083f5-7707-4ed6-b2b8-6c83ad2d47ce.png)


2.

![image](https://user-images.githubusercontent.com/98330884/185565825-cef5d19c-995a-40d0-820e-169995eeb515.png)


3. 커밋 순서 변경 & 커밋 빼기

![image](https://user-images.githubusercontent.com/98330884/185565889-f0b21d14-d1d5-4a34-b601-9a6af135f058.png)

4. 해결완료

![image](https://user-images.githubusercontent.com/98330884/185565973-3c9d4ce2-9b04-4bda-bebc-1318e516eca5.png)


### Rebase

- Git에서 한 브랜치에서 다른 브랜치로 합치는 방법은 Merge와 Rebase다.
- Merge와 Rebase의 실행 결과는 같지만 커밋 히스토리가 달라진다.
- Merge는 쉽고 안전하지만 커밋 히스토리가 지저분할 수 있다 반면 Rebase는 잘 모르고 사용할 경우 위험할 수 있어 까다롭지만 커밋 히스토리를 깔끔하게 관리할 수 있다.
- Rebase는 base를 새롭게 설정한다는 의미로 이해하면 좋다.
- `$ git rebase [newbase]`
- Merge와 Rebase 차이
    

    ![image](https://user-images.githubusercontent.com/98330884/185566076-da21775c-9e94-4e04-a376-f6746b482965.png)

    (1) merge 명령을 이용해 3-way Merge로 새로운 커밋을 만들어내는 것이다.
    
    이 때 내부적으로 공통 조상인 C2를 이용하게 된다.
    
    ![image](https://user-images.githubusercontent.com/98330884/185566138-4afc9b2e-e619-494b-b463-283930f6fc2c.png)
    
    (2) `experiment`
     브랜치로 이동해 master를 base삼아 Rebase 하겠다는 의미.
    
    ```
    $ git checkout experiment
    $ git rebase master
    First, rewinding head to replay your work on top of it...
    Applying: added staged command
    ```
    
    ![image](https://user-images.githubusercontent.com/98330884/185566207-5de58b73-6211-49ee-b99b-51588cb69568.png)
    
- 단점
    - 이미 공개해서 사람들이 사용하는 커밋을 Rebase하면 반드시 문제가 생긴다는 것이다.
    - 협업 없이 혼자 사용하는 경우는 문제될 것이 없다.
    - `git pull` 명령을 실행할 때 기본적으로 `-rebase` 옵션이 적용되도록 pull.rebase 설정을 추가할 수 있습니다. `git config --global pull.rebase true` 명령으로 추가한다.

## **🔻인프라**

### CentOS9 서비스들

- systemctl 명령어로 서비스를 다룬다.
    - status : 현재상태
    - start : 켜기
    - stop : 끄기
    - enable 활성화
        
        -재부팅시에 자동으로 켜짐
        
    - disable : 비활성화
        
        -재부팅시에 자동으로 켜지지 않음
        

```
sudo systemctl status nginx # nginx 꺼져있음
sudo systemctl start nginx # nginx 켜기
sudo systemctl enable nginx # nginx 활성화

sudo systemctl status firewalld # firewalld 켜져있음
sudo systemctl stop firewalld # firewalld 끄기
sudo systemctl disable firewalld # firewalld 비활성화
```

### 알게된 것

서버 한개에 여러개의 사이트가 연결된 경우, 연결되었을 때 어떤 사이트에 연결되어야 하는 지 알 수 없다.

→ 

해결 방법 : 도메인 3개를 준비, ex ) site1.com, site2.com, site3.com

도메인이 들어오면 dns서버가 결국엔 내부 서버 주소로 바꿔서 돌아온다. 

앞에 nginx를 두고, 현재 서버에 들어온 도메인 주소를 받는다. 들어올 때의 내용을 보고 해당 사이트로 라우팅 해준다 , **nginx는 포워딩이 빠르다.**

www.naver.com입력시 ip로 바꾸려는 노력을 해야한다 , 

방법1) host파일한테 ip가 무엇인지 물어본다. 

방법2) 내부 캐시를 확인한다(dns서버에게 받아왔었는지 확인)

방법3) 한국통신한테 물어본다(DNS서버) → ip주소를 알아온 후 내부 캐시에 저장

 

**nginx에서 multisite를 돌리는 방법**

1. 여러 개의 도메인을 준비한다.
    
    도메인을 강제로 만드는 방법이 있다.
    
    1. host 파일에 넣고 싶은 도메인, ip를 넣을 수 있다
        - mac host파일 수정하는 법

### 실습

1. `sudo systemctl start nginx`  ⇒ nginx 켜기

![image](https://user-images.githubusercontent.com/98330884/185566298-1f6b58e0-45d7-4d20-b0b3-5bc710cd9bba.png)

2. `sudo systemctl status nginx # nginx`  ⇒ 상태확인 (켜져있음)

![image](https://user-images.githubusercontent.com/98330884/185566341-6c0f4c47-07e7-43ff-b13f-396ed3cf8b95.png)

3. `sudo systemctl enable nginx` ⇒ nginx 활성화

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/d29f9772-188f-4fc6-9c79-4b8976fea563/Untitled.png)

4.`sudo systemctl stop firewalld` firewalld 끄기

   `sudo systemctl disable firewalld` firewalld 비활성화

![image](https://user-images.githubusercontent.com/98330884/185566424-875c4806-4498-44c2-9f65-73e0f41717db.png)


1. 브라우저로 http://{CENTOS_IP} 로 접속, HelloWorld 확인

![image](https://user-images.githubusercontent.com/98330884/185566458-35e58efd-ced3-49a7-b38d-39c306013fda.png)


## 🔻스프링부트

### **th:each**

*반복문*

ex. `<tr th:each="question : ${questionList}">`

`<td th:text="${question.subject}"></td>`

### **loop**

*반복문*

ex. `<tr th:each="question, loop : ${questionList}">`

```
<td>
    [[${loop.count}]] / [[${loop.size}]]
</td>
```

`<td>[[${loop.count}]] / [[${loop.size}]]``</td>`

### **#strings.substring**

*문자열 자르기*

ex. `<td th:text="|Date. ${#strings.substring(question.createDate, 2, 16)}|"></td>`

### **템플릿 상속**

템플릿 파일들을 모두 표준 HTML 구조로 변경하면 body 엘리먼트 바깥 부분(head 엘리먼트 등)은 모두 같은 내용으로 중복된다. 그러면 CSS 파일 이름이 변경되거나 새로운 CSS 파일이 추가될 때마다 모든 템플릿 파일을 일일이 수정해야 한다.

타임리프는 이런 중복의 불편함을 해소하기 위해 템플릿 상속 기능을 제공한다. 템플릿 상속은 기본 틀이 되는 템플릿을 먼저 작성하고 다른 템플릿에서 그 템플릿을 상속해 사용하는 방법이다.

```html
<!-- 기본 템플릿 안에 삽입될 내용 Start -->
<th:block layout:fragment="content"></th:block>
<!-- 기본 템플릿 안에 삽입될 내용 End -->
```

```html
<html layout:decorate="~{layout}">
<div layout:fragment="content" class="container my-3">
    <table class="table">
        (... 생략 ...)
    </table>
</div>
</html>
```

다음과 같이 작성하면 위에 템플릿 안에 내용을 밑에 템플릿에서 작성한 내용으로 대체할 수 있다.

### **Validation**

build.gradle 추가

implementation 'org.springframework.boot:spring-boot-starter-validation’

![image](https://user-images.githubusercontent.com/98330884/185566550-34bcde7d-8ef5-432f-a689-740a10022f2e.png)


```java
@AllArgsConstructor
@Getter
public class QuestionForm {
    @NotEmpty(message = "제목은 필수항목입니다.")
    @Size(max = 200, message = "제목을 200자 이하로 입력해주세요.")
    private String subject;
    @NotEmpty(message = "내용은 필수항목입니다.")
    private String content;
}
```

@NotEmpty로 Null을 방지하고 @Size로 문자 길이를 제한하고 메시지를 포함

```java
@PostMapping("/create")
    public String questionCreate(Model model, @Valid QuestionForm questionForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "question_form";
        }

        questionService.create(questionForm.getSubject(), questionForm.getContent());
        return "redirect:/question/list"; // 질문 저장후 질문목록으로 이동
    }
```

@Valid QuestionForm questionForm, BindingResult bindingResult

@Valid을 붙여줌으로써 유효한지 검사를 하고  bindingResult에 모든 결과를 담는다. bindingResult.hasErrors()가 참인경우는 검사에 걸렸을 경우 

```html
<div class="alert alert-danger" role="alert" th:if="${#fields.hasAnyErrors()}">
  <div th:each="err : ${#fields.allErrors()}" th:text="${err}" />
</div>
```

템플릿에서 에러가 존재할 경우 해당 메시지를 출력할 수 있다.

### **기존 입력 값 유지**

QuestionForm과 같이 매개변수로 바인딩한 객체는 Model 객체로 전달하지 않아도 템플릿에서 사용이 가능하다.

```html
<form th:action="@{/question/create}" th:object="${questionForm}" method="post">
        <div class="alert alert-danger" role="alert" th:if="${#fields.hasAnyErrors()}">
            <div th:each="err : ${#fields.allErrors()}" th:text="${err}" />
        </div>
        <div class="mb-3">
            <label for="subject" class="form-label">제목</label>
            <input type="text" th:field="*{subject}" class="form-control">
        </div>
        <div class="mb-3">
            <label for="content" class="form-label">내용</label>
            <textarea th:field="*{content}" class="form-control" rows="10"></textarea>
        </div>
        <input type="submit" value="저장하기" class="btn btn-primary my-2">
    </form>
```

th:object = “${questionForm}”으로 questionForm을 받고 th:field="*{subject}", th:field="*{content}"로 post 요청 전 값을 유지할 수 있다.

### 실습

[GitHub - jeonga-Son/jump_to_sbb](https://github.com/jeonga-Son/jump_to_sbb.git)

## **회고 과정에서 나왔던 질문 (최소 200자 이상)**

---

### 🔻 href를 CDN으로 받는 과정이 뭐가 다른가요?

 [http://localhost:8080/bootstrap.min.css](http://localhost:8080/bootstrap.min.css)

- 장점 : 만약에 개발하는 곳에서 인터넷 안된더라도 작동
- 단점 : 귀찮지만 다운로드 해야 됨

[https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.2.0/css/bootstrap.min.css](https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.2.0/css/bootstrap.min.css)

- 장점 : 가져오기 편하다(다운로드 안해도 됨)
- 단점 : 만약에 폐쇄망에서 작동(x)

### 🔻매개변수에 객체를 저렇게 사용하면 모델에 따로 넣어주지 않아도 return 받는 템플릿에서 객체 사용이 가능하다는 건가요?

맞습니다.

### 🔻 입력이 없을 경우 프론트단에서 제출을 못하게 하는방법은 안좋나요?

그렇지 않습니다. 오히려 좋습니다. 하지만 서버단에서 spring validation을 활용할 줄 도 알아야 합니다.

## **회고 인증샷 & 팀 자랑**

> 저희 팀은 수업 종료 후 디스코드 음성채널방에 모여서 다같이 복습을 진행합니다. 수업 종료 후 바로 복습을 하다보니 훨씬 학습효과가 좋았습니다.
>

<img width="1151" alt="스크린샷 2022-08-19 오후 4 02 00" src="https://user-images.githubusercontent.com/98330884/185564281-b7fe242b-2e5d-4610-ab35-17317a5f2b7c.png">
