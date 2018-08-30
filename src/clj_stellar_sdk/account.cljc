(ns clj-stellar-sdk.account
    (:require
     [clj-stellar-sdk.transaction :as transaction])
    #?(:clj (:import
             [org.stellar.sdk CreateAccountOperation$Builder KeyPair Server]
             [org.stellar.sdk.responses AccountResponse])))

(defn from-keypair
  ^AccountResponse [^Server server ^KeyPair pair]
  (-> server
      (.accounts)
      (.account pair)))

(defn create!
  [^Server server ^KeyPair source ^KeyPair destination ^String starting-balance]
  (let [builder (doto (new CreateAccountOperation$Builder destination starting-balance)
                  (.setSourceAccount source))
        operation (.build builder)]
     (transaction/submit! server source [operation])))

(defn balances
  [^Server server ^KeyPair pair]
  (let [account (from-keypair server pair)
        balances (.getBalances account)]
    (map #(hash-map :type (.getAssetType %1)
                    :code (.getAssetCode %1)
                    :balance (.getBalance %1))
         balances)))


