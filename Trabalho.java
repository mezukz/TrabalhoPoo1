package trabalho;

import java.io.File;
import java.util.Scanner;

/**
 *
 * @author Raul Duarte Martins de Oliveira e Ygor Dreyer
 */
public class Trabalho {

    public static void main(String[] args) {
        File f = new File("ListaDeRoupas.xml");
        Empregados empregados = new Empregados();
        LojaDeRoupa lista = new LojaDeRoupa();
        Loja loja = new Loja();
        
        // Confere a existencia da loja, se não existir pede para ser criada uma loja
        
        loja.abrirLoja();
        
        // Confere a existencia de cadastro de empregados se não existir pede o
        // cadastro de um Gerente e um Atendente
        
        empregados.inicializar(); 
        empregados.lerAtendente();
        empregados.lerGerente();
        
        // Se existir uma lista de roupas ela é importada também
        
        if(f.exists()){
            lista.ler();
        }
        
        // Autenticação de Funcionário e menu de opções
        
        int sair = 0;
        int voltar = 0;
            do{
            voltar = 0;
        int op = empregados.autenticar();
        int op2;
        Scanner sc;
        while(op==0){
            System.out.println("Login ou senha incorreto.");
            op = empregados.autenticar();
        }
        while(voltar == 0){
            switch(op){
                case 1:
                    System.out.println("\n------------------"
                            + "\nMENU:\n"
                            + "1-Adicionar roupas\n"
                            + "2-Remover roupas\n"
                            + "3-Editar roupas\n"
                            + "4-Listar roupas\n"
                            + "5-Listar atendentes\n"
                            + "6-Exportar lista de roupas\n"
                            + "7-Ler lista de roupas\n"
                            + "8-Adicionar Atendente\n"
                            + "9-Remover Atendente\n"
                            + "10-Editar Atendente\n"
                            + "11-Adicionar Gerente\n"
                            + "12-Remover Gerente\n"
                            + "13-Editar Gerente\n"
                            + "14-Exportar listas de Funcionários\n"
                            + "15-Ler listas de Funcionários\n"
                            + "16-Voltar para login\n"
                            + "17-Sair\n"
                            + "------------------");
                    sc = new Scanner(System.in);
                    op2 = sc.nextInt();
                    sc.nextLine();
                    switch(op2){
                        case 1:
                            lista.adicionar();
                        break;
                        case 2:
                            lista.remover();
                        break;
                        case 3:
                            lista.editar();
                        break;
                        case 4:
                            lista.listarPorNome();
                        break;
                        case 5:
                            empregados.listarTodos();
                        break;
                        case 6:
                            lista.exportar();
                        break;
                        case 7:
                            lista.ler();
                        break;
                        case 8:
                            empregados.adicionar();
                        break;
                        case 9:
                            empregados.remover();
                        break;
                        case 10:
                            empregados.editar();
                        break;
                        case 11:
                            empregados.adicionarGerente();
                        break;
                        case 12:
                            empregados.removerGerente();
                        break;
                        case 13:
                            empregados.editarGerente();
                        break;
                        case 14:
                            empregados.exportarAtendente();
                            empregados.exportarGerente();
                        break;
                        case 15:
                            empregados.lerAtendente();
                            empregados.lerGerente();
                        break;
                        case 16:
                            voltar = 1;
                        break;
                        case 17:
                            sair = 1;
                            voltar = 1;
                        break;
                        default:
                            System.out.println("Opção inválida.");
                        break;
                    }
                break;
                case 2:
                    System.out.println("\n------------------"
                            + "\nMENU:\n"
                            + "1-Listar roupas\n"
                            + "2-Exportar lista de roupas\n"
                            + "3-Ler lista de roupas\n"
                            + "4-Voltar para login\n"
                            + "5-Sair\n"
                            + "------------------");
                    sc = new Scanner(System.in);
                    op2 = sc.nextInt();
                    sc.nextLine();
                    switch(op2){
                        case 1:
                            lista.listarPorNome();
                        break;
                        case 2:
                            lista.exportar();
                        break;
                        case 3:
                            lista.ler();
                        break;
                        case 4:
                            voltar = 1;
                        break;
                        case 5:
                            sair = 1;
                            voltar = 1;
                        break;
                    }
                break;
            }
        }
    }while(sair == 0);
    }
    
}
