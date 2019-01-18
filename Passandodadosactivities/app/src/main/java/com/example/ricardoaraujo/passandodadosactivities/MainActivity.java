package com.example.ricardoaraujo.passandodadosactivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button buttonEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonEnviar = findViewById(R.id.enviarDados);

        buttonEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //indica que quer abrir a classe SegundaActivity
                Intent intent = new Intent(getApplicationContext(), SegundaActivity.class);

                // passar dados
                intent.putExtra("nome","Ricardo");
                intent.putExtra("idade",29);

                startActivity( intent );

            }
        });

    }
}
