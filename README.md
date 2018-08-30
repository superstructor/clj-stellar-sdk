
[![Clojars Project](https://img.shields.io/clojars/v/clj-stellar-sdk.svg)](https://clojars.org/clj-stellar-sdk)
[![CircleCI](https://circleci.com/gh/xlm-sg/clj-stellar-sdk/tree/master.svg?style=svg)](https://circleci.com/gh/xlm-sg/clj-stellar-sdk/tree/master)
[![GitHub license](https://img.shields.io/github/license/xlm-sg/clj-stellar-sdk.svg)](LICENSE.txt)

clj-stellar-sdk
===============

clj-stellar-sdk is a Clojure and ClojureScript library for communicating with a
[Stellar Horizon server](https://github.com/stellar/horizon). It is used for
building Stellar apps either on the JVM, Node.js or in the browser.

## Usage

```clojure
$ boot repl

(require '[clj-stellar-sdk.network :as network])
; nil

(network/use-testnet!)
; nil

(network/current)
; e.g. #object[org.stellar.sdk.Network 0x68d509c0 "org.stellar.sdk.Network@68d509c0"]

(network/passphrase (network/current))
; "Test SDF Network ; September 2015"

(require '[clj-stellar-sdk.keypair :as keypair])
; nil

(def s (keypair/from-secret-seed "SBK5Q77CDEUOCHNDCOUOXXJ3TQJO7NP44H5WAB2G4IX7MICL65ANI2CG"))
(def d (keypair/from-secret-seed "SCFIZ4DFMK4EUTEXQOTYNDXU4V6LYDV5TSGRXMXWB3QYPHG37GPJV75U"))
; e.g. #object[org.stellar.sdk.KeyPair 0x238eaee7 "org.stellar.sdk.KeyPair@238eaee7"]

(require '[clj-stellar-sdk.account :as account])
; nil

(account/balances (server/testnet) s)
; ({:type "native", :balance "9189.9998900", :code nil})

(require '[clj-stellar-sdk.payment :as payment])
; nil

(payment/native! (server/testnet) s d "10")
; e.g. {:result :success, :hash "4214b683989632742a95b42af6bae2b478af8aaf6a732234aad908a55535a402"}
```

## Donations

Stellar Lumens (XLM) address:

```
GBTYXS544T4YTTOQX6WHFLQHROLU2KJM7D2LA2VQPTCP4PT4EHFM7VVM
```

## License

Copyright © 2018 Isaac Johnston and [contributors](https://github.com/xlm-sg/clj-stellar-sdk/contributors).

Distributed under the Eclipse Public License, the same as Clojure.
