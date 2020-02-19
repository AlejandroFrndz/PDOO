/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civitas;
import java.util.ArrayList;
/**
 *
 * @author francisco
 */
class Sorpresa_irACasilla extends Sorpresa{
    private Tablero tablero;
    private int valor;
    
    Sorpresa_irACasilla(Tablero tablero1, int valor1, String texto1){
        super();
        tablero = tablero1;
        valor = valor1;
        super.setTexto(texto1);
    }
    
    @Override
    protected void aplicarAJugador(int actual, ArrayList<Jugador> todos){
        if (actual > -1 && actual < todos.size()){
            this.informe(actual, todos);
            int casillaActual = todos.get(actual).getNumCasillaActual(); //obtener pos del jugador
            int tirada = tablero.calcularTirada(casillaActual,valor); //obtener el valor de la tirada
            int nuevaPosicion = tablero.nuevaPosicion(casillaActual, tirada); //obtener el valor de la nueva posicion
            todos.get(actual).moverACasilla(nuevaPosicion); //mover al jugador a la nueva posicion
            tablero.getCasilla(nuevaPosicion).recibeJugador(actual, todos); //la casilla objetivo recibe al jugador
        }
    }
    
    @Override
    protected void informe(int actual, ArrayList<Jugador> todos){
        if(super.jugadorCorrecto(actual, todos))
            Diario.getInstance().ocurreEvento(todos.get(actual).getNombre() + " se ve afectado por una sorpresa de tipo irACasilla " + super.toString());
    }
}
