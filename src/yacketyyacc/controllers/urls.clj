(ns yacketyyacc.controllers.urls
  (:use compojure
        relevance.string-template)
  (:require [yacketyyacc.models.url :as url]))

(defn create [{params :params}]
  (render-plain-template "new_url"
                         {:shortened (url/shortened-url (params :url))}))

(defn show [{params :params}]
  (let [url (url/find-by-slug (params :url))]
    (if (nil? url)
      (page-not-found)
      (do
        (url/update {:id (url :id) :clicks (inc (url :clicks))})
        (redirect-to (url :original_url))))))
