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

function submitJsonAjax() {
    var payload = {
        name: $('#name').val(),
        age: parseInt($('#age').val(), 10),
        birthDate: $('#birthDate').val(),
        address: $('#address').val()
    };
    $.ajax({
        url: window.CREATE_JSON_AJAX_URL,
        type: 'POST',
        contentType: 'application/json; charset=UTF-8',
        data: JSON.stringify(payload),
        success: function (res) {
            $('#result').text('JSON 비동기 성공: ' + res);
        },
        error: function (xhr) {
            $('#result').text('JSON 비동기 실패: ' + (xhr.responseText || xhr.status));
        }
    });
}