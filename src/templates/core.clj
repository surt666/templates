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
