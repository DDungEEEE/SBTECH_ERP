document.addEventListener("DOMContentLoaded", () => {
    loadTasks();
    loadEmployees();
});

/* ✅ LocalDate 배열 → yyyy-MM-dd 변환 */
function formatDate(dateArray) {
    if (!dateArray) return "-";
    return `${dateArray[0]}-${String(dateArray[1]).padStart(2, '0')}-${String(dateArray[2]).padStart(2, '0')}`;
}

/* ✅ 상태 Badge 변환 */
function statusBadge(status) {
    if (status === "대기") return `<span class="status PENDING">대기</span>`;
    if (status === "진행 중") return `<span class="status IN_PROGRESS">진행중</span>`;
    if (status === "완료") return `<span class="status DONE">완료</span>`;
    return `<span class="status">${status}</span>`;
}

/* ✅ 업무 리스트 조회 */
async function loadTasks() {
    const token = localStorage.getItem("accessToken");
    const res = await fetch(`/erp/api/v1/task`, {
        headers: { "Authorization": "Bearer " + token }
    });

    const tasks = await res.json(); // ← 배열 그대로

    const taskTable = document.querySelector("#taskTable");
    taskTable.innerHTML = "";

    let pending = 0, inProgress = 0, done = 0;

    tasks.forEach(t => {
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
    const token = localStorage.getItem("accessToken");
    const res = await fetch(`/erp/api/v1/employee/list/user?status=ACTIVE`, {
        headers: { "Authorization": "Bearer " + token }
    });

    const employees = await res.json();
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
        status: document.getElementById("statusSelect").value,
        priority: "MEDIUM"
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

/* ✅ 모달 */
function openTaskModal() {
    document.getElementById("taskModal").classList.add("show");
}
function closeTaskModal() {
    document.getElementById("taskModal").classList.remove("show");
}
