(ns user
  "Tools for interactive development with the REPL. This file should
  not be included in a production build of the application."
  (:require
   [clojure.java.io :as io]
   [clojure.java.javadoc :refer [javadoc]]
   [clojure.pprint :refer [pprint]]
   [clojure.reflect :refer [reflect]]
   [clojure.repl :refer [apropos dir doc find-doc pst source]]
   [clojure.set :as set]
   [clojure.string :as str]
   [clojure.test :as test]
   [clojure.tools.namespace.repl :refer [refresh refresh-all]]
   [ring.adapter.jetty :as ring]
   [{{name}}.app]))

(def system
  "A Var containing an object representing the application under
  development."
  nil)

(defn start
  "Starts the system running, updates the Var #'system."
  ([] (start nil))
  ([{:keys [port join?] :or {port 3000 join? false}}]
     (alter-var-root
      #'system
      (fn [sys]
        (assoc sys
          :server
          (ring/run-jetty #'{{name}}.app/handler {:port port :join? join?}))))))

(defn stop
  "Stops the system if it is currently running, updates the Var
  #'system."
  []
  (alter-var-root #'system
                  (fn [s]
                    (when-let [server (:server s)]
                      (println "stopping server...")
                      (.stop server))
                    (dissoc s :server))))

(defn go
  "Initializes and starts the system running."
  []
  (start)
  :ready)

(defn reset
  "Stops the system, reloads modified source files, and restarts it."
  []
  (stop)
  (refresh :after 'user/go))
