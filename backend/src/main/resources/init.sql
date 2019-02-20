use sams;

insert into tbl_user(id, name,password_hash,email,contact_information_phone_number,contact_information_qq_number,contact_information_wc_number,grade,clazz,enabled)
values ('171249530', '梅鸦','$2a$10$aw9hWdGeKR5mYRcyAodySeMxnjgjLD0OVFBbVpFKGUDg9OVfl2GFO','171249530@smail.nju.edu.cn','18574940215','1206985125','lllshen123','2017','1',TRUE);
insert into tbl_user(id, name,password_hash,email,contact_information_phone_number,contact_information_qq_number,contact_information_wc_number,grade,clazz,enabled)
values ('171249576','周无防','$2a$10$YVgAqf2ezAJLrXluA5J/w.mC2ZAriTngt3t2ny8RNmURLQdyvN/.u','171249576@smail.nju.edu.cn','18878921212','1002646708','huotong33','2017','1',TRUE);
insert into tbl_user(id, name,password_hash,email,contact_information_phone_number,contact_information_qq_number,contact_information_wc_number,grade,clazz,enabled)
values ('171249566','月巧生','$2a$10$BNiM5Rh0TSmDpDCOtNevAe50ctNZSy1iJopTbVWbIecfX0InxpoKO','171249566@smail.nju.edu.cn','13377943556','702465531','wenceze','2017','1',TRUE);

insert into tbl_role(role_name) values('STUDENT');

insert into user_role (user_id, role_id) values('171249530', 1);
insert into user_role (user_id, role_id) values('171249576', 1);
insert into user_role (user_id, role_id) values('171249566', 1);