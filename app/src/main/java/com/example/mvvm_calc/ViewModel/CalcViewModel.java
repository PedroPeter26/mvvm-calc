package com.example.mvvm_calc.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvm_calc.Model.Calculator;

public class CalcViewModel extends ViewModel {

    private MutableLiveData<Double> resultado = new MutableLiveData<>();
    public LiveData<Double> getResultado() {
        return resultado;
    }

    public void realizarOperacion(double num1, double num2, String operacion) {

        switch (operacion) {
            case "Sumar":
                resultado.setValue(Calculator.sumar(num1, num2));
                break;
            case "Restar":
                resultado.setValue(Calculator.restar(num1, num2));
                break;
            case "Multiplicar":
                resultado.setValue(Calculator.multiplicar(num1, num2));
                break;
            case "Dividir":
                resultado.setValue(Calculator.dividir(num1, num2));
                break;
            default:
                throw new IllegalArgumentException("Operación no válida");
        }
    }
}
