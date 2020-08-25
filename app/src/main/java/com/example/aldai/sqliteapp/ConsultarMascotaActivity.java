package com.example.aldai.sqliteapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aldai.sqliteapp.utilidades.Utilidades;

public class ConsultarMascotaActivity extends AppCompatActivity {

    EditText campoId,campoNombre,campoRaza;

    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_mascotas);

        conn=new ConexionSQLiteHelper(getApplicationContext(),"bd_usuarios",null,1);

        campoId= (EditText) findViewById(R.id.documentoId);
        campoNombre= (EditText) findViewById(R.id.campoNombreConsulta);
        campoRaza= (EditText) findViewById(R.id.campoRaza);


    }

    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btnConsultar: consultar();
                break;
            case R.id.btnActualizar: actualizarMascota();
                break;
            case R.id.btnEliminar: eliminarMacota();
                break;
        }

    }

    private void eliminarMacota() {
        SQLiteDatabase db=conn.getWritableDatabase();
        String[] parametros={campoId.getText().toString()};

        db.delete(Utilidades.TABLA_MASCOTA,Utilidades.CAMPO_ID_MASCOTA+"=?",parametros);
        Toast.makeText(getApplicationContext(),"Ya se Eliminó la mascota",Toast.LENGTH_LONG).show();
        campoId.setText("");
        limpiar();
        db.close();
    }

    private void actualizarMascota() {
        SQLiteDatabase db=conn.getWritableDatabase();
        String[] parametros={campoId.getText().toString()};
        ContentValues values=new ContentValues();
        values.put(Utilidades.CAMPO_NOMBRE_MASCOTA,campoNombre.getText().toString());
        values.put(Utilidades.CAMPO_RAZA_MASCOTA,campoRaza.getText().toString());

        db.update(Utilidades.TABLA_MASCOTA,values,Utilidades.CAMPO_ID_MASCOTA+"=?",parametros);
        Toast.makeText(getApplicationContext(),"Ya se actualizó la mascota",Toast.LENGTH_LONG).show();
        db.close();

    }

   /*private void consultarSql() {
        SQLiteDatabase db=conn.getReadableDatabase();
        String[] parametros={campoId.getText().toString()};

        try {
            //select nombre,telefono from usuario where codigo=?
            Cursor cursor=db.rawQuery("SELECT "+Utilidades.CAMPO_NOMBRE_MASCOTA+","+Utilidades.CAMPO_RAZA_MASCOTA+
                    " FROM "+Utilidades.TABLA_MASCOTA+" WHERE "+Utilidades.CAMPO_ID_MASCOTA+"=? ",parametros);

            cursor.moveToFirst();
            campoNombre.setText(cursor.getString(0));
            campoRaza.setText(cursor.getString(1));
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"El documento no existe",Toast.LENGTH_LONG).show();
            limpiar();
        }

    }*/

    private void consultar() {
        SQLiteDatabase db=conn.getReadableDatabase();
        String[] parametros={campoId.getText().toString()};
        String[] campos={Utilidades.CAMPO_NOMBRE_MASCOTA,Utilidades.CAMPO_RAZA_MASCOTA};

        try {
            Cursor cursor =db.query(Utilidades.TABLA_MASCOTA,campos,Utilidades.CAMPO_ID_MASCOTA+"=?",parametros,null,null,null);
            cursor.moveToFirst();
            campoNombre.setText(cursor.getString(0));
            campoRaza.setText(cursor.getString(1));
            cursor.close();
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"El documento no existe",Toast.LENGTH_LONG).show();
            limpiar();
        }


    }

    private void limpiar() {
        campoNombre.setText("");
        campoRaza.setText("");
    }
}