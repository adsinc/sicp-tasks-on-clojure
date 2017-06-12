(ns sicp-tasks-on-clojure.chapter1-2-6)

(defn square [x] (* x x))

(defn divides? [a b]
  (= (mod a b) 0))

(defn next [n]
  (if (= n 2)
    3
    (+ n 2)))

(defn find-divisor [n test-divisor]
  (cond
    (> (square test-divisor) n) n
    (divides? n test-divisor) test-divisor
    :else (find-divisor n (next test-divisor))))

(defn smallest-divisor [n]
  (find-divisor n 2))

(defn prime? [n]
  (= n (smallest-divisor n)))

(defn expmod [base exp m]
  (cond
    (= exp 0) 1
    (even? exp) (mod (square (expmod base (/ exp 2) m)) m)
    :else (mod (* base (expmod base (- exp 1) m)) m)))

(defn fermat-test [n]
  (letfn [(try-it [a] (= (expmod a n n) a))]
    (try-it (inc (rand-int (dec n))))))

(defn fast-prime? [n times]
  (cond
    (= times 0) true
    (fermat-test n) (fast-prime? n (dec times))
    :else false))

(defn search-primes [from to]
  (for [i (range from (inc to))
        :when (and (odd? i)
                   (time (prime? i)))]
    i))

(defn search-primes2 [from to]
  (loop [i from]
    (when (and (odd? i) (prime? i))
      (time (prime? i))
      (println i))
    (when-not (= i to)
      (recur (inc i)))))