package fr.eni_ecole.jee.bean;

public class Question 
{
	// Attributs
	private int id;
	private int idTheme;
	private String enonce;
	private Boolean typeReponse;
	private String image;
	
	// Constructeurs
	public Question()
	{
		
	}	
	
	public Question(int id, int idTheme, String enonce, Boolean typeReponse, String image) 
	{
		super();
		setId(idTheme);
		setIdTheme(idTheme);
		setEnonce(enonce);
		setTypeReponse(typeReponse);
		setImage(image);
	}

	// Accesseurs
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getIdTheme() {
		return idTheme;
	}
	public void setIdTheme(int idTheme) {
		this.idTheme = idTheme;
	}
	
	public String getEnonce() {
		return enonce;
	}
	public void setEnonce(String enonce) {
		this.enonce = enonce;
	}
	
	public Boolean getTypeReponse() {
		return typeReponse;
	}
	public void setTypeReponse(Boolean typeReponse) {
		this.typeReponse = typeReponse;
	}
	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}	
}
