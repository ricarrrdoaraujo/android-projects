package com.example.ricar.abas.activity;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ricar.abas.R;
import com.example.ricar.abas.activity.fragment.EmAltaFragment;
import com.example.ricar.abas.activity.fragment.HomeFragment;
import com.example.ricar.abas.activity.fragment.InscricoesFragment;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private SmartTabLayout smartTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewPager);
        smartTabLayout = findViewById(R.id.viewPagerTab);

        //Aplica configurações na Action Bar
        getSupportActionBar().setElevation(0);
        getSupportActionBar().setTitle("YouTube");

        //configurar abas
        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                //gerenciador de fragmentos
                getSupportFragmentManager(),
                //configurações dos fragmentos que vamos precisar em cada uma das abas
                FragmentPagerItems.with(this)
                        .add("Home", HomeFragment.class)
                        .add("Incrições",InscricoesFragment.class)
                        .add("Em Alta",EmAltaFragment.class)
                        .create()
        );
        //o adaptador é passado para o viewPager
        //e o viewPager baseado no adaptador é carregado corretamente
        viewPager.setAdapter(adapter);
        // utilizando o smartTabLayout para configurar o viewpager que já
        //está com o adapter configurado
        smartTabLayout.setViewPager(viewPager);
    }
}
