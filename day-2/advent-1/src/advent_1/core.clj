(ns advent-1.core
  (:require [clojure.string :as str]
            [clojure.java.io :as io])
  (:gen-class))

(defn- valid-command?
  "Checks if the command is valid"
  [[op & rest]]
    (cond
      (or (= op 1) (= op 2)) (= (count rest) 3)
      (= op 99) true
      :else false))

(defn- compute-commands
  "Computes the commands modifiying the program"
  [start program]
  (if (>= start (count program))
      program
      (let [end (+ start 4)
            code (take 4 (subvec program start))]
        (when (valid-command? code)
          (let [[op pos-1 pos-2 res] code
                val-1 (get program pos-1)
                val-2 (get program pos-2)]
            (cond
              (= op 1) (compute-commands end (assoc program res (+ val-1 val-2)))
              (= op 2) (compute-commands end (assoc program res (* val-1 val-2)))
              (= op 99) program
              :else nil))))))

(defn- parse-file
  "Reads the input file into a vector"
  [file]
  (mapv #(Integer/parseInt %)
        (-> (slurp file)
            (str/split #","))))

(defn- process-values
  "Processes all the read values"
  [values]
  (compute-commands 0 values))

(defn- write-results
  "Write results to the console"
  [result]
  (println "Position 0:" (first result)))

(defn -main
  "Entry point to the app"
  [& args]
  (-> (io/resource "input.txt")
      parse-file
      process-values
      write-results))
