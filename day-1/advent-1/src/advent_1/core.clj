(ns advent-1.core
  (:require [clojure.string :as str]
            [clojure.java.io :as io])
  (:gen-class))

(defn- compute-fuel
  "Computes the fuel needed to launch a module"
  [mass]
  (int (- (Math/floor (/ mass 3)) 2)))

(defn- parse-file
  "Reads the input file into a vector"
  [file]
  (with-open [rdr (io/reader file)]
    (->> (line-seq rdr)
         (into []))))

(defn- process-values
  "Processes all the read values"
  [values]
  (->> values
       (map #(compute-fuel (Integer/parseInt %)))
       (reduce +)))

(defn- write-results
  "Write results to the console"
  [result]
  (println "Total fuel:" result))

(defn -main
  "Entry point to the app"
  [& args]
  (-> (io/resource "modules.txt")
      parse-file
      process-values
      write-results))
