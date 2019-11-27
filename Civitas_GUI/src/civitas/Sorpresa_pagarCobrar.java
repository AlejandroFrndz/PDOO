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
class Sorpresa_pagarCobrar extends Sorpresa {
    private int valor;
    
    Sorpresa_pagarCobrar(int valor1, String texto){
        super();
        this.valor = valor1;
        super.setTexto(texto);
    }
    
    @Override
    protected void informe(int actual, ArrayList<Jugador> todos){
        if(super.jugadorCorrecto(actual, todos))
            Diario.getInstance().ocurreEvento(todos.get(actual).getNombre() + " se ve afectado por una sorpresa de tipo Pagar/Cobrar " + super.toString());
    }
    
    @Override
    protected void aplicarAJugador(int actual, ArrayList<Jugador> todos){
        if (actual > -1 && actual < todos.size()){
            this.informe(actual, todos);
            todos.get(actual).modificarSaldo(valor);
        }
    }
}
