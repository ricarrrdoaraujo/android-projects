package com.example.ricar.organizze.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.ricar.organizze.R;
import com.example.ricar.organizze.adapter.AdapterMovimentacao;
import com.example.ricar.organizze.config.ConfiguracaoFirebase;
import com.example.ricar.organizze.helper.Base64Custom;
import com.example.ricar.organizze.model.Movimentacao;
import com.example.ricar.organizze.model.Usuario;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class PrincipalActivity extends AppCompatActivity {

    private MaterialCalendarView calendarView;
    private TextView textoSaudacao, textoSaldo;
    private Double despesaTotal = 0.0;
    private Double receitaTotal = 0.0;
    private Double resumoUsuario = 0.0;
    private FirebaseAuth autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
    private DatabaseReference firebaseRef = ConfiguracaoFirebase.getFirebaseDatabase();
    private DatabaseReference usuarioRef;
    private ValueEventListener valueEventListenerUsuario, valueEventListenerMovimentacoes;

    private AdapterMovimentacao adapterMovimentacao;
    private RecyclerView recyclerView;
    private List<Movimentacao> movimentacoes = new ArrayList<>();
    private DatabaseReference movimentacaoRef;
    private String mesAnoSelecionado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Organizze");
        setSupportActionBar(toolbar);

        textoSaldo = findViewById(R.id.textSaldo);
        textoSaudacao = findViewById(R.id.textSaudacao);
        calendarView = findViewById(R.id.calendarView);
        recyclerView = findViewById(R.id.recyclerMovimentos);
        configurarCalendarView();

        //configurar adapter
        adapterMovimentacao = new AdapterMovimentacao(movimentacoes,this);

        //configurar recycler view
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapterMovimentacao);

    }

    public void recuperarMovimentacao(){
        String idUsuario = Base64Custom.codificarBase64(autenticacao.getCurrentUser().getEmail());
        usuarioRef = firebaseRef.child("usuarios").child(idUsuario);

        movimentacaoRef = firebaseRef.child("movimentacao")
                                     .child(idUsuario)
                                     .child(mesAnoSelecionado);

        valueEventListenerMovimentacoes = movimentacaoRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //limpando as movimentacoes
                movimentacoes.clear();
                //getChildren() pega o dataSnapshot e percorre o primeiro filho dele
                for (DataSnapshot dados: dataSnapshot.getChildren()) {

                    Movimentacao movimentacao = dados.getValue( Movimentacao.class );
                    //Log.i("dadosRet", "dados " + movimentacao.getCategoria());
                    movimentacoes.add(movimentacao);

                }
                // notificar o adapter que os dados foram mudados
                adapterMovimentacao.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

       // Log.i("MES", "mes: " + mesAnoSelecionado);
    }

    public void recuperarResumo(){

        String idUsuario = Base64Custom.codificarBase64(autenticacao.getCurrentUser().getEmail());
        usuarioRef = firebaseRef.child("usuarios").child(idUsuario);

        //atribui o ValueEventListener usuarioRef a variável valueEventListener para
        //remover quando o método onStop() executar

        valueEventListenerUsuario = usuarioRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                /*
                 Definindo Usuario.class como parametro do getValue
                 converte o retorno do firebase para um objeto do tipo Usuario.
                  */
                Usuario usuario = dataSnapshot.getValue(Usuario.class);

                despesaTotal = usuario.getDespesaTotal();
                receitaTotal = usuario.getReceitaTotal();
                resumoUsuario = receitaTotal - despesaTotal;

                //definir o padrão de formatacao numérica
                //https://docs.oracle.com/javase/tutorial/i18n/format/decimalFormat.html
                DecimalFormat decimalFormat = new DecimalFormat("0.##");
                String resultadoFormatado = decimalFormat.format(resumoUsuario);

                textoSaudacao.setText("Olá, " + usuario.getNome());
                textoSaldo.setText("R$ " +  resultadoFormatado);
                Log.i("saldo:", "dados" + resultadoFormatado);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    //CRIAR MENU
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.menuSair :
                //DESLOGAR USUÁRIO
                autenticacao.signOut();
                //ENVIAR USUÁRIO PARA ACTIVITY PRINCIPAL
                startActivity(new Intent(this, MainActivity.class));
                //finalizar ActivityPrincipal
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void adicionarDespesa(View view){
        startActivity(new Intent(this, DespesasActivity.class));
    }

    public void adicionarReceita(View view){
        startActivity(new Intent(this, ReceitasActivity.class));
    }

    public void configurarCalendarView(){
        CharSequence meses[] = {"Janeiro","Fevereiro","Março","Abril","Maio", "Junho","Julho","Agosto", "Setembro","Outubro","Novembro","Dezembro"};
        calendarView.setTitleMonths(meses);

        CalendarDay dataAtual = calendarView.getCurrentDate();
        // "%02d" formata com 2 zeros
        String mesSelecionado = String.format("%02d", (dataAtual.getMonth() + 1));
        mesAnoSelecionado = String.valueOf(mesSelecionado + "" + dataAtual.getYear());

        calendarView.setOnMonthChangedListener(new OnMonthChangedListener() {
            @Override
            public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {
                String mesSelecionado = String.format("%02d", (date.getMonth() + 1));
                mesAnoSelecionado = String.valueOf(mesSelecionado + "" + date.getYear());

                movimentacaoRef.removeEventListener(valueEventListenerMovimentacoes);
                recuperarMovimentacao();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        recuperarResumo();
        recuperarMovimentacao();
        //Log.i("Evento", "evento foi iniciado");
    }

    @Override
    protected void onStop() {
        super.onStop();
        //Log.i("Evento", "evento foi removido");
        // remove o evento quando o app não estiver executando
        usuarioRef.removeEventListener(valueEventListenerUsuario);
        movimentacaoRef.removeEventListener(valueEventListenerMovimentacoes);
    }
}
