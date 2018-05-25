-- password == username
INSERT INTO user (role, city, email, name, number, password_hash, surname, username, activated,adress) VALUES ('ADMIN', 'Novi Sad', 'admin@admin.com', 'Ivan', '0635569989', '$2a$10$gvI2xtxfH3appKH3z8n7VeLPi84yNLwz5BgsA5VPACZYvfGlX6gLW', 'Vukasinovic', 'admin', true,'' );
INSERT INTO user (role, city, email, name, number, password_hash, surname, username, activated,adress) VALUES ('AGENT', 'Beograd', 'agent@agent.com','Agent', '0641569989', '$2a$10$7KP/ADSkFDZvXY29PKY/7.auVm6wh.5/yoDfqPzZVWu.ynXywu3/a', 'Agentic', 'agent', true,'');
INSERT INTO user (role, city, email, name, number, password_hash, surname, username, activated,adress) VALUES ('USER', 'Vranje', 'user@user.com','User', '0641569589', '$2a$10$HcZhQGkdYjhCo88cS.ItSuLS3k6sf5LX5Q1vzubkeuBs5xtUrQlGW', 'Useric', 'user', true,'Futoska 38');
INSERT INTO user (role, city, email, name, number, password_hash, surname, username, activated,adress) VALUES ('USER', 'Beograd', 'mironikos94@gmail.com', 'Dejan', '0615565239', '$2a$10$6m31kUpEGWGUzgprvOr1HuR8UN6MLVsZ5WhWsggbB5cFGAq/ag17O', 'Stojkic', 'dejan', true,'Becejska 38');
INSERT INTO user (role, city, email, name, number, password_hash, surname, username, activated,adress) VALUES ('USER', 'Novi Sad', 'milanovicstefann@gmail.com', 'Stefan', '0645565239', '$2a$10$JTD4TwVJR52Z1pNuDBLi/e3DofPF34O.wA45lCTQj98TW1ZjRloQC', 'Milanovic', 'stefan', true,'Celopecka 38');
INSERT INTO user (role, city, email, name, number, password_hash, surname, username, activated,adress) VALUES ('USER', 'Novi Sad', 'jovananovakovic404@gmail.com', 'Jovana', '0645565239', '$2a$10$70Z7prS4LAlScnqvnb.h5e39uFWFKS2U/PEobOX.nDJGuSnLoVvSa', 'Novakovic', 'jovana', true,'Francuska 38');
INSERT INTO user (role, city, email, name, number, password_hash, surname, username, activated,adress) VALUES ('USER', 'Becej', 'ivan.becej@gmail.com', 'Ivan', '0645565239', '$2a$10$fz1ursP3273g2AXk1Vx8huZcDfNbF0Hk.0KEF4rUy56Wui5uzEMES', 'Vukasinovic', 'ivan', true,'Niska 38');


INSERT INTO country (id,name,code ) VALUES (1,'Germany','GER');
INSERT INTO country (id,name,code) VALUES (2,'Irland','IRL');
INSERT INTO country (id,name,code) VALUES (3,'France','FRA');


INSERT INTO city (id,name, country_id ) VALUES (1,'Paris',3);
INSERT INTO city (id,name, country_id ) VALUES (2,'Instambul',1);
INSERT INTO city (id,name, country_id) VALUES (3,'Hamburg',1);



INSERT INTO category_of_lodging (id,label,name ) VALUES (1,'5','kategorisana');
INSERT INTO category_of_lodging (id,label,name ) VALUES (2,'4','kategorisana');

INSERT INTO type_of_lodging (id,label,name ) VALUES (1,'Hotel','Stojkic lux');
INSERT INTO type_of_lodging (id,label,name ) VALUES (2,'Breakfast and bed','Frans');
INSERT INTO type_of_lodging (id,label,name ) VALUES (3,'Apartman','Numanovic');


-- INSERT INTO additional_service (id,parking,wi_fi,breakfast,half_board,full_board,tv,kitchen,private_bathroom ) VALUES (1,TRUE ,FALSE,TRUE ,FALSE,TRUE ,FALSE,TRUE ,FALSE );
-- INSERT INTO additional_service (id,parking,wi_fi,breakfast,half_board,full_board,tv,kitchen,private_bathroom ) VALUES (2,TRUE ,TRUE ,TRUE ,TRUE,TRUE ,FALSE,TRUE ,FALSE );
-- INSERT INTO additional_service (id,parking,wi_fi,breakfast,half_board,full_board,tv,kitchen,private_bathroom ) VALUES (3,TRUE ,FALSE,TRUE ,FALSE,TRUE ,FALSE,TRUE ,FALSE );

INSERT INTO additional_service (id,name) VALUES (1,'parking')
INSERT INTO additional_service (id,name) VALUES (2,'wi_fi')
INSERT INTO additional_service (id,name) VALUES (3,'breakfast')
INSERT INTO additional_service (id,name) VALUES (4,'half_board')
INSERT INTO additional_service (id,name) VALUES (5,'full_board')
INSERT INTO additional_service (id,name) VALUES (6,'tv')
INSERT INTO additional_service (id,name) VALUES (7,'kitchen')
INSERT INTO additional_service (id,name) VALUES (8 ,'private_bathroom')

INSERT INTO lodging (id,address,details , image,rating,persons_number,category_id,type_id,city_id,agent_id) VALUES (1,'Bulevar Despota Stefana 7','NOVOO ,nove stvari vise detalja na telefon ..... xD','',4,4 ,1,2,3,2);
INSERT INTO lodging (id,address,details , image,rating,persons_number,category_id,type_id,city_id,agent_id) VALUES (2,'Bulevar Despota Stefana 35','Starija ali ocuvana garsonjeraa','',3,2,1,1,2,2);

INSERT INTO lodging_additional_service (additional_service_id,lodging_id) VALUES (1,1);
INSERT INTO lodging_additional_service (additional_service_id,lodging_id) VALUES (3,1);
INSERT INTO lodging_additional_service (additional_service_id,lodging_id) VALUES (4,1);
INSERT INTO lodging_additional_service (additional_service_id,lodging_id) VALUES (5,1);

INSERT INTO lodging_additional_service (additional_service_id,lodging_id) VALUES (1,2);
INSERT INTO lodging_additional_service (additional_service_id,lodging_id) VALUES (2,2);
INSERT INTO lodging_additional_service (additional_service_id,lodging_id) VALUES (3,2);
INSERT INTO lodging_additional_service (additional_service_id,lodging_id) VALUES (6,2);

INSERT INTO price_list (id,year,date_created, january,february,mart,april,may,june,july,august,september,october,november,december) VALUES (1,'1994','2007-01-24 19:21:58',12.31,20.00,34.00,39.12,41.19,45.85,49.12,55.95,78.12,67.00,56.00,45.99);


INSERT INTO comment (id,body,accepted,lodging_id,user_id) VALUES (1,'Samo kvalitetan text',true,1,3 );
INSERT INTO comment (id,body,accepted,lodging_id,user_id) VALUES (2,'Cao druze, poruka nije namenskaa',false,2,4 );
INSERT INTO comment (id,body,accepted,lodging_id,user_id) VALUES (3,'A komentar je na mestu ,dje je problem objaviteee ,ah !',false,2,5 );
INSERT INTO comment (id,body,accepted,lodging_id,user_id) VALUES (4,'Pozdrav za smestaj Numanovic',false,1,6 );

INSERT INTO message (id,title,body,date_sent,receiver_id,sender_id) VALUES (2,'Naslov za vavek vekova ','Pozdrav ja se zovem Marica i mozemo li se upoznati','2017-02-22 10:42:58',5,4 );
INSERT INTO message (id,title,body,date_sent,receiver_id,sender_id) VALUES (1,'Odgovor marici ','Cao Marice , nisam tu za vikend cujemo se nekad !!','2017-02-22 11:02:58',4,5 );

INSERT INTO rating (id,date_created,star,user_id,lodging_id) VALUES (1,'2018-04-22 09:42:58',4,3,2 );

INSERT INTO reservation (id,date_start,date_end,active,visited,lodging_id,user_id) VALUES (1,'2018-04-22 10:42:58','2018-05-22 10:42:58',true,true ,1,3 );


