/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rubik.erp.modulo.compras;

import com.rubik.erp.domain.ProductoDomain;
import com.rubik.erp.model.Empleado;
import com.rubik.erp.model.Producto;
import com.rubik.variables.UnidadesDeMedida;
import com.vaadin.data.Binder;
import com.vaadin.data.converter.StringToDoubleConverter;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import de.steinwedel.messagebox.MessageBox;
import java.util.Date;
import org.rubicone.vaadin.fam3.silk.Fam3SilkIcon;

/**
 *
 * @author GRUCAS
 */
public class WindowProductoSimple extends Window {
    
    Empleado empleado = (Empleado) VaadinSession.getCurrent().getSession().getAttribute("USUARIO_ACTIVO");
    
    VerticalLayout cont = new VerticalLayout();
    String title_window = "CATALOGOS DE PRODUCTOS";

    public Producto producto = new Producto();
    public Boolean isEdit = false;

    TextField txtCodigoInterno = new TextField("Codigo Interno:");
    TextField txtDescripcionCorta = new TextField("Desc. Corta:");
    TextArea txtDescripcion = new TextArea("Descripcion:");
    TextField txtModelo = new TextField("Modelo:");
    TextField txtNoParte = new TextField("No Parte:");
    TextField txtNoSerie = new TextField("No Serie:");
    TextField txtMarca = new TextField("Marca:");
    NativeSelect<String> cboUnidadMedida = new NativeSelect("Unidad:");
    NativeSelect<Integer> cboIVA = new NativeSelect("% IVA:");
    
    TextField txtPrecioCompra = new TextField("Precio Compra:");
    
    Button btnGuardar = new Button("Guardar", Fam3SilkIcon.DISK);
    Button btnCancelar = new Button("Cancelar", Fam3SilkIcon.CANCEL);

    public WindowProductoSimple() {
        setCaption("ALTA DEL PRODUCTO");
        initComponents();
        
        ProductoDomain service = new ProductoDomain();
        txtCodigoInterno.setValue(service.getMaxID().toString());
    }

    public WindowProductoSimple(Producto prod) {
        setCaption("MODIFICACION DEL PRODUCTO");
        isEdit = true;
        this.producto = prod;
        initComponents();
    }

    public void initComponents() {
        setContent(cont);
        setResizable(false);
        
        String strWidth = "100%";
        
        setWidth("75%");
        setHeight("80%");
    
        Binder<Producto> binder = new Binder<>();
        binder.forField(txtCodigoInterno).bind(Producto::getCodigo_interno, Producto::setCodigo_interno);
        binder.forField(txtDescripcionCorta).bind(Producto::getDescripcion_corta, Producto::setDescripcion_corta);
        binder.forField(txtDescripcion).bind(Producto::getDescripcion, Producto::setDescripcion);
        binder.forField(txtModelo).bind(Producto::getModelo, Producto::setModelo);
        binder.forField(txtNoParte).bind(Producto::getNo_parte, Producto::setNo_parte);
        binder.forField(txtNoSerie).bind(Producto::getNo_serie, Producto::setNo_serie);
        binder.forField(txtMarca).bind(Producto::getMarca, Producto::setMarca);
        binder.forField(cboUnidadMedida).bind(Producto::getUnidad_medida, Producto::setUnidad_medida);
        binder.forField(txtPrecioCompra).withConverter(new StringToDoubleConverter(0.0, "El valor debe ser numerico")).bind(Producto::getPrecio_compra, Producto::setPrecio_compra);
        binder.forField(cboIVA).bind(Producto::getPorc_iva, Producto::setPorc_iva);

        btnCancelar.addClickListener((event) -> {
            close();
        });

        btnGuardar.addClickListener((event) -> {
            try {
                binder.writeBean(producto);
                toUpperCase();

                ProductoDomain service = new ProductoDomain();
                
                producto.setActivo(true);
                
                if (isEdit) {
                    producto.setFecha_modificacion(new Date());
                    service.ProductoUpdate(producto);
                } else {
                    producto.setFecha_elaboracion(new Date());
                    producto.setFecha_modificacion(new Date());
                    service.ProductoInsert(producto);
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
        
        txtCodigoInterno.setEnabled(false);
        txtDescripcionCorta.setMaxLength(100);
        
        txtDescripcion.setRows(7);
        
        txtModelo.setMaxLength(50);
        txtNoParte.setMaxLength(50);
        txtNoSerie.setMaxLength(50);
        txtMarca.setMaxLength(50);
        
        cboUnidadMedida.setEmptySelectionAllowed(false);
        cboUnidadMedida.setItems(UnidadesDeMedida.UNIDADES_LIST);
        cboUnidadMedida.setValue(UnidadesDeMedida.UNIDADES_LIST.get(0));

        cboIVA.setEmptySelectionAllowed(false);
        cboIVA.setItems(0, 16);
        cboIVA.setValue(16);
        
        txtPrecioCompra.setMaxLength(12);
        
        txtCodigoInterno.setWidth(strWidth);
        txtDescripcionCorta.setWidth(strWidth);
        txtDescripcion.setWidth(strWidth);
        txtModelo.setWidth(strWidth);
        txtNoParte.setWidth(strWidth);
        txtNoSerie.setWidth(strWidth);
        txtMarca.setWidth(strWidth);
        cboIVA.setWidth(strWidth);
        cboUnidadMedida.setWidth(strWidth);
        txtPrecioCompra.setWidth(strWidth);
        
        if (isEdit) {
            binder.readBean(producto);
        }
        
        FormLayout fLay = new FormLayout();
        
        fLay.addComponents(new Label("Informacion Basica del Producto"){{setStyleName("h3");}});
        fLay.addComponents(txtCodigoInterno, txtDescripcionCorta, txtDescripcion, txtModelo, txtNoParte, txtNoSerie, 
                txtMarca, cboUnidadMedida, cboIVA, txtPrecioCompra);
     
        cont.addComponents(fLay, new HorizontalLayout(btnCancelar, btnGuardar));
        cont.setComponentAlignment(cont.getComponent(1), Alignment.MIDDLE_CENTER);
    }
    
    public void toUpperCase() {
        producto.setDescripcion_corta(txtDescripcionCorta.getValue().toUpperCase());
        producto.setDescripcion(txtDescripcion.getValue().toUpperCase());
        producto.setModelo(txtModelo.getValue().toUpperCase());
        producto.setMarca(txtMarca.getValue().toUpperCase());
        producto.setNo_serie(txtNoSerie.getValue().toUpperCase());
    }
    
}