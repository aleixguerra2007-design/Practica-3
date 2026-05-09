package prog2.model;

import prog2.vista.BiblioException;

import java.io.Serializable;
import java.util.Date;

/**
 * Prestec és la classe que representa els préstecs que es poden fer a la biblioteca
 * on un usuari concret selecciona un exemplar concret i hi ha un període de temps perquè
 * l'usuari el retorni. Hi ha dos tipus de préstecs segons aquest rang de temps per retornar-ho:
 * PrestecNormal i PrestecLLarg.
 *
 * @author Yucheng Guo i Aleix Gutiérrez
 */
public abstract class Prestec implements InPrestec, Serializable {
    /**
     * Atributs de la classe Prestec
     */
    private Exemplar exemplar;
    private Usuari usuari;
    private Date dataCreacio;
    private Date dataLimitRetorn;
    private boolean retornat;

    /**
     * Constructor de la classe Prestec
     * @param exemplar
     * @param usuari
     * @param dataCreacio
     */
    public Prestec(Exemplar exemplar, Usuari usuari, Date dataCreacio) {
        this.exemplar = exemplar;
        this.usuari = usuari;
        this.dataCreacio = dataCreacio;
        this.retornat = false;
    }

    /**
     * Setter de l'atribut exemplar
     * @param exemplar
     */
    @Override
    public void setExemplar(Exemplar exemplar) {
        this.exemplar = exemplar;
    }

    /**
     * Getter de l'atribut exemplar
     * @return
     */
    @Override
    public Exemplar getExemplar() {
        return this.exemplar;
    }

    /**
     * Setter de l'atribut usuari
     * @param usuari
     */
    @Override
    public void setUsuari(Usuari usuari) {
        this.usuari = usuari;
    }

    /**
     * Getter de l'atribut usuari
     * @return
     */
    @Override
    public Usuari getUsuari() {
        return this.usuari;
    }

    /**
     * Setter de l'atribut dataCreacio
     * @param data
     */
    @Override
    public void setDataCreacio(Date data) {
        this.dataCreacio = data;
    }

    /**
     * Getter de l'atribut dataCreacio
     * @return
     */
    @Override
    public Date getDataCreacio() {
        return this.dataCreacio;
    }

    /**
     * Setter de l'atribut dataLimitRetorn
     * @param data
     */
    @Override
    public void setDataLimitRetorn(Date data) {
        this.dataLimitRetorn = data;
    }

    /**
     * Getter de l'atribut dataLimitRetorn
     * @return
     */
    @Override
    public Date getDataLimitRetorn() {
        return dataLimitRetorn;
    }

    /**
     * Setter de l'atribut retornat
     * @param retornat
     */
    @Override
    public void setRetornat(boolean retornat) {
        this.retornat = retornat;
    }

    /**
     * Getter de l'atribut retornat
     * @return
     */
    @Override
    public boolean getRetornat() {
        return this.retornat;
    }

    /**
     * Retornar préstec. Llança excepció si el préstec ja es vaig retornar
     */
    @Override
    public void retorna() throws BiblioException {
        if(getRetornat()){
            throw new BiblioException("Ja es va retornar el llibre");
        }
        setRetornat(true);

        getExemplar().setDisponible(true);

        if(this instanceof PrestecLlarg){
            int n = getUsuari().getNumPrestecsLlargs();
            getUsuari().setNumPrestecsLlargs(n-1);
        }
        else if(this instanceof PrestecNormal){
            int n = getUsuari().getNumPrestecsNormals();
            getUsuari().setNumPrestecsNormals(n-1);
        }

    }

    /**
     * Retornar durada prestec. La durada del prestec depen del tipus de prestec
     */
    @Override
    public abstract long duradaPrestec();

    /**
     * Retorna tipus prèstec: normal o llarg
     */
    @Override
    public abstract String tipusPrestec();

    /**
     * Retornar true si el prestec està endarrerit per a la data actual
     */
    @Override
    public boolean prestecEndarrerit() {

        if (getRetornat()) {
            return false;
        }

        Date dataActual = new Date();

        return dataActual.after(getDataLimitRetorn());
    }

    /**
     * toString de la classe prèstec
     * @return informació llegible
     */

    @Override
    public String toString() {
        return "Prestec{ " +
                "exemplar =" + exemplar +
                "|| usuari=" + usuari +
                "|| dataCreacio=" + dataCreacio +
                ", dataLimitRetorn=" + dataLimitRetorn +
                ", retornat=" + retornat +
                '}';
    }
}