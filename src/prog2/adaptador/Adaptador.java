package prog2.adaptador;

import prog2.model.Dades;
import prog2.model.Exemplar;
import prog2.vista.BiblioException;

import java.io.*;

public class Adaptador implements Serializable{

    private Dades dades;

    public Adaptador(){
        this.dades = new Dades();
    }

    public void guardaDades(String camiDesti) throws BiblioException{
        try{
            FileOutputStream fos = new FileOutputStream(camiDesti);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this.dades);
            oos.close();
        } catch (IOException e) {
            throw new BiblioException(("Error en guardar les dades: " + e.getMessage()));
        }
    }
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
}

