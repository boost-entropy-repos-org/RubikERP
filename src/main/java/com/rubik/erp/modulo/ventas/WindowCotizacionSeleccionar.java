/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rubik.erp.modulo.ventas;

import com.rubik.erp.domain.CotizacionVentaDetDomain;
import com.rubik.erp.domain.CotizacionVentaDomain;
import com.rubik.erp.model.CotizacionVenta;
import com.rubik.erp.model.CotizacionVentaDet;
import com.rubik.erp.model.Empleado;
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
public class WindowCotizacionSeleccionar extends Window {
    
    Empleado empleado = (Empleado) VaadinSession.getCurrent().getSession().getAttribute("USUARIO_ACTIVO");
    
    VerticalLayout cont = new VerticalLayout();
    String title_window = "Cotizacion de Venta";

    public CotizacionVenta cotizacion_selected;
    public Boolean seleccionado = false;

    Button btnAceptar = new Button("Seleccionar", Fam3SilkIcon.ACCEPT);
    Button btnCancelar = new Button("Cancelar", Fam3SilkIcon.CANCEL);
    
    Grid<CotizacionVenta> gridCotizacion = new Grid<>();
    List<CotizacionVenta> listCotizacion = new ArrayList<>();
    
    Grid<CotizacionVentaDet> gridCotizacionVentaDet = new Grid<>();
    List<CotizacionVentaDet> listCotizacionVentaDet = new ArrayList<>();
    public List<CotizacionVentaDet> listPartidasSeleccionadas = new ArrayList<>();
    
    Label lblFolio;

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    
    public WindowCotizacionSeleccionar() {
        lblFolio = new Label("SELECCIONAR PARTIDAS DE COTIZACION") {
            {
                setStyleName("h2");
            }
        };
        initComponents();
    }

    public void initComponents() {
        setSizeFull();
        
        gridCotizacion.setSizeFull();
        gridCotizacion.setCaption("Cotizaciones:");
        gridCotizacion.setSelectionMode(Grid.SelectionMode.SINGLE);

        Grid.Column<CotizacionVenta, String> columnFecha = gridCotizacion.addColumn(det -> ((det.getFecha_elaboracion()!= null) ? dateFormat.format(det.getFecha_elaboracion()) : ""));
        columnFecha.setCaption("FECHA");
        columnFecha.setId("FECHA");
        columnFecha.setWidth(120);
        gridCotizacion.addColumn(CotizacionVenta::getFolio).setCaption("FOLIO").setWidth(120);
//        gridCotizacion.addColumn(CotizacionVenta::getCliente).setCaption("CLIENTE");
        gridCotizacion.addColumn(CotizacionVenta::getVendedor).setCaption("VENDEDOR");
        gridCotizacion.setItems(getCotizaciones());
        
        gridCotizacion.addItemClickListener((event) -> {
            gridCotizacionVentaDet.setItems(getCotizacionesDet(event.getItem()));
        });

        gridCotizacionVentaDet.setCaption("Partidas:");
        gridCotizacionVentaDet.setSizeFull();
        gridCotizacionVentaDet.addColumn(CotizacionVentaDet::getCantidad).setCaption("CTD").setWidth(70);
        gridCotizacionVentaDet.addColumn(CotizacionVentaDet::getDescripcion).setCaption("DESCRIPCION");
        gridCotizacionVentaDet.setSelectionMode(Grid.SelectionMode.MULTI);
        
        btnCancelar.addClickListener((event) -> {
            close();
        });

        btnAceptar.addClickListener((event) -> {
            if (gridCotizacionVentaDet.getSelectedItems().size() >= 1) {
                
                seleccionado = true;
                cotizacion_selected = gridCotizacion.getSelectedItems().iterator().next();
                
                gridCotizacionVentaDet.getSelectedItems().forEach((t) -> {
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
                new HorizontalLayout(gridCotizacion,gridCotizacionVentaDet){{setSpacing(true); setMargin(true);}});

        cont.setComponentAlignment(cont.getComponent(0), Alignment.MIDDLE_CENTER);
        cont.setComponentAlignment(cont.getComponent(1), Alignment.MIDDLE_CENTER);
        cont.setComponentAlignment(cont.getComponent(2), Alignment.MIDDLE_CENTER);
        
        setContent(cont);
        setModal(true);
        setResizable(false);
        setClosable(false);
    }
    
    public List getCotizaciones() {
        CotizacionVentaDomain service = new CotizacionVentaDomain();
        service.getCotizacionVenta(" activo = 1 AND estado_doc = 'TERMINADO' ", "", " folio DESC");
        listCotizacion = service.getObjects();

        if (!service.getOk()) {
            MessageBox.createError()
                    .withCaption("Error al cargar la informacion!")
                    .withMessage("Err: " + service.getNotification())
                    .withRetryButton()
                    .open();
        }
        
        return listCotizacion;
    }
    
    public List getCotizacionesDet(CotizacionVenta cot) {
        CotizacionVentaDetDomain service = new CotizacionVentaDetDomain();
        service.getCotizacionVentaDet(" documento_id = " + cot.getId() + " AND facturada = 0 ", "", " id DESC");
        listCotizacionVentaDet = service.getObjects();

        if (!service.getOk()) {
            MessageBox.createError()
                    .withCaption("Error al cargar la informacion!")
                    .withMessage("Err: " + service.getNotification())
                    .withRetryButton()
                    .open();
        }
        
        return listCotizacionVentaDet;
    }
    
}