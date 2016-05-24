#Elements of Programming

**REPL** Read-Eval-Print-Loop

1. Take the leftmost operators
2. Evaluate its operands
3. Apply the operator to operands

>(2 \* pi )  * radius

>(2   \* 3.14159) \* radius

>6.28318 \* radius

>6.329318 \* 10


def power(x: *Double*, y: *Int*): Double =

## How functions are evaluated?

1. Evaluate all function arguments, from left to right
2. Replace the function application by the function's right-hand side, at the same time
3. Replace the formal parameters of the function by the actual arguments

```Scala
sumOfSquares(3, 2+2)
sumOfSquares(3, 4)
square(3) + square(4)
```
9 + square(4)
9 + 16
25

### The name is **Substitution model**: Reduce an expression to a value
*(applicable to all expressions, as long as they have no side effects)*

### Call By Value
> In this case the Substitution model is **call by value**
In Scala, it is used the call by value. It is much efficient.


**Advantage**: evaluates every function argument only once

**Disadvantage**:

```Scala
first(x: Int, y:Int) = x

first (1, loop) //This operation never ends
```

### Call By Name
> Other model can be **call by name**

**Advantage**: a function argument is not evaluated if the corresponding parameter is unused in the evaluation of the function body

```Scala
sumOfSquares(3, 2+2)
sumOfSquares(3, 2+2)
square(3) + square(4)
9 + (2+2) * (2+2)
9 + 4 * 4
25

def test(x: Int, y:Int) = x * x

test(7, 2 * 4)
7 * 7
49
```

**In Scala if you want to use call by name**
```Scala
def constOne(x: Int, y: => Int) = 1  // the y :=> Int indicates that the parameter is evaluated as call by name

constOne(1+2, loop)
constsOne(3, loop)
1

constsOne(loop, 1+2)
//This program does not return anything because the first parameter uses a call by value substitution model
```
### Does every expression reduce to a value(in a finite number of steps)? No.
def loop:Int = loop
