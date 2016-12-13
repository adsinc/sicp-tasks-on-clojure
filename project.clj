(defproject sicp-tasks-on-clojure "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [javax.ws.rs/jsr311-api "1.1.1"]
                 [org.glassfish.jersey.containers/jersey-container-grizzly2-http "2.24.1"]
                 [org.clojure/java.jdbc "0.7.0-alpha1"]
                 ]
  :aot [book.imaging annotations.jaxrs]
  )
