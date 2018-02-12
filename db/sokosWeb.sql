-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.2.12-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for sokosweb
CREATE DATABASE IF NOT EXISTS `sokosweb` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `sokosweb`;

-- Dumping structure for table sokosweb.departments
CREATE TABLE IF NOT EXISTS `departments` (
  `departmentID` int(11) NOT NULL AUTO_INCREMENT,
  `departmentName` varchar(50) NOT NULL,
  PRIMARY KEY (`departmentID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

-- Dumping data for table sokosweb.departments: ~0 rows (approximately)
/*!40000 ALTER TABLE `departments` DISABLE KEYS */;
INSERT INTO `departments` (`departmentID`, `departmentName`) VALUES
	(1, 'Management'),
	(2, 'Front Office'),
	(3, 'Housekeeping'),
	(4, 'Guest Service'),
	(5, 'Food and Beverage'),
	(6, 'Engineering and Maintenance'),
	(7, 'Other');
/*!40000 ALTER TABLE `departments` ENABLE KEYS */;

-- Dumping structure for table sokosweb.place
CREATE TABLE IF NOT EXISTS `place` (
  `placeID` int(11) NOT NULL AUTO_INCREMENT,
  `placeName` varchar(50) NOT NULL,
  PRIMARY KEY (`placeID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table sokosweb.place: ~0 rows (approximately)
/*!40000 ALTER TABLE `place` DISABLE KEYS */;
/*!40000 ALTER TABLE `place` ENABLE KEYS */;

-- Dumping structure for table sokosweb.rights
CREATE TABLE IF NOT EXISTS `rights` (
  `rightsID` int(11) NOT NULL AUTO_INCREMENT,
  `rightsDetails` varchar(50) NOT NULL,
  PRIMARY KEY (`rightsID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table sokosweb.rights: ~0 rows (approximately)
/*!40000 ALTER TABLE `rights` DISABLE KEYS */;
/*!40000 ALTER TABLE `rights` ENABLE KEYS */;

-- Dumping structure for table sokosweb.taskhistory
CREATE TABLE IF NOT EXISTS `taskhistory` (
  `historyID` int(11) NOT NULL AUTO_INCREMENT,
  `taskNumber` int(11) NOT NULL,
  `author` int(11) NOT NULL,
  `changes` int(11) NOT NULL,
  `time` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`historyID`),
  KEY `FK__tasks` (`taskNumber`),
  KEY `FK__users` (`author`),
  KEY `FK__taskstatus1` (`changes`),
  CONSTRAINT `FK__tasks` FOREIGN KEY (`taskNumber`) REFERENCES `tasks` (`taskID`),
  CONSTRAINT `FK__taskstatus1` FOREIGN KEY (`changes`) REFERENCES `taskstatus` (`taskStatusID`),
  CONSTRAINT `FK__users` FOREIGN KEY (`author`) REFERENCES `users` (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table sokosweb.taskhistory: ~0 rows (approximately)
/*!40000 ALTER TABLE `taskhistory` DISABLE KEYS */;
/*!40000 ALTER TABLE `taskhistory` ENABLE KEYS */;

-- Dumping structure for table sokosweb.tasks
CREATE TABLE IF NOT EXISTS `tasks` (
  `taskID` int(11) NOT NULL AUTO_INCREMENT,
  `taskStatus` int(11) NOT NULL,
  `dueDate` date NOT NULL,
  `dueTime` time NOT NULL,
  `department` int(11) NOT NULL,
  `title` varchar(50) NOT NULL,
  `place` int(11) NOT NULL,
  `detils` varchar(50) DEFAULT '0',
  `attachment` varchar(50) DEFAULT '0',
  PRIMARY KEY (`taskID`),
  KEY `FK__taskstatus` (`taskStatus`),
  KEY `FK__department` (`department`),
  KEY `FK__place` (`place`),
  CONSTRAINT `FK__department` FOREIGN KEY (`department`) REFERENCES `departments` (`departmentID`),
  CONSTRAINT `FK__place` FOREIGN KEY (`place`) REFERENCES `place` (`placeID`),
  CONSTRAINT `FK__taskstatus` FOREIGN KEY (`taskStatus`) REFERENCES `taskstatus` (`taskStatusID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table sokosweb.tasks: ~0 rows (approximately)
/*!40000 ALTER TABLE `tasks` DISABLE KEYS */;
/*!40000 ALTER TABLE `tasks` ENABLE KEYS */;

-- Dumping structure for table sokosweb.taskstatus
CREATE TABLE IF NOT EXISTS `taskstatus` (
  `taskStatusID` int(11) NOT NULL AUTO_INCREMENT,
  `statusName` varchar(50) NOT NULL,
  PRIMARY KEY (`taskStatusID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- Dumping data for table sokosweb.taskstatus: ~0 rows (approximately)
/*!40000 ALTER TABLE `taskstatus` DISABLE KEYS */;
INSERT INTO `taskstatus` (`taskStatusID`, `statusName`) VALUES
	(1, 'New'),
	(2, 'In process'),
	(3, 'Done'),
	(4, 'Cancelled');
/*!40000 ALTER TABLE `taskstatus` ENABLE KEYS */;

-- Dumping structure for table sokosweb.users
CREATE TABLE IF NOT EXISTS `users` (
  `userID` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `firstname` varchar(50) NOT NULL,
  `surname` varchar(50) NOT NULL,
  `department` int(11) NOT NULL,
  `rights` int(11) NOT NULL,
  PRIMARY KEY (`userID`),
  KEY `department` (`department`),
  KEY `FK__rights` (`rights`),
  CONSTRAINT `FK__departments` FOREIGN KEY (`department`) REFERENCES `departments` (`departmentID`),
  CONSTRAINT `FK__rights` FOREIGN KEY (`rights`) REFERENCES `rights` (`rightsID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table sokosweb.users: ~0 rows (approximately)
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
/*!40000 ALTER TABLE `users` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
