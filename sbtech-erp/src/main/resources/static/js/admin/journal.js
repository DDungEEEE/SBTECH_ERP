// ✅ 계정 목록
const accounts = [
    "현금", "보통예금", "외상매출금", "외상매입금", "상품", "매출", "매출원가",
    "급여", "통신비", "차량유지비", "임차료", "감가상각비", "전기세", "수도세"
];

// ✅ 랜덤 직원
const employees = ["김민수", "박하늘", "최예준", "이도윤", "한서아", "정유진"];

// ✅ 랜덤 날짜
function randomRecentDate() {
    const today = new Date();
    const d = new Date(today - Math.random() * 30 * 24 * 60 * 60 * 1000);
    return d.toISOString().split("T")[0];
}

// ✅ 초기 전표 데이터
let journalData = Array.from({length: 20}, (_, i) => ({
    date: randomRecentDate(),
    no: `JV-${1000 + i}`,
    acc: accounts[Math.floor(Math.random() * accounts.length)],
    type: Math.random() < 0.5 ? "DEBIT" : "CREDIT",
    amount: (Math.floor(Math.random() * 50) + 1) * 10000,
    user: employees[Math.floor(Math.random() * employees.length)]
}));

// ✅ 화면 로드
document.addEventListener("DOMContentLoaded", () => {
    loadAccounts();
    renderJournal();
});

function loadAccounts() {
    const sel = document.getElementById("accountSelect");
    accounts.forEach(a => sel.innerHTML += `<option value="${a}">${a}</option>`);
}

function openJournalModal() {
    document.getElementById("journalModal").classList.add("show");
}
function closeJournalModal() {
    document.getElementById("journalModal").classList.remove("show");
}

// ✅ 랜더링
function renderJournal(list = journalData) {
    const table = document.getElementById("journalTable");
    table.innerHTML = "";

    list.sort((a,b)=> new Date(b.date)-new Date(a.date)).forEach(j => {
        table.innerHTML += `
        <tr>
            <td>${j.date}</td>
            <td>${j.no}</td>
            <td>${j.acc}</td>
            <td>${j.type === "DEBIT" ? j.amount.toLocaleString() : "-"}</td>
            <td>${j.type === "CREDIT" ? j.amount.toLocaleString() : "-"}</td>
            <td>${j.amount.toLocaleString()}원</td>
            <td>${j.user}</td>
        </tr>`;
    });
}

// ✅ 저장
function saveJournal() {
    const acc = document.getElementById("accountSelect").value;
    const type = document.getElementById("typeSelect").value;
    const amount = document.getElementById("amountInput").value;

    if (!acc || !amount) {
        return alert("계정과 금액을 입력하세요!");
    }

    journalData.unshift({
        date: new Date().toISOString().split("T")[0],
        no: `JV-${1000 + journalData.length}`,
        acc, type,
        amount: Number(amount),
        user: employees[Math.floor(Math.random() * employees.length)]
    });

    closeJournalModal();
    renderJournal();
    alert("전표 등록 완료 ✅");
}

// ✅ 검색
function searchJournal(keyword) {
    const filtered = journalData.filter(j => j.acc.includes(keyword) || j.no.includes(keyword));
    renderJournal(filtered);
}
