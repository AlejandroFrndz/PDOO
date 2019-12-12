#encoding:utf-8
# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

require_relative "tipo_casilla.rb"
require_relative "titulo_propiedad.rb"
require_relative "sorpresa.rb"
require_relative "mazo_sorpresas"

module Civitas
  class Casilla
    attr_reader :nombre, :tituloPropiedad #añado tipo para hacer la comprabacion sobre las sorpresas
    
    def initialize( nombre = nil)
      @nombre  = nombre;
      @tituloPropiedad = nil
    end


    
    def jugadorCorrecto(actual, todos)
      actual < todos.count() && actual > -1
    end
    
    def informe(actual, todos)
      if(jugadorCorrecto(actual, todos))
        Diario.instance.ocurre_evento(todos[actual].nombre.to_s + " ha caido en la casilla " + to_s)
      end
    end
    
    
    def to_s()
      return @nombre
    end
    
    private :informe, :initialize
  end
end
