package prog2.adaptador;

import prog2.model.Dades;
import prog2.model.Exemplar;
import prog2.model.Prestec;
import prog2.model.Usuari;
import prog2.vista.BiblioException;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Adaptador és la classe intermediària entre el package model i vista que així afavorint l'encapsualació
 * de les dades
 *
 * @author Yucheng Guo i Aleix Gutierrez
 */
public class Adaptador implements Serializable{

    /**
     * Atributs de la classe Adaptador:
     */
    private Dades dades;

    /**
     * Constructor de la classe adaptador
     */
    public Adaptador(){
        this.dades = new Dades();
    }

    /**
     * Aquest mètode retorna una llista amb els toString() dels exemplars registrats
     * @return
     */
    public List<String> recuperarExemplars(){
        List<String> listExemplars = new ArrayList<>();
        ArrayList<Exemplar> arrListExemplars = dades.recuperaExemplars();

        Iterator<Exemplar> it = arrListExemplars.iterator();
        Exemplar exemplar;
        String datos;
        while(it.hasNext()){
            exemplar = it.next();
            datos = exemplar.toString();
            listExemplars.add(datos);
        }

        return listExemplars;
    }

    /**
     * Aquest mètode retorna una llista amb els toString() dels usuaris registrats
     * @return
     */
    public List<String> recuperarUsuaris(){
        List<String> listUsuaris = new ArrayList<>();
        ArrayList<Usuari> arrListUsuaris= dades.recuperaUsuaris();

        Iterator<Usuari> it = arrListUsuaris.iterator();
        Usuari usuari;
        String datos;
        while(it.hasNext()){
            usuari = it.next();
            datos = usuari.toString();
            listUsuaris.add(datos);
        }

        return listUsuaris;
    }

    /**
     * Aquest mètode retorna una llista amb els toString() dels préstecs registrats
     * @return
     */
    public List<String> recuperarPrestecs(){
        List<String> listPrestecs = new ArrayList<>();
        ArrayList<Prestec> arrListPrestecs = dades.recuperaPrestecs();

        Iterator<Prestec> it = arrListPrestecs.iterator();
        Prestec prestec;
        String datos;
        while(it.hasNext()){
            prestec = it.next();
            datos = prestec.toString();
            listPrestecs.add(datos);
        }

        return listPrestecs;
    }

    /**
     * Aquest mètode retorna una llista amb els toString() dels préstecs no retornats registrats
     * @return
     */
    public List<String> recuperarPrestecsNoRetornats(){
        List<String> listPrestecsNoRet = new ArrayList<>();
        ArrayList<Prestec> arrListPrestecsNoRet = dades.recuperaPrestecsNoRetornats();

        Iterator<Prestec> it = arrListPrestecsNoRet.iterator();
        Prestec prestec;
        String datos;
        while(it.hasNext()){
            prestec = it.next();
            datos = prestec.toString();
            listPrestecsNoRet.add(datos);
        }

        return listPrestecsNoRet;
    }

    /**
     * Aquest mètode guarda les dades de la biblioteca en un fitxer
     * @param camiDesti
     * @throws BiblioException
     */
    public void guardaDades(String camiDesti) throws BiblioException{
        File file = new File(camiDesti);
        try{
            FileOutputStream fos = new FileOutputStream(camiDesti);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this.dades);
            oos.close();
            fos.close();
        } catch (IOException e) {
            throw new BiblioException("Error en guardar les dades");
        }
    }

    /**
     * Aquest mètode carrega les dades d'una biblioteca guardada en un fitxer indicat
     * @param camiOrigen
     * @throws BiblioException
     */
    public void carregaDades(String camiOrigen) throws BiblioException{
        try{
            FileInputStream fis = new FileInputStream(camiOrigen);
            ObjectInputStream ois = new ObjectInputStream(fis);
            this.dades = (Dades) ois.readObject();
            ois.close();
        }catch(IOException  | ClassNotFoundException e) {
            throw new BiblioException("Error en carregar les dades: " + e.getMessage());
        }
    }

    /**
     * Crida al mètode afegirUsuari de la classe Dades per registrar un nou usuari
     * @param email
     * @param nom
     * @param adreca
     * @param esEstudiant
     * @throws BiblioException
     */
    public void afegirUsuari(String email, String nom, String adreca, boolean esEstudiant) throws BiblioException{
        dades.afegirUsuari(email, nom, adreca, esEstudiant);
    }

    /**
     * Crida al mètode afegirExemplar de la classe Dades per registrar un nou exemplar
     * @param id
     * @param titol
     * @param autor
     * @param admetPrestecLlarg
     * @throws BiblioException
     */
    public void afegirExemplar(String id, String titol, String autor, boolean admetPrestecLlarg) throws BiblioException{
        dades.afegirExemplar(id, titol, autor, admetPrestecLlarg);
    }

    /**
     * Crida al mètode afegirPrestec de la classe Dades per registrar un nou préstec
     * @param exemplarPos
     * @param usuariPos
     * @param esLlarg
     * @throws BiblioException
     */
    public void afegirPrestec(int exemplarPos, int usuariPos, boolean esLlarg) throws BiblioException{
        dades.afegirPrestec(exemplarPos, usuariPos, esLlarg);
    }

    /**
     * Crida al mètode retornarPrestec de la classe Dades i retorna el préstec en la posició indicada
     * @param prestecPos
     * @throws BiblioException
     */
    public void retornar(int prestecPos) throws BiblioException{
        dades.retornarPrestec(prestecPos);
    }
}

