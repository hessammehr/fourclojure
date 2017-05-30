(defn reduction-seq
 ([f v col]
  (if-not (seq col) [v]
    (lazy-seq (cons v (reduction-seq f (f v (first col)) (rest col))))))
 ([f col]
  (when (next col)
    (reduction-seq f (first col) (rest col)))))

; Tests

(= (take 5 (reduction-seq + (range))) [0 1 3 6 10])
(= (reduction-seq conj [1] [2 3 4]) [[1] [1 2] [1 2 3] [1 2 3 4]])
(= (last (reduction-seq * 2 [3 4 5])) (reduce * 2 [3 4 5]) 120)
