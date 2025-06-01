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

        Button obliczBtn = findViewById(R.id.obliczButton);
        EditText poleA = findViewById(R.id.poleA); // a
        EditText poleB = findViewById(R.id.poleB);   // b
        EditText poleC = findViewById(R.id.poleC);    // c

        EditText wynik1 = findViewById(R.id.wynik1Edit);
        EditText wynik2 = findViewById(R.id.wynik2Edit);

        obliczBtn.setOnClickListener(v -> {
            wynik1.setText("");
            wynik2.setText("");

            double a = Double.parseDouble(poleA.getText().toString());
            double b = Double.parseDouble(poleB.getText().toString());
            double c = Double.parseDouble(poleC.getText().toString());

            double delta = b * b - 4 * a * c;

            if (delta < 0) {
                Toast.makeText(this, "Brak rozwiązań", Toast.LENGTH_SHORT).show();
            } else if (delta == 0) {
                double x = -b / (2 * a);
                wynik1.setText(String.valueOf(x));
                Toast.makeText(this, "Równanie ma jedno rozwiązanie", Toast.LENGTH_SHORT).show();
            } else {
                double sqrtDelta = Math.sqrt(delta);
                double x1 = (-b + sqrtDelta) / (2 * a);
                double x2 = (-b - sqrtDelta) / (2 * a);
                wynik1.setText(String.valueOf(x1));
                wynik2.setText(String.valueOf(x2));
            }
        });
