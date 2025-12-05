package MVC;

import MVC_Factorial.ExcepcionPersonalizada.formatoRomanoInvalido;
import java.util.HashMap;

/**
 *
 * @author josue
 */
public class Modelo {

    //Esto es un regex que valida los patrones validos de un patron romano, lo explico con mis propias palabras.
    // 1 -> M{0,3} = La letra M puede estar en la cadena de 0 a 3 vaces.
    // 2 -> (CM|CD|D?C{0,3} = Estos son patrones que se permiten, es decir, solo se pueden escribir de esa forma, o es CM o CD, el ultimo es mas complejo
    // 2.1 -> D?C{0,3} = significa dos cosas, una -> puede haber una D seguida de maximo 3 C -> DCCC, la segunda es que puede o no haber una D, o una C, pero que
    //si hay una D antes de las C, las C deben ir despues de la D, en pocas palabras, no puede ser -> CCD pero si CD, es confuso, pero esto es por el manejo de restas.
    private static final String PATRON_ROMANO = "^M{0,3}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$"; // Esto es un regex.

    //Creamos un hashMap con los valores de los simbolos romanos.
    private static final HashMap<Character, Integer> valoresRomanos = new HashMap<>();

    //Dentro de un bloque estatico inicializamos los valores para evitar que se carguen despues.
    static {
        valoresRomanos.put('I', 1);
        valoresRomanos.put('V', 5);
        valoresRomanos.put('X', 10);
        valoresRomanos.put('L', 50);
        valoresRomanos.put('C', 100);
        valoresRomanos.put('D', 500);
        valoresRomanos.put('M', 1000);
    }

    //Este metodo convierte la cadena en su valor decimal.
    public int convertirCadena(String cadenaRomana) {

        int resultado = 0;
        int valorAnterior = 0;

        //Primero verificamos si la cadena esta vacia o es nula.
        if (cadenaRomana == null || cadenaRomana.trim().isEmpty()) {
            //Lanzamos el error si es que se cumple alguna de las condiciones especificadas.
            throw new formatoRomanoInvalido("Debes ingresar al menos un simbolo para poder hacer la conversion");
        } else {
            //En caso de que se haya validado la cadena, ahora validaremos el formato con el regex.
            validarFormatoRomano(cadenaRomana);
            //Despues de ser valida, dentro de un for iteramos la cadena de manera inverza para poder encontrar las posibles restas que hayan.
            for (int i = cadenaRomana.length() - 1; i >= 0; i--) {
                //Vamos pasando el simbolo romano actual para recibir su valor decimal.
                int valorActual = verificarValor(cadenaRomana.charAt(i));
                //Si el valor actual es menor que el anterior se hara una resta, en el caso de que el valor anterior sea V y el valor actual sea I, se hara la resta.
                if (valorActual < valorAnterior) {
                    resultado -= valorActual;
                } else {
                    //Si no lo es, se hara una suma, en caso de que el valor anterior haya sido I y el actual sea V, se hara una suma.
                    resultado += valorActual;
                }
                //Asignamos el valor que haya pasado para trabajar con el en las comparaciones.
                valorAnterior = valorActual;

            }

        }
        //Retornamos el resultado.
        return resultado;

    }

    //Este metodo verifica el valor del simbolo romano y regresa su valor numerico.
    private int verificarValor(char valor) {
        //Primero verificamos si el simbolo romano ingresado es valido.
        if (!valoresRomanos.containsKey(valor)) {
            throw new formatoRomanoInvalido("Caracter romano invalido: El simbolo " + valor + " no es un simbolo romano valido");
        }
        //Si el simbolo romano existe, retornamos su valor numerico.
        return valoresRomanos.get(valor);

    }

    //Es este metodo usamos PATRON_ROMANO para validar la cadena.
    private void validarFormatoRomano(String cadenaRomana) {

        String cadena = cadenaRomana.trim();
        //Verificamos que la cadena coincida con el formato romano que hemos declarado.
        if (!cadena.matches(PATRON_ROMANO)) {
            //Su no cumple con el formato romano, lanzamos la excepcion.
            throw new formatoRomanoInvalido("La cadena " + cadenaRomana + " no cumple las reglas del formato romano");
        }

    }

}
