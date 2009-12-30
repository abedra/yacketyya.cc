(ns yacketyyacc.models.url
  (:require clj-record.boot))

(def db {:classname "org.postgresql.Driver"
         :subprotocol "postgresql"
         :subname "yacketyyacc"
         :user "yacketyyacc"
         :password "yacketyyacc"})

(clj-record.core/init-model)

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
  (first (find-by-sql ["select * from urls where original_url = ?", original_url])))

(defn find-by-slug
  [slug]
  (first (find-by-sql ["select * from urls where shortened_url = ?", slug])))

(defn find-or-create
  [original_url]
  (or (find-by-original-url original_url)
      (create {:original_url original_url :shortened_url (random-string 4)})))

(defn total-yaccs-shorn
  []
  ((first (find-by-sql ["select max(id) from urls"])) :max))

(defn shortened-url
  [original_url]
  ((find-or-create original_url) :shortened_url))