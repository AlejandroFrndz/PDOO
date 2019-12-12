#!/bin/env ruby
# encoding: utf-8
require_relative "jugador_especulador.rb"
require_relative "sorpresa.rb"
require_relative "diario.rb"
require_relative "jugador.rb"
module Civitas
  class Sorpresa_ConvertirEspeculador < Sorpresa
    def initialize(fin)
      super()
      @fianza = fin
    end
    
    def aplicarAJugador(actual, todos)
      nuevo = Jugador_Especulador.new(todos[actual], @fianza)
      if(nuevo.propiedades != nil)
        for i in 0..nuevo.propiedades.length-1
          nuevo.getPropiedades()[i].actualizaPropietarioPorConversion(nuevo)
        end
      end
      
      
      todos.delete_at(actual)
      todos.insert(actual, nuevo)
      
      informe(actual, todos)
    end
    
    
    def informe(actual, todos)
       indice = actual
      if(jugadorCorrecto(actual,todos))
        
        puts "Valor de ACTUAL ES: #{@nombre}"
        cadena = "#{@nombre} se ha convertido en especulador"
        Diario.instance.ocurre_evento(cadena)

      end
    end
    
  end
end