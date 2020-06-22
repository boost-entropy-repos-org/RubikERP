/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rubik.erp.modulo.ventas;

import com.rubik.erp.domain.ClienteDomain;
import com.rubik.erp.domain.ProyectoDomain;
import com.rubik.erp.model.Cliente;
import com.rubik.erp.model.Empleado;
import com.rubik.erp.model.Proyecto;
import com.vaadin.data.Binder;
import com.vaadin.data.converter.LocalDateToDateConverter;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import de.steinwedel.messagebox.MessageBox;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.rubicone.vaadin.fam3.silk.Fam3SilkIcon;

/**
 *
 * @author Dev
 */
public class WindowVentasProyectos extends Window {
    
    Empleado empleado = (Empleado) VaadinSession.getCurrent().getSession().getAttribute("USUARIO_ACTIVO");
    
    VerticalLayout cont = new VerticalLayout();

    public Proyecto proyecto = new Proyecto();
    public Boolean isEdit = false;
    
    TextField txtNombre = new TextField("Nombre:");
    TextArea txtDescripcion = new TextArea("Descripcion:");
    DateField txtFechaInicio = new DateField("Fecha Inicio:", LocalDate.now());
    DateField txtFechaFin = new DateField("Fecha Fin:", LocalDate.now());
    NativeSelect<Cliente> cboClientes = new NativeSelect("Cliente:");
    List<Cliente> listClientes = new ArrayList<>();
    
    Binder<Proyecto> binder = new Binder<>();

    CheckBox chkActivo = new CheckBox("Activo", true);

    public WindowVentasProyectos() {
        setCaption("ALTA DE PROYECTO");
        initComponents();
    }

    public WindowVentasProyectos(Proyecto proy) {
        setCaption("MODIFICACION DE PROYECTO");
        isEdit = true;
        this.proyecto = proy;
        initComponents();
        
        if (isEdit) {
            binder.readBean(proyecto);
            for (Cliente cte : listClientes) {
                if (proyecto.getCliente_id().equals(cte.getId())) {
                    cboClientes.setValue(cte);
                    break;
                }
            }
        }
    }

    public void initComponents() {
        setContent(cont);
        setResizable(false);
        
        String strWidth = "400";
        
        setWidth("600");
        setHeight("90%");
        
        Button btnGuardar = new Button("Guardar", Fam3SilkIcon.DISK);
        Button btnCancelar = new Button("Cancelar", Fam3SilkIcon.CANCEL);

        binder.forField(txtNombre).bind(Proyecto::getNombre, Proyecto::setNombre);
        binder.forField(txtDescripcion).bind(Proyecto::getDescripcion, Proyecto::setDescripcion);
        binder.forField(chkActivo).bind(Proyecto::getActivo, Proyecto::setActivo);
        binder.forField(txtFechaInicio).withConverter(new LocalDateToDateConverter()).bind(Proyecto::getFecha_inicio, Proyecto::setFecha_inicio);
        binder.forField(txtFechaFin).withConverter(new LocalDateToDateConverter()).bind(Proyecto::getFecha_fin, Proyecto::setFecha_fin);
        
        cboClientes.setItems(getClientes());
        cboClientes.setEmptySelectionAllowed(false);
        try {
            cboClientes.setSelectedItem(listClientes.get(0));
        } catch (Exception e) {
            MessageBox.createError()
                    .withCaption("Error!")
                    .withMessage("No existen Clientes dados de alta. ")
                    .withRetryButton()
                    .open();
        }

        btnCancelar.addClickListener((event) -> {
            close();
        });

        btnGuardar.addClickListener((event) -> {
            try {
                binder.writeBean(proyecto);
                toUpperCase();

                ProyectoDomain service = new ProyectoDomain();

                proyecto.setUsuario_id(empleado.getId());
                proyecto.setUsuario(empleado.getNombre_completo());
                proyecto.setCliente_id(cboClientes.getValue().getId());
                proyecto.setNombre_cliente(cboClientes.getValue().getRazon_social());

                if (isEdit) {
                    proyecto.setFecha_modificacion(new Date());
                    service.ProyectoUpdate(proyecto);
                } else {
                    service.ProyectoInsert(proyecto);
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

        txtDescripcion.setRows(5);

        txtNombre.setWidth(strWidth);
        txtDescripcion.setWidth(strWidth);
        cboClientes.setWidth(strWidth);
        
        FormLayout fLay = new FormLayout();
        
        fLay.addComponents(new Label("Informacion del Proyecto"){{setStyleName("h3");}});
        fLay.addComponents(txtNombre,txtFechaInicio, txtFechaFin, txtDescripcion, cboClientes, chkActivo);
     
        cont.addComponents(fLay, new HorizontalLayout(btnCancelar, btnGuardar));
        cont.setComponentAlignment(cont.getComponent(1), Alignment.MIDDLE_CENTER);
    }
    
    public void toUpperCase() {
        proyecto.setNombre(txtNombre.getValue().toUpperCase());
        proyecto.setDescripcion(txtDescripcion.getValue().toUpperCase());
    }
    
    public List<Cliente> getClientes() {
        ClienteDomain cteDomain = new ClienteDomain();
        cteDomain.getCliente(" activo = 1", "", "razon_social ASC");
        listClientes = cteDomain.getObjects();
        return listClientes;
    }
    
}