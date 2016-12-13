(ns book.html-dsl
  (:require [clojure.string])
  (:use clojure.test))

(defmacro are* [f & body]
  `(are [x# y#] (~'= (~f x#) y#)
        ~@body))

(declare html attrs)

(deftest test-html
         (are* html
               [:html]
               "<html></html>"

               [:a [:b]]
               "<a><b></b></a>"

               [:a {:href "/"} "Home"]
               "<a href=\"/\">Home</a>"

               [:div "foo" [:span "bar"] "baz"]
               "<div>foo<span>bar</span>baz</div>"))

(deftest test-attrs
  (are* (comp clojure.string/trim attrs)
        nil ""

        {:foo "bar"}
        "foo=\"bar\""

        (sorted-map :a "b" :c "d")
        "a=\"b\" c=\"d\""))

(defn attrs
  [attr-map]
  {:pre [(or (map? attr-map)
              (nil? attr-map))]}
  (->> attr-map
       (mapcat (fn [[k v]] [\space (name k) "=\"" v "\""]))
               (apply str)))

(defn html
  [x]
  {:pre [(if (sequential? x)
           (some #(-> x first %) [keyword? symbol? string?])
           (not (map? x)))]
   :post [(string? %)]}
  (if-not (sequential? x)
    (str x)
    (let [[tag & body] x
          [attr-map body] (if (map? (first body))
                                    [(first body) (rest body)]
                                    [nil body])]
      (str "<" (name tag) (attrs attr-map) ">"
           (apply str (map html body))
           "</" (name tag) ">"))))

(run-tests)