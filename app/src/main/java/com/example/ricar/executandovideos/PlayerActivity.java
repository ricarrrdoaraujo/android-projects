package com.example.ricar.executandovideos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

public class PlayerActivity extends AppCompatActivity {

    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        videoView = findViewById(R.id.videoView);

        //Esconder a statusBar e a barra de navegação
        //getWindow recupera um objeto de tela
        //getDecorView recupera um objeto que nos permite manipular um objeto de tela
        View decorView = getWindow().getDecorView();

        // configura as flags para manipular componentes da tela
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;

        decorView.setSystemUiVisibility(uiOptions);

        //Esconder ActionBar
        getSupportActionBar().hide();

        //Executar o vídeo
        //setMediaController() permite que vc defina quais vão ser os
        //controladores do vídeo
        videoView.setMediaController(new MediaController(this));

        //android.resource:// define o local onde ficam os recursos do Android
        //getPackageName() recupera o nome do pacote
        //R.raw.video define que está dentro de raw
        videoView.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.video);
        videoView.start();
    }
}
