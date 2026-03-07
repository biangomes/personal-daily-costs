package br.beanascigom.mycosts;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CostsActivity extends AppCompatActivity {

    private EditText editTextDataEHorario;
    private EditText editTextNomeEstabelecimento;
    private EditText editTextValorCompra;
    private RadioGroup radioGroupPagamento;
    private Button buttonSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_costs);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inicializar views
        editTextDataEHorario = findViewById(R.id.editTextDataEHorario);
        editTextNomeEstabelecimento = findViewById(R.id.editTextMultiLineNomeEstabelecimento);
        editTextValorCompra = findViewById(R.id.editTextNumberDecimalValorCompra);
        radioGroupPagamento = findViewById(R.id.radioGroupPagamento);
        buttonSalvar = findViewById(R.id.buttonSalvar);

        // Configurar listener para o botão salvar
        buttonSalvar.setOnClickListener(v -> salvarGasto());
    }

    private void salvarGasto() {
        String data = editTextDataEHorario.getText().toString().trim();
        String estabelecimento = editTextNomeEstabelecimento.getText().toString().trim();
        String valor = editTextValorCompra.getText().toString().trim();
        String formaPagamento = "";

        int selectedId = radioGroupPagamento.getCheckedRadioButtonId();
        if (selectedId != -1) {
            RadioButton radioButton = findViewById(selectedId);
            formaPagamento = radioButton.getText().toString();
        }

        // Validação básica
        if (data.isEmpty() || estabelecimento.isEmpty() || valor.isEmpty() || formaPagamento.isEmpty()) {
            Toast.makeText(this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show();
            return;
        }

        // Aqui você pode salvar os dados (ex: em banco de dados, SharedPreferences, etc.)
        // Por enquanto, apenas exibe um Toast com os dados
        String mensagem = "Gasto salvo:\nData: " + data + "\nEstabelecimento: " + estabelecimento +
                "\nValor: R$ " + valor + "\nPagamento: " + formaPagamento;
        Toast.makeText(this, mensagem, Toast.LENGTH_LONG).show();

        // Limpar campos após salvar (opcional)
        limparCampos();
    }

    private void limparCampos() {
        editTextDataEHorario.setText("");
        editTextNomeEstabelecimento.setText("");
        editTextValorCompra.setText("");
        radioGroupPagamento.clearCheck();
    }
}