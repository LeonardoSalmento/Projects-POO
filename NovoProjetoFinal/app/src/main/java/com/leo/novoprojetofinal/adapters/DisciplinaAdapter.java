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

import com.leo.novoprojetofinal.app.EditarDisciplinaActivity;
import com.leo.novoprojetofinal.app.ListaBimestreActivity;
import com.leo.novoprojetofinal.R;
import com.leo.novoprojetofinal.models.Disciplina;

import java.util.List;

import io.objectbox.Box;

/**
 * Created by Leo on 3/3/2018.
 */

//1 - Declarar a lista de objetos e a variavel de contexto
//2 - criar classe view holder
//3 - cria o construtor da classe adapter
//4 - extende o adapter para a viewholder do proprio adapter
//5 - implementa os metodos

public class DisciplinaAdapter extends RecyclerView.Adapter<DisciplinaAdapter.ViewHolder>{

    private List<Disciplina> disciplinas;
    private Context context;
    private Box<Disciplina> disciplinaBox;

    public DisciplinaAdapter(List<Disciplina> disciplinas, Context context, Box<Disciplina> disciplinaBox) {
        this.disciplinas = disciplinas;
        this.context = context;
        this.disciplinaBox = disciplinaBox;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvApelido;
        private TextView tvProfessor;
//        private TextView tvMedia;

        public ViewHolder(View itemView) {
            super(itemView);

            tvApelido = itemView.findViewById(R.id.cv_apelido_disciplina);
            tvProfessor = itemView.findViewById(R.id.cv_professor);
 //           tvMedia = itemView.findViewById(R.id.cv_media);
        }
    }


    @Override
    public DisciplinaAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cardview_disciplina,parent,false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(DisciplinaAdapter.ViewHolder holder, int position) {
        Disciplina disciplina = disciplinas.get(position);
        holder.tvApelido.setText(disciplina.getApelido());
        holder.tvProfessor.setText(disciplina.getProfessor());


        holder.itemView.setOnClickListener(v-> {
            final Intent intent = new Intent(context, ListaBimestreActivity.class);
            intent.putExtra("disciplinaId",disciplina.getId());
            context.startActivity(intent);
        });

        holder.itemView.setOnLongClickListener( v->{
            PopupMenu popupMenu = new PopupMenu(context, v);
            popupMenu.getMenuInflater().inflate(R.menu.menu_disciplina, popupMenu.getMenu());

            popupMenu.setOnMenuItemClickListener((MenuItem item) -> {
                switch (item.getItemId()) {
                    case R.id.editar_disciplina:
                        editarDisciplina(v, disciplina);
                        break;

                    case R.id.excluir_disciplina:
                        excluirDisciplina(v, disciplina, position);
                        break;
                }
                return false;
            });

            popupMenu.show();
            return true;


        });
    }

    private void excluirDisciplina(View v, Disciplina disciplina, int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.context);

        builder.setTitle("Disciplina")
                .setPositiveButton("SIM", ((dialog, which) -> {
                    this.disciplinas.remove(disciplina);
                    this.disciplinaBox.remove(disciplina);
                    notifyItemRemoved(position);
                    notifyItemChanged(position);
                    Snackbar.make(v,"Disciplina " + disciplina.getNome() + " foi removida",Snackbar.LENGTH_SHORT).show();
                }))
                .setNegativeButton("NÃO", ((dialog, which) -> {
                    Toast.makeText(context,"Disciplina " + disciplina.getNome() + " não removida", Toast.LENGTH_SHORT).show();
                }))
                .create()
                .show();
    }

    private void editarDisciplina(View v, Disciplina disciplina) {
        Intent intent = new Intent(context,EditarDisciplinaActivity.class);
        intent.putExtra("disciplinaId",disciplina.getId());
        context.startActivity(intent);

    }

    @Override
    public int getItemCount() {
        return this.disciplinas.size();
    }
}
