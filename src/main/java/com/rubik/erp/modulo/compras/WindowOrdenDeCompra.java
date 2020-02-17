/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rubik.erp.modulo.compras;

import com.rubik.erp.config._Folios;
import com.rubik.erp.domain.ConfiguracionDomain;
import com.rubik.erp.domain.EmpleadoDomain;
import com.rubik.erp.model.Configuracion;
import com.rubik.erp.model.Empleado;
import com.rubik.erp.model.OrdenDeCompra;
import com.rubik.erp.model.OrdenDeCompraDet;
import com.rubik.erp.model.Proveedor;
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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
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
    
    Button btnAgregarPartida = new Button("Agregar",Fam3SilkIcon.ADD);
    Button btnModificarPartida = new Button("Modificar",Fam3SilkIcon.PENCIL);
    Button btnEliminarPartida = new Button("Eliminar",Fam3SilkIcon.DELETE);
    Button btnVisualizarDocs = new Button("Documentos",Fam3SilkIcon.FOLDER_EXPLORE);
    Button btnGuardar = new Button("Guardar", Fam3SilkIcon.DISK);
    Button btnCancelar = new Button("Cancelar", Fam3SilkIcon.CANCEL);
    
    Grid<OrdenDeCompraDet> gridRemisionDet = new Grid<>();
    List<OrdenDeCompraDet> listRemisionDet = new ArrayList<>();
    
    List<Empleado> listAutorizadoresCompras = new ArrayList<>();
    
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
        
        gridRemisionDet.setHeight("272");
        gridRemisionDet.setSelectionMode(Grid.SelectionMode.SINGLE);

        gridRemisionDet.addColumn(OrdenDeCompraDet::getCantidad).setCaption("CTD").setWidth(75);
        gridRemisionDet.addColumn(OrdenDeCompraDet::getDescripcion).setCaption("DESCRIPCION");
        gridRemisionDet.addColumn(OrdenDeCompraDet::getPrecio_unitario).setCaption("P.U.").setWidth(100);
        gridRemisionDet.addColumn(OrdenDeCompraDet::getTotal).setCaption("TOTAL").setWidth(100);

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
//                        listRemisionDet.add(partida);
//                        gridRemisionDet.setItems(listRemisionDet);
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
//                            listRemisionDet.add(partida);
//                            gridRemisionDet.setItems(listRemisionDet);
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
//                                listRemisionDet.remove(partida);
//                                gridRemisionDet.setItems(listRemisionDet);
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
//                    for (RemisionDet partidaTemp : listRemisionDet) {
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
//                    for (RemisionDet partidaTemp : listRemisionDet) { // Obtiene el total
//                        total += partidaTemp.getTotal();
//                    }
//
//                    ordenDeCompra.setTotal(total);
//                    service.RemisionInsert(ordenDeCompra);
//                    updateFolio();
//                    
//                    for (RemisionDet partidaTemp : listRemisionDet) { //Guarda la partida con el ID de la ordenDeCompra
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
        
        txtSolicita.setEnabled(false);
        txtSolicita.setValue(empleado.getNombre_completo());
        txtObservaciones.setRows(2);
        txtDireccionEntrega.setRows(2);
       
        txtFechaRequerida.setWidth(strWidth);
        txtSolicita.setWidth(strWidth);
        txtObservaciones.setWidth(strWidth);
        cboAutorizador.setWidth(strWidth);
        txtDireccionEntrega.setWidth(strWidth);
        
        cboAutorizador.setItems(getAutorizadorCompras());
        cboAutorizador.setSelectedItem(listAutorizadoresCompras.get(0));
        cboAutorizador.setEmptySelectionAllowed(false);

        if (isEdit) {
            binder.readBean(ordenDeCompra);
            
            gridRemisionDet.setItems(getPartidas());
            
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

        cont.setSpacing(false);
        cont.addComponents(lblFolio,
                new HorizontalLayout(new Label("Folio Remision:"), txtFolioRemision) // 1
                    {{
                        setComponentAlignment(getComponent(0), Alignment.MIDDLE_CENTER);
                    }},
                new HorizontalLayout(fLay1, fLay2), // 2
                new HorizontalLayout(new Label("Autorizador:"), cboAutorizador, new Label("Se recibe en:"), cboAlmacenRecibe) // 3
                    {{
                        setComponentAlignment(getComponent(0), Alignment.MIDDLE_CENTER);
                        setComponentAlignment(getComponent(2), Alignment.MIDDLE_CENTER);
                    }}, 
                new HorizontalLayout(btnAgregarPartida,btnModificarPartida,btnEliminarPartida), // 4
                gridRemisionDet, // 5
                fLayTotales, // 6
                new HorizontalLayout(btnCancelar, btnGuardar)); // 7
        
        cont.setComponentAlignment(cont.getComponent(0), Alignment.MIDDLE_CENTER);
        cont.setComponentAlignment(cont.getComponent(1), Alignment.MIDDLE_RIGHT);
        cont.setComponentAlignment(cont.getComponent(2), Alignment.MIDDLE_CENTER);
        cont.setComponentAlignment(cont.getComponent(3), Alignment.MIDDLE_CENTER);
        cont.setComponentAlignment(cont.getComponent(4), Alignment.MIDDLE_CENTER);
        cont.setComponentAlignment(cont.getComponent(5), Alignment.MIDDLE_CENTER);
        cont.setComponentAlignment(cont.getComponent(6), Alignment.MIDDLE_RIGHT);
        cont.setComponentAlignment(cont.getComponent(7), Alignment.MIDDLE_CENTER);
        
        setContent(cont);
        setModal(true);
        setResizable(false);
        setClosable(false);
    }
    
    public List getPartidas() {
        String strWhere = " documento_id = " + ordenDeCompra.getId();

//        RemisionDetDomain service = new RemisionDetDomain();
//        service.getRemisionDet(strWhere, "", " id DESC");
//        listRemisionDet = service.getObjects();
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
        domain.getOneConfiguracion(_Folios.FOLIO_ORDEN_COMPRA, _Folios.SERIE_ORDEN_COMPRA);
        Configuracion conf = domain.getObject();
        
        folio = conf.getSerie() + ManageString.fillWithZero(conf.getFolio(), 5);
        
        return folio;
    }
    
    public void updateFolio() {
        ConfiguracionDomain domain = new ConfiguracionDomain();
        domain.ConfiguracionUpdate(_Folios.FOLIO_ORDEN_COMPRA);
    }
    
}