package trabalho;

/**
 *
 * @author Raul
 */

/*
    Classe derivada de Funcionario que guarda os dados do(s) gerente.
*/
public class Gerente extends Funcionario{

    public Gerente() {
        super();
    }

    public Gerente(String nome, String login, String senha) {
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
