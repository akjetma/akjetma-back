(ns back.util
  (:require [clojure.java.io :as io]
            [clojure.data.json :as json]
            [clojure.stacktrace :as trace]))

(defn resource
  [path]
  (slurp (io/resource path)))

(defn json-response
  [body]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body (json/write-str body)})

(defn wrap-error
  [app]
  (fn [request]
    (try
      (app request)
      (catch Exception e
        (trace/print-stack-trace e)
        {:status 400
         :headers {"Content-Type" "text/html"}
         :body (.getMessage e)}))))

