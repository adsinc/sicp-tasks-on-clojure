(ns sicp-tasks-on-clojure.ex-2-28)

(def x (list (list 1 2) (list 3 4)))

(defn fringe [t]
  (let [[x & xs] t]
    (cond
      (nil? t) (list)
      (seq? x) (concat (fringe x) (fringe xs))
      :else (cons x (fringe xs)))))