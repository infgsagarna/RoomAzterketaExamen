package com.example.roomazterketaexamen.datos.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.roomazterketaexamen.datos.entities.Tarea;
import com.example.roomazterketaexamen.datos.entities.Usuario;

@Dao
public interface TareaDao {

    @Insert
    public long insertTarea(Tarea tarea);

    @Insert
    public void insertTareas(Tarea... tarea);

    /*@Query("Select * from tareas where texto_tarea=:nombreTarea")
    public Tarea getTareaByName(String nombreTarea);*/

    @Query("Select * from tareas where texto_tarea=:nombreTarea AND lista_id=:listaId")
    public Tarea getTareaByNameAndListaId(String nombreTarea,long listaId);

    @Delete
    void deleteTarea(Tarea tarea);
}
