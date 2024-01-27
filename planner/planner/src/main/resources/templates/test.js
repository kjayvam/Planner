"use strict"

// 실행
$(function(){
    // Access to XMLHttpRequest at 'file:///C:/Users/Seokbong/Desktop/test/testModal.html' from origin 'null' has been blocked by CORS policy: Cross origin requests are only supported for protocol schemes: http, data, chrome, chrome-extension, chrome-untrusted, https.
    // Loacal에서는 CORS policy 문제 발생함.
    $(document).on('click', '.modalBtn', function(){
        // 모달의 id값 가져오기
        // [id].html 지정
        let modal = this.getAttribute('id');
        let filePath = './' + modal + '.html';

        // modalArea 안의 .modal-content에 [id].html 로드 후 모달 show
        $('#modalArea').find('.modal-content').load(filePath, function(){
            $('#modalArea').modal('show');
        });
    });

    // 모달 종료(hide) 버튼
    $(document).on('click', '#closeBtn', function(){
        $('#modalArea').modal('hide');
    });
})