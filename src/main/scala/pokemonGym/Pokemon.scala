package pokemonGym

/**
 * Created by leandromoras on 6/8/15.
 */
case class Pokemon(
		val energia: Int,
		val energiaMaxima: Int,
		val peso: Int,
		val fuerza: Int,
		val velocidad: Int,
    val genero: Char,
    val especie: Especie,
    val ataques: List[(Ataque, Int)],
    val nivel: Int = 1,
    val experiencia: Int = 0) {
  
  require(validarAtaques(), "Los ataques no son ni del tipo principal $especie.tipoPrincipal" +
                            " ni del tipo secundario $especie.tipoSecundario" )

  val MAXIMA_ENERGIA_RECOBRADA = 50

  def ganarExperiencia(exp: Int) : Pokemon = {
    val expParaSubirNivel = 2 * (nivel - 1) * especie.resistenciaEvolutiva + especie.resistenciaEvolutiva
    val expRestante = expParaSubirNivel - experiencia
    if(exp >= expRestante){
      copy(experiencia = expParaSubirNivel).subirDeNivel().ganarExperiencia(exp - expRestante)
    } else {
      copy(experiencia = experiencia + exp)
    }
  }
  
  def conoceAtaque(ataque: Ataque): Boolean = {
    !(ataques.find { case (atq, _) => atq == ataque }).isEmpty
  }
  
  def puntosDeAtaque(ataque: Ataque): Int = {
    ataque match {
      case atq if conoceAtaque(atq) => (ataques.find { case (atq, _) => atq == ataque }
                                               .map { case (_, puntos) => puntos}).get
      case _ => 0
    }
  }
  
  def usarAtaque(ataque: Ataque): Pokemon = {
    if (conoceAtaque(ataque)) {
      copy(ataques = (ataque, puntosDeAtaque(ataque) - 1) :: ataques.filterNot((tupl) => tupl._1 == ataque))
    } else {
      throw new IllegalArgumentException
    }
  }
  
  def valido(): Boolean = {
    
    true
  }
  
  def subirDeNivel() = {
      val nuevoPoke = copy(nivel = nivel + 1,
      fuerza = fuerza + especie.aumentoFuerza,
      velocidad = velocidad + especie.aumentoVelocidad,
      peso = peso + especie.aumentoPeso,
      energiaMaxima = energiaMaxima + especie.aumentoEnergiaMaxima).aumentarEnergiaAlMaximo()
      if (nuevoPoke.valido()) nuevoPoke else this
  }

  def aumentarVelocidad(aumento: Int) = copy(velocidad = velocidad + aumento)
  
  def validarAtaques(): Boolean = {
    ataques.forall((ataque) => especie.tipoPrincipal == ataque._1.tipo || especie.tipoSecundario == ataque._1.tipo)
  }
  
  def aumentarEnergiaAlMaximo() : Pokemon = copy(energia = energiaMaxima)

  def usarPocion() : Pokemon = {
    if(energiaMaxima - energia < MAXIMA_ENERGIA_RECOBRADA){
      copy(energia = energiaMaxima)
    }else{
      copy(energia = energia + MAXIMA_ENERGIA_RECOBRADA)
    }
  }
}
