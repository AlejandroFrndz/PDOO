/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civitas;

import java.util.ArrayList;

/**
 *
 * @author alejandro
 */
public class Casilla_Juez extends Casilla {
    private static int carcel;
    
    Casilla_Juez(int numCasillaCarcel, String name){
        super(name);
        carcel = numCasillaCarcel;
    }
    
    @Override
    void recibeJugador(int actual, ArrayList<Jugador> todos){
        if (super.jugadorCorrecto(actual, todos)){
            this.informe(actual, todos);
            todos.get(actual).encarcelar(carcel);
        }   
    }
    
    private void informe(int actual, ArrayList<Jugador> todos) {
        if (super.jugadorCorrecto(actual, todos)) {
            Diario.getInstance().ocurreEvento(todos.get(actual).getNombre() + " ha caido en el juez");
        }
    }
}
