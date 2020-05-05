/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rubik.erp.modulo.compras;

import com.rubik.erp.config._Folios;
import com.rubik.erp.config._Pago_Documentos;
import com.rubik.erp.domain.ConfiguracionDomain;
import com.rubik.erp.domain.EmpleadoDomain;
import com.rubik.erp.domain.ProveedorDomain;
import com.rubik.erp.domain.RemisionDetDomain;
import com.rubik.erp.model.Configuracion;
import com.rubik.erp.model.Empleado;
import com.rubik.erp.model.OrdenDeCompra;
import com.rubik.erp.model.OrdenDeCompraDet;
import com.rubik.erp.model.Proveedor;
import com.rubik.erp.model.Remision;
import com.rubik.erp.model.RemisionDet;
import com.rubik.manage.ManageDates;
import com.rubik.manage.ManageNumbers;
import com.rubik.manage.ManageString;
import com.vaadin.data.Binder;
import com.vaadin.data.converter.LocalDateToDateConverter;
import com.vaadin.data.converter.StringToDoubleConverter;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.rubicone.vaadin.fam3.silk.Fam3SilkIcon;

/**
 *
 * @author Dev
 */
public class WindowOrdenDeCompra  extends Window {
    
    Empleado empleado = (Empleado) VaadinSession.getCurrent().getSession().getAttribute("USUARIO_ACTIVO");
    
    VerticalLayout cont = new VerticalLayout();
    String title_window = "Remisiones de Compra";

    OrdenDeCompra ordenDeCompra;
    Remision remision;
    List<RemisionDet> listRemisionDet = new ArrayList<>();
    
    Boolean isEdit = false;
    
    TextField txtFolioRemision = new TextField();
    NativeSelect<Proveedor> cboProveedor = new NativeSelect("Proveedor:");
    NativeSelect<String> cboCondicionesPago = new NativeSelect("Condiciones Pago:");
    NativeSelect<String> cboMetodoPago = new NativeSelect("Metodo Pago:");
    NativeSelect<String> cboMoneda = new NativeSelect("Moneda:");
    TextField txtTipoCambio = new TextField("Tipo de Cambio:");
    
    DateField txtFechaRequerida = new DateField("Fecha Requerida:", LocalDate.now());
    DateField txtFechaEntrega = new DateField("Fecha Entrega:", LocalDate.now());
    TextArea txtObservaciones = new TextArea("Observaciones:");
    
    TextField txtSolicita = new TextField("Solicitante:");
    NativeSelect<Empleado> cboAutorizador = new NativeSelect();
    TextArea txtDireccionEntrega = new TextArea("Direccion Entrega:");
    
    NativeSelect<String> cboAlmacenRecibe = new NativeSelect();
    TextField txtImporte = new TextField("Importe:");
    TextField txtDescuento = new TextField("Descuento:");
    TextField txtSubtotal = new TextField("Subtotal:");
    TextField txtIVA = new TextField("IVA:");
    TextField txtTotal = new TextField("Total:");
    
    Button btnAgregarPartida = new Button("",Fam3SilkIcon.ADD);
    Button btnModificarPartida = new Button("",Fam3SilkIcon.PENCIL);
    Button btnEliminarPartida = new Button("",Fam3SilkIcon.DELETE);
    Button btnVisualizarDocs = new Button("Documentos",Fam3SilkIcon.FOLDER_EXPLORE);
    Button btnBuscarRemision = new Button("",Fam3SilkIcon.MAGNIFIER);
    Button btnGuardar = new Button("Guardar", Fam3SilkIcon.DISK);
    Button btnCancelar = new Button("Cancelar", Fam3SilkIcon.CANCEL);
    
    Grid<OrdenDeCompraDet> gridOrdenDeCompraDet = new Grid<>();
    List<OrdenDeCompraDet> listOrdenDeCompraDet = new ArrayList<>();
    
    List<Empleado> listAutorizadoresCompras = new ArrayList<>();
    List<Proveedor> proveedorList = new ArrayList<>();
    
    Label lblFolio;
    String folio = "";

    public WindowOrdenDeCompra() {
        lblFolio = new Label("ORDEN DE COMPRA " + getFolio()) {
            {
                setStyleName("h2");
            }
        };
        initComponents();
        btnModificarPartida.setEnabled(false);
    }

    public WindowOrdenDeCompra(OrdenDeCompra ordenTemp) {
        isEdit = true;
        ordenDeCompra = ordenTemp;
        lblFolio = new Label("ORDEN DE COMPRA " + ordenDeCompra.getFolio()) {
            {
                setStyleName("h2");
            }
        };
        initComponents();
    }

    public void initComponents() {
        String strWidth = "300";
        
        setWidth("80%");
        setHeight("80%");
        
        Binder<OrdenDeCompra> binder = new Binder<>();
        
        binder.forField(txtFolioRemision).bind(OrdenDeCompra::getFolio_remision, OrdenDeCompra::setFolio_remision);
        binder.forField(cboCondicionesPago).bind(OrdenDeCompra::getCond_pago, OrdenDeCompra::setCond_pago);
        binder.forField(cboMetodoPago).bind(OrdenDeCompra::getMetodo_pago, OrdenDeCompra::setMetodo_pago);
        binder.forField(cboMoneda).bind(OrdenDeCompra::getMoneda, OrdenDeCompra::setMoneda);
        binder.forField(txtTipoCambio).withConverter(new StringToDoubleConverter(0.0, "El valor debe ser numerico")).bind(OrdenDeCompra::getTipo_cambio, OrdenDeCompra::setTipo_cambio);
        binder.forField(txtFechaRequerida).withConverter(new LocalDateToDateConverter(ZoneId.systemDefault())).bind(OrdenDeCompra::getFecha_requisicion, OrdenDeCompra::setFecha_requisicion);
        binder.forField(txtFechaEntrega).withConverter(new LocalDateToDateConverter(ZoneId.systemDefault())).bind(OrdenDeCompra::getFecha_entrega, OrdenDeCompra::setFecha_entrega);
        binder.forField(txtObservaciones).bind(OrdenDeCompra::getObservaciones, OrdenDeCompra::setObservaciones);
        binder.forField(txtSolicita).bind(OrdenDeCompra::getSolicita, OrdenDeCompra::setSolicita);
        binder.forField(txtDireccionEntrega).bind(OrdenDeCompra::getDireccion_entrega, OrdenDeCompra::setDireccion_entrega);
        binder.forField(cboAlmacenRecibe).bind(OrdenDeCompra::getRecibe, OrdenDeCompra::setRecibe);
        binder.forField(txtImporte).withConverter(new StringToDoubleConverter(0.0, "El valor debe ser numerico")).bind(OrdenDeCompra::getImporte, OrdenDeCompra::setImporte);
        binder.forField(txtDescuento).withConverter(new StringToDoubleConverter(0.0, "El valor debe ser numerico")).bind(OrdenDeCompra::getDescuento, OrdenDeCompra::setDescuento);
        binder.forField(txtSubtotal).withConverter(new StringToDoubleConverter(0.0, "El valor debe ser numerico")).bind(OrdenDeCompra::getSubtotal, OrdenDeCompra::setSubtotal);
        binder.forField(txtIVA).withConverter(new StringToDoubleConverter(0.0, "El valor debe ser numerico")).bind(OrdenDeCompra::getIva, OrdenDeCompra::setIva);
        binder.forField(txtTotal).withConverter(new StringToDoubleConverter(0.0, "El valor debe ser numerico")).bind(OrdenDeCompra::getTotal, OrdenDeCompra::setTotal);
        
        gridOrdenDeCompraDet.setHeight("272");
        gridOrdenDeCompraDet.setSelectionMode(Grid.SelectionMode.SINGLE);

        gridOrdenDeCompraDet.addColumn(OrdenDeCompraDet::getCantidad).setCaption("CTD").setWidth(75);
        gridOrdenDeCompraDet.addColumn(OrdenDeCompraDet::getDescripcion).setCaption("DESCRIPCION");
        gridOrdenDeCompraDet.addComponentColumn(this::getTextField).setCaption("P.U.").setWidth(120);
        gridOrdenDeCompraDet.addColumn(OrdenDeCompraDet::getTotal).setCaption("TOTAL").setWidth(120);

        btnAgregarPartida.addClickListener((event) -> {
//            WindowRemisionDet windows = new WindowRemisionDet(ordenDeCompra);
//            windows.center();
//            windows.setModal(true);
//            windows.addCloseListener((e) -> {
//                Boolean ok = (Boolean) VaadinSession.getCurrent().getSession().getAttribute("PARTIDA_OK");
//                if (ok) {
//                    if (isEdit) {
//                        gridRemisionDet.setItems(getPartidas());
//                    } else {
//                        RemisionDet partida = (RemisionDet) VaadinSession.getCurrent().getSession().getAttribute("REMISION_DET");
//                        listOrdenDeCompraDet.add(partida);
//                        gridRemisionDet.setItems(listOrdenDeCompraDet);
//                    }
//                }
//            });
//            getUI().addWindow(windows);
        });
        
        btnModificarPartida.addClickListener((event) -> {
//            if(gridRemisionDet.getSelectedItems().size() == 1){
//                WindowRemisionDet windows = new WindowRemisionDet(ordenDeCompra,gridRemisionDet.getSelectedItems().iterator().next());
//                windows.center();
//                windows.setModal(true);
//                windows.addCloseListener((e) -> {
//                    Boolean ok = (Boolean) VaadinSession.getCurrent().getSession().getAttribute("PARTIDA_OK");
//                    if (ok) {
//                        if (isEdit) {
//                            gridRemisionDet.setItems(getPartidas());
//                        } else {
//                            RemisionDet partida = (RemisionDet) VaadinSession.getCurrent().getSession().getAttribute("REMISION_DET");
//                            listOrdenDeCompraDet.add(partida);
//                            gridRemisionDet.setItems(listOrdenDeCompraDet);
//                        }
//                    }
//                });
//                getUI().addWindow(windows);
//            } else {
//                MessageBox.createError()
//                        .withCaption("Error!")
//                        .withMessage("Debe tener una partida seleccionada para poder modificarla.")
//                        .withRetryButton()
//                        .open();
//            }
        });
        
        btnEliminarPartida.addClickListener((event) -> {
//            if (gridRemisionDet.getSelectedItems().size() == 1) {
//                MessageBox.createQuestion()
//                        .withCaption("Atencion!")
//                        .withMessage("Desea eliminar la partida seleccionada?.")
//                        .withOkButton(() -> {
//
//                            RemisionDet partida = gridRemisionDet.getSelectedItems().iterator().next();
//
//                            if (isEdit) {
//                                RemisionDetDomain dom = new RemisionDetDomain();
//                                dom.RemisionDetDelete(partida);
//                                gridRemisionDet.setItems(getPartidas());
//                            } else {
//                                listOrdenDeCompraDet.remove(partida);
//                                gridRemisionDet.setItems(listOrdenDeCompraDet);
//                            }
//
//                        })
//                        .withNoButton(()-> {
//                        })
//                        .open();
//            } else {
//                MessageBox.createError()
//                        .withCaption("Error!")
//                        .withMessage("Debe tener una partida seleccionada para poder eliminarla.")
//                        .withRetryButton()
//                        .open();
//            }
        });
        
        btnCancelar.addClickListener((event) -> {
            close();
        });

        btnGuardar.addClickListener((event) -> {
//            try {
//                Empleado autoriza = cboAutorizador.getValue();
//                Double total = 0.0;
//                
//                if(!isEdit){
//                    ordenDeCompra = new Remision();
//                }
//
//                binder.writeBean(ordenDeCompra);
//                toUpperCase();
//
//                ordenDeCompra.setUsuario_id(empleado.getId());
//                ordenDeCompra.setUsuario(empleado.getNombre_completo());
//                ordenDeCompra.setEstado_doc(_DocumentoEstados.EN_PROCESO);
//                ordenDeCompra.setTipo_documento(_DocumentoTipos.REMISION_DE_COMPRA);
//                ordenDeCompra.setTipo_archivo("PDF");
//                ordenDeCompra.setFecha_requerida(ManageDates.getDateFromLocalDate(txtFechaRequerida.getValue()));
//                ordenDeCompra.setAutoriza(autoriza.getNombre_completo());
//                ordenDeCompra.setAutoriza_id(autoriza.getId());
//                ordenDeCompra.setActivo(true);
//                
//                RemisionDomain service = new RemisionDomain();
//
//                if (isEdit) {
//                    ordenDeCompra.setFecha_modificacion(new Date());
//                    for (RemisionDet partidaTemp : listOrdenDeCompraDet) {
//                        total += partidaTemp.getTotal();
//                    }
//                    ordenDeCompra.setTotal(total);
//                    
//                    service.RemisionUpdate(ordenDeCompra);
//                    
//                } else {
//                    ordenDeCompra.setFolio(getFolio());
//                    ordenDeCompra.setSerie("");
//                    
//                    RemisionDetDomain domainDet = new RemisionDetDomain();
//                    
//                    for (RemisionDet partidaTemp : listOrdenDeCompraDet) { // Obtiene el total
//                        total += partidaTemp.getTotal();
//                    }
//
//                    ordenDeCompra.setTotal(total);
//                    service.RemisionInsert(ordenDeCompra);
//                    updateFolio();
//                    
//                    for (RemisionDet partidaTemp : listOrdenDeCompraDet) { //Guarda la partida con el ID de la ordenDeCompra
//                        partidaTemp.setFolio(ordenDeCompra.getFolio());
//                        partidaTemp.setDocumento_id(ordenDeCompra.getId());
//                        domainDet.RemisionDetInsert(partidaTemp);
//                    }
//                }
//
//                if (service.getOk()) {
//                    MessageBox.createInfo()
//                            .withCaption("Atencion")
//                            .withMessage(service.getNotification())
//                            .open();
//                    close();
//                } else {
//                    MessageBox.createError()
//                            .withCaption("Error!")
//                            .withMessage(service.getNotification())
//                            .withRetryButton()
//                            .open();
//                }
//            } catch (Exception ex) {
//                MessageBox.createError()
//                        .withCaption("Error!")
//                        .withMessage("Verifique que la informacion este completa o sea correcta. ")
//                        .withRetryButton()
//                        .open();
//            }
        });
        
        btnBuscarRemision.addClickListener((event) -> {
                WindowRemisionSeleccionar windows = new WindowRemisionSeleccionar();
                windows.center();
                windows.setModal(true);
                windows.addCloseListener((e) -> {
                    if(windows.seleccionado){
                        remision = windows.remision;
                        
                        txtSolicita.setValue(remision.getSolicita());
                        txtFolioRemision.setValue(remision.getFolio());
                        txtFechaRequerida.setValue(ManageDates.getLocalDateFromDate(remision.getFecha_requerida()));
                        txtObservaciones.setValue(remision.getObservaciones());
                        cboMetodoPago.setValue(remision.getMetodo_pago());
//                        cboMoneda.setValue(remision.getMoneda());
//                        txtTipoCambio.setValue(remision.getTipo_cambio()+"");
                        txtImporte.setValue(remision.getImporte()+"");
                        txtDescuento.setValue(remision.getDescuento()+"");
                        txtSubtotal.setValue(remision.getSubtotal()+"");
                        txtIVA.setValue(remision.getIva()+"");
                        txtTotal.setValue(remision.getTotal()+"");
                        txtDireccionEntrega.setValue(remision.getDireccion_entrega());
                        
                        llenarPartidas();
                        
                    }
                });
                getUI().addWindow(windows);
        });
        
        txtSolicita.setEnabled(false);
        txtSolicita.setValue(empleado.getNombre_completo());
        txtObservaciones.setRows(2);
        txtDireccionEntrega.setRows(2);
        
        cboCondicionesPago.setItems(_Pago_Documentos.CONDICIONES_PAGO);
        cboCondicionesPago.setValue(_Pago_Documentos.CONDICIONES_PAGO.get(0));
        cboCondicionesPago.setEmptySelectionAllowed(false);

        cboMetodoPago.setItems(_Pago_Documentos.METODOS_PAGO);
        cboMetodoPago.setValue(_Pago_Documentos.METODOS_PAGO.get(0));
        cboMetodoPago.setEmptySelectionAllowed(false);
        
        cboMoneda.setItems(_Pago_Documentos.MONEDAS);
        cboMoneda.setValue(_Pago_Documentos.MONEDAS.get(0));
        cboMoneda.setEmptySelectionAllowed(false);
        
        cboAlmacenRecibe.setItems("Almacen Matriz");
        cboAlmacenRecibe.setValue("Almacen Matriz");
        cboAlmacenRecibe.setEmptySelectionAllowed(false);

        gridOrdenDeCompraDet.setWidth("100%");
        
        txtSolicita.setWidth(strWidth);
        txtFechaRequerida.setWidth(strWidth);
        txtDireccionEntrega.setWidth(strWidth);
        txtFechaEntrega.setWidth(strWidth);
        cboProveedor.setWidth(strWidth);
        
        cboCondicionesPago.setWidth(strWidth);
        cboMetodoPago.setWidth(strWidth);
        cboMoneda.setWidth(strWidth);
        txtTipoCambio.setWidth(strWidth);
        txtObservaciones.setWidth(strWidth);
        
        cboAutorizador.setWidth(strWidth);
        cboAlmacenRecibe.setWidth("150");
        
        txtImporte.setWidth("160");
        txtDescuento.setWidth("160");
        txtSubtotal.setWidth("160");
        txtIVA.setWidth("160");
        txtTotal.setWidth("160");
        
        cboAutorizador.setItems(getAutorizadorCompras());
        cboAutorizador.setSelectedItem(listAutorizadoresCompras.get(0));
        cboAutorizador.setEmptySelectionAllowed(false);

        cboProveedor.setItems(getProveedor());
        cboProveedor.setSelectedItem(proveedorList.get(0));
        cboProveedor.setEmptySelectionAllowed(false);
        
        txtFolioRemision.setEnabled(false);
        txtImporte.setEnabled(false);
        txtSubtotal.setEnabled(false);
        txtIVA.setEnabled(false);
        txtTotal.setEnabled(false);
        
        if (isEdit) {
            binder.readBean(ordenDeCompra);
            
            gridOrdenDeCompraDet.setItems(getPartidas());
            
            for (Empleado autorizador : listAutorizadoresCompras) {
                if (ordenDeCompra.getAutoriza_id().equals(autorizador.getId())) {
                    cboAutorizador.setValue(autorizador);
                }
            }
        }
        
        // ACOMODO =============================================================
        FormLayout fLay1 = new FormLayout();
        fLay1.addComponents(txtSolicita,txtFechaRequerida, txtDireccionEntrega, txtFechaEntrega, cboProveedor);
        fLay1.setSpacing(false);
        
        FormLayout fLay2 = new FormLayout();
        fLay2.addComponents(cboCondicionesPago, cboMetodoPago, cboMoneda, txtTipoCambio,txtObservaciones);
        fLay2.setSpacing(false);
        
        FormLayout fLayTotales = new FormLayout();
        fLayTotales.addComponents(txtImporte,txtDescuento, txtSubtotal, txtIVA, txtTotal);
        fLayTotales.setSpacing(false);
        fLayTotales.setWidth("100%");
        
        HorizontalLayout hLay1 = new HorizontalLayout(
                new Label("Autorizador:"), cboAutorizador, new Label("Almacen:"), cboAlmacenRecibe,
                btnAgregarPartida,btnModificarPartida,btnEliminarPartida);
        hLay1.setSpacing(true);
        hLay1.setComponentAlignment(hLay1.getComponent(0), Alignment.MIDDLE_CENTER);
        hLay1.setComponentAlignment(hLay1.getComponent(2), Alignment.MIDDLE_CENTER);

        cont.setSpacing(false);
        cont.addComponents(lblFolio,
                new HorizontalLayout(new Label("Folio Remision:"), txtFolioRemision, btnBuscarRemision) // 1
                    {{
                        setComponentAlignment(getComponent(0), Alignment.MIDDLE_CENTER);
                    }},
                new HorizontalLayout(fLay1, fLay2), // 2
                hLay1, // 3
                gridOrdenDeCompraDet, // 4
                fLayTotales, // 5
                new HorizontalLayout(btnCancelar, btnGuardar)); // 6
        
        cont.setComponentAlignment(lblFolio, Alignment.MIDDLE_CENTER);
        cont.setComponentAlignment(cont.getComponent(1), Alignment.MIDDLE_RIGHT);
        cont.setComponentAlignment(cont.getComponent(2), Alignment.MIDDLE_CENTER);
        cont.setComponentAlignment(hLay1, Alignment.MIDDLE_RIGHT);
        cont.setComponentAlignment(cont.getComponent(4), Alignment.MIDDLE_CENTER);
        cont.setComponentAlignment(cont.getComponent(5), Alignment.MIDDLE_RIGHT);
        cont.setComponentAlignment(cont.getComponent(6), Alignment.MIDDLE_CENTER);
        
        setContent(cont);
        setModal(true);
        setResizable(false);
        setClosable(false);
    }
    
    public List getPartidas() {
        String strWhere = " documento_id = " + ordenDeCompra.getId();

//        RemisionDetDomain service = new RemisionDetDomain();
//        service.getRemisionDet(strWhere, "", " id DESC");
//        listOrdenDeCompraDet = service.getObjects();
//
//        if (!service.getOk()) {
//            MessageBox.createError()
//                    .withCaption("Error al cargar la informacion!")
//                    .withMessage("Err: " + service.getNotification())
//                    .withRetryButton()
//                    .open();
//        }
        return listRemisionDet;
    }
    
    public void toUpperCase() {
        ordenDeCompra.setSolicita(txtSolicita.getValue().toUpperCase());
        ordenDeCompra.setObservaciones(txtObservaciones.getValue().toUpperCase());
        ordenDeCompra.setDireccion_entrega(txtDireccionEntrega.getValue().toUpperCase());
    }
    
    public List<Empleado> getAutorizadorCompras() {
        EmpleadoDomain provService = new EmpleadoDomain();
        provService.getEmpleado(" autorizador = 1 ", "", " nombre ASC");
        
        listAutorizadoresCompras = provService.getObjects();
        return listAutorizadoresCompras;
    }
    
    public String getFolio() {
        ConfiguracionDomain domain = new ConfiguracionDomain();
        domain.getOneFolioConfiguracion(_Folios.FOLIO_ORDEN_COMPRA, _Folios.SERIE_ORDEN_COMPRA);
        Configuracion conf = domain.getObject();
        
        folio = conf.getSerie() + ManageString.fillWithZero(conf.getFolio(), 5);
        
        return folio;
    }
    
    public void updateFolio() {
        ConfiguracionDomain domain = new ConfiguracionDomain();
        domain.ConfiguracionFolioUpdate(_Folios.FOLIO_ORDEN_COMPRA);
    }
    
    public List<Proveedor> getProveedor() {
        ProveedorDomain provService = new ProveedorDomain();
        provService.getProveedor("", "", "razon_social ASC");

        proveedorList = provService.getObjects();
        return proveedorList;
    }
    
    public void llenarPartidas(){
        RemisionDetDomain remisionDetService = new RemisionDetDomain();
        remisionDetService.getRemisionDet(" documento_id = " + remision.getId(), "", "");
        listRemisionDet = remisionDetService.getObjects();
        
        for (RemisionDet partTemp : listRemisionDet) {
            int i = 0;
            
            OrdenDeCompraDet partida = new OrdenDeCompraDet();
            partida.setFolio(folio);
            partida.setDocumento_id(0);
            partida.setEmpresa_id(0);
            partida.setEmpresa("");
            partida.setUnidad_id(0);
            partida.setUnidad("");
            partida.setUsuario_id(empleado.getId());
            partida.setUsuario(empleado.getNombre_completo());
            partida.setNo_partida(i++);
            partida.setFecha_alta(new Date());
            partida.setCantidad(partTemp.getCantidad());
            partida.setProducto_id(partTemp.getProducto_id());
            partida.setDescripcion(partTemp.getDescripcion());
            partida.setUnidad_medida(partTemp.getUnidad_medida());
            partida.setPorc_iva(partTemp.getPorc_iva());
            partida.setPrecio_unitario(partTemp.getPrecio_unitario());
            partida.setImporte(partTemp.getImporte());
            partida.setDescuento(partTemp.getDescuento());
            partida.setSubtotal(partTemp.getSubtotal());
            partida.setIva(partTemp.getIva());
            partida.setTotal(partTemp.getTotal());
            partida.setServicio(partTemp.getServicio());
            partida.setFolio_remision(remision.getFolio());
            partida.setRemision_id(remision.getId());
            partida.setNo_parte(partTemp.getNo_parte());
            partida.setNo_serie(partTemp.getNo_serie());
            partida.setModelo(partTemp.getModelo());
            partida.setMarca(partTemp.getMarca());
            partida.setCodigo_interno(partTemp.getCodigo_interno());
            partida.setCodigo_proveedor(partTemp.getCodigo_proveedor());
            
            listOrdenDeCompraDet.add(partida);
        }
        
        gridOrdenDeCompraDet.setItems(listOrdenDeCompraDet);
        
//        calcularTotales();
    }
    
    public void calcularTotales(){
        Double importe = 0.0;
        Double descuento = 0.0;
        Double subtotal = 0.0;
        Double IVA = 0.0;
        Double total = 0.0;
        
        for (OrdenDeCompraDet ordenDeCompraDet : listOrdenDeCompraDet) {
            importe += ordenDeCompraDet.getImporte();
            descuento += ordenDeCompraDet.getDescuento();
            subtotal += ordenDeCompraDet.getSubtotal();
            IVA += ordenDeCompraDet.getIva();
            total += ordenDeCompraDet.getTotal();
        }

        txtImporte.setValue(importe+"");
        txtDescuento.setValue(descuento+"");
        txtSubtotal.setValue(subtotal+"");
        txtIVA.setValue(IVA+"");
        txtTotal.setValue(total+"");
    }
    
    public TextField getTextField(OrdenDeCompraDet part){
        
        TextField txtTotal = new TextField();
        txtTotal.setStyleName(ValoTheme.TEXTFIELD_ALIGN_RIGHT);
        
        if(part.getPrecio_unitario()!=null){
            txtTotal.setValue(part.getPrecio_unitario().toString());
        }else{
            txtTotal.setValue("0.00");
        }

        txtTotal.setWidth("100");
        txtTotal.setMaxLength(15);
        
        txtTotal.addFocusListener((event) -> {
            if(txtTotal.getValue().length() == 0){
                txtTotal.setValue("0.0");
            }else{
                txtTotal.setValue(txtTotal.getValue());
            }
            txtTotal.setSelection(0, txtTotal.getValue().length());
        });
        
        txtTotal.addValueChangeListener((event) -> {
            txtTotal.setValue(txtTotal.getValue().trim());

            if(txtTotal.getValue().length() >= 0){
                if(!ManageNumbers.isStringToDouble(txtTotal.getValue())){
                    txtTotal.setValue("0.0");
                }else{
//                    part.setPrecio_unitario(Double.parseDouble(txtTotal.getValue()));
                }
            }else{
                txtTotal.setValue("0.0");
            }
            
            part.setPrecio_unitario(Double.parseDouble(txtTotal.getValue()));
            calcularPartida(part);
            System.out.println("CALCULAR TOTALES --- ");
            
//            gridOrdenDeCompraDet.
        });
       
        return txtTotal;
    }
    
    public void calcularPartida(OrdenDeCompraDet part){
        part.setImporte( part.getPrecio_unitario() * part.getCantidad() );
        part.setDescuento(0.0);
        part.setSubtotal(part.getImporte() - part.getDescuento());
        part.setIva(part.getSubtotal() * .16);
        part.setTotal(part.getSubtotal() + part.getIva());
    }
    
}