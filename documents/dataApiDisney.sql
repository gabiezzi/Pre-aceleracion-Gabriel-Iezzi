CREATE DATABASE  IF NOT EXISTS `disney` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `disney`;
-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: localhost    Database: disney
-- ------------------------------------------------------
-- Server version	8.0.30

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
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categories` (
  `id` bigint NOT NULL,
  `deleted` bit(1) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (10,_binary '\0','static/imagenes/Animated.jpg','Animated'),(28,_binary '\0','static/imagenes/categories/LiveAction.jpg','LiveAction'),(34,_binary '\0','static/imagenes/Action.jpg','Action'),(35,_binary '\0','static/imagenes/categories/SuperHeroes.jpg','SuperHeroes');
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `characters`
--

DROP TABLE IF EXISTS `characters`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `characters` (
  `id` bigint NOT NULL,
  `age` int DEFAULT NULL,
  `deleted` bit(1) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `story` varchar(255) DEFAULT NULL,
  `weight` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `characters`
--

LOCK TABLES `characters` WRITE;
/*!40000 ALTER TABLE `characters` DISABLE KEYS */;
INSERT INTO `characters` VALUES (3,12,_binary '\0','imagenes/personajes/pinocho.jpg','pinocho','Niño de madera',40),(4,45,_binary '','imagenes/personajes/buzzL.jpg','buzz lightyear','astronauta',0.6),(5,12,_binary '','imagenes/personajes/woody.jpg','woody','vaquero',0.4),(6,1900,_binary '\0','imagenes/personajes/dino.jpg','dino','trex',0.5),(12,30,_binary '\0','imagenes/personajes/heidi.jpg','heidi','campesina',0.5),(25,20,_binary '\0','imagenes/personajes/Stinky.jpg','Stinky','perritoSalchicha',0.5),(38,20,_binary '\0','imagenes/personajes/hercules.jpg','hercules','Heroe mitologico',85),(39,20,_binary '\0','imagenes/personajes/jazmin.jpg','jazmin','Princesa',60),(40,3,_binary '\0','imagenes/personajes/abu.jpg','Mono abu','mono ladron',10),(41,34,_binary '\0','imagenes/personajes/BuzzL.jpg','Buzz Lightyear','Astronauta de juguete',0.5),(44,14,_binary '\0','imagenes/personajes/mowly.jpg','Mowly','niño de la selva',34),(45,22,_binary '\0','imagenes/personajes/aladin.jpg','aladin','ladron',69);
/*!40000 ALTER TABLE `characters` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (47);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movies`
--

DROP TABLE IF EXISTS `movies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movies` (
  `id` bigint NOT NULL,
  `category_id` bigint NOT NULL,
  `creation_date` date DEFAULT NULL,
  `deleted` bit(1) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `rating` double DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqm9g8lj38gwq0rdoflb6jrvbd` (`category_id`),
  CONSTRAINT `FKqm9g8lj38gwq0rdoflb6jrvbd` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movies`
--

LOCK TABLES `movies` WRITE;
/*!40000 ALTER TABLE `movies` DISABLE KEYS */;
INSERT INTO `movies` VALUES (2,10,'1990-06-10',_binary '\0','static/images/movies/Mulan.jpg',5,'Mulan'),(13,10,'2010-06-10',_binary '\0','static/images/Pinocho.jpg',3,'Pinocho'),(16,10,'2010-06-11',_binary '\0','static/images/ReyLeon.jpg',4,'ReyLeon'),(26,10,'1990-06-10',_binary '\0','static/images/Tarzan.jpg',5,'Tarzan'),(27,10,'1990-06-10',_binary '\0','static/images/movies/Up.jpg',5,'Up'),(29,10,'1990-06-10',_binary '\0','static/images/movies/Aladdin.jpg',5,'Aladdin'),(30,10,'1990-06-10',_binary '\0','static/images/movies/ToyStory.jpg',5,'Toy Story'),(31,10,'1990-06-10',_binary '\0','static/images/movies/Aladdin2.jpg',2,'Aladdin y los 40 ladrones'),(32,10,'2015-06-10',_binary '','static/images/movies/Moana.jpg',3,'Moana'),(33,10,'1992-06-10',_binary '\0','static/images/movies/librodelaselva.jpg',3,'Libro de la selva'),(37,10,'1992-06-10',_binary '\0','static/images/movies/Hercules.jpg',3,'Hercules'),(42,35,'1992-06-10',_binary '\0','static/images/movies/Spiderman.jpg',3,'Spiderman'),(43,28,'1992-06-10',_binary '\0','static/images/movies/AladinoLA.jpg',3,'Aladino Live Action');
/*!40000 ALTER TABLE `movies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movies_characters`
--

DROP TABLE IF EXISTS `movies_characters`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movies_characters` (
  `movie_id` bigint NOT NULL,
  `character_id` bigint NOT NULL,
  KEY `FK3twajyof8hfffuul5iqq79kgs` (`character_id`),
  KEY `FKbqopm07wsu7f6vmlnfd789wnq` (`movie_id`),
  CONSTRAINT `FK3twajyof8hfffuul5iqq79kgs` FOREIGN KEY (`character_id`) REFERENCES `characters` (`id`),
  CONSTRAINT `FKbqopm07wsu7f6vmlnfd789wnq` FOREIGN KEY (`movie_id`) REFERENCES `movies` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movies_characters`
--

LOCK TABLES `movies_characters` WRITE;
/*!40000 ALTER TABLE `movies_characters` DISABLE KEYS */;
INSERT INTO `movies_characters` VALUES (30,41),(30,25),(30,12),(30,6),(33,44),(13,3),(37,38),(43,39),(43,40),(43,45),(31,45),(31,39),(31,40),(29,45),(29,40),(29,39);
/*!40000 ALTER TABLE `movies_characters` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_entity`
--

DROP TABLE IF EXISTS `user_entity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_entity` (
  `id` bigint NOT NULL,
  `account_non_expired` bit(1) NOT NULL,
  `account_non_locked` bit(1) NOT NULL,
  `credentials_non_expired` bit(1) NOT NULL,
  `enabled` bit(1) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_entity`
--

LOCK TABLES `user_entity` WRITE;
/*!40000 ALTER TABLE `user_entity` DISABLE KEYS */;
INSERT INTO `user_entity` VALUES (1,_binary '',_binary '',_binary '',_binary '','password1','email@gmail.com');
/*!40000 ALTER TABLE `user_entity` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-08-21  0:40:14
