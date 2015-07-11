package pokemonGym

import scala.util.Try

/**
 * @author seb
 */

trait Actividad {
  def realizarActividad(estado:Estado) : Estado
}

case class RealizarAtaque(ataque: Ataque) extends Actividad {
  def realizarActividad(estado:Estado) : Estado = {
    ataque match {
      case ataque @ Ataque(tipo, _, efecto) if estado.pokemon.conoceAtaque(ataque) && estado.pokemon.puntosDeAtaque(ataque) > 0 =>
        val estadoDespuesAtaque = estado.map(pokemon => {
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
        efecto(estadoDespuesAtaque)
      
      case ataque => estado.flatMap(poke => Invalido(poke,
          "El pokemon quiso ejecutar el ataque " +
          ataque.toString() +
          " que es desconocido o no tiene puntos de ataque"))
    }
  }
}

case class AprenderAtaque(ataque : Ataque) extends Actividad {
  def realizarActividad(estado:Estado) : Estado = {
    ataque.tipo match {
      case Normal => estado.map(poke => {
        poke.copy(ataques = (ataque, ataque.maximoPuntosAtaque) :: poke.ataques)
      })

      case tipo if estado.pokemon.esDelTipo(tipo) => estado.map(poke => {
        poke.copy(ataques = (ataque, ataque.maximoPuntosAtaque) :: poke.ataques)
      })
      
      case _ => estado.flatMap(pokemon => KO(pokemon))
    }

  }
}

case class LevantarPesas(kilos: Int) extends Actividad {
  def realizarActividad(estado:Estado) : Estado = {
    if (estado.pokemon.esDelTipo(Fantasma)) {
      estado.flatMap(poke => Invalido(poke, "Los pokemons del tipo fantasma"))
    } else {
      estado match {
        case Paralizado(_) => KO(estado.pokemon)
        case _ => {
          if (kilos > 10 * estado.pokemon.fuerza) {
            estado.flatMap(poke => Paralizado(poke))
          } else {
            val experienciaGanada = if (estado.pokemon.esDelTipo(Pelea)) 2 * kilos else kilos
            estado.map(_.ganarExperiencia(experienciaGanada))
          }
        }
      }
    }
  }
}

case class UsarPiedraParaEvolucionar(tipoPiedra: Tipo) extends Actividad {
  def realizarActividad(estado:Estado) : Estado = {
    estado.flatMap(poke => {
        if(tipoPiedra.leGanaA(poke.especie.tipoPrincipal) ||
           tipoPiedra.leGanaA(poke.especie.tipoSecundario)) {

          Envenenado(poke)
        } else {
          estado.map(_.usarPiedra(tipoPiedra))
        }
      }
    )
  }
}

case class Nadar(tiempo: Int) extends Actividad {
  def realizarActividad(estado:Estado) : Estado = {
    if (Agua.leGanaA(estado.pokemon.especie.tipoPrincipal)) { // TODO: falta el tipo secundario
      estado.flatMap(pokemon => KO(pokemon))
    } else {
      estado.map(_.nadar(tiempo))
    }
  }
}

case object UsarPocion extends Actividad {
  def realizarActividad(estado:Estado) : Estado = {
    estado.map(_.usarPocion())
  }
}

case object ComerCalcio extends Actividad {
  def realizarActividad(estado:Estado) : Estado = {
    estado.map(_.comerCalcio())
  }
}

case object ComerZinc extends Actividad {
  def realizarActividad(estado:Estado) : Estado = {
    estado.map(_.comerZinc())
  }
}

case object UsarAntidoto extends Actividad {
  def realizarActividad(estado:Estado) : Estado = {
    estado match {
      case Envenenado(_) => estado.flatMap(poke => OK(poke))
      case _ => estado
    }
  }
}

case object UsarEther extends Actividad {
  def realizarActividad(estado:Estado) : Estado = {
    estado match {
      case KO(_) => estado
      case _ => estado.flatMap(poke => OK(poke))
    }
  }
}

case object Descansar extends Actividad {
  def realizarActividad(estado:Estado) : Estado = {
    estado match {
      case OK(_) =>
        if(estado.pokemon.energia < estado.pokemon.energiaMaxima / 2){
          estado.flatMap(poke => Dormido(poke.recobrarPuntosAtaque()))
        } else {
          estado.map(_.recobrarPuntosAtaque())
        }
      case _ => estado.map(_.recobrarPuntosAtaque())
    }
  }
}

case object FingirIntercambio extends Actividad {
  def realizarActividad(estado:Estado) : Estado = {
    estado.map(poke => {
      poke.especie.condicionEvolutiva match {
        case Intercambiar => poke.copy(especie = poke.especie.evolucion.get)
        case _ => if (poke.genero.equals('M')) {
          poke.copy(peso = poke.peso + 1)
        } else {
          poke.copy(peso = poke.peso - 10)
        }
      }
    })
  }
}

object ComerHierro extends Actividad {
  def realizarActividad(estado:Estado) : Estado = {
    estado.map(poke => {
      poke.copy(fuerza = poke.fuerza + 5)
    })
  }
}