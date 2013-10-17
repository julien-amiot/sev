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
  id                        integer not null,
  libelle                   varchar(255),
  is_en_surface             boolean,
  constraint pk_station primary key (id))
;

create table ventilateur (
  libelle                   varchar(255) not null,
  constraint pk_ventilateur primary key (libelle))
;

create table zone (
  libelle                   varchar(255) not null,
  constraint pk_zone primary key (libelle))
;


create table section_station (
  section_libelle                varchar(255) not null,
  station_id                     integer not null,
  constraint pk_section_station primary key (section_libelle, station_id))
;

create table station_section (
  station_id                     integer not null,
  section_libelle                varchar(255) not null,
  constraint pk_station_section primary key (station_id, section_libelle))
;

create table station_to_stationPrecedente (
  station_id                     integer not null,
  stationPrecedente_id           integer not null,
  constraint pk_station_to_stationPrecedente primary key (station_id, stationPrecedente_id))
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



alter table section_station add constraint fk_section_station_section_01 foreign key (section_libelle) references section (libelle) on delete restrict on update restrict;

alter table section_station add constraint fk_section_station_station_02 foreign key (station_id) references station (id) on delete restrict on update restrict;

alter table station_section add constraint fk_station_section_station_01 foreign key (station_id) references station (id) on delete restrict on update restrict;

alter table station_section add constraint fk_station_section_section_02 foreign key (section_libelle) references section (libelle) on delete restrict on update restrict;

alter table station_to_stationPrecedente add constraint fk_station_to_stationPreceden_01 foreign key (station_id) references station (id) on delete restrict on update restrict;

alter table station_to_stationPrecedente add constraint fk_station_to_stationPreceden_02 foreign key (stationPrecedente_id) references station (id) on delete restrict on update restrict;

# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists etat_ccvm;

drop table if exists etat_ventilateur;

drop table if exists ligne;

drop table if exists reseau;

drop table if exists section;

drop table if exists section_station;

drop table if exists station;

drop table if exists station_section;

drop table if exists station_to_stationPrecedente;

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

