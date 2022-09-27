    const accountModifyNickname  = document.querySelector(".modal_accountModifyNickname")
    function showAccountModifyNickname(){
        accountModifyNickname.style.display='block';
    }
    function closeProfileNickname(){
        accountModifyNickname.style.display='none';
    }

    const accountModifyPassword  = document.querySelector(".modal_accountModifyPassword")
    function showAccountModifyPassword(){
        accountModifyPassword.style.display='block';
    }
    function closeProfilePassword(){
        accountModifyPassword.style.display='none';
    }

    function removeAccount(username){
      Swal.fire({
        title: '회원 탈퇴하기',
        text: "회원을 탈퇴하면 다시 복구시킬 수 없습니다.",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: '삭제',
        cancelButtonText: '취소'
      }).then((result) => {
        if (result.value) {
            Swal.fire({
                title: '삭제가 완료되었습니다.',
                text: '그동안 서비스를 이용해주셔서 감사합니다.',
                icon: 'success'
            }).then((result2) => {
                location.href = `/account/remove/${username}`;
            })
        }
      })
   }
