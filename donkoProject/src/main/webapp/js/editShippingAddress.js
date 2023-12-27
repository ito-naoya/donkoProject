/**
 * 配送先編集用のバリューチェック
 */
document.getElementById('editButton').addEventListener('click', function(event) {
  
  // フォーム内の各要素を取得
  const postcodeInput = document.getElementById('exampleInputPostalCode');

  // 各要素の値を取得
  const postcodeValue = postcodeInput.value;
  
  // エラーメッセージの表示領域を取得
  let errorMessagePostalCode = document.getElementById('errorMessagePostalCode');
  
// 郵便番号の Nullチェック
  if (postcodeValue.length != 7 || isNaN(postcodeValue)) {
    // 入力した文字が7桁超えの場合
    errorMessagePostalCode.textContent ='半角数字の7桁で入力してください。';
    event.preventDefault();
  } else {
    // 成功した場合
    errorMessagePostalCode.textContent ='';
  }
});
