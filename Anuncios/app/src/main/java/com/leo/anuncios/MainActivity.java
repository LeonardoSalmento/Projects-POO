package com.leo.anuncios;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.View;

import com.leo.anuncios.model.Anuncio;

import java.util.ArrayList;
import java.util.List;

import io.objectbox.Box;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Anuncio> anuncios;
    private Box<Anuncio> anuncioBox;

    private RecyclerView recyclerView;
    private AnuncioAdapter anuncioAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private FloatingActionButton fabAddAnuncio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fabAddAnuncio = findViewById(R.id.btn_fab);
        fabAddAnuncio.setOnClickListener(new View.OnClickListener() {
             @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,NovoAnuncioCadastro.class));
            }
        });

        anuncioBox =  ((App)getApplication()).getBoxStore().boxFor(Anuncio.class);


        recyclerView = findViewById(R.id.rv_lista_anuncios);

         if (!logado()){
             startActivity(new Intent(this, LoginActivity.class));
             finish();
         }

    }

    private boolean logado() {
        final SharedPreferences preferences = getSharedPreferences("anuncios.file", MODE_PRIVATE);
        final long usuarioId = preferences.getLong("UsuarioId", -1);
        if (usuarioId == -1){
            return false;
        }else{
            return true;
        }
    }

    protected void onResume() {
        super.onResume();

        List<Anuncio> anuncios = anuncioBox.getAll();

        AnuncioAdapter anuncioAdapter = new AnuncioAdapter(MainActivity.this, anuncios, anuncioBox);
        recyclerView.setAdapter(anuncioAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
    }




    private void initializeAdapter() {
        recyclerView = findViewById(R.id.rv_lista_anuncios);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        anuncioAdapter = new AnuncioAdapter(this,anuncios, anuncioBox);
        recyclerView.setAdapter(anuncioAdapter);
    }



}
