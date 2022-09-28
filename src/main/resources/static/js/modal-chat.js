
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
            email:user1.username,
            partner: showBoardNickname.innerText
        }),
    }).then((response)=>{
        if(response.status==200){
            location.href="chat/list";
        }
    })
}