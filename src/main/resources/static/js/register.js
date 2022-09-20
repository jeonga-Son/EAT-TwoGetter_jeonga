var booleanId = false;
var booleanNickname = false;

var nicknameCheck= "";
var emailCheck= "";

// 회원가입 양식 최종 체크 메서드
function accountRegister(){
    var passwordCheck = document.getElementById('passwordCheck').value;
    var password = document.getElementById('password').value;

    var formNickname = document.getElementById('nickname').value;
    var formEmail = document.getElementById('username').value;
    if(password == ""){
        Swal.fire({
            icon: 'warning',
            title: '',
            text: '비밀번호를 입력해주세요.'
        })
        return;
    }
    if(passwordCheck == ""){
        Swal.fire({
            icon: 'warning',
            title: '',
            text: '비밀번호 확인을 입력해주세요.'
        })
        return;
    }
    
    if(formNickname != nicknameCheck){
        Swal.fire({
            icon: 'warning',
            title: '',
            text: '닉네임이 중복되었는지 확인해주세요!!'
        })
        return;
    }
    if(formEmail != emailCheck) {
        Swal.fire({
            icon: 'warning',
            title: '',
            text: '이메일이 중복되었는지 확인해주세요!!'
        })
        return;
    }
    if(booleanId===true && booleanNickname==true){
        if(passwordCheck===password ){
            fetch('http://localhost:8080/account/register', {
                method: 'POST',
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({
                    nickname: formNickname,
                    username: formEmail,
                    password: password
                })
            })
            Swal.fire({
              icon: 'success',
              title: '',
              text: '가입이 완료되었습니다!'
            }).then((result) => {
                window.location.href="http://localhost:8080/account/login"
            })
        }else{
            Swal.fire({
              icon: 'warning',
              title: '',
              text: 'Password가 일치하지 않습니다!'
            })
        }
    }
    else if(booleanId==true && booleanNickname==false){
        Swal.fire({
          icon: 'warning',
          title: '',
          text: '닉네임이 중복되었는지 확인해주세요!!'
        })
    }else{
        Swal.fire({
          icon: 'warning',
          title: '',
          text: '이메일이 중복되었는지 확인해주세요!!'
        })
    }
}

// 이메일 중복 확인 로직
function checkEmail(){
    const email = document.getElementById('username').value;
    if(email===""){
        Swal.fire({
          icon: 'warning',
          title: '',
          text: 'E-mail을 입력해주세요!'
        })
    }
    else if(email.includes("@")==false){
        Swal.fire({
          icon: 'warning',
          title: '',
          text: '이메일 형식(@)으로 작성해주세요!'
        })
    }
    else{
        fetch(`http://localhost:8080/api/users?username=${email}`)
            .then((response)=>response.json())
            .then(data=>{
                if(data[0]!=null){
                    Swal.fire({
                      icon: 'warning',
                      title: '',
                      text: '이미 있는 E-mail입니다. 다른 E-mail을 사용해주세요.'
                    })
                }else{
                    emailCheck = email;
                    Swal.fire({
                      icon: 'success',
                      title: '',
                      text: '사용가능한 E-mail입니다.'
                    })
                    booleanId=true;
                }
            });
    }
}

// 닉네임 중복 확인 로직
function checkNickname(){
    var nickname = document.getElementById('nickname').value;
    if(nickname==""){
        Swal.fire({
          icon: 'warning',
          title: '',
          text: '닉네임을 입력해주세요!'
        })
    }else {
        fetch(`http://localhost:8080/api/user/${nickname}`)
            .then((response)=>response.json())
            .then(data=>{
                if(data==true){
                    nicknameCheck = nickname;
                    Swal.fire({
                      icon: 'success',
                      title: '',
                      text: '생성가능한 닉네임입니다!'
                    })
                    booleanNickname=true;
                }else{
                    Swal.fire({
                      icon: 'warning',
                      title: '',
                      text: '이미 존재하는 닉네임이에요.'
                    })
                }
            });
    }
}