
drop database if exists battle_buddies_db;

use battle_buddies_db;
insert into users (password,  username, email,  is_admin) values ( '$2a$10$DRibzeKQLUtnTOF5nCnnMegxRg0Tu4hLrVLX0u1L9QCbFZkiGtSS.', 'admin', 'cwhitloe0@kickstarter.com', true);
insert into users (password,  username, email,  is_admin) values ( '$2a$10$DRibzeKQLUtnTOF5nCnnMegxRg0Tu4hLrVLX0u1L9QCbFZkiGtSS.', 'izzy', 'veye1@printfriendly.com', true);
insert into users (password,  username, email,  is_admin) values ( '$2a$10$DRibzeKQLUtnTOF5nCnnMegxRg0Tu4hLrVLX0u1L9QCbFZkiGtSS.', 'bridget', 'crudiger2@vk.com',  true);
insert into users (password,  username, email,  is_admin) values ( '$2a$10$DRibzeKQLUtnTOF5nCnnMegxRg0Tu4hLrVLX0u1L9QCbFZkiGtSS.', 'dani', 'mpellatt3@themeforest.net',  true);
insert into users (password,  username, email,  is_admin) values ( '$2a$10$DRibzeKQLUtnTOF5nCnnMegxRg0Tu4hLrVLX0u1L9QCbFZkiGtSS.', 'vdunbar4', 'oharrowsmith4@marriott.com',  false);
insert into users (password,  username, email,  is_admin) values ( '$2a$10$DRibzeKQLUtnTOF5nCnnMegxRg0Tu4hLrVLX0u1L9QCbFZkiGtSS.', 'dgirardi5', 'areignard5@xing.com', false);
insert into users (password,  username, email,  is_admin) values ( '$2a$10$DRibzeKQLUtnTOF5nCnnMegxRg0Tu4hLrVLX0u1L9QCbFZkiGtSS.', 'ofrye6', 'susher6@state.gov', true);
insert into users (password,  username, email,  is_admin) values ( '$2a$10$DRibzeKQLUtnTOF5nCnnMegxRg0Tu4hLrVLX0u1L9QCbFZkiGtSS.', 'naspinall7', 'emartyn7@free.fr', true);
insert into users (password,  username, email,  is_admin) values ( '$2a$10$DRibzeKQLUtnTOF5nCnnMegxRg0Tu4hLrVLX0u1L9QCbFZkiGtSS.', 'cogers8', 'druoss8@netscape.com', false);
insert into users (password,  username, email,  is_admin) values ( '$2a$10$DRibzeKQLUtnTOF5nCnnMegxRg0Tu4hLrVLX0u1L9QCbFZkiGtSS.',  'mghelardoni9', 'koylett9@cnbc.com', false);




insert into profiles ( branch_id,rank_id, gender, first_name, last_name, age, bio, married, mil_spouse, user_id) values ( 1,2,false, 'Gayla', 'Hedgecock', 18, 'Pellentesque at nulla. Suspendisse potenti. Cras in purus eu magna vulputate luctus', true, true, 1);
insert into profiles ( branch_id,rank_id, gender, first_name, last_name, age, bio, married, mil_spouse, user_id) values ( 2,6,true, 'Olympie', 'Jurries', 23, 'Curabitur at ipsum ac tellus semper interdum. Mauris ullamcorper purus sit amet nulla. Quisque arcu libero, rutrum ac, lobortis vel, dapibus at, diam.',false, false, 2);
insert into profiles ( branch_id,rank_id, gender, first_name, last_name, age, bio, married, mil_spouse, user_id) values ( 3,10,true, 'Obadias', 'Goby', 56, 'Maecenas leo odio, condimentum id, luctus nec, molestie sed, justo. Pellentesque viverra pede ac diam. Cras pellentesque volutpat dui.', false, false, 3);
insert into profiles ( branch_id,rank_id, gender, first_name, last_name, age, bio, married, mil_spouse, user_id) values ( 4,4,false, 'Goldarina', 'Perrinchief', 34, 'Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Vivamus vestibulum sagittis sapien. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.',false, false, 4);
insert into profiles ( branch_id,rank_id, gender, first_name, last_name, age, bio, married, mil_spouse, user_id) values ( 5,7,true, 'Hans', 'Clay', 43, 'Nullam porttitor lacus at turpis. Donec posuere metus vitae ipsum. Aliquam non mauris.',false, true, 5);
insert into profiles ( branch_id,rank_id, gender, first_name, last_name, age, bio, married, mil_spouse, user_id) values ( 1,2,true, 'Garold', 'Spark', 65, 'Morbi non lectus. Aliquam sit amet diam in magna bibendum imperdiet. Nullam orci pede, venenatis non, sodales sed, tincidunt eu, felis.', false, false, 6);
insert into profiles ( branch_id,rank_id, gender, first_name, last_name, age, bio, married, mil_spouse, user_id) values ( 2,10,true, 'Darb', 'Boncore', 57, 'Vestibulum quam sapien, varius ut, blandit non, interdum in, ante. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Duis faucibus accumsan odio. Curabitur convallis.',false, false, 7);
insert into profiles ( branch_id,rank_id, gender, first_name, last_name, age, bio, married, mil_spouse, user_id) values ( 3,9,false, 'Eugen', 'Thomazet', 27, 'Nullam porttitor lacus at turpis. Donec posuere metus vitae ipsum. Aliquam non mauris.',true, false, 8);
insert into profiles ( branch_id,rank_id, gender, first_name, last_name, age, bio, married, mil_spouse, user_id) values ( 4,7,true, 'Zsazsa', 'Haslen', 39, 'Etiam vel augue. Vestibulum rutrum rutrum neque. Aenean auctor gravida sem.',true, true, 9);
insert into profiles ( branch_id,rank_id, gender, first_name, last_name, age, bio, married, mil_spouse, user_id) values (5,1,false, 'Calvin', 'Muggach', 40, 'Curabitur at ipsum ac tellus semper interdum. Mauris ullamcorper purus sit amet nulla. Quisque arcu libero, rutrum ac, lobortis vel, dapibus at, diam.', false, true, 10);




insert into children  (age, gender, profile_id) values (2, 'Female', 2);
insert into children  (age, gender, profile_id) values (6, 'Male', 2);
insert into children  (age, gender, profile_id) values (13, 'Female', 4);
insert into children  (age, gender, profile_id) values (9, 'Male', 10);
insert into children  (age, gender, profile_id) values (2, 'Male', 7);
insert into children  (age, gender, profile_id) values (4, 'Male', 6);
insert into children  (age, gender, profile_id) values (4, 'Female', 8);
insert into children  (age, gender, profile_id) values (6, 'Female', 4);
insert into children  (age, gender, profile_id) values (7, 'Female', 10);
insert into children  (age, gender, profile_id) values (10, 'Male', 3);
insert into children  (age, gender, profile_id) values (5, 'Male', 7);
insert into children  (age, gender, profile_id) values (5, 'Female', 5);
insert into children  (age, gender, profile_id) values (3, 'Male', 8);
insert into children  (age, gender, profile_id) values (1, 'Female', 4);
insert into children  (age, gender, profile_id) values (1, 'Female', 2);
insert into children  (age, gender, profile_id) values (16, 'Female', 1);
insert into children  (age, gender, profile_id) values (8, 'Male', 1);
insert into children  (age, gender, profile_id) values (8, 'Female', 7);
insert into children  (age, gender, profile_id) values (6, 'Female', 9);
insert into children  (age, gender, profile_id) values (10, 'Male', 3);
insert into children  (age, gender, profile_id) values (12, 'Male', 3);
insert into children  (age, gender, profile_id) values (3, 'Male', 8);
insert into children  (age, gender, profile_id) values (9, 'Male', 7);



insert into hobbies (hobby) values ( 'Gardening');
insert into hobbies (hobby) values ( 'Cooking');
insert into hobbies (hobby) values ( 'Learning');
insert into hobbies (hobby) values ( 'exercising');
insert into hobbies (hobby) values ( 'Writing');
insert into hobbies (hobby) values ( 'Dance');
insert into hobbies (hobby) values ( 'Photography');
insert into hobbies (hobby) values ( 'Hiking');
insert into hobbies (hobby) values ( 'Drawing/Painting');
insert into hobbies (hobby) values ( 'Hunting');
insert into hobbies (hobby) values ( 'Sewing');
insert into hobbies (hobby) values ( 'Sports/Extreme Sports');
insert into hobbies (hobby) values ( 'Programming');
insert into hobbies (hobby) values ( 'Biking');
insert into hobbies (hobby) values ( 'Camping');
insert into hobbies (hobby) values ( 'Wood Working');
insert into hobbies (hobby) values ( 'Pottery');
insert into hobbies (hobby) values ( 'Exercise');
insert into hobbies (hobby) values ( 'Traveling');
insert into hobbies (hobby) values ( 'Playing Instruments');
insert into hobbies (hobby) values ( 'Volunteer Work');

insert into traits (trait) values ( 'Giving');
insert into traits (trait) values ( 'Clever');
insert into traits (trait) values ( 'Ambitious');
insert into traits (trait) values ( 'Honest');
insert into traits (trait) values ( 'Easygoing');
insert into traits (trait) values ( 'Quiet');
insert into traits (trait) values ( 'Insightful');
insert into traits (trait) values ( 'Devoted');
insert into traits (trait) values ( 'Confident');
insert into traits (trait) values ( 'Adventurous');
insert into traits (trait) values ( 'Focused');
insert into traits (trait) values ( 'Friendly');
insert into traits (trait) values ( 'Open minded');
insert into traits (trait) values ( 'Dependable');
insert into traits (trait) values ( 'Generous');
insert into traits (trait) values ( 'Wise');
insert into traits (trait) values ( 'Strong');
insert into traits (trait) values ( 'Loyal');
insert into traits (trait) values ( 'Hard Working');
insert into traits (trait) values ( 'Creative');











insert into hobby_profile (hobby_id, profile_id) values ( 1, 1);
insert into hobby_profile (hobby_id, profile_id) values ( 2, 2);
insert into hobby_profile (hobby_id, profile_id) values ( 3, 3);
insert into hobby_profile (hobby_id, profile_id) values ( 4, 4);
insert into hobby_profile (hobby_id, profile_id) values ( 5,5 );
insert into hobby_profile (hobby_id, profile_id) values ( 6, 6);
insert into hobby_profile (hobby_id, profile_id) values ( 7, 7);
insert into hobby_profile (hobby_id, profile_id) values ( 8, 8);
insert into hobby_profile (hobby_id, profile_id) values ( 9, 9);
insert into hobby_profile (hobby_id, profile_id) values ( 10, 10);
insert into hobby_profile (hobby_id, profile_id) values ( 11, 1);
insert into hobby_profile (hobby_id, profile_id) values ( 12, 2);
insert into hobby_profile (hobby_id, profile_id) values ( 13, 3);
insert into hobby_profile (hobby_id, profile_id) values ( 14, 4);
insert into hobby_profile (hobby_id, profile_id) values ( 15,5 );
insert into hobby_profile (hobby_id, profile_id) values ( 16, 6);
insert into hobby_profile (hobby_id, profile_id) values ( 17, 7);
insert into hobby_profile (hobby_id, profile_id) values ( 18, 8);
insert into hobby_profile (hobby_id, profile_id) values ( 19, 9);
insert into hobby_profile (hobby_id, profile_id) values ( 20, 10);



insert into profile_trait (trait_id, profile_id) values ( 1, 1);
insert into profile_trait (trait_id, profile_id) values ( 2, 2);
insert into profile_trait (trait_id, profile_id) values ( 3, 3);
insert into profile_trait (trait_id, profile_id) values ( 4, 4);
insert into profile_trait (trait_id, profile_id) values ( 5,5 );
insert into profile_trait (trait_id, profile_id) values ( 6, 6);
insert into profile_trait (trait_id, profile_id) values ( 7, 7);
insert into profile_trait (trait_id, profile_id) values ( 8, 8);
insert into profile_trait (trait_id, profile_id) values ( 9, 9);
insert into profile_trait (trait_id, profile_id) values ( 10, 10);
insert into profile_trait (trait_id, profile_id) values ( 11, 1);
insert into profile_trait (trait_id, profile_id) values ( 12, 2);
insert into profile_trait (trait_id, profile_id) values ( 13, 3);
insert into profile_trait (trait_id, profile_id) values ( 14, 4);
insert into profile_trait (trait_id, profile_id) values ( 15,5 );
insert into profile_trait (trait_id, profile_id) values ( 16, 6);
insert into profile_trait (trait_id, profile_id) values ( 17, 7);
insert into profile_trait (trait_id, profile_id) values ( 18, 8);
insert into profile_trait (trait_id, profile_id) values ( 19, 9);
insert into profile_trait (trait_id, profile_id) values ( 20, 10);



insert into locations (id, city, country, state) values (1, 'Salt Lake City', 'United States', 'UT');
insert into locations (id, city, country, state) values (2, 'San Antonio', 'United States', 'TX');
insert into locations (id, city, country, state) values (3, 'Charlotte', 'United States', 'NC');
insert into locations (id, city, country, state) values (4, 'Miami', 'United States', 'FL');
insert into locations (id, city, country, state) values (5, 'Honolulu', 'United States', 'HI');


insert into location_profile (profile_id, location_id) values (1, 2);
insert into location_profile (profile_id, location_id) values (2, 1);
insert into location_profile (profile_id, location_id) values (3, 4);
insert into location_profile (profile_id, location_id) values (4, 3);
insert into location_profile (profile_id, location_id) values (5, 5);
insert into location_profile (profile_id, location_id) values (6, 1);
insert into location_profile (profile_id, location_id) values (7, 5);
insert into location_profile (profile_id, location_id) values (8, 4);
insert into location_profile (profile_id, location_id) values (9, 3);
insert into location_profile (profile_id, location_id) values (10, 4);

Insert into branches (branch) values('Army');
Insert into branches (branch) values('Navy');
Insert into branches (branch) values('Marine Corps');
Insert into branches (branch) values('Air Force');
Insert into branches (branch) values('Coast Guard');

Insert into Ranks (rank) values('E1');
Insert into Ranks (rank) values('E2');
Insert into Ranks (rank) values('E3');
Insert into Ranks (rank) values('E4');
Insert into Ranks (rank) values('E5');
Insert into Ranks (rank) values('E6');
Insert into Ranks (rank) values('E7');
Insert into Ranks (rank) values('E8');
Insert into Ranks (rank) values('E9');
Insert into Ranks (rank) values('O1');
Insert into Ranks (rank) values('O2');
Insert into Ranks (rank) values('O3');
Insert into Ranks (rank) values('O4');
Insert into Ranks (rank) values('O5');
Insert into Ranks (rank) values('O6');
Insert into Ranks (rank) values('O7');
Insert into Ranks (rank) values('O8');
Insert into Ranks (rank) values('O9');
Insert into Ranks (rank) values('O10');








