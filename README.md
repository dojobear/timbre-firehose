# timbre-firehose

A Clojure library designed to ... well, that part is up to you.

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
