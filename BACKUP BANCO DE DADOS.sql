-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: dbinfox
-- ------------------------------------------------------
-- Server version	5.7.21

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
-- Table structure for table `clientes`
--

DROP TABLE IF EXISTS `clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `clientes` (
  `id_cliente` int(11) NOT NULL AUTO_INCREMENT,
  `nome_cliente` varchar(50) NOT NULL,
  `end_cliente` varchar(100) NOT NULL,
  `fone_cliente` varchar(50) NOT NULL,
  `email_cliente` varchar(60) NOT NULL,
  `cpf` varchar(15) NOT NULL,
  PRIMARY KEY (`id_cliente`)
) ENGINE=MyISAM AUTO_INCREMENT=41 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes`
--

LOCK TABLES `clientes` WRITE;
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
INSERT INTO `clientes` VALUES (33,'THULIO','asdasd','(22) 2222-2222','asdadas','213.123.131-23'),(30,'teste','teste','(12) 3131-2321','teste','121.312.313-12'),(32,'THULIO','asdasd','(22) 2222-2222','asdadas','213.123.131-23'),(37,'José de Assis','Rua Amélia','(92) 9283-8392','josedeassis@gmail.com','929.288.392-93'),(29,'Maria Rita','Vinte e Um de Dezembros','(71) 7717-1717','mariarita@gmail.com','089.797.397-44'),(27,'Silas','Rua Gilvandro Soares','(12) 3131-2321','silascmv@gmail.com','089.797.344-50'),(28,'TESTE','TESTE','(12) 3131-3123','SAASUSAH','122.313.123-22'),(39,'TESTE22','TESTE222','(12) 3123-2131','TESTE222','123.123.123-12'),(35,'sdadas','VERDADE','(12) 3123-1231','VERDADE','123.123.123-12'),(36,'João Pedro','Avenida Boa Viagem','(82) 8228-2819','joaopedro@hotmail.com','192.929.229-19'),(38,'Fernando','Prado','(12) 3131-2312','Silas','112.323.131-23');
/*!40000 ALTER TABLE `clientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ordem_servi`
--

DROP TABLE IF EXISTS `ordem_servi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ordem_servi` (
  `id_os` int(11) NOT NULL AUTO_INCREMENT,
  `fk_id_cliente` int(11) NOT NULL,
  `data_os` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `tipo` varchar(100) DEFAULT NULL,
  `situacao` varchar(50) NOT NULL,
  `produto` varchar(50) NOT NULL,
  `descricao` varchar(100) DEFAULT NULL,
  `defeito` varchar(150) NOT NULL,
  `servico` varchar(150) NOT NULL,
  `tecnico_respo` varchar(50) NOT NULL,
  `valor` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id_os`),
  KEY `fk_id_cliente` (`fk_id_cliente`)
) ENGINE=MyISAM AUTO_INCREMENT=24 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ordem_servi`
--

LOCK TABLES `ordem_servi` WRITE;
/*!40000 ALTER TABLE `ordem_servi` DISABLE KEYS */;
INSERT INTO `ordem_servi` VALUES (18,32,'2018-10-29 16:57:49','Orçamento','Finalizado','TESTE','','TESTE','TESTE','TESTE',200.00),(19,37,'2018-10-29 17:14:01','Orçamento','Finalizado','notebook','','não liga','manutenção','Silas',310.00),(20,29,'2018-10-30 18:44:22','Orçamento','Em correção','Computador','PRIMEIRO PROBLEMA IDENTIFICADO FOI O GABINETE QUE NÃO LIGA','Fonte Queimada','Manutenção','Silas',300.00),(21,29,'2018-10-30 18:46:26','Orçamento','Finalizado','Tv','','Não Liga','Manutenção','Silas',300.00),(22,36,'2018-10-30 19:10:11','Orçamento','Finalizado','TESTE','','TESTE','TESTE','TESTE',111.00),(23,33,'2018-10-30 19:14:05','Orçamento','Finalizado','sasaas','','assasaas','assa','assasa',111.00);
/*!40000 ALTER TABLE `ordem_servi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuarios` (
  `id_usu` int(11) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `fone` varchar(15) DEFAULT NULL,
  `login` varchar(15) NOT NULL,
  `senha` varchar(15) NOT NULL,
  `perfil` varchar(20) NOT NULL,
  PRIMARY KEY (`id_usu`),
  UNIQUE KEY `login` (`login`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'Silas Cardoso','34472239','admin','admin','admin'),(2,'DBA','996171578','dba','dba','DBA'),(4,'testando','3333333','login','senha','admin'),(3,'Atendimento','939939939','atendimento','123456','atendimento'),(6,'Teste','teste','teste','teste','atendimento'),(100,'Thulio','thulio','thulio','thulio','admin'),(15,'TESTE','TESTE','login2','senha','atendimento');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-10-30 17:00:14
