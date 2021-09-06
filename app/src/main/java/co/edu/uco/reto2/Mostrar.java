package co.edu.uco.reto2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Mostrar extends AppCompatActivity {
    TextView tvnombre, tvapellido, tvfecha, mensajeValidacion;
    Button tvbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar);
        tvnombre = findViewById(R.id.tvnombre);
        tvapellido = findViewById(R.id.tvapellido);
        tvfecha = findViewById(R.id.tvfecha);
        mensajeValidacion = findViewById(R.id.mensajeValidacion);

        String nombre = getIntent().getStringExtra("name");
        String apellido = getIntent().getStringExtra("apellido");
        String fecha = getIntent().getStringExtra("fecha");
        String mensaje = getIntent().getStringExtra("mensaje");

        tvnombre.setText(nombre);
        tvapellido.setText(apellido);
        tvfecha.setText(fecha);
        mensajeValidacion.setText(mensaje);

        tvbutton = (Button)findViewById(R.id.tvbutton);

        tvbutton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                Intent intent = new Intent(Mostrar.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

}