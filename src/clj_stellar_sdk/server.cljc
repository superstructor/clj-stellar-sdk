(ns clj-stellar-sdk.server
  #?(:clj (:import
            [org.stellar.sdk Server])))

(def testnet-uri "https://horizon-testnet.stellar.org")
(def public-uri "https://horizon.stellar.org/")

(defn from-uri
  [uri]
  (new Server uri))

(defn testnet
  []
  (from-uri testnet-uri))

(defn public
  []
  (from-uri public-uri))
