(ns yacketyyacc.main
  (:use compojure)
  (:require [yacketyyacc.models.url :as url]))

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

(defn index []
  (html (form-to [:post "/"]
                 [:label {:for "url"} "URL: "]
                 (text-field "url")
                 (submit-button "Shorten"))))

(defn post-link [params]
  (str "http://yacketyya.cc/" (random-string 5)))

(defroutes yacketyyacc
  (GET  "/" (index))
  (POST "/" (post-link params)))