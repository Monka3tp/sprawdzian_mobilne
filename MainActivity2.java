package com.example.sprawdzian;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // widoki
        Button   obliczBtn    = findViewById(R.id.obliczButton);
        EditText przychodEdit = findViewById(R.id.przychodEdit);
        EditText dochodEdit   = findViewById(R.id.dochodEdit);
        CheckBox kupCheckBox  = findViewById(R.id.kwotaBox);

        obliczBtn.setOnClickListener(v -> {

            /* 1. Walidacja danych wejściowych */
            String input = przychodEdit.getText()
                    .toString()
                    .replace(',', '.')
                    .trim();

            if (input.isEmpty()) {
                Toast.makeText(this, "Podaj przychód!", Toast.LENGTH_SHORT).show();
                return;
            }

            double przychod = Double.parseDouble(input);

            /* 2. Koszt uzyskania przychodu – z ifami */
            int kosztUzyskania;
            if (kupCheckBox.isChecked()) {
                kosztUzyskania = 3600;
            } else {
                kosztUzyskania = 3000;
            }

            /* 3. Podstawa opodatkowania */
            double podstawa = Math.max(0, przychod - kosztUzyskania);

            /* 4. Obliczenie podatku */
            double podatek;
            if      (podstawa <= 30_000) {
                podatek = 0;
            } else if (podstawa <= 120_000) {
                podatek = (podstawa - 30_000) * 0.12;
            } else {
                podatek = 90_000 * 0.12 + (podstawa - 120_000) * 0.32;
            }

            int podatekZl = (int) Math.round(podatek);

            /* 5. Toast z wynikiem */
            Toast.makeText(this, "Podatek: " + podatekZl + " zł", Toast.LENGTH_LONG).show();

            /* 6. Dochód = przychód – podatek */
            int dochod = (int) Math.round(przychod - podatekZl);
            dochodEdit.setText(String.valueOf(dochod));
        });
    }
}
