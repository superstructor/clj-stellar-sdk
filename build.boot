(set-env!
  :exclusions   '[org.clojure/clojure]
  :dependencies '[[org.clojure/clojure "1.9.0" :scope "provided"]]
  :repositories #(conj % ["clojars" {:url "https://clojars.org/repo/"
                                     :username (get-sys-env "CLOJARS_USER" :required)
                                     :password (get-sys-env "CLOJARS_PASS" :required)}]))

(task-options! pom
  (clojure.edn/read-string
    (slurp (clojure.java.io/file "project.edn"))))

(deftask deploy
  []
  (comp (pom) (jar) (push :repo "clojars")))

