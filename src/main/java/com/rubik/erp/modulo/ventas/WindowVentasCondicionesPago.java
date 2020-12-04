/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rubik.erp.modulo.ventas;

import com.rubik.erp.domain.CondicionesPagoDomain;
import com.rubik.erp.model.CondicionesPago;
import com.rubik.erp.model.Empleado;
import com.vaadin.data.Binder;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import de.steinwedel.messagebox.MessageBox;
import org.rubicone.vaadin.fam3.silk.Fam3SilkIcon;

/**
 *
 * @author GRUCAS
 */
public class WindowVentasCondicionesPago extends Window {
    
    Empleado empleado = (Empleado) VaadinSession.getCurrent().getSession().getAttribute("USUARIO_ACTIVO");
    
    VerticalLayout cont = new VerticalLayout();

    CondicionesPago condPago = new CondicionesPago();
    Boolean isEdit = false;
    
    TextField txtNombre = new TextField("Nombre:");
    
    Binder<CondicionesPago> binder = new Binder<>();

    public WindowVentasCondicionesPago() {
        setCaption("ALTA DE CONDICIONES DE PAGO");
        initComponents();
    }

    public WindowVentasCondicionesPago(CondicionesPago cond) {
        setCaption("MODIFICACION DE CONDICIONES DE PAGO");
        isEdit = true;
        this.condPago = cond;
        initComponents();
        
        if (isEdit) {
            binder.readBean(condPago);
        }
    }

    public void initComponents() {
        setContent(cont);
        setResizable(false);
        
        String strWidth = "400";
        
        setWidth("520");
        setHeight("280");
        
        Button btnGuardar = new Button("Guardar", Fam3SilkIcon.DISK);
        Button btnCancelar = new Button("Cancelar", Fam3SilkIcon.CANCEL);

        binder.forField(txtNombre).bind(CondicionesPago::getCondicion_pago, CondicionesPago::setCondicion_pago);

        btnCancelar.addClickListener((event) -> {
            close();
        });

        btnGuardar.addClickListener((event) -> {
            try {
                binder.writeBean(condPago);
                toUpperCase();

                CondicionesPagoDomain service = new CondicionesPagoDomain();

                if (isEdit) {
                    service.CondicionesPagoUpdate(condPago);
                } else {
                    service.CondicionesPagoInsert(condPago);
                }

                if (service.getOk()) {
                    MessageBox.createInfo()
                            .withCaption("Atencion")
                            .withMessage(service.getNotification())
                            .open();
                    close();
                } else {
                    MessageBox.createError()
                            .withCaption("Error!")
                            .withMessage(service.getNotification())
                            .withRetryButton()
                            .open();
                }
            } catch (Exception ex) {
                MessageBox.createError()
                        .withCaption("Error!")
                        .withMessage("Verifique que la informacion este completa o sea correcta. ")
                        .withRetryButton()
                        .open();
            }
        });

        txtNombre.setMaxLength(250);
        txtNombre.setWidth(strWidth);
        
        FormLayout fLay = new FormLayout();
        
        fLay.addComponents(txtNombre);
     
        cont.addComponents(fLay, new HorizontalLayout(btnCancelar, btnGuardar));
        cont.setComponentAlignment(cont.getComponent(1), Alignment.MIDDLE_CENTER);
    }
    
    public void toUpperCase() {
        condPago.setCondicion_pago(txtNombre.getValue().toUpperCase());
    }

}