function changeTextColor(checkbox, labelId) {
    var label = document.getElementById(labelId);
    if (checkbox.checked) {
        // チェックボックスがチェックされたら、テキストの色を赤にする
        label.style.color = 'red';
    } else {
        // チェックボックスがチェックされていなければ、削除済みなら灰色、それ以外なら緑色に戻す
        label.style.color = label.innerText === '削除済み' ? '#CCC' : '#00FF00';
    }
}
function confirmStatusChange() {
    return confirm('ステータスを切り替えます。よろしいですか？');//アラートがうごかねぇ
}