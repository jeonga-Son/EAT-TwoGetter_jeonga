
// 1. 기본 맵 생성

// 1) 카카오맵을 담을 컨테이너 생성
var mapContainer = document.getElementById('map'), // 지도를 표시할 div
    mapOption = {
        center: new kakao.maps.LatLng(37.566826, 126.9786567), // 지도의 중심좌표
        level: 4 // 지도의 확대 레벨
    };

// 2) 로컬 스토리지 활용, 핀 위치 이동.
/**
새로 고침 또는 페이지 이동을 하여도 핀이 그 자리에 고정되어 나타나도록 하기 위함.
*/
if(localStorage.getItem("Lat")!=null){
    var latCenter = localStorage.getItem("Lat")
    var lngCenter = localStorage.getItem("Lng")
    mapOption.center = new kakao.maps.LatLng(latCenter, lngCenter)
}

// 3) 지도 객체 생성.
/**
parameter : Map(container, options)
*/
var map = new kakao.maps.Map(mapContainer, mapOption);


// 4) 부가기능(줌 컨트롤)

// 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
var zoomControl = new kakao.maps.ZoomControl();
map.addControl(zoomControl, kakao.maps.ControlPosition.BOTTOMRIGHT);



// 2. 위치 수집, GPS 마커 표시 기능(GeoLocation)

// 1) gpsButton 메서드
/**
현재 자신의 위치를 알려줄 수 있는 gps 마커를 띄우는 버튼에 필요한 메서드
*/
var gpsMarkers;

localStorage.setItem("isMarker", "0");

function gpsButton(){

    // HTML5의 geolocation으로 사용할 수 있는지 확인합니다
    if (navigator.geolocation) {
        // GeoLocation을 이용해서 접속 위치를 얻어옵니다
        navigator.geolocation.getCurrentPosition(function(position) {

            var lat = position.coords.latitude, // 위도
                lon = position.coords.longitude; // 경도

            var locPosition = new kakao.maps.LatLng(lat, lon); // 마커가 표시될 위치를 geolocation으로 얻어온 좌표로 생성합니다

            if(localStorage.getItem("isMarker") == "1"){
                map.setCenter(locPosition);
                return;
            }

            // 마커와 인포윈도우를 표시합니다
            displayMarker(locPosition);
        });
    } else { // HTML5의 GeoLocation을 사용할 수 없을때 마커 표시 위치와 인포윈도우 내용을 설정합니다
        var locPosition = new kakao.maps.LatLng(x, y);
        displayMarker(locPosition);
    }
}


// 2) 마커 이미지 객체 생성
/**
마커 이미지와 사이즈를 설정할 수 있다.
*/
let icon = new kakao.maps.MarkerImage(
    //마커 이미지를 변경
    "images/gpsIcon.png",
    new kakao.maps.Size(50, 50)
);


// 3) 마커 표시 함수
/*
마커를 생성하고 변경된 이미지를 반영한 후, 현재 사용자의 위치에 띄운다.
gps 마커가 지도의 가운데에 보이게 하기 위해 setCenter() 메서드를 사용한다.
*/


function displayMarker(locPosition) {
    // gps마커를 생성합니다
    gpsMarker = new kakao.maps.Marker({
        map: map,
        position: locPosition
    });
    gpsMarker.setImage(icon);
    // 지도 중심좌표를 접속위치로 변경합니다
    map.setCenter(locPosition);
    localStorage.setItem("isMarker", 1);
}


// 3. 현재 위치 표시(주소, 좌표), 마커게시판 생성 버튼

// 1) 기본 필요 변수, 메서드

// (1) 주소-좌표 변환 객체
var geocoder = new kakao.maps.services.Geocoder();


// (2) 현재 지도 중심좌표로 주소를 검색해서 지도 좌측 상단에 표시하는 메서드
searchAddrFromCoords(map.getCenter(), displayCenterInfo);

// (3) 중심 좌표나 확대 수준이 변경됐을 때 지도 중심 좌표에 대한 주소 정보를 표시하도록 이벤트를 등록
kakao.maps.event.addListener(map, 'idle', function() {
    searchAddrFromCoords(map.getCenter(), displayCenterInfo);
});

// (4) (2)에 필요한 메서드
function searchAddrFromCoords(coords, callback) {
    // 좌표로 행정동 주소 정보를 요청합니다
    geocoder.coord2Address(coords.getLng(), coords.getLat(), callback);
}

function searchDetailAddrFromCoords(coords, callback) {
    // 좌표로 법정동 상세 주소 정보를 요청합니다
    geocoder.coord2Address(coords.getLng(), coords.getLat(), callback);
}

// 2) 하단 박스에 중심 좌표의 주소정보를 표출하는 메서드
function displayCenterInfo(result, status) {
    if (status === kakao.maps.services.Status.OK) {
        var infoDiv = document.getElementById('centerAddr');
        var infoLng = document.getElementById('centerLatLng');

        //지도 현재 좌표 위도 경도
        for(var i = 0; i < result.length; i++) {
            // 행정동의 region_type 값은 'H' 이므로
            infoDiv.innerHTML = result[i].address.address_name;
            infoLng.innerHTML = map.getCenter();
            var centerMapx = map.getCenter().Ma
            var centerMapy = map.getCenter().La
            // 로컬 스토리지에 좌표 경위도 값을 저장
            localStorage.setItem("Lat", centerMapx)
            localStorage.setItem("Lng", centerMapy)

        }
    }
}
