package br.beanascigom.mycosts;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class PessoasActivity extends AppCompatActivity {

    private ListView listViewPessoas;
    private List<Pessoa> pessoas;
    private PessoaAdapter pessoaAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pessoas);

        listViewPessoas = findViewById(R.id.listViewPessoas);
        listViewPessoas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Pessoa pessoa = (Pessoa) listViewPessoas.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), getString(R.string.pessoa_de_nome) + pessoa.getNome() + getString(R.string.foi_clicada),
                        Toast.LENGTH_LONG).show();
            }
        });   // clicar no elemento da lista

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
        pessoaAdapter = new PessoaAdapter(this, pessoas);
        listViewPessoas.setAdapter(pessoaAdapter);
    }
}