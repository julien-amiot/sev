# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table etat_ccvm (
  libelle                   varchar(255) not null,
  constraint pk_etat_ccvm primary key (libelle))
;

create table etat_ventilateur (
  libelle                   varchar(255) not null,
  couleur_hexadecimale      varchar(255),
  constraint pk_etat_ventilateur primary key (libelle))
;

create table ligne (
  libelle                   varchar(255) not null,
  guide_operateur_ligne_uri varchar(255),
  guide_operateur_ligne_degrade_uri varchar(255),
  reseau_libelle            varchar(255),
  constraint pk_ligne primary key (libelle))
;

create table reseau (
  libelle                   varchar(255) not null,
  constraint pk_reseau primary key (libelle))
;

create table section (
  libelle                   varchar(255) not null,
  ligne_libelle             varchar(255),
  constraint pk_section primary key (libelle))
;

create table station (
  libelle                   varchar(255),
  en_surface                boolean,
  constraint pk_station primary key (libelle))
;

create table ventilateur (
  libelle                   varchar(255) not null,
  constraint pk_ventilateur primary key (libelle))
;

create table zone (
  libelle                   varchar(255) not null,
  constraint pk_zone primary key (libelle))
;

create sequence etat_ccvm_seq;

create sequence etat_ventilateur_seq;

create sequence ligne_seq;

create sequence reseau_seq;

create sequence section_seq;

create sequence station_seq;

create sequence ventilateur_seq;

create sequence zone_seq;

alter table ligne add constraint fk_ligne_reseau_1 foreign key (reseau_libelle) references reseau (libelle) on delete restrict on update restrict;
create index ix_ligne_reseau_1 on ligne (reseau_libelle);
alter table section add constraint fk_section_ligne_2 foreign key (ligne_libelle) references ligne (libelle) on delete restrict on update restrict;
create index ix_section_ligne_2 on section (ligne_libelle);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists etat_ccvm;

drop table if exists etat_ventilateur;

drop table if exists ligne;

drop table if exists reseau;

drop table if exists section;

drop table if exists station;

drop table if exists ventilateur;

drop table if exists zone;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists etat_ccvm_seq;

drop sequence if exists etat_ventilateur_seq;

drop sequence if exists ligne_seq;

drop sequence if exists reseau_seq;

drop sequence if exists section_seq;

drop sequence if exists station_seq;

drop sequence if exists ventilateur_seq;

drop sequence if exists zone_seq;

