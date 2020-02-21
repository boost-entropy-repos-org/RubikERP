/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rubik.erp.modulo.compras;

import com.rubik.erp.domain.RemisionDetDomain;
import com.rubik.erp.model.Empleado;
import com.rubik.erp.model.Remision;
import com.rubik.erp.model.RemisionDet;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import de.steinwedel.messagebox.MessageBox;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author GRUCAS
 */
public class WindowRemisionDetInfo extends Window {
    
    Empleado empleado = (Empleado) VaadinSession.getCurrent().getSession().getAttribute("USUARIO_ACTIVO");
    
    VerticalLayout cont = new VerticalLayout();
    String title_window = "Remisiones de Compra";

    Remision remision;
    
    Grid<RemisionDet> gridRemisionDet = new Grid<>();
    List<RemisionDet> listRemision = new ArrayList<>();
    Label lblFolio;

    public WindowRemisionDetInfo(Remision remision) {
        lblFolio = new Label("REMISION " + remision.getFolio()) {
            {
                setStyleName("h2");
            }
        };
        initComponents();
    }

    public void initComponents() {
        setWidth("70%");
        setHeight("60%");
        
        gridRemisionDet.setSizeFull();
        gridRemisionDet.setSelectionMode(Grid.SelectionMode.SINGLE);

        gridRemisionDet.addColumn(RemisionDet::getCantidad).setCaption("CTD").setWidth(140);
        gridRemisionDet.addColumn(RemisionDet::getMarca).setCaption("MARCA").setWidth(200);
        gridRemisionDet.addColumn(RemisionDet::getModelo).setCaption("MODELO");
        gridRemisionDet.addColumn(RemisionDet::getDescripcion).setCaption("DESCRIPCION").setWidth(130);
        gridRemisionDet.setItems(getRemisiones());

        cont.setSpacing(false);
        cont.addComponents(lblFolio,
                gridRemisionDet);

        cont.setComponentAlignment(cont.getComponent(0), Alignment.MIDDLE_CENTER);
        cont.setComponentAlignment(cont.getComponent(1), Alignment.MIDDLE_CENTER);
        
        setContent(cont);
        setModal(true);
        setResizable(false);
        setClosable(false);
    }
    
    public List getRemisiones() {
        RemisionDetDomain service = new RemisionDetDomain();
        service.getRemisionDet(" documento_id = " + remision.getId(), "", " no_partida DESC");
        listRemision = service.getObjects();

        if (!service.getOk()) {
            MessageBox.createError()
                    .withCaption("Error al cargar la informacion!")
                    .withMessage("Err: " + service.getNotification())
                    .withRetryButton()
                    .open();
        }
        
        return listRemision;
    }
    
}