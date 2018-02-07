(set-env!
  :exclusions   '[org.clojure/clojure]
  :dependencies '[[seancorfield/boot-tools-deps "0.4.0" :scope "test"]]
  :repositories #(conj % ["clojars" {:url "https://clojars.org/repo/"
                                     :username (get-sys-env "CLOJARS_USER" :required)
                                     :password (get-sys-env "CLOJARS_PASS" :required)}]))

(task-options! pom
  (clojure.edn/read-string
    (slurp (clojure.java.io/file "project.edn"))))

(require '[boot-tools-deps.core :refer [deps]])

(deftask deploy
  []
  (comp (deps
          :repeatable true
          :overwrite-boot-deps true)
        (pom)
        (jar)
        (push :repo "clojars")))

