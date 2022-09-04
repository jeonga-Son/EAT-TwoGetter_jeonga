const showProfileModal  = document.querySelector(".modal")

//로그아웃 버튼을 눌렀을 떄 localStorage에 저장된 위도, 경도 값을 제거한다.
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

//본인이 아닌 다른 사용자와 채팅이 가능하도록 설정
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
//채팅하기 버튼을 눌렀을 때 사용되는 함수로 채팅방을 생성한다.
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
        //채팅방 제목, 유저네임, 상대방이름을 body에 추가하여 POST 요청을 보낸다.
    }).then((response)=>{
        if(response.status==200){
            location.href="chat/list";
        }
        //성공적으로 마치면 url 이동
    })

}

//중간 지점 찾기 버튼을 누르면 선택된 게시글이 해당하는 위도, 경도 값을 url에 추가하여 보낸다.
function middlespace(){
    var partnerLat= document.getElementById('showBoardLat').innerText
    var partnerLng =document.getElementById('showBoardLng').innerText
    location.href=`/middlePlaceMap/middleMap?lat=${partnerLat}&lng=${partnerLng}`;
}