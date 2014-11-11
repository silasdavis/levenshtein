(ns levenshtein.core)

(defn traverse
  "Traverse moves over the matrix of edit distances for prefixes left-to-right row-by-row.

  Since we only need the previous row and the last element calculated of the current row,
  as the first argument we take an accumulator [p c] of the p the previous row,
  and c the current row so far.

  The second argument is the characters indexing the current matrix element."
  [[p c] [x y]]
  (if (> (count p) 1)
    [(rest p)
     (conj c (if (= x y)
               (first p)
               (inc (min
                     (first p) ;; i-1 j-1 substitution
                     (second p) ;; i-1 j deletion
                     (last c)))))] ;; i j-1 insertion
    (recur [c [(inc (first c))]] [x y]))) ;; once we have reached the end of the row, flip the previous and current row and seed the new current row


(defn edit-distance
  [u v]
  (last (last (reduce traverse
                      [(vec (range 0 (inc (count u)))) [1]]
                      (for [y v x u] [x y])))))


(defn edit-distance-matrix [u v]
  (let [[[first-row _] & intermediates] (take-nth (count u)
                                                  (reductions traverse
                                                              [(vec (range 0 (inc (count u)))) [1]]
                                                              (for [y v x u] [x y])))]
    (vec (cons first-row (map last intermediates)))))
