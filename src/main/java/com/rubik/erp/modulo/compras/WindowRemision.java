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
import com.rubik.erp.domain.RemisionDetDomain;
import com.rubik.erp.domain.RemisionDomain;
import com.rubik.erp.model.Configuracion;
import com.rubik.erp.model.Empleado;
import com.rubik.erp.model.Remision;
import com.rubik.erp.model.RemisionDet;
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
public class WindowRemision extends Window {
    
    Empleado empleado = (Empleado) VaadinSession.getCurrent().getSession().getAttribute("USUARIO_ACTIVO");
    
    VerticalLayout cont = new VerticalLayout();
    String title_window = "Remisiones de Compra";

    Remision remision;
    Boolean isEdit = false;

    DateField txtFechaRequerida = new DateField("Fecha Requerida:", LocalDate.now());
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
    String folio = "";

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
//        gridRemisionDet.addColumn(RemisionDet::getPrecio_unitario).setCaption("P.U.").setWidth(100);
//        gridRemisionDet.addColumn(RemisionDet::getTotal).setCaption("TOTAL").setWidth(100);

        btnAgregarPartida.addClickListener((event) -> {
            WindowRemisionDet windows = new WindowRemisionDet(remision);
            windows.center();
            windows.setModal(true);
            windows.addCloseListener((e) -> {
                Boolean ok = (Boolean) VaadinSession.getCurrent().getSession().getAttribute("PARTIDA_OK");
                if (ok) {
                    if (isEdit) {
                        gridRemisionDet.setItems(getPartidas());
                    } else {
                        RemisionDet partida = (RemisionDet) VaadinSession.getCurrent().getSession().getAttribute("REMISION_DET");
                        listRemisionDet.add(partida);
                        gridRemisionDet.setItems(listRemisionDet);
                    }
                }
            });
            getUI().addWindow(windows);
        });
        
        btnModificarPartida.addClickListener((event) -> {
            if(gridRemisionDet.getSelectedItems().size() == 1){
                WindowRemisionDet windows = new WindowRemisionDet(remision,gridRemisionDet.getSelectedItems().iterator().next());
                windows.center();
                windows.setModal(true);
                windows.addCloseListener((e) -> {
                    Boolean ok = (Boolean) VaadinSession.getCurrent().getSession().getAttribute("PARTIDA_OK");
                    if (ok) {
                        if (isEdit) {
                            gridRemisionDet.setItems(getPartidas());
                        } else {
                            RemisionDet partida = (RemisionDet) VaadinSession.getCurrent().getSession().getAttribute("REMISION_DET");
                            listRemisionDet.add(partida);
                            gridRemisionDet.setItems(listRemisionDet);
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
            if (gridRemisionDet.getSelectedItems().size() == 1) {
                MessageBox.createQuestion()
                        .withCaption("Atencion!")
                        .withMessage("Desea eliminar la partida seleccionada?.")
                        .withOkButton(() -> {

                            RemisionDet partida = gridRemisionDet.getSelectedItems().iterator().next();

                            if (isEdit) {
                                RemisionDetDomain dom = new RemisionDetDomain();
                                dom.RemisionDetDelete(partida);
                                gridRemisionDet.setItems(getPartidas());
                            } else {
                                listRemisionDet.remove(partida);
                                gridRemisionDet.setItems(listRemisionDet);
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
                    remision = new Remision();
                }

                binder.writeBean(remision);
                toUpperCase();

                remision.setUsuario_id(empleado.getId());
                remision.setUsuario(empleado.getNombre_completo());
                remision.setEstado_doc(_DocumentoEstados.EN_PROCESO);
                remision.setTipo_documento(_DocumentoTipos.REMISION_DE_COMPRA);
                remision.setTipo_archivo("PDF");
                remision.setFecha_requerida(ManageDates.getDateFromLocalDate(txtFechaRequerida.getValue()));
                remision.setAutoriza(autoriza.getNombre_completo());
                remision.setAutoriza_id(autoriza.getId());
                remision.setActivo(true);
                
                RemisionDomain service = new RemisionDomain();

                if (isEdit) {
                    remision.setFecha_modificacion(new Date());
                    for (RemisionDet partidaTemp : listRemisionDet) {
                        total += partidaTemp.getTotal();
                    }
                    remision.setTotal(total);
                    
                    service.RemisionUpdate(remision);
                    
                } else {
                    remision.setFolio(getFolio());
                    remision.setSerie("");
                    
                    RemisionDetDomain domainDet = new RemisionDetDomain();
                    
                    for (RemisionDet partidaTemp : listRemisionDet) { // Obtiene el total
                        total += partidaTemp.getTotal();
                    }

                    remision.setTotal(total);
                    service.RemisionInsert(remision);
                    updateFolio();
                    
                    for (RemisionDet partidaTemp : listRemisionDet) { //Guarda la partida con el ID de la remision
                        partidaTemp.setFolio(remision.getFolio());
                        partidaTemp.setDocumento_id(remision.getId());
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

        if (isEdit) {
            binder.readBean(remision);
            
            gridRemisionDet.setItems(getPartidas());
            
            for (Empleado autorizador : listAutorizadoresCompras) {
                if (remision.getAutoriza_id().equals(autorizador.getId())) {
                    cboAutorizador.setValue(autorizador);
                }
            }
        }
        
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
        
        setContent(cont);
        setModal(true);
        setResizable(false);
        setClosable(false);
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
        
        folio = conf.getSerie() + ManageString.fillWithZero(conf.getFolio(), 5);
        
        return folio;
    }
    
    public void updateFolio() {
        ConfiguracionDomain domain = new ConfiguracionDomain();
        domain.ConfiguracionUpdate(_Folios.FOLIO_REMISION);
    }
    
}