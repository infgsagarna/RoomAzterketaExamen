package com.example.roomazterketaexamen;

import com.example.roomazterketaexamen.datos.ListaTareasDatabase;
import com.example.roomazterketaexamen.datos.entities.ListaTareas;
import com.example.roomazterketaexamen.datos.entities.Tarea;
import com.example.roomazterketaexamen.datos.entities.Usuario;

import java.util.Date;

public class PrecargaBD {

    public static void insertarDatos(ListaTareasDatabase bd) {
        // Aquí puedes realizar inserciones de datos de prueba utilizando los DAOs

        // Por ejemplo, para insertar un usuario de prueba:
        Usuario u1 = new Usuario("gontzal", "gontzal@gmail.com", "1234");
        Usuario u2 = new Usuario("oier", "oier@gmail.com", "1234");
        long us1=bd.usuarioDao().insertUser(u1);
        long us2=bd.usuarioDao().insertUser(u2);


        // Inserta listas de compras de prueba
        //ListaCompra shoppingList1 = new ListaCompra("Zerrenda 1", new Date(), user.getUserId());
        ListaTareas listaTareas = new ListaTareas("Lista "+u1.getNombre(), "05/02/2023", us1);
        long lt1=bd.listaTareasDao().insertList(listaTareas);
        ListaTareas listaTareas2 = new ListaTareas("Lista "+u2.getNombre(), "05/02/2022", us2);
        long lt2=bd.listaTareasDao().insertList(listaTareas2);


        // Inserta tareas de prueba para Lista 1
        Tarea t1 = new Tarea("05/02/2023","Ir al médico",lt1);
        Tarea t2 = new Tarea("05/02/2023","Examen Acceso a datos",lt1);

        // Inserta tareas de prueba para Lista 2
        Tarea t3 = new Tarea("05/02/2022","Ir a comer",lt2);
        Tarea t4 = new Tarea("05/02/2022","Sacar el DNI",lt2);

        bd.tareaDao().insertTareas(t1,t2,t3,t4);

        // Puedes continuar insertando más datos de prueba según tus necesidades
    }
}
