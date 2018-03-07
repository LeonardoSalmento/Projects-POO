package com.leo.anuncios;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.leo.anuncios.model.Anuncio;

import java.util.ArrayList;
import java.util.List;

import io.objectbox.Box;

/**
 * Created by Leo on 2/12/2018.
 */

//1 - Declarar a lista de objetos e a variavel de contexto
//2 - criar classe view holder
//3 - cria o construtor da classe adapter
//4 - extende o adapter para a viewholder do proprio adapter
//5 - implementa os metodos

public class AnuncioAdapter extends RecyclerView.Adapter<AnuncioAdapter.AnuncioViewHolder>{
    private Context context;
    private List<Anuncio> anuncios;
    private Box<Anuncio> anuncioBox;

    public AnuncioAdapter(Context context, List<Anuncio> anuncios, Box<Anuncio> anuncioBox) {
        this.context = context;
        this.anuncios = anuncios;
        this.anuncioBox = anuncioBox;

    }


    public class AnuncioViewHolder extends RecyclerView.ViewHolder{

        final TextView nome;
        final TextView valor;
        final TextView localizacao;
        final TextView data;

        public AnuncioViewHolder(View itemView) {
            super(itemView);

            nome = itemView.findViewById(R.id.cv_nome);
            valor = itemView.findViewById(R.id.cv_preco);
            localizacao = itemView.findViewById(R.id.cv_local);
            data = itemView.findViewById(R.id.cv_data);
        }
    }

    @Override
    public AnuncioViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cardview_anuncio,parent,false);
        AnuncioViewHolder anuncioViewHolder = new AnuncioViewHolder(view);

        return anuncioViewHolder;
    }

    @Override
    public void onBindViewHolder(AnuncioViewHolder holder, final int position) {
        final AnuncioViewHolder anuncioViewHolder = holder;
        final Anuncio anuncio = anuncios.get(position);

        anuncioViewHolder.nome.setText(anuncio.getNome());
        anuncioViewHolder.localizacao.setText(anuncio.getLocal());
        anuncioViewHolder.data.setText(anuncio.getData());
        anuncioViewHolder.valor.setText(anuncio.getValor().toString());

        anuncioViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, anuncio.getNome().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        anuncioViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                PopupMenu popupMenu = new PopupMenu(context,v);
                popupMenu.getMenuInflater().inflate(R.menu.menu_main,popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener((MenuItem item) -> {
                    switch (item.getItemId()) {
                        case R.id.btn_editar:
                            editarAnuncio(v,anuncio,position);
                            break;

                        case R.id.btn_excluir:
                            excluirAnuncio(v,anuncio,position);
                            break;
                    }
                    return false;
                    });

                popupMenu.show();
                return true;
            }
        });


    }

    private void excluirAnuncio(View v, Anuncio anuncio, int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.context);

        builder.setTitle("OlxApp");
        builder.setMessage("Deseja remover " + anuncio.getNome()+ " permanentemente?");
        builder.setPositiveButton("SIM", (dialog, which) -> {
            this.anuncios.remove(anuncio);
            this.anuncioBox.remove(anuncio);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, getItemCount());
            Snackbar.make(v, "Anúncio " + anuncio.getNome() + " removido",
                    Snackbar.LENGTH_LONG).show();
        });
        builder.setNegativeButton("NÃO", (dialog, which) -> {
            Snackbar.make(v, "Anúncio não removido", Snackbar.LENGTH_LONG).show();
        });

        builder.create().show();
    }


    private void editarAnuncio(View v, Anuncio anuncio, int position) {
        Intent intent = new Intent(context, NovoAnuncioCadastro.class);
        intent.putExtra("idAnuncio", anuncio.getId());

        context.startActivity(intent);
    }


    @Override
    public int getItemCount() {
        return this.anuncios.size();
    }


}
