(ns clj-stellar-sdk.account
    (:require
     [clj-stellar-sdk.transaction :as transaction])
    #?(:clj (:import
             [org.stellar.sdk CreateAccountOperation$Builder KeyPair]
             [org.stellar.sdk.responses AccountResponse])))

(defn from-keypair
  [server pair]
  (-> server
      (.accounts)
      (.account pair)))

(defn create!
  [server source destination starting-balance]
  (let [builder (doto (new CreateAccountOperation$Builder destination starting-balance)
                  (.setSourceAccount source))
        operation (.build builder)]
     (transaction/submit! server source [operation])))

(defn balances
  [server pair]
  (let [account (from-keypair server pair)
        balances (.getBalances account)]
    (map #(hash-map :asset-type (.getAssetType %1)
                    :asset-code (.getAssetCode %1)
                    :balance    (.getBalance %1))
         balances)))


