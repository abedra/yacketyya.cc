(ns yacketyyacc.views.main
  (:require [yacketyyacc.views.layout :as layout]))

(defn index []
  (layout/application
   [:div {:id "url-form"}
    [:form {:action "/" :method "POST" :id "url-shortener"}
     [:div {:id "input-container"}
      [:input {:id "url" :name "url" :type "text"}]
      [:input {:type "button" :value "Shave" :id "submit" :class "button"}]]
     [:div {:class "clearfix"}]]]))

(defn about []
  (layout/static "Yackety Yack :: About"
   [:p "Yackety Yacc allows you to take long urls   and shave them to make them postable via twitter or any other service that limits the number of characters you are allowed to use in a message.  It makes communication via long urls much simpler. Yackety Yacc is an experiment in the " [:a {:href "http://github.com/weavejester/compojure"} "Compojure Web Framework"] " powered by the wonderful " [:a {:href "http://clojure.org"} "Clojure"] " language."]
   [:p [:b "Terms of Use:"] [:br] "Yackety Yacc is a free service meant for good, not spam.  Reports of abuse will cause your url to be removed without warning. In other words, just play nice and you can have all the happy shortened urls you want!  Neither Yackety Yacc or Aaron Bedra offers any warranty of any kind."]))