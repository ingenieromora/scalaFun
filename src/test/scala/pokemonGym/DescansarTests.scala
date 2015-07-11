package pokemonGym

import org.scalatest.{Matchers, FlatSpec}

/**
 * @author leandro.mora
 */
class DescansarTests extends FlatSpec with Matchers {

  "Un pokemon" should "OK, descansa y recupera al maximo sus puntos de ataque" in {
    val charizard = Pokemon(150, 150, 30, 35, 25, 'F', Especie.Charizard(), List((FuriaDragon, 5), (Ascuas, 10)), 55)
    val charizarDespuesDeActividad = gim.ejecutar(OK(charizard), Descansar)
    val ataquesEsperados = List((FuriaDragon,10), (Ascuas, 25))
    charizarDespuesDeActividad.pokemon.ataques should be (ataquesEsperados)

    val charizardEsperado = Pokemon(150, 150, 30, 35, 25, 'F', Especie.Charizard(), ataquesEsperados, 55)
    charizarDespuesDeActividad should be (OK(charizardEsperado))
  }

  "Un pokemon" should "OK, descansa y como tiene menos del 50% de energia, recupera al maximo sus puntos de ataque y se queda dormido" in {
    val charizard = Pokemon(50, 150, 30, 35, 25, 'F', Especie.Charizard(), List((FuriaDragon, 5), (Ascuas, 10)), 55)
    val charizarDespuesDeActividad = gim.ejecutar(OK(charizard), Descansar)
    val ataquesEsperados = List((FuriaDragon,10), (Ascuas, 25))
    charizarDespuesDeActividad.pokemon.ataques should be (ataquesEsperados)

    val charizardEsperado = Pokemon(50, 150, 30, 35, 25, 'F', Especie.Charizard(), ataquesEsperados, 55)
    charizarDespuesDeActividad should be (Dormido(charizardEsperado))
  }

  "Un pokemon" should "envenenado, descansa y recupera al maximo sus puntos de ataque" in {
    val charizard = Pokemon(150, 150, 30, 35, 25, 'F', Especie.Charizard(), List((FuriaDragon, 5), (Ascuas, 10)), 55)
    val charizarDespuesDeActividad = gim.ejecutar(Envenenado(charizard), Descansar)
    val ataquesEsperados = List((FuriaDragon,10), (Ascuas, 25))
    charizarDespuesDeActividad.pokemon.ataques should be (ataquesEsperados)

    val charizardEsperado = Pokemon(150, 150, 30, 35, 25, 'F', Especie.Charizard(), ataquesEsperados, 55)
    charizarDespuesDeActividad should be (Envenenado(charizardEsperado))
  }
}
