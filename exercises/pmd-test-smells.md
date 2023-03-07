# Detecting test smells with PMD

In folder [`pmd-documentation`](../pmd-documentation) you will find the documentation of a selection of PMD rules designed to catch test smells.
Identify which of the test smells discussed in classes are implemented by these rules.

Use one of the rules to detect a test smell in one of the following projects:

- [Apache Commons Collections](https://github.com/apache/commons-collections)
- [Apache Commons CLI](https://github.com/apache/commons-cli)
- [Apache Commons Math](https://github.com/apache/commons-math)
- [Apache Commons Lang](https://github.com/apache/commons-lang)

Discuss the test smell you found with the help of PMD and propose here an improvement.
Include the improved test code in this file.

## Answer
On retrouve dans le dossier [`pmd-documentation`](../pmd-documentation) quelques tests smells disuctés en classe, comme par exemple :
- Verifier qu'un teste n'est pas trop d'assertions.
- Verifier qu'un teste est au moins une assertion.

Je teste `UseAssertEqualsInsteadOfAssertTrue` sur [Apache Commons Collections](https://github.com/apache/commons-collections)
On veut évitér les `.equals` dans un `assertTrue`:
```java
public class FooTest extends TestCase {
    void testCode() {
        Object a, b;
        assertTrue(a.equals(b));                    // bad usage
        assertEquals("a should equals b", a, b);    // good usage
    }
}
```

```
commons-collections/src/test/java/org/apache/commons/collections4/list/AbstractListTest.java:256:       UseAssertEqualsInsteadOfAssertTrue:     Use assertEquals(x, y) instead of assertTrue(x.equals(y))
commons-collections/src/test/java/org/apache/commons/collections4/list/AbstractListTest.java:258:       UseAssertEqualsInsteadOfAssertTrue:     Use assertEquals(x, y) instead of assertTrue(x.equals(y))
commons-collections/src/test/java/org/apache/commons/collections4/list/AbstractListTest.java:271:       UseAssertEqualsInsteadOfAssertTrue:     Use assertEquals(x, y) instead of assertTrue(x.equals(y))
commons-collections/src/test/java/org/apache/commons/collections4/list/AbstractListTest.java:273:       UseAssertEqualsInsteadOfAssertTrue:     Use assertEquals(x, y) instead of assertTrue(x.equals(y))
commons-collections/src/test/java/org/apache/commons/collections4/map/ReferenceIdentityMapTest.java:274:        UseAssertEqualsInsteadOfAssertTrue:     Use assertEquals(x, y) instead of assertTrue(x.equals(y))
commons-collections/src/test/java/org/apache/commons/collections4/map/ReferenceIdentityMapTest.java:275:        UseAssertEqualsInsteadOfAssertTrue:     Use assertEquals(x, y) instead of assertTrue(x.equals(y))
commons-collections/src/test/java/org/apache/commons/collections4/multiset/AbstractMultiSetTest.java:542:       UseAssertEqualsInsteadOfAssertTrue:     Use assertEquals(x, y) instead of assertTrue(x.equals(y))
commons-collections/src/test/java/org/apache/commons/collections4/multiset/AbstractMultiSetTest.java:546:       UseAssertEqualsInsteadOfAssertTrue:     Use assertEquals(x, y) instead of assertTrue(x.equals(y))
commons-collections/src/test/java/org/apache/commons/collections4/multiset/AbstractMultiSetTest.java:555:       UseAssertEqualsInsteadOfAssertTrue:     Use assertEquals(x, y) instead of assertTrue(x.equals(y))
commons-collections/src/test/java/org/apache/commons/collections4/multiset/AbstractMultiSetTest.java:567:       UseAssertEqualsInsteadOfAssertTrue:     Use assertEquals(x, y) instead of assertTrue(x.equals(y))
commons-collections/src/test/java/org/apache/commons/collections4/multiset/AbstractMultiSetTest.java:571:       UseAssertEqualsInsteadOfAssertTrue:     Use assertEquals(x, y) instead of assertTrue(x.equals(y))
commons-collections/src/test/java/org/apache/commons/collections4/multiset/AbstractMultiSetTest.java:580:       UseAssertEqualsInsteadOfAssertTrue:     Use assertEquals(x, y) instead of assertTrue(x.equals(y))
```

On regarde le premier cas remonté: 
```java
/**
 *  Tests {@link List#equals(Object)}.
 */
@Test
public void testListEquals() {
    resetEmpty();
    List<E> list = getCollection();
    assertTrue("Empty lists should be equal", list.equals(getConfirmed()));
```

On peut l'améliorer: 
```java
/**
 *  Tests {@link List#equals(Object)}.
 */
@Test
public void testListEquals() {
    resetEmpty();
    List<E> list = getCollection();
    assertEquals("Empty lists should be equal", list, getConfirmed());
```
