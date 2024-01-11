// 発送済み非表示機能
document.addEventListener("DOMContentLoaded", function() {
  const hideShippedButton = document.getElementById("hideShippedButton");
  const thead = document.getElementById("thead");
  const tbody = document.querySelector("table.purchaseDetailTable tbody");
  // テーブルの各行を取得
  const tableRows = document.querySelectorAll("table.purchaseDetailTable tbody tr");

  // ボタンを押した時に表示を切り替える
  hideShippedButton.addEventListener("click", function() {
    let statusCounts = 0;

    if (hideShippedButton.innerText === "発送済みを非表示") {
      for (let i = 0; i < tableRows.length; i++) {
        const shippingStatusCell = tableRows[i].cells[4].innerText.trim(); // 4は配送ステータスの列に対応するインデックス
        if (shippingStatusCell === "発送済み") {
          tableRows[i].style.display = "none";
          statusCounts++
        }
      }
      // ソートを実施したときに購入履歴が0件の場合、タイトルも非表示
      if (tableRows.length === statusCounts) {
        thead.style.display = "none";
        // 新しい行を作成してメッセージを挿入
        const borderElement = document.getElementById("border");
        // メッセージを表示
        borderElement.innerHTML =
          `<div class="d-flex justify-content-center align-items-center" style="height:100%; color:lightgray;">
             <p class="mb-0">購入履歴はありません</p>
           </div>`;
      }
      hideShippedButton.innerText = "全て表示";
    } else {
      // メッセージが表示されている場合は非表示にする
      const existingMessage = document.querySelector("#border .d-flex");
      if (existingMessage) {
        existingMessage.remove();
      }
      if (tableRows.length === statusCounts) {
        thead.style.display = "";
      }
      // デフォルトの表示に戻す
      for (let i = 0; i < tableRows.length; i++) {
        tableRows[i].style.display = "";
      }
      thead.style.display = "";
      hideShippedButton.innerText = "発送済みを非表示";
    }
  });
});
