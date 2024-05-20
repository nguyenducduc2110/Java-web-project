use newservlet;

Create TABLE role(
id bigint NOT NULL PRIMARY KEY auto_increment,
name VARCHAR(255) NOT NULL,
code VARCHAR(255) NOT NULL,
createddate TIMESTAMP NULL, /*cột này dùng để lưu ngày tạo mới, báo mới*/
modifieddate TIMESTAMP NULL,/*cột này để lưu ngày thay đổi content báo*/
createdby varchar(255) null,/*Lưu người tạo báo đầu tiên*/
modifiedby varchar(255) null/*Lưu người thay đổi báo*/
/*==>id, và 4 cái trên là 5 field luôn luôn phải có trong mỗi tabel*/
);

CREATE TABLE user (
id bigint NOT NULL PRIMARY KEY auto_increment,
username VARCHAR(150) NOT NULL,
password VARCHAR(150) NOT NULL,
fullname VARCHAR(150) NOT NULL,
roleid bigint not null,
status int NOT NULL,/*lưu khi người dùng có hoạt động nữa hay ko (1-còn), (0-hết) */
createddate TIMESTAMP NULL,
modifieddate TIMESTAMP NULL,
createdby VARCHAR(255) NULL,
modifiedby VARCHAR(255) NULL,
 constraint fk_user_role foreign key (roleid) references role(id)
);
 
 
 CREATE TABLE news (
id bigint NOT NULL PRIMARY KEY auto_increment,
title varchar(255) null,
thumbnail varchar(255) null,/*chứa tên hình ảnh*/
shortdescription text null,
content text null,
categoryid bigint not null,
createddate TIMESTAMP NULL,
modifieddate TIMESTAMP NULL,
createdby VARCHAR(255) NULL,
modifiedby VARCHAR(255) NULL,
constraint fk_news_catogery foreign key(categoryid) references category(id)
);
 
 CREATE TABLE category(
id bigint NOT NULL PRIMARY KEY auto_increment,
name VARCHAR(255) NOT NULL,
code VARCHAR(255) NOT NULL,
createddate TIMESTAMP NULL,
modifieddate TIMESTAMP NULL,
createdby VARCHAR(255) NULL,
modifiedby VARCHAR(255) NULL
);
 
 CREATE TABLE comment(
id bigint NOT NULL PRIMARY KEY auto_increment,
content text not null,
user_id bigint not null,
news_id bigint not null,
createddate TIMESTAMP NULL,
modifieddate TIMESTAMP NULL,
createdby VARCHAR(255) NULL,
modifiedby VARCHAR(255) NULL, /*comment là bảng trung gian của user và news*/
constraint  fk_comment_user  foreign key(user_id) references user(id),
 constraint fk_comment_news foreign key(news_id) references news(id)
);/*Bảng lúc nào cũng nên có 1 khoá chính là id*/