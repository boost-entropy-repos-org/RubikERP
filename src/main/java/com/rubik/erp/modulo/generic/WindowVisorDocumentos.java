/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rubik.erp.modulo.generic;

import com.rubik.Base.DocumentObjectBase;
import com.rubik.erp.model.Empleado;
import com.rubik.erp.model.NodeFile;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import java.util.ArrayList;
import java.util.List;
import org.rubicone.vaadin.fam3.silk.Fam3SilkIcon;

/**
 *
 * @author Dev
 */
public class WindowVisorDocumentos extends Window {
    
    Empleado empleado = (Empleado) VaadinSession.getCurrent().getSession().getAttribute("USUARIO_ACTIVO");
    
    VerticalLayout cont = new VerticalLayout();
    
    DocumentObjectBase documento;
    
    Button btnAdd = new Button("Agregar",Fam3SilkIcon.ADD);
    Button btnDelete = new Button("Eliminar",Fam3SilkIcon.ADD);
    
    Grid<NodeFile> gridSelecProd = new Grid<>();
    List<NodeFile> listProducto = new ArrayList<>();
    
    public WindowVisorDocumentos(DocumentObjectBase document, String tipo_documento) {
        setCaption(tipo_documento + " DE " + document.getTipo_documento() + " FOLIO " + document.getFolio());
        VaadinSession.getCurrent().getSession().setAttribute("PRODUCTO_SELECCIONADO",null);
        initComponents();
    }

    public void initComponents() { 
        
        gridSelecProd.setWidth("100%");
        gridSelecProd.setSelectionMode(SelectionMode.SINGLE);
        gridSelecProd.addColumn(NodeFile::getFolio).setCaption("FOLIO").setId("FOLIO");
        gridSelecProd.addColumn(NodeFile::getProveedor).setCaption("CTE/PROVEEDOR").setId("CTE/PROVEEDOR");

        cont.addComponents(new HorizontalLayout(btnAdd,btnDelete), gridSelecProd);
        cont.setComponentAlignment(cont.getComponent(0), Alignment.MIDDLE_CENTER);
        cont.setComponentAlignment(cont.getComponent(1), Alignment.MIDDLE_CENTER);

        btnAdd.addClickListener((event) -> {
            WindowFileNode windows = new WindowFileNode();
            windows.center();
            windows.setModal(true);
//            windows.addCloseListener(ev -> {
//                gridSelecProd.setItems(getProducto(windows.producto.getId()));
//            });
            getUI().addWindow(windows);
        });
        
//        gridSelecProd.setItems(getProducto());
        
        setResizable(false);
        setContent(cont);
        setModal(true);
        center();
        setWidth("70%");
        setHeight("70%");
    }

    
//    public List getProducto() {
//        ProductoDomain service = new ProductoDomain();
//        service.getProducto("activo = 1", "", " id ASC");
//        listProducto = service.getObjects();
//
//        if (!service.getOk()) {
//            MessageBox.createError()
//                    .withCaption("Error al cargar la informacion!")
//                    .withMessage("Err: " + service.getNotification())
//                    .withRetryButton()
//                    .open();
//        }
//        return listProducto;
//    }
//    
//    public List getProducto(Integer id) {
//        ProductoDomain service = new ProductoDomain();
//        service.getProducto("id = " + id, "", " id ASC");
//        listProducto = service.getObjects();
//
//        if (!service.getOk()) {
//            MessageBox.createError()
//                    .withCaption("Error al cargar la informacion!")
//                    .withMessage("Err: " + service.getNotification())
//                    .withRetryButton()
//                    .open();
//        }
//        return listProducto;
//    }
    
}