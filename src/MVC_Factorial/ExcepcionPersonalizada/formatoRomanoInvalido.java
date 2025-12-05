package MVC_Factorial.ExcepcionPersonalizada;
/**
 *
 * @author josue
 */
public class formatoRomanoInvalido extends IllegalArgumentException {
    //Creamos una excepcion personalizada para manejar el error de una manera mas especifica.
    public formatoRomanoInvalido(String mensaje) {
        super(mensaje);
    }

}
