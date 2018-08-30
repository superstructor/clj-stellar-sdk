(ns clj-stellar-sdk.asset
  #?(:clj
     (:import
      [org.stellar.sdk Asset AssetTypeNative KeyPair])))

(defn create
  [^String code ^KeyPair issuer]
  (Asset/createNonNativeAsset code issuer))

(defn native
  ^AssetTypeNative []
  (new AssetTypeNative))
