const showProfileModal  = document.querySelector(".modal")
function removeStorage(){
    localStorage.removeItem("Lat")
    localStorage.removeItem("Lng")
}
function showProfile(){
    showProfileModal.style.display='block';
}
function closeProfile(){
    showProfileModal.style.display='none';
}
const chatModal = document.querySelector('.chatRoomModal')
function showChat(){
    var showBoardNickname = document.getElementById('showBoardNickname')
    if(user1.nickname==showBoardNickname.innerText){
        alert("채팅은 다른 사용자와 가능합니다")
    }else{
        chatModal.style.display='block';
    }
}
function closeChat(){
    chatModal.style.display='none';
}
function postChat(){
    var chatRoomTitle=  document.getElementById('chatRoomTitle')
    var showBoardNickname = document.getElementById('showBoardNickname')

    fetch("/chatApi/chatPost",{
        method: 'POST',
        headers:{
            'content-type':'application/json'
        },
        body : JSON.stringify({
            chatTitle: chatRoomTitle.value,
            username: user1.nickname,
            partner: showBoardNickname.innerText
        }),
    }).then((response)=>{
        if(response.status==200){
            location.href="chat/list";
        }
    })

}

function middleSpace(){
//     console.log(user1.nickname)
//     console.log("---")
//     console.log(showBoardNickname.innerText)
    if(user1.nickname === showBoardNickname.innerText){
        Swal.fire({
          icon: 'error',
          title: '다른 사람과 만나세요!',
          text: '자기 자신과의 중간거리는 알아볼 수 없어요!',
        })

        return;
    }

    var partnerLat= document.getElementById('showBoardLat').innerText
    var partnerLng =document.getElementById('showBoardLng').innerText
    location.href=`/middlePlaceMap/middleMap?lat=${partnerLat}&lng=${partnerLng}`;
}