package com.aplicacion.ejercicio3_1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aplicacion.ejercicio3_1.Clases.SQLiteConexion;
import com.aplicacion.ejercicio3_1.Clases.TablaEmpleados;

public class MainActivity extends AppCompatActivity {

    SQLiteConexion conexion;
    Button btnGuardar, btnLista;
    EditText txtNombre, txtApellido, txtEdad, txtDireccion, txtPuesto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtNombre = (EditText) findViewById(R.id.txtNombre);
        txtApellido = (EditText) findViewById(R.id.txtApellido);
        txtEdad = (EditText) findViewById(R.id.txtEdad);
        txtDireccion = (EditText) findViewById(R.id.txtDireccion);
        txtPuesto = (EditText) findViewById(R.id.txtPuesto);
        btnGuardar = (Button) findViewById(R.id.btnGuardar);
        btnLista = (Button) findViewById(R.id.btnLista);

        //Guarda los datos del Empleado en la BD
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!txtNombre.getText().toString().isEmpty() && !txtApellido.getText().toString().isEmpty() &&
                !txtEdad.getText().toString().isEmpty() && !txtDireccion.getText().toString().isEmpty() &&
                !txtPuesto.getText().toString().isEmpty()) {
                    registrarEmpleado();
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("Verifique que todos los campos esten Completos")
                            .setTitle("Atención");

                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            }
        });

        //Muestra la lista de Empleados Registrados en la BD
        btnLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ActivityListaEmpleados.class);
                startActivity(i);
            }
        });
    }

    public void registrarEmpleado() {
        conexion=new SQLiteConexion(this, TablaEmpleados.NameDatabase, null, 1);
        SQLiteDatabase db = conexion.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(TablaEmpleados.nombres, txtNombre.getText().toString().trim());
        valores.put(TablaEmpleados.apellidos, txtApellido.getText().toString().trim());
        valores.put(TablaEmpleados.edad, txtEdad.getText().toString().trim());
        valores.put(TablaEmpleados.direccion, txtDireccion.getText().toString().trim());
        valores.put(TablaEmpleados.puesto, txtPuesto.getText().toString().trim());

        Long resultado = db.insert(TablaEmpleados.tablaEmpleados,TablaEmpleados.id, valores);
        Toast.makeText(getApplicationContext(),"Registro Ingresado con el Código: " + resultado.toString(),Toast.LENGTH_LONG).show();

        db.close();
        limpiar();
    }

    void limpiar() {
        txtNombre.setText("");
        txtApellido.setText("");
        txtEdad.setText("");
        txtDireccion.setText("");
        txtPuesto.setText("");
    }
}