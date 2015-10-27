package fr.eni_ecole.jee.bean;

public class PlageHoraireTest 
{
	// Attributs
	private String idTest;
	private String idPlage;
	
	// Constructeurs
	public PlageHoraireTest()
	{
		
	}	
	
	public PlageHoraireTest(String idTest, String idPlage) 
	{
		super();
		setIdTest(idTest);
		setIdPlage(idPlage);
	}

	// Accesseurs
	public String getIdTest() {
		return idTest;
	}
	public void setIdTest(String idTest) {
		this.idTest = idTest;
	}

	public String getIdPlage() {
		return idPlage;
	}
	public void setIdPlage(String idPlage) {
		this.idPlage = idPlage;
	}
}
