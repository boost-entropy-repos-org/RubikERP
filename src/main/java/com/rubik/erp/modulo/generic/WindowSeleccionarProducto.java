/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rubik.erp.modulo.generic;

import com.rubik.erp.domain.ProductoDomain;
import com.rubik.erp.model.Empleado;
import com.rubik.erp.model.Producto;
import com.rubik.erp.modulo.compras.WindowProductoSimple;
import com.vaadin.data.HasValue;
import com.vaadin.data.ValueProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.components.grid.HeaderCell;
import com.vaadin.ui.components.grid.HeaderRow;
import com.vaadin.ui.themes.ValoTheme;
import de.steinwedel.messagebox.MessageBox;
import java.util.ArrayList;
import java.util.List;
import org.rubicone.vaadin.fam3.silk.Fam3SilkIcon;

/**
 *
 * @author Dev
 */
public class WindowSeleccionarProducto extends Window {
    
    Empleado empleado = (Empleado) VaadinSession.getCurrent().getSession().getAttribute("USUARIO_ACTIVO");
    
    VerticalLayout cont = new VerticalLayout();
    
    Button btnSeleccionar = new Button("Seleccionar",Fam3SilkIcon.ACCEPT);
    Button btnAdd = new Button("Agregar",Fam3SilkIcon.PACKAGE);
    
    Grid<Producto> gridSelecProd = new Grid<>();
    List<Producto> listProducto = new ArrayList<>();
    ListDataProvider<Producto> listDataProviderProducto;
    
    public WindowSeleccionarProducto() {    
        setCaption("Seleccione un Producto");
        VaadinSession.getCurrent().getSession().setAttribute("PRODUCTO_SELECCIONADO",null);
        initComponents();
    }

    public void initComponents() { 
        
        gridSelecProd.setWidth("100%");
        gridSelecProd.setSelectionMode(SelectionMode.SINGLE);
        gridSelecProd.addColumn(Producto::getCodigo_interno).setCaption("CODIGO").setId("CODIGO");
        gridSelecProd.addColumn(Producto::getDescripcion_corta).setCaption("DESCRIPCION CORTA").setId("DESCRIPCION CORTA");
        gridSelecProd.addColumn(Producto::getModelo).setCaption("MODELO").setId("MODELO");
        gridSelecProd.addColumn(Producto::getMarca).setCaption("MARCA").setId("MARCA");
        gridSelecProd.addColumn(Producto::getNo_serie).setCaption("NO SERIE").setId("NO SERIE");

        cont.addComponents(new HorizontalLayout(btnAdd,btnSeleccionar), gridSelecProd);
        cont.setComponentAlignment(cont.getComponent(0), Alignment.MIDDLE_CENTER);
        cont.setComponentAlignment(cont.getComponent(1), Alignment.MIDDLE_CENTER);

        btnSeleccionar.addClickListener((event) -> {
            if (gridSelecProd.getSelectedItems().size() > 0) {
                if (gridSelecProd.getSelectedItems().iterator().next() != null) {
                    VaadinSession.getCurrent().getSession().setAttribute("PRODUCTO_SELECCIONADO",gridSelecProd.getSelectedItems().iterator().next());
                    close();
                } else {
                    MessageBox.createError()
                            .withCaption("Error!")
                            .withMessage("Debe seleccionar un Producto para continuar.")
                            .withRetryButton()
                            .open();
                }
            } else {
                MessageBox.createError()
                        .withCaption("Error!")
                        .withMessage("Debe seleccionar un Producto para continuar.")
                        .withRetryButton()
                        .open();
            }
        });

        btnAdd.addClickListener((event) -> {
            WindowProductoSimple windows = new WindowProductoSimple();
            windows.center();
            windows.setModal(true);
            windows.addCloseListener(ev -> {
                if(windows.producto!=null){
                    gridSelecProd.setItems(getProducto(windows.producto.getId()));
                }
            });
            getUI().addWindow(windows);
        });
        
        gridSelecProd.setItems(getProducto());
        
        listDataProviderProducto = new ListDataProvider<>(getProducto());
        listDataProviderProducto.clearFilters();
        gridSelecProd.setDataProvider(listDataProviderProducto);

        HeaderRow filterRow = gridSelecProd.appendHeaderRow();

        for (final Grid.Column<Producto, ?> column : gridSelecProd.getColumns()) {
            final HeaderCell headerCell = filterRow.getCell(column);
            if (null != column.getId()) {
                switch (column.getId()) {
                    case "DESCRIPCION CORTA":
                        headerCell.setComponent(createFilterTextField(column));
                        break;
                    case "CODIGO":
                        headerCell.setComponent(createFilterTextField(column));
                        break;
                    case "MODELO":
                        headerCell.setComponent(createFilterTextField(column));
                        break;
                    case "MARCA":
                        headerCell.setComponent(createFilterTextField(column));
                        break;
                    default:
                        break;
                }
            }
        }
        
        setResizable(false);
        setContent(cont);
        setModal(true);
        center();
        setWidth("70%");
        setHeight("70%");
    }

    private TextField createFilterTextField(final Grid.Column<Producto, ?> column) {
        final TextField tfFilter = getColumnFilterField();

        tfFilter.addValueChangeListener((HasValue.ValueChangeEvent<String> event) -> {
            final ValueProvider<Producto, String> valueProvider = (ValueProvider<Producto, String>) column.getValueProvider();
            
            listDataProviderProducto.addFilter(valueProvider, value -> {
                return value.toLowerCase().contains(tfFilter.getValue().toLowerCase());
            });
            listDataProviderProducto.refreshAll();
        });
        
        return tfFilter;
    }

    private TextField getColumnFilterField() {
        TextField filter = new TextField();
        filter.setWidth("100%");
        filter.addStyleName(ValoTheme.TEXTFIELD_TINY);
        filter.setPlaceholder("Buscar");
        return filter;
    }
    
    public List getProducto() {
        ProductoDomain service = new ProductoDomain();
        service.getProducto("activo = 1", "", " descripcion_corta ASC");
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
    
    public List getProducto(Integer id) {
        ProductoDomain service = new ProductoDomain();
        service.getProducto("id = " + id, "", " descripcion_corta ASC");
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