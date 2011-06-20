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
 * Compra
 * Classe com as caracteristicas de compra, 
 * como o codigo, cliente, e a hora de compra.
 * @author Rodrigo Leonavas
 * */
@Entity
public class Compra implements Serializable {

    @Id
    @Column
	@SequenceGenerator(name = "seq_comprid", sequenceName = "seq_comprid")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_comprid")
    private Integer codigo;

    @ManyToOne
	@JoinColumn(name = "clienteemail")
    private Cliente cliente;
	
	@OneToMany(cascade = {CascadeType.ALL})
	@JoinColumn(name = "compracod")
	private Set<LinhaDeCompra> linhasCompra;

	@Column
	private String horaCompra;
	
	/**
	 * Logger que sera usado para esta classe.
	 * */
	static Logger log = Logger.getLogger("org.xcommerce.beans.Compra");

	/**
	 * Pojo.
	 * Hibernate obriga a criacao de um Pojo.
	 * */
    public Compra() {
		this.linhasCompra = new HashSet<LinhaDeCompra>();
		this.codigo = null;
		this.cliente = null;
		this.horaCompra = null;
	}

	/*public Compra(int codigo, Cliente cliente, String horaCompra) {
		this.codigo = codigo;
		this.cliente = cliente;
		this.horaCompra = horaCompra;
	}*/

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
	 * Retorna um objeto cliente associado a compra
	 * @return objeto Cliente
	 **/
	public Cliente getCliente() { return this.cliente; }

	/** 
	 * Define um objeto cliente associado a compra
	 * @param novo cliente para a compra
	 **/
	public void setCliente(Cliente cliente) { this.cliente = cliente; } 
	
	/**
	 * Pega a data da compra.
	 * @return a data da compra.
	 * */
    public String getHoraCompra() { return this.horaCompra; }

	/**
	 * Define uma data para a compra.
	 * @param data da compra.
	 * */
    public void setHoraCompra(String horaCompra) { this.horaCompra = horaCompra; }

	/**
	 * Pega o conjunto de <code>LinhaDeCompra</code> da compra.
	 * @return conjunto de <code>LinhaDeCompra</code> da compra.
	 * */
	public Set<LinhaDeCompra> getLinhasCompra() { return this.linhasCompra; }

	public void setLinhasCompra(Set set) { this.linhasCompra = set; }
	// metodos dos beans

	/**
	 * Calcula o total da compra.
	 * @return total da compra.
	 * */
	public float total() {
		float total = new Float(0.0);
		Set linhas = this.linhasCompra;		
	
		Iterator it = linhas.iterator();
		
		while (it.hasNext()) {
			LinhaDeCompra lc = (LinhaDeCompra) it.next();
			total = total + lc.subTotal();
		}

		return total;
	}

	/**
	 * Insere a compra no banco.
	 * */
	public void insert() {
		Session session = DBManager.getSession();
		session.beginTransaction();
		session.save(this);
		session.getTransaction().commit();
	}

	/**
	 * Remove a compra do banco.
	 * */
	public void remove() {
		Session session = DBManager.getSession();
		session.beginTransaction();
		session.delete(this);
		session.getTransaction().commit();
	}

	/**
	 * Atualiza a compra no banco.
	 * */
	public void update() {
		Session session = DBManager.getSession();
		session.beginTransaction();
		session.update(this);
		session.getTransaction().commit();
	}
	
	/**
	 * Pega uma compra, dado um ID.
	 * @param id Id da compra.
	 * @return Compra.
	 * */
	public static Compra find(Integer id) {
		Compra c = new Compra();
		Session session = DBManager.getSession();
		session.beginTransaction();
		session.load(c, id);
		session.getTransaction().commit();
		return c;
	}
	
	/**
	 * Retorna todas as compras, que um certo cliente fez.<BR>
	 * @param email Filtro.
	 * @return lista com todas as compras de um dado cliente.
	 * */
	public static List find(String email) {  //TODO FIX consulta
		Session session = DBManager.getSession();
		return session.createQuery(
				"select c from Compra c where c.cliente = :cliente"
			).setParameter("cliente", email).list();
	}

	/**
	 * Retorna todas as compras.<BR>
	 * <b>Este comando tem que ocorrer dentro de uma transacao.</b>
	 * @return lista com todos os produtos.
	 * */
	public static List findAll() {
		Session session = DBManager.getSession();
		return session.createQuery("SELECT c FROM Compra c").list();
	}

	/*public static Compra next(ResultSet rs) throws Exception {
            Compra compra = null;

            if (rs.next()) {
                compra = new Compra(
                    rs.getInteger("codigo"),
                    rs.getString("cliente"),
                    rs.getString("horaCompra")
				);
            }

            return compra;
	}*/
	
	// testes de unidade
	// testa insert
	private static void teste01 () {
		Cliente cliente = new Cliente();
		cliente.setEmail("ze@email.com");
		cliente.insert();

		Compra c = new Compra();
		c.setCliente(cliente);
		c.setHoraCompra("agora!");

		c.insert();

		log.debug("Compra inserida.");
	}

	// testa find e remove
	private static void teste02 () {
		Compra c = Compra.find(new Integer(1));
		log.debug("Compra encontrada.");

		c.setCliente(Cliente.find("ze@email.com"));
		c.update();

		log.debug("Compra atualizada.");
	}

	// testa find all
	private static void teste03() {
		Session session = DBManager.getSession();
		session.beginTransaction();

		List l = Compra.findAll();
		log.debug("Pegou todas!");
		
		Iterator it = l.iterator();
		while (it.hasNext()) {
			Compra c = (Compra) it.next();
			log.info("Nome do cliente: " + c.getCliente().getEmail());
		}

		log.debug("Exibiu todas as compras.");
		
		session.getTransaction().commit();
	}

	private static void teste04() {
		Compra c = new Compra();
		c = Compra.find(new Integer(1));
		c.setLinhasCompra(new HashSet<LinhaDeCompra>());

		LinhaDeCompra lc = new LinhaDeCompra();
		//lc.setCompra(c);
		lc.setQuantidade(new Integer(13));
		lc.setPrecoUnitario(new Float(45.00));
		try { lc.insert(); } 
		catch (Exception e ) { }

		c.getLinhasCompra().add(lc);
		c.insert();

		log.info("Compra encontrada hora: " + c.getHoraCompra());
		log.info("Nome do usuario da compra: " + c.getCliente().getEmail());
	}

	/**
	 * Main para executar os testes de unidade.
	 * */
	public static void main (String args[]) {
		Compra.teste01();
		Compra.teste02();
		Compra.teste03();
		Compra.teste04();
	}
}
