# timbre-firehose

A <a href="https://github.com/ptaoussanis/timbre" title="Timbre appender"> utilizing the Amazonica library to send logs and errors to Firehose.

```clojure
[timbre-firehose "0.1.1"]
```

## Usage

```clojure
(:require [taoensso.timbre :as timbre]
          [timbre-firehose.core :refer [firehose-appender])

(timbre/merge-config!
  {:appenders {:firehose (firehose-appender {:stream-name "/path/my-file.log"
                                             :credentials ["account-aws-access-key" "secret" "us-east-1"]})}})
```

## License

Copyright © 2016 David Bernal

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
