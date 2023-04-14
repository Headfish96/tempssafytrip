// 전역변수 - start
let serviceKey =
  "HGWbiXI4puxU2CzKvcLxn5CesxFWEHG%2F49E1OBVZH4N3flzq0q%2B442V4Ii2Bk43fuLm0jmkitNv5Qaxxb1U6ag%3D%3D";
let key = "dlrjtdmsdlatlfhqkfrmqgkszldlqslek"; // 임시 발급 api키
let domain = "http://localhost:8080";

let typeToTitle = {
  popular: "인기 여행지",
  festival: "축제",
  culture: "문화시설",
  leisureSports: "레포츠",
};
let page;
// 전역변수 - end

// onload 이벤트 - start
window.onload = () => {
  let type = new URL(window.location.href).searchParams.get("type");
  let url = `${domain}/${root}/api?keyword=list&type=${type}&key=${key}`;

  insertTitle(type);

  fetch(url)
    .then((response) => response.json())
    .then((data) => {
      let items = data;

      for (let item of items) {
        let sectionEl = document.getElementById("list-section");
        sectionEl.innerHTML += `
          <div class="list-container" id=${item.contentId}>
              <div class="list-img"><img src="${item.firstImage}" alt="" /></div>
              <div class="list-discription-container">
                  <h2 class="list-title">${item.title}</h2>
                  <div class="list-text">${item.overview}</div>
                  <button class="like-btn" onclick="moveDetail(this)">상세 보기</button>
              </div>
          </div>
        `;
      }
    });
};
// onload 이벤트 - end

function insertTitle(type) {
  document.getElementById("headTitle").innerText = typeToTitle[type];
  document.getElementById("pageTitle").innerText = typeToTitle[type];
}

function moveDetail(_this) {
  let contentId = _this.parentElement.parentElement.id;
  window.location.href = `${domain}/${root}/detail?contentId=${contentId}`;
}

// 스크롤 이벤트
window.addEventListener("scroll", function () {
  // 현재 스크롤 위치
  var scrollPosition = window.scrollY;

  // 전체 문서 높이
  var documentHeight = document.body.scrollHeight;

  // 현재 브라우저 높이
  var windowHeight = window.innerHeight;

  // 스크롤이 제일 마지막에 도달했는지 확인
  if (scrollPosition + windowHeight > documentHeight) {
    console.log("a");
  }
});
