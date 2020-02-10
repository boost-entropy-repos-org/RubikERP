/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rubik.erp.modulo.compras;

import com.rubik.erp.domain.RemisionDetDomain;
import com.rubik.erp.modulo.generic.WindowSeleccionarProducto;
import com.rubik.erp.model.Empleado;
import com.rubik.erp.model.Producto;
import com.rubik.erp.model.Remision;
import com.rubik.erp.model.RemisionDet;
import com.rubik.manage.ManageNumbers;
import com.vaadin.data.Binder;
import com.vaadin.data.converter.StringToDoubleConverter;
import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import de.steinwedel.messagebox.MessageBox;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.rubicone.vaadin.fam3.silk.Fam3SilkIcon;

/**
 *
 * @author Dev
 */
public class WindowRemisionDet extends Window {

    Empleado empleado = (Empleado) VaadinSession.getCurrent().getSession().getAttribute("USUARIO_ACTIVO");

    RemisionDet partida = null;
    Boolean isEdit = false;

    VerticalLayout cont = new VerticalLayout();

    TextField txtCantidad = new TextField("Cantidad:");
    TextField txtTotal = new TextField("Total: $");
    TextArea txtDescripcion = new TextArea("Descripcion:");

    Button btnCancelar = new Button("Cancelar", Fam3SilkIcon.CANCEL);
    Button btnGuardar = new Button("Guardar", Fam3SilkIcon.DISK);
    Button btnProducto = new Button("Seleccionar Producto", Fam3SilkIcon.MAGNIFIER);
    
    Binder<RemisionDet> binder = new Binder<>();
    List<RemisionDet> listRequiDet = new ArrayList<>();
    
    Remision remision = new Remision();
    
    public WindowRemisionDet(Remision rem) {
        remision = rem;
        VaadinSession.getCurrent().getSession().setAttribute("REMISION_DET",null);
        VaadinSession.getCurrent().getSession().setAttribute("PARTIDA_OK",false);
        initComponents();
        partida = new RemisionDet();
    }
    
    public WindowRemisionDet(Remision rem, RemisionDet partida) {
        isEdit = true;
        remision = rem;
        this.partida = partida;
        VaadinSession.getCurrent().getSession().setAttribute("REMISION_DET",null);
        VaadinSession.getCurrent().getSession().setAttribute("PARTIDA_OK",false);
        initComponents();
    }

    public void initComponents() {
        setCaption("PARTIDAS DE LA REMISION");
        setWidth("490");
        setHeight("455");
        
        String strWidth = "350";

        txtCantidad.setWidth(strWidth);
        txtDescripcion.setWidth(strWidth);
        txtTotal.setWidth(strWidth);
        
        txtCantidad.setValue("1");
        txtDescripcion.setEnabled(false);
        txtTotal.setEnabled(false);
        
        txtCantidad.addFocusListener((event) -> {            
            txtCantidad.setSelection(0, txtCantidad.getValue().length());
        });

        binder.forField(txtCantidad).withValidator(cantidad -> cantidad.length() >= 1 && ManageNumbers.ToInteger(cantidad) >= 1, "Verifique que la cantidad este correcta").withConverter(new StringToIntegerConverter(0, "El valor debe ser numerico.")).bind(RemisionDet::getCantidad, RemisionDet::setCantidad);
        binder.forField(txtDescripcion).withValidator(val -> val.length() >= 1 , "Verifique que este un Producto seleccionado").bind(RemisionDet::getDescripcion, RemisionDet::setDescripcion);
        binder.forField(txtTotal).withValidator(val -> val.length() >= 1 , "Verifique que el valor sea correcto.").withConverter(new StringToDoubleConverter(0.0, "El valor debe ser numerico.")).bind(RemisionDet::getTotal, RemisionDet::setTotal);

        if (isEdit) {
            binder.readBean(partida);
        }

        btnCancelar.addClickListener((event) -> {
            VaadinSession.getCurrent().getSession().setAttribute("PARTIDA_OK",false);
            close();
        });
        
        btnProducto.addClickListener((event) -> {
            WindowSeleccionarProducto window = new WindowSeleccionarProducto();
            window.addCloseListener(ev -> {
                
                Producto p = (Producto) VaadinSession.getCurrent().getSession().getAttribute("PRODUCTO_SELECCIONADO");
                System.out.println("PRODUCTO SELECCIONADO " + p);
                if(p != null){
                    partida = new RemisionDet();
                    partida.setDescripcion(p.getDescripcion());
                    partida.setProducto_id(p.getId());
                    partida.setUnidad_medida(p.getUnidad_medida());
                    partida.setPrecio_unitario(p.getPrecio_compra());
                    
                    txtDescripcion.setValue(p.getDescripcion());
                    txtTotal.setValue(p.getPrecio_compra().toString());
                }
            });
            getUI().addWindow(window);
        });
        
        txtCantidad.addValueChangeListener((event) -> {
            if(ManageNumbers.ToInteger(txtCantidad.getValue())==0){
                txtCantidad.setValue("1");
            }
            
            txtTotal.setValue(
                    (ManageNumbers.ToInteger(txtCantidad.getValue()) * partida.getPrecio_unitario())+""
            );
        });

        btnGuardar.addClickListener((event) -> {
            if (!txtDescripcion.getValue().isEmpty() && !txtCantidad.getValue().isEmpty()) {

                try {
                    binder.writeBean(partida);

                    partida.setUsuario(empleado.getNombre_completo());
                    partida.setUsuario_id(empleado.getId());
                    partida.setUnidad(empleado.getUnidad());
                    partida.setUnidad_id(empleado.getUnidad_id());
                    partida.setEmpresa(empleado.getEmpresa());
                    partida.setEmpresa_id(empleado.getEmpresa_id());
                    partida.setFecha_alta(new Date());
                    partida.setCantidad(ManageNumbers.ToInteger(txtCantidad.getValue()));
                    partida.setTotal(ManageNumbers.stringToDouble(txtTotal.getValue()));
                    
                    RemisionDetDomain domain = new RemisionDetDomain();
                    
                    if(remision != null){
                        if(isEdit){
                            domain.RemisionDetUpdate(partida);
                        }else{
                            partida.setFolio(remision.getFolio());
                            partida.setDocumento_id(remision.getId());
                            domain.RemisionDetInsert(partida);
                        }
                    }else{
                        VaadinSession.getCurrent().getSession().setAttribute("REMISION_DET",partida);
                    }
                    
                    VaadinSession.getCurrent().getSession().setAttribute("PARTIDA_OK",true);
                    close();
                } catch (Exception e) {
                    e.printStackTrace();
                    MessageBox.createError()
                            .withCaption("Error!")
                            .withMessage("Verifique que la informacion este completa o sea correcta: " + e.getMessage())
                            .withRetryButton()
                            .open();
                }
            } else {
                MessageBox.createError()
                        .withCaption("Error!")
                        .withMessage("Verifique que este seleccionado un Producto y que la cantidad sea correcta.")
                        .withRetryButton()
                        .open();
            }
        });
        
        FormLayout fLay = new FormLayout();
        fLay.addComponents(btnProducto,txtCantidad, txtDescripcion, txtTotal);
        
        cont.addComponents(fLay, new HorizontalLayout(btnCancelar, btnGuardar));
        
        cont.setComponentAlignment(cont.getComponent(0), Alignment.MIDDLE_LEFT);
        cont.setComponentAlignment(cont.getComponent(1), Alignment.MIDDLE_CENTER);
        
        center();
        setModal(true);
        setResizable(false);
        setContent(cont);
    }
}
