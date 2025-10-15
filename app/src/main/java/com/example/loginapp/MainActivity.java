package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText txtCorreo, txtContrasena;
    Button btnIniciarSesion, btnIrRegistro;

    SharedPreferences preferencias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferencias = getSharedPreferences("sesion", MODE_PRIVATE);

        if (preferencias.getBoolean("sesion_activa", false)) {
            startActivity(new Intent(MainActivity.this, DashboardActivity.class));
            finish();
            return;
        }

        setContentView(R.layout.activity_main);

        txtCorreo = findViewById(R.id.txtCorreo);
        txtContrasena = findViewById(R.id.txtContrasena);
        btnIniciarSesion = findViewById(R.id.btnIniciarSesion);
        btnIrRegistro = findViewById(R.id.btnIrRegistro);

        btnIniciarSesion.setOnClickListener(v -> {
            String correo = txtCorreo.getText().toString().trim();
            String contrasena = txtContrasena.getText().toString();

            String correoGuardado = preferencias.getString("correo", "");
            String contrasenaGuardada = preferencias.getString("contrasena", "");

            if (correo.equals(correoGuardado) && contrasena.equals(contrasenaGuardada)) {
                preferencias.edit().putBoolean("sesion_activa", true).apply();
                Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, DashboardActivity.class));
                finish();
            } else {
                Toast.makeText(this, "Datos incorrectos", Toast.LENGTH_SHORT).show();
            }
        });

        btnIrRegistro.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, RegistroActivity.class));
        });
    }
}