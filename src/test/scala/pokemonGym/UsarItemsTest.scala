package pokemonGym

import org.scalatest.{Matchers, FlatSpec}

/**
 * @author leandro.mora
 */
class UsarItemsTest extends FlatSpec with Matchers {

//Usar Pocion
  "Un pokemon" should "usar pocion y curarse 20 puntos" in {
    val charizard = Pokemon(130, 150, 30, 35, 25, 'F', Especie.Charizard(), List((FuriaDragon, 4)), 55)
    val charizarDespuesDeActividad = gim.ejecutar(OK(charizard) , UsarPocion()).pokemon
    charizarDespuesDeActividad.energia should be (150)
  }

  "Un pokemon" should "usar pocion curarse 50 puntos como maximo" in {
    val charizard = Pokemon(90, 150, 30, 35, 25, 'F', Especie.Charizard(), List((FuriaDragon, 4)), 55)
    val charizarDespuesDeActividad = gim.ejecutar(OK(charizard) , UsarPocion()).pokemon
    charizarDespuesDeActividad.energia should be (140)
  }

//Toma antidoto
  "Un pokemon" should "envenado, toma antidoto y se cura" in {
    val charizard = Pokemon(90, 150, 30, 35, 25, 'F', Especie.Charizard(), List((FuriaDragon, 4)), 55)
    val estadoDespuesDeActividad = gim.ejecutar(Envenenado(charizard) , UsarAntidoto())
    estadoDespuesDeActividad should be (OK(charizard))
  }

  "Un pokemon" should "ok, toma antidoto y no pasa nada" in {
    val charizard = Pokemon(90, 150, 30, 35, 25, 'F', Especie.Charizard(), List((FuriaDragon, 4)), 55)
    val estadoDespuesDeActividad = gim.ejecutar(KO(charizard) , UsarAntidoto())
    estadoDespuesDeActividad should be (KO(charizard))
  }

//Toma Ether tests
  "Un pokemon" should "estado Envenenado, toma ether y se cura" in {
    val charizard = Pokemon(90, 150, 30, 35, 25, 'F', Especie.Charizard(), List((FuriaDragon, 4)), 55)
    val estadoDespuesDeActividad = gim.ejecutar(Envenenado(charizard) , UsarEther())
    estadoDespuesDeActividad should be (OK(charizard))
  }

  "Un pokemon" should "estado KO, toma ether y no pasa nada" in {
    val charizard = Pokemon(90, 150, 30, 35, 25, 'F', Especie.Charizard(), List((FuriaDragon, 4)), 55)
    val estadoDespuesDeActividad = gim.ejecutar(KO(charizard) , UsarEther())
    estadoDespuesDeActividad should be (KO(charizard))
  }

  //Usar Piedra Tests

  "Pikachu" should "usar piedra electrica y evolucionar" in {
    val pikachu = Pokemon(90, 150, 30, 35, 25, 'M', Especie.Pikachu(), List((Impactrueno, 4)), 55)
    val estadoDespuesDeActividad = gim.ejecutar(OK(pikachu), UsarPiedraParaEvolucionar(Electrico))
    val evolucionEsperada = Pokemon(90, 150, 30, 35, 25, 'M', Especie.Raichu(), List((Impactrueno, 4)), 55)
    estadoDespuesDeActividad.pokemon should be (evolucionEsperada)
  }

  "Pikachu" should "usar piedra Agua y no hacer nada" in {
    val pikachu = Pokemon(90, 150, 30, 35, 25, 'M', Especie.Pikachu(), List((Impactrueno, 4)), 55)
    val estadoDespuesDeActividad = gim.ejecutar(OK(pikachu), UsarPiedraParaEvolucionar(Agua))
    estadoDespuesDeActividad should be (OK(pikachu))
  }

  "Pikachu" should "usar piedra Tierra y no Evolucionar y quedar Envenenado" in {
    val pikachu = Pokemon(90, 150, 30, 35, 25, 'M', Especie.Pikachu(), List((Mordida, 4)), 55)
    val estadoDespuesDeActividad = gim.ejecutar(OK(pikachu), UsarPiedraParaEvolucionar(Tierra))
    estadoDespuesDeActividad should be (Envenenado(pikachu))
  }

  "Jigglypuff" should "usar piedra Lunar y evoluciona" in {
    val jigglypuff = Pokemon(90, 150, 30, 35, 25, 'M', Especie.Jigglypuff(), List((Reposar, 4)), 55)
    val estadoDespuesDeActividad = gim.ejecutar(OK(jigglypuff), UsarPiedraParaEvolucionar(Lunar))
    val wigglytuff = Pokemon(90, 150, 30, 35, 25, 'M', Especie.Wigglytuff(), List((Reposar, 4)), 55)
    estadoDespuesDeActividad should be (OK(wigglytuff))
  }

  "Wigglytuff" should "usar piedra Lunar y no evoluciona" in {
    val wigglytuff = Pokemon(90, 150, 30, 35, 25, 'M', Especie.Wigglytuff(), List((Reposar, 4)), 55)
    val estadoDespuesDeActividad = gim.ejecutar(OK(wigglytuff), UsarPiedraParaEvolucionar(Lunar))
    estadoDespuesDeActividad should be (OK(wigglytuff))
  }

}
