/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rubik.erp.modulo.compras;

import com.rubik.erp.domain.OrdenDeCompraDetDomain;
import com.rubik.erp.domain.OrdenDeCompraDomain;
import com.rubik.erp.model.Empleado;
import com.rubik.erp.model.OrdenDeCompra;
import com.rubik.erp.model.OrdenDeCompraDet;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import de.steinwedel.messagebox.MessageBox;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.rubicone.vaadin.fam3.silk.Fam3SilkIcon;

/**
 *
 * @author Dev
 */
public class WindowOrdenDeCompraSeleccionar extends Window {
    
    Empleado empleado = (Empleado) VaadinSession.getCurrent().getSession().getAttribute("USUARIO_ACTIVO");
    
    VerticalLayout cont = new VerticalLayout();

    public OrdenDeCompra oc_selected;
    public Boolean seleccionado = false;

    Button btnAceptar = new Button("Seleccionar", Fam3SilkIcon.ACCEPT);
    Button btnCancelar = new Button("Cancelar", Fam3SilkIcon.CANCEL);
    
    Grid<OrdenDeCompra> gridOC = new Grid<>();
    List<OrdenDeCompra> listOC = new ArrayList<>();
    
    Grid<OrdenDeCompraDet> gridOCDet = new Grid<>();
    List<OrdenDeCompraDet> listOCDet = new ArrayList<>();
    public List<OrdenDeCompraDet> listPartidasSeleccionadas = new ArrayList<>();
    
    Label lblFolio;

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    
    public WindowOrdenDeCompraSeleccionar() {
        lblFolio = new Label("SELECCIONAR LAS PARTIDAS DE LA ORDEN DE COMPRA") {
            {
                setStyleName("h2");
            }
        };
        initComponents();
    }

    public void initComponents() {
        setSizeFull();
        
        gridOC.setSizeFull();
        gridOC.setCaption("Ordenes de Compra:");
        gridOC.setSelectionMode(Grid.SelectionMode.SINGLE);

        Grid.Column<OrdenDeCompra, String> columnFecha = gridOC.addColumn(det -> ((det.getFecha_elaboracion()!= null) ? dateFormat.format(det.getFecha_elaboracion()) : ""));
        columnFecha.setCaption("FECHA");
        columnFecha.setId("FECHA");
        columnFecha.setWidth(120);
        gridOC.addColumn(OrdenDeCompra::getFolio).setCaption("FOLIO").setWidth(120);
        gridOC.addColumn(OrdenDeCompra::getPedido).setCaption("PEDIDO");
        gridOC.addColumn(OrdenDeCompra::getProveedor).setCaption("PROVEEDOR");
        gridOC.setItems(getOrdenesDeCompra());
        
        gridOC.addItemClickListener((event) -> {
            gridOCDet.setItems(getOrdenesDeCompraDet(event.getItem()));
        });

        gridOCDet.setCaption("Partidas:");
        gridOCDet.setSizeFull();
        gridOCDet.addColumn(OrdenDeCompraDet::getCantidad).setCaption("CTD").setWidth(70);
        gridOCDet.addColumn(OrdenDeCompraDet::getDescripcion).setCaption("DESCRIPCION");
        gridOCDet.setSelectionMode(Grid.SelectionMode.MULTI);
        
        btnCancelar.addClickListener((event) -> {
            close();
        });

        btnAceptar.addClickListener((event) -> {
            if (gridOCDet.getSelectedItems().size() >= 1) {
                
                seleccionado = true;
                oc_selected = gridOC.getSelectedItems().iterator().next();
                
                gridOCDet.getSelectedItems().forEach((t) -> {
                    listPartidasSeleccionadas.add(t);
                });

                close();
                
            } else {
                MessageBox.createError()
                        .withCaption("Error!")
                        .withMessage("Debe tener una Partida seleccionada para poder añadirlo a la Requisicion.")
                        .withRetryButton()
                        .open();
            }
        });

        cont.setSpacing(false);
        cont.addComponents(lblFolio,
                new HorizontalLayout(btnCancelar, btnAceptar),
                new HorizontalLayout(gridOC,gridOCDet){{setSpacing(true); setMargin(true);}});

        cont.setComponentAlignment(cont.getComponent(0), Alignment.MIDDLE_CENTER);
        cont.setComponentAlignment(cont.getComponent(1), Alignment.MIDDLE_CENTER);
        cont.setComponentAlignment(cont.getComponent(2), Alignment.MIDDLE_CENTER);
        
        setContent(cont);
        setModal(true);
        setResizable(false);
        setClosable(false);
    }
    
    public List getOrdenesDeCompra() {
        OrdenDeCompraDomain service = new OrdenDeCompraDomain();
        service.getOrdenDeCompra(" activo = 1", "", " folio DESC");
        listOC = service.getObjects();

        if (!service.getOk()) {
            MessageBox.createError()
                    .withCaption("Error al cargar la informacion!")
                    .withMessage("Err: " + service.getNotification())
                    .withRetryButton()
                    .open();
        }
        
        return listOC;
    }
    
    public List getOrdenesDeCompraDet(OrdenDeCompra octemp) {
        OrdenDeCompraDetDomain service = new OrdenDeCompraDetDomain();
        service.getOrdenDeCompraDet(" documento_id = " + octemp.getId(), "", " id DESC");
        listOCDet = service.getObjects();

        if (!service.getOk()) {
            MessageBox.createError()
                    .withCaption("Error al cargar la informacion!")
                    .withMessage("Err: " + service.getNotification())
                    .withRetryButton()
                    .open();
        }
        
        return listOCDet;
    }
    
}