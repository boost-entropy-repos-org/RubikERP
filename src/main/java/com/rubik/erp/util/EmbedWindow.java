/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rubik.erp.util;

import com.vaadin.server.StreamResource;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Window;

/**
 *
 * @author Dev
 */
public class EmbedWindow extends Window {

    String file_name;
    StreamResource.StreamSource source;
    StreamResource resource;
    String mimeType;
    Embedded emb;

    public EmbedWindow(String file_name, StreamResource.StreamSource source) {
        this.file_name = file_name;
        this.source = source;
        setDraggable(false);
        setResizable(false);
        setScrollLeft(15);
        center();
        setModal(true);
    }

    public EmbedWindow(String file_name, StreamResource resource) {
        this.file_name = file_name;
        this.resource = resource;
        setDraggable(false);
        setResizable(false);
        setScrollLeft(15);
        center();
        setModal(true);
    }

    public EmbedWindow(StreamResource.StreamSource source) {
        this.source = source;
        setDraggable(false);
        setResizable(false);
        setScrollLeft(15);
        center();
        setModal(true);
    }

    public EmbedWindow(StreamResource resource) {
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

}
