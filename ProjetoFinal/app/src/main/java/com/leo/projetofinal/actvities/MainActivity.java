package com.leo.projetofinal.actvities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.leo.projetofinal.R;
import com.leo.projetofinal.model.Disciplina;

public class MainActivity extends AppCompatActivity {
    private Context context;

    private Button btnAluno;
    private Button btnDisciplinas;
    private Button btnEventos;
    private Button btnSair;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupViews();

        btnDisciplinas.setOnClickListener(v -> {
               startActivity(new Intent(this, DisciplinaActivity.class));
            });


    }

    private void setupViews() {
        btnAluno = findViewById(R.id.btn_aluno);
        btnDisciplinas = findViewById(R.id.btn_disciplinas);
        btnEventos = findViewById(R.id.btn_eventos);
        btnSair = findViewById(R.id.btn_sair);
    }
}
