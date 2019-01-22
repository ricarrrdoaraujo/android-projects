package com.example.ricardoaraujo.listadetarefas.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ricardoaraujo.listadetarefas.R;
import com.example.ricardoaraujo.listadetarefas.model.Tarefa;

import java.util.List;

/*
*
*
* */

public class TarefaAdapter extends RecyclerView.Adapter<TarefaAdapter.MyViewHolder> {


    //O adapter precisa receber uma lista de tarefas para exibição
    private List<Tarefa> listaTarefas;

    public TarefaAdapter(List<Tarefa> lista) {
        this.listaTarefas = lista;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View itemLista = LayoutInflater.from(viewGroup.getContext())
                                        .inflate(R.layout.lista_tarefa_adapter, viewGroup, false);

        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        // i = posição do item
        Tarefa tarefa = listaTarefas.get(i);
        myViewHolder.tarefa.setText(tarefa.getNome());

    }

    @Override
    public int getItemCount() {
        //quantos itens serão exibidos na lista
        return this.listaTarefas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tarefa;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tarefa = itemView.findViewById(R.id.textTarefa);
        }
    }


}
