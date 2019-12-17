#encoding:utf-8
# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

require_relative 'mazo_sorpresas.rb'
require_relative 'tablero.rb'

module Civitas
  class Sorpresa
    
    def initialize(texto)
      @texto = texto
    end

    def jugadorCorrecto(actual, todos)
      actual < todos.count() && actual > -1
    end

    def to_s()
      @texto;
    end
    
  end
end

