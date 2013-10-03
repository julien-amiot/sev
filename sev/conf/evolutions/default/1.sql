# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table ventilateur (
  id                        integer not null,
  libelle                   varchar(255),
  constraint pk_ventilateur primary key (id))
;

create sequence ventilateur_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists ventilateur;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists ventilateur_seq;

