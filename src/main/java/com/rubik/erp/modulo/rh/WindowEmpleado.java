/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rubik.erp.modulo.rh;

import com.rubik.erp.model.Empleado;
import com.rubik.manage.ManageDates;
import com.vaadin.data.Binder;
import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.server.Sizeable;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;
import de.steinwedel.messagebox.MessageBox;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import org.rubicone.vaadin.fam3.silk.Fam3SilkIcon;

/**
 *
 * @author GrucasDev
 */
public class WindowEmpleado extends Window {

    Empleado empleado_app = (Empleado) VaadinSession.getCurrent().getSession().getAttribute("USUARIO_ACTIVO");
    
    VerticalLayout cont = new VerticalLayout();
    String title_window = "CATALOGOS DE PRODUCTOS";
    
    Empleado empleado;
    Boolean isEdit = false;

    TabSheet container;
    String container_width = "100%";
    String container_height = "400";

    Button btnCancelar = new Button("Cancelar", Fam3SilkIcon.CANCEL);
    Button btnGuardar = new Button("Guardar", Fam3SilkIcon.DISK);

    //Datos empleado
    TextField txt_dempleado_no_empleado = new TextField("No. Empleado:");
    TextField txt_dempleado_nombre = new TextField("Nombre:");
    TextField txt_dempleado_apaterno = new TextField("A. Paterno:");
    TextField txt_dempleado_amaterno = new TextField("A. Materno:");
    TextField txt_dempleado_email = new TextField("Email:");
    TextField txt_dempleado_domicilio = new TextField("Domicilio:");
    TextField txt_dempleado_ciudad = new TextField("Ciudad:");
    TextField txt_dempleado_estado = new TextField("Estado:");
    TextField txt_dempleado_cp = new TextField("CP:");
    DateField txt_dempleado_fecha_ingreso = new DateField("Fecha de Ingreso:", LocalDate.now());
    NativeSelect<String> cbo_dempleado_pais = new NativeSelect("Pais:");
    NativeSelect<Empresa> cbo_dempleado_empresa = new NativeSelect("Empresa:");
    NativeSelect<UnidadNegocio> cbo_dempleado_unegocio = new NativeSelect("U Negocio:");
    CheckBox chk_dempleado_activo = new CheckBox("Activo");

    //Extras
    TextField txt_extras_tel_personal = new TextField("Tel Personal:");
    TextField txt_extras_tel_empresa = new TextField("Tel Empresa:");
    TextField txt_extras_email_personal = new TextField("Email Personal:");

    //RH
    TextField txt_rh_nss = new TextField("NSS:");
    NativeSelect<Puesto> cbo_dempleado_puesto = new NativeSelect<>("Puesto:");
    NativeSelect<Departamento> cbo_dempleado_departamento = new NativeSelect<>("Departamento");

     List<Puesto> listPuestoAux;
     List<Departamento> listDepartamentoAux;

    //Configuraciones
    //---compras
    CheckBox chk_conf_requesitor = new CheckBox("Requesitor");
    CheckBox chk_conf_autorizador = new CheckBox("Autorizador");

    //Accesos
    //Documentos
    //SERVICIOS
    EmpresaService empService = new EmpresaService();
    UnidadNegocioService unegocioService = new UnidadNegocioService();

    //Listas
    List<Empresa> empresas;
    List<UnidadNegocio> unidadesNegocios;

    //Binder
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
        setWidth("1010");
        setHeight("580");

        container = new TabSheet();
        container.setHeight(100.0f, Sizeable.Unit.PERCENTAGE);
        container.addStyleName(ValoTheme.TABSHEET_FRAMED);
        container.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);

        // llenado de listas generales
        empService.getEmpresa(" activo = 1", "", "");
        empresas = empService.getObjects();

        //Binding
        String strMsgBind = "Es necesario este campo.";
        binder.forField(txt_dempleado_no_empleado).withValidator(txt -> txt.length() >= 1, strMsgBind).bind(Empleado::getClave_empleado, Empleado::setClave_empleado);
        binder.forField(txt_dempleado_nombre).withValidator(txt -> txt.length() >= 1, strMsgBind).bind(Empleado::getNombre, Empleado::setNombre);
        binder.forField(txt_dempleado_apaterno).withValidator(txt -> txt.length() >= 1, strMsgBind).bind(Empleado::getApellido_paterno, Empleado::setApellido_paterno);
        binder.forField(txt_dempleado_amaterno).withValidator(txt -> txt.length() >= 1, strMsgBind).bind(Empleado::getApellido_materno, Empleado::setApellido_materno);
        binder.forField(txt_dempleado_email).bind(Empleado::getEmail_empresa, Empleado::setEmail_empresa);
        binder.forField(txt_dempleado_domicilio).withValidator(txt -> txt.length() >= 1, strMsgBind).bind(Empleado::getDomicilio, Empleado::setDomicilio);
        binder.forField(txt_dempleado_ciudad).withValidator(txt -> txt.length() >= 1, strMsgBind).bind(Empleado::getCiudad, Empleado::setCiudad);
        binder.forField(txt_dempleado_estado).withValidator(txt -> txt.length() >= 1, strMsgBind).bind(Empleado::getEstado, Empleado::setEstado);
        binder.forField(txt_dempleado_cp).withValidator(txt -> txt.length() >= 1, strMsgBind).withConverter(new StringToIntegerConverter(0, "El valor debe ser Numerico!")).bind(Empleado::getCp, Empleado::setCp);
        binder.forField(cbo_dempleado_pais).bind(Empleado::getPais, Empleado::setPais);
        binder.forField(txt_extras_tel_personal).bind(Empleado::getTelefono_personal, Empleado::setTelefono_personal);
        binder.forField(txt_extras_tel_empresa).bind(Empleado::getTelefono_empresa, Empleado::setTelefono_empresa);
        binder.forField(txt_extras_email_personal).bind(Empleado::getEmail_personal, Empleado::setEmail_personal);
        binder.forField(txt_rh_nss).bind(Empleado::getNss, Empleado::setNss);
        binder.forField(chk_conf_requesitor).bind(Empleado::getRequisitor, Empleado::setRequisitor);
        binder.forField(chk_conf_autorizador).bind(Empleado::getAutorizador, Empleado::setAutorizador);
        binder.forField(chk_dempleado_activo).bind(Empleado::getActivo, Empleado::setActivo);

        container.addTab(getData(), "Informacion del Empleado");
        container.addTab(getExtras(), "Extras");
        container.addTab(getRecursosHumanos(), "Recursos Humanos");
        container.addTab(getAccesos(), "Accesos");
        container.addTab(getConfiguraciones(), "Configuraciones");
        container.addTab(getDocumentos(), "Documentos");

        cont.addComponents(
                container,
                new HorizontalLayout(btnCancelar,btnGuardar){{setSpacing(true); setMargin(false);}}
        );

        cont.setComponentAlignment(container, Alignment.MIDDLE_CENTER);
        cont.setComponentAlignment(cont.getComponent(1), Alignment.MIDDLE_CENTER);

        if (isEdit) {
            cargarEmpleado();
        }

        btnCancelar.addClickListener(ev -> {
            close();
        });

        btnGuardar.addClickListener(ev -> {
            guardarEmpleado();
        });

        setContent(cont);
    }

    public Component getData() {
        VerticalLayout panelPrincipal = new VerticalLayout();
        panelPrincipal.setMargin(false);
        panelPrincipal.setSpacing(false);
        panelPrincipal.setWidth(container_width);
        panelPrincipal.setHeight(container_height);

        VerticalLayout vLay = new VerticalLayout();
        vLay.setMargin(false);
        vLay.setSpacing(false);
        vLay.setSizeUndefined();

        panelPrincipal.addComponent(vLay);
        panelPrincipal.setComponentAlignment(vLay, Alignment.MIDDLE_CENTER);

        String width_1 = "355";
        String width_2 = "355";

        txt_dempleado_cp.setMaxLength(5);

        txt_dempleado_nombre.setWidth(width_1);
        txt_dempleado_apaterno.setWidth(width_1);
        txt_dempleado_amaterno.setWidth(width_1);
        txt_dempleado_domicilio.setWidth(width_1);
        txt_dempleado_ciudad.setWidth(width_1);
        txt_dempleado_estado.setWidth(width_1);
        txt_dempleado_cp.setWidth(width_1);

        cbo_dempleado_pais.setWidth(width_2);
        txt_dempleado_email.setWidth(width_2);
        cbo_dempleado_empresa.setWidth(width_2);
        cbo_dempleado_unegocio.setWidth(width_2);
        txt_dempleado_fecha_ingreso.setWidth(width_2);
        txt_dempleado_no_empleado.setWidth("70");
        chk_dempleado_activo.setValue(true);

        cbo_dempleado_empresa.setItems(empresas);
        cbo_dempleado_empresa.setValue(empresas.get(0));
        cbo_dempleado_empresa.setEmptySelectionAllowed(false);

        cbo_dempleado_empresa.addSelectionListener(ev -> {
            unegocioService.getUnidadNegocio(" empresa_id = " + cbo_dempleado_empresa.getValue().getId(), "", "");
            unidadesNegocios = unegocioService.getObjects();
            cbo_dempleado_unegocio.setItems(unidadesNegocios);
            cbo_dempleado_unegocio.setValue(unidadesNegocios.get(0));
        });

        unegocioService.getUnidadNegocio(" empresa_id = " + cbo_dempleado_empresa.getValue().getId(), "", "");
        unidadesNegocios = unegocioService.getObjects();
        cbo_dempleado_unegocio.setItems(unidadesNegocios);
        cbo_dempleado_unegocio.setValue(unidadesNegocios.get(0));
        cbo_dempleado_unegocio.setEmptySelectionAllowed(false);

        cbo_dempleado_pais.setItems(GrucasDomainConfig.PAISES);
        cbo_dempleado_pais.setValue(GrucasDomainConfig.PAIS_MEXICO);
        cbo_dempleado_pais.setEmptySelectionAllowed(false);

        HorizontalLayout forms = new HorizontalLayout();
        forms.setSpacing(true);
        forms.addComponents(
                new FormLayout(txt_dempleado_nombre,txt_dempleado_apaterno,txt_dempleado_amaterno,txt_dempleado_domicilio,txt_dempleado_ciudad,txt_dempleado_estado,txt_dempleado_cp){{setSpacing(true);setMargin(false);}},
                new FormLayout(cbo_dempleado_pais,txt_dempleado_email,cbo_dempleado_empresa,cbo_dempleado_unegocio,txt_dempleado_fecha_ingreso,txt_dempleado_no_empleado,chk_dempleado_activo){{setSpacing(true);setMargin(false);}}
        );

        vLay.addComponents(
                forms
        );
        vLay.setComponentAlignment(forms, Alignment.MIDDLE_CENTER);

        return panelPrincipal;
    }

    public Component getExtras() {
        VerticalLayout panelPrincipal = new VerticalLayout();
        panelPrincipal.setMargin(false);
        panelPrincipal.setSpacing(false);
        panelPrincipal.setWidth(container_width);
        panelPrincipal.setHeight(container_height);

        VerticalLayout vLay = new VerticalLayout();
        vLay.setMargin(false);
        vLay.setSpacing(false);
        vLay.setSizeUndefined();

        panelPrincipal.addComponent(vLay);
        panelPrincipal.setComponentAlignment(vLay, Alignment.MIDDLE_CENTER);

        String width_1 = "355";
        String width_2 = "355";

        txt_extras_tel_personal.setWidth(width_1);
        txt_extras_tel_empresa.setWidth(width_1);
        txt_extras_email_personal.setWidth(width_1);

        HorizontalLayout forms = new HorizontalLayout();
        forms.setSpacing(true);
        forms.addComponents(
                new FormLayout(txt_extras_tel_personal,txt_extras_tel_empresa,txt_extras_email_personal){{setSpacing(true);setMargin(false);}}
        );

        vLay.addComponents(
                forms
        );
        vLay.setComponentAlignment(forms, Alignment.MIDDLE_CENTER);

        return panelPrincipal;
    }

    public Component getRecursosHumanos() {
        VerticalLayout panelPrincipal = new VerticalLayout();
        panelPrincipal.setMargin(false);
        panelPrincipal.setSpacing(false);
        panelPrincipal.setWidth(container_width);
        panelPrincipal.setHeight(container_height);

        VerticalLayout vLay = new VerticalLayout();
        vLay.setMargin(false);
        vLay.setSpacing(false);
        vLay.setSizeUndefined();

        panelPrincipal.addComponent(vLay);
        panelPrincipal.setComponentAlignment(vLay, Alignment.MIDDLE_CENTER);

        String width_1 = "355";
        String width_2 = "355";

        txt_rh_nss.setWidth(width_1);
        cbo_dempleado_departamento.setWidth(width_1);
        cbo_dempleado_puesto.setWidth(width_1);

        PuestoService serviceUnidadAux = new PuestoService();
        serviceUnidadAux.getPuesto("", "", "nombre ASC");
        listPuestoAux = serviceUnidadAux.getObjects();
        cbo_dempleado_puesto.setItems(listPuestoAux);
        cbo_dempleado_puesto.setValue(listPuestoAux.get(0));
        cbo_dempleado_puesto.setEmptySelectionAllowed(false);

        DepartamentoService serviceDep = new DepartamentoService();
        serviceDep.getDepartamento("", "", "departamento ASC");
        listDepartamentoAux = serviceDep.getObjects();
        cbo_dempleado_departamento.setItems(listDepartamentoAux);
        cbo_dempleado_departamento.setValue(listDepartamentoAux.get(0));
        cbo_dempleado_departamento.setEmptySelectionAllowed(false);

        HorizontalLayout forms = new HorizontalLayout();
        forms.setSpacing(true);
        forms.addComponents(
                new FormLayout(txt_rh_nss,cbo_dempleado_departamento,cbo_dempleado_puesto){{setSpacing(true);setMargin(false);}}
        );

        vLay.addComponents(
                forms
        );
        vLay.setComponentAlignment(forms, Alignment.MIDDLE_CENTER);

        return panelPrincipal;
    }

    public Component getAccesos() {
        VerticalLayout panelPrincipal = new VerticalLayout();
        panelPrincipal.setMargin(false);
        panelPrincipal.setSpacing(false);
        panelPrincipal.setWidth(container_width);
        panelPrincipal.setHeight(container_height);

        VerticalLayout vLay = new VerticalLayout();
        vLay.setMargin(false);
        vLay.setSpacing(false);
        vLay.setSizeUndefined();

        panelPrincipal.addComponent(vLay);
        panelPrincipal.setComponentAlignment(vLay, Alignment.MIDDLE_CENTER);

        return panelPrincipal;
    }

    public Component getConfiguraciones() {
        VerticalLayout panelPrincipal = new VerticalLayout();
        panelPrincipal.setMargin(false);
        panelPrincipal.setSpacing(false);
        panelPrincipal.setWidth(container_width);
        panelPrincipal.setHeight(container_height);

        VerticalLayout vLay = new VerticalLayout();
        vLay.setMargin(false);
        vLay.setSpacing(false);
        vLay.setSizeUndefined();

        panelPrincipal.addComponent(vLay);
        panelPrincipal.setComponentAlignment(vLay, Alignment.MIDDLE_CENTER);

        Panel panelCompras = new Panel("Sistema de Compras");
        panelCompras.setContent(new VerticalLayout(chk_conf_requesitor, chk_conf_autorizador));

        HorizontalLayout panels = new HorizontalLayout();
        panels.setSpacing(true);
        panels.addComponents(
                panelCompras
        );

        vLay.addComponents(
                panels
        );
        vLay.setComponentAlignment(panels, Alignment.MIDDLE_CENTER);

        return panelPrincipal;
    }

    public Component getDocumentos() {
        VerticalLayout panelPrincipal = new VerticalLayout();
        panelPrincipal.setMargin(false);
        panelPrincipal.setSpacing(false);
        panelPrincipal.setWidth(container_width);
        panelPrincipal.setHeight(container_height);

        VerticalLayout vLay = new VerticalLayout();
        vLay.setMargin(false);
        vLay.setSpacing(false);
        vLay.setSizeUndefined();

        panelPrincipal.addComponent(vLay);
        panelPrincipal.setComponentAlignment(vLay, Alignment.MIDDLE_CENTER);

        return panelPrincipal;
    }

    public void guardarEmpleado() {
        try {
            toUpperCase();
            binder.writeBean(empleado);

            Empresa empresa = cbo_dempleado_empresa.getValue();
            UnidadNegocio unegocio = cbo_dempleado_unegocio.getValue();

            empleado.setUsuario(usuario.getFullName());
            empleado.setUsuario_id(usuario.getId());
            empleado.setEmpresa(empresa.getRazon_social());
            empleado.setEmpresa_id(empresa.getId());
            empleado.setUnidad(unegocio.getNombre());
            empleado.setUnidad_id(unegocio.getId());
            empleado.setFecha_modificacion(new Date());
            empleado.setFecha_ingreso(ManageDates.getDateFromLocalDate(txt_dempleado_fecha_ingreso.getValue()));
            empleado.setPuesto_id(cbo_dempleado_puesto.getValue().getId());
            empleado.setPuesto(cbo_dempleado_puesto.getValue().getNombre());
            empleado.setClasificacion_puesto(cbo_dempleado_puesto.getValue().getClasificacion_puesto());
            empleado.setDepartamento_id(cbo_dempleado_departamento.getValue().getId());
            empleado.setDepartamento(cbo_dempleado_departamento.getValue().getDepartamento());

            EmpleadoService service = new EmpleadoService();

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

    public void cargarEmpleado() {
        binder.readBean(empleado);

        txt_dempleado_fecha_ingreso.setValue(ManageDates.getLocalDateFromDate(empleado.getFecha_ingreso()));

        for (int i = 0; i < empresas.size(); i++) {
            if (Objects.equals(empresas.get(i).getId(), empleado.getEmpresa_id())) {
                cbo_dempleado_empresa.setValue(empresas.get(i));

                unegocioService.getUnidadNegocio(" empresa_id = " + empresas.get(i).getId(), "", "");
                unidadesNegocios = unegocioService.getObjects();
                cbo_dempleado_unegocio.setItems(unidadesNegocios);

                for (int j = 0; j < unidadesNegocios.size(); j++) {
                    if (Objects.equals(unidadesNegocios.get(j).getId(), empleado.getUnidad_id())) {
                        cbo_dempleado_unegocio.setValue(unidadesNegocios.get(j));
                        break;
                    }
                }

                break;
            }
        }
        for (Puesto puest : listPuestoAux) {
            if (empleado.getPuesto().equals(puest.getNombre())) {
                cbo_dempleado_puesto.setValue(puest);
            }
        }

        for (Departamento dep : listDepartamentoAux) {
            if (empleado.getDepartamento().equals(dep.getDepartamento())) {
                cbo_dempleado_departamento.setValue(dep);
            }
        }

    }

    public void toUpperCase() {
        txt_dempleado_no_empleado.setValue(txt_dempleado_no_empleado.getValue().toUpperCase());
        txt_dempleado_nombre.setValue(txt_dempleado_nombre.getValue().toUpperCase());
        txt_dempleado_apaterno.setValue(txt_dempleado_apaterno.getValue().toUpperCase());
        txt_dempleado_amaterno.setValue(txt_dempleado_amaterno.getValue().toUpperCase());
        txt_dempleado_domicilio.setValue(txt_dempleado_domicilio.getValue().toUpperCase());
        txt_dempleado_ciudad.setValue(txt_dempleado_ciudad.getValue().toUpperCase());
        txt_dempleado_estado.setValue(txt_dempleado_estado.getValue().toUpperCase());
        txt_dempleado_cp.setValue(txt_dempleado_cp.getValue().toUpperCase());
        txt_extras_tel_personal.setValue(txt_extras_tel_personal.getValue().toUpperCase());
        txt_extras_tel_empresa.setValue(txt_extras_tel_empresa.getValue().toUpperCase());
        txt_rh_nss.setValue(txt_rh_nss.getValue().toUpperCase());
    }

}
