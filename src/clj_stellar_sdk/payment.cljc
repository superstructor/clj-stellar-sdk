(ns clj-stellar-sdk.payment
  (:require
    [clj-stellar-sdk.transaction :as transaction])
  #?(:clj
     (:import
       [org.stellar.sdk AssetTypeNative PaymentOperation$Builder])))

(defn native
  [destination amount]
  (let [builder (new PaymentOperation$Builder destination (new AssetTypeNative) amount)]
    (.build builder)))

(defn native!
  [server source destination amount]
  (transaction/submit! server source [(native destination amount)]))