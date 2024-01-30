package com.example.roomazterketaexamen.datos.clase_aux;

import java.util.Date;

public class TareaInfo {

   // private String nombreListaTareas;
    //private String fechaUltimaTarea;

    private String fechaTarea;
    private String textoTarea;

    public TareaInfo(String fechaTarea, String textoTarea) {
        this.fechaTarea = fechaTarea;
        this.textoTarea = textoTarea;
    }

    public TareaInfo() {
    }

    public String getFechaTarea() {
        return fechaTarea;
    }

    public void setFechaTarea(String fechaTarea) {
        this.fechaTarea = fechaTarea;
    }

    public String getTextoTarea() {
        return textoTarea;
    }

    public void setTextoTarea(String textoTarea) {
        this.textoTarea = textoTarea;
    }
}

