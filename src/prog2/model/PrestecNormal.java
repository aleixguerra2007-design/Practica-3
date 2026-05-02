package prog2.model;

import java.util.Date;

public class PrestecNormal extends Prestec{

    public PrestecNormal(Exemplar exemplar, Usuari usuari, Date dataCreacio) {
        super(exemplar, usuari, dataCreacio);

        long durada = duradaPrestec();
        Date dataCalculada = new Date(dataCreacio.getTime() + durada);

        setDataLimitRetorn(dataCalculada);
    }

    @Override
    public long duradaPrestec() {
        return 70_000L;
    }

    @Override
    public String tipusPrestec() {
        return "Normal";
    }

    @Override
    public String toString() {
        return "Tipus=" + tipusPrestec() + ", " + super.toString();
    }
}
