package prog2.model;

import prog2.vista.BiblioException;

import java.io.Serializable;
import java.util.Date;

public abstract class Prestec implements InPrestec, Serializable {
    /**
     * Atributs de la classe Prestec
     */
    private Exemplar exemplar;
    private Usuari usuari;
    private Date dataCreacio;
    private Date dataLimitRetorn;
    private boolean retornat;

    public Prestec(Exemplar exemplar, Usuari usuari, Date dataCreacio) {
        this.exemplar = exemplar;
        this.usuari = usuari;
        this.dataCreacio = dataCreacio;
        this.retornat = false;
    }

    @Override
    public void setExemplar(Exemplar exemplar) {
        this.exemplar = exemplar;
    }

    @Override
    public Exemplar getExemplar() {
        return this.exemplar;
    }

    @Override
    public void setUsuari(Usuari usuari) {
        this.usuari = usuari;
    }

    @Override
    public Usuari getUsuari() {
        return this.usuari;
    }

    @Override
    public void setDataCreacio(Date data) {
        this.dataCreacio = data;
    }

    @Override
    public Date getDataCreacio() {
        return this.dataCreacio;
    }

    @Override
    public void setDataLimitRetorn(Date data) {
        this.dataLimitRetorn = data;
    }

    @Override
    public Date getDataLimitRetorn() {
        return dataLimitRetorn;
    }

    @Override
    public void setRetornat(boolean retornat) {
        this.retornat = retornat;
    }

    @Override
    public boolean getRetornat() {
        return this.retornat;
    }

    /**
     * Retornar prestec. Llança excepció si el prestec ja es vaig retornar
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
        return "Prestec{" +
                "exemplar=" + exemplar +
                ", usuari=" + usuari +
                ", dataCreacio=" + dataCreacio +
                ", dataLimitRetorn=" + dataLimitRetorn +
                ", retornat=" + retornat +
                '}';
    }
}