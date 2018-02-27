(ns clj-stellar-sdk.signer
  #?(:clj (:import
            [org.stellar.sdk Signer])))

(defn from-keypair
  [keypair]
  (Signer/ed25519PublicKey keypair))