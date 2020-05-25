/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rubik.erp.modulo.compras;

import com.rubik.erp.config._DocumentoEstados;
import com.rubik.erp.domain.EmpleadoDomain;
import com.rubik.erp.domain.RequisicionDomain;
import com.rubik.erp.fragments.FragmentTop;
import com.rubik.erp.model.Empleado;
import com.rubik.erp.model.Requisicion;
import com.rubik.erp.modulo.generic.WindowCancelarDocumento;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import de.steinwedel.messagebox.MessageBox;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.rubicone.vaadin.fam3.silk.Fam3SilkIcon;

/**
 *
 * @author GRUCAS
 */
public class ComprasMonitorRequisiciones extends Panel implements View {

    public static final String NAME = "MONITOR_REQUISICIONES";
    VerticalLayout container = new VerticalLayout();

    Empleado empleado = (Empleado) VaadinSession.getCurrent().getSession().getAttribute("USUARIO_ACTIVO");
    
    Grid<Requisicion> gridRequisiciones = new Grid<>();
    List<Requisicion> listRequisiciones = new ArrayList<>();
    
    NativeSelect<Empleado> cboAutorizador = new NativeSelect();
    List<Empleado> listAutorizadores = new ArrayList<>();

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    
    public ComprasMonitorRequisiciones() {
        initComponents();
        
        Label lblTitulo = new Label("MONITOR DE REQUISICIONES") {
            {
                setStyleName("h1");
            }
        };
        lblTitulo.setSizeUndefined();

        setContent(container);

        container.setMargin(false);
        container.addComponents(new FragmentTop(),
                lblTitulo,
                new HorizontalLayout(new Label("Autorizador:"), cboAutorizador){{setComponentAlignment(getComponent(0), Alignment.MIDDLE_CENTER);}},
                gridRequisiciones);
        
        container.setComponentAlignment(container.getComponent(0), Alignment.MIDDLE_CENTER);
        container.setComponentAlignment(container.getComponent(1), Alignment.MIDDLE_CENTER);
        container.setComponentAlignment(container.getComponent(2), Alignment.MIDDLE_CENTER);
        container.setComponentAlignment(container.getComponent(3), Alignment.MIDDLE_CENTER);

        setContent(container);
    }

    public void initComponents() {
        setSizeFull();
        
        cboAutorizador.setItems(getAutorizadorCompras());
        cboAutorizador.setEmptySelectionAllowed(false);
        for (Empleado autorizador : listAutorizadores) {
            if (empleado.getId().equals(autorizador.getId())) {
                cboAutorizador.setValue(autorizador);
            }
        }
        
        cboAutorizador.addValueChangeListener((event) -> {
            gridRequisiciones.setItems(getRequisiciones());
        });

        Grid.Column<Requisicion, String> columnFecha = gridRequisiciones.addColumn(det -> ((det.getFecha_requerida() != null) ? dateFormat.format(det.getFecha_requerida()) : ""));
        columnFecha.setCaption("F. REQ");
        columnFecha.setId("F. REQ");
        columnFecha.setWidth(120);
        gridRequisiciones.addColumn(Requisicion::getFolio).setCaption("FOLIO").setId("FOLIO").setWidth(120);
        gridRequisiciones.addColumn(Requisicion::getPrioridad).setCaption("PRIORIDAD").setId("PRIORIDAD").setWidth(135);
        gridRequisiciones.addColumn(Requisicion::getEstado_doc).setCaption("ESTADO").setId("ESTADO").setWidth(135);
        gridRequisiciones.addColumn(Requisicion::getSolicita).setCaption("SOLICITA").setId("SOLICITA");
        gridRequisiciones.addComponentColumn(this::getBtnInfo).setCaption("").setWidth(131);
        gridRequisiciones.addComponentColumn(this::getBtnCancelar).setCaption("").setWidth(175);
        gridRequisiciones.addComponentColumn(this::getBtnAutorizar).setCaption("").setWidth(180);

        gridRequisiciones.setItems(getRequisiciones());
        gridRequisiciones.setSelectionMode(Grid.SelectionMode.SINGLE);
        gridRequisiciones.setSizeFull();
        gridRequisiciones.setHeight("500px");
    }
    
    public List<Empleado> getAutorizadorCompras() {
        EmpleadoDomain provService = new EmpleadoDomain();
        provService.getEmpleado(" autorizador = 1 ", "", " nombre ASC");
        
        listAutorizadores = provService.getObjects();
        return listAutorizadores;
    }
    
    private Button getBtnInfo(Requisicion requisicion) {
        Button btnTabulador = new Button("INFO", Fam3SilkIcon.REPORT_MAGNIFY);
        btnTabulador.addClickListener((event) -> {
            WindowRequisicionDetInfo window = new WindowRequisicionDetInfo(requisicion);
            window.center();
            window.setModal(true);
            window.addCloseListener(ev -> {
            });
            getUI().addWindow(window);
        });

        return btnTabulador;
    }
    
    private Button getBtnCancelar(Requisicion requisicion) {
        Button btnTabulador = new Button("CANCELAR", Fam3SilkIcon.CANCEL);
        btnTabulador.addClickListener((event) -> {
            MessageBox.createQuestion()
                    .withCaption("Confirmar Accion")
                    .withMessage("Desea Cancelar la Requisicion " + requisicion.getFolio() + "?")
                    .withYesButton(() -> {

                        WindowCancelarDocumento winCancelar = new WindowCancelarDocumento();
                        winCancelar.addCloseListener((e) -> {
                            requisicion.setActivo(false);
                            String razon_cancelar = winCancelar.RAZON_DE_CANCELAMIENTO;
                            requisicion.setRazon_cancelar(razon_cancelar);
                            requisicion.setEstado_doc(_DocumentoEstados.CANCELADO);
                            requisicion.setFecha_modificacion(new Date());
                            
                            RequisicionDomain service = new RequisicionDomain();
                            service.RequisicionUpdate(requisicion);

                            gridRequisiciones.setItems(getRequisiciones());

                        });
                        getUI().addWindow(winCancelar);
                    })
                    .withNoButton()
                    .open();
        });

        return btnTabulador;
    }
    
    private Button getBtnAutorizar(Requisicion requisicion) {
        Button btnTabulador = new Button("AUTORIZAR", Fam3SilkIcon.ACCEPT);
        btnTabulador.addClickListener((event) -> {
            MessageBox.createQuestion()
                    .withCaption("Confirmar Accion")
                    .withMessage("Desea Autorizar y pasar al Departamento de Compras la Requisicion " + requisicion.getFolio() + "?")
                    .withYesButton(() -> {
                        requisicion.setAutoriza(empleado.getNombre_completo());
                        requisicion.setAutoriza_id(empleado.getId());
                        
                        RequisicionDomain service = new RequisicionDomain();
                        service.RequisicionAutorizar(requisicion);

                        gridRequisiciones.setItems(getRequisiciones());
                    })
                    .withNoButton()
                    .open();
        });

        return btnTabulador;
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }

    public List getRequisiciones() {
        Empleado empleadoTemp = cboAutorizador.getValue();
        
        String strWhere = " activo = 1 and estado_doc = '" + _DocumentoEstados.POR_AUTORIZAR + "' AND autoriza_id = " + empleadoTemp.getId();

        RequisicionDomain service = new RequisicionDomain();
        service.getRequisicion(strWhere, "", "fecha_requerida DESC");
        listRequisiciones = service.getObjects();

        if (!service.getOk()) {
            MessageBox.createError()
                    .withCaption("Error al cargar la informacion!")
                    .withMessage("Err: " + service.getNotification())
                    .withRetryButton()
                    .open();
        }
        return listRequisiciones;
    }
    
}