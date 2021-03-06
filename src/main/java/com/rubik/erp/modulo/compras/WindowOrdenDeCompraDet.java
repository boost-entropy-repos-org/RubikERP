/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rubik.erp.modulo.compras;

import com.rubik.erp.domain.OrdenDeCompraDetDomain;
import com.rubik.erp.model.Empleado;
import com.rubik.erp.model.OrdenDeCompra;
import com.rubik.erp.model.OrdenDeCompraDet;
import com.rubik.erp.model.Producto;
import com.rubik.erp.modulo.generic.WindowSeleccionarProducto;
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
public class WindowOrdenDeCompraDet extends Window {

    Empleado empleado = (Empleado) VaadinSession.getCurrent().getSession().getAttribute("USUARIO_ACTIVO");

    OrdenDeCompraDet partida = null;
    Boolean isEdit = false;

    VerticalLayout cont = new VerticalLayout();

    TextField txtCantidad = new TextField("Cantidad:");
    TextArea txtDescripcion = new TextArea("Descripcion:");
    TextField txtImporte = new TextField("Importe:");

    Button btnCancelar = new Button("Cancelar", Fam3SilkIcon.CANCEL);
    Button btnGuardar = new Button("Guardar", Fam3SilkIcon.DISK);
    Button btnProducto = new Button("Seleccionar Producto", Fam3SilkIcon.MAGNIFIER);
    
    Binder<OrdenDeCompraDet> binder = new Binder<>();
    List<OrdenDeCompraDet> listRequiDet = new ArrayList<>();
    
    OrdenDeCompra ordenDeCompra = new OrdenDeCompra();
    
    public WindowOrdenDeCompraDet(OrdenDeCompra oc) {
        ordenDeCompra = oc;
        VaadinSession.getCurrent().getSession().setAttribute("ORDEN_COMPRA_DET",null);
        VaadinSession.getCurrent().getSession().setAttribute("PARTIDA_COMPRA_OK",false);
        initComponents();
        partida = new OrdenDeCompraDet();
    }
    
    public WindowOrdenDeCompraDet(OrdenDeCompra oc, OrdenDeCompraDet partida) {
        isEdit = true;
        ordenDeCompra = oc;
        this.partida = partida;
        VaadinSession.getCurrent().getSession().setAttribute("ORDEN_COMPRA_DET",null);
        VaadinSession.getCurrent().getSession().setAttribute("PARTIDA_COMPRA_OK",false);
        initComponents();
    }

    public void initComponents() {
        setCaption("PARTIDAS DE LA ORDEN DE COMPRA");
        setWidth("490");
        setHeight("455");
        
        String strWidth = "350";

        txtCantidad.setWidth(strWidth);
        txtDescripcion.setWidth(strWidth);
        txtImporte.setWidth(strWidth);
        
        txtCantidad.setValue("1");
        txtDescripcion.setEnabled(false);
        
        txtCantidad.addFocusListener((event) -> {            
            txtCantidad.setSelection(0, txtCantidad.getValue().length());
        });
        
        txtImporte.addFocusListener((event) -> {            
            txtImporte.setSelection(0, txtImporte.getValue().length());
        });

        binder.forField(txtCantidad).withConverter(new StringToIntegerConverter(0, "El valor debe ser numerico.")).bind(OrdenDeCompraDet::getCantidad, OrdenDeCompraDet::setCantidad);
        binder.forField(txtDescripcion).withValidator(val -> val.length() >= 1 , "Verifique que este un Producto seleccionado").bind(OrdenDeCompraDet::getDescripcion, OrdenDeCompraDet::setDescripcion);
        binder.forField(txtImporte).withConverter(new StringToDoubleConverter(0.0, "El valor debe ser numerico.")).bind(OrdenDeCompraDet::getPrecio_unitario, OrdenDeCompraDet::setPrecio_unitario);

        if (isEdit) {
            binder.readBean(partida);
        }

        btnCancelar.addClickListener((event) -> {
            VaadinSession.getCurrent().getSession().setAttribute("PARTIDA_COMPRA_OK",false);
            close();
        });
        
        btnProducto.addClickListener((event) -> {
            WindowSeleccionarProducto window = new WindowSeleccionarProducto();
            window.addCloseListener(ev -> {
                
                Producto p = (Producto) VaadinSession.getCurrent().getSession().getAttribute("PRODUCTO_SELECCIONADO");
                if(p != null){
                    partida = new OrdenDeCompraDet();
                    partida.setDescripcion(p.getDescripcion_corta());
                    partida.setProducto_id(p.getId());
                    partida.setUnidad_medida(p.getUnidad_medida());
                    partida.setPrecio_unitario(p.getPrecio_compra());
                    
                    partida.setNo_parte(p.getNo_parte());
                    partida.setNo_serie(p.getNo_serie());
                    partida.setModelo(p.getModelo());
                    partida.setMarca(p.getMarca());
                    partida.setCodigo_interno(p.getCodigo_interno());

                    txtDescripcion.setValue(p.getDescripcion_corta());
                    txtImporte.setValue(p.getPrecio_compra().toString());
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
            if (!txtDescripcion.getValue().isEmpty() && !txtCantidad.getValue().isEmpty() && !txtImporte.getValue().isEmpty()) {

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
                    partida.setImporte(partida.getCantidad() * partida.getPrecio_unitario());
                    
                    OrdenDeCompraDetDomain domain = new OrdenDeCompraDetDomain();
                    
                    if(ordenDeCompra != null){
                        if(isEdit){
                            domain.OrdenDeCompraDetUpdate(partida);
                        }else{
                            partida.setFolio(ordenDeCompra.getFolio());
                            partida.setDocumento_id(ordenDeCompra.getId());
                            domain.OrdenDeCompraDetInsert(partida);
                        }
                    }
                    VaadinSession.getCurrent().getSession().setAttribute("ORDEN_COMPRA_DET",partida);
                    VaadinSession.getCurrent().getSession().setAttribute("PARTIDA_COMPRA_OK",true);
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
        fLay.addComponents(btnProducto,txtCantidad, txtDescripcion, txtImporte);
        
        cont.addComponents(fLay, new HorizontalLayout(btnCancelar, btnGuardar));
        
        cont.setComponentAlignment(cont.getComponent(0), Alignment.MIDDLE_LEFT);
        cont.setComponentAlignment(cont.getComponent(1), Alignment.MIDDLE_CENTER);
        
        center();
        setModal(true);
        setResizable(false);
        setContent(cont);
    }
}
