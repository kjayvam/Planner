const EVENT_LISTENER = 'DOMContentLoaded';
document.addEventListener(EVENT_LISTENER , onClickEvent);

function onClickEvent() {

    /* Logo 버튼 - index 이동 */
    // let indexBtn = document.querySelector("button[name='moveIndexBtn']");
    // document.querySelector('.logo').addEventListener('click', indexMove);
    let indexBtn = document.getElementsByName('moveIndexBtn')[0];
    indexBtn.addEventListener('click', indexMove);

    function indexMove() {
        location.href = '/';
    }

    /* ID/PW 찾기 버튼 - ID/PW 찾기 이동 */
    let findBtns = document.getElementsByName('moveFindBtn');

    for (let i = 0; i < findBtns.length; i++) {
        findBtns[i].addEventListener('click', findMove);
    }

    function findMove() {
        location.href = '/move/findAccount';
    }

    /* 회원가입 버튼 - 회원가입 이동 */
    let signupBtns = document.getElementsByName('moveSignupBtn');

    for (let i = 0; i < signupBtns.length; i++) {
        signupBtns[i].addEventListener('click', signupMove);
    }

    function signupMove() {
        location.href = '/move/signup';
    }
}
