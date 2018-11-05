(ns ^:figwheel-hooks crossword.app
  (:require
   [goog.dom :as gdom]
   [reagent.core :as reagent :refer [atom]]))

;; define your app data so that it doesn't get over-written on reload
(defonce app-state (atom {:crossword-name "NYT Daily Mini: Sunday, October 28"}))
;(reset! app-state {:crossword-name "NYT Daily Mini: Sunday, October 28"})

(def example-crossword [
  [{}      {:n 1 :l \D} {:n 2 :l \U} {:n 3 :l \C} {:n 4 :l \K}]
  [{:n 5 :l \D} {:l \R}      {:l \D}      {:l \R}      {:l \E}]
  [{:n 6 :l \R} {:l \A}      {:l \D}      {:l \O}      {:l \N}]
  [{:n 7 :l \O} {:l \C}      {:l \E}      {:l \A}      {:l \N}]
  [{:n 8 :l \P} {:l \O}      {:l \R}      {:l \K}      {:l \Y}]
])

(defn get-app-element []
  (gdom/getElement "app"))

;; https://developer.mozilla.org/en-US/docs/Web/SVG/Tutorial/Getting_Started
;; https://developer.mozilla.org/en-US/docs/Web/SVG/Attribute/Presentation
;; https://www.w3.org/TR/css-color-3/#svg-color

(defn cell [{number :n, letter :l}]
  [:g
   [:rect {:width "60" :height "60" :x "3" :y "3"
           :stroke "black" :stroke-width "3" :fill "blue" :fill-opacity "0.1"}]
   [:text {:x "6" :y "15" :font-family "monospace"
           :font-size "12" :fill "darkgray"}
    number]
   [:text {:x "34" :y "44" :font-family "sans-serif" :font-size "30"
           :fill "black" :text-anchor "middle"}
    letter]])

(defn crossword [data]
  (doseq [row data]
    []))

(defn puzzle-ui [crossword-data]
  [:div
   [:h1 (:crossword-name @app-state)]
   [:svg  {:version "1.1" :baseProfile "full" :xmlns "http://www.w3.org/2000/svg"
           :width "300" :height "300"}
    [cell (-> crossword-data first second)]]])

(defn mount [el]
  (reagent/render-component [puzzle-ui example-crossword] el))

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
