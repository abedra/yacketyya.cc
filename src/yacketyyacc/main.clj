(ns yacketyyacc.main
  (:use compojure
        clojure.contrib.logging
        relevance.string-template
        yacketyyacc.handlers.logging)
  (:require [yacketyyacc.models.url :as url]
            [yacketyyacc.controllers.urls :as urls]))

(defn index [params]
  (render-template "index"
                   {:total (or (url/total-yaccs-shorn) 0)}))

(defn about []
  (render-template "about" {}))

(defroutes yacketyyacc
  (GET  "/"         (standard-route index))
  (POST "/"         (standard-route urls/create))
  (GET  "/:url"     (standard-route urls/show))
  (GET  "/about"    (about))
  (GET  "/assets/*" (or (serve-file (params :*)) :next))
  (ANY  "*"         (page-not-found)))