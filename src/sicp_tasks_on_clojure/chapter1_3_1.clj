(ns sicp-tasks-on-clojure.chapter1-3-1)

(defn sum [term a next b]
  (loop [a a
         acc 0]
    (if (> a b)
      acc
      (recur (next a) (+ acc (term a))))))

(defn cube [a]
  (* a a a))

(defn integral [f a b dx]
  (letfn [(add-dx [x] (+ x dx))]
    (* (sum f (+ a (/ dx 2)) add-dx b)
       dx)))

(defn ^double simpson [f a b n]
  (let [h (/ (- b a) n)]
    (letfn [(koeff [k]
              (cond
                (= k 0) 1
                (odd? k) 4
                :else 2))
            (yk [k]
              (* (koeff k)
                 (f (+ a (* k h)))))]
      (loop [k (dec n)
             acc (yk n)]
        (if (= 0 k)
          (* (/ h 3) (+ acc (yk k)))
          (recur (dec k) (+ acc (yk k))))))))

(defn accumulate [combiner null-value term a next b]
  (loop [a a
         acc null-value]
    (if (> a b)
      acc
      (recur (next a) (combiner acc (term a))))))

(defn product [term a next b]
  (accumulate * 1 term a next b))

;(defn product [term a next b]
;  (loop [a a
;         acc 1]
;    (if (> a b)
;      acc
;      (recur (next a) (* acc (term a))))))

(defn factorial [n]
  (product identity 1 inc n))
