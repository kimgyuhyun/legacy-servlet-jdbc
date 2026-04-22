function submitFormAjax() {
    $.ajax({
        url: window.CREATE_FORM_AJAX_URL,
        type: 'POST',
        data: $('#userForm').serialize(),
        success: function (res) {
            $('#result').text('Form 비동기 성공: ' + res);
        },
        error: function (xhr) {
            $('#result').text('Form 비동기 실패: ' + (xhr.responseText || xhr.status));
        }
    });

}