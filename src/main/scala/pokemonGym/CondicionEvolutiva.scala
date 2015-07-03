package pokemonGym

/**
 * @author leandro.mora
 */
abstract class CondicionEvolutiva

case class SubirDeNivel(nivel: Int) extends CondicionEvolutiva
case class Intercambiar() extends CondicionEvolutiva
case class UsarPiedra() extends CondicionEvolutiva
case class UsarPiedraLunar() extends CondicionEvolutiva
case class NoEvoluciona() extends CondicionEvolutiva