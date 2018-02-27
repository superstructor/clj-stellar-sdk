(ns clj-stellar-sdk.offer
  (:require [clj-stellar-sdk.asset :as asset])
  #?(:clj
     (:import
      [org.stellar.sdk ManageOfferOperation$Builder])))

(defn buy
  [code issuer amount price]
  (let [builder (new ManageOfferOperation$Builder
                     (asset/native) ; selling XLM
                     (asset/create code issuer)          ; buying an asset
                     amount
                     price)]
    (.build builder)))

(defn sell
  [code issuer amount price]
  (let [builder (new ManageOfferOperation$Builder
                     (asset/create code issuer)           ; selling an asset
                     (asset/native)  ; buying XLM
                     amount
                     price)]
    (.build builder)))

