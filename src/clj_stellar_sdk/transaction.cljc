(ns clj-stellar-sdk.transaction
  #?(:clj
     (:import
       [org.stellar.sdk KeyPair Server Transaction$Builder])))

(defn submit!
  ([^Server server ^KeyPair source operations]
   (submit! server source operations [source]))
  ([^Server server ^KeyPair source operations signers]
   (let [source-account (-> server (.accounts) (.account source))
         builder        (new Transaction$Builder source-account)]
     (doseq [operation operations]
       (.addOperation builder operation))
     (let [transaction (.build builder)]
       (doseq [signer signers]
         (.sign transaction signer))
       (try
         (let [response (.submitTransaction server transaction)]
           {:result :success
            :hash   (.getHash response)})
         (catch Exception e
           {:result  :failure
            :message (.getMessage e)}))))))
