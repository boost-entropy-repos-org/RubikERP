/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rubik.erp.modulo.ventas;

import com.rubik.erp.domain.ClienteDomain;
import com.rubik.erp.fragments.FragmentTop;
import com.rubik.erp.model.Cliente;
import com.rubik.erp.model.Empleado;
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
public class VentasClientes extends Panel implements View {

    public static final String NAME = "CLIENTES";
    VerticalLayout container = new VerticalLayout();

    Empleado empleado = (Empleado) VaadinSession.getCurrent().getSession().getAttribute("USUARIO_ACTIVO");

    TextField txtBusqueda = new TextField();
    
    Grid<Cliente> gridCliente = new Grid<>();
    Button btnAdd = new Button("Agregar", Fam3SilkIcon.ADD);
    Button btnModify = new Button("Modificar", Fam3SilkIcon.PENCIL);
    Button btnSearch = new Button(Fam3SilkIcon.MAGNIFIER);
    List<Cliente> listProveedor = new ArrayList<>();

    public VentasClientes() {
        initComponents();

        HorizontalLayout hLayoutAux = new HorizontalLayout(txtBusqueda,btnSearch,btnAdd, btnModify);

        Label lblTitulo = new Label("Clientes") {
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
                gridCliente);
        
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

        gridCliente.addColumn(Cliente::getRazon_social).setCaption("RAZON SOCIAL").setId("RAZON SOCIAL");
        gridCliente.addColumn(Cliente::getRfc).setCaption("RFC").setId("RFC").setWidth(150);
        gridCliente.addColumn(Cliente::getTipo_cliente).setCaption("TIPO").setId("TIPO").setWidth(120);
        gridCliente.addColumn(Cliente::getContacto_venta_nombre).setCaption("CONTACTO").setId("CONTACTO");
        gridCliente.addColumn(Cliente::getContacto_venta_email).setCaption("EMAIL").setId("EMAIL").setWidth(200);;

        gridCliente.setItems(getClientes());
        gridCliente.setSelectionMode(Grid.SelectionMode.SINGLE);
        gridCliente.setSizeFull();
        gridCliente.setHeight("500px");

        btnAdd.addClickListener((event) -> {
            WindowVentasCliente windows = new WindowVentasCliente();
            windows.center();
            windows.setModal(true);
            windows.addCloseListener(ev -> {
                gridCliente.setItems(getClientes());
            });
            getUI().addWindow(windows);
        });

        btnModify.addClickListener((event) -> {
            if (gridCliente.getSelectedItems().size() == 1) {
                WindowVentasCliente windows = new WindowVentasCliente(gridCliente.getSelectedItems().iterator().next());
                windows.center();
                windows.setModal(true);
                windows.addCloseListener((e) -> {
                    gridCliente.setItems(getClientes());
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
            gridCliente.setItems(getClientes());
            txtBusqueda.setValue("");

        });

    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }

    public List getClientes() {
        String strWhere = " activo = 1 ";

        if (txtBusqueda.getValue() != "") {
            strWhere += " AND razon_social LIKE '%" + txtBusqueda.getValue().toUpperCase() + "%' OR rfc LIKE '%" + txtBusqueda.getValue().toUpperCase() + "%' ";
        }

        ClienteDomain service = new ClienteDomain();
        service.getCliente(strWhere, "", "razon_social ASC");
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
