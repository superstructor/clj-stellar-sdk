(ns clj-stellar-sdk.options
  (:require
    [clj-stellar-sdk.signer :as signer])
  #?(:clj
     (:import
       [org.stellar.sdk KeyPair SetOptionsOperation$Builder]))
  (:refer-clojure :exclude [set]))

(defn set
  [{:keys [^KeyPair inflation-destination clear-flags set-flags master-key-weight
           low-threshold medium-threshold high-threshold ^String home-domain
           ^KeyPair signer-keypair signer-weight ^KeyPair source-account]}]
  (let [builder (cond-> (new SetOptionsOperation$Builder)

                        inflation-destination
                        (.setInflationDestination inflation-destination)

                        clear-flags
                        (.setClearFlags (.intValue clear-flags))

                        set-flags
                        (.setSetFlags (.intValue set-flags))

                        master-key-weight
                        (.setMasterKeyWeight (.intValue master-key-weight))

                        low-threshold
                        (.setLowThreshold (.intValue low-threshold))

                        medium-threshold
                        (.setMediumThreshold (.intValue medium-threshold))

                        high-threshold
                        (.setHighThreshold (.intValue high-threshold))

                        home-domain
                        (.setHomeDomain home-domain)

                        (and signer-keypair signer-weight)
                        (.setSigner (signer/from-keypair signer-keypair)
                                    (.intValue signer-weight))

                        source-account
                        (.setSourceAccount source-account))]
    (.build builder)))
