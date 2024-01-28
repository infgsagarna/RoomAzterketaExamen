package com.example.roomazterketaexamen.datos.clase_aux;

import java.util.Date;

public class TareaInfo {

    private String nombreListaTareas;
    private String fechaUltimaTarea;

    private String fechaTarea;
    private String textoTarea;

    public TareaInfo(String nombreListaTareas, String fechaUltimaTarea, String fechaTarea, String textoTarea) {
        this.nombreListaTareas = nombreListaTareas;
        this.fechaUltimaTarea = fechaUltimaTarea;
        this.fechaTarea = fechaTarea;
        this.textoTarea = textoTarea;
    }

    public TareaInfo() {
    }

    public String getNombreListaTareas() {
        return nombreListaTareas;
    }

    public void setNombreListaTareas(String nombreListaTareas) {
        this.nombreListaTareas = nombreListaTareas;
    }

    public String getFechaUltimaTarea() {
        return fechaUltimaTarea;
    }

    public void setFechaUltimaTarea(String fechaUltimaTarea) {
        this.fechaUltimaTarea = fechaUltimaTarea;
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

