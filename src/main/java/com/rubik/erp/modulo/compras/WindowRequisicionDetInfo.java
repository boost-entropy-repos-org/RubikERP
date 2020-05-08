/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rubik.erp.modulo.compras;

import com.rubik.erp.domain.RequisicionDetDomain;
import com.rubik.erp.model.Empleado;
import com.rubik.erp.model.Requisicion;
import com.rubik.erp.model.RequisicionDet;
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
public class WindowRequisicionDetInfo extends Window {
    
    Empleado empleado = (Empleado) VaadinSession.getCurrent().getSession().getAttribute("USUARIO_ACTIVO");
    
    VerticalLayout cont = new VerticalLayout();
    String title_window = "Requisiciones de Compra";

    Requisicion requisicion;
    
    Grid<RequisicionDet> gridRequisicionDet = new Grid<>();
    List<RequisicionDet> listRequisicion = new ArrayList<>();
    Label lblFolio;

    public WindowRequisicionDetInfo(Requisicion requisicion) {
        this.requisicion = requisicion;
        lblFolio = new Label("REQUISICION " + this.requisicion.getFolio()) {
            {
                setStyleName("h2");
            }
        };
        initComponents();
    }

    public void initComponents() {
        setWidth("70%");
        setHeight("60%");
        
        gridRequisicionDet.setSizeFull();
        gridRequisicionDet.setSelectionMode(Grid.SelectionMode.SINGLE);

        gridRequisicionDet.addColumn(RequisicionDet::getCantidad).setCaption("CTD").setWidth(70);
        gridRequisicionDet.addColumn(RequisicionDet::getMarca).setCaption("MARCA").setWidth(160);
        gridRequisicionDet.addColumn(RequisicionDet::getModelo).setCaption("MODELO").setWidth(160);
        gridRequisicionDet.addColumn(RequisicionDet::getDescripcion).setCaption("DESCRIPCION");
        gridRequisicionDet.setItems(getRequisiciones());

        cont.setSpacing(false);
        cont.addComponents(lblFolio,gridRequisicionDet);

        cont.setComponentAlignment(cont.getComponent(0), Alignment.MIDDLE_CENTER);
        cont.setComponentAlignment(cont.getComponent(1), Alignment.MIDDLE_CENTER);
        
        setContent(cont);
        setModal(true);
        setResizable(false);
        setClosable(true);
    }
    
    public List getRequisiciones() {
        RequisicionDetDomain service = new RequisicionDetDomain();
        service.getRequisicionDet(" documento_id = " + requisicion.getId(), "", " id DESC");
        listRequisicion = service.getObjects();

        if (!service.getOk()) {
            MessageBox.createError()
                    .withCaption("Error al cargar la informacion!")
                    .withMessage("Err: " + service.getNotification())
                    .withRetryButton()
                    .open();
        }
        
        return listRequisicion;
    }
    
}