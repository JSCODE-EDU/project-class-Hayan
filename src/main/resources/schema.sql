--drop table if exists board;

create table board (
        post_id bigint not null auto_increment,
        name varchar(100),
        password varchar(100),
        title varchar(255),
        content text,
        created_at datetime(6),
        primary key (post_id)
    ) engine=InnoDB default charset=utf8mb4;