package com.leo.novoprojetofinal.app;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.leo.novoprojetofinal.App;
import com.leo.novoprojetofinal.R;
import com.leo.novoprojetofinal.models.Bimestre;
import com.leo.novoprojetofinal.models.Disciplina;

import io.objectbox.Box;

public class CadastroBimestreActivity extends AppCompatActivity {

    private TextInputEditText editNotaMensal;
    private TextInputEditText editNotaBimestral;
    private Button btnSalvar;
    private Box<Bimestre> bimestreBox;
    private Box<Disciplina> disciplinaBox;
    private Disciplina disciplina;
    private Bimestre bimestre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_bimestre);

        setupViews();

        bimestreBox = ((App)getApplication()).getBoxStore().boxFor(Bimestre.class);
        disciplinaBox = ((App)getApplication()).getBoxStore().boxFor(Disciplina.class);

        final Intent intent = getIntent();
        long disciplinaId = intent.getLongExtra("disciplinaId",-1);

        disciplina = disciplinaBox.get(disciplinaId);



        bimestre = new Bimestre();

        btnSalvar.setOnClickListener(v-> {
            salvarBimestre();

        });

    }

    private void salvarBimestre() {
        String mensal = editNotaMensal.getText().toString();
        String bimestral = editNotaBimestral.getText().toString();

        bimestre.setMensal(Double.valueOf(mensal));
        bimestre.setBimestral(Double.valueOf(bimestral));
        bimestre.getDisciplinaToOne().setTarget(disciplina);

        bimestreBox.put(bimestre);
        Toast.makeText(this,"Bimestre salvo",Toast.LENGTH_SHORT).show();
        finish();

    }

    private void setupViews() {
        editNotaMensal = findViewById(R.id.edit_text_new_nota_mensal);
        editNotaBimestral = findViewById(R.id.edit_text_new_nota_bimestral);
        btnSalvar = findViewById(R.id.btn_salvar_bimestre);
    }
}
