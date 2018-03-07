package com.leo.projetofinal.actvities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.leo.projetofinal.R;

public class LoginActivity extends AppCompatActivity {

    private Context context;
    private EditText login;
    private EditText senha;
    private Button btnEntrar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        setupViews();
        logar();


    }


    private void logar() {
        btnEntrar.setOnClickListener(view -> {
            startActivity(new Intent(this, MainActivity.class));
            finish();

        });
    }


    void setupViews(){
        login = findViewById(R.id.edit_text_email);
        senha = findViewById(R.id.edit_text_senha);
        btnEntrar = findViewById(R.id.btn_entrar);
    }

    public void cadastroClick(View v) {
        LayoutInflater inflater = getLayoutInflater();

        View view = inflater.inflate(R.layout.cadastro_aluno,null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Cadastro");
        builder.setPositiveButton(getString(R.string.salvar), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                salvarCadastroAluno();
            }
        });

        builder.setNegativeButton(getString(R.string.cancelar), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Snackbar.make(v,"Cancelado",Snackbar.LENGTH_SHORT).show();
            }
        });
        builder.setView(view);
        builder.create();
        builder.show();

    }

    private void salvarCadastroAluno() {
        Toast.makeText(LoginActivity.this,"Salvando...",Toast.LENGTH_SHORT).show();
    }
}
