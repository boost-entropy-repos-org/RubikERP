/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rubik.erp.modulo.generic;

import com.rubik.Base.DocumentObjectBase;
import com.rubik.erp.domain.NodeFileDomain;
import com.rubik.erp.domain.ProveedorDomain;
import com.rubik.erp.model.Empleado;
import com.rubik.erp.model.NodeFile;
import com.rubik.erp.model.Proveedor;
import com.rubik.erp.util.ExpedienteDigital;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;
import com.wcs.wcslib.vaadin.widget.multifileupload.ui.MultiFileUpload;
import com.wcs.wcslib.vaadin.widget.multifileupload.ui.UploadFinishedHandler;
import com.wcs.wcslib.vaadin.widget.multifileupload.ui.UploadStateWindow;
import de.steinwedel.messagebox.MessageBox;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.rubicone.vaadin.fam3.silk.Fam3SilkIcon;

/**
 *
 * @author Dev
 */
public class WindowFileNode extends Window {
    
    Empleado empleado = (Empleado) VaadinSession.getCurrent().getSession().getAttribute("USUARIO_ACTIVO");
    
    VerticalLayout cont = new VerticalLayout();

    NodeFile fileNode = new NodeFile();

    TextField txtFolio = new TextField("Folio del Documento:");
    NativeSelect<Proveedor> cboProveedor = new NativeSelect("Proveedor:");
    List<Proveedor> proveedorList = new ArrayList<>();
    
    Button btnCancelar = new Button("Cancelar", Fam3SilkIcon.CANCEL);
    MultiFileUpload btnSubir;
    
    DocumentObjectBase documento;

    public WindowFileNode(DocumentObjectBase document) {
        setCaption("ALTA DE DOCUMENTO");
        documento = document;
        initComponents();
    }

    public void initComponents() {
        String strWidth = "400";
        setWidth("600");
        setHeight("300");
        
        UploadFinishedHandler handlerSuccess = (InputStream stream, String fileName, String mimeType, long length, int filesLeftInQueue) -> {
            if (txtFolio.getValue().isEmpty()) {
                MessageBox.createError()
                        .withCaption("Error!")
                        .withMessage("Debe colocar primero un folio valido y seleccionar un Proveedor para subir el archivo.. ")
                        .withRetryButton()
                        .open();
            } else {
                try {
                    NodeFileDomain service = new NodeFileDomain();

                    fileNode.setParent_id(documento.getId());
                    fileNode.setParent_folio(documento.getFolio());
                    fileNode.setNombre(txtFolio.getValue().toUpperCase() + ".pdf");
                    fileNode.setFolio(txtFolio.getValue().toUpperCase());
                    fileNode.setCliente_proveedor_id(cboProveedor.getValue().getId());
                    fileNode.setCliente_proveedor(cboProveedor.getValue().getRazon_social());
                    fileNode.setTipo_documento(documento.getTipo_documento());
                    fileNode.setExtension(mimeType);

                    if (ExpedienteDigital.saveDocumentInEDFolder(stream, fileNode)) {
                        service.NodeFileInsert(fileNode);

                        if (service.getOk()) {
                            MessageBox.createInfo()
                                    .withCaption("Atencion")
                                    .withMessage(service.getNotification())
                                    .open();
                            close();
                        } else {
                            MessageBox.createError()
                                    .withCaption("Error!")
                                    .withMessage(service.getNotification())
                                    .withRetryButton()
                                    .open();
                        }
                    } else {
                        MessageBox.createError()
                                .withCaption("Error!")
                                .withMessage("Error al intentar guardar el archivo en el servidor.")
                                .withRetryButton()
                                .open();
                    }
                } catch (Exception ex) {
                    MessageBox.createError()
                            .withCaption("Error!")
                            .withMessage("Verifique que la informacion este completa o sea correcta. " + ex.getMessage())
                            .withRetryButton()
                            .open();
                }
            }
        };
        
        btnCancelar.addClickListener((event) -> {
            close();
        });

        cboProveedor.setItems(getProveedor());
        cboProveedor.setEmptySelectionAllowed(false);
        
        try {
            cboProveedor.setSelectedItem(proveedorList.get(0));
        } catch (Exception e) {
            MessageBox.createError()
                    .withCaption("Error!")
                    .withMessage("No existen proveedores dados de alta. ")
                    .withRetryButton()
                    .open();
        }
        
        txtFolio.setWidth(strWidth);
        cboProveedor.setWidth(strWidth);
        
        UploadStateWindow window = new UploadStateWindow();
        window.setOverallProgressVisible(true);
        window.setModal(true);
        window.center();
        
        btnSubir = new MultiFileUpload(handlerSuccess, window, false);
        
        btnSubir.setWidth("200");
        btnSubir.getSmartUpload().setUploadButtonCaptions("Subir y Guardar", "Subir y Guardar");
        btnSubir.setMaxFileCount(1);
        btnSubir.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
        btnSubir.addStyleName(ValoTheme.BUTTON_PRIMARY);
        btnSubir.getSmartUpload().setAcceptFilter("PDF");
        
        FormLayout fLay = new FormLayout();
        
        fLay.addComponents(new Label("Añadir Cotizacion a la Requisicion"){{setStyleName("h3");}});
        fLay.addComponents(txtFolio, cboProveedor);
        fLay.setMargin(false);
        fLay.setSpacing(false);
        
        cont.addComponents(fLay, new HorizontalLayout(btnCancelar, btnSubir));
        cont.setComponentAlignment(cont.getComponent(1), Alignment.MIDDLE_CENTER);
        
        setContent(cont);
        setResizable(false);
    }
    
    public List<Proveedor> getProveedor() {
        ProveedorDomain provService = new ProveedorDomain();
        provService.getProveedor("", "", "razon_social ASC");
        proveedorList = provService.getObjects();
        return proveedorList;
    }
    
}