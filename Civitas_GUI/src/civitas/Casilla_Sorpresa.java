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
public class Casilla_Sorpresa extends Casilla {
    private MazoSorpresas mazo;

    Casilla_Sorpresa(MazoSorpresas mazo1, String name){
        super(name);
        mazo = mazo1;
    }
    
    @Override
    void recibeJugador(int iactual, ArrayList<Jugador> todos){
        if(super.jugadorCorrecto(iactual,todos)){
            mazo.siguiente().aplicarAJugador(iactual, todos);
            informe(iactual,todos);
            todos.get(iactual).setPuedeComprar(false);

        }
    }
    
    private void informe(int actual, ArrayList<Jugador> todos) {
        if (super.jugadorCorrecto(actual, todos)) {
            Diario.getInstance().ocurreEvento(todos.get(actual).getNombre() + " ha caido en una sorpresa. ¡Buena suerte!");
        }
    }
}
