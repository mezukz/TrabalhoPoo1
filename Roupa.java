package trabalho;

/**
 *
 * @author Raul Duarte Martins de Oliveira e Ygor Dreyer
 */

/*
    Classe que contém as características das roupas
*/
public class Roupa {
    private String nome, tamanho, tecido;
    private double preco;
    private Tipo tipo;

    public Roupa() {
    }
    
    public Roupa(String nome, String tamanho, String tecido, double preco, Tipo tipo) {
        this.nome = nome;
        this.tamanho = tamanho;
        this.tecido = tecido;
        this.preco = preco;
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public String getTamanho() {
        return tamanho;
    }

    public String getTecido() {
        return tecido;
    }

    public double getPreco() {
        return preco;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public void setTecido(String tecido) {
        this.tecido = tecido;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }
    
    
}
