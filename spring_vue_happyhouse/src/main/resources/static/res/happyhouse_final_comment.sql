-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: happyhouse_final
-- ------------------------------------------------------
-- Server version	8.0.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `username` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `comment` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `createtime` datetime DEFAULT NULL,
  `board_num` int NOT NULL DEFAULT '0',
  `commentcol` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`comment`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (NULL,'asdfsdafasd','2022-05-19 11:01:51',7,NULL),(NULL,'ddd','2022-05-18 23:52:34',7,NULL),(NULL,'ddddd','2022-05-19 11:01:46',7,NULL),(NULL,'dfdfd','2022-05-18 23:46:25',7,NULL),(NULL,'dfdsafdasfdas','2022-05-18 23:45:52',7,NULL),(NULL,'erereree','2022-05-19 11:01:40',7,NULL),(NULL,'fdd','2022-05-18 23:45:43',7,NULL),(NULL,'fdsfdsafads','2022-05-18 23:53:05',7,NULL),(NULL,'fffff','2022-05-18 23:52:38',7,NULL),(NULL,'zzzz','2022-05-18 23:28:11',6,NULL),(NULL,'zzzzdddddd','2022-05-18 23:44:41',7,NULL),(NULL,'zzzzddddddddfff','2022-05-18 23:44:48',7,NULL),(NULL,'zzzzzz','2022-05-18 23:40:54',6,NULL),(NULL,'zzzzzzzzzzzzzzzzzzzz','2022-05-18 23:42:41',7,NULL),(NULL,'ㄴㅇㄹㄴㅇㄴ','2022-05-23 14:48:33',0,NULL),(NULL,'ㄹㅇㄹㅇㄹㅇ','2022-05-18 23:34:34',7,NULL),(NULL,'ㅇㄹㅇ','2022-05-18 21:07:26',0,NULL),(NULL,'ㅇㄹㅇㄹ','2022-05-23 15:09:14',0,NULL),(NULL,'ㅇㅇㅇㅇㅇ','2022-05-18 20:45:45',0,NULL),(NULL,'ㅗ롤','2022-05-18 21:03:21',0,NULL),('hi','안녕','2022-05-18 11:23:48',0,NULL),(NULL,'안녕하세요~','2022-05-18 20:40:44',0,NULL);
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-27  3:41:05
