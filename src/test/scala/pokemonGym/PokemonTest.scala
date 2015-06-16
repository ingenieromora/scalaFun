package pokemonGym

import org.scalatest._
import pokemonGym._

/**
 * @author seb
 */
class PokemonTest extends FlatSpec with Matchers {

  "Un pokemon" should "no conocer ataques que no son de su tipo" in {
    val ataqueAgua = (Ataque(Agua, 30), 30)
    evaluating {
    	Pokemon(30, 30, 2, 4, 3, 'F', Especie.Charmander(), List(ataqueAgua), 1)
    }  should produce[Exception]
  }
}

