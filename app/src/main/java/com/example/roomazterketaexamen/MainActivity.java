package com.example.roomazterketaexamen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.roomazterketaexamen.datos.ListaTareasDatabase;
import com.example.roomazterketaexamen.datos.entities.ListaTareas;
import com.example.roomazterketaexamen.datos.entities.Usuario;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private ListaTareasDatabase bd; // Declarar tu instancia de la base de datos

    private SharedPreferences sharedPreferences;

    final private static String TAG = "BaseDatos_Lista_Tareas";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bd = ListaTareasDatabase.getDatabase(this);


        // rellenar con datos.
        //PrecargaBD.insertarDatos(bd);


        Button bRegistrar = findViewById(R.id.bRegistrar);

        bRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // registrar nuevo usuario
                registrar();
            }
        });

        Button bLogear = findViewById(R.id.bLogear);
        bLogear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // logear usuario
                logear();
            }
        });

        // SharedPreferences abiarazi.
        sharedPreferences = getSharedPreferences("DatosLogin", Context.MODE_PRIVATE);

    }

    @Override
    public void onStart() {
        super.onStart();

        leerDatosLogin();

    }

    private void registrar() {
        EditText etEmail = findViewById(R.id.editTextEmail);
        EditText etPass = findViewById(R.id.editTextPass);

        // Obtener el texto del EditText
        String email = etEmail.getText().toString();
        String password = etPass.getText().toString();

        Usuario oUser = bd.usuarioDao().getUserByEmail(email);

        if (oUser == null ) {
            Log.d(TAG, "Registro:success");
            // Crear usuario



            //***********EL NOMBRE SERA LO INTRODUCIDO ANTES DE LA ARROBA**********///////
            String nombre = email.split("@")[0];

            // Crear lista tarea para el usuario.
            ListaTareas listaTareas = new ListaTareas("Lista " + nombre, null);

            long listaId=bd.listaTareasDao().insertList(listaTareas);


            //Crear Usuario
            oUser = new Usuario(nombre, email, password,listaId);

            //hasiera batean ez da beharrezkoa
            long userId = bd.usuarioDao().insertUser(oUser);
         //   Log.d(TAG, "  userId: " + userId);
            oUser.setUserId(userId);





            // Mostrar mensaje.
            Toast.makeText(MainActivity.this, "Registrado: " + oUser.getNombre(),
                    Toast.LENGTH_SHORT).show();
            guardarDatos();

            // LLamar a listaCompraActivity.
            Intent intent = new Intent(MainActivity.this, ListaTareasActivity.class);
            intent.putExtra("userId", oUser.getUserId());
            intent.putExtra("listaId", oUser.getListaId());
            startActivity(intent);
        } else {
            Log.d(TAG, "Registro:Failed");
            Toast.makeText(MainActivity.this, "Email ya registrado.",
                    Toast.LENGTH_SHORT).show();
        }
    }


    private void logear() {
        EditText etEmail = findViewById(R.id.editTextEmail);
        EditText etPass = findViewById(R.id.editTextPass);

        // Obtener el texto del EditText
        String email = etEmail.getText().toString();
        String password = etPass.getText().toString();

        Usuario oUser = bd.usuarioDao().getUserByEmail(email);


        if (oUser != null && oUser.getPassword().equals(password)) {
            Log.d(TAG, "Login:success");
            Toast.makeText(MainActivity.this, "Logeado: " + oUser.getNombre(),
                    Toast.LENGTH_SHORT).show();
            guardarDatos();
            // Llamar a listaTareasActivity
            Intent intent = new Intent(MainActivity.this, ListaTareasActivity.class);
            intent.putExtra("userId", oUser.getUserId());
            intent.putExtra("listaId", oUser.getListaId());
            startActivity(intent);
        } else {
            Toast.makeText(MainActivity.this, "Usuario y/o contraseña incorrecta",
                    Toast.LENGTH_SHORT).show();
            Log.d(TAG, "Login:error");
        }




    }


    private void leerDatosLogin() {

        String email = sharedPreferences.getString("Email", "");
        String password = sharedPreferences.getString("Pass", "");

        EditText etEmail = findViewById(R.id.editTextEmail);
        EditText etPass = findViewById(R.id.editTextPass);

        etEmail.setText(email);
        etPass.setText(password);

    }


    private void guardarDatos() {
        EditText etEmail = findViewById(R.id.editTextEmail);
        EditText etPass = findViewById(R.id.editTextPass);

        // Obtener el texto del EditText
        String email = etEmail.getText().toString();
        String password = etPass.getText().toString();

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Email", email);
        editor.putString("Pass", password);
        editor.apply();
    }
}