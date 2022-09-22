 // script 내 전역 변수
  const SIZE = 5; // page size
  let PAGE = 1; // 현재 page
  let latitude = null; // 위도
  let longitude = null; // 경도

  for(var i=0; i<boardDistance.length; i++){
    boardDistance[i] = boardDistance[i] * 1000;
    boardDistance[i] = Math.round(boardDistance[i] * 100 / 100)
  }
  var boardDistanceCopy = [...boardDistance];

  // 위도, 경도 가져오기
  navigator.geolocation.getCurrentPosition(
          (position) => {
            latitude = position.coords.latitude;
            longitude = position.coords.longitude;
          },
  );

  // board 테이블의 데이터를 ajax로 가져오는 함수
  function getArticle() {
    $.ajax({
      type: 'GET',
      url: `/boards?page=${PAGE}&latitude=${latitude}&longitude=${longitude}`,
      success: (data) => {
        let totalCount = data.totalCount;
        let contents = data.contents;
        let html = '';
        let pageHtml = '';

        if (!!contents && contents.length > 0) {
          //라벨을 위한 json 데이터
          let storeTypes = {'한식':'kr','일식':'jp','중식':'ch','양식':'wes','카페':'cafe','야식':'night','기타':'other'};
          $.each(contents, (i, v) => {
            v.createdDate = v.createdDate.replace(/T/gi, ' ')
            v.createdDate = (v.createdDate).substring(0,16)
            // contents loop돌면서 html을 세팅한다. 한줄씩 append 하는 것보단 완성된 하나의 html을 append 하는것이 성능면에서 더 좋다.
            html += `
                            <div class="article_wrap" data-id="${v.id}">
                              <div class="column title"><span class="store_type ${storeTypes[v.storeType]}">${v.storeType}</span>${v.title}</div>
                              <div class="column store_name">${v.storeName}</div>
                              <div class="column user_name">${v.username}</div>
                              <div class="column created_date">${v.createdDate}</div>
                              <div class="column distance">${boardDistance[i]} m</div>
                          </div>`;
          });
        } else {
          // 게시글이 없을 경우
          html = '<div>게시글이 없습니다</div>';
        }

        // 총 개수를 이용해 페이지 버튼을 만든다.
        for(var i=0; i < (totalCount/SIZE); i++) {
          // 현재 페이지이면 'on' class 추가
          pageHtml += `<div class="page ${i+1 === PAGE ? 'on' : ''}">${i + 1}</div>`;
        }

        // 총 개수 세팅
        $('.total_count').text(totalCount);
        // 게시글 html 세팅
        $('.article_area').empty().html(html);
        // 페이지 (하단 버튼) html 세팅
        $('.paging_area').empty().append(pageHtml);
      }
    });
  }

  // 화면의 dom이 로드되면 실행
  $(() => {
    // 위도, 경도를 가져오는데에 시간이 조금 걸리기 때문에 완료될 때까지 반복 체크
    let checkPosition = setInterval(() => {
      if (!!latitude && !!longitude) {
        // 위도,경도를 가져오는데에 성공했다면 board 테이블의 데이터를 가져오는 함수 호출.
        getArticle();
        // 더 이상 체크할 필요 없으므로 interval을 해제해준다.
        clearInterval(checkPosition);
      }
    }, 1000);

    // page 버튼 클릭
    $(document).on('click', '.page', (e) => {
      console.log((e.target).innerText)
      if((e.target).innerText == 1){
        for(var i=0; i<boardDistance.length; i++){
          boardDistance[i]= boardDistanceCopy[i]
        }
      }
      if((e.target).innerText == 2){
        for(var i=0; i<boardDistance.length; i++){
          boardDistance[i]= boardDistanceCopy[i+5]
        }
      }
      if((e.target).innerText == 3){
        for(var i=0; i<boardDistance.length; i++){
          boardDistance[i]= boardDistanceCopy[i+10]
        }
      }
      // 클릭한 페이지 버튼의 인덱스(페이지)를 현재페이지로 설정하고, 함수를 호출한다.
      let index = $(e.target).index();
      PAGE = index + 1;
      getArticle();
    });
  });