/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rubik.erp.modulo.compras;

import com.rubik.erp.config._DocumentoEstados;
import com.rubik.erp.domain.EmpleadoDomain;
import com.rubik.erp.domain.RemisionDomain;
import com.rubik.erp.fragments.FragmentTop;
import com.rubik.erp.model.Empleado;
import com.rubik.erp.model.Remision;
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
public class ComprasMonitorRemisiones extends Panel implements View {

    public static final String NAME = "MONITOR_REMISIONES";
    VerticalLayout container = new VerticalLayout();

    Empleado empleado = (Empleado) VaadinSession.getCurrent().getSession().getAttribute("USUARIO_ACTIVO");
    
    Grid<Remision> gridRemisiones = new Grid<>();
    List<Remision> listRemisiones = new ArrayList<>();
    
    NativeSelect<Empleado> cboAutorizador = new NativeSelect();
    List<Empleado> listAutorizadores = new ArrayList<>();

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    
    public ComprasMonitorRemisiones() {
        initComponents();
        
        Label lblTitulo = new Label("MONITOR DE REMISIONES") {
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
                gridRemisiones);
        
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
            gridRemisiones.setItems(getRemisiones());
        });

        Grid.Column<Remision, String> columnFecha = gridRemisiones.addColumn(det -> ((det.getFecha_requerida() != null) ? dateFormat.format(det.getFecha_requerida()) : ""));
        columnFecha.setCaption("F. REQ");
        columnFecha.setId("F. REQ");
        columnFecha.setWidth(120);
        gridRemisiones.addColumn(Remision::getFolio).setCaption("FOLIO").setId("FOLIO").setWidth(120);
        gridRemisiones.addColumn(Remision::getPrioridad).setCaption("PRIORIDAD").setId("PRIORIDAD").setWidth(135);
        gridRemisiones.addColumn(Remision::getEstado_doc).setCaption("ESTADO").setId("ESTADO").setWidth(135);
        gridRemisiones.addColumn(Remision::getSolicita).setCaption("SOLICITA").setId("SOLICITA");
        gridRemisiones.addComponentColumn(this::getBtnInfo).setCaption("").setWidth(131);
        gridRemisiones.addComponentColumn(this::getBtnCancelar).setCaption("").setWidth(175);
        gridRemisiones.addComponentColumn(this::getBtnAutorizar).setCaption("").setWidth(180);

        gridRemisiones.setItems(getRemisiones());
        gridRemisiones.setSelectionMode(Grid.SelectionMode.SINGLE);
        gridRemisiones.setSizeFull();
        gridRemisiones.setHeight("500px");
    }
    
    public List<Empleado> getAutorizadorCompras() {
        EmpleadoDomain provService = new EmpleadoDomain();
        provService.getEmpleado(" autorizador = 1 ", "", " nombre ASC");
        
        listAutorizadores = provService.getObjects();
        return listAutorizadores;
    }
    
    private Button getBtnInfo(Remision remision) {
        Button btnTabulador = new Button("INFO", Fam3SilkIcon.REPORT_MAGNIFY);
        btnTabulador.addClickListener((event) -> {
            WindowRemisionDetInfo window = new WindowRemisionDetInfo(remision);
            window.center();
            window.setModal(true);
            window.addCloseListener(ev -> {
            });
            getUI().addWindow(window);
        });

        return btnTabulador;
    }
    
    private Button getBtnCancelar(Remision remision) {
        Button btnTabulador = new Button("CANCELAR", Fam3SilkIcon.CANCEL);
        btnTabulador.addClickListener((event) -> {
            MessageBox.createQuestion()
                    .withCaption("Confirmar Accion")
                    .withMessage("Desea Cancelar la Remision " + remision.getFolio() + "?")
                    .withYesButton(() -> {

                        WindowCancelarDocumento winCancelar = new WindowCancelarDocumento();
                        winCancelar.addCloseListener((e) -> {
                            remision.setActivo(false);
                            String razon_cancelar = winCancelar.RAZON_DE_CANCELAMIENTO;
                            remision.setRazon_cancelar(razon_cancelar);
                            remision.setEstado_doc(_DocumentoEstados.CANCELADO);
                            remision.setFecha_modificacion(new Date());
                            
                            RemisionDomain service = new RemisionDomain();
                            service.RemisionUpdate(remision);

                            gridRemisiones.setItems(getRemisiones());

                        });
                        getUI().addWindow(winCancelar);
                    })
                    .withNoButton()
                    .open();
        });

        return btnTabulador;
    }
    
    private Button getBtnAutorizar(Remision remision) {
        Button btnTabulador = new Button("AUTORIZAR", Fam3SilkIcon.ACCEPT);
        btnTabulador.addClickListener((event) -> {
            MessageBox.createQuestion()
                    .withCaption("Confirmar Accion")
                    .withMessage("Desea Autorizar y pasar al Departamento de Compras la Remision " + remision.getFolio() + "?")
                    .withYesButton(() -> {
                        remision.setAutoriza(empleado.getNombre_completo());
                        remision.setAutoriza_id(empleado.getId());
                        
                        RemisionDomain service = new RemisionDomain();
                        service.RemisionAutorizar(remision);

                        gridRemisiones.setItems(getRemisiones());
                    })
                    .withNoButton()
                    .open();
        });

        return btnTabulador;
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }

    public List getRemisiones() {
        Empleado empleadoTemp = cboAutorizador.getValue();
        
        String strWhere = " activo = 1 and estado_doc = 'TERMINADO' AND autoriza_id = " + empleadoTemp.getId();

        RemisionDomain service = new RemisionDomain();
        service.getRemision(strWhere, "", "fecha_requerida DESC");
        listRemisiones = service.getObjects();

        if (!service.getOk()) {
            MessageBox.createError()
                    .withCaption("Error al cargar la informacion!")
                    .withMessage("Err: " + service.getNotification())
                    .withRetryButton()
                    .open();
        }
        return listRemisiones;
    }
    
}