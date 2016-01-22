(defproject back "0.1.0"
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [http-kit "2.1.18"]
                 [polaris "0.0.15"]
                 [ring "1.4.0"]
                 [ring-cors "0.1.7"]
                 [clj-tagsoup "0.3.0"]
                 [org.clojure/data.json "0.2.6"]                 
                 [org.clojure/math.numeric-tower "0.0.4"]]
  :min-lein-version "2.0.0"  
  :main back.server)
