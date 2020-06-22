/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rubik.erp.modulo.ventas;

import com.rubik.erp.domain.ProyectoDomain;
import com.rubik.erp.fragments.FragmentTop;
import com.rubik.erp.model.Empleado;
import com.rubik.erp.model.Proyecto;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import de.steinwedel.messagebox.MessageBox;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.rubicone.vaadin.fam3.silk.Fam3SilkIcon;

/**
 *
 * @author Dev
 */
public class VentasProyectos  extends Panel implements View {

    public static final String NAME = "PROYECTOS";
    VerticalLayout container = new VerticalLayout();

    Empleado empleado = (Empleado) VaadinSession.getCurrent().getSession().getAttribute("USUARIO_ACTIVO");

    TextField txtBusqueda = new TextField();
    
    Grid<Proyecto> gridProyecto = new Grid<>();
    Button btnAdd = new Button("Agregar", Fam3SilkIcon.ADD);
    Button btnModify = new Button("Modificar", Fam3SilkIcon.PENCIL);
    Button btnSearch = new Button(Fam3SilkIcon.MAGNIFIER);
    List<Proyecto> listProyecto = new ArrayList<>();
    
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public VentasProyectos() {
        initComponents();

        HorizontalLayout hLayoutAux = new HorizontalLayout(txtBusqueda,btnSearch,btnAdd, btnModify);

        Label lblTitulo = new Label("PROYECTOS / CONTRATOS") {
            {
                setStyleName("h1");
            }
        };
        lblTitulo.setSizeUndefined();

        setContent(container);

        container.setMargin(false);
        container.addComponents(new FragmentTop(),
                lblTitulo,
                hLayoutAux,
                gridProyecto);
        
        container.setComponentAlignment(container.getComponent(0), Alignment.MIDDLE_CENTER);
        container.setComponentAlignment(container.getComponent(1), Alignment.MIDDLE_CENTER);
        container.setComponentAlignment(container.getComponent(2), Alignment.MIDDLE_CENTER);
        container.setComponentAlignment(container.getComponent(3), Alignment.MIDDLE_CENTER);

        setContent(container);
    }

    public void initComponents() {
        setSizeFull();
        
        txtBusqueda.setWidth("310");
        txtBusqueda.setPlaceholder("Proyecto / Contrato");      

        Grid.Column<Proyecto, String> columnInicio = gridProyecto.addColumn(det -> ((det.getFecha_inicio()!= null) ? dateFormat.format(det.getFecha_inicio()) : ""));
        columnInicio.setCaption("INICIO");
        columnInicio.setId("INICIO");
        columnInicio.setWidth(120);
        Grid.Column<Proyecto, String> columnFin= gridProyecto.addColumn(det -> ((det.getFecha_fin()!= null) ? dateFormat.format(det.getFecha_fin()) : ""));
        columnFin.setCaption("FIN");
        columnFin.setId("FIN");
        columnFin.setWidth(120);
        gridProyecto.addColumn(Proyecto::getNombre).setCaption("PROYETO/CONTRATO").setId("PROYETO/CONTRATO");
        gridProyecto.addColumn(Proyecto::getNombre_cliente).setCaption("CLIENTE").setId("CLIENTE");
        
        gridProyecto.setItems(getProyecto());
        gridProyecto.setSelectionMode(Grid.SelectionMode.SINGLE);
        gridProyecto.setSizeFull();
        gridProyecto.setHeight("500px");

        btnAdd.addClickListener((event) -> {
            WindowVentasProyectos windows = new WindowVentasProyectos();
            windows.center();
            windows.setModal(true);
            windows.addCloseListener(ev -> {
                gridProyecto.setItems(getProyecto());
            });
            getUI().addWindow(windows);
        });

        btnModify.addClickListener((event) -> {
            if (gridProyecto.getSelectedItems().size() == 1) {
                WindowVentasProyectos windows = new WindowVentasProyectos(gridProyecto.getSelectedItems().iterator().next());
                windows.center();
                windows.setModal(true);
                windows.addCloseListener((e) -> {
                    gridProyecto.setItems(getProyecto());
                });
                getUI().addWindow(windows);
            } else {
                MessageBox.createError()
                        .withCaption("Error!")
                        .withMessage("Debe tener un Proyecto/Contrato seleccionado para poder modificarlo.")
                        .withRetryButton()
                        .open();
            }
        });
        
        btnSearch.addClickListener((event) -> {
            gridProyecto.setItems(getProyecto());
            txtBusqueda.setValue("");

        });

    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }

    public List getProyecto() {
        String strWhere = " activo = 1 ";

        if (txtBusqueda.getValue() != "") {
            strWhere += " AND nombre LIKE '%" + txtBusqueda.getValue().toUpperCase() + "%'";
        }

        ProyectoDomain service = new ProyectoDomain();
        service.getProyecto(strWhere, "", "nombre ASC");
        listProyecto = service.getObjects();

        if (!service.getOk()) {
            MessageBox.createError()
                    .withCaption("Error al cargar la informacion!")
                    .withMessage("Err: " + service.getNotification())
                    .withRetryButton()
                    .open();
        }
        return listProyecto;
    }
    
}