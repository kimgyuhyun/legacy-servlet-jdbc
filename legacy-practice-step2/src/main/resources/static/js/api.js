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

// Read 부분

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

function loadDetailByFetch(id) {
    fetch(window.USER_FETCH_DETAIL + '/' + id, {
        method: 'GET'
    })
    .then(function (response) {
        if (!response.ok) {
            throw new Error('HTTP ' + response.status);
        }
        return response.json();
    })
    .then(function (data) {
        $('#result').text(JSON.stringify(data, null, 2));
    })
    .catch(function (error) {
        $('#result').text('Fetch 단일 조회 실패: ' + error.message);
    });
}

function renderUserList(list) {
    var html = '';

    if (!list || list.length == 0) {
        html = '<tr><td colspan="8">조회된 사용자가 없습니다.</td></tr>';
        $('#userTableBody').html(html);
        return;
    }

    list.forEach(function (user) {
        html += '<tr>'
            + '<td>' + user.id + '</td>'
            + '<td>' + user.name + '</td>'
            + '<td>' + user.age + '</td>'
            + '<td>' + user.birthDate + '</td>'
            + '<td>' + user.address + '</td>'
            + '<td><button type="button" onclick="loadDetailByAjax(' + user.id + ')">Ajax 상세보기</button></td>'
            + '<td><button type="button" onclick="loadDetailByAxios(' + user.id + ')">Axios 상세보기</button></td>'
            + '<td><button type="button" onclick="loadDetailByFetch(' + user.id + ')">Fetch 상세보기</button></td>'
            + '</tr>'
    });

    $('#userTableBody').html(html);
}

function loadListByAjax() {
    $.ajax({
        url: window.USER_AJAX_LIST_URL,
        type: 'GET',
        dataType: 'json',
        success: function(list) {
            renderUserList(list);
            $('#result').text('Ajax 목록 조회 성공: ' + list.length + '건');
        },
        error: function (xhr) {
            $('#result').text('Ajax 목록 조회 실패: ' + (xhr.responseText || xhr.status));
        }
    });
}


function loadListByAxios() {
    axios.get(window.USER_AXIOS_LIST_URL)
        .then(function (response) {
            var list = response.data;
            renderUserList(list);
            $('#result').text('Axios 목록 조회 성공: ' + list.length + '건');
        })
        .catch(function (error) {
            var msg = (error.response && error.response.data)
                ? error.response.data
                : error.message;
            $('#result').text('Axios 목록 조회 실패: ' + msg);
        });
}

function loadListByFetch() {
    fetch(window.USER_FETCH_LIST_URL, {
        method: 'GET'
    })
    .then(function (response) {
        if (!response.ok) {
            throw new Error('HTTP ' + response.status);
        }
        return response.json();
    })
    .then(function (list) {
        renderUserList(list);
        $('#result').text('Fetch 목록 조회 성공: ' + list.length + '건');
    })
    .catch(function (error) {
        $('#result').text('Fetch 목록 조회 실패: ' + error.message);
    });
}


// update 부분

function updateByAjaxForm() {
    $.ajax({
        url: window.USER_UPDATE_AJAX_FORM_URL,
        type: 'POST',
        data: $('#updateForm').serialize(),
        success: function (res) {
            $('#result').text('Ajax serialize 수정 성공: ' + res);
        },
        error: function(xhr) {
            $('#result').text('Ajax serialize 수정 실패: ' + (xhr.responseText || xhr.status));
        }
    });
}

function updateByAjaxJson() {
    var payload = {
        id: parseInt($('#id').val(), 10),
        name: $('#name').val(),
        age: parseInt($('#age').val(), 10),
        birthDate: $('#birthDate').val(),
        address: $('#address').val()
    };

    $.ajax({
        url: window.USER_UPDATE_JSON_URL,
        type: 'POST',
        contentType: 'application/json; charset=UTF-8',
        data: JSON.stringify(payload),
        success: function(res) {
            $('#result').text('Ajax JSON 수정 성공: ' + res);
        },
        error: function (xhr) {
            $('#result').text('Ajax JSON 수정 실패: ' + (xhr.responseText || xhr.status))
        }
    });
}

function updateByAxiosJson() {
    var payload = {
        id: parseInt($('#id').val(), 10),
        name: $('#name').val(),
        age: parseInt($('#age').val(), 10),
        birthDate: $('#birthDate').val(),
        address: $('#address').val()
    };

    axios.post(window.USER_UPDATE_JSON_URL, payload, {
        headers: {
            'Content-Type': 'application/json; charset=UTF-8'
        }
    })
    .then(function (response) {
        $('#result').text('Axios JSON 수정 성공: ' + response.data);
    })
    .catch(function (error) {
        var msg = (error.response && error.response.data)
        ? error.response.data
        : error.message;
        $('#result').text('Axios JSON 수정 실패: ' + msg);
    });
}


function updateByFetchJson() {
var payload = {
        id: parseInt($('#id').val(), 10),
        name: $('#name').val(),
        age: parseInt($('#age').val(), 10),
        birthDate: $('#birthDate').val(),
        address: $('#address').val()
    };

    fetch(window.USER_UPDATE_JSON_URL, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json; charset=UTF-8'
        },
        body: JSON.stringify(payload)
    })
    .then(function (response) {
        if (!response.ok) {
            throw new Error('HTTP ' + response.status);
        }
        return response.text(); // 컨트롤러가 int 반환이면 text가 안전
    })
    .then(function (response) {
        $('#result').text('Fetch JSON 수정 성공: ' + response);
    })
    .catch(function (error) {
        $('#result').text('Fetch JSON 수정 실패: ' + error.message)
    });
}


function updatePutByAxiosJson() {
    var payload = {
        id: parseInt($('#id').val(), 10),
        name: $('#name').val(),
        age: parseInt($('#age').val(), 10),
        birthDate: $('#birthDate').val(),
        address: $('#address').val()
    };

    axios.put(window.USER_UPDATE_PUT_JSON_URL, payload, {
        headers: {
            'Content-Type': 'application/json; charset=UTF-8'
        }
    })
    .then(function (response) {
        $('#result').text('Axios JSON Put 수정 성공: ' + response.data);
    })
    .catch(function (error) {
        var msg = (error.response && error.response.data)
        ? error.response.data
        : error.message;
        $('#result').text('Axios JSON Put 수정 실패: ' + msg);
    });
}

function updatePatchByAxiosJson() {
    var payload = {
        id: parseInt($('#id').val(), 10),
        name: $('#name').val(),
    };

    axios.patch(window.USER_UPDATE_PATCH_JSON_URL, payload, {
        headers: {
            'Content-Type': 'application/json; charset=UTF-8'
        }
    })
    .then(function (response) {
        $('#result').text('Axios JSON Patch 수정 성공: ' + response.data);
    })
    .catch(function (error) {
        var msg = (error.response && error.response.data)
        ? error.response.data
        : error.message;
        $('#result').text('Axios JSON Patch 수정 실패: ' + msg);
    });
}

function updatePatchByAxiosJsonMap() {
    var payload = {
        id: parseInt($('#id').val(), 10),
        name: $('#name').val(),
    };

    axios.patch(window.USER_UPDATE_PATCH_JSON_MAP_URL, payload, {
        headers: {
            'Content-Type': 'application/json; charset=UTF-8'
        }
    })
    .then(function (response) {
        $('#result').text('Axios JSONMap Patch 수정 성공: ' + response.data);
    })
    .catch(function (error) {
        var msg = (error.response && error.response.data)
        ? error.response.data
        : error.message;
        $('#result').text('Axios JSONMap Patch 수정 실패: ' + msg);
    });
}

function updatePatchByAxiosJsonNode() {
    var payload = {
        id: parseInt($('#id').val(), 10),
        name: $('#name').val(),
    };

    axios.patch(window.USER_UPDATE_PATCH_JSON_MAP_URL, payload, {
        headers: {
            'Content-Type': 'application/json; charset=UTF-8'
        }
    })
    .then(function (response) {
        $('#result').text('Axios JSONNode Patch 수정 성공: ' + response.data);
    })
    .catch(function (error) {
        var msg = (error.response && error.response.data)
        ? error.response.data
        : error.message;
        $('#result').text('Axios JSONNode Patch 수정 실패: ' + msg);
    });
}
