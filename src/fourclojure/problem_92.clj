(ns fourclojure.problem-92)

(defn parse-roman [n]
  (let [nums {\I 1 \V 5 \X 10 \L 50 \C 100
              \D 500 \M 1000}
        digits (seq n)]
    (defn parse-inner [[x y & others] acc]
      (if (nil? x)
        acc
        (if (nil? y)
          (+ acc (nums x))
          (if (>= (nums x) (nums y))
            (recur (conj others y) (+ acc (nums x)))
            (recur others (+ acc (- (nums y) (nums x))))))))
    (parse-inner digits 0)))
