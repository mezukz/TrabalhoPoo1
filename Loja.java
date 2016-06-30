package trabalho;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
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
    Cria a loja ou abre a loja já existente
*/
public class Loja {
    private String nome, endereco, cnpj, faturamento;
    ArrayList<Loja> lojaDeRoupas = new ArrayList();

    public Loja(String nome, String endereco, String cnpj, String faturamento) {
        this.nome = nome;
        this.endereco = endereco;
        this.cnpj = cnpj;
        this.faturamento = faturamento;
    }

    public Loja() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getFaturamento() {
        return faturamento;
    }

    public void setFaturamento(String faturamento) {
        this.faturamento = faturamento;
    }
    
    /*
        Exporta os dados da loja de roupa
    */
    public void exportar(){
        try{ 
            Element li = new Element("list");
        
            for(Loja loja:lojaDeRoupas){
            Element lj = new Element("loja");
            Element nome = new Element("nome");
            Element endereco = new Element("endereco");
            Element cnpj = new Element("cnpj");
            Element faturamento = new Element("faturamento");
        
            nome.setText(loja.getNome());
            endereco.setText(loja.getEndereco());
            cnpj.setText(loja.getCnpj());
            faturamento.setText(loja.getFaturamento());
            
            lj.addContent(nome);
            lj.addContent(endereco);
            lj.addContent(cnpj);
            lj.addContent(faturamento);
            
            li.addContent(lj);
            }
        
        Document doc = new Document();
        doc.setRootElement(li);
        
        XMLOutputter xout = new XMLOutputter();
        FileWriter aquivo = new FileWriter("Loja.xml");
        
        xout.output(doc, aquivo);
    }
    catch (Exception ex){
        System.out.println(ex.getMessage());
    }
    }
    
    /*
        Lê os dados da loja de roupa do arquivo
    */
    
    public void ler(){
        try{
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            org.w3c.dom.Document doc = db.parse("Loja.xml");
            NodeList list = doc.getElementsByTagName("loja");
            Loja lj;
            int totalList = list.getLength();
            for (int i = 0; i < totalList; i++){
                Node n = list.item(i);
                if (n.getNodeType() == Node.ELEMENT_NODE){
                    
                    org.w3c.dom.Element elm = (org.w3c.dom.Element) n;
                    
                    String nome = elm.getElementsByTagName("nome").item(0).getTextContent();
                    String endereco = elm.getElementsByTagName("endereco").item(0).getTextContent();
                    String cnpj = elm.getElementsByTagName("cnpj").item(0).getTextContent();
                    String faturamento = elm.getElementsByTagName("faturamento").item(0).getTextContent();
                    
                    lj = new Loja(nome, endereco, cnpj, faturamento);
                    
                    lojaDeRoupas.add(lj);
                    
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    
    /*
        Adiciona nova loja. metodo usado caso não exista uma loja.
    */
    
    public void adicionar(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Endereço: ");
        String endereco = sc.nextLine();
        System.out.print("cnpj: ");
        String cnpj = sc.nextLine();
        System.out.print("Faturamento: ");
        String faturamento = sc.nextLine();
        Loja lj = new Loja(nome, endereco, cnpj, faturamento);
        
        lojaDeRoupas.add(lj);
    }
    
    /*
        Imprime os dados da loja
    */
    
    public void printLoja(){
        for(Loja lj : lojaDeRoupas){
            System.out.println("Bem vindo a: "+lj.getNome());
            System.out.println("---------------------");
            System.out.println("Dados da loja: ");
            System.out.println("Endereço: "+lj.getEndereco());
            System.out.println("CNPJ: "+lj.getCnpj());
            System.out.println("Faturamento: "+lj.getFaturamento());
            System.out.println("---------------------");
        }
    }
    
    /*
        Confere se existe uma loja, caso exista printa os dados da mesma
        se não existir uma loja é adicionada uma e salva em um arquivo
    */
    
    public void abrirLoja(){
        File f = new File("Loja.xml");
        Loja lj = new Loja();
        if(f.exists()){
            lj.ler();
            lj.printLoja();
        }
        else{
            System.out.println("Ainda não existe uma loja.");
            System.out.println("Insira os dados da nova loja:");
            lj.adicionar();
            lj.exportar();
        }
    }
}
