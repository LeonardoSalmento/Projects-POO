package com.leo.novoprojetofinal.app;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.leo.novoprojetofinal.App;
import com.leo.novoprojetofinal.R;
import com.leo.novoprojetofinal.models.Aluno;

import io.objectbox.Box;

public class AlunoActivity extends AppCompatActivity {

    private TextView nomeAluno;
    private TextView nomeEscola;
    private Box<Aluno> alunoBox;
    private Aluno aluno;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aluno);

    }

    @Override
    protected void onResume() {
        super.onResume();
        alunoBox = ((App)getApplication()).getBoxStore().boxFor(Aluno.class);
        aluno = obterAlunoLogado();
        setupViews();

        nomeAluno.setText(aluno.getNome());
        nomeEscola.setText(aluno.getNomeEscola());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_aluno,menu);

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.edit_aluno) {
            editarAluno();
        }
        return false;
    }


    private void editarAluno() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final View view = getLayoutInflater().inflate(R.layout.editar_aluno,null);

        final TextInputEditText novoNome = view.findViewById(R.id.edit_text_edit_nome);
        final TextInputEditText novaEscola = view.findViewById(R.id.edit_text_edit_nome_escola);
        final TextInputEditText novoEmail = view.findViewById(R.id.edit_text_edit_email);
        final TextInputEditText novaMediaEscola = view.findViewById(R.id.edit_text_edit_media_escola);
        final TextInputEditText novaMediaPessoal = view.findViewById(R.id.edit_text_edit_media_pessoal);

        novoNome.setText(aluno.getNome());
        novaEscola.setText(aluno.getNomeEscola());
        novoEmail.setText(aluno.getEmail());
        novaMediaEscola.setText(aluno.getMediaEscola().toString());
        novaMediaPessoal.setText(aluno.getMediaPessoal().toString());

        builder.setView(view)
                .setTitle("Editar Aluno")
                .setPositiveButton("Salvar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        aluno = obterAlunoLogado();
                        aluno.setNome(novoNome.getText().toString());
                        aluno.setNomeEscola(novaEscola.getText().toString());
                        aluno.setEmail(novoEmail.getText().toString());
                        aluno.setMediaEscola(Double.valueOf(novaMediaEscola.getText().toString()));
                        aluno.setMediaPessoal(Double.valueOf(novaMediaPessoal.getText().toString()));

                        alunoBox.put(aluno);
                        Toast.makeText(AlunoActivity.this,"Salvo",Toast.LENGTH_SHORT).show();
                        finish();

                    }
                })

                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(AlunoActivity.this,"Cancelado",Toast.LENGTH_SHORT).show();
                    }
                });

        builder.create().show();


    }

    private void setupViews() {
        nomeAluno = findViewById(R.id.text_view_nome_aluno);
        nomeEscola = findViewById(R.id.text_view_nome_escola);
    }

    private Aluno obterAlunoLogado(){
        final SharedPreferences preferences = getSharedPreferences("projetofinal.file", MODE_PRIVATE);
        long id = preferences.getLong("alunoId", -1);
        return alunoBox.get(id);
    }

}