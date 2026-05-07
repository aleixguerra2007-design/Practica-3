/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package prog2.vista;
import java.util.List;
import java.util.Scanner;
import prog2.adaptador.Adaptador;

/**
 *
 * @author dortiz
 */
public class BiblioUB {

    // Declarem les constants del menu principal
    static private enum OpcionsMenuPrincipal {
        MENU_PRINCIPAL_EXEMPLARS,
        MENU_PRINCIPAL_USUARIS,
        MENU_PRINCIPAL_PRESTECS,
        MENU_PRINCIPAL_SAVE,
        MENU_PRINCIPAL_LOAD,
        MENU_PRINCIPAL_EXIT
    }

    ;

    // Declarem descripcions personalitzades per a les opcions del menú principal
    static private String[] descMenuPrincipal = {"Gestió Exemplars",
            "Gestió Usuaris",
            "Gestió Prestecs",
            "Guardar Dades",
            "Recuperar Dades",
            "Sortir"};

    static private enum OpcionsMenuGestioExemplars {
        MENU_GESTIO_EXEMPLARS_ADD,
        MENU_GESTIO_EXEMPLARS_VIEW,
        MENU_GESTIO_EXEMPLARS_EXIT
    }

    ;

    // Declarem descripcions personalitzades per a les opcions del menú principal
    static private String[] descMenuGestioExemplars = {"Afegir Exemplar",
            "Visualitzar Exemplars",
            "Sortir"};

    static private enum OpcionsMenuGestioClients {
        MENU_GESTIO_USUARIS_ADD,
        MENU_GESTIO_USUARIS_VIEW,
        MENU_GESTIO_USUARIS_EXIT
    }

    ;

    // Declarem descripcions personalitzades per a les opcions del menú principal
    static private String[] descMenuGestioUsuaris = {"Afegir Usuari",
            "Visualitzar Usuaris",
            "Sortir"};

    static private enum OpcionsMenuGestioPrestecs {
        MENU_GESTIO_PRESTECS_ADD,
        MENU_GESTIO_PRESTECS_REMOVE,
        MENU_GESTIO_PRESTECS_VIEW,
        MENU_GESTIO_PRESTECS_VIEW_URG,
        MENU_GESTIO_PRESTECS_EXIT
    }

    ;

    // Declarem descripcions personalitzades per a les opcions del menú principal
    static private String[] descMenuGestioPrestecs = {"Afegir Prestec",
            "Retornar Prestec",
            "Visualitzar Prestecs",
            "Visualitzar Prestecs no Retornats",
            "Sortir"};


    /**
     * Adaptador de l'aplicació
     */
    private Adaptador adaptador;

    /* Constructor*/
    public BiblioUB() {
        adaptador = new Adaptador();
    }

    /**
     * Aquest mètode gestiona el menu principal de la biblioteca. Crida als mètodes que gestionen els
     * submenus i demana a l'usuari l'opció que vol escollir .
     */
    public void gestioBiblioUB() {
        // Creem un objecte per llegir des del teclat
        Scanner sc = new Scanner(System.in);

        // Creem l'objecte per al menú. Li passem com a primer paràmetre el nom del menú
        Menu<OpcionsMenuPrincipal> menu = new Menu<>("Menu principal", OpcionsMenuPrincipal.values());
        menu.setDescripcions(descMenuGestioExemplars);

        // Assignem la descripció de les opcions
        menu.setDescripcions(descMenuPrincipal);

        OpcionsMenuPrincipal opcio;
        do {
            // Mostrem les opcions del menú i demanem una opció
            menu.mostrarMenu();
            opcio = menu.getOpcio(sc);

            // Fem les accions necessàries per a la opció triada
            switch(opcio) {
                case MENU_PRINCIPAL_EXEMPLARS:
                    // Mostra el menú per a la gestió d'exemplars
                    menuGestioExemplars(sc);
                    break;

                case MENU_PRINCIPAL_USUARIS:
                    // Mostra el menú per a la gestió d'usuaris
                    menuGestioUsuaris(sc);
                    break;

                case MENU_PRINCIPAL_PRESTECS:
                    // Mostra el menú per a la gestió de prestecs
                    menuGestioPrestecs(sc);
                    break;

                case MENU_PRINCIPAL_SAVE:
                    // Guardar dades
                    String dstFile = getFilePath(sc,false); // Obtenir el fitxer de sortida
                    if(dstFile != null) {
                        // Guardar les dades al fitxer triat
                        try {
                             this.adaptador.guardaDades(dstFile);
                             System.err.println("Dades guardades");
                        } catch (BiblioException ex) {
                            System.out.println("Error guardant les dades: " + ex.getMessage());
                        }
                    }                   
                    break;
                case MENU_PRINCIPAL_LOAD:
                    // Carregar dades                   
                    String srcFile = getFilePath(sc,false); // Obtenir el fitxer d'entrada
                    if(srcFile != null) {
                        // Carregar les dades del fitxer triat
                        try {
                             this.adaptador.carregaDades(srcFile);
                             System.err.println("Dades carregades");
                        } catch(BiblioException ex) {
                            System.out.println("Error carregant les dades." + ex.getMessage());
                        }
                    }     
                    break;
                case MENU_PRINCIPAL_EXIT:
                    // Sortir      1
                    System.err.println("Sortint de l'aplicació...");
                    break;
            }
        } while(opcio != OpcionsMenuPrincipal.MENU_PRINCIPAL_EXIT);
    }

    /**
     * Aquest mètode gestiona el submenu pels exemplars de la biblioteca
     * @param sc
     */
    private void menuGestioExemplars(Scanner sc) {
        Scanner scanner = new Scanner(System.in);
        Menu<OpcionsMenuGestioExemplars> menu = new Menu<>("Gestió exemplars", OpcionsMenuGestioExemplars.values());
        menu.setDescripcions(descMenuGestioExemplars);

        OpcionsMenuGestioExemplars opcio;
        do{
            menu.mostrarMenu();

            opcio = menu.getOpcio(sc);

            switch(opcio){
                case MENU_GESTIO_EXEMPLARS_ADD:
                    afegirExemplar(sc);
                    break;
                case MENU_GESTIO_EXEMPLARS_VIEW:
                    showList("Llista d'exemplars", adaptador.recuperarExemplars());
                    //System.out.println(adaptador.carregaDades());
                    break;
                case MENU_GESTIO_EXEMPLARS_EXIT:
                    break;
            }

        }while(opcio != OpcionsMenuGestioExemplars.MENU_GESTIO_EXEMPLARS_EXIT);
    }
    
    /**
     * Afegir un nou article
     * @param sc
     */

    //String id, String titol, String autor, boolean admetPrestecLlarg
    private void afegirExemplar(Scanner sc){
        Scanner scan = new Scanner(System.in);

        String id, titol, autor, admetPrestecLlarg;
        boolean admetPL;

        //Pedimos los datos del ejemplar al usuario:
        System.out.println("Introdueix les dades de l'exemplar: ");
        System.out.print("Identificador: ");
        id = scan.nextLine();
        System.out.print("Titol: ");
        titol = scan.nextLine();
        System.out.print("Autor: ");
        autor = scan.nextLine();

        //Nos aseguramos de que el usuario indique bien el último dato:
        do{
            System.out.print("Admet prestecs llargs? (s/n) ");
            admetPrestecLlarg = scan.nextLine();
            admetPrestecLlarg = admetPrestecLlarg.toLowerCase();
        }
        while(!admetPrestecLlarg.equals("s") && !admetPrestecLlarg.equals("n"));

        //Creamos y añadimos el ejemplar a la lista de ejemplares:
        if(admetPrestecLlarg.equals("s")){
            admetPL = true;
        }
        else{
            admetPL = false;
        }
        try {
            adaptador.afegirExemplar(id, titol, autor, admetPL);
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
    }

    /**
     * Aquest mètode gestiona el submenu pels usuaris
     * @param sc
     */
    private void menuGestioUsuaris(Scanner sc) {
        Scanner scanner = new Scanner(System.in);
        Menu<OpcionsMenuGestioClients> menu = new Menu<> ("Gestió clients", OpcionsMenuGestioClients.values());
        menu.setDescripcions(descMenuGestioUsuaris);

        OpcionsMenuGestioClients opcio;
        do{
            menu.mostrarMenu();
            opcio = menu.getOpcio(scanner);
            switch(opcio){
                case MENU_GESTIO_USUARIS_ADD:
                    afegirUsuari(scanner);
                    break;
                case MENU_GESTIO_USUARIS_VIEW:
                    showList("Llista d'usuaris", adaptador.recuperarUsuaris());
                    break;
                case MENU_GESTIO_USUARIS_EXIT: break;
            }
        }while(opcio != OpcionsMenuGestioClients.MENU_GESTIO_USUARIS_EXIT);
    }
    
    /**
     * Afegir un nou usuari
     * @param sc
     */
    
    private void afegirUsuari(Scanner sc){

        String email, nom, adreca, estudiantOProfessor;
        boolean esEstudiant;

        //Pedimos los datos del ejemplar al usuario:
        System.out.println("Introdueix les dades de l'usuari: ");
        System.out.print("Email: ");
        email = sc.nextLine();
        System.out.print("Nom: ");
        nom = sc.nextLine();
        System.out.print("Adreça: ");
        adreca = sc.nextLine();

        //Nos aseguramos de que el usuario indique bien el último dato:
        do{
            System.out.print("Ets un estudiant (0) o un professor (1)? ");
            estudiantOProfessor = sc.nextLine();
        }
        while(!estudiantOProfessor.equals("0") && !estudiantOProfessor.equals("1"));

        //Creamos y añadimos el ejemplar a la lista de ejemplares:
        if(estudiantOProfessor.equals("0")){
            esEstudiant = true;
        }
        else{
            esEstudiant = false;
        }
        try {
            adaptador.afegirUsuari(email, nom, adreca, esEstudiant);
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
    }

    /**
     * Aquest mètode gestiona el submenu pels préstecs
     * @param sc
     */
    private void menuGestioPrestecs(Scanner sc) {
        Scanner scanner = new Scanner(System.in);
        Menu<OpcionsMenuGestioPrestecs> menu = new Menu<> ("Gestió de préstecs", OpcionsMenuGestioPrestecs.values());
        menu.setDescripcions(descMenuGestioPrestecs);

        OpcionsMenuGestioPrestecs opcio;
        do{
            menu.mostrarMenu();
            opcio = menu.getOpcio(scanner);

            switch(opcio){
                case MENU_GESTIO_PRESTECS_ADD: afegirPrestec(sc); break;
                case MENU_GESTIO_PRESTECS_REMOVE: cancelarPrestec(sc);break;
                case MENU_GESTIO_PRESTECS_VIEW: showList("Llista de préstecs: ", adaptador.recuperarPrestecs()); break;
                case MENU_GESTIO_PRESTECS_VIEW_URG: showList("Llista de préstecs no retornats", adaptador.recuperarPrestecsNoRetornats()); break;
                case MENU_GESTIO_PRESTECS_EXIT: break;
            }
        }while(opcio != OpcionsMenuGestioPrestecs.MENU_GESTIO_PRESTECS_EXIT);
    }
    
    /**
     * Afegir un nou prestec
     * @param sc
     */

    private void afegirPrestec(Scanner sc){
        int usuariPos = -1, exemplarPos = -1;
        String prestecLlargONormal;
        boolean esLlarg;
        boolean datosSuficientes = true;

        //Detenemos el proceso si no hay ejemplares o usuarios registrados
        if(adaptador.recuperarExemplars().isEmpty()){
            System.err.println("Encara no hi ha cap exemplar registrat");
            datosSuficientes = false;
        }
        if(adaptador.recuperarUsuaris().isEmpty()){
            System.err.println("Encara no hi ha cap usuari registrat");
            datosSuficientes = false;
        }

        if(datosSuficientes) {
            //Mostramos por pantalla la lista de usuarios
            showList("Llista d'usuaris: ", adaptador.recuperarUsuaris());

            System.out.print("Introdueix l'index de l'usuari que vol l'exemplar: ");
            //Nos aseguramos de que el índice sea válido:
            do{
                try {
                    usuariPos = sc.nextInt();
                } catch(Exception e){
                    System.out.println("Introdueix un nombre enter si us plau: ");
                    sc.nextLine();
                }
            }while(usuariPos < 0 || usuariPos >= adaptador.recuperarUsuaris().size());

            //Mostramos por pantalla la lista de ejemplares
            showList("Llista d'exemplars", adaptador.recuperarExemplars());
            System.out.println("Introdueix l'index del exemplar demanat: ");
            //Nos aseguramos de que el índice sea válido:
            do{
                try {
                    exemplarPos = sc.nextInt();
                } catch(Exception e){
                    System.out.println("Introdueix un nombre enter si us plau: ");
                    sc.nextLine();
                }
            }while(exemplarPos < 0 || exemplarPos >= adaptador.recuperarExemplars().size());

            sc.nextLine();

            do {
                System.out.print("El prestec és llarg (0) o normal(1)? ");
                prestecLlargONormal = sc.nextLine();
            }while(!prestecLlargONormal.equals("0") && !prestecLlargONormal.equals("1"));

            if(prestecLlargONormal.equals("0")){
                esLlarg = true;
            }else{
                esLlarg = false;
            }

            //Finalizamos el proceso:
            try {
                adaptador.afegirPrestec(exemplarPos, usuariPos, esLlarg);
            }catch(BiblioException e){
                System.err.println(e.getMessage());
            }
        }
    }

    /**
     * Retorna un préstec. Demana a l'usuari les dades necessaries i crida al mètode retornar de la
     * classe Adaptador
     * @param sc
     */
    private void cancelarPrestec(Scanner sc){
        int prestecPos = -1;
        boolean datosSuficientes = true;

        //Detenemos el procesos si no hay préstamos no devueltos registrados
        if(adaptador.recuperarPrestecsNoRetornats().isEmpty()){
            System.err.println("No hi ha cap préstec no retornat");
            datosSuficientes = false;
        }

        if(datosSuficientes){
            //Mostramos la lista de préstamos no devueltos:
            showList("Préstecs no retornats: ", adaptador.recuperarPrestecsNoRetornats());
            System.out.println("Introdueix l'index del préstec que vols retornar: ");

            //Nos aseguramos de que el índice sea válido:
            do{
                try {
                    prestecPos = sc.nextInt();
                } catch(Exception e){
                    System.out.println("Introdueix un nombre enter si us plau: ");
                    sc.nextLine();
                }
            }while(prestecPos < 0 || prestecPos >= adaptador.recuperarPrestecsNoRetornats().size());

            //Completamos el proceso:
            try {
                adaptador.retornar(prestecPos);
            }catch(BiblioException e){
                System.err.println(e.getMessage());
            }
        }
    }

     /**
     * Mostra una llista d'objectes
     * @param title Títol a posar com a capçalera
     * @param lines Llista d'objectes per mostrar
     *
      */

    private void showList(String title, List<String> lines) {
        System.out.println("============================================");
        System.out.println(title);
        System.out.println("============================================");
        int i = 0;
        for(String l : lines) {
            System.out.println("\t[" + (i++) + "] " + l);
        }
        System.out.println("============================================");
    }

    /**
     * Demana el camí d'un fitxer
     * @param sc Objecte per a la lectura de dades de teclat
     * @param mustExist Exigeix que el fitxer existeixi (True) o no (False)
     * @return Ruta al fitxer entrada per l'usuari o null si s'ha cancelat
     *
      */

     private String getFilePath(Scanner sc, boolean mustExist) {
        String filePath = null;

        // Mostrar el missatge demanant la entrada
        System.out.println("Entra ruta completa fitxer (o ENTER per ometre):");

            // Llegim la ruta del fitxer
            filePath = sc.nextLine();

            // Si la ruta està buida retornem un null
            if(filePath.isEmpty()) {
                return null;
            }

        return filePath;
    }
}