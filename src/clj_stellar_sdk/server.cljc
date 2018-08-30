(ns clj-stellar-sdk.server
  #?(:clj (:import
            [org.stellar.sdk Server])))

(def testnet-uri "https://horizon-testnet.stellar.org")
(def public-uri "https://horizon.stellar.org/")

(defn from-uri
  ^Server [^String uri]
  (new Server uri))

(defn testnet
  ^Server []
  (from-uri testnet-uri))

(defn public
  ^Server []
  (from-uri public-uri))
