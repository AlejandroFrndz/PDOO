/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civitas;
import java.util.Random;

/**
 *
 * @author alejandrof
 */
public class Dado {
    private Random random;
    private int ultimoResultado;
    private Boolean debug;
    private static final Dado instance = new Dado();
    private static final int SalidaCarcel = 5;
    
    private Dado(){
        debug = false;
        ultimoResultado = 0;
        random = new Random();
    }
    
    public static Dado getInstance(){
        return instance;
    }
    
    int tirar(){
        if(debug){
            ultimoResultado = 1;
            return ultimoResultado;
        }
            
        else
            ultimoResultado = random.nextInt(6) + 1;
            return ultimoResultado;
    }
    
    Boolean salgoDeLaCarcel(){
        this.tirar();
        //System.out.println(ultimoResultado); Comprobación
        return ultimoResultado >= 5;
        
    }
    
    int quienEmpieza(int n){
        int actual, maximo=0, primero=-1;
        for(int i = 0; i < n; i++){
            actual = this.tirar();
            if(actual > maximo){
                maximo = actual;
                primero = i;
            }
        }
        
        return primero;
    }
    
    int getUltimoResultado(){
        return ultimoResultado;
    }
    
    void setDebug(Boolean d){
        if(d){
            debug = true;
            Diario.getInstance().ocurreEvento("Modo Debug del Dado activado");
        }
        
        else{
            debug = false;
            Diario.getInstance().ocurreEvento("Modo Debug del Dado desactivado");
        }
    }
}
