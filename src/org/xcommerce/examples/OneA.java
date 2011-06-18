package org.xcommerce.examples;

import org.xcommerce.db.DBManager;

import org.hibernate.*;
import java.io.Serializable;
import javax.persistence.*;

// precisa colocar isto para funcionar
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
public class OneA implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="id_onea")
	private Integer id;

	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name="id_oneb")
	@Cascade(CascadeType.ALL)
	private OneB oneb;
	
	public OneA() {
		id = null;
		oneb = null;
	}

	public Integer getId() { return this.id; }
	public void setB(OneB b) { this.oneb = b; }
	public OneB getB() { return this.oneb; }

	public static void main (String[] args) {
		OneA a = new OneA();
		OneB b = new OneB();

		a.setB(b);
		b.setA(a);

		Session session = DBManager.getSession();
		session.beginTransaction();
		session.save(a);
		session.save(b);
		session.getTransaction().commit();
		
		session = DBManager.getSession();
		session.beginTransaction();
		session.delete(b);
		session.getTransaction().commit();
	}
}
