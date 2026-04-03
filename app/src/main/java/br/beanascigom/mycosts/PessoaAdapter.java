package br.beanascigom.mycosts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class PessoaAdapter extends BaseAdapter {

    private String[] tipos;
    private Context context;
    private List<Pessoa> pessoas;

    public static class PessoaHolder {
        public TextView textViewValorNome;
        public TextView textViewValorMedia;
        public TextView textViewValorBolsista;
        public TextView textViewValorTipo;
        public TextView textViewValorMaoUsada;

    }

    public PessoaAdapter(Context context, List<Pessoa> pessoas) {
        this.context = context;
        this.pessoas = pessoas;
        tipos = context.getResources().getStringArray(R.array.tipos);
    }

    @Override
    public int getCount() {
        return pessoas.size();
    }

    @Override
    public Object getItem(int position) {
        return pessoas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PessoaHolder holder;

        if (convertView == null) {
            LayoutInflater inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.linha_lista_pessoas, parent, false);

            holder = new PessoaHolder();
            holder.textViewValorNome = convertView.findViewById(R.id.textViewValorNome);
            holder.textViewValorMedia = convertView.findViewById(R.id.textViewValorMedia);
            holder.textViewValorBolsista = convertView.findViewById(R.id.textViewValorBolsista);
            holder.textViewValorTipo = convertView.findViewById(R.id.textViewValorTipo);
            holder.textViewValorMaoUsada = convertView.findViewById(R.id.textViewMaoUsada);

            convertView.setTag(holder);
        } else {
            holder = (PessoaHolder) convertView.getTag();
        }

        Pessoa pessoa = pessoas.get(position);
        holder.textViewValorNome.setText(pessoa.getNome());
        holder.textViewValorMedia.setText(String.valueOf(pessoa.getMedia()));

        if (pessoa.isBolsista()) {
            holder.textViewValorBolsista.setText(R.string.possui_bolsa);
        } else {
            holder.textViewValorBolsista.setText(R.string.nao_possui_bolsa);
        }

        holder.textViewValorTipo.setText(tipos[pessoa.getTipo()]);

        holder.textViewValorMaoUsada.setText(pessoa.getMaoUsada().toString());
        switch (pessoa.getMaoUsada()) {
            case Direita:
                holder.textViewValorMaoUsada.setText(R.string.direita);
                break;
            case Esquerda:
                holder.textViewValorMaoUsada.setText(R.string.esquerda);
                break;
            case Ambas:
                holder.textViewValorMaoUsada.setText(R.string.ambas);
                break;
        }

        return convertView;
    }


}
