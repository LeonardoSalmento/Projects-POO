package com.leo.novoprojetofinal.app;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.leo.novoprojetofinal.App;
import com.leo.novoprojetofinal.R;
import com.leo.novoprojetofinal.models.Aluno;

import io.objectbox.Box;

public class CadastroAlunoActivity extends AppCompatActivity {

    private TextInputEditText novoNomeAluno;
    private TextInputEditText novoNomeEscola;
    private TextInputEditText novaMediaEscola;
    private TextInputEditText novaMediaPessoal;
    private TextInputEditText novoEmail;
    private TextInputEditText novaSenha;
    private Button btnSalvarAluno;

    private Aluno aluno;
    private Box<Aluno> alunoBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_aluno);

        alunoBox = ((App)getApplication()).getBoxStore().boxFor(Aluno.class);

        aluno = new Aluno();

        Intent intent = new Intent();
        long id = intent.getLongExtra("alunoId",-1);

        setupViews();

        if (id != -1){
            aluno = alunoBox.get(id);
            novoNomeAluno.setText(aluno.getNome());
            novoNomeEscola.setText(aluno.getNomeEscola());
            novaMediaEscola.setText(aluno.getMediaEscola().toString());
            novaMediaPessoal.setText(aluno.getMediaPessoal().toString());
            novoEmail.setText(aluno.getEmail());
            novaSenha.setText(aluno.getSenha());

        }else{
            aluno = new Aluno();
        }


        btnSalvarAluno.setOnClickListener(v -> {
            String nomeAluno = novoNomeAluno.getText().toString();
            String nomeEscola = novoNomeEscola.getText().toString();
            String mediaEscola = novaMediaEscola.getText().toString();
            String mediaPessoal = novaMediaPessoal.getText().toString();
            String email = novoEmail.getText().toString();
            String senha = novaSenha.getText().toString();

            aluno.setNome(nomeAluno);
            aluno.setNomeEscola(nomeEscola);
            aluno.setMediaEscola(Double.valueOf(mediaEscola));
            aluno.setMediaPessoal(Double.valueOf(mediaPessoal));
            aluno.setEmail(email);
            aluno.setSenha(senha);

            alunoBox.put(aluno);
            Toast.makeText(this, "Cadastro Salvo", Toast.LENGTH_SHORT).show();
            finish();
        });
    }

    private void setupViews() {
        novoNomeAluno = findViewById(R.id.edit_novo_aluno);
        novoNomeEscola = findViewById(R.id.edit_novo_nome_escola);
        novaMediaEscola = findViewById(R.id.edit_nova_media_escola);
        novaMediaPessoal = findViewById(R.id.edit_nova_media_pessoal);
        novoEmail = findViewById(R.id.edit_text_novo_email);
        novaSenha = findViewById(R.id.edit_text_nova_senha);
        btnSalvarAluno = findViewById(R.id.btn_salvar_aluno);

    }
}
