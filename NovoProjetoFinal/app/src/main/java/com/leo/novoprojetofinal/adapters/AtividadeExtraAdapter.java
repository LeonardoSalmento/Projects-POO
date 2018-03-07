package com.leo.novoprojetofinal.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.leo.novoprojetofinal.app.CadastroAtividadeExtraActivity;
import com.leo.novoprojetofinal.app.ItemAtividadeExtraActivity;
import com.leo.novoprojetofinal.R;
import com.leo.novoprojetofinal.models.AtividadeExtra;

import java.util.List;

import io.objectbox.Box;

/**
 * Created by Leo on 2/25/2018.
 */

public class AtividadeExtraAdapter extends RecyclerView.Adapter<AtividadeExtraAdapter.ViewHolder> {


    private final List<AtividadeExtra> atividadeExtras;
    private final Context context;
    private Box<AtividadeExtra> atividadeExtraBox;

    public AtividadeExtraAdapter(List<AtividadeExtra> atividadeExtras, Context context, Box<AtividadeExtra> atividadeExtraBox) {

        this.atividadeExtras = atividadeExtras;
        this.context = context;
        this.atividadeExtraBox = atividadeExtraBox;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tvNomeAtividadeExtra;
        private TextView tvNomeProfessorAtividadeExtra;

        public ViewHolder(View itemView) {
            super(itemView);

            tvNomeAtividadeExtra = itemView.findViewById(R.id.cv_atividade_extra);
            tvNomeProfessorAtividadeExtra = itemView.findViewById(R.id.cv_atividade_extra_professor);

        }

    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cardview_atividade_extra, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        AtividadeExtra atividadeExtra = this.atividadeExtras.get(position);
        viewHolder.tvNomeAtividadeExtra.setText(atividadeExtra.getNome());
        viewHolder.tvNomeProfessorAtividadeExtra.setText(atividadeExtra.getProfessor());

        viewHolder.itemView.setOnClickListener(v -> {
            Toast.makeText(context, atividadeExtra.getNome(), Toast.LENGTH_SHORT).show();
        });

        viewHolder.itemView.setOnLongClickListener(v -> {
            PopupMenu popupMenu = new PopupMenu(context, v);
            popupMenu.getMenuInflater().inflate(R.menu.menu_item_atividade_extra, popupMenu.getMenu());

            popupMenu.setOnMenuItemClickListener((MenuItem item) -> {
                switch (item.getItemId()) {
                    case R.id.item_detalhes_atividade_extra:
                        detalhesAtividade(v, atividadeExtra, position);
                        break;

                    case R.id.item_editar_atividade_extra:
                        editarAtividade(v, atividadeExtra, position);
                        break;

                    case R.id.item_excluir_atividade_extra:
                        excluirAtividade(v, atividadeExtra, position);
                        break;
                }
                return false;
            });

            popupMenu.show();
            return true;


        });


    }

    @Override
    public int getItemCount() {
        return this.atividadeExtras.size();
    }

    private void excluirAtividade(View v, AtividadeExtra atividadeExtra, int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.context);

        builder.setTitle("Atividade")
                .setPositiveButton("SIM", ((dialog, which) -> {
                    this.atividadeExtras.remove(atividadeExtra);
                    this.atividadeExtraBox.remove(atividadeExtra);
                    notifyItemRemoved(position);
                    notifyItemChanged(position);
                    Snackbar.make(v,"Atividade " + atividadeExtra.getNome() + " foi removida",Snackbar.LENGTH_SHORT).show();
                }))
                .setNegativeButton("NÃO", ((dialog, which) -> {
                    Toast.makeText(context,"Atividade " + atividadeExtra.getNome() + " não removida", Toast.LENGTH_SHORT).show();
                }))
                .create()
                .show();


    }

    private void editarAtividade(View v, AtividadeExtra atividadeExtra, int position) {
        final Intent intent = new Intent(context, CadastroAtividadeExtraActivity.class);
        intent.putExtra("atividadeExtraId",atividadeExtra.getId());
        context.startActivity(intent);
    }

    private void detalhesAtividade(View v, AtividadeExtra atividadeExtra, int position) {
        final Intent intent = new Intent(context, ItemAtividadeExtraActivity.class);
        intent.putExtra("atividadeExtraId",atividadeExtra.getId());
        context.startActivity(intent);
    }
}
