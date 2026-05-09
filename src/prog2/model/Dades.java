package prog2.model;

import prog2.vista.BiblioException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

/**
 * Dades és la classe principal del package model, aquí es gestionen totes les relacions que hi
 * ha entre les diferents classes del paquet per fer possibles les operacions del gestor de la
 * biblioteca: afegir préstecs, retornar-los, afegir usuaris o exemplars...
 *
 * @author Yucheng Guo i Aleix Gutierrez
 */
public class Dades implements InDades, Serializable {

    /**
     * Atributs de la classe Dades
     */
    private LlistaExemplars llistaExemplars;
    private LlistaUsuaris llistaUsuaris;
    private LlistaPrestecs llistaPrestecs;

    /**
     * Constructor de la classe Dades
     */
    public Dades() {
        this.llistaExemplars = new LlistaExemplars();
        this.llistaUsuaris = new LlistaUsuaris();
        this.llistaPrestecs = new LlistaPrestecs();
    }

    /**
     * Afegeix exemplar. Llança excepció si l'id ja existeix
     *
     * @param id
     * @param titol
     * @param autor
     * @param admetPrestecLlarg
     */
    @Override
    public void afegirExemplar(String id, String titol, String autor, boolean admetPrestecLlarg) throws BiblioException {
        Exemplar e = new Exemplar(id, titol, autor, admetPrestecLlarg);
        llistaExemplars.afegir(e);
    }

    /**
     * Aquest mètode retorna la llista d'exemplars
     * @return
     */
    @Override
    public ArrayList<Exemplar> recuperaExemplars() {
        return llistaExemplars.getArrayList();
    }

    /**
     * Afegeix usuari. Llança excepció si l'email ja existeix
     *
     * @param email
     * @param nom
     * @param adreca
     * @param esEstudiant
     */
    @Override
    public void afegirUsuari(String email, String nom, String adreca, boolean esEstudiant) throws BiblioException {
        Usuari usuari;
        if(esEstudiant){
            usuari = new Estudiant(email, nom, adreca);
        } else{
            usuari = new Professor(email, nom, adreca);
        }
        llistaUsuaris.afegir(usuari);
    }

    /**
     * Aquest mètode retorna la llista d'usuaris
     * @return
     */
    @Override
    public ArrayList<Usuari> recuperaUsuaris() {
        return llistaUsuaris.getArrayList();
    }

    /**
     * Afegeix préstec. Ha de fer diferents comprovacions que poden llançar excepcions.
     * Quan s'afegeix el préstec, s'han de tenir en compte les posicions d'exemplar
     * i usuari dins dels seus ArrayLists
     *
     * @param exemplarPos
     * @param usuariPos
     * @param esLlarg
     */
    @Override
    public void afegirPrestec(int exemplarPos, int usuariPos, boolean esLlarg) throws BiblioException {

        //Recuperem objectes exemplar i usuari
        Exemplar e = llistaExemplars.getAt(exemplarPos);
        Usuari u = llistaUsuaris.getAt(usuariPos);;
        if(esLlarg && !e.getAdmetPrestecLlarg()){
            throw new BiblioException("Aquest exemplar no permet préstecs llargs");
        }
        if(!e.isDisponible()){
            throw new BiblioException("L'exemplar no es troba disponible");
        }

        int numPrestecsNormals = 0;
        int numPrestecsLlargs = 0;

        Iterator<Prestec> itrPrestec = llistaPrestecs.getArrayList().iterator();
        Prestec prestec;
        while(itrPrestec.hasNext()) {
            prestec = itrPrestec.next();
            //Mirem si l'usuari té exemplars
            if (prestec.getUsuari().equals(u) && !prestec.getRetornat()) {
                //Mirem si té préstecs endarrerits
                if (prestec.prestecEndarrerit()) {
                    throw new BiblioException("L'usuari té llibres endarrerits");
                }

                //Comptem número de prestecs per cada tipus
                if (prestec instanceof PrestecNormal) {
                    numPrestecsNormals++;
                } else if (prestec instanceof PrestecLlarg) {
                    numPrestecsLlargs++;
                }
            }
        }

        //Mirem si excedeix el nombre de préstecs: normals i llargs
        if(esLlarg) {
            if (numPrestecsLlargs >= u.getMaxPrestecsLlargs()) {
                throw new BiblioException("Has superat el límit de préstecs llargs");
            }
        }
        else if(numPrestecsNormals >= u.getMaxPrestecsNormals()){
            throw new BiblioException("Has superat el límit de préstecs normals");
        }

        //Creem llibre
        Prestec nou;
        if(esLlarg){
            nou = new PrestecLlarg(e, u, new Date());
            u.setNumPrestecsLlargs(u.getNumPrestecsLlargs()+1);
        } else{
            nou = new PrestecNormal(e, u, new Date());
            u.setNumPrestecsNormals(u.getNumPrestecsNormals()+1);
        }
        llistaPrestecs.afegir(nou);
        e.setDisponible(false);
    }

    /**
     * Retornar préstec. Llança excepció si el prestec ja es vaig retornar.
     * El préstec s'identifica amb la seva posició dins de l'ArrayList
     *
     * @param position
     */
    @Override
    public void retornarPrestec(int position) throws BiblioException {

        ArrayList<Prestec> prestecsNoRetornats = recuperaPrestecsNoRetornats();
        //Comprovem el número de posició introduit
        if(position < 0 || position >= prestecsNoRetornats.size()){
            throw new BiblioException("El número de posició introduït no és vàlid");
        }

        Prestec p = prestecsNoRetornats.get(position);
        /*
        if(p.getRetornat()){
            throw new BiblioException("Aquest préstec ja ha estat retornat");
        }
        p.setRetornat(true);
        p.getExemplar().setDisponible(true);*/
        p.retorna();
    }

    /**
     * Aquest mètode retorna la llista de préstecs.
     * @return
     */
    @Override
    public ArrayList<Prestec> recuperaPrestecs() {
        return llistaPrestecs.getArrayList();
    }

    /**
     * Aquest mètode retorna la llista dels préstecs no retornats
     * @return
     */
    @Override
    public ArrayList<Prestec> recuperaPrestecsNoRetornats() {
        Llista<Prestec> noRetornats = new Llista<>();

        Iterator<Prestec> itrPrestec = llistaPrestecs.getArrayList().iterator();
        Prestec p;
        while(itrPrestec.hasNext()){
            p = itrPrestec.next();
            if(!p.getRetornat()){
                noRetornats.afegir(p);
            }
        }
        return noRetornats.getArrayList();
    }
}
