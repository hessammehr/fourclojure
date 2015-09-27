(ns fourclojure.problem-79)

(defn paths [n]
  (if (zero? n)
    '([0])
    (lazy-cat
     (for [i (paths (dec n)) j [0 1]]
       (conj i (+ (last i) j))))))

(defn get-nums [triangle path]
  (map nth triangle path))

(defn all-nums [triangle]
  (map #(get-nums triangle %) (paths (dec (count triangle)))))

(defn triangle-minimal [triangle]
  (apply min
         (map (partial apply +) (all-nums triangle))))