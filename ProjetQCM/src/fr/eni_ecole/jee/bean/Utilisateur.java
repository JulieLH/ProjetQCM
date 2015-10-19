package fr.eni_ecole.jee.bean;

public class Utilisateur 
{
	// Attributs
	private int id;
	private int type;
	private String login;
	private String motDePasse;
	private String nom;
	private String prenom;
	private String mail;
	private String numTel;
	
	// Constructeurs
	public Utilisateur()
	{
		
	}
	
	public Utilisateur(int id, int type, String login, String motDePasse, String nom, String prenom, String mail, String numTel) 
	{
		super();
		setId(id);
		setType(type);
		setLogin(login);
		setMotDePasse(motDePasse);
		setNom(nom);
		setPrenom(prenom);
		setMail(mail);
		setNumTel(numTel);
	}
	
	// Accesseurs
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getMotDePasse() {
		return motDePasse;
	}
	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public String getNumTel() {
		return numTel;
	}
	public void setNumTel(String numTel) {
		this.numTel = numTel;
	}
}