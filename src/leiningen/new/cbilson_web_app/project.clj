(defproject {{name}} "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [compojure "1.1.9"]
                 [ring/ring-core "1.3.1"]
                 [ring/ring-jetty-adapter "1.3.1"]
                 [org.clojure/core.async "0.1.338.0-5c5012-alpha"]

                 [org.clojure/clojurescript "0.0-2322"]
                 [om "0.7.1"]
                 [com.facebook/react "0.11.1"]
                 [cljs-http "0.1.16"]]
  :plugins [[lein-ring "0.8.11"]
            [lein-cljsbuild "1.0.3"]
            [lein-pdo "0.1.1"]]
  :aliases {"up" ["pdo" "cljsbuild" "auto" "dev," "ring" "server-headless"]}
  :ring {:handler {{name}}.app/handler}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring-mock "0.1.5"]
                        [org.clojure/tools.namespace "0.2.6"]]
         :plugins [[com.cemerick/austin "0.1.5"]]
         :source-paths ["dev"]}}
  :cljsbuild {
              :builds {
                       :dev {
                             :source-paths ["src-cljs"]
                             :compiler {:output-to "resources/public/js/app.js"
                                        :output-dir "resources/public/js"
                                        :optimizations :none
                                        :pretty-print true
                                        :source-map "resources/public/js/app.js.map"}}
                       :prod {
                              :source-paths ["src-cljs"]
                              :compiler {:output-to "resources/public/js-min/app-min.js"
                                         :output-dir "resources/public/js-min"
                                         :optimizations :advanced
                                         :pretty-print false
                                         :source-map "resources/public/js-min/app-min.js.map"}}}})
