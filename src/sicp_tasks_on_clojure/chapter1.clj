(ns sicp-tasks-on-clojure.chapter1)

(defn good-enougth? [guess x]
  (< (abs (- (* guess guess) x)) 0.001))

(def average [x y]
  (/ (+ x y) 2))

(defn improve [guess x]
  (average guess (/ x guess)))

(defn sqrt-iter [guess x]
  (if (good-enougth? guess x)
    guess
    (sqrt-iter (improve guess x)
               x)))

