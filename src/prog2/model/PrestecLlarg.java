package prog2.model;

import java.io.Serializable;
import java.util.Date;

public class PrestecLlarg extends Prestec implements Serializable {

    /**
     * Constructor de la classe PrestecLlarg
     * @param exemplar
     * @param usuari
     * @param dataCreacio
     */
    public PrestecLlarg(Exemplar exemplar, Usuari usuari, Date dataCreacio) {
        super(exemplar, usuari, dataCreacio);

        // 1. Càlcul de la data (Lògica específica de la filla)
        long durada = duradaPrestec();
        Date dataCalculada = new Date(dataCreacio.getTime() + durada);

        // 2. Guardem el resultat a la mare (Utilitzant el setter heretat)
        setDataLimitRetorn(dataCalculada);
    }

    /**
     * Retorna la durada del préstec de tipus llarg
     * @return
     */
    @Override
    public long duradaPrestec() {
        return 140_000L;
    }

    /**
     * Retorna un String amb el tipus de préstec (llarg)
     * @return
     */
    @Override
    public String tipusPrestec() {
        return "Llarg";
    }

    /**
     * Retorna un String amb les dades del préstec llarg
     * @return
     */
    @Override
    public String toString() {
        return "Tipus=" + tipusPrestec() + ", " + super.toString();
    }
}
