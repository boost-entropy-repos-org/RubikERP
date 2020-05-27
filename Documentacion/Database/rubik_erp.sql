/*
SQLyog Ultimate v11.13 (64 bit)
MySQL - 8.0.18 : Database - rubik_erp
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`rubik_erp` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;

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

insert  into `configuracion`(`id`,`serie_requisicion`,`folio_requisicion`,`serie_orden_compra`,`folio_orden_compra`,`serie_cotizacion`,`folio_cotizacion`,`serie_remision`,`folio_remision`,`serie_factura`,`folio_factura`,`autocompletar_totales`) values (1,'RQ',5,'OC',0,'CT',0,'RM',0,'FA',0,0);

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
  `importe_letra` text,
  `activo` tinyint(1) DEFAULT '1',
  `proveedor_id` int(10) DEFAULT NULL,
  `proveedor` varchar(100) DEFAULT NULL,
  `cond_pago` varchar(100) DEFAULT NULL,
  `remision_id` int(10) DEFAULT NULL,
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
  `folio_requisicion` varchar(100) DEFAULT NULL,
  `requisicion_id` int(10) DEFAULT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `producto` */

insert  into `producto`(`id`,`fecha_elaboracion`,`fecha_modificacion`,`empresa_id`,`empresa`,`unidad_id`,`unidad`,`usuario_id`,`usuario`,`activo`,`codigo_interno`,`descripcion_corta`,`descripcion`,`modelo`,`no_parte`,`no_serie`,`marca`,`unidad_medida`,`tipo_producto`,`sub_tipo_producto`,`clasificacion`,`inventariable`,`inventario_actual`,`inventario_maximo`,`inventario_minimo`,`porc_iva`,`precio_compra`,`iva_compra`,`precio_venta`,`iva_venta`,`porc_descuento`,`descuento_venta`,`porc_utilidad`,`proveedor_id_1`,`proveedor_1`,`proveedor_id_2`,`proveedor_2`) values (1,'2020-05-24 10:16:53','2020-05-24 10:16:53',0,'',0,'',0,'',1,'1','PRODUCTO 1','PRODUCTO IMPELLER 1','MOD1','NOPARTE1','NOSERIE1','MARCA1','PIEZA','','','PRODUCTO',1,0,0,0,0,0,0,0,0,0,0,0,2,NULL,2,NULL),(2,'2020-05-24 10:16:53','2020-05-24 10:16:53',0,'',0,'',0,'',1,'2','PRODUCTO 2','PRODUCTO IMPELLER 2','MOD2','NOPARTE2','NOSERIE2','MARCA2','PIEZA','','','PRODUCTO',1,0,0,0,0,0,0,0,0,0,0,0,2,NULL,2,NULL),(3,'2020-05-24 10:16:53','2020-05-24 10:16:53',0,'',0,'',0,'',1,'3','PRODUCTO 3','PRODUCTO IMPELLER 3','MOD3','NOPARTE3','NOSERIE3','MARCA3','PIEZA','','','PRODUCTO',1,0,0,0,0,0,0,0,0,0,0,0,2,NULL,2,NULL),(4,'2020-05-24 11:10:53','2020-05-24 11:10:53',0,'',0,'',0,'',1,'4','PRODUCTO 4','','PROD4','NOPART4','NOSERIE4','MARCA4','PIEZA','','','',0,0,0,0,16,1000,0,0,0,0,0,0,0,'',0,''),(5,'2020-05-24 11:11:44','2020-05-24 11:11:44',0,'',0,'',0,'',1,'5','PROD5','','MOD5','NOPART5','NOSERIE5','MARCA5','PIEZA','','','',0,0,0,0,16,250,0,0,0,0,0,0,0,'',0,''),(6,'2020-05-25 09:07:37','2020-05-25 09:07:37',0,'',0,'',0,'',1,'6','PRODUCTO 6 SEIS','','MODELO','no parte','NO SERIE','MARCA','PIEZA','','','',0,0,0,0,16,5000,0,0,0,0,0,0,0,'',0,'');

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

insert  into `proveedor`(`id`,`fecha_elaboracion`,`fecha_modificacion`,`empresa_id`,`empresa`,`unidad_id`,`unidad`,`usuario_id`,`usuario`,`no_paper`,`activo`,`clasificacion_proveedor`,`tipo_proveedor`,`dias_credito`,`clave_proveedor`,`razon_social`,`rfc`,`domicilio`,`ciudad`,`estado`,`pais`,`cp`,`contacto_compra_telefono`,`contacto_compra_ext`,`contacto_compra_nombre`,`contacto_compra_email`,`contacto_contabilidad_telefono`,`contacto_contabilidad_nombre`,`contacto_contabilidad_email`,`no_cuenta_1`,`clave_interbancaria_1`,`banco_1`,`sucursal_1`,`no_cuenta_2`,`clave_interbancaria_2`,`banco_2`,`sucursal_2`,`no_cuenta_3`,`clave_interbancaria_3`,`banco_3`,`sucursal_3`) values (1,'2020-05-24 10:14:19','2020-05-24 10:14:19',0,'',0,'',0,'',1,1,'PROVEEDOR','CREDITO',60,'1','PROVEEDOR 1','BEMP8905273G1','CALLE','CIUDAD','ESTADO','MEXICO','89365','8331223344','331','Contacto Compra 1','contactoProv1@prov.com','','','','','','','','','','','','','','',''),(2,'2020-05-24 10:14:19','2020-05-24 10:14:19',0,'',0,'',0,'',1,1,'PROVEEDOR','CREDITO',30,'2','PROVEEDOR 2','BEMP8905273G1','CALLE','CIUDAD','ESTADO','MEXICO','89365','8331223344','332','Contacto Compra 2','contactoProv2@prov.com','','','','','','','','','','','','','','',''),(3,'2020-05-24 10:14:19','2020-05-24 10:14:19',0,'',0,'',0,'',1,1,'PROVEEDOR','CREDITO',60,'3','PROVEEDOR 3','BEMP8905273G1','CALLE','CIUDAD','ESTADO','MEXICO','89365','8331223344','333','Contacto Compra 3','contactoProv3@prov.com','','','','','','','','','','','','','','','');

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
  `proveedor_id` int(10) DEFAULT NULL,
  `proveedor` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `requisicion` */

insert  into `requisicion`(`id`,`fecha_elaboracion`,`fecha_modificacion`,`empresa_id`,`empresa`,`unidad_id`,`unidad`,`usuario_id`,`usuario`,`estado_doc`,`estado`,`folio`,`serie`,`observaciones`,`tipo_documento`,`tipo_archivo`,`razon_cancelar`,`cliente_id`,`cliente`,`cliente_rfc`,`metodo_pago`,`moneda`,`tipo_cambio`,`importe`,`descuento`,`subtotal`,`iva`,`total`,`porc_iva`,`importe_letra`,`activo`,`prioridad`,`fecha_requerida`,`direccion_entrega`,`fecha_orden_compra`,`folio_orden_compra`,`autoriza_id`,`autoriza`,`fecha_autorizo`,`firma_autorizo`,`solicita`,`firma_solicita`,`proveedor_id`,`proveedor`) values (1,'2020-05-24 11:11:54','2020-05-25 12:08:12',0,'',0,'',2,'LUIS HUMBERTO GOMEZ TIBURCIO','AUTORIZADO','','RQ00001','','OBSERVACION 1','REQUISICION DE COMPRA','PDF','',0,'','','',NULL,NULL,0,0,0,0,0,0,'',1,'ALTA','2020-05-24 00:00:00','ENTREGAR EN DIRECCION.',NULL,NULL,2,'LUIS HUMBERTO GOMEZ TIBURCIO','2020-05-25 12:08:12','675F06801871CF34093C229291A3B6F4','LUIS HUMBERTO GOMEZ TIBURCIO','675F06801871CF34093C229291A3B6F4',2,'PROVEEDOR 2'),(2,'2020-05-24 11:16:59','2020-05-24 11:24:52',0,'',0,'',2,'LUIS HUMBERTO GOMEZ TIBURCIO','AUTORIZADO','','RQ00002','','NINGUNA','REQUISICION DE COMPRA','PDF','',0,'','','',NULL,NULL,0,0,0,0,0,0,'',1,'ALTA','2020-05-24 00:00:00','ENTREGAR EN ALMACEN',NULL,NULL,2,'LUIS HUMBERTO GOMEZ TIBURCIO','2020-05-24 11:24:52','32B3E16D7A4BE134498F4F16E2C380A7','LUIS HUMBERTO GOMEZ TIBURCIO','32B3E16D7A4BE134498F4F16E2C380A7',3,'PROVEEDOR 3'),(3,'2020-05-25 09:10:49','2020-05-25 09:13:31',0,'',0,'',2,'LUIS HUMBERTO GOMEZ TIBURCIO','AUTORIZADO','','RQ00003','','OBSERVACIONES','REQUISICION DE COMPRA','PDF','',0,'','','',NULL,NULL,0,0,0,0,0,0,'',1,'ALTA','2020-05-25 00:00:00','DIRECCION DE ENTREGA',NULL,NULL,2,'LUIS HUMBERTO GOMEZ TIBURCIO','2020-05-25 09:13:31','C78D7C9A2DFECF7E370BEB3710749B48','LUIS HUMBERTO GOMEZ TIBURCIO','C78D7C9A2DFECF7E370BEB3710749B48',1,'PROVEEDOR 1'),(4,'2020-05-25 13:46:48','2020-05-25 13:47:10',0,'',0,'',2,'LUIS HUMBERTO GOMEZ TIBURCIO','POR AUTORIZAR','','RQ00004','','OBSERVACIONES','REQUISICION DE COMPRA','PDF','',0,'','','',NULL,NULL,0,0,0,0,0,0,'',1,'ALTA','2020-05-25 00:00:00','ENTREGA EN ALMACEN',NULL,NULL,3,'GABRIELA ALEJANDRA BARRIOS REYNA',NULL,NULL,'LUIS HUMBERTO GOMEZ TIBURCIO','F32FCD3B8A53226597EB059444116DA5',1,'PROVEEDOR 1'),(5,'2020-05-26 12:46:34','2020-05-26 12:47:26',0,'',0,'',2,'LUIS HUMBERTO GOMEZ TIBURCIO','EN PROCESO','','RQ00005','','OBSERVACIONES','REQUISICION DE COMPRA','PDF','',0,'','','',NULL,NULL,0,0,0,0,0,0,'',1,'ALTA','2020-05-26 00:00:00','DIRECCION DE ENTREGA',NULL,NULL,3,'GABRIELA ALEJANDRA BARRIOS REYNA',NULL,NULL,'LUIS HUMBERTO GOMEZ TIBURCIO',NULL,1,'PROVEEDOR 1');

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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `requisicion_det` */

insert  into `requisicion_det`(`id`,`folio`,`documento_id`,`empresa_id`,`empresa`,`unidad_id`,`unidad`,`usuario_id`,`usuario`,`no_partida`,`fecha_alta`,`cantidad`,`producto_id`,`descripcion`,`unidad_medida`,`precio_unitario`,`importe`,`descuento`,`subtotal`,`iva`,`total`,`servicio`,`no_parte`,`no_serie`,`modelo`,`marca`,`codigo_interno`,`codigo_proveedor`) values (1,'RQ00001',1,0,'',0,'',2,'LUIS HUMBERTO GOMEZ TIBURCIO',0,'2020-05-24 11:12:08',10,4,'PRODUCTO 4','PIEZA',1000,NULL,NULL,NULL,NULL,NULL,0,'NOPART4','NOSERIE4','PROD4','MARCA4','4',NULL),(2,'RQ00001',1,0,'',0,'',2,'LUIS HUMBERTO GOMEZ TIBURCIO',0,'2020-05-24 11:12:03',3,3,'PRODUCTO 3','PIEZA',0,NULL,NULL,NULL,NULL,NULL,0,'NOPARTE3','NOSERIE3','MOD3','MARCA3','3',NULL),(3,'RQ00001',1,0,'',0,'',2,'LUIS HUMBERTO GOMEZ TIBURCIO',0,'2020-05-24 11:11:48',1,5,'PROD5','PIEZA',250,NULL,NULL,NULL,NULL,NULL,0,'NOPART5','NOSERIE5','MOD5','MARCA5','5',NULL),(4,'RQ00002',2,0,'',0,'',2,'LUIS HUMBERTO GOMEZ TIBURCIO',0,'2020-05-24 11:16:28',1,5,'PROD5','PIEZA',250,NULL,NULL,NULL,NULL,NULL,0,'NOPART5','NOSERIE5','MOD5','MARCA5','5',NULL),(5,'RQ00002',2,0,'',0,'',2,'LUIS HUMBERTO GOMEZ TIBURCIO',0,'2020-05-24 11:16:35',3,1,'PRODUCTO 1','PIEZA',0,NULL,NULL,NULL,NULL,NULL,0,'NOPARTE1','NOSERIE1','MOD1','MARCA1','1',NULL),(6,'RQ00002',2,0,'',0,'',2,'LUIS HUMBERTO GOMEZ TIBURCIO',0,'2020-05-24 11:16:41',5,4,'PRODUCTO 4','PIEZA',1000,NULL,NULL,NULL,NULL,NULL,0,'NOPART4','NOSERIE4','PROD4','MARCA4','4',NULL),(7,'RQ00002',2,0,'',0,'',2,'LUIS HUMBERTO GOMEZ TIBURCIO',0,'2020-05-24 11:16:48',1,2,'PRODUCTO 2','PIEZA',0,NULL,NULL,NULL,NULL,NULL,0,'NOPARTE2','NOSERIE2','MOD2','MARCA2','2',NULL),(8,'RQ00002',2,0,'',0,'',2,'LUIS HUMBERTO GOMEZ TIBURCIO',0,'2020-05-24 11:16:55',1,3,'PRODUCTO 3','PIEZA',0,NULL,NULL,NULL,NULL,NULL,0,'NOPARTE3','NOSERIE3','MOD3','MARCA3','3',NULL),(9,'RQ00003',3,0,'',0,'',2,'LUIS HUMBERTO GOMEZ TIBURCIO',0,'2020-05-25 09:12:47',4,6,'PRODUCTO 6 SEIS','PIEZA',5000,20000,NULL,NULL,NULL,NULL,0,'no parte','NO SERIE','MODELO','MARCA','6',NULL),(10,'RQ00004',4,0,'',0,'',2,'LUIS HUMBERTO GOMEZ TIBURCIO',0,'2020-05-25 13:46:45',1,1,'PRODUCTO 1','PIEZA',0,0,NULL,NULL,NULL,NULL,0,'NOPARTE1','NOSERIE1','MOD1','MARCA1','1',NULL),(11,'RQ00005',5,0,'',0,'',2,'LUIS HUMBERTO GOMEZ TIBURCIO',0,'2020-05-26 12:47:24',22,5,'PROD5','PIEZA',250,5500,NULL,NULL,NULL,NULL,0,'NOPART5','NOSERIE5','MOD5','MARCA5','5',NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
