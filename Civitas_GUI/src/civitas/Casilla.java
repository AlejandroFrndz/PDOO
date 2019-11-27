/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civitas;
import java.util.ArrayList;
/**
 *
 * @author alejandrof
 */
public class Casilla {
    protected String nombre;

    Casilla(String name){
        nombre = name;
    }
     
    public String getNombre(){
        return nombre;
    }
    
    private void informe(int actual, ArrayList<Jugador> todos) {
        if (this.jugadorCorrecto(actual, todos)) {
            Diario.getInstance().ocurreEvento(todos.get(actual).getNombre() + " ha caido en " + this.toString());
        }
    }
    
    @Override
    public String toString(){
       return nombre;
    }
    
    public Boolean jugadorCorrecto(int actual, ArrayList<Jugador> todos){
        return actual < todos.size() && actual > -1;
    }
    
    void recibeJugador(int iactual, ArrayList<Jugador> todos){
        informe(iactual, todos);
    }
}
