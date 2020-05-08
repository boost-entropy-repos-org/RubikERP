/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rubik.erp.modulo.configuracion;

import com.rubik.erp.domain.ConfiguracionDomain;
import com.rubik.erp.fragments.FragmentTop;
import com.rubik.erp.model.Configuracion;
import com.rubik.erp.model.Empleado;
import com.vaadin.data.Binder;
import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.navigator.View;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import de.steinwedel.messagebox.MessageBox;
import org.rubicone.vaadin.fam3.silk.Fam3SilkIcon;

/**
 *
 * @author Dev
 */
public class ViewConfiguracion extends Panel implements View {

    public static final String NAME = "CONFIGURACION";
    
    Empleado empleado = (Empleado) VaadinSession.getCurrent().getSession().getAttribute("USUARIO_ACTIVO");
    
    VerticalLayout cont = new VerticalLayout();

    Configuracion configuracion = new Configuracion();

    TextField txtSerieRequisicion = new TextField("Serie Requisicion:");
    TextField txtFolioRequisicion = new TextField("Folio Requisicion:");
    
    TextField txtSerieOrdenCompra = new TextField("Serie Orden de Compra:");
    TextField txtFolioOrdenCompra = new TextField("Folio Orden de Compra:");
    
    TextField txtSerieCotizacion = new TextField("Serie Cotizacion:");
    TextField txtFolioCotizacion = new TextField("Folio Cotizacion:");
    
    TextField txtSerieFactura = new TextField("Serie Factura:");
    TextField txtFolioFactura = new TextField("Folio Factura:");
    
    CheckBox chkAutoCompletarTotales = new CheckBox("Autocompletar Totales en Documentos", true);
    
    Button btnGuardar = new Button("Guardar", Fam3SilkIcon.DISK);
    
    public ViewConfiguracion() {
        initComponents();
    }

    public void initComponents() {
        setSizeFull();
        String strWidth = "200";
        
        Label lblTitulo = new Label("CONFIGURACION DEL SISTEMA") {
            {
                setStyleName("h1");
            }
        };
        lblTitulo.setSizeUndefined();
        
        ConfiguracionDomain service = new ConfiguracionDomain();
        service.getConfiguracion();
        configuracion = service.getObject();
    
        Binder<Configuracion> binder = new Binder<>();
        binder.forField(txtSerieRequisicion).bind(Configuracion::getSerie_requisicion, Configuracion::setSerie_requisicion);
        binder.forField(txtFolioRequisicion).withConverter(new StringToIntegerConverter(0, "El valor debe ser numerico")).bind(Configuracion::getFolio_requisicion, Configuracion::setFolio_requisicion);
        binder.forField(txtSerieOrdenCompra).bind(Configuracion::getSerie_orden_compra, Configuracion::setSerie_orden_compra);
        binder.forField(txtFolioOrdenCompra).withConverter(new StringToIntegerConverter(0, "El valor debe ser numerico")).bind(Configuracion::getFolio_orden_compra, Configuracion::setFolio_orden_compra);
        binder.forField(txtSerieCotizacion).bind(Configuracion::getSerie_cotizacion, Configuracion::setSerie_cotizacion);
        binder.forField(txtFolioCotizacion).withConverter(new StringToIntegerConverter(0, "El valor debe ser numerico")).bind(Configuracion::getFolio_cotizacion, Configuracion::setFolio_cotizacion);
        binder.forField(txtSerieFactura).bind(Configuracion::getSerie_factura, Configuracion::setSerie_factura);
        binder.forField(txtFolioFactura).withConverter(new StringToIntegerConverter(0, "El valor debe ser numerico")).bind(Configuracion::getFolio_factura, Configuracion::setFolio_factura);
        binder.forField(chkAutoCompletarTotales).bind(Configuracion::getAutocompletar_totales, Configuracion::setAutocompletar_totales);

        binder.readBean(configuracion);
        
        btnGuardar.addClickListener((event) -> {
            try {
                binder.writeBean(configuracion);
                toUpperCase();

                service.ConfiguracionUpdate(configuracion);
                VaadinSession.getCurrent().getSession().setAttribute("CONFIGURACION", configuracion);
                
                if (service.getOk()) {
                    MessageBox.createInfo()
                            .withCaption("Atencion")
                            .withMessage(service.getNotification())
                            .open();
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
        
        txtSerieRequisicion.setMaxLength(4);
        txtSerieOrdenCompra.setMaxLength(4);
        txtSerieCotizacion.setMaxLength(4);
        txtSerieFactura.setMaxLength(4);
        
        txtFolioRequisicion.setMaxLength(4);
        txtFolioOrdenCompra.setMaxLength(4);
        txtFolioCotizacion.setMaxLength(4);
        txtFolioFactura.setMaxLength(4);
        
        txtSerieRequisicion.setWidth(strWidth);
        txtSerieOrdenCompra.setWidth(strWidth);
        txtSerieCotizacion.setWidth(strWidth);
        txtSerieFactura.setWidth(strWidth);
        txtFolioRequisicion.setWidth(strWidth);
        txtFolioOrdenCompra.setWidth(strWidth);
        txtFolioCotizacion.setWidth(strWidth);
        txtFolioFactura.setWidth(strWidth);

        FormLayout fLay = new FormLayout();
        fLay.setSizeUndefined();
        
        fLay.addComponents(new Label("Folios & Series"){{setStyleName("h3");}});
        fLay.addComponents(txtSerieRequisicion, txtSerieOrdenCompra, txtSerieCotizacion, txtSerieFactura, 
                txtFolioRequisicion, txtFolioOrdenCompra, txtFolioCotizacion, txtFolioFactura);
        fLay.addComponents(new Label("Autocompletar"){{setStyleName("h3");}});
        fLay.addComponents(chkAutoCompletarTotales);
     
        cont.setMargin(false);
        cont.addComponents(new FragmentTop(),lblTitulo,
                fLay, 
                new Label("** Todos los cambios se veran reflejados al volver a iniciar sesion.**"),
                new HorizontalLayout(btnGuardar));
        cont.setComponentAlignment(cont.getComponent(1), Alignment.MIDDLE_CENTER);
        cont.setComponentAlignment(cont.getComponent(2), Alignment.MIDDLE_CENTER);
        cont.setComponentAlignment(cont.getComponent(3), Alignment.MIDDLE_CENTER);
        cont.setComponentAlignment(cont.getComponent(4), Alignment.MIDDLE_CENTER);
        setContent(cont);
        
    }
    
    public void toUpperCase() {
        txtSerieRequisicion.setValue(txtSerieRequisicion.getValue().toUpperCase());
        txtSerieOrdenCompra.setValue(txtSerieOrdenCompra.getValue().toUpperCase());
        txtSerieCotizacion.setValue(txtSerieCotizacion.getValue().toUpperCase());
        txtSerieFactura.setValue(txtSerieFactura.getValue().toUpperCase());
    }
    
}