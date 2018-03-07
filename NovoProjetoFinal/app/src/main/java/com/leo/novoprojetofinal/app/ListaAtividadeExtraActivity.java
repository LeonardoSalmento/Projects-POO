package com.leo.novoprojetofinal.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.leo.novoprojetofinal.adapters.AtividadeExtraAdapter;
import com.leo.novoprojetofinal.App;
import com.leo.novoprojetofinal.R;
import com.leo.novoprojetofinal.models.Aluno;
import com.leo.novoprojetofinal.models.AtividadeExtra;
import com.leo.novoprojetofinal.models.AtividadeExtra_;

import java.util.List;

import io.objectbox.Box;
import io.objectbox.query.QueryBuilder;

public class ListaAtividadeExtraActivity extends AppCompatActivity{

    private RecyclerView rvAtividadeExtra;
    private Box<AtividadeExtra> atividadeExtraBox;
    private Box<Aluno> alunoBox;
    private FloatingActionButton fabNovaAtividadeExtra;
    private Aluno alunoLogado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_atividade_extra);

        atividadeExtraBox = ((App)getApplication()).getBoxStore().boxFor(AtividadeExtra.class);
        alunoBox = ((App)getApplication()).getBoxStore().boxFor(Aluno.class);

        alunoLogado = obterAlunoLogado();

        setupViews();

        fabNovaAtividadeExtra.setOnClickListener(v-> startActivity(new Intent(this,CadastroAtividadeExtraActivity.class)));

    }

    @Override
    protected void onResume() {
        super.onResume();

        QueryBuilder<AtividadeExtra> builder = atividadeExtraBox.query();
        builder.equal(AtividadeExtra_.alunoToOneId,alunoLogado.getId());
        List<AtividadeExtra> atividadeExtrasAluno = builder.build().find();

        AtividadeExtraAdapter adapter = new AtividadeExtraAdapter(atividadeExtrasAluno, this,atividadeExtraBox);

        rvAtividadeExtra.setAdapter(adapter);
        rvAtividadeExtra.setLayoutManager(new LinearLayoutManager(this));
        rvAtividadeExtra.setHasFixedSize(true);


    }

    private void setupViews() {
            rvAtividadeExtra = findViewById(R.id.rv_atividades_extra);
            fabNovaAtividadeExtra = findViewById(R.id.fab_nova_atividade_extra);
        }

    private Aluno obterAlunoLogado(){
        final SharedPreferences preferences = getSharedPreferences("projetofinal.file", MODE_PRIVATE);
        long id = preferences.getLong("alunoId", -1);
        return alunoBox.get(id);
    }
}
