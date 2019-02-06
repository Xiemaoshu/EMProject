-- 一、删除数据库
DROP DATABASE IF EXISTS emdb ;
-- 二、创建数据库
CREATE DATABASE emdb CHARACTER SET UTF8 ;
-- 三、使用数据库
USE emdb ;
-- 四、创建数据表
-- 1、用户数据表
CREATE TABLE member (
	mid					VARCHAR(50),
	password			VARCHAR(32),
	name				VARCHAR(20),
	sflag				INT,
	CONSTRAINT pk_mid PRIMARY KEY (mid)
) type = innodb;
-- 2、角色数据表
CREATE TABLE role (
	rid					INT 	AUTO_INCREMENT ,
	title				VARCHAR(50),
	flag				VARCHAR(50),
	CONSTRAINT pk_rid PRIMARY KEY (rid)
) type = innodb;
-- 3、用户-角色关系表
CREATE TABLE member_role (
	mid					VARCHAR(50),
	rid 				INT ,
	CONSTRAINT fk_mid1 FOREIGN KEY(mid) REFERENCES member(mid) ,
	CONSTRAINT fk_rid1 FOREIGN KEY(rid) REFERENCES role(rid)
) type = innodb;
-- 4、权限表
CREATE TABLE action (
	actid 				INT 	AUTO_INCREMENT ,
	title 				VARCHAR(50),
	flag 				VARCHAR(50),
	CONSTRAINT pk_actid PRIMARY KEY (actid) 
) type = innodb;
-- 5、角色-权限关系表
CREATE TABLE role_action (
	rid 				INT,
	actid 				INT,
	CONSTRAINT fk_rid6 FOREIGN KEY(rid) REFERENCES role(rid) ,
	CONSTRAINT fk_actid6 FOREIGN KEY(actid) REFERENCES action(actid)
) type = innodb;

-- 6、职位等级表
CREATE TABLE level (
	lid					INT 	AUTO_INCREMENT ,
	losal 				DOUBLE,
	hisal 				DOUBLE,
	title 				VARCHAR(50),
	flag 				VARCHAR(10),
	CONSTRAINT pk_lid PRIMARY KEY (lid)
) type = innodb;

-- 7、部门表
CREATE TABLE dept (
	deptno 				INT 	AUTO_INCREMENT ,
	dname 				VARCHAR(50),
	maxnum 				INT,
	currnum				INT	DEFAULT 0,
	CONSTRAINT pk_deptno PRIMARY KEY (deptno) 
) type = innodb;

-- 8、创建雇员表
CREATE TABLE emp (
	empno 				INT 	AUTO_INCREMENT ,
	deptno 				INT,
	mid 				VARCHAR(50),
	lid 				INT,
	ename 				VARCHAR(50),
	job 				VARCHAR(50),
	sal 				DOUBLE,
	comm 				DOUBLE,
	hiredate 			DATE,
	photo				VARCHAR(200) ,
	flag				INT ,
	CONSTRAINT pk_empno PRIMARY KEY (empno) ,
	CONSTRAINT fk_deptno3 FOREIGN KEY(deptno) REFERENCES dept(deptno) ,
	CONSTRAINT fk_lid3 FOREIGN KEY(lid) REFERENCES level(lid)
) type = innodb;
-- 9、雇员工资变化记录表
CREATE TABLE elog (
	elid				INT 	AUTO_INCREMENT ,
	empno				INT,
	deptno				INT,
	mid					VARCHAR(50),
	lid					INT,
	job					VARCHAR(50),
	sal					DOUBLE,
	comm				DOUBLE,	
	sflag				INT,
	flag				INT,
	note				TEXT,
	CONSTRAINT pk_elid PRIMARY KEY (elid) ,
	CONSTRAINT fk_empno5 FOREIGN KEY(empno) REFERENCES emp(empno) ON DELETE CASCADE ,
	CONSTRAINT fk_lid5 FOREIGN KEY(lid) REFERENCES level(lid) ,
	CONSTRAINT fk_deptno5 FOREIGN KEY(deptno) REFERENCES dept(deptno)
) type = innodb;
-- 五、测试数据
-- 1、增加角色信息
INSERT INTO role(title,flag) VALUES ('管理员','member') ;
INSERT INTO role(title,flag) VALUES ('部门管理','dept') ;
INSERT INTO role(title,flag) VALUES ('雇员管理','emp') ;
-- 2、增加权限信息
INSERT INTO action(title,flag) VALUES ('增加管理员','member:add') ;
INSERT INTO action(title,flag) VALUES ('管理员列表','member:list') ;
INSERT INTO action(title,flag) VALUES ('部门增加','dept:add') ;
INSERT INTO action(title,flag) VALUES ('部门列表','dept:list') ;
INSERT INTO action(title,flag) VALUES ('部门编辑','dept:edit') ;
INSERT INTO action(title,flag) VALUES ('员工入职','emp:add') ;
INSERT INTO action(title,flag) VALUES ('员工列表','emp:list') ;
INSERT INTO action(title,flag) VALUES ('员工编辑','emp:edit') ;
INSERT INTO action(title,flag) VALUES ('员工离职','emp:remove') ;
-- 3、增加角色与权限关系
INSERT INTO role_action(rid,actid) VALUES (1,1) ;
INSERT INTO role_action(rid,actid) VALUES (1,2) ;
INSERT INTO role_action(rid,actid) VALUES (2,3) ;
INSERT INTO role_action(rid,actid) VALUES (2,4) ;
INSERT INTO role_action(rid,actid) VALUES (2,5) ;
INSERT INTO role_action(rid,actid) VALUES (3,6) ;
INSERT INTO role_action(rid,actid) VALUES (3,7) ;
INSERT INTO role_action(rid,actid) VALUES (3,8) ;
INSERT INTO role_action(rid,actid) VALUES (3,9) ;

-- 4、增加管理员信息
-- salt : mldnjava,Base64 : “bWxkbmphdmE=”，格式：密码password({salt})
-- admin / hello
INSERT INTO member(mid,password,name,sflag) VALUES ('admin','3E585791E7C446317AF238314843DDEB','管理员',1) ;
-- mermard / hello
INSERT INTO member(mid,password,name,sflag) VALUES ('mermaid','3E585791E7C446317AF238314843DDEB','老李',0) ;
-- mldn / java
INSERT INTO member(mid,password,name,sflag) VALUES ('mldn','08FD48BA8B67F026CE01E4DD15BF717A','隔壁老王',0) ;
-- xiemaoshu / 123
INSERT INTO member(mid,password,name,sflag) VALUES ('xiemaoshu','D0AEA5478F9FA4799DD2AE2B9F99A15E','谢茂树',1) ;

-- 5、管理员与角色权限
INSERT INTO member_role(mid,rid) VALUES ('admin',1) ;
INSERT INTO member_role(mid,rid) VALUES ('admin',2) ;
INSERT INTO member_role(mid,rid) VALUES ('mermaid',2) ;
INSERT INTO member_role(mid,rid) VALUES ('mermaid',2) ;
INSERT INTO member_role(mid,rid) VALUES ('mldn',3) ;
INSERT INTO member_role(mid,rid) VALUES ('mldn',3) ;
INSERT INTO member_role(mid,rid) VALUES ('xiemaoshu',1) ;
INSERT INTO member_role(mid,rid) VALUES ('xiemaoshu',2) ;
-- 6、部门表数据
INSERT INTO dept(dname,maxnum) VALUES ('财务部',3) ;
INSERT INTO dept(dname,maxnum) VALUES ('销售部',20) ;
INSERT INTO dept(dname,maxnum) VALUES ('开发部',10) ;
INSERT INTO dept(dname,maxnum) VALUES ('产品部',3) ;
INSERT INTO dept(dname,maxnum) VALUES ('后勤部',2) ;

-- 7、工资等级表数据
INSERT INTO level(losal,hisal,title,flag) VALUES (1500.0,3000.0,'实习生','T1') ;
INSERT INTO level(losal,hisal,title,flag) VALUES (3001.0,5000.0,'公司员工','T2') ;
INSERT INTO level(losal,hisal,title,flag) VALUES (5001.0,8000.0,'小组组长','T3') ;
INSERT INTO level(losal,hisal,title,flag) VALUES (8001.0,12000.0,'部门副经理','T4') ;
INSERT INTO level(losal,hisal,title,flag) VALUES (12001.0,18000.0,'部门经理','T5') ;
INSERT INTO level(losal,hisal,title,flag) VALUES (18001.0,25000.0,'总监','T6') ;
INSERT INTO level(losal,hisal,title,flag) VALUES (25001.0,35000.0,'副总裁','T7') ;
INSERT INTO level(losal,hisal,title,flag) VALUES (35001.0,99999.0,'总裁','T8') ;
