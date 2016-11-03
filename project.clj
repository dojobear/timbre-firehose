(defproject timbre-firehose "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :plugins [[lein-midje "3.1.3"]]
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"] 
                 [cheshire "5.4.0" :exclusions [com.fasterxml.jackson.core/jackson-core]]
                 [com.taoensso/timbre "4.7.4"]
                 [amazonica "0.3.77"]]
  :profiles {:dev {:dependencies [[midje "1.6.3"]]}})
