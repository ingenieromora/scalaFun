package pokemonGym

/**
 * @author seb
 */
case class Ataque(
  tipo: Tipo,
  maximoPuntosAtaque: Int,
  efectoColateral: (Estado => Estado) = (estado) => estado)

object Mordida extends Ataque(Normal, 30)
object Enfocarse extends Ataque(Normal, 25, (estado) => estado.map((poke) => poke.aumentarVelocidad(1)))
object Reposar extends Ataque(Normal, 20, (estado) => estado.flatMap((poke) => Dormido(poke.aumentarEnergiaAlMaximo())))
object FuriaDragon extends Ataque(Dragon, 10)
object Ascuas extends Ataque(Fuego, 25)
object Impactrueno extends Ataque(Electrico, 25)
object Rain_Dish extends Ataque(Agua, 100)
