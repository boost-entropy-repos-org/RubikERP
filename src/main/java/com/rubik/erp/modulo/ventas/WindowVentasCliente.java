/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rubik.erp.modulo.ventas;

import com.rubik.erp.domain.ClienteDomain;
import com.rubik.erp.model.Cliente;
import com.rubik.erp.model.Empleado;
import com.vaadin.data.Binder;
import com.vaadin.data.converter.StringToDoubleConverter;
import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import de.steinwedel.messagebox.MessageBox;
import java.util.Date;
import org.rubicone.vaadin.fam3.silk.Fam3SilkIcon;

/**
 *
 * @author Dev
 */
public class WindowVentasCliente extends Window {
    
    Empleado empleado = (Empleado) VaadinSession.getCurrent().getSession().getAttribute("USUARIO_ACTIVO");
    
    VerticalLayout cont = new VerticalLayout();
    String title_window = "CATALOGOS DE CLIENTES";

    public Cliente cliente = new Cliente();
    public Boolean isEdit = false;
    
    TextField txtClaveCliente = new TextField("Clave Cliente:");
    TextField txtRazonSocial = new TextField("Razon Social:");
    TextField txtRfc = new TextField("RFC:");
    TextField txtDomicilio = new TextField("Domicilio:");
    TextField txtCiudad = new TextField("Ciudad:");
    TextField txtEstado = new TextField("Estado:");
    NativeSelect<String> cboPais = new NativeSelect("Pais:");
    TextField txtCp = new TextField("CP:");
    NativeSelect<String> cboTipo = new NativeSelect("Tipo:");
    NativeSelect<Integer> cboDiasCredito = new NativeSelect("Dias de Credito:");
    TextField txtLimiteCredito = new TextField("Limite de Credito:");
    TextField txtContactoGeneralNombre = new TextField("Nombre:");
    TextField txtContactoGeneralTelefono = new TextField("Telefono:");
    TextField txtContactoGeneralEmail = new TextField("Email:");
    TextField txtContactoVentaNombre = new TextField("Nombre:");
    TextField txtContactoVentaTelefono = new TextField("Telefono:");
    TextField txtContactoVentaEmail = new TextField("Email:");
    TextField txtContactoContaNombre = new TextField("Nombre:");
    TextField txtContactoContaTelefono = new TextField("Telefono:");
    TextField txtContactoContaEmail = new TextField("Email:");
    TextField txtCuentaClientes = new TextField("Cuenta Cliente:");
    TextField txtCuentaPagos = new TextField("Cuenta Pagos:");
    TextField txtCuentaAnticipos = new TextField("Cuenta Anticipos:");
    TextField txtCuentaHonorarios = new TextField("Cuenta Honorarios:");
    CheckBox chkActivo = new CheckBox("Activo", true);

    public WindowVentasCliente() {
        setCaption("ALTA DEL CLIENTE");
        initComponents();
        
        ClienteDomain service = new ClienteDomain();
        txtClaveCliente.setValue(service.getMaxID().toString());
    }

    public WindowVentasCliente(Cliente cliente) {
        setCaption("MODIFICACION DEL CLIENTE");
        isEdit = true;
        this.cliente = cliente;
        initComponents();
    }

    public void initComponents() {
        setContent(cont);
        setResizable(false);
        
        String strWidth = "400";
        
        setWidth("600");
        setHeight("80%");
        
        Button btnGuardar = new Button("Guardar", Fam3SilkIcon.DISK);
        Button btnCancelar = new Button("Cancelar", Fam3SilkIcon.CANCEL);

        Binder<Cliente> binder = new Binder<>();
        binder.forField(txtClaveCliente).bind(Cliente::getClave_cliente, Cliente::setClave_cliente);
        binder.forField(txtRazonSocial).bind(Cliente::getRazon_social, Cliente::setRazon_social);
        binder.forField(txtRfc).bind(Cliente::getRfc, Cliente::setRfc);
        binder.forField(txtDomicilio).bind(Cliente::getDomicilio, Cliente::setDomicilio);
        binder.forField(txtCiudad).bind(Cliente::getCiudad, Cliente::setCiudad);
        binder.forField(txtEstado).bind(Cliente::getEstado, Cliente::setEstado);
        binder.forField(cboPais).bind(Cliente::getPais, Cliente::setPais);
        binder.forField(txtCp).bind(Cliente::getCp, Cliente::setCp);
        binder.forField(cboTipo).bind(Cliente::getTipo_cliente, Cliente::setTipo_cliente);
        binder.forField(cboDiasCredito).bind(Cliente::getDias_credito, Cliente::setDias_credito);
        binder.forField(txtLimiteCredito).withConverter(new StringToDoubleConverter(0.0, "El valor debe ser numerico")).bind(Cliente::getLimite_credito, Cliente::setLimite_credito);
        binder.forField(txtContactoGeneralNombre).bind(Cliente::getContacto_general_nombre, Cliente::setContacto_general_nombre);
        binder.forField(txtContactoGeneralTelefono).bind(Cliente::getContacto_telefono_general, Cliente::setContacto_telefono_general);
        binder.forField(txtContactoGeneralEmail).bind(Cliente::getContacto_general_email, Cliente::setContacto_general_email);
        binder.forField(txtContactoVentaNombre).bind(Cliente::getContacto_venta_nombre, Cliente::setContacto_venta_nombre);
        binder.forField(txtContactoVentaTelefono).bind(Cliente::getContacto_venta_telefono, Cliente::setContacto_venta_telefono);
        binder.forField(txtContactoVentaEmail).bind(Cliente::getContacto_venta_email, Cliente::setContacto_venta_email);
        binder.forField(txtContactoContaNombre).bind(Cliente::getContacto_contabilidad_nombre, Cliente::setContacto_contabilidad_nombre);
        binder.forField(txtContactoContaTelefono).bind(Cliente::getContacto_contabilidad_telefono, Cliente::setContacto_contabilidad_telefono);
        binder.forField(txtContactoContaEmail).bind(Cliente::getContacto_contabilidad_email, Cliente::setContacto_contabilidad_email);
        binder.forField(txtCuentaClientes).bind(Cliente::getCuenta_clientes, Cliente::setCuenta_clientes);
        binder.forField(txtCuentaPagos).bind(Cliente::getCuenta_pagos, Cliente::setCuenta_pagos);
        binder.forField(txtCuentaAnticipos).bind(Cliente::getCuenta_anticipos, Cliente::setCuenta_anticipos);
        binder.forField(txtCuentaHonorarios).bind(Cliente::getCuenta_honorarios, Cliente::setCuenta_honorarios);
        binder.forField(this.chkActivo).bind(Cliente::getActivo, Cliente::setActivo);

        if (isEdit) {
            binder.readBean(cliente);
        }

        btnCancelar.addClickListener((event) -> {
            close();
        });

        btnGuardar.addClickListener((event) -> {
            try {
                binder.writeBean(cliente);
                toUpperCase();

                ClienteDomain service = new ClienteDomain();

                cliente.setUnidad_id(empleado.getUsuario_id());
                cliente.setUnidad(empleado.getUnidad());
                cliente.setUsuario_id(empleado.getId());
                cliente.setUsuario(empleado.getNombre_completo());
                cliente.setEmpresa(empleado.getEmpresa());
                cliente.setEmpresa_id(empleado.getEmpresa_id());

                if (isEdit) {
                    cliente.setFecha_modificacion(new Date());
                    service.ClienteUpdate(cliente);
                } else {
                    service.ClienteInsert(cliente);
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

        txtClaveCliente.setEnabled(false);
        txtRazonSocial.setMaxLength(250);
        txtRfc.setMaxLength(13);
        txtDomicilio.setMaxLength(100);
        txtCiudad.setMaxLength(50);
        txtEstado.setMaxLength(50);
        
        cboPais.setEmptySelectionAllowed(false);
        cboPais.setItems("MEXICO", "ESTADOS UNIDOS");
        cboPais.setValue("MEXICO");
       
        txtCp.setMaxLength(5);

        cboTipo.setEmptySelectionAllowed(false);
        cboTipo.setItems("CONTADO", "CREDITO");
        cboTipo.setValue("CONTADO");

        cboDiasCredito.setEmptySelectionAllowed(false);
        cboDiasCredito.setItems(0, 15, 30, 60, 90);
        cboDiasCredito.setValue(0);

        txtContactoGeneralNombre.setMaxLength(70);
        txtContactoGeneralTelefono.setMaxLength(20);
        txtContactoGeneralEmail.setMaxLength(50);
        
        txtContactoVentaNombre.setMaxLength(70);
        txtContactoVentaTelefono.setMaxLength(20);
        txtContactoVentaEmail.setMaxLength(50);
        
        txtContactoContaNombre.setMaxLength(70);
        txtContactoContaTelefono.setMaxLength(20);
        txtContactoContaEmail.setMaxLength(50);
        
        txtCuentaClientes.setMaxLength(20);
        txtCuentaPagos.setMaxLength(20);
        txtCuentaAnticipos.setMaxLength(20);
        txtCuentaHonorarios.setMaxLength(20);
        
        txtClaveCliente.setWidth(strWidth);
        txtRazonSocial.setWidth(strWidth);
        txtRfc.setWidth(strWidth);
        txtDomicilio.setWidth(strWidth);
        txtCiudad.setWidth(strWidth);
        txtEstado.setWidth(strWidth);
        cboPais.setWidth(strWidth);
        txtCp.setWidth(strWidth);
        cboTipo.setWidth(strWidth);
        cboDiasCredito.setWidth(strWidth);
        txtContactoGeneralNombre.setWidth(strWidth);
        txtContactoGeneralTelefono.setWidth(strWidth);
        txtContactoGeneralEmail.setWidth(strWidth);
        txtContactoVentaNombre.setWidth(strWidth);
        txtContactoVentaTelefono.setWidth(strWidth);
        txtContactoVentaEmail.setWidth(strWidth);
        txtContactoContaTelefono.setWidth(strWidth);
        txtContactoContaNombre.setWidth(strWidth);
        txtContactoContaEmail.setWidth(strWidth);
        txtCuentaClientes.setWidth(strWidth);
        txtCuentaPagos.setWidth(strWidth);
        txtCuentaAnticipos.setWidth(strWidth);
        txtCuentaHonorarios.setWidth(strWidth);
        
        FormLayout fLay = new FormLayout();
        
        fLay.addComponents(new Label("Informacion Cliente"){{setStyleName("h3");}});
        fLay.addComponents(txtClaveCliente, chkActivo, txtRazonSocial, txtRfc, txtDomicilio, txtCiudad, txtEstado, cboPais, txtCp);
        fLay.addComponents(new Label("Credito"){{setStyleName("h3");}});
        fLay.addComponents(cboTipo, cboDiasCredito, txtLimiteCredito);
        fLay.addComponents(new Label("Contacto General"){{setStyleName("h3");}});
        fLay.addComponents(txtContactoGeneralNombre, txtContactoGeneralTelefono, txtContactoGeneralEmail);
        fLay.addComponents(new Label("Contacto de Venta"){{setStyleName("h3");}});
        fLay.addComponents(txtContactoVentaNombre, txtContactoVentaTelefono, txtContactoVentaEmail);
        fLay.addComponents(new Label("Contacto de Contabilidad"){{setStyleName("h3");}});
        fLay.addComponents(txtContactoContaNombre, txtContactoContaEmail, txtContactoContaTelefono);
        fLay.addComponents(new Label("Cuentas Contables"){{setStyleName("h3");}});
        fLay.addComponents(txtCuentaClientes, txtCuentaPagos, txtCuentaAnticipos,txtCuentaHonorarios);
     
        cont.addComponents(fLay, new HorizontalLayout(btnCancelar, btnGuardar));
        cont.setComponentAlignment(cont.getComponent(1), Alignment.MIDDLE_CENTER);
    }
    
    public void toUpperCase() {
        cliente.setRazon_social(txtRazonSocial.getValue().toUpperCase());
        cliente.setDomicilio(txtDomicilio.getValue().toUpperCase());
        cliente.setRfc(txtRfc.getValue().toUpperCase());
        cliente.setEstado(txtEstado.getValue().toUpperCase());
        cliente.setCiudad(txtCiudad.getValue().toUpperCase());
    }
    
}