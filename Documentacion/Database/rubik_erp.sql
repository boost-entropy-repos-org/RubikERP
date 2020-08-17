/*
SQLyog Ultimate v11.13 (64 bit)
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
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

/*Data for the table `cliente` */

insert  into `cliente`(`id`,`fecha_elaboracion`,`fecha_modificacion`,`empresa_id`,`empresa`,`unidad_id`,`unidad`,`usuario_id`,`usuario`,`activo`,`clave_cliente`,`razon_social`,`nombre_corto`,`rfc`,`domicilio`,`ciudad`,`estado`,`cp`,`pais`,`tipo_cliente`,`dias_credito`,`limite_credito`,`saldo_a_favor`,`saldo_pendiente`,`no_contable`,`contacto_general_nombre`,`contacto_general_email`,`contacto_telefono_general`,`contacto_venta_nombre`,`contacto_venta_email`,`contacto_venta_telefono`,`contacto_contabilidad_nombre`,`contacto_contabilidad_email`,`contacto_contabilidad_telefono`,`matriz`,`sucursal`,`id_matriz`,`cuenta_clientes`,`cuenta_pagos`,`cuenta_anticipos`,`cuenta_honorarios`) values (1,NULL,'2020-08-12 22:10:37',0,'',0,'',5,'EDER ABEL MARISCAL ESCAMILLA',1,'1','MEXICHEM COMPUESTOS, S.A. DE C.V.','','MCO061215JR5','AUTOPISTA ALTAMIRA, PUERTO INDUSTRIAL DE ALTAMIRA.','ALTAMIRA','TAMAULIPAS','89603','MEXICO','CREDITO',60,0,0,0,'','','','','','','','','','',0,0,0,'','','',''),(2,NULL,NULL,0,'',0,'',6,'KEREN LIZETH ECHEVERRIA GARCIA',1,'2','BASF MEXICANA, S.A. DE C.V.','',' BME8109104S6','AV. INSURGENTES SUR 975 ','ALTAMIRA','TAMAULIPAS','03710','MEXICO','CONTADO',0,0,0,0,'','','','','','','','','','',0,0,0,'','','',''),(3,NULL,'2020-07-21 20:26:26',0,'',0,'',4,'VALERIA ALEJANDRINA ROSARIO JIMENEZ',1,'3','STYROPEK MEXICO, S.A. DE C.V.','','SME140605850','FERNANDO MONTES DE OCA INT.71 CONDESA CUAHUTEMOC','MEXICO','','','MEXICO','CREDITO',60,0,0,0,'','','','','','','','','','',0,0,0,'','','',''),(4,NULL,NULL,0,'',0,'',6,'KEREN LIZETH ECHEVERRIA GARCIA',1,'4','ALTAMIRA SERVICIOS DE INFRAESTRUCTURA, S.A. DE C.V.','',' ASI981210D95','AV. DESARROLLO TEXTIL','ALTAMIRA','TAMAULIPAS',' 8960','MEXICO','CONTADO',0,0,0,0,'','','','','','','','','','',0,0,0,'','','',''),(5,NULL,'2020-06-17 15:43:25',0,'',0,'',6,'KEREN LIZETH ECHEVERRIA GARCIA',1,'5','THE CHEMOURS COMPANY MEXICO S. DE R.L. DE C.V.','','DPM000525554','LAGO ZURICH 245 INT.402-403 ','ALTAMIRA','TAMAULIPAS','11529','MEXICO','CONTADO',0,0,0,0,'','','Vikas.juyal@chemours.com','52 01 800 0623 570','','','','','','',0,0,0,'','','',''),(6,NULL,NULL,0,'',0,'',6,'KEREN LIZETH ECHEVERRIA GARCIA',1,'6','INEOS STYROLUTION MEXICANA, S.A. DE C.V.','',' SME1101034E1','AVENIDA INSURGENTES SUR 859 INT.1102 NAPOLES ','CIUDAD DE MEXICO','','03810','MEXICO','CONTADO',0,0,0,0,'','','','','','','','','','',0,0,0,'','','',''),(7,NULL,'2020-06-19 23:52:46',0,'',0,'',1,'JESUS ARMANDO SEQUERA LOYA',1,'7','INGENIO EL MANTE, S.A. DE C.V.','',' IMA9211033S4','PASEO DE LOS TAMARINDOS P4 INT.60 BOSQUES DE LAS LOMAS','CIUDAD DE MEXICO','TAMAULIPAS','','MEXICO','CREDITO',30,0,0,0,'','','','','','','','','','',0,0,0,'','','',''),(8,NULL,NULL,0,'',0,'',6,'KEREN LIZETH ECHEVERRIA GARCIA',1,'8','GRUPO R DE EXPLORACION MARINA S.A. DE C.V.','','GRE090525DUA','MARGEN DERECHO DEL RIO PANUCO','','VERACRUZ',' 9203','MEXICO','CONTADO',0,0,0,0,'','','','','','','','','','',0,0,0,'','','',''),(9,NULL,NULL,0,'',0,'',6,'KEREN LIZETH ECHEVERRIA GARCIA',1,'9','AB CALSA','',' ACA0401281AA','PASEO DE LA REFORMA 2620 INT.PISO 8 OF. 803-805','CIUDAD DE MEXICO','',' 1195','MEXICO','CONTADO',0,0,0,0,'','','','','','','','','','',0,0,0,'','','',''),(10,NULL,NULL,0,'',0,'',6,'KEREN LIZETH ECHEVERRIA GARCIA',1,'10','COMPAÑÍA MINERA AUTLÁN, S.A.B. DE C.V.','','MAU531005M39','CARRETERA MEXICO-TAMPICO KM 457 ','',' 92018','92018','MEXICO','CONTADO',0,0,0,0,'','','','','','','','','','',0,0,0,'','','',''),(11,NULL,NULL,0,'',0,'',6,'KEREN LIZETH ECHEVERRIA GARCIA',1,'11','COMPAÑIA MINERA AUTLAN, S.A.B. DE C.V','','MAU531005M39','CARRETERA MEXICO-TAMPICO KM 457 ','','TAMAULIPAS','92018','MEXICO','CONTADO',0,0,0,0,'','','','','','','','','','',0,0,0,'','','',''),(12,NULL,NULL,0,'',0,'',6,'KEREN LIZETH ECHEVERRIA GARCIA',1,'12','CIA. AZUCARERA DEL RIO GUAYALEJO, S.A. DE C.V.','','ARG840523EW9','PASEO DE LOS TAMARINDOS 60 4TO PISO ','','','05120','MEXICO','CONTADO',0,0,0,0,'','','','','','','','','','',0,0,0,'','','',''),(13,NULL,NULL,0,'',0,'',6,'KEREN LIZETH ECHEVERRIA GARCIA',1,'13','ESEASA OFFSHORE S.A DE C.V','',' EOF140113BG7','MONTCITO 38, DESPACHO 1, PISO 31 NAPOLES BENITO JUÁREZ','CIUDAD DE MEXICO','','03810','MEXICO','CREDITO',0,0,0,0,'','','','','','','','','','',0,0,0,'','','',''),(14,NULL,'2020-06-17 15:45:52',0,'',0,'',6,'KEREN LIZETH ECHEVERRIA GARCIA',1,'14','ALMACENAMIENTO Y LOGISTICA PORTUARIA S.A. DE C.V.','',' ALP070126EV4','MAR ROJO INT. 601 PUERTO INDUSTRIAL DE ALTAMIRA ','ALTAMIRA','TAMAULIPAS','89603','MEXICO','CONTADO',0,0,0,0,'','Erika Lee','Compras@alpasa.com.mx','52 01 800 0623 570','Erika Lee','Compras@alpasa.com.mx','52 01 800 0623 570','','','',0,0,0,'','','',''),(15,NULL,NULL,0,'',0,'',5,'EDER ABEL MARISCAL ESCAMILLA',1,'15','CREACIONES IGUAZU S.A. DE C.V.','','CIG041222UI7','C2 1100 FRACC. FIMEX','ALTAMIRA','TAMAULIPAS','89603','MEXICO','CREDITO',30,31,0,0,'','','','','Ing.  Javier Cárdenas Arellano','','','','','',0,0,0,'','','',''),(16,NULL,NULL,0,'',0,'',8,'LUIS HUMBERTO REYNA GURROLA',1,'16','INGENIO EL MANTER','','','','','','','MEXICO','CONTADO',0,0,0,0,'','RAFAEL LOPEZ','rafael.lopez@gsaenz.com.mx','','','','','','','',0,0,0,'','','',''),(17,NULL,'2020-08-12 22:10:19',0,'',0,'',5,'EDER ABEL MARISCAL ESCAMILLA',1,'17','NALCO DE MEXICO S. DE R. L. DE C.V.','','NME900531HM0','AV. MONTERREY, MIRAMAR SECTOR 1','ALTAMIRA','TAMAULIPAS','89604','MEXICO','CREDITO',0,63,0,0,'','Ing. Juan Jose Izaguirre ','juanjose.izaguirre@ecolab.com','','Ing. Estefany Ríos Ponce','estefany.rios@ecolab.com','','','','',0,0,0,'','','',''),(18,NULL,NULL,0,'',0,'',3,'GABRIELA ALEJANDRA BARRIOS REYNA',1,'18','SOLENIS TECHNOLOGIES MEXICO, S. DE R.L. DE C.V.','','','AV. SARA 4553 COL GUADALUPE TEPEYAC','CD. DE MEXICO','','07840','MEXICO','CREDITO',30,0,0,0,'','ING. SARA CARRIZALES','','','','','','','','',0,0,0,'','','',''),(19,NULL,NULL,0,'',0,'',8,'LUIS HUMBERTO REYNA GURROLA',1,'19','MEXICO CARBON MANUFACTURING','','','BLVD DE LOS RÍOS 9600','ALTAMIRA','TAMAULIPAS','','MEXICO','CREDITO',30,0,0,0,'','compras','compras@mxcb.co.mx','','Alejandro Leal','','','','','',0,0,0,'','','',''),(20,NULL,NULL,0,'',0,'',2,'LUIS HUMBERTO GOMEZ TIBURCIO',1,'20','REFINERIA MADERO','','PTI151101TE5','FRANCISCO I MADERO #3201','MADERO','TAMAULIPAS','89540','MEXICO','CREDITO',30,0,0,0,'','','','','Ing. Jorge A. Silva Garcia','jorge.alfredo.silva@pemex.com','8332291100','','','',0,0,0,'','','',''),(21,NULL,NULL,0,'',0,'',5,'EDER ABEL MARISCAL ESCAMILLA',1,'21','INGENIO TAMAZULA,S.A. DE C.V.','','ITA840522FS4','PASEO DE LOS TAMARINDOS P-4 INT.60 BOSQUES DE LAS LOMAS','CUAJIMALPA DE MORELOS','CIUDAD DE MÉXICO','','MEXICO','CREDITO',60,90,0,0,'','Julio Moreno','julio.moreno@gsaenz.com.mx ','55 7908 3294 ','','','','','','',0,0,0,'','','',''),(22,NULL,NULL,0,'',0,'',8,'LUIS HUMBERTO REYNA GURROLA',1,'22','VOPAK','','','PUERTO INDUSTRIAL','ALTAMIRA','TAMAULIPAS','','MEXICO','CREDITO',30,0,0,0,'','Carlos Rivera','','833 2 6020 71','compras','','','Angélica Arteaga','','',0,0,0,'','','','');

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

insert  into `configuracion`(`id`,`serie_requisicion`,`folio_requisicion`,`serie_orden_compra`,`folio_orden_compra`,`serie_cotizacion`,`folio_cotizacion`,`serie_remision`,`folio_remision`,`serie_factura`,`folio_factura`,`autocompletar_totales`) values (1,'RQ',5,'OC',2,'CT',4,'RM',0,'FA',0,0);

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
  `referencia_cliente` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `cotizacion_venta` */

insert  into `cotizacion_venta`(`id`,`fecha_elaboracion`,`fecha_modificacion`,`empresa_id`,`empresa`,`unidad_id`,`unidad`,`usuario_id`,`usuario`,`estado_doc`,`estado`,`folio`,`serie`,`observaciones`,`tipo_documento`,`tipo_archivo`,`razon_cancelar`,`cliente_id`,`cliente`,`cliente_rfc`,`metodo_pago`,`moneda`,`tipo_cambio`,`importe`,`descuento`,`subtotal`,`iva`,`total`,`porc_iva`,`importe_letra`,`activo`,`emisor_rfc`,`emisor_nombre`,`emisor_calle`,`emisor_numero_exterior`,`emisor_numero_interior`,`emisor_codigo_postal`,`emisor_colonia`,`emisor_municipio`,`emisor_estado`,`emisor_pais`,`receptor_nombre`,`receptor_calle`,`receptor_numero_exterior`,`receptor_numero_interior`,`receptor_codigo_postal`,`receptor_colonia`,`receptor_municipio`,`receptor_estado`,`receptor_pais`,`vendedor_id`,`vendedor`,`dias_caduca`,`condiciones_pago`,`condiciones_cotizacion`,`tiempo_tentrega`,`terminada_requisicion`,`terminada_no_requisicion`,`proyecto_id`,`proyecto`,`solicitante`,`atencion`,`referencia_cliente`) values (1,'2020-08-16 22:23:43','2020-08-16 22:24:19',0,'',0,'',1,'JESUS ARMANDO SEQUERA LOYA','TERMINADO','','CT00002','','','COTIZACION DE VENTA','PDF','',9,'AB CALSA',' ACA0401281AA','EFECTIVO','PESOS',1.00,100000.00,0.0,100000.00,16000.0,116000.00,16,'CIENTO DIECISEIS MIL Y 00/100  PESOS MEXICANOS.',1,'IWS140818UY0','IMPELLER WELL SERVICES, S.A. DE C.V.','Emilio Castán # 203 Col. 2 de Junio',NULL,NULL,'89327',NULL,'Tampico','Tamaulipas','MEXICO','AB CALSA','PASEO DE LA REFORMA 2620 INT.PISO 8 OF. 803-805',NULL,NULL,' 1195',NULL,'CIUDAD DE MEXICO','','MEXICO',NULL,'JESUS ARMANDO SEQUERA LOYA',7,'CONTADO','','',0,0,1,'SIN PROYECTO','','',''),(2,'2020-08-16 23:33:48','2020-08-16 23:33:48',0,'',0,'',1,'JESUS ARMANDO SEQUERA LOYA','TERMINADO','','CT00003','','','COTIZACION DE VENTA','PDF','',9,'AB CALSA',' ACA0401281AA','EFECTIVO','PESOS',1.00,5500.00,0.0,5500.00,880.0,6380.00,16,'SEIS MIL TRECIENTOS OCHENTA Y 00/100  PESOS MEXICANOS.',1,'IWS140818UY0','IMPELLER WELL SERVICES, S.A. DE C.V.','Emilio Castán # 203 Col. 2 de Junio',NULL,NULL,'89327',NULL,'Tampico','Tamaulipas','MEXICO','AB CALSA','PASEO DE LA REFORMA 2620 INT.PISO 8 OF. 803-805',NULL,NULL,' 1195',NULL,'CIUDAD DE MEXICO','','MEXICO',NULL,'JESUS ARMANDO SEQUERA LOYA',7,'CONTADO','','',0,0,1,'SIN PROYECTO','','',''),(3,'2020-08-16 23:45:46','2020-08-16 23:45:46',0,'',0,'',1,'JESUS ARMANDO SEQUERA LOYA','TERMINADO','','CT00004','','','COTIZACION DE VENTA','PDF','',9,'AB CALSA',' ACA0401281AA','EFECTIVO','PESOS',1.00,5000.00,0.0,5000.00,800.0,5800.00,16,'CINCO MIL OCHOCIENTOS Y 00/100  PESOS MEXICANOS.',1,'IWS140818UY0','IMPELLER WELL SERVICES, S.A. DE C.V.','Emilio Castán # 203 Col. 2 de Junio',NULL,NULL,'89327',NULL,'Tampico','Tamaulipas','MEXICO','AB CALSA','PASEO DE LA REFORMA 2620 INT.PISO 8 OF. 803-805',NULL,NULL,' 1195',NULL,'CIUDAD DE MEXICO','','MEXICO',NULL,'JESUS ARMANDO SEQUERA LOYA',7,'CONTADO','','',0,0,1,'SIN PROYECTO','','','');

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

insert  into `cotizacion_venta_det`(`id`,`folio`,`documento_id`,`empresa_id`,`empresa`,`unidad_id`,`unidad`,`usuario_id`,`usuario`,`no_partida`,`fecha_alta`,`cantidad`,`producto_id`,`descripcion`,`unidad_medida`,`porc_iva`,`precio_unitario`,`importe`,`descuento`,`subtotal`,`iva`,`total`,`servicio`,`no_parte`,`no_serie`,`modelo`,`marca`,`codigo_proveedor`,`codigo_interno`,`facturada`,`entregada`,`requisicion_id`,`fecha_entrega`) values (1,'CT00002',1,0,'',0,'',1,'JESUS ARMANDO SEQUERA LOYA',0,'2020-08-16 22:24:15',10,1,'COPLE ES10 OMEGA REXNOR','PIEZA',16,10000,100000,0,NULL,NULL,NULL,0,'','','ES10','OMEGA REXNOR ',NULL,'1',1,0,1,'no se sabe'),(2,'CT00003',2,0,'',0,'',1,'JESUS ARMANDO SEQUERA LOYA',0,'2020-08-16 23:36:38',1,59,'PRODUCTO KIT DE PRUEBA','KIT',16,5500,5500,0,NULL,NULL,NULL,0,'','','HOLA','',NULL,'59',1,0,2,'35553'),(3,'CT00004',3,0,'',0,'',1,'JESUS ARMANDO SEQUERA LOYA',0,'2020-08-16 23:46:13',1,58,'PRODUCTO PRUEBA PARA VENTA Y COMPRA DE MANTENIMIENTO DE BOMBA','PIEZA',16,5000,5000,0,NULL,NULL,NULL,0,'','','','',NULL,'58',1,0,4,'');

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

insert  into `empleado`(`id`,`fecha_elaboracion`,`fecha_modificacion`,`empresa_id`,`empresa`,`unidad_id`,`unidad`,`usuario_id`,`usuario`,`password`,`activo`,`clave_empleado`,`nombre`,`apellido_paterno`,`apellido_materno`,`nss`,`domicilio`,`colonia`,`cp`,`ciudad`,`estado`,`pais`,`telefono_empresa`,`telefono_personal`,`email_empresa`,`email_personal`,`fecha_ingreso`,`autorizador`,`departamento_id`,`departamento`,`puesto_id`,`puesto`,`clasificacion_puesto`,`url_firma`) values (1,'2020-05-07 14:31:01','2020-05-07 14:31:01',NULL,NULL,NULL,NULL,NULL,'asequera','asequera.3321',1,NULL,'JESUS ARMANDO','SEQUERA','LOYA',NULL,NULL,NULL,NULL,NULL,'TAMAULIPAS','MEXICO','8333992231','8338503576','asequera@impeller.com.mx',NULL,NULL,1,NULL,'GERENCIA',NULL,'GERENTE GENERAL',NULL,NULL),(2,'2020-05-07 14:31:01','2020-05-07 14:31:01',NULL,NULL,NULL,NULL,NULL,'luis','luis.3389',1,NULL,'LUIS HUMBERTO','GOMEZ','TIBURCIO',NULL,NULL,NULL,NULL,NULL,'TAMAULIPAS','MEXICO','8333992231','8333140906','luis@impeller.com.mx',NULL,NULL,1,NULL,'GERENCIA',NULL,'GERENTE ADMINISTRATIVO',NULL,NULL),(3,'2020-05-07 14:31:01','2020-05-25 11:46:51',NULL,NULL,NULL,NULL,NULL,'gabriela','gabriela.2245',1,NULL,'GABRIELA ALEJANDRA','BARRIOS','REYNA',NULL,NULL,NULL,NULL,NULL,'TAMAULIPAS','MEXICO','8333992231','8331100147','gabriela@impeller.com.mx',NULL,'2020-05-25 00:00:00',1,NULL,'VENTAS',NULL,'GERENTE DE VENTAS',NULL,NULL),(4,'2020-05-07 14:31:01','2020-05-07 14:31:01',NULL,NULL,NULL,NULL,NULL,'valeria','valeria.1214',1,NULL,'VALERIA ALEJANDRINA','ROSARIO','JIMENEZ',NULL,NULL,NULL,NULL,NULL,'TAMAULIPAS','MEXICO','8333992231','8334032908','valeria@impeller.com.mx',NULL,NULL,0,NULL,'VENTAS',NULL,'AUXILIAR DE VENTAS',NULL,NULL),(5,'2020-05-07 14:31:01','2020-05-07 14:31:01',NULL,NULL,NULL,NULL,NULL,'eder','eder.8574',1,NULL,'EDER ABEL','MARISCAL','ESCAMILLA',NULL,NULL,NULL,NULL,NULL,'TAMAULIPAS','MEXICO','8333992231','8332896777','eder@impeller.com.mx',NULL,NULL,0,NULL,'VENTAS',NULL,'AUXILIAR DE VENTAS',NULL,NULL),(6,'2020-05-07 14:31:01','2020-05-07 14:31:01',NULL,NULL,NULL,NULL,NULL,'keren','keren5510.3698',1,NULL,'KEREN LIZETH','ECHEVERRIA','GARCIA',NULL,NULL,NULL,NULL,NULL,'TAMAULIPAS','MEXICO','8333992231','8333992231','keren@impeller.com.mx',NULL,NULL,0,NULL,'COMPRAS',NULL,'AUXILIAR DE COMPRAS',NULL,NULL),(7,'2020-05-07 14:31:01','2020-05-07 14:31:01',NULL,NULL,NULL,NULL,NULL,'ameraz','alejandro.2200',1,NULL,'OMAR ALEJANDRO','MERAZ','LARA',NULL,NULL,NULL,NULL,NULL,'TAMAULIPAS','MEXICO','8333992231','8334445525','ameraz@impeller.com.mx',NULL,NULL,0,NULL,'ALMACEN',NULL,'AUXILIAR DE ALMACEN',NULL,NULL),(8,'2020-05-07 14:31:01','2020-05-07 14:31:01',NULL,NULL,NULL,NULL,NULL,'hreyna','hreyna.9865',1,NULL,'LUIS HUMBERTO','REYNA','GURROLA',NULL,NULL,NULL,NULL,NULL,'TAMAULIPAS','MEXICO','8333992231','8334021468','hreyna@impeller.com.mx',NULL,NULL,0,NULL,'VENTAS',NULL,'AUXILIAR DE VENTAS',NULL,NULL),(9,'2020-05-07 14:31:01','2020-05-07 14:31:01',NULL,NULL,NULL,NULL,NULL,'rcruz','rcruz.9905',1,NULL,'RUBEN','CRUZ','RAVIZE',NULL,NULL,NULL,NULL,NULL,'TAMAULIPAS','MEXICO','8333992231',NULL,'rcruz@impeller.com.mx',NULL,NULL,0,NULL,'OPERACIONES',NULL,'AUXILIAR DE OPERACIONES',NULL,NULL);

/*Table structure for table `node_file` */

DROP TABLE IF EXISTS `node_file`;

CREATE TABLE `node_file` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `parent_id` int(10) DEFAULT NULL,
  `parent_folio` varchar(100) DEFAULT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  `folio` varchar(100) DEFAULT NULL,
  `cliente_proveedor_id` int(10) DEFAULT NULL,
  `cliente_proveedor` varchar(255) DEFAULT NULL,
  `tipo_documento` varchar(100) DEFAULT NULL,
  `url` text,
  `extension` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `node_file` */

insert  into `node_file`(`id`,`parent_id`,`parent_folio`,`nombre`,`folio`,`cliente_proveedor_id`,`cliente_proveedor`,`tipo_documento`,`url`,`extension`) values (1,27,'RQ00027','49532.pdf','49532',17,'PROPART INC.','REQUISICION DE COMPRA','/home/admindev/serverapps/tomcat9/ED_Impeller/REQUISICION DE COMPRA/RQ00027/49532.pdf','application/pdf');

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `orden_compra` */

insert  into `orden_compra`(`id`,`fecha_elaboracion`,`fecha_modificacion`,`empresa_id`,`empresa`,`unidad_id`,`unidad`,`usuario_id`,`usuario`,`estado_doc`,`estado`,`folio`,`serie`,`observaciones`,`tipo_documento`,`tipo_archivo`,`razon_cancelar`,`cliente_id`,`cliente`,`cliente_rfc`,`metodo_pago`,`moneda`,`tipo_cambio`,`importe`,`descuento`,`subtotal`,`iva`,`total`,`porc_iva`,`importe_letra`,`activo`,`proveedor_id`,`proveedor`,`cond_pago`,`requisicion_id`,`folio_requisicion`,`solicita_id`,`solicita`,`recibe_id`,`recibe`,`autoriza_id`,`autoriza`,`fecha_entrega`,`direccion_entrega`,`fecha_requisicion`,`factura_id`,`factura`,`folio_factura`,`no_cuenta`,`interbancaria`,`banco`,`sucursal`,`concepto_pago`,`fecha_autoriza`,`firma_autoriza`,`forma_pago`,`fecha_solicita`,`firma_solicita`,`pedido`,`comprador`,`tiempo_entrega`,`instrucciones_entrega`,`cotizacion_id`,`cotizacion`,`cotizacion_proveedor`) values (1,'2020-08-16 22:28:07','2020-08-16 22:28:28',0,'',0,'',1,'JESUS ARMANDO SEQUERA LOYA','EN PROCESO','','OC00002','','','ORDEN DE COMPRA','PDF','',0,'','','EFECTIVO','PESOS',1.00,63000.00,0.0,63000.00,10080.0,73080.00,16,'SETENTA Y TRES MIL OCHENTA Y 00/100  PESOS MEXICANOS.',1,3,' ABSORMEX CMPC TISSUE, S.A. DE C.V.','CONTADO',1,'RQ00002',NULL,'JESUS ARMANDO SEQUERA LOYA',NULL,'Almacen Matriz',3,'GABRIELA ALEJANDRA BARRIOS REYNA','2020-08-16 00:00:00','','2020-08-16 00:00:00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'47F952DB33DDD8CCA08F606B500CB205',NULL,'JESUS ARMANDO SEQUERA LOYA','1 DIAS',NULL,1,'CT00002','');

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `orden_compra_det` */

insert  into `orden_compra_det`(`id`,`folio`,`documento_id`,`empresa_id`,`empresa`,`unidad_id`,`unidad`,`usuario_id`,`usuario`,`no_partida`,`fecha_alta`,`cantidad`,`producto_id`,`descripcion`,`unidad_medida`,`porc_iva`,`precio_unitario`,`importe`,`descuento`,`subtotal`,`iva`,`total`,`servicio`,`folio_requisicion`,`requisicion_id`,`no_parte`,`no_serie`,`modelo`,`marca`,`codigo_proveedor`,`codigo_interno`) values (1,'OC00002',1,0,'',0,'',1,'JESUS ARMANDO SEQUERA LOYA',0,'2020-08-16 22:28:12',10,1,'COPLE ES10 OMEGA REXNOR','PIEZA',16,6300.00,63000.00,NULL,NULL,NULL,NULL,0,'RQ00002',1,'','','ES10','OMEGA REXNOR ','3','1');

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
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8;

/*Data for the table `producto` */

insert  into `producto`(`id`,`fecha_elaboracion`,`fecha_modificacion`,`empresa_id`,`empresa`,`unidad_id`,`unidad`,`usuario_id`,`usuario`,`activo`,`codigo_interno`,`descripcion_corta`,`descripcion`,`modelo`,`no_parte`,`no_serie`,`marca`,`unidad_medida`,`tipo_producto`,`sub_tipo_producto`,`clasificacion`,`inventariable`,`inventario_actual`,`inventario_maximo`,`inventario_minimo`,`porc_iva`,`precio_compra`,`iva_compra`,`precio_venta`,`iva_venta`,`porc_descuento`,`descuento_venta`,`porc_utilidad`,`proveedor_id_1`,`proveedor_1`,`proveedor_id_2`,`proveedor_2`) values (1,'2020-07-17 20:30:20','2020-07-17 20:30:20',0,'',0,'',0,'',1,'1','COPLE ES10 OMEGA REXNOR','COPLE ES10 OMEGA REXNOR','ES10','','','OMEGA REXNOR ','PIEZA','','','',0,0,0,0,16,6300,0,10000,0,0,0,0,0,'',0,''),(2,'2020-07-20 15:19:03','2020-07-20 15:19:03',0,'',0,'',0,'',1,'2','SILENCIADOR VTM-8','SILENCIADOR VTM-8','E3','VTM-8','-','VERSAMATIC','PIEZA','','','',0,0,0,0,16,51.43,0,100,0,0,0,0,0,'',0,''),(3,'2020-07-20 21:13:01','2020-07-20 21:13:01',0,'',0,'',0,'',1,'3','SERVICIO DE REPARACIÓN DE CABEZAL URACA','SERVICIO DE REPARACIÓN DE CABEZAL URACA','500','','','URACA','PIEZA','','','',0,0,0,0,16,13585.305,0,0,0,0,0,0,0,'',0,''),(6,'2020-07-21 20:44:00','2020-07-21 20:44:00',0,'',0,'',0,'',1,'6','VALVULA TIPO GLOBO DE 3\" 150#','VALVULA TIPO GLOBO DE 3\" 150#','FIGURA W-5275F-AA','CON CPO. DE AC. FUNDIDO A216 GR WCB INT. AC. INOX.','','WALWORTH','PIEZA','','','',0,0,0,0,16,320.56,0,0,0,0,0,0,0,'',0,''),(7,'2020-07-21 20:48:46','2020-07-21 20:48:46',0,'',0,'',0,'',1,'7','BOMBA DE VACIADO DE TAMBORES','BOMBA DE VACIADO DE TAMBORES',' PFS40-40','ACERO INOXIDABLE CON INTERNOS DE ALLOY 625 FKM','','','PIEZA','','','',0,0,0,0,16,494.45,0,0,0,0,0,0,0,'',0,''),(8,'2020-07-21 20:50:22','2020-07-21 20:50:22',0,'',0,'',0,'',1,'8','MOTOR NEUMATICO ,80-100PSI@15-32CFM, 300-9,000 RPM','MOTOR NEUMATICO ,80-100PSI@15-32CFM, 300-9,000 RPM','MOD.M6','','','MCA. FINISH THOMPSON','PIEZA','','','',0,0,0,0,16,300.15,0,0,0,0,0,0,0,'',0,''),(9,'2020-07-22 14:41:21','2020-07-23 20:27:59',0,'',0,'',0,'',1,'9','MANTENIMIENTO PREDICTIVO A BOMBA ELÉCTRICA TIPO JOKEY','SUMINISTRO DE MANO DE OBRA CALIFICADA Y MATERIALES 						\nNECESARIOS PARA REALIZAR MANTENIMIENTO A  BOMBA						\nELÉCTRICA TIPO JOKEY						\n						\nCONSISTE EN 						\nA)	BLOQUEO ELÉCTRICO						\nB)	DESACOPLAR BOMBA						\nC)	REVISIÓN DE COPLE						\nD)	REVISIÓN DE VENTILADOR						\nE)	REVISIÓN DE ALINEACIÓN						\nF)	MEGUEO DE MOTOR						\nG)	REVISIÓN DE CONEXIONES						\nH)	REVISIÓN DE EMPAQUES \n','','','','','SERVICIO','','','SERVICIO',0,0,0,0,16,6500,896.55,7500,1034.48,0,0,0,11,'RODAMIENTOS Y ACCESORIOS S.A. DE C.V.',3,' ABSORMEX CMPC TISSUE, S.A. DE C.V.'),(11,'2020-07-23 17:36:59','2020-07-23 20:55:22',0,'',0,'',0,'',1,'11','BOMBA DE DOBLE DIAFRAGMA DE 1/2\"','MARCA: IWAKI AIR\nMODELO: TC-X151VT-NPT\nCUERPO DE LA BOMBA: KYNAR\nDIAFRAGMAS: TEFLÓN\nVÁLVULAS PLANAS: TEFLÓN\nASIENTOS: KYNAR\nSUCCIÓN: 1/2\"\nDESCARGA: 1/2\"\nTIPO DE CONEXIÓN: ROSCADA NPT','TC-X151VT-NPT','1E00051MN','-','IWAKI','PIEZA','','','',0,0,0,0,16,880,121.38,1086,149.79,0,0,0,21,'IWAKI AMERICA',21,'IWAKI AMERICA '),(12,'2020-07-28 17:02:06','2020-07-28 17:02:06',0,'',0,'',0,'',1,'12','BOMBA HAWK MODELO XLT 1830IR','BOMBA HAWK MODELO XLT 1830IR','XLT 1830IR','1.099-088.0','-','HAWK','PIEZA','','','',0,0,0,0,16,1829.25,0,0,0,0,0,0,0,'',0,''),(13,'2020-07-29 22:00:46','2020-07-29 22:00:46',0,'',0,'',0,'',1,'13','SERVICIO DE MAQUINADO','SERVICIO DE MAQUINADO','N/A','N/A','','','PIEZA','','','',0,0,0,0,16,0,0,0,0,0,0,0,0,'',0,''),(14,'2020-07-31 20:05:58','2020-07-31 20:05:58',0,'',0,'',0,'',1,'14','MANO DE OBRA POR SERVICIO DE MANTENIMIENTO CORRECTIVO A BOMBA VERTICAL','TRASLADO DE NALCO A TALLER, ASI COMO RETORNO A PLANTA FABRICACION DE FLECHAS DE IMPULSORES, COLUMNA Y MOTRIZ FABRICACION E INSTALACION DE BUJES DE TAZONES, PRENSE Y CENTRADOR BALANCEO DE IMPULSOR ARMADO','GOULDS VIT 5 STAGES','','','GOULDS ','SERVICIO','','','SERVICIO',0,0,0,0,16,0,0,0,0,0,0,0,5,'AMSI-TA APLICACIONES MANTENIMIENTO Y SERVICIOS INDUSTRIALES.',5,'AMSI-TA APLICACIONES MANTENIMIENTO Y SERVICIOS INDUSTRIALES.'),(15,'2020-07-31 20:09:29','2020-07-31 20:09:29',0,'',0,'',0,'',1,'15','SUMINISTRO DE MATERIALES PARA MANTENIMIENTO CORRECTIVO DE BOMBA VERTICAL','SUMINISTRO DE FLECHAS EN SS416, SUMINISTRO DE BRONCE SAE64 PARA BUJES, SUMINISTRO DE BRONCE SAE64 PARA ANILLOS DE DESGASTE, SUMINISTRO DE PINTURA 25P PARA FONDO Y 10P PARA ACABADOS','GOULDS VIT CT 05 STAGES','','','GOULDS ','SERVICIO','','','SERVICIO',0,0,0,0,16,0,0,0,0,0,0,0,2,'ACEROX',2,'ACEROX'),(16,'2020-07-31 20:14:11','2020-07-31 20:14:11',0,'',0,'',0,'',1,'16','SERVICIO DE MANTENIMIENTO A BOMBA GOULDS','SERVICIO DE MANTENIMIENTO A BOMBA GOULDS','','','','','PIEZA','','','',0,0,0,0,16,14274.18,0,0,0,0,0,0,0,'',0,''),(17,'2020-08-03 14:37:48','2020-08-03 14:51:28',0,'',0,'',0,'',1,'17','CAMIÓN DE ALTO VACÍO DE 12.0 METROS CÚBICOS','CAMIÓN DE ALTO VACÍO DE 12.0 METROS CÚBICOS','12.0 METROS CÚBICOS','','','','SERVICIO','','','',0,0,0,0,16,1800,0,0,0,0,0,0,3,'ABSORMEX CMPC TISSUE, S.A. DE C.V.',3,' ABSORMEX CMPC TISSUE, S.A. DE C.V.'),(18,'2020-08-03 20:58:40','2020-08-03 21:03:36',0,'',0,'',0,'',1,'18','SERVICIO DE MANTENIMIENTO BOMBA VERTICAL GOULDS MG-2295 TAG P1101 A/B','SE INCLUYE:\n\nA)      RECEPCIÓN EN PLANTA INEOS SITIO ALTAMIRA Y TRASLADO A TALLER DE IMPELLER\nB)      DESARMADO E INSPECCIÓN GENERAL.\nC)      REVISIÓN DE DEFLEXIÓN Y DESGASTE EN FLECHA DE COLUMNA\nD)      REVISIÓN DE DEFLEXIÓN Y DESGASTE EN FLECHA DE CUERPO DE TAZONES\nE)      REVISIÓN DE CLAROS EN BUJES DE TAZONES\nF)       REVISIÓN DE CLAROS DE DESGASTE EN ANILLOS DE DESGASTE\nG)     REVISIÓN DE CLARO DE ESTADO DE MANGUITOS CÓNICOS\nH)     REVISIÓN DE ESTADO DE IMPULSORES\nI)        PRUEBA DE LÍQUIDOS PENETRANTES A IMPULSORES\nJ)        ENTREGA DE REPORTE FOTOGRÁFICO Y TÉCNICO\nK)      ARMANDO DE EQUIPO Y TRASLADO A PLANTA INEOS SITIO ALTAMIRA','GOULDS MG-2295 ','','','','SERVICIO','','','',0,0,0,0,16,35000,0,0,0,0,0,0,3,'ABSORMEX CMPC TISSUE, S.A. DE C.V.',3,' ABSORMEX CMPC TISSUE, S.A. DE C.V.'),(19,'2020-08-04 15:20:51','2020-08-04 15:20:51',0,'',0,'',0,'',1,'19','VIBRADOR NEUMÁTICO MARCA HOUSTON MODELO HVT 280-1','VIBRADOR NEUMÁTICO MARCA HOUSTON MODELO HVT 280-1','HVT 280-1','','','HOUSTON','PIEZA','','','PRODUCTO',1,0,0,0,16,0,0,492.75,67.97,0,0,0,23,'MEXTRADE INTERNACIONAL, S.A. DE C.V.',3,' ABSORMEX CMPC TISSUE, S.A. DE C.V.'),(20,'2020-08-04 15:49:29','2020-08-04 15:49:29',0,'',0,'',0,'',1,'20','AMORTIGUADOR DE PULSACIONES','AMORTIGUADOR DE PULSACIONES MARCA MARCA VERSAMATIC MODELO VTA 3, \nDIÁMETRO: DE 3\"\nCONSTRUCCIÓN: ALUMINIO\nDIAFRAGMA: TEFLÓN \nMÁXIMA PRESIÓN DE TRABAJO: 8.6 BAR (125 PSI)','VTA 3 DL2','','','VERSAMATIC ','PIEZA','','','PRODUCTO',1,0,0,0,16,0,0,3029.91,0,0,0,0,10,'TRANSMISIONES Y EQUIPOS INDUSTRIALES, S.A. DE C.V.',10,'TRANSMISIONES Y EQUIPOS INDUSTRIALES, S.A. DE C.V.'),(21,'2020-08-04 15:58:33','2020-08-04 15:58:33',0,'',0,'',0,'',1,'21','AMORTIGUADOR DE PULSACIONES MODELO VTA 3 DL2','AMORTIGUADOR DE PULSACIONES MARCA VERSAMATIC CON LAS SIGUIENTES CARACTERÍSTICAS:\n\nMODELO: VTA 3 DL2\nDIÁMETRO DE ENTRADA DEL FLUIDO : 3\"\nMATERIAL DEL CUERPO: ALUMINIO\nMATERIAL DEL DIAFRAGMA: TEFLÓN\nPRESIÓN MÁXIMA DE TRABAJO: 8.6 BAR (125 PSI)\nENTRADA DE AIRE: 1/4\"\n','VTA 3 DL2','','','VERSAMATIC','PIEZA','','','PRODUCTO',1,0,0,0,16,0,0,3029.91,417.92,0,0,0,10,'TRANSMISIONES Y EQUIPOS INDUSTRIALES, S.A. DE C.V.',10,'TRANSMISIONES Y EQUIPOS INDUSTRIALES, S.A. DE C.V.'),(22,'2020-08-04 16:43:17','2020-08-04 17:01:52',0,'',0,'',0,'',1,'22','FILTRO REGULADOR DE AIRE MARCA NORGREN','FILTRO REGULADOR DE AIRE MARCA NORGREN, DE 1/2\", ESTÁNDAR, RANGO DE AJUSTE 0.3 A 10 BAR, INCLUYE MANÓMETRO.','R74G4AKRMG','','','NORGREN','PIEZA','','','PRODUCTO',1,0,0,0,16,0,0,143.95,19.86,0,0,0,24,'TUBERIAS Y VALVULAS DEL NOROESTES.A. DE C.V.',24,'TUBERIAS Y VALVULAS DEL NOROESTES.A. DE C.V.'),(23,'2020-08-04 17:10:20','2020-08-04 17:10:20',0,'',0,'',0,'',1,'23','GRUA','GRÚA DE 75 TONELADAS','','','','','PIEZA','','','',0,0,0,0,16,3500,0,0,0,0,0,0,0,'',0,''),(24,'2020-08-04 20:19:51','2020-08-04 21:19:35',0,'',0,'',0,'',1,'24','RETIRO DE TORNILLOS CAPADOS BOMBA WILDEN P1702B','RETIRO DE TORNILLOS CAPADOS BOMBA WILDEN P1702B\n\nSE INCLUYE:\n    \nA) TRASLADO AL TALLER DE IMPELLER WELL SERVICES\nB) RETIRO DE TORNILLOS Y SUMINISTRO DE 2 TORNILLOS 1/4\" \nC) REPARACION DE CUERDA, EN CASO DE PRESENTAR DAÑO\nD) TRASLADO A INEOS STYROLUTION ALTAMIRA\nE) REPORTE FOTOGRAFICO DE LA ACTIVIDAD','','','','','SERVICIO','','','SERVICIO',1,0,0,0,16,2360,325.52,3800.75,524.24,0,0,0,3,'ABSORMEX CMPC TISSUE, S.A. DE C.V.',3,' ABSORMEX CMPC TISSUE, S.A. DE C.V.'),(25,'2020-08-04 21:28:55','2020-08-04 21:28:55',0,'',0,'',0,'',1,'25','BOMBA VERTICAL','CONSISTE EN:						\nBLOQUEO Y CANDADEO DE EQUIPO						\nTRAMITES DE PERMISOS VOPAK						\nDESMONTAJE DE MOTOR						\nRETIRO DE ACEITE						\nRETIRAR TUBERIAS DE ACEITE						\nRETIRAR DESCARGA						\nDESMONTAJE DE BOMBA						\nTRANSPORTE A PLANTA Y VICEVERSA						\nDESARMADO						\nRETIRAR IMPULSOR						\nDIMENCIONAR FLECHA						\nFABRICACION DE MANGAS						\nCOLOCACION DE EMPAQUETADURA						\nBALANCEO DINAMICO						\nLIMPIEZA GENERAL						\nAPLICACIÓN DE FONDO ESTRUCTURAL SEGÚN CLIENTE						\nAPLICACIÓN DE PINTURA SEGÚN CLIENTE						\nARLADO DE BOMBA						\nCOLOCACION DE COMAL EN HUECO DE BOMBA						\nPRUEBAS DE FUNCIONAMIENTO						\n','','','','','PIEZA','','','',0,0,0,0,16,455800,0,0,0,0,0,0,0,'',0,''),(26,'2020-08-06 15:21:12','2020-08-07 17:41:01',0,'',0,'',0,'',1,'26','MANTENIMIENTO A REDUCTOR DE ACCIONAMIENTO DE TELAS DE FILTRO BANDA F6001.','MANTENIMIENTO A REDUCTOR DE ACCIONAMIENTO DE TELAS DE FILTRO BANDA F6001.\nSE INCLUYE:\n1.	RETIRO DE REDUCTOR.\n2.	TRASLADO A TALLER IMPELLER WELL SERVICES\n3.	DESARMADO DE EQUIPO.\n4.	INSPECCIÓN DE INTERNOS\n5.	REPORTE PRELIMINAR DE CONDICIONES ENCONTRADAS \n6.	 SOLICITUD DE REFACCIONES.\n7.	REVISIÓN DE ALOJAMIENTOS DE CAJA REDUCTORA.\n8.	ARMADO DE RECTOR CON LOS AJUSTES REQUERIDOS POR EL EQUIPO.\n9.	TRASLADO DE EQUIPO A PLANTA INEOS STYROLUTION\n10.	INSTALACIÓN DE EQUIPO EN SU POSICIÓN DE TRABAJO.\n11.	PRUEBAS OPERATIVAS.\n12.	REPORTE TÉCNICO – FOTOGRÁFICO. \nSE INCLUYEN  MEDIDAS INICIALES Y FINALES, AJUSTES Y DETALLES ENCONTRADOS.\n','','','','','SERVICIO','','','SERVICIO',1,0,0,0,16,0,0,15890,2191.72,0,0,0,3,'ABSORMEX CMPC TISSUE, S.A. DE C.V.',3,' ABSORMEX CMPC TISSUE, S.A. DE C.V.'),(27,'2020-08-06 22:16:02','2020-08-06 22:16:02',0,'',0,'',0,'',1,'27','REPARACIÓN DE BOMBAS DE AGUA','REPARACIÓN DE BOMBAS DE AGUA TIPO DURCO\nCONSISTE EN:\nBLOQUEO Y CANDADEO						\nRETIRAR GUARDA						\nRETIRAR COPLE FLEXIBLE (EN CASO DE REQUERIR CAMBIO  SE COTIZA POR SEPARADO)						\nDESMONTAJE DE BOMBA 						\nREVISIÓN DE MANGA (EN CASO DE REQUERIR CAMBIO DE COTIZA POR SEPARADO)						\nCAMBIO DE EMPAQUETADURA						\nCAMBIO DE EMPAQUE DE VOLUTA 						\nLIMPIEZA GENERAL						\nPINTURA						\nREVISIÓN DE FLEXIÓN DE FLECHA						\nARMADO DE BOMBA 						\nMONTAJE Y ALINEACIÓN						\n','DURCO','','','','PIEZA','','','',0,0,0,0,16,35500,0,0,0,0,0,0,0,'',0,''),(28,'2020-08-06 22:25:49','2020-08-06 22:25:49',0,'',0,'',0,'',1,'28','BOMBAS DE AGUA MXCB','SUMINISTRO DE MANO DE OBRA CALIFICADA, HERRAMIENTA, MATERIALES NECESARIOS PARA REALIZAR MANTENIMIENTO A BOMBAS DE AGUA       PU-7101 A/B   PU-7102 A/B  PU-7103 OIL COOLER WATER PUMP  \n\n\nBLOQUEO Y CANDADEO						\nRETIRAR GUARDA						\nRETIRAR COPLE FLEXIBLE (EN CASO DE REQUERIR CAMBIO  SE COTIZA POR SEPARADO)						\nDESMONTAJE DE BOMBA 						\nREVISION DE MANGA (EN CASO DE REQUERIR CAMBIO DE COTIZA POR SEPARADO)						\nCAMBIO DE EMPAQUETADURA						\nCAMBIO DE EMPAQUE DE VOLUTA 						\nLIMPIEZA GENERAL						\nPINTURA						\nREVISIÓN DE FLEXIÓN DE FLECHA						\nARMADO DE BOMBA 						\nMONTAJE Y ALINEACIÓN						\n','TIPO DURCO','','','','PIEZA','','','',0,0,0,0,16,35500,0,0,0,0,0,0,0,'',0,''),(29,'2020-08-07 15:26:51','2020-08-07 16:03:03',0,'',0,'',0,'',1,'29','IMPULSOR PARA BOMBA GOULDS 3196 LTI 4X6-10G EN 316-SS, DIÁMETRO DE 10\"','IMPULSOR PARA BOMBA GOULDS 3196 LTI 4X6-10G EN 316-SS, DIÁMETRO DE 10\" (PARA BOMBA BAF L2)','3196 LTI 4X6-10G EN 316-SS','GOU 0C06089A-1203','','GOULDS','PIEZA','','','PRODUCTO',1,0,0,0,16,990,136.55,1400,193.1,0,0,0,17,'PROPART INC.',17,'PROPART INC.'),(30,'2020-08-07 15:30:53','2020-08-07 16:03:21',0,'',0,'',0,'',1,'30','IMPULSOR PARA BOMBA GOULDS 3196 MTI 3X4-13 EN 316-SS, DIÁMETRO DE 13\"','IMPULSOR PARA BOMBA GOULDS 3196 MTI 3X4-13 EN 316-SS, DIÁMETRO DE 13\" ( PARA BOMBA DE TERMINADO L2)','3196 MTI 3X4-13 EN 316-SS','GOU RB10542-1203','','GOULDS','PIEZA','','','PRODUCTO',1,0,0,0,16,695,95.86,1200,165.52,0,0,0,17,'PROPART INC.',17,'PROPART INC.'),(31,'2020-08-07 15:33:22','2020-08-07 15:54:31',0,'',0,'',0,'',1,'31','IMPULSOR DURCO TAMAÑO 4 X 3 - 13 EN 316-SS DIÁMETRO 13\"','IMPULSOR DURCO TAMAÑO 4X3 - 13 EN 316-SS DIÁMETRO 13\"(PARA BOMBAS DE TERMINADO L1 ).','4 X 3 - 13 EN 316-SS ','DUR MY51028A130-D4','','DURCO','PIEZA','','','PRODUCTO',1,0,0,0,16,695,95.86,1200,165.52,0,0,0,17,'PROPART INC.',17,'PROPART INC.'),(32,'2020-08-07 15:58:34','2020-08-07 16:03:41',0,'',0,'',0,'',1,'32','IMPULSOR  PARA BOMBA GOULDS 3756 3X4-10 EN BRONCE','IMPULSOR PARA BOMBA GOULDS 3756 3X4-10 EN BRONCE, DIÁMETRO DE 8.560\" (PARA BOMBA BAF L1)','3756 3X4-10 BRONCE','','','GOULDS','PIEZA','','','PRODUCTO',1,0,0,0,16,1328,183.17,1750,241.38,0,0,0,3,'ABSORMEX CMPC TISSUE, S.A. DE C.V.',3,' ABSORMEX CMPC TISSUE, S.A. DE C.V.'),(33,'2020-08-07 21:46:13','2020-08-07 21:46:13',0,'',0,'',0,'',1,'33','FLECHA DE 4\"','FLECHA DE 4 \" X 63\"','4140','','','','PIEZA','','','',0,0,0,0,16,183.75,0,0,0,0,0,0,0,'',0,''),(34,'2020-08-07 22:06:23','2020-08-08 00:46:27',0,'',0,'',0,'',1,'34','SERVICIO DE MANTENIMIENTO A PALANCA DE VALVULA DE CONTROL','SERVICIO DE MANTENIMIENTO A PALANCA DE VALVULA DE CONTROL  DE CONSOLA DEL TAG: V-30001/2/3/4 \n\n•TRASLADO DE PALANCA DE CONTROL A TALLER DE SERVICIO.\n\n•DESARMADO DE PALANCA DE CONTROL Y LIMPIEZA A TODOS LOS COMPONENTES INTERNOS.\n\n•EVALUACIÓN, TOMA DE MEDIDAS Y DIMENSIONAMIENTO.\n\n•VERIFICACION DE ESTADO FÍSICO DE PISTÓN DISTRIBUIDOR, PISTÓN DE INVERSIÓN, CAMISAS DE ALOJAMIENTO DE PISTONES, O´RINGS, RESORTE DE PISTÓN DISTRIBUIDOR, RESORTE DE RETORNO A POSICIÓN DE PALANCA, PALANCA DE CONTROL, ASIENTO DE SELLO.\n\n•INSPECCIÓN Y REVISIÓN DE CUERDA, TORNILLERÍA, ARANDELAS DE PRESIÓN, CONJUNTO DE TAPA DE VENTILACIÓN, EMPAQUETADURAS (TAPAS DE  PISTONES Y BASE) Y CONECTORES.\n\n•REHABILITACIÓN DE CAMISAS DE ALOJAMIENTO DE PISTONES, REHABILITACIÓN DE ALOJAMIENTO DE SELLO, REHABILITACION DE DIÁMETROS Y PUNTAS DE PISTONES DE ACUERDO CON MEDIDAS DE FABRICANTE.\n\n•VERIFICACIÓN DE MEDIDAS Y TOLERANCIAS EN CAMISAS DE PISTÓN DISTRIBUIDOR, PISTÓN DE INVERSIÓN, CAMISAS DE PISTÓN DISTRIBUIDOR, CAMISA DE PISTÓN DE INVERSIÓN Y CAMISA DE CUERPO.\n\n•FABRICACIÓN DE PARTES DAÑADAS Y/O SUMINISTRO DE: EMPAQUES O JUNTAS, O´RING , SELLOS DE NEOPRENO DE PORTA SELLO Y ASIENTO DE CUERPO.\n\n•RECUPERACIÓN DE MEDIDAS DE PISTONES, CAMISAS, PISTÓN DISTRIBUIDOR Y PISTÓN DE INVERSIÓN.\n\n•BARRENADO Y REFRESCADO DE CUERDAS EN LOS ALOJAMIENTOS DE TORNILLERÍA DE ENSAMBLAJE.\n\n•ARMADO DE PALANCA DE CONTROL.\n\n•APLICACIÓN DE PINTURA COLOR AMARILLO.\n\n•PRUEBA DE COMPORTAMIENTO DE PALANCA DE CONTROL EN TALLER DE SERVICIO.\n\n•TRANSPORTE DE PALANCA DE CONTROL A INSTALACIONES DE LA REFINERIA FRANCISCO I. MADERO, EN CD. MADERO, TAMPS.\n\n•ENTREGA DE REPORTE FINAL.','','','','','PIEZA','','','',0,0,0,0,16,0,0,0,0,0,0,0,3,'ABSORMEX CMPC TISSUE, S.A. DE C.V.',3,' ABSORMEX CMPC TISSUE, S.A. DE C.V.'),(36,'2020-08-07 22:58:19','2020-08-07 23:38:12',0,'',0,'',0,'',1,'36','SERVICIO DE MANTENIMIENTO A MOTORES NEUMÁTICOS DE MALACATE','SERVICIO DE MANTENIMIENTO A MOTORES NEUMÁTICOS DE MALACATE DEL V-30001/2/3/4 \n\n•TRASLADO DE MOTOR NEUMÁTICO A TALLER DE SERVICIO\n\n•DESARMADO DE MOTOR Y LIMPIEZA DE COMPONENTES INTERNOS.\n\n•EVALUACIÓN, TOMA DE MEDIDAS Y DIMENSIONAMIENTO.\n\n•VERIFICACION DE ESTADO FÍSICO DE CIGÜEÑAL, CAMISAS (5), PISTONES DE ENSAMBLE (5), CAMISA DE VÁLVULA ROTATIVA, PISTÓN DISTRIBUIDOR O VÁLVULA ROTATIVA, RODAMIENTOS DE CIGÜEÑAL, O´RINGS DE LUBRICACIÓN, ENSAMBLE DE PISTONES, BIELA, ANILLOS DE COMPRESIÓN DE PISTONES, ANILLOS DE LUBRICACIÓN DE PISTONES, CANDADOS DE PERNOS DE FIJACIÓN PISTÓN/BIELA, RODAMIENTOS DE PISTÓN ROTATIVO O DISTRIBUCIÓN, ANILLO DE COMPRESIÓN DE PISTÓN ROTATIVO O DISTRIBUCIÓN Y ELEVADOR DE ACEITE.\n\n•SANDBLASTEO DE CARCAZA DE MOTOR Y APLICACIÓN DE PINTURA COLOR AMARILLO.\n\n•INSPECCIÓN Y REVISIÓN DE CUERDA, TORNILLERÍA, ARANDELAS DE PRESIÓN, CONJUNTO DE TAPA DE VENTILACIÓN, SELLO DE ACEITE (RETEN), EMPAQUETADURAS (TAPAS PISTONES Y CUERPO DE MOTOR), TAPONES DE DRENADO Y LLENADO   Y CONECTORES.\n\n•REHABILITACION DE CAMISAS DE ALOJAMIENTO DE PISTONES, REHABILITACION DE DIÁMETROS Y PUNTAS DE PISTONES DE ACUERDO CON MEDIDAS DE FABRICANTE.\n\n•SUMINISTRO DE RODAMIENTO, EMPAQUES, Y BUJES NECESARIOS PARA ARMADO.\n\n•VERIFICACIÓN DE MEDIDAS Y TOLERANCIAS EN CAMISAS DE PISTONES DE ENSAMBLE, CAMISAS DE PISTÓN DISTRIBUIDOR O VÁLVULA ROTATIVA, MUÑONES DE CIGÜEÑAL, ENSAMBLE DE CIGÜEÑAL, ANILLO DE BIELA, MANGA Y BUJE DE BROCE DE LUBRICACIÓN, CAJA DE RODAMIENTOS.\n\n•ARMADO DE MOTOR NEUMÁTICO.\n\n•PRUEBA DE COMPORTAMIENTO DEL MOTOR NEUMÁTICO EN TALLER DE SERVICIO.\n\n•TRANSPORTE DE MOTOR NEUMÁTICO A INSTALACIONES DE LA REFINERIA FRANCISCO I. MADERO.\n\n•ENTREGA DE REPORTE FINAL.','','','','','PIEZA','','','PRODUCTO',1,0,0,0,16,0,0,0,0,0,0,0,3,'ABSORMEX CMPC TISSUE, S.A. DE C.V.',3,' ABSORMEX CMPC TISSUE, S.A. DE C.V.'),(37,'2020-08-07 23:29:42','2020-08-07 23:29:42',0,'',0,'',0,'',1,'37','SERVICIO DE MANTENIMIENTO A MOTORES NEUMÁTICOS DE JUNTA ROTATORIA','SERVICIO DE MANTENIMIENTO A MOTORES NEUMÁTICOS DE JUNTA ROTATORIA DEL V-30001/2/3/4 \n\n•DESACOPLADO, DESMONTAJE, DESARMADO DE MOTOR, APLICAR LIMPIEZA A COMPONENTES INTERNOS.\n\n•DESACOPLAR MOTOR NEUMÁTICO DE SU BASE.\n\n•TRASLADO A LAS INSTALACIONES DEL TALLER EXTERNO.\n\n•SANDBLASTEO  A MOTOR Y APLICACIÓN DE PINTURA COLOR AMARILLO.\n\n•DESARMADO DE MOTOR.\n\n•LIMPIEZA DE INTERNOS EN GENERAL.\n\n•VERIFICACION DE ESTADO FÍSICO DE CIGÜEÑAL, CAMISAS (5), PISTONES DE ENSAMBLE (5), CAMISA DE VÁLVULA ROTATIVA, PISTÓN DISTRIBUIDOR O VÁLVULA ROTATIVA. RODAMIENTOS DE CIGÜEÑAL, O´RINGS DE LUBRICACIÓN, ENSAMBLE DE PISTONES Y BIELA. \n \n•INSPECCIÓN Y REVISIÓN DE CUERDA, TORNILLERÍA, ARANDELAS DE PRESIÓN, CONJUNTO DE TAPA DE VENTILACIÓN, SELLO DE ACEITE (RETEN), EMPAQUETADURAS (TAPAS PISTONES Y CUERPO DE MOTOR), TAPONES  DE DRENADO Y LLENADO   Y CONECTORES.\n\n•VERIFICACIÓN DE MEDIDAS Y TOLERANCIAS EN CAMISAS DE PISTONES DE ENSAMBLE, CAMISAS DE PISTÓN DISTRIBUIDOR O VÁLVULA ROTATIVA, MUÑONES DE CIGÜEÑAL, ENSAMBLE DE CIGÜEÑAL, ANILLO DE BIELA, MANGA Y BUJE DE BROCE DE LUBRICACIÓN, CAJA DE RODAMIENTOS.\n\n•FABRICACIÓN DE PARTES DAÑADAS Y/O SUMINISTRO, RODAMIENTOS NUEVOS, EMPAQUES O JUNTAS, O´RING , SELLO DE ACEITE(RETEN).\n\n•RECUPERACIÓN DE MEDIDAS DE MUÑONES DE CIGÜEÑAL, CAMISAS, BUJES, MANGAS, VÁLVULA ROTATIVA O PISTÓN DISTRIBUIDOR.\n\n•BARRENADO Y REFRESCADO DE CUERDAS EN LOS ALOJAMIENTOS DE TORNILLERÍA DE ENSAMBLAJE.\n\n•ARMADO DE MOTOR NEUMÁTICO.\n\n•PRUEBA DE COMPORTAMIENTO DEL MOTOR NEUMÁTICO EN TALLER DE SERVICIO.\n\n•TRANSPORTE DE MOTOR NEUMÁTICO A INSTALACIONES DE LA REFINERIA FRANCISCO I. MADERO.\n\n•ENTREGA DE REPORTE FINAL.','','','','','PIEZA','','','PRODUCTO',1,0,0,0,16,0,0,0,0,0,0,0,3,'ABSORMEX CMPC TISSUE, S.A. DE C.V.',3,' ABSORMEX CMPC TISSUE, S.A. DE C.V.'),(38,'2020-08-11 15:31:53','2020-08-11 15:31:53',0,'',0,'',0,'',1,'38','FLECHA 4140','FLECHA PARA BOMBA 3196 MTX, 4X6-13,  4140 (ARANDELAS, CANDADO Y TUERCA DE SEGURIDAD)','','','','','PIEZA','','','',0,0,0,0,16,7500,0,0,0,0,0,0,0,'',0,''),(39,'2020-08-11 15:33:43','2020-08-11 15:33:43',0,'',0,'',0,'',1,'39','SELLO DE LABERINTOS LC / LCC','SELLO DE LABERINTOS LC / LCC PARA BOMBA 3196 MTX, 4X6-13.','','','','','PIEZA','','','',0,0,0,0,16,5000,0,0,0,0,0,0,0,'',0,''),(40,'2020-08-11 15:35:22','2020-08-11 15:35:22',0,'',0,'',0,'',1,'40','RODAMIENTO 6309 SKF Y 3309 A/C3 SKF','RODAMIENTO 6309 SKF Y 3309 A/C3 SKF PARA BOMBA 3196 MTX, 4X6-13.','','','','','PIEZA','','','',0,0,0,0,16,0,500,0,0,0,0,0,0,'',0,''),(41,'2020-08-11 15:36:33','2020-08-11 15:36:33',0,'',0,'',0,'',1,'41','ELEMENTO SE Y MASAS PARA COPLE TAMAÑO 10 (MAQUINADOS SEGÚN MUESTRA)','ELEMENTO SE Y MASAS PARA COPLE TAMAÑO 10 (MAQUINADOS SEGÚN MUESTRA)','','','','','PIEZA','','','',0,0,0,0,16,0,500,0,0,0,0,0,0,'',0,''),(42,'2020-08-11 15:37:42','2020-08-11 15:37:42',0,'',0,'',0,'',1,'42','EMPAQUE DE CARCAZA 7078228 5127 GOULDS','EMPAQUE DE CARCAZA 7078228 5127 GOULDS','','','','','PIEZA','','','',0,0,0,0,16,0,0,0,0,0,0,0,0,'',0,''),(43,'2020-08-11 17:10:31','2020-08-11 17:10:31',0,'',0,'',0,'',1,'43','BOMBA CENTRIFUGA KSB ETNY100-080-160 SG 3X4-8','BOMBA CENTRIFUGA KSB MODELO ETNY100-080-160 SG 3X4-8\n\nCONTRUCCION:\nIMPULSOR: FUNDICION  EN-GJL- 250/A48CL35B\nEJE: ACERO 1.4021+QT+HRC 50\nCAJA DE RODAMIENTOS: FUNDICION ESFEROLITICA EN-GJS-400-15\n\nCONDICIONES DE OPERACION:\n\nCAUDAL: 150 M3/H\nALTURA DE BOMBEO: 41 METROS\nTEMPERATURA BOMBEO: 170 °C\n\n\n\n\n','ETNY100-080-160 SG ','','','KSB','PIEZA','','','PRODUCTO',1,0,0,0,16,6168,850.76,8326.8,1148.52,0,0,0,4,'AGIMEX',10,'TRANSMISIONES Y EQUIPOS INDUSTRIALES, S.A. DE C.V.'),(44,'2020-08-11 17:35:11','2020-08-13 21:06:42',0,'',0,'',0,'',1,'44','BOMBA NEUMÁTICA MARCA ASPEN','BOMBA NEUMÁTICA.\nPARA AIRE ACONDICIONADO, VOLTAJE 230 VCA, FRECUENCIA 50/60 HZ. AMPERAJE\n0,1 AMP, WATTS 16W (COMPLETA CON SUCCION Y DESCARGA).\nCODIGO: 7122351','MINI ORANGE (FP 2473)','','','MARCA ASPEN ','PIEZA','','','',0,0,0,0,16,1848.03,254.9,0,0,0,0,0,27,'CORESA PARTES Y EQUIPOS S.A. DE C.V.',9,'EQUIPUMP S.A. DE C.V.'),(45,'2020-08-11 17:53:01','2020-08-11 17:53:01',0,'',0,'',0,'',1,'45','DEAN MODEL R4140 SIZE 4X6X8.5','DEAN MODEL R4140 SIZE 4X6\nHORIZONTAL END SUCTION HEAVY DUTY HIGH TEMP CENTRIFUGAL PUMP, \nCARBON STEEL CONS 300# ANSI FLANGED PORTS FITTED WITH 609 SINGLE \nMECH SEAL CAR/TUNG FACES, WITH FTBVD STYLE GLAND    \nIMPELLER @ 7.75” DIA, INCLUDES STEEL COUPLING GUARD FOR 364TS FRAME SIZE.\n','','','','','PIEZA','','','PRODUCTO',1,0,0,0,16,0,0,0,0,0,0,0,3,'ABSORMEX CMPC TISSUE, S.A. DE C.V.',3,' ABSORMEX CMPC TISSUE, S.A. DE C.V.'),(46,'2020-08-11 20:52:50','2020-08-11 20:52:50',0,'',0,'',0,'',1,'46','REPARACIÓN DE BOMBA DE VACIÓ','\nSUMINISTRO DE MANO DE OBRA CALIFICADA, HERRAMIENTA, MATERIALES NECESARIOS PARA REALIZAR MANTENIMIENTO A BOMBA DE VACIO           						\nSUMINISTRO DE MANO DE OBRA CALIFICADA, HERRAMIENTA, MATERIALES NECESARIOS PARA REALIZAR MANTENIMIENTO A BOMBA DE AGUA						\nBLOQUEO Y CANDADEO						\nRETIRAR GUARDA						\nRETIRAR COPLE FLEXIBLE (EN CASO DE REQUERIR CAMBIO  SE COTIZA POR SEPARADO)						\nDESMONTAJE DE BOMBA 						\nREVISION DE DESGASTES DE LA BOMBA						\nREVISIÓN DE COPLE						\nCAMBIO DE EMPAQUE DE CARCASA						\nLIMPIEZA GENERAL						\nINSPECCION DE MANGAS						\nREVISIÓN DE FLEXION DE FLECHA						\nCAMBIO DE RODAMIENTOS						\nINSPECCION DE ROTOR						\nREVISIÓN DE CLAROS Y TOLERANCIAS						\nCAMBIO DE EMPAQUETADURA 						\nREVISION DE ALOJAMIENTOS DE RODAMIENTOS						\nCAMBIO DE O RING S						\n						\nARMADO Y PRUEBAS						\n','NASCH','','','','PIEZA','','','',0,0,0,0,16,71500,0,0,0,0,0,0,0,'',0,''),(47,'2020-08-13 20:13:33','2020-08-13 20:21:41',0,'',0,'',0,'',1,'47','BRIDA PARA ANSI 3 1/2\" EN PVC PARA AGITADOR','BRIDA PARA ANSI 3 1/2\" EN PVC PARA AGITADOR VTA4-08.04 B 01(2)/08.6','VTA4-08.04 B 01(2)/08.6','','','CFG','PIEZA','','','PRODUCTO',1,0,0,0,16,180,24.83,258.5,35.66,0,0,0,29,'CRAMIX - FLUIDMIX GROUP S.A. DE C.V.',29,'CRAMIX - FLUIDMIX GROUP S.A. DE C.V.'),(48,'2020-08-13 20:17:49','2020-08-13 20:17:49',0,'',0,'',0,'',1,'47','RECORTE DE IMPULSOR 4X6-10 G','RECORTE PARA IMPULSOR BOMBA GOULDS 3196 LTI 4X6-10G EN 316-SS, RECORTE A  9.0000 IN\" DIAM. (PARA BOMBA\nBAF L2)\n','','','','','SERVICIO','','','SERVICIO',1,0,0,0,16,2700,372.41,3800,524.14,0,0,0,28,'JOSE MANUELA SEQUERA LOYA',5,'AMSI-TA APLICACIONES MANTENIMIENTO Y SERVICIOS INDUSTRIALES.'),(49,'2020-08-13 20:28:08','2020-08-13 20:57:00',0,'',0,'',0,'',1,'49','BALANCEO DINAMICO 4X6-10G','BALANCEO DINÁMICO PARA IMPULSOR BOMBA GOULDS 3196 MTI 3X4-13 EN 316-SS, RECORTE DE 12.750\" DIAM. 1800 RPM ( PARA BOMBA DE TERMINADO L2)\n','','','','','SERVICIO','','','SERVICIO',1,0,0,0,16,3000,413.79,4000,551.72,0,0,0,5,'AMSI-TA APLICACIONES MANTENIMIENTO Y SERVICIOS INDUSTRIALES.',28,'JOSE MANUELA SEQUERA LOYA'),(50,'2020-08-13 20:31:44','2020-08-13 20:31:44',0,'',0,'',0,'',1,'50','RECORTE DE IMPULSOR 3X4-13','RECORTE PARA IMPULSOR BOMBA GOULDS 3196 MTI 3X4-13 EN 316-SS, RECORTE DE 12.750\" DIAM. ( PARA BOMBA DE TERMINADO L2)\n','','','','','SERVICIO','','','SERVICIO',1,0,0,0,16,2800,386.21,3800,524.14,0,0,0,5,'AMSI-TA APLICACIONES MANTENIMIENTO Y SERVICIOS INDUSTRIALES.',28,'JOSE MANUELA SEQUERA LOYA'),(51,'2020-08-13 20:33:46','2020-08-13 20:56:08',0,'',0,'',0,'',1,'51','BALANCEO DINAMICO 4X6-10G','BALANCEO DINÁMICO  PARA IMPULSOR PARA BOMBA GOULDS 3196 LTI 4X6-10G EN 316-SS, DIÁMETRO A 9.0000 IN\" 3600 RPM (PARA BOMBA BAF L2)\n','','','','','SERVICIO','','','SERVICIO',1,0,0,0,16,3000,413.79,4000,551.72,0,0,0,5,'AMSI-TA APLICACIONES MANTENIMIENTO Y SERVICIOS INDUSTRIALES.',28,'JOSE MANUELA SEQUERA LOYA'),(52,'2020-08-13 21:02:37','2020-08-13 21:02:37',0,'',0,'',0,'',1,'52','MANTENIMIENTO A BOMBA DE VACIO','SUMINISTRO DE MANO DE OBRA, HERRAMIENTA, MATERIALES CONSUMIBLES PARA LA REPARACIÓN DE BOMBA DE VACIO BAJO EL SIGUIENTE ALCANCE: TRASLADO A TALLER, DESARMADO, LIMPIEZA, ANÁLISIS DE PARTES, REVISIÓN DE CLAROS, APLICACIÓN DE SANDBLAST DE PARTES EXTERNAS, MAQUINADO DE FLECHA,  MAQUINADO DE MANGAS, INSTALACIÓN DE RODAMIENTOS, INSTALACIÓN DE EMPAQUETADURA, BALANCEO, APLICACIÓN DE RECUBRIMIENTO EPOXICO, ARMADO, AJUSTES SEGÚN FABRICANTE, APLICACIÓN DE PINTURA ANTICORROSIVA, APLICACIÓN DE PINTURA DE ACABADO.','','','','','PIEZA','','','',0,0,0,0,16,0,0,0,0,0,0,0,0,'',0,''),(53,'2020-08-13 21:03:43','2020-08-13 21:18:17',0,'',0,'',0,'',1,'52','BOMBA CENTRIFUGA CV3196 3X3-10','BOMBA CENTRIFUGA HORIZONTAL TIPO ANSI B73.1M, \nMARCA: GOULDS MODELO CV3196 FABRICADA COMPLETAMENTE EN CD4MCU\n\nCARACTERÍSTICAS:\n•	BOMBA CENTRIFUGA HORIZONTAL BRIDAS ANSI  MARCA: GOULDS \n•	MODELO: 3196 TAMAÑO:   3X3-10\n•	CONDICIONES DE OPERACIÓN: CACHAZA , FLUJO 400 GPM Y TDH 60 FT \n•	SELLO MECÁNICO : EMPAQUETADURA\n•	MOTOR ELÉCTRICO: HORIZONTAL, MARCA US MOTOR, TIPO TOTALMENTE CERRADO CON \n                      VENTILACIÓN ALTA EFICIENCIA, POTENCIA 20 HP A 1800 RPM, FS 1.15, VOLTAJE \n                      220/440 VOLTS. \n•	ACOPLAMIENTO: MARCA REXNORD FLEXIBLE CON ESPACIADOR LIBRE DE LUBRICACIÓN. \n                       GUARDA COPLE. \n•	BASE METÁLICA:  TODO EL EQUIPO ARMADO, MONTADO Y PRE ALINEADO EN BASE DE ACERO \n                       TIPO ANSI. TODO EL EQUIPO MONTADO Y PREALINEADO.\n•	ACABADO: FONDO ANTICORROSIVO Y PINTURA EN POLIURETANO.\n\n','CV 3196 3X3-10','','','GOULDS ','PIEZA','','','PRODUCTO',1,0,0,0,16,0,0,16500,2275.86,0,0,0,17,'PROPART INC.',22,'MANTENIMIENTO Y MECANIZADO ARVE S.A DE C.V'),(54,'2020-08-13 21:35:13','2020-08-15 23:42:43',0,'',0,'',0,'',1,'54','MOTOR DE 3 HP','MOTOR DE 3 HP','US MOTORS','','','','PIEZA','','','PRODUCTO',1,0,0,0,16,9500,1310.34,0,0,0,0,0,31,'SOELSA SA DE CV',9,'EQUIPUMP S.A. DE C.V.'),(55,'2020-08-13 21:37:15','2020-08-13 21:37:15',0,'',0,'',0,'',1,'55','\nMOTOR DE 3 HP','\nMOTOR DE 3 HP','','','','','PIEZA','','','',0,0,0,0,16,6900,0,0,0,0,0,0,0,'',0,''),(56,'2020-08-13 21:54:28','2020-08-13 21:54:28',0,'',0,'',0,'',1,'56','MOTOBOMBA BARNES DE 3\" 9HP A GASOLINA','MOTOBOMBA AUTOCEBANTE MARCA: BARNES, MODELO: 18M, 9HP A GASOLINA MONTADA SOBRE CARRO CON RUEDAS, CON LAS SIGUIENTES CARACTERÍSTICAS:\n\n•TAMAÑO: 3\" X 3\" (SUCCIÓN/DESCARGA) PASO DE SÓLIDOS: 3/4\" (1.91 CM). \n•IMPULSOR DISEÑO: 5 ÁLABES, TIPO SEMIABIERTO CON UN PASO DE SÓLIDOS DE 3/4\" (1.91 CM) BALANCEADO ESTÁTICAMENTE. \n•MATERIAL: HIERRO GRIS ASTM A-48, CLASE 30.\n•CUERPO HIERRO GRIS ASTM A-48, CLASE 30. CUENTA CON VOLUTA INTEGRADA. \n•ACOPLAMIENTO HIERRO GRIS ASTM A-48, CLASE 30.\n•SELLO MECANICO: LUBRICADO POR AGUA. MATERIAL: CERÁMICA PARTE ESTACIONARIA, ANILLO DE CARBÓN Y SELLO DE EXCLUSIÓN PARTE ROTATORIA, ELASTÓMERO DE BUNA-N, RESORTE DE ACERO INOXIDABLE.\n•EMPAQUES ADAMPAC.\n•LAINAS DE AJUSTE EN ACERO INOXIDABLE\n•MOTOR DE COMBUSTIÓN INTERNA A GASOLINA MONOCILINDRICO DE 4 TIEMPOS, MARCA: HONDA. \n•POTENCIA 9 HP \n•MONTADA SOBRE CARRO DE RUEDAS DE FIERRO PARA SU FÁCIL TRASLADO.\n•JUEGO DE MANGUERAS RÍGIDAS VERDE OLIVO CON ABRAZADERAS Y CONEXIONES RÁPIDAS (6 METROS EN LA SUCCIÓN Y 6 MTS EN LA DESCARGA)\n','18M','','','BARNES','PIEZA','','','',0,0,0,0,16,0,0,0,0,0,0,0,0,'',0,''),(57,'2020-08-14 20:22:11','2020-08-14 20:22:11',0,'',0,'',0,'',1,'57','MANTENIMIENTO PREVENTIVO A MOTOR DE COMBUSTIÓN MARCA. JOHN DEREE (NO. DE SERIE PE4045D684521)','\n\"MANTENIMIENTO PREVENTIVO A MOTOR DE COMBUSTIÓN MARCA. JOHN\nDEERE (NO. DE SERIE PE4045D684521)\"						\nCONSISTE EN 						\nINSPECCIÓN DE RESISTENCIA ELÉCTRICA DEL SUS. AGUA						\nINSPECCIÓN DEL CABLEADO SUS. ELÉCTRICO						\nINSPECCIÓN DE BANDAS, MANGUERAS Y LUCES DE TABLERO						\nINSPECCIÓN / LIMPIEZA DE SUS. DE VENTEO DEL CIGÜEÑAL						\nCAMBIO DE FILTRO DE ACEITE DE MOTOR						\nCAMBIO DE FILTRO DEL DIÉSEL						\nCAMBIO DE FILTRO DE AIRE						\nCAMBIO DE REFRIGERANTE						\nREV./CAMBIO DE MANGUERAS						\nREV./CAMBIO DE BANDAS						\nREV./CAMBIO DE BATERÍAS						\nREVISIÓN DE BBA DE AGUA, SIST. DE EMISIÓN DE GASES						\nREVISIÓN DE BBA DE AGUA, SIST. DE EMISIÓN DE GASES						\n						\n						\nREFACCIONES Y ADICIONALES A ESTA COTIZACIÓN  SE COTIZAN POR SEPARADO						\n','','','','','PIEZA','','','',0,0,0,0,16,29950,0,0,0,0,0,0,0,'',0,''),(58,'2020-08-16 23:28:00','2020-08-16 23:28:00',0,'',0,'',0,'',1,'58','PRODUCTO PRUEBA','PRODUCTO PRUEBA PARA VENTA Y COMPRA DE MANTENIMIENTO DE BOMBA','','','','','PIEZA','','','PRODUCTO',1,0,0,0,16,3000,413.79,5000,689.66,0,0,0,3,' ABSORMEX CMPC TISSUE, S.A. DE C.V.',3,' ABSORMEX CMPC TISSUE, S.A. DE C.V.'),(59,'2020-08-16 23:36:29','2020-08-16 23:36:29',0,'',0,'',0,'',1,'59','PRODUCTO KIT','PRODUCTO KIT DE PRUEBA','HOLA','','','','KIT','','','',0,0,0,0,16,4000,0,5500,0,0,0,0,0,'',0,'');

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
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

/*Data for the table `proveedor` */

insert  into `proveedor`(`id`,`fecha_elaboracion`,`fecha_modificacion`,`empresa_id`,`empresa`,`unidad_id`,`unidad`,`usuario_id`,`usuario`,`no_paper`,`activo`,`clasificacion_proveedor`,`tipo_proveedor`,`dias_credito`,`clave_proveedor`,`razon_social`,`rfc`,`domicilio`,`ciudad`,`estado`,`pais`,`cp`,`contacto_compra_telefono`,`contacto_compra_ext`,`contacto_compra_nombre`,`contacto_compra_email`,`contacto_contabilidad_telefono`,`contacto_contabilidad_nombre`,`contacto_contabilidad_email`,`no_cuenta_1`,`clave_interbancaria_1`,`banco_1`,`sucursal_1`,`no_cuenta_2`,`clave_interbancaria_2`,`banco_2`,`sucursal_2`,`no_cuenta_3`,`clave_interbancaria_3`,`banco_3`,`sucursal_3`) values (1,'2020-05-28 19:50:34','2020-05-28 19:50:34',0,'',0,'',0,'',1,1,'PROVEEDOR','CONTADO',0,'1','ATM VALVULAS S.A. DE C.V.','AVA080702G7','PEÑA GUERRA NO.. 417 COL. CONSTITUYENTES DE QUERETARO SECTOR 1, SAN NICOLAS DE LOS GARZA.','','MONTERREY, NUEVO LEON.','MEXICO','6649','(81) 8376-1100','','JACKELINE LOZANO',' jlozanon@atmvalvulas.com','','','cobranza@atmvalvulas.com','','','','','','','','','','','',''),(2,'2020-05-28 20:09:29','2020-05-28 20:09:29',0,'',0,'',0,'',1,1,'PROVEEDOR','CONTADO',0,'2','ACEROX','PICN97072231','BELISARIO DOMINGUEZ NO. 114, COL. NUEVO PROGRESO.','TAMPICO','TAMAULIPAS','MEXICO','89318','833-462-46-00','','NELSON PIERDANT NIÑO','aceroxventas@outlook.com','','','','','','','','','','','','','','',''),(3,'2020-05-28 20:25:40','2020-08-13 21:13:37',0,'',0,'',0,'',1,0,'PROVEEDOR','CONTADO',0,'3',' ABSORMEX CMPC TISSUE, S.A. DE C.V.','IPG970717QU9','AVENIDA INDUSTRIAL HUMBERTO LOBO NO. 9013 ','','','MEXICO','66023','','','','','','','','','','','','','','','','','','',''),(4,'2020-05-28 21:07:57','2020-05-28 21:07:57',0,'',0,'',0,'',1,1,'PROVEEDOR','CONTADO',0,'4','AGIMEX','','','','','MEXICO','','(55) 85-60-06-10','','ING. ANTONIO GARCIA','ventas_agitadoresindustriales@prodigy.net.mx','','','','','','','','','','','','','','',''),(5,'2020-05-28 21:19:14','2020-05-28 21:19:14',0,'',0,'',0,'',1,1,'PROVEEDOR','CONTADO',0,'5','AMSI-TA APLICACIONES MANTENIMIENTO Y SERVICIOS INDUSTRIALES.','TOAH881104TM','','CIUDAD MADERO','TAMAULIPAS','MEXICO','','(833)154-9907','','HECTOR TORRES','amsi-ta.adm@outlook.com','','','','','','','','','','','','','','',''),(6,'2020-05-28 21:26:04','2020-06-04 19:48:26',0,'',0,'',0,'',1,1,'PROVEEDOR','CONTADO',0,'6','ANVA SOLUCIONES INDUSTRIALES S.A. DE C.V.','ASI170111PM7','AHUEHUETE NO. 339 FRACCIONAMIENTO ARECAS','ALTAMIRA','TAMAULIPAS','MEXICO','89602','833 312 86 54','','ING. ALDO ISRAEL LIMON HERNANDEZ','aldo.limon@anvasi.com ','','','','','','','','','','','','','','',''),(7,'2020-05-28 21:42:40','2020-05-28 21:42:40',0,'',0,'',0,'',1,1,'PROVEEDOR','CONTADO',0,'7','BIRMEX S.A. DE C.V.','BYR130304JR5','','','QUERETARO','MEXICO','76100','442 161 2065 ','','ING. LUIS HERNANDEZ','luis.hernandez@byrmex.com','','','','','','','','','','','','','','',''),(8,'2020-06-04 19:15:42','2020-06-04 19:15:42',0,'',0,'',0,'',1,1,'PROVEEDOR','CONTADO',0,'8','TORNILLOS Y HERRAMIENTAS DOSA S.A. DE C.V.','THD041214TG1','','TAMPICO','TAMAULIPAS','MEXICO','','(833)2-26-50-35','','HILDA ANGELICA HERRERA','hildadosa@hotmail.com.mx','','','','','','','','','','','','','','',''),(9,'2020-06-04 19:20:38','2020-06-04 19:20:38',0,'',0,'',0,'',1,1,'PROVEEDOR','CONTADO',0,'9','EQUIPUMP S.A. DE C.V.','EQU970402NSA',' RICARDO FLORES MAGON NO. 518, COL. SANTA MA. LA RIBERA',' CIUDAD DE MEXICO','','MEXICO',' 0640','55-5541-1331','','MIGUEL MEJIA','miguelmejia@equi-pump.com','','','','','','','','','','','','','','',''),(10,'2020-06-04 19:38:32','2020-06-04 19:38:32',0,'',0,'',0,'',1,1,'PROVEEDOR','CREDITO',30,'10','TRANSMISIONES Y EQUIPOS INDUSTRIALES, S.A. DE C.V.','TEI820204S67','DAKOTA NO. 157, COL. NAPOLES, ','BENITO JUAREZ','CIUDAD DE MÉXICO','MEXICO','03810','55 5687-7514','','ARTURO ALVAREZ',' tecnicos@teisa.com.mx','','','cobranza@teisa.com.mx','','','','','','','','','','','',''),(11,'2020-06-04 19:45:48','2020-06-04 19:45:48',0,'',0,'',0,'',1,1,'PROVEEDOR','CONTADO',0,'11','RODAMIENTOS Y ACCESORIOS S.A. DE C.V.','RAC870406P40','AV. NOGALAR NO. 107, COL. CUAUHTEMOC, SAN NICOLAS DE LOS GARZA','NUEVO LEON','MONTERREY','MEXICO','','833 850 80 57','','JORGE HERNANDEZ DEL ANGEL','Jorge.hernandez@ryasa.com.mx','','','facturaryasa@ryasa.com.mx','','','','','','','','','','','',''),(12,'2020-06-04 19:53:29','2020-06-04 19:53:29',0,'',0,'',0,'',1,1,'PROVEEDOR','CONTADO',0,'12','MATERIALES INDUSTRIALES DE MEXICO, S.A. DE C.V. ',' MIM7207049C1','','ALTAMIRA','TAMAULIPAS','MEXICO','','833-346-6869','','ANABEL MERAZ CONTRERAS','anabel.meraz@grupo-mim.com','','','facturas@mim.fep.com.mx','','','','','','','','','','','',''),(13,'2020-06-04 20:01:48','2020-06-04 20:01:48',0,'',0,'',0,'',1,1,'PROVEEDOR','CONTADO',0,'13','COHISER DE TAMPICO S.A. DE C.V.',' XAXX01010100',' CARRETERA TAMPICO MANTE NO. 2309-B, COL. DEL BOSQUE','TAMPICO','TAMAULIPAS','MEXICO',' 8931','(833) 132 0490','','ZURY CANDELARIO',' telemarketing@cohisertampico.com.mx','','','','','','','','','','','','','','',''),(14,'2020-06-04 20:15:57','2020-06-04 20:15:57',0,'',0,'',0,'',1,1,'PROVEEDOR','CONTADO',0,'14','WM. W. MEYER & SONS, INC.','','1700 BLVD. LIVERTYVILLE','','ILLINOIS','ESTADOS UNIDOS','','9847-918-0111','','JOE KOWALSKI','JKowalski@wmwmeyer.com','','','','','','','','','','','','','','',''),(15,'2020-06-04 20:24:10','2020-06-04 20:24:10',0,'',0,'',0,'',1,1,'PROVEEDOR','CONTADO',0,'15','BOMBAS GRUNDFOS DE MEXICO S.A. DE C.V.','BGM931210HJ5','BLVD. TLC NO. 15 PARQUE IND. STIVA AEROPUERTO ','APODACA','NUEVO LEON','MEXICO',' 6660','(52) 81 8144 4000','','ISAAC GARCIA ','servicioaclientes-mx@sales.grundfos.com','','','kaluna@grundfos.com','','','','','','','','','','','',''),(16,'2020-06-04 20:31:50','2020-06-04 20:31:50',0,'',0,'',0,'',1,1,'PROVEEDOR','CONTADO',0,'16','POWER PROCESS CONTROL S.A. DE C.V.','PPC0209114F0','','TAMPICO','TAMAULIPAS','MEXICO','','(833) 241 1880','','Ericka Gabriela Uribe Rusca ','euribe@ppcsesco.com','','MELISSA GEA','mgea@ppcsesco.com>','','','','','','','','','','','',''),(17,'2020-06-04 20:43:02','2020-06-04 20:43:02',0,'',0,'',0,'',1,1,'PROVEEDOR','CREDITO',30,'17','PROPART INC.','02-0580-220','','AUSTIN','TEXAS','ESTADOS UNIDOS','','(512) 266-8276 ','','JUAN GUTIERREZ','juan@propartusa.com','O 512.266.8276','LISA LAMB','lisa@propartusa.com','','','','','','','','','','','',''),(18,'2020-06-04 20:47:04','2020-06-04 20:47:04',0,'',0,'',0,'',1,1,'PROVEEDOR','CONTADO',0,'18','LOGOS INDUSTRIAL, S.A. DE C.V.',' LIN001107J30','SAN PEDRO Nº 223 COLONIA FRACCIONAMIENTO SAN ANGEL ALTAMIRA ','ALTAMIRA','TAMAULIPAS','MEXICO','89604','(833) 2-26-64-98 ','','JAVIER PEREZ NUÑEZ','javier.perez@logosindustrial.com.mx','','','','','','','','','','','','','','',''),(19,'2020-06-04 20:53:00','2020-06-04 20:53:00',0,'',0,'',0,'',1,1,'PROVEEDOR','CONTADO',0,'19','LA CASA DEL BOMBERO','TOMP500629GP0','AVENIDA MONTERREY 701-3 COL: ENRIQUE CARDENAS GONZALEZ ','TAMPICO','TAMAULIPAS','MEXICO',' 8930','(833)-3-06-23-92','','KARINA SILVA SALVADOR',' casadelbombero@hotmail.com','','','','','','','','','','','','','','',''),(20,'2020-07-17 20:21:00','2020-07-17 20:27:37',0,'',0,'',0,'',1,1,'PROVEEDOR','CONTADO',0,'20','RODAMIENTOS Y ACCESORIOS S.A. DE C.V. ','',' CALLE SEXTA AVENIDA NO. 310 VILLAHERMOSA, 89319 TAMPICO, TAMPS.','TAMPICO','TAMAULIPAS','MEXICO','','833 393 2503','','Jorge Hernandez Del Angel ','jorge.hernandez@ryasa.com','','','','','','','','','','','','','','',''),(21,'2020-07-23 20:54:57','2020-07-23 20:54:57',0,'',0,'',0,'',1,1,'PROVEEDOR','CONTADO',0,'21','IWAKI AMERICA ','','5 BOYTON ROAD','HOLLISTON','MA','ESTADOS UNIDOS','01746','','','Adán López','alopez@iwakiamerica.com','','','','','','','','','','','','','','',''),(22,'2020-07-30 15:26:33','2020-07-30 15:26:33',0,'',0,'',0,'',1,1,'PROVEEDOR','CREDITO',15,'22','MANTENIMIENTO Y MECANIZADO ARVE S.A DE C.V','','CALLE 4TA # 1601 COL. EJIDO MIRAMAR','ALTAMIRA','TAMAULIPAS','MEXICO','89604','833 128 2656','','Juan Manuel Arteaga Rodriguez','soporte@mymec.com.mx','','','','','','','','','','','','','','',''),(23,'2020-08-04 15:18:50','2020-08-04 15:42:01',0,'',0,'',0,'',1,1,'PROVEEDOR','CONTADO',0,'23','MEXTRADE INTERNACIONAL, S.A. DE C.V.','','ABRAHAM LINCOLN # 5693 COL. VALLE VERDE 2DO SECTOR','MONTERREY','NUEVO LEÓN','MEXICO','64117','(81) 8864-0700','','DANIEL GARZA','dgarza@mextradeint.com','','','','','','','','','','','','','','',''),(24,'2020-08-04 15:41:31','2020-08-04 15:41:31',0,'',0,'',0,'',1,1,'PROVEEDOR','CONTADO',0,'24','TUBERIAS Y VALVULAS DEL NOROESTES.A. DE C.V.','','AV. ALFONSO REYES 4080 NTE, COL. DEL NORTE','MONTERREY ','NUEVO LEON','MEXICO','','818 3513232','','ING. ALEJANDRO MARTINEZ','instrumentacionmty01@tuvanosa.com','','','','','','','','','','','','','','',''),(25,'2020-08-10 17:34:42','2020-08-10 17:34:42',0,'',0,'',0,'',1,1,'PROVEEDOR','CONTADO',0,'25','IRSA ACEROS SA DE CV','','SAN LUIS POTOSI','SAN LUIS','','MEXICO','','','','','','448188679','','','','','','','','','','','','','',''),(26,'2020-08-10 19:15:26','2020-08-10 19:16:19',0,'',0,'',0,'',1,1,'PROVEEDOR','CONTADO',0,'26','IIRSACERO S.A DE C.V','IIR980112LT','AVE. LOS ÁNGELES  600 OTE. COL. DEL NORT','','MONTERREY','MEXICO','64500','83 51 25 00','','Alejandra Lozano','alejandra.lozano@iirsacero.com.mx','','','','','','','','','','','','','','',''),(27,'2020-08-11 21:55:22','2020-08-11 21:55:22',0,'',0,'',0,'',1,1,'PROVEEDOR','CONTADO',0,'27','CORESA PARTES Y EQUIPOS S.A. DE C.V.','','HIDALGO 403 CENTRO','SAN PEDRO GARZA GARCÍA','NUEVO LEON','MEXICO','66230','833 212 65 00','','Jose Gamaliel Alonso Gonzalez','jalonso@grupocoresa.com','','','','','','','','','','','','','','',''),(28,'2020-08-13 00:55:34','2020-08-13 01:32:13',0,'',0,'',0,'',1,1,'PROVEEDOR','CONTADO',0,'28','JOSE MANUELA SEQUERA LOYA','SELM810318P1A','CALLE CLAVEL NO. 109 COL. LAS VIOLETAS','TAMPICO','TAMAULIPAS','MEXICO','89368','833 218 3267','','JOSE MANUEL SEQUERA LOYA','VENTAS@MYSIND.COM.MX','','','','','','','','','','','','','','',''),(29,'2020-08-13 20:19:52','2020-08-13 20:19:52',0,'',0,'',0,'',1,1,'PROVEEDOR','CONTADO',0,'29','CRAMIX - FLUIDMIX GROUP S.A. DE C.V.','','','IRAPUATO','GUANAJUATO','MEXICO','','','','RICARDO VILLAFAÑA','r.villafana@autmix.com','','','','','','','','','','','','','','',''),(30,'2020-08-13 21:18:14','2020-08-13 21:18:14',0,'',0,'',0,'',1,1,'PROVEEDOR','CONTADO',0,'30','ESPECIALIDADES INDUSTRIALES STIGLITZ, S.A. DE C.V.','EIS1609036P1','','TAMPICO','TAMAULIPAS','MEXICO','','8332180328','','ROLANDO CABA','industrias.stiglitz@gmail','','','','','','','','','','','','','','',''),(31,'2020-08-13 21:26:53','2020-08-13 21:26:53',0,'',0,'',0,'',1,1,'PROVEEDOR','CREDITO',0,'31','SOELSA SA DE CV','','CALLE PINO 201 COLONIA DEL BOSQUE','','','MEXICO','','','','JULIO HERNANDEZ','','','','','','','','','','','','','','','','');

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
  `nombre` text,
  `descripcion` text,
  `nombre_cliente` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `proyecto` */

insert  into `proyecto`(`id`,`fecha_elaboracion`,`fecha_modificacion`,`empresa_id`,`empresa`,`unidad_id`,`unidad`,`usuario_id`,`usuario`,`activo`,`no_paper`,`cliente_id`,`fecha_inicio`,`fecha_fin`,`nombre`,`descripcion`,`nombre_cliente`) values (1,NULL,NULL,0,'',0,'',2,'LUIS HUMBERTO GOMEZ TIBURCIO',1,1,12,NULL,NULL,'SIN PROYECTO','',''),(2,NULL,'2020-07-30 20:02:53',0,'',0,'',8,'LUIS HUMBERTO REYNA GURROLA',1,1,12,'2020-07-29 00:00:00','2020-07-29 00:00:00','MAQUINADO DE FLECHA DE BOMBA DE VACIO','MAQUINADO DE FLECHA DE BOMBA DE VACIO',' CIA. AZUCARERA DEL RIO GUAYALEJO, S.A. DE C.V. '),(3,NULL,'2020-07-30 20:00:49',0,'',0,'',8,'LUIS HUMBERTO REYNA GURROLA',1,1,12,'2020-07-30 00:00:00','2020-07-30 00:00:00','REPARACION DE ROTOR DE BOMBA','REPARACIÓN DE FLECHA DE BOMBA DE VACIÓ \nINCLUYE\nMAQUINADO DE FLECHA\nMETALIZADO DE FLECHA EN ZONA DE IMPULSOR\nRECTIFICADO DE IMPULSOR \nRECTIFICADO DE CUÑERO\nMAQUINADO DE ROSCAS\nFABRICACIÓN DE MANGAS\n\n',' CIA. AZUCARERA DEL RIO GUAYALEJO, S.A. DE C.V. ');

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `remision_entrega` */

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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `requisicion` */

insert  into `requisicion`(`id`,`fecha_elaboracion`,`fecha_modificacion`,`empresa_id`,`empresa`,`unidad_id`,`unidad`,`usuario_id`,`usuario`,`estado_doc`,`estado`,`folio`,`serie`,`observaciones`,`tipo_documento`,`tipo_archivo`,`razon_cancelar`,`cliente_id`,`cliente`,`cliente_rfc`,`metodo_pago`,`moneda`,`tipo_cambio`,`importe`,`descuento`,`subtotal`,`iva`,`total`,`porc_iva`,`importe_letra`,`activo`,`prioridad`,`fecha_requerida`,`direccion_entrega`,`tiempo_entrega`,`fecha_orden_compra`,`folio_orden_compra`,`autoriza_id`,`autoriza`,`fecha_autorizo`,`firma_autorizo`,`solicita`,`firma_solicita`,`proveedor_id`,`proveedor`,`cotizacion_id`,`folio_cotizacion`) values (1,'2020-08-16 22:26:08','2020-08-16 22:27:57',0,'',0,'',1,'JESUS ARMANDO SEQUERA LOYA','TERMINADO','','RQ00002','','','REQUISICION DE COMPRA','PDF','',0,'','','',NULL,NULL,0.00,0.0,0.00,0.0,0.00,0,'',1,'ALTA','2020-08-16 00:00:00','','1 DIAS','2020-08-16 22:28:28','OC00002',1,'JESUS ARMANDO SEQUERA LOYA','2020-08-16 22:27:57','FA9719051AF282651F106184D0CAABC5','JESUS ARMANDO SEQUERA LOYA','FA9719051AF282651F106184D0CAABC5',3,' ABSORMEX CMPC TISSUE, S.A. DE C.V.',1,'CT00002'),(2,'2020-08-16 23:38:25','2020-08-16 23:48:33',0,'',0,'',1,'JESUS ARMANDO SEQUERA LOYA','POR AUTORIZAR','','RQ00003','','','REQUISICION DE COMPRA','PDF','',0,'','','',NULL,NULL,0.00,0.0,0.00,0.0,0.00,0,'',1,'ALTA','2020-08-16 00:00:00','','1 DIAS',NULL,NULL,3,'GABRIELA ALEJANDRA BARRIOS REYNA',NULL,NULL,'JESUS ARMANDO SEQUERA LOYA','B72ADDE4E0E821EF25F748FC67354D6F',3,' ABSORMEX CMPC TISSUE, S.A. DE C.V.',2,'CT00003'),(3,'2020-08-16 23:46:34','2020-08-16 23:48:41',0,'',0,'',1,'JESUS ARMANDO SEQUERA LOYA','POR AUTORIZAR','','RQ00004','','','REQUISICION DE COMPRA','PDF','',0,'','','',NULL,NULL,0.00,0.0,0.00,0.0,0.00,0,'',1,'ALTA','2020-08-16 00:00:00','','1 DIAS',NULL,NULL,3,'GABRIELA ALEJANDRA BARRIOS REYNA',NULL,NULL,'JESUS ARMANDO SEQUERA LOYA','8BE035B05D5EAE0A312291C59BCE2D71',3,' ABSORMEX CMPC TISSUE, S.A. DE C.V.',3,'CT00004'),(4,'2020-08-16 23:48:09','2020-08-16 23:48:09',0,'',0,'',1,'JESUS ARMANDO SEQUERA LOYA','EN PROCESO','','RQ00005','','','REQUISICION DE COMPRA','PDF','',0,'','','',NULL,NULL,0.00,0.0,0.00,0.0,0.00,0,'',1,'ALTA','2020-08-16 00:00:00','','1 DIAS',NULL,NULL,3,'GABRIELA ALEJANDRA BARRIOS REYNA',NULL,NULL,'JESUS ARMANDO SEQUERA LOYA','90107984E26065F625DF4BABBC206916',3,' ABSORMEX CMPC TISSUE, S.A. DE C.V.',3,'CT00004');

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
  `no_parte` varchar(100) DEFAULT NULL,
  `no_serie` varchar(100) DEFAULT NULL,
  `modelo` varchar(100) DEFAULT NULL,
  `marca` varchar(100) DEFAULT NULL,
  `codigo_interno` varchar(10) DEFAULT NULL,
  `codigo_proveedor` varchar(100) DEFAULT NULL,
  `partida_cotizacion_id` int(10) DEFAULT NULL,
  `cotizacion_id` int(10) DEFAULT NULL,
  `folio_cotizacion` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `requisicion_det` */

insert  into `requisicion_det`(`id`,`folio`,`documento_id`,`empresa_id`,`empresa`,`unidad_id`,`unidad`,`usuario_id`,`usuario`,`no_partida`,`fecha_alta`,`producto_id`,`descripcion`,`unidad_medida`,`cantidad`,`precio_unitario`,`importe`,`descuento`,`subtotal`,`porc_iva`,`iva`,`total`,`servicio`,`no_parte`,`no_serie`,`modelo`,`marca`,`codigo_interno`,`codigo_proveedor`,`partida_cotizacion_id`,`cotizacion_id`,`folio_cotizacion`) values (1,'RQ00002',1,NULL,NULL,NULL,NULL,NULL,NULL,0,'2020-08-16 22:26:17',1,'COPLE ES10 OMEGA REXNOR','PIEZA',10,6300.00,63000.00,NULL,NULL,16,NULL,NULL,0,'','','ES10','OMEGA REXNOR ','1','3',1,1,'CT00002'),(2,'RQ00003',2,NULL,NULL,NULL,NULL,NULL,NULL,0,'2020-08-16 23:38:39',59,'PRODUCTO KIT DE PRUEBA','KIT',1,4000.00,4000.00,NULL,NULL,16,NULL,NULL,0,'','','HOLA','','59','3',2,2,'CT00003'),(3,'RQ00004',3,NULL,NULL,NULL,NULL,NULL,NULL,0,'2020-08-16 23:48:01',58,'PRODUCTO PRUEBA PARA VENTA Y COMPRA DE MANTENIMIENTO DE BOMBA','PIEZA',1,3000.00,3000.00,NULL,NULL,16,NULL,NULL,0,'','','','','58','3',3,3,'CT00004'),(4,'RQ00005',4,NULL,NULL,NULL,NULL,NULL,NULL,0,'2020-08-16 23:48:15',58,'PRODUCTO PRUEBA PARA VENTA Y COMPRA DE MANTENIMIENTO DE BOMBA','PIEZA',1,3000.00,3000.00,NULL,NULL,16,NULL,NULL,0,'','','','','58','3',3,3,'CT00004');

/*Table structure for table `view_cotizaciones_disponibles` */

DROP TABLE IF EXISTS `view_cotizaciones_disponibles`;

/*!50001 DROP VIEW IF EXISTS `view_cotizaciones_disponibles` */;
/*!50001 DROP TABLE IF EXISTS `view_cotizaciones_disponibles` */;

/*!50001 CREATE TABLE  `view_cotizaciones_disponibles`(
 `id` int(10) ,
 `fecha_elaboracion` datetime ,
 `fecha_modificacion` datetime ,
 `empresa_id` int(10) ,
 `empresa` varchar(100) ,
 `unidad_id` int(10) ,
 `unidad` varchar(100) ,
 `usuario_id` int(10) ,
 `usuario` varchar(100) ,
 `estado_doc` varchar(100) ,
 `estado` varchar(100) ,
 `folio` varchar(100) ,
 `serie` varchar(100) ,
 `observaciones` text ,
 `tipo_documento` varchar(100) ,
 `tipo_archivo` varchar(100) ,
 `razon_cancelar` text ,
 `cliente_id` int(10) ,
 `cliente` varchar(100) ,
 `cliente_rfc` varchar(100) ,
 `metodo_pago` varchar(100) ,
 `moneda` varchar(100) ,
 `tipo_cambio` double(8,2) ,
 `importe` double(15,2) ,
 `descuento` double(10,1) ,
 `subtotal` double(15,2) ,
 `iva` double(10,1) ,
 `total` double(15,2) ,
 `porc_iva` int(10) ,
 `importe_letra` text ,
 `activo` tinyint(1) ,
 `emisor_rfc` varchar(100) ,
 `emisor_nombre` varchar(100) ,
 `emisor_calle` varchar(100) ,
 `emisor_numero_exterior` varchar(100) ,
 `emisor_numero_interior` varchar(100) ,
 `emisor_codigo_postal` varchar(100) ,
 `emisor_colonia` varchar(100) ,
 `emisor_municipio` varchar(100) ,
 `emisor_estado` varchar(100) ,
 `emisor_pais` varchar(100) ,
 `receptor_nombre` varchar(100) ,
 `receptor_calle` varchar(100) ,
 `receptor_numero_exterior` varchar(100) ,
 `receptor_numero_interior` varchar(100) ,
 `receptor_codigo_postal` varchar(100) ,
 `receptor_colonia` varchar(100) ,
 `receptor_municipio` varchar(100) ,
 `receptor_estado` varchar(100) ,
 `receptor_pais` varchar(100) ,
 `vendedor_id` int(10) ,
 `vendedor` varchar(100) ,
 `dias_caduca` int(10) ,
 `condiciones_pago` text ,
 `condiciones_cotizacion` text ,
 `tiempo_tentrega` varchar(200) ,
 `terminada_requisicion` tinyint(1) ,
 `terminada_no_requisicion` tinyint(1) ,
 `proyecto_id` int(10) ,
 `proyecto` varchar(200) ,
 `solicitante` varchar(100) ,
 `atencion` varchar(100) ,
 `referencia_cliente` varchar(250) 
)*/;

/*Table structure for table `view_cotizaciones_sin_requisicion` */

DROP TABLE IF EXISTS `view_cotizaciones_sin_requisicion`;

/*!50001 DROP VIEW IF EXISTS `view_cotizaciones_sin_requisicion` */;
/*!50001 DROP TABLE IF EXISTS `view_cotizaciones_sin_requisicion` */;

/*!50001 CREATE TABLE  `view_cotizaciones_sin_requisicion`(
 `view_cotizacion_id` int(10) 
)*/;

/*View structure for view view_cotizaciones_disponibles */

/*!50001 DROP TABLE IF EXISTS `view_cotizaciones_disponibles` */;
/*!50001 DROP VIEW IF EXISTS `view_cotizaciones_disponibles` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_cotizaciones_disponibles` AS select `cotizacion_venta`.`id` AS `id`,`cotizacion_venta`.`fecha_elaboracion` AS `fecha_elaboracion`,`cotizacion_venta`.`fecha_modificacion` AS `fecha_modificacion`,`cotizacion_venta`.`empresa_id` AS `empresa_id`,`cotizacion_venta`.`empresa` AS `empresa`,`cotizacion_venta`.`unidad_id` AS `unidad_id`,`cotizacion_venta`.`unidad` AS `unidad`,`cotizacion_venta`.`usuario_id` AS `usuario_id`,`cotizacion_venta`.`usuario` AS `usuario`,`cotizacion_venta`.`estado_doc` AS `estado_doc`,`cotizacion_venta`.`estado` AS `estado`,`cotizacion_venta`.`folio` AS `folio`,`cotizacion_venta`.`serie` AS `serie`,`cotizacion_venta`.`observaciones` AS `observaciones`,`cotizacion_venta`.`tipo_documento` AS `tipo_documento`,`cotizacion_venta`.`tipo_archivo` AS `tipo_archivo`,`cotizacion_venta`.`razon_cancelar` AS `razon_cancelar`,`cotizacion_venta`.`cliente_id` AS `cliente_id`,`cotizacion_venta`.`cliente` AS `cliente`,`cotizacion_venta`.`cliente_rfc` AS `cliente_rfc`,`cotizacion_venta`.`metodo_pago` AS `metodo_pago`,`cotizacion_venta`.`moneda` AS `moneda`,`cotizacion_venta`.`tipo_cambio` AS `tipo_cambio`,`cotizacion_venta`.`importe` AS `importe`,`cotizacion_venta`.`descuento` AS `descuento`,`cotizacion_venta`.`subtotal` AS `subtotal`,`cotizacion_venta`.`iva` AS `iva`,`cotizacion_venta`.`total` AS `total`,`cotizacion_venta`.`porc_iva` AS `porc_iva`,`cotizacion_venta`.`importe_letra` AS `importe_letra`,`cotizacion_venta`.`activo` AS `activo`,`cotizacion_venta`.`emisor_rfc` AS `emisor_rfc`,`cotizacion_venta`.`emisor_nombre` AS `emisor_nombre`,`cotizacion_venta`.`emisor_calle` AS `emisor_calle`,`cotizacion_venta`.`emisor_numero_exterior` AS `emisor_numero_exterior`,`cotizacion_venta`.`emisor_numero_interior` AS `emisor_numero_interior`,`cotizacion_venta`.`emisor_codigo_postal` AS `emisor_codigo_postal`,`cotizacion_venta`.`emisor_colonia` AS `emisor_colonia`,`cotizacion_venta`.`emisor_municipio` AS `emisor_municipio`,`cotizacion_venta`.`emisor_estado` AS `emisor_estado`,`cotizacion_venta`.`emisor_pais` AS `emisor_pais`,`cotizacion_venta`.`receptor_nombre` AS `receptor_nombre`,`cotizacion_venta`.`receptor_calle` AS `receptor_calle`,`cotizacion_venta`.`receptor_numero_exterior` AS `receptor_numero_exterior`,`cotizacion_venta`.`receptor_numero_interior` AS `receptor_numero_interior`,`cotizacion_venta`.`receptor_codigo_postal` AS `receptor_codigo_postal`,`cotizacion_venta`.`receptor_colonia` AS `receptor_colonia`,`cotizacion_venta`.`receptor_municipio` AS `receptor_municipio`,`cotizacion_venta`.`receptor_estado` AS `receptor_estado`,`cotizacion_venta`.`receptor_pais` AS `receptor_pais`,`cotizacion_venta`.`vendedor_id` AS `vendedor_id`,`cotizacion_venta`.`vendedor` AS `vendedor`,`cotizacion_venta`.`dias_caduca` AS `dias_caduca`,`cotizacion_venta`.`condiciones_pago` AS `condiciones_pago`,`cotizacion_venta`.`condiciones_cotizacion` AS `condiciones_cotizacion`,`cotizacion_venta`.`tiempo_tentrega` AS `tiempo_tentrega`,`cotizacion_venta`.`terminada_requisicion` AS `terminada_requisicion`,`cotizacion_venta`.`terminada_no_requisicion` AS `terminada_no_requisicion`,`cotizacion_venta`.`proyecto_id` AS `proyecto_id`,`cotizacion_venta`.`proyecto` AS `proyecto`,`cotizacion_venta`.`solicitante` AS `solicitante`,`cotizacion_venta`.`atencion` AS `atencion`,`cotizacion_venta`.`referencia_cliente` AS `referencia_cliente` from `cotizacion_venta` where (`cotizacion_venta`.`folio` in (select distinct `cotizacion_venta_det`.`folio` from `cotizacion_venta_det` where (`cotizacion_venta_det`.`requisicion_id` = 0)) and (`cotizacion_venta`.`estado_doc` = 'TERMINADO')) */;

/*View structure for view view_cotizaciones_sin_requisicion */

/*!50001 DROP TABLE IF EXISTS `view_cotizaciones_sin_requisicion` */;
/*!50001 DROP VIEW IF EXISTS `view_cotizaciones_sin_requisicion` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_cotizaciones_sin_requisicion` AS select distinct `cotizacion_venta_det`.`documento_id` AS `view_cotizacion_id` from `cotizacion_venta_det` where ((`cotizacion_venta_det`.`requisicion_id` = 0) or isnull(`cotizacion_venta_det`.`requisicion_id`)) */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
