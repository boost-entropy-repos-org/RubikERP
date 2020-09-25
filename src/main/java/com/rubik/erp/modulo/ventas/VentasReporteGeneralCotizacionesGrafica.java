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
import com.rubik.manage.ManageDates;
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

    Integer abiertos, pendientes, cerrados, cancelados = 0;

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
        cboYear.setValue(ManageDates.getYear());
        fillReportData();

        cboYear.addValueChangeListener((event) -> {
            vContainer.removeAllComponents();
            fillReportData();
            makeChart();
        });

        Responsive.makeResponsive(this);
    }

    public List<String> fillReportData() {
        List<String> list_tickets = new ArrayList<>();
        ReporteCotizacionesVentaDAO dao = new ReporteCotizacionesVentaDAO(DomainConfig.getEnvironment());

        dao.getCotizacionVentaEnero(cboYear.getValue());  // Valor seleccionado del combo-box
        ReporteCotizacionesVenta repoEnero = dao.getObject();

        ReporteCotizacionesVentaDAO dao2 = new ReporteCotizacionesVentaDAO(DomainConfig.getEnvironment());

        dao2.getCotizacionVentaFebrero(cboYear.getValue());  // Valor seleccionado del combo-box
        ReporteCotizacionesVenta repoFebrero = dao2.getObject();

        ReporteCotizacionesVentaDAO dao3 = new ReporteCotizacionesVentaDAO(DomainConfig.getEnvironment());

        dao3.getCotizacionVentaMarzo(cboYear.getValue());  // Valor seleccionado del combo-box
        ReporteCotizacionesVenta repoMarzo = dao3.getObject();

        ReporteCotizacionesVentaDAO dao4 = new ReporteCotizacionesVentaDAO(DomainConfig.getEnvironment());

        dao4.getCotizacionVentaAbril(cboYear.getValue());  // Valor seleccionado del combo-box
        ReporteCotizacionesVenta repoAbril = dao4.getObject();

        ReporteCotizacionesVentaDAO dao5 = new ReporteCotizacionesVentaDAO(DomainConfig.getEnvironment());

        dao5.getCotizacionVentaMayo(cboYear.getValue());  // Valor seleccionado del combo-box
        ReporteCotizacionesVenta repoMayo = dao5.getObject();

        ReporteCotizacionesVentaDAO dao6 = new ReporteCotizacionesVentaDAO(DomainConfig.getEnvironment());

        dao6.getCotizacionVentaJunio(cboYear.getValue());  // Valor seleccionado del combo-box
        ReporteCotizacionesVenta repoJunio = dao6.getObject();

        ReporteCotizacionesVentaDAO dao7 = new ReporteCotizacionesVentaDAO(DomainConfig.getEnvironment());

        dao7.getCotizacionVentaJulio(cboYear.getValue());  // Valor seleccionado del combo-box
        ReporteCotizacionesVenta repoJulio = dao7.getObject();

        ReporteCotizacionesVentaDAO dao8 = new ReporteCotizacionesVentaDAO(DomainConfig.getEnvironment());

        dao8.getCotizacionVentaAgosto(cboYear.getValue());  // Valor seleccionado del combo-box
        ReporteCotizacionesVenta repoAgosto = dao8.getObject();

        ReporteCotizacionesVentaDAO dao9 = new ReporteCotizacionesVentaDAO(DomainConfig.getEnvironment());

        dao9.getCotizacionVentaSeptiembre(cboYear.getValue());  // Valor seleccionado del combo-box
        ReporteCotizacionesVenta repoSeptiembre = dao9.getObject();

        ReporteCotizacionesVentaDAO dao10 = new ReporteCotizacionesVentaDAO(DomainConfig.getEnvironment());

        dao10.getCotizacionVentaOctubre(cboYear.getValue());  // Valor seleccionado del combo-box
        ReporteCotizacionesVenta repoOctubre = dao10.getObject();

        ReporteCotizacionesVentaDAO dao11 = new ReporteCotizacionesVentaDAO(DomainConfig.getEnvironment());

        dao11.getCotizacionVentaNoviembre(cboYear.getValue());  // Valor seleccionado del combo-box
        ReporteCotizacionesVenta repoNoviembre = dao11.getObject();

        ReporteCotizacionesVentaDAO dao12 = new ReporteCotizacionesVentaDAO(DomainConfig.getEnvironment());

        dao12.getCotizacionVentaDiciembre(cboYear.getValue());  // Valor seleccionado del combo-box
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

        return list_tickets;
    }

    public List getList() {
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
        List<String> ticketList = getList();

        BarChartConfig barConfig = new BarChartConfig();
        barConfig.
                data().
                labelsAsList(ticketList)
                .addDataset(
                        new BarDataset().backgroundColor("rgba(255, 83, 10, 1)").label("EN PROCESO").yAxisID("y-axis-1"))
                .addDataset(
                        new BarDataset().backgroundColor("rgba(0, 163, 0, 1)").label("TERMINADOS").yAxisID("y-axis-1"))
                .addDataset(
                        new BarDataset().backgroundColor("rgba(0, 163, 0, 1)").label("CANCELADOS").yAxisID("y-axis-1"))
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
                .text("TICKETS")
                .and()
                .scales()
                .add(Axis.Y, new LinearScale().display(true).position(Position.LEFT).id("y-axis-1"))
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
                        data.add(reporteAnualList.get(i).getEn_proceso()+ 0.0);
                        break;
                    case 2:
                        data.add(reporteAnualList.get(i).getTerminados()+  0.0);
                        break;
                    case 3:
                        data.add(reporteAnualList.get(i).getCancelados()+  0.0);
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
                new HorizontalLayout(new Label("AÑO:"), cboYear),
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
