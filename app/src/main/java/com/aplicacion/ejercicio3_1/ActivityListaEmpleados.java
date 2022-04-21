package com.aplicacion.ejercicio3_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.aplicacion.ejercicio3_1.Clases.Empleados;
import com.aplicacion.ejercicio3_1.Clases.SQLiteConexion;
import com.aplicacion.ejercicio3_1.Clases.TablaEmpleados;

import java.io.Serializable;
import java.util.ArrayList;

public class ActivityListaEmpleados extends AppCompatActivity {

    SQLiteConexion conexion;
    ListView listaEmpleados;
    ArrayList<Empleados> lista;
    ArrayList<String> ArregloEmple;
    Button btnRegresar1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_empleados);

        conexion = new SQLiteConexion(this, TablaEmpleados.NameDatabase, null, 1);
        listaEmpleados = (ListView) findViewById(R.id.listaEmpleados);
        btnRegresar1 = (Button) findViewById(R.id.btnAtras1);

        obtenerEmpleados();

        ArrayAdapter adp = new ArrayAdapter(this, android.R.layout.simple_list_item_1, ArregloEmple);
        listaEmpleados.setAdapter(adp);

        //Selecciona el empleado dentro de la lista
        listaEmpleados.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Empleados emple = lista.get(i);

                Intent intent = new Intent(ActivityListaEmpleados.this, ActivityEmpleado.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("empleado", emple);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
            }
        });

        btnRegresar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent atr = new Intent(ActivityListaEmpleados.this, MainActivity.class);
                startActivity(atr);
                finish();
            }
        });
    }

    private void obtenerEmpleados() {
        SQLiteDatabase db = conexion.getReadableDatabase();
        Empleados list_perso = null;
        lista = new ArrayList<Empleados>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TablaEmpleados.tablaEmpleados, null);

        while (cursor.moveToNext()){
            list_perso = new Empleados();
            list_perso.setId(cursor.getInt(0));
            list_perso.setNombres(cursor.getString(1));
            list_perso.setApellidos(cursor.getString(2));
            list_perso.setEdad(cursor.getInt(3));
            list_perso.setDireccion(cursor.getString(4));
            list_perso.setPuesto(cursor.getString(5));

            lista.add(list_perso);
        }
        cursor.close();

        llenalista();
    }

    private void llenalista() {
        ArregloEmple = new ArrayList<String>();
        for (int i=0; i<lista.size(); i++){
            ArregloEmple.add(lista.get(i).getId() +") "+
                    lista.get(i).getNombres() +" | "+
                    lista.get(i).getApellidos() +" | "+
                    lista.get(i).getEdad() + " | "+
                    lista.get(i).getDireccion() + " | "+
                    lista.get(i).getPuesto());
        }
    }
}