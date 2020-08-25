package com.example.aldai.sqliteapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Menu extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        ConexionSQLiteHelper conn=new ConexionSQLiteHelper(this,"bd_usuarios",null,1);
    }
    public void onClick(View view) {
        Intent miIntent=null;
        switch (view.getId()){
            case R.id.btnOpcionRegistro:
                miIntent=new Intent(Menu.this,RegistroUsuariosActivity.class);
                break;
            case R.id.btnRegistroMascota:
                miIntent=new Intent(Menu.this,RegistroMascotaActivity.class);
                break;
            case R.id.btnConsultaIndividual:
                miIntent=new Intent(Menu.this,ConsultarUsuariosActivity.class);
                break;
            case R.id.btnConsultarMascota:
                miIntent=new Intent(Menu.this,ConsultarMascotaActivity.class);
                break;
            case R.id.btnConsultaLista:
                miIntent=new Intent(Menu.this,ConsultarListaListViewActivity.class);
                break;
            case R.id.btnConsultaListaMascota:
                miIntent=new Intent(Menu.this,ListaMascotasActivity.class);
                break;
        }
        if (miIntent!=null){
            startActivity(miIntent);
        }

    }
}
