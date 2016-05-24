#Review:

## Imperative programming
-> modify mutable variables -> using assignments
-> control structures such as if-then-else, loops, break, continue, return

Mutable variables     => memory cells
Variable dereferences => load instructions
Variable assignments  => store instructions
Control structures    => jumps

Model like Von Neumman

--------------------------------------------------------------------------------

## Functional programming:

Look for
* Concentrate on defining theories for operators (expressed as **functions**)
* Avoid mutations
* Have powerful ways to abstract and compose functions

> Without mutable variables, assignments, loops

>Focus on functions: can be produced, consumed and composed

**Functions are first-class citizens**
1. Defined anywhere (including inside another functions)
2. They can be passed as parameters to functions and returned as results
3. There exists a set of operators to compose functions


- Functional programming __in restricted sense__ does not have mutable variables: *Pure Lips*, *Haskel(without I/O Monad)*
- Functional programming __in wider sense__ enables the construction of elegant programs that focus on functions: *Scala*, *Haskell(1990)*, *Lisp(1959)*, *Clojure(2007)*

### Why it is becoming so popular?
1. Simple reasoning principles
2. Better modularity
3. Good for exploiting parallelism for multicore and cloud computing

About Scala: **Programming in Scala** Martin Odersky.
