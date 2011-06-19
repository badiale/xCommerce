package org.xcommerce.beans;


// Log4J
import org.apache.log4j.Logger;

// coisas do hibernate
import org.xcommerce.db.DBManager;
import org.hibernate.*;
import java.io.Serializable;
import javax.persistence.*;

import java.util.*;

/**
 * Estoque
 * @author Gabriel Perri Gimenes
 * */
@Entity
public class Estoque implements Serializable {

    @Id
    @Column
    private Integer codigo;
    
    @Column
    private Float preco;
	    
    @Column
    private Integer quantidade;
    
    @Column
    private String fornecedor;
    
	/**
	 * Logger que sera usado para esta classe.
	 * */
	static Logger log = Logger.getLogger("org.xcommerce.beans.Estoque");

	/**
	 * Pojo.
	 * Hibernate obriga a criacao de um Pojo.
	 * */
    public Estoque() {
		codigo = null;
		preco = null;
		quantidade = null;
		fornecedor = null;
	}

	/**
	 * Pega o codigo do estoque.
	 * @return o codigo do estoque.
	 * */
    public Integer getCodigo() { return this.codigo; }

	/**
	 * Define um codigo para o estoque.
	 * @param codigo novo codigo do estoque.
	 * */
    public void setCodigo(Integer codigo) { this.codigo = codigo; }
    
  /**
	 * Pega o preco do estoque.
	 * @return o preco do estoque.
	 * */
    public Float getPreco() { return this.preco; }

	/**
	 * Define um preco para o estoque.
	 * @param preco novo preco do estoque.
	 * */
    public void setPreco(Float preco) { this.preco = preco; }
    
  /**
	 * Pega a quantidade do estoque.
	 * @return a quantidade do estoque.
	 * */
    public Integer getQuantidade() { return this.quantidade; }

	/**
	 * Define uma quantidade para o estoque.
	 * @param quantidade nova quantidade do estoque.
	 * */
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }
    
  /**
	 * Pega o fornecedor do estoque.
	 * @return o fornecedor do estoque.
	 * */
    public String getFornecedor() { return this.fornecedor; }

	/**
	 * Define um fornecedor para o estoque.
	 * @param fornecedor novo fornecedor do estoque.
	 * */
    public void setFornecedor(String fornecedor) { this.fornecedor = fornecedor; }
    
	/**
	 * Insere o estoque no banco.
	 * */
	public void insert() {
		Session session = DBManager.getSession();
		session.beginTransaction();
		session.save(this);
		session.getTransaction().commit();
	}

	/**
	 * Remove o estoque do banco.
	 * */
	public void remove() {
		Session session = DBManager.getSession();
		session.beginTransaction();
		session.delete(this);
		session.getTransaction().commit();
	}

	/**
	 * Atualiza o estoque no banco.
	 * */
	public void update() {
		Session session = DBManager.getSession();
		session.beginTransaction();
		session.update(this);
		session.getTransaction().commit();
	}
	
		/**
	 * Pega um estoque, dado um Id.
	 * @param id Id do estoque.
	 * @return Estoque.
	 * */
	public static Estoque find(Integer id) {
		Estoque e = new Estoque();
		Session session = DBManager.getSession();
		session.beginTransaction();
		session.load(e, id);
		session.getTransaction().commit();
		return e;
	}
	
		/**
	 * Retorna todos os estoques.<BR>
	 * <b>Este comando tem que ocorrer dentro de uma transacao.</b>
	 * @return lista com todos os estoques.
	 * */
	public static List findAll() {
		Session session = DBManager.getSession();
		return session.createQuery("SELECT e FROM Estoque e").list();
	}
	
	// testes de unidade
	// testa insert
	private static void teste01 () {
		Estoque e = new Estoque();
	
		e.setCodigo();
		e.setPreco();
		e.setQuantidade();
		e.setFornecedor();
	
		e.insert();

		log.debug("Estoque inserido.");
	}

	// testa find e update
	private static void teste02 () {
		Estoque e = Estoque.find();
		log.debug("Estoque encontrado.");

		p.setPreco();
		p.update();

		log.debug("Estoque atualizado.");
	}

	// testa find all
	private static void teste03() {
		Session session = DBManager.getSession();
		session.beginTransaction();

		List l = Estoque.findAll();
		log.debug("Pegou todos");
		
		Iterator it = l.iterator();
		while (it.hasNext()) {
			Estoque e = (Estoque) it.next();
			log.info("Fornecedor do estoque: " + e.getFornecedor());
		}

		log.debug("Exibiu todos os estoques.");
		
		session.getTransaction().commit();
	}


	// testa remove
	private static void teste04() {
		Estoque e = Estoque.find();
		log.debug("Pegou o estoque");

		e.remove();
		log.info("Estoque removido");
	}
	
	/**
	 * Main para executar os testes de unidade.
	 * */
	public static void main (String args[]) {
		teste01();
		teste02();
		teste03();
		teste04();
	}
}
