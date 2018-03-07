package com.leo.projetofinal.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.leo.projetofinal.R;
import com.leo.projetofinal.model.Aluno;
import com.leo.projetofinal.model.Disciplina;

import java.util.List;

import io.objectbox.Box;

/**
 * Created by Leo on 2/22/2018.
 */

public class DisciplinasAdapter extends RecyclerView.Adapter<DisciplinasAdapter.AlunoViewHolder>{
    private Context context;
    private List<Aluno> alunos;
    private List<Disciplina> disciplinas;
    private Box<Aluno> alunoBox;
    private Box<Disciplina> disciplinaBox;


    public DisciplinasAdapter(Context context, List<Aluno> alunos,List<Disciplina> disciplinas, Box<Aluno> alunoBox, Box<Disciplina> disciplinaBox) {
        this.context = context;
        this.alunos = alunos;
        this.alunoBox = alunoBox;
        this.disciplinas = disciplinas;
        this.disciplinaBox = disciplinaBox;
    }


    public class AlunoViewHolder extends RecyclerView.ViewHolder{

        final TextView disciplina;
        final TextView professor;
        final TextView media;

        public AlunoViewHolder(View itemView) {
            super(itemView);

            disciplina = itemView.findViewById(R.id.cv_disciplina);
            professor = itemView.findViewById(R.id.cv_professor);
            media = itemView.findViewById(R.id.cv_media);

        }
    }

    @Override
    public AlunoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cardview_disciplinas,parent,false);
        AlunoViewHolder alunoViewHolder = new AlunoViewHolder(view);

        return alunoViewHolder;
    }

    @Override
    public void onBindViewHolder(AlunoViewHolder holder, int position) {
        AlunoViewHolder alunoViewHolder = holder;
        Aluno aluno = alunos.get(position);

        Disciplina Disciplina1 = new Disciplina();


        alunoViewHolder.disciplina.setText(Disciplina1.getNomeDisciplina());
        alunoViewHolder.professor.setText(Disciplina1.getNomeProfessor());
        alunoViewHolder.media.setText("0");

        alunoViewHolder.itemView.setOnClickListener(v -> {
            Toast.makeText(context,"click",Toast.LENGTH_SHORT).show();
            });

        alunoViewHolder.itemView.setOnLongClickListener(v -> {
            Toast.makeText(context,"clicando",Toast.LENGTH_SHORT).show();

            return false;
            });

    }

    @Override
    public int getItemCount() {
        return this.alunos.size();
    }
}
