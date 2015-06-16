package pokemonGym

import org.scalatest._
import pokemonGym._

/**
 * @author seb
 */
class NadarTest extends FlatSpec with Matchers {
  
  "Un pokemon" should "Disminuir energia y aumentar velocidad por nadar siendo Tipo Agua Primario" in {
    val magikarp = Pokemon(20, 10, 10, 4, 3, 'M', Especie.Magikarp, Nil, 1)
    var estadoMagic = gim.ejecutar(magikarp, Nadar(10))
    estadoMagic match {
      case OK(poke) => {
        poke match {
          case Pokemon(energia, _, _, _, velocidad, _, _, _, _, exp) => {
            energia shouldEqual 10
            velocidad shouldEqual 3
            exp shouldEqual 2000
          }
        }
      }
    }
  }
  
  "Un pokemon" should "Disminuir energia y aumentar velocidad por nadar siendo Tipo Agua Secundario" in {
    val poliwhirl = Pokemon(20, 10, 10, 4, 3, 'M', Especie.Poliwhirl, Nil, 1)
    var estadoPoliwhirl = gim.ejecutar(poliwhirl, Nadar(10))
    estadoPoliwhirl match {
      case OK(poke) => {
        poke match {
          case Pokemon(energia, _, _, _, velocidad, _, _, _, _, exp) => {
            energia shouldEqual 10
            velocidad shouldEqual 3
            exp shouldEqual 2000
          }
        }
      }
    }
  }
  
  "Un pokemon" should "KO por nada siendo tipo tierra Tierra" in {
    val deeglet = Pokemon(20, 10, 10, 4, 3, 'M', Especie.Deeglet, Nil, 1)
    var estadoDeeglet = gim.ejecutar(deeglet, Nadar(10))
    estadoDeeglet match {
      case KO(poke) => {
          poke shouldEqual deeglet 
      }
    }
  }
}
  

