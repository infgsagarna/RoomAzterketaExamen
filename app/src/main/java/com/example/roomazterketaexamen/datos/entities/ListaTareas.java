package com.example.roomazterketaexamen.datos.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity (tableName = "lista_tareas",
foreignKeys = @ForeignKey(
        entity = Usuario.class,
        parentColumns = "usuario_id", // Este es el nombre del usuario
        childColumns = "usuario_id", // Este es el nombre de usuario de esta clase
        onDelete = ForeignKey.CASCADE))
public class ListaTareas {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "lista_id")
    private long listaId;

    @NonNull
    @ColumnInfo(name = "nombre_lista_tareas")
    private String nombre;


    @ColumnInfo(name = "fecha_ultima_tarea")
    private String fechaUltimaTarea;

    @NonNull
    @ColumnInfo(name = "usuario_id")
    private long usuarioId;

    public ListaTareas() {

    }

    public ListaTareas(@NonNull String nombre, String fechaUltimaTarea, long usuarioId) {
        this.nombre = nombre;
        this.fechaUltimaTarea = fechaUltimaTarea;
        this.usuarioId = usuarioId;
    }

    public long getListaId() {
        return listaId;
    }

    public void setListaId(long listaId) {
        this.listaId = listaId;
    }


    public String getFechaUltimaTarea() {
        return fechaUltimaTarea;
    }

    public void setFechaUltimaTarea( String fechaUltimaTarea) {
        this.fechaUltimaTarea = fechaUltimaTarea;
    }

    @NonNull
    public String getNombre() {
        return nombre;
    }

    public void setNombre(@NonNull String nombre) {
        this.nombre = nombre;
    }

    public long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(long usuarioId) {
        this.usuarioId = usuarioId;
    }
}
