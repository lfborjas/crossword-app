(ns ^:figwheel-hooks crossword.app
  (:require
   [goog.dom :as gdom]
   [reagent.core :as reagent :refer [atom]]))

;; define your app data so that it doesn't get over-written on reload
(defonce app-state (atom {:crossword-name "NYT Daily Mini"}))

(def example-crossword [
  [{}      {:n 1 :l \D} {:n 2 :l \U} {:n 3 :l \C} {:n 4 :l \K}]
  [{:n 5 :l \D} {:l \R}      {:l \D}      {:l \R}      {:l \E}]
  [{:n 6 :l \R} {:l \A}      {:l \D}      {:l \O}      {:l \N}]
  [{:n 7 :l \O} {:l \C}      {:l \E}      {:l \A}      {:l \N}]
  [{:n 8 :l \P} {:l \O}      {:l \R}      {:l \K}      {:l \Y}]
])

(defn get-app-element []
  (gdom/getElement "app"))

(defn cell [])

(defn crossword []
  [:div
   [:h1 (:crossword-name @app-state)]
   [:h3 "I guess this is cool. Is it fast?"]])

(defn mount [el]
  (reagent/render-component [crossword] el))

(defn mount-app-element []
  (when-let [el (get-app-element)]
    (mount el)))

;; conditionally start your application based on the presence of an "app" element
;; this is particularly helpful for testing this ns without launching the app
(mount-app-element)

;; specify reload hook with ^;after-load metadata
(defn ^:after-load on-reload []
  (mount-app-element)
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)
