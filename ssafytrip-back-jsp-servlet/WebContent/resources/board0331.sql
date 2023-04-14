DROP TABLE IF EXISTS `enjoytrip`.`board` ;

CREATE TABLE IF NOT EXISTS `enjoytrip`.`board` (
  `article_no` INT NOT NULL AUTO_INCREMENT,
  `user_id` VARCHAR(16) NULL DEFAULT NULL,
  `subject` VARCHAR(100) NULL DEFAULT NULL,
  `content` VARCHAR(2000) NULL DEFAULT NULL,
  `hit` INT NULL DEFAULT 0,
  `register_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`article_no`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
LOCK TABLES `board` WRITE;
INSERT INTO `board` (user_id, subject, content)VALUES ( '김이름','1번 글 제목이 들어갑니다.', '1번 글 내용이 들어갑니다.'),( '김이름','2번 글 제목이 들어갑니다.', '2번 글 내용이 들어갑니다.'),( '김이름','3번 글 제목이 들어갑니다.', '3번 글 내용이 들어갑니다.');
UNLOCK TABLES;
COLLATE = utf8mb4_0900_ai_ci;