
function confirmStatusChange() {
    return confirm('ステータスを切り替えます。よろしいですか？');
}

function submitPostForm(optionCategoryName) {
    document.getElementById('postForm' + optionCategoryName).submit();
}
