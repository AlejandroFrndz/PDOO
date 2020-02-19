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
public class Sorpresa_convertirEspeculador extends Sorpresa{
    
    private int fianza;
    
    Sorpresa_convertirEspeculador(int fin){
        super();
        fianza = fin;
    }
    
    @Override
    protected void aplicarAJugador(int actual, ArrayList<Jugador> todos){
        Jugador_Especulador nuevo = new Jugador_Especulador(todos.get(actual), fianza);
        
        for(int i = 0; i < nuevo.getPropiedades().size(); i++)
            nuevo.getPropiedades().get(i).actualizaPropietarioPorConversion(nuevo);
        
        todos.remove(actual);
        todos.add(actual, nuevo);
        
        this.informe(actual, todos);
    }
    
    @Override
    protected void informe(int actual, ArrayList<Jugador> todos){
        if(super.jugadorCorrecto(actual, todos))
            Diario.getInstance().ocurreEvento(todos.get(actual).getNombre() + " se ha convertido en un especulador");
    }
}
