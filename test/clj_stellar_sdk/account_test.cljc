(ns clj-stellar-sdk.account-test
  (:require
    [clj-stellar-sdk.account :as account]
    [clj-stellar-sdk.keypair :as keypair]
    [clj-stellar-sdk.network :as network]
    [clj-stellar-sdk.server :as server]
    #?(:clj [clojure.test :refer [deftest are is testing use-fixtures]]
       :cljs [clojure.test :refer-macros [deftest are is testing use-fixtures]]))
  #?(:clj (:import [org.stellar.sdk.responses AccountResponse])))

(use-fixtures :once (fn [f] (network/use-public!) (f)))

(deftest from-keypair
  (is (instance? AccountResponse (account/from-keypair (server/testnet) (keypair/from-secret-seed "SBK5Q77CDEUOCHNDCOUOXXJ3TQJO7NP44H5WAB2G4IX7MICL65ANI2CG")))))

(deftest create!
  (let [source (keypair/from-secret-seed "SBK5Q77CDEUOCHNDCOUOXXJ3TQJO7NP44H5WAB2G4IX7MICL65ANI2CG")
        destination (keypair/random)]
    (is (= {:result :success :hash nil}
           (account/create! (server/testnet) source destination "1")))))

