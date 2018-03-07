package com.leo.projetofinal.actvities;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.leo.projetofinal.App;
import com.leo.projetofinal.CadastroDisciplinaActivity;
import com.leo.projetofinal.R;
import com.leo.projetofinal.model.Aluno;
import com.leo.projetofinal.model.Disciplina;

import io.objectbox.Box;

public class DisciplinaActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disciplina);


    }

    public void CadastroDisciplinaClick(View view) {
        startActivity(new Intent(this, CadastroDisciplinaActivity.class));
    }


}
