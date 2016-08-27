/*
 * Brady Houseknecht (20262734) ITM 462 ~ Assignment #3
 */

/* --------------------------------------------------------------
 * DROP / CREATE DATABASE
 * ------------------------------------------------------------ */

	DROP DATABASE IF EXISTS `house_brady_20262734_assignment3`;

	CREATE DATABASE `house_brady_20262734_assignment3` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

/* --------------------------------------------------------------
 * CREATE TABLES
 * ------------------------------------------------------------ */

	CREATE TABLE `house_brady_20262734_assignment3`.`stu_specialization` (
  		`SpecializationId` INT NOT NULL AUTO_INCREMENT COMMENT 'Auto-incrementing record id (aka primary key).',
  		`Title` VARCHAR(255) NOT NULL COMMENT 'Specialization title value.  This value is required and should be unique.',
  		`Description` VARCHAR(1024) NOT NULL COMMENT 'One to two sentences describing specialization.  This value is required.',
  	PRIMARY KEY (`SpecializationId`),
  	UNIQUE INDEX `Title_UNIQUE` (`Title` ASC));

	CREATE TABLE `house_brady_20262734_assignment3`.`stu_user` (
  		`UserId` INT NOT NULL AUTO_INCREMENT,
  		`Name` VARCHAR(255) NOT NULL COMMENT 'The user’s name. This value will be used to identify (or communicate) with the user within the application or via email.',
  		`Login` VARCHAR(32) NOT NULL COMMENT 'User’s login ',
  		`Password` VARCHAR(40) NOT NULL COMMENT 'Field used to store MD5 Hash.',
  		`Locked` BIT NOT NULL DEFAULT 0 COMMENT 'Field used to lock users out after 3 failed login attempts.',
  		`Email` VARCHAR(255) NOT NULL COMMENT 'User’s email address.  This field is required in case the user’s password must be reset or the user’s account enters a locked state.',
		`IsAdmin` BIT DEFAULT 0 COMMENT 'User Admin flag. When true the user has access to the admin component',
  	PRIMARY KEY (`UserId`),
  	UNIQUE INDEX `Name_UNIQUE` (`Name` ASC),
  	UNIQUE INDEX `Login_UNIQUE` (`Login` ASC));

	CREATE TABLE `house_brady_20262734_assignment3`.`stu_course` (
	  `CourseId` INT NOT NULL AUTO_INCREMENT,
	  `CourseNumber` VARCHAR(32) NOT NULL,
	  `Title` VARCHAR(256) NOT NULL,
	  `Credits` FLOAT NOT NULL,
	  `Cost` FLOAT NOT NULL,
	  `SpecializationId` INT NOT NULL,
	  PRIMARY KEY (`CourseId`),
	  UNIQUE INDEX `CourseNumber_UNIQUE` (`CourseNumber` ASC)) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

	CREATE TABLE `house_brady_20262734_assignment3`.`stu_user_course_link` (
	  `UserCourseLinkId` INT NOT NULL AUTO_INCREMENT,
	  `UserId` INT NOT NULL,
	  `CourseId` INT NOT NULL,
	  PRIMARY KEY (`UserCourseLinkId`)) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

	CREATE TABLE `house_brady_20262734_assignment3`.`stu_site` (
	  `SiteId` INT NOT NULL AUTO_INCREMENT,
	  `Title` VARCHAR(256) NOT NULL,
	  `SubTitle` VARCHAR(1024) NOT NULL,
	  `Salt` VARCHAR(10) NOT NULL,
	  PRIMARY KEY (`SiteId`)) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

	CREATE TABLE `house_brady_20262734_assignment3`.`stu_component` (
	  `ComponentId` INT NOT NULL AUTO_INCREMENT,
	  `ComponentOption` VARCHAR(32) NOT NULL,
	  `ComponentView` VARCHAR(32) NOT NULL,
	  `Enabled` BIT DEFAULT 0,
	  PRIMARY KEY (`ComponentId`)) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/* --------------------------------------------------------------
 * CREATE FOREIGN KEYS
 * ------------------------------------------------------------ */

	ALTER TABLE `house_brady_20262734_assignment3`.`stu_course`
	ADD INDEX `SpecializationId_Idx` (`SpecializationId` ASC);
	ALTER TABLE `house_brady_20262734_assignment3`.`stu_course`
	ADD CONSTRAINT `SpecializationId`
	  FOREIGN KEY (`SpecializationId`)
	  REFERENCES `house_brady_20262734_assignment3`.`stu_specialization` (`specializationId`)
	  ON DELETE NO ACTION
	  ON UPDATE NO ACTION;

	ALTER TABLE `house_brady_20262734_assignment3`.`stu_user_course_link`
	ADD INDEX `UserCourseId_Idx` (`UserId` ASC),
	ADD INDEX `CourseId_Idx` (`CourseId` ASC);
	ALTER TABLE `house_brady_20262734_assignment3`.`stu_user_course_link`
	ADD CONSTRAINT `UserCourseId`
	  FOREIGN KEY (`UserId`)
	  REFERENCES `house_brady_20262734_assignment3`.`stu_user` (`UserId`)
	  ON DELETE NO ACTION
	  ON UPDATE NO ACTION,
	ADD CONSTRAINT `CourseId`
	  FOREIGN KEY (`CourseId`)
	  REFERENCES `house_brady_20262734_assignment3`.`stu_course` (`CourseId`)
	  ON DELETE NO ACTION
	  ON UPDATE NO ACTION;

/* --------------------------------------------------------------
 * CREATE STORED PROCEDURES
 * ------------------------------------------------------------ */

	DELIMITER $$
	CREATE PROCEDURE `house_brady_20262734_assignment3`.`stu_component_enabled_s`
    	 (
        	IN  p_option                    	VARCHAR(32)   ,
        	IN  p_view		                    VARCHAR(32)
        )
	BEGIN

		DECLARE l_matches INT;

		SET l_matches = ( SELECT COUNT(*) FROM `house_brady_20262734_assignment3`.`stu_component`
		WHERE ComponentOption = TRIM(p_option) AND ComponentView = TRIM(p_view) AND Enabled = 1 );

		IF l_matches > 0 THEN
			SELECT TRUE AS 'ENABLED';
		ELSE
			SELECT FALSE AS 'ENABLED';
		END IF;

	END;
	$$
	DELIMITER ;


	DELIMITER $$
	CREATE PROCEDURE `house_brady_20262734_assignment3`.`stu_site_u`
    	 (
        	IN  p_title							VARCHAR(256)	,
        	IN  p_subTitle                      VARCHAR(1024)
    	 )
	BEGIN

    	UPDATE `house_brady_20262734_assignment3`.`stu_site`
    	SET
    		Title 		= p_title 				,
			SubTitle 	= p_subTitle
    	WHERE
    		SiteId = 6;

	END;
	$$
	DELIMITER ;


	DELIMITER $$
	CREATE PROCEDURE `house_brady_20262734_assignment3`.`stu_component_u`
    	 (
        	IN  p_componentId					INT	,
        	IN  p_enabled                       BIT
    	 )
	BEGIN

    	UPDATE `house_brady_20262734_assignment3`.`stu_component`
    	SET
    		Enabled 	= p_enabled
    	WHERE
    		ComponentId = p_componentId;

	END;
	$$
	DELIMITER ;



	DELIMITER $$
	CREATE PROCEDURE `house_brady_20262734_assignment3`.`stu_component_i`
    	 (
        	IN  p_option        		        VARCHAR(32),
        	IN  p_view			 			    VARCHAR(32),
        	IN  p_enabled						BIT
        )
	BEGIN

		INSERT INTO `house_brady_20262734_assignment3`.`stu_component`
    	    	 (
					ComponentOption,
					ComponentView,
					Enabled
		         )
    		VALUES
        	 	(
					p_option,
					p_view,
					p_enabled
				);
	END;
	$$
	DELIMITER ;


	DELIMITER $$
	CREATE PROCEDURE `house_brady_20262734_assignment3`.`stu_user_course_link_s`
    	 (
        	IN  p_login                    		VARCHAR(32)   ,
        	IN  p_courseId 						INT
        )
	BEGIN

		DECLARE l_userId INT;
		DECLARE l_matches INT;

		SET l_userId = (SELECT u.UserId FROM `house_brady_20262734_assignment3`.`stu_user` u WHERE u.Login = TRIM(p_login));

		SET l_matches = (SELECT COUNT(*) FROM `house_brady_20262734_assignment3`.`stu_user_course_link` WHERE CourseId = p_courseId AND UserId = l_userId);

		IF l_matches > 0 THEN
			SELECT TRUE AS 'ENROLLED';
		ELSE
			SELECT FALSE AS 'ENROLLED';
		END IF;

	END;
	$$
	DELIMITER ;

	DELIMITER $$
	CREATE PROCEDURE `house_brady_20262734_assignment3`.`stu_user_course_link_i`
    	 (
        	IN  p_login                    		VARCHAR(32)   ,
        	IN  p_courseId 						INT
        )
	BEGIN

		DECLARE l_userId INT;
		DECLARE l_matches INT;

		SET l_userId = (SELECT u.UserId FROM `house_brady_20262734_assignment3`.`stu_user` u WHERE u.Login = TRIM(p_login));

		SET l_matches = (SELECT COUNT(*) FROM `house_brady_20262734_assignment3`.`stu_user_course_link` WHERE CourseId = p_courseId AND UserId = l_userId);

		IF l_matches = 0 THEN

			INSERT INTO `house_brady_20262734_assignment3`.`stu_user_course_link`
    	    	 (
					CourseId		                ,
					UserId
		         )
    		VALUES
        	 	(
					p_courseId,
					l_userId
         		);

		END IF;

	END;
	$$
	DELIMITER ;


	DELIMITER $$
	CREATE PROCEDURE `house_brady_20262734_assignment3`.`stu_user_i`
    	 (
        	IN  p_name                      	VARCHAR(255)   ,
        	IN  p_login                    		VARCHAR(32)   ,
        	IN  p_password                      VARCHAR(32)   ,
        	IN  p_email                     	VARCHAR(255)  ,
        	IN  p_salt							VARCHAR(10) ,
        	IN  p_isAdmin						BIT
        )
	BEGIN

	    INSERT INTO `house_brady_20262734_assignment3`.`stu_user`
    	     (
				Name		                    ,
				Login                         	,
           		Password	 	                ,
           		Email							,
           		IsAdmin
	         )
    	VALUES
         	(
           		p_name	                  		,
           		p_login 	                    ,
           		SHA1(CONCAT(TRIM(p_password),TRIM(p_salt)))	,
           		p_email							,
           		p_isAdmin

         	);
	END;
	$$
	DELIMITER ;


	DELIMITER $$
	CREATE PROCEDURE `house_brady_20262734_assignment3`.`stu_user_auth_s`
    	 (
        	IN  p_login                    		VARCHAR(32)   ,
        	IN  p_password                      VARCHAR(32)   ,
        	IN  p_salt							VARCHAR(10)
        )
	BEGIN

		DECLARE l_matches INT;

		SET l_matches = ( SELECT COUNT(*) FROM `house_brady_20262734_assignment3`.`stu_user`
		WHERE Login=TRIM(p_login) AND Password=(SHA1(CONCAT(TRIM(p_password),TRIM(p_salt)))) );

		IF l_matches > 0 THEN
			SELECT TRUE AS 'AUTHENTICATED';
		ELSE
			SELECT FALSE AS 'AUTHENTICATED';
		END IF;

	END;
	$$
	DELIMITER ;


	DELIMITER $$
	CREATE PROCEDURE `house_brady_20262734_assignment3`.`stu_user_u`
    	 (
    	    IN  p_login                    		VARCHAR(32)   	,
        	IN  p_name                      	VARCHAR(255)   	,
        	IN  p_password                      VARCHAR(32)   	,
        	IN  p_email                     	VARCHAR(255)   	,
        	IN  p_salt							VARCHAR(10)     ,
        	IN  p_locked						BIT             ,
        	IN  p_isAdmin						BIT				   
    	 )
	BEGIN
		
    	UPDATE stu_user
    	SET
    		Name 		= p_name 				,
			Password 	= SHA1(CONCAT(TRIM(p_password),TRIM(p_salt))),
           	Email      	= p_email				,
           	Locked		= p_locked              ,
           	IsAdmin		= p_isAdmin
    	WHERE
    		Login = TRIM(p_login);

	END;
	$$
	DELIMITER ;

	DELIMITER $$
	CREATE PROCEDURE `house_brady_20262734_assignment3`.`stu_user_s`
		(
			IN p_login 		VARCHAR(32)
		)
	BEGIN

    	SELECT 	*
    	FROM	`house_brady_20262734_assignment3`.`stu_user`
    	WHERE   Login = TRIM(p_login);

	END;
	$$
	DELIMITER ;

	DELIMITER $$
 	CREATE PROCEDURE `house_brady_20262734_assignment3`.`stu_specialization_i`
    	 (
        	IN  p_title                      	VARCHAR(255)    ,
        	IN  p_description                   VARCHAR(1024)
    	 )
	BEGIN

	    INSERT INTO `house_brady_20262734_assignment3`.`stu_specialization`
    	     (
				Title		                    ,
           		Description
	         )
    	VALUES
         	(
           		p_title	                  		,
           		p_description
         	);
	END;
	$$
	DELIMITER ;

 	DELIMITER $$
	CREATE PROCEDURE `house_brady_20262734_assignment3`.`stu_site_i`
    	 (
        	IN  p_title                      	VARCHAR(256)   ,
        	IN  p_subTitle                 		VARCHAR(1024)  ,
        	IN  p_salt							VARCHAR(10)
    	 )
	BEGIN

	    INSERT INTO `house_brady_20262734_assignment3`.`stu_site`
    	     (
				Title		                    ,
				SubTitle                       	,
				Salt
	         )
    	VALUES
         	(
           		p_title	                  		,
           		p_subTitle 	                    ,
           		p_salt
         	);
	END;
	$$
	DELIMITER ;

	CREATE PROCEDURE `house_brady_20262734_assignment3`.`stu_course_i`
	(IN `p_courseNumber` VARCHAR(32), IN `p_title` VARCHAR(256), IN `p_credits` FLOAT, IN `p_cost` FLOAT, IN `p_specializationId` INT)
	COMMENT 'Course Insert Procedure.'
	NOT DETERMINISTIC NO SQL SQL SECURITY DEFINER
	INSERT INTO `house_brady_20262734_assignment3`.`stu_course`
	( CourseNumber , Title , Credits , Cost , SpecializationId )
	VALUES ( p_courseNumber , p_title , p_credits , p_cost , p_specializationId );

	CREATE PROCEDURE `house_brady_20262734_assignment3`.`stu_course_summary_s`()
	COMMENT 'Lists course numbers and descriptions.'
	NOT DETERMINISTIC NO SQL SQL SECURITY DEFINER
	SELECT CourseId, CourseNumber, Title, SpecializationId FROM stu_course ORDER BY CourseNumber ASC;

	CREATE PROCEDURE `house_brady_20262734_assignment3`.`stu_course_detail_s`(IN `p_courseId` INT)
	COMMENT 'View the details of a particular course based on it''s id.'
	NOT DETERMINISTIC NO SQL SQL SECURITY DEFINER
	SELECT DISTINCT course.CourseId, course.CourseNumber, course.Title as 'CourseDescription',
	course.Credits, course.Cost, special.SpecializationId, special.Title as 'Specialization',
	special.Description as 'SpecializationDescription'
	FROM stu_course course
	INNER JOIN stu_specialization special
	ON course.SpecializationId = special.SpecializationId
	WHERE course.CourseId = p_courseId;

	CREATE PROCEDURE `house_brady_20262734_assignment3`.`stu_site_s`()
	COMMENT 'Get the contents of the stu_site table.'
	NOT DETERMINISTIC NO SQL SQL SECURITY DEFINER
	SELECT * FROM stu_site;

	CREATE PROCEDURE `house_brady_20262734_assignment3`.`stu_user_summary_s`()
	COMMENT 'get the contents of the stu_user table.'
	NOT DETERMINISTIC NO SQL SQL SECURITY DEFINER
	SELECT * FROM stu_user;

	CREATE PROCEDURE `house_brady_20262734_assignment3`.`stu_user_detail_s`(IN `p_userId` INT)
	COMMENT 'Get all courses that the user is enrolled in.'
	NOT DETERMINISTIC NO SQL SQL SECURITY DEFINER
	SELECT DISTINCT course.CourseId, course.CourseNumber, course.Title as 'CourseDescription',
	course.Credits, course.Cost, special.SpecializationId, special.Title as 'Specialization',
	special.Description as 'SpecializationDescription'
	FROM stu_course course
	INNER JOIN stu_specialization special ON course.SpecializationId = special.SpecializationId
	INNER JOIN stu_user_course_link link ON course.CourseId = link.CourseId
	INNER JOIN stu_user u ON link.UserId = u.UserId WHERE u.UserId = p_userId;

	CREATE PROCEDURE  `house_brady_20262734_assignment3`.`stu_component_s`()
	COMMENT 'Get the contents of the stu_component table.'
	NOT DETERMINISTIC NO SQL SQL SECURITY DEFINER
	SELECT ComponentId as 'Id', ComponentOption as 'Opt', ComponentView as 'Vw', Enabled as 'State' FROM stu_component;

/* --------------------------------------------------------------
 * CREATE DATA
 * ------------------------------------------------------------ */


	CALL `house_brady_20262734_assignment3`.`stu_specialization_i`('Data Management', 'This specialization focuses on the design, development and administration of traditional and Internet-based data management');
	CALL `house_brady_20262734_assignment3`.`stu_specialization_i`('IT Entrepreneurship and Management', 'This specialization focuses on managerial and entrepreneurial skills needed to launch a new enterprise.');

	CALL `house_brady_20262734_assignment3`.`stu_course_i`('ITMD 422','Advanced Database Management', '3.0', '3,588.00', '1');
	CALL `house_brady_20262734_assignment3`.`stu_course_i`('ITMS 428','Database Security', '3.0', '3,588.00', '1');
	CALL `house_brady_20262734_assignment3`.`stu_course_i`('ITMO 444','Cloud Computing Technologies', '3.0', '3,588.00', '1');
	CALL `house_brady_20262734_assignment3`.`stu_course_i`('ITMM 470','Fundamentals of Management for Technical Professionals', '3.0', '3,588.00', '2');
	CALL `house_brady_20262734_assignment3`.`stu_course_i`('ITMM 480','IT Entrepreneurship', '3.0', '3,588.00', '2');
	
	CALL `house_brady_20262734_assignment3`.`stu_site_i`('itmStu', 'ITM 462 Assignment 3', 'word');
	CALL `house_brady_20262734_assignment3`.`stu_user_i`('brady house', 'bhouse', '33November', 'bradyhouse@gmail.com','word',1);
	CALL `house_brady_20262734_assignment3`.`stu_user_i`('bilbo baggins', 'bbaggins', '33November', 'bbaggins@gmail.com','word',0);
	CALL `house_brady_20262734_assignment3`.`stu_component_i`('admin', 'list', '1');
	CALL `house_brady_20262734_assignment3`.`stu_component_i`('admin', 'siteupdate', '1');
	CALL `house_brady_20262734_assignment3`.`stu_component_i`('setup', 'reset', '1');
	CALL `house_brady_20262734_assignment3`.`stu_component_i`('setup', 'list', '1');
	CALL `house_brady_20262734_assignment3`.`stu_component_i`('course', 'list', '1');
	CALL `house_brady_20262734_assignment3`.`stu_component_i`('user', 'list', '1');
	
