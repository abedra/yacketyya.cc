(ns yacketyyacc.controllers.main
  (:use [hiccup.core :only (html)]
        [clojure.tools.logging :only (info)]
        [ring.util.response :only (redirect)])
  (:require [yacketyyacc.models.url :as url]))

(defn create [url]
  (info url)
  (let [short (url/shortened-url url)
        link (str "http://localhost:8080/" short)]
    (html
     [:p "Your new url is: "
      [:a {:href link}] (str) link])))

(defn show [id]
  (let [url (url/find-by-slug id)]
    (if (nil? url)
      "TODO"
      (do
        #_(url/update {:id (url :id) :clicks (inc (url :clicks))})
        (redirect (url :original_url))))))
