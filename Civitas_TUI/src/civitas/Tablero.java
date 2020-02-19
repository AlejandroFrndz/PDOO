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
public class Tablero {
   private int numCasillaCarcel;
   private int porSalida;
   private Boolean tieneJuez;
   ArrayList<Casilla> casillas;
   
   public Tablero(int carcel){
       if(carcel >= 1)
           numCasillaCarcel = carcel;
       else
           numCasillaCarcel = 1;
       
       Casilla salida = new Casilla("Salida");
       
       casillas = new ArrayList<>();
       casillas.add(salida);
       
       porSalida = 0;
       tieneJuez = false;
   }
   
   private Boolean correcto(){
       return (casillas.size() > numCasillaCarcel) && tieneJuez;
   }
   
   private Boolean correcto(int numCasilla){
       return this.correcto() && (numCasilla < casillas.size());
   }
   
   int getCarcel(){
       return numCasillaCarcel;
   }
   
   int getPorSalida(){
       if(porSalida > 0){
           porSalida--;
           return porSalida+1;
       }
       else
           return porSalida;
   }
   
   void añadeCasilla(Casilla casilla){
       if(casillas.size() == numCasillaCarcel){
           Casilla carcel = new Casilla("Cárcel");
           casillas.add(carcel);
           casillas.add(casilla);
       }
       else{
           casillas.add(casilla);
           if(casillas.size() == numCasillaCarcel){
               Casilla carcel = new Casilla("Cárcel");
               casillas.add(carcel);
           }
       }
   }
   
   void añadeJuez(){
       if(!tieneJuez){
           Casilla juez = new Casilla_Juez(numCasillaCarcel,"Juez");
           this.añadeCasilla(juez);  
           tieneJuez = true;
       }
   }
   
   Casilla getCasilla(int numCasilla){
       if(this.correcto(numCasilla))
           return casillas.get(numCasilla);
       else
           return null;
   }
   
   int nuevaPosicion(int actual, int tirada){
       if(!this.correcto())
           return -1;
       else{
           if(actual+tirada < casillas.size()){
               return actual+tirada;
           }
           
           else{
               porSalida++;
               return (actual+tirada) % casillas.size();
           }
       }
   }
   
   int calcularTirada(int origen, int destino){
       if((destino - origen) < 0)
           return destino-origen+casillas.size();
       else
           return destino-origen;
   }
}
