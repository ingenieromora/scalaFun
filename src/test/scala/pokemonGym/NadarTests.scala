package pokemonGym

import org.scalatest.{Matchers, FlatSpec}

/**
 * @author leandro.mora
 */
class NadarTests extends FlatSpec with Matchers {
  "Un pokemon que no es Agua y no pierde con Agua" should "nada y pierde energia y gana experiencia" in {
    val ratata = Pokemon(150, 150, 30, 35, 25, 'F', Especie.Ratata(), List((Mordida, 4)), 55)
    val ratataDespuesDeActividad = gim.ejecutar(OK(ratata), Nadar(5)).pokemon
    val pokemonEsperado = Pokemon(145, 150, 30, 35, 25, 'F', Especie.Ratata(), List((Mordida, 4)), 55, 1000)
    ratataDespuesDeActividad should be (pokemonEsperado)
  }

  "Un pokemon que pierde con Agua" should "no gana nada queda KO" in {
    val charizard = Pokemon(150, 150, 30, 35, 25, 'F', Especie.Charizard(), List((FuriaDragon, 4)), 55)
    val charizarDespuesDeActividad = gim.ejecutar(OK(charizard), Nadar(5))
    charizarDespuesDeActividad should be (KO(charizard))
  }

  "Un pokemon de tipo Agua" should "nada y pierde energia, gana experiencia y gana velocidad " in {
    val blastoise = Pokemon(150, 150, 30, 35, 25, 'F', Especie.Blastoise(), List((Rain_Dish, 4)), 55)
    val blastoiseDespuesDeActividad = gim.ejecutar(OK(blastoise), Nadar(60)).pokemon
    val pokemonEsperado = Pokemon(90, 150, 30, 35, 26, 'F', Especie.Blastoise(), List((Rain_Dish, 4)), 55, 12000)
    blastoiseDespuesDeActividad should be (pokemonEsperado)
  }
}
