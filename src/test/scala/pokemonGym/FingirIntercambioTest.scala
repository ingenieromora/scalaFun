package pokemonGym

import org.scalatest.{Matchers, FlatSpec}

/**
 * @author leandro.mora
 */
class FingirIntercambioTest extends FlatSpec with Matchers {

  "Un pokemon cuya especie tiene intercambiar como condicion evolutiva" should "evolucionar" in {
    val ratata = Pokemon(1, 1, 1, 1, 1, 'F', Especie.Ratata(), List((Reposar, 400)), 1)
    val estadoDevuelto = gim.ejecutar(OK(ratata) , FingirIntercambio)
    val pokemonEsperado = Pokemon(1, 1, 1, 1, 1, 'F', Especie.Raticate(), List((Reposar, 400)), 1)
    estadoDevuelto.pokemon should be (pokemonEsperado)
  }
  "Un pokemon cuya especie tiene otra condicion evolutiva y es hembra" should "bajar diez kilos" in {
    val jigglypuff = Pokemon(90, 150, 30, 35, 25, 'F', Especie.Jigglypuff(), List((Reposar, 4)), 55)
    val estadoDevuelto = gim.ejecutar(OK(jigglypuff) , FingirIntercambio)
    val pokemonEsperado = Pokemon(90, 150, 20, 35, 25, 'F', Especie.Jigglypuff(), List((Reposar, 4)), 55)
    estadoDevuelto.pokemon should be (pokemonEsperado)
  }
  "Un pokemon cuya especie tiene otra condicion evolutiva y es macho" should "subir 1 kilo" in {
    val pikachu = Pokemon(90, 150, 30, 35, 25, 'M', Especie.Pikachu(), List((Impactrueno, 4)), 55)
    val estadoDevuelto = gim.ejecutar(OK(pikachu) , FingirIntercambio)
    val pokemonEsperado =  Pokemon(90, 150, 31, 35, 25, 'M', Especie.Pikachu(), List((Impactrueno, 4)), 55)
    estadoDevuelto.pokemon should be (pokemonEsperado)
  }
}
