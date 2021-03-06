(ns ^:figwheel-hooks crossword.app
  (:require
   [goog.dom :as gdom]
   [reagent.core :as reagent :refer [atom as-element]]))

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

(defn cell [{number :n, letter :l} & {:keys [pos size]}]
  (let [[fill opacity] (if (every? nil? [number letter])
                         ["black" "1.0"] ["white" "0.1"])
        [x y] pos
        with-offset  #(str (+ (* size %1) %2))
        with-x-offset (partial with-offset x)
        with-y-offset (partial with-offset y)
        number-size   (str (* 0.2 size))
        letter-size   (str (* 0.5 size))]
    [:g
     [:rect {:width (str size) :height (str size)
             :x (with-x-offset 3) :y (with-y-offset 3)
             :stroke "black" :stroke-width "3"
             :fill fill :fill-opacity opacity}]
     [:text {:x (with-x-offset 6) :y (with-y-offset 15)
             :font-family "monospace" :font-size number-size
             :fill "darkgray"}
      number]
     [:text {:x (with-x-offset 34) :y (with-y-offset 44)
             :font-family "sans-serif" :font-size letter-size
             :fill "black" :text-anchor "middle"}
      letter]]))

(defn crossword [crossword-data & {:keys [size]}]
  (let [with-index (partial map-indexed vector)
        cell-size  (/ size (count (first crossword-data)))
        board-size (str (+ size 6))]
    [:svg  {:version "1.1" :baseProfile "full" :xmlns "http://www.w3.org/2000/svg"
            :width board-size :height board-size}
     (for [[y row]  (with-index crossword-data)
           [x item] (with-index row)]
       ^{:key (str (:l item) (:n item) x y)}
       [cell item :pos [x y] :size cell-size])]))

(defn puzzle-ui [crossword-data]
  [:div
   [:h1 (:crossword-name @app-state)]
   [crossword crossword-data :size 300]])

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
