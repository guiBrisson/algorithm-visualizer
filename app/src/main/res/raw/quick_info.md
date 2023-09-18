# Quick sort

Quicksort is an efficient, general-purpose sorting algorithm. Quicksort was developed by British 
computer scientist Tony Hoare in 1959 and published in 1961. It is still a commonly used algorithm 
for sorting.

Quicksort is a divide-and-conquer algorithm. It works by selecting a 'pivot' element from the array 
and partitioning the other elements into two sub-arrays, according to whether they are less than or 
greater than the pivot. For this reason, it is sometimes called partition-exchange sort. 
The sub-arrays are then sorted recursively. This can be done in-place, requiring small additional 
amounts of memory to perform the sorting. 

## Complexity
| Name        | **Quick sort** |
|-------------|----------------|
| **Best**    | O(n log n)     |
| **Average** | O(n log n)     |
| **Worst**   | O(n²)          |
| **Memory**  | 1              |
| **Stable**  | No             |

## References

- [Wikipedia](https://en.wikipedia.org/wiki/Quicksort)