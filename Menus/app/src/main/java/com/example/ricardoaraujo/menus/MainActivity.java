package com.example.ricardoaraujo.menus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /*
        * getMenuInflater retorna o objeto que nos permite inflar os menus.
        * Inflar é converter XML em objetos do tipo View
        * */

        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.itemSalvar :
                Toast.makeText(
                        MainActivity.this,
                        "Item Salvar",
                        Toast.LENGTH_SHORT).show();
                break;
            case R.id.itemEditar :
                Toast.makeText(
                        MainActivity.this,
                        "Item Salvar",
                        Toast.LENGTH_SHORT).show();
                break;

            case R.id.itemConfiguracoes :
                Toast.makeText(
                        MainActivity.this,
                        "Item Comfiguracao",
                        Toast.LENGTH_SHORT).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
