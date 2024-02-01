const EventListener = 'DOMContentLoaded';
document.addEventListener(EventListener, onClickEvent);

function onClickEvent() {

    /* Logo 버튼 - index 이동 */
    let indexBtn = document.getElementsByName('moveIndexBtn')[0];
    indexBtn.addEventListener('click', indexMove);

    function indexMove() {
        location.href = '/';
    }
    btn-href-controller
    /* id 찾기 버튼 - id 찾기 이동 */
    let idBtns = document.getElementsByName('moveIdBtn');

    for(let i = 0; i < idBtns.length; i++) {
        idBtns[i].addEventListener('click', idMove);
    }

    function idMove() {
        location.href = '/move/find/id';
    }

    /* pw 찾기 버튼 - pw 찾기 이동 */
    let pwBtns = document.getElementsByName('movePwBtn');

    for(let i = 0; i < pwBtns.length; i++) {
        pwBtns[i].addEventListener('click', pwMove);
    }

    function pwMove() {
        location.href = '/move/find/pw';
    }

    /* 회원가입 버튼 - 회원가입 이동 */
    let signupBtns = document.getElementsByName('moveSignupBtn');

    for(let i = 0; i < signupBtns.length; i++) {
        signupBtns[i].addEventListener('click', signupMove);
    }

    function signupMove() {
        location.href = '/move/join/member';
    }
}
