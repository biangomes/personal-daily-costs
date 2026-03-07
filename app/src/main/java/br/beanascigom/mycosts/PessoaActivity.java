package br.beanascigom.mycosts;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PessoaActivity extends AppCompatActivity {

    private EditText editTextNome, editTextMedia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextNome = findViewById(R.id.editTextNome);
        editTextMedia = findViewById(R.id.editTextMedia);
    }

    public void limparCampos(View view) {
        editTextNome.setText(null);
        editTextMedia.setText(null);

        editTextNome.requestFocus();    // força o cursor da entrada de valores volta pro campo nome

        Toast.makeText(this,
                R.string.as_entradas_foram_apagadas,
                Toast.LENGTH_LONG)
                .show();
    }

    public void salvarValores(View view) {
        String nome = editTextNome.getText().toString();
        String mediaString = editTextMedia.getText().toString();

        if (nome == null || nome.isBlank()) {
            Toast.makeText(this,
                    R.string.faltou_entrar_com_o_nome,
                    Toast.LENGTH_LONG)
                    .show();

            editTextNome.requestFocus();
            return;
        }

        if (mediaString == null || mediaString.isBlank()) {
            Toast.makeText(this,
                            R.string.faltou_entrar_com_a_media_de_gastos,
                            Toast.LENGTH_LONG)
                    .show();

            editTextMedia.requestFocus();
            return;
        }

        int media = 0;

        try {
            media = Integer.parseInt(mediaString);
        } catch (NumberFormatException e) {
            Toast.makeText(this,
                    R.string.media_deve_ser_um_numero_inteiro,
                    Toast.LENGTH_LONG)
                    .show();
        }

        if (media < 0 || media > 100) {
            Toast.makeText(this,
                    R.string.media_deve_ser_um_numero_entre_zero_e_cem,
                    Toast.LENGTH_LONG)
                    .show();

            editTextMedia.requestFocus();
            editTextMedia.setSelection(0, editTextMedia.getText().toString().length());
            return;
        }

        Toast.makeText(this,
                getString(R.string.nome_valor) + nome + "\n" +
                getString(R.string.media_valor) + media,
                Toast.LENGTH_LONG)
                .show();
    }
}