alter table tablename auto_increment=0
SET SQL_SAFE_UPDATES = 0

wp_user
ID, USER_NAME, LOGIN_NAME, LOGIN_PASSWORD, WP_ORG_ID, USER_STATUS, PHONE, EMAIL, MAJOR, IS_MAJOR
'1', '管理员', 'admin', '21232F297A57A5A743894A0E4A801FC3', '1', '0', '1', '1', '0', '1'

wp_menutree
insert into wp_menutree(parent_id,menu_name,menu_url,menu_path,menu_no,is_leaf) values(0,'系统管理','','0,',1,0);
insert into wp_menutree(parent_id,menu_name,menu_url,menu_path,menu_no,is_leaf) values(1,'部门管理','/sys/org/list','0,1,',2,1);
insert into wp_menutree(parent_id,menu_name,menu_url,menu_path,menu_no,is_leaf) values(1,'用户管理','/sys/user/userlist','0,1,',3,1);
insert into wp_menutree(parent_id,menu_name,menu_url,menu_path,menu_no,is_leaf) values(1,'角色管理','/sys/role/rolelist','0,1,',4,1);
insert into wp_menutree(parent_id,menu_name,menu_url,menu_path,menu_no,is_leaf) values(1,'菜单管理','/sys/menutree/menuinfo','0,1,',5,1);
insert into wp_menutree(parent_id,menu_name,menu_url,menu_path,menu_no,is_leaf) values(1,'授权管理','/sys/role/acllist','0,1,',6,1);

c_dir
INSERT INTO `c_dir` VALUES
(1,'高中数学',0,'0,',0,1,1,0,'gz_shuxue'),
(2,'高中语文',0,'0,',0,2,2,0,'gz_yw'),
(3,'高中英语',0,'0,',0,3,3,0,'gz_yingyu'),
(4,'高中物理',0,'0,',0,4,4,0,'gz_wuli'),
(5,'高中化学',0,'0,',0,5,5,0,'gz_huaxue'),
(6,'高中生物',0,'0,',0,6,6,0,'gz_shengwu'),
(7,'高中政治',0,'0,',0,7,7,0,'gz_zhengzhi'),
(8,'高中地理',0,'0,',0,8,8,0,'gz_dili'),
(9,'高中历史',0,'0,',0,9,9,0,'gz_lishi');




