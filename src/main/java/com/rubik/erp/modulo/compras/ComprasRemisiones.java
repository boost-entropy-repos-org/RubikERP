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
import com.vaadin.ui.DateField;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import de.steinwedel.messagebox.MessageBox;
import java.util.ArrayList;
import java.util.List;
import org.rubicone.vaadin.fam3.silk.Fam3SilkIcon;

/**
 *
 * @author Dev
 */
public class ComprasRemisiones extends Panel implements View {

    public static final String NAME = "REMISIONES";
    VerticalLayout container = new VerticalLayout();

    Empleado empleado = (Empleado) VaadinSession.getCurrent().getSession().getAttribute("USUARIO_ACTIVO");

    TextField txtBusqueda = new TextField();
    
    Grid<Remision> gridRemisiones = new Grid<>();
    DateField txtFechaIni = new DateField();
    DateField txtFechaFin = new DateField();
    Button btnAdd = new Button("Agregar", Fam3SilkIcon.ADD);
    Button btnModify = new Button("Modificar", Fam3SilkIcon.PENCIL);
    Button btnCancel = new Button("Cancelar", Fam3SilkIcon.CANCEL);
    Button btnSearch = new Button(Fam3SilkIcon.MAGNIFIER);
    List<Remision> listProducto = new ArrayList<>();

    public ComprasRemisiones() {
        initComponents();

        HorizontalLayout hLayoutAux = new HorizontalLayout(
                txtBusqueda,
                new Label("Fecha: "),txtFechaIni,new Label("A: "),txtFechaFin,
                btnSearch,btnAdd, btnModify);
        hLayoutAux.setComponentAlignment(hLayoutAux.getComponent(1), Alignment.MIDDLE_CENTER);
        hLayoutAux.setComponentAlignment(hLayoutAux.getComponent(3), Alignment.MIDDLE_CENTER);
        
        Label lblTitulo = new Label("REMISIONES") {
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
                gridRemisiones);
        
        container.setComponentAlignment(container.getComponent(0), Alignment.MIDDLE_CENTER);
        container.setComponentAlignment(container.getComponent(1), Alignment.MIDDLE_CENTER);
        container.setComponentAlignment(container.getComponent(2), Alignment.MIDDLE_CENTER);
        container.setComponentAlignment(container.getComponent(3), Alignment.MIDDLE_CENTER);

        setContent(container);
    }

    public void initComponents() {
        setSizeFull();

        txtFechaIni.setWidth("115");
        txtFechaFin.setWidth("115");
        
        txtBusqueda.setWidth("310");
        txtBusqueda.setPlaceholder("Folio de Remision a buscar");      

        gridRemisiones.addColumn(Remision::getFecha_requerida).setCaption("F. REQ").setId("F. REQ").setWidth(120);
        gridRemisiones.addColumn(Remision::getFolio).setCaption("FOLIO").setId("FOLIO").setWidth(120);
        gridRemisiones.addColumn(Remision::getPrioridad).setCaption("PRIORIDAD").setId("PRIORIDAD").setWidth(135);
        gridRemisiones.addColumn(Remision::getEstado_doc).setCaption("ESTADO").setId("ESTADO").setWidth(135);
        gridRemisiones.addColumn(Remision::getFecha_orden_compra).setCaption("FECHA OC").setId("FECHA OC").setWidth(120);
        gridRemisiones.addColumn(Remision::getFolio_orden_compra).setCaption("FOLIO OC").setId("FOLIO OC").setWidth(120);
        gridRemisiones.addColumn(Remision::getRazon_cancelar).setCaption("CANCELACION").setId("CANCELACION");

        gridRemisiones.setItems(getRemisiones());
        gridRemisiones.setSelectionMode(Grid.SelectionMode.SINGLE);
        gridRemisiones.setSizeFull();
        gridRemisiones.setHeight("500px");

        btnAdd.addClickListener((event) -> {
            WindowRemision windows = new WindowRemision();
            windows.center();
            windows.setModal(true);
            windows.addCloseListener(ev -> {
                gridRemisiones.setItems(getRemisiones());
            });
            getUI().addWindow(windows);
        });

        btnModify.addClickListener((event) -> {
            if (gridRemisiones.getSelectedItems().size() == 1) {
                WindowRemision windows = new WindowRemision(gridRemisiones.getSelectedItems().iterator().next());
                windows.center();
                windows.setModal(true);
                windows.addCloseListener((e) -> {
                    gridRemisiones.setItems(getRemisiones());
                });
                getUI().addWindow(windows);
            } else {
                MessageBox.createError()
                        .withCaption("Error!")
                        .withMessage("Debe tener un Proveedor seleccionado para poder modificarlo.")
                        .withRetryButton()
                        .open();
            }
        });
        
        btnSearch.addClickListener((event) -> {
            gridRemisiones.setItems(getRemisiones());
            txtBusqueda.setValue("");

        });

    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }

    public List getRemisiones() {
        String strWhere = " activo = 1 ";

        if (!"".equals(txtBusqueda.getValue())) {
            strWhere += " AND folio = '" + txtBusqueda.getValue().toUpperCase() + "'";
        }
        
        if(txtFechaIni.getValue() != null && txtFechaFin.getValue() != null){
            strWhere += "";
        }

        RemisionDomain service = new RemisionDomain();
        service.getRemision(strWhere, "", "fecha_requerida DESC");
        listProducto = service.getObjects();

        if (!service.getOk()) {
            MessageBox.createError()
                    .withCaption("Error al cargar la informacion!")
                    .withMessage("Err: " + service.getNotification())
                    .withRetryButton()
                    .open();
        }
        return listProducto;
    }
    
}
