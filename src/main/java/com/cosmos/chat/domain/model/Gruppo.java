package com.cosmos.chat.domain.model;

import java.util.Set;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
@Entity
public class Gruppo {

    @Id
    private String nomeGruppo;
    
    @ManyToMany(mappedBy = "gruppi")
    Set<User> users;

    public Gruppo() {
    }
    public Gruppo(String nomeGruppo) {
        this.nomeGruppo = nomeGruppo;
    }
    public String getNomeGruppo() {
        return nomeGruppo;
    }
    public void setNomeGruppo(String nomeGruppo) {
        this.nomeGruppo = nomeGruppo;
    }
    public Set<User> getUsers() {
		return this.users;
	}
	public void addUsers(Collection<User> users) {
		this.users.addAll(users);
	}
    public void addUser(User user){
        this.users.add(user);
    }   
    public void deleteUsers(){
        this.users.clear();
    }
}
