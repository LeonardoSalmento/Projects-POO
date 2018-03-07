package com.leo.novoprojetofinal.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.leo.novoprojetofinal.adapters.DisciplinaAdapter;
import com.leo.novoprojetofinal.App;
import com.leo.novoprojetofinal.R;
import com.leo.novoprojetofinal.models.Aluno;
import com.leo.novoprojetofinal.models.Disciplina;
import com.leo.novoprojetofinal.models.Disciplina_;

import java.util.List;

import io.objectbox.Box;
import io.objectbox.query.QueryBuilder;

public class ListaDisciplinaActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Box<Disciplina> disciplinaBox;
    private Box<Aluno> alunoBox;
    private FloatingActionButton fabAddDisciplina;
    private Aluno alunoLogado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_disciplina);

        disciplinaBox = ((App)getApplication()).getBoxStore().boxFor(Disciplina.class);
        alunoBox = ((App)getApplication()).getBoxStore().boxFor(Aluno.class);

        alunoLogado = obterAlunoLogado();

        setupViews();


        fabAddDisciplina.setOnClickListener(v -> {

            startActivity(new Intent(this, CadastroDisciplinaActivity.class));
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        QueryBuilder<Disciplina> builder = disciplinaBox.query();
        builder.equal(Disciplina_.alunoToOneId,alunoLogado.getId());
        List<Disciplina> disciplinasAluno = builder.build().find();


        DisciplinaAdapter adapter = new DisciplinaAdapter(disciplinasAluno, this, disciplinaBox);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

    }

    private void setupViews() {
        fabAddDisciplina = findViewById(R.id.fab_nova_disciplina);
        recyclerView = findViewById(R.id.rv_lista_disciplina);
    }

    private Aluno obterAlunoLogado(){
        final SharedPreferences preferences = getSharedPreferences("projetofinal.file", MODE_PRIVATE);
        long id = preferences.getLong("alunoId", -1);
        return alunoBox.get(id);
    }
}
