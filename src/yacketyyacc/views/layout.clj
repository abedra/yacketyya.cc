(ns yacketyyacc.views.layout
  (:use [hiccup.page :only (html5 include-js include-css)])
  (:require [yacketyyacc.models.url :as url]))

(defn application [& content]
  (html5
   [:head
    [:meta {:http-equiv "Content-Type" :content "text/html" :charset "utf-8"}]
    [:title "Yackety Yacc"]
    (include-js "javascripts/jquery-1.3.2.min.js"
                "javascripts/application.js")
    (include-css "stylesheets/reset.css"
                 "stylesheets/screen.css")]
   [:body
    [:div {:class "wrapper"}
     [:div {:id "header-wrap"}
      [:div {:id "header"}
       [:h1 [:a {:href "/"} "Yackety Yacc"]]
       [:span "Shave your urls and they'll purr like a Walrus."]
       [:div {:id "top-nav"}
        [:ul
         [:li [:a {:class "highlight" :href "/about"} "What is Yackety Yacc"]]
         [:li [:a {:class "highlight" :href "http://github.com/abedra/yacketyya.cc/issues"}
               "Found a bug?"]]
         [:li [:a {:class "highlight" :href "http://github.com/abedra/yacketyya.cc"}
               "Source"]]]]]]
     [:div {:id "content-wrap"}
      [:div {:id "content"}
       content
       [:div {:id "new-url"}]]
      [:div {:id "totals"}
       [:p [:span {:id "total-urls"} (url/total-yaccs-shorn)] " urls shorn to date, and many more to come!"]
       [:p "Copyright &copy; 2009-2012 Aaron Bedra"]]]]]))

(defn static [title & content]
  (html5
   [:head
    [:meta {:http-equiv "Content-Type" :content "text/html" :charset "utf-8"}]
    [:title title]
    (include-css "stylesheets/reset.css"
                 "stylesheets/screen.css")]
   [:body
    [:div {:class "wrapper"}
     [:div {:id "header-wrap"}
      [:div {:id "header"}
       [:h1 [:a {:href "/"} "Yackety Yacc"]]
       [:span "Shave your urls and they'll purr like a Walrus."]
       [:div {:id "top-nav"}
        [:ul
         [:li [:a {:class "highlight" :href "/about"} "What is Yackety Yacc"]]
         [:li [:a {:class "highlight" :href "http://github.com/abedra/yacketyya.cc/issues"}
               "Found a bug?"]]
         [:li [:a {:class "highlight" :href "http://github.com/abedra/yacketyya.cc"}
               "Source"]]]]]]
     [:div {:id "content-wrap"}
      [:div {:id "content"} content]
      [:div {:id "totals"}
       [:p "Copyright &copy; 2009-2012 Aaron Bedra"]]]]]))