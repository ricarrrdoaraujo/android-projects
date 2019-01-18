package com.example.ricardoaraujo.passandodadosactivities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SegundaActivity extends AppCompatActivity {

    private TextView textNome, textIdade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda);

        textNome.findViewById(R.id.textNome);
        textIdade.findViewById(R.id.textIdade);

        //Recuperar os dados enviados
        Bundle dados = getIntent().getExtras();
        String nome = dados.getString("name");
        int idade = dados.getInt("idade");

        //configurar os valores recuperados
        textNome.setText(nome);
        textIdade.setText(String.valueOf(idade)); // String.valueof() converte pra string

    }
}
