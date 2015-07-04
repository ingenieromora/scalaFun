package pokemonGym

/**
 * @author seb
 */
case class Rutina(nombre: String, actividades: List[Actividad] )

object RutinaConDescansar extends Rutina("rutina con descansar", List(UsarAntidoto(), Descansar(), UsarEther()))
object RutinaAtaqueFuriaDragon extends Rutina("rutina con furia dragon", List(LevantarPesas(1), RealizarAtaque(FuriaDragon)))
object RutinaConPesas1 extends Rutina("rutina con pesas 1", List(UsarAntidoto(), LevantarPesas(12)))
object RutinaConPesas2 extends Rutina("rutina con pesas 2", List(LevantarPesas(12), Descansar(), RealizarAtaque(FuriaDragon)))
object RutinaConPesas3 extends Rutina("rutina con pesas 3", List(LevantarPesas(12), RealizarAtaque(FuriaDragon)))
