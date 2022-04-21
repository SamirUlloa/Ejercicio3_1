package com.aplicacion.ejercicio3_1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aplicacion.ejercicio3_1.Clases.Empleados;
import com.aplicacion.ejercicio3_1.Clases.SQLiteConexion;
import com.aplicacion.ejercicio3_1.Clases.TablaEmpleados;

public class ActivityEmpleado extends AppCompatActivity {

    EditText txtEmpleadoId, txtEmpleadoNombre, txtEmpleadoApellido, txtEmpleadoEdad, txtEmpleadoDireccion, txtEmpleadoPuesto;
    Button btnActualizar, btnEliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empleado);

        txtEmpleadoId = (EditText) findViewById(R.id.txtEmpledoId);
        txtEmpleadoNombre = (EditText) findViewById(R.id.txtEmpledoNombre);
        txtEmpleadoApellido = (EditText) findViewById(R.id.txtEmpledoApellido);
        txtEmpleadoEdad = (EditText) findViewById(R.id.txtEmpledoEdad);
        txtEmpleadoDireccion = (EditText) findViewById(R.id.txtEmpledoDireccion);
        txtEmpleadoPuesto = (EditText) findViewById(R.id.txtEmpledoPuesto);
        btnActualizar = (Button) findViewById(R.id.btnActualizar);
        btnEliminar = (Button) findViewById(R.id.btnEliminar);

        Bundle obj = getIntent().getExtras();
        Empleados conta = null;

        if (obj != null) {
            conta = (Empleados) obj.getSerializable("empleado");

            String idV = conta.getId() + "";
            String edadV = conta.getEdad() + "";

            txtEmpleadoId.setText(idV);
            txtEmpleadoNombre.setText(conta.getNombres().toString());
            txtEmpleadoApellido.setText(conta.getApellidos().toString());
            txtEmpleadoEdad.setText(edadV);
            //txtEmpleadoEdad.setText(conta.getEdad());
            txtEmpleadoDireccion.setText(conta.getDireccion().toString());
            txtEmpleadoPuesto.setText(conta.getPuesto().toString());
        }

        //Boton para Actualizar la información de un Empleado
        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actualizarEmpleados();
            }
        });

        //Boton para Eliminar un Empleado
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eliminarEmpleados();
                //notifyDataSetChanged();
            }
        });
    }

    private void actualizarEmpleados() {
        SQLiteConexion conexion = new SQLiteConexion(this, TablaEmpleados.NameDatabase,null, 1);
        SQLiteDatabase db = conexion.getWritableDatabase();

        String cod = txtEmpleadoId.getText().toString().trim();

        ContentValues valores = new ContentValues();

        valores.put(TablaEmpleados.nombres, txtEmpleadoNombre.getText().toString());
        valores.put(TablaEmpleados.apellidos, txtEmpleadoApellido.getText().toString());
        valores.put(TablaEmpleados.edad, txtEmpleadoEdad.getText().toString());
        valores.put(TablaEmpleados.direccion, txtEmpleadoDireccion.getText().toString());
        valores.put(TablaEmpleados.puesto, txtEmpleadoPuesto.getText().toString());

        if (!txtEmpleadoId.getText().toString().isEmpty()){
            db.update("empleados", valores, "id=" + cod, null);
            Toast.makeText(this, "Se Actualizo el Registro: " + cod, Toast.LENGTH_LONG).show();
        }
    }

    private void eliminarEmpleados() {

        AlertDialog.Builder builder = new AlertDialog.Builder(ActivityEmpleado.this);
        builder.setMessage("Esta seguro que desea eliminar el Empleado:? " + txtEmpleadoNombre.getText().toString())
                .setTitle("Atención");

        builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                SQLiteConexion conexion = new SQLiteConexion(ActivityEmpleado.this, TablaEmpleados.NameDatabase,null, 1);
                SQLiteDatabase db = conexion.getWritableDatabase();

                String cod = txtEmpleadoId.getText().toString();

                db.delete("empleados", "id=" + cod, null);
                Toast.makeText(ActivityEmpleado.this, "Registro " + cod + " Eliminado Correctamente", Toast.LENGTH_LONG).show();

                db.close();

                Intent intent = new Intent(ActivityEmpleado.this, ActivityListaEmpleados.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();

            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    //Control del boton hacia atras.
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == event.KEYCODE_BACK){
            Intent atras = new Intent(ActivityEmpleado.this, ActivityListaEmpleados.class);
            startActivity(atras);
        }
        return super.onKeyDown(keyCode, event);
    }
}