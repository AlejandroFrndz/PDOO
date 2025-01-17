/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civitas;
import java.util.ArrayList;
import java.util.Collections;
import GUI.*;

/**
 *
 * @author alejandrof
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CivitasView vista = new CivitasView();
        Dado.createInstance(vista);
        Dado.getInstance().setDebug(false);
        
        CapturaNombres capturador = new CapturaNombres(vista,true);
        
        ArrayList<String> nombres;
        
        nombres = capturador.getNombres();
        
        CivitasJuego juego = new CivitasJuego(nombres);
                
        Controlador controlador = new Controlador(juego, vista);
        
        vista.setCivitasJuego(juego);
        vista.setTablero();
        controlador.juega();
        

    }
    
}