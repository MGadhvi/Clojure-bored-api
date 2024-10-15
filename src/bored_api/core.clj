(ns bored-api.core
  (:require [clj-http.client :as client]
            [clojure.core.async :refer [go <!]]))

(def api-url "https://www.boredapi.com/api/activity")

(defn fetch-activity []
  (let [response (client/get api-url {:as :json})]
    (if (= 200 (:status response))
      (:body response)
      (println "Error fetching activity:" (:status response)))))

(defn -main []
  (println "Fetching a random activity...")
  (let [activity (fetch-activity)]
    (when activity
      (println "Activity suggestion:" (:activity activity)))))
