package pokemonGym

/**
 * @author seb
 */
case class Especie(
    val resistenciaEvolutiva: Int,
    val pesoMaximo: Int,
    val tipoPrincipal: Tipo,
    val tipoSecundario: Tipo,
    val aumentoVelocidad: Int,
    val aumentoPeso: Int,
    val aumentoFuerza: Int,
    val aumentoEnergiaMaxima: Int,
    val piedraEvolutiva: String = "", //Por defecto no usan piedra para evolucionar
    val evolucion: String = "") //Por defecto no tiene evolucion
    {
    }

object Especie {
  def Charmander() = {
    Especie(350, 50, Fuego, null, 50, 50, 50, 50)
  }
  def Charizard() = {
    Especie(400, 100, Fuego, Dragon, 60, 60, 60 , 60)
  }
  def Ratata() = {
    Especie(1, 1, Normal, null, 1, 1, 1, 1)
  }
  def Monkey() = {
	  Especie(25, 6, Pelea, null, 3, 4, 5, 6)
  }
  def Gengar() = {
	  Especie(390, 1, Fantasma, null, 45, 45, 50, 60)
  }

  def Nidorino() = {
    Especie(500, 60, Veneno, null, 20, 5, 10, 50, "Lunar","Nidoking")
  }

  def Pikachu() = {
    Especie(300, 30, Electrico, null, 5, 5, 10, 50, "Usar Piedra","Raichu")
  }
}