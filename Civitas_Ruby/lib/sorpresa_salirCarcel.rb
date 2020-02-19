# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
require_relative 'sorpresa.rb'
require_relative 'jugador.rb'
require_relative 'diario.rb'
module Civitas
  class Sorpresa_salirCarcel < Sorpresa
    def initialize(mazo,texto)
      super(texto)
      @mazo = mazo
    end
    
    def aplicarAJugador(actual, todos)
      if(jugadorCorrecto(actual, todos))
        informe(actual, todos)
        
        salvoconducto = false;
        for i in todos
          if(!salvoconducto)
            salvoconducto = i.tieneSalvoconducto()
          end
        end
      
        if(!salvoconducto)
          todos[actual].obtenerSalvoconducto(self)
          self.salirDelMazo();
        end
      end
    end
    
    def salirDelMazo()
      @mazo.inhabilitarCartaEspecial(self)
    end
    
    
    def usada()
      @mazo.habilitarCartaEspecial(self)
    end
    
    def informe(actual, todos)
      Diario.instance().ocurre_evento(todos[actual].to_s + " se ve afectado por una carta de tipo salirCarcel y " + to_s())
    end
    
    
  end
end
