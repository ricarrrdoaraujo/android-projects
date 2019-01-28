package com.example.ricar.slider;

//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.heinrichreimersoftware.materialintro.app.IntroActivity;
import com.heinrichreimersoftware.materialintro.slide.FragmentSlide;
import com.heinrichreimersoftware.materialintro.slide.SimpleSlide;

public class MainActivity extends IntroActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        setButtonBackVisible(false);
        setButtonNextVisible(false);

        addSlide(new FragmentSlide.Builder()
                .background(R.color.colorAccent)
                .fragment(R.layout.intro_1)
                .build()
        );
        addSlide(new FragmentSlide.Builder()
                .background(R.color.colorAccent)
                .fragment(R.layout.intro_2)
                .build()
        );

        /*


        addSlide(new SimpleSlide.Builder()
                                .title("Título")
                                .description("Descrição")
                                .image(R.drawable.um)
                                .background(R.color.colorAccent)
                                .build()
        );

        addSlide(new SimpleSlide.Builder()
                .title("Título 2")
                .description("Descrição 2")
                .image(R.drawable.dois)
                .background(R.color.colorAccent)
                .build()
        );

        addSlide(new SimpleSlide.Builder()
                .title("Título 3")
                .description("Descrição 3")
                .image(R.drawable.tres)
                .background(R.color.colorAccent)
                .build()
        );*/
    }
}
