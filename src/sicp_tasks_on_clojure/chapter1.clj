(ns sicp-tasks-on-clojure.chapter1)

(defn good-enougth? [guess x]
  (< (Math/abs (- (Math/pow guess 2) x)) 0.001))

(defn average [x y]
  (/ (+ x y) 2.0))

(defn improve [guess x]
  (average guess (/ x guess)))

(defn sqrt-iter [guess x]
  (if (good-enougth? guess x)
    guess
    (sqrt-iter (improve guess x)
               x)))

(defn sqrt [x]
  (sqrt-iter 1 x))
