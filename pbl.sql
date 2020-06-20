CREATE SCHEMA pbl_db;
USE pbl_db;

DROP TABLE IF EXISTS User;
CREATE TABLE IF NOT EXISTS User (
  userID VARCHAR(15) NOT NULL ,
  userName VARCHAR(50) NOT NULL,
  password VARCHAR(50) NOT NULL,
  email VARCHAR(100) DEFAULT NULL,
  phone VARCHAR(20) DEFAULT NULL,
  school VARCHAR(50) DEFAULT NULL,
  department VARCHAR(50) DEFAULT NULL,
  imgPath VARCHAR(100) DEFAULT NULL ,
  role INT(1) DEFAULT 0,
  PRIMARY KEY (userID)
  )DEFAULT CHARACTER SET = utf8;

DROP TABLE IF EXISTS Admin;
CREATE TABLE IF NOT EXISTS Admin (
  adminID VARCHAR(15) NOT NULL ,
  password VARCHAR(50) NOT NULL,
  PRIMARY KEY (adminID)
  )DEFAULT CHARACTER SET = utf8;

DROP TABLE IF EXISTS File;
CREATE TABLE IF NOT EXISTS File (
  fileID INT(32) NOT NULL AUTO_INCREMENT,
  fileName VARCHAR(50) NOT NULL,
  filePath VARCHAR(100) NOT NULL,
  uploadTime DATETIME NOT NULL ,
  uploadUserID VARCHAR(15) NOT NULL ,
  programID INT(32) NOT NULL,
  FOREIGN KEY (uploadUserID) REFERENCES User(userID),
  PRIMARY KEY (fileID)
)DEFAULT CHARACTER SET = utf8;
#INSERT INTO User VALUES ("test","test","test","test","test","test","test",0,0);
#INSERT INTO File VALUES (0,"test","test",now(),"test");
#ALTER TABLE User ADD CONSTRAINT FOREIGN KEY (imgID) REFERENCES File(FILEID);


DROP TABLE IF EXISTS Course;
CREATE TABLE IF NOT EXISTS Course (
  courseID INT(32) NOT NULL AUTO_INCREMENT,
  courseName VARCHAR(50) NOT NULL,
  introduction VARCHAR(255) NOT NULL,
  imgPath VARCHAR (100) NOT NULL ,
  startTime DATETIME NOT NULL,
  endTime DATETIME NOT NULL,
  hasDelete INT(1) DEFAULT 0,
  teacherID VARCHAR(15) NOT NULL,
  PRIMARY KEY (courseID),
  FOREIGN KEY (teacherID) references User(userID)
  )DEFAULT CHARACTER SET = utf8;

DROP TABLE IF EXISTS Program;
CREATE TABLE IF NOT EXISTS Program (
  programID INT(32) NOT NULL AUTO_INCREMENT,
  programName VARCHAR(50) NOT NULL,
  introduction VARCHAR(255) NOT NULL,
  startTime DATETIME NOT NULL,
  endTime DATETIME NOT NULL,
  teacherRatio double(5,2),
  studentRatio double(5,2),
  courseID INT(32) NOT NULL,
  gradePolicy VARCHAR(255) NOT NULL,
  hasDelete INT(1) DEFAULT 0,
  PRIMARY KEY (programID),
  FOREIGN KEY (courseID) references Course(courseID)
  )DEFAULT CHARACTER SET = utf8;

ALTER TABLE File ADD CONSTRAINT FOREIGN KEY (programID) REFERENCES Program(programID);
DROP TABLE IF EXISTS Task;
CREATE TABLE IF NOT EXISTS Task (
  taskID INT(32) NOT NULL AUTO_INCREMENT,
  taskName VARCHAR(50) NOT NULL,
  introduction VARCHAR(255) NOT NULL,
  startTime DATETIME NOT NULL,
  endTime DATETIME NOT NULL,
  programID INT(32) NOT NULL,
  hasDelete INT(1) DEFAULT 0,
  PRIMARY KEY (taskID),
  FOREIGN KEY (programID) references Program(programID)
)DEFAULT CHARACTER SET = utf8;

DROP TABLE IF EXISTS Discussion;
CREATE TABLE IF NOT EXISTS Discussion
(
  discussionID INT(32) NOT NULL AUTO_INCREMENT,
  programID INT(32) NOT NULL,
  PRIMARY KEY (discussionID),
  FOREIGN KEY (programID) references Program(programID)
)DEFAULT CHARACTER SET = utf8;

DROP TABLE IF EXISTS Comment;
CREATE TABLE IF NOT EXISTS Comment (
  commentID INT(32) NOT NULL AUTO_INCREMENT,
  content VARCHAR(255) NOT NULL,
  replyID INT(32) DEFAULT NULL,
  discussionID INT(32) NOT NULL,
  userID VARCHAR(15) NOT NULL ,
  publishTime DATE NOT NULL ,
  PRIMARY KEY (commentID),
  FOREIGN KEY (discussionID) REFERENCES Discussion(discussionID),
  FOREIGN KEY (userID) REFERENCES User(userID)
)DEFAULT CHARACTER SET = utf8;


DROP TABLE IF EXISTS course_user;
CREATE TABLE IF NOT EXISTS course_user
(
  courseID INT(32) NOT NULL,
  userID VARCHAR(15) NOT NULL ,
  PRIMARY KEY (courseID,userID),
  FOREIGN KEY (courseID) REFERENCES Course(courseID),
  FOREIGN KEY (userID) REFERENCES User(userID)
)DEFAULT CHARACTER SET = utf8;



DROP TABLE IF EXISTS program_user;
CREATE TABLE IF NOT EXISTS program_user
(
  programID INT(32) NOT NULL,
  userID VARCHAR(15) NOT NULL ,
  isLeader int(1) default 0,
  PRIMARY KEY (programID,userID),
  FOREIGN KEY (programID) REFERENCES Program(programID),
  FOREIGN KEY (userID) REFERENCES User(userID)
)DEFAULT CHARACTER SET = utf8;

DROP TABLE IF EXISTS task_user;
CREATE TABLE IF NOT EXISTS task_user
(
  taskID INT(32) NOT NULL,
  userID VARCHAR(15) NOT NULL ,
  isFinish int(1) default 0,
  PRIMARY KEY (taskID,userID),
  FOREIGN KEY (taskID) REFERENCES task(taskID),
  FOREIGN KEY (userID) REFERENCES User(userID)
)DEFAULT CHARACTER SET = utf8;

DROP TABLE IF EXISTS grades;
CREATE TABLE IF NOT EXISTS grades
(
  id INT(32) NOT NULL AUTO_INCREMENT,
  programID  INT(32) NOT NULL,
  userID1 VARCHAR(15) NOT NULL ,
  userID2 VARCHAR(15) NOT NULL ,
  role int(2) NOT NULL ,
  grade double,
  evaluation  VARCHAR(150),
  PRIMARY KEY (id),
  FOREIGN KEY (programID) REFERENCES Program(programID),
  FOREIGN KEY (userID1) REFERENCES User(userID),
  FOREIGN KEY (userID2) REFERENCES User(userID)
)DEFAULT CHARACTER SET = utf8;

INSERT INTO User VALUES ("xiaoming","xiaoming","e10adc3949ba59abbe56e057f20f883e","test","test","test","test","/test",0);
INSERT INTO User VALUES ("kaiyudai","kaiyudai","e10adc3949ba59abbe56e057f20f883e","test","test","test","test","/test",1);
INSERT INTO Admin VALUES ("administrator","e10adc3949ba59abbe56e057f20f883e");
INSERT INTO Course VALUES (null,"程序设计","programming","/test",now(),now(),0,"kaiyudai");
INSERT INTO Program VALUES (null,"project","programming",now(),now(),0.6,0.4,1,"policy",0);
INSERT INTO Task VALUES (null,"lab1","programming",now(),now(),1,0);
INSERT INTO File VALUES (null,"testFile","./test",now(),"xiaoming",1);
INSERT INTO Discussion VALUES (null,1);
INSERT INTO Comment VALUES (null,"this is a comment",null,1,"xiaoming",now());



















