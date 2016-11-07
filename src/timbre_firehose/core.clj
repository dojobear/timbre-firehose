(ns timbre-firehose.core
  (:require [cheshire.core :as json]
            [taoensso.timbre :as timbre]
            [amazonica.aws.kinesisfirehose :as fh]
            [amazonica.core :refer [with-credential]]))

(defmacro with-aws-cred
  "If AWS credentials are supplied, then call the block using Amazonica's
   `with-credential`, otherwise just execute the block directly."
  [credential & body]
  `(if ~credential
     (with-credential ((juxt :access-key :secret-key :endpoint) ~credential)
       ~@body)
     ~@body))

(defn- create-output
  [data]
  (let [{:keys [level error-level? vargs_ ?file ?line ?err_ ?ns-str msg_]} data]
    (json/generate-string (merge {:level level
                                  :message @vargs_
                                  :file ?file
                                  :line ?line
                                  :ns ?ns-str}
                                 (when-let [e @?err_]
                                   {:error {:stacktrace (timbre/stacktrace e {:stacktrace-fonts {}})
                                            :exception-message (.getMessage e)
                                            :error-level error-level?}})))))

(defn firehose-appender
  [{:keys [stream-name credential]}]
  {:async? false
   :enabled? true
   :min-level nil
   :output-fn (fn [data]
                (create-output data))
   :fn (fn [data]
         (let [{:keys [output-fn]} data]
           (try 
             (with-aws-cred credential (fh/put-record stream-name (output-fn data)))
             (catch java.lang.IllegalArgumentException _
               nil))))})
