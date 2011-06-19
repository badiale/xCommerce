package org.xcommerce.beans;

import java.io.*;		    // File and Serializable imports
import java.util.*;		    // Set
import javax.persistence.*;	    // Persistence/Annotations
import org.hibernate.Session;	    // Database session
import org.xcommerce.config.Config;  // Hibernate session handler
import org.xcommerce.db.DBManager;  // Hibernate session handler

/**
 * Class to handle products images in an e-commerce application.
 * @author Fábio Abrão Luca
 */
@Entity
@Table(name="imagens")
public class Imagem implements Serializable, Config {
    public static String imagesFolder = appFolder+"/xCommerce/images/products";
    
    @Id
    @Column(name="id",unique=true,nullable=false)
    @SequenceGenerator(name="seq_id_imagem")
    @GeneratedValue(strategy=GenerationType.AUTO, generator="seq_id_imagem")
    private Long id;
    
    @Column(name = "dataCriacao")
    private String dataCriacao;
    
    @ManyToOne
    @JoinColumn(name = "prodid")
    private Produto produto;
    
    static {
	File filesdir = new File(imagesFolder);
	filesdir.mkdir();
    }
    
    // Constructors
    public Imagem(){
	id = null;
	produto= null;
	dataCriacao = null;
    }
    public Imagem (Produto produto, String data) {
	id = null;
	setProduto(produto);
	setDataCriacao(data);
    }
    
    // Setters
    public void setId (Long id) { this.id = id; }
    public void setProduto (Produto produto) { this.produto = produto; }
    public void setDataCriacao (String data) { this.dataCriacao = data; }
    // Getters
    public Long getId () { return this.id; }
    public Produto getProduto () { return this.produto; }
    public String getDataCriacao () { return this.dataCriacao; }
    
    /**
     * Inserts the object of this class in the database and touches the hard
     * drive for a blank file to write the uploaded image to.
     */
    public void insert () throws IOException {
	Session session = DBManager.getSession();
	// Database transaction to save the object
	session.beginTransaction();
	session.save(this);
	session.getTransaction().commit();
	// Touching hard drive
	File file = new File(imagesFolder+"/"+this.id);
	file.createNewFile(); // May generate IOException
    }
    
    /**
     * Removes the object of this class from the database and the hard drive.
     */
    public void remove () {
	Session session = DBManager.getSession();
	// Removes file from the hard drive
	File file = new File(imagesFolder+"/"+this.id);
	file.delete();
	// Removes entry from the database
	session.beginTransaction();
	session.delete(this);
	session.getTransaction().commit();
    }
    
    /**
     * @param session Hibernate session (<code>DBManager.getSession()</code>).
     * @return All images from the database.
     */
    public static List findAll (Session session) {
	// Query in Hibernate Query Language
	String hql = "from Imagem";
	org.hibernate.Query query = session.createQuery(hql);
	return query.list();
    }
    
    /**
     * @param session Hibernate session (<code>DBManager.getSession()</code>).
     * @param nome Name of the image file.
     * @return Image object with given name.
     */
    public static Imagem findById (Session session, Long id) {
	return (Imagem) session.get("org.xcommerce.beans.Imagem", id);
    }
    
//    /**
//     * Unit test for <code>insert()</code>
//     */
//    private static void UnitTest01 () {
//	Imagem imagem = new Imagem();
//	imagem.setProduto(Produto.find(new Integer(1)));
//	imagem.setDataCriacao("2011/06/17 15:35:00");
//	try {
//	    imagem.insert();
//	    System.out.println("Inserted ID: "+imagem.id);
//	} catch (IOException e) {
//	    System.err.println("ERRO:UnitTest01: "+e);
//	    e.printStackTrace();
//	}
//    }
//    /**
//     * Unit test for <code>remove()</code>
//     */
//    private static void UnitTest02 () {
//	Imagem imagem = new Imagem();
//	imagem.setProduto(Produto.find(new Integer(1)));
//	imagem.setDataCriacao("2011/06/17 15:35:00");
//	try {
//	    imagem.insert();
//	    System.out.println("Inserted ID: "+imagem.id);
//	} catch (IOException e) {
//	    System.err.println("ERRO:UnitTest02: "+e);
//	    e.printStackTrace();
//	}
//        imagem.remove();
//        System.out.println("Removed ID: "+imagem.id);
//    }
//    /**
//     * Unit test for <code>findAll()</code>
//     */
//    private static void UnitTest03 () {
//	Imagem imagem1 = new Imagem(1,"2011/06/17 01:35:00");
//	Imagem imagem2 = new Imagem(1,"2011/06/17 02:35:00");
//	Imagem imagem3 = new Imagem(1,"2011/06/17 03:35:00");
//	Imagem imagem4 = new Imagem(1,"2011/06/17 04:35:00");
//	Imagem imagem5 = new Imagem(1,"2011/06/17 05:35:00");
//	try {
//	    imagem1.insert();
//	    imagem2.insert();
//	    imagem3.insert();
//	    imagem4.insert();
//	    imagem5.insert();
//	} catch (IOException e) {
//	    System.err.println("ERRO:UnitTest03: "+e);
//	    e.printStackTrace();
//	}
//	Session session = DBManager.getSession();
//	session.beginTransaction();
//	List imagens = Imagem.findAll(session);
//	Iterator iterator = imagens.iterator();
//	while(iterator.hasNext()) {
//	    Imagem image = (Imagem) iterator.next();
//	    System.out.print(image.getId()+" ");
//	}
//    }
//    /**
//     * Unit test for <code>findById()</code>
//     */
//    private static void UnitTest04 () {
//	Imagem imagem1 = new Imagem(1,"2011/06/17 01:35:00");
//	try {
//	    imagem1.insert();
//	} catch (IOException e) {
//	    System.err.println("ERRO:UnitTest04: "+e);
//	    e.printStackTrace();
//	}
//	Session session = DBManager.getSession();
//	session.beginTransaction();
//	Imagem image = Imagem.findById(session,new Long(1));
//	System.out.print(image.getId()+" "+image.getProduto().getCodigo()+" "+image.getDataCriacao());
//    }
//    /**
//     * Unit test for <code>findByProduto()</code>
//     */
//    private static void UnitTest05 () {
//	Imagem imagem1 = new Imagem(1,"2011/06/17 01:35:00");
//	Imagem imagem2 = new Imagem(1,"2011/06/17 02:35:00");
//	Imagem imagem3 = new Imagem(1,"2011/06/17 03:35:00");
//	Imagem imagem4 = new Imagem(2,"2011/06/17 04:35:00");
//	Imagem imagem5 = new Imagem(2,"2011/06/17 05:35:00");
//	try {
//	    imagem1.insert();
//	    imagem2.insert();
//	    imagem3.insert();
//	    imagem4.insert();
//	    imagem5.insert();
//	} catch (IOException e) {
//	    System.err.println("ERRO:UnitTest05: "+e);
//	    e.printStackTrace();
//	}
//	Session session = DBManager.getSession();
//	session.beginTransaction();
//	List imagens = Imagem.findByProduto(session,2);
//	Iterator iterator = imagens.iterator();
//	while(iterator.hasNext()) {
//	    Imagem image = (Imagem) iterator.next();
//	    System.out.print(image.getId()+" ");
//	}
//    }
//    
//    public static void main (String args[]) {
//	//UnitTest01(); // insert()
//	//UnitTest02(); // remove()
//	//UnitTest03(); // findAll()
//	//UnitTest04(); // findById()
//	//UnitTest05(); // findByProduto()
//    }
}
