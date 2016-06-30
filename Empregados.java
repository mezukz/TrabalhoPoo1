package trabalho;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Raul Duarte Martins de Oliveira e Ygor Dreyer
 */

/*
    Cria as listas de gerentes e atendentes e trabalha com os
    Funcionários em geral
*/

public class Empregados {
    ArrayList<Gerente> listaGerente = new ArrayList();
    ArrayList<Atendente> listaAtendentes = new ArrayList();
    
    /*
        Adiciona novo Atendente
    */
    public void adicionar(){
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Digite os dados do novo Atendente: ");
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Login: ");
        String login = sc.nextLine();
        System.out.print("Senha: ");
        String senha = sc.nextLine();
        Atendente f = new Atendente(nome, login, senha);
        listaAtendentes.add(f);
    }
    
    /*
        Adiciona novo Gerente
    */
    public void adicionarGerente(){
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Digite os dados do novo Gerente: ");
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Login: ");
        String login = sc.nextLine();
        System.out.print("Senha: ");
        String senha = sc.nextLine();
        Gerente g = new Gerente(nome, login, senha);
        listaGerente.add(g);
    }
    
    /*
        Faz a autenticação de login para loja
    */
    public int autenticar(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Login: ");
        String login = sc.nextLine();
        System.out.print("Senha: ");
        String senha = sc.nextLine();
        for(Gerente g : listaGerente){
            if(g.getLogin().equals(login)){
                if(g.getSenha().equals(senha)){
                    System.out.print("Seja bem vindo "+g.getNome()+" *GERENTE*");
                    return 1;
                }
            }
        }
        for(Atendente a : listaAtendentes){
            if(a.getLogin().equals(login)){
                if(a.getSenha().equals(senha)){
                    System.out.print("Seja bem vindo "+a.getNome()+" *ATENDENTE*");
                    return 2;
                }
            }
        }
        return 0;
    }
    
    /*
        Editar cadastro de Atendente
    */
    
    public void editar(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite o nome do atendente que procura: ");
        String procura = sc.nextLine();
        int i = 0, x = 0;
        for(Atendente f1 : listaAtendentes){
            System.out.println(f1.getNome());
            if(f1.getNome().equals(procura)){
                System.out.println("Digite os novos dados do Atendente: ");
                System.out.print("Nome: ");
                String nome = sc.nextLine();
                System.out.print("Login: ");
                String login = sc.nextLine();
                System.out.print("Senha: ");
                String senha = sc.nextLine();
                Atendente f = new Atendente(nome, login, senha);
                listaAtendentes.set(i, f);
                x++;
            }
            i++;
        }
        if(x==0){
            System.out.println("Atendente não encontrado.");
        }
    }
    
    public void editarGerente(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite o nome do gerente que procura: ");
        String procura = sc.nextLine();
        int i = 0, x = 0;
        for(Gerente g1 : listaGerente){
            System.out.println(g1.getNome());
            if(g1.getNome().equals(procura)){
                System.out.println("Digite os novos dados do Gerente: ");
                System.out.print("Nome: ");
                String nome = sc.nextLine();
                System.out.print("Login: ");
                String login = sc.nextLine();
                System.out.print("Senha: ");
                String senha = sc.nextLine();
                Gerente g = new Gerente(nome, login, senha);
                listaGerente.set(i, g);
                x++;
            }
            i++;
        }
        if(x==0){
            System.out.println("Gerente não encontrado.");
        }
    }
    
    /*
        Remover cadastro de Atendente
    */
    
    public void remover(){
        Atendente f2 = new Atendente();
        Scanner sc = new Scanner(System.in);
        int i = 0,x = 0;
        System.out.print("Digite o nome do atendente a ser removido: ");
        String procura = sc.nextLine();
        for(Atendente f1 : listaAtendentes){
            if(f1.getNome() != null && f1.getNome().equals(procura)){
                System.out.println("Atendente removido com sucesso!");
                listaAtendentes.set(i, f2);
                listaAtendentes.remove(f2);
            }
            i++;
        }
        if(x==0){
            System.out.println("Atendente não encontrado.");
        }
    }
    
    public void removerGerente(){
        Gerente g2 = new Gerente();
        Scanner sc = new Scanner(System.in);
        int i = 0,x = 0;
        if(listaGerente.size() > 2){
            System.out.print("Digite o nome do gerente a ser removido: ");
            String procura = sc.nextLine();
            for(Gerente g1 : listaGerente){
                if(g1.getNome() != null && g1.getNome().equals(procura)){
                    System.out.println("Gerente removido com sucesso!");
                    listaGerente.set(i, g2);
                    listaGerente.remove(g2);
                }
                i++;
            }
            if(x==0){
                System.out.println("Gerente não encontrado.");
            }
        }
        else{
            System.out.println("O único gerente não pode ser removido.");
        }
    }
    
    /*
        Cria o arquivo de gerente com base na listaDeGerente
    */
    
    public void exportarGerente(){
        try{ 
        Element li = new Element("list");
        
                for(Gerente g : listaGerente){
                Element ge = new Element("gerente");
                Element nome = new Element("nome");
                Element login = new Element("login");
                Element senha = new Element("senha");

                nome.setText(g.getNome());
                login.setText(g.getLogin());
                senha.setText(g.getSenha());

                ge.addContent(nome);
                ge.addContent(login);
                ge.addContent(senha);
                
                li.addContent(ge);
                }
                Document doc = new Document();
                doc.setRootElement(li);

                XMLOutputter xout = new XMLOutputter();
                FileWriter aquivo = new FileWriter("Gerente.xml");

                xout.output(doc, aquivo);
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        }
    
    /*
        Cria o arquivo de Atendentes com base na lista de Atendentes
    */
              
     public void exportarAtendente(){
        try{ 
        Element li = new Element("list");
                for(Atendente f : listaAtendentes){
                Element at = new Element("atendente");
                Element nome = new Element("nome");
                Element login = new Element("login");
                Element senha = new Element("senha");

                nome.setText(f.getNome());
                login.setText(f.getLogin());
                senha.setText(f.getSenha());

                at.addContent(nome);
                at.addContent(login);
                at.addContent(senha);
                
                li.addContent(at);
            }
        Document doc = new Document();
        doc.setRootElement(li);
        
        XMLOutputter xout = new XMLOutputter();
        FileWriter aquivo = new FileWriter("Atendente.xml");
        
        xout.output(doc, aquivo);
    }
    catch (Exception ex){
        System.out.println(ex.getMessage());
    }
    }
     
     /*
        Le o arquivo de Gerentes e guarda os registros dele na lista de gerentes
     */
     
    
    public void lerGerente(){
        try{
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            org.w3c.dom.Document doc = db.parse("Gerente.xml");
            NodeList list = doc.getElementsByTagName("gerente");
            int totalList = list.getLength();
            Gerente g;
            for (int i = 0; i < totalList; i++){
                Node n = list.item(i);
                if (n.getNodeType() == Node.ELEMENT_NODE){
                    
                    org.w3c.dom.Element elm = (org.w3c.dom.Element) n;
                    
                    String nome = elm.getElementsByTagName("nome").item(0).getTextContent();
                    String login = elm.getElementsByTagName("login").item(0).getTextContent();
                    String senha = elm.getElementsByTagName("senha").item(0).getTextContent();
                    
                    g = new Gerente(nome, login, senha);
                    
                    listaGerente.add(g);
                    
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    
    /*
        Le o arquivo de atendentes e guarda os registros
        deles na lista de atendentes
    */
    
    public void lerAtendente(){
        try{
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            org.w3c.dom.Document doc = db.parse("Atendente.xml");
            NodeList list = doc.getElementsByTagName("atendente");
            int totalList = list.getLength();
            Atendente f;
            for (int i = 0; i < totalList; i++){
                Node n = list.item(i);
                if (n.getNodeType() == Node.ELEMENT_NODE){
                    
                    org.w3c.dom.Element elm = (org.w3c.dom.Element) n;
                    
                    String nome = elm.getElementsByTagName("nome").item(0).getTextContent();
                    String login = elm.getElementsByTagName("login").item(0).getTextContent();
                    String senha = elm.getElementsByTagName("senha").item(0).getTextContent();
                    
                    f = new Atendente(nome, login, senha);
                    
                    listaAtendentes.add(f);
                    
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    
    /*
        Lista todos os atendententes
    */
    
    public void listarTodos(){
        int i = 1;
        for(Atendente f : listaAtendentes){
            System.out.println("Atendente "+i+": "+f.getNome());
            i++;
        }   
    }
    
    /*
        Confere a existencia de uma lista de Atendentes e Gerentes.
    */
    
    public void inicializar(){
        File f = new File("Gerente.xml");
        File f1 = new File("Atendente.xml");
        Empregados emp = new Empregados();
        if(f.exists() && f1.exists()){

        }
        else{
            System.out.println("Insira os dados do primeiro Gerente:");
            emp.adicionarGerente();
            System.out.println("Insira os dados do primeiro Atendente:");
            emp.adicionar();
            emp.exportarGerente();
            emp.exportarAtendente();
        }
        
    }
}
