import helloworld.Hello

val hello = new Hello

println("Be polite and positive")
println(hello.sayHello("People"))


/**
  * Concept 2: When to use spaces
  *
  */


for(i <- 0.to(2))
  print(i+1)

// If a method takes only 1 parameter, you can call it without a do or parentheses
for(i <- 0 to 2 )
  print(i + 1)

Console println 10

(1).+(2)

1 + 2


/**
  How Arrays are used?
  Scala achieves a conceptual simplicity by treating everything, from ar-
  rays to expressions, as objects with methods.
  **/

  //Option A
val greetStrings = new Array[String](3)
greetStrings(0) = "Hello"
greetStrings(1) = ", "
greetStrings(2) = "world!\n"
for (i <- 0.to(2))
  print(greetStrings.apply(i))


//Option B
greetStrings.update(0, "Hello")
greetStrings.update(1, ", ")
greetStrings.update(2, "world!\n")
for (i <- 0.to(2))
  print(greetStrings.apply(i))

/**
  Playing with Lists
  **/

val oneTwo = List(1, 2)
val threeFour = List(3, 4)
val oneTwoThreeFour = oneTwo ::: threeFour
println(oneTwo +" and "+ threeFour +" were not mutated.")
println("Thus, "+ oneTwoThreeFour +" is a new list.")

val twoThree = List(2, 3)
val oneTwoThree = 1 :: twoThree
println(oneTwoThree)

/**
  * Because the time it takes to append to a list grows linearly with the size of the
list, whereas prepending with :: takes constant time. Your options if
you want to build a list efficiently by appending elements is to prepend
them, then when you’re done call reverse ; or use a ListBuffer , a
mutable list that does offer an append operation.
  */
