(ns back.text
  (:require [clojure.string :as string]
            [clojure.zip :as zip]
            [pl.danieljanus.tagsoup :as soup]
            [org.httpkit.client :as http]
            [back.util :as util]))

(def non-text
  #{:script :iframe :style :link})

(def stopwords
  (set 
   (string/split 
    (util/resource "stopwords") 
    #"\n")))

(def dbg (atom nil))

(defn webpage
  [url]
  (-> url
      http/get
      deref
      :body
      string/lower-case
      soup/parse-string))

(defn text-content
  [root]
  (cond (= java.lang.String (type root)) [root]
        (non-text (soup/tag root)) []        
        :else (mapcat text (soup/children root))))

(defn webpage-wordcount
  [page]
  (->> page
       text-content
       (mapcat #(string/split % #"\b"))
       (map string/trim)
       (filter (partial re-matches #"^\w{3,}$"))
       (remove stopwords)
       frequencies))

(defn top-words
  [url limit]
  (->> url webpage webpage-wordcount
       (sort-by last >)
       (take limit)
       (into {})))

(defn wordcount-response
  [{{:strs [url]} :params}]
  (util/json-response 
   (top-words url 500)))
