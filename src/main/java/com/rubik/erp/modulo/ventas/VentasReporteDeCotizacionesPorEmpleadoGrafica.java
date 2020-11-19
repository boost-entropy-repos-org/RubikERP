/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rubik.erp.modulo.ventas;

import com.byteowls.vaadin.chartjs.ChartJs;
import com.byteowls.vaadin.chartjs.config.BarChartConfig;
import com.byteowls.vaadin.chartjs.data.BarDataset;
import com.byteowls.vaadin.chartjs.data.Dataset;
import com.byteowls.vaadin.chartjs.options.InteractionMode;
import com.byteowls.vaadin.chartjs.options.Position;
import com.byteowls.vaadin.chartjs.options.scale.Axis;
import com.byteowls.vaadin.chartjs.options.scale.DefaultScale;
import com.byteowls.vaadin.chartjs.options.scale.LinearScale;
import com.rubik.erp.config.DomainConfig;
import com.rubik.erp.dao.ReporteCotizacionesVentaPorEmpleadoDAO;
import com.rubik.erp.domain.reports.ReporteCotizacionesVentaPorEmpleado;
import com.rubik.erp.fragments.FragmentTop;
import com.rubik.erp.model.Empleado;
import com.rubik.manage.ManageDates;
import com.vaadin.navigator.View;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.rubicone.vaadin.fam3.silk.Fam3SilkIcon;

/**
 *
 * @author GRUCAS
 */
public class VentasReporteDeCotizacionesPorEmpleadoGrafica extends Panel implements View {
    
    public static final String NAME = "REPORTE_COTIZACIONES_EMPLEADO_GRAFICA";
    VerticalLayout vContainer = new VerticalLayout();

    Empleado empleado = (Empleado) VaadinSession.getCurrent().getSession().getAttribute("USUARIO_ACTIVO");
    
    ChartJs chartsj;
  
    Label lblTitulo = new Label("REPORTE DE COTIZACIONES POR VENDEDOR");
    List<ReporteCotizacionesVentaPorEmpleado> cotizacionesList = new ArrayList<>();
    
    Button btnSearch = new Button(Fam3SilkIcon.MAGNIFIER);
    
    DateField txtFechaIni = new DateField();
    DateField txtFechaFin = new DateField();

    public VentasReporteDeCotizacionesPorEmpleadoGrafica() {
        setSizeFull();
        initComponents();

        txtFechaIni.setValue(ManageDates.getLocalDateFromDate(ManageDates.getFirstDayOfTheMonth()));
        txtFechaFin.setValue(ManageDates.getLocalDateFromDate(ManageDates.getLastDayOfTheMonth()));

        chartsj = makeChart();
        
        vContainer.setMargin(false);
        vContainer.addComponents(new FragmentTop(),
                lblTitulo,
                new HorizontalLayout(new Label("Periodo graficado: "), txtFechaIni, txtFechaFin, btnSearch)
                {{setComponentAlignment(this.getComponent(0), Alignment.MIDDLE_CENTER);}},
                chartsj);

        vContainer.setComponentAlignment(vContainer.getComponent(0), Alignment.MIDDLE_CENTER);
        vContainer.setComponentAlignment(vContainer.getComponent(1), Alignment.MIDDLE_CENTER);
        vContainer.setComponentAlignment(vContainer.getComponent(2), Alignment.MIDDLE_CENTER);
        vContainer.setComponentAlignment(vContainer.getComponent(3), Alignment.MIDDLE_CENTER);
      
        vContainer.setExpandRatio(vContainer.getComponent(0), 0);
        vContainer.setExpandRatio(lblTitulo, 0);
        vContainer.setExpandRatio(chartsj, 10);

        btnSearch.addClickListener((event) -> {
            makeChart();
        });

        setContent(vContainer);
    }

    public void initComponents() {
        setSizeFull();
        vContainer.setHeight("100%");
        lblTitulo.setStyleName("h2");
        Responsive.makeResponsive(this);
    }
    
    public List<String> fillCotizaciones(){
        List<String> listaTecnicos = new ArrayList<>();
        
        String strWhere = "";
        
        if (txtFechaIni.getValue() != null && txtFechaFin.getValue() != null) {
            SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");

            strWhere
                    += " AND fecha_elaboracion >= '" + dt.format(ManageDates.getDateFromLocalDate(txtFechaIni.getValue())) + " 00:00:00"
                    + "' AND fecha_elaboracion <= '" + dt.format(ManageDates.getDateFromLocalDate(txtFechaFin.getValue())) + " 23:59:59'";
        }
        
        ReporteCotizacionesVentaPorEmpleadoDAO dao = new ReporteCotizacionesVentaPorEmpleadoDAO(DomainConfig.getEnvironment());
        dao.getReporteCotizacionesVentaPorEmpleado(strWhere);
        
        cotizacionesList = dao.getObjects();
        
        for (ReporteCotizacionesVentaPorEmpleado tecnico : cotizacionesList) {
            listaTecnicos.add(tecnico.getUsuario());
        }
        
        return listaTecnicos;
    }
    
    public ChartJs makeChart() {
        setSizeFull();
        List<String> buquesList = fillCotizaciones();
        
        BarChartConfig barConfig = new BarChartConfig();
        barConfig.horizontal();
        barConfig.
                data()
                .labelsAsList(buquesList)
                .addDataset(
                        new BarDataset().backgroundColor("rgba(229, 229, 0, 1)").label("COTIZACIONES").xAxisID("x-axis-1"))
                .addDataset(
                        new BarDataset().backgroundColor("rgba(21, 99, 4, 1)").label("VENTAS").xAxisID("x-axis-1"))
                .and();
        barConfig.
                options()
                .responsive(true)
                .hover()
                .mode(InteractionMode.DATASET)
                .intersect(true)
                .animationDuration(1000)
                .and()
                .title()
                .display(true)
                .and()
                .scales()
                .add(Axis.X, new LinearScale().display(true).position(Position.RIGHT).id("x-axis-1"))
                .and()
                .done();

        int dataset_number = 1;
        
        List<String> labels = barConfig.data().getLabels();
        for (Dataset<?, ?> ds : barConfig.data().getDatasets()) {
            BarDataset lds = (BarDataset) ds;
            List<Double> data = new ArrayList<>();
            for (int i = 0; i < labels.size(); i++) {
                switch (dataset_number) {
                    case 1:
                        data.add(cotizacionesList.get(i).getCantidad()+ 0.0);
                        break;
                    case 2:
                        data.add(cotizacionesList.get(i).getTotal());
                        break;
                    default:
                        break;
                }
            }
            lds.dataAsList(data);
            dataset_number++;
        }

        ChartJs chart = new ChartJs(barConfig);
        chart.setJsLoggingEnabled(true);
        chart.setWidth("70%");
       
        return chart;
    }
      
}