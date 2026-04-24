// CREATE 부분
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
        url: window.CREATE_Async_URL,
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

function submitJsonAxios() {
    var payload = {
        name: $('#name').val(),
        age: parseInt ($('#age').val(), 10),
        birthDate: $('#birthDate').val(),
        address: $('#address').val()
    };

    axios.post(window.CREATE_Async_URL, payload, {
        headers: {
            'Content-Type': 'application/json; charset=UTF-8'
        }
    })
    .then(function (response) {
        $('#result').text('Axios 비동기 성공: ' + response.data);
    })
    .catch(function (error) {
        var msg = (error.response && error.response.data)
            ? error.response.data
            : error.message;
        $('#result').text('Axios 비동기 실패: ' + msg);
    });
}

function submitJsonFetch() {
    var payload = {
        name: $('#name').val(),
        age: parseInt($('#age').val(), 10),
        birthDate: $('#birthDate').val(),
        address: $('#address').val()
    };

    fetch(window.CREATE_Async_URL, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json; charset=UTF-8'
        },
        body: JSON.stringify(payload)
    })
    .then(function (res) {
        if (!res.ok) {
            throw new Error('HTTP ' + res.status);
        }
        return res.text() // 컨트롤러가 int를 반환해서 res.text 사용
    })
    .then(function (data) {
        $('#result').text('Fetch 비동기 성공: ' + data);
    })
    .catch(function (err) {
        $('#result').text('Fetch 비동기 실패: ' + err.message)
    });
}

function loadDetailByAjax(id) {
    $.ajax({
        url: window.USER_AJAX_DETAIL + '/' + id,
        type: 'GET',
        dataType: 'json',
        success: function (data) {
            $('#result').text(JSON.stringify(data, null, 2));
        },
        error: function (xhr) {
            $('#result').text('단일 조회 실패: ' + (xhr.responseText || xhr.status))
        }
    });
}

function loadDetailByAxios(id) {
    axios.get(window.USER_AXIOS_DETAIL + '/' + id)
        .then(function (response) {
            $('#result').text(JSON.stringify(response.data, null, 2));
        })
        .catch(function (error) {
            var msg = (error.response && error.response.data)
            ? error.response.data
            : error.message;
            $('#result').text('Axios 단일 조회 실패: ' + msg);
        });
}



