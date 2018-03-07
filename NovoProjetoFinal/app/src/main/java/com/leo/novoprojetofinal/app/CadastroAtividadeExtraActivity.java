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
import com.leo.novoprojetofinal.models.AtividadeExtra;

import io.objectbox.Box;

public class CadastroAtividadeExtraActivity extends AppCompatActivity{

    private TextInputEditText editNomeAtividadeExtra;
    private TextInputEditText editNomeProfessorAtividadeExtra;
    private TextInputEditText editDescricaoAtividadeExtra;
    private Button btnSalvarAtividadeExtra;

    private AtividadeExtra atividadeExtra;
    private Box<AtividadeExtra> atividadeExtraBox;
    private Box<Aluno> alunoBox;
    private Aluno alunoLogado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_atividade_extra);

        setupViews();

        atividadeExtraBox = ((App)getApplication()).getBoxStore().boxFor(AtividadeExtra.class);
        alunoBox = ((App)getApplication()).getBoxStore().boxFor(Aluno.class);

        alunoLogado = obterAlunoLogado();

        atividadeExtra = new AtividadeExtra();

        btnSalvarAtividadeExtra.setOnClickListener(v-> {
            salvarAtividadeExtra(atividadeExtra);
        });

        final Intent intent = getIntent();
        long atividadeExtraId = intent.getLongExtra("atividadeExtraId", -1);

        if (atividadeExtraId != -1){
            atividadeExtra = atividadeExtraBox.get(atividadeExtraId);
            editNomeAtividadeExtra.setText(atividadeExtra.getNome());
            editNomeProfessorAtividadeExtra.setText(atividadeExtra.getProfessor());
            editDescricaoAtividadeExtra.setText(atividadeExtra.getDescricao());
        }else{
            atividadeExtra = new AtividadeExtra();
        }

    }

    private void salvarAtividadeExtra(AtividadeExtra atividadeExtra) {
        String nomeAtividadeExtra = editNomeAtividadeExtra.getText().toString();
        String nomeProfessorAtividadeExtra = editNomeProfessorAtividadeExtra.getText().toString();
        String descricaoAtividadeExtra = editDescricaoAtividadeExtra.getText().toString();

        atividadeExtra.setNome(nomeAtividadeExtra);
        atividadeExtra.setProfessor(nomeProfessorAtividadeExtra);
        atividadeExtra.setDescricao(descricaoAtividadeExtra);
        atividadeExtra.getAlunoToOne().setTarget(alunoLogado);

        atividadeExtraBox.put(atividadeExtra);
        Toast.makeText(this,"Atividade Salva",Toast.LENGTH_SHORT).show();
        finish();


    }

    private void setupViews() {
            editNomeAtividadeExtra = findViewById(R.id.edit_text_nova_atividade_extra);
            editNomeProfessorAtividadeExtra = findViewById(R.id.edit_text_novo_nome_professor_atividade_extra);
            editDescricaoAtividadeExtra = findViewById(R.id.edit_text_nova_descricao_atividade_extra);
            btnSalvarAtividadeExtra = findViewById(R.id.btn_salvar_atividade_extra);

        }

    private Aluno obterAlunoLogado(){
        final SharedPreferences preferences = getSharedPreferences("projetofinal.file", MODE_PRIVATE);
        long id = preferences.getLong("alunoId", -1);
        return alunoBox.get(id);
    }


}
