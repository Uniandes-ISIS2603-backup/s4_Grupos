

delete from EventoEntity;

delete from PersonaEntity;

delete from GrupoDeInteresEntity;

delete from ComentarioEntity;

insert into EventoEntity (id, nombre, fecha) values (15, 'Festival de Funk 2018','27/09/2018');

insert into PersonaEntity (id, nombre, contrasena) values (666, 'Pacman Returns', 'mPia5sUspw3orrCd');

insert into GrupoDeInteresEntity (id, nombre, descripcion) values (24, 'Planes de ocio', 'Nada como dormir todo el dia');

insert into ComentarioEntity (id, nombre, texto) values (21, 'Daniel Augusto Ramirez Duenas', 'Looool que buen grupo');

insert into ComentarioEntity (id, nombre, texto) values (99, 'Andrea Beltran' , 'Hola, de que trata este grupo?');