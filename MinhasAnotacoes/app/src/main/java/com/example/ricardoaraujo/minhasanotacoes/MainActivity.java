package com.example.ricardoaraujo.minhasanotacoes;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private AnotacaoPreferencias preferencias;
    private EditText editAnotacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editAnotacao = findViewById(R.id.editAnotacao);

        preferencias = new AnotacaoPreferencias(getApplicationContext());

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Validar se foi digitado algo
                String textRecuperado = editAnotacao.getText().toString();
                if(textRecuperado.equals("")){
                    Snackbar.make(view,"Preencha a anotação", Snackbar.LENGTH_LONG)
                            .show();
                } else {
                    preferencias.salvarAnotacao(textRecuperado);
                    Snackbar.make(view,"Anotação salva", Snackbar.LENGTH_LONG)
                            .show();
                }

            }
        });
        //Recuperar anotacao
        String anotacao = preferencias.recuperarAnotacao();

        if( !anotacao.equals("") ){
            editAnotacao.setText(anotacao);
        }

    }


}
