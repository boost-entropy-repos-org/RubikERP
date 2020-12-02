/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rubik.erp.modulo.ventas;

import com.rubik.erp.domain.CondicionesPagoDomain;
import com.rubik.erp.model.CondicionesPago;
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
import java.util.ArrayList;
import java.util.List;
import org.rubicone.vaadin.fam3.silk.Fam3SilkIcon;

/**
 *
 * @author GRUCAS
 */
public class WindowVentasCondicionesPagoSeleccionar extends Window {

    VerticalLayout container = new VerticalLayout();
    Empleado empleado = (Empleado) VaadinSession.getCurrent().getSession().getAttribute("USUARIO_ACTIVO");
    
    Grid<CondicionesPago> gridCondiciones = new Grid<>();
    Button btnAdd = new Button(Fam3SilkIcon.ADD);
    Button btnModify = new Button(Fam3SilkIcon.PENCIL);
    List<CondicionesPago> listCondiciones = new ArrayList<>();

    public WindowVentasCondicionesPagoSeleccionar() {
        initComponents();

        HorizontalLayout hLayoutAux = new HorizontalLayout(btnAdd, btnModify);

        Label lblTitulo = new Label("CONDICIONES DE PAGO") {
            {
                setStyleName("h1");
            }
        };
        lblTitulo.setSizeUndefined();

        setContent(container);

        container.setMargin(false);
        container.addComponents(lblTitulo,
                hLayoutAux,
                gridCondiciones);
        
        container.setComponentAlignment(container.getComponent(0), Alignment.MIDDLE_CENTER);
        container.setComponentAlignment(container.getComponent(1), Alignment.MIDDLE_CENTER);
        container.setComponentAlignment(container.getComponent(2), Alignment.MIDDLE_CENTER);

        setContent(container);
    }

    public void initComponents() {
        setContent(container);
        setResizable(false);
        
        setWidth("600");
        setHeight("90%");  

        gridCondiciones.addColumn(CondicionesPago::getCondicion_pago).setCaption("NOMBRE").setId("NOMBRE");
        
        gridCondiciones.setItems(getCondiciones());
        gridCondiciones.setSelectionMode(Grid.SelectionMode.SINGLE);
        gridCondiciones.setSizeFull();
        gridCondiciones.setHeight("500px");

        btnAdd.addClickListener((event) -> {
            WindowVentasCondicionesPago windows = new WindowVentasCondicionesPago();
            windows.center();
            windows.setModal(true);
            windows.addCloseListener(ev -> {
                gridCondiciones.setItems(getCondiciones());
            });
            getUI().addWindow(windows);
        });

        btnModify.addClickListener((event) -> {
            if (gridCondiciones.getSelectedItems().size() == 1) {
                WindowVentasCondicionesPago windows = new WindowVentasCondicionesPago(gridCondiciones.getSelectedItems().iterator().next());
                windows.center();
                windows.setModal(true);
                windows.addCloseListener((e) -> {
                    gridCondiciones.setItems(getCondiciones());
                });
                getUI().addWindow(windows);
            } else {
                MessageBox.createError()
                        .withCaption("Error!")
                        .withMessage("Debe tener una Condicion de pago seleccionada para poder modificarlo.")
                        .withRetryButton()
                        .open();
            }
        });

    }

    public List getCondiciones() {
        CondicionesPagoDomain service = new CondicionesPagoDomain();
        service.getCondicionesPago("", "", "condicion_pago ASC");
        listCondiciones = service.getObjects();

        if (!service.getOk()) {
            MessageBox.createError()
                    .withCaption("Error al cargar la informacion!")
                    .withMessage("Err: " + service.getNotification())
                    .withRetryButton()
                    .open();
        }
        return listCondiciones;
    }
    
}