package com.example.ricardoaraujo.calendario;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CalendarView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;

public class MainActivity extends AppCompatActivity {

    private MaterialCalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calendarView = findViewById(R.id.calendarView);

        /*
        //limitar data
        calendarView.state().edit()
                .setMinimumDate(CalendarDay.from(2015,1,1))
                .setMaximumDate(CalendarDay.from(2018,3,1))
                .commit();
         */

        //configurar array com nomes dos meses
        CharSequence meses[] = {"Janeiro", "Fereveiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
        calendarView.setTitleMonths(meses);

        //configurar semana
        CharSequence semana[] = {"Domingo","Segunda", "Terça", "Quarta", "Quinta", "Sexta", "Sábado"};
        calendarView.setWeekDayLabels(semana);

        calendarView.setOnMonthChangedListener(new OnMonthChangedListener() {
            @Override
            public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {
                Log.i("data", "valor: " + (date.getMonth() + 1) + "/" + date.getYear());
            }
        });

        /*calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                Log.i("data", "valor: " + date);
            }
        });*/

    }
}
