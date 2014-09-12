(ns {{name}}.app
  (:require-macros [cljs.core.async.macros :refer [go alt!]])
  (:require [goog.events :as events]
            [cljs.core.async :refer [put! <! >! chan timeout]]
            [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]
            [cljs-http.client :as http]))

(enable-console-print!)

(defn $ [id] (.getElementById js/document))

(defn events
  ([el event-type]
     (events el event-type (chan)))
  ([el event-type c]
     (events/listen el event-type
                    (fn [e] (put! c evt)))))
