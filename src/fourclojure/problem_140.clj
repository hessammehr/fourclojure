(ns fourclojure.problem-140
  (:require [clojure.contrib.Math])
  )

(defn gray-series [n]
  (let [powers (take (iterate (partial * 2) 1) n)]))
