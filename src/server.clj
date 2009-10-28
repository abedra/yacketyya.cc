(use 'compojure
     'yacketyyacc.main)

(run-server {:port 8080} "/*" (servlet yacketyyacc))