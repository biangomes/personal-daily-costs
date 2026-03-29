package br.beanascigom.mycosts;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class PessoasActivity extends AppCompatActivity {

    private ListView listViewPessoas;
    private List<Pessoa> pessoas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pessoas);

        listViewPessoas = findViewById(R.id.listViewPessoas);

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

        ArrayAdapter<Pessoa> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, pessoas);

        listViewPessoas.setAdapter(adapter);
    }
}