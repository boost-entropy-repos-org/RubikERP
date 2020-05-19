/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rubik.erp.util;

import com.rubik.erp.model.NodeFile;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author Dev
 */
public class ExpedienteDigital {
    
    //ruta de destino a carpeta temporal
    public static final String SEPARADOR = System.getProperty("file.separator");
    public static final String FOLDER_ED = System.getProperty("catalina.base") + SEPARADOR + "ED_Impeller";
    
    public static boolean saveDocumentInEDFolder(InputStream input, NodeFile node){
        
        File archivoCopia = null;
        
        createEDFolder(node.getTipo_documento() + SEPARADOR);
        
        try {

            archivoCopia
                    = new File(
                            FOLDER_ED + SEPARADOR + 
                            node.getTipo_documento() + SEPARADOR + 
                            node.getCliente_proveedor_id() + SEPARADOR + 
                            node.getNombre() + ".pdf");
            
            OutputStream output;
            output = new FileOutputStream(archivoCopia);

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
            
            if(archivoCopia.exists()){
                return true;
            }else{
                return false;
            }

        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return false;
    }
    
    static void createEDFolder(String subfolder){
        File f = new File(FOLDER_ED + subfolder);
        
        if(!f.exists()){
            f.mkdir();
        }
    }
    
}