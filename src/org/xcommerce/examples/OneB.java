package org.xcommerce.examples;

import org.xcommerce.db.DBManager;

import org.hibernate.*;
import java.io.Serializable;
import javax.persistence.*;

@Entity
public class OneB implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="id_oneb")
	private Integer id;

	@OneToOne(mappedBy="oneb")
	private OneA onea;

	public OneB() {
		id = null;
		onea = null;
	}

	public Integer getId() { return this.id; }
	public void setA(OneA a) { this.onea = a; }
	public OneA getA() { return this.onea; }
}
