package trabalho;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.io.FileWriter;
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
    Contém as operações envolvendo as roupas da loja
*/
public class LojaDeRoupa {
    ArrayList<Roupa> listaDeRoupas = new ArrayList();
    
    /*
        Adiciona nova roupa a lista de roupas da loja
    */
    
    public void adicionar(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Tipo(1-Masculino/2-Feminino/3-Infantil): ");
        int tipo = sc.nextInt();
        Tipo t = Tipo.MASCULINO;
        while(tipo < 1 || tipo > 3){
            System.out.println("Tipo inválido. Digite novamente:");
            tipo = sc.nextInt();
        }
        switch(tipo){
            case 1:
                t = Tipo.MASCULINO;
            break;
            case 2:
                t = Tipo.FEMININO;
            break;
            case 3:
                t = Tipo.INFANTIL;
            break;
        }
        sc.nextLine();
        System.out.print("Tamanho: ");
        String tamanho = sc.nextLine();
        System.out.print("Tecido: ");
        String tecido = sc.nextLine();
        System.out.print("Preço: ");
        double preco = sc.nextDouble();
        sc.nextLine();
        Roupa r = new Roupa(nome, tamanho, tecido, preco, t);
        listaDeRoupas.add(r);
        
    }
    
    /*
        Remove roupa da lista de roupas da loja
        Roupa procurada pelo nome
    */
    
    public void remover(){
        Scanner sc = new Scanner(System.in);
        int i = 0, x = 0;
        System.out.print("Digite o nome da roupa a ser removida: ");
        String procura = sc.nextLine();
        for(Roupa r2 : listaDeRoupas){
            if(r2.getNome() != null && r2.getNome().equals(procura)){
                System.out.println("Removido com sucesso!");
                listaDeRoupas.set(i, r2);
                listaDeRoupas.remove(r2);
            }
            i++;
        }
        if(x==0){
            System.out.println("Roupa não encontrada.");
        }
    }
    
    /*
        Edita uma roupa da lista de roupas da loja
        Roupa procurada pelo nome
    */
    
    public void editar(){
        Scanner sc = new Scanner(System.in);
        int i = 0,x = 0;
        System.out.print("Digite o nome da roupa que procura: ");
        String procura = sc.nextLine();
        for(Roupa r2 : listaDeRoupas){
            if(r2.getNome() != null && r2.getNome().equals(procura)){
                System.out.println("Digite novos dados da roupa: ");
                System.out.print("Nome: ");
                String nome = sc.nextLine();
                System.out.print("Tipo(1-Masculino/2-Feminino/3-Infantil): ");
                int tipo = sc.nextInt();
                Tipo t = Tipo.MASCULINO;
                while(tipo < 1 || tipo > 3){
                    System.out.println("Tipo inválido. Digite novamente:");
                    tipo = sc.nextInt();
                }
                switch(tipo){
                    case 1:
                        t = Tipo.MASCULINO;
                    break;
                    case 2:
                        t = Tipo.FEMININO;
                    break;
                    case 3:
                        t = Tipo.INFANTIL;
                    break;
                }
                sc.nextLine();
                System.out.print("Tamanho: ");
                String tamanho = sc.nextLine();
                System.out.print("Tecido: ");
                String tecido = sc.nextLine();
                System.out.print("Preço: ");
                double preco = sc.nextDouble();
                sc.nextLine();
                Roupa r = new Roupa(nome, tamanho, tecido, preco, t);
                listaDeRoupas.set(i, r);
                x++;
            }
            i++;
        }
        if(x==0){
            System.out.println("Roupa não encontrada.");
        }

    }
    
    /*
        Lista as roupas da loja pelo nome
    */
    
    public void listarPorNome(){        
        Collections.sort(listaDeRoupas, (r,r1) -> r.getNome().compareTo(r1.getNome()));
        for(Roupa r2 : listaDeRoupas){
            System.out.print("Nome: "+r2.getNome()+" ");
            System.out.println("Tipo: "+r2.getTipo()+" ");
            System.out.print("Tamanho: "+r2.getTamanho()+" ");
            System.out.println("Tecido: "+r2.getTecido()+" ");
            System.out.println("Preço: "+r2.getPreco()+" ");
        }
    }
    
    /*
        Exporta a lista de roupas para o arquivo xml
    */
    
    public void exportar(){
        try{ 
        Element li = new Element("list");
        
        for(Roupa r1:listaDeRoupas){
            Element rp = new Element("roupa");
            Element nome = new Element("nome");
            Element tipo = new Element("tipo");
            Element tamanho = new Element("tamanho");
            Element tecido = new Element("tecido");
            Element preco = new Element("preco");
        
            nome.setText(r1.getNome());
            tipo.setText(r1.getTipo().toString());
            tamanho.setText(r1.getTamanho());
            tecido.setText(r1.getTecido());
            preco.setText(String.valueOf(r1.getPreco()));
            
            rp.addContent(nome);
            rp.addContent(tipo);
            rp.addContent(tamanho);
            rp.addContent(tecido);
            rp.addContent(preco);
            
            li.addContent(rp);
            
        }
        
        Document doc = new Document();
        doc.setRootElement(li);
        
        XMLOutputter xout = new XMLOutputter();
        FileWriter aquivo = new FileWriter("ListaDeRoupas.xml");
        
        xout.output(doc, aquivo);
    }
    catch (Exception ex){
        System.out.println(ex.getMessage());
    }
    }
    
    /*
        Lê o arquivo xml com a lista de roupas e remonta a lista
    */
    
    public void ler(){
        try{
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            org.w3c.dom.Document doc = db.parse("ListaDeRoupas.xml");
            NodeList list = doc.getElementsByTagName("roupa");
            Roupa r;
            int totalList = list.getLength();
            for (int i = 0; i < totalList; i++){
                Node n = list.item(i);
                if (n.getNodeType() == Node.ELEMENT_NODE){
                    
                    org.w3c.dom.Element elm = (org.w3c.dom.Element) n;
                    
                    String nome = elm.getElementsByTagName("nome").item(0).getTextContent();
                    String tipo = elm.getElementsByTagName("tipo").item(0).getTextContent();
                    String tamanho = elm.getElementsByTagName("tamanho").item(0).getTextContent();
                    String tecido = elm.getElementsByTagName("tecido").item(0).getTextContent();
                    double preco = Double.parseDouble(elm.getElementsByTagName("preco").item(0).getTextContent());
                    
                    r = new Roupa(nome, tamanho, tecido, preco, Tipo.valueOf(tipo));
                    
                    listaDeRoupas.add(r);
                    
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    
}
