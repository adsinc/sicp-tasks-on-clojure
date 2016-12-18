(ns book.jdbc
  (:require [clojure.java.jdbc :as jdbc])
  (:use [korma db core]))

(def db-spec {:classname "org.sqlite.JDBC"
              :subprotocol "sqlite"
              :subname "test.db"})

(jdbc/execute! db-spec
               (jdbc/create-table-ddl
                 :authors
                 [[:id "integer primary key"]
                  [:first_name "varchar"]
                  [:last_name "varchar"]]))

(jdbc/insert-multi! db-spec :authors
              [{:first_name "Chas" :last_name "Emerick"}
               {:first_name "Oleg" :last_name "Kaurov"}
               {:first_name "Roma" :last_name "Sco4i"}])

(jdbc/query db-spec ["select * from authors where id > ?" 0])

(jdbc/with-db-transaction [db db-spec]
                          (.setTransactionIsolation (jdbc/get-connection db)
                                                    java.sql.Connection/TRANSACTION_SERIALIZABLE)
                          (jdbc/delete! db :authors ["id = ?" 1])
                          (throw (Exception. "Abort transaction!")))

(jdbc/execute! db-spec
               (jdbc/create-table-ddl
                 :country
                 [[:id "integer primary key"]
                  [:country "varchar"]]))

(jdbc/execute! db-spec
               (jdbc/create-table-ddl
                 :author
                 [[:id "integer primary key"]
                  [:country_id "integer constraint fk_country_id references country(id)"]
                  [:first_name "varchar"]
                  [:last_name "varchar"]
                  ]))

(jdbc/insert-multi! db-spec :country
                    [{:id 1 :country "USA"}
                    {:id 2 :country "Canada"}
                    {:id 3 :country "France"}])

(jdbc/insert-multi! db-spec :author
                    [
                     {:first_name "Chas" :last_name "Emerick" :country_id "1"}
                     {:first_name "Christophe" :last_name "Grand" :country_id "3"}
                     {:first_name "Brian" :last_name "Carper" :country_id "2"}
                     {:first_name "Mark" :last_name "Twain" :country_id "1"}
                    ])

(defdb korma-db db-spec)

(declare author)

(defentity country
           (pk :id)
           (has-many author))

(defentity author
           (pk :id)
           (table :author)
           (belongs-to country))

(select author
        (with country)
        (where {:first_name "Chas"}))

(select author
        (with country)
        (where (like :first_name "Ch%"))
        (order :last_name :asc)
        (limit 1)
        (offset 1))

(println (sql-only (select author
        (with country)
        (where (like :first_name "Ch%"))
        (order :last_name :asc)
        (limit 1)
        (offset 1))))

