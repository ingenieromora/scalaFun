package pokemonGym

import org.scalatest._
import pokemonGym._

/**
 * @author seb
 */
class PokemonTest extends FlatSpec with Matchers {

  "Un pokemon" should "no conocer ataques que no son de su tipo" in {
    val ataqueAgua = (Ataque(Agua, 30), 30)
    // TODO no usen métodos deprecados, revisen la doc de scalatest
    // TODO no esperen que lance "Exception" (podrían ser cualquier otra excepción y no la que ustedes esperan)
    a [Exception] should be thrownBy {
    	Pokemon(30, 30, 2, 4, 3, 'F', Especie.Charmander(), List(ataqueAgua), 1)
    }
  }
}

