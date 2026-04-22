package prog2.model;

public class Professor extends Usuari{

    /**
     * Constructor de la classe Professor
     * @param email
     * @param nom
     * @param adreca
     */
    public Professor(String email, String nom, String adreca){
        super(email, nom, adreca);
    }

    /**
     * Retorna un String on s'indica el tipus d'usuari
     *
     * @return
     */
    @Override
    public String tipusUsuari() {
        return "Professor";
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
        return 2;
    }
}
