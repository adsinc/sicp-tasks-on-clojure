(ns sicp-tasks-on-clojure.chapter1-3-1)

(defn sum [term a next b]
  (if (> a b)
    0
    (+ (term a)
       (sum term (next a) next b))))

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

