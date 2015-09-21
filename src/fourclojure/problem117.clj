(ns fourclojure.problem-117)



(defn print-maze [maze]
  (print (interpose \newline maze)))


(defn parse-maze [maze]
  (vec (map vec maze)))

(defn find-pos [maze ch]
  (let [f (flatten maze) w (count (first maze))]
    (->> f
         (keep-indexed #(when (= ch %2) %1))
         (map #(vector (quot % w) (rem % w))))))

(defn neighbors [maze [r c]]
  (let [h (count maze) w (count (first maze))
        candidates [[(- r 1) c] [(+ r 1) c]
                    [r (- c 1)] [r (+ c 1)]]]
    (filter (fn [[rr cc]] (and (>= rr 0)
                               (< rr h)
                               (>= cc 0)
                               (< cc w)))
            candidates)))

(defn flip [maze pos from to]
  (if (= (get-in maze pos) from)
         (assoc-in maze pos to)
         maze))

;(defn solve [maze]
;  (loop [maze maze mice (find-pos m \M) cheese (find-pos m \C)]
;    (if-not (seq cheese)
;      true)
;    ))



(defn evolve [maze]
  (let [ms (find-pos maze \M)
        edge (mapcat #(neighbors maze %) ms)]
    ;(reduce #(flip maze % \M \X) maze ms)
         (println ms edge)
    ))


(def m (parse-maze ["########"
                    "#M  #  #"
                    "#   #  #"
                    "# # #  #"
                    "#   #  #"
                    "#  #   #"
                    "#  # # #"
                    "#  #   #"
                    "#  #  C#"
                    "########"]))

;(defn parse-maze [lines]
;  {:get (fn [r c])}
;  (defn parse-line [line]
;    )
;  (mapcat ))

