(use 'compojure
     'yacketyyacc.main)

(run-server {:port 8088} "/*" (servlet yacketyyacc))