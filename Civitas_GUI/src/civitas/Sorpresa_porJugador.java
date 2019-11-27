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
public class Sorpresa_porJugador extends Sorpresa {
    private int valor;
    
    Sorpresa_porJugador(int valor1, String texto){
        super();
        this.valor = valor1;
        super.setTexto(texto);
    }
    
    @Override
    protected void aplicarAJugador(int actual, ArrayList<Jugador> todos){
        if (jugadorCorrecto(actual,todos)){
            this.informe(actual, todos);
            Sorpresa PAGARCOBRAR = new Sorpresa_pagarCobrar(valor*(-1), "");
            for (int i = 0; i < todos.size(); i++){
                if(i != actual)
                    PAGARCOBRAR.aplicarAJugador(i, todos);
            }
            
            Sorpresa PAGARCOBRAR2 = new Sorpresa_pagarCobrar(valor*(todos.size()-1), "");
            PAGARCOBRAR2.aplicarAJugador(actual, todos);
        }
    }
    
    @Override
    protected void informe(int actual, ArrayList<Jugador> todos){
        if(super.jugadorCorrecto(actual, todos))
            Diario.getInstance().ocurreEvento(todos.get(actual).getNombre() + " se ve afectado por una sorpresa de tipo porJugador " + super.toString());
    }
}
