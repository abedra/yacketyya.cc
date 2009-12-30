(ns yacketyyacc.models.url-test
  (:use clojure.test
        yacketyyacc.models.url))

(deftest random-string-test
  (testing "returns a string of length n characters"
    (are [result input] (= result (count (random-string input)))
         4 4
         5 5
         20 20)))

(deftest find-or-create-test
  (binding [random-string (constantly "aaaa")]
    (testing "returns the url from the db when it finds the original url in the db"
      (binding [find-by-original-url (constantly {:original_url "http://example.com"
                                                  :shortened_url "hIl0"})]
        (is (= {:original_url "http://example.com" :shortened_url "hIl0"}
               (find-or-create "http://example.com")))))
    (testing "returns a new url from the db when it does not find the original url"
      (binding [find-by-original-url (constantly nil)]
        (is (not (nil? (find-or-create "http://example.com"))))))))