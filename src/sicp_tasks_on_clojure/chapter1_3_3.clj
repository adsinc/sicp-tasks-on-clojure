(ns sicp-tasks-on-clojure.chapter1-3-3)

(def tolerance 0.00001)

(defn fixed-point [f first-guess]
  (letfn [(close-enough? [v1 v2]
            (< (Math/abs (- v1 v2)) tolerance))
          (try-count [guess]
            (let [next (f guess)]
              (if (close-enough? guess next)
                next
                (try-count next))))]
    (try-count first-guess)))

(defn avg [x y] (/ (+ x y) 2))

(defn sqrt [x]
  (fixed-point #(avg % (/ x %)) 1.0))