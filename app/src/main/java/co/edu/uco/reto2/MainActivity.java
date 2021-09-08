package co.edu.uco.reto2;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private Button btnSelectDate;
    private TextView txtDate;
    private TextView mensaje;
    private EditText inputNombre;
    private EditText inputApellido;
    private Calendar calendar;
    private DatePickerDialog datePickerDialog;
    private Button btnCalculateAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @SuppressLint("WrongViewCast")
    public void initComponents(){
        btnSelectDate = findViewById(R.id.btnSelectDate);
        txtDate = findViewById(R.id.txtDate);
        btnCalculateAge=findViewById(R.id.btnCalculateAge);
        inputNombre = findViewById(R.id.inputNombre);
        inputApellido = findViewById(R.id.inputApellido);
        mensaje = findViewById(R.id.mensaje);

        btnCalculateAge.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                String name = inputNombre.getText().toString();
                String apellido = inputApellido.getText().toString();
                String fecha = txtDate.getText().toString();
                String message = mensaje.getText().toString();

                calendar = Calendar.getInstance();
                int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
                int currentMonth = calendar.get(Calendar.MONTH);
                int currentYear = calendar.get(Calendar.YEAR);

                if((txtDate.getText().toString().equals("")) || (inputNombre.getText().toString().equals("")) || (inputApellido.getText().toString().equals("")) ){
                    Toast.makeText(getApplicationContext(), R.string.textoToast, Toast.LENGTH_SHORT).show();
                }else{
                    String birthDate = txtDate.getText().toString();
                    String[] birthDateArray = birthDate.split("/");

                    int age = currentYear - Integer.parseInt(birthDateArray[2]);


                    if(Integer.parseInt(birthDateArray[1]) < currentMonth){
                        age--;
                    }
                    if(Integer.parseInt(birthDateArray[0]) < currentDay){
                        age++;
                    }
                    if(age >= 18){
                        message = "Usted es mayor de edad. Su edad es: " + age + " años";
                        Intent intent = new Intent(MainActivity.this, Mostrar.class);
                        intent.putExtra("name",name);
                        intent.putExtra("apellido",apellido);
                        intent.putExtra("fecha",fecha);
                        intent.putExtra("mensaje", message);
                        startActivity(intent);
                    }
                    else{
                        message = "Su edad es : " + age + " años. No puede continuar porque es menor de edad";
                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
    }


    public void selectDate(View view) {
        btnSelectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();
                int birthDay = calendar.get(Calendar.DAY_OF_MONTH);
                int birthMonth = calendar.get(Calendar.MONTH);
                int birthYear = calendar.get(Calendar.YEAR);

                datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        txtDate.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    }
                }, birthYear, birthMonth, birthDay);
                datePickerDialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());
                datePickerDialog.show();
            }
        });
    }

    /*public void calculateAge(View view) {

        btnCalculateAge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();
                int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
                int currentMonth = calendar.get(Calendar.MONTH);
                int currentYear = calendar.get(Calendar.YEAR);

                if(txtDate.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), R.string.textoToast, Toast.LENGTH_SHORT).show();
                }else{
                    String birthDate = txtDate.getText().toString();
                    String[] birthDateArray = birthDate.split("-");

                    int age = currentYear - Integer.parseInt(birthDateArray[2]);
                    String message = "";

                    if(Integer.parseInt(birthDateArray[1]) < currentMonth){
                        age--;
                    }
                    if(Integer.parseInt(birthDateArray[0]) < currentDay){
                        age++;
                    }
                    if(age >= 18){
                        message = "Usted es mayor de edad";
                    }
                    else{
                        message = "Usted es menor de edad";
                    }

                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                }

            }
        });
    }*/
}




