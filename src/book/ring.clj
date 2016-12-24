(ns book.ring
  (:use [ring.adapter.jetty :only (run-jetty)])
  (:use [ring.middleware.params :only (wrap-params)]))

(use '[ring.middleware.params :only (wrap-params)])

(defn app*
  [{:keys [uri params]}]
  {:body (format "You requested %s with query %s" uri params)})

(def app (wrap-params app*))

(def server (run-jetty #'app {:port 8080 :join? false}))