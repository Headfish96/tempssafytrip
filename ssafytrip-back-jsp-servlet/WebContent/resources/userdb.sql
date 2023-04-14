
create table member (
	email varchar(50),
    password varchar(100),
    nickname varchar(20),
    PRIMARY KEY (email)
);

insert into member (email, password, nickname)
values ('ssafy@ssafy.com', 'ssafy', 'ssafy');

insert into member (email, password, nickname)
values ('admin@ssafy.com', 'admin', 'admin');