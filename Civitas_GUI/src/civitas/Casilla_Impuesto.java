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
public class Casilla_Impuesto extends Casilla{
    private float importe;
    
    Casilla_Impuesto(float cantidad, String name){
        super(name);
        importe = cantidad;
    }
    
    @Override
    void recibeJugador(int actual, ArrayList<Jugador> todos){
        if (super.jugadorCorrecto(actual, todos)){
            informe(actual, todos);
            todos.get(actual).pagaImpuesto(importe);
            todos.get(actual).setPuedeComprar(false);
        }
    }
    
    private void informe(int actual, ArrayList<Jugador> todos) {
        if (super.jugadorCorrecto(actual, todos)) {
            Diario.getInstance().ocurreEvento(todos.get(actual).getNombre() + " ha caido en el impuesto de " + this.toString());
        }
    }
    
    @Override
    public String toString(){
        String cadena = nombre + "\n Importe: " + importe;        
        return cadena;
    }
    
    public float get_importe(){
        return importe;
    }
}
