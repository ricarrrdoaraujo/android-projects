package com.example.ricardoaraujo.cardviewaula.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.ricardoaraujo.cardviewaula.R;
import com.example.ricardoaraujo.cardviewaula.adapter.PostagemAdapter;
import com.example.ricardoaraujo.cardviewaula.model.Postagem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerPostagem;
    private List<Postagem> postagens = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerPostagem = findViewById(R.id.recyclerPostagem);

        //Define layout
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerPostagem.setLayoutManager(layoutManager);

        //Define adapter
        this.prepararPostagem();
        PostagemAdapter adapter = new PostagemAdapter(postagens);
        recyclerPostagem.setAdapter(adapter);

    }

    public void prepararPostagem(){

        Postagem p = new Postagem("Ricardo araujo", "Viagem legal!!!", R.drawable.imagem1);
        this.postagens.add(p);

        p = new Postagem("Hotel X", "Enjoy!!!", R.drawable.imagem2);
        this.postagens.add(p);

        p = new Postagem("Mariazinha", "#Paris!!!", R.drawable.imagem3);
        this.postagens.add(p);

        p = new Postagem("Marco Polo", "Beautiful pic!!!", R.drawable.imagem4);
        this.postagens.add(p);

    }

}
