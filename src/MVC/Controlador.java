package MVC;

import MVC_Factorial.ExcepcionPersonalizada.formatoRomanoInvalido;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author josue
 */
public class Controlador implements ActionListener {

    //Implementamos la vista y el modelo.
    private final Modelo modelo; 
    private final Vista vista;

    //Recibimos la vista y el modelo a travez de un constructor en el main.
    public Controlador(Modelo modelo, Vista vista) {
        this.modelo = modelo;
        this.vista = vista;
        this.vista.setListener(this);
    }

    //Sobreescribimos el metodo de actionPerfomed.
    @Override
    public void actionPerformed(ActionEvent e) {
        //Verificamos que se presione el boton.
        if ("Convertir".equals(e.getActionCommand())) {
            //Ponemos el codigo dentro de un try para poder lanzar la excepcion que ocurra o mostrar el resultado.
            try {
                //Llamamos al metodo convetirCadena.
                int valor = modelo.convertirCadena(vista.obtenerCadenaRomana());
                //Mostramos el resultado en la vista.
                vista.mostrarResultado(valor);
            } catch (formatoRomanoInvalido ex) {
                //Si hay un error, aqui lo capturamos y lanzamos el mensaje del error,
                vista.mostrarMensajeError(ex.getMessage());
            }

        }
    }

}
