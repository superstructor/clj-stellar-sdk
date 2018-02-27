(ns clj-stellar-sdk.asset
  #?(:clj
     (:import
      [org.stellar.sdk Asset AssetTypeNative])))

(defn create
  [code issuer]
  (Asset/createNonNativeAsset code issuer))

(defn native
  []
  (new AssetTypeNative))
