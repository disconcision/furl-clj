(ns hello-world.core
    (:require [reagent.dom :as rdom]
    [reagent.core :as r]))

(enable-console-print!)

(println "FURL>>")

;; define your app data so that it doesn't get over-written on reload


(def text-buffer (r/atom {:text ">"}))

(def click-count (r/atom 0))

(defn update-buffer [ a e]
 (println "UPDATE" (String.fromCharCode (.-charCode e)))
 (update a :text #(str % (String.fromCharCode (.-charCode e)))))


(defn text-shit []
[:div
   
   [:input {:type "text"
           :value "type here"
           :on-key-press (fn [e]
                        (println  (String.fromCharCode (.-charCode e))"6666" (str "sdf" (.-charCode e)))
                        (swap! text-buffer update-buffer e)
                        (if (= 13 (.-charCode e))
                          (println "ENTER")
                          (println "NOT ENTER")))
                          }]
   [:p (-> @text-buffer :text str)]])

(defn box-text [t]
 (println "box-text" t)
  [:div (for [c t] [:span {:style {:display "inline-block ":width "24px" :height "24px" :background-color "#ddd" :margin "1px" :padding "7px" :font-size "1.5em"}} (str c)])])


(defn simple-component []
  (let [ s (:text @text-buffer) ]
  [:div 
   [:p "furl"]
   (text-shit)
   [box-text s]
   #_[:p.someclass
    "I have " [:strong "bold"]
    [:span {:style {:color "red"}} " and red "] "text."]]))



(defn render-simple []
  (rdom/render
    [simple-component]
    (.-body js/document)))

(defn on-js-reload []
  (render-simple)
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)

(render-simple)