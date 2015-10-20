package fr.eni_ecole.jee.bean;

public class Section 
{
	// Attributs
	private int numSection;
	private int idTest;
	private int idTheme;
	private int nbQuestions;
	
	// Constructeurs
	public Section()
	{
		
	}	
	
	public Section(int numSection, int idTest, int idTheme, int nbQuestions) 
	{
		super();
		setNumSection(numSection);
		setIdTest(idTest);
		setIdTheme(idTheme);
		setNbQuestions(nbQuestions);
	}

	// Accesseurs
	public int getNumSection() {
		return numSection;
	}
	public void setNumSection(int numSection) {
		this.numSection = numSection;
	}
	
	public int getIdTest() {
		return idTest;
	}
	public void setIdTest(int idTest) {
		this.idTest = idTest;
	}
	
	public int getIdTheme() {
		return idTheme;
	}
	public void setIdTheme(int idTheme) {
		this.idTheme = idTheme;
	}
	
	public int getNbQuestions() {
		return nbQuestions;
	}
	public void setNbQuestions(int nbQuestions) {
		this.nbQuestions = nbQuestions;
	}	
}
