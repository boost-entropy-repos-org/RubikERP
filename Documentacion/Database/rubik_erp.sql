/*
SQLyog Ultimate v9.10 
MySQL - 5.5.62 : Database - rubik_erp
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`rubik_erp` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `rubik_erp`;

/*Table structure for table `empleado` */

DROP TABLE IF EXISTS `empleado`;

CREATE TABLE `empleado` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `fecha_elaboracion` datetime DEFAULT NULL,
  `fecha_modificacion` datetime DEFAULT NULL,
  `empresa_id` int(10) DEFAULT NULL,
  `empresa` varchar(100) DEFAULT NULL,
  `unidad_id` int(10) DEFAULT NULL,
  `unidad` varchar(100) DEFAULT NULL,
  `usuario_id` int(10) DEFAULT NULL,
  `usuario` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `activo` tinyint(1) DEFAULT '1',
  `clave_empleado` varchar(100) DEFAULT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  `apellido_paterno` varchar(100) DEFAULT NULL,
  `apellido_materno` varchar(100) DEFAULT NULL,
  `nss` varchar(100) DEFAULT NULL,
  `domicilio` varchar(100) DEFAULT NULL,
  `colonia` varchar(100) DEFAULT NULL,
  `cp` int(10) DEFAULT NULL,
  `ciudad` varchar(100) DEFAULT NULL,
  `estado` varchar(100) DEFAULT NULL,
  `pais` varchar(100) DEFAULT NULL,
  `telefono_empresa` varchar(100) DEFAULT NULL,
  `telefono_personal` varchar(100) DEFAULT NULL,
  `email_empresa` varchar(100) DEFAULT NULL,
  `email_personal` varchar(100) DEFAULT NULL,
  `fecha_ingreso` datetime DEFAULT NULL,
  `autorizador` tinyint(1) DEFAULT '1',
  `nombre_completo` varchar(100) DEFAULT NULL,
  `departamento_id` int(10) DEFAULT NULL,
  `departamento` varchar(100) DEFAULT NULL,
  `puesto_id` int(10) DEFAULT NULL,
  `puesto` varchar(100) DEFAULT NULL,
  `clasificacion_puesto` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `empleado` */

insert  into `empleado`(`id`,`fecha_elaboracion`,`fecha_modificacion`,`empresa_id`,`empresa`,`unidad_id`,`unidad`,`usuario_id`,`usuario`,`password`,`activo`,`clave_empleado`,`nombre`,`apellido_paterno`,`apellido_materno`,`nss`,`domicilio`,`colonia`,`cp`,`ciudad`,`estado`,`pais`,`telefono_empresa`,`telefono_personal`,`email_empresa`,`email_personal`,`fecha_ingreso`,`autorizador`,`nombre_completo`,`departamento_id`,`departamento`,`puesto_id`,`puesto`,`clasificacion_puesto`) values (1,'2020-01-12 00:00:00','2020-01-12 00:00:00',1,' ',1,' ',1,'a','a',1,'100001','Pablo','Benavides','Molina',' ','Loma Bonita 206 - 4','Jesus Elias Piña III',89365,'Tampico','Tamaulipas','Mexico','8331243823','8331243823','pblo.benavides@gmail.com','pblo.benavides@gmail.com',NULL,1,'PABLO BENAVIDES MOLINA',0,'SUPER ADMIN',0,NULL,NULL);

/*Table structure for table `producto` */

DROP TABLE IF EXISTS `producto`;

CREATE TABLE `producto` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `fecha_elaboracion` datetime DEFAULT NULL,
  `fecha_modificacion` datetime DEFAULT NULL,
  `empresa_id` int(10) DEFAULT NULL,
  `empresa` varchar(100) DEFAULT NULL,
  `unidad_id` int(10) DEFAULT NULL,
  `unidad` varchar(100) DEFAULT NULL,
  `usuario_id` int(10) DEFAULT NULL,
  `usuario` varchar(100) DEFAULT NULL,
  `activo` tinyint(1) DEFAULT '1',
  `codigo_interno` varchar(100) DEFAULT NULL,
  `descripcion_corta` varchar(100) DEFAULT NULL,
  `descripcion` text,
  `modelo` varchar(100) DEFAULT NULL,
  `no_parte` varchar(100) DEFAULT NULL,
  `no_serie` varchar(100) DEFAULT NULL,
  `marca` varchar(100) DEFAULT NULL,
  `unidad_medida` varchar(100) DEFAULT NULL,
  `tipo_producto` varchar(100) DEFAULT NULL,
  `sub_tipo_producto` varchar(100) DEFAULT NULL,
  `clasificacion` varchar(100) DEFAULT NULL,
  `inventariable` tinyint(1) DEFAULT '1',
  `inventario_actual` int(10) DEFAULT NULL,
  `inventario_maximo` int(10) DEFAULT NULL,
  `inventario_minimo` int(10) DEFAULT NULL,
  `porc_iva` double DEFAULT NULL,
  `precio_compra` double DEFAULT NULL,
  `iva_compra` double DEFAULT NULL,
  `precio_venta` double DEFAULT NULL,
  `iva_venta` double DEFAULT NULL,
  `porc_descuento` double DEFAULT NULL,
  `descuento_venta` double DEFAULT NULL,
  `porc_utilidad` double DEFAULT NULL,
  `proveedor_id_1` int(10) DEFAULT NULL,
  `proveedor_1` varchar(100) DEFAULT NULL,
  `proveedor_id_2` int(10) DEFAULT NULL,
  `proveedor_2` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `producto` */

/*Table structure for table `proveedor` */

DROP TABLE IF EXISTS `proveedor`;

CREATE TABLE `proveedor` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `fecha_elaboracion` datetime DEFAULT NULL,
  `fecha_modificacion` datetime DEFAULT NULL,
  `empresa_id` int(10) DEFAULT NULL,
  `empresa` varchar(100) DEFAULT NULL,
  `unidad_id` int(10) DEFAULT NULL,
  `unidad` varchar(100) DEFAULT NULL,
  `usuario_id` int(10) DEFAULT NULL,
  `usuario` varchar(100) DEFAULT NULL,
  `no_paper` tinyint(1) DEFAULT '1',
  `activo` tinyint(1) DEFAULT '1',
  `clasificacion_proveedor` varchar(100) DEFAULT NULL,
  `tipo_proveedor` varchar(100) DEFAULT NULL,
  `dias_credito` int(10) DEFAULT NULL,
  `clave_proveedor` varchar(100) DEFAULT NULL,
  `razon_social` varchar(250) DEFAULT NULL,
  `rfc` varchar(100) DEFAULT NULL,
  `domicilio` varchar(250) DEFAULT NULL,
  `ciudad` varchar(100) DEFAULT NULL,
  `estado` varchar(100) DEFAULT NULL,
  `pais` varchar(100) DEFAULT NULL,
  `cp` varchar(100) DEFAULT NULL,
  `contacto_compra_telefono` varchar(100) DEFAULT NULL,
  `contacto_compra_nombre` varchar(100) DEFAULT NULL,
  `contacto_compra_email` varchar(100) DEFAULT NULL,
  `contacto_contabilidad_telefono` varchar(100) DEFAULT NULL,
  `contacto_contabilidad_nombre` varchar(100) DEFAULT NULL,
  `contacto_contabilidad_email` varchar(100) DEFAULT NULL,
  `no_cuenta_1` varchar(100) DEFAULT NULL,
  `clave_interbancaria_1` varchar(100) DEFAULT NULL,
  `banco_1` varchar(100) DEFAULT NULL,
  `sucursal_1` varchar(100) DEFAULT NULL,
  `no_cuenta_2` varchar(100) DEFAULT NULL,
  `clave_interbancaria_2` varchar(100) DEFAULT NULL,
  `banco_2` varchar(100) DEFAULT NULL,
  `sucursal_2` varchar(100) DEFAULT NULL,
  `no_cuenta_3` varchar(100) DEFAULT NULL,
  `clave_interbancaria_3` varchar(100) DEFAULT NULL,
  `banco_3` varchar(100) DEFAULT NULL,
  `sucursal_3` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `proveedor` */

insert  into `proveedor`(`id`,`fecha_elaboracion`,`fecha_modificacion`,`empresa_id`,`empresa`,`unidad_id`,`unidad`,`usuario_id`,`usuario`,`no_paper`,`activo`,`clasificacion_proveedor`,`tipo_proveedor`,`dias_credito`,`clave_proveedor`,`razon_social`,`rfc`,`domicilio`,`ciudad`,`estado`,`pais`,`cp`,`contacto_compra_telefono`,`contacto_compra_nombre`,`contacto_compra_email`,`contacto_contabilidad_telefono`,`contacto_contabilidad_nombre`,`contacto_contabilidad_email`,`no_cuenta_1`,`clave_interbancaria_1`,`banco_1`,`sucursal_1`,`no_cuenta_2`,`clave_interbancaria_2`,`banco_2`,`sucursal_2`,`no_cuenta_3`,`clave_interbancaria_3`,`banco_3`,`sucursal_3`) values (1,NULL,'2020-01-12 22:07:22',1,' ',1,' ',1,'PABLO BENAVIDES MOLINA',1,1,'PROVEEDOR','CONTADO',0,'1','PABLO BENAVIDES MOLINA','BEMP8905273G1','LOMA BONITA 206-4 COL. JESUS ELIAS PIÑA III','TAMPICO','TAMAULIPAS','MEXICO','89365','8331243823','Fulanito de tal','pblo.benavides@gmail.com','','','','','','','','','','','','','','','');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
