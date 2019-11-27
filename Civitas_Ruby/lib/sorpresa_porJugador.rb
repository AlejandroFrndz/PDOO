require_relative 'sorpresa.rb'
require_relative 'jugador.rb'
require_relative 'diario.rb'
module Civitas
  class Sorpresa_porJugador < Sorpresa
    
    def initialize(valor, texto)
      super()
      @valor = valor
      @texto = texto
    end
    
    def aplicarAJugador(actual, todos)
      if(jugadorCorrecto(actual, todos))
        informe(actual, todos)
        pagarcobrar = Sorpresa.newPAGARCOBRAR(Tipo_Sorpresa::PAGARCOBRAR, @valor*-1, "")
        for i in 0..todos.count() do
          if(i != actual)
            pagarcobrar.aplicarAJugador(i, todos)
          end
        end
        pagarcobrar2 = Sorpresa.newPAGARCOBRAR(Tipo_Sorpresa::PAGARCOBRAR, @valor*(todos.count()-1), "")
        pagarcobrar2.aplicarAJugador(actual, todos)
      end
    end
    
    
    def informe(actual, todos)
      Diario.instance().ocurre_evento(todos[actual].to_s + " se ve afectado por una carta de tipo porJugador y " + to_s())
    end
  end
end