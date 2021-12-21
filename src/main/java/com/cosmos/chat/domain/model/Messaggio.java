package com.cosmos.chat.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "messaggio")
public class Messaggio {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="messaggio_id")
	private Integer id;
    private String messaggio;
    private String mittente;
    private String destinatario;

    public Messaggio(String messaggio) {
        this.messaggio = messaggio;
    }
    public Messaggio() {

    }

    public String getMittente() {
        return mittente;
    }
    public void setMittente(String mittente) {
        this.mittente = mittente;
    }
    public String getDestinatario() {
        return destinatario;
    }
    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }
    public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

    public String getMessaggio() {
        return messaggio;
    }
    public void setMessaggio(String messaggio) {
        this.messaggio = messaggio;
    }
    
    
}
