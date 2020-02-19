/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civitas;

/**
 *
 * @author alejandro
 */

public class Jugador_Especulador extends Jugador{
    private static final int FactorEspeculador = 2;
    private final float fianza;
    
    Jugador_Especulador(Jugador original, float fian){
        super(original);
        fianza = fian;
        actualizarPropietarios();
    }
    
    private void actualizarPropietarios(){
        for(TituloPropiedad propiedad: propiedades){
            propiedad.actualizaPropietarioPorConversion(this);
        }
    }
    
    private Boolean puedoEdificarCasa(TituloPropiedad propiedad){
        return propiedad.getNumCasas() < CasasMax*FactorEspeculador && saldo >= propiedad.getPrecioEdificar() && propiedades.contains(propiedad);
    }
    
    private Boolean puedoEdificarHotel(TituloPropiedad propiedad){
        return propiedad.getNumHoteles() < HotelesMax*FactorEspeculador && saldo >= propiedad.getPrecioEdificar() && propiedades.contains(propiedad) && propiedad.getNumCasas() == CasasMax;
    }
    
    private Boolean debeSerEncarcelado(){
        if(encarcelado)
            return false;
        else{
            if(tieneSalvoconducto()){
                perderSalvoconducto();
                Diario.getInstance().ocurreEvento("¡" + nombre + " se libra de la cárcel!");
                return false;
            }
            
            else{
                if(saldo > fianza){
                    paga(fianza);
                    return false;
                }
                
                else
                    return true;
            }
        }
    }
    
    @Override
    Boolean pagaImpuesto(float cantidad){
        if(encarcelado)
            return false;
        else
            return paga(cantidad/FactorEspeculador);
    }
    
    @Override
    public String toString(){
        String cadena = nombre + " (Especulador)" + "\n Casilla Actual: " + numCasillaActual + "\n Saldo: " + saldo + "\n Propiedades: ";
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
        
        cadena += "\n Factor de Especulación: " + FactorEspeculador;
        cadena += "\n Fianza: " + fianza;
        
        cadena += "\n Está Encarcelado: ";
        
        if(encarcelado)
            cadena += "SI";
        else
            cadena += "NO";
        
        return cadena;
    }
    
    private int getCasasMax(){
        return CasasMax*FactorEspeculador;
    }
    
    private int getHotelesMax(){
        return HotelesMax*FactorEspeculador;
    }
}
