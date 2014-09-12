(ns leiningen.new.cbilson-web-app
  (:require [leiningen.new.templates :refer [renderer name-to-path ->files]]
            [leiningen.core.main :as main]))

(def render (renderer "cbilson-web-app"))

(defn cbilson-web-app
  "Create an om+compojure application"
  [name]
  (let [data {:name name
              :sanitized (name-to-path name)}]
    (main/info "Generating fresh 'lein new' cbilson-web-app project.")
    (->files data
             ["project.clj" (render "project.clj" data)]
             [".gitignore" (render "gitignore" data)]
             ["dev/user.clj" (render "user.clj" data)]
             ["src/{{sanitized}}/app.clj" (render "app.clj" data)]
             ["src-cljs/{{sanitized}}/app.cljs" (render "app.cljs" data)]
             ["resources/public/index.html" (render "index.html" data)])))
