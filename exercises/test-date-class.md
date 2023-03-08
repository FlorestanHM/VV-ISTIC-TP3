# Test the Date class

Implement a class `Date` with the interface shown below:

```java
class Date implements Comparable<Date> {

    public Date(int day, int month, int year) { ... }

    public static boolean isValidDate(int day, int month, int year) { ... }

    public static boolean isLeapYear(int year) { ... }

    public Date nextDate() { ... }

    public Date previousDate { ... }

    public int compareTo(Date other) { ... }

}
```

The constructor throws an exception if the three given integers do not form a valid date.

`isValidDate` returns `true` if the three integers form a valid year, otherwise `false`.

`isLeapYear` says if the given integer is a leap year.

`nextDate` returns a new `Date` instance representing the date of the following day.

`previousDate` returns a new `Date` instance representing the date of the previous day.

`compareTo` follows the `Comparable` convention:

* `date.compareTo(other)` returns a positive integer if `date` is posterior to `other`
* `date.compareTo(other)` returns a negative integer if `date` is anterior to `other`
* `date.compareTo(other)` returns `0` if `date` and `other` represent the same date.
* the method throws a `NullPointerException` if `other` is `null` 

Design and implement a test suite for this `Date` class.
You may use the test cases discussed in classes as a starting point. 
Also, feel free to add any extra method you may need to the `Date` class.


Use the following steps to design the test suite:

1. With the help of *Input Space Partitioning* design a set of initial test inputs for each method. Write below the characteristics and blocks you identified for each method. Specify which characteristics are common to more than one method.
2. Evaluate the statement coverage of the test cases designed in the previous step. If needed, add new test cases to increase the coverage. Describe below what you did in this step.
3. If you have in your code any predicate that uses more than two boolean operators check if the test cases written to far satisfy *Base Choice Coverage*. If needed add new test cases. Describe below how you evaluated the logic coverage and the new test cases you added.
4. Use PIT to evaluate the test suite you have so far. Describe below the mutation score and the live mutants. Add new test cases or refactor the existing ones to achieve a high mutation score.

Use the project in [tp3-date](../code/tp3-date) to complete this exercise.

## Answer

1.
On a créé deux fichiers csv: un fichier avec des dates valides et un fichier avec des dates invalides
- Les valeurs valides (day, month, year):
```
1,1,-1
1,2,0
28,2,1
29,4,2
15,10,199
15,10,200
16,10,200
14,11,200
```
- Valeurs invalides :
```
30,2,0
31,4,0
12,31,2023
```

Pour la méthode `compareTo` j'ai directement fait plusieurs assert avec tous les cas auquel j'ai pensé.
- compareTo est négatif:
```java
Date date = new Date(15, 6, 23);

Date posterieurDay = new Date(11, 1, 23);

Date posterieurMonth = new Date(9, 2, 23);
Date posterieurMonth2 = new Date(10, 2, 23);
Date posterieurMonth3 = new Date(12, 2, 23);

Date posterieurYear0 = new Date(9, 1, 24);
Date posterieurYear1 = new Date(10, 1, 24);
Date posterieurYear2 = new Date(11, 1, 24);

Date posterieurYear3 = new Date(9, 2, 24);
```
- compareTo est nulle:
```java
Date date = new Date(10, 1, 23);
Date other = new Date(10, 1, 23);
```
- compareTo est positif:
```java
Date date = new Date(15, 6, 23);

Date anterieurDay = new Date(14, 6, 23);

Date anterieurMonth = new Date(16, 5, 23);
Date anterieurMonth2 = new Date(15, 5, 23);
Date anterieurMonth3 = new Date(14, 5, 23);

Date anterieurYear0 = new Date(16, 6, 22);
Date anterieurYear1 = new Date(15, 6, 22);
Date anterieurYear2 = new Date(14, 6, 22);
```

2.
On a coverage de lignes de 86%.
On peut rajouter une valeur :
```
31, 12, 25
```
Et on monte à 88% de coverage. Le code pas couvert n'est pas atteignable.

3.
On a deux predicat avec plusieurs booléen : 
```java
month <= 12 && day > 0 && day <= numberOfDaysInMonth(month, year)
```
On rajoute un cas de teste pour pouvoir tester ces trois booléen séparement:
```
30,2,0
12,31,2023
-2,2,2024
```

```java
(year % 4 == 0) && (year % 100 != 0 || year % 400 == 0)
```
On rajoute des cas de teste:
- valide: 
```
29,2,400
29,2,52
```
- invalide:
```
29,2,200
```

4.
On a un score de mutation de 59%
![](https://codimd.math.cnrs.fr/uploads/upload_da9b8e76ff456d356ff86f9bc862fe4f.png)
Pour tuer plus de mutants, on peut comparer les dates obtenus par `nextDate` et `previousDate`.

On obtient un score de mutation de 86%
![](https://codimd.math.cnrs.fr/uploads/upload_bebd52d69544b7f9eabbba8d6018daf1.png)

On pourrait changer le code pour augmenter le score de mutation, mais ça ne fait pas de sens actuellement.