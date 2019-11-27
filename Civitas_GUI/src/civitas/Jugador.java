/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civitas;
import java.util.ArrayList;
import GUI.Dado;

public class Jugador implements Comparable<Jugador> {
    
    protected static final int CasasMax = 4;
    protected static final int CasasPorHotel = 4;
    protected static final int HotelesMax = 4;
    protected static final float PasoPorSalida = 1000;
    protected static final float PrecioLibertad = 200;
    private static final float SaldoInicial = 7500;
    protected boolean encarcelado;
    protected String nombre;
    protected int numCasillaActual;
    protected boolean puedeComprar;
    protected float saldo;
    protected ArrayList<TituloPropiedad> propiedades;
    protected Sorpresa salvoconducto;
    
    Jugador(String name){
        nombre = name;
        propiedades = new ArrayList<>();
        encarcelado = false;
        numCasillaActual = 0;
        puedeComprar = true;
        saldo = SaldoInicial;
        salvoconducto = null;
    }
    
    protected Jugador(Jugador orig){
        nombre = orig.nombre;
        propiedades = new ArrayList<>();
        for(int i = 0; i < orig.propiedades.size(); i++)
            propiedades.add(orig.propiedades.get(i));
        encarcelado = orig.encarcelado;
        numCasillaActual = orig.numCasillaActual;
        puedeComprar = orig.puedeComprar;
        saldo = orig.saldo;
        salvoconducto = orig.salvoconducto;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    int cantidadCasasHoteles(){
        int amount = 0;
        
        for(int i = 0; i < propiedades.size(); i++)
            amount += propiedades.get(i).cantidadCasasHoteles();
        
        return amount;
    }
    
    Boolean enBancarrota(){
        return saldo <= 0;
    }
    
    private Boolean existeLaPropiedad(int ip){
        return ip >= 0 && ip < propiedades.size();
    }
    
    private int getCasasMax(){
        return CasasMax;
    }
    
    int getCasasPorHotel(){
        return CasasPorHotel;
    }
    
    private int getHotelesMax(){
        return HotelesMax;
    }
    
    int getNumCasillaActual(){
        return numCasillaActual;
    }
    
    private float getPrecioLibertad(){
        return PrecioLibertad;
    }
    
    private float getPremioPasoSalida(){
        return PasoPorSalida;
    }
    
    public ArrayList<TituloPropiedad> getPropiedades(){
        return propiedades;
    }
    
    Boolean getPuedeComprar(){
        return puedeComprar;
    }
    
    public float getSaldo(){
        return saldo;
    }
    
    public Boolean isEncarcelado(){
        return encarcelado;
    }
    
    public void setPuedeComprar(boolean puede){
        puedeComprar = puede;
    }
    
    private Boolean puedoEdificarCasa(TituloPropiedad propiedad){
        return propiedad.getNumCasas() < CasasMax && saldo >= propiedad.getPrecioEdificar() && propiedades.contains(propiedad);
    }
    
    private Boolean puedoEdificarHotel(TituloPropiedad propiedad){
        return propiedad.getNumHoteles() < HotelesMax && saldo >= propiedad.getPrecioEdificar() && propiedades.contains(propiedad) && propiedad.getNumCasas() == CasasMax;
    }
    
    private Boolean debeSerEncarcelado(){
        if(encarcelado)
            return false;
        else{
            if(!tieneSalvoconducto())
                return true;
            else{
                perderSalvoconducto();
                Diario.getInstance().ocurreEvento("¡" + nombre + " se libra de la cárcel!");
                return false;
            }
        }
    }
    
    Boolean encarcelar(int numCasillaCarcel){
        if(debeSerEncarcelado()){
            moverACasilla(numCasillaCarcel);
            encarcelado = true;
            Diario.getInstance().ocurreEvento(nombre + " ha sido encarcelado");
        }
        
        return encarcelado;
    }
    
    Boolean obtenerSalvoconducto(Sorpresa s){
        if(encarcelado)
            return false;
        else{
            salvoconducto = s;
            return true;
        }
    }
    
    protected void perderSalvoconducto(){
        if(salvoconducto != null){
            ((Sorpresa_salirCarcel) salvoconducto).usada();
            salvoconducto = null;
        }
    }
    
    Boolean tieneSalvoconducto(){
        return salvoconducto != null;
    }
    
    Boolean puedeComprarCasilla(){
        puedeComprar = !encarcelado;
        
        return puedeComprar;
    }
    
    Boolean paga(float cantidad){
        return modificarSaldo(cantidad*(-1));
    }
    
    Boolean pagaImpuesto(float cantidad){
        if(encarcelado)
            return false;
        else
            return paga(cantidad);
    }
    
    Boolean pagaAlquiler(float cantidad){
        if(encarcelado)
            return false;
        else
            return paga(cantidad);
    }
    
    Boolean recibe(float cantidad){
        if(encarcelado)
            return false;
        else
            return modificarSaldo(cantidad);
    }
    
    Boolean modificarSaldo(float cantidad){
        saldo += cantidad;
        
        if(cantidad >= 0)
            Diario.getInstance().ocurreEvento("El saldo de " + nombre + " se ha incrementado en " + cantidad);
        else
            Diario.getInstance().ocurreEvento("El saldo de " + nombre + " se ha decrementado en " + (cantidad*-1));
        
        return true;
    }
    
    Boolean moverACasilla(int numCasilla){
        if(encarcelado)
            return false;
        else{
            numCasillaActual = numCasilla;
            //puedeComprar = false;
            Diario.getInstance().ocurreEvento(nombre + " se mueve a la casilla nº " + numCasilla);
            return true;
        }
    }
    
    private Boolean puedoGastar(float cantidad){
        if(encarcelado)
            return false;
        else
            return saldo >= cantidad;
    }
    
    Boolean vender(int ip){
        if(encarcelado)
            return false;
        else{
            if(existeLaPropiedad(ip)){ 
               if(propiedades.get(ip).vender(this)){
                   Diario.getInstance().ocurreEvento(nombre + " ha vendido " + propiedades.get(ip).getNombre());
                   propiedades.remove(ip);
                   return true;
               }
               
               else
                return false;
            }
            
            else
                return false;
        }
    }
    
    Boolean tieneAlgoQueGestionar(){
        return !propiedades.isEmpty();
    }
    
    protected Boolean puedeSalirCarcelPagando(){
        return saldo >= PrecioLibertad;
    }
    
    Boolean salirCarcelPagando(){
        if(encarcelado && puedeSalirCarcelPagando()){
            if(paga(PrecioLibertad)){
                encarcelado = false;
                Diario.getInstance().ocurreEvento(nombre + " ha comprado su libertad y ya no está encarcelado");
                return true;
            }
            
            else
                return false;
        }
        
        else
            return false;
    }
    
    Boolean salirCarcelTirando(){
        if(Dado.getInstance().salgoDeLaCarcel()){
            encarcelado = false;
            Diario.getInstance().ocurreEvento("¡" + nombre + " ha conseguido salir de la cárcel!");
            return true;
        }
        else
            return false;
    }
    
    Boolean pasaPorSalida(){
        modificarSaldo(PasoPorSalida);
        Diario.getInstance().ocurreEvento(nombre + " ha pasado por la salida y recibe su bonus de " + PasoPorSalida);
        return true;
    }
    
    @Override
    public int compareTo(Jugador otro) {
        return (new Float(this.getSaldo()).compareTo(otro.getSaldo()));
    }
    
    @Override
    public String toString(){
        String cadena = nombre + "\n Casilla Actual: " + numCasillaActual + "\n Saldo: " + saldo + "\n Propiedades: ";
        if(propiedades.isEmpty())
            cadena += "Ninguna";
        else{
            for(int i = 0; i < propiedades.size(); i++)
                cadena += propiedades.get(i).getNombre() + ", ";
        }
        
        cadena += "\n Puede Comprar: ";
        
        if(puedeComprar)
            cadena += "Si";
        else
            cadena += "No";
        
        cadena += "\n Está Encarcelado: ";
        
        if(encarcelado)
            cadena += "SI";
        else
            cadena += "NO";
        
        return cadena;
    }
    
    Boolean construirCasa(int ip){
         Boolean result = false;
         if(encarcelado)
             return result;
         
         if(!encarcelado && existeLaPropiedad(ip)){
             TituloPropiedad propiedad = propiedades.get(ip);
             Boolean puedoEdificarCasa = puedoEdificarCasa(propiedad);
             
             if(puedoEdificarCasa){
                 result = propiedad.construirCasa(this);
                 Diario.getInstance().ocurreEvento("El jugador " + nombre + " construye una casa en "+ propiedades.get(ip).getNombre());
             }
         }
         
         return result;
    }
    
    Boolean construirHotel(int ip){
        Boolean result = false;
        if(encarcelado)
            return result;
        
        if(existeLaPropiedad(ip)){
            TituloPropiedad propiedad = propiedades.get(ip);
            Boolean puedoEdificarHotel = puedoEdificarHotel(propiedad);            
            
            if (puedoEdificarHotel){
                result = propiedad.construirHotel(this);
                propiedad.derruirCasas(CasasPorHotel, this);
                Diario.getInstance().ocurreEvento("El jugador " +nombre+ " construye un hotel en " + propiedades.get(ip).getNombre());
            }                       
        }
        
        return result;
    }
    
    Boolean hipotecar(int ip){
        Boolean result = false;
        if (encarcelado)
           return result;
        
        if (existeLaPropiedad(ip)){
            TituloPropiedad propiedad = propiedades.get(ip);
            result = propiedad.hipotecar(this);
        }
        
        if (result)
            Diario.getInstance().ocurreEvento("El jugador " +nombre+ " hipoteca la propiedad "+ip);
        
        return result;
    }
    
    Boolean cancelarHipoteca(int ip){
        Boolean result = false;
        
        if (encarcelado)
            return result;
        
        if(this.existeLaPropiedad(ip)){
            
            TituloPropiedad propiedad = propiedades.get(ip);
            float cantidad = propiedad.getImporteCancelarHipoteca();
            Boolean puedoGastar = this.puedoGastar(cantidad);
            
            if(puedoGastar){
                result = propiedad.cancelarHipoteca(this);
                
                if(result)
                    Diario.getInstance().ocurreEvento("El jugador " + nombre + " cancela la hipoteca de la propiedad " + propiedad.getNombre());
            }
        }
  
        return result;
    }
    
    Boolean comprar(TituloPropiedad titulo){
        Boolean result = false;
        
        if(encarcelado)
            return result;
        
        if (puedeComprar){
            float precio = titulo.getPrecioCompra();
            
            if(puedoGastar(precio)){
                result = titulo.comprar(this);
            }
        }
        
        if (result){
            propiedades.add(titulo);
            Diario.getInstance().ocurreEvento("El jugador " + nombre + " compra la propiedad " + titulo.toString());
            System.out.println("Se ha efectuado la compra");
        }
        
        return result;
    }
    
}
