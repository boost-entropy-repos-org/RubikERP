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

/*Table structure for table `cliente` */

DROP TABLE IF EXISTS `cliente`;

CREATE TABLE `cliente` (
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
  `clave_cliente` varchar(100) DEFAULT NULL,
  `razon_social` varchar(100) DEFAULT NULL,
  `nombre_corto` varchar(100) DEFAULT NULL,
  `rfc` varchar(100) DEFAULT NULL,
  `domicilio` varchar(100) DEFAULT NULL,
  `ciudad` varchar(100) DEFAULT NULL,
  `estado` varchar(100) DEFAULT NULL,
  `cp` varchar(100) DEFAULT NULL,
  `pais` varchar(100) DEFAULT NULL,
  `tipo_cliente` varchar(100) DEFAULT NULL,
  `dias_credito` int(10) DEFAULT NULL,
  `limite_credito` double DEFAULT NULL,
  `saldo_a_favor` double DEFAULT NULL,
  `saldo_pendiente` double DEFAULT NULL,
  `no_contable` varchar(100) DEFAULT NULL,
  `contacto_general_nombre` varchar(100) DEFAULT NULL,
  `contacto_general_email` varchar(100) DEFAULT NULL,
  `contacto_telefono_general` varchar(100) DEFAULT NULL,
  `contacto_venta_nombre` varchar(100) DEFAULT NULL,
  `contacto_venta_email` varchar(100) DEFAULT NULL,
  `contacto_venta_telefono` varchar(100) DEFAULT NULL,
  `contacto_contabilidad_nombre` varchar(100) DEFAULT NULL,
  `contacto_contabilidad_email` varchar(100) DEFAULT NULL,
  `contacto_contabilidad_telefono` varchar(100) DEFAULT NULL,
  `matriz` tinyint(1) DEFAULT '1',
  `sucursal` tinyint(1) DEFAULT '1',
  `id_matriz` int(10) DEFAULT NULL,
  `cuenta_clientes` varchar(100) DEFAULT NULL,
  `cuenta_pagos` varchar(100) DEFAULT NULL,
  `cuenta_anticipos` varchar(100) DEFAULT NULL,
  `cuenta_honorarios` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `cliente` */

/*Table structure for table `configuracion` */

DROP TABLE IF EXISTS `configuracion`;

CREATE TABLE `configuracion` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `serie_remision` varchar(5) DEFAULT NULL,
  `folio_remision` int(10) DEFAULT '0',
  `serie_orden_compra` varchar(5) DEFAULT NULL,
  `folio_orden_compra` int(10) DEFAULT '0',
  `serie_cotizacion` varchar(5) DEFAULT NULL,
  `folio_cotizacion` int(10) DEFAULT '0',
  `serie_factura` varchar(5) DEFAULT NULL,
  `folio_factura` int(10) DEFAULT '0',
  `autocompletar_totales` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `configuracion` */

insert  into `configuracion`(`id`,`serie_remision`,`folio_remision`,`serie_orden_compra`,`folio_orden_compra`,`serie_cotizacion`,`folio_cotizacion`,`serie_factura`,`folio_factura`,`autocompletar_totales`) values (1,'RM',3,'OC',0,'CT',0,'FA',0,0);

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
  `departamento_id` int(10) DEFAULT NULL,
  `departamento` varchar(100) DEFAULT NULL,
  `puesto_id` int(10) DEFAULT NULL,
  `puesto` varchar(100) DEFAULT NULL,
  `clasificacion_puesto` varchar(100) DEFAULT NULL,
  `url_firma` varchar(120) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `empleado` */

insert  into `empleado`(`id`,`fecha_elaboracion`,`fecha_modificacion`,`empresa_id`,`empresa`,`unidad_id`,`unidad`,`usuario_id`,`usuario`,`password`,`activo`,`clave_empleado`,`nombre`,`apellido_paterno`,`apellido_materno`,`nss`,`domicilio`,`colonia`,`cp`,`ciudad`,`estado`,`pais`,`telefono_empresa`,`telefono_personal`,`email_empresa`,`email_personal`,`fecha_ingreso`,`autorizador`,`departamento_id`,`departamento`,`puesto_id`,`puesto`,`clasificacion_puesto`,`url_firma`) values (1,'2020-01-12 00:00:00','2020-01-12 00:00:00',1,' ',1,' ',1,'a','a',1,'100001','Pablo','Benavides','Molina',' ','Loma Bonita 206 - 4','Jesus Elias Piña III',89365,'Tampico','Tamaulipas','Mexico','8331243823','8331243823','pblo.benavides@gmail.com','pblo.benavides@gmail.com',NULL,1,0,'SUPER ADMIN',0,NULL,NULL,NULL),(2,'2020-01-12 00:00:00','2020-01-12 00:00:00',1,'',1,'',1,'','',1,'','Claudia Ibeth','Osornio','Cruz','','Loma Bonita 206 - 4','Jesus Elias Piña III',89365,'Tampico','Tamaulipas','Mexico','8331243823','8331243823','pblo.benavides@gmail.com','pblo.benavides@gmail.com',NULL,1,0,'',0,NULL,NULL,NULL),(3,'2020-01-12 00:00:00','2020-01-12 00:00:00',1,'',1,'',1,'','',1,'','Kathia','Benavides ','Molina','','Loma Bonita 206 - 4','Jesus Elias Piña III',89365,'Tampico','Tamaulipas','Mexico','8331243823','8331243823','pblo.benavides@gmail.com','pblo.benavides@gmail.com',NULL,1,0,'',0,NULL,NULL,NULL),(4,'2020-01-12 00:00:00','2020-01-12 00:00:00',1,'',1,'',1,'','',1,'','Lorenzo','Osornio','Cruz','','Loma Bonita 206 - 4','Jesus Elias Piña III',89365,'Tampico','Tamaulipas','Mexico','8331243823','8331243823','pblo.benavides@gmail.com','pblo.benavides@gmail.com',NULL,0,0,'',0,NULL,NULL,NULL),(5,'2020-01-12 00:00:00','2020-01-12 00:00:00',1,'',1,'',1,'','',1,'','Juan  Antonio','Hernandez','Martinez','','Loma Bonita 206 - 4','Jesus Elias Piña III',89365,'Tampico','Tamaulipas','Mexico','8331243823','8331243823','pblo.benavides@gmail.com','pblo.benavides@gmail.com',NULL,0,0,'',0,NULL,NULL,NULL);

/*Table structure for table `orden_compra` */

DROP TABLE IF EXISTS `orden_compra`;

CREATE TABLE `orden_compra` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `fecha_elaboracion` datetime DEFAULT NULL,
  `fecha_modificacion` datetime DEFAULT NULL,
  `empresa_id` int(10) DEFAULT NULL,
  `empresa` varchar(100) DEFAULT NULL,
  `unidad_id` int(10) DEFAULT NULL,
  `unidad` varchar(100) DEFAULT NULL,
  `usuario_id` int(10) DEFAULT NULL,
  `usuario` varchar(100) DEFAULT NULL,
  `estado_doc` varchar(100) DEFAULT NULL,
  `estado` varchar(100) DEFAULT NULL,
  `folio` varchar(100) DEFAULT NULL,
  `serie` varchar(100) DEFAULT NULL,
  `observaciones` varchar(100) DEFAULT NULL,
  `tipo_documento` varchar(100) DEFAULT NULL,
  `tipo_archivo` varchar(100) DEFAULT NULL,
  `razon_cancelar` varchar(100) DEFAULT NULL,
  `cliente_id` int(10) DEFAULT NULL,
  `cliente` varchar(100) DEFAULT NULL,
  `cliente_rfc` varchar(100) DEFAULT NULL,
  `metodo_pago` varchar(100) DEFAULT NULL,
  `moneda` varchar(100) DEFAULT NULL,
  `tipo_cambio` double DEFAULT NULL,
  `importe` double DEFAULT NULL,
  `descuento` double DEFAULT NULL,
  `subtotal` double DEFAULT NULL,
  `iva` double DEFAULT NULL,
  `total` double DEFAULT NULL,
  `porc_iva` int(10) DEFAULT NULL,
  `importe_letra` varchar(100) DEFAULT NULL,
  `activo` tinyint(1) DEFAULT '1',
  `proveedor_id` int(10) DEFAULT NULL,
  `proveedor` varchar(100) DEFAULT NULL,
  `cond_pago` varchar(100) DEFAULT NULL,
  `remision_id` int(10) DEFAULT NULL,
  `folio_remision` varchar(100) DEFAULT NULL,
  `solicita_id` int(10) DEFAULT NULL,
  `solicita` varchar(100) DEFAULT NULL,
  `recibe_id` int(10) DEFAULT NULL,
  `recibe` varchar(100) DEFAULT NULL,
  `autoriza_id` int(10) DEFAULT NULL,
  `autoriza` varchar(100) DEFAULT NULL,
  `fecha_entrega` datetime DEFAULT NULL,
  `direccion_entrega` varchar(100) DEFAULT NULL,
  `fecha_requisicion` datetime DEFAULT NULL,
  `factura_id` int(10) DEFAULT NULL,
  `factura` varchar(100) DEFAULT NULL,
  `folio_factura` varchar(100) DEFAULT NULL,
  `no_cuenta` varchar(100) DEFAULT NULL,
  `interbancaria` varchar(100) DEFAULT NULL,
  `banco` varchar(100) DEFAULT NULL,
  `sucursal` varchar(100) DEFAULT NULL,
  `concepto_pago` varchar(100) DEFAULT NULL,
  `fecha_autoriza` datetime DEFAULT NULL,
  `firma_autoriza` varchar(100) DEFAULT NULL,
  `forma_pago` varchar(100) DEFAULT NULL,
  `fecha_solicita` datetime DEFAULT NULL,
  `firma_solicita` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `orden_compra` */

/*Table structure for table `orden_compra_det` */

DROP TABLE IF EXISTS `orden_compra_det`;

CREATE TABLE `orden_compra_det` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `folio` varchar(100) DEFAULT NULL,
  `documento_id` int(10) DEFAULT NULL,
  `empresa_id` int(10) DEFAULT NULL,
  `empresa` varchar(100) DEFAULT NULL,
  `unidad_id` int(10) DEFAULT NULL,
  `unidad` varchar(100) DEFAULT NULL,
  `usuario_id` int(10) DEFAULT NULL,
  `usuario` varchar(100) DEFAULT NULL,
  `no_partida` int(10) DEFAULT NULL,
  `fecha_alta` datetime DEFAULT NULL,
  `cantidad` int(10) DEFAULT NULL,
  `producto_id` int(10) DEFAULT NULL,
  `descripcion` varchar(100) DEFAULT NULL,
  `unidad_medida` varchar(100) DEFAULT NULL,
  `porc_iva` int(2) DEFAULT NULL,
  `precio_unitario` double DEFAULT NULL,
  `importe` double DEFAULT NULL,
  `descuento` double DEFAULT NULL,
  `subtotal` double DEFAULT NULL,
  `iva` double DEFAULT NULL,
  `total` double DEFAULT NULL,
  `servicio` tinyint(1) DEFAULT '1',
  `folio_remision` varchar(100) DEFAULT NULL,
  `remision_id` int(10) DEFAULT NULL,
  `no_parte` varchar(100) DEFAULT NULL,
  `no_serie` varchar(100) DEFAULT NULL,
  `modelo` varchar(100) DEFAULT NULL,
  `marca` varchar(100) DEFAULT NULL,
  `codigo_proveedor` varchar(100) DEFAULT NULL,
  `codigo_interno` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `orden_compra_det` */

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
  `porc_iva` int(11) DEFAULT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Data for the table `producto` */

insert  into `producto`(`id`,`fecha_elaboracion`,`fecha_modificacion`,`empresa_id`,`empresa`,`unidad_id`,`unidad`,`usuario_id`,`usuario`,`activo`,`codigo_interno`,`descripcion_corta`,`descripcion`,`modelo`,`no_parte`,`no_serie`,`marca`,`unidad_medida`,`tipo_producto`,`sub_tipo_producto`,`clasificacion`,`inventariable`,`inventario_actual`,`inventario_maximo`,`inventario_minimo`,`porc_iva`,`precio_compra`,`iva_compra`,`precio_venta`,`iva_venta`,`porc_descuento`,`descuento_venta`,`porc_utilidad`,`proveedor_id_1`,`proveedor_1`,`proveedor_id_2`,`proveedor_2`) values (1,'2020-02-20 10:27:16','2020-02-20 10:27:16',0,'',0,'',0,'',1,'1','PRODUCTO 1','PRODUCTO 1','23234','s44sfww3','A34Q34Q3-Q33','MARCA UNO','PIEZA','','','PRODUCTO',1,0,10,2,16,116,16,200,27.59,0,0,0,2,'PROVEEDOR 2',3,'PROVEEDOR 3'),(2,'2020-02-20 10:27:16','2020-02-20 10:27:16',0,'',0,'',0,'',1,'2','PRODUCTO 2','PRODUCTO 2','235','280er65','144545-4545','MARCA DOS','PIEZA','','','PRODUCTO',1,3,10,2,16,232,32,300,41.38,0,0,0,1,'PROVEEDOR 1',2,'PROVEEDOR 2'),(3,'2020-02-20 10:27:16','2020-02-20 10:27:16',0,'',0,'',0,'',1,'3','PRODUCTO 3','PRODUCTO 3','2214','508498','erwe2rwe21r-4','MARCA UNO','PIEZA','','','PRODUCTO',1,5,20,5,16,1160,160,1500,206.9,0,0,0,2,'PROVEEDOR 2',3,'PROVEEDOR 3'),(4,'2020-02-20 10:27:16','2020-02-20 10:27:16',0,'',0,'',0,'',1,'4','PRODUCTO 4','PRODUCTO 4','22698','201806','545-454sr25','MARCA TRES','PIEZA','','','PRODUCTO',1,6,25,2,16,11.6,1.6,15,2.07,0,0,0,3,'PROVEEDOR 3',1,'PROVEEDOR 1'),(5,'2020-02-20 10:27:16','2020-02-20 10:27:16',0,'',0,'',0,'',1,'5','PRODUCTO 5','PRODUCTO 5','2248','8084895','554-a45485e-e','MARCA TRES','PIEZA','','','PRODUCTO',1,8,20,5,16,500,68.97,600,82.76,0,0,0,1,'PROVEEDOR 1',2,'PROVEEDOR 2'),(6,'2020-02-20 10:27:16','2020-02-20 10:27:16',0,'',0,'',0,'',1,'6','PRODUCTO 6','PRODUCTO 6','21585','925239','5454-e','MARCA DOS','PIEZA','','','PRODUCTO',1,2,30,3,16,600,82.76,800,110.34,0,0,0,2,'PROVEEDOR 2',3,'PROVEEDOR 3'),(7,'2020-02-20 10:27:16','2020-02-20 10:27:16',0,'',0,'',0,'',1,'7','PRODUCTO 7','PRODUCTO 7','24789','8484532','545e-454','MARCA UNO','PIEZA','','','PRODUCTO',1,0,10,5,16,800,110.34,1200,165.52,0,0,0,3,'PROVEEDOR 3',1,'PROVEEDOR 1'),(8,'2020-02-20 10:27:16','2020-02-20 10:27:16',0,'',0,'',0,'',1,'8','PRODUCTO 8','PRODUCTO 8','98556','54548','54592-er5','MARCA TRES','PIEZA','','','PRODUCTO',1,0,5,2,16,300,41.38,360,49.66,0,0,0,1,'PROVEEDOR 1',2,'PROVEEDOR 2'),(9,'2020-02-20 10:27:16','2020-02-20 10:27:16',0,'',0,'',0,'',1,'9','PRODUCTO 9','PRODUCTO 9','36579','545455','54er4-e','MARCA DOS','PIEZA','','','PRODUCTO',1,3,8,2,16,150,20.69,250,34.48,0,0,0,2,'PROVEEDOR 2',3,'PROVEEDOR 3'),(10,'2020-02-20 10:27:16','2020-02-20 10:27:16',0,'',0,'',0,'',1,'10','MANTENIMIENTO','MANTENIMIENTO PREVENTIVO DE MAQUINARIA, MOTOR Y ENGRANES','NA','NA','NA','NA','SERVICIO','','','PRODUCTO',1,9,12,5,16,80,11.03,110,15.17,0,0,0,3,'PROVEEDOR 3',1,'PROVEEDOR 1');

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `proveedor` */

insert  into `proveedor`(`id`,`fecha_elaboracion`,`fecha_modificacion`,`empresa_id`,`empresa`,`unidad_id`,`unidad`,`usuario_id`,`usuario`,`no_paper`,`activo`,`clasificacion_proveedor`,`tipo_proveedor`,`dias_credito`,`clave_proveedor`,`razon_social`,`rfc`,`domicilio`,`ciudad`,`estado`,`pais`,`cp`,`contacto_compra_telefono`,`contacto_compra_nombre`,`contacto_compra_email`,`contacto_contabilidad_telefono`,`contacto_contabilidad_nombre`,`contacto_contabilidad_email`,`no_cuenta_1`,`clave_interbancaria_1`,`banco_1`,`sucursal_1`,`no_cuenta_2`,`clave_interbancaria_2`,`banco_2`,`sucursal_2`,`no_cuenta_3`,`clave_interbancaria_3`,`banco_3`,`sucursal_3`) values (1,'2020-01-12 22:07:22','2020-01-12 22:07:22',1,' ',1,' ',1,'PABLO BENAVIDES MOLINA',1,1,'PROVEEDOR','CONTADO',0,'1','PABLO BENAVIDES MOLINA','BEMP8905273G1','LOMA BONITA 206-4 COL. JESUS ELIAS PIÑA III','TAMPICO','TAMAULIPAS','MEXICO','89365','8331243823','Fulanito de tal','pblo.benavides@gmail.com','','','','','','','','','','','','','','',''),(2,'2020-01-12 22:07:22','2020-01-12 22:07:22',1,' ',1,' ',1,'PABLO BENAVIDES MOLINA',1,1,'PROVEEDOR','CREDITO',15,'2','PROVEEDOR 2','ASDASDASDASDA','SDASDASDAS','DASDAS','DASDASD','MEXICO','23232','asdasd','aasdasdasd','asdasda','asdasd','asdasdas','dasdasd','asdasda','sdasd','asdasdas','dasdasd','asdasda','sdasdasdas','dasd','','','','',''),(3,'2020-01-30 22:27:03','2020-01-30 22:27:03',0,'',0,'',0,'',1,1,'PROVEEDOR','CONTADO',0,'3','PROVEEDOR 3','PROV323423244','2424','24E','RTYYRTYU','MEXICO','y6666','tyrty','fghrtrty','rtyr','rtyrty','rtyrt','yrty','jkljkl','jkljk','ljklj','kl','','','','','','','','');

/*Table structure for table `remision` */

DROP TABLE IF EXISTS `remision`;

CREATE TABLE `remision` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `fecha_elaboracion` datetime DEFAULT NULL,
  `fecha_modificacion` datetime DEFAULT NULL,
  `empresa_id` int(10) DEFAULT NULL,
  `empresa` varchar(100) DEFAULT NULL,
  `unidad_id` int(10) DEFAULT NULL,
  `unidad` varchar(100) DEFAULT NULL,
  `usuario_id` int(10) DEFAULT NULL,
  `usuario` varchar(100) DEFAULT NULL,
  `estado_doc` varchar(100) DEFAULT NULL,
  `estado` varchar(100) DEFAULT NULL,
  `folio` varchar(100) DEFAULT NULL,
  `serie` varchar(100) DEFAULT NULL,
  `observaciones` text,
  `tipo_documento` varchar(100) DEFAULT NULL,
  `tipo_archivo` varchar(100) DEFAULT NULL,
  `razon_cancelar` varchar(100) DEFAULT NULL,
  `cliente_id` int(10) DEFAULT NULL,
  `cliente` varchar(100) DEFAULT NULL,
  `cliente_rfc` varchar(100) DEFAULT NULL,
  `metodo_pago` varchar(100) DEFAULT NULL,
  `moneda` varchar(100) DEFAULT NULL,
  `tipo_cambio` double DEFAULT NULL,
  `importe` double DEFAULT NULL,
  `descuento` double DEFAULT NULL,
  `subtotal` double DEFAULT NULL,
  `iva` double DEFAULT NULL,
  `total` double DEFAULT NULL,
  `porc_iva` int(10) DEFAULT NULL,
  `importe_letra` varchar(100) DEFAULT NULL,
  `activo` tinyint(1) DEFAULT '1',
  `prioridad` varchar(100) DEFAULT NULL,
  `fecha_requerida` datetime DEFAULT NULL,
  `direccion_entrega` varchar(100) DEFAULT NULL,
  `fecha_orden_compra` datetime DEFAULT NULL,
  `folio_orden_compra` varchar(100) DEFAULT NULL,
  `autoriza_id` int(10) DEFAULT NULL,
  `autoriza` varchar(100) DEFAULT NULL,
  `fecha_autorizo` datetime DEFAULT NULL,
  `firma_autorizo` varchar(100) DEFAULT NULL,
  `solicita` varchar(100) DEFAULT NULL,
  `firma_solicita` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `remision` */

insert  into `remision`(`id`,`fecha_elaboracion`,`fecha_modificacion`,`empresa_id`,`empresa`,`unidad_id`,`unidad`,`usuario_id`,`usuario`,`estado_doc`,`estado`,`folio`,`serie`,`observaciones`,`tipo_documento`,`tipo_archivo`,`razon_cancelar`,`cliente_id`,`cliente`,`cliente_rfc`,`metodo_pago`,`moneda`,`tipo_cambio`,`importe`,`descuento`,`subtotal`,`iva`,`total`,`porc_iva`,`importe_letra`,`activo`,`prioridad`,`fecha_requerida`,`direccion_entrega`,`fecha_orden_compra`,`folio_orden_compra`,`autoriza_id`,`autoriza`,`fecha_autorizo`,`firma_autorizo`,`solicita`,`firma_solicita`) values (1,'2020-02-20 11:38:46','2020-03-01 10:51:04',0,'',0,'',1,'PABLO BENAVIDES MOLINA','AUTORIZADO','','RM00002','','ENTREGAR EN UN SOLO PAQUETE','REMISION DE COMPRA','PDF','',0,'','','',NULL,NULL,0,0,0,0,11411.6,0,'',1,'MEDIA','2020-02-24 00:00:00','',NULL,NULL,1,'PABLO BENAVIDES MOLINA','2020-03-01 10:51:04','7D5AAB48CD890BD54F1721EAEA115775','PABLO BENAVIDES MOLINA','7D5AAB48CD890BD54F1721EAEA115775'),(2,'2020-02-20 11:58:26','2020-02-20 12:08:47',0,'',0,'',1,'PABLO BENAVIDES MOLINA','EN PROCESO','','RM00003','','SERVICIO EN PLANTA','REMISION DE COMPRA','PDF','',0,'','','',NULL,NULL,0,0,0,0,80,0,'',1,'ALTA','2020-02-20 00:00:00','',NULL,NULL,1,'PABLO BENAVIDES MOLINA',NULL,NULL,'PABLO BENAVIDES MOLINA',NULL);

/*Table structure for table `remision_det` */

DROP TABLE IF EXISTS `remision_det`;

CREATE TABLE `remision_det` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `folio` varchar(100) DEFAULT NULL,
  `documento_id` int(10) DEFAULT NULL,
  `empresa_id` int(10) DEFAULT NULL,
  `empresa` varchar(100) DEFAULT NULL,
  `unidad_id` int(10) DEFAULT NULL,
  `unidad` varchar(100) DEFAULT NULL,
  `usuario_id` int(10) DEFAULT NULL,
  `usuario` varchar(100) DEFAULT NULL,
  `no_partida` int(10) DEFAULT NULL,
  `fecha_alta` datetime DEFAULT NULL,
  `cantidad` int(10) DEFAULT NULL,
  `producto_id` int(10) DEFAULT NULL,
  `descripcion` varchar(100) DEFAULT NULL,
  `unidad_medida` varchar(100) DEFAULT NULL,
  `precio_unitario` double DEFAULT NULL,
  `importe` double DEFAULT NULL,
  `descuento` double DEFAULT NULL,
  `subtotal` double DEFAULT NULL,
  `iva` double DEFAULT NULL,
  `total` double DEFAULT NULL,
  `servicio` tinyint(1) DEFAULT '1',
  `no_parte` varchar(20) DEFAULT NULL,
  `no_serie` varchar(20) DEFAULT NULL,
  `modelo` varchar(20) DEFAULT NULL,
  `marca` varchar(50) DEFAULT NULL,
  `codigo_interno` varchar(10) DEFAULT NULL,
  `codigo_proveedor` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `remision_det` */

insert  into `remision_det`(`id`,`folio`,`documento_id`,`empresa_id`,`empresa`,`unidad_id`,`unidad`,`usuario_id`,`usuario`,`no_partida`,`fecha_alta`,`cantidad`,`producto_id`,`descripcion`,`unidad_medida`,`precio_unitario`,`importe`,`descuento`,`subtotal`,`iva`,`total`,`servicio`,`no_parte`,`no_serie`,`modelo`,`marca`,`codigo_interno`,`codigo_proveedor`) values (1,'RM00002',1,1,' ',1,' ',1,'PABLO BENAVIDES MOLINA',0,'2020-02-20 11:37:40',1,4,'PRODUCTO 4','PIEZA',11.6,NULL,NULL,NULL,NULL,11.6,0,'201806','545-454sr25','22698','MARCA TRES','4',NULL),(2,'RM00002',1,1,' ',1,' ',1,'PABLO BENAVIDES MOLINA',0,'2020-02-20 11:37:56',10,5,'PRODUCTO 5','PIEZA',500,NULL,NULL,NULL,NULL,5000,0,'8084895','554-a45485e-e','2248','MARCA TRES','5',NULL),(3,'RM00002',1,1,' ',1,' ',1,'PABLO BENAVIDES MOLINA',0,'2020-02-20 11:38:13',8,7,'PRODUCTO 7','PIEZA',800,NULL,NULL,NULL,NULL,6400,0,'8484532','545e-454','24789','MARCA UNO','7',NULL),(4,'RM00003',2,1,' ',1,' ',1,'PABLO BENAVIDES MOLINA',0,'2020-02-20 11:58:10',1,10,'MANTENIMIENTO PREVENTIVO DE MAQUINARIA, MOTOR Y ENGRANES','SERVICIO',80,NULL,NULL,NULL,NULL,80,0,'NA','NA','NA','NA','10',NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
