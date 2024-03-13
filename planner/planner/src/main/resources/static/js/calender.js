
var calendar;
document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');
    calendar = new FullCalendar.Calendar(calendarEl, {
        // no plugin configuration required!
        editable: true,

        //헤더툴바
        headerToolbar: {
            left: 'prev,next today',
            center: 'title',
            right: 'dayGridMonth,timeGridWeek,timeGridDay,listMonth'
        },
        //헤더텍스트
        buttonText: {
            today:    '오늘',
            month:    '월간',
            week:     '주간',
            day:      '일간',
            list:     '목록'
        },
        //navLinks: true, // can click day/week names to navigate views
        businessHours: {
            daysOfWeek: [ 1,2,3,4,5 ],
            startTime: '00:00',
            endTime: '24:00'
        }, // 주말 회색표시
        //editable: true,
        //selectable: true,
        initialView: 'dayGridMonth',    //월 달력 표시
        locale: "ko",   //한국어설정
        themeSystem: 'bootstrap5',  //부트스트랩5와 호환
        // *일 표시 > * 로 숫자만 표시되게 바꾸기
        dayCellContent: function (info) {
            var number = document.createElement("a");
            number.classList.add("fc-daygrid-day-number");
            number.innerHTML = info.dayNumberText.replace("일", '').replace("日","");
            if (info.view.type === "dayGridMonth") {
                return {
                    html: number.outerHTML
                };
            }
            return {
                domNodes: []
            };
        },

        // 일정
        events: [
            { // this object will be "parsed" into an Event Object
                id: '1',
                title: '1번', // 제목
                start: '2024-02-22T09:00', // 시작시간 형식: 'yyyy-mm-ddThh:mm'
                end: '2024-02-24T18:00', // 마감시간 형식: 'yyyy-mm-ddThh:mm'
                color: 'red',    // 배경, 테두리색상
                editable: true
            },
            { // this object will be "parsed" into an Event Object
                id: '2',
                title: '2번', // 제목
                start: '2024-02-22T09:00', // 시작시간 형식: 'yyyy-mm-ddThh:mm'
                end: '2024-02-22T18:00', // 마감시간 형식: 'yyyy-mm-ddThh:mm'
                color: 'blue'    // 색상
            },
            { // this object will be "parsed" into an Event Object
                id: '2',
                title: '2번', // 제목
                start: '2024-02-23T09:00', // 시작시간 형식: 'yyyy-mm-ddThh:mm'
                end: '2024-02-23T18:00', // 마감시간 형식: 'yyyy-mm-ddThh:mm'
                color: 'blue'    // 색상
            },
            { // this object will be "parsed" into an Event Object
                id: '2',
                title: '2번', // 제목
                start: '2024-02-24T09:00', // 시작시간 형식: 'yyyy-mm-ddThh:mm'
                end: '2024-02-24T18:00', // 마감시간 형식: 'yyyy-mm-ddThh:mm'
                color: 'blue'    // 색상
            },
            { // 단순반복
                id: '3',
                title: '3번', // 제목
                daysOfWeek: [1, 2, 3, 4, 5],
                startTime: '09:00', // 시작시간 형식: 'hh:mm'
                endTime: '09:00', // 마감시간 형식: 'hh:mm'
                color: 'yellow'    // 색상
            },
            {
                title: 'BCH237',
                start: '2024-02-24T09:00',
                end: '2024-02-24T09:00',
                extendedProps: {
                    department: 'BioChemistry'
                },
                description: 'Lecture'
            }
        ]

    });

    /*var event = calendar.getEventById('2');
    event.remove();*/

    calendar.render();
});

/* 일정 추가*/
function addCalendar(event){
    calendar.addEvent(event);
}

/* 일정 삭제*/
function removeCalendar(id){
    calendar.getEventById(id).remove();
}