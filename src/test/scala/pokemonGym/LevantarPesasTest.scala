package pokemonGym

import org.scalatest._
import pokemonGym._

/**
 * @author seb
 */
class LevantarPesasTest extends FlatSpec with Matchers {

  "Un pokemon" should "subir exp cuando levanta pesas" in {
    val charizard = Pokemon(150, 150, 30, 35, 25, 'F', Especie.Charizard(), List((FuriaDragon, 4)), 55)
    val experienciaAntes = charizard.experiencia
    val charizarDespuesDeActividad = gim.ejecutar(OK(charizard), LevantarPesas(10)).pokemon
    charizarDespuesDeActividad.experiencia should be (experienciaAntes + 10)
  }
  
  "Un pokemon" should "subir el doble exp cuando levanta pesas y es tipo pelea" in {
    val monkey = Pokemon(150, 150, 30, 35, 25, 'F', Especie.Monkey(), Nil, 20)
    val experienciaAntes = monkey.experiencia
    val monkeyDespuesDeActividad = gim.ejecutar(OK(monkey), LevantarPesas(10)).pokemon
    monkeyDespuesDeActividad.experiencia should be (experienciaAntes + 20)
  }
  
  "Un pokemon" should "quedar en estado invalido si es fantasma" in {
	  val gengar = Pokemon(300, 300, 1, 100, 200, 'F', Especie.Gengar(), Nil, 60)
	  val experienciaAntes = gengar.experiencia
	  val gengarDespuesDeActividad = gim.ejecutar(OK(gengar), LevantarPesas(10))
	  gengarDespuesDeActividad match {
      case Invalido(_, _) => 
      case _ => fail("El estado deberia ser invalido")
    }
  }
  
  "Un pokemon" should "paralizarse sin sumar exp cuando levanta mas de 10 kilos por cada unidad de fuerza" in {
	  val charizard = Pokemon(150, 150, 30, 35, 25, 'F', Especie.Charizard(), List((FuriaDragon, 4)), 55)
	  val experienciaAntes = charizard.experiencia
    val estadoCharizardDespuesDeActividad = gim.ejecutar(OK(charizard), LevantarPesas(360))
	  estadoCharizardDespuesDeActividad match {
		  case Paralizado(poke) => poke.experiencia shouldEqual experienciaAntes
      case _ => fail("El estado deberia ser paralizado")
	  }
  }
  
  "Un pokemon" should "pasarse a KO sin sumar exp si levanta pesas estando paralizado" in {
	  val charizard = Pokemon(150, 150, 30, 35, 25, 'F', Especie.Charizard(), Nil, 55)
    val experienciaAntes = charizard.experiencia
    val estadoCharizardDespuesDeActividad = gim.ejecutar(OK(charizard), LevantarPesas(360), LevantarPesas(3))
    estadoCharizardDespuesDeActividad match { 
      case KO(poke) => poke.experiencia should be (experienciaAntes)
      case Paralizado(poke) => poke.experiencia shouldEqual 45454
      case _ => fail("El estado deberia ser KO")
    }
  }
}

