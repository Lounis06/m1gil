/* Création de la table des utilisateurs */
create table users (
	idusr integer primary key auto_increment not null,
	login varchar(32) not null,
    mail varchar(64) not null,
    password varchar(32) not null,
	firstName varchar(32) not null,
	lastName varchar(32) not null,
	birthDate date not null,
	telephone varchar(18) not null,
    isAdmin boolean default false
);

/* Création de la table des catégories */
create table category (
	idcat integer primary key auto_increment not null,
	name varchar(64) not null,
	parent integer
);

/* Création de la table des articles */
create table article (
	idart integer primary key auto_increment not null,
	title text not null,
	publishingDate date not null,
	author integer not null,
	category integer not null,
	abstract text,
	keywords text,
	content text
);

alter table article add constraint article_fk_art 
	foreign key (author) references users(idusr);
alter table article add constraint article_fk_cat 
	foreign key (category) references category(idcat);