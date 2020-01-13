/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rubik.erp.window;

import com.rubik.erp.domain.ProveedorDomain;
import com.rubik.erp.model.Empleado;
import com.rubik.erp.model.Proveedor;
import com.vaadin.data.Binder;
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
public class WindowComprasProveedor extends Window {
    
    Empleado empleado = (Empleado) VaadinSession.getCurrent().getSession().getAttribute("USUARIO_ACTIVO");
    
    VerticalLayout cont = new VerticalLayout();
    String title_window = "CATALOGOS DE PROVEEDORES";

    public Proveedor proveedor = new Proveedor();
    public Boolean isEdit = false;

    TextField txtClaveProveedor = new TextField("Clave Proveedor:");
    TextField txtRazonSocial = new TextField("Razon Social:");
    TextField txtRfc = new TextField("RFC:");
    TextField txtDomicilio = new TextField("Domicilio:");
    TextField txtCiudad = new TextField("Ciudad:");
    TextField txtEstado = new TextField("Estado:");
    NativeSelect<String> cboPais = new NativeSelect("Pais:");
    TextField txtCp = new TextField("CP:");
    NativeSelect<String> cboClasificacion = new NativeSelect("Clasificacion:");
    NativeSelect<String>cboTipo = new NativeSelect("Tipo:");
    NativeSelect<Integer> cboDiasCredito = new NativeSelect("Dias de Credito:");
    TextField txtContactoCompraTelefono = new TextField("Telefono:");
    TextField txtContactoCompraNombre = new TextField("Nombre:");
    TextField txtContactoCompraEmail = new TextField("Email:");
    TextField txtContactoContaTelefono = new TextField("Telefono:");
    TextField txtContactoContaNombre = new TextField("Nombre:");
    TextField txtContactoContaEmail = new TextField("Email:");
    TextField txtNoCuenta_1 = new TextField("Numero de Cuenta:");
    TextField txtClabe_1 = new TextField("Clabe Interbancaria:");
    TextField txtBanco_1 = new TextField("Banco:");
    TextField txtSucursal_1 = new TextField("Sucursal:");
    TextField txtNoCuenta_2 = new TextField("Numero de Cuenta:");
    TextField txtClabe_2 = new TextField("Clabe Interbancaria:");
    TextField txtBanco_2 = new TextField("Banco:");
    TextField txtSucursal_2 = new TextField("Sucursal:");
    TextField txtNoCuenta_3 = new TextField("Numero de Cuenta:");
    TextField txtClabe_3 = new TextField("Clabe Interbancaria:");
    TextField txtBanco_3 = new TextField("Banco:");
    TextField txtSucursal_3 = new TextField("Sucursal:");
    CheckBox chkActivo = new CheckBox("Activo", true);

    public WindowComprasProveedor() {
        setCaption("ALTA DEL PROVEEDOR");
        initComponents();
        
        ProveedorDomain service = new ProveedorDomain();
        txtClaveProveedor.setValue(service.getMaxID().toString());
    }

    public WindowComprasProveedor(Proveedor prov) {
        setCaption("MODIFICACION DEL PROVEEDOR");
        isEdit = true;
        this.proveedor = prov;
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
    
        Binder<Proveedor> binder = new Binder<>();
        binder.forField(txtClaveProveedor).bind(Proveedor::getClave_proveedor, Proveedor::setClave_proveedor);
        binder.forField(txtRazonSocial).bind(Proveedor::getRazon_social, Proveedor::setRazon_social);
        binder.forField(txtRfc).bind(Proveedor::getRfc, Proveedor::setRfc);
        binder.forField(txtDomicilio).bind(Proveedor::getDomicilio, Proveedor::setDomicilio);
        binder.forField(txtCiudad).bind(Proveedor::getCiudad, Proveedor::setCiudad);
        binder.forField(txtEstado).bind(Proveedor::getEstado, Proveedor::setEstado);
        binder.forField(cboPais).bind(Proveedor::getPais, Proveedor::setPais);
        binder.forField(txtCp).bind(Proveedor::getCp, Proveedor::setCp);
        binder.forField(cboClasificacion).bind(Proveedor::getClasificacion_proveedor, Proveedor::setClasificacion_proveedor);
        binder.forField(cboTipo).bind(Proveedor::getTipo_proveedor, Proveedor::setTipo_proveedor);
        binder.forField(cboDiasCredito).bind(Proveedor::getDias_credito, Proveedor::setDias_credito);
        binder.forField(txtContactoCompraTelefono).bind(Proveedor::getContacto_compra_telefono, Proveedor::setContacto_compra_telefono);
        binder.forField(txtContactoCompraNombre).bind(Proveedor::getContacto_compra_nombre, Proveedor::setContacto_compra_nombre);
        binder.forField(txtContactoCompraEmail).bind(Proveedor::getContacto_compra_email, Proveedor::setContacto_compra_email);
        binder.forField(txtContactoContaTelefono).bind(Proveedor::getContacto_contabilidad_telefono, Proveedor::setContacto_contabilidad_telefono);
        binder.forField(txtContactoContaNombre).bind(Proveedor::getContacto_contabilidad_nombre, Proveedor::setContacto_contabilidad_nombre);
        binder.forField(txtContactoContaEmail).bind(Proveedor::getContacto_contabilidad_email, Proveedor::setContacto_contabilidad_email);
        binder.forField(txtNoCuenta_1).bind(Proveedor::getNo_cuenta_1, Proveedor::setNo_cuenta_1);
        binder.forField(txtClabe_1).bind(Proveedor::getClave_interbancaria_1, Proveedor::setClave_interbancaria_1);
        binder.forField(txtBanco_1).bind(Proveedor::getBanco_1, Proveedor::setBanco_1);
        binder.forField(txtSucursal_1).bind(Proveedor::getSucursal_1, Proveedor::setSucursal_1);
        binder.forField(txtNoCuenta_2).bind(Proveedor::getNo_cuenta_2, Proveedor::setNo_cuenta_2);
        binder.forField(txtClabe_2).bind(Proveedor::getClave_interbancaria_2, Proveedor::setClave_interbancaria_2);
        binder.forField(txtBanco_2).bind(Proveedor::getBanco_2, Proveedor::setBanco_2);
        binder.forField(txtSucursal_2).bind(Proveedor::getSucursal_2, Proveedor::setSucursal_2);
        binder.forField(txtNoCuenta_3).bind(Proveedor::getNo_cuenta_3, Proveedor::setNo_cuenta_3);
        binder.forField(txtClabe_3).bind(Proveedor::getClave_interbancaria_3, Proveedor::setClave_interbancaria_3);
        binder.forField(txtBanco_3).bind(Proveedor::getBanco_3, Proveedor::setBanco_3);
        binder.forField(txtSucursal_3).bind(Proveedor::getSucursal_3, Proveedor::setSucursal_3);
        binder.forField(this.chkActivo).bind(Proveedor::getActivo, Proveedor::setActivo);

        if (isEdit) {
            binder.readBean(proveedor);
        }

        btnCancelar.addClickListener((event) -> {
            close();
        });

        btnGuardar.addClickListener((event) -> {
            try {
                binder.writeBean(proveedor);
                toUpperCase();

                ProveedorDomain service = new ProveedorDomain();

                proveedor.setUnidad_id(empleado.getUsuario_id());
                proveedor.setUnidad(empleado.getUnidad());
                proveedor.setUsuario_id(empleado.getId());
                proveedor.setUsuario(empleado.getNombre_completo());
                proveedor.setEmpresa(empleado.getEmpresa());
                proveedor.setEmpresa_id(empleado.getEmpresa_id());

                if (isEdit) {
                    proveedor.setFecha_modificacion(new Date());
                    service.ProveedorUpdate(proveedor);
                } else {
                    service.ProveedorInsert(proveedor);
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

        txtClaveProveedor.setEnabled(false);
        txtRazonSocial.setMaxLength(250);
        txtRfc.setMaxLength(13);
        txtDomicilio.setMaxLength(100);
        txtCiudad.setMaxLength(50);
        txtEstado.setMaxLength(50);
        
        cboPais.setEmptySelectionAllowed(false);
        cboPais.setItems("MEXICO", "ESTADOS UNIDOS");
        cboPais.setValue("MEXICO");
       
        txtCp.setMaxLength(5);
        
        cboClasificacion.setEmptySelectionAllowed(false);
        cboClasificacion.setItems("PROVEEDOR", "ACREEDOR");
        cboClasificacion.setValue("PROVEEDOR");

        cboTipo.setEmptySelectionAllowed(false);
        cboTipo.setItems("CONTADO", "CREEDITO");
        cboTipo.setValue("CONTADO");

        cboDiasCredito.setEmptySelectionAllowed(false);
        cboDiasCredito.setItems(0, 15, 30, 60, 90);
        cboDiasCredito.setValue(0);

        txtContactoCompraNombre.setMaxLength(70);
        txtContactoCompraTelefono.setMaxLength(20);
        txtContactoCompraEmail.setMaxLength(50);
        txtContactoContaNombre.setMaxLength(70);
        txtContactoContaTelefono.setMaxLength(20);
        txtContactoContaEmail.setMaxLength(50);
        
        txtNoCuenta_1.setMaxLength(25);
        txtClabe_1.setMaxLength(25);
        txtBanco_1.setMaxLength(25);
        txtSucursal_1.setMaxLength(25);
        txtNoCuenta_2.setMaxLength(25);
        txtClabe_2.setMaxLength(25);
        txtBanco_2.setMaxLength(25);
        txtSucursal_2.setMaxLength(25);
        txtNoCuenta_3.setMaxLength(25);
        txtClabe_3.setMaxLength(25);
        txtBanco_3.setMaxLength(25);
        txtSucursal_3.setMaxLength(25);
        
        txtClaveProveedor.setWidth(strWidth);
        txtRazonSocial.setWidth(strWidth);
        txtRfc.setWidth(strWidth);
        txtDomicilio.setWidth(strWidth);
        txtCiudad.setWidth(strWidth);
        txtEstado.setWidth(strWidth);
        cboPais.setWidth(strWidth);
        txtCp.setWidth(strWidth);
        cboClasificacion.setWidth(strWidth);
        cboTipo.setWidth(strWidth);
        cboDiasCredito.setWidth(strWidth);
        txtContactoCompraTelefono.setWidth(strWidth);
        txtContactoCompraNombre.setWidth(strWidth);
        txtContactoCompraEmail.setWidth(strWidth);
        txtContactoContaTelefono.setWidth(strWidth);
        txtContactoContaNombre.setWidth(strWidth);
        txtContactoContaEmail.setWidth(strWidth);
        txtNoCuenta_1.setWidth(strWidth);
        txtClabe_1.setWidth(strWidth);
        txtBanco_1.setWidth(strWidth);
        txtSucursal_1.setWidth(strWidth);
        txtNoCuenta_2.setWidth(strWidth);
        txtClabe_2.setWidth(strWidth);
        txtBanco_2.setWidth(strWidth);
        txtSucursal_2.setWidth(strWidth);
        txtNoCuenta_3.setWidth(strWidth);
        txtClabe_3.setWidth(strWidth);
        txtBanco_3.setWidth(strWidth);
        txtSucursal_3.setWidth(strWidth);
        
        FormLayout fLay = new FormLayout();
        
        fLay.addComponents(new Label("Informacion Proveedor"));
        fLay.addComponents(txtClaveProveedor, chkActivo, txtRazonSocial, txtRfc, txtDomicilio, txtCiudad, txtEstado, cboPais, txtCp);
        fLay.addComponents(new Label("Credito"));
        fLay.addComponents(cboClasificacion, cboTipo, cboDiasCredito);
        fLay.addComponents(new Label("Contactos Compra"));
        fLay.addComponents(txtContactoCompraNombre, txtContactoCompraEmail, txtContactoCompraTelefono);
        fLay.addComponents(new Label("Contactos Contabilidad"));
        fLay.addComponents(txtContactoContaNombre, txtContactoContaEmail, txtContactoContaTelefono);
        fLay.addComponents(new Label("Cuenta Bancaria 1"));
        fLay.addComponents(txtNoCuenta_1, txtClabe_1, txtBanco_1,txtSucursal_1);
        fLay.addComponents(new Label("Cuenta Bancaria 2"));
        fLay.addComponents(txtNoCuenta_2, txtClabe_2, txtBanco_2,txtSucursal_2);
        fLay.addComponents(new Label("Cuenta Bancaria 3"));
        fLay.addComponents(txtNoCuenta_3, txtClabe_3, txtBanco_3,txtSucursal_3);
     
        cont.addComponents(fLay, new HorizontalLayout(btnCancelar, btnGuardar));
        cont.setComponentAlignment(cont.getComponent(1), Alignment.MIDDLE_CENTER);
    }
    
    public void toUpperCase() {
        proveedor.setRazon_social(txtRazonSocial.getValue().toUpperCase());
        proveedor.setDomicilio(txtDomicilio.getValue().toUpperCase());
        proveedor.setRfc(txtRfc.getValue().toUpperCase());
        proveedor.setEstado(txtEstado.getValue().toUpperCase());
        proveedor.setCiudad(txtCiudad.getValue().toUpperCase());
    }
    
}