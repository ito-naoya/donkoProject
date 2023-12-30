/**
 * 配送先登録用のバリューチェック
 */
document.getElementById('createButton').addEventListener('click', function(event) {
  
  // フォーム内の各要素を取得
  const addresseeInput = document.getElementById('exampleInputAddresses');
  const postcodeInput = document.getElementById('exampleInputPostCode');
  const addressInput = document.getElementById('exampleInputAddress');

  // 各要素の値を取得
  const addresseeValue = addresseeInput.value;
  const postcodeValue = postcodeInput.value;
  const addressValue = addressInput.value;
  
  // エラーメッセージの表示領域を取得
  let errorMessageAddressee = document.getElementById('errorMessageAddressee');
  let errorMessagePostalCode = document.getElementById('errorMessagePostalCode');
  let errorMessageAddress = document.getElementById('errorMessageAddress');
  
  // 宛名のNullチェック
  if (addresseeValue === null || addresseeValue.trim() === '') {
    // Nullまたは空白の場合
    errorMessageAddressee.textContent ='宛名を入力してください';
    // フォームの送信を防ぐ
    event.preventDefault();
  } else if (addresseeValue.length > 25) {
    // 25文字を超えた場合
    errorMessageAddressee.textContent = '25文字以内で入力してください。';
    event.preventDefault();
  } else {
    // 成功した場合
    errorMessageAddressee.textContent = '';
  }

// 郵便番号の Nullチェック
  if (postcodeValue === null || postcodeValue.trim() === '') {
    // Nullまたは空白の場合
    errorMessagePostalCode.textContent ='郵便番号を入力してください。';
    event.preventDefault();
  } else {
    // 成功した場合
    errorMessagePostalCode.textContent ='';
  }

// 住所のvalueチェック
if (addressValue === null || addressValue.trim() === '') {
  // Nullまたは空白の場合
  errorMessageAddress.textContent ='住所を入力してください。';
  event.preventDefault();
} else if (addressValue.length > 255) {
  // 255文字超えた場合
  errorMessageAddress.textContent ='255文字以内で入力してください。';
  event.preventDefault();
} else {
  // 成功した場合
  errorMessageAddress.textContent ='';
}

  // 全てのエラーメッセージが空の場合にフォームを送信
  if (
    errorMessageAddressee.textContent === '' &&
    errorMessagePostalCode.textContent === '' &&
    errorMessageAddress.textContent === ''
  ) {
    // フォーム送信処理を追加
    document.getElementById('createButton').submit();
  }
});
