(ns back.server
  (:require [org.httpkit.server :as http]
            [polaris.core :as polaris]
            [ring.middleware.content-type :refer [wrap-content-type]]
            [ring.middleware.params :refer [wrap-params]]
            [ring.middleware.cors :refer [wrap-cors]]
            [back.util :as util]
            [back.text :as text]))

(def routes
  [["/words.json" :words text/wordcount-response]])

(def router
  (-> routes
      polaris/build-routes
      polaris/router
      util/wrap-error
      wrap-content-type
      wrap-params
      (wrap-cors :access-control-allow-origin [#"http://localhost:\d+" #"https://akjetma.github.io"]
                 :access-control-allow-methods [:get :post])))

(defonce server (atom nil))

(defn stop-server
  []
  (when-not (nil? @server)
    (@server :timeout 100)
    (println "server stopped")
    (reset! server nil)))

(defn start-server
  []
  (stop-server)
  (let [port (Integer/parseInt (get (System/getenv) "PORT" "5000"))
        server* (http/run-server #'router {:port port})]
    (println "server started on port" port)
    (reset! server server*)))

(defn -main
  []
  (start-server))
