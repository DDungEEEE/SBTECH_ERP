// ✅ 날짜 생성 함수 (최근 30일 랜덤)
function randomRecentDate() {
    const today = new Date();
    const past = new Date(today);
    past.setDate(today.getDate() - Math.floor(Math.random() * 30));

    return past.toISOString().split("T")[0];
}

// ✅ 직원 목록 (랜덤 담당자용)
const employees = [
    "김민수", "이서준", "박현우", "최다은", "정지후",
    "김현지", "오세연", "이도윤", "한서아", "윤지환",
    "박하늘", "정유진", "김하린", "최예준", "오지원",
    "서민혁", "하유진", "강주원", "배소율", "장준호"
];

// ✅ 랜덤 직원 반환
function randomEmployee() {
    return employees[Math.floor(Math.random() * employees.length)];
}

// ✅ 더미 품목 목록
const items = [
    "리튬 배터리 A123", "파워 셀 B55", "냉각 모듈 C100", "컨트롤러 메인보드 X92",
    "고전력 인버터 P77", "고효율 모터 T22", "센서 유닛 U11", "배선 하니스 K33",
    "스위치 모듈 S88", "냉각팬 F20", "전류 센서 CS50", "회로 보호 퓨즈 FZ-18",
    "히트싱크 HS-250", "전력 변환 PCB V3", "배터리 모듈 BM-4", "고내열 케이블 C22",
    "통신 모듈 CM-3", "강철 하우징 SH-82", "접착제 A88", "절연 필름 IF-10"
];

// ✅ 더미 입/출고 기록 (최근 날짜 자동)
const seedData = [
    { item: "리튬 배터리 A123", type: "IN", qty: 50 },
    { item: "파워 셀 B55", type: "OUT", qty: 5 },
    { item: "냉각 모듈 C100", type: "IN", qty: 30 },
    { item: "센서 유닛 U11", type: "IN", qty: 100 },
    { item: "고효율 모터 T22", type: "OUT", qty: 2 },
    { item: "컨트롤러 메인보드 X92", type: "OUT", qty: 1 },
    { item: "배선 하니스 K33", type: "IN", qty: 200 },
    { item: "냉각팬 F20", type: "IN", qty: 80 },
    { item: "전류 센서 CS50", type: "OUT", qty: 10 },
    { item: "히트싱크 HS-250", type: "IN", qty: 60 },
    { item: "전력 변환 PCB V3", type: "OUT", qty: 12 },
    { item: "배터리 모듈 BM-4", type: "IN", qty: 15 },
    { item: "강철 하우징 SH-82", type: "OUT", qty: 8 },
    { item: "통신 모듈 CM-3", type: "IN", qty: 25 },
    { item: "접착제 A88", type: "IN", qty: 40 },
    { item: "절연 필름 IF-10", type: "OUT", qty: 90 },
    { item: "스위치 모듈 S88", type: "IN", qty: 30 },
    { item: "히트싱크 HS-250", type: "OUT", qty: 10 },
    { item: "냉각 모듈 C100", type: "IN", qty: 25 },
    { item: "전류 센서 CS50", type: "IN", qty: 50 }
];

// ✅ 날짜 + 담당자 포함된 실제 리스트 생성
let inoutHistory = seedData.map(d => ({
    date: randomRecentDate(),
    ...d,
    user: randomEmployee()
}));

// ✅ select에 품목 채우기
function loadItems() {
    const select = document.getElementById("itemSelect");
    select.innerHTML = `<option value="">품목 선택</option>`;
    items.forEach(i => select.innerHTML += `<option value="${i}">${i}</option>`);
}

// ✅ 테이블 렌더링
function renderInOut() {
    const table = document.getElementById("inoutTable");
    table.innerHTML = "";

    inoutHistory
        .sort((a, b) => new Date(b.date) - new Date(a.date)) // 최근순
        .forEach(r => {
            table.innerHTML += `
        <tr>
            <td>${r.date}</td>
            <td>${r.item}</td>
            <td><span class="badge ${r.type === "IN" ? "green" : "red"}">${r.type === "IN" ? "입고" : "출고"}</span></td>
            <td>${r.qty}</td>
            <td>${r.user}</td>
        </tr>`;
        });
}

// ✅ 기록 저장 (등록 시에도 랜덤 직원)
function saveInOut() {
    const item = document.getElementById("itemSelect").value;
    const qty = document.getElementById("qtyInput").value;
    const type = document.getElementById("typeSelect").value;

    if (!item || !qty) return alert("품목과 수량을 입력하세요!");

    inoutHistory.unshift({
        date: new Date().toISOString().split("T")[0],
        item, type, qty: Number(qty), user: "관리자"
    });

    renderInOut();
    document.getElementById("qtyInput").value = "";
    alert("입·출고가 등록되었습니다 ✅");
}

// ✅ 초기 로드
document.addEventListener("DOMContentLoaded", () => {
    loadItems();
    renderInOut();
});
