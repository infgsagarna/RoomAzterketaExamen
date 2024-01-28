package com.example.roomazterketaexamen;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.roomazterketaexamen.datos.ListaTareasDatabase;
import com.example.roomazterketaexamen.datos.clase_aux.TareaInfo;
import com.example.roomazterketaexamen.datos.entities.Tarea;
import com.example.roomazterketaexamen.datos.entities.Usuario;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ListaTareasActivity extends AppCompatActivity {
    private ListaTareasDatabase bd; // Declarar tu instancia de la base de datos
    //--

    String nombreTarea;

    long userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_tareas);

        // Leer el ID del usuario
        Intent intent = getIntent();
        if (intent != null) {
            userId = intent.getLongExtra("userId", 0L);
            Toast.makeText(ListaTareasActivity.this, "UserID: " + userId,
                    Toast.LENGTH_SHORT).show();
        }


        // Cargar base de datos.
        bd = ListaTareasDatabase.getDatabase(this);

        String nombre = bd.usuarioDao().getUserNameById(userId);

        TextView tvUsuario = findViewById(R.id.textViewUsuario);
        tvUsuario.setText("Bienvenido "+nombre);

        leerTareas();

        // listeners de los botones.
        Button bGuardar = findViewById(R.id.bGuardarTarea);
        bGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Guardar productos
                guardarTarea();
            }
        });

        Button bEliminarUser = findViewById(R.id.bEliminarUser);
        bEliminarUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Eliminar usuario.
                eliminarUsuario();
            }
        });

        Button bEliminar = findViewById(R.id.bEliminarTarea);
        bEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // eliminar producto
                eliminarTarea();
            }
        });
    }

    private void eliminarTarea() {
        // leer datos introducidos.
        getDatos();
        // crear objeto.
        Tarea oTarea;
        oTarea = bd.tareaDao().getTareaByName(nombreTarea);
        if (oTarea != null) {
            // eliminar tarea.
            bd.tareaDao().deleteTarea(oTarea);
            // actualizar lista tareas.
            leerTareas();
        } else {
            Toast.makeText(ListaTareasActivity.this, "Tarea inexistente: " + nombreTarea,
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void eliminarUsuario() {
        Usuario oUser = bd.usuarioDao().getUserById(userId);
        bd.usuarioDao().deleteUser(oUser);
        Toast.makeText(ListaTareasActivity.this, "Usuario eliminado: " + oUser.getNombre(),
                Toast.LENGTH_SHORT).show();
        finish();
    }

    private void guardarTarea() {
        // Leer datos introducidos.
        getDatos();
        // crear objetotarea
        Tarea oTarea;
        long listaId=bd.listaTareasDao().getListaIdByUserId(userId);
        long tareaId;

        //obtener String con fecha actual
        String fecha=fechaActual();

        oTarea = bd.tareaDao().getTareaByName(nombreTarea);
        if (oTarea == null){ // si es un nueva la tarea, meterla en la BS.
            oTarea = new Tarea(fecha,nombreTarea,listaId);
            tareaId = bd.tareaDao().insertTarea(oTarea);
            bd.listaTareasDao().updateListaTareas(listaId,fecha);
          //  oTarea.setProductoId(prodId);
        }else {
            Toast.makeText(ListaTareasActivity.this, "Tarea existente.",
                    Toast.LENGTH_SHORT).show();
        }

        leerTareas();
    }

    private String fechaActual() {
        //Update de la ultima fecha de actualización de la lista de tareas
        long ahora = System.currentTimeMillis();
        Calendar calendario = Calendar.getInstance();
        calendario.setTimeInMillis(ahora);
        int d = calendario.get(Calendar.DAY_OF_MONTH);
        String dia=String.valueOf(d);
        if (d < 10)
            dia="0"+d;
        int m = calendario.get(Calendar.MONTH)+1;
        String mes=String.valueOf(m);
        if (m < 10)
            mes="0"+m;
        String anno = String.valueOf(calendario.get(Calendar.YEAR));
        return dia+"/"+mes+"/"+anno;
    }

    private void leerTareas() {

       // long listaId = userId;
        List<TareaInfo> TareasList =  bd.listaTareasDao().getTareaInfo(userId);


        String listaTareas = "";
        for (int i = 0; i < TareasList.size();i++) {
            String tareaInfo = "- "+TareasList.get(i).getNombreListaTareas() + "\n          Última tarea: " + TareasList.get(i).getFechaUltimaTarea()
                    + "\n          Nombre Tarea: " + TareasList.get(i).getTextoTarea()
                    + "\n          Fecha Tarea: " + TareasList.get(i).getFechaTarea();
            listaTareas += tareaInfo + "\n\n";
            //listaTareas += tareaInfo;
        }

        TextView tLista = findViewById(R.id.tLista);
        // Atención!!! operación asíncrona
        tLista.setText(listaTareas);

    }



    private void getDatos() {

        EditText etTarea = findViewById(R.id.editTextTarea);

        // Obtener el texto del EditText
        nombreTarea = etTarea.getText().toString();

    }
}