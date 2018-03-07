package com.leo.novoprojetofinal.app;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.leo.novoprojetofinal.adapters.BimestreAdapter;
import com.leo.novoprojetofinal.App;
import com.leo.novoprojetofinal.R;
import com.leo.novoprojetofinal.models.Bimestre;
import com.leo.novoprojetofinal.models.Bimestre_;
import com.leo.novoprojetofinal.models.Disciplina;

import java.util.List;

import io.objectbox.Box;
import io.objectbox.query.QueryBuilder;

public class ListaBimestreActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Box<Disciplina> disciplinaBox;
    private Disciplina disciplina;
    private Box<Bimestre> bimestreBox;
    private FloatingActionButton fabAdicionar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_bimestre);
        setupViews();

        disciplinaBox = ((App)getApplication()).getBoxStore().boxFor(Disciplina.class);
        bimestreBox = ((App)getApplication()).getBoxStore().boxFor(Bimestre.class);

        long disciplinaId = obterIdDisciplina();

        fabAdicionar.setOnClickListener(v-> {
            Intent intent1 = new Intent(this,CadastroBimestreActivity.class);
            intent1.putExtra("disciplinaId", disciplinaId);
            startActivity(intent1);
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        long disciplinaId = obterIdDisciplina();

        QueryBuilder<Bimestre> builder = bimestreBox.query();
        builder.equal(Bimestre_.disciplinaToOneId, disciplinaId);
        List<Bimestre> bimestres = builder.build().find();


        BimestreAdapter adapter = new BimestreAdapter(bimestres, this, bimestreBox);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);


    }

    private void setupViews() {
        recyclerView = findViewById(R.id.rv_lista_bimestre);
        fabAdicionar = findViewById(R.id.fab_novo_bimestre);
    }

    public long obterIdDisciplina(){
        Intent intent = getIntent();

        long disciplinaId = intent.getLongExtra("disciplinaId", -1);
        return disciplinaId;
    }


}
