(ns yacketyyacc.main
  (:use compojure))

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

(defroutes yacketyyacc
  (GET "/" (str "Random string " (random-string 5))))