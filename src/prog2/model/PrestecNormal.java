package prog2.model;

import java.io.Serializable;
import java.util.Date;

public class PrestecNormal extends Prestec implements Serializable {

    /**
     * Constructor de la classe PrestecNormal
     * @param exemplar
     * @param usuari
     * @param dataCreacio
     */
    public PrestecNormal(Exemplar exemplar, Usuari usuari, Date dataCreacio) {
        super(exemplar, usuari, dataCreacio);

        long durada = duradaPrestec();
        Date dataCalculada = new Date(dataCreacio.getTime() + durada);

        setDataLimitRetorn(dataCalculada);
    }

    /**
     * Retorna la durada del préstec del tipus normal
     * @return
     */
    @Override
    public long duradaPrestec() {
        return 70_000L;
    }

    /**
     * Retorna un String amb el tipus de préstec (normal)
     * @return
     */
    @Override
    public String tipusPrestec() {
        return "Normal";
    }

    /**
     * Retorna un String amb les dades del préstec normal
     * @return
     */
    @Override
    public String toString() {
        return "Tipus=" + tipusPrestec() + ", " + super.toString();
    }
}
