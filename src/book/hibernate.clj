(ns book.hibernate
  (:import (org.hibernate SessionFactory cfg.Configuration)
           (book.hibernate Author)))

(defonce session-factory
         (delay (-> (Configuration.)
                    .configure
                    .buildSessionFactory)))

(defmacro with-transaction
  [& body]
  `(let [~'tx (.beginTransaction ~'session)]
     ~@body
     (.commit ~'tx)))

(defn add-authors
  [& authors]
  (with-session @session-factory
                (with-transaction
                  (doseq [author authors]
                    (.save session author)))))

(add-authors
  (Author. "Christophe" "Grand")
  (Author. "Brian" "Carper")
  (Author. "Chas" "Emerick"))

(defmacro with-session
  [session-factory & body]
  `(with-open [~'session (.openSession ~(vary-meta session-factory assoc :tag 'SessionFactory))]
     ~@body))

(defn get-authors
  []
  (with-session @session-factory
                (-> session
                    (.createQuery "from Author")
                    .list)))

(for [{:keys [id firstName lastName]} (map bean (get-authors))]
  (str id ", " lastName ", " firstName))

