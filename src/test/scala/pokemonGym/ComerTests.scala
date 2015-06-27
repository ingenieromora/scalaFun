package pokemonGym

import org.scalatest.{Matchers, FlatSpec}

/**
 * @author leandro.mora
 */
class ComerTests extends FlatSpec with Matchers {

  "Un pokemon" should "come calcio y aumenta en 5 su velocidad" in {
    val charizard = Pokemon(150, 150, 30, 35, 25, 'F', Especie.Charizard(), List((FuriaDragon, 4)), 55)
    val velocidadAntes = charizard.velocidad
    val charizarDespuesDeActividad = gim.ejecutar(charizard, ComerCalcio()).pokemon
    charizarDespuesDeActividad.velocidad should be (velocidadAntes + 5)
  }

}
