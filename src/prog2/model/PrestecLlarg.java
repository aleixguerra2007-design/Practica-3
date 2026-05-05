package prog2.model;

import java.io.Serializable;
import java.util.Date;

public class PrestecLlarg extends Prestec implements Serializable {

    public PrestecLlarg(Exemplar exemplar, Usuari usuari, Date dataCreacio) {
        super(exemplar, usuari, dataCreacio);

        // 1. Càlcul de la data (Lògica específica de la filla)
        long durada = duradaPrestec();
        Date dataCalculada = new Date(dataCreacio.getTime() + durada);

        // 2. Guardem el resultat a la mare (Utilitzant el setter heretat)
        setDataLimitRetorn(dataCalculada);
    }

    @Override
    public long duradaPrestec() {
        return 140_000L;
    }

    @Override
    public String tipusPrestec() {
        return "Llarg";
    }

    @Override
    public String toString() {
        return "Tipus=" + tipusPrestec() + ", " + super.toString();
    }
}
