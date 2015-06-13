package pokemonGym

/**
 * @author seb
 */
case class Ataque(val tipo: Tipo, var puntosAtaque: Int,
                  val efectoColateral:(Pokemon => Pokemon) = {(pokemon: Pokemon) => pokemon}) {
  
  def usarAtaque: Ataque = {
    copy(puntosAtaque = puntosAtaque - 1)
  }
}

object Mordida extends Ataque(Normal, 30)
object Enfocarse extends Ataque(Normal, 30, (pokemon: Pokemon) => pokemon.aumentarVelocidad(1))
object Reposar extends Ataque(Normal, 30, (pokemon: Pokemon) => pokemon.aumentarVelocidad(1))