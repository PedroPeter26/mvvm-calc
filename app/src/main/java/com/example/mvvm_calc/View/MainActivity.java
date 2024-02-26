package com.example.mvvm_calc.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.mvvm_calc.R;
import com.example.mvvm_calc.ViewModel.CalcViewModel;

public class MainActivity extends AppCompatActivity {

    private EditText editTextNum1;
    private EditText editTextNum2;
    private EditText editTextResultado;
    private Spinner spinnerOperacion;
    private Button buttonCalcular;
    private CalcViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextNum1 = findViewById(R.id.editTextNum1);
        editTextNum2 = findViewById(R.id.editTextNum2);
        editTextResultado = findViewById(R.id.editTextResultado);
        spinnerOperacion = findViewById(R.id.spinnerOperacion);
        buttonCalcular = findViewById(R.id.buttonCalcular);

        viewModel = new ViewModelProvider(this).get(CalcViewModel.class);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.operaciones_array,
                android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnerOperacion.setAdapter(adapter);

        buttonCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcularResultado();
            }
        });

        viewModel.getResultado().observe(this, new Observer<Double>() {
            @Override
            public void onChanged(Double resultado) {
                editTextResultado.setText(String.valueOf(resultado));
            }
        });
    }

    private void calcularResultado() {
        double num1 = Double.parseDouble(editTextNum1.getText().toString());
        double num2 = Double.parseDouble(editTextNum2.getText().toString());
        String operacion = spinnerOperacion.getSelectedItem().toString();
        viewModel.realizarOperacion(num1, num2, operacion);
    }
}