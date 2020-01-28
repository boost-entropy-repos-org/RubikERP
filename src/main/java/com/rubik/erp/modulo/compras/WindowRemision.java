/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rubik.erp.modulo.compras;

import com.rubik.erp.domain.EmpleadoDomain;
import com.rubik.erp.domain.RemisionDomain;
import com.rubik.erp.model.Empleado;
import com.rubik.erp.model.Remision;
import com.rubik.erp.model.RemisionDet;
import com.vaadin.data.Binder;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import java.util.ArrayList;
import java.util.List;
import org.rubicone.vaadin.fam3.silk.Fam3SilkIcon;

/**
 *
 * @author Dev
 */
public class WindowRemision extends Window {
    
    Empleado empleado = (Empleado) VaadinSession.getCurrent().getSession().getAttribute("USUARIO_ACTIVO");
    
    VerticalLayout cont = new VerticalLayout();
    String title_window = "Remisiones de Compra";

    public Remision remision = new Remision();
    public Boolean isEdit = false;

    DateField txtFechaRequerida = new DateField("Fecha Requerida:");
    TextField txtSolicita = new TextField("Solicitante:");
    TextArea txtObservaciones = new TextArea("Observaciones:");
    TextArea txtDireccionEntrega = new TextArea("Descripcion Entrega:");
    NativeSelect<Empleado> cboAutorizador = new NativeSelect("Autorizador:");
    NativeSelect<String> cboPrioridad = new NativeSelect("Prioridad:");

    Button btnAgregarPartida = new Button(Fam3SilkIcon.ADD);
    Button btnModificarPartida = new Button(Fam3SilkIcon.PENCIL);
    Button btnEliminarPartida = new Button(Fam3SilkIcon.DELETE);
    Button btnGuardar = new Button("Guardar", Fam3SilkIcon.DISK);
    Button btnCancelar = new Button("Cancelar", Fam3SilkIcon.CANCEL);
    
    Grid<RemisionDet> gridRequiDet = new Grid<>();
    List<RemisionDet> listRequiDet = new ArrayList<>();
    
    List<Empleado> listAutorizadoresCompras = new ArrayList<>();

    public WindowRemision() {
        initComponents();
        
        RemisionDomain service = new RemisionDomain();
    }

    public WindowRemision(Remision prod) {
        isEdit = true;
        this.remision = prod;
        initComponents();
    }

    public void initComponents() {
        setContent(cont);
        setResizable(false);
        
        String strWidth = "300";
        
        setWidth("80%");
        setHeight("80%");

        Binder<Remision> binder = new Binder<>();
        binder.forField(txtSolicita).bind(Remision::getSolicita, Remision::setSolicita);
        binder.forField(txtObservaciones).bind(Remision::getObservaciones, Remision::setObservaciones);
        binder.forField(txtDireccionEntrega).bind(Remision::getDireccion_entrega, Remision::setDireccion_entrega);
        binder.forField(cboPrioridad).bind(Remision::getPrioridad, Remision::setPrioridad);
        
        gridRequiDet.setHeight("272");
        gridRequiDet.setSelectionMode(Grid.SelectionMode.SINGLE);

        gridRequiDet.addColumn(RemisionDet::getCantidad).setCaption("CANTIDAD");
        gridRequiDet.addColumn(RemisionDet::getDescripcion).setCaption("DESCRIPCION");
        gridRequiDet.addColumn(RemisionDet::getImporte).setCaption("IMPORTE");
        gridRequiDet.addColumn(RemisionDet::getTotal).setCaption("TOTAL");
        
        if (isEdit) {
            binder.readBean(remision);
            
//            for (Proveedor prov : proveedorList1) {
//                if (remision.getProveedor_id_1().equals(prov.getId())) {
//                    cboProveedor1.setValue(prov);
//                }
//            }
        }

        btnCancelar.addClickListener((event) -> {
            close();
        });

        btnGuardar.addClickListener((event) -> {
//            try {
//                binder.writeBean(remision);
//                toUpperCase();
//
//                ProductoDomain service = new ProductoDomain();
//
//                if (isEdit) {
//                    remision.setFecha_modificacion(new Date());
//                    service.ProductoUpdate(remision);
//                } else {
//                    service.ProductoInsert(remision);
//                }
//
//                if (service.getOk()) {
//                    MessageBox.createInfo()
//                            .withCaption("Atencion")
//                            .withMessage(service.getNotification())
//                            .open();
//                    close();
//                } else {
//                    MessageBox.createError()
//                            .withCaption("Error!")
//                            .withMessage(service.getNotification())
//                            .withRetryButton()
//                            .open();
//                }
//            } catch (Exception ex) {
//                MessageBox.createError()
//                        .withCaption("Error!")
//                        .withMessage("Verifique que la informacion este completa o sea correcta. ")
//                        .withRetryButton()
//                        .open();
//            }
        });
        
        txtSolicita.setEnabled(false);
        txtSolicita.setValue(empleado.getNombre_completo());
        txtObservaciones.setRows(2);
        txtDireccionEntrega.setRows(2);
        
        cboPrioridad.setEmptySelectionAllowed(false);
        cboPrioridad.setItems("ALTA", "MEDIA", "BAJA");
        cboPrioridad.setValue("ALTA");
       
        txtFechaRequerida.setWidth(strWidth);
        txtSolicita.setWidth(strWidth);
        txtObservaciones.setWidth(strWidth);
        cboPrioridad.setWidth(strWidth);
        cboAutorizador.setWidth(strWidth);
        txtDireccionEntrega.setWidth(strWidth);
        
        cboAutorizador.setItems(getAutorizadorCompras());
        cboAutorizador.setSelectedItem(listAutorizadoresCompras.get(0));
        cboAutorizador.setEmptySelectionAllowed(false);

        FormLayout fLay = new FormLayout();
        fLay.addComponents(txtFechaRequerida,txtSolicita, cboPrioridad, cboAutorizador, txtObservaciones, 
                txtDireccionEntrega);

        cont.addComponents(
                new HorizontalLayout(fLay, 
                        new VerticalLayout(
                                new HorizontalLayout(
                                        btnAgregarPartida,btnModificarPartida,btnEliminarPartida),
                                gridRequiDet){{setComponentAlignment(getComponent(0), Alignment.MIDDLE_CENTER);}}
                ), new HorizontalLayout(btnCancelar, btnGuardar));
        cont.setComponentAlignment(cont.getComponent(1), Alignment.MIDDLE_CENTER);
    }
    
    public void toUpperCase() {
        remision.setSolicita(txtSolicita.getValue().toUpperCase());
        remision.setObservaciones(txtObservaciones.getValue().toUpperCase());
        remision.setDireccion_entrega(txtDireccionEntrega.getValue().toUpperCase());
    }
    
    public List<Empleado> getAutorizadorCompras() {
        EmpleadoDomain provService = new EmpleadoDomain();
        provService.getEmpleado(" autorizador = 1 ", "", " nombre ASC");
        
        listAutorizadoresCompras = provService.getObjects();
        return listAutorizadoresCompras;
    }
    
}