window.onload = function() {
    // 初期文字数をカウントして表示する関数
    function updateCharacterCount() {
        const itemNameInput = document.getElementById('itemName');
        const itemNameCount = itemNameInput.value.length;
        const itemNameMaxLength = itemNameInput.maxLength;
        document.getElementById('itemNameCount').textContent = `${itemNameCount}/${itemNameMaxLength}`;

        const itemDescriptionInput = document.getElementById('itemDescription');
        const itemDescriptionCount = itemDescriptionInput.value.length;
        const itemDescriptionMaxLength = itemDescriptionInput.maxLength;
        document.getElementById('itemDescriptionCount').textContent = `${itemDescriptionCount}/${itemDescriptionMaxLength}`;
    }

    // ページ読み込み時に文字数を更新
    updateCharacterCount();

    // 入力時のイベントリスナーを設定
    document.getElementById('itemName').addEventListener('input', updateCharacterCount);
    document.getElementById('itemDescription').addEventListener('input', updateCharacterCount);
};

function previewImage(event) {
    const preview = document.getElementById('image-preview');
    const defaultText = document.getElementById('default-text');
    const file = event.target.files[0];

    // ファイル検証
    const validationError = validateFile(file);
    if (validationError) {
        defaultText.textContent = validationError; // default-textにエラーメッセージを表示
        defaultText.style.display = 'block';
        preview.style.display = 'none'; // 画像プレビューを非表示に
        event.target.value = ''; // ファイル入力をリセット
        return; // 処理を中断
    }

    const reader = new FileReader();
    reader.onloadend = function() {
        if (file) {
            preview.src = reader.result;
            preview.style.display = 'block';
            defaultText.style.display = 'none';
        } else {
            preview.src = "";
            preview.style.display = 'none';
            defaultText.style.display = 'block';
        }
    }
    reader.readAsDataURL(file);
}


//各種入力チェック
function validateFile(file) {
    const fileSizeLimit = 2 * 1024 * 1024; // 2MB
     // ファイルサイズのチェック
    if (file.size > fileSizeLimit) {
        return "ファイルサイズは2MB以下にしてください。";
    }
}


//金額について、正規表現でカンマ区切りの半角のみ受け付ける
const priceInput = document.getElementById('price');
if (priceInput) {
    priceInput.addEventListener('input', function (event) {
        let value = event.target.value;
        // 最初の0を削除
        value = value.replace(/^0+/, '');
        // 全ての非数字を削除
        value = value.replace(/[^\d]/g, '');
        // 3桁ごとにカンマ区切りにする
        value = value.replace(/\B(?=(\d{3})+(?!\d))/g, ',');
        event.target.value = value;
    });
}