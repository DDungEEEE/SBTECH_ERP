const sampleInventory = [
    { name: "리튬 배터리 A123", category: "배터리", qty: 520, price: 1200000 },
    { name: "파워 셀 B55", category: "배터리", qty: 50, price: 2500000 },
    { name: "냉각 모듈 C100", category: "파츠", qty: 310, price: 180000 },
    { name: "컨트롤러 메인보드 X92", category: "전자부품", qty: 22, price: 4500000 },
    { name: "고전력 인버터 P77", category: "전력장치", qty: 140, price: 3200000 },
    { name: "고효율 모터 T22", category: "모터", qty: 8, price: 9800000 },
    { name: "센서 유닛 U11", category: "센서", qty: 450, price: 560000 },
    { name: "배선 하니스 K33", category: "케이블", qty: 1200, price: 24000 },
    { name: "스위치 모듈 S88", category: "전자부품", qty: 330, price: 87000 },
    { name: "열 관리 패드 TH-500", category: "파츠", qty: 75, price: 34000 },
    { name: "냉각팬 F20", category: "파츠", qty: 450, price: 27000 },
    { name: "전류 센서 CS50", category: "센서", qty: 160, price: 90000 },
    { name: "회로 보호 퓨즈 FZ-18", category: "전기부품", qty: 30, price: 5000 },
    { name: "제어 소프트웨어 키", category: "소프트웨어", qty: 200, price: 1500000 },
    { name: "고무 실링 G11", category: "파츠", qty: 900, price: 1200 },
    { name: "히트싱크 HS-250", category: "파츠", qty: 55, price: 78000 },
    { name: "강화 알루미늄 프레임 AF55", category: "프레임", qty: 12, price: 12000000 },
    { name: "전력 변환 PCB V3", category: "PCB", qty: 500, price: 200000 },
    { name: "배터리 모듈 BM-4", category: "배터리", qty: 70, price: 3200000 },
    { name: "파워 컨버터 PCX-12", category: "전력장치", qty: 15, price: 8800000 },
    { name: "온도 센서 T90", category: "센서", qty: 1400, price: 17000 },
    { name: "볼트 세트 B20", category: "파츠", qty: 2600, price: 150 },
    { name: "고전류 릴레이 RL-80", category: "전기부품", qty: 44, price: 120000 },
    { name: "디스플레이 패널 DP70", category: "디스플레이", qty: 19, price: 7500000 },
    { name: "통신 모듈 CM-3", category: "통신", qty: 88, price: 1450000 },
    { name: "강철 하우징 SH-82", category: "하우징", qty: 300, price: 90000 },
    { name: "고내열 케이블 C22", category: "케이블", qty: 510, price: 6000 },
    { name: "접착제 A88", category: "소모품", qty: 170, price: 9000 },
    { name: "절연 필름 IF-10", category: "소모품", qty: 2100, price: 2500 },
    { name: "스테인리스 브라켓 ST30", category: "파츠", qty: 42, price: 70000 }
];


function loadInventory() {
    const table = document.getElementById("inventoryTable");
    table.innerHTML = "";

    sampleInventory.forEach(item => {
        const value = item.qty * item.price;
        const status = item.qty < 100 ? `<span class="badge danger">부족</span>` : `<span class="badge ok">정상</span>`;
        table.innerHTML += `
          <tr>
            <td>${item.name}</td>
            <td>${item.category}</td>
            <td>${item.qty}</td>
            <td>${item.price.toLocaleString()}원</td>
            <td>${value.toLocaleString()}원</td>
            <td>${status}</td>
          </tr>`;
    });
}

function searchInventory(keyword) {
    const filtered = sampleInventory.filter(i => i.name.includes(keyword));
    const table = document.getElementById("inventoryTable");
    table.innerHTML = "";
    filtered.forEach(i => {
        table.innerHTML += `<tr><td>${i.name}</td><td>${i.category}</td><td>${i.qty}</td><td>${i.price}</td><td>-</td><td>-</td></tr>`;
    });
}

document.addEventListener("DOMContentLoaded", loadInventory);
