/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rubik.erp.modulo.generic;

import com.rubik.erp._ED;
import com.rubik.erp.domain.NodeFileDomain;
import com.rubik.erp.domain.ProveedorDomain;
import com.rubik.erp.model.Empleado;
import com.rubik.erp.model.NodeFile;
import com.rubik.erp.model.Proveedor;
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
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
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
    
    Button btnGuardar = new Button("Guardar", Fam3SilkIcon.DISK);
    Button btnCancelar = new Button("Cancelar", Fam3SilkIcon.CANCEL);
    
    MultiFileUpload btnSubir;

    public WindowFileNode() {
        setCaption("ALTA DE DOCUMENTO");
        initComponents();
    }

    public void initComponents() {
        String strWidth = "400";
        setWidth("600");
        setHeight("300");
        
        UploadFinishedHandler handlerSuccess = new UploadFinishedHandler() {
            @Override
            public void handleFile(InputStream stream, String fileName, String mimeType, long length, int filesLeftInQueue) {
                InputStream input = null;
                String uuid = UUID.randomUUID().toString();
                try {
                    
                    File archivoCopia = 
                            new File(_ED.FOLDER_ED + System.getProperty("file.separator") + uuid + ".pdf");//ruta definida con el nombre del archivo

                    input = stream;
                    OutputStream output = new FileOutputStream(archivoCopia);

                    byte[] buffer = new byte[1024];// un buffer de 1 KB
                    int bytes = input.read(buffer);
                    int data = 0;
                    while (bytes > 0) {
                        output.write(buffer, 0, bytes);
                        data += bytes;//Compruebo el tamaño del archivo en bytes
                        long i = System.nanoTime();
                        bytes = input.read(buffer);//leo unos bytes del input
                        long f = i - System.nanoTime();//Tomo los nanosegundos que han pasado despues de leer
                    }

                    input.close();
                    output.close();

                    MessageBox.createInfo()
                            .withCaption("Archivo Cargado")
                            .withMessage("El archivo fue cargado correctamente. ")
                            .open();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        
        btnCancelar.addClickListener((event) -> {
            close();
        });

        btnGuardar.addClickListener((event) -> {
            try {

                NodeFileDomain service = new NodeFileDomain();

                fileNode.setFolio(txtFolio.getValue().toUpperCase());
                fileNode.setProveedor(cboProveedor.getValue().toString());
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
            } catch (Exception ex) {
                MessageBox.createError()
                        .withCaption("Error!")
                        .withMessage("Verifique que la informacion este completa o sea correcta. ")
                        .withRetryButton()
                        .open();
            }
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
        
//        cont.setMargin(false);
//        cont.setSpacing(false);
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