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
  (if (contains? from (get-in maze pos))
    (assoc-in maze pos to)
    maze))

(defn evolve [maze]
  (let [ms (find-pos maze \M)
        edges (mapcat #(neighbors maze %) ms)]
    (reduce #(flip %1 %2 #{\space \C} \M) (reduce #(flip %1 %2 #{\M} \X) maze ms) edges)))

(defn moves [maze]
  (take-while #(seq (find-pos % \M)) (iterate evolve maze)))

(defn solvable? [maze]
  (if (seq (find-pos (last (moves maze)) \C))
    false
    true))

(assert (= false (solvable? (parse-maze [
                              "########"
                              "#M  #  #"
                              "#   #  #"
                              "# # #  #"
                              "#   #  #"
                              "#  #   #"
                              "#  # # #"
                              "#  #   #"
                              "#  #  C#"
                              "########"]))))

(assert (= true (solvable? (parse-maze [
                             "########"
                             "#M  #  #"
                             "#   #  #"
                             "# #    #"
                             "#   #  #"
                             "#  #   #"
                             "#  # # #"
                             "#  #   #"
                             "#  #  C#"
                             "########"]))))



