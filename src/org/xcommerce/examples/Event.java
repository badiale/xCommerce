package org.xcommerce.examples;

import org.xcommerce.db.DBManager;

// coisas do hibernate
import org.hibernate.*;

// Date e essas coisas
import java.util.*;

// sempre devemos implementar o Serializable
import java.io.Serializable;

// eh necessario para usar as annotations
import javax.persistence.*;

/**
 * Example on how to use Hibernate with annotations.
 * @author Rodrigo Mello
 * */
@Entity
@Table(name = "event")
public class Event implements Serializable {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "date")
    private Date date;

	/**
	 * Pojo.
	 * Hibernate obriga a criacao de um Pojo.
	 * */
    public Event() {}

	/**
	 * Pega o id do evento.
	 * @return o id do evento.
	 * */
    public Integer getId() { return id; }

	/**
	 * Define um id para o evento
	 * @param id novo id do evento
	 * */
    public void setId(Integer id) { this.id = id; }

	/**
	 * Pega a data do evento.
	 * @return retorna a data do evento.
	 * */
    public Date getDate() { return date; }

	/**
	 * Define a data do evento
	 * @param date nova data para o evento
	 * */
    public void setDate(Date date) { this.date = date; }

	/**
	 * Pega o titulo do evento.
	 * @return titulo do evento e retornado.
	 * */
    public String getTitle() { return title; }

	/**
	 * Define o titulo do evento.
	 * @param title novo titulo para o evento.
	 * */
    public void setTitle(String title) { this.title = title; }

	/**
	 * Simple test
	 * @param args An vector of arguments. (Not used on this example)
	 * */
	public static void main (String args[]) {
		Event e = new Event();
		e.setId(new Integer(1));
		e.setDate(new Date());
		e.setTitle("Titulo");

		Session session = DBManager.getSession();
		session.beginTransaction();
		session.save(e);
		session.getTransaction().commit();
	}
}
