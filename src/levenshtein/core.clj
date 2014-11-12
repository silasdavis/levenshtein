(ns levenshtein.core)

(defn traverse
  "Traverse moves over the matrix of edit distances for prefixes left-to-right row-by-row.

  Since we only need the previous row and the last element calculated of the current row,
  as the first argument we take an accumulator [p c] of the p the previous row,
  and c the current row so far.

  The second argument is the characters indexing the current matrix element."
  [[p c] [x y]]
  (if (second p) ;; If there is only 1 element in our previous row we have 'gone off the end' of the row
    [(rest p)
     (conj c (if (= x y)
               (first p)
               (inc (min
                     (first p) ;; i-1 j-1 substitution
                     (second p) ;; i-1 j deletion
                     (c (dec (count c)))))))] ;; i j-1 insertion
    (recur [c [(inc (first c))]] [x y]))) ;; once we have reached the end of the row, flip the previous and current row and seed the new current row


(defn edit-distance
  [u v]
  (last (last (reduce traverse
                      [(vec (range 0 (inc (count u)))) [1]]
                      (for [y v x u] [x y])))))

;; Note this is provided to examine the algorith, we do not produce an n x m matrix to find the edit distance above
(defn edit-distance-matrix [u v]
  (let [[[first-row _] & intermediates] (take-nth (count u)
                                                  (reductions traverse
                                                              [(vec (range 0 (inc (count u)))) [1]]
                                                              (for [y v x u] [x y])))]
    (vec (cons first-row (map last intermediates)))))
