(ns timbre-firehose.core-test
  (:use midje.sweet)
  (:require [timbre-firehose.core :as target]
            [taoensso.timbre :as timbre]
            [amazonica.aws.kinesisfirehose :as fh]))

(fact "invoking timbre with a firehose appender makes the correct calls to amazonica."
  (do (timbre/set-config! {:level :debug :appenders {:firehose (target/firehose-appender {:stream-name "test"})}})
      (timbre/error "error test message") => nil
      (provided (fh/put-record "test" #"\{\"level\":\"error\",\"message\":\[\"error test message\"\],\"file\":\".*/timbre-firehose/test/timbre_firehose/core_test.clj\",\"line\":9,\"ns\":\"timbre-firehose.core-test\"\}") => nil)))










