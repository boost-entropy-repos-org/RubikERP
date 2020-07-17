/*
SQLyog Ultimate v9.10 
MySQL - 8.0.16 : Database - rubik_erp
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`rubik_erp` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

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
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

/*Data for the table `cliente` */

insert  into `cliente`(`id`,`fecha_elaboracion`,`fecha_modificacion`,`empresa_id`,`empresa`,`unidad_id`,`unidad`,`usuario_id`,`usuario`,`activo`,`clave_cliente`,`razon_social`,`nombre_corto`,`rfc`,`domicilio`,`ciudad`,`estado`,`cp`,`pais`,`tipo_cliente`,`dias_credito`,`limite_credito`,`saldo_a_favor`,`saldo_pendiente`,`no_contable`,`contacto_general_nombre`,`contacto_general_email`,`contacto_telefono_general`,`contacto_venta_nombre`,`contacto_venta_email`,`contacto_venta_telefono`,`contacto_contabilidad_nombre`,`contacto_contabilidad_email`,`contacto_contabilidad_telefono`,`matriz`,`sucursal`,`id_matriz`,`cuenta_clientes`,`cuenta_pagos`,`cuenta_anticipos`,`cuenta_honorarios`) values (1,NULL,NULL,0,'',0,'',6,'KEREN LIZETH ECHEVERRIA GARCIA',1,'1','MEXICHEM COMPUESTOS, S.A. DE C.V. ','',' MCO061215JR5','AUTOPISTA ALTAMIRA, PUERTO INDUSTRIAL DE ALTAMIRA.','ALTAMIRA','TAMAULIPAS','89603','MEXICO','CONTADO',0,0,0,0,'','','','','','','','','','',0,0,0,'','','',''),(2,NULL,NULL,0,'',0,'',6,'KEREN LIZETH ECHEVERRIA GARCIA',1,'2','BASF MEXICANA, S.A. DE C.V. ','',' BME8109104S6','AV. INSURGENTES SUR 975 ','ALTAMIRA','TAMAULIPAS','03710','MEXICO','CONTADO',0,0,0,0,'','','','','','','','','','',0,0,0,'','','',''),(3,NULL,NULL,0,'',0,'',6,'KEREN LIZETH ECHEVERRIA GARCIA',1,'3','STYROPEK MEXICO, S.A. DE C.V. ','',' SME140605850','FERNANDO MONTES DE OCA INT.71 CONDESA CUAHUTEMOC','MEXICO','','','MEXICO','CONTADO',0,0,0,0,'','','','','','','','','','',0,0,0,'','','',''),(4,NULL,NULL,0,'',0,'',6,'KEREN LIZETH ECHEVERRIA GARCIA',1,'4','ALTAMIRA SERVICIOS DE INFRAESTRUCTURA, S.A. DE C.V. ','',' ASI981210D95','AV. DESARROLLO TEXTIL','ALTAMIRA','TAMAULIPAS',' 8960','MEXICO','CONTADO',0,0,0,0,'','','','','','','','','','',0,0,0,'','','',''),(5,NULL,'2020-06-17 15:43:25',0,'',0,'',6,'KEREN LIZETH ECHEVERRIA GARCIA',1,'5','THE CHEMOURS COMPANY MEXICO S. DE R.L. DE C.V. ','','DPM000525554','LAGO ZURICH 245 INT.402-403 ','ALTAMIRA','TAMAULIPAS','11529','MEXICO','CONTADO',0,0,0,0,'','','Vikas.juyal@chemours.com','52 01 800 0623 570','','','','','','',0,0,0,'','','',''),(6,NULL,NULL,0,'',0,'',6,'KEREN LIZETH ECHEVERRIA GARCIA',1,'6',' INEOS STYROLUTION MEXICANA, S.A. DE C.V. ','',' SME1101034E1','AVENIDA INSURGENTES SUR 859 INT.1102 NAPOLES ','CIUDAD DE MEXICO','','03810','MEXICO','CONTADO',0,0,0,0,'','','','','','','','','','',0,0,0,'','','',''),(7,NULL,'2020-06-19 23:52:46',0,'',0,'',1,'JESUS ARMANDO SEQUERA LOYA',1,'7',' INGENIO EL MANTE, S.A. DE C.V. ','',' IMA9211033S4','PASEO DE LOS TAMARINDOS P4 INT.60 BOSQUES DE LAS LOMAS','CIUDAD DE MEXICO','TAMAULIPAS','','MEXICO','CREDITO',30,0,0,0,'','','','','','','','','','',0,0,0,'','','',''),(8,NULL,NULL,0,'',0,'',6,'KEREN LIZETH ECHEVERRIA GARCIA',1,'8','GRUPO R DE EXPLORACION MARINA S.A. DE C.V. ','','GRE090525DUA','MARGEN DERECHO DEL RIO PANUCO','','VERACRUZ',' 9203','MEXICO','CONTADO',0,0,0,0,'','','','','','','','','','',0,0,0,'','','',''),(9,NULL,NULL,0,'',0,'',6,'KEREN LIZETH ECHEVERRIA GARCIA',1,'9','AB CALSA','',' ACA0401281AA','PASEO DE LA REFORMA 2620 INT.PISO 8 OF. 803-805','CIUDAD DE MEXICO','',' 1195','MEXICO','CONTADO',0,0,0,0,'','','','','','','','','','',0,0,0,'','','',''),(10,NULL,NULL,0,'',0,'',6,'KEREN LIZETH ECHEVERRIA GARCIA',1,'10',' COMPAÑÍA MINERA AUTLÁN, S.A.B. DE C.V.','','MAU531005M39','CARRETERA MEXICO-TAMPICO KM 457 ','',' 92018','92018','MEXICO','CONTADO',0,0,0,0,'','','','','','','','','','',0,0,0,'','','',''),(11,NULL,NULL,0,'',0,'',6,'KEREN LIZETH ECHEVERRIA GARCIA',1,'11','COMPAÑIA MINERA AUTLAN, S.A.B. DE C.V','','MAU531005M39','CARRETERA MEXICO-TAMPICO KM 457 ','','TAMAULIPAS','92018','MEXICO','CONTADO',0,0,0,0,'','','','','','','','','','',0,0,0,'','','',''),(12,NULL,NULL,0,'',0,'',6,'KEREN LIZETH ECHEVERRIA GARCIA',1,'12',' CIA. AZUCARERA DEL RIO GUAYALEJO, S.A. DE C.V. ','','ARG840523EW9','PASEO DE LOS TAMARINDOS 60 4TO PISO ','','','05120','MEXICO','CONTADO',0,0,0,0,'','','','','','','','','','',0,0,0,'','','',''),(13,NULL,NULL,0,'',0,'',6,'KEREN LIZETH ECHEVERRIA GARCIA',1,'13','ESEASA OFFSHORE S.A DE C.V ','',' EOF140113BG7','MONTCITO 38, DESPACHO 1, PISO 31 NAPOLES BENITO JUÁREZ','CIUDAD DE MEXICO','','03810','MEXICO','CREDITO',0,0,0,0,'','','','','','','','','','',0,0,0,'','','',''),(14,NULL,'2020-06-17 15:45:52',0,'',0,'',6,'KEREN LIZETH ECHEVERRIA GARCIA',1,'14','ALMACENAMIENTO Y LOGISTICA PORTUARIA S.A. DE C.V.','',' ALP070126EV4','MAR ROJO INT. 601 PUERTO INDUSTRIAL DE ALTAMIRA ','ALTAMIRA','TAMAULIPAS','89603','MEXICO','CONTADO',0,0,0,0,'','Erika Lee','Compras@alpasa.com.mx','52 01 800 0623 570','Erika Lee','Compras@alpasa.com.mx','52 01 800 0623 570','','','',0,0,0,'','','','');

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

insert  into `configuracion`(`id`,`serie_requisicion`,`folio_requisicion`,`serie_orden_compra`,`folio_orden_compra`,`serie_cotizacion`,`folio_cotizacion`,`serie_remision`,`folio_remision`,`serie_factura`,`folio_factura`,`autocompletar_totales`) values (1,'RQ',0,'OC',0,'CT',0,'RM',0,'FA',0,0);

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
  `observaciones` text CHARACTER SET utf8 COLLATE utf8_general_ci,
  `tipo_documento` varchar(100) DEFAULT NULL,
  `tipo_archivo` varchar(100) DEFAULT NULL,
  `razon_cancelar` text CHARACTER SET utf8 COLLATE utf8_general_ci,
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
  `importe_letra` text CHARACTER SET utf8 COLLATE utf8_general_ci,
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
  `condiciones_pago` text CHARACTER SET utf8 COLLATE utf8_general_ci,
  `condiciones_cotizacion` text CHARACTER SET utf8 COLLATE utf8_general_ci,
  `tiempo_tentrega` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `terminada_requisicion` tinyint(1) DEFAULT '0',
  `terminada_no_requisicion` tinyint(1) DEFAULT '0',
  `proyecto_id` int(10) DEFAULT NULL,
  `proyecto` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `solicitante` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `atencion` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `referencia_cliente` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `cotizacion_venta` */

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
  `descripcion` text CHARACTER SET utf8 COLLATE utf8_general_ci,
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
  `fecha_entrega` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `cotizacion_venta_det` */

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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Data for the table `empleado` */

insert  into `empleado`(`id`,`fecha_elaboracion`,`fecha_modificacion`,`empresa_id`,`empresa`,`unidad_id`,`unidad`,`usuario_id`,`usuario`,`password`,`activo`,`clave_empleado`,`nombre`,`apellido_paterno`,`apellido_materno`,`nss`,`domicilio`,`colonia`,`cp`,`ciudad`,`estado`,`pais`,`telefono_empresa`,`telefono_personal`,`email_empresa`,`email_personal`,`fecha_ingreso`,`autorizador`,`departamento_id`,`departamento`,`puesto_id`,`puesto`,`clasificacion_puesto`,`url_firma`) values (1,'2020-05-07 14:31:01','2020-05-07 14:31:01',NULL,NULL,NULL,NULL,NULL,'asequera','asequera.3321',1,NULL,'JESUS ARMANDO','SEQUERA','LOYA',NULL,NULL,NULL,NULL,NULL,'TAMAULIPAS','MEXICO','8333992231','8338503576','asequera@impeller.com.mx',NULL,NULL,1,NULL,'GERENCIA',NULL,'GERENTE GENERAL',NULL,NULL),(2,'2020-05-07 14:31:01','2020-05-07 14:31:01',NULL,NULL,NULL,NULL,NULL,'luis','luis.3389',1,NULL,'LUIS HUMBERTO','GOMEZ','TIBURCIO',NULL,NULL,NULL,NULL,NULL,'TAMAULIPAS','MEXICO','8333992231','8333140906','luis@impeller.com.mx',NULL,NULL,1,NULL,'GERENCIA',NULL,'GERENTE ADMINISTRATIVO',NULL,NULL),(3,'2020-05-07 14:31:01','2020-05-25 11:46:51',NULL,NULL,NULL,NULL,NULL,'gabriela','gabriela.2245',1,NULL,'GABRIELA ALEJANDRA','BARRIOS','REYNA',NULL,NULL,NULL,NULL,NULL,'TAMAULIPAS','MEXICO','8333992231','8331100147','gabriela@impeller.com.mx',NULL,'2020-05-25 00:00:00',1,NULL,'VENTAS',NULL,'GERENTE DE VENTAS',NULL,NULL),(4,'2020-05-07 14:31:01','2020-05-07 14:31:01',NULL,NULL,NULL,NULL,NULL,'valeria','valeria.1214',1,NULL,'VALERIA ALEJANDRINA','ROSARIO','JIMENEZ',NULL,NULL,NULL,NULL,NULL,'TAMAULIPAS','MEXICO','8333992231','8334032908','valeria@impeller.com.mx',NULL,NULL,0,NULL,'VENTAS',NULL,'AUXILIAR DE VENTAS',NULL,NULL),(5,'2020-05-07 14:31:01','2020-05-07 14:31:01',NULL,NULL,NULL,NULL,NULL,'eder','eder.8574',1,NULL,'EDER ABEL','MARISCAL','ESCAMILLA',NULL,NULL,NULL,NULL,NULL,'TAMAULIPAS','MEXICO','8333992231','8332896777','eder@impeller.com.mx',NULL,NULL,0,NULL,'VENTAS',NULL,'AUXILIAR DE VENTAS',NULL,NULL),(6,'2020-05-07 14:31:01','2020-05-07 14:31:01',NULL,NULL,NULL,NULL,NULL,'keren','keren5510.3698',1,NULL,'KEREN LIZETH','ECHEVERRIA','GARCIA',NULL,NULL,NULL,NULL,NULL,'TAMAULIPAS','MEXICO','8333992231','8333992231','keren@impeller.com.mx',NULL,NULL,0,NULL,'COMPRAS',NULL,'AUXILIAR DE COMPRAS',NULL,NULL),(7,'2020-05-07 14:31:01','2020-05-07 14:31:01',NULL,NULL,NULL,NULL,NULL,'ameraz','alejandro.2200',1,NULL,'OMAR ALEJANDRO','MERAZ','LARA',NULL,NULL,NULL,NULL,NULL,'TAMAULIPAS','MEXICO','8333992231','8334445525','ameraz@impeller.com.mx',NULL,NULL,0,NULL,'ALMACEN',NULL,'AUXILIAR DE ALMACEN',NULL,NULL),(8,'2020-05-07 14:31:01','2020-05-07 14:31:01',NULL,NULL,NULL,NULL,NULL,'hreyna','hreyna.9865',1,NULL,'LUIS HUMBERTO','REYNA','GURROLA',NULL,NULL,NULL,NULL,NULL,'TAMAULIPAS','MEXICO','8333992231','8334021468','hreyna@impeller.com.mx',NULL,NULL,0,NULL,'OPERACIONES',NULL,'GERENTE DE OPERACIONES',NULL,NULL),(9,'2020-05-07 14:31:01','2020-05-07 14:31:01',NULL,NULL,NULL,NULL,NULL,'rcruz','rcruz.9905',1,NULL,'RUBEN','CRUZ','RAVIZE',NULL,NULL,NULL,NULL,NULL,'TAMAULIPAS','MEXICO','8333992231',NULL,'rcruz@impeller.com.mx',NULL,NULL,0,NULL,'OPERACIONES',NULL,'AUXILIAR DE OPERACIONES',NULL,NULL);

/*Table structure for table `node_file` */

DROP TABLE IF EXISTS `node_file`;

CREATE TABLE `node_file` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `parent_id` int(10) DEFAULT NULL,
  `parent_folio` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `nombre` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `folio` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `cliente_proveedor_id` int(10) DEFAULT NULL,
  `cliente_proveedor` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `tipo_documento` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `url` text CHARACTER SET utf8 COLLATE utf8_general_ci,
  `extension` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `node_file` */

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
  `observaciones` text CHARACTER SET utf8 COLLATE utf8_general_ci,
  `tipo_documento` varchar(100) DEFAULT NULL,
  `tipo_archivo` varchar(100) DEFAULT NULL,
  `razon_cancelar` text CHARACTER SET utf8 COLLATE utf8_general_ci,
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
  `cond_pago` text CHARACTER SET utf8 COLLATE utf8_general_ci,
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
  `descripcion` text CHARACTER SET utf8 COLLATE utf8_general_ci,
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
  `contacto_compra_ext` varchar(7) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `contacto_compra_nombre` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `contacto_compra_email` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `contacto_contabilidad_telefono` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `contacto_contabilidad_nombre` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `contacto_contabilidad_email` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `no_cuenta_1` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `clave_interbancaria_1` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `banco_1` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sucursal_1` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `no_cuenta_2` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `clave_interbancaria_2` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `banco_2` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sucursal_2` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `no_cuenta_3` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `clave_interbancaria_3` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `banco_3` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sucursal_3` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

/*Data for the table `proveedor` */

insert  into `proveedor`(`id`,`fecha_elaboracion`,`fecha_modificacion`,`empresa_id`,`empresa`,`unidad_id`,`unidad`,`usuario_id`,`usuario`,`no_paper`,`activo`,`clasificacion_proveedor`,`tipo_proveedor`,`dias_credito`,`clave_proveedor`,`razon_social`,`rfc`,`domicilio`,`ciudad`,`estado`,`pais`,`cp`,`contacto_compra_telefono`,`contacto_compra_ext`,`contacto_compra_nombre`,`contacto_compra_email`,`contacto_contabilidad_telefono`,`contacto_contabilidad_nombre`,`contacto_contabilidad_email`,`no_cuenta_1`,`clave_interbancaria_1`,`banco_1`,`sucursal_1`,`no_cuenta_2`,`clave_interbancaria_2`,`banco_2`,`sucursal_2`,`no_cuenta_3`,`clave_interbancaria_3`,`banco_3`,`sucursal_3`) values (1,'2020-05-28 19:50:34','2020-05-28 19:50:34',0,'',0,'',0,'',1,1,'PROVEEDOR','CONTADO',0,'1','ATM VALVULAS S.A. DE C.V.','AVA080702G7','PEÑA GUERRA NO.. 417 COL. CONSTITUYENTES DE QUERETARO SECTOR 1, SAN NICOLAS DE LOS GARZA.','','MONTERREY, NUEVO LEON.','MEXICO','6649','(81) 8376-1100','','JACKELINE LOZANO',' jlozanon@atmvalvulas.com','','','cobranza@atmvalvulas.com','','','','','','','','','','','',''),(2,'2020-05-28 20:09:29','2020-05-28 20:09:29',0,'',0,'',0,'',1,1,'PROVEEDOR','CONTADO',0,'2','ACEROX','PICN97072231','BELISARIO DOMINGUEZ NO. 114, COL. NUEVO PROGRESO.','TAMPICO','TAMAULIPAS','MEXICO','89318','833-462-46-00','','NELSON PIERDANT NIÑO','aceroxventas@outlook.com','','','','','','','','','','','','','','',''),(3,'2020-05-28 20:25:40','2020-05-28 20:25:40',0,'',0,'',0,'',1,1,'PROVEEDOR','CREDITO',0,'3',' ABSORMEX CMPC TISSUE, S.A. DE C.V.','IPG970717QU9','AVENIDA INDUSTRIAL HUMBERTO LOBO NO. 9013 ','','','MEXICO','66023','','','','','','','','','','','','','','','','','','',''),(4,'2020-05-28 21:07:57','2020-05-28 21:07:57',0,'',0,'',0,'',1,1,'PROVEEDOR','CONTADO',0,'4','AGIMEX','','','','','MEXICO','','(55) 85-60-06-10','','ING. ANTONIO GARCIA','ventas_agitadoresindustriales@prodigy.net.mx','','','','','','','','','','','','','','',''),(5,'2020-05-28 21:19:14','2020-05-28 21:19:14',0,'',0,'',0,'',1,1,'PROVEEDOR','CONTADO',0,'5','AMSI-TA APLICACIONES MANTENIMIENTO Y SERVICIOS INDUSTRIALES.','TOAH881104TM','','CIUDAD MADERO','TAMAULIPAS','MEXICO','','(833)154-9907','','HECTOR TORRES','amsi-ta.adm@outlook.com','','','','','','','','','','','','','','',''),(6,'2020-05-28 21:26:04','2020-06-04 19:48:26',0,'',0,'',0,'',1,1,'PROVEEDOR','CONTADO',0,'6','ANVA SOLUCIONES INDUSTRIALES S.A. DE C.V.','ASI170111PM7','AHUEHUETE NO. 339 FRACCIONAMIENTO ARECAS','ALTAMIRA','TAMAULIPAS','MEXICO','89602','833 312 86 54','','ING. ALDO ISRAEL LIMON HERNANDEZ','aldo.limon@anvasi.com ','','','','','','','','','','','','','','',''),(7,'2020-05-28 21:42:40','2020-05-28 21:42:40',0,'',0,'',0,'',1,1,'PROVEEDOR','CONTADO',0,'7','BIRMEX S.A. DE C.V.','BYR130304JR5','','','QUERETARO','MEXICO','76100','442 161 2065 ','','ING. LUIS HERNANDEZ','luis.hernandez@byrmex.com','','','','','','','','','','','','','','',''),(8,'2020-06-04 19:15:42','2020-06-04 19:15:42',0,'',0,'',0,'',1,1,'PROVEEDOR','CONTADO',0,'8','TORNILLOS Y HERRAMIENTAS DOSA S.A. DE C.V.','THD041214TG1','','TAMPICO','TAMAULIPAS','MEXICO','','(833)2-26-50-35','','HILDA ANGELICA HERRERA','hildadosa@hotmail.com.mx','','','','','','','','','','','','','','',''),(9,'2020-06-04 19:20:38','2020-06-04 19:20:38',0,'',0,'',0,'',1,1,'PROVEEDOR','CONTADO',0,'9','EQUIPUMP S.A. DE C.V.','EQU970402NSA',' RICARDO FLORES MAGON NO. 518, COL. SANTA MA. LA RIBERA',' CIUDAD DE MEXICO','','MEXICO',' 0640','55-5541-1331','','MIGUEL MEJIA','miguelmejia@equi-pump.com','','','','','','','','','','','','','','',''),(10,'2020-06-04 19:38:32','2020-06-04 19:38:32',0,'',0,'',0,'',1,1,'PROVEEDOR','CREDITO',30,'10','TRANSMISIONES Y EQUIPOS INDUSTRIALES, S.A. DE C.V.','TEI820204S67','DAKOTA NO. 157, COL. NAPOLES, ','BENITO JUAREZ','CIUDAD DE MÉXICO','MEXICO','03810','55 5687-7514','','ARTURO ALVAREZ',' tecnicos@teisa.com.mx','','','cobranza@teisa.com.mx','','','','','','','','','','','',''),(11,'2020-06-04 19:45:48','2020-06-04 19:45:48',0,'',0,'',0,'',1,1,'PROVEEDOR','CONTADO',0,'11','RODAMIENTOS Y ACCESORIOS S.A. DE C.V.','RAC870406P40','AV. NOGALAR NO. 107, COL. CUAUHTEMOC, SAN NICOLAS DE LOS GARZA','NUEVO LEON','MONTERREY','MEXICO','','833 850 80 57','','JORGE HERNANDEZ DEL ANGEL','Jorge.hernandez@ryasa.com.mx','','','facturaryasa@ryasa.com.mx','','','','','','','','','','','',''),(12,'2020-06-04 19:53:29','2020-06-04 19:53:29',0,'',0,'',0,'',1,1,'PROVEEDOR','CONTADO',0,'12','MATERIALES INDUSTRIALES DE MEXICO, S.A. DE C.V. ',' MIM7207049C1','','ALTAMIRA','TAMAULIPAS','MEXICO','','833-346-6869','','ANABEL MERAZ CONTRERAS','anabel.meraz@grupo-mim.com','','','facturas@mim.fep.com.mx','','','','','','','','','','','',''),(13,'2020-06-04 20:01:48','2020-06-04 20:01:48',0,'',0,'',0,'',1,1,'PROVEEDOR','CONTADO',0,'13','COHISER DE TAMPICO S.A. DE C.V.',' XAXX01010100',' CARRETERA TAMPICO MANTE NO. 2309-B, COL. DEL BOSQUE','TAMPICO','TAMAULIPAS','MEXICO',' 8931','(833) 132 0490','','ZURY CANDELARIO',' telemarketing@cohisertampico.com.mx','','','','','','','','','','','','','','',''),(14,'2020-06-04 20:15:57','2020-06-04 20:15:57',0,'',0,'',0,'',1,1,'PROVEEDOR','CONTADO',0,'14','WM. W. MEYER & SONS, INC.','','1700 BLVD. LIVERTYVILLE','','ILLINOIS','ESTADOS UNIDOS','','9847-918-0111','','JOE KOWALSKI','JKowalski@wmwmeyer.com','','','','','','','','','','','','','','',''),(15,'2020-06-04 20:24:10','2020-06-04 20:24:10',0,'',0,'',0,'',1,1,'PROVEEDOR','CONTADO',0,'15','BOMBAS GRUNDFOS DE MEXICO S.A. DE C.V.','BGM931210HJ5','BLVD. TLC NO. 15 PARQUE IND. STIVA AEROPUERTO ','APODACA','NUEVO LEON','MEXICO',' 6660','(52) 81 8144 4000','','ISAAC GARCIA ','servicioaclientes-mx@sales.grundfos.com','','','kaluna@grundfos.com','','','','','','','','','','','',''),(16,'2020-06-04 20:31:50','2020-06-04 20:31:50',0,'',0,'',0,'',1,1,'PROVEEDOR','CONTADO',0,'16','POWER PROCESS CONTROL S.A. DE C.V.','PPC0209114F0','','TAMPICO','TAMAULIPAS','MEXICO','','(833) 241 1880','','Ericka Gabriela Uribe Rusca ','euribe@ppcsesco.com','','MELISSA GEA','mgea@ppcsesco.com>','','','','','','','','','','','',''),(17,'2020-06-04 20:43:02','2020-06-04 20:43:02',0,'',0,'',0,'',1,1,'PROVEEDOR','CREDITO',30,'17','PROPART INC.','02-0580-220','','AUSTIN','TEXAS','ESTADOS UNIDOS','','(512) 266-8276 ','','JUAN GUTIERREZ','juan@propartusa.com','O 512.266.8276','LISA LAMB','lisa@propartusa.com','','','','','','','','','','','',''),(18,'2020-06-04 20:47:04','2020-06-04 20:47:04',0,'',0,'',0,'',1,1,'PROVEEDOR','CONTADO',0,'18','LOGOS INDUSTRIAL, S.A. DE C.V.',' LIN001107J30','SAN PEDRO Nº 223 COLONIA FRACCIONAMIENTO SAN ANGEL ALTAMIRA ','ALTAMIRA','TAMAULIPAS','MEXICO','89604','(833) 2-26-64-98 ','','JAVIER PEREZ NUÑEZ','javier.perez@logosindustrial.com.mx','','','','','','','','','','','','','','',''),(19,'2020-06-04 20:53:00','2020-06-04 20:53:00',0,'',0,'',0,'',1,1,'PROVEEDOR','CONTADO',0,'19','LA CASA DEL BOMBERO','TOMP500629GP0','AVENIDA MONTERREY 701-3 COL: ENRIQUE CARDENAS GONZALEZ ','TAMPICO','TAMAULIPAS','MEXICO',' 8930','(833)-3-06-23-92','','KARINA SILVA SALVADOR',' casadelbombero@hotmail.com','','','','','','','','','','','','','','','');

/*Table structure for table `proyecto` */

DROP TABLE IF EXISTS `proyecto`;

CREATE TABLE `proyecto` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `fecha_elaboracion` datetime DEFAULT NULL,
  `fecha_modificacion` datetime DEFAULT NULL,
  `empresa_id` int(10) DEFAULT NULL,
  `empresa` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `unidad_id` int(10) DEFAULT NULL,
  `unidad` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `usuario_id` int(10) DEFAULT NULL,
  `usuario` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `activo` tinyint(1) DEFAULT '1',
  `no_paper` tinyint(1) DEFAULT '1',
  `cliente_id` int(10) DEFAULT NULL,
  `fecha_inicio` datetime DEFAULT NULL,
  `fecha_fin` datetime DEFAULT NULL,
  `nombre` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `descripcion` text CHARACTER SET utf8 COLLATE utf8_general_ci,
  `nombre_cliente` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `proyecto` */

insert  into `proyecto`(`id`,`fecha_elaboracion`,`fecha_modificacion`,`empresa_id`,`empresa`,`unidad_id`,`unidad`,`usuario_id`,`usuario`,`activo`,`no_paper`,`cliente_id`,`fecha_inicio`,`fecha_fin`,`nombre`,`descripcion`,`nombre_cliente`) values (1,NULL,NULL,0,'',0,'',2,'LUIS HUMBERTO GOMEZ TIBURCIO',1,1,12,NULL,NULL,'SIN PROYECTO','','');

/*Table structure for table `remision_entrega` */

DROP TABLE IF EXISTS `remision_entrega`;

CREATE TABLE `remision_entrega` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `fecha_elaboracion` datetime DEFAULT NULL,
  `fecha_modificacion` datetime DEFAULT NULL,
  `empresa_id` int(10) DEFAULT NULL,
  `empresa` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `unidad_id` int(10) DEFAULT NULL,
  `unidad` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `usuario_id` int(10) DEFAULT NULL,
  `usuario` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `estado_doc` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `estado` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `folio` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `serie` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `observaciones` text CHARACTER SET utf8 COLLATE utf8_general_ci,
  `tipo_documento` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `tipo_archivo` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `razon_cancelar` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `cliente_id` int(10) DEFAULT NULL,
  `cliente` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `cliente_rfc` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `metodo_pago` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `moneda` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `tipo_cambio` double DEFAULT NULL,
  `importe` double DEFAULT NULL,
  `descuento` double DEFAULT NULL,
  `subtotal` double DEFAULT NULL,
  `iva` double DEFAULT NULL,
  `total` double DEFAULT NULL,
  `porc_iva` int(10) DEFAULT NULL,
  `importe_letra` text CHARACTER SET utf8 COLLATE utf8_general_ci,
  `activo` tinyint(1) DEFAULT '1',
  `entrega_id` int(10) DEFAULT NULL,
  `entrega` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `direccion_tentrega` text CHARACTER SET utf8 COLLATE utf8_general_ci,
  `orden_compra_partida_id` int(10) DEFAULT NULL,
  `orden_compra_id` int(10) DEFAULT NULL,
  `folio_orden_compra` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `cotizacion_id` int(10) DEFAULT NULL,
  `folio_cotizacion` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `fecha_entrega` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `remision_entrega` */

/*Table structure for table `remision_entrega_det` */

DROP TABLE IF EXISTS `remision_entrega_det`;

CREATE TABLE `remision_entrega_det` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `folio` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `documento_id` int(10) DEFAULT NULL,
  `empresa_id` int(10) DEFAULT NULL,
  `empresa` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `unidad_id` int(10) DEFAULT NULL,
  `unidad` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `usuario_id` int(10) DEFAULT NULL,
  `usuario` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `no_partida` int(10) DEFAULT NULL,
  `fecha_alta` datetime DEFAULT NULL,
  `cantidad` int(10) DEFAULT NULL,
  `producto_id` int(10) DEFAULT NULL,
  `descripcion` text CHARACTER SET utf8 COLLATE utf8_general_ci,
  `unidad_medida` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `porc_iva` int(10) DEFAULT NULL,
  `precio_unitario` double DEFAULT NULL,
  `importe` double DEFAULT NULL,
  `descuento` double DEFAULT NULL,
  `subtotal` double DEFAULT NULL,
  `iva` double DEFAULT NULL,
  `total` double DEFAULT NULL,
  `servicio` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `remision_entrega_det` */

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
  `importe_letra` text CHARACTER SET utf8 COLLATE utf8_general_ci,
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `requisicion` */

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
  `descripcion` text CHARACTER SET utf8 COLLATE utf8_general_ci,
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `requisicion_det` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
