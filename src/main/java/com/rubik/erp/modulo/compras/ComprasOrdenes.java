/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rubik.erp.modulo.compras;

import com.rubik.erp.domain.OrdenDeCompraDomain;
import com.rubik.erp.fragments.FragmentTop;
import com.rubik.erp.model.Empleado;
import com.rubik.erp.model.OrdenDeCompra;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.rubicone.vaadin.fam3.silk.Fam3SilkIcon;

/**
 *
 * @author Dev
 */
public class ComprasOrdenes  extends Panel implements View {

    public static final String NAME = "ORDENES_COMPRA";
    VerticalLayout container = new VerticalLayout();

    Empleado empleado = (Empleado) VaadinSession.getCurrent().getSession().getAttribute("USUARIO_ACTIVO");

    TextField txtBusqueda = new TextField();
    
    Grid<OrdenDeCompra> gridOC = new Grid<>();
    DateField txtFechaIni = new DateField();
    DateField txtFechaFin = new DateField();
    Button btnSearch = new Button(Fam3SilkIcon.MAGNIFIER);
    
    Button btnAdd = new Button("Agregar", Fam3SilkIcon.ADD);
    Button btnModify = new Button("Modificar", Fam3SilkIcon.PENCIL);
    Button btnCancel = new Button("Cancelar", Fam3SilkIcon.CANCEL);
    Button btnTerminar = new Button("Terminar", Fam3SilkIcon.NOTE_GO);
    Button btnPrint = new Button("Imprimir", Fam3SilkIcon.PRINTER);
    
    List<OrdenDeCompra> listOC = new ArrayList<>();

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    
    public ComprasOrdenes() {
        initComponents();

        HorizontalLayout hLayoutAux = new HorizontalLayout(
                txtBusqueda,
                new Label("Fecha: "),txtFechaIni,new Label("A: "),txtFechaFin,btnSearch);
        
        HorizontalLayout hLayoutAux2 = new HorizontalLayout(btnAdd, btnModify, btnCancel, btnPrint, btnTerminar);
        
        hLayoutAux.setComponentAlignment(hLayoutAux.getComponent(1), Alignment.MIDDLE_CENTER);
        hLayoutAux.setComponentAlignment(hLayoutAux.getComponent(3), Alignment.MIDDLE_CENTER);
        
        Label lblTitulo = new Label("ORDENES DE COMPRA") {
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
                gridOC);
        
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
        txtBusqueda.setPlaceholder("Folio de OC");      

        Grid.Column<OrdenDeCompra, String> columnFecha = gridOC.addColumn(det -> ((det.getFecha_elaboracion()!= null) ? dateFormat.format(det.getFecha_elaboracion()) : ""));
        columnFecha.setCaption("FECHA");
        columnFecha.setId("FECHA");
        columnFecha.setWidth(120);
        gridOC.addColumn(OrdenDeCompra::getFolio).setCaption("FOLIO").setId("FOLIO").setWidth(120);
        gridOC.addColumn(OrdenDeCompra::getFolio_remision).setCaption("FOLIO REM").setId("FOLIO REM").setWidth(120);
        gridOC.addColumn(OrdenDeCompra::getEstado_doc).setCaption("ESTADO").setId("ESTADO").setWidth(130);
        gridOC.addColumn(OrdenDeCompra::getProveedor).setCaption("PROVEEDOR").setId("PROVEEDOR");
        gridOC.addColumn(OrdenDeCompra::getImporte).setCaption("IMPORTE").setId("IMPORTE").setWidth(120);
        gridOC.addColumn(OrdenDeCompra::getIva).setCaption("IVA").setId("IVA").setWidth(120);
        gridOC.addColumn(OrdenDeCompra::getTotal).setCaption("TOTAL").setId("TOTAL").setWidth(120);

        gridOC.setItems(getOrdenes());
        gridOC.setSelectionMode(Grid.SelectionMode.SINGLE);
        gridOC.setSizeFull();
        gridOC.setHeight("500px");

        btnAdd.addClickListener((event) -> {
            WindowOrdenDeCompra windows = new WindowOrdenDeCompra();
            windows.center();
            windows.setModal(true);
            windows.addCloseListener(ev -> {
                gridOC.setItems(getOrdenes());
            });
            getUI().addWindow(windows);
        });

        btnModify.addClickListener((event) -> {
//            if (gridOC.getSelectedItems().size() == 1) {
//                if(gridOC.getSelectedItems().iterator().next().getEstado_doc().equals(_DocumentoEstados.EN_PROCESO)){
//                    WindowRemision windows = new WindowRemision(gridOC.getSelectedItems().iterator().next());
//                    windows.center();
//                    windows.setModal(true);
//                    windows.addCloseListener((e) -> {
//                        gridOC.setItems(getOrdenes());
//                    });
//                    getUI().addWindow(windows);
//                }else{
//                    MessageBox.createError()
//                            .withCaption("Error!")
//                            .withMessage("No puede modificar una Remision de Compra que esta en espera de su Autorizacion.")
//                            .withRetryButton()
//                            .open();
//                }
//            } else {
//                MessageBox.createError()
//                        .withCaption("Error!")
//                        .withMessage("Debe tener una Remision seleccionada para poder modificarla.")
//                        .withRetryButton()
//                        .open();
//            }
        });
        
        btnSearch.addClickListener((event) -> {
            gridOC.setItems(getOrdenes());
            txtBusqueda.setValue("");
        });
        
        btnPrint.addClickListener((event) -> {
        });
        
        btnTerminar.addClickListener((event) -> {
//            if (gridOC.getSelectedItems().size() == 1) {
//                
//                OrdenDeCompra remision = gridOC.getSelectedItems().iterator().next();
//                
//                if(remision.getEstado_doc().equals(_DocumentoEstados.EN_PROCESO)){
//                    
//                    MessageBox.createQuestion()
//                        .withCaption("Atencion!")
//                        .withMessage("Desea que la Remision " + remision.getFolio() + ""
//                                + " sea terminada? Ya no podrá realizar modificaciones.")
//                        .withOkButton(() -> {
//                            OrdenDeCompraDomain domain = new OrdenDeCompraDomain();
//                            domain.RemisionTerminar(remision);
//                            
//                            gridOC.setItems(getOrdenes());
//                            
//                            MessageBox.createInfo()
//                                    .withCaption("Error!")
//                                    .withMessage("Remision de Compra terminada correctamente. Aun esta pendiente de Autorizacion.")
//                                    .withRetryButton()
//                                    .open();
//                        })
//                        .withNoButton(() -> {})
//                        .open();
//                    
//                }else{
//                    MessageBox.createError()
//                        .withCaption("Error!")
//                        .withMessage("Con el estado " + remision.getEstado_doc() + " de la Remision " + remision.getFolio() + ""
//                                + " no es posible pasar a Autorizacion.")
//                        .withRetryButton()
//                        .open();
//                }
//            } else {
//                MessageBox.createError()
//                        .withCaption("Error!")
//                        .withMessage("Debe tener una Remision seleccionada para poder pasarla a Autorizacion.")
//                        .withRetryButton()
//                        .open();
//            }
        });

    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }

    public List getOrdenes() {
        String strWhere = " activo = 1 ";

        if (!"".equals(txtBusqueda.getValue())) {
            strWhere += " AND folio = '" + txtBusqueda.getValue().toUpperCase() + "'";
        }
        
        if(txtFechaIni.getValue() != null && txtFechaFin.getValue() != null){
            strWhere += "";
        }

        OrdenDeCompraDomain service = new OrdenDeCompraDomain();
        service.getOrdenDeCompra(strWhere, "", "fecha_elaboracion DESC");
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