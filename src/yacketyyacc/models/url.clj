(ns yacketyyacc.models.url
  (:require clj-record.boot))

(def db {:classname "org.postgresql.Driver"
         :subprotocol "postgresql"
         :subname "likestream_development"
         :user "postgres"})

(clj-record.core/init-model)