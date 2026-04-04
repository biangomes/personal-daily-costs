package br.beanascigom.mycosts;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PessoasActivity extends AppCompatActivity {

    private RecyclerView recyclerViewPessoas;
    private List<Pessoa> pessoas;
    private PessoaRecyclerViewAdapter pessoaRecyclerViewAdapter;
    private PessoaRecyclerViewAdapter.OnItemClickListener onItemClickListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pessoas);

        recyclerViewPessoas = findViewById(R.id.recyclerViewPessoas);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewPessoas.setLayoutManager(layoutManager);
        recyclerViewPessoas.setHasFixedSize(true);
        recyclerViewPessoas.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));


        onItemClickListener = new PessoaRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Pessoa pessoa = pessoas.get(position);
                Toast.makeText(getApplicationContext(),
                        getString(R.string.pessoa_de_nome) + pessoa.getNome() + getString(R.string.foi_clicada),
                        Toast.LENGTH_LONG).show();
            }
            @Override
            public void onItemLongClick(View view, int position) {
                Pessoa pessoa = pessoas.get(position);
                Toast.makeText(getApplicationContext(),
                        getString(R.string.pessoa_de_nome) + pessoa.getNome() + getString(R.string.recebeu_um_click_longo),
                        Toast.LENGTH_LONG).show();
            }
        };

        popularListaPessoas();
    }

    private void popularListaPessoas() {
        String[] pessoas_nomes = getResources().getStringArray(R.array.pessoas_nome);
        int[] pessoas_maos_usadas = getResources().getIntArray(R.array.pessoas_maos_usadas);
        int[] pessoas_medias = getResources().getIntArray(R.array.pessoas_media);
        int[] pessoas_bolsistas = getResources().getIntArray(R.array.pessoas_bolsista);
        int[] pessoas_tipos = getResources().getIntArray(R.array.pessoas_tipo);

        pessoas = new ArrayList<>();
        Pessoa pessoa;
        boolean bolsista;
        MaoUsada maoUsada;

        MaoUsada[] maosUsadas = MaoUsada.values();

        for (int cont = 0; cont < pessoas_nomes.length; cont++) {
            bolsista = (pessoas_bolsistas[cont] == 1 ? true : false);
            maoUsada = maosUsadas[pessoas_maos_usadas[cont]];
            pessoa = new Pessoa(pessoas_nomes[cont], pessoas_medias[cont], bolsista, pessoas_tipos[cont], maoUsada);
            pessoas.add(pessoa);
        }
        // terceiro parametro do construtor eh null se nao quiser tratar evento
        pessoaRecyclerViewAdapter = new PessoaRecyclerViewAdapter(this, pessoas, onItemClickListener);

        recyclerViewPessoas.setAdapter(pessoaRecyclerViewAdapter);
    }
}