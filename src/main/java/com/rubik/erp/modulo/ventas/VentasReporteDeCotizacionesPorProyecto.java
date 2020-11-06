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
import com.rubik.erp.domain.EmpleadoDomain;
import com.rubik.erp.domain.reports.ReporteCotizacionesVentaPorEmpleado;
import com.rubik.erp.fragments.FragmentTop;
import com.rubik.erp.model.Empleado;
import com.vaadin.navigator.View;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author GRUCAS
 */
public class VentasReporteDeCotizacionesPorProyecto extends Panel implements View {
    
    public static final String NAME = "REPORTE_COTIZACIONES_PROYECTO";
    VerticalLayout vContainer = new VerticalLayout();

    Empleado empleado = (Empleado) VaadinSession.getCurrent().getSession().getAttribute("USUARIO_ACTIVO");
    
    ChartJs chartsj;
  
    Label lblTitulo = new Label("REPORTE DE COTIZACIONES POR VENDEDOR");
    List<ReporteCotizacionesVentaPorEmpleado> tecnicos = new ArrayList<>();

    public VentasReporteDeCotizacionesPorProyecto() {
        setSizeFull();
        initComponents();
        
        chartsj = makeChart();
        //chartsj2 = getChart();
        
        vContainer.setMargin(false);
        vContainer.addComponents(new FragmentTop(),
                lblTitulo,chartsj);

        vContainer.setComponentAlignment(vContainer.getComponent(0), Alignment.MIDDLE_CENTER);
        vContainer.setComponentAlignment(vContainer.getComponent(1), Alignment.MIDDLE_CENTER);
        vContainer.setComponentAlignment(vContainer.getComponent(2), Alignment.MIDDLE_CENTER);
      
        vContainer.setExpandRatio(vContainer.getComponent(0), 0);
        vContainer.setExpandRatio(lblTitulo, 0);
        vContainer.setExpandRatio(chartsj, 10);
        //vContainer.setExpandRatio(chartsj2, 10);

        setContent(vContainer);
    }

    public void initComponents() {
        setSizeFull();
        vContainer.setHeight("100%");
        lblTitulo.setStyleName("h2");
        Responsive.makeResponsive(this);
    }
    
    public List<String> fillTecnicos(){
        List<String> listaTecnicos = new ArrayList<>();
        ReporteCotizacionesVentaPorEmpleadoDAO dao = new ReporteCotizacionesVentaPorEmpleadoDAO(DomainConfig.getEnvironment());
        dao.getReporteCotizacionesVentaPorEmpleado();
        
        tecnicos = dao.getObjects();
        
        for (ReporteCotizacionesVentaPorEmpleado tecnico : tecnicos) {
            listaTecnicos.add(tecnico.getUsuario());
        }
        
        return listaTecnicos;
    }
    
    public ChartJs makeChart() {
         setSizeFull();
        List<String> buquesList = fillTecnicos();
        
        BarChartConfig barConfig = new BarChartConfig();
        barConfig.horizontal();
        barConfig.
                data()
                .labelsAsList(buquesList)
                .addDataset(
                        new BarDataset().backgroundColor("rgba(229, 229, 0, 1)").label("COTIZACIONES").xAxisID("x-axis-1"))
                .and();
        barConfig.
                options()
                .responsive(true)
                .hover()
                .mode(InteractionMode.INDEX)
                .intersect(true)
                .animationDuration(1000)
                .and()
                .title()
                .display(true)
                .text("ESTADO DE TICKETS")
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
                        data.add(tecnicos.get(i).getCantidad()+ 0.0);
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
//        chart.addClickListener((a, b) -> DemoUtils.notification(a, b, barConfig.data().getDatasets().get(a)));
//        chart.addLegendClickListener((dataSetIndex, visible, visibleDatasets) -> DemoUtils.legendNotification(dataSetIndex, visible, visibleDatasets));
    }
    
      public ChartJs getChart() {
        setSizeFull();
        BarChartConfig config = new BarChartConfig();
        config.data()
            .labels("January", "February", "March", "April", "May", "June", "July")
            .addDataset(new BarDataset().label("Dataset 1").backgroundColor("rgba(220,220,220,0.5)"))
            .addDataset(new BarDataset().label("Dataset 2").backgroundColor("rgba(151,187,205,0.5)"))
            .addDataset(new BarDataset().label("Dataset 3").backgroundColor("rgba(151,187,145,0.5)"))
            .and()
        .options()
            .responsive(true)
            .title()
                .display(true)
                .text("Chart.js Bar Chart - Stacked")
                .and()
            .tooltips()
                .mode(InteractionMode.INDEX)
                .intersect(false)
                .and()
            .scales()
            .add(Axis.X, new DefaultScale()
                    .stacked(true))
            .add(Axis.Y, new DefaultScale()
                    .stacked(true))
            .and()
            .done();
        
        // add random data for demo
        List<String> labels = config.data().getLabels();
        for (Dataset<?, ?> ds : config.data().getDatasets()) {
            BarDataset lds = (BarDataset) ds;
            List<Double> data = new ArrayList<>();
            for (int i = 0; i < labels.size(); i++) {
                data.add((double) (Math.random() > 0.5 ? -1 : 1) * Math.round(Math.random() * 100));
            }
            lds.dataAsList(data);
        }

        ChartJs chart = new ChartJs(config);
        chart.setWidth("70%");    
        chart.setJsLoggingEnabled(true);
        return chart;
    }
      
}