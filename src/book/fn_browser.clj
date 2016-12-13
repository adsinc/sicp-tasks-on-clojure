(ns book.fn-browser
  (:import (java.util Vector)
           (javax.swing JList JFrame JScrollPane JButton JOptionPane JTextArea)
           (java.awt Dimension BorderLayout)
           (java.awt.event ActionListener)))

(declare window)

(defonce fn-names (->> (ns-publics 'clojure.core)
                       (map key)
                       sort
                       Vector.
                       JList.))

(defn show-info []
  (when-let [selected-fn (.getSelectedValue fn-names)]
    (JOptionPane/showMessageDialog
      window
      (-> (ns-resolve 'clojure.core selected-fn)
          meta
          :doc
          (JTextArea. 10 40)
          JScrollPane.)
      (str "Doc string for clojure.core/" selected-fn)
      JOptionPane/INFORMATION_MESSAGE)))

(defonce window (doto (JFrame. "\"Interacive Developement\"")
                  (.setSize (Dimension. 400 300))
                  (.add (JScrollPane. fn-names))
                  (.add BorderLayout/SOUTH
                        (doto (JButton. "Show Info")
                          (.addActionListener (reify ActionListener
                                                (actionPerformed [_ e]
                                                  (show-info))
                                                ))))
                  (.setVisible true)))



