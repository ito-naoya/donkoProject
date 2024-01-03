
function confirmStatusChange() {
    return confirm('ステータスを切り替えます。よろしいですか？');
}

function submitPostForm(optionCategoryName) {
    document.getElementById('postForm' + optionCategoryName).submit();
}

document.addEventListener('DOMContentLoaded', function () {
    var showModal = document.getElementById('modalTrigger').getAttribute('data-show-modal') === 'true';
    if (showModal) {
        var myModal = new bootstrap.Modal(document.getElementById('modalOptionAdd'));
        myModal.show();
    }
});
