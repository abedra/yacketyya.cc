(ns yacketyyacc.handlers.logging
  (:use clojure.contrib.logging))

(defn with-logging [handler prefix]
  (fn [request]
    (log :info (str prefix (:uri request)
                    "[" (:request-method request) "]"
                    "\n Params: "
                    (:params request)))
    (handler request)))

(defn standard-route
  [f]
  (with-logging f "Processing Request: "))