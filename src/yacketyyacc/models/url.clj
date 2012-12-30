(ns yacketyyacc.models.url
  (:refer-clojure :exclude [chars])
  (:require [clojure.java.jdbc :as sql]))

(def db "postgres://localhost/yacketyyacc")

;;; map of ascii characters 0-9 A-Z a-z
(def chars (map char (concat (range 48 57) (range 65 90) (range 97 122))))

(defn random-char
  "Generates a random character"
  []
  (nth chars
       (.nextInt (java.util.Random.) (count chars))))

(defn random-string
  "Generates a random string of length n"
  [length]
  (apply str (take length (repeatedly random-char))))

(defn find-by-original-url
  [original_url]
  (sql/with-connection db
    (sql/with-query-results results
      ["select * from urls where original_url = ?", original_url]
      (first results))))

(defn find-by-slug
  [slug]
  (sql/with-connection db
    (sql/with-query-results results
      ["select * from urls where shortened_url = ?", slug]
      (first results))))

(defn find-or-create
  [original_url]
  (or (find-by-original-url original_url)
      (sql/with-connection db
        (sql/insert-values :urls
                           [:original_url :shortened_url]
                           [original_url (random-string 4)]))))

(defn total-yaccs-shorn
  []
  (sql/with-connection db
    (sql/with-query-results results
      ["select max(id) from urls"]
      ((first results) :max))))

(defn shortened-url
  [original_url]
  ((find-or-create original_url) :shortened_url))