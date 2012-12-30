(ns yacketyyacc.main
  (:use [compojure.core :only (defroutes GET POST)]
        [ring.adapter.jetty :only (run-jetty)])
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [yacketyyacc.controllers.main :as main]
            [yacketyyacc.views.main :as views]
            [yacketyyacc.handlers.logging :as logging]))

(defroutes routes
  (GET "/" [] (views/index))
  (POST "/" [url] (main/create url))
  (GET "/about" [] (views/about))
  (GET "/:id" [id] (main/show id))
  (route/resources "/"))

(def application
  (-> (handler/site routes)
      logging/with-logging))

(defn -main []
  (run-jetty (var application) {:port 8080
                                :join? false}))