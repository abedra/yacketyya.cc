(ns yacketyyacc.models.url
  (:require clj-record.boot))

(def db {:classname "org.postgresql.Driver"
         :subprotocol "postgresql"
         :subname "yacketyyacc"
         :user "yacketyyacc"
         :password "yacketyyacc"})

(clj-record.core/init-model)