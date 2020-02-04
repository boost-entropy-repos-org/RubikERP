/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rubik.erp.modulo.compras;

import com.rubik.erp.config._Folios;
import com.rubik.erp.domain.ConfiguracionDomain;
import com.rubik.erp.domain.EmpleadoDomain;
import com.rubik.erp.domain.RemisionDetDomain;
import com.rubik.erp.domain.RemisionDomain;
import com.rubik.erp.model.Configuracion;
import com.rubik.erp.model.Empleado;
import com.rubik.erp.model.Remision;
import com.rubik.erp.model.RemisionDet;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.rubicone.vaadin.fam3.silk.Fam3SilkIcon;

/**
 *
 * @author Dev
 */
public class WindowRemision extends Window {
    
    Empleado empleado = (Empleado) VaadinSession.getCurrent().getSession().getAttribute("USUARIO_ACTIVO");
    
    VerticalLayout cont = new VerticalLayout();
    String title_window = "Remisiones de Compra";

    Remision remision;
    Boolean isEdit = false;

    DateField txtFechaRequerida = new DateField("Fecha Requerida:");
    TextField txtSolicita = new TextField("Solicitante:");
    TextArea txtObservaciones = new TextArea("Observaciones:");
    TextArea txtDireccionEntrega = new TextArea("Descripcion Entrega:");
    NativeSelect<Empleado> cboAutorizador = new NativeSelect("Autorizador:");
    NativeSelect<String> cboPrioridad = new NativeSelect("Prioridad:");

    Button btnAgregarPartida = new Button("Agregar",Fam3SilkIcon.ADD);
    Button btnModificarPartida = new Button("Modificar",Fam3SilkIcon.PENCIL);
    Button btnEliminarPartida = new Button("Eliminar",Fam3SilkIcon.DELETE);
    Button btnGuardar = new Button("Guardar", Fam3SilkIcon.DISK);
    Button btnCancelar = new Button("Cancelar", Fam3SilkIcon.CANCEL);
    
    Grid<RemisionDet> gridRemisionDet = new Grid<>();
    List<RemisionDet> listRemisionDet = new ArrayList<>();
    
    List<Empleado> listAutorizadoresCompras = new ArrayList<>();
    
    Label lblFolio;

    public WindowRemision() {
        lblFolio = new Label("REMISION " + getFolio()) {
            {
                setStyleName("h2");
            }
        };
        initComponents();
        btnModificarPartida.setEnabled(false);
    }

    public WindowRemision(Remision remisionTemp) {
        isEdit = true;
        remision = remisionTemp;
        lblFolio = new Label("REMISION " + remisionTemp.getFolio()) {
            {
                setStyleName("h2");
            }
        };
        initComponents();
    }

    public void initComponents() {
        setContent(cont);
        setModal(true);
        setResizable(false);
        
        String strWidth = "300";
        
        setWidth("80%");
        setHeight("80%");

        Binder<Remision> binder = new Binder<>();
        binder.forField(txtSolicita).bind(Remision::getSolicita, Remision::setSolicita);
        binder.forField(txtObservaciones).bind(Remision::getObservaciones, Remision::setObservaciones);
        binder.forField(txtDireccionEntrega).bind(Remision::getDireccion_entrega, Remision::setDireccion_entrega);
        binder.forField(cboPrioridad).bind(Remision::getPrioridad, Remision::setPrioridad);
        
        gridRemisionDet.setHeight("272");
        gridRemisionDet.setSelectionMode(Grid.SelectionMode.SINGLE);

        gridRemisionDet.addColumn(RemisionDet::getCantidad).setCaption("CTD").setWidth(75);
        gridRemisionDet.addColumn(RemisionDet::getDescripcion).setCaption("DESCRIPCION");
        gridRemisionDet.addColumn(RemisionDet::getPrecio_unitario).setCaption("P.U.").setWidth(100);
        gridRemisionDet.addColumn(RemisionDet::getTotal).setCaption("TOTAL").setWidth(100);
        
        if (isEdit) {
            binder.readBean(remision);
            
//            for (Proveedor prov : proveedorList1) {
//                if (remision.getProveedor_id_1().equals(prov.getId())) {
//                    cboProveedor1.setValue(prov);
//                }
//            }
        }

        btnAgregarPartida.addClickListener((event) -> {
            WindowRemisionDet windows = new WindowRemisionDet(remision);
            windows.center();
            windows.setModal(true);
            windows.addCloseListener((e) -> {
                if(isEdit){
                    gridRemisionDet.setItems(getPartidas());
                }else{
                    
                    RemisionDet partida = (RemisionDet) VaadinSession.getCurrent().getSession().getAttribute("REMISION_DET");
                    listRemisionDet.add(partida);
                    gridRemisionDet.setItems(listRemisionDet);
                }
            });
            getUI().addWindow(windows);
        });
        
        btnCancelar.addClickListener((event) -> {
            close();
        });

        btnGuardar.addClickListener((event) -> {
            try {
                
                if(!isEdit){
                    remision = new Remision();
                }

                binder.writeBean(remision);
                toUpperCase();

                RemisionDomain service = new RemisionDomain();

                if (isEdit) {
                    remision.setFecha_modificacion(new Date());
                    service.RemisionUpdate(remision);
                } else {
                    service.RemisionInsert(remision);
                }
                
                if(isEdit){
                    RemisionDetDomain domainDet = new RemisionDetDomain();
                    
                    for (RemisionDet partidaTemp : listRemisionDet) {
                        partidaTemp.setFolio(remision.getFolio());
                        partidaTemp.setId(remision.getId());
                        
                        domainDet.RemisionDetInsert(partidaTemp);
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
        txtDireccionEntrega.setWidth(strWidth);
        
        cboAutorizador.setItems(getAutorizadorCompras());
        cboAutorizador.setSelectedItem(listAutorizadoresCompras.get(0));
        cboAutorizador.setEmptySelectionAllowed(false);

        FormLayout fLay = new FormLayout();
        fLay.addComponents(txtFechaRequerida,txtSolicita, cboPrioridad, cboAutorizador, txtObservaciones, 
                txtDireccionEntrega);

        cont.setSpacing(false);
        cont.addComponents(lblFolio,
                new HorizontalLayout(fLay, 
                        new VerticalLayout(
                                new HorizontalLayout(
                                        btnAgregarPartida,btnModificarPartida,btnEliminarPartida),
                                gridRemisionDet){{setComponentAlignment(getComponent(0), Alignment.MIDDLE_CENTER);}}
                ){{setSpacing(false);}}, new HorizontalLayout(btnCancelar, btnGuardar));
        cont.setComponentAlignment(cont.getComponent(0), Alignment.MIDDLE_CENTER);
        cont.setComponentAlignment(cont.getComponent(1), Alignment.MIDDLE_CENTER);
        cont.setComponentAlignment(cont.getComponent(2), Alignment.MIDDLE_CENTER);
    }
    
    public List getPartidas() {
        String strWhere = " documento_id = " + remision.getId();

        RemisionDetDomain service = new RemisionDetDomain();
        service.getRemisionDet(strWhere, "", " id DESC");
        listRemisionDet = service.getObjects();

        if (!service.getOk()) {
            MessageBox.createError()
                    .withCaption("Error al cargar la informacion!")
                    .withMessage("Err: " + service.getNotification())
                    .withRetryButton()
                    .open();
        }
        return listRemisionDet;
    }
    
    public void toUpperCase() {
        remision.setSolicita(txtSolicita.getValue().toUpperCase());
        remision.setObservaciones(txtObservaciones.getValue().toUpperCase());
        remision.setDireccion_entrega(txtDireccionEntrega.getValue().toUpperCase());
    }
    
    public List<Empleado> getAutorizadorCompras() {
        EmpleadoDomain provService = new EmpleadoDomain();
        provService.getEmpleado(" autorizador = 1 ", "", " nombre ASC");
        
        listAutorizadoresCompras = provService.getObjects();
        return listAutorizadoresCompras;
    }
    
    public String getFolio() {
        ConfiguracionDomain domain = new ConfiguracionDomain();
        domain.getOneConfiguracion(_Folios.FOLIO_REMISION, _Folios.SERIE_REMISION);
        Configuracion conf = domain.getObject();
        
        String folio = conf.getSerie() + ManageString.fillWithZero(conf.getFolio(), 5);
        
        return folio;
    }
    
}