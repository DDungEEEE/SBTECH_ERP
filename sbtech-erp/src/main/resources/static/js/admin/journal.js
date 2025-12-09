const API_URL = "/erp/api/v1/journal-entry";
const ACCOUNT_API = "/erp/api/v1/ledger-accounts";
const TOKEN = localStorage.getItem("accessToken");

let journalData = [];
let ledgerAccounts = []; // 🔥 계정과목 목록 저장

// ===============================
// 📌 화면 로드시 자동 실행
// ===============================
document.addEventListener("DOMContentLoaded", async () => {
    await loadAccountsFromAPI();
    await loadJournalEntries();
    renderJournal();
});


// ===============================
// 📌 1. 전표 전체 조회 API
// ===============================
async function loadJournalEntries() {
    const res = await fetch(API_URL, {
        headers: { "Authorization": "Bearer " + TOKEN }
    });

    if (!res.ok) {
        alert("전표 데이터를 불러오지 못했습니다 ❌");
        return;
    }

    journalData = await res.json();
}


// ===============================
// 📌 2. 계정과목 목록 API
// ===============================
async function loadAccountsFromAPI() {
    const res = await fetch(ACCOUNT_API, {
        headers: { "Authorization": "Bearer " + TOKEN }
    });

    const body = await res.json();
    ledgerAccounts = body.data; // 🔥 서버 응답 구조 반영
}


// ===============================
// 📌 3. 전표 테이블 렌더링
// ===============================
function renderJournal(list = journalData) {
    const table = document.getElementById("journalTable");
    table.innerHTML = "";

    list.forEach(e => {
        e.lines.forEach(line => {
            table.innerHTML += `
                <tr>
                    <td>${e.entryDate}</td>
                    <td>JV-${String(e.id).padStart(4, "0")}</td>
                    <td>${line.accountName}</td>
                    <td>${line.debit > 0 ? line.debit.toLocaleString() : "-"}</td>
                    <td>${line.credit > 0 ? line.credit.toLocaleString() : "-"}</td>
                    <td>${(line.debit + line.credit).toLocaleString()}원</td>
                    <td>${e.writerName || "-"}</td>
                </tr>
            `;
        });
    });
}


// ===============================
// 📌 4. 검색 기능
// ===============================
function searchJournal(keyword) {
    keyword = keyword.trim();

    const filtered = journalData.filter(e =>
        e.description.includes(keyword) ||
        e.lines.some(l => l.accountName.includes(keyword))
    );

    renderJournal(filtered);
}


// ===============================
// 📌 5. 모달 열기 / 닫기
// ===============================
function openJournalModal() {
    document.getElementById("journalModal").classList.add("show");
}
function closeJournalModal() {
    document.getElementById("journalModal").classList.remove("show");
}


// ===============================
// 📌 6. 라인 추가 버튼
// ===============================
function addLineRow() {
    const tbody = document.getElementById("lineTableBody");

    tbody.insertAdjacentHTML("beforeend", `
      <tr>
        <td>
            <select class="acc-select">
                ${ledgerAccounts.map(a => `<option value="${a.id}">${a.name}</option>`).join("")}
            </select>
        </td>
        <td><input class="debit-input" type="number" min="0" oninput="calcSum()"></td>
        <td><input class="credit-input" type="number" min="0" oninput="calcSum()"></td>
        <td><button onclick="this.closest('tr').remove(); calcSum()">❌</button></td>
      </tr>
    `);

    calcSum();
}


// ===============================
// 📌 7. 차변/대변 합계 계산
// ===============================
function calcSum() {
    let d = 0, c = 0;

    document.querySelectorAll(".debit-input").forEach(i => d += Number(i.value || 0));
    document.querySelectorAll(".credit-input").forEach(i => c += Number(i.value || 0));

    document.getElementById("sumDebit").innerText = d.toLocaleString();
    document.getElementById("sumCredit").innerText = c.toLocaleString();
}


// ===============================
// 📌 8. 전표 저장 API 요청
// ===============================
async function saveJournal() {

    const desc = document.getElementById("entryDesc").value;
    const date = document.getElementById("entryDate").value;

    if (!desc || !date) return alert("전표 설명 및 날짜를 입력하세요");

    let lines = [];
    document.querySelectorAll("#lineTableBody tr").forEach(tr => {
        const accId = tr.querySelector(".acc-select").value;
        const debit = tr.querySelector(".debit-input").value;
        const credit = tr.querySelector(".credit-input").value;

        lines.push({
            accountId: Number(accId),
            debit: Number(debit),
            credit: Number(credit)
        });
    });

    if (lines.length === 0)
        return alert("최소 1개의 라인을 추가해야 합니다.");

    const payload = {
        entryDate: date,
        description: desc,
        lines: lines
    };

    console.log("📤 전송 데이터:", payload);

    const res = await fetch(API_URL, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Authorization": "Bearer " + TOKEN
        },
        body: JSON.stringify(payload)
    });

    if (!res.ok) {
        alert("전표 저장 실패 ❌");
        return;
    }

    alert("전표 저장 완료 ✅");
    closeJournalModal();
    await loadJournalEntries();
    renderJournal();
}
