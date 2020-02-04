/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rubik.erp.modulo.compras;

import com.rubik.erp.domain.ProductoDomain;
import com.rubik.erp.domain.ProveedorDomain;
import com.rubik.erp.model.Empleado;
import com.rubik.erp.model.Producto;
import com.rubik.erp.model.Proveedor;
import com.rubik.variables.UnidadesDeMedida;
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
public class WindowProducto extends Window {
    
    Empleado empleado = (Empleado) VaadinSession.getCurrent().getSession().getAttribute("USUARIO_ACTIVO");
    
    VerticalLayout cont = new VerticalLayout();
    String title_window = "CATALOGOS DE PRODUCTOS";

    public Producto producto = new Producto();
    public Boolean isEdit = false;

    TextField txtCodigoInterno = new TextField("Codigo Interno:");
    TextField txtDescripcionCorta = new TextField("Desc. Corta:");
    TextArea txtDescripcion = new TextArea("Descripcion:");
    NativeSelect<String> cboClasificacion = new NativeSelect("Clasificacion:");
    TextField txtModelo = new TextField("Modelo:");
    TextField txtNoParte = new TextField("No Parte:");
    TextField txtNoSerie = new TextField("No Serie:");
    TextField txtMarca = new TextField("Marca:");
    NativeSelect<String> cboUnidadMedida = new NativeSelect("Unidad:");
    
    CheckBox chkInventariable = new CheckBox("Inventariable", true);
    TextField txtInventarioActual = new TextField("Inv. Actual:");
    TextField txtInventarioMaximo = new TextField("Inv. Maximo:");
    TextField txtInventarioMinimo = new TextField("Inv. Minimo:");
    
    NativeSelect<Double> cboIVA = new NativeSelect("% IVA:");
    TextField txtPrecioCompra = new TextField("Precio Compra:");
    TextField txtPrecioVenta = new TextField("Precio Venta:");
    TextField txtUtilidad = new TextField("Utilidad: % ");
    
    NativeSelect<Proveedor> cboProveedor1 = new NativeSelect("Proveedor 1:");
    NativeSelect<Proveedor> cboProveedor2 = new NativeSelect("Proveedor 2:");
    
    List<Proveedor> proveedorList1 = new ArrayList<>();
    List<Proveedor> proveedorList2 = new ArrayList<>();

    CheckBox chkActivo = new CheckBox("Activo", true);
    
    Button btnGuardar = new Button("Guardar", Fam3SilkIcon.DISK);
    Button btnCancelar = new Button("Cancelar", Fam3SilkIcon.CANCEL);

    public WindowProducto() {
        setCaption("ALTA DEL PRODUCTO");
        initComponents();
        
        ProductoDomain service = new ProductoDomain();
        txtCodigoInterno.setValue(service.getMaxID().toString());
    }

    public WindowProducto(Producto prod) {
        setCaption("MODIFICACION DEL PRODUCTO");
        isEdit = true;
        this.producto = prod;
        initComponents();
    }

    public void initComponents() {
        setContent(cont);
        setResizable(false);
        
        String strWidth = "400";
        
        setWidth("600");
        setHeight("80%");
    
        Binder<Producto> binder = new Binder<>();
        binder.forField(txtCodigoInterno).bind(Producto::getCodigo_interno, Producto::setCodigo_interno);
        binder.forField(txtDescripcionCorta).bind(Producto::getDescripcion_corta, Producto::setDescripcion_corta);
        binder.forField(txtDescripcion).bind(Producto::getDescripcion, Producto::setDescripcion);
        binder.forField(cboClasificacion).bind(Producto::getClasificacion, Producto::setClasificacion);
        binder.forField(txtModelo).bind(Producto::getModelo, Producto::setModelo);
        binder.forField(txtNoParte).bind(Producto::getNo_parte, Producto::setNo_parte);
        binder.forField(txtNoSerie).bind(Producto::getNo_serie, Producto::setNo_serie);
        binder.forField(txtMarca).bind(Producto::getMarca, Producto::setMarca);
        binder.forField(cboUnidadMedida).bind(Producto::getUnidad_medida, Producto::setUnidad_medida);
        binder.forField(chkInventariable).bind(Producto::getInventariable, Producto::setInventariable);
        binder.forField(txtInventarioActual).withConverter(new StringToIntegerConverter(0, "El valor debe ser numerico")).bind(Producto::getInventario_actual, Producto::setInventario_actual);
        binder.forField(txtInventarioMaximo).withConverter(new StringToIntegerConverter(0, "El valor debe ser numerico")).bind(Producto::getInventario_maximo, Producto::setInventario_maximo);
        binder.forField(txtInventarioMinimo).withConverter(new StringToIntegerConverter(0, "El valor debe ser numerico")).bind(Producto::getInventario_minimo, Producto::setInventario_minimo);
        binder.forField(cboIVA).bind(Producto::getPorc_iva, Producto::setPorc_iva);
        binder.forField(txtPrecioCompra).withConverter(new StringToDoubleConverter(0.0, "El valor debe ser numerico")).bind(Producto::getPrecio_compra, Producto::setPrecio_compra);
        binder.forField(txtPrecioVenta).withConverter(new StringToDoubleConverter(0.0, "El valor debe ser numerico")).bind(Producto::getPrecio_venta, Producto::setPrecio_venta);
        binder.forField(txtUtilidad).withConverter(new StringToDoubleConverter(0.0, "El valor debe ser numerico")).bind(Producto::getPorc_utilidad, Producto::setPorc_utilidad);
        binder.forField(chkActivo).bind(Producto::getActivo, Producto::setActivo);
        
        cboProveedor1.setItems(getProveedor(1));
        cboProveedor1.setSelectedItem(proveedorList1.get(0));
        cboProveedor1.setEmptySelectionAllowed(false);
        
        cboProveedor2.setItems(getProveedor(2));
        cboProveedor2.setSelectedItem(proveedorList2.get(0));
        cboProveedor2.setEmptySelectionAllowed(false);
        
        if (isEdit) {
            binder.readBean(producto);
            
            for (Proveedor prov : proveedorList1) {
                if (producto.getProveedor_id_1().equals(prov.getId())) {
                    cboProveedor1.setValue(prov);
                }
            }
            
            for (Proveedor prov : proveedorList2) {
                if (producto.getProveedor_id_2().equals(prov.getId())) {
                    cboProveedor2.setValue(prov);
                }
            }
        }

        btnCancelar.addClickListener((event) -> {
            close();
        });

        btnGuardar.addClickListener((event) -> {
            try {
                binder.writeBean(producto);
                toUpperCase();

                ProductoDomain service = new ProductoDomain();

                Proveedor prov1 = cboProveedor1.getValue();
                Proveedor prov2 = cboProveedor2.getValue();
                
                if(prov1 != null){
                    producto.setProveedor_1(prov1.getRazon_social());
                    producto.setProveedor_id_1(prov1.getId());
                }else{
                    producto.setProveedor_1("");
                    producto.setProveedor_id_1(0);
                }
                
                if(prov2 != null){
                    producto.setProveedor_2(prov2.getRazon_social());
                    producto.setProveedor_id_2(prov2.getId());                    
                }else{
                    producto.setProveedor_2("");
                    producto.setProveedor_id_2(0);
                }
                
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
        txtDescripcion.setRows(3);
        
        cboClasificacion.setEmptySelectionAllowed(false);
        cboClasificacion.setItems("PRODUCTO", "SERVICIO");
        cboClasificacion.setValue("PRODUCTO");
        
        txtModelo.setMaxLength(50);
        txtNoParte.setMaxLength(50);
        txtNoSerie.setMaxLength(50);
        txtMarca.setMaxLength(50);
        
        cboUnidadMedida.setEmptySelectionAllowed(false);
        cboUnidadMedida.setItems(UnidadesDeMedida.UNIDADES_LIST);
        cboUnidadMedida.setValue(UnidadesDeMedida.UNIDADES_LIST.get(0));
        
        txtInventarioActual.setMaxLength(4);
        txtInventarioMaximo.setMaxLength(4);
        txtInventarioMinimo.setMaxLength(4);
        
        cboIVA.setEmptySelectionAllowed(false);
        cboIVA.setItems(0.0, 16.0);
        cboIVA.setValue(0.0);
        
        txtPrecioCompra.setMaxLength(12);
        txtPrecioVenta.setMaxLength(12);
        txtUtilidad.setMaxLength(4);
        
        txtCodigoInterno.setWidth(strWidth);
        txtDescripcionCorta.setWidth(strWidth);
        txtDescripcion.setWidth(strWidth);
        cboClasificacion.setWidth(strWidth);
        txtModelo.setWidth(strWidth);
        txtNoParte.setWidth(strWidth);
        txtNoSerie.setWidth(strWidth);
        txtMarca.setWidth(strWidth);
        cboUnidadMedida.setWidth(strWidth);
        txtInventarioActual.setWidth(strWidth);
        txtInventarioMaximo.setWidth(strWidth);
        txtInventarioMinimo.setWidth(strWidth);
        cboIVA.setWidth(strWidth);
        txtPrecioCompra.setWidth(strWidth);
        txtPrecioVenta.setWidth(strWidth);
        txtUtilidad.setWidth(strWidth);
        cboProveedor1.setWidth(strWidth);
        cboProveedor2.setWidth(strWidth);
        
        FormLayout fLay = new FormLayout();
        
        fLay.addComponents(new Label("Informacion del Producto"){{setStyleName("h3");}});
        fLay.addComponents(txtCodigoInterno, chkActivo, txtDescripcionCorta, txtDescripcion, 
                cboClasificacion, txtModelo, txtNoParte, txtNoSerie, txtMarca,cboUnidadMedida);
        fLay.addComponents(new Label("Inventario"){{setStyleName("h3");}});
        fLay.addComponents(chkInventariable, txtInventarioActual, txtInventarioMaximo,txtInventarioMinimo);
        fLay.addComponents(new Label("Precios & Costos"){{setStyleName("h3");}});
        fLay.addComponents(cboIVA, txtPrecioCompra, txtPrecioVenta,txtUtilidad);
        fLay.addComponents(new Label("Proveedores"){{setStyleName("h3");}});
        fLay.addComponents(cboProveedor1, cboProveedor2);
     
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
    
    public List<Proveedor> getProveedor(int prov) {
        ProveedorDomain provService = new ProveedorDomain();
        provService.getProveedor("", "", "razon_social ASC");
        
        if(prov == 1){
            proveedorList1 = provService.getObjects();
            return proveedorList1;
        }else{
            proveedorList2 = provService.getObjects();
            return proveedorList2;
        }
    }
    
}