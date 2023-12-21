//写真をアップロード時にキャプションを表示する
function previewImage(event) {
	//アップロードした画像
    const preview = document.getElementById('image-preview');
    //初期表示の文字（or画像）
    const defaultText = document.getElementById('default-text');
    const file = event.target.files[0];
    const reader = new FileReader();
	//2.写真の読み込みが終了したらm￥、キャプションの表示切り替え
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
	//1.写真をアップロードした時に、ファイルを読み込む
    if (file) {
        reader.readAsDataURL(file);
    } else {
        preview.src = "";
        preview.style.display = 'none';
        defaultText.style.display = 'block';
    }
}


//金額について、正規表現でカンマ区切りの半角のみ受け付ける
const priceInput = document.getElementById('price');
if (priceInput) {
    priceInput.addEventListener('input', function (event) {
        let value = event.target.value;
        //全ての半角数字のみ受け付ける
        value = value.replace(/[^\d]/g, '');
        //3桁ごとにカンマ区切りにする
        value = value.replace(/\B(?=(\d{3})+(?!\d))/g, ',');
        event.target.value = value;
    });
}


