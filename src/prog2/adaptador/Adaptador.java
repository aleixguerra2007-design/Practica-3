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

    public void afegirUsuari(String email, String nom, String adreca, boolean esEstudiant) throws BiblioException{
        dades.afegirUsuari(email, nom, adreca, esEstudiant);
    }
    public void afegirExemplar(String id, String titol, String autor, boolean admetPrestecLlarg) throws BiblioException{
        dades.afegirExemplar(id, titol, autor, admetPrestecLlarg);
    }
    public void afegirPrestec(int exemplarPos, int usuariPos, boolean esLlarg) throws BiblioException{
        dades.afegirPrestec(exemplarPos, usuariPos, esLlarg);
    }
    public void retornar(int prestecPos) throws BiblioException{
        dades.retornarPrestec(prestecPos);
    }
}

