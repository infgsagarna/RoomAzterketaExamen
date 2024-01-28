package com.example.roomazterketaexamen.datos;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.roomazterketaexamen.datos.dao.ListaTareasDao;
import com.example.roomazterketaexamen.datos.dao.TareaDao;
import com.example.roomazterketaexamen.datos.dao.UsuarioDao;
import com.example.roomazterketaexamen.datos.entities.ListaTareas;
import com.example.roomazterketaexamen.datos.entities.Tarea;
import com.example.roomazterketaexamen.datos.entities.Usuario;

@Database(entities = {Usuario.class, ListaTareas.class, Tarea.class}, version = 1, exportSchema = false)

public abstract class ListaTareasDatabase extends RoomDatabase {

    public abstract UsuarioDao usuarioDao();
    public abstract TareaDao tareaDao();
    public abstract ListaTareasDao listaTareasDao();


    // Puedes crear una instancia única de la base de datos (Patrón Singleton)
    private static volatile ListaTareasDatabase INSTANCE;

    public static ListaTareasDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ListaTareasDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    ListaTareasDatabase.class, "lista_tareas_database").allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
