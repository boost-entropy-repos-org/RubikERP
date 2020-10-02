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
import com.rubik.erp.dao.ReporteCotizacionesVentaDAO;
import com.rubik.erp.domain.reports.ReporteCotizacionesVenta;
import com.rubik.erp.fragments.FragmentTop;
import com.rubik.erp.model.Empleado;
import com.vaadin.navigator.View;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author GRUCAS
 */
public class VentasReporteGeneralCotizacionesGrafica extends Panel implements View {

    public static final String NAME = "REPORTE_GENERAL_COTIZACIONES_GRAFICA";
    VerticalLayout vContainer = new VerticalLayout();

    Empleado empleado = (Empleado) VaadinSession.getCurrent().getSession().getAttribute("USUARIO_ACTIVO");

    ChartJs chartsj;
    Label lblTitulo = new Label("REPORTE ANUAL DE COTIZACIONES");

    NativeSelect<Integer> cboYear = new NativeSelect<>();
    List<ReporteCotizacionesVenta> reporteAnualList = new ArrayList<>();
    
    public VentasReporteGeneralCotizacionesGrafica() {
        initComponents();
        makeChart();
    }

    public void initComponents() {
        setSizeFull();
        vContainer.setHeight("100%");
        lblTitulo.setStyleName("h2");
        
        cboYear.setItems(DomainConfig.YEAR);
        cboYear.setEmptySelectionAllowed(false);
        cboYear.setValue(2020);
        
        cboYear.addValueChangeListener((event) -> {
            vContainer.removeAllComponents();
            makeChart();
        });
        
        Responsive.makeResponsive(this);
    }

    public void fillReportData() {
        ReporteCotizacionesVentaDAO dao = new ReporteCotizacionesVentaDAO(DomainConfig.getEnvironment());
        dao.getCotizacionVentaEnero(cboYear.getValue());
        ReporteCotizacionesVenta repoEnero = dao.getObject();

        ReporteCotizacionesVentaDAO dao2 = new ReporteCotizacionesVentaDAO(DomainConfig.getEnvironment());
        dao2.getCotizacionVentaFebrero(cboYear.getValue());
        ReporteCotizacionesVenta repoFebrero = dao2.getObject();

        ReporteCotizacionesVentaDAO dao3 = new ReporteCotizacionesVentaDAO(DomainConfig.getEnvironment());
        dao3.getCotizacionVentaMarzo(cboYear.getValue());
        ReporteCotizacionesVenta repoMarzo = dao3.getObject();

        ReporteCotizacionesVentaDAO dao4 = new ReporteCotizacionesVentaDAO(DomainConfig.getEnvironment());
        dao4.getCotizacionVentaAbril(cboYear.getValue());
        ReporteCotizacionesVenta repoAbril = dao4.getObject();

        ReporteCotizacionesVentaDAO dao5 = new ReporteCotizacionesVentaDAO(DomainConfig.getEnvironment());
        dao5.getCotizacionVentaMayo(cboYear.getValue());
        ReporteCotizacionesVenta repoMayo = dao5.getObject();

        ReporteCotizacionesVentaDAO dao6 = new ReporteCotizacionesVentaDAO(DomainConfig.getEnvironment());
        dao6.getCotizacionVentaJunio(cboYear.getValue());
        ReporteCotizacionesVenta repoJunio = dao6.getObject();

        ReporteCotizacionesVentaDAO dao7 = new ReporteCotizacionesVentaDAO(DomainConfig.getEnvironment());
        dao7.getCotizacionVentaJulio(cboYear.getValue());
        ReporteCotizacionesVenta repoJulio = dao7.getObject();

        ReporteCotizacionesVentaDAO dao8 = new ReporteCotizacionesVentaDAO(DomainConfig.getEnvironment());
        dao8.getCotizacionVentaAgosto(cboYear.getValue());
        ReporteCotizacionesVenta repoAgosto = dao8.getObject();

        ReporteCotizacionesVentaDAO dao9 = new ReporteCotizacionesVentaDAO(DomainConfig.getEnvironment());
        dao9.getCotizacionVentaSeptiembre(cboYear.getValue());
        ReporteCotizacionesVenta repoSeptiembre = dao9.getObject();

        ReporteCotizacionesVentaDAO dao10 = new ReporteCotizacionesVentaDAO(DomainConfig.getEnvironment());
        dao10.getCotizacionVentaOctubre(cboYear.getValue());
        ReporteCotizacionesVenta repoOctubre = dao10.getObject();

        ReporteCotizacionesVentaDAO dao11 = new ReporteCotizacionesVentaDAO(DomainConfig.getEnvironment());
        dao11.getCotizacionVentaNoviembre(cboYear.getValue());
        ReporteCotizacionesVenta repoNoviembre = dao11.getObject();

        ReporteCotizacionesVentaDAO dao12 = new ReporteCotizacionesVentaDAO(DomainConfig.getEnvironment());
        dao12.getCotizacionVentaDiciembre(cboYear.getValue());
        ReporteCotizacionesVenta repoDiciembre = dao12.getObject();

        reporteAnualList = new ArrayList<>();
        reporteAnualList.add(repoEnero);
        reporteAnualList.add(repoFebrero);
        reporteAnualList.add(repoMarzo);
        reporteAnualList.add(repoAbril);
        reporteAnualList.add(repoMayo);
        reporteAnualList.add(repoJunio);
        reporteAnualList.add(repoJulio);
        reporteAnualList.add(repoAgosto);
        reporteAnualList.add(repoSeptiembre);
        reporteAnualList.add(repoOctubre);
        reporteAnualList.add(repoNoviembre);
        reporteAnualList.add(repoDiciembre);
    }

    public List getLabelList() {
        return new ArrayList<String>() {
            {
                add("ENERO");
                add("FEBRERO");
                add("MARZO");
                add("ABRIL");
                add("MAYO");
                add("JUNIO");
                add("JULIO");
                add("AGOSTO");
                add("SEPTIEMBRE");
                add("OCTUBRE");
                add("NOVIEMBRE");
                add("DICIEMBRE");
            }
        };
    }

    public void makeChart() {
        fillReportData();

        BarChartConfig barConfig = new BarChartConfig();
        barConfig.
                data().
                labelsAsList(getLabelList())
                .addDataset(
                        new BarDataset().backgroundColor("rgba(204, 204, 0, 1)").label("EN PROCESO").yAxisID("y-axis-1"))
                .addDataset(
                        new BarDataset().backgroundColor("rgba(0, 163, 0, 1)").label("TERMINADOS").yAxisID("y-axis-1"))
                .addDataset(
                        new BarDataset().backgroundColor("rgba(255, 0, 0, 1)").label("CANCELADOS").yAxisID("y-axis-1"))
                .and();

        barConfig.
                options()
                .responsive(true)
                .hover()
                .mode(InteractionMode.INDEX)
                .intersect(true)
                .animationDuration(400)
                .and()
                .title()
                .display(true)
                .text("Cotizaciones de Venta")
                .and()
                .scales()
                .add(Axis.Y, new LinearScale().display(true).position(Position.LEFT).id("y-axis-1"))
                .and()
                .done();

        int dataset_number = 1;

        for (Dataset<?, ?> ds : barConfig.data().getDatasets()) {
            BarDataset lds = (BarDataset) ds;
            List<Double> data = new ArrayList<>();
            for (int i = 0; i < barConfig.data().getLabels().size(); i++) {
                
                System.out.println("REPORTE POR MES " + i + " - " + reporteAnualList.get(i).toString());
                
                switch (dataset_number) {
                    case 1:
                        data.add(reporteAnualList.get(i).getEn_proceso() + 0.0);
                        break;
                    case 2:
                        data.add(reporteAnualList.get(i).getTerminado() + 0.0);
                        break;
                    case 3:
                        data.add(reporteAnualList.get(i).getCancelado() + 0.0);
                        break;
                    default:
                        break;
                }
            }
            lds.dataAsList(data);
            dataset_number++;
        }

        chartsj = new ChartJs(barConfig);
        chartsj.setJsLoggingEnabled(true);
        chartsj.setWidth("70%");

        vContainer.setMargin(false);
        vContainer.addComponents(new FragmentTop(),
                lblTitulo,
                new HorizontalLayout(new Label("AÑO:"), cboYear)
                    {{
                        setComponentAlignment(getComponent(0), Alignment.MIDDLE_CENTER);
                    }},
                chartsj);

        vContainer.setComponentAlignment(vContainer.getComponent(0), Alignment.MIDDLE_CENTER);
        vContainer.setComponentAlignment(vContainer.getComponent(1), Alignment.MIDDLE_CENTER);
        vContainer.setComponentAlignment(vContainer.getComponent(2), Alignment.MIDDLE_CENTER);
        vContainer.setComponentAlignment(vContainer.getComponent(3), Alignment.MIDDLE_CENTER);
        vContainer.setExpandRatio(vContainer.getComponent(0), 0);
        vContainer.setExpandRatio(lblTitulo, 0);
        vContainer.setExpandRatio(chartsj, 10);

        setContent(vContainer);
    }

}
