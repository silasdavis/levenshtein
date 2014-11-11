# levenshtein

Implementation of Wagner-Fischer algorithm in O(nm) time and O(m) space.

## Usage

Calculcate the Levenshtein edit distance:

```Clojure
(use '[levenshtein.core])
(edit-distance "Levenshtein" "Hamming")
;; => 10
```
Or see the entire matrix of prefix edit distances calculated as intermediaries to the edit distance:

```Clojure
(edit-distance-matrix "Levenshtein" "Hamming")
;; => [[0 1 2 3 4 5 6 7 8 9 10 11]
;;     [1 1 2 3 4 5 6 7 8 9 10 11]
;;     [2 2 2 3 4 5 6 7 8 9 10 11]
;;     [3 3 3 3 4 5 6 7 8 9 10 11]
;;     [4 4 4 4 4 5 6 7 8 9 10 11]
;;     [5 5 5 5 5 5 6 7 8 9 9 10]
;;     [6 6 6 6 6 5 6 7 8 9 10 9]
;;     [7 7 7 7 7 6 6 7 8 9 10 10]])
```

## License

Copyright © 2014 Silas Davis

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
