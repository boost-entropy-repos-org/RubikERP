/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rubik.erp.modulo.ventas;

import com.rubik.erp.config.DomainConfig;
import com.rubik.erp.config.FactorySession;
import com.rubik.erp.config._DocumentoEstados;
import com.rubik.erp.config._Puestos;
import com.rubik.erp.domain.CotizacionVentaDomain;
import com.rubik.erp.fragments.FragmentTop;
import com.rubik.erp.model.CotizacionVenta;
import com.rubik.erp.model.Empleado;
import com.rubik.erp.util.EmbedWindow;
import com.rubik.manage.ManageDates;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.StreamResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import de.steinwedel.messagebox.MessageBox;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;
import org.rubicone.vaadin.fam3.silk.Fam3SilkIcon;

/**
 *
 * @author Dev
 */
public class VentasCotizaciones extends Panel implements View {

    public static final String NAME = "COTIZACIONES_VENTA";
    VerticalLayout container = new VerticalLayout();

    Empleado empleado = (Empleado) VaadinSession.getCurrent().getSession().getAttribute("USUARIO_ACTIVO");

    TextField txtBusqueda = new TextField();
    
    Grid<CotizacionVenta> gridCotizaciones = new Grid<>();
    DateField txtFechaIni = new DateField();
    DateField txtFechaFin = new DateField();
    Button btnSearch = new Button(Fam3SilkIcon.MAGNIFIER);
    
    NativeSelect<String> cboEstadoDocumento = new NativeSelect();
    
    Button btnAdd = new Button("Agregar", Fam3SilkIcon.ADD);
    Button btnModify = new Button("Modificar", Fam3SilkIcon.PENCIL);
    Button btnCancel = new Button("Cancelar", Fam3SilkIcon.CANCEL);
    Button btnTerminar = new Button("Terminar", Fam3SilkIcon.NOTE_GO);
    Button btnPrint = new Button("Imprimir", Fam3SilkIcon.PRINTER);
    
    List<CotizacionVenta> listOC = new ArrayList<>();

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    
    public VentasCotizaciones() {
        initComponents();

        HorizontalLayout hLayoutAux = new HorizontalLayout(
                cboEstadoDocumento,
                txtBusqueda,
                new Label("Fecha: "),txtFechaIni,new Label("A: "),txtFechaFin,btnSearch);
        
        HorizontalLayout hLayoutAux2 = new HorizontalLayout(btnAdd, btnModify, btnCancel, btnPrint, btnTerminar);
        
        hLayoutAux.setComponentAlignment(hLayoutAux.getComponent(1), Alignment.MIDDLE_CENTER);
        hLayoutAux.setComponentAlignment(hLayoutAux.getComponent(3), Alignment.MIDDLE_CENTER);
        
        Label lblTitulo = new Label("COTIZACIONES DE VENTA") {
            {
                setStyleName("h1");
            }
        };
        lblTitulo.setSizeUndefined();

        container.setMargin(false);
        container.addComponents(new FragmentTop(),
                lblTitulo,
                hLayoutAux,
                hLayoutAux2,
                gridCotizaciones);
        
        container.setComponentAlignment(container.getComponent(0), Alignment.MIDDLE_CENTER);
        container.setComponentAlignment(container.getComponent(1), Alignment.MIDDLE_CENTER);
        container.setComponentAlignment(container.getComponent(2), Alignment.MIDDLE_CENTER);
        container.setComponentAlignment(container.getComponent(3), Alignment.MIDDLE_CENTER);

        setContent(container);
    }

    public void initComponents() {
        setSizeFull();

        txtFechaIni.setWidth("115");
        txtFechaFin.setWidth("115");
        
        txtBusqueda.setWidth("200");
        txtBusqueda.setPlaceholder("Folio Cotizacion");
        
        cboEstadoDocumento.setItems("TODOS", _DocumentoEstados.EN_PROCESO, _DocumentoEstados.TERMINADO,_DocumentoEstados.CANCELADO);
        cboEstadoDocumento.setEmptySelectionAllowed(false);
        cboEstadoDocumento.setValue(_DocumentoEstados.EN_PROCESO);
        
        Grid.Column<CotizacionVenta, String> columnFecha = gridCotizaciones.addColumn(det -> ((det.getFecha_elaboracion()!= null) ? dateFormat.format(det.getFecha_elaboracion()) : ""));
        columnFecha.setCaption("FECHA");
        columnFecha.setId("FECHA");
        columnFecha.setWidth(120);
        gridCotizaciones.addColumn(CotizacionVenta::getFolio).setCaption("FOLIO").setId("FOLIO").setWidth(120);
        gridCotizaciones.addColumn(CotizacionVenta::getEstado_doc).setCaption("ESTADO").setId("ESTADO").setWidth(130);
        gridCotizaciones.addColumn(CotizacionVenta::getCliente).setCaption("CLIENTE").setId("CLIENTE");
        gridCotizaciones.addColumn(CotizacionVenta::getImporte).setCaption("IMPORTE").setId("IMPORTE").setWidth(120);
        gridCotizaciones.addColumn(CotizacionVenta::getIva).setCaption("IVA").setId("IVA").setWidth(120);
        gridCotizaciones.addColumn(CotizacionVenta::getTotal).setCaption("TOTAL").setId("TOTAL").setWidth(120);

        gridCotizaciones.setItems(getCotizacionesVentas());
        gridCotizaciones.setSelectionMode(Grid.SelectionMode.SINGLE);
        gridCotizaciones.setSizeFull();
        gridCotizaciones.setHeight("500px");

        btnAdd.addClickListener((event) -> {
            WindowVentasCotizaciones windows = new WindowVentasCotizaciones();
            windows.center();
            windows.setModal(true);
            windows.addCloseListener(ev -> {
                gridCotizaciones.setItems(getCotizacionesVentas());
            });
            getUI().addWindow(windows);
        });

        btnModify.addClickListener((event) -> {
            if (gridCotizaciones.getSelectedItems().size() == 1) {
                if(!gridCotizaciones.getSelectedItems().iterator().next().getEstado_doc().equals(_DocumentoEstados.TERMINADO)){
                    WindowVentasCotizaciones windows = new WindowVentasCotizaciones(gridCotizaciones.getSelectedItems().iterator().next());
                    windows.center();
                    windows.setModal(true);
                    windows.addCloseListener((e) -> {
                        gridCotizaciones.setItems(getCotizacionesVentas());
                    });
                    getUI().addWindow(windows);
                }else{
                    MessageBox.createError()
                            .withCaption("Error!")
                            .withMessage("No puede modificar una Requisicion de Compra que esta en espera de su Autorizacion.")
                            .withRetryButton()
                            .open();
                }
            } else {
                MessageBox.createError()
                        .withCaption("Error!")
                        .withMessage("Debe tener una Requisicion seleccionada para poder modificarla.")
                        .withRetryButton()
                        .open();
            }
        });
        
        btnSearch.addClickListener((event) -> {
            gridCotizaciones.setItems(getCotizacionesVentas());
            txtBusqueda.setValue("");
        });
        
        btnPrint.addClickListener((event) -> {
            if (gridCotizaciones.getSelectedItems().size() == 1) {
                CotizacionVenta ocTemp = gridCotizaciones.getSelectedItems().iterator().next();

                try {
                    final HashMap map = new HashMap();
                    map.put("folio", ocTemp.getFolio());

                    StreamResource.StreamSource source = new StreamResource.StreamSource() {
                        @Override
                        public InputStream getStream() {
                            byte[] b = null;
                            try {
                                InputStream fileStream = getClass().getClassLoader().getResourceAsStream("/reportes/CotizacionDeVenta.jasper");
                                b = JasperRunManager.runReportToPdf(fileStream, map, FactorySession.getRubikConnection(DomainConfig.getEnvironment()));

                            } catch (JRException ex) {
                                ex.printStackTrace();
                            }
                            return new ByteArrayInputStream(b);
                        }
                    };

                    StreamResource resource = new StreamResource(source, "COTIZACION_" + ocTemp.getFolio() + ".pdf");

                    EmbedWindow windowPDF = new EmbedWindow(resource);
                    windowPDF.setCaption("Cotizacion de Venta:");
                    windowPDF.setHeight("100%");
                    windowPDF.setWidth("80%");
                    windowPDF.setMimeType("application/pdf");
                    windowPDF.setDraggable(false);
                    windowPDF.setResizable(false);
                    windowPDF.setScrollLeft(15);
                    windowPDF.center();
                    windowPDF.setModal(true);
                    windowPDF.insertEmbedded();
                    getUI().addWindow(windowPDF);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else {
                MessageBox.createError()
                        .withCaption("Error!")
                        .withMessage("Debe seleccionar una Cotizacion de Venta para poder imprimirla.")
                        .withRetryButton()
                        .open();
            }
        });
        
        btnTerminar.addClickListener((event) -> {
            if (gridCotizaciones.getSelectedItems().size() == 1) {
                
                CotizacionVenta cotVenta = gridCotizaciones.getSelectedItems().iterator().next();
                
                if(cotVenta.getEstado_doc().equals(_DocumentoEstados.EN_PROCESO)){
                    
                    MessageBox.createQuestion()
                        .withCaption("Atencion!")
                        .withMessage("Desea que la Cotizacion de Venta " + cotVenta.getFolio() + ""
                                + " sea terminada? Ya no podrá realizar modificaciones.")
                        .withOkButton(() -> {
                            
                            cotVenta.setEstado_doc(_DocumentoEstados.TERMINADO);
                            
                            CotizacionVentaDomain domain = new CotizacionVentaDomain();
                            domain.CotizacionVentaUpdate(cotVenta);
                            
                            gridCotizaciones.setItems(getCotizacionesVentas());
                            
                            MessageBox.createInfo()
                                    .withCaption("Error!")
                                    .withMessage("Cotizacion de Venta terminada correctamente.")
                                    .withRetryButton()
                                    .open();
                        })
                        .withNoButton(() -> {})
                        .open();
                    
                }else{
                    MessageBox.createError()
                        .withCaption("Error!")
                        .withMessage("Con el estado " + cotVenta.getEstado_doc() + " de la Cotizacion " + cotVenta.getFolio() + ""
                                + " no es posible pasar a Autorizacion.")
                        .withRetryButton()
                        .open();
                }
            } else {
                MessageBox.createError()
                        .withCaption("Error!")
                        .withMessage("Debe tener una Cotizacion de Venta seleccionada para poder terminarlo.")
                        .withRetryButton()
                        .open();
            }
        });

    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }

    public List getCotizacionesVentas() {
        String strWhere = " activo = 1 ";
        
        if(empleado.getPuesto().equals(_Puestos.AUXILIAR_VENTAS)){
            strWhere += " AND usuario_id = " + empleado.getId();
        }

        if (!"".equals(txtBusqueda.getValue())) {
            strWhere += " AND folio = '" + txtBusqueda.getValue().toUpperCase() + "'";
        }
        
        if (txtFechaIni.getValue() != null && txtFechaFin.getValue() != null) {
            SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");

            strWhere
                    += " AND fecha_elaboracion >= '" + dt.format(ManageDates.getDateFromLocalDate(txtFechaIni.getValue())) + " 00:00:00"
                    + "' AND fecha_elaboracion <= '" + dt.format(ManageDates.getDateFromLocalDate(txtFechaFin.getValue())) + " 23:59:59'";
        }
        
        if(!"TODOS".equals(cboEstadoDocumento.getValue())){
            strWhere += " AND estado_doc = '" + cboEstadoDocumento.getValue() + "'";
        }

        CotizacionVentaDomain service = new CotizacionVentaDomain();
        service.getCotizacionVenta(strWhere, "", "fecha_elaboracion DESC");
        listOC = service.getObjects();

        if (!service.getOk()) {
            MessageBox.createError()
                    .withCaption("Error al cargar la informacion!")
                    .withMessage("Err: " + service.getNotification())
                    .withRetryButton()
                    .open();
        }
        return listOC;
    }
    
}