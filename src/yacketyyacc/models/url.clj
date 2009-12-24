(ns yacketyyacc.models.url
  (:require clj-record.boot))

(def db {:classname "org.postgresql.Driver"
         :subprotocol "postgresql"
         :subname "yacketyyacc"
         :user "yacketyyacc"
         :password "yacketyyacc"})

(clj-record.core/init-model)

(def chars (map char (concat (range 48 58) (range 66 92) (range 97 123))))

(defn random-char
  "Generates a random character"
  []
  (nth chars
       (.nextInt (java.util.Random.) (count chars))))

(defn random-string
  "Generates a random string of length n"
  [length]
  (apply str (take length (repeatedly random-char))))

(defn find-by-slug [slug]
  (first (find-by-sql ["select * from urls where shortened_url = ? limit 1", slug])))

(defn find-or-create [original_url]
  (let [lookup (first (find-by-sql ["select * from urls where original_url = ? limit 1", original_url]))]
    (if lookup
      lookup
      (create {:original_url original_url :shortened_url (random-string 4)}))))

(defn total-yaccs-shorn []
  ((first (find-by-sql ["select max(id) from urls"])) :max))
