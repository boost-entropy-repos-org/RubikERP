/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rubik.erp.modulo.rh;

import com.rubik.erp.config._Departamentos;
import com.rubik.erp.config._Puestos;
import com.rubik.erp.domain.EmpleadoDomain;
import com.rubik.erp.model.Empleado;
import com.rubik.manage.ManageDates;
import com.vaadin.data.Binder;
import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import de.steinwedel.messagebox.MessageBox;
import java.time.LocalDate;
import java.util.Date;
import org.rubicone.vaadin.fam3.silk.Fam3SilkIcon;

/**
 *
 * @author GrucasDev
 */
public class WindowEmpleado extends Window {

    Empleado empleado_app = (Empleado) VaadinSession.getCurrent().getSession().getAttribute("USUARIO_ACTIVO");
    
    VerticalLayout cont = new VerticalLayout();
    String title_window = "EMPLEADOS";
    
    Empleado empleado;
    Boolean isEdit = false;

    Button btnCancelar = new Button("Cancelar", Fam3SilkIcon.CANCEL);
    Button btnGuardar = new Button("Guardar", Fam3SilkIcon.DISK);

    TextField txtCveEmpleado = new TextField("No. Empleado:");
    TextField txtNombre = new TextField("Nombre:");
    TextField txtAPaterno = new TextField("A. Paterno:");
    TextField txtaMaterno = new TextField("A. Materno:");
    TextField txtEmail = new TextField("Email:");
    TextField txtDomicilio = new TextField("Domicilio:");
    TextField txtCiudad = new TextField("Ciudad:");
    TextField txtEstado = new TextField("Estado:");
    TextField txtCP = new TextField("CP:");
    DateField txtFechaIngreso = new DateField("Fecha de Ingreso:", LocalDate.now());
    NativeSelect<String> cboPais = new NativeSelect("Pais:");
    NativeSelect<String> cboDepartamento = new NativeSelect("Departamento:");
    NativeSelect<String> cboPuesto = new NativeSelect("Puesto:");
    CheckBox chkActivo = new CheckBox("Activo");
    CheckBox chkAutorizador = new CheckBox("Autorizador");
    TextField txtTelPersonal = new TextField("Tel Personal:");
    TextField txtTelEmpresa = new TextField("Tel Empresa:");
    TextField txtEmailPersonal = new TextField("Email Personal:");
    PasswordField txtPassword = new PasswordField("Contraseña:");
    TextField txtNSS = new TextField("NSS:");

    Binder<Empleado> binder = new Binder<>();

    public WindowEmpleado() {
        empleado = new Empleado();
        initComponents();
        setCaption("ALTA DE EMPLEADO");
    }

    public WindowEmpleado(Empleado empleado) {
        this.empleado = empleado;
        isEdit = true;

        initComponents();
        setCaption("MODIFICACION DE EMPLEADO");
    }

    public void initComponents() {
        setWidth("600");
        setHeight("580");

        String strMsgBind = "Es necesario este campo.";
        binder.forField(txtCveEmpleado).withValidator(txt -> txt.length() >= 1, strMsgBind).bind(Empleado::getClave_empleado, Empleado::setClave_empleado);
        binder.forField(txtNombre).withValidator(txt -> txt.length() >= 1, strMsgBind).bind(Empleado::getNombre, Empleado::setNombre);
        binder.forField(txtAPaterno).withValidator(txt -> txt.length() >= 1, strMsgBind).bind(Empleado::getApellido_paterno, Empleado::setApellido_paterno);
        binder.forField(txtaMaterno).withValidator(txt -> txt.length() >= 1, strMsgBind).bind(Empleado::getApellido_materno, Empleado::setApellido_materno);
        binder.forField(txtDomicilio).withValidator(txt -> txt.length() >= 1, strMsgBind).bind(Empleado::getDomicilio, Empleado::setDomicilio);
        binder.forField(txtCiudad).withValidator(txt -> txt.length() >= 1, strMsgBind).bind(Empleado::getCiudad, Empleado::setCiudad);
        binder.forField(txtEstado).withValidator(txt -> txt.length() >= 1, strMsgBind).bind(Empleado::getEstado, Empleado::setEstado);
        binder.forField(txtCP).withValidator(txt -> txt.length() >= 1, strMsgBind).withConverter(new StringToIntegerConverter(0, "El valor debe ser Numerico!")).bind(Empleado::getCp, Empleado::setCp);
        binder.forField(cboPais).bind(Empleado::getPais, Empleado::setPais);
        binder.forField(cboDepartamento).bind(Empleado::getDepartamento, Empleado::setDepartamento);
        binder.forField(cboPuesto).bind(Empleado::getPuesto, Empleado::setPuesto);
        binder.forField(txtTelPersonal).bind(Empleado::getTelefono_personal, Empleado::setTelefono_personal);
        binder.forField(txtTelEmpresa).bind(Empleado::getTelefono_empresa, Empleado::setTelefono_empresa);
        binder.forField(txtEmail).bind(Empleado::getEmail_empresa, Empleado::setEmail_empresa);
        binder.forField(txtEmailPersonal).bind(Empleado::getEmail_personal, Empleado::setEmail_personal);
        binder.forField(txtPassword).bind(Empleado::getPassword, Empleado::setPassword);
        binder.forField(txtNSS).bind(Empleado::getNss, Empleado::setNss);
        binder.forField(chkAutorizador).bind(Empleado::getAutorizador, Empleado::setAutorizador);
        binder.forField(chkActivo).bind(Empleado::getActivo, Empleado::setActivo);

        FormLayout formulario = new FormLayout();

        String strWith = "355";

        txtNombre.setMaxLength(20);
        txtAPaterno.setMaxLength(15);
        txtaMaterno.setMaxLength(15);
        txtDomicilio.setMaxLength(30);
        txtCiudad.setMaxLength(20);
        txtEstado.setMaxLength(20);
        txtCP.setMaxLength(5);
        txtCveEmpleado.setMaxLength(8);
        txtNSS.setMaxLength(11);
        txtEmail.setMaxLength(35);
        txtPassword.setMaxLength(8);
        txtTelPersonal.setMaxLength(10);
        txtTelEmpresa.setMaxLength(10);
        txtEmailPersonal.setMaxLength(35);
        chkActivo.setValue(true);
        
        txtNombre.setWidth(strWith);
        txtAPaterno.setWidth(strWith);
        txtaMaterno.setWidth(strWith);
        txtDomicilio.setWidth(strWith);
        txtCiudad.setWidth(strWith);
        txtEstado.setWidth(strWith);
        txtCP.setWidth(strWith);
        cboPais.setWidth(strWith);
        txtCveEmpleado.setWidth(strWith);
        txtNSS.setWidth(strWith);
        txtFechaIngreso.setWidth(strWith);
        cboDepartamento.setWidth(strWith);
        cboPuesto.setWidth(strWith);
        txtEmail.setWidth(strWith);
        txtPassword.setWidth(strWith);
        txtTelPersonal.setWidth(strWith);
        txtTelEmpresa.setWidth(strWith);
        txtEmailPersonal.setWidth(strWith);
        chkActivo.setValue(true);

        cboDepartamento.setItems(_Departamentos.DEPARTAMENTOS);
        cboDepartamento.setValue(_Departamentos.DEPARTAMENTOS.get(0));
        cboDepartamento.setEmptySelectionAllowed(false);

        cboDepartamento.addSelectionListener(ev -> {
            cboPuesto.setItems(_Puestos.getSUBPUESTOS(cboDepartamento.getValue()));
            cboPuesto.setValue(_Puestos.getSUBPUESTOS(cboDepartamento.getValue()).get(0));
        });

        cboPuesto.setItems(_Puestos.getSUBPUESTOS(cboDepartamento.getValue()));
        cboPuesto.setValue(_Puestos.getSUBPUESTOS(cboDepartamento.getValue()).get(0));
        cboPuesto.setEmptySelectionAllowed(false);

        cboPais.setItems("MEXICO");
        cboPais.setValue("MEXICO");
        cboPais.setEmptySelectionAllowed(false);

        formulario.setSizeUndefined();
        formulario.addComponents(
                new Label("Informacion Personal"){{setStyleName("h3");}},
                txtNombre,txtAPaterno,txtaMaterno,txtDomicilio,txtCiudad,txtEstado,txtCP,cboPais,
                new Label("Informacion del Empleado"){{setStyleName("h3");}},
                txtCveEmpleado,txtNSS,txtFechaIngreso,cboDepartamento,cboPuesto,chkAutorizador,
                new Label("Contacto & Acceso al Sistema"){{setStyleName("h3");}},
                txtTelPersonal,txtTelEmpresa,txtEmailPersonal,txtEmail,txtPassword,
                chkActivo);
        
        cont.addComponents(
                formulario,
                new HorizontalLayout(btnCancelar,btnGuardar){{setSpacing(true); setMargin(false);}}
        );

        cont.setComponentAlignment(formulario, Alignment.MIDDLE_CENTER);
        cont.setComponentAlignment(cont.getComponent(1), Alignment.MIDDLE_CENTER);

        if (isEdit) {
            binder.readBean(empleado);
            txtFechaIngreso.setValue(ManageDates.getLocalDateFromDate(empleado.getFecha_ingreso()));
        }

        btnCancelar.addClickListener(ev -> {
            close();
        });

        btnGuardar.addClickListener(ev -> {
            guardarEmpleado();
        });

        setContent(cont);
    }

    public void guardarEmpleado() {
        try {
            toUpperCase();
            binder.writeBean(empleado);

            empleado.setUsuario(empleado.getEmail_empresa());
            empleado.setFecha_modificacion(new Date());
            empleado.setFecha_ingreso(ManageDates.getDateFromLocalDate(txtFechaIngreso.getValue()));

            EmpleadoDomain service = new EmpleadoDomain();

            if (isEdit) {
                service.EmpleadoUpdate(empleado);
            } else {
                empleado.setFecha_elaboracion(new Date());
                service.EmpleadoInsert(empleado);
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

        } catch (Exception e) {
            e.printStackTrace();
            MessageBox.createError()
                    .withCaption("Error!")
                    .withMessage("Verifique que la informacion este completa o sea correcta. ")
                    .withRetryButton()
                    .open();
        }
    }

    public void toUpperCase() {
        txtCveEmpleado.setValue(txtCveEmpleado.getValue().toUpperCase());
        txtNombre.setValue(txtNombre.getValue().toUpperCase());
        txtAPaterno.setValue(txtAPaterno.getValue().toUpperCase());
        txtaMaterno.setValue(txtaMaterno.getValue().toUpperCase());
        txtDomicilio.setValue(txtDomicilio.getValue().toUpperCase());
        txtCiudad.setValue(txtCiudad.getValue().toUpperCase());
        txtEstado.setValue(txtEstado.getValue().toUpperCase());
        txtCP.setValue(txtCP.getValue().toUpperCase());
        txtTelPersonal.setValue(txtTelPersonal.getValue().toUpperCase());
        txtTelEmpresa.setValue(txtTelEmpresa.getValue().toUpperCase());
        txtNSS.setValue(txtNSS.getValue().toUpperCase());
    }

}
