/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rubik.erp.modulo.compras;

import com.rubik.erp.domain.RemisionDomain;
import com.rubik.erp.model.Empleado;
import com.rubik.erp.model.Remision;
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
public class WindowRemisionSeleccionar extends Window {
    
    Empleado empleado = (Empleado) VaadinSession.getCurrent().getSession().getAttribute("USUARIO_ACTIVO");
    
    VerticalLayout cont = new VerticalLayout();
    String title_window = "Remisiones de Compra";

    public Remision remision;
    public Boolean seleccionado = false;

    Button btnChecar = new Button("Seleccionar", Fam3SilkIcon.ACCEPT);
    Button btnCancelar = new Button("Cancelar", Fam3SilkIcon.CANCEL);
    
    Grid<Remision> gridRemision = new Grid<>();
    List<Remision> listRemision = new ArrayList<>();
    Label lblFolio;

    public WindowRemisionSeleccionar() {
        lblFolio = new Label("SELECCIONAR REMISION") {
            {
                setStyleName("h2");
            }
        };
        initComponents();
    }

    public void initComponents() {
        setWidth("70%");
        setHeight("60%");
        
        gridRemision.setSizeFull();
        gridRemision.setSelectionMode(Grid.SelectionMode.SINGLE);

        gridRemision.addColumn(Remision::getFolio).setCaption("FOLIO").setWidth(140);
        gridRemision.addColumn(Remision::getPrioridad).setCaption("PRIORIDAD").setWidth(130);
        gridRemision.addColumn(Remision::getSolicita).setCaption("SOLICITA").setWidth(200);
        gridRemision.addColumn(Remision::getObservaciones).setCaption("OBSERVACIONES");
        gridRemision.setItems(getRemisiones());
        
        btnCancelar.addClickListener((event) -> {
            close();
        });

        btnChecar.addClickListener((event) -> {
            if (gridRemision.getSelectedItems().size() == 1) {
                
                seleccionado = true;
                remision = gridRemision.getSelectedItems().iterator().next();
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
                gridRemision);

        cont.setComponentAlignment(cont.getComponent(0), Alignment.MIDDLE_CENTER);
        cont.setComponentAlignment(cont.getComponent(1), Alignment.MIDDLE_CENTER);
        cont.setComponentAlignment(cont.getComponent(2), Alignment.MIDDLE_CENTER);
        
        setContent(cont);
        setModal(true);
        setResizable(false);
        setClosable(false);
    }
    
    public List getRemisiones() {
        RemisionDomain service = new RemisionDomain();
        service.getRemision(" activo = 1 AND folio_orden_compra IS NULL and estado_doc = 'TERMINADO' ", "", " folio DESC");
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