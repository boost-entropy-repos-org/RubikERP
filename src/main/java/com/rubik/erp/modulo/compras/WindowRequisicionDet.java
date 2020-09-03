/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rubik.erp.modulo.compras;

import com.rubik.erp.domain.RequisicionDetDomain;
import com.rubik.erp.modulo.generic.WindowSeleccionarProducto;
import com.rubik.erp.model.Empleado;
import com.rubik.erp.model.Producto;
import com.rubik.erp.model.Requisicion;
import com.rubik.erp.model.RequisicionDet;
import com.rubik.manage.ManageNumbers;
import com.vaadin.data.Binder;
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
public class WindowRequisicionDet extends Window {

    Empleado empleado = (Empleado) VaadinSession.getCurrent().getSession().getAttribute("USUARIO_ACTIVO");

    RequisicionDet partida = null;
    Boolean isEdit = false;

    VerticalLayout cont = new VerticalLayout();

    TextField txtCantidad = new TextField("Cantidad:");
    TextArea txtDescripcion = new TextArea("Descripcion:");

    Button btnCancelar = new Button("Cancelar", Fam3SilkIcon.CANCEL);
    Button btnGuardar = new Button("Guardar", Fam3SilkIcon.DISK);
    Button btnProducto = new Button("Seleccionar Producto", Fam3SilkIcon.MAGNIFIER);
    
    Binder<RequisicionDet> binder = new Binder<>();
    List<RequisicionDet> listRequiDet = new ArrayList<>();
    
    Requisicion requisicion = new Requisicion();
    
    public WindowRequisicionDet(Requisicion req) {
        requisicion = req;
        VaadinSession.getCurrent().getSession().setAttribute("REQUISICION_DET",null);
        VaadinSession.getCurrent().getSession().setAttribute("PARTIDA_OK",false);
        initComponents();
        partida = new RequisicionDet();
    }
    
    public WindowRequisicionDet(Requisicion req, RequisicionDet partida) {
        isEdit = true;
        requisicion = req;
        this.partida = partida;
        VaadinSession.getCurrent().getSession().setAttribute("REQUISICION_DET",null);
        VaadinSession.getCurrent().getSession().setAttribute("PARTIDA_OK",false);
        initComponents();
    }

    public void initComponents() {
        setCaption("PARTIDAS DE LA REQUISICION");
        setWidth("490");
        setHeight("455");
        
        String strWidth = "350";

        txtCantidad.setWidth(strWidth);
        txtDescripcion.setWidth(strWidth);
        
        txtCantidad.setValue("1");
        txtDescripcion.setEnabled(false);
        
        txtCantidad.addFocusListener((event) -> {            
            txtCantidad.setSelection(0, txtCantidad.getValue().length());
        });

        binder.forField(txtCantidad).withValidator(cantidad -> cantidad.length() >= 1 && ManageNumbers.ToInteger(cantidad) >= 1, "Verifique que la cantidad este correcta").withConverter(new StringToIntegerConverter(0, "El valor debe ser numerico.")).bind(RequisicionDet::getCantidad, RequisicionDet::setCantidad);
        binder.forField(txtDescripcion).withValidator(val -> val.length() >= 1 , "Verifique que este un Producto seleccionado").bind(RequisicionDet::getDescripcion, RequisicionDet::setDescripcion);

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
                    partida = new RequisicionDet();
                    partida.setDescripcion(p.getDescripcion_corta());
                    partida.setProducto_id(p.getId());
                    partida.setUnidad_medida(p.getUnidad_medida());
                    partida.setPrecio_unitario(p.getPrecio_compra());
                    partida.setImporte(Integer.parseInt(txtCantidad.getValue()) * partida.getPrecio_unitario());
                    partida.setSubtotal(Integer.parseInt(txtCantidad.getValue()) * partida.getPrecio_unitario());
                    partida.setPorc_iva(p.getPorc_iva());
                    partida.setDescuento(0.0);
                    partida.setIva(Integer.parseInt(txtCantidad.getValue()) * p.getIva_compra());
                    partida.setTotal(partida.getSubtotal() + partida.getIva());
                    
                    partida.setNo_parte(p.getNo_parte());
                    partida.setNo_serie(p.getNo_serie());
                    partida.setModelo(p.getModelo());
                    partida.setMarca(p.getMarca());
                    partida.setCodigo_interno(p.getCodigo_interno());
                    partida.setPartida_cotizacion_id(0);
                    partida.setCotizacion_id(0);
                    partida.setFolio_cotizacion("");

                    txtDescripcion.setValue(p.getDescripcion_corta());
                }
            });
            getUI().addWindow(window);
        });
        
        txtCantidad.addValueChangeListener((event) -> {
            if(ManageNumbers.ToInteger(txtCantidad.getValue())==0){
                txtCantidad.setValue("1");
            }            
        });

        btnGuardar.addClickListener((event) -> {
            if (!txtDescripcion.getValue().isEmpty() && !txtCantidad.getValue().isEmpty()) {

                try {
                    binder.writeBean(partida);

                    partida.setUsuario(empleado.getNombre_completo());
                    partida.setUsuario_id(empleado.getId());
                    partida.setEmpresa_id(0);
                    partida.setEmpresa("");
                    partida.setUnidad_id(0);
                    partida.setUnidad("");
                    partida.setFecha_alta(new Date());
                    partida.setCantidad(ManageNumbers.ToInteger(txtCantidad.getValue()));
                    partida.setImporte(partida.getCantidad() * partida.getPrecio_unitario());
                    partida.setDescuento(0.0);
                    partida.setSubtotal(partida.getImporte() - partida.getDescuento());
                    partida.setIva(Integer.parseInt(txtCantidad.getValue()) * (partida.getSubtotal() * partida.getPorc_iva()));
                    partida.setTotal(partida.getSubtotal() + partida.getIva());
                    partida.setPartida_cotizacion_id(0);
                    partida.setCotizacion_id(0);
                    partida.setFolio_cotizacion("");
                    
                    RequisicionDetDomain domain = new RequisicionDetDomain();
                    
                    if(requisicion.getId()!= 0 && requisicion.getId()!= null){
                        if(isEdit){
                            domain.RequisicionDetUpdate(partida);
                        }else{
                            partida.setFolio(requisicion.getFolio());
                            partida.setDocumento_id(requisicion.getId());
                            domain.RequisicionDetInsert(partida);
                        }
                    }else{
                        VaadinSession.getCurrent().getSession().setAttribute("REQUISICION_DET",partida);
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
        fLay.addComponents(btnProducto,txtCantidad, txtDescripcion);
        
        cont.addComponents(fLay, new HorizontalLayout(btnCancelar, btnGuardar));
        
        cont.setComponentAlignment(cont.getComponent(0), Alignment.MIDDLE_LEFT);
        cont.setComponentAlignment(cont.getComponent(1), Alignment.MIDDLE_CENTER);
        
        center();
        setModal(true);
        setResizable(false);
        setContent(cont);
    }
}
