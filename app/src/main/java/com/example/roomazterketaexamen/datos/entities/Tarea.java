package com.example.roomazterketaexamen.datos.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity (tableName = "tareas",
foreignKeys = @ForeignKey(
        entity = ListaTareas.class,
        parentColumns = "lista_id",
        childColumns = "lista_id",
        onDelete = ForeignKey.CASCADE))
public class Tarea {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "tarea_id")
    private long tareaId;

    @ColumnInfo(name = "fecha_tarea")
    private String fechaTarea;

    @ColumnInfo(name = "texto_tarea")
    private String textoTarea;

    @NonNull
    @ColumnInfo(name = "lista_id")
    private long listaId;

    public Tarea() {

    }

    public Tarea(String fechaTarea, String textoTarea, long listaId) {
        this.fechaTarea = fechaTarea;
        this.textoTarea = textoTarea;
        this.listaId = listaId;
    }

    public long getTareaId() {
        return tareaId;
    }

    public void setTareaId(long tareaId) {
        this.tareaId = tareaId;
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

    public long getListaId() {
        return listaId;
    }

    public void setListaId(long listaId) {
        this.listaId = listaId;
    }
}
