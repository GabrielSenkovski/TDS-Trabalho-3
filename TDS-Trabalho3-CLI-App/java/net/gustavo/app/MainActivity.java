package net.gustavo.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText entrada = findViewById(R.id.entrada);
        Button botao = findViewById(R.id.botao);
        TextView saida = findViewById(R.id.saida);

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = entrada.getText().toString().trim();
                if (!name.isEmpty()) {
                    saida.setText("Hello, " + name);
                } else {
                    saida.setText("Hello, user!");
                }
            }
        });
    }
}
