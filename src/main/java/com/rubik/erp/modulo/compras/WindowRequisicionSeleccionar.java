/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rubik.erp.modulo.compras;

import com.rubik.erp.domain.RequisicionDomain;
import com.rubik.erp.model.Empleado;
import com.rubik.erp.model.Requisicion;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import de.steinwedel.messagebox.MessageBox;
import java.util.ArrayList;
import java.util.List;
import org.rubicone.vaadin.fam3.silk.Fam3SilkIcon;

/**
 *
 * @author Dev
 */
public class WindowRequisicionSeleccionar extends Window {
    
    Empleado empleado = (Empleado) VaadinSession.getCurrent().getSession().getAttribute("USUARIO_ACTIVO");
    
    VerticalLayout cont = new VerticalLayout();
    String title_window = "Requisicion de Compra";

    public Requisicion requisicion_selected;
    public Boolean seleccionado = false;

    Button btnChecar = new Button("Seleccionar", Fam3SilkIcon.ACCEPT);
    Button btnCancelar = new Button("Cancelar", Fam3SilkIcon.CANCEL);
    
    Grid<Requisicion> gridRequisicion = new Grid<>();
    List<Requisicion> listRequisicion = new ArrayList<>();
    Label lblFolio;

    public WindowRequisicionSeleccionar() {
        lblFolio = new Label("SELECCIONAR REQUISICION") {
            {
                setStyleName("h2");
            }
        };
        initComponents();
    }

    public void initComponents() {
        setWidth("70%");
        setHeight("60%");
        
        gridRequisicion.setSizeFull();
        gridRequisicion.setSelectionMode(Grid.SelectionMode.SINGLE);

        gridRequisicion.addColumn(Requisicion::getFolio).setCaption("FOLIO").setWidth(140);
        gridRequisicion.addColumn(Requisicion::getPrioridad).setCaption("PRIORIDAD").setWidth(130);
        gridRequisicion.addColumn(Requisicion::getSolicita).setCaption("SOLICITA").setWidth(200);
        gridRequisicion.addColumn(Requisicion::getObservaciones).setCaption("OBSERVACIONES");
        gridRequisicion.setItems(getRequisiciones());
        
        btnCancelar.addClickListener((event) -> {
            close();
        });

        btnChecar.addClickListener((event) -> {
            if (gridRequisicion.getSelectedItems().size() == 1) {
                
                seleccionado = true;
                requisicion_selected = gridRequisicion.getSelectedItems().iterator().next();
                close();
                
            } else {
                MessageBox.createError()
                        .withCaption("Error!")
                        .withMessage("Debe tener un Proveedor seleccionado para poder modificarlo.")
                        .withRetryButton()
                        .open();
            }
        });

        cont.setSpacing(false);
        cont.addComponents(lblFolio,
                new HorizontalLayout(btnCancelar, btnChecar),
                gridRequisicion);

        cont.setComponentAlignment(cont.getComponent(0), Alignment.MIDDLE_CENTER);
        cont.setComponentAlignment(cont.getComponent(1), Alignment.MIDDLE_CENTER);
        cont.setComponentAlignment(cont.getComponent(2), Alignment.MIDDLE_CENTER);
        
        setContent(cont);
        setModal(true);
        setResizable(false);
        setClosable(false);
    }
    
    public List getRequisiciones() {
        RequisicionDomain service = new RequisicionDomain();
        service.getRequisicion(" activo = 1 AND estado_doc = 'AUTORIZADO' ", "", " folio DESC");
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