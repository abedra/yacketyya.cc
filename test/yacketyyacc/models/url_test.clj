(ns yacketyyacc.models.url-test
  (:refer-clojure :exclude [chars])
  (:use clojure.test
        yacketyyacc.models.url))

(deftest random-string-test
  (testing "returns a string of length n characters"
    (are [result input] (= result (count (random-string input)))
         4 4
         5 5
         20 20)))

(deftest find-or-create-test
  (with-redefs [random-string (constantly "aaaa")]
    (testing "returns the url from the db when it finds the original url in the db"
      (with-redefs [find-by-original-url (constantly {:original_url "http://example.com"
                                                  :shortened_url "hIl0"})]
        (is (= {:original_url "http://example.com" :shortened_url "hIl0"}
               (find-or-create "http://example.com")))))
    (testing "returns a new url from the db when it does not find the original url"
      (with-redefs [find-by-original-url (constantly nil)]
        (is (not (nil? (find-or-create "http://example.com"))))))))