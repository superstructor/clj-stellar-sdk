(ns clj-stellar-sdk.payment
  (:require
    [clj-stellar-sdk.transaction :as transaction])
  #?(:clj
     (:import
       [java.lang String]
       [org.stellar.sdk AssetTypeNative KeyPair PaymentOperation PaymentOperation$Builder Server])))

(defn native
  ^PaymentOperation [^KeyPair destination ^String amount]
  (let [builder (new PaymentOperation$Builder destination (new AssetTypeNative) amount)]
    (.build builder)))

(defn native!
  [^Server server ^KeyPair source ^KeyPair destination ^String amount]
  (transaction/submit! server source [(native destination amount)]))