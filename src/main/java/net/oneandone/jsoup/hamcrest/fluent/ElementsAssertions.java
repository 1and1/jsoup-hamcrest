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

import static org.hamcrest.Matchers.hasSize;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import net.oneandone.jsoup.hamcrest.DocumentMatcher;

public class ElementsAssertions implements DocumentMatcher {

    private List<Matcher<? super Elements>> matchers = new ArrayList<>();

    private final String css;

    ElementsAssertions(String css) {
        this.css = css;
    }

    protected String getCss() {
        return css;
    }

    @Override
    public void match(Document document) {
        Elements elements = document.select(getCss());
        MatcherAssert.assertThat(elements, new AllOfMatcher<>(
                description -> description.appendText("elements selected by ").appendValue(getCss())
                        .appendText(" matching "), matchers));
    }

    /**
     * Changes the fluent style to apply to each element hereafter instead
     *
     * @return
     */
    public EachElementAssertions each() {
        return new EachElementAssertions(css, this);
    }

    /**
     * Assert that a count of elements found by the css query.
     *
     * @param count the expected number of elements
     * @return this for fluent chaining
     */
    public ElementsAssertions hasCount(int count) {
        matchers.add(hasSize(count));
        return this;
    }
}
