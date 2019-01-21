package com.example.ricardoaraujo.bancodedadossqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try{
            //criar banco de dados
            SQLiteDatabase bancoDados = openOrCreateDatabase("app", MODE_PRIVATE, null);

            //criar tabela
            bancoDados.execSQL("CREATE TABLE IF NOT EXISTS pessoas ( nome VARCHAR, idade INT(3) ) ");

            //inserir dados
            //bancoDados.execSQL("INSERT INTO pessoas (nome, idade) VALUES ('Pedro', 40)");
            //bancoDados.execSQL("INSERT INTO pessoas (nome, idade) VALUES ('Mariana', 46)");

            /*String consulta = "SELECT nome, idade " +
                                "FROM pessoas WHERE nome = 'Ricardo' ";*/

            //Recuperar pessoas
            String consulta = "SELECT nome, idade " +
                                "FROM pessoas WHERE idade >= 30 OR idade = 18";
            Cursor cursor = bancoDados.rawQuery(consulta, null);

            //Indices da tabela
            int indiceNome = cursor.getColumnIndex("nome");
            int indiceIdade = cursor.getColumnIndex("idade");

            cursor.moveToFirst();
            while(cursor != null){

                String nome = cursor.getString(indiceNome);
                String idade = cursor.getString(indiceIdade);

                Log.i("RESULTADO - nome: ", nome + " / idade " + idade);

                cursor.moveToNext();
            }
            
        }
        catch(Exception e) {
            e.printStackTrace();
        }


    }
}
