(ns yacketyyacc.main
  (:use compojure
        clojure.contrib.logging
        relevance.string-template)
  (:require [yacketyyacc.models.url :as url]))

(defn with-logging [handler prefix]
  (fn [request]
    (log :info (str prefix (:uri request)
                    "[" (:request-method request) "]"
                    "\n Params: "
                    (:params request)))
    (handler request)))

(defn standard-route
  [f]
  (with-logging f "Processing Request: "))

(defn index [params]
  (render-template "index" {:total (or (url/total-yaccs-shorn) 0)}))

(defn post-link [{params :params}]
  (println params)
  (render-plain-template "new_url"
                         {:shortened ((url/find-or-create (params :url)) :shortened_url)}))

(defn lookup-url [{params :params}]
  (let [url (url/find-by-slug (params :url))]
    (if (nil? url)
      (page-not-found)
      (do
        (url/update {:id (url :id) :clicks (inc (url :clicks))})
        (redirect-to (url :original_url))))))

(defn about []
  (render-template "about" {}))

(defroutes yacketyyacc
  (GET  "/"  (standard-route index))
  (POST "/"  (standard-route post-link))
  (GET  "/about" (about))
  (GET  "/:url" (standard-route lookup-url))
  (GET  "/assets/*" (or (serve-file (params :*)) :next))
  (ANY  "*" (page-not-found)))