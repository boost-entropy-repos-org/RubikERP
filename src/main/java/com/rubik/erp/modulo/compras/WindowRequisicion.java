/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rubik.erp.modulo.compras;

import com.rubik.erp.config._DocumentoEstados;
import com.rubik.erp.config._DocumentoTipos;
import com.rubik.erp.config._Folios;
import com.rubik.erp.domain.ConfiguracionDomain;
import com.rubik.erp.domain.EmpleadoDomain;
import com.rubik.erp.domain.ProveedorDomain;
import com.rubik.erp.domain.RequisicionDetDomain;
import com.rubik.erp.domain.RequisicionDomain;
import com.rubik.erp.model.Configuracion;
import com.rubik.erp.model.Empleado;
import com.rubik.erp.model.Proveedor;
import com.rubik.erp.model.Requisicion;
import com.rubik.erp.model.RequisicionDet;
import com.rubik.erp.modulo.generic.WindowVisorDocumentos;
import com.rubik.manage.ManageDates;
import com.rubik.manage.ManageString;
import com.vaadin.data.Binder;
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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.rubicone.vaadin.fam3.silk.Fam3SilkIcon;

/**
 *
 * @author Dev
 */
public class WindowRequisicion extends Window {
    
    Empleado empleado = (Empleado) VaadinSession.getCurrent().getSession().getAttribute("USUARIO_ACTIVO");
    
    VerticalLayout cont = new VerticalLayout();
    String title_window = "Requisicion de Compra";

    Requisicion requisicion;
    Boolean isEdit = false;

    DateField txtFechaRequerida = new DateField("Fecha Requerida:", LocalDate.now());
    TextField txtSolicita = new TextField("Solicitante:");
    TextArea txtObservaciones = new TextArea("Observaciones:");
    TextArea txtDireccionEntrega = new TextArea("Descripcion Entrega:");
    NativeSelect<Empleado> cboAutorizador = new NativeSelect("Autorizador:");
    NativeSelect<String> cboPrioridad = new NativeSelect("Prioridad:");
    NativeSelect<Proveedor> cboProveedor = new NativeSelect("Proveedor sugerido:");

    Button btnAgregarPartida = new Button("Agregar",Fam3SilkIcon.ADD);
    Button btnModificarPartida = new Button("Modificar",Fam3SilkIcon.PENCIL);
    Button btnEliminarPartida = new Button("Eliminar",Fam3SilkIcon.DELETE);
    Button btnExpediente = new Button("", Fam3SilkIcon.FOLDER);
    Button btnGuardar = new Button("Guardar", Fam3SilkIcon.DISK);
    Button btnCancelar = new Button("Cancelar", Fam3SilkIcon.CANCEL);
    
    Grid<RequisicionDet> gridRequisicionDet = new Grid<>();
    List<RequisicionDet> listRequisicionDet = new ArrayList<>();
    
    List<Empleado> listAutorizadoresCompras = new ArrayList<>();
    List<Proveedor> proveedorList = new ArrayList<>();
    
    Label lblFolio;
    String folio = "";

    public WindowRequisicion() {
        lblFolio = new Label("REQUISICION " + getFolio()) {
            {
                setStyleName("h2");
            }
        };
        initComponents();
        btnModificarPartida.setEnabled(false);
        btnExpediente.setEnabled(false);
    }

    public WindowRequisicion(Requisicion requisicionTemp) {
        isEdit = true;
        requisicion = requisicionTemp;
        lblFolio = new Label("REQUISICION " + requisicionTemp.getFolio()) {
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

        Binder<Requisicion> binder = new Binder<>();
        binder.forField(txtSolicita).bind(Requisicion::getSolicita, Requisicion::setSolicita);
        binder.forField(txtObservaciones).bind(Requisicion::getObservaciones, Requisicion::setObservaciones);
        binder.forField(txtDireccionEntrega).bind(Requisicion::getDireccion_entrega, Requisicion::setDireccion_entrega);
        binder.forField(cboPrioridad).bind(Requisicion::getPrioridad, Requisicion::setPrioridad);
        
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
        
        gridRequisicionDet.setHeight("272");
        gridRequisicionDet.setSelectionMode(Grid.SelectionMode.SINGLE);

        gridRequisicionDet.addColumn(RequisicionDet::getCantidad).setCaption("CTD").setWidth(75);
        gridRequisicionDet.addColumn(RequisicionDet::getDescripcion).setCaption("DESCRIPCION");
//        gridRequisicionDet.addColumn(RequisicionDet::getPrecio_unitario).setCaption("P.U.").setWidth(100);
//        gridRequisicionDet.addColumn(RequisicionDet::getTotal).setCaption("TOTAL").setWidth(100);

        btnAgregarPartida.addClickListener((event) -> {
            WindowRequisicionDet windows = new WindowRequisicionDet(requisicion);
            windows.center();
            windows.setModal(true);
            windows.addCloseListener((e) -> {
                Boolean ok = (Boolean) VaadinSession.getCurrent().getSession().getAttribute("PARTIDA_OK");
                if (ok) {
                    if (isEdit) {
                        gridRequisicionDet.setItems(getPartidas());
                    } else {
                        RequisicionDet partida = (RequisicionDet) VaadinSession.getCurrent().getSession().getAttribute("REMISION_DET");
                        listRequisicionDet.add(partida);
                        gridRequisicionDet.setItems(listRequisicionDet);
                    }
                }
            });
            getUI().addWindow(windows);
        });
        
        btnExpediente.addClickListener((event) -> {
            WindowVisorDocumentos windows = new WindowVisorDocumentos(requisicion, _DocumentoTipos.COTIZACION_DE_COMPRA);
            windows.center();
            windows.setModal(true);
            getUI().addWindow(windows);
        });
        
        btnModificarPartida.addClickListener((event) -> {
            if(gridRequisicionDet.getSelectedItems().size() == 1){
                WindowRequisicionDet windows = new WindowRequisicionDet(requisicion,gridRequisicionDet.getSelectedItems().iterator().next());
                windows.center();
                windows.setModal(true);
                windows.addCloseListener((e) -> {
                    Boolean ok = (Boolean) VaadinSession.getCurrent().getSession().getAttribute("PARTIDA_OK");
                    if (ok) {
                        if (isEdit) {
                            gridRequisicionDet.setItems(getPartidas());
                        } else {
                            RequisicionDet partida = (RequisicionDet) VaadinSession.getCurrent().getSession().getAttribute("REMISION_DET");
                            listRequisicionDet.add(partida);
                            gridRequisicionDet.setItems(listRequisicionDet);
                        }
                    }
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
            if (gridRequisicionDet.getSelectedItems().size() == 1) {
                MessageBox.createQuestion()
                        .withCaption("Atencion!")
                        .withMessage("Desea eliminar la partida seleccionada?.")
                        .withOkButton(() -> {

                            RequisicionDet partida = gridRequisicionDet.getSelectedItems().iterator().next();

                            if (isEdit) {
                                RequisicionDetDomain dom = new RequisicionDetDomain();
                                dom.RequisicionDetDelete(partida);
                                gridRequisicionDet.setItems(getPartidas());
                            } else {
                                listRequisicionDet.remove(partida);
                                gridRequisicionDet.setItems(listRequisicionDet);
                            }

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
                
                if(!isEdit){
                    requisicion = new Requisicion();
                }

                binder.writeBean(requisicion);
                toUpperCase();
                
                Proveedor prov1 = cboProveedor.getValue();
                
                if(prov1 != null){
                    requisicion.setProveedor(prov1.getRazon_social());
                    requisicion.setProveedor_id(prov1.getId());
                }else{
                    requisicion.setProveedor("");
                    requisicion.setProveedor_id(0);
                }
                
                requisicion.setUsuario_id(empleado.getId());
                requisicion.setUsuario(empleado.getNombre_completo());
                requisicion.setEstado_doc(_DocumentoEstados.EN_PROCESO);
                requisicion.setTipo_documento(_DocumentoTipos.REMISION_DE_ENTREGA);
                requisicion.setTipo_archivo("PDF");
                requisicion.setFecha_requerida(ManageDates.getDateFromLocalDate(txtFechaRequerida.getValue()));
                requisicion.setAutoriza(autoriza.getNombre_completo());
                requisicion.setAutoriza_id(autoriza.getId());
                requisicion.setActivo(true);
                
                RequisicionDomain service = new RequisicionDomain();

                if (isEdit) {
                    requisicion.setFecha_modificacion(new Date());
                    for (RequisicionDet partidaTemp : listRequisicionDet) {
                        total += partidaTemp.getTotal();
                    }
                    requisicion.setTotal(total);
                    
                    service.RequisicionUpdate(requisicion);
                    
                } else {
                    requisicion.setFolio(getFolio());
                    requisicion.setSerie("");
                    
                    RequisicionDetDomain domainDet = new RequisicionDetDomain();
                    
//                    for (RequisicionDet partidaTemp : listRequisicionDet) { // Obtiene el total
//                        total += partidaTemp.getTotal();
//                    }
//                    requisicion.setTotal(total);
                    service.RequisicionInsert(requisicion);
                    updateFolio();
                    
                    for (RequisicionDet partidaTemp : listRequisicionDet) { //Guarda la partida con el ID de la requisicion
                        partidaTemp.setFolio(requisicion.getFolio());
                        partidaTemp.setDocumento_id(requisicion.getId());
                        domainDet.RequisicionDetInsert(partidaTemp);
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
                ex.printStackTrace();
                MessageBox.createError()
                        .withCaption("Error!")
                        .withMessage("Verifique que la informacion este completa o sea correcta. ")
                        .withRetryButton()
                        .open();
            }
        });
        
        txtSolicita.setEnabled(false);
        txtSolicita.setValue(empleado.getNombre_completo());
        txtObservaciones.setRows(2);
        txtDireccionEntrega.setRows(2);
        
        cboPrioridad.setEmptySelectionAllowed(false);
        cboPrioridad.setItems("ALTA", "MEDIA", "BAJA");
        cboPrioridad.setValue("ALTA");
       
        txtFechaRequerida.setWidth(strWidth);
        txtSolicita.setWidth(strWidth);
        txtObservaciones.setWidth(strWidth);
        cboPrioridad.setWidth(strWidth);
        cboAutorizador.setWidth(strWidth);
        cboProveedor.setWidth(strWidth);
        txtDireccionEntrega.setWidth(strWidth);
        
        cboAutorizador.setItems(getAutorizadorCompras());
        cboAutorizador.setSelectedItem(listAutorizadoresCompras.get(0));
        cboAutorizador.setEmptySelectionAllowed(false);

        if (isEdit) {
            binder.readBean(requisicion);
            
            for (Proveedor prov : proveedorList) {
                if (requisicion.getProveedor_id().equals(prov.getId())) {
                    cboProveedor.setValue(prov);
                }
            }
            
            gridRequisicionDet.setItems(getPartidas());
            
            for (Empleado autorizador : listAutorizadoresCompras) {
                if (requisicion.getAutoriza_id().equals(autorizador.getId())) {
                    cboAutorizador.setValue(autorizador);
                }
            }
        }
        
        FormLayout fLay = new FormLayout();
        fLay.setSpacing(false);
        fLay.addComponents(txtFechaRequerida,txtSolicita, cboPrioridad, cboAutorizador, txtObservaciones, 
                txtDireccionEntrega,cboProveedor);

        cont.setSpacing(false);
        cont.addComponents(lblFolio,
                new HorizontalLayout(fLay, 
                        new VerticalLayout(
                                new HorizontalLayout(btnExpediente,
                                        btnAgregarPartida,btnModificarPartida,btnEliminarPartida),
                                gridRequisicionDet){{setComponentAlignment(getComponent(0), Alignment.MIDDLE_CENTER);}}
                ){{setSpacing(false);}}, new HorizontalLayout(btnCancelar, btnGuardar));
        cont.setComponentAlignment(cont.getComponent(0), Alignment.MIDDLE_CENTER);
        cont.setComponentAlignment(cont.getComponent(1), Alignment.MIDDLE_CENTER);
        cont.setComponentAlignment(cont.getComponent(2), Alignment.MIDDLE_CENTER);
        
        setContent(cont);
        setModal(true);
        setResizable(false);
        setClosable(false);
    }
    
    public List getPartidas() {
        String strWhere = " documento_id = " + requisicion.getId();

        RequisicionDetDomain service = new RequisicionDetDomain();
        service.getRequisicionDet(strWhere, "", " id DESC");
        listRequisicionDet = service.getObjects();

        if (!service.getOk()) {
            MessageBox.createError()
                    .withCaption("Error al cargar la informacion!")
                    .withMessage("Err: " + service.getNotification())
                    .withRetryButton()
                    .open();
        }
        return listRequisicionDet;
    }
    
    public void toUpperCase() {
        requisicion.setSolicita(txtSolicita.getValue().toUpperCase());
        requisicion.setObservaciones(txtObservaciones.getValue().toUpperCase());
        requisicion.setDireccion_entrega(txtDireccionEntrega.getValue().toUpperCase());
    }
    
    public List<Empleado> getAutorizadorCompras() {
        EmpleadoDomain provService = new EmpleadoDomain();
        provService.getEmpleado(" autorizador = 1 ", "", " nombre ASC");
        
        listAutorizadoresCompras = provService.getObjects();
        return listAutorizadoresCompras;
    }
    
    public String getFolio() {
        ConfiguracionDomain domain = new ConfiguracionDomain();
        domain.getOneFolioConfiguracion(_Folios.FOLIO_REMISION, _Folios.SERIE_REMISION);
        Configuracion conf = domain.getObject();
        
        folio = conf.getSerie() + ManageString.fillWithZero(conf.getFolio(), 5);
        
        return folio;
    }
    
    public void updateFolio() {
        ConfiguracionDomain domain = new ConfiguracionDomain();
        domain.ConfiguracionFolioUpdate(_Folios.FOLIO_REMISION);
    }
    
    public List<Proveedor> getProveedor() {
        ProveedorDomain provService = new ProveedorDomain();
        provService.getProveedor("", "", "razon_social ASC");
        proveedorList = provService.getObjects();
        return proveedorList;
    }
    
}