/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rubik.erp.modulo.ventas;

import com.rubik.erp.config.DomainConfig;
import com.rubik.erp.config._CONTABILIDAD;
import com.rubik.erp.config._DocumentoEstados;
import com.rubik.erp.config._DocumentoTipos;
import com.rubik.erp.config._Folios;
import com.rubik.erp.config._Pago_Documentos;
import com.rubik.erp.config._Pais;
import com.rubik.erp.domain.ClienteDomain;
import com.rubik.erp.domain.ConfiguracionDomain;
import com.rubik.erp.domain.CotizacionVentaDetDomain;
import com.rubik.erp.domain.CotizacionVentaDomain;
import com.rubik.erp.domain.ProyectoDomain;
import com.rubik.erp.model.Cliente;
import com.rubik.erp.model.Configuracion;
import com.rubik.erp.model.CotizacionVenta;
import com.rubik.erp.model.CotizacionVentaDet;
import com.rubik.erp.model.Empleado;
import com.rubik.erp.model.Proyecto;
import com.rubik.manage.ManageNumbers;
import com.rubik.manage.ManageString;
import com.rubik.manage.Numero_Letras;
import com.vaadin.data.Binder;
import com.vaadin.data.converter.StringToDoubleConverter;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.rubicone.vaadin.fam3.silk.Fam3SilkIcon;

/**
 *
 * @author Dev
 */
public class WindowVentasCotizaciones extends Window {
    
    Empleado empleado = (Empleado) VaadinSession.getCurrent().getSession().getAttribute("USUARIO_ACTIVO");
    
    VerticalLayout cont = new VerticalLayout();
    String title_window = "Cotizaciones de Venta";

    CotizacionVenta cotizacionDeVenta;

    Boolean isEdit = false;
    
    NativeSelect<Integer> cboVigencia = new NativeSelect("Vigencia(en dias):");
    NativeSelect<Cliente> cboCliente = new NativeSelect("Cliente:");
    NativeSelect<String> cboCondicionesPago = new NativeSelect("Condiciones Pago:");
    NativeSelect<String> cboMetodoPago = new NativeSelect("Metodo Pago:");
    NativeSelect<String> cboMoneda = new NativeSelect("Moneda:");
    TextField txtTipoCambio = new TextField("Tipo de Cambio:");
    TextField txtVendedor = new TextField("Vendedor:");
    TextField txtTiempoEntrega = new TextField("Tiempo de Entrega:");
    TextField txtSolicitante = new TextField("Solicita:");
    TextField txtAtencion = new TextField("Atencion:");
    TextField txtReferanciaCliente = new TextField("Referencia:");
    NativeSelect<Proyecto> cboProyecto = new NativeSelect("Proyecto:");
    TextArea txtCondicionesDeCotizacion = new TextArea("Condiciones de Cotizacion:");
    TextArea txtNotas = new TextArea("Notas:");
    TextArea txtMontoLetra = new TextArea("Monto con Letra:");

    Button btnAgregarPartida = new Button("",Fam3SilkIcon.ADD);
    Button btnModificarPartida = new Button("",Fam3SilkIcon.PENCIL);
    Button btnEliminarPartida = new Button("",Fam3SilkIcon.DELETE);

    TextField txtImporte = new TextField("Importe:");
    TextField txtDescuento = new TextField("Descuento:");
    TextField txtSubtotal = new TextField("Subtotal:");
    TextField txtIVA = new TextField("IVA:");
    TextField txtTotal = new TextField("Total:");
    
    Button btnGuardar = new Button("Guardar", Fam3SilkIcon.DISK);
    Button btnCancelar = new Button("Cancelar", Fam3SilkIcon.CANCEL);
    
    Grid<CotizacionVentaDet> gridCotizacionDeVentaDet = new Grid<>();
    List<CotizacionVentaDet> listCotizacionDeVentaDet = new ArrayList<>();
    
    List<Cliente> clienteList = new ArrayList<>();
    List<Proyecto> proyectoList = new ArrayList<>();
    
    Label lblFolio;
    String folio = "";

    public WindowVentasCotizaciones() {
        lblFolio = new Label("Cotizacion de Venta " + getFolio()) {
            {
                setStyleName("h2");
            }
        };
        
        cotizacionDeVenta = new CotizacionVenta();
        
        initComponents();
        txtTipoCambio.setValue("1");
    }

    public WindowVentasCotizaciones(CotizacionVenta cotiTemp) {
        isEdit = true;
        cotizacionDeVenta = cotiTemp;
        lblFolio = new Label("Cotizacion de Venta " + cotizacionDeVenta.getFolio()) {
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
        
        Binder<CotizacionVenta> binder = new Binder<>();
        binder.forField(cboVigencia).bind(CotizacionVenta::getDias_caduca, CotizacionVenta::setDias_caduca);
        binder.forField(cboCondicionesPago).bind(CotizacionVenta::getCondiciones_pago, CotizacionVenta::setCondiciones_pago);
        binder.forField(cboMetodoPago).bind(CotizacionVenta::getMetodo_pago, CotizacionVenta::setMetodo_pago);
        binder.forField(cboMoneda).bind(CotizacionVenta::getMoneda, CotizacionVenta::setMoneda);
        binder.forField(txtTipoCambio).withConverter(new StringToDoubleConverter(0.0, "El valor debe ser numerico")).bind(CotizacionVenta::getTipo_cambio, CotizacionVenta::setTipo_cambio);
        binder.forField(txtVendedor).bind(CotizacionVenta::getVendedor, CotizacionVenta::setVendedor);
        binder.forField(txtTiempoEntrega).bind(CotizacionVenta::getTiempo_tentrega, CotizacionVenta::setTiempo_tentrega);
        
        binder.forField(txtSolicitante).bind(CotizacionVenta::getSolicitante, CotizacionVenta::setSolicitante);
        binder.forField(txtAtencion).bind(CotizacionVenta::getAtencion, CotizacionVenta::setAtencion);
        binder.forField(txtReferanciaCliente).bind(CotizacionVenta::getReferencia_cliente, CotizacionVenta::setReferencia_cliente);
        
        binder.forField(txtNotas).bind(CotizacionVenta::getObservaciones, CotizacionVenta::setObservaciones);
        binder.forField(txtCondicionesDeCotizacion).bind(CotizacionVenta::getCondiciones_cotizacion, CotizacionVenta::setCondiciones_cotizacion);
        binder.forField(txtMontoLetra).bind(CotizacionVenta::getImporte_letra, CotizacionVenta::setImporte_letra);

        binder.forField(txtImporte).withConverter(new StringToDoubleConverter(0.0, "El valor debe ser numerico")).bind(CotizacionVenta::getImporte, CotizacionVenta::setImporte);
        binder.forField(txtDescuento).withConverter(new StringToDoubleConverter(0.0, "El valor debe ser numerico")).bind(CotizacionVenta::getDescuento, CotizacionVenta::setDescuento);
        binder.forField(txtSubtotal).withConverter(new StringToDoubleConverter(0.0, "El valor debe ser numerico")).bind(CotizacionVenta::getSubtotal, CotizacionVenta::setSubtotal);
        binder.forField(txtIVA).withConverter(new StringToDoubleConverter(0.0, "El valor debe ser numerico")).bind(CotizacionVenta::getIva, CotizacionVenta::setIva);
        binder.forField(txtTotal).withConverter(new StringToDoubleConverter(0.0, "El valor debe ser numerico")).bind(CotizacionVenta::getTotal, CotizacionVenta::setTotal);
        
        gridCotizacionDeVentaDet.setHeight("450");
        gridCotizacionDeVentaDet.setSelectionMode(Grid.SelectionMode.SINGLE);
        
        gridCotizacionDeVentaDet.addItemClickListener(event -> {
            if (event.getMouseEventDetails().isDoubleClick()) {
                if (gridCotizacionDeVentaDet.getSelectedItems().size() == 1) {
                    WindowVentasCotizacionesDet windows = new WindowVentasCotizacionesDet(cotizacionDeVenta, gridCotizacionDeVentaDet.getSelectedItems().iterator().next());
                    windows.center();
                    windows.setModal(true);
                    windows.addCloseListener((e) -> {
                        Boolean ok = (Boolean) VaadinSession.getCurrent().getSession().getAttribute("PARTIDA_COT_VENTA_OK");
                        if (ok) {
                            if (isEdit) {
                                gridCotizacionDeVentaDet.setItems(getPartidas());
                            } else {
                                gridCotizacionDeVentaDet.setItems(listCotizacionDeVentaDet);
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

        gridCotizacionDeVentaDet.addColumn(CotizacionVentaDet::getCantidad).setCaption("CTD").setWidth(75);
        gridCotizacionDeVentaDet.addColumn(CotizacionVentaDet::getDescripcion).setCaption("DESCRIPCION");
        gridCotizacionDeVentaDet.addColumn(CotizacionVentaDet::getPrecio_unitario).setCaption("P.U.").setWidth(120);
        gridCotizacionDeVentaDet.addColumn(CotizacionVentaDet::getImporte).setCaption("IMPORTE").setWidth(120);


        btnAgregarPartida.addClickListener((event) -> {
            WindowVentasCotizacionesDet windows = new WindowVentasCotizacionesDet(cotizacionDeVenta);
            windows.center();
            windows.setModal(true);
            windows.addCloseListener((e) -> {
                Boolean ok = (Boolean) VaadinSession.getCurrent().getSession().getAttribute("PARTIDA_COT_VENTA_OK");
                if (ok) {
                    if (isEdit) {
                        gridCotizacionDeVentaDet.setItems(getPartidas());
                    } else {
                        CotizacionVentaDet partida = (CotizacionVentaDet) VaadinSession.getCurrent().getSession().getAttribute("PARTIDA_COT_VENTA");
                        listCotizacionDeVentaDet.add(partida);
                        gridCotizacionDeVentaDet.setItems(listCotizacionDeVentaDet);
                    }
                }
                calcularTotales();
            });
            getUI().addWindow(windows);
        });
        
        btnModificarPartida.addClickListener((event) -> {
            CotizacionVentaDet partidaSeleccionada = gridCotizacionDeVentaDet.getSelectedItems().iterator().next();
            
            if (partidaSeleccionada != null) {
                WindowVentasCotizacionesDet windows = new WindowVentasCotizacionesDet(cotizacionDeVenta,partidaSeleccionada);
                windows.center();
                windows.setModal(true);
                windows.addCloseListener((e) -> {
                    Boolean ok = (Boolean) VaadinSession.getCurrent().getSession().getAttribute("PARTIDA_COT_VENTA_OK");
                    if (ok) {
                        if (isEdit) {
                            gridCotizacionDeVentaDet.setItems(getPartidas());
                        } else {
                            gridCotizacionDeVentaDet.setItems(listCotizacionDeVentaDet);
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
            if (gridCotizacionDeVentaDet.getSelectedItems().size() == 1) {
                MessageBox.createQuestion()
                        .withCaption("Atencion!")
                        .withMessage("Desea eliminar la partida seleccionada?.")
                        .withOkButton(() -> {

                            CotizacionVentaDet partida = gridCotizacionDeVentaDet.getSelectedItems().iterator().next();

                            if (isEdit) {
                                CotizacionVentaDetDomain dom = new CotizacionVentaDetDomain();
                                dom.CotizacionVentaDetDelete(partida);
                                gridCotizacionDeVentaDet.setItems(getPartidas());
                            } else {
                                listCotizacionDeVentaDet.remove(partida);
                                gridCotizacionDeVentaDet.setItems(listCotizacionDeVentaDet);
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
                Double total = 0.0;

                binder.writeBean(cotizacionDeVenta);
                toUpperCase();

                cotizacionDeVenta.setUsuario_id(empleado.getId());
                cotizacionDeVenta.setUsuario(empleado.getNombre_completo());
                cotizacionDeVenta.setEstado_doc(_DocumentoEstados.EN_PROCESO);
                cotizacionDeVenta.setTipo_documento(_DocumentoTipos.COTIZACION_DE_VENTA);
                cotizacionDeVenta.setTipo_archivo("PDF");
                cotizacionDeVenta.setActivo(true);
                cotizacionDeVenta.setCliente_id(cboCliente.getValue().getId());
                cotizacionDeVenta.setCliente(cboCliente.getValue().getRazon_social());
                cotizacionDeVenta.setCliente_rfc(cboCliente.getValue().getRfc());
                
                cotizacionDeVenta.setEmisor_nombre(DomainConfig.EMPRESA.getRazon_social());
                cotizacionDeVenta.setEmisor_rfc(DomainConfig.EMPRESA.getRfc());
                cotizacionDeVenta.setEmisor_calle(DomainConfig.EMPRESA.getDireccion());
                cotizacionDeVenta.setEmisor_municipio(DomainConfig.EMPRESA.getCiudad());
                cotizacionDeVenta.setEmisor_estado(DomainConfig.EMPRESA.getEstado());
                cotizacionDeVenta.setEmisor_pais(DomainConfig.EMPRESA.getPais());
                cotizacionDeVenta.setEmisor_codigo_postal(DomainConfig.EMPRESA.getCp());
                
                cotizacionDeVenta.setReceptor_nombre(cboCliente.getValue().getRazon_social());
                cotizacionDeVenta.setReceptor_rfc(cboCliente.getValue().getRfc());
                cotizacionDeVenta.setReceptor_calle(cboCliente.getValue().getDomicilio());
                cotizacionDeVenta.setReceptor_municipio(cboCliente.getValue().getCiudad());
                cotizacionDeVenta.setReceptor_estado(cboCliente.getValue().getEstado());
                cotizacionDeVenta.setReceptor_pais(cboCliente.getValue().getPais());
                cotizacionDeVenta.setReceptor_codigo_postal(cboCliente.getValue().getCp());
                cotizacionDeVenta.setTerminada_no_requisicion(false);
                cotizacionDeVenta.setTerminada_requisicion(false);
                
                cotizacionDeVenta.setProyecto_id(cboProyecto.getValue().getId());
                cotizacionDeVenta.setProyecto(cboProyecto.getValue().getNombre());
                
                CotizacionVentaDomain service = new CotizacionVentaDomain();

                if (isEdit) {
                    cotizacionDeVenta.setFecha_modificacion(new Date());
                    service.CotizacionVentaUpdate(cotizacionDeVenta);
                    
                } else {
                    cotizacionDeVenta.setFolio(getFolio());
                    cotizacionDeVenta.setSerie("");

                    service.CotizacionVentaInsert(cotizacionDeVenta);
                    updateFolio();

                    CotizacionVentaDetDomain domainDet = new CotizacionVentaDetDomain();
                    
                    for (CotizacionVentaDet partidaTemp : listCotizacionDeVentaDet) { //Guarda la partida con el ID de la cotizacionDeVenta
                        partidaTemp.setFolio(cotizacionDeVenta.getFolio());
                        partidaTemp.setDocumento_id(cotizacionDeVenta.getId());
                        partidaTemp.setEntregada(false);
                        partidaTemp.setFacturada(false);
                        partidaTemp.setRequisicion_id(0);
                        domainDet.CotizacionVentaDetInsert(partidaTemp);
                    }
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
        
        cboMoneda.addSelectionListener((event) -> {
            if(cboMoneda.getValue().equals(_Pago_Documentos.MONEDA_PESOS)){
                txtTipoCambio.setValue("1");
            }
        });

        txtDescuento.addFocusListener((event) -> {
            txtDescuento.setSelection(0, txtDescuento.getValue().length());
        });
                
        txtDescuento.addBlurListener((event) -> {
            calcularTotales();
        });
        
        txtVendedor.setEnabled(false);
        txtVendedor.setValue(empleado.getNombre_completo());
        
        txtNotas.setRows(3);
        txtCondicionesDeCotizacion.setRows(3);
        txtMontoLetra.setRows(3);
        
        cboCondicionesPago.setItems(_Pago_Documentos.CONDICIONES_PAGO);
        cboCondicionesPago.setValue(_Pago_Documentos.CONDICIONES_PAGO.get(0));
        cboCondicionesPago.setEmptySelectionAllowed(false);
        
        cboVigencia.setItems(7,15,30,45);
        cboVigencia.setValue(7);
        cboVigencia.setEmptySelectionAllowed(false);

        cboMetodoPago.setItems(_Pago_Documentos.METODOS_PAGO);
        cboMetodoPago.setValue(_Pago_Documentos.METODOS_PAGO.get(0));
        cboMetodoPago.setEmptySelectionAllowed(false);
        
        cboMoneda.setItems(_Pago_Documentos.MONEDAS);
        cboMoneda.setValue(_Pago_Documentos.MONEDAS.get(0));
        cboMoneda.setEmptySelectionAllowed(false);

        gridCotizacionDeVentaDet.setWidth("100%");
        
        txtVendedor.setWidth(strWidth);
        cboCliente.setWidth(strWidth);
        cboProyecto.setWidth(strWidth);
        
        txtSolicitante.setWidth(strWidth);
        txtAtencion.setWidth(strWidth);
        txtReferanciaCliente.setWidth(strWidth);
        txtTiempoEntrega.setWidth(strWidth);
        cboCondicionesPago.setWidth(strWidth);
        cboMetodoPago.setWidth(strWidth);
        cboMoneda.setWidth(strWidth);
        txtTipoCambio.setWidth(strWidth);
        cboVigencia.setWidth(strWidth);
        
        txtCondicionesDeCotizacion.setWidth("580");
        txtNotas.setWidth("580");
        txtMontoLetra.setWidth("580");
        
        txtImporte.setWidth("160");
        txtDescuento.setWidth("160");
        txtSubtotal.setWidth("160");
        txtIVA.setWidth("160");
        txtTotal.setWidth("160");

        cboCliente.setItems(getClientes());
        cboCliente.setSelectedItem(clienteList.get(0));
        cboCliente.setEmptySelectionAllowed(false);
        
        cboCliente.addValueChangeListener((event) -> {
            if(cboCliente.getValue().getPais().equals(_Pais.NACIONAL)){
                cboMoneda.setValue(_Pago_Documentos.MONEDA_PESOS);
            } else {
                cboMoneda.setValue(_Pago_Documentos.MONEDA_DOLARES);
            }
        });
        
        cboProyecto.setItems(getProyectos());
        cboProyecto.setSelectedItem(proyectoList.get(0));
        cboProyecto.setEmptySelectionAllowed(false);
        
        txtImporte.setEnabled(false);
        txtSubtotal.setEnabled(false);
        txtIVA.setEnabled(false);
        txtTotal.setEnabled(false);
        
        if (isEdit) {
            binder.readBean(cotizacionDeVenta);
            
            gridCotizacionDeVentaDet.setItems(getPartidas());
            
            for (Cliente cte : clienteList) {
                if (cotizacionDeVenta.getCliente_id().equals(cte.getId())) {
                    cboCliente.setValue(cte);
                    break;
                }
            }
            
            for (Proyecto proy : proyectoList) {
                if (cotizacionDeVenta.getProyecto_id().equals(proy.getId())) {
                    cboProyecto.setValue(proy);
                    break;
                }
            }
        }
        
        // ACOMODO =============================================================
        FormLayout fLay1 = new FormLayout();
        fLay1.addComponents(cboVigencia,txtVendedor,txtTiempoEntrega, cboCliente, txtSolicitante, txtAtencion);
        fLay1.setSpacing(false);
        
        FormLayout fLay2 = new FormLayout();
        fLay2.addComponents(cboCondicionesPago, cboMetodoPago, cboMoneda, txtTipoCambio, txtReferanciaCliente, cboProyecto);
        fLay2.setSpacing(false);
        
        FormLayout fLayTotales = new FormLayout();
        fLayTotales.addComponents(txtImporte,txtDescuento, txtSubtotal, txtIVA, txtTotal);
        fLayTotales.setSpacing(false);
        fLayTotales.setWidth("100%");
        
        HorizontalLayout hLay1 = new HorizontalLayout(btnAgregarPartida,btnModificarPartida,btnEliminarPartida);
        hLay1.setSpacing(true);
        hLay1.setComponentAlignment(hLay1.getComponent(0), Alignment.MIDDLE_CENTER);
        hLay1.setComponentAlignment(hLay1.getComponent(1), Alignment.MIDDLE_CENTER);
        hLay1.setComponentAlignment(hLay1.getComponent(2), Alignment.MIDDLE_CENTER);
        
        FormLayout fLayNotas = new FormLayout(txtCondicionesDeCotizacion,txtNotas,txtMontoLetra);
        fLayNotas.setSpacing(true);
        fLayNotas.setMargin(false);
        
        HorizontalLayout hLayTotales = new HorizontalLayout(fLayNotas,fLayTotales);
        hLayTotales.setMargin(false);
        hLayTotales.setSpacing(true);

        cont.setSpacing(false);
        cont.addComponents(lblFolio,
                new HorizontalLayout(fLay1, fLay2), // 2|
                hLay1, // 2
                gridCotizacionDeVentaDet, // 3
                hLayTotales, // 4
                new HorizontalLayout(btnCancelar, btnGuardar){{setMargin(true);}}); // 5
        
        cont.setComponentAlignment(lblFolio, Alignment.MIDDLE_CENTER);
        cont.setComponentAlignment(cont.getComponent(1), Alignment.MIDDLE_CENTER);
        cont.setComponentAlignment(cont.getComponent(2), Alignment.MIDDLE_CENTER);
        cont.setComponentAlignment(cont.getComponent(3), Alignment.MIDDLE_RIGHT);
        cont.setComponentAlignment(cont.getComponent(4), Alignment.MIDDLE_RIGHT);
        cont.setComponentAlignment(cont.getComponent(5), Alignment.MIDDLE_CENTER);
        
        setContent(cont);
        setModal(true);
        setSizeFull();
        setResizable(true);
        setClosable(true);
    }
    
    public List getPartidas() {
        String strWhere = " documento_id = " + cotizacionDeVenta.getId();

        CotizacionVentaDetDomain service = new CotizacionVentaDetDomain();
        service.getCotizacionVentaDet(strWhere, "", " id DESC");
        listCotizacionDeVentaDet = service.getObjects();

        if (!service.getOk()) {
            MessageBox.createError()
                    .withCaption("Error al cargar la informacion!")
                    .withMessage("Err: " + service.getNotification())
                    .withRetryButton()
                    .open();
        }
        return listCotizacionDeVentaDet;
    }
    
    public void toUpperCase() {
        cotizacionDeVenta.setCondiciones_cotizacion(txtCondicionesDeCotizacion.getValue().toUpperCase());
        cotizacionDeVenta.setObservaciones(txtNotas.getValue().toUpperCase());
        cotizacionDeVenta.setTiempo_tentrega(txtTiempoEntrega.getValue().toUpperCase());
        cotizacionDeVenta.setSolicitante(txtSolicitante.getValue().toUpperCase());
        cotizacionDeVenta.setAtencion(txtAtencion.getValue().toUpperCase());
        cotizacionDeVenta.setReferencia_cliente(txtReferanciaCliente.getValue().toUpperCase());
    }
    
    public String getFolio() {
        ConfiguracionDomain domain = new ConfiguracionDomain();
        domain.getOneFolioConfiguracion(_Folios.FOLIO_COTIZACION, _Folios.SERIE_COTIZACION);
        Configuracion conf = domain.getObject();
        
        folio = conf.getSerie() + ManageString.fillWithZero(conf.getFolio(), 5);
        
        return folio;
    }
    
    public void updateFolio() {
        ConfiguracionDomain domain = new ConfiguracionDomain();
        domain.ConfiguracionFolioUpdate(_Folios.FOLIO_COTIZACION);
    }
    
    public List<Cliente> getClientes() {
        ClienteDomain cliDomain = new ClienteDomain();
        cliDomain.getCliente("", "", "razon_social ASC");

        clienteList = cliDomain.getObjects();
        return clienteList;
    }
    
    public List<Proyecto> getProyectos() {
        ProyectoDomain cliDomain = new ProyectoDomain();
        cliDomain.getProyecto("activo = 1", "", "id ASC");

        proyectoList = cliDomain.getObjects();
        return proyectoList;
    }
    
    public void cargarPartidas(){
        CotizacionVentaDetDomain requisicionDetService = new CotizacionVentaDetDomain();
        requisicionDetService.getCotizacionVentaDet(" documento_id = " + cotizacionDeVenta.getId(), "", "");
        listCotizacionDeVentaDet = requisicionDetService.getObjects();
        
        for (CotizacionVentaDet partTemp : listCotizacionDeVentaDet) {
            int i = 0;
            
            CotizacionVentaDet partida = new CotizacionVentaDet();
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
            partida.setNo_parte(partTemp.getNo_parte());
            partida.setNo_serie(partTemp.getNo_serie());
            partida.setModelo(partTemp.getModelo());
            partida.setMarca(partTemp.getMarca());
            partida.setCodigo_interno(partTemp.getCodigo_interno());
            partida.setCodigo_proveedor(partTemp.getCodigo_proveedor());
            
            listCotizacionDeVentaDet.add(partida);
        }
        
        gridCotizacionDeVentaDet.setItems(listCotizacionDeVentaDet);
        
        calcularTotales();
    }

    public void calcularTotales(){
        Double importe = 0.0;
        Double descuento = ManageNumbers.stringToDouble(txtDescuento.getValue());
        Double subtotal = 0.0;
        Double IVA = 0.0;
        Double total = 0.0;
        
        txtDescuento.setValue(descuento.toString());
        
        for (CotizacionVentaDet ordenDeCompraDet : listCotizacionDeVentaDet) {
            ordenDeCompraDet.setImporte(ordenDeCompraDet.getCantidad() * ordenDeCompraDet.getPrecio_unitario());
            importe += ordenDeCompraDet.getImporte();
        }
        
        subtotal = importe - descuento;
        
        if(cboCliente.getValue().getPais().equals(_Pais.NACIONAL)){
            IVA = subtotal * ((float)_CONTABILIDAD.IVA_NACIONAL/100);
            cotizacionDeVenta.setPorc_iva(_CONTABILIDAD.IVA_NACIONAL);
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
        txtMontoLetra.setValue(importeLetra.Convertir(total.toString(), true, 
                cboMoneda.getValue().equals(_Pago_Documentos.MONEDA_PESOS)?true:false));
        
        gridCotizacionDeVentaDet.getDataProvider().refreshAll();
    }
    
}