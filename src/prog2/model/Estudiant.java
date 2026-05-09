package prog2.model;

import java.io.Serializable;

/**
 * Estudiant és una subclasse de la classe Usuari que es diferencia de la resta de subclasses
 * pel total de préstecs normals (2) i llargs (1) que pot fer.
 *
 * @author Yucheng Guo i Aleix Gutierrez
 */
public class Estudiant extends Usuari implements Serializable {

    /**
     * Constructor de la classe Estudiant
     * @param email
     * @param nom
     * @param adreca
     */
    public Estudiant(String email, String nom, String adreca){
        super(email, nom, adreca);
    }

    /**
     * Retorna un String on s'indica el tipus d'usuari
     *
     * @return
     */
    @Override
    public String tipusUsuari() {
        return "Estudiant";
    }

    /**
     * Retorna el màxim de préstecs normals que pot fer l'usuari depenent de
     * si és estudiant o professor
     *
     * @return
     */
    @Override
    public int getMaxPrestecsNormals() {
        return 2;
    }

    /**
     * Retorna el màxim de préstecs llargs que pot fer l'usuari depenent de
     * si és estudiant o professor
     *
     * @return
     */
    @Override
    public int getMaxPrestecsLlargs() {
        return 1;
    }
}
