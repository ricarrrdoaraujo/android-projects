package com.example.ricar.aprendaingls.activity;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ricar.aprendaingls.R;
import com.example.ricar.aprendaingls.activity.fragment.BichosFragment;
import com.example.ricar.aprendaingls.activity.fragment.NumerosFragment;
import com.example.ricar.aprendaingls.activity.fragment.VogaisFragment;
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

        viewPager = findViewById(R.id.viewpager);
        smartTabLayout = findViewById(R.id.viewpagertab);

        getSupportActionBar().setElevation(0);
        getSupportActionBar().setTitle("Aprenda Inglês");

        //configurar abas
        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(),
                FragmentPagerItems.with(this)
                        .add("Bichos", BichosFragment.class)
                        .add("Números",NumerosFragment.class)
                        .add("Vogais",VogaisFragment.class)
                        .create()
        );
        //o adaptador com os fragments é passado para o viewPager
        //e o viewPager baseado no adaptador é carregado corretamente
        viewPager.setAdapter(adapter);
        // utilizando o smartTabLayout para configurar o viewpager que já
        //está com o adapter configurado
        smartTabLayout.setViewPager(viewPager);
    }
}
