package br.beanascigom.mycosts;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
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
    private Spinner spinner;

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
        spinner = findViewById(R.id.spinner);

        // Configurar listener para o botão salvar
        buttonSalvar.setOnClickListener(v -> salvarGasto());

        // Configurar o adapter para o spinner de parcelas
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.parcelas_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        // Inicialmente, esconder o spinner
        spinner.setVisibility(View.GONE);

        // Configurar listener para o RadioGroup para mostrar/esconder o spinner
        radioGroupPagamento.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.radioButtonCredito) {
                spinner.setVisibility(View.VISIBLE);
            } else {
                spinner.setVisibility(View.GONE);
            }
        });
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

        // Obter a quantidade de parcelas selecionadas, se aplicável
        String parcelas = "";
        if (selectedId == R.id.radioButtonCredito) {
            parcelas = spinner.getSelectedItem().toString();
        }

        // Validação básica
        if (data.isEmpty() || estabelecimento.isEmpty() || valor.isEmpty() || formaPagamento.isEmpty()) {
            Toast.makeText(this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show();
            return;
        }

        // Aqui você pode salvar os dados (ex: em banco de dados, SharedPreferences, etc.)
        // Por enquanto, apenas exibe um Toast com os dados
        String mensagem = "Gasto salvo:\nData: " + data + "\nEstabelecimento: " + estabelecimento +
                "\nValor: R$ " + valor + "\nPagamento: " + formaPagamento + "\nParcelas: " + parcelas;
        Toast.makeText(this, mensagem, Toast.LENGTH_LONG).show();

        // Limpar campos após salvar (opcional)
        limparCampos();
    }

    private void limparCampos() {
        editTextDataEHorario.setText("");
        editTextNomeEstabelecimento.setText("");
        editTextValorCompra.setText("");
        radioGroupPagamento.clearCheck();
        spinner.setSelection(0);
        spinner.setVisibility(View.GONE);
    }
}