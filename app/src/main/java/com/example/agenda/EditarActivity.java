package com.example.agenda;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.agenda.db.DbContactos;
import com.example.agenda.entidades.Contactos;

public class EditarActivity extends AppCompatActivity {

    EditText txtNombre, txtTelefono, txtCorreo;
    Button btnGuardar;
    Contactos contactos;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);

        txtNombre = findViewById(R.id.txtNombre);
        txtTelefono = findViewById(R.id.txtTelefono);
        txtCorreo = findViewById(R.id.txtEmail);
        btnGuardar = findViewById(R.id.btnGuardar);

        if (savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if (extras == null){
                id = Integer.parseInt(null);
            }else{
                id = extras.getInt("ID");
            }
        }else{
            id = (int) savedInstanceState.getSerializable("ID");
        }
        DbContactos dbContactos = new DbContactos(EditarActivity.this);
        contactos = dbContactos.verContacto(id);

        if(contactos!=null){
            txtNombre.setText(contactos.getNombre());
            txtTelefono.setText(contactos.getTelefono());
            txtCorreo.setText(contactos.getCorreo_electronico());
            btnGuardar.setVisibility(View.INVISIBLE);
            txtNombre.setInputType(InputType.TYPE_NULL);
            txtTelefono.setInputType(InputType.TYPE_NULL);
            txtCorreo.setInputType(InputType.TYPE_NULL);
        }
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!txtNombre.getText().toString().equals("") && !txtTelefono.getText().toString().equals("") && !txtCorreo.getText().toString().equals("")){
                  boolean correcto = dbContactos.editarContacto(id,txtNombre.getText().toString(), txtTelefono.getText().toString(), txtCorreo.getText().toString());
                    if (correcto){
                        Toast.makeText(EditarActivity.this, "REGISTRO ACTUALIZADO", Toast.LENGTH_LONG).show();
                        verRegistro();
                    }else {
                        Toast.makeText(EditarActivity.this, "ERROR AL ACTUALIZAR", Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(EditarActivity.this, "SE DEBEN LLENAR TODOS LOS CAMPOS",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private void verRegistro(){
        Intent intent = new Intent(this, VerActivity.class);
        startActivity(intent);
    }
}
