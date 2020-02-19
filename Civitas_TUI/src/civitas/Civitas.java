/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civitas;
import java.util.ArrayList;
import java.util.Collections;
import JuegoTexto.Controlador;
import JuegoTexto.VistaTextual;

/**
 *
 * @author alejandrof
 */
public class Civitas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        ArrayList<String> nombres = new ArrayList<>();
        nombres.add("Nathan");
        nombres.add("Sam");
        nombres.add("Sully");
        nombres.add("Elena");
        
        CivitasJuego juego = new CivitasJuego(nombres);
        Dado.getInstance().setDebug(true);
        VistaTextual vista = new VistaTextual();
        Controlador controlador = new Controlador(juego, vista);
        
        controlador.juega();
    }
    
}