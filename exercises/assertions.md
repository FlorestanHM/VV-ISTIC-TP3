# On assertions

Answer the following questions:

1. The following assertion fails `assertTrue(3 * .4 == 1.2)`. Explain why and describe how this type of check should be done.

2. What is the difference between `assertEquals` and `assertSame`? Show scenarios where they produce the same result and scenarios where they do not produce the same result.

3. In classes we saw that `fail` is useful to mark code that should not be executed because an exception was expected before. Find other uses for `fail`. Explain the use case and add an example.

4. In JUnit 4, an exception was expected using the `@Test` annotation, while in JUnit 5 there is a special assertion method `assertThrows`. In your opinion, what are the advantages of this new way of checking expected exceptions?

## Answer
1.
"Also notice how doubles are compared using a precision. Floating point types should never be compared with direct equality due to numerical errors. The code can be checked here." : 3.3.2. Real examples of good testing practice (https://oscarlvp.github.io/vandv-classes/)

On ne peut pas comparer des floats avec un test d'égalité à cause des erreurs d'approximation. Il est conseillé de comparé les floats avec des comparateurs : > ou <, et de fixer quelle précision on veut utiliser.

2.
`assertEquals` compare la valeur de deux objets, tandis que `assertSame` compare si deux objets sont le même objet en mémoire.

On a donc deux scénario: 

- Résultat différent: 
```java
List<String> list1 = new ArrayList<>();
List<String> list2 = new ArrayList<>();

list1.add("hello");
list2.add("hello");

assertEquals(list1, list2); // passes
assertSame(list1, list2); // fails
```

- Même résultat: 
```java
List<String> list1 = new ArrayList<>();
list1.add("hello");

List<String> list2 = list1;

assertEquals(list1, list2); // passes
assertSame(list1, list2); // passes
```

3.
`fail()` est utile pour marquer que du code ne devrait pas être executer car une excepetion est attendu avant. 
Il peut être également utile pour marquer que du code ne devrait pas être atteignable pour plusieurs autres raisons, par exemple: 
```
switch (a) {
    case 1:
        // do something
        break;
    case 2:
        // do something
        break;
    default:
        fail("Unreachable code");
        break;
}
```
`fail()` peut aussi être utilisé pour tester si les inputs sont valides, et donc si les tests sont bien utilisés. Tout en renvoyant un message d'erreur clair. 
On peut également utiliser `fail()`pour marquer qu'une fonctionnalité n'est pas encore implementé. 

En général, `fail()` permet de faire échouer le test car il se retrouve dans un état non attendu, en envoyant un message d'erreur clair.

4.
