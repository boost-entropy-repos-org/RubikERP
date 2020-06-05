/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rubik.erp.modulo.compras;

import com.rubik.erp.config.DomainConfig;
import com.rubik.erp.config.FactorySession;
import com.rubik.erp.config._DocumentoEstados;
import com.rubik.erp.domain.RequisicionDomain;
import com.rubik.erp.fragments.FragmentTop;
import com.rubik.erp.model.Empleado;
import com.rubik.erp.model.Requisicion;
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
public class ComprasRequisiciones extends Panel implements View {

    public static final String NAME = "REQUISICIONES";
    VerticalLayout container = new VerticalLayout();

    Empleado empleado = (Empleado) VaadinSession.getCurrent().getSession().getAttribute("USUARIO_ACTIVO");

    TextField txtBusqueda = new TextField();
    
    Grid<Requisicion> gridRequisiciones = new Grid<>();
    DateField txtFechaIni = new DateField();
    DateField txtFechaFin = new DateField();
    Button btnSearch = new Button(Fam3SilkIcon.MAGNIFIER);
    
    Button btnAdd = new Button("Agregar", Fam3SilkIcon.ADD);
    Button btnModify = new Button("Modificar", Fam3SilkIcon.PENCIL);
    Button btnCancel = new Button("Cancelar", Fam3SilkIcon.CANCEL);
    Button btnTerminar = new Button("Terminar", Fam3SilkIcon.NOTE_GO);
    Button btnPrint = new Button("Imprimir", Fam3SilkIcon.PRINTER);
    
    List<Requisicion> listRequisiciones = new ArrayList<>();

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    
    public ComprasRequisiciones() {
        initComponents();

        HorizontalLayout hLayoutAux = new HorizontalLayout(
                txtBusqueda,
                new Label("Fecha: "),txtFechaIni,new Label("A: "),txtFechaFin,btnSearch);
        
        HorizontalLayout hLayoutAux2 = new HorizontalLayout(btnAdd, btnModify, btnCancel, btnPrint, btnTerminar);
        
        hLayoutAux.setComponentAlignment(hLayoutAux.getComponent(1), Alignment.MIDDLE_CENTER);
        hLayoutAux.setComponentAlignment(hLayoutAux.getComponent(3), Alignment.MIDDLE_CENTER);
        
        Label lblTitulo = new Label("REQUISICIONES") {
            {
                setStyleName("h1");
            }
        };
        lblTitulo.setSizeUndefined();

        setContent(container);

        container.setMargin(false);
        container.addComponents(new FragmentTop(),
                lblTitulo,
                hLayoutAux,
                hLayoutAux2,
                gridRequisiciones);
        
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
        txtBusqueda.setPlaceholder("Folio de Requisicion");      

        Grid.Column<Requisicion, String> columnFecha = gridRequisiciones.addColumn(det -> ((det.getFecha_requerida() != null) ? dateFormat.format(det.getFecha_requerida()) : ""));
        columnFecha.setCaption("F. REQ");
        columnFecha.setId("F. REQ");
        columnFecha.setWidth(120);
        gridRequisiciones.addColumn(Requisicion::getFolio).setCaption("FOLIO").setId("FOLIO").setWidth(120);
        gridRequisiciones.addColumn(Requisicion::getPrioridad).setCaption("PRIORIDAD").setId("PRIORIDAD").setWidth(135);
        gridRequisiciones.addColumn(Requisicion::getEstado_doc).setCaption("ESTADO").setId("ESTADO").setWidth(135);
        
        Grid.Column<Requisicion, String> columnFechaOC = gridRequisiciones.addColumn(det -> ((det.getFecha_orden_compra() != null) ? dateFormat.format(det.getFecha_orden_compra()) : ""));
        columnFechaOC.setCaption("FECHA OC");
        columnFechaOC.setId("FECHA OC");
        columnFechaOC.setWidth(120);
        gridRequisiciones.addColumn(Requisicion::getFolio_orden_compra).setCaption("FOLIO OC").setId("FOLIO OC").setWidth(120);
        gridRequisiciones.addColumn(Requisicion::getRazon_cancelar).setCaption("CANCELACION").setId("CANCELACION");

        gridRequisiciones.setItems(getRequisiciones());
        gridRequisiciones.setSelectionMode(Grid.SelectionMode.SINGLE);
        gridRequisiciones.setSizeFull();
        gridRequisiciones.setHeight("500px");

        btnAdd.addClickListener((event) -> {
            WindowRequisicion windows = new WindowRequisicion();
            windows.center();
            windows.setModal(true);
            windows.addCloseListener(ev -> {
                gridRequisiciones.setItems(getRequisiciones());
            });
            getUI().addWindow(windows);
        });

        btnModify.addClickListener((event) -> {
            if (gridRequisiciones.getSelectedItems().size() == 1) {
                if(gridRequisiciones.getSelectedItems().iterator().next().getEstado_doc().equals(_DocumentoEstados.EN_PROCESO)){
                    WindowRequisicion windows = new WindowRequisicion(gridRequisiciones.getSelectedItems().iterator().next());
                    windows.center();
                    windows.setModal(true);
                    windows.addCloseListener((e) -> {
                        gridRequisiciones.setItems(getRequisiciones());
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
            gridRequisiciones.setItems(getRequisiciones());
            txtBusqueda.setValue("");
        });
        
        btnPrint.addClickListener((event) -> {
            if (gridRequisiciones.getSelectedItems().size() == 1) {
                Requisicion requi = gridRequisiciones.getSelectedItems().iterator().next();
                if (!requi.getEstado_doc().equals(_DocumentoEstados.EN_PROCESO)) {

                    try {
                        final HashMap map = new HashMap();
                        map.put("folio", requi.getFolio());

                        StreamResource.StreamSource source = new StreamResource.StreamSource() {
                            @Override
                            public InputStream getStream() {
                                byte[] b = null;
                                try {
                                    InputStream fileStream = getClass().getClassLoader().getResourceAsStream("/reportes/Requisiciones.jasper");
                                    b = JasperRunManager.runReportToPdf(fileStream, map, FactorySession.getRubikConnection(DomainConfig.getEnvironment()));

                                } catch (JRException ex) {
                                    ex.printStackTrace();
                                }
                                return new ByteArrayInputStream(b);
                            }
                        };

                        StreamResource resource = new StreamResource(source, "Requisicion_" + requi.getFolio() + ".pdf");

                        EmbedWindow windowPDF = new EmbedWindow(resource);
                        windowPDF.setCaption("Requisicion de compra:");
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
                            .withMessage("Debe Completar la Requisicion para Imprimir el documento")
                            .withRetryButton()
                            .open();
                }

            } else {
                MessageBox.createError()
                        .withCaption("Error!")
                        .withMessage("Debe seleccionar una Requisicion para poder imprimirla.")
                        .withRetryButton()
                        .open();
            }
        });
        
        btnTerminar.addClickListener((event) -> {
            if (gridRequisiciones.getSelectedItems().size() == 1) {
                
                Requisicion requisicion = gridRequisiciones.getSelectedItems().iterator().next();
                
                if(requisicion.getEstado_doc().equals(_DocumentoEstados.EN_PROCESO)){
                    
                    MessageBox.createQuestion()
                        .withCaption("Atencion!")
                        .withMessage("Desea que la Requisicion " + requisicion.getFolio() + ""
                                + " sea terminada? Ya no podrá realizar modificaciones.")
                        .withOkButton(() -> {
                            RequisicionDomain domain = new RequisicionDomain();
                            domain.RequisicionTerminar(requisicion);
                            
                            gridRequisiciones.setItems(getRequisiciones());
                            
                            MessageBox.createInfo()
                                    .withCaption("Atencion!")
                                    .withMessage("Requisicion de Compra terminada correctamente. Aun esta pendiente de Autorizacion.")
                                    .withRetryButton()
                                    .open();
                        })
                        .withNoButton(() -> {})
                        .open();
                    
                }else{
                    MessageBox.createError()
                        .withCaption("Error!")
                        .withMessage("Con el estado " + requisicion.getEstado_doc() + " de la Requisicion " + requisicion.getFolio() + ""
                                + " no es posible pasar a Autorizacion.")
                        .withRetryButton()
                        .open();
                }
            } else {
                MessageBox.createError()
                        .withCaption("Error!")
                        .withMessage("Debe tener una Requisicion seleccionada para poder pasarla a Autorizacion.")
                        .withRetryButton()
                        .open();
            }
        });

    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }

    public List getRequisiciones() {
        String strWhere = " activo = 1 ";

        if (!"".equals(txtBusqueda.getValue())) {
            strWhere += " AND folio = '" + txtBusqueda.getValue().toUpperCase() + "'";
        }
        
        if(txtFechaIni.getValue() != null && txtFechaFin.getValue() != null){
            strWhere += "";
        }

        RequisicionDomain service = new RequisicionDomain();
        service.getRequisicion(strWhere, "", "fecha_requerida DESC");
        listRequisiciones = service.getObjects();

        if (!service.getOk()) {
            MessageBox.createError()
                    .withCaption("Error al cargar la informacion!")
                    .withMessage("Err: " + service.getNotification())
                    .withRetryButton()
                    .open();
        }
        return listRequisiciones;
    }
    
}