package com.leo.novoprojetofinal.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.leo.novoprojetofinal.App;
import com.leo.novoprojetofinal.R;
import com.leo.novoprojetofinal.models.Aluno;
import com.leo.novoprojetofinal.models.Disciplina;

import io.objectbox.Box;

public class CadastroDisciplinaActivity extends AppCompatActivity {

    private TextInputEditText editNovoNome;
    private TextInputEditText editNovoApelido;
    private TextInputEditText editNovoProfessor;
    private TextInputEditText editqtdBimestre;
    private Button btnSalvarDisciplina;
    private Box<Aluno> alunoBox;
    private Box<Disciplina> disciplinaBox;
    private Aluno alunoLogado;
    private Disciplina disciplina;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_disciplina);

        setupViews();

        alunoBox = ((App)getApplication()).getBoxStore().boxFor(Aluno.class);
        disciplinaBox = ((App)getApplication()).getBoxStore().boxFor(Disciplina.class);

        alunoLogado = obterAlunoLogado();

        final Intent intent = getIntent();
        long cadastroid = intent.getLongExtra("cadastroId",-1);

        if (cadastroid != -1){
            editNovoNome.setText(disciplina.getNome());
            editNovoApelido.setText(disciplina.getApelido());
            editNovoProfessor.setText(disciplina.getProfessor());

        }


        btnSalvarDisciplina.setOnClickListener(v-> {
            salvarDisciplina();

        });


    }

    private void setupViews() {
        editNovoNome = findViewById(R.id.edit_text_nome_disciplina);
        editNovoApelido = findViewById(R.id.edit_text_apelido_disciplina);
        editNovoProfessor = findViewById(R.id.edit_text_nome_professor_disciplina);
        editqtdBimestre = findViewById(R.id.edit_text_quantidade_bimestres_disciplina);
        btnSalvarDisciplina = findViewById(R.id.btn_salvar_disciplina);


    }

    private Aluno obterAlunoLogado() {
        SharedPreferences preferences = getSharedPreferences("projetofinal.file", MODE_PRIVATE);
        long id = preferences.getLong("alunoId", -1);
        return alunoBox.get(id);

    }

    public void salvarDisciplina() {
        String nomeDisciplina = editNovoNome.getText().toString();
        String apelidoDisciplina = editNovoApelido.getText().toString();
        String professorDisciplina = editNovoProfessor.getText().toString();

        Integer qtdBimestre = Integer.parseInt(editqtdBimestre.getText().toString());

        disciplina = new Disciplina(nomeDisciplina,apelidoDisciplina,professorDisciplina,qtdBimestre);

        disciplina.getAlunoToOne().setTarget(alunoLogado);


        disciplinaBox.put(disciplina);
        Toast.makeText(this,"Disciplina Salva",Toast.LENGTH_SHORT).show();
        finish();
    }

    //TODO Terminar o cadastro e fazer todo o resto...... da parte da disciplina......
}
