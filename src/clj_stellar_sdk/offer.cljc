(ns clj-stellar-sdk.offer
  (:require [clj-stellar-sdk.asset :as asset])
  #?(:clj
     (:import
      [org.stellar.sdk ManageOfferOperation ManageOfferOperation$Builder KeyPair])))

(defn buy
  ^ManageOfferOperation [^String code ^KeyPair issuer ^String amount ^String price]
  (let [builder (new ManageOfferOperation$Builder
                     (asset/native)             ; selling XLM to
                     (asset/create code issuer) ; buy an asset
                     amount
                     price)]
    (.build builder)))

(defn sell
  ^ManageOfferOperation [^String code ^KeyPair issuer ^String amount ^String price]
  (let [builder (new ManageOfferOperation$Builder
                     (asset/create code issuer) ; selling an asset
                     (asset/native)             ; to buy XLM
                     amount
                     price)]
    (.build builder)))

