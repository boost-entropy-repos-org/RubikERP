/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rubik.erp.modulo.generic;

import com.vaadin.server.StreamResource;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Window;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 *
 * @author GRUCAS
 */
public final class ContentPDFViewer extends Window {

    String file_name;
    String path;
    String title;
    String mimeType;
    Embedded emb;

    public ContentPDFViewer(String url, String nombre, String title) {
        this.file_name = nombre;
        this.path = url;
        this.title = title;
        generarPDF(path, file_name);
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public Embedded getEmb() {
        return emb;
    }

    public void setEmb(Embedded emb) {
        this.emb = emb;
    }

    public void insertEmbedded() {

    }

    public void generarPDF(String url, String nombre) {
        File file = new File(url);

        try {
            StreamResource.StreamSource resource = () -> {
                byte[] b = null;
                try {
                    b = Files.readAllBytes(file.toPath());
                } catch (IOException ex) {
                    
                }
                return new ByteArrayInputStream(b);
            };
            
            StreamResource source = new StreamResource(resource, nombre);
            setCaption(title);
            setHeight("100%");
            setWidth("80%");
            setMimeType("application/pdf");
            setDraggable(false);
            setResizable(false);
            setScrollLeft(15);
            center();
            setModal(true);
            
            emb = new Embedded(title, source);
            emb.setType(Embedded.TYPE_BROWSER);
            emb.setMimeType(getMimeType());
            emb.setSizeFull();
            setContent(emb);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
    
}
