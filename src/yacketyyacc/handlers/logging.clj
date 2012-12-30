(ns yacketyyacc.handlers.logging
  (:use [clojure.tools.logging :only (info)]))

(defn with-logging [handler]
  ;; TODO: filter asset requests
  (fn [request]
    (info (str (:uri request)
               "[" (:request-method request) "]"
               "\n Params: "
               (or (:params request) "{}")))
    (handler request)))