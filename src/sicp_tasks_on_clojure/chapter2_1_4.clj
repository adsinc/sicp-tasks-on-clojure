(ns sicp-tasks-on-clojure.chapter2-1-1)

(defrecord interval [min max])

(defn add-interval [x y]
  (->interval (+ (:min x) (:min y))
              (+ (:max x) (:max y))))

(defn sub-interval [x y]
  (->interval (- (:min x) (:min y))
              (- (:max x) (:max y))))

(defn mul-interval [x y]
  (let [p1 (* (:min x) (:min y))
        p2 (* (:min x) (:max y))
        p3 (* (:max x) (:min y))
        p4 (* (:max x) (:max y))]
    (->interval (min p1 p2 p3 p4)
                (max p2 p2 p3 p4))))

(defn div-interval
  [x y]
  {:pre [(or
           (> (:min y) 0)
           (< (:max y) 0))]}
  (mul-interval x
                (->interval (/ 1.0 (:max y))
                            (/ 1.0 (:min y)))))

(defn width [x]
  (/ (- (:max x) (:min x))
     2))