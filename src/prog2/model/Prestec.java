package prog2.model;

import java.util.Date;

public class Prestec implements InPrestec{
    private String tipus;

    @Override
    public void setExemplar(Exemplar exemplar) {

    }

    @Override
    public Exemplar getExemplar() {
        return null;
    }

    @Override
    public void setUsuari(Usuari usuari) {

    }

    @Override
    public Usuari getUsuari() {
        return null;
    }

    @Override
    public void setDataCreacio(Date data) {

    }

    @Override
    public Date getDataCreacio() {
        return null;
    }

    @Override
    public void setDataLimitRetorn(Date data) {

    }

    @Override
    public Date getDataLimitRetorn() {
        return null;
    }

    @Override
    public String tipusPrestec() {
        return "";
    }

    @Override
    public void setRetornat(boolean retornat) {

    }

    @Override
    public boolean getRetornat() {
        return false;
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
