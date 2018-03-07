package com.leo.projetofinal;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.leo.projetofinal.model.Aluno;
import com.leo.projetofinal.model.Disciplina;

import io.objectbox.Box;

public class CadastroDisciplinaActivity extends AppCompatActivity {

    private TextInputEditText novaDisciplina;
    private TextInputEditText novoProfessor;
    private TextInputEditText novaMedia;

    private Aluno aluno;
    private Box<Aluno> alunoBox;
    private Disciplina disciplina;
    private Box<Disciplina> disciplinaBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_disciplina);

        alunoBox = ((App)getApplication()).getBoxStore().boxFor(Aluno.class);
        disciplinaBox = ((App)getApplication()).getBoxStore().boxFor(Disciplina.class);

        Intent intent = getIntent();
        long id = intent.getLongExtra("idDisciplina", -1);

        novaDisciplina = findViewById(R.id.edit_text_disciplina);
        novoProfessor = findViewById(R.id.edit_text_nome_professor);
        novaMedia = findViewById(R.id.edit_text_mediaEscola);

        if (id != -1){
            disciplina = disciplinaBox.get(id);
            novaDisciplina.setText(disciplina.getNomeDisciplina());
            novoProfessor.setText(disciplina.getNomeProfessor());
            novaMedia.setText("8");


        }else{
            disciplina = new Disciplina();
        }


    }

    public void salvarDisciplina(View view){
        String nomeDisciplina = novaDisciplina.getText().toString();
        String nomeProfessor = novoProfessor.getText().toString();
        String media = novaMedia.getText().toString();

        disciplina.setNomeDisciplina(nomeDisciplina);
        disciplina.setNomeProfessor(nomeProfessor);
        disciplina.setNota(Double.valueOf(media));

        disciplinaBox.put(disciplina);

        finish();
    }
}
