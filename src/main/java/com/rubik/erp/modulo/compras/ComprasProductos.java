/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rubik.erp.modulo.compras;

import com.rubik.erp.domain.ProductoDomain;
import com.rubik.erp.fragments.FragmentTop;
import com.rubik.erp.model.Empleado;
import com.rubik.erp.model.Producto;
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
public class ComprasProductos extends Panel implements View {

    public static final String NAME = "PRODUCTOS";
    VerticalLayout container = new VerticalLayout();

    Empleado empleado = (Empleado) VaadinSession.getCurrent().getSession().getAttribute("USUARIO_ACTIVO");

    TextField txtBusqueda = new TextField();
    
    Grid<Producto> gridProducto = new Grid<>();
    Button btnAdd = new Button("Agregar", Fam3SilkIcon.ADD);
    Button btnModify = new Button("Modificar", Fam3SilkIcon.PENCIL);
    Button btnSearch = new Button(Fam3SilkIcon.MAGNIFIER);
    List<Producto> listProducto = new ArrayList<>();

    public ComprasProductos() {
        initComponents();

        HorizontalLayout hLayoutAux = new HorizontalLayout(txtBusqueda,btnSearch,btnAdd, btnModify);

        Label lblTitulo = new Label("PRODUCTOS") {
            {
                setStyleName("h1");
            }
        };
        lblTitulo.setSizeUndefined();

        container.setMargin(false);
        container.addComponents(new FragmentTop(),
                lblTitulo,
                hLayoutAux,
                gridProducto);
        
        container.setComponentAlignment(container.getComponent(0), Alignment.MIDDLE_CENTER);
        container.setComponentAlignment(container.getComponent(1), Alignment.MIDDLE_CENTER);
        container.setComponentAlignment(container.getComponent(2), Alignment.MIDDLE_CENTER);
        container.setComponentAlignment(container.getComponent(3), Alignment.MIDDLE_CENTER);

        setContent(container);
    }

    public void initComponents() {
        setSizeFull();
        
        txtBusqueda.setWidth("310");
        txtBusqueda.setPlaceholder("Producto a buscar");      

        gridProducto.addColumn(Producto::getCodigo_interno).setCaption("CODIGO").setId("CODIGO").setWidth(105);
        gridProducto.addColumn(Producto::getNo_serie).setCaption("NS").setId("NS").setWidth(97);
        gridProducto.addColumn(Producto::getMarca).setCaption("MARCA").setId("MARCA").setWidth(135);
        gridProducto.addColumn(Producto::getDescripcion_corta).setCaption("DESCRIPCION").setId("DESCRIPCION");
        gridProducto.addColumn(Producto::getInventario_actual).setCaption("INVENTARIO").setId("INVENTARIO").setWidth(120);
        

        gridProducto.setItems(getProducto());
        gridProducto.setSelectionMode(Grid.SelectionMode.SINGLE);
        gridProducto.setSizeFull();
        gridProducto.setHeight("500px");

        btnAdd.addClickListener((event) -> {
            WindowProducto windows = new WindowProducto();
            windows.center();
            windows.setModal(true);
            windows.addCloseListener(ev -> {
                gridProducto.setItems(getProducto());
            });
            getUI().addWindow(windows);
        });

        btnModify.addClickListener((event) -> {
            if (gridProducto.getSelectedItems().size() == 1) {
                WindowProducto windows = new WindowProducto(gridProducto.getSelectedItems().iterator().next());
                windows.center();
                windows.setModal(true);
                windows.addCloseListener((e) -> {
                    gridProducto.setItems(getProducto());
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
            gridProducto.setItems(getProducto());
            txtBusqueda.setValue("");
        });

    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }

    public List getProducto() {
        String strWhere = " activo = 1 ";

        if (txtBusqueda.getValue() != "") {
            strWhere += " AND descripcion_corta LIKE '%" + txtBusqueda.getValue().toUpperCase() + "%' "
                    + "OR marca LIKE '%" + txtBusqueda.getValue().toUpperCase() + "%' "
                    + "OR modelo LIKE '%" + txtBusqueda.getValue().toUpperCase() + "%' "
                    + "OR descripcion LIKE '%" + txtBusqueda.getValue().toUpperCase() + "%' "
                    + "OR numero_serie LIKE '%" + txtBusqueda.getValue().toUpperCase() + "%' ";
        }

        ProductoDomain service = new ProductoDomain();
        service.getProducto(strWhere, "", "descripcion ASC");
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
