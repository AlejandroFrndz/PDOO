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
public class Casilla_Calle extends Casilla {
    private TituloPropiedad tituloPropiedad;
    private float importe;
    
    Casilla_Calle(TituloPropiedad titulo){
        super(titulo.getNombre());
        tituloPropiedad = titulo;
        importe = titulo.getPrecioCompra();
    }
    
    @Override
    void recibeJugador(int iactual, ArrayList<Jugador> todos){
        if(super.jugadorCorrecto(iactual, todos)){
            informe(iactual,todos);
            Jugador jugador = todos.get(iactual);
            jugador.setPuedeComprar(true);

            
            if(!tituloPropiedad.tienePropietario())
                jugador.puedeComprarCasilla();
            else{
                tituloPropiedad.tramitarAlquiler(jugador);
            }
            
        }
    }
    
    public TituloPropiedad getTituloPropiedad(){
        return tituloPropiedad;
    }

    @Override
    public String toString(){
        String cadena = nombre + "\nPrecio: " + importe;
        
        return cadena;
    }
    
    private void informe(int actual, ArrayList<Jugador> todos) {
        if (super.jugadorCorrecto(actual, todos)) {
            Diario.getInstance().ocurreEvento(todos.get(actual).getNombre() + " ha caido en la calle " + this.toString());
        }
    }
    
}
