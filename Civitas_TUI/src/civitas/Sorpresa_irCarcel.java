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
class Sorpresa_irCarcel extends Sorpresa {
    private Tablero tablero;
    
    Sorpresa_irCarcel(Tablero tablero1){
        super();
        this.tablero = tablero1;
    }
    
    
    
    @Override
    protected void aplicarAJugador(int  actual, ArrayList<Jugador> todos){
        if (super.jugadorCorrecto(actual, todos)){
            this.informe(actual, todos);
            todos.get(actual).encarcelar(tablero.getCarcel());
        }
        
    }
    
    @Override
    protected void informe(int actual, ArrayList<Jugador> todos){
        if(super.jugadorCorrecto(actual, todos))
            Diario.getInstance().ocurreEvento(todos.get(actual).getNombre() + " se ve afectado por una sorpresa de tipo irCarcel ");
    }
    
}
