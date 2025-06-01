package com.example.zadanieandroid;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    boolean[] aktywneKosci = {true, true, true};
    int[] obrazyKosci = {
            R.drawable.kosc1, R.drawable.kosc2, R.drawable.kosc3,
            R.drawable.kosc4, R.drawable.kosc5, R.drawable.kosc6
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView kosc1 = findViewById(R.id.d1);
        ImageView kosc2 = findViewById(R.id.d2);
        ImageView kosc3 = findViewById(R.id.d3);
        ImageView[] kosci = {kosc1, kosc2, kosc3};

        TextView wynik = findViewById(R.id.result);
        Button rzut = findViewById(R.id.rollButton);

        rzut.setOnClickListener(v -> {
            int suma = 0;

            for (int i = 0; i < 3; i++) {
                if (aktywneKosci[i]) {
                    int oczka = (int) (Math.random() * 6);
                    kosci[i].setImageResource(obrazyKosci[oczka]);
                    suma += (oczka + 1);
                }
            }
            wynik.setText(String.valueOf(suma));
        });

        for (int i = 0; i < 3; i++) {
            int indeks = i;
            kosci[i].setOnClickListener(v -> {
                aktywneKosci[indeks] = !aktywneKosci[indeks];
                kosci[indeks].setAlpha(aktywneKosci[indeks] ? 1.0f : 0.5f);
            });
        }
    }
}
