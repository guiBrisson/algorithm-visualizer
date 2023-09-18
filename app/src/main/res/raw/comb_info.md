# Comb sort

Comb sort is a relatively simple sorting algorithm originally designed by Włodzimierz Dobosiewicz
and Artur Borowy in 1980, later rediscovered (and given the name "Combsort") by Stephen Lacey
and Richard Box in 1991. Comb sort improves on bubble sort in the same way that Shellsort
improves on insertion sort.

The basic idea is to eliminate _turtles_, or small values near the end of the list, since in a bubble
sort these slow the sorting down tremendously. _Rabbits_, large values around the beginning of the 
list, do not pose a problem in bubble sort.

## Complexity

| Name        | **Comb sort**                                 |
|-------------|-----------------------------------------------|
| **Best**    | Θ(n log n)                                    |
| **Average** | Θ(n²/2ᵖ), where p is the number of increments |
| **Worst**   | O(n²)                                         |
| **Memory**  | 1                                             |
| **Stable**  | No                                            |

## References

- [Wikipedia](https://en.wikipedia.org/wiki/Comb_sort)