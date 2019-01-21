package com.example.ricar.aprendaingls.activity.fragment;


import android.media.MediaPlayer;
import android.os.Bundle;
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
public class BichosFragment extends Fragment implements View.OnClickListener {

    private ImageButton buttonCao, buttonGato, buttonLeao,
                        buttonMacaco, buttonOvelha, buttonVaca;

    private MediaPlayer mediaPlayer;

    public BichosFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bichos, container, false);
        // dentro do fragment tem que chamar findViewById através do view
        buttonCao = view.findViewById(R.id.buttonCao);
        buttonGato = view.findViewById(R.id.buttonGato);
        buttonLeao = view.findViewById(R.id.buttonLeao);
        buttonMacaco = view.findViewById(R.id.buttonMacaco);
        buttonOvelha = view.findViewById(R.id.buttonOvelha);
        buttonVaca = view.findViewById(R.id.buttonVaca);

        buttonCao.setOnClickListener(this);
        buttonGato.setOnClickListener(this);
        buttonVaca.setOnClickListener(this);
        buttonOvelha.setOnClickListener(this);
        buttonMacaco.setOnClickListener(this);
        buttonLeao.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        Log.i("Elemento clicado " , "Item: " + view.getId());
        switch(view.getId()){
            case R.id.buttonCao :
                tocarSom(R.raw.dog);
                break;
            case R.id.buttonGato :
                tocarSom(R.raw.cat);
                break;
            case R.id.buttonLeao :
                tocarSom(R.raw.lion);
                break;
            case R.id.buttonMacaco :
                tocarSom(R.raw.monkey);
                break;
            case R.id.buttonOvelha :
                tocarSom(R.raw.sheep);
                break;
            case R.id.buttonVaca :
                tocarSom(R.raw.cow);
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
