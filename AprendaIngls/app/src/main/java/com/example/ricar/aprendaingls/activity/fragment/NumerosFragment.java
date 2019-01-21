package com.example.ricar.aprendaingls.activity.fragment;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.ricar.aprendaingls.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NumerosFragment extends Fragment implements View.OnClickListener {

    private ImageButton buttonUm, buttonDois, buttonTres,
                        buttonQuatro, buttonCinco, buttonSeis;

    MediaPlayer mediaPlayer;

    public NumerosFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_numeros, container, false);

        buttonUm = view.findViewById(R.id.buttonUm);
        buttonDois = view.findViewById(R.id.buttonDois);
        buttonTres = view.findViewById(R.id.buttonTres);
        buttonQuatro = view.findViewById(R.id.buttonQuatro);
        buttonCinco = view.findViewById(R.id.buttonCinco);
        buttonSeis = view.findViewById(R.id.buttonSeis);

        //Aplica eventos de click
        buttonUm.setOnClickListener(this);
        buttonDois.setOnClickListener(this);
        buttonTres.setOnClickListener(this);
        buttonQuatro.setOnClickListener(this);
        buttonCinco.setOnClickListener(this);
        buttonSeis.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        Log.i("Elemento clicado " , "Item: " + v.getId());
        switch(v.getId()){
            case R.id.buttonUm :
                tocarSom(R.raw.one);
                break;
            case R.id.buttonDois :
                tocarSom(R.raw.two);
                break;
            case R.id.buttonTres :
                tocarSom(R.raw.three);
                break;
            case R.id.buttonQuatro :
                tocarSom(R.raw.four);
                break;
            case R.id.buttonCinco :
                tocarSom(R.raw.five);
                break;
            case R.id.buttonSeis :
                tocarSom(R.raw.six);
                break;
        }
    }

    public void tocarSom(int som){
        mediaPlayer = MediaPlayer.create(getActivity(), som);
        if (mediaPlayer != null){
            mediaPlayer.start();
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                //quando o som for finalizado executa o que está dentro do método onCompletion()
                //no caso, liberar os recursos
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mediaPlayer.release();
                }
            });
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null){
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
