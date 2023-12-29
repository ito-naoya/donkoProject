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

function deleteUser() {
//
const modal = document.querySelector('.js-modal');
const modalButton = document.querySelector('.deleteUserButton');
const modalClose = document.querySelector('.deleteUserCloseButton'); // xボタンのjs-close-buttonを取得し変数に格納

// 
modalButton.addEventListener('click', () => {
  modal.classList.add('is-open');
});

// 
modalClose.addEventListener('click', () => { 
  // xボタンをクリックしたときのイベントを登録
  modal.classList.remove('is-open'); 
});
};