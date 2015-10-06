(ns fourclojure.problem-86)

(defn step [num]
  (apply + (map (comp square read-string str) (seq (str num)))))

(defn happy? [n]
  (loop [n n vals #{}]
    (println n)
    (if (= n 1)
      true
      (if (contains? vals n)
        false
        (recur (step n) (conj vals n))))))
