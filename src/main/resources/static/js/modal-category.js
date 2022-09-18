
var arrLat = [];
var arrLng = [];
var positions = [];
var arridBoard= [];
var boardMarkers = [];

// 카테고리 마커이미지 담는 배열
var arrImages = [];

// 카테고리 마커 이미지의 이미지 크기
var imageSize2 = new kakao.maps.Size(40, 40);

//카테고리 마커 이미지, 사이즈 담는 배열
var markerImage2=[];

// 카테고리 마커 이미지 각각 다르게 담는 코드
for (var i=0; i<boardInfo.length; i++) {
//    console.log(boardInfo[i].storeType)

    if ( boardInfo[i].storeType === "한식") {
        arrImages[i] = "https://raw.githubusercontent.com/jeonga-Son/image/819496faa58bb39662d1a612d8d39ee09c4e6609/images/k-food_marker.png"
        markerImage2[i] = new kakao.maps.MarkerImage(arrImages[i], imageSize2);
    }
    else if ( boardInfo[i].storeType === "일식") {
        arrImages[i] = "https://raw.githubusercontent.com/jeonga-Son/image/819496faa58bb39662d1a612d8d39ee09c4e6609/images/j-food_marker.png"
        markerImage2[i] = new kakao.maps.MarkerImage(arrImages[i], imageSize2);
    }
    else if ( boardInfo[i].storeType === "중식") {
        arrImages[i] = "https://raw.githubusercontent.com/jeonga-Son/image/819496faa58bb39662d1a612d8d39ee09c4e6609/images/c-food_marker.png"
        markerImage2[i] = new kakao.maps.MarkerImage(arrImages[i], imageSize2);
    }
    else if ( boardInfo[i].storeType === "양식") {
        arrImages[i] = "https://raw.githubusercontent.com/jeonga-Son/image/819496faa58bb39662d1a612d8d39ee09c4e6609/images/w-food_marker.png"
        markerImage2[i] = new kakao.maps.MarkerImage(arrImages[i], imageSize2);
    }
    else if ( boardInfo[i].storeType === "카페") {
        arrImages[i] = "https://raw.githubusercontent.com/jeonga-Son/image/5b8f8ed1221caab4804143506aaa248247b7d5c8/images/cafe_marker.png"
        markerImage2[i] = new kakao.maps.MarkerImage(arrImages[i], imageSize2);
    }
    else if ( boardInfo[i].storeType === "야식") {
        arrImages[i] = "https://raw.githubusercontent.com/jeonga-Son/image/819496faa58bb39662d1a612d8d39ee09c4e6609/images/late-food_marker.png"
        markerImage2[i] = new kakao.maps.MarkerImage(arrImages[i], imageSize2);
    }
    else {
        arrImages[i] = "https://raw.githubusercontent.com/jeonga-Son/image/819496faa58bb39662d1a612d8d39ee09c4e6609/images/etc_marker.png"
        markerImage2[i] = new kakao.maps.MarkerImage(arrImages[i], imageSize2);
    }
}


// 마커 이미지를 생성합니다
const boardDetailModal = document.querySelector('.boardDetailModal');
const editBoardModal = document.querySelector('.editBoardModal');
const boardDetailModal_close = document.querySelector('.boardDetailModal_close');
const boardDetailModal_close2 = document.querySelector('.boardDetailModal_close2');
const boardDeleteBtn = document.querySelector('.boardDeleteBtn');
const boardModifyBtn = document.querySelector('.boardModifyBtn');
const editSaveBtn = document.querySelector('.btn-editSave');
const findMiddleBtn = document.querySelector('.findMiddleBtn');
const chat_Btn = document.querySelector('.chat_btn');

showBoardMarker();


function showBoardMarker() {

    for (var i = 0; i < boardInfo.length; i++) {
        arrLat[i] = boardInfo[i].lat;
        arrLng[i] = boardInfo[i].lng;
        arridBoard[i] = boardInfo[i].id;
    }

    for (var i = 0; i < boardInfo.length; i++) {
        positions[i] = new kakao.maps.LatLng(arrLat[i], arrLng[i]);
    }

    for (var i = 0; i < positions.length; i++) {
        addMarker(positions[i], arridBoard[i], markerImage2[i]);
    }
}

function addMarker(positions2, idBoard2, img) {
    // 마커를 생성합니다
    var boardMarker = new kakao.maps.Marker({
        map: map, // 마커를 표시할 지도
        position: positions2, // 마커를 표시할 위치
        image : img, // 마커 이미지
        title: idBoard2
    });

    // 생성된 마커를 배열에 추가합니다
    boardMarkers.push(boardMarker);


    kakao.maps.event.addListener(boardMarker, 'click', function() {

        var showBoardNickname= document.getElementById('showBoardNickname')
        var showBoardLocate = document.getElementById('showBoardLocate')
        var showBoardTitle = document.getElementById('showBoardTitle')
        var showBoardType = document.getElementById('showBoardType')
        var showBoardName = document.getElementById('showBoardName')
        var showBoardOrder = document.getElementById('showBoardOrder')
        var showBoardMin = document.getElementById('showBoardMin')
        var showBoardDel = document.getElementById('showBoardDel')
        var showBoardContent = document.getElementById('showBoardContent')
        var makeBoardTime = document.getElementById('makeBoardTime')
        var showBoardLat = document.getElementById('showBoardLat')
        var showBoardLng = document.getElementById('showBoardLng')


        if(user1==null){
            Swal.fire({
                  title: '  ',
                  text: '로그인 이후 주문메이트 공고 확인이 가능합니다.',
                  showClass: {
                    popup: 'animate__animated animate__fadeInDown'
                  },
                  hideClass: {
                    popup: 'animate__animated animate__fadeOutUp'
                  }
                }).then((result) => {
                    location.href="/account/login"
                })
        }

        fetch(`/getMarkerBoard/${boardMarker.getTitle()}`)
            .then(data=>data.json())
            .then(responseData=>{
                console.log(responseData)

                let geocoder = new kakao.maps.services.Geocoder();

                let callback = function(result, status) {
                    if (status === kakao.maps.services.Status.OK) {
                        showBoardLocate.innerText =result[0].address.address_name
                    }
                };

                let coord = new kakao.maps.LatLng(responseData.lat, responseData.lng);
                var boardlocate = geocoder.coord2Address(coord.getLng(), coord.getLat(), callback)
                var createDate = responseData.createdDate
                var createDate_ = createDate.substring(0, 4)+"년 "+createDate.substring(5, 7)+"월" +createDate.substring(8, 10)+"일 ";
                var createDate__ = " "+createDate.substring(11,13)+"시"+createDate.substring(14,16)+"분"
                var boardMinimumOrderAmount = responseData.minimumOrderAmount + "원"
                var boardDeliveryCharge = responseData.deliveryCharge + "원"

                makeBoardTime.innerText = createDate_
                makeBoardTime.innerText+=createDate__
                showBoardNickname.innerText = responseData.username
                showBoardTitle.innerText = responseData.title
                showBoardType.innerText = responseData.storeType
                showBoardName.innerText = responseData.storeName
                showBoardOrder.innerText = responseData.orderDetail
                showBoardMin.innerText = boardMinimumOrderAmount
                showBoardDel.innerText = boardDeliveryCharge
                showBoardContent.innerText = responseData.content
                showBoardLat.innerText = responseData.lat
                showBoardLng.innerText = responseData.lng
                showBoardId.innerText = responseData.id
                localStorage.setItem("markerBoardId", responseData.id);

                if(user1.nickname == showBoardNickname.innerText) {
                    boardDeleteBtn.style.display ="inline-block";
                    boardModifyBtn.style.display ="inline-block";
                    findMiddleBtn.style.display="none";
                    chat_Btn.style.display="none";
                } else {
                    boardDeleteBtn.style.display ="none";
                    boardModifyBtn.style.display ="none";
                    findMiddleBtn.style.display="inline-block";
                    chat_Btn.style.display="inline-block";
                }
            })
        boardDetailModal.style.display = 'block';
    });
}

function whenLogoutBulidMarker(){
    Swal.fire({
      title: '  ',
      text: '로그인 이후 주문메이트 공고 생성이 가능합니다.',
      showClass: {
        popup: 'animate__animated animate__fadeInDown'
      },
      hideClass: {
        popup: 'animate__animated animate__fadeOutUp'
      }
    }).then((result) => {
        location.href="/account/login"
    })
}

/**
모달 게시판 삭제 시 기능하는 기능 메서드
*/
function deleteGetBoardId(){

    const boardId = document.getElementById('showBoardId');
    const url = "/board/delete/" + boardId.innerText;
    location.href = url;

}

/**
모달 게시판 삭제 시 나타나는 알림창 메서드
*/
function deleteBoard(){
		Swal.fire({
		  title: '주문메이트 공고 삭제',
		  text: "삭제하시면 다시 복구시킬 수 없습니다.",
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
            text: '새로운 주문메이트 공고를 생성해보세요!',
            icon: 'success'
             }).then((result2) => {
                const boardId = document.getElementById('showBoardId');
                const url = "/board/delete/" + boardId.innerText;
                location.href = url;
             })
		  }
		})
	}

function modifyBoard(){
    boardDetailModal.style.display = "none";
    editBoardModal.style.display="block";

    var modifyBoardNickname= document.getElementById('modifyBoardNickname')
    var modifyBoardLocate = document.getElementById('modifyBoardLocate')
    var modifyBoardTitle = document.getElementById('modifyBoardTitle')
    var modifyBoardType = document.getElementById('modifyBoardType')
    var modifyBoardName = document.getElementById('modifyBoardName')
    var modifyBoardOrder = document.getElementById('modifyBoardOrder')
    var modifyBoardMin = document.getElementById('modifyBoardMin')
    var modifyBoardDel = document.getElementById('modifyBoardDel')
    var modifyBoardContent = document.getElementById('modifyBoardContent')
    var modifyBoardTime = document.getElementById('modifyBoardTime')
    var modifyBoardLat = document.getElementById('modifyBoardLat')
    var modifyBoardLng = document.getElementById('modifyBoardLng')

    let markerBoardId = parseInt(localStorage.getItem("markerBoardId"))

    fetch(`/getMarkerBoard/${markerBoardId}`)
        .then(data=>data.json())
        .then(responseData=>{
            console.log(responseData)

            let geocoder = new kakao.maps.services.Geocoder();

            let callback = function(result, status) {
                if (status === kakao.maps.services.Status.OK) {
                    document.getElementById("modifyBoardLocate").setAttribute("value", result[0].address.address_name)
                }
            };

            let coord = new kakao.maps.LatLng(responseData.lat, responseData.lng);
            var boardlocate = geocoder.coord2Address(coord.getLng(), coord.getLat(), callback)

            modifyBoardTitle.value = responseData.title
            modifyBoardStoreType.value = responseData.storeType
            modifyBoardStoreName.value =responseData.storeName
            modifyBoardOrderDetail.value = responseData.orderDetail
            modifyBoardMinimumOrderAmount.value = responseData.minimumOrderAmount
            modifyBoardDeliveryCharge.value = responseData.deliveryCharge
            modifyBoardContent.value = responseData.content
            modifyBoardLat.value = responseData.lat
            modifyBoardLng.value = responseData.lng
            modifyBoardId.value = markerBoardId
        })

}


boardDetailModal_close.addEventListener('click', () => {
    boardDetailModal.style.display = 'none';
});

boardDetailModal_close2.addEventListener('click', () => {
    boardDetailModal.style.display = 'none';
});