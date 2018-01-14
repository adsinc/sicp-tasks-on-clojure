(ns sicp-tasks-on-clojure.ex-2-2)

(defrecord segment [start end])

(defrecord point [x y])

(defrecord rectangle [s1 s2 s3 s4])

(defn seg-length [segment]
  (let [start (:start segment)
        end (:end segment)
        a (- (:x end) (:x start))
        b (- (:y end) (:y start))]
    (Math/sqrt (+ (* a a)
                  (* b b)))))

(defn perimeter [rect]
  (* 2
     (+ (seg-length (:s1 rect))
        (seg-length (:s2 rect)))))

(defn square [rect]
     (* (seg-length (:s1 rect))
        (seg-length (:s2 rect))))

(def r (->rectangle (->segment (->point 0 0) (->point 0 5))
                    (->segment (->point 0 5) (->point 5 5))
                    (->segment (->point 5 5) (->point 5 0))
                    (->segment (->point 5 0) (->point 0 0))))