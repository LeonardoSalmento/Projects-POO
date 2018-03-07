package com.leo.novoprojetofinal.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.leo.novoprojetofinal.App;
import com.leo.novoprojetofinal.R;
import com.leo.novoprojetofinal.models.AtividadeExtra;

import io.objectbox.Box;

public class ItemAtividadeExtraActivity extends AppCompatActivity{

    private TextView tvItemNomeAtividadeExtra;
    private TextView tvItemNomeProfessorAtividadeExtra;
    private TextView tvItemDescricaoAtividadeExtra;

    private AtividadeExtra atividadeExtra;
    private Box<AtividadeExtra> atividadeExtraBox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_atividade_extra);
        atividadeExtraBox = ((App) getApplication()).getBoxStore().boxFor(AtividadeExtra.class);


        setupViews();

    }

    @Override
    protected void onResume() {
        super.onResume();
        final Intent intent = getIntent();

        long atividadeExtraId = intent.getLongExtra("atividadeExtraId", -1);


        atividadeExtra = atividadeExtraBox.get(atividadeExtraId);
        tvItemNomeAtividadeExtra.setText(atividadeExtra.getNome());
        tvItemNomeProfessorAtividadeExtra.setText(atividadeExtra.getProfessor());
        tvItemDescricaoAtividadeExtra.setText(atividadeExtra.getDescricao());

    }


    private void setupViews() {
            tvItemNomeAtividadeExtra = findViewById(R.id.text_view_item_nome_atividade_extra);
            tvItemNomeProfessorAtividadeExtra = findViewById(R.id.text_view_item_professor_atividade_extra);
            tvItemDescricaoAtividadeExtra = findViewById(R.id.text_view_item_descricao_atividade_extra);
        }
}
