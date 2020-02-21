/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rubik.erp.modulo.compras;

import com.rubik.erp.domain.RemisionDomain;
import com.rubik.erp.fragments.FragmentTop;
import com.rubik.erp.model.Empleado;
import com.rubik.erp.model.Remision;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import de.steinwedel.messagebox.MessageBox;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
                new HorizontalLayout(),
                gridRemisiones);
        
        container.setComponentAlignment(container.getComponent(0), Alignment.MIDDLE_CENTER);
        container.setComponentAlignment(container.getComponent(1), Alignment.MIDDLE_CENTER);
        container.setComponentAlignment(container.getComponent(2), Alignment.MIDDLE_CENTER);

        setContent(container);
    }

    public void initComponents() {
        setSizeFull();   

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
    
    private Button getBtnInfo(Remision remision) {
        Button btnTabulador = new Button("INFO", Fam3SilkIcon.REPORT_MAGNIFY);
        btnTabulador.addClickListener((event) -> {
//            WindowComercialTabulador window = new WindowComercialTabulador(cte);
//            window.center();
//            window.setModal(true);
//            window.addCloseListener(ev -> {
//            });
//            getUI().addWindow(window);
        });

        return btnTabulador;
    }
    
    private Button getBtnCancelar(Remision remision) {
        Button btnTabulador = new Button("CANCELAR", Fam3SilkIcon.CANCEL);
        btnTabulador.addClickListener((event) -> {
//            WindowComercialTabulador window = new WindowComercialTabulador(cte);
//            window.center();
//            window.setModal(true);
//            window.addCloseListener(ev -> {
//            });
//            getUI().addWindow(window);
        });

        return btnTabulador;
    }
    
    private Button getBtnAutorizar(Remision remision) {
        Button btnTabulador = new Button("AUTORIZAR", Fam3SilkIcon.ACCEPT);
        btnTabulador.addClickListener((event) -> {
//            WindowComercialTabulador window = new WindowComercialTabulador(cte);
//            window.center();
//            window.setModal(true);
//            window.addCloseListener(ev -> {
//            });
//            getUI().addWindow(window);
        });

        return btnTabulador;
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }

    public List getRemisiones() {
        String strWhere = " activo = 1 and estado_doc = 'TERMINADO'";

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