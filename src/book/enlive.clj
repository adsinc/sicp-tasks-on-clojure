(ns book.enlive
  (:require [net.cgrand.enlive-html :as h]))

(h/defsnippet footer "footer.html" [:.footer]
              [message]
              [:.footer] (h/content message))

(h/deftemplate friends-list "friends.html"
               [username friends friends-class]
               [:.username] (h/content username)
               [:ul.friends :li] (h/clone-for [f friends]
                                              (h/do-> (h/content f)
                                                      (h/add-class friends-class)))
               [:body] (h/append (footer (str "Goodbuy, " username))))

(spit "res.html" (apply str (friends-list "Chas" ["Christophe", "Brian"] "programmer")))


