(defproject yacketyyacc "0.1.0-SNAPSHOT"
  :description "A url shortener"
  :url "http://github.com/abedra/yacketyya.cc"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [org.clojure/tools.logging "0.2.3"]
                 [org.clojure/java.jdbc "0.2.3"]
                 [compojure "1.1.3"]
                 [postgresql "9.1-901.jdbc4"]
                 [antlr/stringtemplate "2.2"]
                 [ring/ring-jetty-adapter "1.1.6"]
                 [hiccup "1.0.2"]]
  :main yacketyyacc.main)
