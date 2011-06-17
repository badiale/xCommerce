
package org.xcommerce.examples;


// coisas para o hibernate funcionar
import org.xcommerce.db.*;
import org.hibernate.*;
import java.io.Serializable;
import javax.persistence.*;

/**
 * Exemplo de herancas no hibernate.
 * Classe pai.
 * @author Lucas Cuculo Badiale
 * */
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
class Pai implements Serializable {
	@Id
	@Column
	private Integer id;

	@Column
	private String name;

	/**
	 * Pojo.
	 * */
	public Pai() {}
	
	/**
	 * Define um novo id.
	 * @param id Novo id a ser utilizado.
	 * */
	public void setId   (Integer id  ) { this.id   = id  ; }

	/**
	 * Define um novo nome.
	 * @param name Novo nome a ser utilizado.
	 * */
	public void setName (String  name) { this.name = name; }
	
	/**
	 * Retorna o id.
	 * @return Id do objeto.
	 * */
	public Integer getId   () { return this.id  ; }

	/**
	 * Retorna o nome do objeto.
	 * @return Nome do objeto.
	 * */
	public String  getName () { return this.name; }
}

/**
 * Exemplo de herancas no hibernate.
 * Classe filha.
 * @author Lucas Cuculo Badiale
 * */
@Entity
public class Heranca extends Pai {
	@Column
	private String descricao;

	/**
	 * Pojo.
	 * */
	public Heranca() {}

	/**
	 * Define uma descricao para o objeto
	 * @param descricao Nova descricao do objeto.
	 * */
	public void setDescricao (String descricao) { this.descricao = descricao; }

	/**
	 * Retorna a descricao do objeto.
	 * @return Descricao do objeto.
	 * */
	public String getDescricao() { return this.descricao; }

	/**
	 * Executa o exemplo
	 * @param args Nao tem efeito neste exemplo.
	 * */
	public static void main (String args[]) {
		Heranca h = new Heranca();
		h.setId(new Integer(1));
		h.setName("Primeiro");
		h.setDescricao("Descricao da classe");

		Session session = DBManager.getSession();
		session.beginTransaction();
		session.save(h);
		session.getTransaction().commit();
	}
}
