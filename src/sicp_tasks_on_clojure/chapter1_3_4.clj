(ns sicp-tasks-on-clojure.chapter1-3-4
  (:use [sicp-tasks-on-clojure.chapter1-3-3 :only [avg fixed-point]])
  (:use [sicp-tasks-on-clojure.chapter1-2-6 :only [square]]))

(defn average-damp [f]
  #(avg % (f %)))

(defn cube-root [x]
  (fixed-point (average-damp #(/ x (square %))) 1.0))

(def dx 0.00001)

(defn deriv [g]
  (fn [x] (/ (- (g (+ x dx)) (g x))
             dx)))

(defn cube [x] (* x x x))

(defn newton-transform [g]
  (fn [x]
    (- x (/ (g x) ((deriv g) x)))))

(defn newton-method [g guess]
  (fixed-point (newton-transform g) guess))

;(defn sqrt [x]
;  (newton-method (fn [y] (- (square y) x))
;                 1.0))

(defn fixed-point-of-transform [g transform guess]
  (fixed-point (transform g) guess))

;(defn sqrt [x]
;  (fixed-point-of-transform (fn [y] (/ x y))
;                            average-damp
;                            1.0))

(defn sqrt [x]
  (fixed-point-of-transform (fn [y] (- (square y) x))
                            newton-transform
                            1.0))

(defn cubic [a b c]
  (fn [x] (+ (cube x)
             (* a (square x))
             (* b x)
             c)))

(defn find-zero [a b c]
  (newton-method (cubic a b c) 1))

(defn double-it [f]
  (comp f f))