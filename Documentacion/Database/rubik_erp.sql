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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `cliente` */

insert  into `cliente`(`id`,`fecha_elaboracion`,`fecha_modificacion`,`empresa_id`,`empresa`,`unidad_id`,`unidad`,`usuario_id`,`usuario`,`activo`,`clave_cliente`,`razon_social`,`nombre_corto`,`rfc`,`domicilio`,`ciudad`,`estado`,`cp`,`pais`,`tipo_cliente`,`dias_credito`,`limite_credito`,`saldo_a_favor`,`saldo_pendiente`,`no_contable`,`contacto_general_nombre`,`contacto_general_email`,`contacto_telefono_general`,`contacto_venta_nombre`,`contacto_venta_email`,`contacto_venta_telefono`,`contacto_contabilidad_nombre`,`contacto_contabilidad_email`,`contacto_contabilidad_telefono`,`matriz`,`sucursal`,`id_matriz`,`cuenta_clientes`,`cuenta_pagos`,`cuenta_anticipos`,`cuenta_honorarios`) values (1,NULL,NULL,0,'',0,'',2,'LUIS HUMBERTO GOMEZ TIBURCIO',1,'1','CLIENTE 1','','BEMP8905273G1','CALLE','CIUDAD','ESTADO','89365','MEXICO','CREDITO',60,50000,0,0,'','contacto cliente 1','cliente1@cliente.com','8331223344','contacto venta cliente 1','ventacliente1@cliente.com','8331223344','','','',0,0,0,'','','',''),(2,NULL,NULL,0,'',0,'',2,'LUIS HUMBERTO GOMEZ TIBURCIO',1,'2','CLIENTE 2','','BEMP8905273G1','CALLE','CIUDAD','ESTADO','89365','MEXICO','CREDITO',30,5000,0,0,'','contacto cliente 2','cliente2@cliente.com','8331223344','contacto venta cliente 2','ventacliente2@cliente.com','8331223344','','','',0,0,0,'','','',''),(3,NULL,NULL,0,'',0,'',2,'LUIS HUMBERTO GOMEZ TIBURCIO',1,'3','CLIENTE 3','','BEMP8905273G1','CALLE','CIUDAD','ESTADO','89365','MEXICO','CREDITO',60,10000,0,0,'','contacto cliente 3','cliente3@cliente.com','8331223344','contacto venta cliente 3','ventacliente3@cliente.com','8331223344','','','',0,0,0,'','','','');

/*Table structure for table `configuracion` */

DROP TABLE IF EXISTS `configuracion`;

CREATE TABLE `configuracion` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `serie_requisicion` varchar(5) DEFAULT NULL,
  `folio_requisicion` int(10) DEFAULT '0',
  `serie_orden_compra` varchar(5) DEFAULT NULL,
  `folio_orden_compra` int(10) DEFAULT '0',
  `serie_cotizacion` varchar(5) DEFAULT NULL,
  `folio_cotizacion` int(10) DEFAULT '0',
  `serie_remision` varchar(10) DEFAULT NULL,
  `folio_remision` int(5) DEFAULT NULL,
  `serie_factura` varchar(5) DEFAULT NULL,
  `folio_factura` int(10) DEFAULT '0',
  `autocompletar_totales` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `configuracion` */

insert  into `configuracion`(`id`,`serie_requisicion`,`folio_requisicion`,`serie_orden_compra`,`folio_orden_compra`,`serie_cotizacion`,`folio_cotizacion`,`serie_remision`,`folio_remision`,`serie_factura`,`folio_factura`,`autocompletar_totales`) values (1,'RQ',9,'OC',4,'CT',4,'RM',3,'FA',0,0);

/*Table structure for table `cotizacion_venta` */

DROP TABLE IF EXISTS `cotizacion_venta`;

CREATE TABLE `cotizacion_venta` (
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
  `razon_cancelar` text,
  `cliente_id` int(10) DEFAULT NULL,
  `cliente` varchar(100) DEFAULT NULL,
  `cliente_rfc` varchar(100) DEFAULT NULL,
  `metodo_pago` varchar(100) DEFAULT NULL,
  `moneda` varchar(100) DEFAULT NULL,
  `tipo_cambio` double(8,2) DEFAULT NULL,
  `importe` double(15,2) DEFAULT NULL,
  `descuento` double(10,1) DEFAULT NULL,
  `subtotal` double(15,2) DEFAULT NULL,
  `iva` double(10,1) DEFAULT NULL,
  `total` double(15,2) DEFAULT NULL,
  `porc_iva` int(10) DEFAULT NULL,
  `importe_letra` text,
  `activo` tinyint(1) DEFAULT '1',
  `emisor_rfc` varchar(100) DEFAULT NULL,
  `emisor_nombre` varchar(100) DEFAULT NULL,
  `emisor_calle` varchar(100) DEFAULT NULL,
  `emisor_numero_exterior` varchar(100) DEFAULT NULL,
  `emisor_numero_interior` varchar(100) DEFAULT NULL,
  `emisor_codigo_postal` varchar(100) DEFAULT NULL,
  `emisor_colonia` varchar(100) DEFAULT NULL,
  `emisor_municipio` varchar(100) DEFAULT NULL,
  `emisor_estado` varchar(100) DEFAULT NULL,
  `emisor_pais` varchar(100) DEFAULT NULL,
  `receptor_nombre` varchar(100) DEFAULT NULL,
  `receptor_calle` varchar(100) DEFAULT NULL,
  `receptor_numero_exterior` varchar(100) DEFAULT NULL,
  `receptor_numero_interior` varchar(100) DEFAULT NULL,
  `receptor_codigo_postal` varchar(100) DEFAULT NULL,
  `receptor_colonia` varchar(100) DEFAULT NULL,
  `receptor_municipio` varchar(100) DEFAULT NULL,
  `receptor_estado` varchar(100) DEFAULT NULL,
  `receptor_pais` varchar(100) DEFAULT NULL,
  `vendedor_id` int(10) DEFAULT NULL,
  `vendedor` varchar(100) DEFAULT NULL,
  `dias_caduca` int(10) DEFAULT NULL,
  `condiciones_pago` text,
  `condiciones_cotizacion` text,
  `tiempo_tentrega` varchar(200) DEFAULT NULL,
  `terminada_requisicion` tinyint(1) DEFAULT '0',
  `terminada_no_requisicion` tinyint(1) DEFAULT '0',
  `proyecto_id` int(10) DEFAULT NULL,
  `proyecto` varchar(200) DEFAULT NULL,
  `solicitante` varchar(100) DEFAULT NULL,
  `atencion` varchar(100) DEFAULT NULL,
  `referencia_cliente` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `cotizacion_venta` */

insert  into `cotizacion_venta`(`id`,`fecha_elaboracion`,`fecha_modificacion`,`empresa_id`,`empresa`,`unidad_id`,`unidad`,`usuario_id`,`usuario`,`estado_doc`,`estado`,`folio`,`serie`,`observaciones`,`tipo_documento`,`tipo_archivo`,`razon_cancelar`,`cliente_id`,`cliente`,`cliente_rfc`,`metodo_pago`,`moneda`,`tipo_cambio`,`importe`,`descuento`,`subtotal`,`iva`,`total`,`porc_iva`,`importe_letra`,`activo`,`emisor_rfc`,`emisor_nombre`,`emisor_calle`,`emisor_numero_exterior`,`emisor_numero_interior`,`emisor_codigo_postal`,`emisor_colonia`,`emisor_municipio`,`emisor_estado`,`emisor_pais`,`receptor_nombre`,`receptor_calle`,`receptor_numero_exterior`,`receptor_numero_interior`,`receptor_codigo_postal`,`receptor_colonia`,`receptor_municipio`,`receptor_estado`,`receptor_pais`,`vendedor_id`,`vendedor`,`dias_caduca`,`condiciones_pago`,`condiciones_cotizacion`,`tiempo_tentrega`,`terminada_requisicion`,`terminada_no_requisicion`,`proyecto_id`,`proyecto`,`solicitante`,`atencion`,`referencia_cliente`) values (1,'2020-06-21 22:45:53','2020-06-21 22:45:53',0,'',0,'',2,'LUIS HUMBERTO GOMEZ TIBURCIO','TERMINADO','','CT00003','','IJHGIGKUGKUYGKU','COTIZACION DE VENTA','PDF','',1,'CLIENTE 1','BEMP8905273G1','TRANSFERENCIA','DOLARES',1.00,3900.00,0.0,3900.00,624.0,4524.00,16,'CUATRO MIL QUINIENTOS VEINTE Y CUATRO Y 0/100 PESOS MEXICANOS.',1,'IWS140818UY0','IMPELLER WELL SERVICES, S.A. DE C.V.','Emilio Castán # 203 Col. 2 de Junio',NULL,NULL,'89327',NULL,'Tampico','Tamaulipas','MEXICO','CLIENTE 1','CALLE',NULL,NULL,'89365',NULL,'CIUDAD','ESTADO','MEXICO',NULL,'LUIS HUMBERTO GOMEZ TIBURCIO',30,'CREDITO','HGHGLJHGLJHG','HJBKJBKNM',0,0,2,'PROYECTO IMPELLER2','JHGJHGJHGLGH','LIUHLIJHLKJHLKJHLK','LIGHLHHKJHKJH'),(2,'2020-06-28 21:32:32','2020-06-28 21:32:32',0,'',0,'',2,'LUIS HUMBERTO GOMEZ TIBURCIO','TERMINADO','','CT00004','','ENTREGAR EN ALMACEN UBICADO EN:','COTIZACION DE VENTA','PDF','',1,'CLIENTE 1','BEMP8905273G1','EFECTIVO','PESOS',1.00,800.00,0.0,800.00,128.0,928.00,16,'NOVECIENTOS VEINTE Y OCHO Y 0/100 PESOS MEXICANOS.',1,'IWS140818UY0','IMPELLER WELL SERVICES, S.A. DE C.V.','Emilio Castán # 203 Col. 2 de Junio',NULL,NULL,'89327',NULL,'Tampico','Tamaulipas','MEXICO','CLIENTE 1','CALLE',NULL,NULL,'89365',NULL,'CIUDAD','ESTADO','MEXICO',NULL,'LUIS HUMBERTO GOMEZ TIBURCIO',7,'CONTADO','ENTREGA ANTES DE TRES MESES','ASDASDASDASD',0,0,2,'PROYECTO IMPELLER2','232323232','2323232','2323232');

/*Table structure for table `cotizacion_venta_det` */

DROP TABLE IF EXISTS `cotizacion_venta_det`;

CREATE TABLE `cotizacion_venta_det` (
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
  `descripcion` text,
  `unidad_medida` varchar(100) DEFAULT NULL,
  `porc_iva` int(10) DEFAULT NULL,
  `precio_unitario` double DEFAULT NULL,
  `importe` double DEFAULT NULL,
  `descuento` double DEFAULT NULL,
  `subtotal` double DEFAULT NULL,
  `iva` double DEFAULT NULL,
  `total` double DEFAULT NULL,
  `servicio` tinyint(1) DEFAULT '0',
  `no_parte` varchar(100) DEFAULT NULL,
  `no_serie` varchar(100) DEFAULT NULL,
  `modelo` varchar(100) DEFAULT NULL,
  `marca` varchar(100) DEFAULT NULL,
  `codigo_proveedor` varchar(100) DEFAULT NULL,
  `codigo_interno` varchar(100) DEFAULT NULL,
  `facturada` tinyint(1) DEFAULT '0',
  `entregada` tinyint(1) DEFAULT '0',
  `requisicion_id` tinyint(1) DEFAULT '0',
  `fecha_entrega` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `cotizacion_venta_det` */

insert  into `cotizacion_venta_det`(`id`,`folio`,`documento_id`,`empresa_id`,`empresa`,`unidad_id`,`unidad`,`usuario_id`,`usuario`,`no_partida`,`fecha_alta`,`cantidad`,`producto_id`,`descripcion`,`unidad_medida`,`porc_iva`,`precio_unitario`,`importe`,`descuento`,`subtotal`,`iva`,`total`,`servicio`,`no_parte`,`no_serie`,`modelo`,`marca`,`codigo_proveedor`,`codigo_interno`,`facturada`,`entregada`,`requisicion_id`,`fecha_entrega`) values (1,'CT00003',1,0,'',0,'',2,'LUIS HUMBERTO GOMEZ TIBURCIO',0,'2020-06-21 22:46:41',2,3,'PRODUCTO 3 TREES','PIEZA',NULL,1550,3100,NULL,NULL,NULL,NULL,0,'34343','434','ASDQ3234','343',NULL,'3',1,0,2,'1 semana'),(2,'CT00003',1,0,'',0,'',2,'LUIS HUMBERTO GOMEZ TIBURCIO',0,'2020-06-21 22:47:07',1,2,'PRODUCTO2','PIEZA',NULL,800,800,NULL,NULL,NULL,NULL,0,'343','WR3R3','AW','34T4',NULL,'2',1,0,1,'223456 7777'),(3,'CT00004',2,0,'',0,'',2,'LUIS HUMBERTO GOMEZ TIBURCIO',0,'2020-06-28 21:38:01',1,2,'PRODUCTO2','PIEZA',NULL,800,800,NULL,NULL,NULL,NULL,0,'343','WR3R3','AW','34T4',NULL,'2',1,0,3,'Una Semana');

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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Data for the table `empleado` */

insert  into `empleado`(`id`,`fecha_elaboracion`,`fecha_modificacion`,`empresa_id`,`empresa`,`unidad_id`,`unidad`,`usuario_id`,`usuario`,`password`,`activo`,`clave_empleado`,`nombre`,`apellido_paterno`,`apellido_materno`,`nss`,`domicilio`,`colonia`,`cp`,`ciudad`,`estado`,`pais`,`telefono_empresa`,`telefono_personal`,`email_empresa`,`email_personal`,`fecha_ingreso`,`autorizador`,`departamento_id`,`departamento`,`puesto_id`,`puesto`,`clasificacion_puesto`,`url_firma`) values (1,'2020-05-07 14:31:01','2020-05-07 14:31:01',NULL,NULL,NULL,NULL,NULL,'asequera','asequera',1,NULL,'JESUS ARMANDO','SEQUERA','LOYA',NULL,NULL,NULL,NULL,NULL,'TAMAULIPAS','MEXICO','8333992231','8338503576','asequera@impeller.com.mx',NULL,NULL,1,NULL,'GERENCIA',NULL,'GERENTE GENERAL',NULL,NULL),(2,'2020-05-07 14:31:01','2020-05-07 14:31:01',NULL,NULL,NULL,NULL,NULL,'luis','luis',1,NULL,'LUIS HUMBERTO','GOMEZ','TIBURCIO',NULL,NULL,NULL,NULL,NULL,'TAMAULIPAS','MEXICO','8333992231','8333140906','luis@impeller.com.mx',NULL,NULL,1,NULL,'GERENCIA',NULL,'GERENTE ADMINISTRATIVO',NULL,NULL),(3,'2020-05-07 14:31:01','2020-05-25 11:46:51',0,'',0,'',0,'gabriela@impeller.com.mx','gabriela',1,'00','GABRIELA ALEJANDRA','BARRIOS','REYNA','0','-','',0,'-','TAMAULIPAS','MEXICO','8333992231','8331100147','gabriela@impeller.com.mx','  o','2020-05-25 00:00:00',1,0,'VENTAS',0,'GERENTE DE VENTAS','',NULL),(4,'2020-05-07 14:31:01','2020-05-07 14:31:01',NULL,NULL,NULL,NULL,NULL,'valeria','valeria',1,NULL,'VALERIA ALEJANDRINA','ROSARIO','JIMENEZ',NULL,NULL,NULL,NULL,NULL,'TAMAULIPAS','MEXICO','8333992231','8334032908','valeria@impeller.com.mx',NULL,NULL,0,NULL,'VENTAS',NULL,'AUXILIAR DE VENTAS',NULL,NULL),(5,'2020-05-07 14:31:01','2020-05-07 14:31:01',NULL,NULL,NULL,NULL,NULL,'eder','eder',1,NULL,'EDER ABEL','MARISCAL','ESCAMILLA',NULL,NULL,NULL,NULL,NULL,'TAMAULIPAS','MEXICO','8333992231','8332896777','eder@impeler.com.mx',NULL,NULL,0,NULL,'VENTAS',NULL,'AUXILIAR DE VENTAS',NULL,NULL),(6,'2020-05-07 14:31:01','2020-05-07 14:31:01',NULL,NULL,NULL,NULL,NULL,'karen','karen',1,NULL,'KAREN LIZETH','ECHEVERRIA','GARCIA',NULL,NULL,NULL,NULL,NULL,'TAMAULIPAS','MEXICO','8333992231','8333992231','karen@impeller.com.mx',NULL,NULL,0,NULL,'COMPRAS',NULL,'AUXILIAR DE COMPRAS',NULL,NULL),(7,'2020-05-07 14:31:01','2020-05-07 14:31:01',NULL,NULL,NULL,NULL,NULL,'ameras','alejandro',1,NULL,'OMAR ALEJANDRO','MERAZ','LARA',NULL,NULL,NULL,NULL,NULL,'TAMAULIPAS','MEXICO','8333992231','8334445525','ameras@impeller.com.mx',NULL,NULL,0,NULL,'ALMACEN',NULL,'AUXILIAR DE ALMACEN',NULL,NULL),(8,'2020-05-07 14:31:01','2020-05-07 14:31:01',NULL,NULL,NULL,NULL,NULL,'hreyna','hreyna',1,NULL,'LUIS HUMBERTO','REYNA','GURROLA',NULL,NULL,NULL,NULL,NULL,'TAMAULIPAS','MEXICO','8333992231','8334021468','hreyna@impeller.com.mx',NULL,NULL,0,NULL,'OPERACIONES',NULL,'GERENTE DE OPERACIONES',NULL,NULL),(9,'2020-05-07 14:31:01','2020-05-07 14:31:01',NULL,NULL,NULL,NULL,NULL,'rcruz','rcruz',1,NULL,'RUBEN','CRUZ','RAVIZE',NULL,NULL,NULL,NULL,NULL,'TAMAULIPAS','MEXICO','8333992231',NULL,'rcruz@impeller.com.mx',NULL,NULL,0,NULL,'OPERACIONES',NULL,'AUXILIAR DE OPERACIONES',NULL,NULL);

/*Table structure for table `node_file` */

DROP TABLE IF EXISTS `node_file`;

CREATE TABLE `node_file` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `parent_id` int(10) DEFAULT NULL,
  `parent_folio` varchar(100) DEFAULT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  `folio` varchar(100) DEFAULT NULL,
  `cliente_proveedor_id` int(10) DEFAULT NULL,
  `cliente_proveedor` varchar(100) DEFAULT NULL,
  `tipo_documento` varchar(100) DEFAULT NULL,
  `url` text,
  `extension` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `node_file` */

insert  into `node_file`(`id`,`parent_id`,`parent_folio`,`nombre`,`folio`,`cliente_proveedor_id`,`cliente_proveedor`,`tipo_documento`,`url`,`extension`) values (1,1,'RQ00001','A400.pdf','A400',2,'PROVEEDOR 2','REQUISICION DE COMPRA','C:\\Program Files\\Apache Software Foundation\\Tomcat 9.0\\ED_Impeller\\REQUISICION DE COMPRA\\RQ00001\\A400.pdf','application/pdf');

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
  `observaciones` text,
  `tipo_documento` varchar(100) DEFAULT NULL,
  `tipo_archivo` varchar(100) DEFAULT NULL,
  `razon_cancelar` text,
  `cliente_id` int(10) DEFAULT NULL,
  `cliente` varchar(100) DEFAULT NULL,
  `cliente_rfc` varchar(100) DEFAULT NULL,
  `metodo_pago` varchar(100) DEFAULT NULL,
  `moneda` varchar(100) DEFAULT NULL,
  `tipo_cambio` double(8,2) DEFAULT NULL,
  `importe` double(15,2) DEFAULT NULL,
  `descuento` double(10,1) DEFAULT NULL,
  `subtotal` double(15,2) DEFAULT NULL,
  `iva` double(10,1) DEFAULT NULL,
  `total` double(15,2) DEFAULT NULL,
  `porc_iva` int(10) DEFAULT NULL,
  `importe_letra` text,
  `activo` tinyint(1) DEFAULT '1',
  `proveedor_id` int(10) DEFAULT NULL,
  `proveedor` varchar(100) DEFAULT NULL,
  `cond_pago` text,
  `requisicion_id` int(10) DEFAULT NULL,
  `folio_requisicion` varchar(100) DEFAULT NULL,
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
  `pedido` varchar(25) DEFAULT NULL,
  `comprador` varchar(100) DEFAULT NULL,
  `tiempo_entrega` varchar(100) DEFAULT NULL,
  `instrucciones_entrega` text,
  `cotizacion_id` int(10) DEFAULT NULL,
  `cotizacion` varchar(50) DEFAULT NULL,
  `cotizacion_proveedor` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `orden_compra` */

insert  into `orden_compra`(`id`,`fecha_elaboracion`,`fecha_modificacion`,`empresa_id`,`empresa`,`unidad_id`,`unidad`,`usuario_id`,`usuario`,`estado_doc`,`estado`,`folio`,`serie`,`observaciones`,`tipo_documento`,`tipo_archivo`,`razon_cancelar`,`cliente_id`,`cliente`,`cliente_rfc`,`metodo_pago`,`moneda`,`tipo_cambio`,`importe`,`descuento`,`subtotal`,`iva`,`total`,`porc_iva`,`importe_letra`,`activo`,`proveedor_id`,`proveedor`,`cond_pago`,`requisicion_id`,`folio_requisicion`,`solicita_id`,`solicita`,`recibe_id`,`recibe`,`autoriza_id`,`autoriza`,`fecha_entrega`,`direccion_entrega`,`fecha_requisicion`,`factura_id`,`factura`,`folio_factura`,`no_cuenta`,`interbancaria`,`banco`,`sucursal`,`concepto_pago`,`fecha_autoriza`,`firma_autoriza`,`forma_pago`,`fecha_solicita`,`firma_solicita`,`pedido`,`comprador`,`tiempo_entrega`,`instrucciones_entrega`,`cotizacion_id`,`cotizacion`,`cotizacion_proveedor`) values (1,'2020-06-21 23:55:17','2020-06-21 23:55:40',0,'',0,'',2,'LUIS HUMBERTO GOMEZ TIBURCIO','EN PROCESO','','OC00002','','SDDASDASDASDASA','ORDEN DE COMPRA','PDF','',0,'','','EFECTIVO','PESOS',1.00,8866.00,0.0,8866.00,1418.0,10284.00,16,'DIEZ MIL DOSCIENTOS OCHENTA Y CUATRO Y 0/100 PESOS MEXICANOS.',1,3,'IESSA INSTRUMENTACION ELECTRONICA Y SISTEMAS S.A. de C.V.','CONTADO',2,'RQ00008',NULL,'LUIS HUMBERTO GOMEZ TIBURCIO',NULL,'Almacen Matriz',3,'GABRIELA ALEJANDRA BARRIOS REYNA','2020-06-21 00:00:00','ASDASDASD','2020-06-21 00:00:00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'E4F8CB642AB913891F59104DAD720220',NULL,'LUIS HUMBERTO GOMEZ TIBURCIO','1 DIAS',NULL,1,'CT00003','2342323'),(2,'2020-06-22 00:05:11','2020-06-22 00:05:37',0,'',0,'',2,'LUIS HUMBERTO GOMEZ TIBURCIO','EN PROCESO','','OC00003','','23423423','ORDEN DE COMPRA','PDF','',0,'','','EFECTIVO','PESOS',1.00,3333.00,0.0,3333.00,533.0,3866.00,16,'TRES MIL OCHOCIENTOS SESENTA Y SEIS Y 0/100 PESOS MEXICANOS.',1,3,'IESSA INSTRUMENTACION ELECTRONICA Y SISTEMAS S.A. de C.V.','CONTADO',1,'RQ00007',NULL,'LUIS HUMBERTO GOMEZ TIBURCIO',NULL,'Almacen Matriz',3,'GABRIELA ALEJANDRA BARRIOS REYNA','2020-06-22 00:00:00','234234242','2020-06-21 00:00:00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'DDB54BFFABE0509D51DA50BE4424290E',NULL,'LUIS HUMBERTO GOMEZ TIBURCIO','1 DIAS',NULL,1,'CT00003',''),(3,'2020-06-28 22:06:11','2020-06-28 22:08:35',0,'',0,'',2,'LUIS HUMBERTO GOMEZ TIBURCIO','EN PROCESO','','OC00004','','OBSERVACIONES','ORDEN DE COMPRA','PDF','',0,'','','EFECTIVO','PESOS',1.00,1200.00,0.0,1200.00,192.0,1392.00,16,'UN MIL TRECIENTOS NOVENTA Y DOS Y 0/100 PESOS MEXICANOS.',1,3,'IESSA INSTRUMENTACION ELECTRONICA Y SISTEMAS S.A. de C.V.','CONTADO',3,'RQ00009',NULL,'LUIS HUMBERTO GOMEZ TIBURCIO',NULL,'Almacen Matriz',3,'GABRIELA ALEJANDRA BARRIOS REYNA','2020-06-28 00:00:00','ENTREGA EN','2020-06-28 00:00:00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'0AD6BC5B0CC6B5CE69E86509F5B43060',NULL,'LUIS HUMBERTO GOMEZ TIBURCIO','1 DIAS',NULL,2,'CT00004','');

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
  `descripcion` text,
  `unidad_medida` varchar(100) DEFAULT NULL,
  `porc_iva` int(2) DEFAULT NULL,
  `precio_unitario` double(15,2) DEFAULT NULL,
  `importe` double(15,2) DEFAULT NULL,
  `descuento` double(10,1) DEFAULT NULL,
  `subtotal` double(15,2) DEFAULT NULL,
  `iva` double(10,1) DEFAULT NULL,
  `total` double(15,2) DEFAULT NULL,
  `servicio` tinyint(1) DEFAULT '0',
  `folio_requisicion` varchar(100) DEFAULT NULL,
  `requisicion_id` int(10) DEFAULT NULL,
  `no_parte` varchar(100) DEFAULT NULL,
  `no_serie` varchar(100) DEFAULT NULL,
  `modelo` varchar(100) DEFAULT NULL,
  `marca` varchar(100) DEFAULT NULL,
  `codigo_proveedor` varchar(100) DEFAULT NULL,
  `codigo_interno` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `orden_compra_det` */

insert  into `orden_compra_det`(`id`,`folio`,`documento_id`,`empresa_id`,`empresa`,`unidad_id`,`unidad`,`usuario_id`,`usuario`,`no_partida`,`fecha_alta`,`cantidad`,`producto_id`,`descripcion`,`unidad_medida`,`porc_iva`,`precio_unitario`,`importe`,`descuento`,`subtotal`,`iva`,`total`,`servicio`,`folio_requisicion`,`requisicion_id`,`no_parte`,`no_serie`,`modelo`,`marca`,`codigo_proveedor`,`codigo_interno`) values (1,'OC00002',1,0,'',0,'',2,'LUIS HUMBERTO GOMEZ TIBURCIO',0,'2020-06-21 23:55:35',2,3,'PRODUCTO 3 TREES','PIEZA',NULL,4433.00,8866.00,NULL,NULL,NULL,NULL,0,'RQ00008',2,'34343','434','ASDQ3234','343','3','3'),(2,'OC00003',2,0,'',0,'',2,'LUIS HUMBERTO GOMEZ TIBURCIO',0,'2020-06-22 00:05:35',1,2,'PRODUCTO2','PIEZA',NULL,3333.00,3333.00,NULL,NULL,NULL,NULL,0,'RQ00007',1,'343','WR3R3','AW','34T4','3','2'),(3,'OC00004',3,0,'',0,'',2,'LUIS HUMBERTO GOMEZ TIBURCIO',0,'2020-06-28 22:08:09',1,2,'PRODUCTO2','PIEZA',NULL,1200.00,1200.00,NULL,NULL,NULL,NULL,0,'RQ00009',3,'343','WR3R3','AW','34T4','3','2');

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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `producto` */

insert  into `producto`(`id`,`fecha_elaboracion`,`fecha_modificacion`,`empresa_id`,`empresa`,`unidad_id`,`unidad`,`usuario_id`,`usuario`,`activo`,`codigo_interno`,`descripcion_corta`,`descripcion`,`modelo`,`no_parte`,`no_serie`,`marca`,`unidad_medida`,`tipo_producto`,`sub_tipo_producto`,`clasificacion`,`inventariable`,`inventario_actual`,`inventario_maximo`,`inventario_minimo`,`porc_iva`,`precio_compra`,`iva_compra`,`precio_venta`,`iva_venta`,`porc_descuento`,`descuento_venta`,`porc_utilidad`,`proveedor_id_1`,`proveedor_1`,`proveedor_id_2`,`proveedor_2`) values (1,'2020-06-03 16:34:14','2020-06-21 14:44:42',0,'',0,'',0,'',1,'1','PRODUCTO 1','','1ERW42','23232','23232','23WWWWW','PIEZA','','','',0,0,0,0,16,5000,0,0,0,0,0,0,1,'PROVEEDOR 1',3,'IESSA INSTRUMENTACION ELECTRONICA Y SISTEMAS S.A. de C.V.'),(2,'2020-06-03 17:21:05','2020-06-03 17:21:05',0,'',0,'',0,'',1,'2','PRODUCTO2','','AW','343','WR3R3','34T4','PIEZA','','','',0,0,0,0,16,800,0,0,0,0,0,0,0,'',0,''),(3,'2020-06-03 18:01:56','2020-06-03 18:01:56',0,'',0,'',0,'',1,'3','PRODUCTO 3 TREES','','ASDQ3234','34343','434','343','PIEZA','','','',0,0,0,0,16,1550,0,0,0,0,0,0,0,'',0,''),(4,'2020-06-21 14:44:32','2020-06-21 14:45:08',0,'',0,'',0,'',1,'4','ASDA','SDASDASDASD','222','2232','23E','232','PIEZA','','','PRODUCTO',1,0,0,0,16,0,0,0,0,0,0,0,1,'PROVEEDOR 1',2,'PROVEEDOR 2');

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
  `contacto_compra_ext` varchar(7) DEFAULT NULL,
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

insert  into `proveedor`(`id`,`fecha_elaboracion`,`fecha_modificacion`,`empresa_id`,`empresa`,`unidad_id`,`unidad`,`usuario_id`,`usuario`,`no_paper`,`activo`,`clasificacion_proveedor`,`tipo_proveedor`,`dias_credito`,`clave_proveedor`,`razon_social`,`rfc`,`domicilio`,`ciudad`,`estado`,`pais`,`cp`,`contacto_compra_telefono`,`contacto_compra_ext`,`contacto_compra_nombre`,`contacto_compra_email`,`contacto_contabilidad_telefono`,`contacto_contabilidad_nombre`,`contacto_contabilidad_email`,`no_cuenta_1`,`clave_interbancaria_1`,`banco_1`,`sucursal_1`,`no_cuenta_2`,`clave_interbancaria_2`,`banco_2`,`sucursal_2`,`no_cuenta_3`,`clave_interbancaria_3`,`banco_3`,`sucursal_3`) values (1,'2020-05-24 10:14:19','2020-05-24 10:14:19',0,'',0,'',0,'',1,1,'PROVEEDOR','CREDITO',60,'1','PROVEEDOR 1','BEMP8905273G1','CALLE','CIUDAD','ESTADO','MEXICO','89365','8331223344','331','Contacto Compra 1','contactoProv1@prov.com','','','','','','','','','','','','','','',''),(2,'2020-05-24 10:14:19','2020-05-24 10:14:19',0,'',0,'',0,'',1,1,'PROVEEDOR','CREDITO',30,'2','PROVEEDOR 2','BEMP8905273G1','CALLE','CIUDAD','ESTADO','MEXICO','89365','8331223344','332','Contacto Compra 2','contactoProv2@prov.com','','','','','','','','','','','','','','',''),(3,'2020-05-24 10:14:19','2020-05-24 10:14:19',0,'',0,'',0,'',1,1,'PROVEEDOR','CREDITO',60,'3','IESSA INSTRUMENTACION ELECTRONICA Y SISTEMAS S.A. de C.V.','BEMP8905273G1','Emiliano Zapata #105','CD MADERO','TAMAULIPAS','MEXICO','89540','833 362 63 73','','SERGIO PONCE','sergio.ponce@iesis.mx','','','','','','','','','','','','','','','');

/*Table structure for table `proyecto` */

DROP TABLE IF EXISTS `proyecto`;

CREATE TABLE `proyecto` (
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
  `no_paper` tinyint(1) DEFAULT '1',
  `cliente_id` int(10) DEFAULT NULL,
  `fecha_inicio` datetime DEFAULT NULL,
  `fecha_fin` datetime DEFAULT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  `descripcion` text,
  `nombre_cliente` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `proyecto` */

insert  into `proyecto`(`id`,`fecha_elaboracion`,`fecha_modificacion`,`empresa_id`,`empresa`,`unidad_id`,`unidad`,`usuario_id`,`usuario`,`activo`,`no_paper`,`cliente_id`,`fecha_inicio`,`fecha_fin`,`nombre`,`descripcion`,`nombre_cliente`) values (1,NULL,NULL,0,'',0,'',2,'LUIS HUMBERTO GOMEZ TIBURCIO',1,1,1,'2020-06-21 00:00:00','2020-06-21 00:00:00','SIN PROYECTO','SASDASDASD','CLIENTE 1'),(2,NULL,'2020-06-21 13:48:41',0,'',0,'',2,'LUIS HUMBERTO GOMEZ TIBURCIO',1,1,2,'2020-06-20 00:00:00','2020-12-31 00:00:00','PROYECTO IMPELLER2','PROYECTO IMPELLER','CLIENTE 2');

/*Table structure for table `remision_entrega` */

DROP TABLE IF EXISTS `remision_entrega`;

CREATE TABLE `remision_entrega` (
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
  `importe_letra` text,
  `activo` tinyint(1) DEFAULT '1',
  `entrega_id` int(10) DEFAULT NULL,
  `entrega` varchar(100) DEFAULT NULL,
  `direccion_tentrega` text,
  `orden_compra_partida_id` int(10) DEFAULT NULL,
  `orden_compra_id` int(10) DEFAULT NULL,
  `folio_orden_compra` varchar(100) DEFAULT NULL,
  `cotizacion_id` int(10) DEFAULT NULL,
  `folio_cotizacion` varchar(100) DEFAULT NULL,
  `fecha_entrega` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `remision_entrega` */

insert  into `remision_entrega`(`id`,`fecha_elaboracion`,`fecha_modificacion`,`empresa_id`,`empresa`,`unidad_id`,`unidad`,`usuario_id`,`usuario`,`estado_doc`,`estado`,`folio`,`serie`,`observaciones`,`tipo_documento`,`tipo_archivo`,`razon_cancelar`,`cliente_id`,`cliente`,`cliente_rfc`,`metodo_pago`,`moneda`,`tipo_cambio`,`importe`,`descuento`,`subtotal`,`iva`,`total`,`porc_iva`,`importe_letra`,`activo`,`entrega_id`,`entrega`,`direccion_tentrega`,`orden_compra_partida_id`,`orden_compra_id`,`folio_orden_compra`,`cotizacion_id`,`folio_cotizacion`,`fecha_entrega`) values (1,'2020-06-28 22:17:16','2020-06-28 22:17:16',0,'',0,'',2,'LUIS HUMBERTO GOMEZ TIBURCIO','TERMINADO','','RM00003','','ENTREGAR EN ','REMISION DE ENTREGA','PDF','',1,'CLIENTE 1','','',NULL,NULL,0,0,0,0,0,0,'',1,NULL,'LUIS HUMBERTO GOMEZ TIBURCIO',NULL,NULL,NULL,'OC00004',NULL,NULL,'2020-06-28 00:00:00');

/*Table structure for table `remision_entrega_det` */

DROP TABLE IF EXISTS `remision_entrega_det`;

CREATE TABLE `remision_entrega_det` (
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
  `descripcion` text,
  `unidad_medida` varchar(100) DEFAULT NULL,
  `porc_iva` int(10) DEFAULT NULL,
  `precio_unitario` double DEFAULT NULL,
  `importe` double DEFAULT NULL,
  `descuento` double DEFAULT NULL,
  `subtotal` double DEFAULT NULL,
  `iva` double DEFAULT NULL,
  `total` double DEFAULT NULL,
  `servicio` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `remision_entrega_det` */

insert  into `remision_entrega_det`(`id`,`folio`,`documento_id`,`empresa_id`,`empresa`,`unidad_id`,`unidad`,`usuario_id`,`usuario`,`no_partida`,`fecha_alta`,`cantidad`,`producto_id`,`descripcion`,`unidad_medida`,`porc_iva`,`precio_unitario`,`importe`,`descuento`,`subtotal`,`iva`,`total`,`servicio`) values (1,'RM00003',1,NULL,NULL,NULL,NULL,NULL,NULL,0,'2020-06-28 22:16:21',1,2,'PRODUCTO2','PIEZA',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0);

/*Table structure for table `requisicion` */

DROP TABLE IF EXISTS `requisicion`;

CREATE TABLE `requisicion` (
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
  `tipo_cambio` double(8,2) DEFAULT NULL,
  `importe` double(15,2) DEFAULT NULL,
  `descuento` double(12,1) DEFAULT NULL,
  `subtotal` double(15,2) DEFAULT NULL,
  `iva` double(12,1) DEFAULT NULL,
  `total` double(15,2) DEFAULT NULL,
  `porc_iva` int(10) DEFAULT NULL,
  `importe_letra` text,
  `activo` tinyint(1) DEFAULT '1',
  `prioridad` varchar(100) DEFAULT NULL,
  `fecha_requerida` datetime DEFAULT NULL,
  `direccion_entrega` varchar(100) DEFAULT NULL,
  `tiempo_entrega` varchar(50) DEFAULT NULL,
  `fecha_orden_compra` datetime DEFAULT NULL,
  `folio_orden_compra` varchar(100) DEFAULT NULL,
  `autoriza_id` int(10) DEFAULT NULL,
  `autoriza` varchar(100) DEFAULT NULL,
  `fecha_autorizo` datetime DEFAULT NULL,
  `firma_autorizo` varchar(100) DEFAULT NULL,
  `solicita` varchar(100) DEFAULT NULL,
  `firma_solicita` varchar(100) DEFAULT NULL,
  `proveedor_id` int(10) DEFAULT NULL,
  `proveedor` varchar(100) DEFAULT NULL,
  `cotizacion_id` int(10) DEFAULT NULL,
  `folio_cotizacion` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `requisicion` */

insert  into `requisicion`(`id`,`fecha_elaboracion`,`fecha_modificacion`,`empresa_id`,`empresa`,`unidad_id`,`unidad`,`usuario_id`,`usuario`,`estado_doc`,`estado`,`folio`,`serie`,`observaciones`,`tipo_documento`,`tipo_archivo`,`razon_cancelar`,`cliente_id`,`cliente`,`cliente_rfc`,`metodo_pago`,`moneda`,`tipo_cambio`,`importe`,`descuento`,`subtotal`,`iva`,`total`,`porc_iva`,`importe_letra`,`activo`,`prioridad`,`fecha_requerida`,`direccion_entrega`,`tiempo_entrega`,`fecha_orden_compra`,`folio_orden_compra`,`autoriza_id`,`autoriza`,`fecha_autorizo`,`firma_autorizo`,`solicita`,`firma_solicita`,`proveedor_id`,`proveedor`,`cotizacion_id`,`folio_cotizacion`) values (1,'2020-06-21 23:07:36','2020-06-21 23:54:24',0,'',0,'',2,'LUIS HUMBERTO GOMEZ TIBURCIO','TERMINADO','','RQ00007','','23423423','REQUISICION DE COMPRA','PDF','',0,'','','',NULL,NULL,0.00,0.0,0.00,0.0,0.00,0,'',1,'ALTA','2020-06-21 00:00:00','234234242','1 DIAS','2020-06-22 00:05:37','OC00003',2,'LUIS HUMBERTO GOMEZ TIBURCIO','2020-06-21 23:54:24','A075C64DC6FDD65E66BCC6CE9B34EAED','LUIS HUMBERTO GOMEZ TIBURCIO','A075C64DC6FDD65E66BCC6CE9B34EAED',3,'IESSA INSTRUMENTACION ELECTRONICA Y SISTEMAS S.A. de C.V.',1,'CT00003'),(2,'2020-06-21 23:30:31','2020-06-21 23:54:26',0,'',0,'',2,'LUIS HUMBERTO GOMEZ TIBURCIO','TERMINADO','','RQ00008','','SDDASDASDASDASA','REQUISICION DE COMPRA','PDF','',0,'','','',NULL,NULL,0.00,0.0,0.00,0.0,0.00,0,'',1,'ALTA','2020-06-21 00:00:00','ASDASDASD','1 DIAS','2020-06-21 23:55:40','OC00002',2,'LUIS HUMBERTO GOMEZ TIBURCIO','2020-06-21 23:54:26','298CDE2E91C535AE7BD3B558F2CF70A1','LUIS HUMBERTO GOMEZ TIBURCIO','298CDE2E91C535AE7BD3B558F2CF70A1',3,'IESSA INSTRUMENTACION ELECTRONICA Y SISTEMAS S.A. de C.V.',1,'CT00003'),(3,'2020-06-28 21:56:12','2020-06-28 22:06:04',0,'',0,'',2,'LUIS HUMBERTO GOMEZ TIBURCIO','TERMINADO','','RQ00009','','OBSERVACIONES','REQUISICION DE COMPRA','PDF','',0,'','','',NULL,NULL,0.00,0.0,0.00,0.0,0.00,0,'',1,'ALTA','2020-06-28 00:00:00','ENTREGA EN','1 DIAS','2020-06-28 22:08:35','OC00004',2,'LUIS HUMBERTO GOMEZ TIBURCIO','2020-06-28 22:06:04','949295A316477B7342CFEC80759D5849','LUIS HUMBERTO GOMEZ TIBURCIO','949295A316477B7342CFEC80759D5849',3,'IESSA INSTRUMENTACION ELECTRONICA Y SISTEMAS S.A. de C.V.',2,'CT00004');

/*Table structure for table `requisicion_det` */

DROP TABLE IF EXISTS `requisicion_det`;

CREATE TABLE `requisicion_det` (
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
  `producto_id` int(10) DEFAULT NULL,
  `descripcion` text,
  `unidad_medida` varchar(100) DEFAULT NULL,
  `cantidad` int(10) DEFAULT NULL,
  `precio_unitario` double(15,2) DEFAULT NULL,
  `importe` double(15,2) DEFAULT NULL,
  `descuento` double(12,1) DEFAULT NULL,
  `subtotal` double(15,2) DEFAULT NULL,
  `porc_iva` int(10) DEFAULT NULL,
  `iva` double(12,1) DEFAULT NULL,
  `total` double(15,2) DEFAULT NULL,
  `servicio` tinyint(1) DEFAULT '1',
  `no_parte` varchar(20) DEFAULT NULL,
  `no_serie` varchar(20) DEFAULT NULL,
  `modelo` varchar(20) DEFAULT NULL,
  `marca` varchar(50) DEFAULT NULL,
  `codigo_interno` varchar(10) DEFAULT NULL,
  `codigo_proveedor` varchar(10) DEFAULT NULL,
  `partida_cotizacion_id` int(10) DEFAULT NULL,
  `cotizacion_id` int(10) DEFAULT NULL,
  `folio_cotizacion` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `requisicion_det` */

insert  into `requisicion_det`(`id`,`folio`,`documento_id`,`empresa_id`,`empresa`,`unidad_id`,`unidad`,`usuario_id`,`usuario`,`no_partida`,`fecha_alta`,`producto_id`,`descripcion`,`unidad_medida`,`cantidad`,`precio_unitario`,`importe`,`descuento`,`subtotal`,`porc_iva`,`iva`,`total`,`servicio`,`no_parte`,`no_serie`,`modelo`,`marca`,`codigo_interno`,`codigo_proveedor`,`partida_cotizacion_id`,`cotizacion_id`,`folio_cotizacion`) values (1,'RQ00007',1,NULL,NULL,NULL,NULL,NULL,NULL,0,'2020-06-21 23:07:28',2,'PRODUCTO2','PIEZA',1,0.00,0.00,NULL,NULL,NULL,NULL,NULL,0,'343','WR3R3','AW','34T4','2','3',2,1,'CT00003'),(2,'RQ00008',2,NULL,NULL,NULL,NULL,NULL,NULL,0,'2020-06-21 23:30:38',3,'PRODUCTO 3 TREES','PIEZA',2,0.00,0.00,NULL,NULL,NULL,NULL,NULL,0,'34343','434','ASDQ3234','343','3','3',1,1,'CT00003'),(3,'RQ00009',3,NULL,NULL,NULL,NULL,NULL,NULL,0,'2020-06-28 21:57:02',2,'PRODUCTO2','PIEZA',1,0.00,0.00,NULL,NULL,NULL,NULL,NULL,0,'343','WR3R3','AW','34T4','2','3',3,2,'CT00004');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
