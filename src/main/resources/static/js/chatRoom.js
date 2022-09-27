const new_message = document.querySelector('.new_message')

<!--로그인 된 사용자가 각각 채팅방을 마지막에 읽은 시간을 넣어줌-->
var lastReadTime=[];

 <!--각 채팅방의 고유번호(ID)를 넣어줌-->
 var chatInfoId = [];

 <!--각 채팅방에 알람 ID값이 담긴 배열-->
 var noticeChatMessages = [];

 chatNotice();

function chatNotice(){
    <!--채팅방 정보 fetch로 받아옴-->
    fetch('/chatApi/chatInfo')
        .then(data=>data.json())
        .then(responseData=>{
            var k=0;
            <!--로그인 된 사용자가 각각 마지막에 채팅메세지 읽은 시간을 넣어줌-->
            for(let i=0; i<responseData.length; i++){
                if(responseData[i].partner==user.nickname){
                    lastReadTime[k] = responseData[i].partnerLastReadTime;
                    chatInfoId[k] = responseData[i].id;
                    k++;
                    <!--console.log("파트너같을때")-->
                }
                    else if(responseData[i].username==user.nickname){
                    lastReadTime[k] = responseData[i].userLastReadTime;
                    chatInfoId[k] = responseData[i].id;
                    <!--console.log("유저같을때")-->
                    k++;
                }
            }
        })

        // <!--noticeChatMessages 배열에 알림 html 태그 넣어줌-->
        for(let i=0; i<chatInfoId.length; i++) {
            noticeChatMessages[i] = document.getElementById(`notice${chatInfoId[i]}`);
        }

        <!--여러 채팅방들의 사용자가 읽지 않은 메세지 숫자를 담고있는 배열-->
        var chatNoticeNum = new Array(lastReadTime.length);

        <!--기본 값 0으로-->
        chatNoticeNum.fill(0);

        <!--이번에는 채팅메세지들을 받아옴-->
        fetch('/chatApi/chatMessage')
            .then(data=>data.json())
            .then(responseData=>{
                <!--채팅방에 대한 채팅내역을 순회하면서 시간비교. 시간은 '>'나 '<'로 비교 가능. 가장 최근에 본 시간 이후의 시간들이면 값을 ++ -->
                for(let j=0; j<chatInfoId.length; j++) {
                    for(let i=0; i<responseData.length; i++) {
                        if(chatInfoId[j]==responseData[i].chatInfo.id) {
                            if((lastReadTime[j] < responseData[i].chatMessageTime) && user.nickname!=responseData[i].username) {
                                <!--console.log(j + "번째 채팅방 알람 증가")-->
                                <!--console.log(chatInfoId.length)-->
                                chatNoticeNum[j]++;
                            }

                        }
                    }
                }
                <!--알림 html 태그에 값을 넣어줌-->
                for(let i=0; i<chatNoticeNum.length; i++) {
                    if ( chatNoticeNum[i] == 0) {
                      new_message.style.display='hidden'
                    }
                    else {
                      noticeChatMessages[i].innerText = chatNoticeNum[i]
                    }
                }
            })

            setTimeout(function (){
                chatNotice()
            }, 3000);
 }