/*
 * Copyright 1&1 Internet AG, https://github.com/1and1/
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.oneandone.jsoup.hamcrest.fluent;

import static org.hamcrest.MatcherAssert.assertThat;

import org.hamcrest.Matcher;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import net.oneandone.jsoup.hamcrest.DocumentMatcher;

/**
 * Entrypoint to start assertions for documents.
 */
public class JsoupAssertions {

    private Document document;

    private JsoupAssertions(Document document) {
        this.document = document;
    }

    private JsoupAssertions(String html) {
        document = Jsoup.parse(html);
    }

    /**
     * Perform an assertion against the document see {@link DocumentAssertions}.
     *
     * @param matcher to evaluate
     * @return this for fluent assertions
     */
    public JsoupAssertions expect(DocumentMatcher matcher) {
        matcher.match(document);
        return this;
    }

    /**
     * Alias for {@link #expect(DocumentMatcher)} for readability in tests.
     *
     * @param matcher to evaluate
     * @return this for fluent assertions
     */
    public JsoupAssertions and(DocumentMatcher matcher) {
        return expect(matcher);
    }

    /**
     * Perform an assertion with a Hamcrest matcher against the document.
     *
     * @param matcher to evaluate
     * @return this for fluent assertions
     */
    public JsoupAssertions expect(Matcher<Document> matcher) {
        assertThat(document, matcher);
        return this;
    }

    /**
     * Alias for {@link #expect(Matcher)} for readability in tests.
     *
     * @param matcher to evaluate
     * @return this for fluent assertions
     */
    public JsoupAssertions and(Matcher<Document> matcher) {
        return expect(matcher);
    }

    /**
     * Creates a new JsoupAssertions for fluent assertions
     *
     * @param html the page source to parse
     * @return this for fluent assertions
     */
    public static JsoupAssertions html(String html) {
        return new JsoupAssertions(html);
    }

    /**
     * Creates a new JsoupAssertions for fluent assertions
     *
     * @param document already parsed jsoup document
     * @return this for fluent assertions
     */
    public static JsoupAssertions html(Document document) {
        return new JsoupAssertions(document);
    }
}
