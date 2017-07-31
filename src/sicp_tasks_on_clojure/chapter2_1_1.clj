(ns sicp-tasks-on-clojure.chapter2-1-1)

(defn gdc [a b]
  (if (= b 0)
    a
    (gdc b (mod a b))))

(defn make-rat [n d]
  (let [g (gdc n d)]
    (println g)
    [(/ n g) (/ d g)]))

(defn numer [r] (first r))

(defn denom [r] (second r))

(defn add-rat [x y]
  (make-rat (+ (* (numer x) (denom y))
               (* (numer y) (denom x)))
            (* (denom x) (denom y))))

(defn sub-rat [x y]
  (make-rat (- (* (numer x) (denom y))
               (* (numer y) (denom x)))
            (* (denom x) (denom y))))

(defn mult-rat [x y]
  (make-rat (* (numer x) (numer y))
            (* (denom x) (denom y))))

(defn mul-rat [x y]
  (make-rat (* (numer x) (denom y))
            (* (denom x) (numer y))))

(defn eq-rat? [x y]
  (= (* (numer x) (denom y))
     (* (numer y) (denom x))))

(defn print-rat [x]
  (printf "%s/%s\n" (numer x) (denom x)))


