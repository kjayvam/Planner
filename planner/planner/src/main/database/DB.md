```sql
create table plan_items
(
    no          int         not null    primary key,
    title       varchar(50) null        comment '제목',
    content     varchar(50) null        comment '내용',
    startDate   varchar(50) null        comment '시작날짜',
    endDate     varchar(50) null        comment '마감날짜',
    progress    varchar(50) null        comment '진행상황',
    privacy     varchar(50) null        comment '공개여부'
)
    comment '플랜 항목';
```

```sql
create table schedule
(
    no           int         not null   primary key,
    planNo       int         null,
    scheduleDate varchar(50) null       comment '일정',
    content      varchar(50) null       comment '내용',
    progress     varchar(50) null       comment '진행상황',
    constraint schedule_plan__fk
        foreign key (planNo) references plan (no)
)
    comment '할 일';
```

```sql
create table user
(
    no       int         not null   primary key,
    id       varchar(30) not null   comment '아이디',
    pw       varchar(50) not null   comment '비밀번호',
    name     varchar(50) null       comment '이름',
    email    varchar(50) null       comment '이메일',
    nickname varchar(50) null       comment '닉네임',
    profile  varchar(50) null       comment '프로필사진'
)
    comment '고객';
```