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

/**
 * Collection of assertion methods
 */
public class DocumentAssertions {
    private DocumentAssertions() {
    }

    /**
     * Perform assertions on a single {@link org.jsoup.nodes.Element}.
     *
     * Will throw if more than one element is found.
     *
     * @param css the selector to use
     * @return fluent assertions for single element
     */
    public static SingleElementAssertions anElement(String css) {
        return new SingleElementAssertions(css);
    }

    /**
     * Perform assertions on each {@link org.jsoup.nodes.Element} selected by the selector.
     *
     * @param css the selector to use
     * @return fluent assertions for each element
     */
    public static EachElementAssertions eachElement(String css) {
        return new EachElementAssertions(css);
    }

    /**
     * Perform assertions on the {@link org.jsoup.select.Elements} collection returned.
     *
     * @param css the selector to use
     * @return fluent assertions for elements collection
     */
    public static ElementsAssertions elements(String css) {
        return new ElementsAssertions(css);
    }
}
