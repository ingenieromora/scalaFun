package pokemonGym

/**
 * @author seb
 */
package object gim {
  
  def dameLaMejorRutina(estadoInicial: Estado, 
                        funcionCriterio: (Estado => Int),
                        listaDeRutinas: Rutina*) : String = {
    val listaDeEstadosDeRutinas = listaDeRutinas.map {
      rutina => ejecutar(estadoInicial, rutina)
    }
    val listaDeEstadosOrdenadosSegunCriterio = listaDeEstadosDeRutinas.filter {
      estado => estado match {
        case Invalido(_,_) => false
        case _ => true
      }
    }.sortBy(funcionCriterio)
    if (listaDeEstadosOrdenadosSegunCriterio.isEmpty) {
      null
    } else {
      val mejorEstado = listaDeEstadosOrdenadosSegunCriterio.last
      val indiceDelMejorEstado = listaDeEstadosDeRutinas.indexOf(mejorEstado)
      listaDeRutinas(indiceDelMejorEstado).nombre
    }
  }
  
  def ejecutar(estadoInicial : Estado, rutina: Rutina): Estado = {
    ejecutar(estadoInicial, rutina.actividades.toSeq : _ *)
  }
  
  def ejecutar(estadoInicial : Estado, listaDeActividades: Actividad*): Estado = {
    listaDeActividades.foldLeft(estadoInicial) { (resultadoAnterior, actividadActual) => {

      val estadoActual =  resultadoAnterior match {
        case a @ Dormido(pok, 0) => OK(pok)
        case a @ Dormido(pok, cont) => Dormido(pok, cont - 1)
        case _ => resultadoAnterior
      }
      
      val estadoDespuesDeActividad: Estado = actividadActual match {
        case RealizarAtaque(ataque @ Ataque(tipo, _, efecto)) if estadoActual.pokemon.conoceAtaque(ataque) && estadoActual.pokemon.puntosDeAtaque(ataque) > 0 =>
          val estadoDspDeRealizarAtaque = estadoActual.map(pokemon => {
              val expGanada = tipo match {
                case Dragon => 80
                case pokemon.especie.tipoPrincipal => 50
                case pokemon.especie.tipoSecundario =>
                  pokemon.genero match {
                    case 'F' => 40
                    case 'M' => 20
                }
              }
              pokemon.usarAtaque(ataque).ganarExperiencia(expGanada)
            })
          efecto(estadoDspDeRealizarAtaque)
          
        case RealizarAtaque(ataque) => estadoActual.flatMap(poke => Invalido(poke,
            "El pokemon quiso ejecutar el ataque " + ataque.toString() + " que es desconocido o no tiene puntos de ataque"))
        
        case LevantarPesas(kilos) => {
          if (estadoActual.pokemon.esDelTipo(Fantasma)) {
            estadoActual.flatMap( poke => Invalido(poke, "Los pokemons del tipo fantasma"))
          } else {
            estadoActual match {
              case estado @ Paralizado(_) => KO(estado.pokemon)
              case estado @ _ => {
                if (kilos > 10 * estado.pokemon.fuerza) {
                  estado.flatMap( poke => Paralizado(poke))
                } else {
                  val experienciaGanada = if (estado.pokemon.esDelTipo(Pelea)) 2 * kilos else kilos
                  estado.map( poke => poke.ganarExperiencia(experienciaGanada))
                }
              }
            }
          }
        }

        case Nadar(tiempo: Int) => {
          if (Agua.leGanaA(estadoActual.pokemon.especie.tipoPrincipal))
            estadoActual.flatMap(pokemon => KO(pokemon))
          else
            estadoActual.map(poke => poke.nadar(tiempo))
        }

        case UsarPocion() => {
          estadoActual.map(pokemon => pokemon.usarPocion())
        }

        case ComerCalcio() => {
          estadoActual.map(pokemon => pokemon.comerCalcio())
        }

        case ComerZinc() => {
          estadoActual.map(pokemon => pokemon.comerZinc())
        }

        case UsarAntidoto() => {
          estadoActual match {
            case estado @ Envenenado(_) => estado.flatMap(poke => OK(poke))
            case estado @ _ => estado
          }
        }

        case UsarEther() => {
          estadoActual match {
            case estado @ KO(_) => estado
            case estado => estado.flatMap(poke => OK(poke))
          }
        }

        case Descansar() => {
          estadoActual match {
            case estado @ OK(_) =>
              if( estado.pokemon.energia < estado.pokemon.energiaMaxima / 2){
                estado.flatMap(poke => Dormido(poke.recobrarPuntosAtaque()))
              } else {
                estado.map(poke => poke.recobrarPuntosAtaque())
              }
            case estado => estado.map(poke => poke.recobrarPuntosAtaque())
          }
        }

        case UsarPiedraParaEvolucionar(tipoPiedra: Tipo) =>
            estadoActual.flatMap(poke => {
              if(tipoPiedra.leGanaA(poke.especie.tipoPrincipal) ||
                  tipoPiedra.leGanaA(poke.especie.tipoSecundario)){
                Envenenado(poke)
              } else {
                estadoActual.map(poke => poke.usarPiedra(tipoPiedra))
              }
            }
          )

        case FingirIntercambio() => estadoActual.map(poke => {
          poke.especie.condicionEvolutiva match {
            case Intercambiar() => poke.copy(especie= poke.especie.evolucion)
            case _ => if(poke.genero.equals('M')) poke.copy(peso = poke.peso + 1) else poke.copy(peso = poke.peso - 10)
          }
        })
      }

      if (estadoDespuesDeActividad.pokemon.valido()) {
        estadoDespuesDeActividad
      } else {
        Invalido(estadoDespuesDeActividad.pokemon, "Las caracteristicas del pokemon son invalidas")
      }
    }}
  }
}