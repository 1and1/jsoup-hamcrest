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

package net.oneandone.jsoup.hamcrest;

import static org.hamcrest.CoreMatchers.equalTo;

import java.util.function.Consumer;
import java.util.function.Function;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.jsoup.nodes.Document;

public class DocumentMatchers {
    private DocumentMatchers() {
    }

    /**
     * Asserts that the document has the given title
     *
     * @param value expeted title
     * @return matcher
     */
    public static Matcher<Document> hasTitle(String value) {
        return hasTitle(equalTo(value));
    }

    /**
     * Asserts that the document's title matches the given matcher
     *
     * @param matcher expeted title
     * @return matcher
     */
    public static Matcher<Document> hasTitle(Matcher<String> matcher) {
        return new DocumentPropertyMatcher<>(Document::title, matcher,
                description -> description.appendText("document has title ").appendDescriptionOf(matcher));
    }

    private static class DocumentPropertyMatcher<T> extends PropertyMatcher<Document, T> {
        DocumentPropertyMatcher(Function<Document, T> extractor, Matcher<T> matcher, Consumer<Description> description) {
            super(description, extractor, matcher);
        }
    }
}
