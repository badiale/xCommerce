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

	@Column
    private Vector<String> categorias;

	@Column
	private Vector<String> caracteristicas;
	
	@Column
	private Vector<String> caracteristicasValor;
	
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
		categorias = new Vector<String>();
		caracteristicas = new Vector<String>();
		caracteristicasValor = new Vector<String>();
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
	
	/**
	 * Pega o vetor de categorias.
	 * @return Vector com as categorias deste produto.
	 * */
	public Vector<String> getCategorias() {return this.categorias; }
	
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

	// TODO
	// remove
	// update
	// finders
	// next
	
	// testes de unidade
	private static void teste01 () {
		Produto p = new Produto();
		p.setNome("negocio da china");
		p.setDescricao("um otimo negocio esse!");
		p.setPreco (new Float(1.99));

		p.getCategorias().add("bom");
		p.getCategorias().add("bonito");
		p.getCategorias().add("barato");

		p.getCaracteristicas().add("Dimensoes");
		p.getCaracteristicasValor().add("12x19x10 cm");

		p.getCaracteristicas().add("Peso");
		p.getCaracteristicasValor().add("100 g");

		p.insert();

		log.info("Produto inserido.");
	}

	/**
	 * Main para executar os testes de unidade.
	 * @param args Numero do teste a ser executado (numero entre 1 e 5).
	 * */
	public static void main (String args[]) {
		// TODO trocar os println pelo log4j

		int teste;
		if (args.length != 1) {
			log.error("Numero incorreto de argumentos.");
			return;
		}
		
		try {
			teste = Integer.parseInt(args[0]);
		} catch (Exception e) {
			log.error("O valor fornecido nao eh um numero");
			return;
		}

		switch (teste) {
			case 1: Produto.teste01(); break;
			case 2: break;
			case 3: break;
			case 4: break;
			case 5: break;
			default: log.error("Teste de unidade invalido.");
		}
	}
}
