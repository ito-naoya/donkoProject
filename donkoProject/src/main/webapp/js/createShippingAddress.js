/**
 * 配送先登録用のバリューチェック
 */
document.getElementById('createButton').addEventListener('click', function(event) {
    // フォーム内の各要素を取得
    const addresseeInput = document.getElementById('exampleInputAddresses');
    const postcodeInput = document.getElementById('exampleInputPostalCode');
    const addressInput = document.getElementById('exampleInputAddress');

    // 各要素の値を取得
    const addresseeValue = addresseeInput ? addresseeInput.value : null;
    const postcodeValue = postcodeInput ? postcodeInput.value : null;
    const addressValue = addressInput ? addressInput.value : null;
    
    // エラーメッセージの表示領域を取得
    let errorMessageAddressee = document.getElementById('errorMessageAddressee');
    let errorMessagePostalCode = document.getElementById('errorMessagePostalCode');
    let errorMessageAddress = document.getElementById('errorMessageAddress');
    
    // 宛名のNullチェック
    if (!addresseeValue || addresseeValue.trim() === '') {
        errorMessageAddressee.textContent = '宛名を入力してください。';
        event.preventDefault();
    } else if (addresseeValue.length > 25) {
        errorMessageAddressee.textContent = '25文字以内で入力してください。';
        event.preventDefault();
    } else {
        errorMessageAddressee.textContent = '';
    }
    
    // 郵便番号のNullチェック
    if (!postcodeValue || postcodeValue.trim() === '') {
        errorMessagePostalCode.textContent = '郵便番号を入力してください。';
        event.preventDefault();
    } else if (postcodeValue.length < 7 || postcodeValue.length > 7) {
        errorMessagePostalCode.textContent = '7文字で入力してください。';
        event.preventDefault();
    }else {
        errorMessagePostalCode.textContent = '';
    }
    
    // 住所のNullチェック
    if (!addressValue || addressValue.trim() === '') {
        errorMessageAddress.textContent = '住所を入力してください。';
        event.preventDefault();
    } else if (addressValue.length > 255) {
        errorMessageAddress.textContent = '255文字以内で入力してください。';
        event.preventDefault();
    } else {
        errorMessageAddress.textContent = '';
    }
    
    // フォーム送信処理を追加
    if (errorMessageAddressee.textContent === '' && errorMessagePostalCode.textContent === '' && errorMessageAddress.textContent === '') {
        // フォーム送信処理を追加
        document.getElementById('createButton').submit();
    }
});
