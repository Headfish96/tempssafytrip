let serviceKey =
  "HGWbiXI4puxU2CzKvcLxn5CesxFWEHG%2F49E1OBVZH4N3flzq0q%2B442V4Ii2Bk43fuLm0jmkitNv5Qaxxb1U6ag%3D%3D";

// 페이지 로딩 후 동작 지정
window.onload = function () {
  var mySwiper = new Swiper(".swiper-container", {
    // 3초마다 자동으로 슬라이드가 넘어갑니다. 1초 = 1000
    autoplay: {
      delay: 3000,
    },
  });
};

function locateDetail(_this) {
  let contentId = _this.id;
  window.location.href = `${root}/detail?contentId=${contentId}`;
}