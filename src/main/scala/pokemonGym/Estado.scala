package pokemonGym

/**
 * @author seb
 */
trait Estado {
  def pokemon: Pokemon
  def map(f: (Pokemon => Pokemon)): Estado
  def filter(f: (Pokemon => Boolean)): Estado
  def flatMap(f: (Pokemon => Estado )): Estado
}

case class Envenenado(val pokemon: Pokemon) extends Estado {
  def map(f: (Pokemon => Pokemon)) = Envenenado(f(pokemon))
  def filter(f: (Pokemon => Boolean)) = if (f(pokemon)) this else Invalido(pokemon, "Fallo el filtrado")
  def flatMap(f: (Pokemon => Estado )) = f(pokemon)
}

case class Dormido(val pokemon: Pokemon, val contador: Int = 3) extends Estado {
  def map(f: (Pokemon => Pokemon)) = this
  def filter(f: (Pokemon => Boolean)) = if (f(pokemon)) this else Invalido(pokemon, "Fallo el filtrado")
  def flatMap(f: (Pokemon => Estado )) = this
}

case class Paralizado(val pokemon: Pokemon) extends Estado {
  def map(f: (Pokemon => Pokemon)) = Paralizado(f(pokemon))
  def filter(f: (Pokemon => Boolean)) = if (f(pokemon)) this else Invalido(pokemon, "Fallo el filtrado")
  def flatMap(f: (Pokemon => Estado )) = f(pokemon)
}

case class KO(val pokemon: Pokemon) extends Estado {
  def map(f: (Pokemon => Pokemon)) = Invalido(pokemon, "El pokemon en estado KO no puede realizar actividades")
  def filter(f: (Pokemon => Boolean)) = if (f(pokemon)) this else Invalido(pokemon, "Fallo el filtrado")
  def flatMap(f: (Pokemon => Estado )) = Invalido(pokemon, "El pokemon en estado KO no puede realizar actividades")
}

case class OK(val pokemon: Pokemon) extends Estado {
	def map(f: (Pokemon => Pokemon)) = OK(f(pokemon))
	def filter(f: (Pokemon => Boolean)) = if (f(pokemon)) this else Invalido(pokemon, "Fallo el filtrado")
  def flatMap(f: (Pokemon => Estado )) = f(pokemon)
}

case class Invalido(val pokemon: Pokemon, val descripcion: String) extends Estado {
  def map(f: (Pokemon => Pokemon)) = Invalido(pokemon, descripcion)
  def filter(f: (Pokemon => Boolean)) = Invalido(pokemon, descripcion)
  def flatMap(f: (Pokemon => Estado )) = this
}