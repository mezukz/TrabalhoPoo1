package trabalho;

/**
 *
 * @author Raul Duarte Martins de Oliveira e Ygor Dreyer
 */

/*
    Classe derivada de Funcionario que guarda os dados dos Atendentes;
*/
public class Atendente extends Funcionario {

    public Atendente() {
        super();
    }

    public Atendente(String nome, String login, String senha) {
        super(nome, login, senha);
    }
    
    public String getNome() {
       return super.getNome();
    }

    public String getLogin() {
        return super.getLogin();
    }

    public String getSenha() {
        return super.getSenha();
    }

}
