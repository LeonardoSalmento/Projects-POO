package com.leo.novoprojetofinal.app;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.leo.novoprojetofinal.App;
import com.leo.novoprojetofinal.R;
import com.leo.novoprojetofinal.models.Disciplina;

import io.objectbox.Box;

public class EditarDisciplinaActivity extends AppCompatActivity {

    private TextInputEditText editNovoNome;
    private TextInputEditText editNovoApelido;
    private TextInputEditText editNovoProfessor;
    private Button btnSalvar;

    private Box<Disciplina> disciplinaBox;
    private Disciplina disciplina;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_disciplina);

        setupViews();

        disciplinaBox = ((App)getApplication()).getBoxStore().boxFor(Disciplina.class);

        btnSalvar.setOnClickListener(v-> {
                salvarDisciplina(disciplina);

        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        final Intent intent = getIntent();
        long disciplinaId = intent.getLongExtra("disciplinaId",-1);


        disciplina = disciplinaBox.get(disciplinaId);


        editNovoNome.setText(disciplina.getNome());
        editNovoApelido.setText(disciplina.getApelido());
        editNovoProfessor.setText(disciplina.getProfessor());

    }

    private void setupViews() {
        editNovoNome = findViewById(R.id.edit_editar_nome_disciplina);
        editNovoApelido = findViewById(R.id.edit_editar_apelido_disciplina);
        editNovoProfessor = findViewById(R.id.edit_editar_nome_professor_disciplina);
        btnSalvar = findViewById(R.id.btn_salvar_edicao_disciplina);
    }


    public void salvarDisciplina(Disciplina disciplina) {
        String nomeDisciplina = editNovoNome.getText().toString();
        String apelidoDisciplina = editNovoApelido.getText().toString();
        String professorDisciplina = editNovoProfessor.getText().toString();

        disciplina.setNome(nomeDisciplina);
        disciplina.setApelido(apelidoDisciplina);
        disciplina.setProfessor(professorDisciplina);

        disciplinaBox.put(disciplina);
        Toast.makeText(this,"Disciplina Salva",Toast.LENGTH_SHORT).show();
        finish();
    }
}
