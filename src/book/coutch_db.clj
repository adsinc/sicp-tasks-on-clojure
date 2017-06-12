(ns book.coutch-db
  (:use [com.ashafa.clutch])
  (:use [com.ashafa.clutch.view-server :only [view-server-exec-string]]))

;(def db (create-database "repl-crud"))

;(put-document db {:_id "foo" :some-data "bar"})

;(put-document db (assoc (get-document db "foo") :other-data "quux"))

;(delete-document db (get-document db "foo"))

;(put-document db {:_id "foo"
;                         :data ["bar" {:details ["bat" false 42]}]})

;(->> (get-document db "foo")
;     :data
;     second
;     :details
;     (filter number?))

;(clutch/bulk-update (create-database "logging")
;                    [{:evt-type "auth/new-user" :username "Chas"}
;                     {:evt-type "auth/new-user" :username "Dave"}
;                     {:evt-type "sales/purchase" :username "Chas" :products ["widget1"]}
;                     {:evt-type "sales/purchase" :username "Robin" :products ["widget14"]}
;                     {:evt-type "sales/RFQ" :username "Robin" :budget 20000}])

;(clutch/save-view "logging" "jsviews"
;  (clutch/view-server-fns :javascript
;    {:type-counts
;     {:map "function(doc) {
;                emit(doc['evt-type'], null)
;            }"
;      :reduce "function(keys, vals, rereduce) {
;                  return rereduce ? sum(vals) : vals.length
;            }"}}))

;(clutch/get-view "logging" "jsviews" :type-counts {:group true})

;(->> (clutch/get-view "logging" "jsviews" :type-counts {:group true})
;     (map (juxt :key :value))
;     (into {}))

;(use '[com.ashafa.clutch.view-server :only (view-server-exec-string)])

;(clutch/configure-view-server "logging" (view-server-exec-string))

;(clutch/save-view "logging" "clj-view"
;                  (clutch/view-server-fns :clojure
;                                          {:type-counts
;                                           {:map (fn [doc] [[(:evt-type doc) nil]])
;                                            :reduce (fn [keys vals rereduce] (if rereduce (reduce + vals) (count vals)))}}))

;(->> (clutch/get-view "logging" "clj-view" :type-counts {:group true})
;     (map (juxt :key :value))
;     (into {}))

;(clutch/get-view "logging" "clj-view" :type-counts)

;(create-database "changes")

(changes "changes" :echo (partial println "changes:"))

(bulk-update "changes" [{:_id "doc1"} {:_id "doc2"}])

(get-document "changes")

(delete-document "changes" "doc1")