(defn merge-with-fn [f & maps]
  (let [assoc-with (fn [f col [k v]] (assoc col k (if-not (contains? col k) v (f (col k) v))))]
    (reduce (fn [acc m] (reduce #(assoc-with f %1 %2) acc m)) maps)))

(= (merge-with-fn * {:a 2, :b 3, :c 4} {:a 2} {:b 2} {:c 5}))
