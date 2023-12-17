function formSwitch() {
    // サイズの表示タイプ（セレクトボックスかチェックボックスか）に基づく表示の切り替え
    const isSelectChecked = document.querySelector('input[name="sizeDisplayType"][value="select"]').checked;
    const sizeSelect = document.getElementById('sizeSelect');
    const sizeCheck = document.getElementById('sizeCheck');

    if (sizeSelect && sizeCheck) {
        sizeSelect.style.display = isSelectChecked ? "" : "none";
        sizeCheck.style.display = isSelectChecked ? "none" : "";
    }

    // セレクトボックスのリセット
    if (!isSelectChecked) {
        const selectBoxes = sizeSelect.querySelectorAll('select');
        selectBoxes.forEach(select => {
            select.selectedIndex = 0;
        });
    }

    // チェックボックスのリセット
    if (isSelectChecked) {
        const checkboxes = sizeCheck.querySelectorAll('input[type="checkbox"]');
        checkboxes.forEach(checkbox => {
            checkbox.checked = false;
        });
    }
}

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

//submit押下時の処理1(regist,edit共通)
const registItem1Form = document.getElementById('registItem1');
if (registItem1Form) {
    registItem1Form.addEventListener('submit', function(event) {
        const selectCategoryElement = document.querySelector('.category-select');
        const selectedCategoryValue = selectCategoryElement.value;
        const errorMessageContainer = document.getElementById('error-message-container');
		//カテゴリー選択の表示のままだったら、エラーメッセージを表示
        if(selectedCategoryValue === "カテゴリーを選択") {
			// submitイベントの本来の動作を止める
            event.preventDefault();
            errorMessageContainer.textContent = 'カテゴリーを選択してください';
            errorMessageContainer.classList.remove('d-none');
        }
    });
}

//submit押下時の処理2(regist,edit共通)
const registItem2Form = document.getElementById('registItem2');
if (registItem2Form) {
    registItem2Form.addEventListener('submit', function(event) {
        const errorMessageContainer = document.getElementById('error-message-container2');

        // 第一のオプションの検証
        const firstOptionSelect = document.getElementById('optionSelect_1');
        if (firstOptionSelect && firstOptionSelect.value.includes("オプション選択")) {
			// submitイベントの本来の動作を止める
            event.preventDefault();
            errorMessageContainer.textContent = '第一のオプションを選択してください';
            errorMessageContainer.classList.remove('d-none');
            return;
        }

        // 第二のオプションの検証
        const sizeDisplayTypeElement = document.querySelector('input[name="sizeDisplayType"]:checked');
		const sizeDisplayType = sizeDisplayTypeElement ? sizeDisplayTypeElement.value : null;

        if (sizeDisplayType === 'select') {
            // セレクトボックスの検証
            const secondOptionSelect = document.getElementById('optionSelect_2');
            if (secondOptionSelect && secondOptionSelect.value.includes("オプション選択")) {
				// submitイベントの本来の動作を止める
                event.preventDefault();
                errorMessageContainer.textContent = '第二のオプションを選択してください';
                errorMessageContainer.classList.remove('d-none');
                return;
            }
        } else if (sizeDisplayType === 'checkbox') {
            // チェックボックスの検証
            const checkboxes = document.querySelectorAll('#sizeCheck input[type="checkbox"]');
            //Array.fromでチェックボックスを配列形式で取得。someでチェック状態を確認してbooleanで取り出す
            const isAnyChecked = Array.from(checkboxes).some(checkbox => checkbox.checked);
            if (!isAnyChecked) {
				// submitイベントの本来の動作を止める
                event.preventDefault();
                errorMessageContainer.textContent = '少なくとも一つのチェックボックスを選択してください';
                errorMessageContainer.classList.remove('d-none');
                return;
            }
        }

        // 新規登録の場合のみファイルのアップロードをチェック
        if (registItem2Form.getAttribute('action') === 'registItem2' && document.getElementById('registFormFile').files.length === 0) {
            event.preventDefault();
            errorMessageContainer.textContent = '写真をアップロードしてください';
            errorMessageContainer.classList.remove('d-none');
        }
    });
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


