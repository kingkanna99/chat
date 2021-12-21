package com.cosmos.chat.chat.authentication.domain.model;

import java.util.Set;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
@Entity
public class Gruppo {

    @Id
    private String nomeGruppo;
    
    /*@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name = "gruppo_user", joinColumns = @JoinColumn(name = "nome_gruppo"), inverseJoinColumns = @JoinColumn(name = "username"))
    private Set<User> users = new HashSet<>();
    */
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
