/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civitas;
import java.util.ArrayList;
import java.util.Collections;
/**
 *
 * @author alejandrof
 */
abstract public class Sorpresa {
    private String texto;
    
    Sorpresa(){
        texto = "";  
    }
    
    public void setTexto(String entrada){
        texto = entrada;
    }
   
    public boolean jugadorCorrecto(int actual, ArrayList<Jugador> todos){
        return actual < todos.size();
    }
    
    abstract protected void informe(int actual, ArrayList<Jugador> todos);
    
    abstract protected void aplicarAJugador(int actual, ArrayList<Jugador> todos);
    
    @Override
    public String toString(){
        return texto;
    }
    
}
