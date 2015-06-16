package pokemonGym

/**
 * @author seb
 */
abstract class Actividad

case class RealizarAtaque(ataque: Ataque) extends Actividad
case class LevantarPesas(kilos: Int) extends Actividad