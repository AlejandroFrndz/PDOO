#!/bin/env ruby
# encoding: utf-8
require_relative "titulo_propiedad.rb"
require_relative 'dado.rb'
require_relative 'diario.rb'
require_relative "jugador.rb"

module Civitas
  class Jugador_Especulador < Jugador
    @@FACTORESPECULADOR = 2
    def initialize(fianza)
       @fianza = fianza
    end
    
    def self.nuevoEspeculador(jugador,fianza)
      jugador_especulador = new(fianza)
      jugador_especulador.from(jugador)
      if(@propiedades != nil)
        jugador_especulador.actualizarPropietarios()
      end
      
      return jugador_especulador
    end
    
    def actualizarPropietarios()
       
        for i in @propiedades
            i.actualizarPropietarioPorConversion(self) 
        end
      
    end
    
    def puedoEdificarCasa(propiedad)
      return propiedad.numCasas < @@CASASMAX*@@FACTORESPECULADOR && @saldo >= propiedad.precioEdificar && @propiedades.include?(propiedad)      
    end
    
    
    def puedoEdificarHotel(propiedad)
      return propiedad.numHoteles < @@HOTELESMAX*@@FACTORESPECULADOR && @saldo >= propiedad.precioEdificar && @propiedades.include?(propiedad) && propiedad.numCasas == @@CASASMAX
    end
    
    def debeSerEncarcelado()
      if @encarcelado
        return false
      else
        if !tieneSalvoconducto()
          if @saldo > @fianza
            paga(@fianza)
            return false;
          else
            return true;
          end
          
        else
          perderSalvoConducto()
          Diario.instance.ocurre_evento("¡ #{@nombre} se libra de la cárcel!")
          return false
        end
        
      end
    end
      
     def pagaImpuesto(cantidad)
       if(@encarcelado)
         return false
       else
         return paga(cantidad/@@FACTORESPECULADOR)
       end
     end
     
     def to_s()
      cadena = "#{@nombre} (Especulador) Casilla Actual: #{@numCasillaActual} \nSaldo: #{@saldo} \nPropiedades: "
      if(!@propiedades.empty?)# && @propiedades != nil)
        #puts "\n LA LONGITUD RESPUESTA A SI ES EMPTY ES #{@propiedades.empty?} y la longitud es #{@propiedades ==  nil} y el primer elemento es #{@propiedades[0].nombre}" 
        for i in @propiedades
            cadena += "#{i.nombre}, "
        end
      else
        cadena += "Ninguna"
      end
      
      cadena += "\n Puede Comprar: "
      
      if @puedeComprar
        cadena += "SI"
      else
        cadena += "NO"
      end
      cadena += "\n Factor de especulacion: #{@@FACTORESPECULADOR}" 
      cadena += "\n Fianza: #{@fianza}"
      cadena += "\n Está encarcelado: "
      
      if @encarcelado
        cadena += "SI\n"
      else
        cadena += "NO\n"
      end
      
      cadena
    end
    
    def getCasasMax()
      return @@CASASMAX*@@FACTORESPECULADOR
      
    end
    
    def getHotelesMax()
      return @@HOTELESMAX*@@FACTORESPECULADOR
    end
    
  end
  
  
end