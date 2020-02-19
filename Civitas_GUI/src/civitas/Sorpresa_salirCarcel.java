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
class Sorpresa_salirCarcel extends Sorpresa{
    private MazoSorpresas mazo;
    
    Sorpresa_salirCarcel(MazoSorpresas mazo1){
        super();
        this.mazo = mazo1;
    }
    
    
    void salirDelMazo(){
            mazo.inhabilitarCartaEspecial(this);
    }
    
    void usada(){
            mazo.habilitarCartaEspecial(this);
    }
    
    @Override
    protected void aplicarAJugador(int actual, ArrayList<Jugador> todos){
        if (super.jugadorCorrecto(actual,todos)){
            this.informe(actual, todos);
            
            Boolean salvoConducto = false;
            for (int i = 0; i < todos.size() && !salvoConducto; i++)
                salvoConducto = todos.get(i).tieneSalvoconducto();
            
            if (!salvoConducto){
                todos.get(actual).obtenerSalvoconducto(this);  
                this.salirDelMazo();
            }
            
        }
    }
    
    @Override
    protected void informe(int actual, ArrayList<Jugador> todos){
        if(super.jugadorCorrecto(actual, todos))
            Diario.getInstance().ocurreEvento(todos.get(actual).getNombre() + " se ve afectado por una sorpresa de tipo salirCarcel ");
    }
}
