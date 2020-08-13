/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rubik.erp.modulo.generic;

import com.rubik.Base.DocumentObjectBase;
import com.rubik.erp.domain.NodeFileDomain;
import com.rubik.erp.model.Empleado;
import com.rubik.erp.model.NodeFile;
import com.rubik.erp.util.ExpedienteDigital;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import de.steinwedel.messagebox.MessageBox;
import java.util.ArrayList;
import java.util.List;
import org.rubicone.vaadin.fam3.silk.Fam3SilkIcon;

/**
 *
 * @author Dev
 */
public class WindowVisorDocumentos extends Window {
    
    Empleado empleado = (Empleado) VaadinSession.getCurrent().getSession().getAttribute("USUARIO_ACTIVO");
    
    VerticalLayout cont = new VerticalLayout();
    
    DocumentObjectBase documento;
    
    Button btnAdd = new Button("Agregar",Fam3SilkIcon.ADD);
    Button btnDelete = new Button("Eliminar",Fam3SilkIcon.DELETE);
    
    Grid<NodeFile> gridDocumentos = new Grid<>();
    List<NodeFile> listProducto = new ArrayList<>();
    
    public WindowVisorDocumentos(DocumentObjectBase document, String tipo_documento) {
        documento = document;
        setCaption(tipo_documento + " DE " + document.getTipo_documento() + " FOLIO " + document.getFolio());
        VaadinSession.getCurrent().getSession().setAttribute("PRODUCTO_SELECCIONADO",null);
        initComponents();
    }

    public void initComponents() { 
        gridDocumentos.setWidth("100%");
        gridDocumentos.setSelectionMode(SelectionMode.SINGLE);
        gridDocumentos.addColumn(NodeFile::getCliente_proveedor).setCaption("CTE/PROVEEDOR").setId("CTE/PROVEEDOR");
        gridDocumentos.addColumn(NodeFile::getFolio).setCaption("FOLIO").setId("FOLIO");
        gridDocumentos.addComponentColumn(this::getBtnVisor).setCaption("VISUALIZAR").setWidth(120);

        cont.addComponents(new HorizontalLayout(btnAdd,btnDelete), gridDocumentos);
        cont.setComponentAlignment(cont.getComponent(0), Alignment.MIDDLE_CENTER);
        cont.setComponentAlignment(cont.getComponent(1), Alignment.MIDDLE_CENTER);

        btnDelete.addClickListener((event) -> {
            if (gridDocumentos.getSelectedItems().size() == 1) {

                MessageBox.createQuestion()
                        .withCaption("Atencion")
                        .withMessage("Desea eliminar el Documento seleccionado?")
                        .withYesButton(() -> {

                            NodeFileDomain service = new NodeFileDomain();
                            service.NodeFileDelete(gridDocumentos.getSelectedItems().iterator().next());
                            if (service.getOk()) {
                                ExpedienteDigital.deleteDocument(gridDocumentos.getSelectedItems().iterator().next());

                                MessageBox.createError()
                                        .withCaption("Atencion!")
                                        .withMessage(service.getNotification())
                                        .withRetryButton()
                                        .open();
                            }

                        })
                        .withRetryButton()
                        .open();
            }
        });
        
        btnAdd.addClickListener((event) -> {
            WindowFileNode windows = new WindowFileNode(documento);
            windows.center();
            windows.setModal(true);
            windows.addCloseListener(ev -> {
                gridDocumentos.setItems(getNodes());
            });
            getUI().addWindow(windows);
        });
        
        gridDocumentos.setItems(getNodes());
        
        setResizable(false);
        setContent(cont);
        setModal(true);
        center();
        setWidth("70%");
        setHeight("70%");
    }
    
    public List getNodes() {
        NodeFileDomain service = new NodeFileDomain();
        service.getNodeFile("parent_id = " + documento.getId() + " AND tipo_documento = '" + documento.getTipo_documento() + "'", "", " id ASC");
        listProducto = service.getObjects();

        if (!service.getOk()) {
            MessageBox.createError()
                    .withCaption("Error al cargar la informacion!")
                    .withMessage("Err: " + service.getNotification())
                    .withRetryButton()
                    .open();
        }
        return listProducto;
    }
    
    private Button getBtnVisor(NodeFile node) {
        Button btnVisor = new Button("", Fam3SilkIcon.REPORT);
        btnVisor.setDescription("Ver Documento");
        btnVisor.addClickListener((event) -> {
            ContentPDFViewer window = new ContentPDFViewer(
                    node.getUrl(), 
                    node.getNombre(),
                    "Cotizacion: " + node.getFolio());
            getUI().addWindow(window);
        });

        return btnVisor;
    }
    
}