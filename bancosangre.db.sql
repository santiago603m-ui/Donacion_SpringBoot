CREATE DATABASE  IF NOT EXISTS `bancosangre_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `bancosangre_db`;
-- MySQL dump 10.13  Distrib 8.0.44, for Win64 (x86_64)
--
-- Host: localhost    Database: bancosangre_db
-- ------------------------------------------------------
-- Server version	8.0.43

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
-- Table structure for table `consentimiento`
--

DROP TABLE IF EXISTS `consentimiento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `consentimiento` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `fecha_firma` date NOT NULL,
  `firma_base64` longtext NOT NULL,
  `donante_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK8ggqxl2epw12ugy2sg7k25t5h` (`donante_id`),
  CONSTRAINT `FK9un10uv4twr3d33thswc5bsn6` FOREIGN KEY (`donante_id`) REFERENCES `donantes` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `consentimiento`
--

LOCK TABLES `consentimiento` WRITE;
/*!40000 ALTER TABLE `consentimiento` DISABLE KEYS */;
INSERT INTO `consentimiento` VALUES (1,'2026-05-26','Aprobado',3);
/*!40000 ALTER TABLE `consentimiento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `donaciones`
--

DROP TABLE IF EXISTS `donaciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `donaciones` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cantidad_ml` double NOT NULL,
  `codigo_unico` varchar(50) NOT NULL,
  `fecha_donacion` date NOT NULL,
  `observaciones` varchar(500) DEFAULT NULL,
  `donante_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKpponmkgi18yw99uhuf66jhjft` (`codigo_unico`),
  KEY `FKdd80vpm1ync2b315dav5jfbbo` (`donante_id`),
  CONSTRAINT `FKdd80vpm1ync2b315dav5jfbbo` FOREIGN KEY (`donante_id`) REFERENCES `donantes` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `donaciones`
--

LOCK TABLES `donaciones` WRITE;
/*!40000 ALTER TABLE `donaciones` DISABLE KEYS */;
INSERT INTO `donaciones` VALUES (1,100,'BA176853','2026-05-26','Ninguna',3),(2,100,'43A03BEE','2026-05-26','Ninguna',4);
/*!40000 ALTER TABLE `donaciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `donantes`
--

DROP TABLE IF EXISTS `donantes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `donantes` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `acepta_consentimiento` bit(1) NOT NULL,
  `apellidos` varchar(255) NOT NULL,
  `correo` varchar(255) DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `documento` varchar(255) NOT NULL,
  `fecha_nacimiento` date NOT NULL,
  `fecha_ultima_donacion` date DEFAULT NULL,
  `firma_consentimiento` text,
  `nombres` varchar(255) NOT NULL,
  `peso` double NOT NULL,
  `telefono` varchar(255) DEFAULT NULL,
  `tipo_sangre` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK98ko7f9ibl9qg1i5pc5xp32u3` (`documento`),
  UNIQUE KEY `UKiehyfsnn8qnxw7tg59x3qki9g` (`correo`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `donantes`
--

LOCK TABLES `donantes` WRITE;
/*!40000 ALTER TABLE `donantes` DISABLE KEYS */;
INSERT INTO `donantes` VALUES (3,_binary '','Gomez','laura.gomez@email.com','Carrera 45 # 12-34','1098765432','1995-08-14','2026-05-26','REGISTRADO_EN_TABLA_CONSENTIMIENTO','Laura',65,'3001234567','O-'),(4,_binary '','Rodriguez','carlos.rod@email.com','Avenida 7 # 89-10','1045678912','1988-11-02','2026-05-26','Carlos Rodriguez','Carlos',80,'3159876543','B+');
/*!40000 ALTER TABLE `donantes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inventario`
--

DROP TABLE IF EXISTS `inventario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inventario` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cantidad_disponible_ml` double NOT NULL,
  `tipo_sangre` varchar(3) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKeeuwrq8k6nq00ybbeiamsggo` (`tipo_sangre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inventario`
--

LOCK TABLES `inventario` WRITE;
/*!40000 ALTER TABLE `inventario` DISABLE KEYS */;
/*!40000 ALTER TABLE `inventario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-05-26 10:47:26
