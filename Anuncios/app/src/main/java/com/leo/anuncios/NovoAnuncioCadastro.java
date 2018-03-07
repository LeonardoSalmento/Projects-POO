package com.leo.anuncios;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.leo.anuncios.model.Anuncio;

import java.util.List;

import io.objectbox.Box;

public class NovoAnuncioCadastro extends AppCompatActivity {

    private TextInputEditText novoNome;
    private TextInputEditText novoLocal;
    private TextInputEditText novoValor;
    private TextInputEditText novoDescricao;

    private Anuncio anuncio;
    private Box<Anuncio> anuncioBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_anuncio_cadastro);


        anuncioBox = ((App) getApplication()).getBoxStore().boxFor(Anuncio.class);

        Intent intent = getIntent();
        long id = intent.getLongExtra("idAnuncio",-1);

        novoNome = findViewById(R.id.nome_novo_produto);
        novoLocal = findViewById(R.id.local_novo_produto);
        novoValor = findViewById(R.id.valor_novo_produto);
        novoDescricao = findViewById(R.id.descricao_novo_produto);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (id != -1){
            anuncio = anuncioBox.get(id);
            novoNome.setText(anuncio.getNome());
            novoValor.setText(anuncio.getValor().toString());
            novoLocal.setText(anuncio.getLocal());
            novoDescricao.setText(anuncio.getDescricao());
            
        }else{
            anuncio = new Anuncio();
        }


    }


    public void salvarAnuncio(View view) {
        String nome = novoNome.getText().toString();
        String valor = novoValor.getText().toString();
        String local = novoLocal.getText().toString();
        String descricao = novoDescricao.getText().toString();

        anuncio.setNome(nome);
        anuncio.setDescricao(descricao);
        anuncio.setValor(Double.valueOf(valor));
        anuncio.setLocal(local);

        anuncioBox.put(anuncio);
        finish();
    }
}
