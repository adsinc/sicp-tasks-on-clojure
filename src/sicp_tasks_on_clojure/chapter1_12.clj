(ns sicp-tasks-on-clojure.chapter1-12)

(defn paskal [i line]
  (if (or (= i 1) (= i line))
    1
    (+ (paskal (dec i) (dec line)) (paskal i (dec line)))))



(defn p [i line cache]
  (let [put #(assoc-in cache [i line] (+ (p (dec i) (dec line)) (p i (dec line))))
        val (get-in cache [i line] (do
                                     (if (or (= i 1) (= i line))
                                       1
                                       (get-in cache [i line]))))]
    val))