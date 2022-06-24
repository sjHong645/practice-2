var main = {

    // REST에서 CRUD
    // 생성 - POST
    // 읽기 - GET
    // 수정 - PUT
    // 삭제 - DELETE

    init : function () {
        var _this = this;
        $('#btn-save').on('click', function() { _this.save(); });
        $('#btn-update').on('click', function() { _this.update(); });
        $('#btn-delete').on('click', function() { _this.delete(); });
    },
    // 화면에 입력한 제목, 작성자, 내용 데이터를
    // DB에 입력하고
    // 성공적으로 입력되면 "글이 등록되었습니다."라는 문구가 나오고
    // 그렇지 않으면 오류 메시지 출력
    save : function () {
        var data = {
            title: $('#title').val(),
            author: $('#author').val(),
            content: $('#content').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/posts',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 등록되었습니다.');
            window.location.href = '/'; // 글 등록을 성공하면 메인페이지(/)로 이동
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });

    },

    update : function() {
        var data = {
            title: $('#title').val(),
            content: $('#content').val()
        };

        var id = $('#id').val();

        $.ajax({
            type: 'PUT',
            url: '/api/v1/posts/'+id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('글이 수정되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
           alert(JSON.stringify(error));
        });
    },

    delete : function() {

        var id = $('#id').val();

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/posts/'+id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
        }).done(function () {
            alert('글이 삭제되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });

    }
};

main.init();