(ns levenshtein.core-test
  (:require [clojure.test :refer :all]
            [levenshtein.core :refer :all]))

(deftest distances
  (testing "Computes some accurate edit distances"
    (are [x y n] (= (edit-distance x y) n)
         "sitting" "kitten" 3
         "invoke" "poke" 3
         "Levenshtein" "Hamming" 10)))

(deftest matrix
  (is (= (edit-distance-matrix "Levenshtein" "Hamming")
         [[0 1 2 3 4 5 6 7 8 9 10 11]
          [1 1 2 3 4 5 6 7 8 9 10 11]
          [2 2 2 3 4 5 6 7 8 9 10 11]
          [3 3 3 3 4 5 6 7 8 9 10 11]
          [4 4 4 4 4 5 6 7 8 9 10 11]
          [5 5 5 5 5 5 6 7 8 9 9 10]
          [6 6 6 6 6 5 6 7 8 9 10 9]
          [7 7 7 7 7 6 6 7 8 9 10 10]])))


(def head (slurp "/home/silas/ephemera/sonnets-head.txt"))

(def tail (slurp "/home/silas/ephemera/sonnets-tail.txt"))

(edit-distance (take 1000 head) (take 1000 tail))
