package com.leo.novoprojetofinal.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.leo.novoprojetofinal.R;

public class MainActivity extends AppCompatActivity {

    private Button btnAluno;
    private Button btnDisciplinas;
    private Button btnAtividadeExtra;
    private Button btnSair;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupViews();

        if (!logado()){
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }

        btnAluno.setOnClickListener(v -> {

            final Intent intent = new Intent(this, AlunoActivity.class);
            intent.putExtra("alunoId",idAlunoLogado());
            startActivity(intent);

        });


        btnDisciplinas.setOnClickListener(v -> {
            startActivity(new Intent(this, ListaDisciplinaActivity.class));
        });

        btnAtividadeExtra.setOnClickListener(v -> {


            startActivity(new Intent(this, ListaAtividadeExtraActivity.class));
        });


        btnSair.setOnClickListener(v -> logout());

    }


    private boolean logado() {
        preferences = getSharedPreferences("projetofinal.file", MODE_PRIVATE);
        final long alunoId = preferences.getLong("alunoId", -1);

        return alunoId != -1;
    }

    private void setupViews() {
        btnAluno = findViewById(R.id.btn_aluno);
        btnDisciplinas = findViewById(R.id.btn_disciplinas);
        btnAtividadeExtra = findViewById(R.id.btn_atividades_extra);
        btnSair = findViewById(R.id.btn_sair);
    }

    private void logout() {
        preferences.edit().clear().apply();
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    private long idAlunoLogado(){
        preferences = getSharedPreferences("projetofinal.file", MODE_PRIVATE);
        final long alunoId = preferences.getLong("alunoId", -1);
        return alunoId;
    }
}