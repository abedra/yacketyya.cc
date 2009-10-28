(ns yacketyyacc.models.url
  (:require clj-record.boot))

(def db {:classname "org.postgresql.Driver"
         :subprotocol "postgresql"
         :subname "yacketyyacc"
         :user "yacketyyacc"
         :password "yacketyyacc"})

(clj-record.core/init-model)

(defn find-by-slug [slug]
  (first (find-by-sql ["select * from urls where shortened_url = ?", slug])))
