(set-env!
  :exclusions   '[org.clojure/clojure]
  :dependencies '[[seancorfield/boot-tools-deps "0.4.5" :scope "test"]
                  [onetom/boot-lein-generate "0.1.3" :scope "test"]
                  [adzerk/boot-test "1.2.0" :scope "test"]])

(require '[clojure.edn :as edn]
         '[clojure.java.io :as io]
         '[boot-tools-deps.core :refer [deps load-deps]]
         '[boot.lein :as boot-lein]
         '[adzerk.boot-test :as boot-test])

(def default-repos
  [["maven-central" {:url "https://repo1.maven.org/maven2"}]
   ["clojars"       {:url      "https://clojars.org/repo/"
                     :username (get-sys-env "CLOJARS_USER" :required)
                     :password (get-sys-env "CLOJARS_PASS" :required)}]])

(def repos
  (reduce-kv #(conj %1 [%2 %3]) default-repos
             (:mvn/repos (edn/read-string (slurp (io/file "deps.edn"))))))

(set-env!
  :repositories repos)

(task-options! pom
               (edn/read-string
                 (slurp (io/file "project.edn"))))

(deftask lein
         []
         (load-deps
           {:overwrite-boot-deps true
            :repeatable true})
         (boot-lein/generate)
         identity)

(ns-unmap *ns* 'test)

(deftask test
         []
         (comp (deps
                 :overwrite-boot-deps true
                 :repeatable true
                 :aliases [:test])
               (boot-test/test
                 :include #"-test$")))

(deftask deploy
         []
         (comp (deps
                 :overwrite-boot-deps true
                 :repeatable true)
               (pom)
               (jar)
               (push :repo "clojars")))

