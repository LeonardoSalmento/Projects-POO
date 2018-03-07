package com.leo.novoprojetofinal.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.leo.novoprojetofinal.App;
import com.leo.novoprojetofinal.R;
import com.leo.novoprojetofinal.models.Aluno;
import com.leo.novoprojetofinal.models.Bimestre;

import io.objectbox.Box;

public class ItemBimestreActivity extends AppCompatActivity {

    private TextView tvNotaMensal;
    private TextView tvNotaBimestral;
    private TextView tvMediaBimestre;
    private TextView tvMediaEscola;
    private TextView tvMediaPessoal;
    private TextView tvMensagem;
    private Box<Bimestre> bimestreBox;
    private Box<Aluno> alunoBox;
    private Bimestre bimestre;
    private Aluno alunoLogado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_bimestre);

        setupViews();


        bimestreBox = ((App)getApplication()).getBoxStore().boxFor(Bimestre.class);
        alunoBox = ((App)getApplication()).getBoxStore().boxFor(Aluno.class);

        alunoLogado = obterAlunoLogado();
        bimestre = obterBimestre();

        tvNotaMensal.setText(bimestre.getMensal().toString());
        tvNotaBimestral.setText(bimestre.getBimestral().toString());
        tvMediaBimestre.setText(alunoLogado.getMediaEscola().toString());
        tvMediaEscola.setText(alunoLogado.getMediaPessoal().toString());
        tvMediaPessoal.setText(bimestre.getMediaAtual().toString());
        String resultado = retornaMensagem(bimestre.getMediaAtual(),alunoLogado.getMediaEscola(),alunoLogado.getMediaPessoal());
        tvMensagem.setText(resultado);

    }

    private Bimestre obterBimestre() {
        Intent intent = getIntent();
        long bimestreId = intent.getLongExtra("bimestreId",-1);
        return bimestreBox.get(bimestreId);
    }

    private void setupViews() {
        tvNotaMensal = findViewById(R.id.tv_nota_mensal);
        tvNotaBimestral = findViewById(R.id.tv_nota_bimestral);
        tvMediaBimestre = findViewById(R.id.tv_media_bimestral);
        tvMediaEscola = findViewById(R.id.tv_media_escolar);
        tvMediaPessoal = findViewById(R.id.tv_media_pessoal);
        tvMensagem = findViewById(R.id.tv_mensagem);
    }

    private String retornaMensagem(Double mediaBimestre, double mediaEscola, double mediaPessoal){
        if(mediaBimestre >= mediaPessoal){
            return "Parabéns você atingiu sua média pessoal!";
        }else if(mediaBimestre >= mediaEscola){
            return "Parabéns você passou por esse bimestre sem muitos problemas!";
        }
        return "Xii, parece que você ficou de recuperação";
    }

    private Aluno obterAlunoLogado(){
        final SharedPreferences preferences = getSharedPreferences("projetofinal.file", MODE_PRIVATE);
        long id = preferences.getLong("alunoId", -1);
        return alunoBox.get(id);
    }

}
