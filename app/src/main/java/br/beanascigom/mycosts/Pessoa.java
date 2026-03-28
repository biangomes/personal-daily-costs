package br.beanascigom.mycosts;

public class Pessoa {
    private String nome;
    private int media;
    private boolean bolsista;
    private int tipo;
    private MaoUsada maoUsada;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getMedia() {
        return media;
    }

    public void setMedia(int media) {
        this.media = media;
    }

    public boolean isBolsista() {
        return bolsista;
    }

    public void setBolsista(boolean bolsista) {
        this.bolsista = bolsista;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public MaoUsada getMaoUsada() {
        return maoUsada;
    }

    public void setMaoUsada(MaoUsada maoUsada) {
        this.maoUsada = maoUsada;
    }
}
