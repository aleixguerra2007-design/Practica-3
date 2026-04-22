package prog2.model;

import java.util.Date;

public abstract class Prestec implements InPrestec{

    /**
     * Atributs de la classe Prestec
     */
    private Exemplar exemplar;
    private Usuari usuari;
    private Date dataCreacio;
    private Date dataLimitRetorn;
    private boolean retornat;

    // TODO: CREAR CONSTRUCTOR Y TERMINAR ULTIMOS METODOS

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
    public abstract String tipusPrestec();

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
    public void retorna() {

    }

    /**
     * Retornar durada prestec. La durada del prestec depen del tipus de prestec
     */
    @Override
    public long duradaPrestec() {
        return 0;
    }

    /**
     * Retornar true si el prestec està endarrerit per a la data actual
     */
    @Override
    public boolean prestecEndarrerit() {
        return false;
    }
}
