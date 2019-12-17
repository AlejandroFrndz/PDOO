#encoding:utf-8
require_relative 'operaciones_juego.rb'
require_relative 'gestiones_inmobiliarias.rb'
require_relative 'civitas_juego.rb'
require_relative 'jugador.rb'
require_relative 'titulo_propiedad.rb'
require_relative 'civitas_juego.rb'
require_relative 'operacion_inmobiliaria.rb'
require_relative 'gestiones_inmobiliarias.rb'
require_relative 'diario.rb'
require_relative 'salidas_carcel.rb'
require_relative 'vista_textual.rb'
module Civitas
  class Controlador
    def initialize(jue, vist)
      @juego = jue
      @vista = vist
    end
    
    def juega()
      @vista.setCivitasJuego(@juego)
      prueba = @juego.finalDelJuego()
      
      while(!@juego.finalDelJuego())
        
        @vista.actualizarVista()
        @vista.pausa()
        
        operacion = @vista.juegoModel.siguientePaso()
        
        @vista.mostrarSiguienteOperacion(operacion)
        
        if(operacion != Operaciones_juego::PASAR_TURNO)
          @vista.mostrarEventos()
        end
        
        if(!@vista.juegoModel.finalDelJuego())
          case operacion
          when Operaciones_juego::COMPRAR
            #comprabacion de que ademas se deba de comprar que sea una calle para saber si se puede comprar
            if(@vista.juegoModel.getCasillaActual().tituloPropiedad != nil)
              if(!@vista.juegoModel.getCasillaActual().tituloPropiedad.tienePropietario())
                respuesta = @vista.comprar()
                if (respuesta == Respuestas::SI)
                  @vista.juegoModel.comprar()
                end
              end
            end
            
            #comprobacion de la casilla impuesto, la debemos de hacer aqui?
            puts "\n El siguinte paso es #{operacion}\n"
            @vista.juegoModel.siguientePasoCompletado(operacion)
            
            
          when Operaciones_juego::GESTIONAR
            @vista.gestionar()
            if(@vista.iGestion != 5)
              operaciones = [GestionesInmobiliarias::VENDER, GestionesInmobiliarias::HIPOTECAR, GestionesInmobiliarias::CANCELAR_HIPOTECA, GestionesInmobiliarias::CONSTRUIR_CASA, GestionesInmobiliarias::CONSTRUIR_HOTEL, GestionesInmobiliarias::TERMINAR]
              op_inmobiliaria = OperacionInmobiliaria.new(operaciones[@vista.iGestion],@vista.iPropiedad)
              
              case op_inmobiliaria.gestion
              when GestionesInmobiliarias::VENDER
                @vista.juegoModel.vender(op_inmobiliaria.numPropiedad)
              when GestionesInmobiliarias::HIPOTECAR
                @vista.juegoModel.hipotecar(op_inmobiliaria.numPropiedad)
              when GestionesInmobiliarias::CANCELAR_HIPOTECA
                @vista.juegoModel.cancelarHipoteca(op_inmobiliaria.numPropiedad)
              when GestionesInmobiliarias::CONSTRUIR_CASA
                @vista.juegoModel.construirCasa(op_inmobiliaria.numPropiedad)
              when GestionesInmobiliarias::CONSTRUIR_HOTEL
                @vista.juegoModel.construirHotel(op_inmobiliaria.numPropiedad)
              end
              
            else
              @vista.juegoModel.siguientePasoCompletado(operacion)
            end
            
            
          when Operaciones_juego::SALIR_CARCEL
            metodo = @vista.salirCarcel()
            if(metodo == SalidasCarcel::PAGANDO)
              exito = @vista.juegoModel.salirCarcelPagando()
            else
              exito = @vista.juegoModel.salirCarcelTirando()
            end
            
            if(!exito)
              @vista.juegoModel.siguientePasoCompletado(operacion)
            end
            
          end
        end
        
      end
      
      clasificacion = @vista.juegoModel.ranking()
    
      puts "\nCLASIFICACION FINAL"
      
      i = clasificacion.length()-1
      
      while i > -1
        puts clasificacion[i]
        i = i -1
      end
    end
    
    
    
    
  end
end