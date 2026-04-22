package prog2.model;

/**
 *
 * @author Yucheng Guo i Aleix Gutierrez
 */
public class Exemplar implements InExemplar{
    /**
     * Atributs de la classe Exemplar:
     */
    private String identificador;
    private String autor;
    private String titol;
    private boolean disponible;
    private boolean admetPrestecLlarg;

    /**
     * Constructor de la classe Exemplar
     * @param identificador
     * @param autor
     * @param titol
     * @param admetPrestecLlarg
     */
    public Exemplar(String identificador, String autor, String titol, boolean admetPrestecLlarg){
        this.identificador = identificador;
        this.autor = autor;
        this.titol = titol;
        this.admetPrestecLlarg = admetPrestecLlarg;
        this.disponible = true;
    }

    /**
     * Setter de l'atribut id
     * @param id
     */
    @Override
    public void setId(String id) {
        this.identificador = identificador;
    }

    /**
     * Getter de l'atribut id
     * @return
     */
    @Override
    public String getId() {
        return this.identificador;
    }

    /**
     * Setter de l'atribut titol
     * @param titol
     */
    @Override
    public void setTitol(String titol) {
        this.titol = titol;
    }

    /**
     * Getter de l'atribut titol
     * @return
     */
    @Override
    public String getTitol() {
        return this.titol;
    }

    /**
     * Setter de l'atribut autor
     * @param autor
     */
    @Override
    public void setAutor(String autor) {
        this.autor = autor;
    }

    /**
     * Getter de l'atribut autor
     * @return
     */
    @Override
    public String getAutor() {
        return autor;
    }

    /**
     * Setter de l'atribut admetPrestecLlarg
     * @param admetPrestecLlarg
     */
    @Override
    public void setAdmetPrestecLlarg(boolean admetPrestecLlarg) {
        this.admetPrestecLlarg = admetPrestecLlarg;
    }

    /**
     * Getter de l'atribut admetPrestecLlarg
     * @return
     */
    @Override
    public boolean getAdmetPrestecLlarg() {
        return this.admetPrestecLlarg;
    }

    /**
     * Setter de l'atribut disponible
     * @param disponible
     */
    public void setDisponible(boolean disponible){
        this.disponible = disponible;
    }

    /**
     * Getter de l'atribut disponible
     * @return
     */
    public boolean isDisponible(){
        return this.disponible;
    }

    /**
     * Retorna un String amb les dades de l'exemplar
     * @return
     */
    public String toString(){
        return "Id=" + getId() + ", Titol=" + getTitol() + ", Autor=" + getAutor() + ", Admet Prestec Llarg=" + getAdmetPrestecLlarg() + ", Disponibl=" + isDisponible();
    }
}
