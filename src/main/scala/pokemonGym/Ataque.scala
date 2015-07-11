package pokemonGym

/**
 * @author seb
 */
// TODO detalle: en las case clases por default todos sus parámetros son "val"
case class Ataque(
  tipo: Tipo,
  maximoPuntosAtaque: Int,
  efectoColateral: (Estado => Estado) = (estado) => estado)

object Mordida extends Ataque(Normal, 30)
object Enfocarse extends Ataque(Normal, 25, (estado) => estado.map(_.aumentarVelocidad(1)))
object Reposar extends Ataque(Normal, 20, (estado) => estado.flatMap((poke) => Dormido(poke.aumentarEnergiaAlMaximo())))
object FuriaDragon extends Ataque(Dragon, 10)
object Ascuas extends Ataque(Fuego, 25)
object Impactrueno extends Ataque(Electrico, 25)
object Rain_Dish extends Ataque(Agua, 100)
