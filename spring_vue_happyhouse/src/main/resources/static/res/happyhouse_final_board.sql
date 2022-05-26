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
-- Table structure for table `board`
--

DROP TABLE IF EXISTS `board`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `board` (
  `num` int NOT NULL AUTO_INCREMENT,
  `createtime` datetime NOT NULL,
  `title` varchar(40) NOT NULL,
  `content` varchar(200) NOT NULL,
  `writername` varchar(40) DEFAULT NULL,
  `hit` int DEFAULT '0',
  PRIMARY KEY (`num`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `board`
--

LOCK TABLES `board` WRITE;
/*!40000 ALTER TABLE `board` DISABLE KEYS */;
INSERT INTO `board` VALUES (1,'2022-05-27 03:17:43','[공지] 매물 커뮤니티에서 중개 활동 금지',' HAPPY HOUSE는 매물 정보 제공 플랫폼입니다. 커뮤니티 특성의 활동을 제외한 상업 활동은 금지합니다.',NULL,57),(2,'2022-05-27 03:18:12','[안내] 커뮤니티 댓글 삭제 관련','커뮤니티 댓글의 선정성, 커뮤니티 특성을 넘어서는 광고 등의 댓글은 HAPPYHOUSE 관리자에 의해 삭제될 수 있습니다.',NULL,88),(3,'2022-05-27 03:18:33','[공지] 매매 실거래가 데이터 반영','실제 거래 일자와 HAPPYHOUSE 매물 검색 반영 일자는 상이할 수 있습니다.',NULL,76),(4,'2022-05-27 03:18:50','[안내] 매물 시,도/구,군/동 지역 관련','법정동을 기준으로 매물이 반영됩니다. 정확한 주소를 확인하시고 매물 검색하시면 정확한 매물 정보를 확인하실 수 있습니다.',NULL,55),(5,'2022-05-27 03:19:10','[안내] 주변 상권 정보 관련','주변 상권 정보는 매물 약 200m 반경 내에서 제공됩니다.',NULL,63),(6,'2022-05-27 03:19:30','[공지] 개인정보처리방침 개정 안내','안녕하세요 HAPPY HOUSE입니다. 항상 HAPPYHOUSE 서비스를 이용해주시는 이용자 여러분께 진심으로 감사드리며 새로운 “개인정보처리방침” 적용에 대한 사전 안내 말씀드립니다.',NULL,123),(7,'2022-05-27 03:19:49','[안내] HAPPY HOUSE 이용방법','매물 검색에서 시,도/구,군/동으로 검색하면 매매 실거래가 정보가 제공됩니다.',NULL,211);
/*!40000 ALTER TABLE `board` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-27  3:41:06
