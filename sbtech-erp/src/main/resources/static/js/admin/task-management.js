document.addEventListener("DOMContentLoaded", () => {
    loadTasks();
    loadEmployees();
});

/* ✅ 공통 GET (SuccessResponse<T> 래핑 처리) */
async function apiGet(url) {
    const res = await fetch(url, {
        headers: {
            "Authorization": "Bearer " + localStorage.getItem("accessToken")
        }
    });

    if (res.status === 401) {
        alert("세션이 만료되었습니다. 다시 로그인해주세요.");
        window.location.href = "/web/login";
        return [];
    }

    const json = await res.json();
    return json.data ?? json;  // SuccessResponse<T> or 배열 둘 다 대응
}

/* ✅ LocalDate([yyyy,mm,dd]) → yyyy-MM-dd */
function formatDate(dateArray) {
    if (!dateArray) return "-";
    return `${dateArray[0]}-${String(dateArray[1]).padStart(2, '0')}-${String(dateArray[2]).padStart(2, '0')}`;
}

/* ✅ 상태 문자열(대기/진행 중/완료) → 뱃지 HTML */
function statusBadge(status) {
    let css = "";

    if (status === "대기") css = "waiting";
    else if (status === "진행 중") css = "in-progress";
    else if (status === "완료") css = "done";

    return `<span class="status ${css}">${status}</span>`;
}

/* ✅ 업무 리스트 조회 */
async function loadTasks() {
    const tasks = await apiGet(`/erp/api/v1/task`);
    if (!tasks) return;

    const taskTable = document.querySelector("#taskTable");
    taskTable.innerHTML = "";

    let pending = 0, inProgress = 0, done = 0;

    tasks.forEach(t => {

        // 🔹 한글 상태 기준으로 카운트
        if (t.status === "대기") pending++;
        else if (t.status === "진행 중") inProgress++;
        else if (t.status === "완료") done++;

        taskTable.innerHTML += `
            <tr>
                <td>${t.title}</td>
                <td>${t.assigneeName ?? '-'}</td>
                <td>${formatDate(t.dueDate)}</td>
                <td>${statusBadge(t.status)}</td>
            </tr>
        `;
    });

    document.getElementById("pendingCount").innerText = `${pending}건`;
    document.getElementById("inProgressCount").innerText = `${inProgress}건`;
    document.getElementById("doneCount").innerText = `${done}건`;
}

/* ✅ 담당자 목록 불러오기 */
async function loadEmployees() {
    const employees = await apiGet(`/erp/api/v1/employee/list/user?status=ACTIVE`);
    if (!employees) return;

    const select = document.getElementById("assigneeSelect");
    select.innerHTML = `<option value="">사원 선택</option>`;

    employees.forEach(emp => {
        select.innerHTML += `<option value="${emp.id}">${emp.name}</option>`;
    });
}

/* ✅ 업무 생성 */
async function assignTask() {
    const token = localStorage.getItem("accessToken");

    const body = {
        title: document.getElementById("taskTitle").value,
        description: "",
        assigneeId: document.getElementById("assigneeSelect").value,
        startDate: new Date().toISOString().slice(0, 10),
        dueDate: document.getElementById("dueDate").value,
        priority: "MEDIUM"   // ⚠️ 필요 없으면 이것도 삭제 가능
    };

    const res = await fetch(`/erp/api/v1/task`, {
        method: "POST",
        headers: {
            "Authorization": "Bearer " + token,
            "Content-Type": "application/json"
        },
        body: JSON.stringify(body)
    });

    if (res.ok) {
        alert("업무가 배정되었습니다 ✅");
        closeTaskModal();
        loadTasks();
    } else {
        alert("업무 배정 실패 ❌");
    }
}
/* ✅ 모달 제어 */
function openTaskModal() {
    document.getElementById("taskModal").classList.add("show");
}
function closeTaskModal() {
    document.getElementById("taskModal").classList.remove("show");
}
