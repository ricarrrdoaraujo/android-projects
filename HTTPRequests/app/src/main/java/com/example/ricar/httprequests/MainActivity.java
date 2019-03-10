package com.example.ricar.httprequests;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ricar.httprequests.api.CEPService;
import com.example.ricar.httprequests.api.DataService;
import com.example.ricar.httprequests.model.CEP;
import com.example.ricar.httprequests.model.Foto;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Button botaoRecuperar;
    private TextView resultado;
    private Retrofit retrofit;
    private List<Foto> listaFotos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botaoRecuperar = findViewById(R.id.buttonRecuperar);
        resultado = findViewById(R.id.resultado);
        retrofit = new Retrofit.Builder()
                //.baseUrl("https://viacep.com.br/ws/")
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        botaoRecuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //recuperarCEPRetrofit();

                recuperarListaRetrofit();

               /* MyTask task = new MyTask();
                String urlApi = "https://blockchain.info/ticker";
                String cep = "13077009";
                String urlCep = "https://viacep.com.br/ws/" + cep + "/json/ ";
                task.execute(urlApi);*/
            }
        });
    }

    private void recuperarListaRetrofit(){

        DataService service = retrofit.create(DataService.class);
        Call<List<Foto>> call = service.recuperarFotos();

        call.enqueue(new Callback<List<Foto>>() {
            @Override
            public void onResponse(Call<List<Foto>> call, Response<List<Foto>> response) {
                if(response.isSuccessful()){
                    listaFotos = response.body();

                    for(int i=0; i < listaFotos.size(); i++){
                        Foto foto = listaFotos.get(i);
                        Log.d("resultado", "resultado: " + foto.getId() +
                        " / " + foto.getTitle());
                    }

                }
            }

            @Override
            public void onFailure(Call<List<Foto>> call, Throwable t) {

            }
        });

    }

    private void recuperarCEPRetrofit(){
        //Retorna um objeto Call
        CEPService cepService = retrofit.create(CEPService.class);
        Call<CEP> call = cepService.recuperarCEP("13077009");

        //cria uma tarefa assíncrona dentro de uma thread para fazer o download das informações
        call.enqueue(new Callback<CEP>() {
            @Override
            public void onResponse(Call<CEP> call, Response<CEP> response) {
                if(response.isSuccessful()){
                    CEP cep = response.body();
                    resultado.setText(cep.getLogradouro() + " / " + cep.getBairro());
                }
            }

            @Override
            public void onFailure(Call<CEP> call, Throwable t) {

            }
        });
    }

    class MyTask extends AsyncTask<String, Void, String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {

            String stringUrl = strings[0];
            InputStream inputStream = null;
            InputStreamReader inputStreamReader = null;
            StringBuffer buffer = null;

            try {

                URL url = new URL(stringUrl);
                HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
                // recupera dados em bytes
                inputStream = conexao.getInputStream();
                // lê em bytes e decodifica para caracteres
                inputStreamReader = new InputStreamReader(inputStream);
                // permite fazer a leitura dos caracteres
                BufferedReader reader = new BufferedReader(inputStreamReader);
                buffer = new StringBuffer();
                String linha = "";

                while((linha = reader.readLine()) != null){
                    buffer.append(linha);
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return buffer.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            /*String logradouro = null;
            String cep = null;
            String complemento = null;
            String bairro = null;
            String localidade = null;
            String uf = null;*/

            String objetoValor = null;
            String valorMoeda = null;
            String simbolo = null;

            try {
                /*JSONObject jsonObject = new JSONObject(s);*/
                /*logradouro = jsonObject.getString("logradouro");
                cep = jsonObject.getString("cep");
                complemento = jsonObject.getString("complemento");
                bairro = jsonObject.getString("bairro");
                localidade = jsonObject.getString("localidade");
                uf = jsonObject.getString("uf");*/

                JSONObject jsonObject = new JSONObject(s);
                objetoValor = jsonObject.getString("BRL");

                JSONObject jsonObject1Real = new JSONObject(objetoValor);
                valorMoeda = jsonObject1Real.getString("last");
                simbolo = jsonObject1Real.getString("symbol");

            } catch (JSONException e) {
                e.printStackTrace();
            }

            /*resultado.setText(logradouro + " / " + cep+ " / " + complemento+
                    " / " + bairro + " / " + localidade + " / " + uf);*/
            resultado.setText(simbolo + " " + valorMoeda);
        }
    }
}
