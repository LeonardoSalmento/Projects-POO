package com.leo.novoprojetofinal.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.leo.novoprojetofinal.App;
import com.leo.novoprojetofinal.R;
import com.leo.novoprojetofinal.models.Aluno;
import com.leo.novoprojetofinal.models.Aluno_;

import java.util.List;

import io.objectbox.Box;
import io.objectbox.query.Query;
import io.objectbox.query.QueryBuilder;


public class LoginActivity extends AppCompatActivity {

    private TextInputEditText editEmail;
    private TextInputEditText editSenha;
    private Button btnEntrar;
    private TextView textCadastro;
    
    private Box<Aluno> alunoBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        
        alunoBox = ((App)getApplication()).getBoxStore().boxFor(Aluno.class);
        
        setupViews();


        btnEntrar.setOnClickListener(v-> {
            String email = editEmail.getText().toString();
            String senha = editSenha.getText().toString();
            QueryBuilder<Aluno> builder = alunoBox.query();
            builder.equal(Aluno_.email,email);
            builder.equal(Aluno_.senha,senha);

            Query<Aluno> query = builder.build();

            List<Aluno> result = query.find();

            if (result.size() > 0){
                logar(result.get(0));
            }else{
                editSenha.getText().clear();
                Toast.makeText(this,"Email e/ou senha incorreto(s)",Toast.LENGTH_SHORT).show();

            }

        });

        textCadastro.setOnClickListener(v-> {
            startActivity(new Intent(this, CadastroAlunoActivity.class));
        });
    }

    private void logar(Aluno aluno) {
        final SharedPreferences preferences = getSharedPreferences("projetofinal.file", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putLong("alunoId",aluno.getId());

        editor.commit();

        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    private void setupViews() {
        editEmail = findViewById(R.id.edit_text_login_email);
        editSenha = findViewById(R.id.edit_text_login_senha);
        btnEntrar = findViewById(R.id.btn_entrar);
        textCadastro = findViewById(R.id.text_v_cadastrar);
    }


}
