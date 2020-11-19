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
import com.byteowls.vaadin.chartjs.options.scale.LinearScale;
import com.rubik.erp.config.DomainConfig;
import com.rubik.erp.dao.ReporteCotizacionesVentaPorProyectoDAO;
import com.rubik.erp.domain.reports.ReporteCotizacionesVentaPorProyecto;
import com.rubik.erp.fragments.FragmentTop;
import com.rubik.erp.model.Empleado;
import com.vaadin.navigator.View;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author GRUCAS
 */
public class VentasReporteDeCotizacionesPorProyectoGrafica extends Panel implements View {
    
    public static final String NAME = "REPORTE_COTIZACIONES_PROYECTO_GRAFICA";
    VerticalLayout vContainer = new VerticalLayout();

    Empleado empleado = (Empleado) VaadinSession.getCurrent().getSession().getAttribute("USUARIO_ACTIVO");
    
    ChartJs chartsj;
  
    Label lblTitulo = new Label("REPORTE DE COTIZACIONES POR PROYECTO");
    List<ReporteCotizacionesVentaPorProyecto> proyectos = new ArrayList<>();
    
    CheckBox checkTotales = new CheckBox("Monto total de cotizaciones");

    public VentasReporteDeCotizacionesPorProyectoGrafica() {
        setSizeFull();
        initComponents();
        
        chartsj = makeChart();
        
        vContainer.setMargin(false);
        vContainer.addComponents(new FragmentTop(),
                lblTitulo, chartsj);

        vContainer.setComponentAlignment(vContainer.getComponent(0), Alignment.MIDDLE_CENTER);
        vContainer.setComponentAlignment(vContainer.getComponent(1), Alignment.MIDDLE_CENTER);
        vContainer.setComponentAlignment(vContainer.getComponent(2), Alignment.MIDDLE_CENTER);
      
        vContainer.setExpandRatio(vContainer.getComponent(0), 0);
        vContainer.setExpandRatio(lblTitulo, 0);
        vContainer.setExpandRatio(chartsj, 10);

        setContent(vContainer);
    }

    public void initComponents() {
        setSizeFull();
        vContainer.setHeight("100%");
        lblTitulo.setStyleName("h2");
        Responsive.makeResponsive(this);
    }
    
    public List<String> fillTecnicos(){
        List<String> listaProyectos = new ArrayList<>();
        ReporteCotizacionesVentaPorProyectoDAO dao = new ReporteCotizacionesVentaPorProyectoDAO(DomainConfig.getEnvironment());
        dao.getReporteCotizacionesVentaPorProyecto();
        
        proyectos = dao.getObjects();
        
        for (ReporteCotizacionesVentaPorProyecto proyectoTemp : proyectos) {
            listaProyectos.add(proyectoTemp.getProyecto());
        }
        
        return listaProyectos;
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
                .text("COTIZACIONES REALIZADAS POR PROYECTO")
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
                        data.add(proyectos.get(i).getCantidad()+ 0.0);
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