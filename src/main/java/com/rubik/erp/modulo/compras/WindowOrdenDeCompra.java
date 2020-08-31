/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rubik.erp.modulo.compras;

import com.rubik.erp.config._CONTABILIDAD;
import com.rubik.erp.config._DocumentoEstados;
import com.rubik.erp.config._DocumentoTipos;
import com.rubik.erp.config._Folios;
import com.rubik.erp.config._Pago_Documentos;
import com.rubik.erp.config._Pais;
import com.rubik.erp.domain.ConfiguracionDomain;
import com.rubik.erp.domain.EmpleadoDomain;
import com.rubik.erp.domain.OrdenDeCompraDetDomain;
import com.rubik.erp.domain.OrdenDeCompraDomain;
import com.rubik.erp.domain.ProveedorDomain;
import com.rubik.erp.domain.RequisicionDetDomain;
import com.rubik.erp.domain.RequisicionDomain;
import com.rubik.erp.model.Configuracion;
import com.rubik.erp.model.Empleado;
import com.rubik.erp.model.OrdenDeCompra;
import com.rubik.erp.model.OrdenDeCompraDet;
import com.rubik.erp.model.Proveedor;
import com.rubik.erp.model.Requisicion;
import com.rubik.erp.model.RequisicionDet;
import com.rubik.erp.modulo.generic.WindowVisorDocumentos;
import com.rubik.manage.ManageDates;
import com.rubik.manage.ManageNumbers;
import com.rubik.manage.ManageString;
import com.rubik.manage.Numero_Letras;
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
import de.steinwedel.messagebox.MessageBox;
import java.text.DecimalFormat;
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
public class WindowOrdenDeCompra extends Window {
    
    Empleado empleado = (Empleado) VaadinSession.getCurrent().getSession().getAttribute("USUARIO_ACTIVO");
    
    VerticalLayout cont = new VerticalLayout();
    String title_window = "Requisiciones de Compra";

    OrdenDeCompra ordenDeCompra;
    Requisicion requisicion;
    List<RequisicionDet> listRequisicionDet = new ArrayList<>();
    
    Boolean isEdit = false;
    
    TextField txtFolioRequisicion = new TextField();
    NativeSelect<Proveedor> cboProveedor = new NativeSelect("Proveedor:");
    NativeSelect<String> cboCondicionesPago = new NativeSelect("Condiciones Pago:");
    NativeSelect<String> cboMetodoPago = new NativeSelect("Metodo Pago:");
    NativeSelect<String> cboMoneda = new NativeSelect("Moneda:");
    TextField txtTipoCambio = new TextField("Tipo de Cambio:");
    
    DateField txtFechaRequerida = new DateField("Fecha Requerida:", LocalDate.now());
    DateField txtFechaEntrega = new DateField("Fecha Entrega:", LocalDate.now());
        
    TextField txtSolicita = new TextField("Solicitante:");
    NativeSelect<Empleado> cboAutorizador = new NativeSelect();
    
    NativeSelect<String> cboAlmacenRecibe = new NativeSelect();
    TextField txtImporte = new TextField("Importe:");
    TextField txtDescuento = new TextField("Descuento:");
    TextField txtSubtotal = new TextField("Subtotal:");
    TextField txtIVA = new TextField("IVA:");
    TextField txtTotal = new TextField("Total:");
    TextField txtCotizacionProveedor = new TextField();
    
    TextField txtTiempoEntrega = new TextField("Tiempo de Entrega:");
    
    TextArea txtDireccionEntrega = new TextArea("Direccion Entrega:");
    TextArea txtMontoLetra = new TextArea("Monto con Letra:");
    TextArea txtObservaciones = new TextArea("Observaciones:");

    Button btnAgregarPartida = new Button("",Fam3SilkIcon.ADD);
    Button btnModificarPartida = new Button("",Fam3SilkIcon.PENCIL);
    Button btnEliminarPartida = new Button("",Fam3SilkIcon.DELETE);
    Button btnCotizacionesProvedores = new Button("Cotizaciones",Fam3SilkIcon.FOLDER_EXPLORE);
    Button btnBuscarRequisicion = new Button("",Fam3SilkIcon.MAGNIFIER);
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
        
        ordenDeCompra = new OrdenDeCompra();
        
        initComponents();
        btnCotizacionesProvedores.setEnabled(false);
        txtTipoCambio.setValue("1");
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
        
        binder.forField(txtFolioRequisicion).bind(OrdenDeCompra::getFolio_requisicion, OrdenDeCompra::setFolio_requisicion);
        binder.forField(cboCondicionesPago).bind(OrdenDeCompra::getCond_pago, OrdenDeCompra::setCond_pago);
        binder.forField(cboMetodoPago).bind(OrdenDeCompra::getMetodo_pago, OrdenDeCompra::setMetodo_pago);
        binder.forField(cboMoneda).bind(OrdenDeCompra::getMoneda, OrdenDeCompra::setMoneda);
        binder.forField(txtTipoCambio).withConverter(new StringToDoubleConverter(0.0, "El valor debe ser numerico")).bind(OrdenDeCompra::getTipo_cambio, OrdenDeCompra::setTipo_cambio);
        binder.forField(txtFechaRequerida).withConverter(new LocalDateToDateConverter(ZoneId.systemDefault())).bind(OrdenDeCompra::getFecha_requisicion, OrdenDeCompra::setFecha_requisicion);
        binder.forField(txtFechaEntrega).withConverter(new LocalDateToDateConverter(ZoneId.systemDefault())).bind(OrdenDeCompra::getFecha_entrega, OrdenDeCompra::setFecha_entrega);
        binder.forField(txtObservaciones).bind(OrdenDeCompra::getObservaciones, OrdenDeCompra::setObservaciones);
        binder.forField(txtSolicita).bind(OrdenDeCompra::getSolicita, OrdenDeCompra::setSolicita);
        binder.forField(txtDireccionEntrega).bind(OrdenDeCompra::getDireccion_entrega, OrdenDeCompra::setDireccion_entrega);
        binder.forField(txtCotizacionProveedor).bind(OrdenDeCompra::getCotizacion_proveedor, OrdenDeCompra::setCotizacion_proveedor);
        
        binder.forField(txtTiempoEntrega).bind(OrdenDeCompra::getTiempo_entrega, OrdenDeCompra::setTiempo_entrega);
        binder.forField(txtMontoLetra).bind(OrdenDeCompra::getImporte_letra, OrdenDeCompra::setImporte_letra);

        binder.forField(cboAlmacenRecibe).bind(OrdenDeCompra::getRecibe, OrdenDeCompra::setRecibe);
        binder.forField(txtImporte).withConverter(new StringToDoubleConverter(0.0, "El valor debe ser numerico")).bind(OrdenDeCompra::getImporte, OrdenDeCompra::setImporte);
        binder.forField(txtDescuento).withConverter(new StringToDoubleConverter(0.0, "El valor debe ser numerico")).bind(OrdenDeCompra::getDescuento, OrdenDeCompra::setDescuento);
        binder.forField(txtSubtotal).withConverter(new StringToDoubleConverter(0.0, "El valor debe ser numerico")).bind(OrdenDeCompra::getSubtotal, OrdenDeCompra::setSubtotal);
        binder.forField(txtIVA).withConverter(new StringToDoubleConverter(0.0, "El valor debe ser numerico")).bind(OrdenDeCompra::getIva, OrdenDeCompra::setIva);
        binder.forField(txtTotal).withConverter(new StringToDoubleConverter(0.0, "El valor debe ser numerico")).bind(OrdenDeCompra::getTotal, OrdenDeCompra::setTotal);
        
        txtSolicita.setEnabled(false);
        txtSolicita.setValue(empleado.getNombre_completo());
        
        txtObservaciones.setRows(3);
        txtDireccionEntrega.setRows(3);
        txtMontoLetra.setRows(3);
        
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

        gridOrdenDeCompraDet.setHeight("272");
        gridOrdenDeCompraDet.setSelectionMode(Grid.SelectionMode.SINGLE);
        
        gridOrdenDeCompraDet.addColumn(OrdenDeCompraDet::getCantidad).setCaption("CTD").setWidth(75);
        gridOrdenDeCompraDet.addColumn(OrdenDeCompraDet::getDescripcion).setCaption("DESCRIPCION");
        gridOrdenDeCompraDet.addColumn(OrdenDeCompraDet::getPrecio_unitario).setCaption("P.U.").setWidth(120);
        gridOrdenDeCompraDet.addColumn(OrdenDeCompraDet::getImporte).setCaption("IMPORTE").setWidth(120);
        gridOrdenDeCompraDet.setWidth("100%");
        
        gridOrdenDeCompraDet.addItemClickListener(event -> {
            if (event.getMouseEventDetails().isDoubleClick()) {
                if (gridOrdenDeCompraDet.getSelectedItems().size() == 1) {
                    WindowOrdenDeCompraDet windows = new WindowOrdenDeCompraDet(ordenDeCompra, gridOrdenDeCompraDet.getSelectedItems().iterator().next());
                    windows.center();
                    windows.setModal(true);
                    windows.addCloseListener((e) -> {
                        Boolean ok = (Boolean) VaadinSession.getCurrent().getSession().getAttribute("PARTIDA_COMPRA_OK");
                        if (ok) {
                            if (isEdit) {
                                gridOrdenDeCompraDet.setItems(getPartidas());
                            } else {
                                gridOrdenDeCompraDet.setItems(listOrdenDeCompraDet);
                            }
                        }
                        calcularTotales();
                    });
                    getUI().addWindow(windows);
                } else {
                    MessageBox.createError()
                            .withCaption("Error!")
                            .withMessage("Debe tener una partida seleccionada para poder modificarla.")
                            .withRetryButton()
                            .open();
                }
            }
        });

        btnAgregarPartida.addClickListener((event) -> {
            WindowOrdenDeCompraDet windows = new WindowOrdenDeCompraDet(ordenDeCompra);
            windows.center();
            windows.setModal(true);
            windows.addCloseListener((e) -> {
                Boolean ok = (Boolean) VaadinSession.getCurrent().getSession().getAttribute("PARTIDA_COMPRA_OK");
                if (ok) {
                    if (isEdit) {
                        gridOrdenDeCompraDet.setItems(getPartidas());
                    } else {
                        OrdenDeCompraDet partida = (OrdenDeCompraDet) VaadinSession.getCurrent().getSession().getAttribute("ORDEN_COMPRA_DET");
                        listOrdenDeCompraDet.add(partida);
                        gridOrdenDeCompraDet.setItems(listOrdenDeCompraDet);
                    }
                }
                calcularTotales();
            });
            getUI().addWindow(windows);
        });
        
        btnModificarPartida.addClickListener((event) -> {
            OrdenDeCompraDet partidaSeleccionada = gridOrdenDeCompraDet.getSelectedItems().iterator().next();
            
            if (partidaSeleccionada != null) {
                WindowOrdenDeCompraDet windows = new WindowOrdenDeCompraDet(ordenDeCompra,partidaSeleccionada);
                windows.center();
                windows.setModal(true);
                windows.addCloseListener((e) -> {
                    Boolean ok = (Boolean) VaadinSession.getCurrent().getSession().getAttribute("PARTIDA_COMPRA_OK");
                    if (ok) {
                        if (isEdit) {
                            gridOrdenDeCompraDet.setItems(getPartidas());
                        } else {
                            gridOrdenDeCompraDet.setItems(listOrdenDeCompraDet);
                        }
                    }
                    calcularTotales();
                });
                getUI().addWindow(windows);
            } else {
                MessageBox.createError()
                        .withCaption("Error!")
                        .withMessage("Debe tener una partida seleccionada para poder modificarla.")
                        .withRetryButton()
                        .open();
            }
        });
        
        btnEliminarPartida.addClickListener((event) -> {
            if (gridOrdenDeCompraDet.getSelectedItems().size() == 1) {
                MessageBox.createQuestion()
                        .withCaption("Atencion!")
                        .withMessage("Desea eliminar la partida seleccionada?.")
                        .withOkButton(() -> {

                            OrdenDeCompraDet partida = gridOrdenDeCompraDet.getSelectedItems().iterator().next();

                            if (isEdit) {
                                OrdenDeCompraDetDomain dom = new OrdenDeCompraDetDomain();
                                dom.OrdenDeCompraDetDelete(partida);
                                gridOrdenDeCompraDet.setItems(getPartidas());
                            } else {
                                listOrdenDeCompraDet.remove(partida);
                                gridOrdenDeCompraDet.setItems(listOrdenDeCompraDet);
                            }
                            
                            calcularTotales();
                        })
                        .withNoButton(()-> {
                        })
                        .open();
            } else {
                MessageBox.createError()
                        .withCaption("Error!")
                        .withMessage("Debe tener una partida seleccionada para poder eliminarla.")
                        .withRetryButton()
                        .open();
            }
        });
        
        btnCancelar.addClickListener((event) -> {
            close();
        });

        btnGuardar.addClickListener((event) -> {
            try {
                Empleado autoriza = cboAutorizador.getValue();
                Double total = 0.0;

                binder.writeBean(ordenDeCompra);
                toUpperCase();

                ordenDeCompra.setUsuario_id(empleado.getId());
                ordenDeCompra.setUsuario(empleado.getNombre_completo());
                ordenDeCompra.setEstado_doc(_DocumentoEstados.EN_PROCESO);
                ordenDeCompra.setTipo_documento(_DocumentoTipos.ORDEN_DE_COMPRA);
                ordenDeCompra.setTipo_archivo("PDF");
                ordenDeCompra.setAutoriza(autoriza.getNombre_completo());
                ordenDeCompra.setAutoriza_id(autoriza.getId());
                ordenDeCompra.setActivo(true);
                
                ordenDeCompra.setProveedor_id(cboProveedor.getValue().getId());
                ordenDeCompra.setProveedor(cboProveedor.getValue().getRazon_social());
                
                OrdenDeCompraDomain service = new OrdenDeCompraDomain();

                if (isEdit) {
                    ordenDeCompra.setFecha_modificacion(new Date());
                    service.OrdenDeCompraUpdate(ordenDeCompra);
                    
                } else {
                    ordenDeCompra.setFolio(getFolio());
                    ordenDeCompra.setSerie("");

                    service.OrdenDeCompraInsert(ordenDeCompra);
                    updateFolio();

                    OrdenDeCompraDetDomain domainDet = new OrdenDeCompraDetDomain();
                    
                    for (OrdenDeCompraDet partidaTemp : listOrdenDeCompraDet) { //Guarda la partida con el ID de la ordenDeCompra
                        partidaTemp.setFolio(ordenDeCompra.getFolio());
                        partidaTemp.setDocumento_id(ordenDeCompra.getId());
                        partidaTemp.setCodigo_proveedor(ordenDeCompra.getProveedor_id()+"");
                        domainDet.OrdenDeCompraDetInsert(partidaTemp);
                    }
                    
                    requisicion.setFecha_orden_compra(new Date());
                    requisicion.setFolio_orden_compra(ordenDeCompra.getFolio());
                    requisicion.setEstado_doc(_DocumentoEstados.TERMINADO);
                    
                    RequisicionDomain reqDomain = new RequisicionDomain();
                    reqDomain.RequisicionUpdate(requisicion);
                }

                if (service.getOk()) {
                    MessageBox.createInfo()
                            .withCaption("Atencion")
                            .withMessage(service.getNotification())
                            .open();
                    close();
                } else {
                    MessageBox.createError()
                            .withCaption("Error!")
                            .withMessage(service.getNotification())
                            .withRetryButton()
                            .open();
                }
            } catch (Exception ex) {
                MessageBox.createError()
                        .withCaption("Error!")
                        .withMessage("Verifique que la informacion este completa o sea correcta. ")
                        .withRetryButton()
                        .open();
            }
        });
        
        btnBuscarRequisicion.addClickListener((event) -> {
            WindowRequisicionSeleccionar windows = new WindowRequisicionSeleccionar();
            windows.center();
            windows.setModal(true);
            windows.addCloseListener((e) -> {
                if (windows.seleccionado) {
                    requisicion = windows.requisicion_selected;

                    ordenDeCompra.setRequisicion_id(requisicion.getId());

                    txtSolicita.setValue(requisicion.getSolicita());
                    ordenDeCompra.setComprador(requisicion.getSolicita());
                    ordenDeCompra.setCotizacion_id(requisicion.getCotizacion_id());
                    ordenDeCompra.setCotizacion(requisicion.getFolio_cotizacion());
                    txtFolioRequisicion.setValue(requisicion.getFolio());
                    txtFechaRequerida.setValue(ManageDates.getLocalDateFromDate(requisicion.getFecha_requerida()));
                    txtTiempoEntrega.setValue(requisicion.getTiempo_entrega());
                    txtObservaciones.setValue(requisicion.getObservaciones());
//                        cboMetodoPago.setValue(requisicion.getMetodo_pago());
                    txtDireccionEntrega.setValue(requisicion.getDireccion_entrega());

                    cargarPartidas();

                    btnCotizacionesProvedores.setEnabled(true);
                }
            });
            getUI().addWindow(windows);
        });

        cboMoneda.addSelectionListener((event) -> {
            if(cboMoneda.getValue().equals(_Pago_Documentos.MONEDA_PESOS)){
                txtTipoCambio.setValue("1");
            }
        });
        
        btnCotizacionesProvedores.addClickListener((event) -> {
            WindowVisorDocumentos windows = new WindowVisorDocumentos(
                    requisicion, _DocumentoTipos.COTIZACION);
            windows.center();
            windows.setModal(true);
            getUI().addWindow(windows);
        });

        txtDescuento.addFocusListener((event) -> {
            txtDescuento.setSelection(0, txtDescuento.getValue().length());
        });
                
        txtDescuento.addBlurListener((event) -> {
            calcularTotales();
        });
        
        txtSolicita.setWidth(strWidth);
        txtFechaRequerida.setWidth(strWidth);
        txtFechaEntrega.setWidth(strWidth);
        cboProveedor.setWidth(strWidth);
        
        txtTiempoEntrega.setWidth(strWidth);
        cboCondicionesPago.setWidth(strWidth);
        cboMetodoPago.setWidth(strWidth);
        cboMoneda.setWidth(strWidth);
        txtTipoCambio.setWidth(strWidth);
        
        txtDireccionEntrega.setWidth("580");
        txtObservaciones.setWidth("580");
        txtMontoLetra.setWidth("580");
        
        txtCotizacionProveedor.setWidth("150");
        
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
        cboProveedor.setEmptySelectionAllowed(false);
        try {
            cboProveedor.setSelectedItem(proveedorList.get(0));
        } catch (Exception e) {
            MessageBox.createError()
                    .withCaption("Error!")
                    .withMessage("No existen proveedores dados de alta. ")
                    .withRetryButton()
                    .open();
        }
        
        txtFolioRequisicion.setEnabled(false);
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
        fLay1.addComponents(txtSolicita,txtFechaRequerida, txtTiempoEntrega, cboProveedor);
        fLay1.setSpacing(false);
        
        FormLayout fLay2 = new FormLayout();
        fLay2.addComponents(txtFechaEntrega, cboCondicionesPago, cboMetodoPago, cboMoneda, txtTipoCambio);
        fLay2.setSpacing(false);
        
        FormLayout fLayTotales = new FormLayout();
        fLayTotales.addComponents(txtImporte,txtDescuento, txtSubtotal, txtIVA, txtTotal);
        fLayTotales.setSpacing(false);
        fLayTotales.setWidth("100%");
        
        HorizontalLayout hLay1 = new HorizontalLayout(new Label("Cot Prov:"),txtCotizacionProveedor,btnCotizacionesProvedores,
                new Label("Autorizador:"), cboAutorizador, new Label("Almacen:"), cboAlmacenRecibe,
                btnAgregarPartida,btnModificarPartida,btnEliminarPartida);
        hLay1.setSpacing(true);
        hLay1.setComponentAlignment(hLay1.getComponent(0), Alignment.MIDDLE_CENTER);
        hLay1.setComponentAlignment(hLay1.getComponent(1), Alignment.MIDDLE_CENTER);
        hLay1.setComponentAlignment(hLay1.getComponent(2), Alignment.MIDDLE_CENTER);
        hLay1.setComponentAlignment(hLay1.getComponent(3), Alignment.MIDDLE_CENTER);
        hLay1.setComponentAlignment(hLay1.getComponent(4), Alignment.MIDDLE_CENTER);
        hLay1.setComponentAlignment(hLay1.getComponent(5), Alignment.MIDDLE_CENTER);
        hLay1.setComponentAlignment(hLay1.getComponent(6), Alignment.MIDDLE_CENTER);
        
        FormLayout fLayNotas = new FormLayout(txtMontoLetra,txtDireccionEntrega,txtObservaciones);
        fLayNotas.setSpacing(true);
        fLayNotas.setMargin(false);
        
        HorizontalLayout hLayTotales = new HorizontalLayout(fLayNotas,fLayTotales);
        hLayTotales.setMargin(false);
        hLayTotales.setSpacing(true);

        cont.setSpacing(false);
        cont.addComponents(lblFolio,
                new HorizontalLayout(new Label("Folio Requisicion:"), txtFolioRequisicion, btnBuscarRequisicion) // 1
                    {{
                        setComponentAlignment(getComponent(0), Alignment.MIDDLE_CENTER);
                    }},
                new HorizontalLayout(fLay1, fLay2), // 2|
                hLay1, // 3
                gridOrdenDeCompraDet, // 4
                hLayTotales, // 5
                new HorizontalLayout(btnCancelar, btnGuardar){{setMargin(true);}}); // 6
        
        cont.setComponentAlignment(lblFolio, Alignment.MIDDLE_CENTER);
        cont.setComponentAlignment(cont.getComponent(1), Alignment.MIDDLE_RIGHT);
        cont.setComponentAlignment(cont.getComponent(2), Alignment.MIDDLE_CENTER);
        cont.setComponentAlignment(hLay1, Alignment.MIDDLE_RIGHT);
        cont.setComponentAlignment(cont.getComponent(4), Alignment.MIDDLE_CENTER);
        cont.setComponentAlignment(hLayTotales, Alignment.MIDDLE_RIGHT);
        cont.setComponentAlignment(cont.getComponent(6), Alignment.MIDDLE_CENTER);
        
        setContent(cont);
        setModal(true);
        setSizeFull();
        setResizable(true);
        setClosable(true);
    }
    
    public List getPartidas() {
        String strWhere = " documento_id = " + ordenDeCompra.getId();

        OrdenDeCompraDetDomain service = new OrdenDeCompraDetDomain();
        service.getOrdenDeCompraDet(strWhere, "", " id DESC");
        listOrdenDeCompraDet = service.getObjects();

        if (!service.getOk()) {
            MessageBox.createError()
                    .withCaption("Error al cargar la informacion!")
                    .withMessage("Err: " + service.getNotification())
                    .withRetryButton()
                    .open();
        }
        return listOrdenDeCompraDet;
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
    
    public void cargarPartidas(){
        RequisicionDetDomain requisicionDetService = new RequisicionDetDomain();
        requisicionDetService.getRequisicionDet(" documento_id = " + requisicion.getId(), "", " id ASC");
        listRequisicionDet = requisicionDetService.getObjects();
        
        for (RequisicionDet partTemp : listRequisicionDet) {
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
            partida.setFolio_requisicion(requisicion.getFolio());
            partida.setRequisicion_id(requisicion.getId());
            partida.setNo_parte(partTemp.getNo_parte());
            partida.setNo_serie(partTemp.getNo_serie());
            partida.setModelo(partTemp.getModelo());
            partida.setMarca(partTemp.getMarca());
            partida.setCodigo_interno(partTemp.getCodigo_interno());
            partida.setCodigo_proveedor(partTemp.getCodigo_proveedor());
            
            listOrdenDeCompraDet.add(partida);
        }
        
        gridOrdenDeCompraDet.setItems(listOrdenDeCompraDet);
        
        calcularTotales();
    }

    public void calcularTotales(){
        Double importe = 0.0;
        Double descuento = ManageNumbers.stringToDouble(txtDescuento.getValue());
        Double subtotal = 0.0;
        Double IVA = 0.0;
        Double total = 0.0;
        
        txtDescuento.setValue(descuento.toString());
        
        for (OrdenDeCompraDet ordenDeCompraDet : listOrdenDeCompraDet) {
            ordenDeCompraDet.setImporte(ordenDeCompraDet.getCantidad() * ordenDeCompraDet.getPrecio_unitario());
            importe += ordenDeCompraDet.getImporte();
        }
        
        subtotal = importe - descuento;
        
        if(cboProveedor.getValue().getPais().equals(_Pais.NACIONAL)){
            IVA = subtotal * ((float)_CONTABILIDAD.IVA_NACIONAL/100);
            ordenDeCompra.setPorc_iva(_CONTABILIDAD.IVA_NACIONAL);
        }else{
            IVA = 0.0;
        }
        
        DecimalFormat df = new DecimalFormat("#.00");
        total = subtotal + IVA;

        txtImporte.setValue(importe+"");
        txtDescuento.setValue(descuento+"");
        txtSubtotal.setValue(subtotal+"");
        txtIVA.setValue(df.format(IVA));
        txtTotal.setValue(df.format(total)); 
        
        Numero_Letras importeLetra = new Numero_Letras();
        txtMontoLetra.setValue(
                importeLetra.Convertir(
                        df.format(total), 
                        true, 
                        cboMoneda.getValue().equals(_Pago_Documentos.MONEDA_PESOS)
                )
        );
        
        gridOrdenDeCompraDet.getDataProvider().refreshAll();
    }
    
}