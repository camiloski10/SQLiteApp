package com.example.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.agenda.db.DbContactos;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class NuevoActivity extends AppCompatActivity {
    EditText txtNombre, txtTelefono, txtEmail;
    Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo);

        txtNombre = findViewById(R.id.txtNombre);
        txtTelefono = findViewById(R.id.txtTelefono);
        txtEmail = findViewById(R.id.txtEmail);
        btnGuardar = findViewById(R.id.btnGuardar);

        BottomNavigationView navigationView = findViewById(R.id.menu_principal);
        navigationView.setOnNavigationItemSelectedListener(
                item -> {
                    if (item.getItemId() ==R.id.menuNuevo){
                        startActivity(new Intent(this, MainActivity.class));
                        return true;
                    }else {
                        return super.onOptionsItemSelected(item);
                    }
                }
        );

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbContactos dbContactos = new DbContactos(NuevoActivity.this);
                long id = dbContactos.insertarContacto(txtNombre.getText().toString(), txtTelefono.getText().toString(), txtEmail.getText().toString());

                if(id > 0){
                    Toast.makeText(NuevoActivity.this, "REGISTRO AGREGADO", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(NuevoActivity.this, "ERROR AL GUARDAR REGISTRO", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private void limpiar(){
        txtNombre.setText("");
        txtTelefono.setText("");
        txtEmail.setText("");
    }
}