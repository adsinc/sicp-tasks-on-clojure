(ns annotations.jaxrs
  (:import (javax.ws.rs Path PathParam Produces GET)))

(definterface Greeting
  (greet [^String visitor-name]))

(deftype ^{Path "/greet/{visitorname}"} GreetingResource []
  Greeting
  (^{GET true
     Produces ["text/plain"]}
     greet
    [this ^{PathParam "visitorname"} visitor-name]
    (format "Hello %s!" visitor-name)))

;(org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory/createHttpServer
;  (java.net.URI/create "http://localhost:8080/")
;  (.packages (ResourceConfig.) (into-array String ["annotations.jaxrs"])))