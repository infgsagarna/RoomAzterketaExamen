package com.example.roomazterketaexamen.datos.dao;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.roomazterketaexamen.datos.clase_aux.TareaInfo;
import com.example.roomazterketaexamen.datos.entities.ListaTareas;

import java.util.Date;
import java.util.List;

@Dao
public interface ListaTareasDao {

    @Insert
    public long insertList(ListaTareas listaTareas);

   /* @Query("SELECT lista_tareas.nombre_lista_tareas, lista_tareas.fecha_ultima_tarea, tareas.texto_tarea FROM lista_tareas " +
            "INNER JOIN tareas ON lista_tareas.lista_id = tareas.lista_tarea_id " +
            "WHERE lista_tareas.usuario_id = :userId")
    public List<TareaInfo> getTareaInfo(long userId);*/
   @Query("SELECT lista_tareas.nombre_lista_tareas as nombreListaTareas, lista_tareas.fecha_ultima_tarea as fechaUltimaTarea,"+
           " tareas.fecha_tarea as fechaTarea, tareas.texto_tarea as textoTarea FROM lista_tareas,tareas " +
           "WHERE lista_tareas.lista_id = tareas.lista_tarea_id AND lista_tareas.usuario_id=:userId")
   public List<TareaInfo> getTareaInfo(long userId);

    @Query("select lista_id from lista_tareas where usuario_id=:userId")
    public long getListaIdByUserId(long userId);;

    @Query("UPDATE lista_tareas SET fecha_ultima_tarea= :fecha WHERE lista_id = :listId")
    public void updateListaTareas(long listId, String fecha);

}
