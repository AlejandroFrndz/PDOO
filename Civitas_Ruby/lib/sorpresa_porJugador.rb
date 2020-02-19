require_relative 'sorpresa.rb'
require_relative 'sorpresa_pagarCobrar'
require_relative 'jugador.rb'
require_relative 'diario.rb'
module Civitas
  class Sorpresa_porJugador < Sorpresa
    
    def initialize(valor, texto)
      super(texto)
      @valor = valor
    end
    
      def aplicarAJugador(actual, todos)
      if jugadorCorrecto(actual,todos)
        informe(actual,todos)
        valor_actual = @valor * (todos.size-1)
        valor_otros = @valor * -1
        
        for i in todos
          if i==todos[actual]
            i.modificarSaldo(valor_actual)
          else
            i.modificarSaldo(valor_otros)
          end
        end       
      end
    end
    
    
    def informe(actual, todos)
      Diario.instance().ocurre_evento(todos[actual].to_s + " se ve afectado por una carta de tipo porJugador y " + to_s())
    end
  end
end