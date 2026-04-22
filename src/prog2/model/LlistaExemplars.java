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

    /**
     * Aquest mètode afegeix un exemplar a la llista i llença excepció si ja està registrat
     * @param e
     * @throws BiblioException si l'exemplar ja està registrat
     */
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

    /**
     * Aquest mètode esborra un exemplar de la llista
     * @param e
     * @throws BiblioException si no es troba l'exemplar en la llista
     */
    public void esborrar(Exemplar e) throws BiblioException{
        boolean encontrado = false;
        String idReferencia = e.getId();
        //Buscamos el elemento que queremos eliminar de la lista:
        Iterator<Exemplar> it = llista.iterator();
        Exemplar exemplar;
        while(it.hasNext()){
            exemplar = it.next();
            if(exemplar.getId().equals(idReferencia)){
                encontrado = true;
                it.remove();
            }
        }
        //Lanzamos excepción si no se ha encontrado el elemento indicado:
        if(!encontrado){
            throw new BiblioException("Aquest exemplar no està registrat");
        }
    }

    /**
     * Aquest mètode indica si la llista conté un exemplar amb un identificador concret.
     * @param identificador
     * @return true si està, false altrament.
     */
    public boolean contains(String identificador){
        //Buscamos en la lista si hay un ejemplar con el identificador indicado.
        Exemplar exemplar;
        Iterator<Exemplar> it = llista.iterator();
        while(it.hasNext()){
            exemplar = it.next();
            if(exemplar.getId().equals(identificador)){
                return true;
            }
        }
        //Si no lo encontramos, devolvemos false
        return false;
    }

}
