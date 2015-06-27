package pokemonGym

import org.scalatest.{Matchers, FlatSpec}

/**
 * @author leandro.mora
 */
class UsarItemsTest extends FlatSpec with Matchers {

  "Un pokemon" should "curarse 20 puntos" in {
    val charizard = Pokemon(130, 150, 30, 35, 25, 'F', Especie.Charizard(), List((FuriaDragon, 4)), 55)
    val charizarDespuesDeActividad = gim.ejecutar(charizard,OK(charizard) , UsarPocion()).pokemon
    charizarDespuesDeActividad.energia should be (150)
  }

  "Un pokemon" should "curarse 50 puntos como maximo" in {
    val charizard = Pokemon(90, 150, 30, 35, 25, 'F', Especie.Charizard(), List((FuriaDragon, 4)), 55)
    val charizarDespuesDeActividad = gim.ejecutar(charizard,OK(charizard) , UsarPocion()).pokemon
    charizarDespuesDeActividad.energia should be (140)
  }

  "Un pokemon" should "envenado, toma antidoto y se cura" in {

  }

  "Un pokemon" should "ok, toma antidoto y no pasa nada" in {

  }
}
