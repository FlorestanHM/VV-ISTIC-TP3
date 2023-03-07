# On assertions

Answer the following questions:

1. The following assertion fails `assertTrue(3 * .4 == 1.2)`. Explain why and describe how this type of check should be done.

2. What is the difference between `assertEquals` and `assertSame`? Show scenarios where they produce the same result and scenarios where they do not produce the same result.

3. In classes we saw that `fail` is useful to mark code that should not be executed because an exception was expected before. Find other uses for `fail`. Explain the use case and add an example.

4. In JUnit 4, an exception was expected using the `@Test` annotation, while in JUnit 5 there is a special assertion method `assertThrows`. In your opinion, what are the advantages of this new way of checking expected exceptions?

## Answer
1.
"Also notice how doubles are compared using a precision. Floating point types should never be compared with direct equality due to numerical errors. The code can be checked here." 
On ne peut pas comparer des floats avec un test d'égalité à cause des erreurs d'approximation. Il est conseillé de comparé les floats avec des comparateurs : > ou <, et de fixer quelle précision on veut utiliser.

