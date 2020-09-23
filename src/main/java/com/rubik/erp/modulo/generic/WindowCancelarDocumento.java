/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rubik.erp.modulo.generic;

import com.rubik.erp.model.Empleado;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import org.rubicone.vaadin.fam3.silk.Fam3SilkIcon;

/**
 *
 * @author Dev
 */
public class WindowCancelarDocumento extends Window {

    Empleado empleado = (Empleado) VaadinSession.getCurrent().getSession().getAttribute("USUARIO_ACTIVO");

    VerticalLayout content = new VerticalLayout();

    TextArea txtRazonCan = new TextArea();
    Button btnAceptar = new Button("Guardar",Fam3SilkIcon.DISK);

    public static String RAZON_DE_CANCELAMIENTO = "";

    public WindowCancelarDocumento() {
        setCaption("Razon Cancelar");
        
        center();
        setModal(true);
        setClosable(false);
        setResizable(false);
        setContent(content);

        RAZON_DE_CANCELAMIENTO = "";

        txtRazonCan.setWidth("350px");
        txtRazonCan.setRows(4);

        content.addComponents(txtRazonCan,btnAceptar);
        content.setComponentAlignment(content.getComponent(1), Alignment.MIDDLE_CENTER);

        btnAceptar.addClickListener((event) -> {
            RAZON_DE_CANCELAMIENTO = txtRazonCan.getValue().toUpperCase();
            close();
        });
    }

}