(ns yacketyyacc.main
  (:use compojure
        relevance.string-template)
  (:require [yacketyyacc.models.url :as url]))

(defn index []
  (render-template "index" {:total (url/total-yaccs-shaven)}))

(defn post-link [params]
  (render-template "new_url" {:shortened ((url/find-or-create (params :url)) :shortened_url)}))

(defn lookup-url [params]
  (redirect-to ((url/find-by-slug (params :url)) :original_url)))

(defroutes yacketyyacc
  (GET  "/"  (index))
  (POST "/"  (post-link params))
  (GET  "/:url" (lookup-url params))
  (GET  "/assets/*" (or (serve-file (params :*)) :next)))