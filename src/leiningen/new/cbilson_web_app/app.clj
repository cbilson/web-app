(ns {{name}}.app
    (:require [compojure.core :refer [defroutes GET POST]]
              [compojure.handler :as ch]
              [compojure.route :as r :refer [resources not-found]]
              [ring.util.response :as resp :refer [resource-response]]))

(defroutes routes
  (GET "/" [] (resource-response "index.html" {:root "public"}))
  (resources "/")
  (not-found "Not Found"))

(def handler
  (ch/site #'routes))
