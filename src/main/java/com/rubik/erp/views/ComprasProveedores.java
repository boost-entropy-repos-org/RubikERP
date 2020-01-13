/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rubik.erp.views;

import com.rubik.erp.domain.ProveedorDomain;
import com.rubik.erp.fragments.FragmentTop;
import com.rubik.erp.model.Empleado;
import com.rubik.erp.model.Proveedor;
import com.rubik.erp.window.WindowComprasProveedor;
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
import java.util.ArrayList;
import java.util.List;
import org.rubicone.vaadin.fam3.silk.Fam3SilkIcon;

/**
 *
 * @author Dev
 */
public class ComprasProveedores extends Panel implements View {

    public static final String NAME = "PROVEEDORES";
    VerticalLayout container = new VerticalLayout();

    Empleado empleado = (Empleado) VaadinSession.getCurrent().getSession().getAttribute("USUARIO_ACTIVO");

    TextField txtBusqueda = new TextField();
    
    Grid<Proveedor> gridProveedor = new Grid<>();
    Button btnAdd = new Button("Agregar", Fam3SilkIcon.ADD);
    Button btnModify = new Button("Modificar", Fam3SilkIcon.PENCIL);
    Button btnSearch = new Button(Fam3SilkIcon.MAGNIFIER);
    List<Proveedor> listProveedor = new ArrayList<>();

    public ComprasProveedores() {
        initComponents();

        HorizontalLayout hLayoutAux = new HorizontalLayout(txtBusqueda,btnSearch,btnAdd, btnModify);

        Label lblTitulo = new Label("PROVEEDOR") {
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
                gridProveedor);
        
        container.setComponentAlignment(container.getComponent(0), Alignment.MIDDLE_CENTER);
        container.setComponentAlignment(container.getComponent(1), Alignment.MIDDLE_CENTER);
        container.setComponentAlignment(container.getComponent(2), Alignment.MIDDLE_CENTER);
        container.setComponentAlignment(container.getComponent(3), Alignment.MIDDLE_CENTER);

        setContent(container);
    }

    public void initComponents() {
        setSizeFull();
        
        txtBusqueda.setWidth("310");
        txtBusqueda.setPlaceholder("RFC o Razon Social a buscar");      

        gridProveedor.addColumn(Proveedor::getRazon_social).setCaption("RAZON SOCIAL").setId("RAZON SOCIAL");
        gridProveedor.addColumn(Proveedor::getRfc).setCaption("RFC").setId("RFC").setWidth(150);
        gridProveedor.addColumn(Proveedor::getContacto_compra_nombre).setCaption("CONTACTO").setId("CONTACTO");
        gridProveedor.addColumn(Proveedor::getContacto_compra_telefono).setCaption("TELEFONO").setId("TELEFONO").setWidth(120);
        gridProveedor.addColumn(Proveedor::getContacto_compra_email).setCaption("EMAIL").setId("EMAIL").setWidth(200);;

        gridProveedor.setItems(getProveedor());
        gridProveedor.setSelectionMode(Grid.SelectionMode.SINGLE);
        gridProveedor.setSizeFull();
        gridProveedor.setHeight("500px");

        btnAdd.addClickListener((event) -> {
            WindowComprasProveedor windows = new WindowComprasProveedor();
            windows.center();
            windows.setModal(true);
            windows.addCloseListener(ev -> {
                gridProveedor.setItems(getProveedor());
            });
            getUI().addWindow(windows);
        });

        btnModify.addClickListener((event) -> {
            if (gridProveedor.getSelectedItems().size() == 1) {
                WindowComprasProveedor windows = new WindowComprasProveedor(gridProveedor.getSelectedItems().iterator().next());
                windows.center();
                windows.setModal(true);
                windows.addCloseListener((e) -> {
                    gridProveedor.setItems(getProveedor());
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
            gridProveedor.setItems(getProveedor());
            txtBusqueda.setValue("");

        });

    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }

    public List getProveedor() {
        String strWhere = " activo = 1 ";

        if (txtBusqueda.getValue() != "") {
            strWhere += " AND razon_social LIKE '%" + txtBusqueda.getValue().toUpperCase() + "%' OR rfc LIKE '%" + txtBusqueda.getValue().toUpperCase() + "%' ";
        }

        ProveedorDomain service = new ProveedorDomain();
        service.getProveedor(strWhere, "", "razon_social ASC");
        listProveedor = service.getObjects();

        if (!service.getOk()) {
            MessageBox.createError()
                    .withCaption("Error al cargar la informacion!")
                    .withMessage("Err: " + service.getNotification())
                    .withRetryButton()
                    .open();
        }
        return listProveedor;
    }
    
}
