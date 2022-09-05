
var arrLat = [];
var arrLng = [];
var positions = [];
var arridBoard= [];
var markers = [];

var imageSrc2 = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";
// 마커 이미지의 이미지 크기 입니다
var imageSize2 = new kakao.maps.Size(35, 50);

// 마커 이미지를 생성합니다
var markerImage2 = new kakao.maps.MarkerImage(imageSrc2, imageSize2);

const boardDetailModal = document.querySelector('.boardDetailModal');
const boardDetailModal_close = document.querySelector('.boardDetailModal_close');
const boardDetailModal_close2 = document.querySelector('.boardDetailModal_close2');

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
        addMarker(positions[i], arridBoard[i]);
    }
}

function addMarker(positions2, idBoard2) {
    // 마커를 생성합니다
    var marker2 = new kakao.maps.Marker({
        map: map, // 마커를 표시할 지도
        position: positions2, // 마커를 표시할 위치
        image : markerImage2, // 마커 이미지
        title: idBoard2
    });

    // 생성된 마커를 배열에 추가합니다
    markers.push(marker2);

    kakao.maps.event.addListener(marker2, 'click', function() {
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
            alert("로그인 이후 가능합니다")
            location.href="/account/login"
        }
        fetch(`/getMarkerBoard/${marker2.getTitle()}`)
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
                }
            )

        boardDetailModal.style.display = 'block';
    });
}

boardDetailModal_close.addEventListener('click', () => {
    boardDetailModal.style.display = 'none';
});

boardDetailModal_close2.addEventListener('click', () => {
    boardDetailModal.style.display = 'none';
});