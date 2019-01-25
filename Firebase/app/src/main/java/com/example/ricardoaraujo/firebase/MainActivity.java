package com.example.ricardoaraujo.firebase;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference referencia = FirebaseDatabase.getInstance().getReference();
    //getReference define o atributo referencia como a raiz do projeto do firebase
    //getInstance() recupera o objeto que nos permite manipular os usuarios
    private FirebaseAuth usuario = FirebaseAuth.getInstance();
    //getInstance() recupera o objeto que nos permite manipular o banco de dados


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Cadastrar usuario
        //usuario.createUserWithEmailAndPassword("ricardo@gmail.com", "123mudar")
         //       .addOnCanceledListener(MainActivity.this, new OnCompleteListener<AuthResult>());


        /* Salvar dados no Firebase
        DatabaseReference produtos = referencia.child("produtos");
        DatabaseReference usuarios = referencia.child("usuarios");

        usuarios.addValueEventListener(new ValueEventListener() {
            //DataSnapshot é o tipo de dado que retorna do firebase
            //o listener está apenas escutando apenas o usuario 001
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //dataSnapshot.getValue() retorna os valores dessa referencias
                Log.i("FIREBASE", dataSnapshot.getValue().toString());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //referencia.child("usuarios").child("001").child("nome").setValue("Ricardo");
        //child permite que crie um filho para a referencia
        //DatabaseReference usuarios = referencia.child("usuarios");

        DatabaseReference produtos = referencia.child("produtos");

        Usuario usuario = new Usuario();
        usuario.setNome("Ricardo");
        usuario.setSobrenome("Araujo");
        usuario.setIdade(29);
        //passando objeto usuario para o set value
        usuarios.child("001").setValue(usuario);

        Produto produto = new Produto();
        produto.setDescricao("Alienware");
        produto.setMarca("Dell");
        produto.setPreco(5000.00);

        produtos.child("002").setValue(produto);*/
    }
}
