package com.gsnotes.bo;



import javax.persistence.*;
import java.util.List;


/**
 * Represente un élément d'un module
 * 
 * @author T. BOUDAA
 *
 */

@Entity
public class Element {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idMatiere;

	private String nom;

	private String code;
	
	private double currentCoefficient;

	@ManyToOne
	@JoinColumn(name="idModule")
	private Module module;

	@OneToMany(mappedBy = "matiere" ,cascade = CascadeType.ALL)
	private List<InscriptionMatiere> inscriptionMatieres;

	public List<InscriptionMatiere> getInscriptionMatieres() {
		return inscriptionMatieres;
	}

	public void setInscriptionMatieres(List<InscriptionMatiere> inscriptionMatieres) {
		this.inscriptionMatieres = inscriptionMatieres;
	}

	public Long getIdMatiere() {
		return idMatiere;
	}

	public void setIdMatiere(Long idMatiere) {
		this.idMatiere = idMatiere;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

	public double getCurrentCoefficient() {
		return currentCoefficient;
	}

	public void setCurrentCoefficient(double currentCoefficient) {
		this.currentCoefficient = currentCoefficient;
	}
	
	

}