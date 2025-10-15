package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistroActivity extends AppCompatActivity {

    EditText txtCorreoRegistro, txtContrasenaRegistro;
    Button btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        txtCorreoRegistro = findViewById(R.id.txtCorreoRegistro);
        txtContrasenaRegistro = findViewById(R.id.txtContrasenaRegistro);
        btnRegistrar = findViewById(R.id.btnRegistrar);

        btnRegistrar.setOnClickListener(v -> {
            String correo = txtCorreoRegistro.getText().toString().trim();
            String contrasena = txtContrasenaRegistro.getText().toString();

            if (!Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
                Toast.makeText(this, "Correo inválido", Toast.LENGTH_SHORT).show();
                return;
            }

            if (contrasena.length() < 6) {
                Toast.makeText(this, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show();
                return;
            }

            SharedPreferences preferencias = getSharedPreferences("sesion", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferencias.edit();
            editor.putString("correo", correo);
            editor.putString("contrasena", contrasena);
            editor.apply();

            Toast.makeText(this, "Usuario registrado correctamente", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}