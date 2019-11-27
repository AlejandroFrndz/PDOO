#!/bin/env ruby
# encoding: utf-8
require_relative 'jugador.rb'

module Civitas
  class TituloPropiedad
    
    attr_reader :numCasas, :numHoteles, :precioEdificar, :nombre, :precioCompra, :propietario, :hipotecado, :hipotecaBase
    
    @@FACTORINTERESHIPOTECA = 1.1
    
    def initialize(nom, ab, fr, hb, pc, pe)
      @nombre = nom
      @alquilerBase = ab
      @factorRevalorizacion = fr
      @hipotecaBase = hb
      @precioCompra = pc
      @precioEdificar = pe
      @propietario = nil
      @numCasas = @numHoteles = 0
      @hipotecado = false
    end
    
    def to_s()
      
      cadena = "#{@nombre} Alquiler Base: #{@alquilerBase}
      \nFactor de Revalorización: #{@factorRevalorizacion}
      \nHipoteca Base: #{@hipotecaBase} 
      \nPrecio de Compra: #{@precioCompra} 
      \nPrecio Edificacion: #{@precioEdificar} 
      \nNúmero de casas: #{@numCasas} 
      \nNúmero de hoteles: #{@numHoteles} 
      \nPropietario:  #{@propietario.nombre}"
              
      if hipotecado
        cadena += " \nHIPOTECADA"
      else
        cadena += " \nNO HIPOTECADA"
      end
    
      cadena
      
    end
    
    def getPrecioAlquiler()
      if @hipotecado || propietarioEncarcelado()
        return 0
      else
        return @alquilerBase*(1+(@numCasas*0.5)+(@numHoteles*2.5))
      end  
    end
    
    def getImporteCancelarHipoteca()
      return @hipotecaBase*@@FACTORINTERESHIPOTECA
    end
    
    def cancelarHipoteca(jugador)
      if @hipotecado && esEsteElPropietario(jugador)
        if jugador.paga(getImporteCancelarHipoteca())
          @hipotecado = false;
          return true
        end
      else
        return false
      end
    end
    
    def hipotecar(jugador)
      
      if (!@hipotecado && esEsteElPropietario(jugador))
        jugador.recibe(@hipotecaBase)
        puts "se hipoteca"
        @hipotecado = true
        return true
      else
        return false
      end
    end
    
    
    def esEsteElPropietario(jugador)
      return @propietario == jugador
    end
    
    def tramitarAlquiler(jugador)
      if tienePropietario() && !esEsteElPropietario(jugador)
        precio = getPrecioAlquiler()
        jugador.pagaAlquiler(precio)
        @propietario.recibe(precio)
      end
    end
    
    def tienePropietario()
      return @propietario != nil
    end
    
    
    def propietarioEncarcelado()
      if tienePropietario()
        return @propietario.encarcelado
       
      else
        return false
      end
    end
    
    
    def cantidadCasasHoteles()
      return @numCasas + @numHoteles
    end
    
    def derruirCasas(n, jugador)
      if esEsteElPropietario(jugador) && @numCasas >= n
        @numCasas -= n
        return true
      end
    end
    
    
    def getPrecioVenta()
      return @precioCompra + @factorRevalorizacion*cantidadCasasHoteles()*@precioEdificar
    end
    
    def construirCasa(jugador)
      done = false
      
      if esEsteElPropietario(jugador)
        if jugador.paga(@precioEdificar)
          @numCasas += 1
          done = true
        end
      end
      
      return done
    end
    
    def construirHotel(jugador)
      done = false
      
      if esEsteElPropietario(jugador)
        if jugador.paga(@precioEdificar)
          @numHoteles += 1
          done = true
        end
      end
      
      done
    end
    
    def comprar(jugador)
      done = false
      if !tienePropietario()
        if jugador.paga(@precioCompra)
          @propietario = jugador
          done = true
        end
      end
      
      return done
      
    end
    
    def vender(jugador)
      done = false
      
      if esEsteElPropietario(jugador)
        jugador.recibe(getPrecioVenta())
        @propietario = nil
        done = true
      end
      
      return done
      
    end
    
    def actualizarPropietarioPorConversion(jugador)
      @propietario = jugador
    end
    
    private :esEsteElPropietario, :getPrecioVenta, :propietarioEncarcelado
  end
end