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
 * Produto.
 * Esta classe contem todas as caracteristicas do produto, 
 * como o codigo, o nome, o preco, descricao, categorias e suas caracteristicas.
 * Nao vai ter mais heranca, isto eh, as caracteristicas do produto sao dadas pelos vetores
 * <code>caracteristicas</code> e <code>caracteristicasValor</code>, e a categoria do produto
 * eh dada pelo vetor <code>categorias</code>.
 * @author Lucas Cuculo Badiale
 * */
@Entity
public class Produto implements Serializable {

    @Id
    @Column
	@SequenceGenerator(name = "seq_prodid", sequenceName = "seq_prodid")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_prodid")
    private Integer codigo;

    @Column
    private String nome;
    
	@Column
    private String descricao;
	
	@Column
	private Float preco;

//	@Column
//  private Vector<String> categorias;

	@Column
	private Vector<String> caracteristicas;
	
	@Column
	private Vector<String> caracteristicasValor;
	
	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name="ecodigo")
	private Estoque estoque;

	@OneToMany(cascade = {CascadeType.ALL})
	@JoinColumn(name = "prodid")
	private Set<Imagem> imagens;
	
	/**
	 * Logger que sera usado para esta classe.
	 * */
	static Logger log = Logger.getLogger("org.xcommerce.beans.Produto");

	/**
	 * Pojo.
	 * Hibernate obriga a criacao de um Pojo.
	 * */
    public Produto() {
		codigo = null;
		nome = null;
		descricao = null;
		preco = null;
		//categorias = new Vector<String>();
		caracteristicas = new Vector<String>();
		caracteristicasValor = new Vector<String>();
		imagens = new HashSet<Imagem>();
	}

	/**
	 * Pega o codigo do produto.
	 * @return o codigo do produto.
	 * */
    public Integer getCodigo() { return this.codigo; }

	/**
	 * Define um codigo para o produto.
	 * @param codigo novo codigo do produto.
	 * */
    private void setCodigo(Integer codigo) { this.codigo = codigo; }
	
	/**
	 * Pega o nome do produto.
	 * @return o nome do produto.
	 * */
    public String getNome() { return this.nome; }

	/**
	 * Define um nome para o produto.
	 * @param nome novo nome do produto.
	 * */
    public void setNome(String nome) { this.nome = nome; }
	
	/**
	 * Pega o descricao do produto.
	 * @return o descricao do produto.
	 * */
    public String getDescricao() { return this.descricao; }

	/**
	 * Define uma descricao para o produto.
	 * @param descricao novo descricao do produto.
	 * */
    public void setDescricao(String descricao) { this.descricao = descricao; }

	/**
	 * Pega o preco do produto.
	 * @return preco do produto.
	 * */
	public Float getPreco() { return this.preco; }

	/**
	 * Define o preco do produto.
	 * @param preco novo preco do produto.
	 * */
	public void setPreco (Float preco) { this.preco = preco; }
	
//	/**
//	 * Pega o vetor de categorias.
//	 * @return Vector com as categorias deste produto.
//	 * */
//	public Vector<String> getCategorias() {return this.categorias; }
	
	/**
	 * Pega o vetor de caracteristicas.
	 * @return Vector com as caracteristicas do produto.
	 * */
	public Vector<String> getCaracteristicas() {return this.caracteristicas; }
	
	/**
	 * Pega o vetor com os valores das caracteristicas do produto.
	 * @return Vector com os valores das caracteristicas do produto.
	 * */
	public Vector<String> getCaracteristicasValor() {return this.caracteristicasValor; }

	/**
	 * Pega o conjunto de fotos do produto.
	 * @return conjunto de fotos do produto.
	 * */
	public Set<Imagem> getImagens() { return this.imagens; }
	
	/**
	 * Pega o estoque associado ao produto.
	 * @return estoque.
	 * */
	public Estoque getEstoque() { return this.estoque; }

	// metodos dos beans
	/**
	 * Insere o produto no banco.
	 * */
	public void insert() {
		Session session = DBManager.getSession();
		session.beginTransaction();
		session.save(this);
		session.getTransaction().commit();
	}

	/**
	 * Remove o produto do banco. <br>
	 * <b>Cuidado: </B> Vai remover as imagens e as informacoes de estoque tambem.
	 * */
	public void remove() {
		Session session = DBManager.getSession();
		session.beginTransaction();
		session.delete(this);
		session.getTransaction().commit();
	}

	/**
	 * Atualiza o produto no banco.
	 * */
	public void update() {
		Session session = DBManager.getSession();
		session.beginTransaction();
		session.update(this);
		session.getTransaction().commit();
	}
	
	/**
	 * Pega um produto, dado um ID.
	 * @param id Id do produto.
	 * @return Produto.
	 * */
	public static Produto find(Integer id) {
		Produto p = new Produto();
		Session session = DBManager.getSession();
		session.beginTransaction();
		session.load(p, id);
		session.getTransaction().commit();
		return p;
	}
	
	/**
	 * Retorna todos os produtos, que contenham uma certa string no nome.<BR>
	 * <b>Este comando tem que ocorrer dentro de uma transacao.</b>
	 * @param nome Filtro.
	 * @return lista com todos os produtos que tem aquele nome.
	 * */
	public static List find(String nome) {
		Session session = DBManager.getSession();
		return session.createQuery(
				"SELECT p FROM Produto p WHERE p.nome LIKE :nome"
				).setParameter("nome", "%" + nome + "%").list();
	}

	/**
	 * Retorna todos os produtos.<BR>
	 * <b>Este comando tem que ocorrer dentro de uma transacao.</b>
	 * @return lista com todos os produtos.
	 * */
	public static List findAll() {
		Session session = DBManager.getSession();
		return session.createQuery("SELECT p FROM Produto p").list();
	}
	
	// testes de unidade
	// testa insert
	private static void teste01 () {
		Produto p = new Produto();
		p.setNome("negocio da china");
		p.setDescricao("um otimo negocio esse!");
		p.setPreco (new Float(1.99));

		//p.getCategorias().add("bom");
		//p.getCategorias().add("bonito");
		//p.getCategorias().add("barato");

		p.getCaracteristicas().add("Dimensoes");
		p.getCaracteristicasValor().add("12x19x10 cm");

		p.getCaracteristicas().add("Peso");
		p.getCaracteristicasValor().add("100 g");
		
		Imagem i = new Imagem();
		i.setDataCriacao("Nomezim");
		try { i.insert(); } catch (Exception e) {}

		p.getImagens().add(i);

		p.insert();

		log.debug("Produto inserido.");
	}

	// testa find e remove
	private static void teste02 () {
		Produto p = Produto.find(new Integer(1));
		log.debug("Produto encontrado.");

		p.setNome("teste02");
		p.update();

		log.debug("Produto atualizado.");
	}

	// testa find all
	private static void teste03() {
		Session session = DBManager.getSession();
		session.beginTransaction();

		List l = Produto.findAll();
		log.debug("Pegou todos");
		
		Iterator it = l.iterator();
		while (it.hasNext()) {
			Produto p = (Produto) it.next();
			log.info("Nome do produto: " + p.getNome());

			Iterator ii = p.getImagens().iterator();
			while (ii.hasNext()) {
				Imagem im = (Imagem) ii.next();
				log.info("Nome da imagem: " + im.getDataCriacao());
			}
		}

		log.debug("Exibiu todos os produtos.");
		
		session.getTransaction().commit();
	}

	// testa find pelo nome
	private static void teste04() {
		Session session = DBManager.getSession();
		session.beginTransaction();
		
		log.info("Procurando por nome");
		List l = Produto.find("t");
		log.debug("Pegou todos");
		
		Iterator it = l.iterator();
		while (it.hasNext()) {
			Produto p = (Produto) it.next();
			log.info("Nome do produto: " + p.getNome());
		}
		log.debug("Exibiu todos os produtos.");
		
		session.getTransaction().commit();
	}

	// testa remove
	private static void teste05() {
		Produto p = Produto.find(new Integer(1));
		log.debug("Pegou o produto");

		p.remove();
		log.info("Produto removido");
	}

	/**
	 * Main para executar os testes de unidade.
	 * */
	public static void main (String args[]) {
		Produto.teste01();
		Produto.teste02();
		Produto.teste03();
		Produto.teste04();
		Produto.teste05();
	}
}
