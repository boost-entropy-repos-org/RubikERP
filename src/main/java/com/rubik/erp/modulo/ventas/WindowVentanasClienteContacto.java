/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rubik.erp.modulo.ventas;

import com.rubik.erp.domain.ClienteContactoDomain;
import com.rubik.erp.model.Cliente;
import com.rubik.erp.model.ClienteContacto;
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
public class WindowVentanasClienteContacto extends Window {
    
    Empleado empleado = (Empleado) VaadinSession.getCurrent().getSession().getAttribute("USUARIO_ACTIVO");
    
    VerticalLayout cont = new VerticalLayout();

    Cliente cliente = new Cliente();
    ClienteContacto contacto = new ClienteContacto();
    Boolean isEdit = false;
    
    TextField txtNombre = new TextField("Nombre:");
    TextField txtEmail = new TextField("Email:");
    TextField txtTelefono = new TextField("Telefono:");

    public WindowVentanasClienteContacto(Cliente cliente) {
        setCaption("NUEVO CONTACTO");

        this.cliente = cliente;
        initComponents();
    }
    
    public WindowVentanasClienteContacto(Cliente cliente, ClienteContacto contacto) {
        setCaption("MODIFICAR CONTACTO");
        
        isEdit = true;
        
        this.contacto = contacto;
        this.cliente = cliente;
        initComponents();
    }

    public void initComponents() {
        setContent(cont);
        setResizable(false);
        
        String strWidth = "400";
        
        setWidth("550");
        setHeight("325");
        
        Button btnGuardar = new Button("Guardar", Fam3SilkIcon.DISK);
        Button btnCancelar = new Button("Cancelar", Fam3SilkIcon.CANCEL);

        Binder<ClienteContacto> binder = new Binder<>();
        binder.forField(txtNombre).bind(ClienteContacto::getNombre, ClienteContacto::setNombre);
        binder.forField(txtEmail).bind(ClienteContacto::getEmail, ClienteContacto::setEmail);
        binder.forField(txtTelefono).bind(ClienteContacto::getTelefono, ClienteContacto::setTelefono);

        if (isEdit) {
            binder.readBean(contacto);
        }

        btnCancelar.addClickListener((event) -> {
            close();
        });

        btnGuardar.addClickListener((event) -> {
            try {
                binder.writeBean(contacto);
                toUpperCase();

                ClienteContactoDomain service = new ClienteContactoDomain();

                contacto.setCliente_id(cliente.getId());

                if (isEdit) {
                    service.ClienteContactoUpdate(contacto);
                } else {
                    service.ClienteContactoInsert(contacto);
                }

                if (service.getOk()) {
                    MessageBox.createInfo()
                            .withCaption("Atencion")
                            .withMessage("Contacto guardado correctamente.")
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
        txtEmail.setMaxLength(150);
        txtTelefono.setMaxLength(100);
        
        txtNombre.setWidth(strWidth);
        txtEmail.setWidth(strWidth);
        txtTelefono.setWidth(strWidth);
        
        FormLayout fLay = new FormLayout();
        
        fLay.addComponents(txtNombre, txtEmail, txtTelefono);
     
        cont.addComponents(fLay, new HorizontalLayout(btnCancelar, btnGuardar));
        cont.setComponentAlignment(cont.getComponent(1), Alignment.MIDDLE_CENTER);
    }
    
    public void toUpperCase() {
        contacto.setNombre(txtNombre.getValue().toUpperCase().trim());
        contacto.setEmail(txtEmail.getValue().toUpperCase().trim());
        contacto.setTelefono(txtTelefono.getValue().toUpperCase().trim());
    }
    
}