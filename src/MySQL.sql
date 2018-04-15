/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2018/4/11 15:10:52                           */
/*==============================================================*/
drop database if exists bbs;
create database bbs;
use bbs;

drop table if exists followpost_picture;

drop table if exists main_forum;

drop table if exists picture;

drop table if exists post;

drop table if exists post_picture;

drop table if exists sub_forum;

drop table if exists user;

/*==============================================================*/
/* Table: followpost                                            */
/*==============================================================*/
create table followpost
(
   id                   int not null auto_increment  comment '',
   post_id              int  comment '',
   user_id              int  comment '',
   content              text comment '',
   send_time            datetime  comment '',
   update_time          datetime  comment '',
   primary key (id)
);

/*==============================================================*/
/* Table: followpost_picture                                    */
/*==============================================================*/
create table followpost_picture
(
   id                   int not null auto_increment,
   followpost_id        int  comment '',
   picture_id           int  comment '',
   primary key (id)
);

/*==============================================================*/
/* Table: main_forum                                            */
/*==============================================================*/
create table main_forum
(
   id                   int not null auto_increment  comment '',
   name                 varchar(30)  comment '',
   info                 varchar(50)  comment '',
   create_time          datetime  comment '',
   primary key (id)
);

/*==============================================================*/
/* Table: picture                                               */
/*==============================================================*/
create table picture
(
   id                   int not null auto_increment  comment '',
   picture              char(30)  comment '',
   upload_time          datetime  comment '',
   primary key (id)
);

/*==============================================================*/
/* Table: post                                                  */
/*==============================================================*/
create table post
(
   id                   int not null auto_increment  comment '',
   user_id              int  comment '',
   sub_forum_id         int  comment '',
   title                varchar(50)  comment '',
   content              text comment '',
   send_time            datetime  comment '',
   update_time          datetime  comment '',
   type                 int  comment '',
   top                  int  comment '',
   view_num             int  comment '',
   primary key (id)
);

/*==============================================================*/
/* Table: post_picture                                          */
/*==============================================================*/
create table post_picture
(
   id                   int not null auto_increment,
   post_id              int  comment '',
   picture_id           int  comment '',
   primary key(id)
);

/*==============================================================*/
/* Table: sub_forum                                             */
/*==============================================================*/
create table sub_forum
(
   id                   int not null auto_increment  comment '',
   main_forum_id        int  comment '',
   name                 varchar(30)  comment '',
   info                 varchar(50)  comment '',
   create_time          datetime  comment '',
   primary key (id)
);

/*==============================================================*/
/* Table: user                                                  */
/*==============================================================*/
create table user
(
   id                   int not null auto_increment  comment '',
   picture_id           int  comment '',
   username             varchar(20)  comment '',
   password             varchar(20)  comment '',
   info                 varchar(50)  comment '',
   sex                  char(4)  comment '',
   email                varchar(30)  comment '',
   status               int  comment '',
   type                 int  comment '',
   level                int  comment '',
   register_time        datetime  comment '',
   primary key (id)
);

alter table followpost add constraint FK_FOLLOWPO_RELATIONS_POST foreign key (post_id)
      references post (id) on delete cascade on update cascade;

alter table followpost add constraint FK_FOLLOWPO_RELATIONS_USER foreign key (user_id)
      references user (id) on delete set null on update set null;

alter table followpost_picture add constraint FK_FOLLOWPO_RELATIONS_PICTURE foreign key (picture_id)
      references picture (id) on delete cascade on update cascade;

alter table followpost_picture add constraint FK_FOLLOWPO_RELATIONS_FOLLOWPO foreign key (followpost_id)
      references followpost (id) on delete cascade on update cascade;

alter table post add constraint FK_POST_RELATIONS_USER foreign key (user_id)
      references user (id) on delete set null on update set null;

alter table post add constraint FK_POST_RELATIONS_SUB_FORU foreign key (sub_forum_id)
      references sub_forum (id) on delete cascade on update cascade;

alter table post_picture add constraint FK_POST_PIC_RELATIONS_POST foreign key (post_id)
      references post (id) on delete cascade on update cascade;

alter table post_picture add constraint FK_POST_PIC_RELATIONS_PICTURE foreign key (picture_id)
      references picture (id) on delete cascade on update cascade;

alter table sub_forum add constraint FK_SUB_FORU_RELATIONS_MAIN_FOR foreign key (main_forum_id)
      references main_forum (id) on delete cascade on update cascade;

alter table user add constraint FK_USER_RELATIONS_PICTURE foreign key (picture_id)
      references picture (id) on delete set null on update set null;

	  
drop table if exists game;
drop table if exists game_link;

/*==============================================================*/
/* Table: game                                                  */
/*==============================================================*/
create table game
(
   id                   int not null auto_increment  comment '',
   home                 char(20),
   away                 char(20),
   date                 date  comment '',
   primary key (id)
);

/*==============================================================*/
/* Table: game_link                                             */
/*==============================================================*/
create table game_link
(
   id                   int not null auto_increment  comment '',
   game_id              int  comment '',
   info                 text comment '',
   order_num            int  comment '',
   primary key (id)
);

alter table game_link add constraint FK_GAME_LIN_RELATIONS_GAME foreign key (game_id)
      references game (id) on delete cascade on update cascade;


