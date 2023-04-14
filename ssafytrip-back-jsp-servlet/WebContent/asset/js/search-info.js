// 전역 변수 - start
let serviceKey =
  "HGWbiXI4puxU2CzKvcLxn5CesxFWEHG%2F49E1OBVZH4N3flzq0q%2B442V4Ii2Bk43fuLm0jmkitNv5Qaxxb1U6ag%3D%3D";
let key = "dlrjtdmsdlatlfhqkfrmqgkszldlqslek"; // 임시 발급 api키
let contentType = {
  12: "관광지",
  14: "문화시설",
  15: "행사/공연/축제",
  25: "여행코스",
  28: "레포츠",
  32: "숙박",
  38: "쇼핑",
  39: "음식점",
};
let domain = "http://localhost:8080";

let markerImgFile = {
  12: "marker_travel.png",
  14: "marker_culture.png",
  15: "marker_festival.png",
  25: "marker_travel_course.png",
  28: "marker_leports.png",
  32: "marker_bed.png",
  38: "marker_shopping.png",
  39: "marker_food.png",
};

let gps = {};
// 전역 변수 - end

// map - start
var container = document.getElementById("map");
var options = {
  center: new kakao.maps.LatLng(36.355623, 127.298608),
  level: 4,
};

var map = new kakao.maps.Map(container, options);
// map - end

// window loading - start
window.onload = () => {
  let sidoUrl = `${domain}/${root}/api?keyword=sido&key=${key}`;
  fetch(sidoUrl)
    .then((response) => response.json())
    .then((data) => {
      for (let item of data) {
        let code = item.code;
        let name = item.name;

        let op1El = document.getElementById("op1");
        op1El.innerHTML += `
          <option value="${code}">${name}</option>
          `;
      }
    });
};
// window loading - end

// option1 change event - start
function changeOp1() {
  let selectEl = document.getElementById("op1");

  let op1El = document.getElementById("op2");
  op1El.innerHTML = "<option value=''>군/구를 선택하세요</option>";

  let gugunUrl = `${domain}/${root}/api?keyword=gugun&key=${key}&sidoCode=${selectEl.value}`;
  fetch(gugunUrl)
    .then((response) => response.json())
    .then((data) => {
      for (let item of data) {
        let code = item.code;
        let name = item.name;

        op1El.innerHTML += `
          <option value="${code}">${name}</option>
          `;
      }
    });
}
// option1 change event - end

// search btn event - start
function searchInfo() {
  // 검색
  var container = document.getElementById("map");
  container.innerHTML = "";
  map = new kakao.maps.Map(container, options);

  let op1 = document.getElementById("op1").value;
  let op2 = document.getElementById("op2").value;
  let op3 = document.getElementById("op3").value;
  let keyword = document.getElementById("search").value;

  if (op1 === "" && op2 === "" && keyword === "") {
    alert("시/도를 선택하거나, 검색어를 입력해주세요");
    return;
  }

  search(keyword, op1, op2, op3);
}
// search btn event - end

function search(keyword, op1, op2, op3) {
  let url = `https://apis.data.go.kr/B551011/KorService1/searchKeyword1?serviceKey=${serviceKey}&numOfRows=1000&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=C&keyword=${keyword}&contentTypeId=${op3}&areaCode=${op1}&sigunguCode=${op2}`;

  if (keyword === "") {
    url = `https://apis.data.go.kr/B551011/KorService1/areaBasedList1?serviceKey=${serviceKey}&numOfRows=1000&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=C&contentTypeId=${op3}&areaCode=${op1}&sigunguCode=${op2}`;
  }

  fetch(url)
    .then((response) => response.json())
    .then((data) => {
      // console.log(data.response.body.items);
      let infoObj = data.response.body.items.item;
      let infoEl = document.getElementById("infoList");
      infoEl.innerHTML = "";

      if (infoObj === undefined) {
        infoEl.innerHTML = "<li>검색 결과가 없습니다.</li>";
        return;
      }

      // 이동할 위도 경도 위치를 생성합니다
      let lng = infoObj[0].mapx;
      let lat = infoObj[0].mapy;
      var moveLatLon = new kakao.maps.LatLng(lat, lng);

      // 지도 중심을 이동 시킵니다
      map.setCenter(moveLatLon);

      for (let item of infoObj) {
        let title = item.title;
        let addr = item.addr1 + " " + item.addr2;
        let contentId = item.contentid;
        let contentTypeId = item.contenttypeid;
        lng = item.mapx;
        lat = item.mapy;

        gps[contentId] = [lat, lng];

        infoEl.innerHTML += `
          <li id=${contentId}>
            <h3 id="infoTitle" class="info-title" onclick="selectInfo(this)">${title}</h3>
            <div class="info-addr">${addr}</div>
            <div class="info-type">분류 : ${contentType[contentTypeId]}</div>
          </li>
        `;

        createMarker(contentId, contentTypeId, title, lat, lng);
      }
    });
}

let selectedMarker = null;
let allInfoWindow = [];

function createMarker(contentId, contentTypeId, title, lat, lng) {
  // var imageSrcTemp = `./asset/img/marker/marker_bed.png`;
  // http://localhost:8080/ssafytrip/asset/img/marker/marker_bed.png
  var imageSrc = `${domain}/${root}/asset/img/marker/${markerImgFile[contentTypeId]}`, // 마커이미지의 주소입니다
    imageSize = new kakao.maps.Size(40, 40), // 마커이미지의 크기입니다
    imageOption = { offset: new kakao.maps.Point(27, 69) }; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.

  // 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
  var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption),
    markerPosition = new kakao.maps.LatLng(lat, lng); // 마커가 표시될 위치입니다

  // 마커를 생성합니다
  var marker = new kakao.maps.Marker({
    position: markerPosition,
    image: markerImage, // 마커이미지 설정
  });

  // 마커가 지도 위에 표시되도록 설정합니다
  marker.setMap(map);

  // 마커를 클릭했을 때 마커 위에 표시할 인포윈도우를 생성합니다
  // var iwContent = `<div style="padding:5px;">${contentId}</div>`; // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
  var iwContent = `
    <div style="margin: 20px; width:200px; height:100px;">
        <div style="margin-bottom:10px;"><h4>${title}</h4></div>
        <hr/>
        <br/>
        <div>${contentType[contentTypeId]}</div>
    </div>
  `;

  var iwRemoveable = true; // removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다

  // 인포윈도우를 생성합니다
  var infowindow = new kakao.maps.InfoWindow({
    content: iwContent,
    removable: iwRemoveable,
  });
  allInfoWindow[allInfoWindow.length] = infowindow;
  console.log(allInfoWindow);

  // 마커에 클릭이벤트를 등록합니다
  kakao.maps.event.addListener(marker, "click", function () {
    // 마커 위에 인포윈도우를 표시합니다
    if (!selectedMarker || selectedMarker !== marker) {
      for (let cur of allInfoWindow) {
        cur.close();
      }
      infowindow.open(map, marker);
    }

    selectedMarker = marker;
  });

  // 마커에 마우스오버 이벤트를 등록합니다
  kakao.maps.event.addListener(marker, "mouseover", function () {
    // 마커에 마우스오버 이벤트가 발생하면 인포윈도우를 마커위에 표시합니다
    if (!selectedMarker || selectedMarker !== marker) {
      for (let cur of allInfoWindow) {
        cur.close();
      }
      infowindow.open(map, marker);
    }
  });

  // 마커에 마우스아웃 이벤트를 등록합니다
  kakao.maps.event.addListener(marker, "mouseout", function () {
    // 마커에 마우스아웃 이벤트가 발생하면 인포윈도우를 제거합니다
    if (selectedMarker !== marker) {
      infowindow.close();
    }
    // selectedMarker = null;
  });
}

function selectInfo(_this) {
  let contentId = _this.parentElement.id;
  // 이동할 위도 경도 위치를 생성합니다

  let lng = gps[contentId][0];
  let lat = gps[contentId][1];

  var moveLatLon = new kakao.maps.LatLng(lng, lat);

  // 지도 중심을 이동 시킵니다
  map.setCenter(moveLatLon);
  map.setLevel(3);
}
