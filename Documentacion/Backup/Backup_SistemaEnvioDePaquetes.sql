-- MySQL dump 10.13  Distrib 5.7.27, for Linux (x86_64)
--
-- Host: localhost    Database: SistemaEnvioDePaquetes
-- ------------------------------------------------------
-- Server version	5.7.27-0ubuntu0.18.04.1

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
-- Table structure for table `Administrador`
--

DROP TABLE IF EXISTS `Administrador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Administrador` (
  `Nombre` varchar(30) NOT NULL,
  `Apellido` varchar(30) NOT NULL,
  `NombreUsuario` varchar(25) NOT NULL,
  PRIMARY KEY (`NombreUsuario`),
  CONSTRAINT `FK_USUARIO_ADMINISTRADOR_NOMBRE_USUARIO` FOREIGN KEY (`NombreUsuario`) REFERENCES `Usuario` (`NombreUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Administrador`
--

LOCK TABLES `Administrador` WRITE;
/*!40000 ALTER TABLE `Administrador` DISABLE KEYS */;
INSERT INTO `Administrador` VALUES ('Administrador1','Usuario1','administrador1'),('Administrador2','Usuario2','administrador2'),('Administrador3','Usuario3','administrador3'),('Helmuth Alexander','Luther Montejo','AlexanderLuther');
/*!40000 ALTER TABLE `Administrador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Bodega`
--

DROP TABLE IF EXISTS `Bodega`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Bodega` (
  `CodigoPaquete` char(7) NOT NULL,
  `Prioridad` tinyint(1) NOT NULL,
  `Destino` varchar(25) NOT NULL,
  `FechaIngreso` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`CodigoPaquete`),
  CONSTRAINT `FK_BODEGA_CODIGO_PAQUETE` FOREIGN KEY (`CodigoPaquete`) REFERENCES `Paquete` (`Codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Bodega`
--

LOCK TABLES `Bodega` WRITE;
/*!40000 ALTER TABLE `Bodega` DISABLE KEYS */;
/*!40000 ALTER TABLE `Bodega` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Cliente`
--

DROP TABLE IF EXISTS `Cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Cliente` (
  `NIT` varchar(9) NOT NULL,
  `DPI` char(15) NOT NULL,
  `Apellido` varchar(30) NOT NULL,
  `Nombre` varchar(30) NOT NULL,
  `Direccion` varchar(30) NOT NULL,
  PRIMARY KEY (`DPI`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Cliente`
--

LOCK TABLES `Cliente` WRITE;
/*!40000 ALTER TABLE `Cliente` DISABLE KEYS */;
INSERT INTO `Cliente` VALUES ('444444444','1231-32456-4564','Cliente 4','Cliente 4','San Marcos'),('222222222','2123-13212-3123','Cliente 2','Cliente 2','Ciudad'),('555555555','4444-55566-6699','Cliente 5','Cliente 5','Ciudad'),('111111111','4879-79879-8789','Cliente1','Cliente1','Ciudad'),('333333333','8798-54512-3132','Cliente 3','Ciente 3','Quetzaltenango');
/*!40000 ALTER TABLE `Cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Codigo`
--

DROP TABLE IF EXISTS `Codigo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Codigo` (
  `CodigoPaquete` char(7) NOT NULL,
  `CodigoRuta` char(7) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Codigo`
--

LOCK TABLES `Codigo` WRITE;
/*!40000 ALTER TABLE `Codigo` DISABLE KEYS */;
INSERT INTO `Codigo` VALUES ('AAA-041','AAA-005');
/*!40000 ALTER TABLE `Codigo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Operador`
--

DROP TABLE IF EXISTS `Operador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Operador` (
  `Nombre` varchar(30) NOT NULL,
  `Apellido` varchar(30) NOT NULL,
  `NombreUsuario` varchar(25) NOT NULL,
  PRIMARY KEY (`NombreUsuario`),
  CONSTRAINT `FK_USUARIO_OPERADOR_NOMBRE_USUARIO` FOREIGN KEY (`NombreUsuario`) REFERENCES `Usuario` (`NombreUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Operador`
--

LOCK TABLES `Operador` WRITE;
/*!40000 ALTER TABLE `Operador` DISABLE KEYS */;
INSERT INTO `Operador` VALUES ('Operador1','Usuario4','operador1'),('Operador2','Usuario5','operador2'),('Operador3','Usuario6','operador3');
/*!40000 ALTER TABLE `Operador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Paquete`
--

DROP TABLE IF EXISTS `Paquete`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Paquete` (
  `Codigo` char(7) NOT NULL,
  `Peso` double NOT NULL,
  `Prioridad` tinyint(1) NOT NULL,
  `Destino` varchar(25) NOT NULL,
  `PrecioDestino` double NOT NULL,
  `PrecioPriorizacion` double NOT NULL,
  `PrecioLibra` double NOT NULL,
  `PrecioTotal` double NOT NULL,
  `DPICliente` varchar(15) DEFAULT NULL,
  `FechaIngreso` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`Codigo`),
  KEY `FK_CLIENTE_DPI` (`DPICliente`),
  CONSTRAINT `FK_CLIENTE_DPI` FOREIGN KEY (`DPICliente`) REFERENCES `Cliente` (`DPI`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Paquete`
--

LOCK TABLES `Paquete` WRITE;
/*!40000 ALTER TABLE `Paquete` DISABLE KEYS */;
INSERT INTO `Paquete` VALUES ('AAA-001',5,0,'Guatemala',50,35,10,100,'4879-79879-8789','2019-08-26 19:18:42'),('AAA-002',2,1,'Totonicapan',50,35,10,105,'4879-79879-8789','2019-08-26 19:19:37'),('AAA-003',4,1,'San Marcos',50,35,10,125,'4879-79879-8789','2019-08-26 19:19:44'),('AAA-004',8,0,'Retalhuleu',50,35,10,130,'4879-79879-8789','2019-08-26 19:19:51'),('AAA-005',15,0,'Huehuetenango',50,35,10,200,'4879-79879-8789','2019-08-26 19:20:00'),('AAA-006',8,0,'Guatemala',50,35,10,130,'4879-79879-8789','2019-08-26 19:20:08'),('AAA-007',6,1,'Totonicapan',50,35,10,145,'4879-79879-8789','2019-08-26 19:20:44'),('AAA-008',8,0,'San Marcos',50,35,10,130,'4879-79879-8789','2019-08-26 19:20:51'),('AAA-009',1,1,'Guatemala',50,35,10,95,'2123-13212-3123','2019-08-26 19:21:35'),('AAA-010',2,0,'Totonicapan',50,35,10,70,'2123-13212-3123','2019-08-26 19:21:42'),('AAA-011',3,1,'San Marcos',50,35,10,115,'2123-13212-3123','2019-08-26 19:21:52'),('AAA-012',3,1,'Retalhuleu',50,35,10,115,'2123-13212-3123','2019-08-26 19:22:01'),('AAA-013',5,0,'Retalhuleu',50,35,10,100,'2123-13212-3123','2019-08-26 19:22:11'),('AAA-014',10,1,'Huehuetenango',50,35,10,185,'2123-13212-3123','2019-08-26 19:22:19'),('AAA-015',12,1,'Guatemala',50,35,10,205,'2123-13212-3123','2019-08-26 19:22:28'),('AAA-016',22,1,'Retalhuleu',50,35,10,305,'2123-13212-3123','2019-08-26 19:22:40'),('AAA-017',8,0,'Guatemala',50,35,10,130,'8798-54512-3132','2019-08-26 19:24:06'),('AAA-018',9,0,'Totonicapan',50,35,10,140,'8798-54512-3132','2019-08-26 19:24:12'),('AAA-019',15,0,'San Marcos',50,35,10,200,'8798-54512-3132','2019-08-26 19:24:20'),('AAA-020',23,0,'Retalhuleu',50,35,10,280,'8798-54512-3132','2019-08-26 19:24:28'),('AAA-021',12,1,'Huehuetenango',50,35,10,205,'8798-54512-3132','2019-08-26 19:24:38'),('AAA-022',2,1,'Guatemala',50,35,10,105,'8798-54512-3132','2019-08-26 19:24:47'),('AAA-023',10,1,'Totonicapan',50,35,10,185,'8798-54512-3132','2019-08-26 19:24:56'),('AAA-024',14,0,'San Marcos',50,35,10,190,'8798-54512-3132','2019-08-26 19:25:04'),('AAA-025',23,0,'Guatemala',50,35,10,280,'1231-32456-4564','2019-08-26 19:26:38'),('AAA-026',2,1,'Totonicapan',50,35,10,105,'1231-32456-4564','2019-08-26 19:26:47'),('AAA-027',3,1,'San Marcos',50,35,10,115,'1231-32456-4564','2019-08-26 19:26:54'),('AAA-028',6,0,'Retalhuleu',50,35,10,110,'1231-32456-4564','2019-08-26 19:27:03'),('AAA-029',10,1,'Huehuetenango',50,35,10,185,'1231-32456-4564','2019-08-26 19:27:11'),('AAA-030',20,1,'Huehuetenango',50,35,10,285,'1231-32456-4564','2019-08-26 19:27:21'),('AAA-031',12,1,'Retalhuleu',50,35,10,205,'1231-32456-4564','2019-08-26 19:27:30'),('AAA-032',10,0,'San Marcos',50,35,10,150,'1231-32456-4564','2019-08-26 19:27:38'),('AAA-033',5,1,'Guatemala',50,35,10,135,'4444-55566-6699','2019-08-26 19:32:14'),('AAA-034',10,1,'Totonicapan',50,35,10,185,'4444-55566-6699','2019-08-26 19:32:23'),('AAA-035',21,1,'San Marcos',50,35,10,295,'4444-55566-6699','2019-08-26 19:32:45'),('AAA-036',12,1,'Retalhuleu',50,35,10,205,'4444-55566-6699','2019-08-26 19:33:05'),('AAA-037',20,0,'Huehuetenango',50,35,10,250,'4444-55566-6699','2019-08-26 19:33:15'),('AAA-038',22,1,'Guatemala',50,35,10,305,'4444-55566-6699','2019-08-26 19:33:25'),('AAA-039',10,1,'Totonicapan',50,35,10,185,'4444-55566-6699','2019-08-26 19:33:37'),('AAA-040',20,0,'San Marcos',50,35,10,250,'4444-55566-6699','2019-08-26 19:33:48'),('AAA-041',2,0,'Huehuetenango',50,35,10,70,'4444-55566-6699','2019-08-26 19:34:00');
/*!40000 ALTER TABLE `Paquete` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PaqueteAsignadoRuta`
--

DROP TABLE IF EXISTS `PaqueteAsignadoRuta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PaqueteAsignadoRuta` (
  `CodigoPaquete` char(7) NOT NULL,
  `CodigoRuta` char(7) NOT NULL,
  `PuntoDeControlActual` int(11) NOT NULL,
  `EnDestino` tinyint(1) NOT NULL,
  `Entregado` tinyint(1) NOT NULL,
  `FechaAsignacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`CodigoPaquete`),
  KEY `FK_RUTA_CODIGO` (`CodigoRuta`),
  CONSTRAINT `FK_PAQUETE_CODIGO` FOREIGN KEY (`CodigoPaquete`) REFERENCES `Paquete` (`Codigo`),
  CONSTRAINT `FK_RUTA_CODIGO` FOREIGN KEY (`CodigoRuta`) REFERENCES `Ruta` (`Codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PaqueteAsignadoRuta`
--

LOCK TABLES `PaqueteAsignadoRuta` WRITE;
/*!40000 ALTER TABLE `PaqueteAsignadoRuta` DISABLE KEYS */;
INSERT INTO `PaqueteAsignadoRuta` VALUES ('AAA-001','AAA-001',2,0,0,'2019-08-26 19:18:06'),('AAA-002','AAA-002',4,1,1,'2019-08-26 18:53:11'),('AAA-003','AAA-003',4,1,1,'2019-08-26 18:53:16'),('AAA-004','AAA-004',1,0,0,'2019-08-26 18:20:35'),('AAA-005','AAA-005',4,1,1,'2019-08-26 18:53:20'),('AAA-006','AAA-001',1,0,0,'2019-08-26 18:20:35'),('AAA-007','AAA-002',4,1,1,'2019-08-26 18:53:25'),('AAA-008','AAA-003',4,1,1,'2019-08-26 18:53:30'),('AAA-009','AAA-001',4,1,1,'2019-08-26 18:53:34'),('AAA-010','AAA-002',1,0,0,'2019-08-26 18:23:28'),('AAA-011','AAA-003',4,1,1,'2019-08-26 18:53:38'),('AAA-012','AAA-004',4,1,1,'2019-08-26 18:53:42'),('AAA-013','AAA-004',1,0,0,'2019-08-26 18:23:28'),('AAA-014','AAA-005',4,1,1,'2019-08-26 18:53:46'),('AAA-015','AAA-001',4,1,1,'2019-08-26 18:53:49'),('AAA-016','AAA-004',4,1,1,'2019-08-26 18:53:53'),('AAA-017','AAA-001',1,0,0,'2019-08-26 18:25:42'),('AAA-018','AAA-002',1,0,0,'2019-08-26 18:25:42'),('AAA-019','AAA-003',4,1,1,'2019-08-26 18:53:56'),('AAA-020','AAA-004',1,0,0,'2019-08-26 18:25:44'),('AAA-021','AAA-005',4,1,1,'2019-08-26 18:54:00'),('AAA-022','AAA-001',4,1,1,'2019-08-26 18:54:04'),('AAA-023','AAA-002',4,1,1,'2019-08-26 18:54:07'),('AAA-024','AAA-003',1,0,0,'2019-08-26 18:25:44'),('AAA-025','AAA-001',1,0,0,'2019-08-26 18:28:25'),('AAA-026','AAA-002',4,1,1,'2019-08-26 18:54:11'),('AAA-027','AAA-003',4,1,1,'2019-08-26 18:54:23'),('AAA-028','AAA-004',1,0,0,'2019-08-26 18:28:25'),('AAA-029','AAA-005',4,1,1,'2019-08-26 18:54:27'),('AAA-030','AAA-005',4,1,1,'2019-08-26 18:54:30'),('AAA-031','AAA-004',4,1,1,'2019-08-26 18:54:34'),('AAA-032','AAA-003',1,0,0,'2019-08-26 18:28:25'),('AAA-033','AAA-001',4,1,1,'2019-08-26 18:54:38'),('AAA-034','AAA-002',4,1,1,'2019-08-26 18:54:41'),('AAA-035','AAA-003',4,1,1,'2019-08-26 18:54:45'),('AAA-036','AAA-004',4,1,1,'2019-08-26 18:54:47'),('AAA-037','AAA-005',1,0,0,'2019-08-26 18:34:45'),('AAA-038','AAA-001',4,1,1,'2019-08-26 18:54:52'),('AAA-039','AAA-002',1,0,0,'2019-08-26 18:34:45'),('AAA-040','AAA-003',1,0,0,'2019-08-26 18:34:45'),('AAA-041','AAA-005',1,0,0,'2019-08-26 18:34:45');
/*!40000 ALTER TABLE `PaqueteAsignadoRuta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PaquetePasaPuntoDeControl`
--

DROP TABLE IF EXISTS `PaquetePasaPuntoDeControl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PaquetePasaPuntoDeControl` (
  `CodigoPaquete` char(7) NOT NULL,
  `CodigoRuta` char(7) NOT NULL,
  `CodigoPuntoDeControl` int(11) NOT NULL,
  `TarifaOperacion` double NOT NULL,
  `CantidadHoras` double NOT NULL,
  `Costo` double NOT NULL,
  `Finalizado` tinyint(1) NOT NULL,
  `EnTurno` tinyint(1) NOT NULL,
  `FechaIngreso` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `Prioridad` tinyint(1) NOT NULL,
  PRIMARY KEY (`CodigoPaquete`,`CodigoRuta`,`CodigoPuntoDeControl`),
  KEY `FK__RUTA_CODIGO_PUNTO_DE_CONTROL` (`CodigoRuta`),
  CONSTRAINT `FK_PAQUETE_CODIGO_PUNTO_DE_CONTROL` FOREIGN KEY (`CodigoPaquete`) REFERENCES `Paquete` (`Codigo`),
  CONSTRAINT `FK__RUTA_CODIGO_PUNTO_DE_CONTROL` FOREIGN KEY (`CodigoRuta`) REFERENCES `Ruta` (`Codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PaquetePasaPuntoDeControl`
--

LOCK TABLES `PaquetePasaPuntoDeControl` WRITE;
/*!40000 ALTER TABLE `PaquetePasaPuntoDeControl` DISABLE KEYS */;
INSERT INTO `PaquetePasaPuntoDeControl` VALUES ('AAA-001','AAA-001',1,15,3,45,1,0,'2019-08-26 17:19:17',0),('AAA-001','AAA-001',2,15,0,0,0,1,'2019-08-26 19:18:06',0),('AAA-001','AAA-001',3,15,0,0,0,0,'2019-08-26 18:19:17',0),('AAA-001','AAA-001',4,15,0,0,0,0,'2019-08-26 18:19:17',0),('AAA-002','AAA-002',1,15,1,15,1,0,'2019-08-26 17:20:34',1),('AAA-002','AAA-002',2,15,2,30,1,0,'2019-08-26 17:43:23',1),('AAA-002','AAA-002',3,15,3,45,1,0,'2019-08-26 17:44:14',1),('AAA-002','AAA-002',4,15,4,60,1,0,'2019-08-26 17:44:52',1),('AAA-003','AAA-003',1,20,1,20,1,0,'2019-08-26 17:20:35',1),('AAA-003','AAA-003',2,20,2,40,1,0,'2019-08-26 17:49:00',1),('AAA-003','AAA-003',3,20,2,40,1,0,'2019-08-26 17:49:49',1),('AAA-003','AAA-003',4,20,1,20,1,0,'2019-08-26 17:50:41',1),('AAA-004','AAA-004',1,15,0,0,0,1,'2019-08-26 18:20:35',0),('AAA-004','AAA-004',2,15,0,0,0,0,'2019-08-26 18:20:35',0),('AAA-004','AAA-004',3,15,0,0,0,0,'2019-08-26 18:20:35',0),('AAA-004','AAA-004',4,15,0,0,0,0,'2019-08-26 18:20:35',0),('AAA-005','AAA-005',1,25,1,25,1,0,'2019-08-26 17:20:35',0),('AAA-005','AAA-005',2,25,2,50,1,0,'2019-08-26 17:46:35',0),('AAA-005','AAA-005',3,25,3,75,1,0,'2019-08-26 17:47:14',0),('AAA-005','AAA-005',4,25,5,125,1,0,'2019-08-26 17:47:51',0),('AAA-006','AAA-001',1,15,0,0,0,1,'2019-08-26 18:20:35',0),('AAA-006','AAA-001',2,15,0,0,0,0,'2019-08-26 18:20:35',0),('AAA-006','AAA-001',3,15,0,0,0,0,'2019-08-26 18:20:35',0),('AAA-006','AAA-001',4,15,0,0,0,0,'2019-08-26 18:20:35',0),('AAA-007','AAA-002',1,15,1,15,1,0,'2019-08-26 17:20:56',1),('AAA-007','AAA-002',2,15,2,30,1,0,'2019-08-26 17:43:34',1),('AAA-007','AAA-002',3,15,3,45,1,0,'2019-08-26 17:44:20',1),('AAA-007','AAA-002',4,15,5,75,1,0,'2019-08-26 17:45:00',1),('AAA-008','AAA-003',1,20,1,20,1,0,'2019-08-26 17:20:56',0),('AAA-008','AAA-003',2,20,2,40,1,0,'2019-08-26 17:49:29',0),('AAA-008','AAA-003',3,20,3,60,1,0,'2019-08-26 17:50:20',0),('AAA-008','AAA-003',4,20,1,20,1,0,'2019-08-26 17:51:08',0),('AAA-009','AAA-001',1,15,1,15,1,0,'2019-08-26 17:23:28',1),('AAA-009','AAA-001',2,15,2,30,1,0,'2019-08-26 17:37:06',1),('AAA-009','AAA-001',3,15,2,30,1,0,'2019-08-26 17:39:23',1),('AAA-009','AAA-001',4,15,2,30,1,0,'2019-08-26 17:40:08',1),('AAA-010','AAA-002',1,15,0,0,0,1,'2019-08-26 18:23:28',0),('AAA-010','AAA-002',2,15,0,0,0,0,'2019-08-26 18:23:28',0),('AAA-010','AAA-002',3,15,0,0,0,0,'2019-08-26 18:23:28',0),('AAA-010','AAA-002',4,15,0,0,0,0,'2019-08-26 18:23:28',0),('AAA-011','AAA-003',1,20,1,20,1,0,'2019-08-26 17:23:28',1),('AAA-011','AAA-003',2,20,2,40,1,0,'2019-08-26 17:49:07',1),('AAA-011','AAA-003',3,20,3,60,1,0,'2019-08-26 17:49:55',1),('AAA-011','AAA-003',4,20,5,100,1,0,'2019-08-26 17:50:48',1),('AAA-012','AAA-004',1,15,1,15,1,0,'2019-08-26 17:23:28',1),('AAA-012','AAA-004',2,15,2,30,1,0,'2019-08-26 17:37:55',1),('AAA-012','AAA-004',3,15,2,30,1,0,'2019-08-26 17:41:41',1),('AAA-012','AAA-004',4,15,3,45,1,0,'2019-08-26 17:42:08',1),('AAA-013','AAA-004',1,15,0,0,0,1,'2019-08-26 18:23:28',0),('AAA-013','AAA-004',2,15,0,0,0,0,'2019-08-26 18:23:28',0),('AAA-013','AAA-004',3,15,0,0,0,0,'2019-08-26 18:23:28',0),('AAA-013','AAA-004',4,15,0,0,0,0,'2019-08-26 18:23:28',0),('AAA-014','AAA-005',1,25,1,25,1,0,'2019-08-26 17:23:28',1),('AAA-014','AAA-005',2,25,2,50,1,0,'2019-08-26 17:46:12',1),('AAA-014','AAA-005',3,25,3,75,1,0,'2019-08-26 17:46:48',1),('AAA-014','AAA-005',4,25,1,25,1,0,'2019-08-26 17:47:27',1),('AAA-015','AAA-001',1,15,1,15,1,0,'2019-08-26 17:23:28',1),('AAA-015','AAA-001',2,15,2,30,1,0,'2019-08-26 17:37:38',1),('AAA-015','AAA-001',3,15,2,30,1,0,'2019-08-26 17:39:29',1),('AAA-015','AAA-001',4,15,3,45,1,0,'2019-08-26 17:40:18',1),('AAA-016','AAA-004',1,15,1,15,1,0,'2019-08-26 17:23:28',1),('AAA-016','AAA-004',2,15,2,30,1,0,'2019-08-26 17:38:04',1),('AAA-016','AAA-004',3,15,2,30,1,0,'2019-08-26 17:41:47',1),('AAA-016','AAA-004',4,15,3,45,1,0,'2019-08-26 17:42:12',1),('AAA-017','AAA-001',1,15,0,0,0,1,'2019-08-26 18:25:42',0),('AAA-017','AAA-001',2,15,0,0,0,0,'2019-08-26 18:25:42',0),('AAA-017','AAA-001',3,15,0,0,0,0,'2019-08-26 18:25:42',0),('AAA-017','AAA-001',4,15,0,0,0,0,'2019-08-26 18:25:42',0),('AAA-018','AAA-002',1,15,0,0,0,1,'2019-08-26 18:25:42',0),('AAA-018','AAA-002',2,15,0,0,0,0,'2019-08-26 18:25:42',0),('AAA-018','AAA-002',3,15,0,0,0,0,'2019-08-26 18:25:42',0),('AAA-018','AAA-002',4,15,0,0,0,0,'2019-08-26 18:25:42',0),('AAA-019','AAA-003',1,20,1,20,1,0,'2019-08-26 17:25:42',0),('AAA-019','AAA-003',2,20,3,60,1,0,'2019-08-26 17:49:38',0),('AAA-019','AAA-003',3,20,4,80,1,0,'2019-08-26 17:50:26',0),('AAA-019','AAA-003',4,20,3,60,1,0,'2019-08-26 17:51:15',0),('AAA-020','AAA-004',1,15,0,0,0,1,'2019-08-26 18:25:44',0),('AAA-020','AAA-004',2,15,0,0,0,0,'2019-08-26 18:25:44',0),('AAA-020','AAA-004',3,15,0,0,0,0,'2019-08-26 18:25:44',0),('AAA-020','AAA-004',4,15,0,0,0,0,'2019-08-26 18:25:44',0),('AAA-021','AAA-005',1,25,1,25,1,0,'2019-08-26 17:25:44',1),('AAA-021','AAA-005',2,25,2,50,1,0,'2019-08-26 17:46:16',1),('AAA-021','AAA-005',3,25,3,75,1,0,'2019-08-26 17:46:53',1),('AAA-021','AAA-005',4,25,2,50,1,0,'2019-08-26 17:47:33',1),('AAA-022','AAA-001',1,15,1,15,1,0,'2019-08-26 17:25:44',1),('AAA-022','AAA-001',2,15,2,30,1,0,'2019-08-26 17:38:42',1),('AAA-022','AAA-001',3,15,2,30,1,0,'2019-08-26 17:39:38',1),('AAA-022','AAA-001',4,15,3,45,1,0,'2019-08-26 17:40:32',1),('AAA-023','AAA-002',1,15,1,15,1,0,'2019-08-26 17:25:44',1),('AAA-023','AAA-002',2,15,2,30,1,0,'2019-08-26 17:43:40',1),('AAA-023','AAA-002',3,15,3,45,1,0,'2019-08-26 17:44:27',1),('AAA-023','AAA-002',4,15,1,15,1,0,'2019-08-26 17:45:07',1),('AAA-024','AAA-003',1,20,0,0,0,1,'2019-08-26 18:25:44',0),('AAA-024','AAA-003',2,20,0,0,0,0,'2019-08-26 18:25:44',0),('AAA-024','AAA-003',3,20,0,0,0,0,'2019-08-26 18:25:44',0),('AAA-024','AAA-003',4,20,0,0,0,0,'2019-08-26 18:25:44',0),('AAA-025','AAA-001',1,15,0,0,0,1,'2019-08-26 18:28:25',0),('AAA-025','AAA-001',2,15,0,0,0,0,'2019-08-26 18:28:25',0),('AAA-025','AAA-001',3,15,0,0,0,0,'2019-08-26 18:28:25',0),('AAA-025','AAA-001',4,15,0,0,0,0,'2019-08-26 18:28:25',0),('AAA-026','AAA-002',1,15,1,15,1,0,'2019-08-26 17:28:25',1),('AAA-026','AAA-002',2,15,2,30,1,0,'2019-08-26 17:43:47',1),('AAA-026','AAA-002',3,15,3,45,1,0,'2019-08-26 17:44:34',1),('AAA-026','AAA-002',4,15,2,30,1,0,'2019-08-26 17:45:13',1),('AAA-027','AAA-003',1,20,1,20,1,0,'2019-08-26 17:28:25',1),('AAA-027','AAA-003',2,20,2,40,1,0,'2019-08-26 17:49:14',1),('AAA-027','AAA-003',3,20,2,40,1,0,'2019-08-26 17:49:59',1),('AAA-027','AAA-003',4,20,6,120,1,0,'2019-08-26 17:50:57',1),('AAA-028','AAA-004',1,15,0,0,0,1,'2019-08-26 18:28:25',0),('AAA-028','AAA-004',2,15,0,0,0,0,'2019-08-26 18:28:25',0),('AAA-028','AAA-004',3,15,0,0,0,0,'2019-08-26 18:28:25',0),('AAA-028','AAA-004',4,15,0,0,0,0,'2019-08-26 18:28:25',0),('AAA-029','AAA-005',1,25,1,25,1,0,'2019-08-26 17:28:25',1),('AAA-029','AAA-005',2,25,2,50,1,0,'2019-08-26 17:46:24',1),('AAA-029','AAA-005',3,25,3,75,1,0,'2019-08-26 17:47:00',1),('AAA-029','AAA-005',4,25,3,75,1,0,'2019-08-26 17:47:38',1),('AAA-030','AAA-005',1,25,1,25,1,0,'2019-08-26 17:28:25',1),('AAA-030','AAA-005',2,25,2,50,1,0,'2019-08-26 17:46:29',1),('AAA-030','AAA-005',3,25,3,75,1,0,'2019-08-26 17:47:07',1),('AAA-030','AAA-005',4,25,4,100,1,0,'2019-08-26 17:47:44',1),('AAA-031','AAA-004',1,15,1,15,1,0,'2019-08-26 17:28:25',1),('AAA-031','AAA-004',2,15,2,30,1,0,'2019-08-26 17:38:14',1),('AAA-031','AAA-004',3,15,2,30,1,0,'2019-08-26 17:41:53',1),('AAA-031','AAA-004',4,15,3,45,1,0,'2019-08-26 17:42:21',1),('AAA-032','AAA-003',1,20,0,0,0,1,'2019-08-26 18:28:25',0),('AAA-032','AAA-003',2,20,0,0,0,0,'2019-08-26 18:28:25',0),('AAA-032','AAA-003',3,20,0,0,0,0,'2019-08-26 18:28:25',0),('AAA-032','AAA-003',4,20,0,0,0,0,'2019-08-26 18:28:25',0),('AAA-033','AAA-001',1,15,1,15,1,0,'2019-08-26 17:34:44',1),('AAA-033','AAA-001',2,15,2,30,1,0,'2019-08-26 17:38:51',1),('AAA-033','AAA-001',3,15,2,30,1,0,'2019-08-26 17:39:45',1),('AAA-033','AAA-001',4,15,3,45,1,0,'2019-08-26 17:40:41',1),('AAA-034','AAA-002',1,15,1,15,1,0,'2019-08-26 17:34:44',1),('AAA-034','AAA-002',2,15,2,30,1,0,'2019-08-26 17:43:54',1),('AAA-034','AAA-002',3,15,0.5,7.5,1,0,'2019-08-26 17:44:40',1),('AAA-034','AAA-002',4,15,3,45,1,0,'2019-08-26 17:45:21',1),('AAA-035','AAA-003',1,20,1,20,1,0,'2019-08-26 17:34:45',1),('AAA-035','AAA-003',2,20,2,40,1,0,'2019-08-26 17:49:21',1),('AAA-035','AAA-003',3,20,1,20,1,0,'2019-08-26 17:50:11',1),('AAA-035','AAA-003',4,20,2,40,1,0,'2019-08-26 17:51:01',1),('AAA-036','AAA-004',1,15,1,15,1,0,'2019-08-26 17:34:45',1),('AAA-036','AAA-004',2,15,2,30,1,0,'2019-08-26 17:38:23',1),('AAA-036','AAA-004',3,15,2,30,1,0,'2019-08-26 17:41:58',1),('AAA-036','AAA-004',4,15,3,45,1,0,'2019-08-26 17:42:27',1),('AAA-037','AAA-005',1,25,0,0,0,1,'2019-08-26 18:34:45',0),('AAA-037','AAA-005',2,25,0,0,0,0,'2019-08-26 18:34:45',0),('AAA-037','AAA-005',3,25,0,0,0,0,'2019-08-26 18:34:45',0),('AAA-037','AAA-005',4,25,0,0,0,0,'2019-08-26 18:34:45',0),('AAA-038','AAA-001',1,15,1,15,1,0,'2019-08-26 17:34:45',1),('AAA-038','AAA-001',2,15,2,30,1,0,'2019-08-26 17:38:58',1),('AAA-038','AAA-001',3,15,2,30,1,0,'2019-08-26 17:39:55',1),('AAA-038','AAA-001',4,15,3,45,1,0,'2019-08-26 17:40:47',1),('AAA-039','AAA-002',1,15,0,0,0,1,'2019-08-26 18:34:45',1),('AAA-039','AAA-002',2,15,0,0,0,0,'2019-08-26 18:34:45',1),('AAA-039','AAA-002',3,15,0,0,0,0,'2019-08-26 18:34:45',1),('AAA-039','AAA-002',4,15,0,0,0,0,'2019-08-26 18:34:45',1),('AAA-040','AAA-003',1,20,0,0,0,1,'2019-08-26 18:34:45',0),('AAA-040','AAA-003',2,20,0,0,0,0,'2019-08-26 18:34:45',0),('AAA-040','AAA-003',3,20,0,0,0,0,'2019-08-26 18:34:45',0),('AAA-040','AAA-003',4,20,0,0,0,0,'2019-08-26 18:34:45',0),('AAA-041','AAA-005',1,25,0,0,0,1,'2019-08-26 18:34:45',0),('AAA-041','AAA-005',2,25,0,0,0,0,'2019-08-26 18:34:45',0),('AAA-041','AAA-005',3,25,0,0,0,0,'2019-08-26 18:34:45',0),('AAA-041','AAA-005',4,25,0,0,0,0,'2019-08-26 18:34:45',0);
/*!40000 ALTER TABLE `PaquetePasaPuntoDeControl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PuntoDeControl`
--

DROP TABLE IF EXISTS `PuntoDeControl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PuntoDeControl` (
  `Codigo` int(11) NOT NULL,
  `CodigoRuta` char(7) NOT NULL,
  `Nombre` varchar(30) NOT NULL,
  `TarifaOperacion` double NOT NULL,
  `CantidadPaquetesCola` int(11) NOT NULL,
  `OperadorAsignado` varchar(25) NOT NULL,
  `UltimoPuntoDeControl` tinyint(1) NOT NULL,
  `TarifaOperacionPropia` tinyint(1) NOT NULL,
  PRIMARY KEY (`Codigo`,`CodigoRuta`),
  KEY `FK_RUTA_CODIGO_RUTA` (`CodigoRuta`),
  CONSTRAINT `FK_RUTA_CODIGO_RUTA` FOREIGN KEY (`CodigoRuta`) REFERENCES `Ruta` (`Codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PuntoDeControl`
--

LOCK TABLES `PuntoDeControl` WRITE;
/*!40000 ALTER TABLE `PuntoDeControl` DISABLE KEYS */;
INSERT INTO `PuntoDeControl` VALUES (1,'AAA-001','Punto 1 Xela-Guatemala',15,10,'operador1',0,0),(1,'AAA-002','Punto 1 Xela-Toto',15,10,'operador2',0,0),(1,'AAA-003','Punto 1 Xela-San Marcos',20,10,'operador3',0,1),(1,'AAA-004','Punto 1 Xela-Reu',15,10,'operador1',0,0),(1,'AAA-005','Punto 1 Xela-Huehue',25,10,'operador2',0,1),(2,'AAA-001','Punto 2  Xela-Guatemala',15,10,'operador1',0,0),(2,'AAA-002','Punto 2 Xela-Toto',15,10,'operador2',0,0),(2,'AAA-003','Punto 2 Xela-San Marcos',20,10,'operador3',0,1),(2,'AAA-004','Punto 2 Xela-Reu',15,10,'operador1',0,0),(2,'AAA-005','Punto 2 Xela-Huehue',25,10,'operador2',0,1),(3,'AAA-001','Punto 3  Xela-Guatemala',15,10,'operador1',0,0),(3,'AAA-002','Punto 3 Xela-Toto',15,10,'operador2',0,0),(3,'AAA-003','Punto 3 Xela-San Marcos',20,10,'operador3',0,1),(3,'AAA-004','Punto 3 Xela-Reu',15,10,'operador1',0,0),(3,'AAA-005','Punto 3 Xela-Huehue',25,10,'operador2',0,1),(4,'AAA-001','Punto 4  Xela-Guatemala',15,10,'operador1',1,0),(4,'AAA-002','Punto 4 Xela-Toto',15,10,'operador2',1,0),(4,'AAA-003','Punto 4 Xela-San Marcos',20,10,'operador3',1,1),(4,'AAA-004','Punto 4 Xela-Reu',15,10,'operador1',1,0),(4,'AAA-005','Punto 4 Xela-Huehue',25,10,'operador2',1,1);
/*!40000 ALTER TABLE `PuntoDeControl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Recepcionista`
--

DROP TABLE IF EXISTS `Recepcionista`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Recepcionista` (
  `Nombre` varchar(30) NOT NULL,
  `Apellido` varchar(30) NOT NULL,
  `NombreUsuario` varchar(25) NOT NULL,
  PRIMARY KEY (`NombreUsuario`),
  CONSTRAINT `FK_USUARIO_RECEPCIONISTA_NOMBRE_USUARIO` FOREIGN KEY (`NombreUsuario`) REFERENCES `Usuario` (`NombreUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Recepcionista`
--

LOCK TABLES `Recepcionista` WRITE;
/*!40000 ALTER TABLE `Recepcionista` DISABLE KEYS */;
INSERT INTO `Recepcionista` VALUES ('Recepcionista1','Usuario7','recepcionista1'),('Recepcionista2','Usuario8','recepcionista2'),('Recepcionista3','Usuario9','recepcionista3');
/*!40000 ALTER TABLE `Recepcionista` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Ruta`
--

DROP TABLE IF EXISTS `Ruta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Ruta` (
  `Codigo` char(7) NOT NULL,
  `Nombre` varchar(30) DEFAULT NULL,
  `Destino` varchar(30) NOT NULL,
  `Activa` tinyint(1) NOT NULL,
  PRIMARY KEY (`Codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Ruta`
--

LOCK TABLES `Ruta` WRITE;
/*!40000 ALTER TABLE `Ruta` DISABLE KEYS */;
INSERT INTO `Ruta` VALUES ('AAA-001','Xela-Guatemala','Guatemala',1),('AAA-002','Xela-Toto','Totonicapan',1),('AAA-003','Xela-San Marcos','San Marcos',1),('AAA-004','Xela-Reu','Retalhuleu',1),('AAA-005','Xela-Huehue','Huehuetenango',1);
/*!40000 ALTER TABLE `Ruta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Tarifa`
--

DROP TABLE IF EXISTS `Tarifa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Tarifa` (
  `TarifaOperacionGlobal` double NOT NULL,
  `PrecioLibraGlobal` double NOT NULL,
  `CuotaPriorizacionGlobal` double NOT NULL,
  `CuotaDestinoGlobal` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Tarifa`
--

LOCK TABLES `Tarifa` WRITE;
/*!40000 ALTER TABLE `Tarifa` DISABLE KEYS */;
INSERT INTO `Tarifa` VALUES (15,10,35,50);
/*!40000 ALTER TABLE `Tarifa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Usuario`
--

DROP TABLE IF EXISTS `Usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Usuario` (
  `Nombre` varchar(30) NOT NULL,
  `Apellido` varchar(30) NOT NULL,
  `NombreUsuario` varchar(25) NOT NULL,
  `Contrasena` varchar(25) NOT NULL,
  `Tipo` varchar(13) NOT NULL,
  `Activo` tinyint(1) NOT NULL,
  PRIMARY KEY (`NombreUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Usuario`
--

LOCK TABLES `Usuario` WRITE;
/*!40000 ALTER TABLE `Usuario` DISABLE KEYS */;
INSERT INTO `Usuario` VALUES ('Administrador1','Usuario1','administrador1','123','Administrador',1),('Administrador2','Usuario2','administrador2','123','Administrador',1),('Administrador3','Usuario3','administrador3','123','Administrador',1),('Helmuth Alexander','Luther Montejo','AlexanderLuther','123','Administrador',1),('Operador1','Usuario4','operador1','123','Operador',1),('Operador2','Usuario5','operador2','123','Operador',1),('Operador3','Usuario6','operador3','123','Operador',1),('Recepcionista1','Usuario7','recepcionista1','123','Recepcionista',1),('Recepcionista2','Usuario8','recepcionista2','123','Recepcionista',1),('Recepcionista3','Usuario9','recepcionista3','123','Recepcionista',1);
/*!40000 ALTER TABLE `Usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-08-26 13:27:08
