#!/bin/env ruby
# encoding: utf-8

require_relative 'jugador.rb'
require_relative 'diario.rb'
require_relative 'dado.rb'
require_relative 'gestor_estados.rb'
require_relative 'tablero.rb'
require_relative 'sorpresa.rb'

module Civitas
  class CivitasJuego
    def initialize(nombres)
      @jugadores = []
      
      for i in (0..nombres.count()-1)
        jugador = Jugador.new(nombres[i])
        @jugadores.push(jugador)
      end
      
      @gestorEstados = Gestor_estados.new()
      @estado = @gestorEstados.estado_inicial()
      
      @indiceJugadorActual = Dado.instance.quienEmpieza(@jugadores.size())
      @mazo = MazoSorpresas.new()
      
      inicializarTablero(@tablero)
      inicializarMazoSorpresas(@mazo)
      
    end
    
    def inicializarTablero(tablero)
      @tablero = Tablero.new(14)

      @tablero.añadeCasilla(Casilla.newCalle(TituloPropiedad.new("Netflix", 10.0, 1.75, 150.0, 300.0, 250.0)))
      @tablero.añadeCasilla(Casilla.newCalle(TituloPropiedad.new("Intel", 30.0, 1.5, 120.0, 200.0, 150.0)))
      @tablero.añadeCasilla(Casilla.newCalle(TituloPropiedad.new("Samsung", 50.0, 2.75, 250.0, 310.0, 350.0)))
      @tablero.añadeJuez()
      @tablero.añadeCasilla(Casilla.newCalle(TituloPropiedad.new("Oneplus", 50.0, 2.75, 250.0, 310.0, 350.0)))
      @tablero.añadeCasilla(Casilla.newSorpresa(@mazo, "Sorpresa"))
      @tablero.añadeCasilla(Casilla.newCalle(TituloPropiedad.new("Tencent", 50.0, 2.75, 250.0, 310.0, 350.0)))
      @tablero.añadeCasilla(Casilla.newCalle(TituloPropiedad.new("SpaceX", 50.0, 2.75, 250.0, 310.0, 350.0)))
      @tablero.añadeCasilla(Casilla.newImpuesto(1000.0, "Impuesto"))
      @tablero.añadeCasilla(Casilla.newCalle(TituloPropiedad.new("Facebook", 50.0, 2.75, 250.0, 310.0, 350.0)))
      @tablero.añadeCasilla(Casilla.newSorpresa(@mazo, "Sorpresa"))
      @tablero.añadeCasilla(Casilla.newCalle(TituloPropiedad.new("Alphabet", 50.0, 2.75, 250.0, 310.0, 350.0)))
      @tablero.añadeCasilla(Casilla.newCalle(TituloPropiedad.new("Tesla", 50.0, 2.75, 250.0, 310.0, 350.0)))
      @tablero.añadeCasilla(Casilla.newSorpresa(@mazo, "Sorpresa"))
      @tablero.añadeCasilla(Casilla.newCalle(TituloPropiedad.new("Amazon", 50.0, 2.75, 250.0, 310.0, 350.0)))
      @tablero.añadeCasilla(Casilla.newCalle(TituloPropiedad.new("Apple", 50.0, 2.75, 250.0, 310.0, 350.0)))
      @tablero.añadeCasilla(Casilla.newDescanso("Silicon valley"))
      @tablero.añadeCasilla(Casilla.newCalle(TituloPropiedad.new("Microsoft", 50.0, 2.75, 250.0, 310.0, 350.0)))

    end
    
    def inicializarMazoSorpresas(mazo)
      @mazo.alMazo(Sorpresa.newPAGARCOBRAR(75, "Tu inversion en una startup sale bien, cobra 75€"))
      @mazo.alMazo(Sorpresa.newPAGARCOBRAR(-75,  "Te han pillado evadiendo impuestos, paga una multa de 75€"))
      @mazo.alMazo(Sorpresa.newIRCASILLA(@tablero, 9, "Ve a la sede de #{@tablero.getCasilla(9).nombre}"))
      @mazo.alMazo(Sorpresa.newIRCASILLA(@tablero, 17, "Ve a la sede de #{@tablero.getCasilla(17).nombre}"))
      @mazo.alMazo(Sorpresa.newIRCASILLA(@tablero, @tablero.numCasillaCarcel, "Han descubierto una de tus fabricas cladestinas en Vietnam. Ve a la cárcel"))
      @mazo.alMazo(Sorpresa.newPORCASAHOTEL(320, "Nuevos inversores entran en juego. Recibe 320€ por cada casas y hotel que poseas"))
      @mazo.alMazo(Sorpresa.newPORCASAHOTEL(-320, "Sube el IBI. Paga 320€ por cada casa y hotel que poseas"))
      @mazo.alMazo(Sorpresa.newPORJUGADOR(250, "Una de tus patentes se ha vuelto muy popular. Recibe 250€ de cada jugador"))
      @mazo.alMazo(Sorpresa.newPORJUGADOR(-250, "Tus competidores presentan una demanda colectiva contra ti y el juez les da la razón. Paga 250€ a cada jugador"))
      @mazo.alMazo(Sorpresa.newIRCARCEL(@tablero))
      @mazo.alMazo(Sorpresa.newSALIRCARCEL(@mazo))
    end
    
    def actualizarInfo()
      puts "#{@jugadores[@indiceJugadorActual].to_s()} "
    end
    
    #metodo privado
    def contabilizarPasosPorSalida(jugadorActual)
      while @tablero.getPorSalida() > 0
        @jugadores[@indiceJugadorActual].pasaPorSalida()
      end
    end
    
    #privado
    def pasarTurno()
      @indiceJugadorActual = (@indiceJugadorActual+1)%@jugadores.length()
    end
    
    def siguientePasoCompletado(operacion)
      @estado = @gestorEstados.siguiente_estado(@jugadores[@indiceJugadorActual],@estado, operacion)
    end
    
    def construirCasa(ip)
      @jugadores[@indiceJugadorActual].construirCasa(ip)
    end
    
    def construirHotel(ip)
      @jugadores[@indiceJugadorActual].construirHotel(ip)
    end
    
    def vender(ip)
      @jugadores[@indiceJugadorActual].vender(ip)
    end
    
    def hipotecar(ip)
      @jugadores[@indiceJugadorActual].hipotecar(ip)
    end
    
    def cancelarHipoteca(ip)
      @jugadores[@indiceJugadorActual].cancelarHipoteca(ip)
    end
    
    def salirCarcelPagando()
      @jugadores[@indiceJugadorActual].salirCarcelPagando()
    end
    
    def salirCarcelTirando()
      @jugadores[@indiceJugadorActual].salirCarcelTirando()
    end
    
    def finalDelJuego()
      for i in (0..@jugadores.length-1)
        if @jugadores[i].enBancarrota()
          return true
        end
      end
      
      return false
    end
    
    def ranking()
      @jugadores = @jugadores.sort()
      @jugadores
    end
    
    def getCasillaActual()
      @tablero.getCasilla(@jugadores[@indiceJugadorActual].numCasillaActual)
    end
    
    def getJugadorActual()
      @jugadores[@indiceJugadorActual]
    end
    
    def infoJugadorTexto()
      @jugadores[@indiceJugadorActual].to_s()
    end
    
    def avanzaJugador()
      jugadorActual = @jugadores[@indiceJugadorActual]
      posicionActual = jugadorActual.numCasillaActual
      tirada = Dado.instance.tirar
      posicionNueva = @tablero.nuevaPosicion(posicionActual, tirada)
      casilla = @tablero.getCasilla(posicionNueva)
      contabilizarPasosPorSalida(jugadorActual)
      jugadorActual.moverACasilla(posicionNueva)
      casilla.recibeJugador(@indiceJugadorActual, @jugadores)
      contabilizarPasosPorSalida(jugadorActual)
    end
    
    def siguientePaso
      jugadorActual = @jugadores[@indiceJugadorActual]
      operacion = @gestorEstados.operaciones_permitidas(jugadorActual, @estado)
      
      puts "\nSIGUIENTE PASO SEGUN SIGUIENTEPASO ES #{operacion.to_s}"
      
      case operacion
      when Operaciones_juego::PASAR_TURNO
        pasarTurno()
        siguientePasoCompletado(operacion)
      when Operaciones_juego::AVANZAR
        avanzaJugador()
        siguientePasoCompletado(operacion)
      end
      
      return operacion
    end
    
    def comprar
      jugadorActual = @jugadores[@indiceJugadorActual]
      numeroCasillaActual = jugadorActual.numCasillaActual
      
      casilla = @tablero.getCasilla(numeroCasillaActual)
      titulo = casilla.tituloPropiedad
      res = jugadorActual.comprar(titulo)
    end
    
    private :contabilizarPasosPorSalida, :inicializarMazoSorpresas, :inicializarTablero, :pasarTurno, :avanzaJugador
  end
end