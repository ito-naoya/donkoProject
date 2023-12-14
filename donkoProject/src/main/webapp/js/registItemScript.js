
    function previewImage(event) {
        const preview = document.getElementById('image-preview');
        const defaultText = document.getElementById('default-text');
        const file = event.target.files[0];
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

        if (file) {
            reader.readAsDataURL(file);
        } else {
            preview.src = "";
            preview.style.display = 'none';
            defaultText.style.display = 'block';
        }
    }

    const registItem1Form = document.getElementById('registItem1');
    if (registItem1Form) {
        registItem1Form.addEventListener('submit', function(event) {
            const selectCategoryElement = document.querySelector('.category-select');
            const selectedCategoryValue = selectCategoryElement.value;
            const errorMessageContainer = document.getElementById('error-message-container');

            if(selectedCategoryValue === "カテゴリーを選択") {
                event.preventDefault();
                errorMessageContainer.textContent = 'カテゴリーを選択してください';
                errorMessageContainer.classList.remove('d-none');
            }
        });
    }

    const registItem2Form = document.getElementById('registItem2');
if (registItem2Form) {
    registItem2Form.addEventListener('submit', function(event) {
        const selectCategoryElement = document.querySelector('.option-select');
        const selectedCategoryValue = selectCategoryElement.value;
        const errorMessageContainer = document.getElementById('error-message-container2');

        if(selectedCategoryValue.includes("オプション選択")) {
            event.preventDefault();
            errorMessageContainer.textContent = 'オプションを選択してください';
            errorMessageContainer.classList.remove('d-none');
        }

        // 新規登録の場合のみファイルのアップロードをチェック
        if(registItem2Form.getAttribute('action') === 'registItem2' && document.getElementById('formFile').files.length === 0) {
            event.preventDefault();
            errorMessageContainer.textContent = '写真をアップロードしてください';
            errorMessageContainer.classList.remove('d-none');
        }
    });
}

    const priceInput = document.getElementById('price');
    if (priceInput) {
        priceInput.addEventListener('input', function (event) {
            let value = event.target.value;
            value = value.replace(/[^\d]/g, '');
            value = value.replace(/\B(?=(\d{3})+(?!\d))/g, ',');
            event.target.value = value;
        });
    }

