#encoding:utf-8
# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
require_relative 'casilla.rb'

module Civitas
  class Tablero
    
    attr_reader :numCasillaCarcel
    
    def initialize(n)
      
      if n >= 1 then
        @numCasillaCarcel = n;
      else
        @numCasillaCarcel = 1;
      end
      
      @casillas = Array.new(0,nil)
      casilla = Casilla.new("Salida")
      @casillas.push(casilla)
      @porSalida = 0
      @tieneJuez = false
      
    end
    
    def correcto(numCasilla = 0)
      if @casillas.count() > @numCasillaCarcel && @tieneJuez && numCasilla >= 0 && numCasilla < @casillas.count() then
        return true
      else
        return false
      end
    end
    
    def getPorSalida()
      if @porSalida > 0 then
        @porSalida -= 1
        return @porSalida+1
      else
        return @porSalida
      end
    end
    
    def añadeCasilla(casilla)
      if @casillas.count() == @numCasillaCarcel then
        carcel = Casilla.newCarcel("Cárcel", @numCasillaCarcel)
        @casillas.push(carcel)
        @casillas.push(casilla)
      else
        @casillas.push(casilla)
        if @casillas.count() == @numCasillaCarcel then
          carcel = Casilla.new("Cárcel")
          @casillas.push(carcel)
        end
      end
      return 0
    end
    
    def añadeJuez()
      if !@tieneJuez then
        juez = Casilla_Juez.new(@numCasillaCarcel,"Juez")
        @casillas.push(juez)
        @tieneJuez = true
      end
      
      return 0
    end
    
    def getCasilla(numCasilla)
      if correcto(numCasilla) then
        return @casillas[numCasilla]
      else
        return nil
      end
    end
    
    def nuevaPosicion(actual, tirada)
      if !correcto() then
        return -1
      elsif  actual + tirada < @casillas.count() then
        return actual + tirada
      else
        @porSalida += 1
        return (actual + tirada) % @casillas.count()
      end
    end
    
    def calcularTirada(origen, destino)
      if (destino - origen) < 0 then
        return destino - origen + @casillas.count()
      else
        return destino - origen
      end
    end
    
    private :correcto
  end
end
