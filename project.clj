(defproject back "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [http-kit "2.1.18"]
                 [polaris "0.0.15"]
                 [ring "1.4.0"]
                 [ring-cors "0.1.7"]
                 [clj-tagsoup "0.3.0"]
                 [org.clojure/data.json "0.2.6"]                 
                 [org.clojure/math.numeric-tower "0.0.4"]]
  
  :main back.server)
