(ns fourclojure.problem178
  (:require [clojure.string :as s]
            [clojure.test :refer [deftest is run-tests]]))

(defn parse-card [[suit val]]
  [(keyword (str suit))
   (get {\T 10 \J 11 \Q 12 \K 13 \A 14} val (read-string (str val)))])

(defn continuous? [set] (= (- (count set) 1) (- (apply max set) (apply min set))))

(defn flush? [hand]
  (let [hand (map parse-card hand)
        [s _] (first hand)]
    (every? #(= (first %) s) hand)))

(defn straight? [hand]
  (let [hand (map parse-card hand)
        vals (into #{} (map second hand))]
    (or
      (when (contains? vals 14) (continuous? (conj (disj vals 14) 1)))
      (continuous? vals))))

(deftest test-parse-cards
  (is (= [:H 14] (parse-card "HA")))
  (is (= [:S 3] (parse-card "S3"))))

(deftest test-straight
  (is (straight? ["S4" "S5" "S6"]))
  (is (straight? ["DA" "H2" "C3" "D4" "D5"]))
  (is (not (straight? ["CA" "D3" "C3" "D4" "S5"])))
  (is (not (straight? ["DA" "D2" "D3" "D4" "D6"])))
  (is (straight? ["DA" "SK" "HQ" "CJ" "HT"]))
  )

(deftest test-flush?
  (is (flush? ["H4" "HA" "HT" "H2" "H3"]))
  (is (not (flush? ["H4" "HA" "CT" "H2" "S4"]))))

(run-tests)