package com.leo.novoprojetofinal.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.leo.novoprojetofinal.R;
import com.leo.novoprojetofinal.app.ItemBimestreActivity;
import com.leo.novoprojetofinal.models.Bimestre;

import java.util.List;

import io.objectbox.Box;

import static android.content.Intent.getIntent;

/**
 * Created by Leo on 3/6/2018.
 */

//1 - Declarar a lista de objetos e a variavel de contexto
//2 - criar classe view holder
//3 - cria o construtor da classe adapter
//4 - extende o adapter para a viewholder do proprio adapter
//5 - implementa os metodos

public class BimestreAdapter extends RecyclerView.Adapter<BimestreAdapter.ViewHolder>{
    private Context context;
    private List<Bimestre> bimestres;
    private Box<Bimestre> bimestreBox;
    private String nomes[];



    public BimestreAdapter(List<Bimestre> bimestres, Context context, Box<Bimestre> bimestreBox) {
        this.context = context;
        this.bimestres = bimestres;
        this.bimestreBox = bimestreBox;

    }



    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView nomeBimestre;


        public ViewHolder(View itemView) {
            super(itemView);

            nomeBimestre = itemView.findViewById(R.id.cv_nome_bimestre);

        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {



        View view = LayoutInflater.from(context).inflate(R.layout.cardview_bimestres, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Bimestre bimestre = bimestres.get(position);
        holder.nomeBimestre.setText(obterNomeBimestre(position));




        holder.itemView.setOnClickListener(v-> {
            Intent intent = new Intent(context,ItemBimestreActivity.class);
            intent.putExtra("bimestreId",bimestre.getId());
            context.startActivity(intent);

        });
    }

    @Override
    public int getItemCount() {
        return this.bimestres.size();
    }

    public String obterNomeBimestre(int posicao){
        nomes = new String[]{"Primeiro", "Segundo", "Terceiro", "Quarto"};
        return nomes[posicao] + " Bimestre";

    }

}
