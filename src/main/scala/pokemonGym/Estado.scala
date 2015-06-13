package pokemonGym

/**
 * @author seb
 */
abstract class Estado()

case object Envenenado extends Estado
case object Dormido extends Estado
case object Paralizado extends Estado
case object KO extends Estado
case object OK extends Estado