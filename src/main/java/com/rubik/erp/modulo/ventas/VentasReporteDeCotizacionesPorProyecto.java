/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rubik.erp.modulo.ventas;

import com.rubik.erp.config.DomainConfig;
import com.rubik.erp.config.FactorySession;
import com.rubik.erp.domain.CotizacionVentaDomain;
import com.rubik.erp.domain.ProyectoDomain;
import com.rubik.erp.fragments.FragmentTop;
import com.rubik.erp.model.CotizacionVenta;
import com.rubik.erp.model.Empleado;
import com.rubik.erp.model.Proyecto;
import com.rubik.erp.util.EmbedWindow;
import com.rubik.erp.util.ExportExcelManager;
import com.rubik.manage.ManageDates;
import com.vaadin.navigator.View;
import com.vaadin.server.FileDownloader;
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
import com.vaadin.ui.VerticalLayout;
import de.steinwedel.messagebox.MessageBox;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;
import org.rubicone.vaadin.fam3.silk.Fam3SilkIcon;

/**
 *
 * @author GRUCAS
 */
public class VentasReporteDeCotizacionesPorProyecto extends Panel implements View {

    public static final String NAME = "REPORTE_COTIZACIONES_PROYECTO";
    
    VerticalLayout container = new VerticalLayout();

    Empleado empleado = (Empleado) VaadinSession.getCurrent().getSession().getAttribute("USUARIO_ACTIVO");
   
    Grid<CotizacionVenta> gridCotizaciones = new Grid<>();
    List<CotizacionVenta> listCotizaciones = new ArrayList<>();
    
    NativeSelect<Proyecto> cboProyecto = new NativeSelect();
    List<Proyecto> listProyectos = new ArrayList<>();
   
    DateField txtFechaIni = new DateField();
    DateField txtFechaFin = new DateField();
    
    Button btnSearch = new Button(Fam3SilkIcon.MAGNIFIER);
    Button btnExcel = new Button("Excel", Fam3SilkIcon.PAGE_EXCEL);
    Button btnPrint = new Button("Imprimir", Fam3SilkIcon.PRINTER);
    Button btnGraficaAnual = new Button("Graficas", Fam3SilkIcon.CHART_BAR);
    
    StreamResource.StreamSource resourceFile;
    FileDownloader downloader;

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
   
    public VentasReporteDeCotizacionesPorProyecto() {
        initComponents();

        HorizontalLayout hLayoutAux = new HorizontalLayout(
                cboProyecto, new Label("Fecha: "),txtFechaIni,new Label("A: "),txtFechaFin,
                btnSearch,btnGraficaAnual, btnExcel, btnPrint);
       
        hLayoutAux.setComponentAlignment(hLayoutAux.getComponent(1), Alignment.MIDDLE_CENTER);
        hLayoutAux.setComponentAlignment(hLayoutAux.getComponent(3), Alignment.MIDDLE_CENTER);
       
        Label lblTitulo = new Label("REPORTE DE COTIZACIONES POR PROYECTO") {
            {
                setStyleName("h1");
            }
        };
        lblTitulo.setSizeUndefined();
        
        btnSearch.addClickListener((event) -> {
            gridCotizaciones.setItems(getCotizacionesVentas());
        });

        cboProyecto.addValueChangeListener((event) -> {
            gridCotizaciones.setItems(getCotizacionesVentas());
        });
        
        container.setMargin(false);
        container.addComponents(new FragmentTop(),
                lblTitulo,
                hLayoutAux,
                gridCotizaciones);
       
        container.setComponentAlignment(container.getComponent(0), Alignment.MIDDLE_CENTER);
        container.setComponentAlignment(container.getComponent(1), Alignment.MIDDLE_CENTER);
        container.setComponentAlignment(container.getComponent(2), Alignment.MIDDLE_CENTER);
        container.setComponentAlignment(container.getComponent(3), Alignment.MIDDLE_CENTER);

        setContent(container);
    }

    public void initComponents() {
        setSizeFull();    

        cboProyecto.setItems(getProyectos());
        cboProyecto.setEmptySelectionAllowed(false);
        cboProyecto.setValue(listProyectos.get(0));
        
        txtFechaIni.setWidth("120");
        txtFechaFin.setWidth("120");
        
        txtFechaIni.setValue(ManageDates.getLocalDateFromDate(ManageDates.getFirstDayOfTheMonth()));
        txtFechaFin.setValue(ManageDates.getLocalDateFromDate(ManageDates.getLastDayOfTheMonth()));
        
        gridCotizaciones.addColumn(CotizacionVenta::getProyecto).setCaption("PROYECTO").setId("PROYECTO");
        gridCotizaciones.addColumn(CotizacionVenta::getFolio).setCaption("FOLIO").setId("FOLIO").setWidth(120);
        Grid.Column<CotizacionVenta, String> columnFecha = gridCotizaciones.addColumn(det -> ((det.getFecha_elaboracion()!= null) ? dateFormat.format(det.getFecha_elaboracion()) : ""));
        columnFecha.setCaption("FECHA");
        columnFecha.setId("FECHA");
        columnFecha.setWidth(120);
        gridCotizaciones.addColumn(CotizacionVenta::getEstado_doc).setCaption("ESTADO").setId("ESTADO").setWidth(130);
        gridCotizaciones.addColumn(CotizacionVenta::getImporte).setCaption("IMPORTE").setId("IMPORTE").setWidth(120);
        gridCotizaciones.addColumn(CotizacionVenta::getIva).setCaption("IVA").setId("IVA").setWidth(120);
        gridCotizaciones.addColumn(CotizacionVenta::getTotal).setCaption("TOTAL").setId("TOTAL").setWidth(120);

        gridCotizaciones.setItems(getCotizacionesVentas());
        gridCotizaciones.setSelectionMode(Grid.SelectionMode.SINGLE);
        gridCotizaciones.setSizeFull();
        gridCotizaciones.setHeight("500px");

        downloader = new FileDownloader(new StreamResource(resourceFile, ""));
        downloader.extend(btnExcel);
        
        btnGraficaAnual.addClickListener((event) -> {
           getUI().getNavigator().navigateTo(VentasReporteGeneralCotizacionesGrafica.NAME);
        });
       
        btnExcel.addClickListener((event) -> {
            ArrayList<String> headers = new ArrayList();
            headers.add("PROYECTO");
            headers.add("FOLIO");
            headers.add("FECHA");
            headers.add("EDO DOC");
            headers.add("IMPORTE");
            headers.add("IVA");
            headers.add("TOTAL");

            ArrayList data = new ArrayList();
            SimpleDateFormat dts = new SimpleDateFormat("yyyy-MM-dd");

            for (CotizacionVenta invTemp : listCotizaciones) {
                ArrayList<String> cells = new ArrayList();
                cells.add(invTemp.getProyecto());
                cells.add(invTemp.getFolio());
                cells.add(invTemp.getFecha_elaboracion() != null ? dts.format(invTemp.getFecha_elaboracion()) : "");
                cells.add(invTemp.getEstado_doc());
                cells.add(invTemp.getImporte().toString());
                cells.add(invTemp.getIva().toString());
                cells.add(invTemp.getTotal().toString());
                data.add(cells);
            }

            try {
                String fileName = "ReportCotizacionesProyecto_" + UUID.randomUUID() + ".xlsx";

                ExportExcelManager exportToExcel = new ExportExcelManager("REPORTE DE COTIZACIONES POR PROYECTO", "Generado del " + ManageDates.getDateSimpleFormat(txtFechaIni.getValue()) + " al " + ManageDates.getDateSimpleFormat(txtFechaFin.getValue()));
                exportToExcel.setPositionCurrency(new int[]{4,5,6});
                
                resourceFile = () -> exportToExcel.getExcelFromListData("Reporte", headers, data);
                downloader.setFileDownloadResource(new StreamResource(resourceFile, fileName));

            } catch (Exception e) {
                System.out.println(e.toString());
                MessageBox.createError()
                        .withCaption("Error!")
                        .withMessage("Ha ocurrido un error al descargar el archivo Excel.")
                        .withRetryButton()
                        .open();
            }
        });
       
        btnPrint.addClickListener((event) -> {
            if (listCotizaciones.size() >= 1) {
                try {
                    final HashMap map = new HashMap();
                    SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");

                    String strWhere = " 1 = 1 ";

                    if (!cboProyecto.getValue().getNombre().equals("TODOS")) {
                        strWhere += " AND proyecto = '" + cboProyecto.getValue().getNombre() + "'";
                    }

                    if (txtFechaIni.getValue() != null && txtFechaFin.getValue() != null) {
                        strWhere
                                += " AND fecha_elaboracion >= '" + dt.format(ManageDates.getDateFromLocalDate(txtFechaIni.getValue())) + " 00:00:00"
                                + "' AND fecha_elaboracion <= '" + dt.format(ManageDates.getDateFromLocalDate(txtFechaFin.getValue())) + " 23:59:59'";
                    }

                    CotizacionVentaDomain service = new CotizacionVentaDomain();
                    service.getCotizacionVenta(strWhere, "", "proyecto ASC");
                    
                    
                    map.put("TITULO", "REPORTE DE COTIZACIONES POR PROYECTO");
                    map.put("SUBTITULO", "Generado del " + ManageDates.getDateSimpleFormat(txtFechaIni.getValue()) + " al " + ManageDates.getDateSimpleFormat(txtFechaFin.getValue()));
                    map.put("fecha_ini", dt.format(ManageDates.getDateFromLocalDate(txtFechaIni.getValue())) + " 00:00:00");
                    map.put("fecha_fin", dt.format(ManageDates.getDateFromLocalDate(txtFechaFin.getValue())) + " 23:59:59'");
                    map.put("strWhere",strWhere);

                    StreamResource.StreamSource source = new StreamResource.StreamSource() {
                        @Override
                        public InputStream getStream() {
                            byte[] b = null;
                            try {
                                InputStream fileStream = getClass().getClassLoader().getResourceAsStream("/reportes/ReporteCotizacionesPorProyecto.jasper");
                                b = JasperRunManager.runReportToPdf(fileStream, map, FactorySession.getRubikConnection(DomainConfig.getEnvironment()));

                            } catch (JRException ex) {
                                ex.printStackTrace();
                            }
                            return new ByteArrayInputStream(b);
                        }
                    };

                    StreamResource resource = new StreamResource(source, "Reporte_Cotizacion_Proyecto.pdf");
                    EmbedWindow windowPDF = new EmbedWindow(resource);
                    windowPDF.setCaption("Reporte general de Cotizaciones");
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
    }
    
    public List getProyectos(){
        ProyectoDomain proyDomain = new ProyectoDomain();
        proyDomain.getProyecto(" descripcion != '' ", "", "nombre ASC");
        listProyectos = proyDomain.getObjects();
        listProyectos.add(0, new Proyecto("SIN PROYECTO"));
        listProyectos.add(0, new Proyecto("TODOS"));
        return listProyectos;
    }

    public List getCotizacionesVentas() {
        String strWhere = " 1 = 1 ";
        
        if(!cboProyecto.getValue().getNombre().equals("TODOS")){
            strWhere += " AND proyecto = '" + cboProyecto.getValue().getNombre() + "'";
        }
        
        if (txtFechaIni.getValue() != null && txtFechaFin.getValue() != null) {
            SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");

            strWhere
                    += " AND fecha_elaboracion >= '" + dt.format(ManageDates.getDateFromLocalDate(txtFechaIni.getValue())) + " 00:00:00"
                    + "' AND fecha_elaboracion <= '" + dt.format(ManageDates.getDateFromLocalDate(txtFechaFin.getValue())) + " 23:59:59'";
        }

        CotizacionVentaDomain service = new CotizacionVentaDomain();
        service.getCotizacionVenta(strWhere, "", "proyecto ASC");
        listCotizaciones = service.getObjects();

        if (!service.getOk()) {
            MessageBox.createError()
                    .withCaption("Error al cargar la informacion!")
                    .withMessage("Err: " + service.getNotification())
                    .withRetryButton()
                    .open();
        }
        return listCotizaciones;
    }
   
}