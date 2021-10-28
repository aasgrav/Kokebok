create database if not exists kokebok;

use kokebok;

CREATE TABLE brukere(
brukernavn varchar(20) NOT NULL,
passord varchar(20) NOT NULL
);

CREATE TABLE oppskrifter(
    ID int auto_increment,
    Oppskriftstittel varchar(64) NOT NULL,
    Ingredienser varchar(256),
    Allergier varchar(256),
    -- Kategori enum('ITALIENSK', 'SPANSK', 'FRANSK', 'INDISK', 'MIDTØSTEN', 'NORSK', 'NORDISK', 'BRITISK', 'AMERIKANSK', 'KINESISK', 'JAPANSK', 
    -- 'ANNEN_ASIATISK', 'ØSTEUROPEISK', 'ANNEN_EUROPEISK', 'SØRAMERIKANSK', 'AFRIKANSK', 'AUSTRALSK', 'ANNEN'),
    Kategori int,
    Oppskriftstekst varchar(2000),
    PRIMARY KEY(ID)
);
