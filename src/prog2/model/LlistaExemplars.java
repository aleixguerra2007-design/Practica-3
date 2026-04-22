package prog2.model;

import prog2.vista.BiblioException;

import java.util.Iterator;

public class LlistaExemplars extends Llista<Exemplar>{

    /**
     * Constructor de la classe LlistaExemplars
     */
    public LlistaExemplars(){
        super();
    }

    @Override
    public void afegir(Exemplar e) throws BiblioException {
        //Comprobamos que no haya ningún ejemplar registrado con el mismo identificador
        String idReferencia = e.getId();
        Iterator<Exemplar> it = llista.iterator();
        Exemplar exemplar;
        while(it.hasNext()){
            exemplar = it.next();
            if(exemplar.getId().equals(idReferencia)){
                throw new BiblioException("Exemplar ja registrat");
            }
        }

        //Si no hay ningún ejemplar con el mismo identificador, lo añadimos a la lista:
        llista.add(e);
    }
}
