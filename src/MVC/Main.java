package MVC;

/**
 *
 * @author josue
 */
public class Main {

    public static void main(String[] args) {
        
        //Creamos objetos de vista y modelo.
        Vista vista = new Vista();
        Modelo modelo = new Modelo();

        //Le pasamos las instancias a el controlador.
        Controlador controlador = new Controlador(modelo, vista);

        //Volvemos la vista visible.
        vista.setVisible(true);

    }
}
