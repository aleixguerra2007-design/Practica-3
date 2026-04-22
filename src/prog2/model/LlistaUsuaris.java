package prog2.model;

import prog2.vista.BiblioException;
import java.util.Iterator;
public class LlistaUsuaris extends Llista<Usuari>{
    public LlistaUsuaris(){
        super();
    }

    /**
     * Aquest mètode afegeix un usuari a la llista
     * @param u
     * @throws BiblioException si l'usuari ja està registrat
     */
    @Override
    public void afegir(Usuari u) throws BiblioException{
        String emailReferencia = u.getEmail();
        //Comprobamos que el usuario no está registrado ya:
        Iterator<Usuari> it = llista.iterator();
        Usuari usuari;
        while(it.hasNext()){
            usuari = it.next();
            if(usuari.getEmail().equals(emailReferencia)) {
                throw new BiblioException("Aquest usuari ja està registrat");
            }
        }
        //Añadimos el usuario a la lista:
        llista.add(u);
    }

    /**
     * Aquest mètode esborra un usuari de la llista
     * @param u
     * @throws BiblioException si l'usuari indicat no està en la llista
     */
    public void esborrar(Usuari u) throws BiblioException{
        boolean encontrado = false;
        String emailReferencia = u.getEmail();
        //Buscamos el usuario en la lista
        Iterator<Usuari> it = llista.iterator();
        Usuari usuari;
        while(it.hasNext()){
            usuari = it.next();
            if(usuari.getEmail().equals(emailReferencia)){
                encontrado = true;
                it.remove();
            }
        }
        //Lanzamos excepción si no lo hemos encontrado en la lista:
        if(!encontrado){
            throw new BiblioException("L'usuari indicat no està registrat");
        }
    }

    /**
     * Aquest mètode indica si un usuari amb un email concret està en la llista
     * @param emailRef
     * @return true si està, false altrament.
     */
    public boolean contains(String emailRef){
        //Recorremos la lista de elementos para ver si está el usuario con el correo indicado:
        Iterator<Usuari> it = llista.iterator();
        Usuari usuari;
        while(it.hasNext()){
            usuari = it.next();
            if(usuari.getEmail().equals(emailRef)){
                return true;
            }
        }
        //Devolevmos false si no lo encontramos
        return false;
    }
}
