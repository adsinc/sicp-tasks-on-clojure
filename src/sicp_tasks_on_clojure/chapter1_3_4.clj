(ns sicp-tasks-on-clojure.chapter1-3-4
  (:use [sicp-tasks-on-clojure.chapter1-3-3 :only [avg fixed-point]])
  (:use [sicp-tasks-on-clojure.chapter1-2-6 :only [square]]))

(defn average-damp [f]
  #(avg % (f %)))

(defn cube-root [x]
  (fixed-point (average-damp #(/ x (square %))) 1.0))