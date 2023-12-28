
function confirmStatusChange() {
    return confirm('ステータスを切り替えます。よろしいですか？');
}

// チェックボックスのクリックイベントを取得して処理する関数
function checkboxClicked(event) {
	event.stopPropagation();
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