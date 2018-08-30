(ns clj-stellar-sdk.keypair
  #?(:clj (:import
            [java.lang String]
            [org.stellar.sdk KeyPair])))

(defn from-account-id
  "Returns a new Stellar KeyPair from a strkey encoded Stellar account ID."
  ^KeyPair [^String account-id]
  (KeyPair/fromAccountId account-id))

(defn from-secret-seed
  "Returns a new Stellar KeyPair from a strkey encoded Stellar secret seed."
  ^KeyPair [^String seed]
  (KeyPair/fromSecretSeed (char-array seed)))

(defn random
  "Returns a random Stellar KeyPair."
  ^KeyPair []
  (KeyPair/random))

(defn sign?
  "Returns true if keypair is capable of signing."
  [^KeyPair keypair]
  (.canSign keypair))

(defn account-id
  ^String [^KeyPair keypair]
  (.getAccountId keypair))

(defn secret-seed
  ^String [^KeyPair keypair]
  (String/valueOf (.getSecretSeed keypair)))
