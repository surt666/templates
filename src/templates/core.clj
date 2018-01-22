(ns templates.core
  (:require [selmer.parser :as s])
  (:import [freemarker.template Configuration Template TemplateExceptionHandler]
           [java.io File OutputStreamWriter ByteArrayOutputStream ByteArrayInputStream]))


;; (defn get-templates []
;;   (IOUtils/readLines (.. (.getClassLoader (class dummy)) (getResourceAsStream "templates/")) Charsets/UTF_8))
(defn dummy [])

(def cfg (doto (Configuration. Configuration/VERSION_2_3_22) (.setClassForTemplateLoading (class dummy) "/fm-templates") (.setTemplateExceptionHandler TemplateExceptionHandler/RETHROW_HANDLER) (.setDefaultEncoding "UTF-8")))

(defn merge-template-fm [template data]
  (let [out (ByteArrayOutputStream.)
        writer (OutputStreamWriter. out)
        temp (.getTemplate cfg template)]
    (.process temp data writer)
  ;  (ByteArrayInputStream. (.toByteArray out))
    (String. (.toByteArray out))))

(s/set-resource-path! "/Users/w19807/projects/templates/selmer-templates")

(defn merge-template-s [template data]
  (s/render-file template data))

(def v {:delvurderinger [{:opfoerelsesaar 1960,
                          :omtilbygningsaar 1980,
                          :vaegtet-areal 130.9,
                          :grundareal 1200,
                          :tagdaekningsmateriale "Tegl",
                          :opvarmning "Fjernvarme"}
                         {:opfoerelsesaar 1920,
                          :omtilbygningsaar 1970,
                          :vaegtet-areal 180,
                          :grundareal 1800,
                          :tagdaekningsmateriale "Strå",
                          :opvarmning "Tegl"}]
        :termin 2018
        :vurderingsstyrelsen {:adrlinie1 "vurgade 11" :adrlinie2 "4000 Roskilde" :adrlinie3 "Danmark"}
        :modtager {:adrlinie1 "Brobergsgade 11 st. th." :adrlinie2 "1427 København" :adrlinie3 "Danmark" :ejerandel 100}
        :ejendom {:vurid 727272 :bfenr 282811 :nr 432424 :kommunenr 7727 :ejerlavnavn "Mit lille lav" :matrikelnr 722728 :adrlinie1 "Brobergsgade 11 st. th." :adrlinie2 "1427 København" :vej-areal 120 :kyst-afstand 2450}
        :sag {:sags-id 82828}
        :salg [{:nr 3 :adresse "Vejnavn 1, Kbh" :handelsdato "10/3-2012" :pris 900000}
               {:nr 3 :adresse "Vejnavn 9, Kbh" :handelsdato "10/3-2013" :pris 1000000}
               {:nr 3 :adresse "Vejnavn 7, Kbh" :handelsdato "10/3-2011" :pris 1200000}
               {:nr 3 :adresse "Vejnavn 4, Kbh" :handelsdato "10/3-2008" :pris 1300000}
               {:nr 3 :adresse "Vejnavn 3, Kbh" :handelsdato "10/3-2002" :pris 1400000}]})
