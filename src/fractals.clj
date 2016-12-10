(ns book.mandelbrot
  (:import (java.awt Color Graphics2D)
           (java.awt.image BufferedImage)
           (javax.swing JFrame ImageIcon JLabel)))

(defn- escape
  [^double a0 ^double b0 depth]
  (loop [a a0
         b b0
         iteration 0]
    (cond
      (< 4 (+ (* a a) (* b b))) iteration
      (>= iteration depth) -1
      :else (recur (+ a0 (- (* a a) (* b b)))
                   (+ b0 (* 2 (* a b)))
                   (inc iteration)))))

(defn mendelbrot
  [rmin rmax imin imax & {:keys [width height depth]
                          :or {width 80 height 40 depth 1000}}]
  (let [rmin (double rmin)
        imin (double imin)
        stride-w (/ (- rmax rmin) width)
        stride-h (/ (- imax imin) height)]
    (loop [x 0
           y (dec height)
           escapes []]
      (if (== x width)
        (if (zero? y)
          (partition width escapes)
          (recur 0 (dec y) escapes))
        (recur (inc x) y (conj escapes (escape (+ rmin (* x stride-w))
                                               (+ imin (* y stride-h))
                                               depth)))))))

(defn render-text
  [mandelbrot-grid]
  (doseq [row mandelbrot-grid]
    (doseq [excape-iter row]
      (print (if (neg? excape-iter) \* \space)))
    (println)))

(defn render-image
  [mendelbrot-grid]
  (let [pallete (vec (for [c (range 360)]
                       (Color/getHSBColor
                         (/ (Math/log c) (Math/log 360))
                         1.0
                         1.0)))
        height (count mendelbrot-grid)
        width (count (first mendelbrot-grid))
        img (BufferedImage. width height BufferedImage/TYPE_INT_RGB)
        ^Graphics2D g (.getGraphics img)]
    (doseq [[y row] (map-indexed vector mendelbrot-grid)
            [x escape-iter] (map-indexed vector row)]
      (.setColor g (if (neg? escape-iter)
                     (pallete 0)
                     (pallete (mod (dec (count pallete)) (inc escape-iter)))))
      (.drawRect g x y 1 1))
    (.dispose g)
    img))

(def m (fn [] (mendelbrot -1.5 -1.3 -0.1 0.1
            :width 800 :height 800 :depth 500)))

(do (time (m)) nil)

(doto (JFrame.)
  (.add (JLabel.
          (ImageIcon.
            (render-image (m)))))
  (.pack)
  (.setDefaultCloseOperation JFrame/DISPOSE_ON_CLOSE)
  (.setVisible true))