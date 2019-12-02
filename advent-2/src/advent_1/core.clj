(ns advent-1.core
  (:require [clojure.string :as str]
            [clojure.java.io :as io])
  (:gen-class))

(defn- compute-fuel
  "Computes the fuel needed to launch a certain mass"
  [mass]
  (int (- (Math/floor (/ mass 3)) 2)))

(defn fuel-seq
  "Generates a lazy seq of the needed recursive fuel"
  [mass]
  (let [needed-fuel (compute-fuel mass)]
    (lazy-seq (cons needed-fuel (fuel-seq needed-fuel)))))

(defn- compute-full-fuel
  "Computes the fuel needed to launch a module"
  [mass]
  (->> (fuel-seq mass)
       (take-while #(pos? %))
       (reduce +)))

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
       (map #(compute-full-fuel (Integer/parseInt %)))
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
