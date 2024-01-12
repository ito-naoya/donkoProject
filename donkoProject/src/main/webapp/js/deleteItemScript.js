// チェックボックスのクリックイベントを取得して処理する関数
function checkboxClicked(event) {
	event.stopPropagation();
 }

let selectedRow = null;
function changeColor(row) {
    // 以前に選択された行があれば、クラスを削除
    if (selectedRow) {
        selectedRow.classList.remove('selected-row');
    }
    // 新しい行にクラスを追加し、選択された行を記録
    row.classList.add('selected-row');
    selectedRow = row;
}

// ページが読み込まれた後に実行
window.onload = function() {
   // チェックボックス要素をすべて取得
   const checkboxes = document.querySelectorAll('input[type="checkbox"][name="itemStatus"]');
   // 各チェックボックスに対してイベントリスナーを設定
   checkboxes.forEach(function(checkbox) {
       checkbox.addEventListener('click', checkboxClicked);
   });
};