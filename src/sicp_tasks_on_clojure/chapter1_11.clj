(ns sicp-tasks-on-clojure.chapter1-11)

(defn f [n]
  (if (< n 3)
    n
    (+ (f (- n 1)) (f (- n 2)) (f (- n 3)))))

(defn f-tail
  ([n] (f-tail n 3 1 2 3))
  ([n i sum-3 sum-2 sum-1]
   (cond
     (< n 3) n
     (= i n) sum-1
     :else (f-tail n (inc i) sum-2 sum-1 (+ sum-1 sum-2 sum-3)))))