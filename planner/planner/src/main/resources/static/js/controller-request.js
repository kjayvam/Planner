const EventListener = 'DOMContentLoaded';
document.addEventListener(EventListener, onClickEvent);

function onClickEvent() {

    /* Logo 버튼 - index 이동 */
    let indexBtn = document.getElementsByName('moveIndexBtn')[0];
    indexBtn.addEventListener('click', indexMove);

    function indexMove() {

        location.href = '/';
    }

    /* id 찾기 버튼 - id 찾기 이동 */
    let idBtn = document.getElementsByName('moveIdBtn')[0];
    idBtn.addEventListener('click', idMove);

    function idMove() {

        location.href = '/move/find/id';
    }

    /* pw 찾기 버튼 - pw 찾기 이동 */
    let pwBtn = document.getElementsByName('movePwBtn')[0];
    pwBtn.addEventListener('click', pwMove);

    function pwMove() {

        location.href = '/move/find/pw';
    }

    /* 회원가입 버튼 - 회원가입 이동 */
    let signupBtn = document.getElementsByName('moveSignupBtn')[0];
    signupBtn.addEventListener('click', signupMove);

    function signupMove() {

        location.href = '/move/join/member';
    }
}
