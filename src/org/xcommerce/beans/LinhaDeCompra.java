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
 * LinhaDeCompra
 * Classe as instancias de produtos de cada compra
 * @author Rodrigo Leonavas
 * */
@Entity
public class LinhaDeCompra implements Serializable {

    @Id
    @Column
	@SequenceGenerator(name = "seq_lcompid", sequenceName = "seq_lcompid")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_lcompid")
    private Integer codigo;

    @ManyToOne
	@JoinColumn(name = "compracod")
    private Compra compra;
	
	@ManyToOne
	@JoinColumn(name = "prodcod")
    private Produto produto;

	@Column
	private Integer quantidade;

	@Column
	private Float precoUnitario;
	
	/**
	 * Logger que sera usado para esta classe.
	 * */
	static Logger log = Logger.getLogger("org.xcommerce.beans.LinhaDeCompra");

	/**
	 * Pojo.
	 * Hibernate obriga a criacao de um Pojo.
	 * */
    public LinhaDeCompra() {
		codigo = null;
		compra = null;
		produto = null;
		quantidade = new Integer(0);
		precoUnitario = new Float(0.0);
	}

	public LinhaDeCompra(int codigo, Compra compra, Produto produto, int quantidade, float precoUnitario) {
		this.codigo = codigo;
		this.compra = compra;
		this.produto = produto;
		this.quantidade = quantidade;
		this.precoUnitario = precoUnitario;
	}

	/**
	 * Pega o codigo da compra.
	 * @return o codigo da compra.
	 * */
    public Integer getCodigo() { return this.codigo; }

	/**
	 * Define um codigo para a compra.
	 * @param codigo novo codigo a compra.
	 * */
    public void setCodigo(Integer codigo) { this.codigo = codigo; }
	
	/** 
	 * Retorna um objeto compra associado a <code>LinhaDeCompra</code>
	 * @return objeto Compra
	 **/
	public Compra getCompra() { return this.compra; }

	/** 
	 * Define um objeto compra associado a <code>LinhaDeCompra</code>
	 * @param nova compra para a <code>LinhaDeCompra</code>
	 **/
	public void setCompra(Compra compra) { this.compra = compra; } 
	
	/** 
	 * Retorna um objeto produto associado a <code>LinhaDeCompra</code>
	 * @return objeto Produto	
	 **/
	public Produto getProduto() { return this.produto; }

	/** 
	 * Define um objeto produto associado a <code>LinhaDeCompra</code>
	 * @param novo produto para a <code>LinhaDeCompra</code>
	 **/
	public void setProduto(Produto produto) { this.produto = produto; }

	/**
	 * Pega a quantidade de produtos na compra.
	 * @return quantidade de podutos na compra.
	 * */
    public Integer getQuantidade() { return this.quantidade; }

	/**
	 * Define uma quantidade de itens para a compra.
	 * @param quantidade de itens.
	 * */
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }

	/**
	 * Pega o preco unitario do produto da compra.
	 * @return preco unitario do produto.
	 * */
    public Float getPrecoUnitario() { return this.precoUnitario; }

	/**
	 * Define o preco do item da compra.
	 * @param preco unitario.
	 * */
    public void setPrecoUnitario(Float precoUnitario) { this.precoUnitario = precoUnitario; }

	// metodos dos beans

	/**
	 * Retorna o subTotal de uma linha de compra.
	 * @return <code>subTotal</code>.
	 * */
	public float subTotal() { return (float) (this.quantidade * this.precoUnitario); }

	/**
	 * Insere a LinhaDeCompra no banco.
	 * */
	public void insert() {
		Session session = DBManager.getSession();
		session.beginTransaction();
		session.save(this);
		session.getTransaction().commit();
	}

	/**
	 * Remove a LinhaDeCompra do banco.
	 * */
	public void remove() {
		Session session = DBManager.getSession();
		session.beginTransaction();
		session.delete(this);
		session.getTransaction().commit();
	}

	/**
	 * Atualiza a LinhaDeCompra no banco.
	 * */
	public void update() {
		Session session = DBManager.getSession();
		session.beginTransaction();
		session.update(this);
		session.getTransaction().commit();
	}
	
	/**
	 * Pega uma <code>LinhaDeCompra</code>, dado um ID.
	 * @param id Id da compra.
	 * @return Compra.
	 * */
	public static LinhaDeCompra find(Integer id) {
		LinhaDeCompra lc = new LinhaDeCompra();
		Session session = DBManager.getSession();
		session.beginTransaction();
		session.load(lc, id);
		session.getTransaction().commit();
		return lc;
	}
	
	/**
	 * Retorna todas as linhas de compra de uma determinada compra.
	 * @param id da compra.
	 * @return lista com todos os produtos de uma compra.
	 * */
	public static List findByCompra(int compraId) {
		Session session = DBManager.getSession();
		return session.createQuery(
				"select lc from LinhaDeCompra lc where lc.compra = :compra"
			).setParameter("compra", compraId).list();
	}

	/**
	 * Retorna todas as linhas de compra de um determinado produto.
	 * @param id do produto.
	 * @return lista com todas as <code>LinhaDeCompra</code> associadas a um produto.
	 * */
	public static List findByProduto(int prodId) {
		Session session = DBManager.getSession();
		return session.createQuery(
				"select lc from LinhaDeCompra lc where lc.produto = :produto"
			).setParameter("produto", prodId).list();
	}

	/**
	 * Retorna todas as <code>LinhaDeCompra</code>.<BR>
	 * <b>Este comando tem que ocorrer dentro de uma transacao.</b>
	 * @return lista com todos os produtos de uma dada compra.
	 * */
	public static List findAll() {
		Session session = DBManager.getSession();
		return session.createQuery("SELECT lc FROM LinhaDeCompra lc").list();
	}
	
	// testes de unidade
	// testa insert
	private static void teste01 () {
		Cliente cliente = new Cliente();
		cliente.setEmail("ze@email.com");
		cliente.insert();

		Produto prod = new Produto();
		prod.setNome("produto 01");
		prod.setDescricao("Muito bom!");
		prod.setPreco(new Float(25.99));
		prod.insert();

		Produto prod2 = new Produto();
		prod2.setNome("produto 02");
		prod2.setDescricao("Nao tao bom...");
		prod2.setPreco(new Float(7.99));
		prod2.insert();

		Compra c = new Compra();
		c.setCliente(cliente);
		c.setHoraCompra("agora!");
		c.insert();

		LinhaDeCompra lc = new LinhaDeCompra();
		lc.setCompra(c);
		lc.setProduto(prod);
		lc.setQuantidade(new Integer(3));
		lc.setPrecoUnitario(new Float(7.99));
		lc.insert();

		log.debug("LinhaDeCompra inserida.");
	}

	// testa find e remove
	private static void teste02 () {
		LinhaDeCompra lc = LinhaDeCompra.find(new Integer(1));
		log.debug("LinhaDeCompra encontrada.");

		lc.setProduto(Produto.find(new Integer(2)));
		lc.update();

		log.debug("LinhaDeCompra atualizada.");

		log.info("Descricao do novo produto: " + lc.getProduto().getDescricao());
	}

	// testa find all
	private static void teste03() {
		Session session = DBManager.getSession();
		session.beginTransaction();

		List l = LinhaDeCompra.findAll();
		log.debug("Pegou todas!");
		
		Iterator it = l.iterator();
		while (it.hasNext()) {
			LinhaDeCompra lc = (LinhaDeCompra) it.next();
			log.info("Descricao do produto: " + lc.getProduto().getDescricao());
		}

		log.debug("Exibiu todas as descricao das linhas de compra compra.");
		
		session.getTransaction().commit();
	} 

	/**
	 * Main para executar os testes de unidade.
	 * */
	public static void main (String args[]) {
		LinhaDeCompra.teste01();
		LinhaDeCompra.teste02();
		LinhaDeCompra.teste03();
	}
}
