# Macchiato Lungo

Project of simple programming language "Macchiato Lungo". It's mainly focused on OOP and design patterns.

##### Language consists of the following instructions:
- Block
A block contains a sequence of variable declarations and a sequence of instructions. Each of these parts can be empty. Declarations placed in a block are visible from its end to the end of its block (and nowhere else). Local declarations can obscure external declarations.

- The for loop <variable> <expression> <instructions>.
A for loop is a control structure used in programming to repeat a block of code a specific number of times. It consists of a variable, an expression, and a set of instructions. The loop executes the instructions a certain number of times, determined by the value of the expression. The variable takes on a different value in each iteration, starting from 0 and going up to the value of the expression minus one. The value of the expression is calculated only once, before the loop execution. Any changes made to the variable within the loop will not affect the number of iterations or the initial value of the variable. If the calculated value of the expression is zero or negative, the loop will not execute at all. If there is an error in calculating the expression, the loop will be interrupted and the instructions will not be executed.

- Conditional instruction if <exp1><operator><exp2> then <instructions> else <instructions>.
○ standard meaning,
○ first we calculate the first expression, then the second expression,
○ an error when calculating an expression interrupts further execution of this instruction,
○ the else <instructions> part can be omitted.

- Assigning a value to the variable <name> <exp>. 
○ assigns a value to the variable equal to the calculated value of the expression,
○ an error in calculating the expression interrupts further execution of this instruction (i.e., in such a
situation the value of the variable is not changed),
○ ends with an error if no declared
variable with the specified name.

- Print out the value of the expression print <exp>.
○ The value of the expression is calculated and then printed out
○ If the expression calculation fails, this instruction does not print out anything

## Debbuger
The project includes a simple debugger capable of performing the following actions:

- c(ontinue) - parameterless instruction. Makes code run until it reaches the end.
- s(tep) - takes number of steps, that is Macchiato Lungo instructions, to be performed.
- d(isplay) - displays actual variable values. Takes one parameter, how many scopes "up" should be displayed.

##### File Main.java implements an example algorithm

```sh
begin block
    var n 30
    for k n-1
        begin block
            var p 1
            k := k+2
            for i k-2
                i := i+2
                if k % i = 0
                    p := 0
                if p = 1
                    print k
        end block
end block
```
