package com.example.cajeroautomatico;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

// GRUPO 4

public class MainActivity extends AppCompatActivity {
// declaran los objetos 

    private EditText etConsig;
    private EditText etRet;
    private TextView tvSaldo;
    private int saldo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etConsig = (EditText) findViewById(R.id.etConsig);
        etRet = (EditText) findViewById(R.id.etRet);
        tvSaldo = (TextView) findViewById(R.id.tvSaldo);

        SharedPreferences pref = getSharedPreferences("Saldos", Context.MODE_PRIVATE);

        String sald = String.valueOf(pref.getInt("Valor", 0));
        tvSaldo.setText(sald);

    }
    // se declara variable, se convierte la variable y se le asigna el saldo, mas lo que entro por consignacion  
    // y luego que imprima
    // guarda en el sharedpreferences
    // se reemplaza puntos por saldo para que guarde  en la variables el saldo

    public void Consignar(View view) {
        int Consig = Integer.parseInt(etConsig.getText().toString());
        saldo=saldo+Consig;
        tvSaldo.setText(String.valueOf(saldo));

            SharedPreferences pref = getSharedPreferences("Saldos", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = pref.edit();
            editor.putInt("Valor", saldo);
            editor.commit();

         if (saldo ==0) {
             Toast.makeText(this, "Por favor ingrese un valor ", Toast.LENGTH_LONG).show();
         }

    }
    public void Retirar(View view){
// se pone un condicional para que le reste al saldo actual y se imprima lo que quedo de saldos

            int retirar = Integer.parseInt(etRet.getText().toString());
            if (saldo >= retirar) {
                saldo = saldo - retirar;
                tvSaldo.setText(String.valueOf(saldo));


                SharedPreferences pref = getSharedPreferences("Saldos", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putInt("Valor", saldo);
                editor.commit();

            } else {
                Toast.makeText(this, "Saldo insuficiente para retirar", Toast.LENGTH_LONG).show();
            }


        }
    }
