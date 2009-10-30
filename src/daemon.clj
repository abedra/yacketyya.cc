(ns likestream.system.daemon
  (:use [clojure.contrib.java-utils :only (file)]
        compojure
        yacketyyacc.main))

(defn daemonize 
  []
  "Start the runner as a daemon."
  (.deleteOnExit (file "tmp/pids/daemon.pid"))
  (.. System out close)
  (.. System err close)
  (run-server {:port 8088} "/*" (servlet yacketyyacc)))

(daemonize)
