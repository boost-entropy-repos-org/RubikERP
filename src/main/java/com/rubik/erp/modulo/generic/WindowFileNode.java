/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rubik.erp.modulo.generic;

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
import de.steinwedel.messagebox.MessageBox;
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
    
    Button btnGuardar = new Button("Guardar", Fam3SilkIcon.DISK);
    Button btnCancelar = new Button("Cancelar", Fam3SilkIcon.CANCEL);

    public WindowFileNode() {
        setCaption("ALTA DE DOCUMENTO");
        initComponents();
    }

    public void initComponents() {
        setContent(cont);
        setResizable(false);
        
        String strWidth = "400";
        setWidth("600");
        setHeight("550");
        
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
        
        FormLayout fLay = new FormLayout();
        
        fLay.addComponents(new Label("Añadir Cotizacion a la Requisicion"){{setStyleName("h3");}});
        fLay.addComponents(txtFolio, cboProveedor);
     
        cont.addComponents(fLay, new HorizontalLayout(btnCancelar, btnGuardar));
        cont.setComponentAlignment(cont.getComponent(1), Alignment.MIDDLE_CENTER);
    }
    
    public List<Proveedor> getProveedor() {
        ProveedorDomain provService = new ProveedorDomain();
        provService.getProveedor("", "", "razon_social ASC");
        proveedorList = provService.getObjects();
        return proveedorList;
    }
    
}