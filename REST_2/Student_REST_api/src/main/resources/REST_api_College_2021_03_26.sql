-- MySQL dump 10.13  Distrib 5.7.32, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: REST_api_College
-- ------------------------------------------------------
-- Server version	5.5.5-10.3.16-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Auth_token`
--

DROP TABLE IF EXISTS `Auth_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Auth_token` (
  `token` varchar(50) NOT NULL,
  `indexNo` int(3) NOT NULL,
  `expirytime` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Auth_token`
--

LOCK TABLES `Auth_token` WRITE;
/*!40000 ALTER TABLE `Auth_token` DISABLE KEYS */;
INSERT INTO `Auth_token` VALUES ('b5fc3bf2-29af-4ac2-9b0c-148bb7990233',200,1616592846902),('faa7b9c2-9ec1-465b-aa33-cdc3a4d47e03',100,1616727450372);
/*!40000 ALTER TABLE `Auth_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Student`
--

DROP TABLE IF EXISTS `Student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Student` (
  `indexNo` int(3) NOT NULL,
  `name` varchar(20) NOT NULL,
  `grade` varchar(3) DEFAULT NULL,
  `password` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`indexNo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Student`
--

LOCK TABLES `Student` WRITE;
/*!40000 ALTER TABLE `Student` DISABLE KEYS */;
INSERT INTO `Student` VALUES (100,'duliya','B','3052b8884611cffbc88348d803959fec18ecda0b31fda30627f5d3b94bcc2b33'),(110,'Gopal','A','e3b54375ec2cc964129746d455a16972119910399169280d06edc7ef545b0ed5'),(200,'indula','B','3052b8884611cffbc88348d803959fec18ecda0b31fda30627f5d3b94bcc2b33'),(300,'Perera','A+','3052b8884611cffbc88348d803959fec18ecda0b31fda30627f5d3b94bcc2b33'),(400,'nimantha','C+','2dd475364bbd91034468b31331eb16123347407b7931ee88a812e016aea6439f'),(500,'samantha','A ','c5422c052bfbd7bbd9764e0467688b62193fec4fa32a1b13af28d1708d5870ec'),(600,'jude','C','f066c9b74c216ddd215acefdc27957bfebfe665448b1be8fa82270b091cbf8d'),(700,'Kanal',' B','909164349b69d36e973cff75d7f84cc073774d42f8a56b17de7719f83f86bac9');
/*!40000 ALTER TABLE `Student` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-03-26 12:13:42
