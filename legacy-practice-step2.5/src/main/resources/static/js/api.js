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

function updateDynamicAxiosJson() {
    var payload = {
        id: parseInt($('#id').val(), 10),
        name: $('#name').val(),
        address: $('#address').val()
    };

    axios.patch(window.USER_UPDATE_DYNAMIC_JSON_URL, payload, {
        headers: {
            'Content-Type': 'application/json; charset=UTF-8'
        }
    })
    .then(function (response) {
        $('#result').text('Axios JSON Dynamic 수정 성공: ' + response.data);
    })
    .catch(function (error) {
        var msg = (error.response && error.response.data)
        ? error.response.data
        : error.message;
        $('#result').text('Axios JSON Dynamic 수정 실패: ' + msg);
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

    axios.patch(window.USER_UPDATE_PATCH_JSON_NODE_URL, payload, {
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

// 삭제 부분

function deleteAjaxForm(id) {
    $.ajax({
        url: window.USER_AJAX_FORM_DELETE,
        type: 'POST',
        data: { id: id},
        success: function (res) {
            $('#result').text('Ajax Form 삭제 성공: ' + res);
//            location.reload();
        },
        error: function (xhr) {
            $('#result').text('Ajax Form 삭제 실패: ' + (xhr.responseText || xhr.status));
        }
    });
}

function deleteAjaxJson(id) {
    var payload = {
        id: id
    };

    $.ajax({
        url: window.USER_JSON_DELETE,
        type: 'POST',
        contentType: 'application/json; charset=UTF-8',
        data: JSON.stringify(payload),
        // data: JSON.stringify({ id: id }) 단일 값이면 이렇게도 가능
        success: function (res) {
            $('#result').text('Ajax Json 삭제 성공: ' + res);
        },
        error: function (xhr) {
            $('#result').text('Ajax Json 삭제 실패: ' + (xhr.responseText || xhr.status));
        }
    })
}

function deleteAxiosPath(id) {
    axios.delete(window.USER_PATH_DELETE + '/' + id)
    .then(function (response) {
            $('#result').text('Axios Path 삭제 성공: ' + response.data);
        })
        .catch(function (error) {
            var msg = (error.response && error.response.data)
            ? error.response.data
            : error.message;
            $('#result').text('Axios Path 삭제 실패: ' + msg);
        });
}

function deleteFetchPath(id) {
    fetch(window.USER_PATH_DELETE + '/' + id, {
        method: 'DELETE'
    })
    .then(function (res) {
        if (!res.ok) {
            throw new Error('HTTP' + res.status);
        }
        return res.text();
    })
    .then(function (res) {
        $('#result').text('Fetch Delete 성공: ' + res);
    })
    .catch(function (err) {
        $('#result').text('Fetch Delete 실패: ' + err.message);
    });
}

function renderSearchResultBody(list) {
    var html = '';
    if (!list || list.length == 0) {
        $('#searchResultBody').html('<tr><td colspan="5">검색 결과가 없습니다.</td></tr>');
        return;
    }
    list.forEach(function (user) {
        html += '<tr>'
            + '<td>' + user.id + '</td>'
            + '<td>' + (user.name != null ? user.name : '') + '</td>'
            + '<td>' + (user.age != null ? user.age : '') + '</td>'
            + '<td>' + (user.birthDate != null ? user.birthDate : '') + '</td>'
            + '<td>' + (user.address != null ? user.address : '') + '</td>'
            + '</tr>';
    })
    $('#searchResultBody').html(html);
}

function AxiosSearchUserByNameAndAddress() {
    const name =  $('#name').val().trim();
    const address = $('#address').val().trim();

    axios.get(window.USER_NAME_ADDRESS_SEARCH_URL,
       { params: { name, address } })
    .then(function (response) {
        var list = response.data;
        renderSearchResultBody(list);
        $('#result').text('검색 성공: ' + list.length + '건');
    })
    .catch(function (error) {
        var msg = (error.response && error.response.data)
            ? error.response.data
            : error.message;
        $('#result').text('검색 실패: ' + msg);
    });
}

function AxiosDynamicSearchUserByNameAndAddress() {
    const name =  $('#name').val().trim();
    const address = $('#address').val().trim();

    axios.get(window.USER_NAME_ADDRESS_DYNAMIC_SEARCH_URL,
       { params: { name, address } })
    .then(function (response) {
        var list = response.data;
        renderSearchResultBody(list);
        $('#result').text('검색 성공: ' + list.length + '건');
    })
    .catch(function (error) {
        var msg = (error.response && error.response.data)
            ? error.response.data
            : error.message;
        $('#result').text('검색 실패: ' + msg);
    });
}

function loadJoinDetailByAxios(id) {
    axios.get(window.USER_JOIN_DETAIL_AXIOS + '/' + id)
        .then(function (response) {
            var detail = response.data;

            if (!detail) {
                $('#result').text('조인 상세 정보가 없습니다.');
                return;
            }

            var html = ''
                + '<table border="1" cellpadding="8" cellspacing="0">'
                + '  <thead>'
                + '    <tr>'
                + '      <th>ID</th>'
                + '      <th>userId</th>'
                + '      <th>전화번호</th>'
                + '      <th>직업</th>'
                + '      <th>생성일</th>'
                + '      <th>수정일</th>'
                + '    </tr>'
                + '  </thead>'
                + '  <tbody>'
                + '    <tr>'
                + '      <td>' + (detail.id != null ? detail.id : '') + '</td>'
                + '      <td>' + (detail.userId != null ? detail.userId : '') + '</td>'
                + '      <td>' + (detail.phone != null ? detail.phone : '미입력') + '</td>'
                + '      <td>' + (detail.job != null ? detail.job : '미입력') + '</td>'
                + '      <td>' + (detail.createAt != null ? detail.createAt : '') + '</td>'
                + '      <td>' + (detail.updateAt != null ? detail.updateAt : '') + '</td>'
                + '    </tr>'
                + '  </tbody>'
                + '</table>';

            $('#joinDetailResult').html(html);
            $('#result').text('조인 상세 조회 성공');
        })
        .catch(function (error) {
            var msg = (error.response && error.response.data)
                ? error.response.data
                : error.message;
            $('#result').text('조인 상세 조회 실패: ' + msg);
        });
}

function SearchUserByIdList() {
    var raw = $('#idList').val().trim();
    if (!raw) {
        $('#result').text('ID를 입력해 주세요.');
        return;
    }
    var idList = raw.split(/[,\s]+/)
        .map(function (s) { return s.trim(); })
        .filter(function (s) { return s.length > 0; })
        .map(function (s) { return Number(s); })
        .filter(function (n) { return Number.isFinite(n); });


   if (idList.length === 0) {
    $('#result').text('유효한 숫자 ID가 없습니다.');
    return;
   }

    axios.post(window.USER_ID_LIST_SEARCH_URL, idList, {
        headers: {
            'Content-Type': 'application/json'
        }
    })
    .then(function (response) {
        var list = response.data;
        renderSearchResultBody(list);
        $('#result').text('ID 목록 조회 성공: ' + list.length + '건');
    })
    .catch(function (error) {
        var msg = (error.response && error.response.data)
            ? error.response.data
            : error.message;
        $('#result').text('ID 목록 조회 실패: ' + msg);
    });
}

function userIdListDelete() {
    var raw = $('#idList').val().trim();
    if (!raw) {
        $('#result').text('ID를 입력해 주세요.');
        return;
    }
    var idList = raw.split(/[,\s]+/)
        .map(function (s) { return s.trim(); })
        .filter(function (s) { return s.length > 0; })
        .map(function (s) { return Number(s); })
        .filter(function (n) { return Number.isFinite(n); });

    if (idList.length === 0) {
        $('#result').text('유효한 숫자 ID가 없습니다.');
        return;
    }


    axios.delete(window.USER_ID_LIST_DELETE, {
        data: idList,
        headers: {
            'Content-Type': 'application/json'
        }
    })
    .then(function (response) {
            $('#result').text('Axios userIdList 삭제 성공: ' + response.data);
        })
        .catch(function (error) {
            var msg = (error.response && error.response.data)
            ? error.response.data
            : error.message;
            $('#result').text('Axios userIdList 삭제 실패: ' + msg);
        });
}




// ========== /user/pageList 페이징 (USER_LOAD_PAGE_LIST_URL, #userPagedTbody, #userPagedClientMsg, #userPageInput, #userSizeInput) ==========
var userPagedState = {
    page: 1,
    size: 10,
    totalElements: 0,
    totalPages: 0
};

function renderUserPagedTbody(list) {
    var tb = document.getElementById('userPagedTbody');
    if (!tb) {
        return;
    }
    if (!list || list.length === 0) {
        tb.innerHTML = '<tr><td colspan="5">조회된 사용자가 없습니다.</td></tr>';
        return;
    }
    var html = '';
    list.forEach(function (u) {
        html += '<tr>'
            + '<td>' + (u.id != null ? u.id : '') + '</td>'
            + '<td>' + (u.name != null ? u.name : '') + '</td>'
            + '<td>' + (u.age != null ? u.age : '') + '</td>'
            + '<td>' + (u.birthDate != null ? u.birthDate : '') + '</td>'
            + '<td>' + (u.address != null ? u.address : '') + '</td>'
            + '</tr>';
    });
    tb.innerHTML = html;
}

function jQUeyyRenderUserPagedTbody(list) {
    var $tb = $('#userPagedTbody');
    if (!$tb.length) {
        return;
    }
    if (!list || list.length === 0) {
        $tb.html('<tr><td colspan="5">조회된 사용자가 없습니다.</td></tr>');
        return;
    }
    var html = '';
    list.forEach(function (u) {
        html += '<tr>'
            + '<td>' + (u.id != null ? u.id : '') + '</td>'
            + '<td>' + (u.name != null ? u.name : '') + '</td>'
            + '<td>' + (u.age != null ? u.age : '') + '</td>'
            + '<td>' + (u.birthDate != null ? u.birthDate : '') + '</td>'
            + '<td>' + (u.address != null ? u.address : '') + '</td>'
            + '</tr>';
    });
    $tb.html(html);
}

function userPagedSetClientMsg(text) {
    var el = document.getElementById('userPagedClientMsg');
    if (el) {
        el.textContent = text;
    }
}

function jQueyuserPagedSetClientMsg(text) {
    var $el = $('#userPagedClientMsg');
    if ($el.length ) {
        $el.text(text);
    }
}


function loadListByAxiosPaged(page, size) {
    var p = page != null && page > 0 ? page : 1;
    var s = size != null && size > 0 ? size : 10;

    var baseUrl = window.USER_LOAD_ASYNC_PAGE_LIST_URL || '/user/axiosList/paged';

    axios.get(baseUrl, {
        params: { page: p, size: s }
    })
        .then(function (response) {
            var body = response.data;
            var list = body.content;

            userPagedState.page = body.page;
            userPagedState.size = body.size;
            userPagedState.totalElements = body.totalElements;
            userPagedState.totalPages = body.totalPages;

            renderUserPagedTbody(list);

            var pi = document.getElementById('userPageInput');
            var si = document.getElementById('userSizeInput');

            if (pi) {
                pi.value = body.page;
            }
            if (si) {
                si.value = body.size;
            }

            jQueyuserPagedSetClientMsg(
                'Axios 페이징 조회 성공 — page: ' + body.page
                + ', size: ' + body.size
                + ', totalElements: ' + body.totalElements
                + ', totalPages: ' + body.totalPages
                + ', 이번 페이지 건수: ' + (list ? list.length : 0)
            );
        })
        .catch(function (error) {
            var msg = (error.response && error.response.data)
                ? error.response.data
                : error.message;
            jQueyuserPagedSetClientMsg('Axios 페이징 조회 실패: ' + msg);
        });
}

function jQueryLoadListByAxiosPaged(page, size) {
    var p = page != null && page > 0 ? page : 1;
    var s = size != null && size > 0 ? size : 10;

    var baseUrl = window.USER_LOAD_ASYNC_PAGE_LIST_URL || '/user/axiosList/paged';

    axios.get(baseUrl, {
        params: { page: p, size: s }
    })
        .then(function (response) {
            var body = response.data;
            var list = body.content;

            userPagedState.page = body.page;
            userPagedState.size = body.size;
            userPagedState.totalElements = body.totalElements;
            userPagedState.totalPages = body.totalPages;

            renderUserPagedTbody(list);

            var $pi = $('#userPageInput');
            var $si = $('#userSizeInput');

            if ($pi.length) {
                $pi.val(body.page);
            }
            if ($si.length) {
                $si.val(body.size);
            }

            userPagedSetClientMsg(
                'Axios 페이징 조회 성공 — page: ' + body.page
                + ', size: ' + body.size
                + ', totalElements: ' + body.totalElements
                + ', totalPages: ' + body.totalPages
                + ', 이번 페이지 건수: ' + (list ? list.length : 0)
            );
        })
        .catch(function (error) {
            var msg = (error.response && error.response.data)
                ? error.response.data
                : error.message;
            userPagedSetClientMsg('Axios 페이징 조회 실패: ' + msg);
        });
}

function userPagedBtnLoad() {
    var pi = document.getElementById('userPageInput');
    var si = document.getElementById('userSizeInput');
    
    var p = pi ? parseInt(pi.value, 10) : 1;
    var s = si ? parseInt(si.value, 10) : 10;
    loadListByAxiosPaged(p > 0 ? p : 1, s > 0 ? s : 10);
}

function jQueryUserPagedBtnLoad() {
    var $pi = $('#userPageInput');
    var $si = $('#userSizeInput');

    var p = $pi.length ? parseInt($pi.val(), 10) : 1;
    var s = $si.length ? parseInt($si.val(), 10) : 10;
    jQueryLoadListByAxiosPaged(p > 0 ? p : 1, s > 0 ? s : 10);
}

function userPagedBtnPrev() {
    if (userPagedState.page <= 1) {
        return;
    }
    loadListByAxiosPaged(userPagedState.page - 1, userPagedState.size);
}

function jQeuryUserPagedBtnPrev() {
    if (userPagedState.page <= 1) {
        return;
    }
    jQueryLoadListByAxiosPaged(userPagedState.page - 1, userPagedState.size);
}

function userPagedBtnNext() {
    if (userPagedState.totalPages > 0 && userPagedState.page >= userPagedState.totalPages) {
        return;
    }
    loadListByAxiosPaged(userPagedState.page + 1, userPagedState.size);
}

function jQeuryUserPagedBtnNext() {
    if (userPagedState.totalPages > 0 && userPagedState.page >= userPagedState.totalPages) {
        return;
    }
    jQueryLoadListByAxiosPaged(userPagedState.page + 1, userPagedState.size);
}