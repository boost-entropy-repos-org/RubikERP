/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rubik.erp.modulo.compras;

import com.rubik.erp.config.DomainConfig;
import com.rubik.erp.config.FactorySession;
import com.rubik.erp.config._DocumentoEstados;
import com.rubik.erp.domain.RemisionEntregaDomain;
import com.rubik.erp.fragments.FragmentTop;
import com.rubik.erp.model.Empleado;
import com.rubik.erp.model.RemisionEntrega;
import com.rubik.erp.modulo.generic.WindowCancelarDocumento;
import com.rubik.erp.util.EmbedWindow;
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
public class ComprasRemisionesDeEntrega extends Panel implements View {

    public static final String NAME = "REMISIONES_DE_ENTREGA";
    VerticalLayout container = new VerticalLayout();

    Empleado empleado = (Empleado) VaadinSession.getCurrent().getSession().getAttribute("USUARIO_ACTIVO");

    TextField txtBusqueda = new TextField();
    
    Grid<RemisionEntrega> gridRemisionesEntrega = new Grid<>();
    DateField txtFechaIni = new DateField();
    DateField txtFechaFin = new DateField();
    Button btnSearch = new Button(Fam3SilkIcon.MAGNIFIER);
    
    NativeSelect<String> cboEstadoDocumento = new NativeSelect();
    
    Button btnAdd = new Button("Agregar", Fam3SilkIcon.ADD);
    Button btnModify = new Button("Modificar", Fam3SilkIcon.PENCIL);
    Button btnCancel = new Button("Cancelar", Fam3SilkIcon.CANCEL);
    Button btnPrint = new Button("Imprimir", Fam3SilkIcon.PRINTER);
    
    List<RemisionEntrega> listOC = new ArrayList<>();

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    
    public ComprasRemisionesDeEntrega() {
        initComponents();

        HorizontalLayout hLayoutAux = new HorizontalLayout(
                cboEstadoDocumento,
                txtBusqueda,
                new Label("Fecha: "),txtFechaIni,new Label("A: "),txtFechaFin,btnSearch);
        
        HorizontalLayout hLayoutAux2 = new HorizontalLayout(btnAdd, btnModify, btnCancel, btnPrint);
        
        hLayoutAux.setComponentAlignment(hLayoutAux.getComponent(1), Alignment.MIDDLE_CENTER);
        hLayoutAux.setComponentAlignment(hLayoutAux.getComponent(3), Alignment.MIDDLE_CENTER);
        
        Label lblTitulo = new Label("REMISIONES DE ENTREGA DE MERCANCIA") {
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
                gridRemisionesEntrega);
        
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
        txtBusqueda.setPlaceholder("Folio Remision");
        
        cboEstadoDocumento.setItems("TODOS", _DocumentoEstados.EN_PROCESO, _DocumentoEstados.TERMINADO,_DocumentoEstados.CANCELADO);
        cboEstadoDocumento.setEmptySelectionAllowed(false);
        cboEstadoDocumento.setValue(_DocumentoEstados.EN_PROCESO);

        Grid.Column<RemisionEntrega, String> columnFecha = gridRemisionesEntrega.addColumn(det -> ((det.getFecha_elaboracion()!= null) ? dateFormat.format(det.getFecha_elaboracion()) : ""));
        columnFecha.setCaption("FECHA");
        columnFecha.setId("FECHA");
        columnFecha.setWidth(120);
        gridRemisionesEntrega.addColumn(RemisionEntrega::getFolio).setCaption("FOLIO").setId("FOLIO").setWidth(120);
        gridRemisionesEntrega.addColumn(RemisionEntrega::getEstado_doc).setCaption("ESTADO").setId("ESTADO").setWidth(130);
        gridRemisionesEntrega.addColumn(RemisionEntrega::getCliente).setCaption("CLIENTE").setId("CLIENTE");
        gridRemisionesEntrega.addColumn(RemisionEntrega::getFolio_cotizacion).setCaption("COTIZACION").setId("COTIZACION").setWidth(120);
        gridRemisionesEntrega.addColumn(RemisionEntrega::getFolio_orden_compra).setCaption("OC").setId("OC").setWidth(120);

        gridRemisionesEntrega.setItems(getRemisionesEntrega());
        gridRemisionesEntrega.setSelectionMode(Grid.SelectionMode.SINGLE);
        gridRemisionesEntrega.setSizeFull();
        gridRemisionesEntrega.setHeight("500px");

        btnAdd.addClickListener((event) -> {
            WindowRemisionDeEntrega windows = new WindowRemisionDeEntrega();
            windows.center();
            windows.setModal(true);
            windows.addCloseListener(ev -> {
                gridRemisionesEntrega.setItems(getRemisionesEntrega());
            });
            getUI().addWindow(windows);
        });

        btnModify.addClickListener((event) -> {
            if (gridRemisionesEntrega.getSelectedItems().size() == 1) {
                WindowRemisionDeEntrega windows = new WindowRemisionDeEntrega(gridRemisionesEntrega.getSelectedItems().iterator().next());
                windows.center();
                windows.setModal(true);
                windows.addCloseListener((e) -> {
                    gridRemisionesEntrega.setItems(getRemisionesEntrega());
                });
                getUI().addWindow(windows);
            } else {
                MessageBox.createError()
                        .withCaption("Error!")
                        .withMessage("Debe tener una Remision de Entrega seleccionada para poder modificarla.")
                        .withRetryButton()
                        .open();
            }
        });
        
        btnSearch.addClickListener((event) -> {
            gridRemisionesEntrega.setItems(getRemisionesEntrega());
            txtBusqueda.setValue("");
        });
        
        btnPrint.addClickListener((event) -> {
            if (gridRemisionesEntrega.getSelectedItems().size() == 1) {
                RemisionEntrega ocTemp = gridRemisionesEntrega.getSelectedItems().iterator().next();
                if (!ocTemp.getEstado_doc().equals(_DocumentoEstados.EN_PROCESO)) {

                    try {
                        final HashMap map = new HashMap();
                        map.put("folio", ocTemp.getFolio());

                        StreamResource.StreamSource source = new StreamResource.StreamSource() {
                            @Override
                            public InputStream getStream() {
                                byte[] b = null;
                                try {
                                    InputStream fileStream = getClass().getClassLoader().getResourceAsStream("/reportes/RemisionesEntrega.jasper");
                                    b = JasperRunManager.runReportToPdf(fileStream, map, FactorySession.getRubikConnection(DomainConfig.getEnvironment()));

                                } catch (JRException ex) {
                                    ex.printStackTrace();
                                }
                                return new ByteArrayInputStream(b);
                            }
                        };

                        StreamResource resource = new StreamResource(source, "OC_" + ocTemp.getFolio() + ".pdf");

                        EmbedWindow windowPDF = new EmbedWindow(resource);
                        windowPDF.setCaption("Remision de Entrega:");
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
                            .withMessage("Debe Terminar la Cotizacion de Venta para Imprimir el documento")
                            .withRetryButton()
                            .open();
                }

            } else {
                MessageBox.createError()
                        .withCaption("Error!")
                        .withMessage("Debe seleccionar una Cotizacion de Venta para poder imprimirla.")
                        .withRetryButton()
                        .open();
            }
        });

        btnCancel.addClickListener((event) -> {
            if (gridRemisionesEntrega.getSelectedItems().size() == 1) {
                RemisionEntrega cotizacion = gridRemisionesEntrega.getSelectedItems().iterator().next();
                if (cotizacion.getEstado_doc().equals(_DocumentoEstados.CANCELADO) || cotizacion.getEstado_doc().equals(_DocumentoEstados.AUTORIZADO)) {
                    MessageBox.createError()
                            .withCaption("Error!")
                            .withMessage("El documento seleccionado esta cancelado o esta autorizado. No se puede CANCELAR.")
                            .withRetryButton()
                            .open();
                } else {
                    MessageBox.createQuestion()
                            .withCaption("Confirmar Accion")
                            .withMessage("Desea Cancelar la Cotizacion " + cotizacion.getFolio() + "?")
                            .withYesButton(() -> {

                                WindowCancelarDocumento winCancelar = new WindowCancelarDocumento();
                                winCancelar.addCloseListener((e) -> {
                                    cotizacion.setActivo(false);
                                    String razon_cancelar = WindowCancelarDocumento.RAZON_DE_CANCELAMIENTO;
                                    cotizacion.setRazon_cancelar(razon_cancelar);

                                    RemisionEntregaDomain service = new RemisionEntregaDomain();
                                    service.Cancelar(cotizacion.getId(), cotizacion.getRazon_cancelar(), cotizacion.getUsuario_id(), cotizacion.getUsuario(), cotizacion.getActivo());

                                    gridRemisionesEntrega.setItems(getRemisionesEntrega());

                                });
                                getUI().addWindow(winCancelar);
                            })
                            .withNoButton()
                            .open();
                }
            } else {
                MessageBox.createError()
                        .withCaption("Error!")
                        .withMessage("Debe seleccionar una Requisicion para Cancelarla")
                        .withRetryButton()
                        .open();
            }
        });
        
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }

    public List getRemisionesEntrega() {
        String strWhere = " activo = 1 ";

        if (!"".equals(txtBusqueda.getValue())) {
            strWhere += " AND folio = '" + txtBusqueda.getValue().toUpperCase() + "'";
        }
        
        if(txtFechaIni.getValue() != null && txtFechaFin.getValue() != null){
            strWhere += "";
        }

        if(!"TODOS".equals(cboEstadoDocumento.getValue())){
            strWhere += " AND estado_doc = '" + cboEstadoDocumento.getValue() + "'";
        }
        
        RemisionEntregaDomain service = new RemisionEntregaDomain();
        service.getRemisionEntrega(strWhere, "", "fecha_elaboracion DESC");
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