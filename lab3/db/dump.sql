-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: mycoolstore
-- ------------------------------------------------------
-- Server version	8.0.34

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
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart` (
  `crt_user` int unsigned NOT NULL,
  `crt_film` int unsigned NOT NULL,
  PRIMARY KEY (`crt_user`,`crt_film`),
  KEY `fk_cart_film1_idx` (`crt_film`),
  CONSTRAINT `fk_cart_film1` FOREIGN KEY (`crt_film`) REFERENCES `film` (`flm_id`),
  CONSTRAINT `fk_cart_user` FOREIGN KEY (`crt_user`) REFERENCES `user` (`usr_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
INSERT INTO `cart` VALUES (2,5);
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `cat_id` int unsigned NOT NULL AUTO_INCREMENT,
  `cat_name` varchar(45) NOT NULL,
  PRIMARY KEY (`cat_id`),
  UNIQUE KEY `cat_name_UNIQUE` (`cat_name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (3,'драма'),(2,'комедия'),(4,'ужасы'),(1,'экшн');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feedback`
--

DROP TABLE IF EXISTS `feedback`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `feedback` (
  `fbk_id` int unsigned NOT NULL AUTO_INCREMENT,
  `fbk_author` int unsigned NOT NULL,
  `fbk_film` int unsigned NOT NULL,
  `fbk_text` text NOT NULL,
  `fbk_rating` tinyint unsigned NOT NULL,
  PRIMARY KEY (`fbk_id`),
  KEY `fk_feedback_user1_idx` (`fbk_author`),
  KEY `fk_feedback_film1_idx` (`fbk_film`),
  CONSTRAINT `fk_feedback_film1` FOREIGN KEY (`fbk_film`) REFERENCES `film` (`flm_id`),
  CONSTRAINT `fk_feedback_user1` FOREIGN KEY (`fbk_author`) REFERENCES `user` (`usr_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feedback`
--

LOCK TABLES `feedback` WRITE;
/*!40000 ALTER TABLE `feedback` DISABLE KEYS */;
INSERT INTO `feedback` VALUES (5,2,4,'блинклас',10);
/*!40000 ALTER TABLE `feedback` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `film`
--

DROP TABLE IF EXISTS `film`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `film` (
  `flm_id` int unsigned NOT NULL AUTO_INCREMENT,
  `flm_description` text NOT NULL,
  `flm_price` decimal(5,2) unsigned NOT NULL,
  `flm_discount` tinyint unsigned DEFAULT NULL,
  `flm_author` varchar(45) NOT NULL,
  `flm_age` enum('0+','3+','6+','12+','18+','21+') NOT NULL,
  `flm_name` varchar(80) NOT NULL,
  PRIMARY KEY (`flm_id`),
  UNIQUE KEY `flm_name_UNIQUE` (`flm_name`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `film`
--

LOCK TABLES `film` WRITE;
/*!40000 ALTER TABLE `film` DISABLE KEYS */;
INSERT INTO `film` VALUES (4,'Официальная адаптация известной игры',11.99,50,'Никита Ордынский','18+','Документы, пожалуйста'),(5,'Фильм пила',10.00,10,'ван','18+','пила');
/*!40000 ALTER TABLE `film` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `film_media`
--

DROP TABLE IF EXISTS `film_media`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `film_media` (
  `fm_id` int unsigned NOT NULL,
  `fm_film_path` varchar(255) NOT NULL,
  `fm_trailer_path` varchar(255) NOT NULL,
  PRIMARY KEY (`fm_id`),
  CONSTRAINT `fk_film_media_film1` FOREIGN KEY (`fm_id`) REFERENCES `film` (`flm_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `film_media`
--

LOCK TABLES `film_media` WRITE;
/*!40000 ALTER TABLE `film_media` DISABLE KEYS */;
INSERT INTO `film_media` VALUES (4,'1700103066464_PAPERS, PLEASE - The Short Film (2018) 4K SUBS.mp4','1700103066460_Ваши документы — Трейлер (2017).mp4'),(5,'1701768824383_ПИЛА (короткометражка Джеймса Вана).mp4','1701768824376_Пила (2004) - Дублированный Трейлер HD.mp4');
/*!40000 ALTER TABLE `film_media` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `m2m_film_category`
--

DROP TABLE IF EXISTS `m2m_film_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `m2m_film_category` (
  `fc_film` int unsigned NOT NULL,
  `fc_category` int unsigned NOT NULL,
  PRIMARY KEY (`fc_film`,`fc_category`),
  KEY `fk_film_has_category_category1_idx` (`fc_category`),
  KEY `fk_film_has_category_film1_idx` (`fc_film`),
  CONSTRAINT `fk_film_has_category_category1` FOREIGN KEY (`fc_category`) REFERENCES `category` (`cat_id`),
  CONSTRAINT `fk_film_has_category_film1` FOREIGN KEY (`fc_film`) REFERENCES `film` (`flm_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `m2m_film_category`
--

LOCK TABLES `m2m_film_category` WRITE;
/*!40000 ALTER TABLE `m2m_film_category` DISABLE KEYS */;
INSERT INTO `m2m_film_category` VALUES (4,1),(4,2),(4,3),(4,4),(5,4);
/*!40000 ALTER TABLE `m2m_film_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `usr_id` int unsigned NOT NULL AUTO_INCREMENT,
  `usr_email` varchar(254) NOT NULL,
  `usr_password` varchar(256) NOT NULL,
  `usr_role` enum('admin','customer') NOT NULL,
  `usr_banned_by` int unsigned DEFAULT NULL,
  PRIMARY KEY (`usr_id`),
  UNIQUE KEY `usr_email_UNIQUE` (`usr_email`),
  KEY `fk_user_user1_idx` (`usr_banned_by`),
  CONSTRAINT `fk_user_user1` FOREIGN KEY (`usr_banned_by`) REFERENCES `user` (`usr_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin@root','413020b1cb310f592011cff4c722c55261f09bcd135911c2d739cd75ba742e8aa0c96efcb75095df4e9f284323b20f1929fbdf5e5c7e44f27a085e22e5bed617','admin',NULL),(2,'conn@test','eb7bcf894dfea3bff2b984e761b4e2fa11ac3eb2f95b51b73bec293efcfb92a0d91d6f8778d32ba29a1b4cbe89678379acd8ce57ac3bbfde09ac7356e6038bbd','customer',NULL),(3,'newmail@gmail.com','b109f3bbbc244eb82441917ed06d618b9008dd09b3befd1b5e07394c706a8bb980b1d7785e5976ec049b46df5f1326af5a2ea6d103fd07c95385ffab0cacbc86','customer',NULL),(4,'newuser@mail.ru','b42fa1ce2b65fd6f4a8501b64deef38fa3e87059c6f015eb5848c50aaa538dc812c8d36dba61f4c505d54516707a3997d555ff08dd447617d9cd3180df95204c','customer',NULL),(14,'testedusasdr@mail','atested','customer',NULL),(16,'please@begging','4f9a167ceff5068ceae10cd70d945cd83c42b6a568dee82ebd925e0aef9388c60ad2059656241e33a9078d2927f15cf00962af4e3480dc029e3acf52753995e3','customer',NULL),(17,'WORKS?@ABOBA','8610ff091761e395bc3682f421c165bfc9b1872d1ffba043f2ad4f8adcdd990a0a3c0ae8ef9171f3e7c1c14100269f81269f4d2b1627849e1302772476df23af','customer',NULL),(18,'aboba@test','97bd306639a3b4ec2c499b7de4cec2b5cae52dbd7b416ee48b247fa5c0e0854139357b84b6a42de5e4ff2ee0ed314d39dfa7095a1232318ab3027ea350e35b41','customer',NULL),(19,'a@b','2d408a0717ec188158278a796c689044361dc6fdde28d6f04973b80896e1823975cdbf12eb63f9e0591328ee235d80e9b5bf1aa6a44f4617ff3caf6400eb172d','customer',NULL),(20,'model@test','88bf81075ee52e6aac94ee8311c98817fe544ff11c6874cae976dbc89bea1e63f29e2f2006075e084362174c4a9fe2ce8ab3fb2b1f98794fa9f6ee6e3629a9aa','customer',NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_film`
--

DROP TABLE IF EXISTS `user_film`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_film` (
  `uf_user` int unsigned NOT NULL,
  `uf_film` int unsigned NOT NULL,
  PRIMARY KEY (`uf_user`,`uf_film`),
  KEY `fk_user_film_film1_idx` (`uf_film`),
  CONSTRAINT `fk_user_film_film1` FOREIGN KEY (`uf_film`) REFERENCES `film` (`flm_id`),
  CONSTRAINT `fk_user_film_user1` FOREIGN KEY (`uf_user`) REFERENCES `user` (`usr_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_film`
--

LOCK TABLES `user_film` WRITE;
/*!40000 ALTER TABLE `user_film` DISABLE KEYS */;
INSERT INTO `user_film` VALUES (2,4),(4,4),(18,4),(18,5);
/*!40000 ALTER TABLE `user_film` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-12-13 16:02:14
