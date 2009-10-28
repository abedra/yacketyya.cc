(ns yacketyyacc.main
  (:use compojure)
  (:require [yacketyyacc.models.url :as url]))

(defn index []
  (html (form-to [:post "/"]
                 [:label {:for "url"} "URL: "]
                 (text-field "url")
                 (submit-button "Shorten"))))

(defn post-link [params]
  (str "Your shortened url is: http://yacketyya.cc/" ((url/find-or-create (params :url)) :shortened_url)))

(defn lookup-url [params]
  (redirect-to ((url/find-by-slug (params :url)) :original_url)))

(defroutes yacketyyacc
  (GET  "/"  (index))
  (POST "/"  (post-link params))
  (GET  "/:url" (lookup-url params)))