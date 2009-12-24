(ns relevance.string-template
  (:use clojure.walk
        clojure.contrib.json.write
        [clojure.contrib java-utils logging pprint])
  (:import [org.antlr.stringtemplate StringTemplateGroup StringTemplateErrorListener]))

(defn load-template-group [path]
  (doto (org.antlr.stringtemplate.StringTemplateGroup. path)
    (.setErrorListener
     (proxy [StringTemplateErrorListener] []
       (error [s t] (throw t))
       (warning [s] (log :warning (str "StringTemplate warning " s)))))))

(defn template-group []
  (load-template-group "templates"))

;; cache the templates except in development
(when-not (= (System/getenv "COMPOJURE_ENV") "development")
  (def template-group (memoize template-group)))

(defn populate-template [template-path attributes]
  (let [template (.getInstanceOf (template-group) template-path)]
    (doseq [[k v] (stringify-keys attributes)]
      (.setAttribute template (as-str k) v))
    template))
  
(defn render-template [template-path attributes]
  (str
   (populate-template "header" attributes)
   (populate-template template-path attributes)
;;   (populate-template "debug" {:attributes (json-str attributes)})
   (populate-template "footer" {})))

(defn render-plain-template [template-path attributes]
  (str (populate-template template-path attributes)))