/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rubik.erp.util;

import com.vaadin.server.StreamResource;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Window;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

/**
 *
 * @author GRUCAS
 */
public class ContentPDFViewer extends Window {

    String file_name;
    StreamResource.StreamSource source;
    StreamResource resource;
    String mimeType;
    Embedded emb;

    public ContentPDFViewer(String file_name, StreamResource.StreamSource source) {
        this.file_name = file_name;
        this.source = source;
        setDraggable(false);
        setResizable(false);
        setScrollLeft(15);
        center();
        setModal(true);
    }

    public ContentPDFViewer(String file_name, StreamResource resource) {
        this.file_name = file_name;
        this.resource = resource;
        setDraggable(false);
        setResizable(false);
        setScrollLeft(15);
        center();
        setModal(true);
    }

    public ContentPDFViewer(StreamResource.StreamSource source) {
        this.source = source;
        setDraggable(false);
        setResizable(false);
        setScrollLeft(15);
        center();
        setModal(true);
    }

    public ContentPDFViewer(StreamResource resource) {
        this.resource = resource;
        setDraggable(false);
        setResizable(false);
        setScrollLeft(15);
        center();
        setModal(true);
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public StreamResource.StreamSource getSource() {
        return source;
    }

    public void setSource(StreamResource.StreamSource source) {
        this.source = source;
    }

    public StreamResource getResource() {
        return resource;
    }

    public void setResource(StreamResource resource) {
        this.resource = resource;
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
        emb = new Embedded("", resource);
        emb.setType(Embedded.TYPE_BROWSER);
        emb.setMimeType(getMimeType());
        emb.setSizeFull();
        setContent(emb);
    }

    public void generarPDF(String url, String nombre) {
        File file = new File(url);

        try {
            StreamResource.StreamSource resource = new StreamResource.StreamSource() {
                @Override
                public InputStream getStream() {

                    byte[] b = null;
                    try {
                        b = Files.readAllBytes(file.toPath());
                    } catch (IOException ex) {
                        
                    }
                    return new ByteArrayInputStream(b);
                }
            };
            
            StreamResource source = new StreamResource(resource, nombre);
            setCaption("Documento de cliente");
            setHeight("100%");
            setWidth("80%");
            setMimeType("application/pdf");
            setDraggable(false);
            setResizable(false);
            setScrollLeft(15);
            center();
            setModal(true);
            insertEmbedded();
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
    
}
