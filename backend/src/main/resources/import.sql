-- password == username
INSERT INTO user (role, city, email, name, number, password_hash, surname, username, activated,adress) VALUES ('ADMIN', 'Novi Sad', 'admin@admin.com', 'Ivan', '0635569989', '$2a$10$gvI2xtxfH3appKH3z8n7VeLPi84yNLwz5BgsA5VPACZYvfGlX6gLW', 'Vukasinovic', 'admin', true,'' );
INSERT INTO user (role, city, email, name, number, password_hash, surname, username, activated,adress) VALUES ('AGENT', 'Beograd', 'agent@agent.com','Agent', '0641569989', '$2a$10$7KP/ADSkFDZvXY29PKY/7.auVm6wh.5/yoDfqPzZVWu.ynXywu3/a', 'Agentic', 'agent', true,'');
INSERT INTO user (role, city, email, name, number, password_hash, surname, username, activated,adress) VALUES ('AGENT', 'Bajmok', 'agent2@agent.com','Agent2', '0658415623', '$2a$10$7KP/ADSkFDZvXY29PKY/7.auVm6wh.5/yoDfqPzZVWu.ynXywu3/a', 'Agentic2', 'agent2', true,'');
INSERT INTO user (role, city, email, name, number, password_hash, surname, username, activated,adress) VALUES ('USER', 'Vranje', 'user@user.com','User', '0641569589', '$2a$10$HcZhQGkdYjhCo88cS.ItSuLS3k6sf5LX5Q1vzubkeuBs5xtUrQlGW', 'Useric', 'user', true,'Futoska 38');
INSERT INTO user (role, city, email, name, number, password_hash, surname, username, activated,adress) VALUES ('USER', 'Beograd', 'mironikos94@gmail.com', 'Dejan', '0615565239', '$2a$10$6m31kUpEGWGUzgprvOr1HuR8UN6MLVsZ5WhWsggbB5cFGAq/ag17O', 'Stojkic', 'dejan', true,'Becejska 38');
INSERT INTO user (role, city, email, name, number, password_hash, surname, username, activated,adress) VALUES ('USER', 'Novi Sad', 'milanovicstefann@gmail.com', 'Stefan', '0645565239', '$2a$10$JTD4TwVJR52Z1pNuDBLi/e3DofPF34O.wA45lCTQj98TW1ZjRloQC', 'Milanovic', 'stefan', true,'Celopecka 38');
INSERT INTO user (role, city, email, name, number, password_hash, surname, username, activated,adress) VALUES ('USER', 'Novi Sad', 'jovananovakovic404@gmail.com', 'Jovana', '0645565239', '$2a$10$70Z7prS4LAlScnqvnb.h5e39uFWFKS2U/PEobOX.nDJGuSnLoVvSa', 'Novakovic', 'jovana', true,'Francuska 38');
INSERT INTO user (role, city, email, name, number, password_hash, surname, username, activated,adress) VALUES ('USER', 'Becej', 'ivan.becej@gmail.com', 'Ivan', '0645565239', '$2a$10$fz1ursP3273g2AXk1Vx8huZcDfNbF0Hk.0KEF4rUy56Wui5uzEMES', 'Vukasinovic', 'ivan', true,'Niska 38');

INSERT INTO country (id,name,code ) VALUES (1,'Germany','GER');
INSERT INTO country (id,name,code) VALUES (2,'Irland','IRL');
INSERT INTO country (id,name,code) VALUES (3,'France','FRA');
INSERT INTO country (id,name,code) VALUES (4,'Turkey','TUR');

INSERT INTO city (id,name, country_id ) VALUES (1,'Paris',3);
INSERT INTO city (id,name, country_id ) VALUES (2,'Istanbul',4);
INSERT INTO city (id,name, country_id) VALUES (3,'Hamburg',1);
INSERT INTO city (id,name, country_id) VALUES (4,'Berlin',1);

INSERT INTO category_of_lodging (id,label,name ) VALUES (1,'1','kategorisana');
INSERT INTO category_of_lodging (id,label,name ) VALUES (2,'2','kategorisana');
INSERT INTO category_of_lodging (id,label,name ) VALUES (3,'3','kategorisana');
INSERT INTO category_of_lodging (id,label,name ) VALUES (4,'4','kategorisana');
INSERT INTO category_of_lodging (id,label,name ) VALUES (5,'5','kategorisana');
INSERT INTO category_of_lodging (id,label,name ) VALUES (6,'0','nekategorisana');

INSERT INTO type_of_lodging (id,label ) VALUES (1,'Hotel');
INSERT INTO type_of_lodging (id,label) VALUES (2,'Breakfast and bed');
INSERT INTO type_of_lodging (id,label ) VALUES (3,'Apartman');

INSERT INTO additional_service (id,name) VALUES (1,'parking')
INSERT INTO additional_service (id,name) VALUES (2,'wi_fi')
INSERT INTO additional_service (id,name) VALUES (3,'breakfast')
INSERT INTO additional_service (id,name) VALUES (4,'half_board')
INSERT INTO additional_service (id,name) VALUES (5,'full_board')
INSERT INTO additional_service (id,name) VALUES (6,'tv')
INSERT INTO additional_service (id,name) VALUES (7,'kitchen')
INSERT INTO additional_service (id,name) VALUES (8 ,'private_bathroom')

INSERT INTO lodging (id,address,details ,rating,persons_number,category_id,type_id,city_id,agent_id,title) VALUES (1,'Bulevar Despota Stefana 7','NOVOO ,nove stvari vise detalja na telefon ..... xD',4,4 ,6,2,1,2,'Dekijev luksuz');
INSERT INTO lodging (id,address,details ,rating,persons_number,category_id,type_id,city_id,agent_id,title) VALUES (2,'Bulevar Despota Stefana 35','Starija ali ocuvana garsonjeraa',3,2,6,1,2,2,'Dekijeve sobe');
INSERT INTO lodging (id,address,details ,rating,persons_number,category_id,type_id,city_id,agent_id,title) VALUES (3,'Sekspirova 42','Garsonjera',3,1,1,1,2,2,'Dekijeve odaje');
INSERT INTO lodging (id,address,details ,rating,persons_number,category_id,type_id,city_id,agent_id,title) VALUES (4,'Tolstojeva 23','Stan, dvosoban',4,1,2,1,4,2,'Dekijev hotel');
INSERT INTO lodging (id,address,details ,rating,persons_number,category_id,type_id,city_id,agent_id,title) VALUES (5,'Andriceva 22','Nesto',2,1,5,1,3,2,'Dekijev mediteranos');

INSERT INTO lodging (id,address,details ,rating,persons_number,category_id,type_id,city_id,agent_id,title) VALUES (6,'Bulevar Oslobodjenja 7','NOVOO ,nove stvari vise detalja na telefon ..... xD',4,4 ,1,2,1,2,'Vila Deki');
INSERT INTO lodging (id,address,details ,rating,persons_number,category_id,type_id,city_id,agent_id,title) VALUES (7,'Bulevar Oslobodjenja 163','Starija ali ocuvana garsonjeraa',3,2,5,1,2,2,'Deki Harizma');
INSERT INTO lodging (id,address,details ,rating,persons_number,category_id,type_id,city_id,agent_id,title) VALUES (8,'Karadjaordjeva 25','Garsonjera',3,1,4,1,2,2,'Deki Aqua');
INSERT INTO lodging (id,address,details ,rating,persons_number,category_id,type_id,city_id,agent_id,title) VALUES (9,'Karadjaordjeva 17','Stan, dvosoban',4,2,2,1,4,2,'Deki Apartments');
INSERT INTO lodging (id,address,details ,rating,persons_number,category_id,type_id,city_id,agent_id,title) VALUES (10,'Branka Radicevica 12','Nesto',2,3,3,1,3,2,'Deki Ludila');

INSERT  INTO image (id,url,lodging_id) VALUES (1,'https://goo.gl/rjfPwB',1);
INSERT  INTO image (id,url,lodging_id) VALUES  (2,'https://goo.gl/3U3VUf',1);
INSERT  INTO image (id,url,lodging_id) VALUES (3,'https://goo.gl/fZAobN',1);
INSERT  INTO image (id,url,lodging_id) VALUES (4,'https://goo.gl/M8cxGj',2);
INSERT  INTO image (id,url,lodging_id) VALUES (5,'https://goo.gl/aVheug',2);
INSERT  INTO image (id,url,lodging_id) VALUES (6,'https://goo.gl/p5sR4g',2);
INSERT  INTO image (id,url,lodging_id) VALUES (7,'https://goo.gl/fZAobN',3);
INSERT  INTO image (id,url,lodging_id) VALUES (8,'https://goo.gl/KCSMVt',3);
INSERT  INTO image (id,url,lodging_id) VALUES (9,'https://goo.gl/gLeY6y',4);
INSERT  INTO image (id,url,lodging_id) VALUES (10,'https://goo.gl/DvL6Ac',4);
INSERT  INTO image (id,url,lodging_id) VALUES (11,'https://goo.gl/rjfPwB',5);
INSERT  INTO image (id,url,lodging_id) VALUES  (12,'https://goo.gl/3U3VUf',5);
INSERT  INTO image (id,url,lodging_id) VALUES (13,'https://goo.gl/fZAobN',6);
INSERT  INTO image (id,url,lodging_id) VALUES (14,'https://goo.gl/M8cxGj',6);
INSERT  INTO image (id,url,lodging_id) VALUES (15,'https://goo.gl/aVheug',7);
INSERT  INTO image (id,url,lodging_id) VALUES (16,'https://goo.gl/p5sR4g',7);
INSERT  INTO image (id,url,lodging_id) VALUES (17,'https://goo.gl/fZAobN',8);
INSERT  INTO image (id,url,lodging_id) VALUES (18,'https://goo.gl/KCSMVt',9);
INSERT  INTO image (id,url,lodging_id) VALUES (19,'https://goo.gl/gLeY6y',10);
INSERT  INTO image (id,url,lodging_id) VALUES (20,'https://goo.gl/DvL6Ac',10);

INSERT INTO lodging_additional_service (additional_service_id,lodging_id) VALUES (1,1);
INSERT INTO lodging_additional_service (additional_service_id,lodging_id) VALUES (3,1);
INSERT INTO lodging_additional_service (additional_service_id,lodging_id) VALUES (4,1);
INSERT INTO lodging_additional_service (additional_service_id,lodging_id) VALUES (5,1);
INSERT INTO lodging_additional_service (additional_service_id,lodging_id) VALUES (1,2);
INSERT INTO lodging_additional_service (additional_service_id,lodging_id) VALUES (2,2);
INSERT INTO lodging_additional_service (additional_service_id,lodging_id) VALUES (3,2);
INSERT INTO lodging_additional_service (additional_service_id,lodging_id) VALUES (6,2);

INSERT INTO price_list (id,year,date_created, january,february,mart,april,may,june,july,august,september,october,november,december,lodging_id) VALUES (1,'2018','2007-01-24 19:21:58',1.31,20.00,34.00,39.12,41.19,145.85,49.12,55.95,78.12,67.00,56.00,45.99,1);
INSERT INTO price_list (id,year,date_created, january,february,mart,april,may,june,july,august,september,october,november,december,lodging_id) VALUES (2,'2018','2008-01-24 19:21:58',2.31,20.00,34.00,39.12,41.19,245.85,49.12,55.95,78.12,67.00,56.00,45.99,2);
INSERT INTO price_list (id,year,date_created, january,february,mart,april,may,june,july,august,september,october,november,december,lodging_id) VALUES (3,'2018','2009-01-24 19:21:58',3.31,20.00,34.00,39.12,41.19,1345.85,49.12,55.95,78.12,67.00,56.00,45.99,3);
INSERT INTO price_list (id,year,date_created, january,february,mart,april,may,june,july,august,september,october,november,december,lodging_id) VALUES (4,'2018','2000-01-24 19:21:58',4.31,20.00,34.00,39.12,41.19,445.85,49.12,55.95,78.12,67.00,56.00,45.99,4);
INSERT INTO price_list (id,year,date_created, january,february,mart,april,may,june,july,august,september,october,november,december,lodging_id) VALUES (5,'2018','2010-01-24 19:21:58',5.31,20.00,34.00,39.12,41.19,545.85,49.12,55.95,78.12,67.00,56.00,45.99,5);
INSERT INTO price_list (id,year,date_created, january,february,mart,april,may,june,july,august,september,october,november,december,lodging_id) VALUES (6,'2018','2007-01-24 19:21:58',6.31,20.00,34.00,39.12,41.19,645.85,49.12,55.95,78.12,67.00,56.00,45.99,6);
INSERT INTO price_list (id,year,date_created, january,february,mart,april,may,june,july,august,september,october,november,december,lodging_id) VALUES (7,'2018','2008-01-24 19:21:58',7.31,20.00,34.00,39.12,41.19,745.85,49.12,55.95,78.12,67.00,56.00,45.99,7);
INSERT INTO price_list (id,year,date_created, january,february,mart,april,may,june,july,august,september,october,november,december,lodging_id) VALUES (8,'2018','2009-01-24 19:21:58',8.31,20.00,34.00,39.12,41.19,845.85,49.12,55.95,78.12,67.00,56.00,45.99,8);
INSERT INTO price_list (id,year,date_created, january,february,mart,april,may,june,july,august,september,october,november,december,lodging_id) VALUES (9,'2018','2000-01-24 19:21:58',9.31,20.00,34.00,39.12,41.19,945.85,49.12,55.95,78.12,67.00,56.00,45.99,9);
INSERT INTO price_list (id,year,date_created, january,february,mart,april,may,june,july,august,september,october,november,december,lodging_id) VALUES (10,'2017','2010-01-24 19:21:58',10.31,20.00,34.00,39.12,41.19,1045.85,49.12,55.95,78.12,67.00,56.00,45.99,7);

INSERT INTO comment (id,body,accepted,lodging_id,user_id) VALUES (1,'Samo kvalitetan text',true,1,3 );
INSERT INTO comment (id,body,accepted,lodging_id,user_id) VALUES (2,'Cao druze, poruka nije namenskaa',false,2,5 );
INSERT INTO comment (id,body,accepted,lodging_id,user_id) VALUES (3,'A komentar je na mestu ,dje je problem objaviteee ,ah !',false,2,7 );
INSERT INTO comment (id,body,accepted,lodging_id,user_id) VALUES (4,'Pozdrav za smestaj Numanovic',false,1,6 );
INSERT INTO comment (id,body,accepted,lodging_id,user_id) VALUES (5,'Odlicno!',true, 1, 5);
INSERT INTO comment (id,body,accepted,lodging_id,user_id) VALUES (6,'Lep smestaj.',true, 1, 6);

INSERT INTO message (id,title,body,date_sent,receiver_id,sender_id) VALUES (2,'Naslov za vavek vekova ','Pozdrav ja se zovem Marica i mozemo li se upoznati','2017-02-22 10:42:58',5,2 );
INSERT INTO message (id,title,body,date_sent,receiver_id,sender_id) VALUES (1,'Odgovor marici ','Cao Marice , nisam tu za vikend cujemo se nekad !!','2017-02-22 11:02:58',2,5 );
INSERT INTO message (body, date_sent, title, receiver_id, sender_id) VALUES ('Hiljadu lepih stvari ti j e trebalo ', '2018-06-19', 'Grahovac', 2, 5);

INSERT INTO rating (id,date_created,star,user_id,lodging_id) VALUES (1,'2018-04-22 09:42:58',4,3,2 );

INSERT INTO reservation (id,date_start,date_end,active,visited,lodging_id,user_id) VALUES (1,'2018-04-21 10:42:58','2018-05-22 10:42:58',true,false ,1,3 );
INSERT INTO reservation (id,date_start,date_end,active,visited,lodging_id,user_id) VALUES (2,'2018-05-22 10:42:58','2018-06-22 10:42:58',true,true ,2,4 );
INSERT INTO reservation (id,date_start,date_end,active,visited,lodging_id,user_id) VALUES (3,'2018-06-23 10:42:58','2018-07-22 10:42:58',true,false ,3,5 );
INSERT INTO reservation (id,date_start,date_end,active,visited,lodging_id,user_id) VALUES (4,'2018-07-24 10:42:58','2018-08-22 10:42:58',true,true ,4,6 );
INSERT INTO reservation (id,date_start,date_end,active,visited,lodging_id,user_id) VALUES (5,'2018-08-25 10:42:58','2018-09-22 10:42:58',true,true ,1,3 );
INSERT INTO reservation (id,date_start,date_end,active,visited,lodging_id,user_id) VALUES (6,'2018-09-26 10:42:58','2018-10-22 10:42:58',true,false ,2,4 );




INSERT INTO permission (id,role,endpoint, method) VALUES (1,'USER', '/addtional-service', 'GET');
INSERT INTO permission (id,role,endpoint, method) VALUES (2,'USER', '/addtional-service/{id}', 'GET');
INSERT INTO permission (id,role,endpoint, method) VALUES (3,'ADMIN', '/addtional-service', 'POST');
INSERT INTO permission (id,role,endpoint, method) VALUES (4,'ADMIN', '/addtional-service/{id}', 'PUT');
INSERT INTO permission (id,role,endpoint, method) VALUES (5,'ADMIN', '/addtional-service/{id}', 'DELETE');

INSERT INTO permission (id,role,endpoint, method) VALUES (6,'USER', '/lodging/getLodgings', 'GET');
INSERT INTO permission (id,role,endpoint, method) VALUES (7,'USER', '/lodging/{id}', 'GET');
INSERT INTO permission (id,role,endpoint, method) VALUES (8,'AGENT', '/lodging/{id}', 'POST');
INSERT INTO permission (id,role,endpoint, method) VALUES (9,'AGENT', '/lodging/{id}', 'PUT');
INSERT INTO permission (id,role,endpoint, method) VALUES (10,'AGENT', '/lodging/{id}', 'DELETE');


INSERT INTO permission (id,role,endpoint, method) VALUES (11,'USER', '/priceList/{id}', 'GET');
INSERT INTO permission (id,role,endpoint, method) VALUES (12,'USER', '/priceList/getPriceLists', 'GET');
INSERT INTO permission (id,role,endpoint, method) VALUES (13,'AGENT', '/priceList', 'POST');
INSERT INTO permission (id,role,endpoint, method) VALUES (14,'AGENT', '/priceList/{id}', 'PUT');

INSERT INTO permission (id,role,endpoint, method) VALUES (15,'USER', '/reservations/{idLodging}', 'POST');
INSERT INTO permission (id,role,endpoint, method) VALUES (16,'USER', '/reservations', 'GET');
INSERT INTO permission (id,role,endpoint, method) VALUES (17,'USER', '/reservations/cancel/{id}', 'GET');
INSERT INTO permission (id,role,endpoint, method) VALUES (18,'USER', '/reservations/getReservationByLodging/{idLodg}', 'GET');
INSERT INTO permission (id,role,endpoint, method) VALUES (19,'USER', '/reservations/check/{id}/{startDate}/{endDate}', 'GET');

INSERT INTO permission (id,role,endpoint, method) VALUES (20,'USER', '/certificates', 'GET');
INSERT INTO permission (id,role,endpoint, method) VALUES (21,'USER', '/certificates', 'POST');
INSERT INTO permission (id,role,endpoint, method) VALUES (22,'USER', '/certificates/issuers', 'GET');
INSERT INTO permission (id,role,endpoint, method) VALUES (23,'USER', '/certificates/{id}', 'GET');
INSERT INTO permission (id,role,endpoint, method) VALUES (24,'USER', '/certificates/check/{id}', 'GET');
INSERT INTO permission (id,role,endpoint, method) VALUES (25,'USER', '/certificates/download/{id}', 'GET');
INSERT INTO permission (id,role,endpoint, method) VALUES (26,'ADMIN', '/certificates/revoke/{id}', 'GET');
INSERT INTO permission (id,role,endpoint, method) VALUES (27,'ADMIN', '/certificates/{id}', 'DELETE');






