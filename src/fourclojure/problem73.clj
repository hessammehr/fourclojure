(ns fourclojure.problem73)

(def b1 [[:e :x :e]
         [:o :o :o]
         [:x :e :x]])

(defn positions [board x]
  (let [b (flatten board)
        idx (keep-indexed #(when (= %2 x) %) b)
        w (count (first board))]
    (->> idx
         (map (fn [i] [(quot i w) (rem i w)]))
         set)))

(def winning (into #{
                     #{[0 0] [1 1] [2 2]}                   ; diagonal
                     #{[2 0] [1 1] [0 2]}                   ; other diagonal
                     }
                   (for [i (range 3)]
                     (into #{} (for [j (range 3)] [i j])))))

(defn wins? [board player]
  (let [p (positions board player)]
    (some #(clojure.set/subset? % p) winning)))

(when-some)