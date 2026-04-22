package prog2.model;

public abstract class Usuari implements InUsuari {
    /**
     * Atributs de la classe Usuari
     */
    private String email;
    private String nom;
    private String adreca;
    private int numPrestecsNormals;
    private int numPrestecsLlargs;

    /**
     * Constructor de la classe Usuari
     * @param email
     * @param nom
     * @param adreca
     */
    public Usuari(String email, String nom, String adreca){
        this.email = email;
        this.nom = nom;
        this.adreca = adreca;
        this.numPrestecsNormals = 0;
        this.numPrestecsLlargs = 0;
    }

    /**
     * Setter de l'atribut email
     * @param email
     */
    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter de l'atribut email
     * @return
     */
    @Override
    public String getEmail() {
        return this.email;
    }

    /**
     * Setter de l'atribut nom
     * @param nom
     */
    @Override
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Getter de l'atribut nom
     * @return
     */
    @Override
    public String getNom() {
        return this.nom;
    }

    /**
     * Setter de l'atribut adreca
     * @param adreca
     */
    @Override
    public void setAdreca(String adreca) {
        this.adreca = adreca;
    }

    /**
     * Getter de l'atribut adreca
     * @return
     */
    @Override
    public String getAdreca() {
        return this.adreca;
    }

    /**
     * Retorna un String on s'indica el tipus d'usuari
     * @return
     */
    @Override
    public abstract String tipusUsuari();

    /**
     * Setter de l'atribut numPrestecsNormals
     * @param numPrestecsNormals
     */
    @Override
    public void setNumPrestecsNormals(int numPrestecsNormals) {
        this.numPrestecsNormals = numPrestecsNormals;
    }

    /**
     * Getter de l'atribut numPrestecsNormals
     * @return
     */
    @Override
    public int getNumPrestecsNormals() {
        return this.numPrestecsNormals;
    }

    /**
     * Setter de l'atribut numPrestecsLlargs
     * @param numPrestecsLlargs
     */
    @Override
    public void setNumPrestecsLlargs(int numPrestecsLlargs) {
        this.numPrestecsLlargs = numPrestecsLlargs;
    }

    /**
     * Getter de l'atribut numPrestecsLlargs
     * @return
     */
    @Override
    public int getNumPrestecsLlargs() {
        return this.numPrestecsLlargs;
    }

    /**
     * Retorna el màxim de préstecs normals que pot fer l'usuari depenent de
     * si és estudiant o professor
     * @return
     */
    @Override
    public abstract int getMaxPrestecsNormals();

    /**
     * Retorna el màxim de préstecs llargs que pot fer l'usuari depenent de
     * si és estudiant o professor
     * @return
     */
    @Override
    public abstract int getMaxPrestecsLlargs();

    public String toString(){
        return "Tipus=" + tipusUsuari() + ", Email=" + getEmail() + ", Nom=" + getNom() + ", Adreca=" + getAdreca() +
                ", Num. prestecs normals=" + getNumPrestecsNormals() + ", Num. prestecs llargs" + getNumPrestecsLlargs();
    }
}
