let serviceKey =
  "HGWbiXI4puxU2CzKvcLxn5CesxFWEHG%2F49E1OBVZH4N3flzq0q%2B442V4Ii2Bk43fuLm0jmkitNv5Qaxxb1U6ag%3D%3D";
let key = "dlrjtdmsdlatlfhqkfrmqgkszldlqslek"; // 임시로 하드코딩한 API 키
let domain = "http://localhost:8080";

window.onload = () => {
  let contentId = new URL(window.location.href).searchParams.get("contentId");

  if (!contentId) {
    console.log("contentId 가 url에 존재하지 않습니다.");
    window.location.href = `${domain}/${root}/home?action=error`;
    return;
  }

  let url = `${domain}/${root}/api?keyword=detail&contentId=${contentId}&key=${key}`;

  fetch(url)
    .then((response) => response.json())
    .then((data) => {
      let item = data;

      let title = item.title;
      let description = item.overview;
      let image = item.firstImage;
      let lat = item.lat;
      let lng = item.lng;
      let addr1 = item.addr1;
      let addr2 = item.addr2;

      let mainImg = document.getElementById("bannerImg");
      mainImg.setAttribute("src", image);

      var container = document.getElementById("map");
      var options = {
        center: new kakao.maps.LatLng(lat, lng),
        level: 5,
      };

      // 마커가 표시될 위치입니다
      var markerPosition = new kakao.maps.LatLng(lat, lng);

      // 마커를 생성합니다
      var marker = new kakao.maps.Marker({
        position: markerPosition,
      });

      var map = new kakao.maps.Map(container, options);

      marker.setMap(map);

      document.getElementById("description-title").innerText = title;
      document.getElementById("description-subtext").innerHTML = description;
      document.getElementById("addr").innerText = addr1 + " " + addr2;
      document.getElementById("detailTitle").innerText = title;
    });

  // 이미지를 비동기로 가져오기
  let imgUrl = `https://apis.data.go.kr/B551011/KorService1/detailImage1?serviceKey=${serviceKey}&MobileOS=ETC&MobileApp=AppTest&_type=json&contentId=${contentId}&imageYN=Y&subImageYN=Y&numOfRows=10&pageNo=1`;
  fetch(imgUrl)
    .then((response) => response.json())
    .then((data) => {
      let imgs = data.response.body.items.item;

      let imgContainer = document.getElementById("imgContainer");
      for (let item of imgs) {
        let img = item.originimgurl;

        imgContainer.innerHTML += `<div><img src="${img}" alt=""></div>`;
      }
    });
};
