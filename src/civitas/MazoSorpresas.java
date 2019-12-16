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
public class MazoSorpresas {
    private ArrayList<Sorpresa> sorpresas;
    private Boolean barajada;
    private int usadas;
    private Boolean debug;
    private ArrayList<Sorpresa> cartasEspeciales;
    private Sorpresa ultimaSorpresa;
    
    private void init(){
        sorpresas = new ArrayList<>();
        cartasEspeciales = new ArrayList<>();
        barajada = false;
        usadas = 0;
    }
    
    MazoSorpresas(Boolean debugging){
        debug = debugging;
        this.init();
        if(debug)
            Diario.getInstance().ocurreEvento("Modo debug del Mazo activado");
    }
    
   MazoSorpresas(){
        debug = false;
        this.init();
    }
    
   void alMazo(Sorpresa s){
        if(!barajada)
            sorpresas.add(s);
    }
    
    Sorpresa siguiente(){
        if(!debug && (!barajada || usadas >= sorpresas.size())){
            Collections.shuffle(sorpresas);
            barajada = true;
            usadas = 0;
        }
        
        usadas++;
        Sorpresa extraida = sorpresas.get(0);
        sorpresas.add(extraida);
        sorpresas.remove(0);
        ultimaSorpresa = extraida;
        return extraida;
    }
    
    void inhabilitarCartaEspecial(Sorpresa sorpresa){
        Boolean remove = sorpresas.remove(sorpresa);
        
        if(remove){
            cartasEspeciales.add(sorpresa);
            Diario.getInstance().ocurreEvento("Se inhabilita la carta especial " + sorpresa.toString());
        }
    }
    
    void habilitarCartaEspecial(Sorpresa sorpresa){
        Boolean remove = cartasEspeciales.remove(sorpresa);
        
        if(remove){
            sorpresas.add(sorpresa);
            Diario.getInstance().ocurreEvento("Se habilita la carta especial " + sorpresa.toString());
        }
    }
    
    Sorpresa getUltimaSorpresa(){
        return ultimaSorpresa;
    }
}
