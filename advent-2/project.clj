(defproject advent-1 "0.1.0-SNAPSHOT"
  :description "Advent of code 2019 first kata"
  :license {:name "MIT License"
            :url "http://www.opensource.org/licenses/mit-license.php"}
  :dependencies [[org.clojure/clojure "1.10.0"]]
  :main ^:skip-aot advent-1.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
