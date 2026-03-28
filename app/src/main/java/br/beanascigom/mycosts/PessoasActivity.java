package br.beanascigom.mycosts;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class PessoasActivity extends AppCompatActivity {

    private ListView listViewPessoas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pessoas);

        listViewPessoas = findViewById(R.id.listViewPessoas);

        popularListaPessoas();
    }

    private void popularListaPessoas() {

    }
}