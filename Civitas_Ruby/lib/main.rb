#encoding:utf-8
# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

require_relative 'tipo_casilla.rb'
require_relative 'estados_juego.rb'
require_relative 'tablero.rb'
require_relative 'mazo_sorpresas.rb'
require_relative 'dado.rb'
require_relative 'jugador.rb'
require_relative 'titulo_propiedad.rb'
require_relative 'casilla.rb'
require_relative 'diario.rb'
require_relative 'gestor_estados.rb'
require_relative 'operaciones_juego.rb'
require_relative 'sorpresa.rb'
require_relative 'tipo_sorpresa.rb'
require_relative 'civitas_juego.rb'
require_relative 'vista_textual.rb'
require_relative 'controlador.rb'



module Civitas
  nombres = []
  #nombres.push("Nathan")
  #nombres.push("Sully")
  #nombres.push("Sam")
  nombres.push("Elena")
  
  juego = CivitasJuego.new(nombres)
  Dado.instance.setDebug(true)
  vista = Vista_textual.new()
  controlador = Controlador.new(juego,vista)
  
  controlador.juega()
  
end