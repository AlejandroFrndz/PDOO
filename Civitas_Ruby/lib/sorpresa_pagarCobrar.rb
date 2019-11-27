require_relative 'sorpresa.rb'
require_relative 'jugador.rb'
require_relative 'diario.rb'
module Civitas
  class Sorpresa_pagarCobrar < Sorpresa
    
    def initialize(valor, texto)
      super()
      @valor = valor
      @texto = texto
    end
    
    def aplicarAJugador(actual, todos)
      if(jugadorCorrecto(actual, todos))
        informe(actual, todos)
        todos[actual].modificarSaldo(@valor)
      end
    end
    
    def informe(actual, todos)
      Diario.instance().ocurre_evento(todos[actual].to_s + " se ve afectado por una carta de tipo pagarCobrar y " + to_s())
    end
  end
end