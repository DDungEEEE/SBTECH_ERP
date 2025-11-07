document.addEventListener("DOMContentLoaded", function () {
    loadEmployees();
});

async function loadEmployees() {
    const token = localStorage.getItem("accessToken");

    const res = await fetch('/erp/api/v1/employee/list', {
        headers: {
            "Authorization": "Bearer " + token
        }
    });

    if (!res.ok) {
        console.error("API 호출 실패", res.status);
        return;
    }

    const json = await res.json();
    console.log("응답 데이터:", json); // 🔍 확인용

    const employees = json.data;
    const workingTable = document.querySelector("#employeeTable tbody");
    const pendingTable = document.querySelector("#pendingTable tbody");

    workingTable.innerHTML = "";
    pendingTable.innerHTML = "";

    employees.forEach(e => {
        if (e.employeeStatus === "재직") {
            workingTable.innerHTML += `
                <tr>
                    <td>${e.id}</td>
                    <td>${e.name}</td>
                    <td>${e.loginId}</td>
                    <td>${e.departmentName ?? "-"}</td>
                    <td>${e.positionName ?? "-"}</td>
                    <td>-</td>
                    <td>${e.employeeStatus}</td>
                </tr>
            `;
        } else if (e.employeeStatus === "승인 대기") {
            pendingTable.innerHTML += `
                <tr>
                    <td>${e.name}</td>
                    <td>${e.loginId}</td>
                    <td>${e.departmentName ?? "-"}</td>
                    <td>${e.positionName ?? "-"}</td>
                    <td>${e.employeeStatus}</td>
                    <td>
                        <button onclick="approveEmployee(${e.id})">승인</button>
                    </td>
                </tr>
            `;
        }
    });
}

function approveEmployee(id) {
    // TODO: 승인 API 호출 넣으면 됨
    alert(id + " 승인 처리!");
}
