/*
SQLyog Ultimate v9.02 
MySQL - 8.0.18 : Database - apiprueba
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`apiprueba` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `apiprueba`;

/*Table structure for table `articulo_insumo` */

DROP TABLE IF EXISTS `articulo_insumo`;

CREATE TABLE `articulo_insumo` (
  `denominacion` varchar(255) DEFAULT NULL,
  `precio_compra` double DEFAULT NULL,
  `precio_venta` double DEFAULT NULL,
  `stock_actual` double DEFAULT NULL,
  `stock_minimo` double DEFAULT NULL,
  `id_control` int(11) NOT NULL,
  `id_rubro` int(11) DEFAULT NULL,
  `id_unidad_medida` int(11) DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id_control`),
  UNIQUE KEY `id` (`id`),
  KEY `FKmslbdfyg7h1golg0b64fpiug7` (`id_rubro`),
  KEY `FKkytvj88cp7kvkjf5vjvlpblho` (`id_unidad_medida`),
  CONSTRAINT `FKkytvj88cp7kvkjf5vjvlpblho` FOREIGN KEY (`id_unidad_medida`) REFERENCES `unidad_medida` (`id`),
  CONSTRAINT `FKmslbdfyg7h1golg0b64fpiug7` FOREIGN KEY (`id_rubro`) REFERENCES `rubro_articulo` (`id_control`),
  CONSTRAINT `FKr27ja73cdoh0y7vi1es9dq2sq` FOREIGN KEY (`id_control`) REFERENCES `control` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `articulo_insumo` */

insert  into `articulo_insumo`(`denominacion`,`precio_compra`,`precio_venta`,`stock_actual`,`stock_minimo`,`id_control`,`id_rubro`,`id_unidad_medida`,`id`) values ('Sal',100,130,3850,1000,9,8,1,1),('Mantecoso',310,400,9.4,20,13,11,2,2),('Rucula',40,60,3,5,16,2,2,3),('Jamon',400,550,19.6,14,17,14,2,4),('Mozzarella',350,430,21.550000000000004,20,18,11,2,5),('Molida',250,350,48.2,20,29,12,2,6),('Tapas empanada',50,70,131,100,30,8,5,7),('Cebolla',30,50,19.1,10,31,2,2,8),('Palmitos',400,500,33.550000000000004,30,38,2,2,9),('Salsa Tomate',60,90,33.9,20,44,2,3,10),('Bife',300,450,20,10,47,12,2,11);

/*Table structure for table `articulo_manufacturado` */

DROP TABLE IF EXISTS `articulo_manufacturado`;

CREATE TABLE `articulo_manufacturado` (
  `denominacion` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `precio_venta` double DEFAULT NULL,
  `tiempo_cocina` int(11) DEFAULT NULL,
  `id_control` int(11) NOT NULL,
  `id_rubro` int(11) DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id_control`),
  UNIQUE KEY `id` (`id`),
  KEY `FK5hv0evxijuuu0l03g36xy5jj1` (`id_rubro`),
  CONSTRAINT `FK5hv0evxijuuu0l03g36xy5jj1` FOREIGN KEY (`id_rubro`) REFERENCES `rubro_general` (`id_control`),
  CONSTRAINT `FKk3k322sjtd6cj1tt6tcv0tpyh` FOREIGN KEY (`id_control`) REFERENCES `control` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Data for the table `articulo_manufacturado` */

insert  into `articulo_manufacturado`(`denominacion`,`image`,`precio_venta`,`tiempo_cocina`,`id_control`,`id_rubro`,`id`) values ('Pizza Especial','pizza-especial.jpg',350,40,15,1,1),('Pizza Rucula y Jamon','pizza-rucula.jpg',350,44,28,1,2),('Empanada Carne','empanada-carne.jpg',260,34,37,20,3),('Pizza Palmitos','pizza-palmitos.jpg',400,44,39,1,4),('Lomo completo','lomo.jpg',400,34,40,22,5),('Hamburguesa mediana','hamburguesa.jpg',270,43,41,19,6),('Lomo Pizza','lomo-pizza.jpg',560,47,42,22,7),('Ravioles con tuco','ravioles.jpg',200,30,45,21,8),('Lasagna','lasagna.png',300,40,46,21,9),('Empanada Jamon y Queso','empanadas-de-jamn-y-queso.jpg',300,26,48,20,10);

/*Table structure for table `articulo_manufacturado_detalle` */

DROP TABLE IF EXISTS `articulo_manufacturado_detalle`;

CREATE TABLE `articulo_manufacturado_detalle` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cantidad` double DEFAULT NULL,
  `id_insumo` int(11) DEFAULT NULL,
  `id_manufacturado` int(11) DEFAULT NULL,
  `id_unidad_medida` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9gccj0nu416vhi6gnle4ep94c` (`id_insumo`),
  KEY `FK92imtng9h8nyblogl36eckx51` (`id_manufacturado`),
  KEY `FKftlw9vg9sg89jvwqsb4ssqx6g` (`id_unidad_medida`),
  CONSTRAINT `FK92imtng9h8nyblogl36eckx51` FOREIGN KEY (`id_manufacturado`) REFERENCES `articulo_manufacturado` (`id_control`),
  CONSTRAINT `FK9gccj0nu416vhi6gnle4ep94c` FOREIGN KEY (`id_insumo`) REFERENCES `articulo_insumo` (`id_control`),
  CONSTRAINT `FKftlw9vg9sg89jvwqsb4ssqx6g` FOREIGN KEY (`id_unidad_medida`) REFERENCES `unidad_medida` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

/*Data for the table `articulo_manufacturado_detalle` */

insert  into `articulo_manufacturado_detalle`(`id`,`cantidad`,`id_insumo`,`id_manufacturado`,`id_unidad_medida`) values (1,0.2,13,15,2),(2,0.1,16,28,1),(3,0.25,18,28,2),(4,12,30,37,5),(5,0.5,29,37,2),(6,0.3,31,37,2),(7,0.2,38,39,2),(8,0.5,47,40,2),(9,0.2,31,40,2),(10,0.3,29,41,2),(11,0.1,17,41,1),(12,0.2,17,42,2),(13,0.2,13,42,2),(14,0.15,44,45,3),(15,0.2,29,46,2),(16,0.2,17,46,2),(17,0.2,31,46,2),(20,50,9,15,1),(21,0.2,17,15,1),(22,0.2,13,48,NULL),(23,0.2,17,48,1),(24,12,30,48,1);

/*Table structure for table `articulo_reventa` */

DROP TABLE IF EXISTS `articulo_reventa`;

CREATE TABLE `articulo_reventa` (
  `denominacion` varchar(255) DEFAULT NULL,
  `imagen` varchar(255) DEFAULT NULL,
  `precio_compra` double DEFAULT NULL,
  `precio_venta` double DEFAULT NULL,
  `stock_actual` double DEFAULT NULL,
  `stock_minimo` double DEFAULT NULL,
  `id_control` int(11) NOT NULL,
  `id_rubro` int(11) DEFAULT NULL,
  `id_unidad_medida` int(11) DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id_control`),
  UNIQUE KEY `id` (`id`),
  KEY `FKiy2d9viejyepm7srr8odsyfpk` (`id_rubro`),
  KEY `FK8uff36h3wjcvpioj739ar8p05` (`id_unidad_medida`),
  CONSTRAINT `FK8uff36h3wjcvpioj739ar8p05` FOREIGN KEY (`id_unidad_medida`) REFERENCES `unidad_medida` (`id`),
  CONSTRAINT `FKiy2d9viejyepm7srr8odsyfpk` FOREIGN KEY (`id_rubro`) REFERENCES `rubro_articulo` (`id_control`),
  CONSTRAINT `FKmeefnn3faa9y8kndcj0xm493j` FOREIGN KEY (`id_control`) REFERENCES `control` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

/*Data for the table `articulo_reventa` */

insert  into `articulo_reventa`(`denominacion`,`imagen`,`precio_compra`,`precio_venta`,`stock_actual`,`stock_minimo`,`id_control`,`id_rubro`,`id_unidad_medida`,`id`) values ('Coca cola 2.5','coca.png',90,120,10,20,10,25,5,1),('Mineral','mineral.jpg',60,88,17,13,27,24,5,2),('Schweppweppes','schweppweppes.jpg',85,130,21,15,32,25,5,3),('Heineken','heineken.jpg',100,160,10,10,33,26,5,4),('Stella','3100084_f.jpg',110,180,25,23,34,26,5,5),('Fanta','fanta.png',100,150,30,20,35,25,5,6),('Sprite','sprite.jpg',100,150,33,20,36,25,5,7),('Citric Litro','citric.png',70,110,19,10,53,49,5,12);

/*Table structure for table `cliente` */

DROP TABLE IF EXISTS `cliente`;

CREATE TABLE `cliente` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `apellido` varchar(255) DEFAULT NULL,
  `correo` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `telefono` bigint(20) DEFAULT NULL,
  `id_domicilio` int(11) DEFAULT NULL,
  `id_rol` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKs4usj99lah5eldgd5g61l3c3w` (`id_rol`),
  KEY `FKc2gxfaettmbkkf05q9bdslvhl` (`id_domicilio`),
  CONSTRAINT `FKc2gxfaettmbkkf05q9bdslvhl` FOREIGN KEY (`id_domicilio`) REFERENCES `domicilio` (`id`),
  CONSTRAINT `FKs4usj99lah5eldgd5g61l3c3w` FOREIGN KEY (`id_rol`) REFERENCES `rol` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

/*Data for the table `cliente` */

insert  into `cliente`(`id`,`apellido`,`correo`,`nombre`,`telefono`,`id_domicilio`,`id_rol`) values (8,'Escudero','lauta66@gmail.com','Lautaro',2615018855,1,3),(11,'Escudero','unapersonamisteriosa1@gmail.com','Lautaro',2615000000,3,1),(12,'Espinosa','pepe@gmail.com','Pepe',261555555,2,2),(13,'Cocinero','carlos@gmail.com','Carlos',232334,4,2),(14,'Cangrejo','doncangrejo@gmail.com','Don',123443,5,4);

/*Table structure for table `configuracion` */

DROP TABLE IF EXISTS `configuracion`;

CREATE TABLE `configuracion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cant_cocineros` int(11) DEFAULT NULL,
  `email_empresa` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `configuracion` */

insert  into `configuracion`(`id`,`cant_cocineros`,`email_empresa`) values (1,2,'unapersonamisteriosa1@gmail.com');

/*Table structure for table `control` */

DROP TABLE IF EXISTS `control`;

CREATE TABLE `control` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fecha_alta` datetime DEFAULT NULL,
  `fecha_baja` datetime DEFAULT NULL,
  `fecha_modificacion` datetime DEFAULT NULL,
  `usuario_baja` varchar(255) DEFAULT NULL,
  `usuario_carga` varchar(255) DEFAULT NULL,
  `usuario_modificacion` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8;

/*Data for the table `control` */

insert  into `control`(`id`,`fecha_alta`,`fecha_baja`,`fecha_modificacion`,`usuario_baja`,`usuario_carga`,`usuario_modificacion`) values (1,'2020-06-22 19:53:12',NULL,NULL,NULL,'lauta66@gmail.com',NULL),(2,'2020-06-22 19:53:29',NULL,NULL,NULL,'lauta66@gmail.com',NULL),(4,'2020-06-22 00:00:00',NULL,NULL,NULL,'lauta66@gmail.com',NULL),(5,'2020-06-22 00:00:00',NULL,NULL,NULL,'lauta66@gmail.com',NULL),(6,'2020-06-22 00:00:00',NULL,NULL,NULL,'lauta66@gmail.com',NULL),(7,'2020-06-22 00:00:00',NULL,NULL,NULL,'lauta66@gmail.com',NULL),(8,'2020-06-22 21:55:17',NULL,NULL,NULL,'lauta66@gmail.com',NULL),(9,'2020-06-22 21:55:31',NULL,'2020-08-02 18:14:44',NULL,'lauta66@gmail.com','lauta66@gmail.com'),(10,'2020-06-22 22:13:55',NULL,'2020-07-31 20:31:32',NULL,'lauta66@gmail.com','lauta66@gmail.com'),(11,'2020-06-22 22:37:49',NULL,NULL,NULL,'lauta66@gmail.com',NULL),(12,'2020-06-22 22:39:41',NULL,NULL,NULL,'lauta66@gmail.com',NULL),(13,'2020-06-22 22:40:35',NULL,'2020-08-02 18:14:55',NULL,'lauta66@gmail.com','lauta66@gmail.com'),(14,'2020-06-22 22:41:29',NULL,NULL,NULL,'lauta66@gmail.com',NULL),(15,'2020-06-22 22:41:52',NULL,'2020-08-02 18:16:11',NULL,'lauta66@gmail.com','lauta66@gmail.com'),(16,'2020-06-22 22:49:18',NULL,'2020-08-04 14:30:34',NULL,'lauta66@gmail.com','lauta66@gmail.com'),(17,'2020-06-22 22:49:41',NULL,'2020-07-30 22:09:36',NULL,'lauta66@gmail.com','lauta66@gmail.com'),(18,'2020-06-22 22:50:17',NULL,NULL,NULL,'lauta66@gmail.com',NULL),(19,'2020-06-22 22:51:44',NULL,NULL,NULL,'lauta66@gmail.com',NULL),(20,'2020-06-22 22:51:55',NULL,NULL,NULL,'lauta66@gmail.com',NULL),(21,'2020-06-22 22:51:55',NULL,NULL,NULL,'lauta66@gmail.com',NULL),(22,'2020-06-22 22:51:55',NULL,NULL,NULL,'lauta66@gmail.com',NULL),(23,'2020-06-22 22:53:49',NULL,NULL,NULL,'lauta66@gmail.com',NULL),(24,'2020-06-22 22:53:56',NULL,NULL,NULL,'lauta66@gmail.com',NULL),(25,'2020-06-22 22:53:56',NULL,NULL,NULL,'lauta66@gmail.com',NULL),(26,'2020-06-22 22:53:56',NULL,NULL,NULL,'lauta66@gmail.com',NULL),(27,'2020-06-22 22:55:03',NULL,'2020-06-23 15:08:50',NULL,'lauta66@gmail.com','lauta66@gmail.com'),(28,'2020-06-23 14:34:12',NULL,'2020-08-02 15:50:44',NULL,'lauta66@gmail.com','lauta66@gmail.com'),(29,'2020-06-23 14:36:14',NULL,'2020-07-30 22:09:19',NULL,'lauta66@gmail.com','lauta66@gmail.com'),(30,'2020-06-23 14:36:57',NULL,NULL,NULL,'lauta66@gmail.com',NULL),(31,'2020-06-23 14:37:56',NULL,'2020-07-31 19:44:51',NULL,'lauta66@gmail.com','lauta66@gmail.com'),(32,'2020-06-23 14:40:01',NULL,'2020-06-23 15:09:02',NULL,'lauta66@gmail.com','lauta66@gmail.com'),(33,'2020-06-23 14:40:52',NULL,'2020-06-23 15:09:17',NULL,'lauta66@gmail.com','lauta66@gmail.com'),(34,'2020-06-23 14:41:53',NULL,'2020-06-23 15:09:35',NULL,'lauta66@gmail.com','lauta66@gmail.com'),(35,'2020-06-23 14:42:46',NULL,'2020-06-23 15:09:52',NULL,'lauta66@gmail.com','lauta66@gmail.com'),(36,'2020-06-23 14:43:23',NULL,'2020-06-23 15:10:06',NULL,'lauta66@gmail.com','lauta66@gmail.com'),(37,'2020-06-23 14:44:57',NULL,'2020-07-31 16:33:35',NULL,'lauta66@gmail.com','lauta66@gmail.com'),(38,'2020-06-23 14:48:23',NULL,NULL,NULL,'lauta66@gmail.com',NULL),(39,'2020-06-23 14:49:14',NULL,'2020-07-31 16:34:23',NULL,'lauta66@gmail.com','lauta66@gmail.com'),(40,'2020-06-23 14:50:22',NULL,'2020-07-31 17:13:31',NULL,'lauta66@gmail.com','lauta66@gmail.com'),(41,'2020-06-23 14:54:48',NULL,'2020-07-31 17:13:54',NULL,'lauta66@gmail.com','lauta66@gmail.com'),(42,'2020-06-23 14:55:44',NULL,'2020-07-31 17:14:51',NULL,'lauta66@gmail.com','lauta66@gmail.com'),(44,'2020-06-29 00:27:27',NULL,NULL,NULL,'lauta66@gmail.com',NULL),(45,'2020-06-29 00:28:56',NULL,'2020-07-31 17:15:06',NULL,'lauta66@gmail.com','lauta66@gmail.com'),(46,'2020-07-30 22:06:38',NULL,'2020-07-31 17:16:00',NULL,'lauta66@gmail.com','lauta66@gmail.com'),(47,'2020-07-31 17:12:37',NULL,NULL,NULL,'lauta66@gmail.com',NULL),(48,'2020-08-04 15:19:11',NULL,NULL,NULL,'lauta66@gmail.com',NULL),(49,'2020-08-04 15:46:46',NULL,NULL,NULL,'lauta66@gmail.com',NULL),(53,'2020-08-04 16:11:38',NULL,'2020-08-04 16:12:02',NULL,'lauta66@gmail.com','lauta66@gmail.com');

/*Table structure for table `detalle_pedido` */

DROP TABLE IF EXISTS `detalle_pedido`;

CREATE TABLE `detalle_pedido` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cantidad` int(11) DEFAULT NULL,
  `subtotal` double DEFAULT NULL,
  `id_manufacturado` int(11) DEFAULT NULL,
  `id_reventa` int(11) DEFAULT NULL,
  `id_pedido` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6t6fn1c2gh49efha3ln5w9quq` (`id_manufacturado`),
  KEY `FKica0nnjhvohfimsp0p74gmm6j` (`id_reventa`),
  KEY `FK7n9hdifr08joboojejveby1vr` (`id_pedido`),
  CONSTRAINT `FK6t6fn1c2gh49efha3ln5w9quq` FOREIGN KEY (`id_manufacturado`) REFERENCES `articulo_manufacturado` (`id_control`),
  CONSTRAINT `FK7n9hdifr08joboojejveby1vr` FOREIGN KEY (`id_pedido`) REFERENCES `pedido` (`id`),
  CONSTRAINT `FKica0nnjhvohfimsp0p74gmm6j` FOREIGN KEY (`id_reventa`) REFERENCES `articulo_reventa` (`id_control`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8;

/*Data for the table `detalle_pedido` */

insert  into `detalle_pedido`(`id`,`cantidad`,`subtotal`,`id_manufacturado`,`id_reventa`,`id_pedido`) values (2,1,120,NULL,10,9),(3,1,350,15,NULL,10),(7,5,1750,15,NULL,14),(8,1,350,15,NULL,15),(9,2,260,NULL,32,15),(10,1,350,15,NULL,16),(11,1,180,NULL,34,16),(12,1,260,37,NULL,17),(13,1,180,NULL,34,17),(14,1,160,NULL,33,17),(15,1,400,39,NULL,17),(16,1,270,41,NULL,18),(17,1,130,NULL,32,18),(18,1,160,NULL,33,18),(34,1,400,40,NULL,29),(35,1,350,15,NULL,29),(36,1,160,NULL,33,29),(37,1,350,28,NULL,30),(38,1,350,15,NULL,31),(39,1,400,40,NULL,32),(40,1,260,37,NULL,32),(41,1,270,41,NULL,33),(42,1,260,37,NULL,34),(43,1,260,37,NULL,35),(44,1,200,45,NULL,36),(49,1,260,37,NULL,41),(50,1,180,NULL,34,41),(51,1,400,40,NULL,42),(52,1,180,NULL,34,42),(53,1,130,NULL,32,42),(54,1,300,46,NULL,43),(55,20,7000,15,NULL,44),(56,32,11200,15,NULL,45),(57,10,3500,15,NULL,46),(58,1,350,15,NULL,47),(59,2,700,15,NULL,48),(60,1,180,NULL,34,48),(61,2,700,15,NULL,49),(62,1,260,37,NULL,50),(63,1,400,40,NULL,51),(64,1,180,NULL,34,52),(65,1,350,15,NULL,52);

/*Table structure for table `domicilio` */

DROP TABLE IF EXISTS `domicilio`;

CREATE TABLE `domicilio` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `calle` varchar(255) DEFAULT NULL,
  `numero` int(11) DEFAULT NULL,
  `id_localidad` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqrmmkejvimmve55527wv6ujtr` (`id_localidad`),
  CONSTRAINT `FKqrmmkejvimmve55527wv6ujtr` FOREIGN KEY (`id_localidad`) REFERENCES `localidad` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `domicilio` */

insert  into `domicilio`(`id`,`calle`,`numero`,`id_localidad`) values (1,'Adolfo Calle',7608,1),(2,'Patricias mendocinas',223,1),(3,'Patricias Mendocinas',7608,1),(4,'Av. San Martin',2345,1),(5,'calle falsa',123,1);

/*Table structure for table `factura` */

DROP TABLE IF EXISTS `factura`;

CREATE TABLE `factura` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fecha` datetime DEFAULT NULL,
  `forma_pago` varchar(255) DEFAULT NULL,
  `monto_descuento` double DEFAULT NULL,
  `nro_tarjeta` varchar(255) DEFAULT NULL,
  `numero` bigint(20) DEFAULT NULL,
  `total` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

/*Data for the table `factura` */

insert  into `factura`(`id`,`fecha`,`forma_pago`,`monto_descuento`,`nro_tarjeta`,`numero`,`total`) values (1,'2020-06-18 00:00:00','1',100,'1003',12,100),(2,'2020-06-18 00:00:00','1',100,'1003',13,100),(3,'2020-06-18 18:39:30','2',48,'12345678',1592505569636,432),(4,'2020-06-19 18:21:44','0',0,'0',1592590904434,470),(6,'2020-06-24 18:35:06','1',12,'0',1593023706388,108),(7,'2020-06-24 18:40:25','0',0,'0',1593024024806,610),(8,'2020-06-24 19:01:31','1',53,'0',1593025291415,477),(9,'2020-06-25 15:16:22','1',100,'0',1593098182083,900),(10,'2020-06-25 15:21:41','1',56,'0',1593098501261,504),(11,'2020-06-26 18:06:47','0',0,'0',1593194807376,910),(12,'2020-06-27 16:42:56','1',66,'0',1593276175983,594),(13,'2020-06-27 16:45:31','2',35,'121234',1593276330648,315),(14,'2020-06-27 18:03:57','1',27,'0',1593281036855,243),(15,'2020-07-30 22:16:10','0',0,'0',1596147369506,300),(16,'2020-08-02 18:03:37','2',88,'12345678',1596391417360,792),(17,'2020-08-02 22:40:27','2',53,'1234345',1596408026549,477);

/*Table structure for table `localidad` */

DROP TABLE IF EXISTS `localidad`;

CREATE TABLE `localidad` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `denominacion` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `localidad` */

insert  into `localidad`(`id`,`denominacion`) values (1,'Mendoza'),(2,'Cordoba'),(3,'Buenos Aires');

/*Table structure for table `pedido` */

DROP TABLE IF EXISTS `pedido`;

CREATE TABLE `pedido` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `estado` int(11) DEFAULT NULL,
  `fecha` datetime DEFAULT NULL,
  `hora_estimada_fin` datetime DEFAULT NULL,
  `numero` bigint(20) DEFAULT NULL,
  `tipo_envio` int(11) DEFAULT NULL,
  `id_cliente` int(11) DEFAULT NULL,
  `id_factura` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9y4jnyp1hxqa386cnly0ay9uw` (`id_cliente`),
  KEY `FKtxgs8kx26lgcev3un7n520sw` (`id_factura`),
  CONSTRAINT `FK9y4jnyp1hxqa386cnly0ay9uw` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id`),
  CONSTRAINT `FKtxgs8kx26lgcev3un7n520sw` FOREIGN KEY (`id_factura`) REFERENCES `factura` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8;

/*Data for the table `pedido` */

insert  into `pedido`(`id`,`estado`,`fecha`,`hora_estimada_fin`,`numero`,`tipo_envio`,`id_cliente`,`id_factura`) values (9,5,'2020-06-23 15:26:57','2020-06-23 15:26:57',1592926016681,1,8,6),(10,3,'2020-06-23 15:33:37','2020-06-23 15:33:37',1592926417446,2,11,NULL),(14,3,'2020-06-24 14:21:25','2020-06-24 14:21:25',1593008484744,1,8,NULL),(15,5,'2020-06-24 18:40:01','2020-06-24 18:40:01',1593024001008,2,8,7),(16,5,'2020-06-24 19:00:40','2020-06-24 19:00:40',1593025239641,1,8,8),(17,5,'2020-06-25 15:16:08','2020-06-25 15:16:08',1593098167815,1,8,9),(18,5,'2020-06-25 15:21:24','2020-06-25 15:21:24',1593098483969,1,8,10),(29,5,'2020-06-26 18:06:44','2020-06-26 19:51:10',1593194766632,2,8,11),(30,3,'2020-06-26 18:14:46','2020-06-26 19:18:52',1593195284038,1,8,NULL),(31,5,'2020-06-26 18:15:52','2020-06-26 19:38:00',1593195348141,1,8,13),(32,5,'2020-06-27 16:42:54','2020-06-27 18:10:59',1593276171085,1,8,12),(33,5,'2020-06-27 18:01:30','2020-06-27 18:46:58',1593280888125,1,11,14),(34,3,'2020-06-27 19:04:54','2020-06-27 19:58:59',1593284692253,1,8,NULL),(35,3,'2020-06-27 19:46:13','2020-06-27 20:37:40',1593287170931,1,11,NULL),(36,3,'2020-06-29 00:31:29','2020-06-29 01:35:34',1593390678640,1,8,NULL),(41,0,'2020-07-07 18:37:04',NULL,1594147020428,1,11,NULL),(42,0,'2020-07-07 19:02:18',NULL,1594148524349,2,11,NULL),(43,5,'2020-07-30 22:16:04','2020-07-30 23:40:41',1596147344716,2,8,15),(44,0,'2020-07-30 22:30:05',NULL,1596148173760,1,8,NULL),(45,0,'2020-07-30 22:30:21',NULL,1596148211981,1,8,NULL),(46,0,'2020-07-31 14:19:30',NULL,1596205165493,1,8,NULL),(47,3,'2020-07-31 14:39:16','2020-07-31 15:29:22',1596206354658,1,8,NULL),(48,5,'2020-08-02 18:02:18','2020-08-02 19:22:28',1596391323921,1,8,16),(49,3,'2020-08-02 18:15:08','2020-08-02 19:35:14',1596392105862,1,8,NULL),(50,3,'2020-08-02 18:21:25','2020-08-02 18:56:01',1596392482894,1,11,NULL),(51,1,'2020-08-02 18:25:44',NULL,1596392742308,1,11,NULL),(52,5,'2020-08-02 22:40:25','2020-08-02 23:20:33',1596408019029,1,8,17);

/*Table structure for table `rol` */

DROP TABLE IF EXISTS `rol`;

CREATE TABLE `rol` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `rol` */

insert  into `rol`(`id`,`descripcion`) values (1,'cliente'),(2,'cocinero'),(3,'admin'),(4,'cajero');

/*Table structure for table `rubro_articulo` */

DROP TABLE IF EXISTS `rubro_articulo`;

CREATE TABLE `rubro_articulo` (
  `denominacion` varchar(255) DEFAULT NULL,
  `id_control` int(11) NOT NULL,
  `id_rubro` int(11) DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id_control`),
  UNIQUE KEY `id` (`id`),
  KEY `FK4iqvoqldguk98o0819ch5qa3p` (`id_rubro`),
  CONSTRAINT `FK4iqvoqldguk98o0819ch5qa3p` FOREIGN KEY (`id_rubro`) REFERENCES `rubro_articulo` (`id_control`),
  CONSTRAINT `FKhafp1yqt7oyxhj6qtrcb5sh4l` FOREIGN KEY (`id_control`) REFERENCES `control` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Data for the table `rubro_articulo` */

insert  into `rubro_articulo`(`denominacion`,`id_control`,`id_rubro`,`id`) values ('Verduras',2,NULL,1),('Condimento',8,NULL,2),('Lacteo',11,NULL,3),('Carne',12,NULL,4),('Fiambre',14,12,5),('Reventa',23,NULL,6),('Bebida',24,23,7),('Gaseosa',25,24,8),('Cerveza',26,24,9),('Jugo',49,24,10);

/*Table structure for table `rubro_general` */

DROP TABLE IF EXISTS `rubro_general`;

CREATE TABLE `rubro_general` (
  `denominacion` varchar(255) DEFAULT NULL,
  `id_control` int(11) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id_control`),
  UNIQUE KEY `id` (`id`),
  CONSTRAINT `FKq7m5fqpft6ej4o86alyhxu91m` FOREIGN KEY (`id_control`) REFERENCES `control` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `rubro_general` */

insert  into `rubro_general`(`denominacion`,`id_control`,`id`) values ('Pizza',1,1),('Hamburguesa',19,2),('Empanadas',20,3),('Pastas',21,4),('Lomos',22,5);

/*Table structure for table `unidad_medida` */

DROP TABLE IF EXISTS `unidad_medida`;

CREATE TABLE `unidad_medida` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `denominacion` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `unidad_medida` */

insert  into `unidad_medida`(`id`,`denominacion`) values (1,'Gramos'),(2,'Kilos'),(3,'Litros'),(4,'Mililitros'),(5,'Unidad');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
