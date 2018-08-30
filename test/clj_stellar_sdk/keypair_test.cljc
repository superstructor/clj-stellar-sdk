(ns clj-stellar-sdk.keypair-test
  (:require
    [clj-stellar-sdk.keypair :as keypair]
    #?(:clj [clojure.test :refer [deftest are is testing use-fixtures]]
       :cljs [clojure.test :refer-macros [deftest are is testing use-fixtures]]))
  #?(:clj (:import [org.stellar.sdk KeyPair])))

(deftest from-account-id
  (is (instance? KeyPair (keypair/from-account-id "GBYCCAOTFGQCB2KDNDS7L34EPTNCKOQWDOO4UPAV2ATVP3DKGY2D25P2"))))

(deftest from-secret-seed
  (is (instance? KeyPair (keypair/from-secret-seed "SBK5Q77CDEUOCHNDCOUOXXJ3TQJO7NP44H5WAB2G4IX7MICL65ANI2CG"))))

(deftest random
  (is (instance? KeyPair (keypair/random))))

(deftest sign?
  (is (not (keypair/sign? (keypair/from-account-id "GBYCCAOTFGQCB2KDNDS7L34EPTNCKOQWDOO4UPAV2ATVP3DKGY2D25P2"))))
  (is (keypair/sign? (keypair/from-secret-seed "SBK5Q77CDEUOCHNDCOUOXXJ3TQJO7NP44H5WAB2G4IX7MICL65ANI2CG"))))

(deftest account-id
  (let [kp (keypair/from-secret-seed "SBK5Q77CDEUOCHNDCOUOXXJ3TQJO7NP44H5WAB2G4IX7MICL65ANI2CG")]
    (is (= "GBYCCAOTFGQCB2KDNDS7L34EPTNCKOQWDOO4UPAV2ATVP3DKGY2D25P2" (keypair/account-id kp)))))

(deftest secret-seed
  (let [kp (keypair/from-secret-seed "SBK5Q77CDEUOCHNDCOUOXXJ3TQJO7NP44H5WAB2G4IX7MICL65ANI2CG")]
    (is (= "SAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAABSU2" (keypair/secret-seed kp)))))
