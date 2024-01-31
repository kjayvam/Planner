$(function () {
    $("#plandatepicker").datepicker();
    $("#scheduledatepicker").datepicker();
});

$('img.ui-datepicker-trigger').css({'cursor':'pointer', 'margin-left':'5px'});  //아이콘(icon) 위치

$.fn.datepicker.dates["en"] = {
    days: ["일", "월", "화", "수", "목", "금", "토"],
    daysShort: ["일", "월", "화", "수", "목", "금", "토"],
    daysMin: ["일", "월", "화", "수", "목", "금", "토"],
    months: ["1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"],
    monthsShort: ["1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"],
    today: "오늘",
    clear: "Clear",
    format: "yyyy/mm/dd",
    titleFormat: "yyyy년 MM" /* Leverages same syntax as 'format' */,
    weekStart: 0,
};