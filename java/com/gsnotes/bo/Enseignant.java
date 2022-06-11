package com.gsnotes.bo;



import javax.persistence.CascadeType;
import javax.persistence.Entity;

import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import java.util.List;


/**
 * Represente un enseignant.
 * 
 * Un enseignant est un cas spéciale de l'Utilisateur
 * 
 * @author T. BOUDAA
 *
 */


@Entity
@PrimaryKeyJoinColumn(name = "idEnseighant")
public class Enseignant extends Utilisateur {


	
	private String specialite;


	@OneToMany(mappedBy = "enseignant", cascade = CascadeType.ALL , targetEntity = Module.class)
	private List<Module> module;

	public List<Module> getModule() {
		return module;
	}

	public void setModule(List<Module> module) {
		this.module = module;
	}

	public String getSpecialite() {
		return specialite;
	}

	public void setSpecialite(String specialite) {
		this.specialite = specialite;
	}





}