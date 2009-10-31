(ns yacketyyacc.main
  (:use compojure
        relevance.string-template)
  (:require [yacketyyacc.models.url :as url]))

(defn index []
  (render-template "index" {:total (url/total-yaccs-shaven)}))

(defn post-link [params]
  (render-plain-template "new_url" {:shortened ((url/find-or-create (params :url)) :shortened_url)}))

(defn lookup-url [params]
  (let [url (url/find-by-slug (params :url))]
    (if (nil? url)
      (page-not-found)
      (do
        (url/update {:id (url :id) :clicks (+ (url :clicks) 1)})
        (redirect-to (url :original_url))))))

(defn about []
  (render-template "about" {}))

(defroutes yacketyyacc
  (GET  "/"  (index))
  (POST "/"  (post-link params))
  (GET  "/about" (about))
  (GET  "/:url" (lookup-url params))
  (GET  "/assets/*" (or (serve-file (params :*)) :next)))