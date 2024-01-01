/**
 * マイページ関連のJSファイル
 * 
 */

// 発送済み非表示機能
document.addEventListener("DOMContentLoaded", function () {
    const hideShippedButton = document.getElementById("hideShippedButton");

// ボタンを押した時に表示を切り替える
    hideShippedButton.addEventListener("click", function () {
        const tableRows = document.querySelectorAll("table.purchaseDetailTable tbody tr");

        if (hideShippedButton.innerText === "発送済みを非表示") {
            for (let i = 0; i < tableRows.length; i++) {
                const shippingStatusCell = tableRows[i].cells[4].innerText.trim(); // 4は配送ステータスの列に対応するインデックス
                if (shippingStatusCell == "発送済み") {
                    tableRows[i].style.display = "none";
                }
            }
            hideShippedButton.innerText = "全て表示";
        } else {
            for (let i = 0; i < tableRows.length; i++) {
                tableRows[i].style.display = ""; // デフォルトの表示に戻す
            }
            hideShippedButton.innerText = "発送済みを非表示";
        }
    });
});

// 退会確認画面のモーダルを表示
document.getElementById("deleteUserButton").addEventListener("click", function() {
  // ユーザ情報のモーダルを非表示にする
  document.getElementById("userInfoWindow").style.display = "none";
  // 確認画面のモーダルを表示する
  document.getElementById("staticBackdrop").style.display = "";

});

// ユーザ情報の確認画面のモーダルを表示
document.getElementById("cancelButton").addEventListener("click", function() {
  // 確認画面のモーダルを非表示にする
  document.getElementById("staticBackdrop").style.display = "none";
    // ユーザ情報のモーダルを表示する
  document.getElementById("userInfoWindow").style.display = "";
});
